package com.emotte.dubbo.order.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.emotte.dubbo.activities.ActivitiesEmottePayService;
import com.emotte.dubbo.activities.service.GJBAPPCardInterfaceService;
import com.emotte.dubbo.activities.solution.SolutionOrderInterfaceService;
import com.emotte.dubbo.emPay.PayFeeDBService;
import com.emotte.dubbo.model.DubboModel;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.dubbo.service.PushInterfaceService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.dubbo.wms.SubPackageService;
import com.emotte.kernel.arch.future.FutureWithKafka;
import com.emotte.kernel.arch.model.ResultData;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.kernel.redis.EJedisPool;
import com.emotte.kernel.spring.SpringContext;
import com.emotte.kernel.third.kafka.KafkaFactory;
import com.emotte.order.authmaner.mapper.reader.ReManagerMapper;
import com.emotte.order.authmaner.model.Manager;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.authorg.service.OrgService;
import com.emotte.order.constant.AfterSaleConstant;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.order.mapper.reader.*;
import com.emotte.order.order.mapper.writer.*;
import com.emotte.order.order.model.*;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.service.*;
import com.emotte.order.order.service.impl.OrderAfterSalesServiceImpl;
import com.emotte.order.thread.SendAfterSaleMsgThread;
import com.emotte.order.util.HttpUtil;
import com.emotte.order.util.LogisticsUtil;
import com.emotte.order.util.Tools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Service("orderInterfaceService")
public class OrderInterfaceServiceImpl implements OrderInterfaceService {

    @Resource
    private WrOrderMapper wrOrderMapper;
    @Resource
    private ReItemMapper reItemMapper;
    @Resource
    private WrItemMapper wrItemMapper;
    @Resource
    private OrderAfterSalesService orderAfterSalesService;
    @Resource
    private WrItemDetailServerMapper wrItemDetailServerMapper;
    @Resource
    private WrItemInterviewMapper wrItemInterviewMapper;
    @Resource
    private WrPayfeeMapper wrPayfeeMapper;
    @Resource
    private RePayfeeMapper rePayfeeMapper;
    @Resource
    private ReOrderMapper reOrderMapper;
    @Resource
    private ReOrderBaseMapper reOrderBaseMapper;
    @Resource
    private SubPackageService subPackageService;
    @Resource
    private PayFeeDBService payFeeDBService;
    @Resource
    private SolutionOrderInterfaceService activitiesInterfaceService;
    @Resource
    private OrderBaseService orderBaseService;
    @Resource
    private AccountPayService accountPayService;
    @Resource
    private WrOrderBaseMapper wrOrderBaseMapper;
    @Resource
    private SMSServiceDubbo sMSServiceDubbo;
    @Resource
    private OrderDistributeService distributeService;
    @Resource
    private OrderDistributeService orderDistributeService;
    @Resource
    private WrOrderTurnLogMapper wrOrderTurnLogMapper;
    @Resource
    private ReOrderTurnLogMapper reOrderTurnLogMapper;
    @Resource
    private OrderService orderService;
    @Resource
    private ReOrderAfterSalesMapper reOrderAfterSalesMapper;
    @Resource
    private RedisClient redisClient;
    @Resource
    private SMSServiceDubbo smsDubbo;
    @Resource
    private ReManagerMapper reManagerMapper;
    @Resource
    private GJBAPPCardInterfaceService cardInterfaceService;
    @Resource
    private PushInterfaceService pushInterfaceService;
    @Resource
    private ReItemDetailServerMapper reItemDetailServerMapper;
    @Resource
    private ItemInterviewService itemInterviewService;
    @Resource
    private OrgService orgService;
    @Resource
    private ReAccountPayMapper reAccountPayMapper;
    @Resource
    private ReActivityMoneyGradeMapper reActivityMoneyGradeMapper;

    @Resource
    private WrAfterSalesMapper wrAfterSalesMapper;

    @Resource
    private WrOrderAfterSalesMapper wrOrderAfterSalesMapper;

    @Resource
    private ActivitiesEmottePayService activitiesEmottePayService;

    @Resource
    private WrAccountPayMapper wrAccountPayMapper;

    @Resource
    private PayfeeService payfeeService;

    /**
     * 订单新增、修改和删除接口
     *
     * @param json
     * @return
     */
    @Override
    public String insertOrUpdateOrder(String json) {
        LogHelper.info(getClass() + ".insertOrUpdateOrder()", "接收的数据:" + json);
        Long start = System.currentTimeMillis();
        Long orderId = 0l;
        String msg = "请求操作失败";
        String result = "{'orderId':'','code':'1','msg':'" + msg + "'}";
        String orderCode = "";
        String catetype = "";
        Long accountId = 0L;
        try {
            if (json.startsWith("[{")) {
                json = json.substring(1, json.length() - 1);
            }
            JSONObject jsonObj = JSONObject.fromObject(json);
            if ("1".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
                if (jsonObj.get("order") != null) {
                    String[] oid = insertOrder(jsonObj).split("-");
                    if (oid.length > 2) {
                        orderCode = oid[2];//订单编号
                        catetype = oid[3];//订单类型

                    }
                    if ("0".equals(oid[0])) {
                        msg = oid[1];
                        if ("null".equals(msg)) {
                            msg = "当前库存不足，减少库存失败！";
                        }
                    } else {
                        orderId = Long.valueOf(oid[0]);
                        if (jsonObj.get("account") != null) {
                            accountId = Long.valueOf(oid[4]);
                        }
                    }
                }
            } else if ("2".equals(jsonObj.get("handle")) || "3".equals(jsonObj.get("handle"))|| jsonObj.getInt("handle") == 2 || jsonObj.getInt("handle") == 3) {
                orderId = updateOrder(jsonObj);
            }
            if (orderId != 0L) {
                if (accountId > 0) {
                    if (!"8".equals(catetype)) {
                        result = "{'orderId':'" + orderId + "','accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}";
                    } else {
                        result = "{'accountId':'" + accountId + "','code':'0','msg':'请求操作成功','orderCode':'" + orderCode + "'}";
                    }
                } else {
                    if (!"8".equals(catetype)) {
                        result = "{'orderId':'" + orderId + "','accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}";
                    } else {
                        result = "{'orderId':'" + orderId + "','code':'0','msg':'请求操作成功','orderCode':'" + orderCode + "'}";
                    }
                }
            } else {
                result = "{'orderId':'','code':'1','msg':'" + msg + "'}";
            }
        } catch (RuntimeException e) {
            String errorMsgInfo = e.getMessage();
            String[] errorMsg = errorMsgInfo.split("_");
            LogHelper.info(getClass(), "insertOrUpdateOrder()获取的异常字符串：" + errorMsgInfo);
            if ("orderUserFollow".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'订单分配门店失败!'}";
            } else if ("orderItem".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'当前商品无库存！'}";
            } else if ("order".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存order失败！'}";
            } else if ("orderItemServer".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存orderItemServer失败！'}";
            } else if ("orderItemInterview".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存orderItemInterview失败！'}";
            } else if ("feeAccount".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存feeAccount失败！'}";
            } else if ("lined".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存lined失败！'}";
            } else if ("account".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存account失败！'}";
            } else if ("fee".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'保存payfee失败！'}";
            } else if ("checkInventory".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'减少库存失败！'}";
            } else if ("orderItem".equals(errorMsg[0])) {
                result = "{'orderId':'','code':'1','msg':'商品不存在或无库存'}";
            } else {
                result = "{'orderId':'','code':'1','msg':'请求操作失败!'}";
            }
            //下单异常增加库存
            /*if (!"".equals(errorMsg[1])) {
				LogHelper.info(getClass()+ "insertOrUpdateOrder()","获取的库存json串："+errorMsg[1]);
				JSONObject backJson = JSONObject.fromObject(errorMsg[1]);
				Map map = (Map)backJson;
				String cityCode = map.get("cityCode").toString();//城市code
				String cateCode = map.get("cateCode").toString();//二级分类code
				String selectionTime = map.get("selectionTime").toString();//选择日期
				String startTime = map.get("startTime").toString();//开始时间段
				String endTime = map.get("endTime").toString();//结束时间段
				Integer num = Integer.valueOf(map.get("num").toString()) ;//服务人员个数
				Long ordId = Long.parseLong(map.get("orderId").toString()) ;//订单ID
				String back = orderService.increaseInventory(cityCode, cateCode, selectionTime, startTime, endTime,num,ordId);
				System.out.println("increaseInventory()获取增加库存返回的json串："+back);
				LogHelper.info(getClass() + ".increaseInventory()","获取增加库存返回的json串："+back);
			}*/
        } catch (Exception e) {
        }
        Long end = System.currentTimeMillis();
        String howLong = "->(调用时长:" + ((end - start) / 1000) + "秒)";
        LogHelper.info(getClass() + ".insertOrUpdateOrder()", "新增、修改订单结果:" + result + howLong);
        return result;

    }

    /**
     * 订单批量转单用接口
     *
     * @param json
     * @return JSONObject
     */
    @Override
    public JSONObject updateOrderForTrack(String json) {
        JSONObject ret = new JSONObject();
        String result = "{'rechargeBy':'','code':'1','msg':'请求操作失败'}";
        try {
            LogHelper.info(getClass() + ".updateOrderForTrack()", "接收的数据:" + json);
            JSONObject jsonObj = JSONObject.fromObject(json);
            String msg = checkHandoverParam(jsonObj);
            if (msg != null && !"".equals(msg)) {
                return JSONObject.fromObject("{'rechargeBy':'','code':'1','msg':'" + msg + "'}");
            }
            Order order = new Order();
            order.setUpdateBy(jsonObj.getLong("updateBy"));
            order.setFollowBy(jsonObj.getLong("followBy"));//离职人ID
            order.setRechargeBy(jsonObj.getLong("rechargeBy"));
            order.setRechargeDept(jsonObj.getLong("rechargeDept"));
            Map<String, String> solutionUserMobile = this.reOrderMapper.querySolutionUserMobile(order);//客户手机号
            int quantity_solution = this.wrOrderMapper.updateOrderFollowUpdate_solution(order);
            Map<String, String> orderUserMobile = this.reOrderMapper.queryOrderUserMobile(order);//客户手机号
            List<Order> list = this.reOrderMapper.queryOrderRechargeBy(order);
            int quantity = this.wrOrderMapper.updateOrderFollowUpdate(order);
            if (quantity > 0 && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    OrderTurnLog orderTurnLog = new OrderTurnLog();
                    orderTurnLog.setOrderId(list.get(i).getId());
                    orderTurnLog.setTurnBy(jsonObj.getLong("followBy"));//离职人id
                    orderTurnLog.setTurnDept(jsonObj.getLong("followDept"));//离职人部门id
                    orderTurnLog.setRechargeBy(jsonObj.getLong("rechargeBy"));//接收人id
                    orderTurnLog.setRechargeDept(jsonObj.getLong("rechargeDept"));//接收人部门id
                    orderTurnLog.setCreateBy(jsonObj.getLong("updateBy"));//操作人id
                    orderTurnLog.setCreateDept(jsonObj.getLong("updateDept"));//操作人部门id
                    this.wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);
                }
            }
            result = "{'rechargeBy':'" + order.getRechargeBy() + "','code':'0','msg':'请求操作成功'}";
            ret.accumulate("order", quantity);
            ret.accumulate("solution", quantity_solution);
            ret.accumulate("orderUserMobile", orderUserMobile != null ? orderUserMobile.get("userMobile") : "");
            ret.accumulate("solutionUserMobile", solutionUserMobile != null ? solutionUserMobile.get("userMobile") : "");
            ret.accumulate("result", result);
            LogHelper.info(getClass() + ".updateOrderForTrack()", "返回的数据:" + ret);
        } catch (Exception e) {
        }
        return ret;
    }
    /**
     * 订单批量转单用接口
     *
     * @param json
     * @return
     *//*
	public String updateOrderForTrack(String json){
		String result = "{'rechargeBy':'','code':'1','msg':'请求操作失败'}";
		try{
			System.out.println("updateOrderForTrack:" +json);
			JSONObject jsonObj = JSONObject.fromObject(json);
			Order order = new Order();
			order.setUpdateBy(jsonObj.getLong("updateBy"));
			order.setFollowBy(jsonObj.getLong("followBy"));
			order.setRechargeBy(jsonObj.getLong("rechargeBy"));
			order.setRechargeDept(jsonObj.getLong("rechargeDept"));
			this.wrOrderMapper.updateOrderFollowUpdate(order);
			result = "{'rechargeBy':'" +order.getRechargeBy() +"','code':'0','msg':'请求操作成功'}";
		}catch(Exception e){}
		return result ;
	}
	*/

    /**
     * 用户分包，转包用
     * json包含一个参数 custId
     */
    @Override
    @Transactional
    public String updateOrderFollow(String json) {
        System.out.println("updateOrderFollow:" + json);
        String result = "{'custId':'','code':'1','msg':'请求操作失败'}";
        try {
            JSONObject jsonObj = JSONObject.fromObject(json);
            Long custId = jsonObj.getLong("custId");
            Long updateBy = jsonObj.getLong("updateBy");
//			Long followBy = jsonObj.getLong("followBy");
//			Long followDept = jsonObj.getLong("followDept");
            Order order = new Order();
            order.setUserId(custId);
            order.setUpdateBy(updateBy);
//			order.setFollowBy(followBy);
//			order.setFollowDept(followDept);
            this.wrOrderMapper.updateOrderFollow(order);
            result = "{'custId':'" + custId + "','code':'0','msg':'请求操作成功'}";
            System.out.println(result);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 结算单新增、修改、删除
     * @param json
     * @return {'accountId':'530750500095049','code':'0','msg':'请求操作成功'}
     * @return {'accountId':'530750500095049,195404876944457','code':'0','msg':'请求操作成功'}
     * @return {'accountId':'','code':'1','msg':'请求操作失败'}
     */
    @Override
    public String insertOrUpdateAccount(String json) {
        LogHelper.info(getClass() + ".insertOrUpdateAccount()", "参数:" + json);
        String accountId = "";
        String result = "{'accountId':'','code':'1','msg':'请求操作失败'}";
        try {
            if (json.startsWith("[{")) {
                json = json.substring(1, json.length() - 1);
            }
            JSONObject jsonObj = JSONObject.fromObject(json);
            String[] acod = accountAdd(jsonObj).split("-");
            accountId = acod[0];
            if(null != accountId && !"".equals(accountId)){
            	result = "{'accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}";
            }
        } catch (Exception e) {
            LogHelper.error(getClass() + ".insertOrUpdateAccount()","异常:" + e);
        }
        LogHelper.info(getClass() + ".insertOrUpdateAccount()", "返回:" + result);
        return result;
    }

    /**
     * 缴费单新增、修改、删除
     * @param json
     * @return {'payfeeId':'565549676747849','code':'0','msg':'请求操作成功'}
     * @return {'payfeeId':'565549676747849,689160846613577','code':'0','msg':'请求操作成功'}
     * @return {'payfeeId':'','code':'1','msg':'请求操作失败'}
     */
    @Override
    public String insertOrUpdatePayfee(String json) {
        LogHelper.info(getClass() + ".insertOrUpdatePayfee()", "接收：" + json);
        String payfeeId = "";
        String result = "{'payfeeId':'','code':'1','msg':'请求操作失败'}";
        try {
            if (json.startsWith("[{")) {
                json = json.substring(1, json.length() - 1);
            }
            JSONObject jsonObj = JSONObject.fromObject(json);
            payfeeId = payfeeAdd(jsonObj);
            if(null != payfeeId && !"".equals(payfeeId)){
            	result = "{'payfeeId':'" + payfeeId + "','code':'0','msg':'请求操作成功'}";
            }
        } catch (Exception e) {
            LogHelper.error(getClass() + ".insertOrUpdatePayfee()", "异常:" + e);
        }
        LogHelper.info(getClass() + ".insertOrUpdatePayfee()", "返回：" + result);
        return result;
    }

    /**
	 * 结算和缴费同时操作
	 * @param json
     * @return {'accountId':'565549676747849','code':'0','msg':'请求操作成功'}
     * @return {'accountId':'565549676747849,689160846613577','code':'0','msg':'请求操作成功'}
     * @return {'accountId':'','code':'1','msg':'请求操作失败'}
	 */
    @Override
    public String insertOrUpdateAccountAndPayfee(String json) {
        LogHelper.info(getClass() + ".insertOrUpdateAccountAndPayfee()", "接收：" + json);
        String result = "{'accountId':'','code':'1','msg':'请求操作失败'}";
        String accountId = "";//结算单id
        String payKind = "";//业务类型
        try {
            if (json.startsWith("[{")) {
                json = json.substring(1, json.length() - 1);
            }
            JSONObject jsonObj = JSONObject.fromObject(json);
            String[] acod = accountAndpayfee(jsonObj).split("-");
            accountId = acod[0];
            payKind = acod[1];
            Integer toCardPay = jsonObj.get("toCardPay")==null?null:jsonObj.getInt("toCardPay");//是否卡扣费标识(1:是、其它:否)
            if ("2".equals(payKind) || (toCardPay != null && toCardPay == 1)) {
                String pjson = "{'orderId':'" + accountId + "'}";
                LogHelper.info(getClass() + ".insertOrUpdateAccountAndPayfee()", "payCost支付参数：" + pjson);
                String payCost = this.payFeeDBService.payCost(pjson);
                LogHelper.info(getClass() + ".insertOrUpdateAccountAndPayfee()", "payCost返回参数：" + payCost);
                if (!"success".equals(payCost)) {
                    this.wrPayfeeMapper.updateAccountById(Long.valueOf(accountId));
                    this.wrPayfeeMapper.updatePayfeeByAccountId(Long.valueOf(accountId));
                } else {
                    result = "{'accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}";
                }
            } else {
                result = "{'accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}";
            }
        } catch (Exception e) {
            LogHelper.error(getClass() + ".insertOrUpdateAccountAndPayfee()", "异常信息:" + e);
        }
        LogHelper.info(getClass() + ".insertOrUpdateAccountAndPayfee()", "返回：" + result);
        return result;
    }

    /**
     * 更新售后单状态接口
     */
    @Override
    public String updateAfterSales(String afterSaleJson) {
        Long afterSaleId = 0l;
        String msg = "请求操作失败";
        String result = "{'afterSaleId':'','code':'1','msg':'" + msg + "'}";
        try {
            System.out.println("接收的afterSaleJson:" + afterSaleJson);
            if (afterSaleJson.startsWith("[{")) {
                afterSaleJson = afterSaleJson.substring(1, afterSaleJson.length() - 1);
            }
            JSONObject asJson = JSONObject.fromObject(afterSaleJson);
            LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单更新接收json串:" + asJson);
            if ("2".equals(asJson.get("handle")) || asJson.getInt("handle") == 2) {
                Object obj = JSONObject.toBean(asJson, OrderAfterSales.class);
                OrderAfterSales afterSales = (OrderAfterSales) obj;
                this.orderAfterSalesService.updateOrderAfterSales(afterSales);
                afterSaleId = afterSales.getId();
            }
            if (afterSaleId != 0L) {
                result = "{'afterSaleId':'" + afterSaleId + "','code':'0','msg':'请求操作成功'}";
            } else {
                result = "{'afterSaleId':'','code':'1','msg':'" + msg + "'}";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /// ******************** @Transactional ****************************///

    /**
     * 订单新增
     *
     * @param jsonObj
     * @return
     */
    @Transactional
    public String insertOrder(JSONObject jsonObj) {
        Long orderId = 0l;
        String accountId = "";//结算单id
        JSONArray jsonOrder = JSONArray.fromObject(jsonObj.get("order"));
        JSONObject jobjOrder = JSONObject.fromObject(jsonOrder.get(0));
        Object objOrder = JSONObject.toBean(jobjOrder, Order.class);
        Order order = (Order) objOrder;
        // 判断订单必填字段
        String checkedOrder = checkedOrder(order);
        if (!"".equals(checkedOrder)) {
            return orderId + "-" + checkedOrder;
        }
        //判断保洁类,自营单次订单，库存是否充足，是则减库存
        String checkInventory = "";
        //生成订单ID
        orderId = this.reOrderMapper.queryOrderId();
        order.setId(orderId);
        Long itemId = 0L;
        BigDecimal totalPay = new BigDecimal(0);
        String msg = "请求操作失败";
        //商品市场价 add20181113
        BigDecimal marketPrice = new BigDecimal(0);
        // 保存商品信息
        if (jsonObj.get("orderItem") != null) {
            JSONArray jsonItem = JSONArray.fromObject(jsonObj.get("orderItem"));
            for (Object object : jsonItem) {
                Item item = new Item();
                JSONObject jobjItem = JSONObject.fromObject(object);
                Object objItem = JSONObject.toBean(jobjItem, Item.class);
                item = (Item) objItem;
                item.setOrderId(order.getId());
                item.setId(null);
                String productCode = item.getProductCode();//商品编号
                if (productCode != null && !"".equals(productCode)) {
                    //根据商品code查询商品市场价 add20181113
                    BigDecimal itemMarketPrice = reItemMapper.findMarketPriceByProductCode(productCode);
                    // 如果是下列订单，给订单一个productcode，用于分包
                    if (order.getCateType() == 1 || order.getCateType() == 2 || order.getCateType() == 8) {
                        order.setProductCode(productCode);
                    }
                    CityProduct cp = new CityProduct();
                    cp.setDictCode(order.getPriceType());
                    cp.setCityCode(order.getCity());
                    cp.setProductCode(productCode);
                    List<CityProduct> productList = null;
                    if (order.getCateType() == 3) {
                        productList = this.reItemMapper.queryForDictPriceFA(cp);
                    } else {
                        productList = this.reItemMapper.queryForDictPrice(cp);
                    }
                    if (productList != null && productList.size() > 0) {
                        CityProduct cpt = productList.get(0);
                        if (order.getParentId() == null) {
                            item.setNowPrice(cpt.getMarketPrice());
                            //判断自营单次订单，计算订单总额
                            if (order.getCateType() != null && order.getCateType() == 1) {
                                //订单是否为日常保洁或者擦玻璃
                                BigDecimal personNum = new BigDecimal(0);
                                if (order.getOrderType() != null && order.getOrderType() != ""
                                        && (order.getOrderType().equals(CommonConstant.ORDER_DAILY_CLEANNING_CATEGORYCODE)//日常保洁
                                        || order.getOrderType().equals(CommonConstant.ORDER_CLEAN_THE_GLASS_CATEGORYCODE))//擦玻璃
                                        ) {
                                    personNum = checkOrderType(jsonObj);
                                }
                                if (personNum.compareTo(BigDecimal.ZERO) != 0) {//服务人员个数不为0，单价X小时 X人数
                                    totalPay = totalPay.add(item.getNowPrice().multiply(new BigDecimal(item.getQuantity()).multiply(personNum)));
                                    marketPrice = marketPrice.add(itemMarketPrice.multiply(new BigDecimal(item.getQuantity()).multiply(personNum)));
                                    LogHelper.info(getClass(),"计算市场价"+marketPrice+"/"+item.getQuantity()+"/"+personNum);
                                } else {//单价X数量
                                    totalPay = totalPay.add(item.getNowPrice().multiply(new BigDecimal(item.getQuantity())));
                                    marketPrice = marketPrice.add(itemMarketPrice.multiply(new BigDecimal(item.getQuantity())));
                                    LogHelper.info(getClass(),"计算市场价"+marketPrice+"/"+item.getQuantity());
                                }
                            } else {
                                totalPay = totalPay.add(cpt.getMarketPrice().multiply(new BigDecimal(item.getQuantity())));
                                marketPrice = marketPrice.add(itemMarketPrice.multiply(new BigDecimal(item.getQuantity())));
                                LogHelper.info(getClass(),"计算市场价"+marketPrice+"/"+item.getQuantity());
                            }
                        } else {
                            totalPay = totalPay.add(item.getNowPrice().multiply(new BigDecimal(item.getQuantity())));
                            marketPrice = marketPrice.add(itemMarketPrice.multiply(new BigDecimal(item.getQuantity())));
                            LogHelper.info(getClass(),"计算市场价"+marketPrice+"/"+item.getQuantity());
                        }
                        item.setCategoryCode(cpt.getCategoryCode());
                        item.setProductInventoryId(cpt.getProductInventoryId());
                        item.setProductPriceType(item.getProductPriceType() == null ? cpt.getProductPriceType() : item.getProductPriceType());
                        item.setProductSpec(item.getProductSpec() == null ? cpt.getProductSpec() : item.getProductSpec());
                        item.setProductUnit(item.getProductUnit() == null ? cpt.getProductUnit() : item.getProductUnit());
                        item.setProductImg(cpt.getProductImg());
                        this.wrItemMapper.insertItem(item);
                        itemId = item.getId();
                    } else {
                        //当前商品不存在
                        throw new RuntimeException("orderItem_" + checkInventory);
                    }
                }
            }
        }
        // 商品订单加运费
        if (order.getCateType() == 3 && null != order.getDeliverPay()) {
            totalPay = totalPay.add(order.getDeliverPay());
        }
        if (totalPay.doubleValue() != 0) {
            order.setTotalPay(totalPay);
        }
        // 分发处理
        if (1 == order.getCateType() || 2 == order.getCateType() || 3 == order.getCateType()) {
            //呼叫中心快速下单
            if (null == order.getRechargeBy() || 0 == order.getRechargeBy()) {
                orderDistributeService.distribute(order);
                order.setOrderFenfa("2");
            } else if (null != order.getRechargeDept()) { //手动分发
                order.setOrderFenfa("1");
            } else if (order.getRechargeBy() != null && order.getOrderChannel() != null
                    && CommonConstant.ORDER_CHANNEL_SHANGQIAO.equals(order.getOrderChannel())) {//APP商桥渠道特殊设置
                //网络运营部 郭沙沙账号  scb-guoss
                order.setCreateBy(95965l);
//				order.setCreateDept(990022905588444l);
                order.setRechargeBy(95965l);
                order.setRechargeDept(990022905588444l);
                order.setOrderFenfa("2");//商桥控制自动分发
            }
        }

        String phones = null;

        //如果是他营三方订单，不给负责人和负责人部门
        if (order.getCateType() != null && order.getCateType() == 4) {
            order.setRechargeBy(null);
            order.setRechargeDept(null);
        }
        //来源(20180001:移动app,20180008:IOS,20180009:安卓,20180010:微站,20180011:微信)
        if ("20180011".equals(order.getOrderSourceId()) || "20180001".equals(order.getOrderSourceId()) || "20180008".equals(order.getOrderSourceId()) || "20180009".equals(order.getOrderSourceId()) || "20180010".equals(order.getOrderSourceId())) {
            if (order.getCateType() != null && order.getCateType() == 8) {
                Long rechargeBy = order.getRechargeBy();
                Long rechargeDept = order.getRechargeDept();
                if ((rechargeBy == null && rechargeDept == null) || (rechargeBy == 0L && rechargeDept == 0L)) {
                    //order.setRechargeBy(95215l);//负责人由陈爱华改为陈华
                    //order.setRechargeDept(990022905588483l);
                    //order.setRechargeBy(96387l);//负责人由陈华改为王增环
                    //order.setRechargeDept(990022905588482l);//呼入组新
                    //order.setRechargeBy(95898L);//2018年4月23日负责人由王增环改为刘文平
                    //order.setRechargeDept(12619003L);//呼叫中心管家部
                    //order.setRechargeBy(97261L);//2018年5月负责人由刘文平改为李金生
                    //order.setRechargeDept(990022905588716L);//网络运营部
                    order.setRechargeBy(95898L);//2018年6月19日负责人由李金生改为刘文平
                    order.setRechargeDept(12619003L);//呼叫中心管家部

                }
            }
        }
        if (order.getCateType() != null && order.getCateType() == 2) {
            order.setTotalPay(new BigDecimal(0));
        }
        if ("20180001".equals(order.getOrderSourceId())) {
            // 移动APP来源订单创建人部门置为0
            order.setCreateDept(0L);
            if (order.getRechargeBy() != null && order.getOrderChannel() != null
                    && CommonConstant.ORDER_CHANNEL_SHANGQIAO.equals(order.getOrderChannel())) {
                order.setCreateDept(990022905588444l);
            }
        }
        // 自营单次和延续，下单默认为已受理
        if (order.getCateType() != null && (order.getCateType() == 1 || order.getCateType() == 2)) {
            order.setOrderStatus(2);
        }

        Long serverId = 0L;
        // 保存服务订单需求信息
        ItemDetailServer itemDetailServer = new ItemDetailServer();
        if (jsonObj.get("orderItemServer") != null) {
            JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("orderItemServer"));
            if (jsonServer.size() > 0) {
                JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                Object objServer = JSONObject.toBean(jobjServer, ItemDetailServer.class);
                itemDetailServer = (ItemDetailServer) objServer;
                itemDetailServer.setOrderId(order.getId());
                itemDetailServer.setItemId(itemId);
                itemDetailServer.setId(null);
                //钟点工时间转换
                if (itemDetailServer.getHours() != null && !"".equals(itemDetailServer.getHours())) {
                    itemDetailServer.setHours(Tools.ChangeTime(itemDetailServer.getHours()));//时间段转换
                }
                try {
                    this.wrItemDetailServerMapper.insertItemDetailServer(itemDetailServer);
                } catch (Exception e) {
                    throw new RuntimeException("itemDetailServer_" + checkInventory);
                }
                serverId = itemDetailServer.getId();
            }
        }
        // 保存服务订单服务人员信息
        if (jsonObj.get("orderItemInterview") != null) {
            LogHelper.info(getClass() + ".insertItemInterview()", "添加上户记录-------");
            JSONArray jsonInterview = JSONArray.fromObject(jsonObj.get("orderItemInterview"));
            if (jsonInterview.size() > 0) {
                for (Object object : jsonInterview) {
                    ItemInterview itemInterview = new ItemInterview();
                    JSONObject jobjInterview = JSONObject.fromObject(object);
                    Object objInterview = JSONObject.toBean(jobjInterview, ItemInterview.class);
                    itemInterview = (ItemInterview) objInterview;
                    itemInterview.setOrderId(order.getId());
                    itemInterview.setOrderItemId(serverId);
                    itemInterview.setId(null);
                    //延续订单下单，下户时间EndTime不存
                    if (order.getCateType() == 2) {
                        itemInterview.setEndTime(null);
                    }
                    //对人下单接口，如果有服务人员，将订单状态改为匹配中
                    if (itemInterview.getPersonId() != null) {
                        order.setOrderStatus(3);
                    }
                    LogHelper.info(getClass() + ".insertItemInterview()", "添加上户记录开始");
                    try {
                        this.wrItemInterviewMapper.insertItemInterview(itemInterview);
                        //占用服务人员排期
                        LogHelper.info(getClass() + ".lindDays()", "占用排期开始");
                        //给排期传标记值
                        itemDetailServer.setMarkType(1);
                        this.itemInterviewService.lindDays(itemDetailServer, itemInterview.getPersonId(), 4);
                        LogHelper.info(getClass() + ".lindDays()", "占用排期结束");
                        //订单设置取消的redis
                        RedisClient redisClient = (RedisClient) SpringContext.getApplicationContext().getBean("redisClient");
                        //当前时间+20分钟
                        long time = new Date().getTime() + 20 * 60 * 1000;
                        redisClient.zadd(CommonConstant.ORDER_CANCLE_EMP_SCHEDULE, time, "{orderId:" + orderId + ",empId:" + itemInterview.getPersonId() + "}");
                        LogHelper.info(getClass() + ".zadd()", "发送redis参数：" + "{orderId:" + orderId + ",empId:" + itemInterview.getPersonId() + "}");
                        //推送服务人员
						/*String title="您有一条新的管家帮订单，请到订单列表里查询";
						String description="您有一条新的\"管家帮\"订单，请到订单列表里查询";
						String pushmsg = "您有一条新的管家帮订单，请到订单列表里查询" ;
						Integer push = this.pushInterfaceService.push(itemInterview.getPersonId(), 1L, title, description, pushmsg) ;
						LogHelper.info(getClass() + ".pushInterfaceService.push()","推送服务人员排期："+push);*/
                    } catch (Exception e) {
                        LogHelper.error(getClass(), ".insertItemInterview()" + e.getMessage(), e);
                        throw new RuntimeException("itemInterview_" + checkInventory);
                    }
                }
            }
        }
        //订单下单位置调换
        try {
            //将订单分包和分包部门及分包时间置空
            this.wrOrderMapper.insertOrder(order);
            //添加转单初始数据
            OrderTurnLog orderTurnLog = new OrderTurnLog();
            orderTurnLog.setOrderId(order.getId());
            orderTurnLog.setRechargeBy(order.getRechargeBy());
            orderTurnLog.setRechargeDept(order.getRechargeDept());
            orderTurnLog.setRemark("初始记录");
            wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);
            //插入订单轮询负责管家记录，并给管家发送短信
            if ((order.getIsNewFF() == 1 || order.getIsNewFF() == 3)
                    && order.getRechargeBy() != null && !"".equals(order.getRechargeBy()) && order.getCateType() == 2) {
                if (order.getIsNewFF() == 1) {
                    Org org = new Org();
                    org.setOrderId(order.getId());
                    org.setManager(order.getRechargeBy().toString());
                    orgService.insertPollRecord(org);
                }
                //查询订单类型
                Dictionary dictionary = new Dictionary();
                dictionary.setDvalue(order.getOrderType());
                dictionary = orderBaseService.queryCategoryType(dictionary);
                //查询管家电话
                Managers managers = new Managers();
                managers.setId(order.getRechargeBy());
                List<Managers> managerList = orderBaseService.queryguanjia(managers);
                String userName = " ";
                if (order.getUserName() != null && !"".equals(order.getUserName()) && !"null".equals(order.getUserName())) {
                    userName = order.getUserName();
                }
                //发给管家短信
                if (managerList.size() != 0 && !managerList.isEmpty()) {
                    JSONObject smsData2 = new JSONObject();
                    smsData2.accumulate("mobiles", managerList.get(0).getPhone());
                    smsData2.accumulate("templet", "order_423"); //
                    smsData2.accumulate("channel", "sys");
                    smsData2.accumulate("system", "order");
                    smsData2.accumulate("rate", "1"); //1即时发送，2定时发送
                    smsData2.accumulate("params", new String[]{userName, order.getUserMobile(), dictionary.getDvalue(), order.getOrderCode(),});
                    String res = this.sMSServiceDubbo.send(smsData2.toString());
                    // 返回结果(success/fail:失败原因)
                    LogHelper.info(getClass() + ".send()", "发送短信结果：" + res);
                }
            }
            if (phones != null) {
                JSONObject smsData = new JSONObject();
                smsData.accumulate("mobiles", phones);
                smsData.accumulate("templet", "order_142"); //
                smsData.accumulate("channel", "sys");
                smsData.accumulate("system", "order");
                smsData.accumulate("rate", "1"); //1即时发送，2定时发送
                smsData.accumulate("params", new String[]{order.getOrderCode(), order.getUserName(), order.getUserMobile()});
                // 返回结果(seccess/fail:失败原因)
                try {
                    // 限制只有北京地区的订单才发送短信通知
                    if (order.getCity().substring(0, 9).equals("101001001")) {
                        String res = this.sMSServiceDubbo.send(smsData.toString());
                        if ("fail".equals(res)) {
                            System.out.println("orderCode:" + order.getOrderCode() + " sen message fail!");
                        }
                    }
                } catch (Exception e) {
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("order_" + checkInventory);
        }

        // 保存延续订单需求排期
        if (jsonObj.get("lined") != null) {
            JSONArray jsonLined = JSONArray.fromObject(jsonObj.get("lined"));
            if (jsonLined.size() > 0) {
                JSONObject jobjLined = JSONObject.fromObject(jsonLined.get(0));
                Object objLined = JSONObject.toBean(jobjLined, ItemDetailServer.class);
                ItemDetailServer serverLined = (ItemDetailServer) objLined;
                serverLined.setOrderId(order.getId());
                serverLined.setId(null);
                try {
                    this.wrItemDetailServerMapper.insertOrderServerLined(serverLined);
                } catch (Exception e) {
                    throw new RuntimeException("serverLined_" + checkInventory);
                }

            }
        } else {
            JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("orderItemServer"));
            if (jsonServer.size() > 0 && (order.getCateType() == 1 || order.getCateType() == 2)) {
                ItemDetailServer additemDetailServer = new ItemDetailServer();
                JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                Object objLined = JSONObject.toBean(jobjServer, ItemDetailServer.class);
                if (objLined != null) {
                    ItemDetailServer itemDetail = (ItemDetailServer) objLined;
                    additemDetailServer.setOrderId(order.getId());
                    additemDetailServer.setId(null);
                    additemDetailServer.setCreateBy(order.getCreateBy());
                    additemDetailServer.setCreateTime(DateUtil.getCurrDateTime());
                    additemDetailServer.setStartTime(itemDetail.getStartTime());
                    additemDetailServer.setEndTime(itemDetail.getEndTime());
                    if (order.getCateType() == 1) {
                        additemDetailServer.setLinedType(1);
                    } else if (order.getCateType() == 2) {
                        //钟点工
                        if (order.getOrderType().equals("100200040002")) {
                            additemDetailServer.setLinedType(2);
                        } else {
                            additemDetailServer.setLinedType(3);
                        }
                    }
                    try {
                        this.wrItemDetailServerMapper.insertOrderServerLined(additemDetailServer);
                    } catch (Exception e) {
                        throw new RuntimeException("serverLined_" + checkInventory);
                    }
                }
            }
        }
        // 保存结算单，保存订时需要同时也保存结算单的才用到
         if (jsonObj.get("account") != null) {
            JSONArray jsonAccount = JSONArray.fromObject(jsonObj.get("account"));
            if (jsonAccount.size() > 0) {
                JSONObject jobjAccount = JSONObject.fromObject(jsonAccount.get(0));
                Object objAccount = JSONObject.toBean(jobjAccount, Payfee.class);
                Payfee feeAccount = (Payfee) objAccount;
                feeAccount.setOrderId(order.getId());
                feeAccount.setId(null);
                feeAccount.setMarketPrice(marketPrice);//订单商品市场价汇总
                if (order.getCateType() != null && order.getCateType() != 2) {
                    feeAccount.setAccountAmount(order.getTotalPay());
                }
                if (feeAccount.getIsManual() == null) {//isManual为空，如果来源是前台的，设置结算单isManual为2,否则为1
                    //来源：移动app，iOS，安卓，微站
                    if ("20180011".equals(order.getOrderSourceId()) || "20180001".equals(order.getOrderSourceId()) || "20180008".equals(order.getOrderSourceId()) || "20180009".equals(order.getOrderSourceId()) || "20180010".equals(order.getOrderSourceId())) {
                        feeAccount.setIsManual(2);
                    } else {
                        feeAccount.setIsManual(1);
                    }
                }
                if (feeAccount.getIsBackType() == null) feeAccount.setIsManual(2);
                try {
                    this.wrPayfeeMapper.insertAccount(feeAccount);
                } catch (Exception e) {
                    throw new RuntimeException("feeAccount_" + checkInventory);
                }
                if(null != feeAccount.getId()){
                	accountId = feeAccount.getId()+"";
                }
                //orderId = feeAccount.getId();
            }
        }
        //同时保存结算单和缴费（特殊下单）
        if (jsonObj.get("accountFee") != null && ("1".equals(jsonObj.getInt("accountFee")) || jsonObj.getInt("accountFee") == 1)) {
            Payfee feeAccount = new Payfee();
            feeAccount.setOrderId(order.getId());
            feeAccount.setIsManual(2);
            feeAccount.setAccountAmount(order.getTotalPay());
            feeAccount.setIsBackType(2);
            feeAccount.setPayStatus(20110003);
            feeAccount.setFeeType(1);
            feeAccount.setPayKind(2);
            feeAccount.setPayType("4");
            feeAccount.setCreateBy(order.getCreateBy());
            feeAccount.setMarketPrice(marketPrice);//订单商品市场价汇总
            try {
                this.wrPayfeeMapper.insertAccount(feeAccount);
            } catch (Exception e) {
                throw new RuntimeException("feeAccount_" + checkInventory);
            }
            Payfee fee = new Payfee();
            fee.setOrderId(feeAccount.getId());//新增的退款结算单id
            fee.setFeeSum(order.getTotalPay());
            fee.setFeePost(20250002); //默认为银行卡缴费
            fee.setIsBackType(1);
            fee.setFeeType(1);
            fee.setAccountStatus(1);  //已对账
            fee.setPayStatus(20110002);
            fee.setCreateBy(order.getCreateBy());
            try {
                this.wrPayfeeMapper.insertPayfee(fee);//生成新缴费单信息
            } catch (Exception e) {
                throw new RuntimeException("fee_" + checkInventory);
            }
        }
        return orderId + "-" + msg + "-" + order.getOrderCode() + "-" + order.getCateType()+ "-" + accountId;
    }

    //判断订单类型是否为日常保洁或擦玻璃
    private BigDecimal checkOrderType(JSONObject jsonObj) {
        ItemDetailServer itemDetailServer = new ItemDetailServer();
        BigDecimal num = new BigDecimal(0);
        LogHelper.info(getClass() + ".checkOrderType()", "获取的orderItemServer：" + jsonObj.get("orderItemServer"));
        try {
            if (jsonObj.get("orderItemServer") != null) {
                JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("orderItemServer"));
                if (jsonServer.size() > 0) {
                    JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                    Object objServer = JSONObject.toBean(jobjServer, ItemDetailServer.class);
                    itemDetailServer = (ItemDetailServer) objServer;
                    num = new BigDecimal(itemDetailServer.getPersonNumber().toString());
                }
            }
        } catch (BaseException e) {
            LogHelper.error(getClass(), ".checkOrderType():" + e.getMessage(), e);
        }
        return num;
    }

    //获取减库存方法，需要的参数，并调用接口
    public String checkInventory(JSONObject jsonObj, Long orderId) {
        String result = "";
        String back = "";
        try {
            JSONArray jsonOrder = JSONArray.fromObject(jsonObj.get("order"));
            JSONObject jobjOrder = JSONObject.fromObject(jsonOrder.get(0));
            Object objOrder = JSONObject.toBean(jobjOrder, Order.class);
            Order order = (Order) objOrder;
            //获取cityCode
            String cityCode = order.getCity();
            //获取三级分类编码
            String threeCateCode = order.getOrderType();
            //获取服务开始时间
            String startTime = "";
            //获取服务结束时间
            String endTime = "";
            //获取选择时间
            String selectionTime = "";
            //服务人员个数
            Integer num = 0;

            //获取服务时间
            if (jsonObj.get("orderItemServer") != null) {
                JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("orderItemServer"));
                if (jsonServer.size() > 0) {
                    JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                    Object objServer = JSONObject.toBean(jobjServer, ItemDetailServer.class);
                    ItemDetailServer itemDetailServer = (ItemDetailServer) objServer;
                    selectionTime = itemDetailServer.getStartTime().substring(0, 10);//取月日年
                    startTime = itemDetailServer.getStartTime().substring(11, 16);//时间段开始时间
                    if (itemDetailServer.getEndTime() != null) {
                        endTime = itemDetailServer.getEndTime().substring(11, 16);//时间段结束时间
                    }
                    num = itemDetailServer.getPersonNumber();//需要服务人员个数
                    LogHelper.info(getClass() + ".checkInventory()", "获取服务日期的参数:" + selectionTime);
                }
            }
            LogHelper.info(getClass() + ".checkInventory()", "获取服务日期的参数selectionTime:" + selectionTime + ",startTime:" + startTime + ",endTime:" + endTime + ",num:" + num + "','orderId':'" + orderId);
            //减库存调用方法
            back = orderService.reduceInventory(cityCode, threeCateCode, selectionTime, startTime, endTime, num, orderId);
            JSONObject backJson = JSONObject.fromObject(back);
            Map map = backJson;
            Map map2 = (Map) map.get("data");
            //成功，则拼成json串，以便添加没成功，增加库存时使用
            if (map2 != null && !"".equals(map2.get("code")) && "1".equals(map2.get("code"))) {
                result = "{'cityCode':'" + cityCode + "','cateCode':'" + threeCateCode + "',"
                        + "'selectionTime':'" + selectionTime + "','startTime':'" + startTime + "','endTime':'" + endTime + "','num':'" + num + "','orderId':'" + orderId + "'}";
            } else {//失败，则返回错误json串
                result = back;
            }
        } catch (BaseException e) {
            LogHelper.error(getClass(), ".checkInventory()" + e.getMessage(), e);
        }

        return result;
    }

    public static String checkedOrder(Order order) {
        String re = "";
        if (order.getUserId() == null) {
            return "userId不能为空";
        }
        if (order.getPriceType() == null) {
            return "priceType不能为空 ";
        }
        if (order.getCateType() == null) {
            return "cateType不能为空 ";
        }
        if (order.getOrderSourceId() == null) {
            return "orderSourceId不能为空 ";
        }
        if (order.getCreateBy() == null) {
            return "createBy不能为空 ";
        }
        if (order.getCity() == null) {
            return "city不能为空 ";
        }
        return re;
    }

    /**
     * 订单修改
     *
     * @param jsonObj
     * @return
     */
    @Transactional
    public Long updateOrder(JSONObject jsonObj) {
        Long orderId = 0L;
        if (jsonObj.get("order") != null) {
            JSONArray jsonOrder = JSONArray.fromObject(jsonObj.get("order"));
            if (jsonOrder.size() > 0) {
                JSONObject jobjOrder = JSONObject.fromObject(jsonOrder.get(0));
                Object objOrder = JSONObject.toBean(jobjOrder, Order.class);
                Order order = (Order) objOrder;
                orderId = order.getId();
                if ("3".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 3) {
                    order.setValid(2);
                    orderService.updateOrder2(order);
                } else {
                    // 商品订单，后台录入业务处理
                    if (order.getCateType() != null && order.getCateType().intValue() == 91) {
                        // 如果是商品订单，订用分包接口
                        LogHelper.info(getClass() + ".updateOrder()", "调用包裹接口开始:" + orderId);
                        String aa = this.subPackageService.subPackage(orderId);
                        LogHelper.info(getClass()+".updateOrder()", "调用subPackage提供方IP地址:" +RpcContext.getContext().getRemoteHost());
                        LogHelper.info(getClass() + ".updateOrder()", "调用包裹接口结束:" + aa);
                        if (!"success".equals(aa)) {
                            return 0L;
                        }
                        order.setCateType(3);
                        if (order.getOrderStatus() != null && (order.getOrderStatus() == 1 || order.getOrderStatus() == 18)) {
                            order.setOrderStatus(2);
                        } else {
                            order.setOrderStatus(null);
                        }
                        order.setPayStatus("20110003");
                        orderService.updateOrder2(order);
                    } else if (order.getCateType() != null && order.getCateType() == 92) {
                        // 他营单次服务订单，后台录入业务处理
                        order.setCateType(4);
                        if (order.getOrderStatus() != null && (order.getOrderStatus() == 1 || order.getOrderStatus() == 18)) {
                            order.setOrderStatus(2);
                        } else {
                            order.setOrderStatus(null);
                        }
                        orderService.updateOrder2(order);
                    } else if (order.getCateType() != null && order.getCateType() == 93) {
                        // 自营单次
                        order.setCateType(1);
                        orderService.updateOrder2(order);
                    } else if (order.getCateType() != null && order.getCateType() == 94) {
                        // 自营延续，后台录入业务处理， 此处不进行可用余额计算
                        //order.setCateType(2);
						/*Map<String, Object> map = null;
						JSONArray accountPayJson = JSONArray.fromObject(jsonObj.get("accountPay")); // 查询结算单
						if (null != accountPayJson && accountPayJson.size() > 0) {
							JSONObject accountPayObj = JSONObject.fromObject(accountPayJson.get(0));
							Long accountId = accountPayObj.getLong("id");
							if (null != accountId) {
								map = accountPayService.queryAccountInfo(accountId);
							}
							if (map != null && MapUtils.getInteger(map, "BUSSSTATUS") != 2) { // 如果业务已处理过，则不更新可用余额
								// 1:定金，2:押金，3:预收，4:收入，5:信息费预收，6:信息费收入
								BigDecimal bd = new BigDecimal(MapUtils.getDouble(map, "TOTALPAY"));
								if ( order.getPayType()==4 || order.getPayType()==6 ) {
									bd = bd.subtract(order.getAccountAmount());
								} else if(order.getPayType()==1||order.getPayType()==2||order.getPayType()==3||order.getPayType()==5){
									bd = bd.add(order.getAccountAmount());
								}
								order.setTotalPay(bd);
							} else {
								order.setTotalPay(null);
							}
						}*/
                        //order.setTotalPay(null);
//						order.setOrderStatus(2);
//						this.wrOrderMapper.updateOrder(order);
                        //orderService.updateOrder2(order);
                        return 0L; // 取消该业务，单独提出
                    } else if (order.getCateTypeNot() != null && "95".equals(order.getCateTypeNot())) {
                        //唯品会分期退款，驳回后，调用接口，余额增加回原来的金额
                        if (order.getTotalPay() != null) {
                            orderService.updateOrder2(order);
                        }
                    } else {
                        // 如果是延续性订单，不修改总金额
                        if (order.getCateType() != null && order.getCateType() == 2) order.setTotalPay(null);
                        orderService.updateOrder2(order);
                    }

                }
                orderId = order.getId();
            }
        }
        Long itemId = 0L;
        if (jsonObj.get("orderItem") != null) {
            JSONArray jsonItem = JSONArray.fromObject(jsonObj.get("orderItem"));
            if (jsonItem.size() > 0) {
                for (Object object : jsonItem) {
                    Item item = new Item();
                    JSONObject jobjItem = JSONObject.fromObject(object);
                    Object objItem = JSONObject.toBean(jobjItem, Item.class);
                    item = (Item) objItem;
                    if (item.getOrderId() != null && item.getId() == null && ("2".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 2)) {
                        this.wrItemMapper.insertItem(item);
                    } else if (item.getOrderId() != null && item.getId() != null) {
                        this.wrItemMapper.updateItem(item);
                    }
                    itemId = item.getId();
                }
            }

        }
        Long serverId = 0L;
        if (jsonObj.get("orderItemServer") != null) {
            JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("orderItemServer"));
            if (jsonServer.size() > 0) {
                JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                Object objServer = JSONObject.toBean(jobjServer, ItemDetailServer.class);
                ItemDetailServer itemDetailServer = (ItemDetailServer) objServer;
                if (orderId != 0) {
                    itemDetailServer.setOrderId(orderId);
                }
                if (itemDetailServer.getId() != null && itemDetailServer.getOrderId() != null) {
                    this.wrItemDetailServerMapper.updateItemDetailServer(itemDetailServer);
                }
                serverId = itemDetailServer.getId();
            }

        }
        if (jsonObj.get("orderItemInterview") != null) {
            JSONArray jsonInterview = JSONArray.fromObject(jsonObj.get("orderItemInterview"));
            if (jsonInterview.size() > 0) {
                for (Object object : jsonInterview) {
                    ItemInterview itemInterview = new ItemInterview();
                    JSONObject jobjInterview = JSONObject.fromObject(object);
                    Object objInterview = JSONObject.toBean(jobjInterview, ItemInterview.class);
                    itemInterview = (ItemInterview) objInterview;
                    if (orderId != 0) {
                        itemInterview.setOrderId(orderId);
                    }
                    if (("2".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 2) && itemInterview.getOrderId() != null
                            && itemInterview.getOrderItemId() != null && itemInterview.getId() == null) {
                        this.wrItemInterviewMapper.insertItemInterview(itemInterview);
                    } else if (itemInterview.getOrderId() != null && itemInterview.getOrderItemId() != null
                            && itemInterview.getId() != null) {
                        this.wrItemInterviewMapper.updateItemInterview(itemInterview);
                    }
                }
            }
        }
        if (jsonObj.get("lined") != null) {
            JSONArray jsonLined = JSONArray.fromObject(jsonObj.get("lined"));
            if (jsonLined.size() > 0) {
                JSONObject jobjLined = JSONObject.fromObject(jsonLined.get(0));
                Object objLined = JSONObject.toBean(jobjLined, ItemDetailServer.class);
                ItemDetailServer serverLined = (ItemDetailServer) objLined;
                if (orderId != 0) {
                    serverLined.setOrderId(orderId);
                }
                this.wrItemDetailServerMapper.updateOrderServerLined(serverLined);
            }
        }
        return orderId;
    }

    /**
     * 新增、修改、删除，结算
     *
     * @param jsonObj
     * @return
     */
    @Transactional
    public String accountAdd(JSONObject jsonObj) {
        String accountId = "";
        int payKind = 0;
        JSONArray jsonAry = JSONArray.fromObject(jsonObj.get("account"));
        if ("1".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
            int numm = 0;
            for (Object object : jsonAry) {
                if (!"".equals(accountId)) {
                    accountId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;

                //查询订单商品市场价总计信息
                Long orderId = payfee.getOrderId();
                BigDecimal marketPrice = reItemMapper.findMarketPriceByOrderId(orderId);
                payfee.setMarketPrice(marketPrice);

                // 判断金额不小于0
                if (payfee.getAccountAmount().compareTo(new BigDecimal(0)) < 1) {
                    new RuntimeException();
                }
                if (numm == 0) {
                    this.wrPayfeeMapper.updateAccountByOrderId(payfee.getOrderId());
                }
                if (payfee.getIsManual() == null) payfee.setIsManual(1);
                if (payfee.getIsBackType() == null) payfee.setIsManual(2);
                this.wrPayfeeMapper.insertAccount(payfee);
                accountId += String.valueOf(payfee.getId());
                payKind = payfee.getPayKind();
                numm++;
                LogHelper.info(getClass() + "accountAdd()", "新增结算单ID:" + accountId);
                System.out.println("接收的accountAdd_CreateBy:" + payfee.getCreateBy());
            }
        } else if ("2".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 2) {
            for (Object object : jsonAry) {
                if (!"".equals(accountId)) {
                    accountId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;

                //查询订单商品市场价总计信息
                Long orderId = payfee.getOrderId();
                BigDecimal marketPrice = reItemMapper.findMarketPriceByOrderId(orderId);
                payfee.setMarketPrice(marketPrice);

                // 判断金额不小于0
                if (payfee.getAccountAmount().compareTo(new BigDecimal(0)) < 1) {
                    new RuntimeException();
                }
                if (payfee.getId() == null || "".equals(payfee.getId())) {
                    if (payfee.getIsManual() == null) payfee.setIsManual(1);
                    if (payfee.getIsBackType() == null) payfee.setIsManual(2);
                    this.wrPayfeeMapper.insertAccount(payfee);
                } else {
                    this.wrPayfeeMapper.updateAccount(payfee);
                    this.wrPayfeeMapper.updatePayfeeByAccountId(payfee.getId());
                }
                accountId += String.valueOf(payfee.getId());
                payKind = payfee.getPayKind();
                System.out.println("接收的accountUpdate_UpdateBy:" + payfee.getUpdateBy());
                LogHelper.info(getClass() + "accountUpdate()", "修改结算单ID:" + accountId);
            }
        } else {//删除结算单
            for (Object object : jsonAry) {
                if (!"".equals(accountId)) {
                    accountId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;

                //查询订单商品市场价总计信息
                Long orderId = payfee.getOrderId();
                BigDecimal marketPrice = reItemMapper.findMarketPriceByOrderId(orderId);
                payfee.setMarketPrice(marketPrice);

                payfee.setValid(2);
                this.wrPayfeeMapper.updateAccount(payfee);
                //结算单对应缴费置为无效
                this.wrPayfeeMapper.updatePayfeeByAccountId(payfee.getId());
                accountId += String.valueOf(payfee.getId());
                System.out.println("接收的accountDelete_UpdateBy:" + payfee.getUpdateBy());
                LogHelper.info(getClass() + "accountUpdate()", "修改结算单ID:" + accountId);
            }
        }
        return accountId + "-" + payKind;
    }

    /**
     * 新增、修改、删除，缴费
     *
     * @param jsonObj
     * @return
     */
    @Transactional
    public String payfeeAdd(JSONObject jsonObj) {
        String payfeeId = "";
        JSONArray jsonAry = JSONArray.fromObject(jsonObj.get("payfee"));
        if ("1".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
            for (Object object : jsonAry) {
                if (!"".equals(payfeeId)) {
                    payfeeId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;
                // 判断金额不小于0
                if (payfee.getFeeSum() != null && payfee.getFeeSum().compareTo(new BigDecimal(0)) < 1) {
                    new RuntimeException();
                }
                this.wrPayfeeMapper.insertPayfee(payfee);
                payfeeId += String.valueOf(payfee.getId());
                LogHelper.info(getClass() + "payfeeInsert()", "新增缴费单ID:" + payfeeId);
                //往kafka中放值
                payfeeAddInkafka(payfeeId);
            }
        } else if ("2".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 2) {
            for (Object object : jsonAry) {
                if (!"".equals(payfeeId)) {
                    payfeeId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;
                // 判断金额不小于0
                if (payfee.getFeeSum() != null && payfee.getFeeSum().compareTo(new BigDecimal(0)) < 1) {
                    new RuntimeException();
                }
                if (payfee.getId() == null && payfee.getOrderId() != null) {
                    this.wrPayfeeMapper.insertPayfee(payfee);
                } else {
                    this.wrPayfeeMapper.updatePayfee(payfee);
                }
                payfeeId += String.valueOf(payfee.getId());
                LogHelper.info(getClass() + "payfeeUpdate()", "修改缴费单ID:" + payfeeId);
                //往kafka中放值
                payfeeAddInkafka(payfeeId);
            }
        } else {
            for (Object object : jsonAry) {
                if (!"".equals(payfeeId)) {
                    payfeeId += ",";
                }
                Payfee payfee = new Payfee();
                JSONObject jobj = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobj, Payfee.class);
                payfee = (Payfee) obj;
                payfee.setValid(1);
                this.wrPayfeeMapper.updatePayfee(payfee);
                payfeeId += String.valueOf(payfee.getId());
                LogHelper.info(getClass() + "payfeeUpdate()", "修改/删除缴费单ID:" + payfeeId);
                //往kafka中放值
                payfeeAddInkafka(payfeeId);
            }
        }
        return payfeeId;
    }

    /**
     * 新增、修改、删除，结算和缴费
     *
     * @param jsonObj
     * @return
     */
    @Transactional
    public String accountAndpayfee(JSONObject jsonObj) {
        String accountId = "";
        int payKind = 0;
        String payfeeId = "";
        if ("1".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
            if (jsonObj.get("account") != null) {
                JSONArray jsonAccount = JSONArray.fromObject(jsonObj.get("account"));
                JSONObject jobjAccount = JSONObject.fromObject(jsonAccount.get(0));
                Object objAccount = JSONObject.toBean(jobjAccount, Payfee.class);
                Payfee account = (Payfee) objAccount;
                // 判断金额不小于0
                if (account.getAccountAmount().compareTo(new BigDecimal(0)) < 1) {
                    new RuntimeException();
                }
                if (account.getIsManual() == null) account.setIsManual(1);
                if (account.getIsBackType() == null) account.setIsManual(2);
                this.wrPayfeeMapper.insertAccount(account);
                accountId = String.valueOf(account.getId());
                payKind = account.getPayKind();
                if (jsonObj.get("payfee") != null) {
                    JSONArray jsonPayfee = JSONArray.fromObject(jsonObj.get("payfee"));
                    for (Object object : jsonPayfee) {
                        Payfee payfee = new Payfee();
                        JSONObject jobjPayfee = JSONObject.fromObject(object);
                        Object obj = JSONObject.toBean(jobjPayfee, Payfee.class);
                        payfee = (Payfee) obj;
                        // 判断金额不小于0
                        if (payfee.getFeeSum().compareTo(new BigDecimal(0)) < 1) {
                            new RuntimeException();
                        }
                        payfee.setOrderId(account.getId());
                        this.wrPayfeeMapper.insertPayfee(payfee);
                        payfeeId += String.valueOf(payfee.getId());
                        //往kafka中放缴费id
                        payfeeAddInkafka(payfeeId);
                    }
                }

            }
        } else if ("2".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
            if (jsonObj.get("account") != null) {
                JSONArray jsonAccount = JSONArray.fromObject(jsonObj.get("account"));
                JSONObject jobjAccount = JSONObject.fromObject(jsonAccount.get(0));
                Object objAccount = JSONObject.toBean(jobjAccount, Payfee.class);
                Payfee account = (Payfee) objAccount;
                this.wrPayfeeMapper.updateAccount(account);
                accountId = String.valueOf(account.getId());
                payKind = account.getPayKind();
                if (jsonObj.get("payfee") != null) {
                    JSONArray jsonPayfee = JSONArray.fromObject(jsonObj.get("payfee"));
                    // 卡结算单支持接口
                    if (account.getPayKind() != null && account.getPayKind() == 4) {
                        this.wrPayfeeMapper.updatePayfeeByAccountId(account.getId());
                        for (Object object : jsonPayfee) {
                            Payfee payfee = new Payfee();
                            JSONObject jobjPayfee = JSONObject.fromObject(object);
                            Object obj = JSONObject.toBean(jobjPayfee, Payfee.class);
                            payfee = (Payfee) obj;
                            this.wrPayfeeMapper.insertPayfee(payfee);
                            payfeeId += String.valueOf(payfee.getId());
                            //往kafka中放缴费id
                            payfeeAddInkafka(payfeeId);
                        }
                    } else {
                        for (Object object : jsonPayfee) {
                            Payfee payfee = new Payfee();
                            JSONObject jobjPayfee = JSONObject.fromObject(object);
                            Object obj = JSONObject.toBean(jobjPayfee, Payfee.class);
                            payfee = (Payfee) obj;
                            if (payfee.getId() == null) {
                                this.wrPayfeeMapper.insertPayfee(payfee);
                            } else {
                                this.wrPayfeeMapper.updatePayfee(payfee);
                            }
                            payfeeId += String.valueOf(payfee.getId());
                            //往kafka中放缴费id
                            payfeeAddInkafka(payfeeId);
                        }
                    }
                }
            }

        } else {
            if (jsonObj.get("account") != null) {
                JSONArray jsonAccount = JSONArray.fromObject(jsonObj.get("account"));
                JSONObject jobjAccount = JSONObject.fromObject(jsonAccount.get(0));
                Object objAccount = JSONObject.toBean(jobjAccount, Payfee.class);
                Payfee account = (Payfee) objAccount;
                account.setValid(1);
                this.wrPayfeeMapper.updateAccount(account);
                accountId = String.valueOf(account.getId());
                payKind = account.getPayKind();
            }
            if (jsonObj.get("payfee") != null) {
                JSONArray jsonPayfee = JSONArray.fromObject(jsonObj.get("payfee"));
                for (Object object : jsonPayfee) {
                    Payfee payfee = new Payfee();
                    JSONObject jobjPayfee = JSONObject.fromObject(object);
                    Object obj = JSONObject.toBean(jobjPayfee, Payfee.class);
                    payfee = (Payfee) obj;
                    payfee.setValid(1);
                    this.wrPayfeeMapper.updatePayfee(payfee);
                    payfeeId += String.valueOf(payfee.getId());
                    //往kafka中放缴费id
                    payfeeAddInkafka(payfeeId);
                }
            }
        }
        return accountId + "-" + payKind;
    }

    @Override
    @Transactional
    public String insertAfterSales(String afterSaleJson) {
        String afterSaleId = "";
        try {
            System.out.println("接收的新增afterSaleJson:" + afterSaleJson);

            JSONObject asJson = JSONObject.fromObject(afterSaleJson);
            Object obj = JSONObject.toBean(asJson, OrderAfterSales.class);
            OrderAfterSales afterSales = (OrderAfterSales) obj;

            Order newOrder = this.reOrderMapper.loadOrder(afterSales.getOrderId());
            //延续服务订单终止
            if (newOrder.getCateType() == 2 && "8".equals(afterSales.getAfterSalesKind())) {//自营延续订单&&延续性服务订单终止
                //计算终止月费用，并生成结算单
                OrderBaseModel newOrderBase = new OrderBaseModel();
                //获取服务价格
                List<OrderBaseModel> newOrderBaseList = this.reOrderBaseMapper.queryOrderBasicServer(newOrder.getId());
                newOrderBase = newOrderBaseList.get(0);
                Date newDate = new Date();
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.setTime(newDate);
                //当月第几天
                int newToday = newCalendar.get(Calendar.DAY_OF_MONTH);
                BigDecimal newEndDate = new BigDecimal(newToday);
                BigDecimal newEndNowPrice = newOrderBase.getNowPrice();
                // 终止月服务费算方法，当前价格/30天*当前服务天数
                BigDecimal newAccount = (newEndNowPrice.divide(new BigDecimal(30), 2, 4)).multiply(newEndDate);
                Payfee newPayfee = new Payfee();
                newPayfee.setOrderId(afterSales.getOrderId());
                newPayfee.setFeeSum(newAccount);
                newPayfee.setPayStatus(20110002);
                newPayfee.setPayKind(2);
                newPayfee.setIsBackType(2);//不是退款结算单
                newPayfee.setOrderId(newOrder.getId());
                newPayfee.setCreateBy(afterSales.getCreateBy());
                newPayfee.setIsManual(2);//由APP录入
                newPayfee.setPayType("3");//结算类型为预收
                // 新增结算单
                this.wrPayfeeMapper.insertAccount(newPayfee);
            }
            // 生成售后单
            this.orderAfterSalesService.insertOrderAfterSales(afterSales);
            afterSaleId = String.valueOf(afterSales.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = "{'afterSaleId':'" + afterSaleId + "','code':'0','msg':'请求操作成功'}";
        if (afterSaleId == "null" || afterSaleId.equals("")) {
            result = "{'afterSaleId':'','code':'1','msg':'请求操作失败'}";
        }
        System.out.println(result);
        return result;
    }

    @Override
    public String updateOrderByAccount(String json) {
        String re = "";
        String orderJson = "";
        try {
            System.out.println("接收的新增结算单json串:" + json);
            JSONObject accJson = JSONObject.fromObject(json);
            Object obj = JSONObject.toBean(accJson, AccountPay.class);
            AccountPay accPay = (AccountPay) obj;

            AccountPay accountPay = new AccountPay();
            accountPay.setId(accPay.getId());
            //查询结算单信息
            List<AccountPay> listAccountPay = this.accountPayService.queryAccountById(accountPay);
            AccountPay accountPayTem = listAccountPay.get(0);
            //根据结算单中订单id，查询订单信息
            Order order = this.reOrderMapper.loadOrder(accountPayTem.getOrderId());
            //订单
            if (accountPayTem.getPayKind().equals("2") && (order.getCateType() == 3 || order.getCateType() == 4)
                    && ("20110001".equals(accountPayTem.getPayStatus()) || "20110002".equals(accountPayTem.getPayStatus()))) {
                //比较FA、他营单次结算单金额和订单总金额是否相等
                if (accountPayTem.getAccountAmount().compareTo(order.getTotalPay()) == 0) {
                    orderJson = "{\"handle\":\"2\",\"order\":[{\"id\":\"" + accountPayTem.getOrderId() + "\"";
                    orderJson += ",\"orderStatus\":\"" + (accountPayTem.getOrderStatus() == null ? "18" : accountPayTem.getOrderStatus()) + "\",\"payStatus\":\"20110003\"}]}";
                }
                Order od = new Order();
                od.setId(accountPayTem.getOrderId());
                if (accountPayTem.getOrderStatus() == null || "1".equals(accountPayTem.getOrderStatus())) {
                    od.setOrderStatus(18);
                } else {
                    od.setOrderStatus(Integer.valueOf(accountPayTem.getOrderStatus()));
                }
                od.setPayStatus("20110003");
                od.setUpdateBy(accPay.getCreateBy());
//				this.wrOrderMapper.updateOrder(od);
                orderService.updateOrder2(order);
            }
            if (accountPayTem.getPayKind().equals("1") && order.getCateType() == 8
                    && ("20110001".equals(accountPayTem.getPayStatus()) || "20110002".equals(accountPayTem.getPayStatus()))) {
                //比较解决方案结算单金额和订单总金额是否相等
                if (accountPayTem.getAccountAmount().compareTo(order.getTotalPay()) == 0) {
                    String solutionJson = "{\"id\":" + order.getId() + ",\"updateBy\":" + accPay.getCreateBy() + ",\"solutionStatus\":\"7\",\"payStatus\":\"20110002\"}";
                    String updateSolution = this.activitiesInterfaceService.updateCustSolutionStatus(solutionJson);
                    JSONObject jsonObj = JSONObject.fromObject(updateSolution);
                    System.out.println("是否取消解决方案订单状态:" + jsonObj.get("code"));
                    orderJson = "{\"handle\":\"2\",\"order\":[{\"id\":\"" + order.getId() + "\"";
                    orderJson += ",\"orderStatus\":\"18\",\"payStatus\":\"20110003\"}]}";
                    Order od = new Order();
                    od.setId(accountPayTem.getOrderId());
                    if (accountPayTem.getOrderStatus() == null || "1".equals(accountPayTem.getOrderStatus())) {
                        od.setOrderStatus(18);
                    } else {
                        od.setOrderStatus(Integer.valueOf(accountPayTem.getOrderStatus()));
                    }
                    od.setPayStatus("20110003");
                    od.setUpdateBy(accPay.getCreateBy());
//					this.wrOrderMapper.updateOrder(od);
                    orderService.updateOrder2(order);
                }
            }
            //re = insertOrUpdateOrder(orderJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = "{'code':'0','msg':'请求操作成功！'}";
//		JSONObject backJson = JSONObject.fromObject(re);
//		if ("1".equals(String.valueOf(backJson.get("code")))) {
//			result = "{'code':'1','msg':'请求操作失败！'}";
//		}
//		System.out.println(result);
        return result;
    }

    @Override
    public String insertOrderAliUser(String aliUserJson) {
        String result = "{'code':'1','msg':'请求操作失败'}";
        try {
            System.out.println("接收的aliUserJson:" + aliUserJson);
            JSONObject asJson = JSONObject.fromObject(aliUserJson);
            Object obj = JSONObject.toBean(asJson, OrderUser.class);
            OrderUser aliUser = (OrderUser) obj;
            this.wrOrderBaseMapper.insertOrderCustomerAnalysis(aliUser);
            result = "{'code':'0','msg':'请求操作成功'}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) {

		/*Order order = new Order();
		order.setTotalPay(BigDecimal.valueOf(6700.00));
		order.setTotalPay(order.getTotalPay().subtract(BigDecimal.valueOf(6500.00)));

		System.out.println(order.getTotalPay());*/
        Random r = new Random();
        for (int i = 1; i < 30; i++) {
            Long[] loArray = new Long[]{95824l, 95838l, 95837l};
            System.out.println(loArray[r.nextInt(3)]);
        }


    }

    @Override
    public String distribute(String json) {
        DubboModel model = new DubboModel();
        try {
            JSONObject asJson = JSONObject.fromObject(json);
            Order order = (Order) JSONObject.toBean(asJson, Order.class);
            if (distributeService.distribute(order)) {
                model.setFlag(DubboModel.FLAG_SUCCESS);
                model.setMsg("处理成功");
                JSONObject data = new JSONObject();
                data.accumulate("rechargeBy", order.getRechargeBy());
                data.accumulate("rechargeDept", order.getRechargeDept());
                model.setData(data.toString());
            } else {
                model.setFlag(DubboModel.FLAG_FAIL);
                model.setMsg("处理失败");
            }
        } catch (Exception e) {
            model.setFlag(DubboModel.FLAG_FAIL);
            model.setMsg("处理失败:" + e.getMessage());
        }
        return JSON.toJSONString(model);
    }

    @Override
    public String insertVmengOrder(String json) {
        System.out.println("接收的insertVmengOrder:" + json);
        Long orderId = 0l;
        String msg = "请求操作失败";
        String result = "{'oId':'','code':'2','msg':'" + msg + "'}";
        try {
            if (json.startsWith("[{")) {
                json = json.substring(1, json.length() - 1);
            }
            JSONObject jsonObj = JSONObject.fromObject(json);
            if ("1".equals(jsonObj.get("handle")) || jsonObj.getInt("handle") == 1) {
                if (jsonObj.get("info") != null) {
                    String[] oid = insertVIndexOrderAccountPayfee(jsonObj).split("-");
                    if ("0".equals(oid[0])) {
                        msg = oid[1];
                    } else {
                        orderId = Long.valueOf(oid[0]);
                    }
                }
            }
            if (orderId != 0L) {
                result = "{'code':'1','msg':'请求操作成功'}";
            } else {
                result = "{'code':'2','msg':'" + msg + "'}";
            }
        } catch (RuntimeException e) {
            if ("product".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'当前商品无库存！'}";
            } else if ("order".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'保存订单失败！'}";
            } else if ("service".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'保存service失败！'}";
            } else if ("account".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'保存account失败！'}";
            } else if ("fee".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'保存fee失败！'}";
            } else if ("package".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'生成包裹失败！'}";
            } else if ("cityCode".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'查询客户城市失败！'}";
            } else if ("ticket".equals(e.getMessage())) {
                result = "{'oId':'','code':'2','msg':'绑定券失败！'}";
            } else {
                result = "{'oId':'','code':'2','msg':'请求操作失败!'}";
            }
        } catch (Exception e) {
            LogHelper.error(getClass(), "insertVmengOrder", e);
        }
        System.out.println(result);
        return result;

    }

    @Transactional
    public String insertVIndexOrderAccountPayfee(JSONObject jsonObj) {
        LogHelper.info(getClass() + ".insertVIndexOrderAccountPayfee()", "第三方接口调用参数接收:" + jsonObj);
        Long orderId = 0l;
        JSONArray jsonOrder = JSONArray.fromObject(jsonObj.get("info"));
        JSONObject jobjOrder = JSONObject.fromObject(jsonOrder.get(0));
        Object objOrder = JSONObject.toBean(jobjOrder, OrderVIndex.class);
        OrderVIndex orderVIndex = (OrderVIndex) objOrder;
        Order order = new Order();
        // 判断订单必填字段
        String checkedOrder = checkVIndexOrder(orderVIndex);
        if (!"".equals(checkedOrder)) {
            return orderId + "-" + checkedOrder;
        }
        //随机生成订单ID
        orderId = this.reOrderMapper.queryOrderId();
        order.setId(orderId);
        //客户城市编码
        String cityCode = "";
        String reciverCityCode = "";
        OrderUser orderUser = new OrderUser();
        orderUser.setUserCountry(orderVIndex.getrStrict());
        orderUser.setUserAddress(orderVIndex.getrCity());
        //接收人城市编码
        List<OrderUser> cityCodeList = orderBaseService.queryCityCodeByArea(orderUser);
        if (cityCodeList.size() != 0 && cityCodeList.size() == 1) {
            reciverCityCode = String.valueOf(cityCodeList.get(0));
        } else {
            throw new RuntimeException("cityCode");
        }
        //根据用户电话，查询系统是否已存在客户，不存在则添加
        int userOrNot = orderBaseService.checkedUserOrNot(orderVIndex.getuMobile());
        if (userOrNot == 0) {
			/*orderUser.setUserCountry(orderVIndex.getuStrict());
			//查询订单城市编码
			if (orderVIndex.getuStrict() != null) {
				cityCode = orderBaseService.queryCityCodeByArea(orderUser);
			}*/
            //将客户信息补全
            orderUser.setRealName(orderVIndex.getuName());
            orderUser.setUserMobile(orderVIndex.getuMobile());
            orderUser.setSex(orderVIndex.getuSex());
            orderUser.setOrigin("20180011");//来源
            orderUser.setChannel(CommonConstant.ORDER_CHANNEL_VINDEX_ID);//渠道
            orderUser.setUserProvince(orderVIndex.getuProvince());
            orderUser.setUserCity(orderVIndex.getuCity());
            orderUser.setUserCountry(orderVIndex.getuStrict());
            orderUser.setUserAddress(orderVIndex.getuAddress());
            orderUser.setLatitude(orderVIndex.getuLatitude());
            orderUser.setLongitude(orderVIndex.getuLongitude());
            orderUser.setCityCode(reciverCityCode);
            //添加新用户
            orderBaseService.insertUser(orderUser);
            //将客户信息添加到订单
            order.setUserId(orderUser.getId());
            order.setUserName(orderVIndex.getuName());
            order.setUserMobile(orderVIndex.getuMobile());
            order.setUserProvince(orderVIndex.getuProvince());
            order.setUserCity(orderVIndex.getuCity());
            order.setUserArea(orderVIndex.getuStrict());
            order.setUserAddress(orderVIndex.getuAddress());
            order.setUserSex(orderVIndex.getuSex());
        } else {
            //查询客户信息
            orderUser.setUserMobile(orderVIndex.getuMobile());
            OrderUser hasUser = orderBaseService.queryUserByMobile(orderUser);
            //将客户信息添加到订单
            order.setUserId(hasUser.getId());
            order.setUserName(hasUser.getRealName());
            order.setUserMobile(hasUser.getUserMobile());
            order.setUserProvince(hasUser.getUserProvince());
            order.setUserCity(hasUser.getUserCity());
            order.setUserArea(hasUser.getUserCountry());
            order.setUserAddress(hasUser.getUserAddress());
            order.setUserSex(hasUser.getUserSex());
            order.setUserCityCode(hasUser.getCityCode());
            cityCode = hasUser.getCityCode();
        }
        Long itemId = 0L;
        //定义城市商品
        CityProduct cp = new CityProduct();
        cp.setDictCode("20000002");//市场价
        String cityStr = "";
        BigDecimal totalPay = new BigDecimal(0);
        String msg = "请求操作失败";
        String categoryCode = "";
        // 保存商品信息
        if (jsonObj.get("product") != null) {
            JSONArray jsonItem = JSONArray.fromObject(jsonObj.get("product"));
            //截取商品编码前9位，作为citycode，存入订单城市字段
            String proCode = jsonItem.getJSONObject(0).getString("productCode");
            cityStr = proCode.substring(0, 9);
            cp.setCityCode(cityStr);
            List<CityProduct> productList = null;
            if (orderVIndex.getCateType() == 3) {
                productList = this.reItemMapper.queryForDictPriceFA(cp);
            } else {
                productList = this.reItemMapper.queryForDictPrice(cp);
            }
            for (Object object : jsonItem) {
                Item item = new Item();
                JSONObject jobjItem = JSONObject.fromObject(object);
                Object objItem = JSONObject.toBean(jobjItem, OrderVIndex.class);
                OrderVIndex itemVIndex = (OrderVIndex) objItem;
                item.setOrderId(orderId);
                int i = 0;
                for (CityProduct cpt : productList) {
                    if (cpt.getProductCode().equals(itemVIndex.getProductCode())) {
                        //订单余额
                        totalPay = totalPay.add(cpt.getMarketPrice().multiply(itemVIndex.getQuantity()));
                        item.setNowPrice(cpt.getMarketPrice());
                        item.setCategoryCode(cpt.getCategoryCode());
                        item.setQuantity(Float.parseFloat(itemVIndex.getQuantity().toString()));
                        item.setProductCode(cpt.getProductCode());
                        //分类编码
                        if (orderVIndex.getCateType() == 1 || orderVIndex.getCateType() == 2 || orderVIndex.getCateType() == 4) {
                            categoryCode = cpt.getCategoryCode();
                            //将商品编码放入order中
                            order.setProductCode(cpt.getProductCode());
                        } else {
                            categoryCode = "1";
                        }
                        item.setProductInventoryId(cpt.getProductInventoryId());
                        item.setProductPriceType("20000002");
                        item.setProductSpec(cpt.getProductSpec());
                        item.setProductUnit(cpt.getProductUnit());
                        item.setProductImg(cpt.getProductImg());
                        item.setProductName(itemVIndex.getProductName());
                        //item.setCreateBy(1l);
                        this.wrItemMapper.insertItem(item);
                        itemId = item.getId();
                        i++;
                        break;
                    }
                }
                // 说明当前商品不存在
                if (i == 0) {
                    throw new RuntimeException("product");
                }
            }
        }

        //订单接收人信息
        order.setReceiverName(orderVIndex.getrName());
        order.setReceiverProvince(orderVIndex.getrProvince());
        order.setReceiverCity(orderVIndex.getrCity());
        order.setReceiverArea(orderVIndex.getrStrict());
        order.setReceiverAddress(orderVIndex.getrAddress());
        order.setLatitude(orderVIndex.getrLatitude());
        order.setLongitude(orderVIndex.getrLongitude());
        order.setReceiverMobile(orderVIndex.getrMobile());
        order.setCity(cityStr);
        order.setOrderType(categoryCode);
        order.setCateType(orderVIndex.getCateType());

        //延续订单下单，订单金额是否置为0，还是直接传多少就是多少
        if (order.getCateType() != null && order.getCateType() == 2) {
            order.setTotalPay(new BigDecimal(0));
            //支付状态
            order.setPayStatus("20110001");
        } else {
            //支付状态
            order.setPayStatus("20110003");
        }
        //下单时间
        order.setCreateTime(orderVIndex.getCreateTime());
        order.setCritical(2);
        order.setRemark(orderVIndex.getoRemark());
        order.setIsInvoice(2);
        order.setDeliverPay(orderVIndex.getDeliverFee());
        //城市编码
        order.setReceiverCityCode(reciverCityCode);
        order.setUserCityCode(cityCode);
        order.setThreeOrderCode(orderVIndex.getoId());
        //价格体系为市场价
        order.setPriceType("20000002");
        //配送时间
        order.setReceiptTime(orderVIndex.getReceiptTime());
        order.setVersion(1l);

        if (totalPay.doubleValue() != 0 && orderVIndex.getCateType() != 2) {
            order.setTotalPay(totalPay);
        } else if (totalPay.doubleValue() != 0 && orderVIndex.getCateType() == 2) {
            order.setTotalPay(new BigDecimal(0));
            totalPay = new BigDecimal(1);
        }
        order.setThreeOrderCode(orderVIndex.getoId());
        //录入人、录入部门为空
        if (orderVIndex != null && CommonConstant.ORDER_CHANNEL_VINDEX_ID.equals(orderVIndex.getoChannel())) {
            //订单渠道:微指数
            order.setOrderChannel(CommonConstant.ORDER_CHANNEL_VINDEX_ID);
            //订单来源：微信
            order.setOrderSourceId("20180011");
        }
		/*else if (orderVIndex != null && CommonConstant.ORDER_CHANNEL_WANGMANAGER_ID.equals(orderVIndex.getoChannel())) {
			order.setRechargeBy(95837l);
			order.setRechargeDept(990022905588442l);
			//订单渠道:微指数
			order.setOrderChannel(CommonConstant.ORDER_CHANNEL_WANGMANAGER_ID);
			//订单来源：微信
			order.setOrderSourceId("20180011");
		}*/

        //订单分发
        if (orderVIndex.getCateType() == 1 || orderVIndex.getCateType() == 2 || orderVIndex.getCateType() == 3) {
            orderDistributeService.distribute(order);
            LogHelper.info(getClass() + ".distribute()", "订单分发负责部门:" + order.getRechargeDeptName());
            order.setOrderFenfa("2");//自动分发
        }
        try {
            // 自营单次和延续，下单默认为已受理
            if (orderVIndex.getCateType() != null && (orderVIndex.getCateType() == 1 || orderVIndex.getCateType() == 2)) {
                order.setOrderStatus(2);
            } else {//微指数FA和他营单次，默认为已受理
                order.setOrderStatus(2);
            }
            //添加订单
            this.wrOrderMapper.insertOrder(order);
            LogHelper.info(getClass() + ".insertOrder()", "添加新单的ID:" + order.getId());
        } catch (Exception e) {
            throw new RuntimeException("order");
        }
        //商品生成包裹操作
        if (order.getCateType() != null && order.getCateType() == 3) {
            LogHelper.info(getClass() + ".updateOrder()", "调用包裹接口开始:" + orderId);
            String aa = this.subPackageService.subPackage(orderId);
            LogHelper.info(getClass() + ".updateOrder()", "调用包裹接口结束:" + aa);
            if (!"success".equals(aa)) {
                throw new RuntimeException("package");
            }
        }
        Long serverId = 0L;
        // 保存服务订单需求信息
        if (jsonObj.get("service") != null && order.getCateType() != 3) {
            JSONArray jsonServer = JSONArray.fromObject(jsonObj.get("service"));
            if (jsonServer.size() > 0) {
                JSONObject jobjServer = JSONObject.fromObject(jsonServer.get(0));
                Object objServer = JSONObject.toBean(jobjServer, OrderVIndex.class);
                OrderVIndex serviceVIndex = (OrderVIndex) objServer;
                ItemDetailServer itemDetailServer = new ItemDetailServer();
                itemDetailServer.setOrderId(order.getId());
                itemDetailServer.setItemId(itemId);
                itemDetailServer.setAddress(orderVIndex.getrAddress());
                itemDetailServer.setStartTime(serviceVIndex.getStartTime());
                itemDetailServer.setEndTime(serviceVIndex.getEndTime());
                itemDetailServer.setServerType(categoryCode);
                itemDetailServer.setRemark(orderVIndex.getoRemark());
                itemDetailServer.setValid(1);
                try {
                    this.wrItemDetailServerMapper.insertItemDetailServer(itemDetailServer);
                } catch (Exception e) {
                    throw new RuntimeException("service");
                }
                serverId = itemDetailServer.getId();
            }
        }
        Long accountId = 0L;
        // 保存结算单，保存时需要同时也保存结算单的才用到
        if (orderVIndex.getAllFee() != null) {
            Payfee account = new Payfee();
            account.setOrderId(order.getId());
            account.setId(null);
            account.setIsBackType(2);
            account.setAccountAmount(totalPay);
            account.setPayStatus(20110003);
            if (orderVIndex.getCateType() == 2) {
                account.setPayType("1");//一元接收为定金
            } else {
                account.setPayType("4");
            }
            account.setPayKind(2);
            account.setBussStatus(1);
            //前后台下单
            account.setIsManual(2);
            account.setPlatformAllFee(orderVIndex.getAllFee());
            //结算单的开始、结束时间默认为下单系统时间
            account.setStartTime(DateUtil.getCurrDateTime());
            account.setEndTime(DateUtil.getCurrDateTime());
            try {
                this.wrPayfeeMapper.insertAccount(account);
            } catch (Exception e) {
                throw new RuntimeException("account");
            }
            accountId = account.getId();
        }
        //保存缴费
        if (jsonObj.get("fee") != null) {
            JSONArray jsonPayfee = JSONArray.fromObject(jsonObj.get("fee"));
            for (Object object : jsonPayfee) {
                Payfee payfee = new Payfee();
                JSONObject jobjPayfee = JSONObject.fromObject(object);
                Object obj = JSONObject.toBean(jobjPayfee, Payfee.class);
                payfee = (Payfee) obj;
                if (payfee.getFeePost() == 1) {//微信
                    payfee.setFeePost(20250007);
                    payfee.setAccountStatus(2);//未对账
                }
                if (payfee.getFeePost() == 2) {//微指数余额
                    payfee.setFeePost(20250025);
                    payfee.setAccountStatus(1);//已对账
                }
                if (payfee.getFeePost() == 3) {//支付宝
                    payfee.setFeePost(20250001);
                    payfee.setAccountStatus(1);//已对账
                }
                if (payfee.getFeePost() == 4) {//旺财支付
                    payfee.setFeePost(20250026);
                    payfee.setAccountStatus(1);//已对账
                }
                if (payfee.getFeePost() == 5) {//券支付
                    payfee.setFeePost(20250011);
                    payfee.setAccountStatus(1);//已对账
                    //调用券接口，对应券置为已使用
                    try {
                        String json = "{userId:" + order.getUserId() + ",ticketNum:" + payfee.getCardsNum() + "}";
                        LogHelper.info(getClass() + ".setTicketStatusToBeUsed()", "调用券接口参数：" + json);
                        String re = cardInterfaceService.setTicketStatusToBeUsed(json);
                        LogHelper.info(getClass() + ".setTicketStatusToBeUsed()", "调用券接口结果:" + re);
                    } catch (Exception e) {
                        throw new RuntimeException("ticket");
                    }
                }
                payfee.setOrderId(accountId);
                payfee.setFeeSum(payfee.getPlateFee());
                //payfee.setPlatformFee(payfee.getPlateFee());
                payfee.setPlatformOrderId(orderVIndex.getoId());
                payfee.setFeeType(1);
                payfee.setIsBackType(1);
                payfee.setPayStatus(20110002);
                this.wrPayfeeMapper.insertPayfee(payfee);
            }
        }
        LogHelper.info(getClass() + ".insertVIndexOrderAccountPayfee()", "返回订单参数11:" + orderId + "-" + msg);
        //他营单次服务，将已受理状态直接
        if (orderVIndex.getCateType() == 4) {
            try {
                redisClient.lpush(CommonConstant.REDIS_ORDER_STATUS, "{orderId:" + order.getId() + ",orderStatus:" + order.getOrderStatus() + "}");
                LogHelper.info(getClass() + ".updateOrder()", "他营单次服务更新订单状态:" + order.getOrderStatus());
            } catch (RedisAccessException e) {
                LogHelper.info(getClass() + ".redis错误", "redis错误:" + e);
            }
        }
        LogHelper.info(getClass() + ".insertVIndexOrderAccountPayfee()", "返回订单参数:" + orderId + "-" + msg);
        return orderId + "-" + msg;
    }


    public static String checkVIndexOrder(OrderVIndex orderVIndex) {
        String re = "";
        if (orderVIndex.getCateType() == null) {
            return "cateType不能为空 ";
        }
        if (orderVIndex.getuMobile() == null) {
            return "uMobile不能为空 ";
        }

        return re;
    }

    @Override
    public String updatePlateOrder(String data) throws BaseException {
        String result = "";
        try {
            JSONObject obj = JSONObject.fromObject(data);
            //获取订单ID和订单状态
            Long orderId = obj.getLong("orderId");
            String orderStatus = obj.getString("orderStatus");
            if (orderId == null || orderStatus == null) throw new BaseException("orderId或者orderStatus为空");
            Order order = this.reOrderMapper.loadOrder(orderId);
            if (order == null) throw new BaseException("订单未找到");
            //根据渠道不同，判断调用不同更新接口方法
            if (order.getOrderChannel() != null && CommonConstant.ORDER_CHANNEL_VINDEX_ID.equals(order.getOrderChannel())) {
                boolean flag = sendToWeizhishu(order, orderStatus, null);
                LogHelper.info(getClass(), "更新微指数订单状态是否成功：" + flag);
                if (flag) {
                    result = "success";
                } else {
                    result = "fail";
                }
            }
            return result;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            LogHelper.error(getClass(), "更新失败", e);
            throw new BaseException("更新失败");
        }
    }

    @Override
    public String updatePlateOrderByAfterSale(String data) throws BaseException {
        String result = "";
        try {
            JSONObject obj = JSONObject.fromObject(data);
            //获取订单ID和订单状态
            Long orderId = obj.getLong("orderId");
            Long afterSaleId = obj.getLong("afterSaleId");
            String auditFlag = obj.getString("auditFlag");
            if (orderId == null || afterSaleId == null) throw new BaseException("orderId或者afterSaleId为空");
            //查询订单
            Order order = this.reOrderMapper.loadOrder(orderId);
            if (order == null) throw new BaseException("订单未找到");
            //查询售后单
            //OrderAfterSales afterSale = this.reOrderAfterSalesMapper.loadAfterSales(afterSaleId);
            //根据渠道不同，判断调用不同更新接口方法
            if (order.getOrderChannel() != null && CommonConstant.ORDER_CHANNEL_VINDEX_ID.equals(order.getOrderChannel())) {
                boolean flag = sendToWeizhishu(order, null, auditFlag);
                LogHelper.info(getClass(), "更新微指数订单状态是否成功：" + flag);
                if (flag) {
                    result = "success";
                } else {
                    result = "fail";
                }
            }
            return result;
        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            throw new BaseException("更新失败");
        }
    }

    private boolean sendToWeizhishu(Order order, String orderStatus, String auditFlag) {
        boolean result = false;
        // 创建发送map数据集合
        HashMap<String, String> data = new HashMap<String, String>();
        //微指数渠道秘钥
        data.put("code", "d7c1f463b10af9be07bab888d854bfd3");
        //微指数订单ID
        data.put("order_id", order.getThreeOrderCode());
        //将订单分类，变更，对应对方分类
        String cateType = "";
        if (order.getCateType() == 1 || order.getCateType() == 2) {
            cateType = "2";
        } else if (order.getCateType() == 3) {
            cateType = "1";
        } else {
            cateType = "3";
        }
        data.put("category_id", cateType);
        //将订单状态变更，对应对方提供状态
        String vIndexStatus = "";
        //是否为退款处理
        String vIndexQuality = "0";
        //订单状态是否允许退款 0:可退款    1:不可退款
        String vIndexIsRefund = "1";
        //物流单号
        String logisticsNum = "";
        //物流名称拼音
        String logisticsName = "";
        //判断退款标记
        vIndexIsRefund = checkIsRefundStatus(order.getCateType(), orderStatus, order.getId());
        //订单状态对应三方订单状态处理
        if (orderStatus != "" && "18".equals(orderStatus)) {//待受理
            vIndexStatus = "1";
        } else if (orderStatus != "" && "2".equals(orderStatus)) {//已受理
            vIndexStatus = "2";
        } else if (orderStatus != "" && "13".equals(orderStatus)) {//拣货中
            vIndexStatus = "3";
        } else if (orderStatus != "" && "14".equals(orderStatus)) {//配送中
            vIndexStatus = "4";
            Order orderParcel = this.reOrderMapper.loadParcelByOrderId(order.getId());
            if (orderParcel == null) {
                LogHelper.info(getClass(), "查询订单物流信息失败，等待5s再次查询：" + order.getId());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                orderParcel = this.reOrderMapper.loadParcelByOrderId(order.getId());
            }
            logisticsNum = orderParcel.getLogisticsNumber();
            logisticsName = orderParcel.getLogisticsName();
            logisticsName = LogisticsUtil.nameToPinyin(logisticsName);
        } else if (orderStatus != "" && "3".equals(orderStatus)) {//匹配中
            vIndexStatus = "5";
        } else if (orderStatus != "" && "4".equals(orderStatus)) {//已匹配
            vIndexStatus = "6";
        } else if (orderStatus != "" && ("6".equals(orderStatus) || "7".equals(orderStatus))) {//面试成功、已签约对应已匹配
            vIndexStatus = "6";
        } else if (orderStatus != "" && "8".equals(orderStatus)) {//已上户
            vIndexStatus = "7";
        } else if (orderStatus != "" && "11".equals(orderStatus)) {//已下户
            vIndexStatus = "8";
        } else if (orderStatus != "" && "9".equals(orderStatus)) {//已完成
            vIndexStatus = "9";
        } else if (orderStatus != "" && "10".equals(orderStatus)) {//已取消
            vIndexStatus = "10";
            vIndexQuality = "1";//是售后处理
        }
        //售后状态对应三方订单状态处理
        if (auditFlag != null && auditFlag != "") {
			/*if ("20260001".equals(auditFlag)) {//已取消
				vIndexStatus = "13";
			}else*/
            if ("20130001".equals(auditFlag) || "20130003".equals(auditFlag) || "20130004".equals(auditFlag)) {//待确认、确认有效、审核中
                vIndexStatus = "11";
            } else if ("20130002".equals(auditFlag) || "20130005".equals(auditFlag)) {//确认无效、审核失败
                vIndexStatus = "12";
            } else if ("20130007".equals(auditFlag) || "20130006".equals(auditFlag)) {//退款中、审核成功
                vIndexStatus = "14";
            } else if ("20130008".equals(auditFlag)) {//退款成功
                vIndexStatus = "15";
            }
            vIndexQuality = "1";//是售后处理
        }
        data.put("status", vIndexStatus);
        //微指数订单是否为售后处理
        data.put("chargeback", vIndexQuality);
        //物流号
        data.put("logistics_num", logisticsNum);
        //物流名称
        data.put("logistics_name", logisticsName);
        //是否可退款
        data.put("is_refund", vIndexIsRefund);
        //调用微指数接口
        LogHelper.info(getClass(), "更新微指数订单参数信息：" + data);
        String message = HttpUtil.post("http://crm.weizhishu.com/index.php/shop/Interface/order.html", data, "", "utf-8");
        System.out.println(message);
        LogHelper.info(getClass(), "更新微指数订单接收信息：" + message);
        JSONObject obj = JSONObject.fromObject(message);
        if ("0".equals(obj.get("errno")) || obj.getInt("errno") == 0) {//返回成功提示
            result = true;
        } else if ("1".equals(obj.get("errno")) || obj.getInt("errno") == 1) {//返回失败提示
            result = false;
        }
        return result;
    }

    /**
     * 售后单更新状态对外接口
     */
    @Override
    public String updateAfterSalesForOutside(String afterSaleJson) throws BaseException {
        System.out.println("接受的三方售后afterSaleJson字符串：" + afterSaleJson);
        LogHelper.info(getClass(), "接受的三方售后afterSaleJson字符串：" + afterSaleJson);
        String msg = "";
        try {
            JSONObject jsonOrderSale = JSONObject.fromObject(afterSaleJson);
            //JSONObject jobjOrder = JSONObject.fromObject(jsonOrderSale.get(0));
            if ("1".equals(jsonOrderSale.get("handle")) || jsonOrderSale.getInt("handle") == 1) {
                String oId = jsonOrderSale.get("oId").toString();//三方订单ID
                String cateType = jsonOrderSale.get("cateType").toString();//订单类型
                String oChannel = jsonOrderSale.get("oChannel").toString();//订单渠道
                String oStatus = jsonOrderSale.get("oStatus").toString();//订单状态
                String chargeback = jsonOrderSale.get("chargeback").toString();//是否是售后标记
                //判断售后json串必填字段是否为空
                String result = checkOrderSale(oId, cateType, oChannel);
                LogHelper.info(getClass(), "checkOrderSale()的result：" + result);
                if (!"".equals(result)) {
                    throw new BaseException("{'code':'2','msg':'" + result + "'}");
                }
                //更新订单及售后单信息
                try {
                    msg = updateOrderAndSalesForOutside(oId, cateType, oChannel, oStatus, chargeback);
                    LogHelper.info(getClass(), "返回更新售后和订单的msg：" + msg);
                } catch (BaseException e) {
                    LogHelper.error(getClass(), e.getMessage() + afterSaleJson, e);
                    throw new BaseException(e.getMessage());
                }
            } else {
                msg = "{'code':'2','msg':'请求操作失败！'}";
            }
        } catch (Exception e) {
            LogHelper.error(getClass(), "更新售后单及订单状态失败:" + msg, e);
        }
        return msg;
    }

    /**
     * 更改售后单状态
     *
     * @param oId
     * @param cateType
     * @param oChannel
     * @param oStatus
     * @param chargeback
     * @return
     * @throws Exception
     */
    private String updateOrderAndSalesForOutside(String oId, String cateType, String oChannel, String oStatus,
                                                 String chargeback) throws Exception {
        String bachMessage = "{'code':'2','msg':'请求操作失败！'}";
        String afterSaleId = "";
        //根据三方订单ID，查询订单信息
        Order order = this.reOrderMapper.loadOrderByPlateId(oId);
        if (order == null) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            order = this.reOrderMapper.loadOrderByPlateId(oId);
        }
        LogHelper.info(getClass(), "根据三方订单查到的订单ID：" + order.getId());
        LogHelper.info(getClass(), "更新三方订单类型：" + cateType);
        LogHelper.info(getClass(), "未更新订单时的状态：" + order.getOrderStatus());
        LogHelper.info(getClass(), "订单的渠道ID：" + oChannel);
        //订单非空并且为已取消,FA、单次服务的订单类型
        if (order != null && "9".equals(oStatus) && (order.getCateType() == 1 || order.getCateType() == 3 || order.getCateType() == 4)) {
            try {
                OrderAfterSales afterSales = new OrderAfterSales();
                afterSales.setOrderId(order.getId());
                afterSales.setCustName(order.getUserName());
                afterSales.setCustMobile(order.getUserMobile());
                afterSales.setIsAT(2);//自动录入
                Long managerDept = 1l;
                LogHelper.info(getClass(), "订单的负责部门：" + order.getRechargeDeptText());
                //定义订单负责人信息
                Manager manager = new Manager();
                //默认录入人为订单的负责人
                if (order.getRechargeBy() != null) {
                    afterSales.setCreateBy(order.getRechargeBy());//创建人默认为订单负责人
                    afterSales.setCreateDept(order.getRechargeDept());//创建部门默认为订单负责部门
                    afterSales.setLoginBy(order.getRechargeBy());//按负责人查询
                    manager.setId(order.getRechargeBy());//订单负责人ID
                } else {//如果没有负责人，随机分给呼叫中心一个客服人员
                    List<Managers> list = this.orderService.queryCallCenter();
                    if (list.size() != 0 && !list.isEmpty()) {
                        int index = getRandom().nextInt(list.size());
                        Long managerId = list.get(index).getId();
                        managerDept = list.get(index).getDeptId();
                        LogHelper.info(getClass(), "订单的负责人ID：" + managerId);
                        LogHelper.info(getClass(), "订单的负责部门ID：" + managerDept);
                        afterSales.setCreateBy(managerId);//创建人默认为订单负责人
                        afterSales.setCreateDept(managerDept);//创建部门默认为订单负责部门
                        afterSales.setLoginBy(managerId);//按负责人查询
                        manager.setId(managerId);//订单负责人ID
                    }
                }
                LogHelper.info(getClass(), "获得的录入人ID：" + afterSales.getCreateBy());
                //loginlevel设置为2,高级管家权限
                afterSales.setLoginLevel(2);
                //获取订单城市并搜索该城市相关部门
				/*if (order.getRechargeDept() != null) {
				String orgCode = order.getRechargeDept().toString().substring(0, 4);
				afterSales.setLoginOrgCode(orgCode);
			}*/
                //负责人部门
                if (order.getRechargeDept() != null) {
                    afterSales.setLoginDept(order.getRechargeDept());
                } else {
                    afterSales.setLoginDept(managerDept);
                }
                if (order.getCateType() == 1) {//自营单次
                    //自营单次订单：已受理、匹配中、已匹配 以上状态可以取消
                    if (order.getOrderStatus() == 2 || order.getOrderStatus() == 3 || order.getOrderStatus() == 4) {
                        afterSales.setAfterSalesKind("4");
                        afterSaleId = this.orderAfterSalesService.insertOrderAfterSales(afterSales);
                        LogHelper.info(getClass(), "更新售后单及订单状态方法返回售后ID：" + afterSaleId);
                    } else {
                        bachMessage = "{'code':'2','msg':'订单状态不允许取消操作！'}";
                    }
                } else if (order.getCateType() == 3) {//FA
                    //FA订单：新建、待受理、已受理 以上状态可以取消
                    if (order.getOrderStatus() == 1 || order.getOrderStatus() == 18 || order.getOrderStatus() == 2) {
                        afterSales.setAfterSalesKind("1");
                        afterSaleId = this.orderAfterSalesService.insertOrderAfterSales(afterSales);
                        LogHelper.info(getClass(), "更新售后单及订单状态方法返回售后ID：" + afterSaleId);
                    } else {
                        bachMessage = "{'code':'2','msg':'订单状态不允许取消操作！'}";
                    }
                } else if (order.getCateType() == 4) {//他营单次
                    //他营单次订单：服务开始时间之前4个小时可以取消
                    afterSales.setAfterSalesKind("4");
                    afterSaleId = this.orderAfterSalesService.insertOrderAfterSales(afterSales);
                    LogHelper.info(getClass(), "更新售后单及订单状态方法返回售后ID：" + afterSaleId);
                }
                if ("".equals(afterSaleId)) {
                    bachMessage = "{'code':'2','msg':'更新订单状态失败！'}";
                } else {
                    bachMessage = "{'code':'1','msg':'更新订单状态成功！'}";
                    //根据订单负责人ID，查询负责人手机号
                    Manager hasManager = reManagerMapper.load(manager.getId());
                    if (hasManager == null) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        hasManager = reManagerMapper.load(manager.getId());
                    }
                    LogHelper.info(getClass() + "queryUserByMobile()", "售后单负责人电话:" + hasManager.getPhone());
                    SendAfterSaleMsgThread afterSaleMsgThread = new SendAfterSaleMsgThread(hasManager.getRealName(), order.getOrderCode().toString(), afterSaleId.toString());
                    afterSaleMsgThread.start();
                }

            } catch (BaseException e) {
                LogHelper.error(getClass(), e.getMessage() + bachMessage, e);
            }

        }
        return bachMessage;
    }

    //判断售后字段是否为空方法
    public String checkOrderSale(String oId, String cateType, String oChannel) {
        String re = "";
        if (oId == null || "".equals(oId)) {
            return "oId不能为空！";
        }
        if (cateType == null || "".equals(cateType)) {
            return "cateType不能为空 ！";
        }
        if (oChannel == null || "".equals(oChannel)) {
            return "oChannel不能为空 ！";
        }
        return re;
    }

    //判断是否退款方法，并返回标记
    private String checkIsRefundStatus(Integer cateType, String orderStatus, Long orderId) {
        String result = "1";
        if (cateType == 1) {//自营单次
            if ("2".equals(orderStatus) || "3".equals(orderStatus) || "4".equals(orderStatus)) {//已受理、匹配中、已匹配
                result = "0";
            }
        } else if (cateType == 2) {//自营延续
            if ("2".equals(orderStatus) || "3".equals(orderStatus) || "4".equals(orderStatus)
                    || "5".equals(orderStatus) || "6".equals(orderStatus) || "7".equals(orderStatus)
                    || "8".equals(orderStatus) || "11".equals(orderStatus)) {//已受理、匹配中、待面试、面试成功、已签约 ,已上户和已下户
                result = "0";
            }
        } else if (cateType == 3) {//FA
            if ("1".equals(orderStatus) || "2".equals(orderStatus) || "18".equals(orderStatus)) {//新建、待受理、已受理
                result = "0";
            }
        } else if (cateType == 4) { //他营单次
            String sysTime = orderService.querySysdate().substring(0, 19);
            String startServiceTime = orderService.queryServiceStartTime(orderId).substring(0, 19);
            if (startServiceTime != null && !"".equals(startServiceTime)) {
                Date sysDate = com.emotte.order.util.DateUtil.str2Date(sysTime);
                Date startDate = com.emotte.order.util.DateUtil.str2Date(startServiceTime);
                int betweenhours = (int) ((startDate.getTime() - sysDate.getTime()) / (60 * 60 * 1000));
                //系统时间大于开始服务时间4小时，可以退款
                if (betweenhours >= 4) {
                    result = "0";
                }
            }

        }
        LogHelper.info(getClass(), "判断是否退款方法返回标记：" + result);
        return result;
    }

    //双重校验锁获取一个Random单例
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    //发送redis
    /*public void sendRedis(Long orderId, Long personId){
    	//订单设置取消的redis
		RedisClient redisClient = (RedisClient) SpringContext.getApplicationContext().getBean("redisClient");
		//当前时间+20分钟
		long time = new Date().getTime() + 20*60*1000 ;
		//long time = new Date().getTime() + 3*60*1000 ;
		try {
			redisClient.zadd(CommonConstant.ORDER_CANCLE_EMP_SCHEDULE,time, "{orderId:"+orderId+",empId:"+personId+"}");
			LogHelper.info(getClass() + ".zadd()","发送redis参数："+"{orderId:"+orderId+",empId:"+personId+"}");
		} catch (RedisAccessException e) {
			LogHelper.debug(getClass() + ".zadd()", "发送redis参数失败！");
		}
    }*/
    //推送服务人员
    public void sendPush(Long personId) {
        try {
            String title = "您有一条新的管家帮订单，请到订单列表里查询";
            String description = "您有一条新的\"管家帮\"订单，请到订单列表里查询";
            String pushmsg = "您有一条新的管家帮订单，请到订单列表里查询";
            Integer push = this.pushInterfaceService.push(personId, 1L, title, description, pushmsg);
            LogHelper.info(getClass() + ".pushInterfaceService.push()", "推送服务人员排期：" + push);
        } catch (BaseException e) {
            LogHelper.error(getClass(), ".pushInterfaceService.push()" + e.getMessage(), e);
        }
    }


    /**
     * 添加活动和结算单方案
     */
    @Override
    public String insertActivityAccount(String json) {
        LogHelper.info(getClass() + ".insertActivityAccount()", "接收的数据:" + json);
        EJedisPool pool = (EJedisPool) SpringContext.getBean("jedisPool");
        String result = "{'code':'1','msg':'请求操作失败'}";
        JSONObject jsonObject = JSONObject.fromObject(json);
        String json1 = null;
        BigDecimal realityMoney = null;
        BigDecimal balance = null;
        try {
            if (null != jsonObject) {
                //调用生成结算单方法 返回结算单id
                //String data=insertOrUpdateAccount(json1);
                //{'accountId':'" + accountId + "','code':'0','msg':'请求操作成功'}
				/*JSONObject jsonObject1=jsonObject.fromObject(data);
				String code=jsonObject1.get("code").toString();
				if(!"0".equals(code)){
					throw new BaseException(jsonObject1.getString(data));
				}*/
                Long activityId = Long.valueOf(jsonObject.get("prestoreId").toString()); //活动id
                Long accountId = Long.valueOf(jsonObject.get("accountId").toString()); //结算单id
                Long orderId = Long.valueOf(jsonObject.getString("orderId"));//订单id
                Integer type = Integer.valueOf(jsonObject.getString("type")); //类型
                AccountActivity accountActivity1 = new AccountActivity();
                accountActivity1.setAccountId(accountId);
                accountActivity1.setOrderId(orderId);
                accountActivity1.setType(type);
                accountActivity1.setStatus(1); //状态 1未处理 2成功 3失败 4不做处理
                accountActivity1.setActivityId(activityId);
                //绑定活动和结算单
                int num = this.wrPayfeeMapper.insertAccountActivity(accountActivity1);
                if (num > 0) {
                    //调用kafka往里面放值
                    FutureWithKafka futureWithKafka = new FutureWithKafka("order", pool);
                    ResultData<String> resultDa = futureWithKafka.setData("CheckFullCut", String.valueOf(accountId));
                    String data = futureWithKafka.getData(resultDa);
                    System.out.println(data);

                    AccountPay accountpay = new AccountPay();
                    accountpay.setId(accountId);
                    accountpay.setOrderId(orderId);
                    //查询结算单
                    AccountPay accountpay1 = reAccountPayMapper.selectAccountPay(accountpay);
                    if (null == accountpay1) {
                        throw new BaseException("查询结算单失败!");
                    }
                    //double money1=accountpay1.getMoney();//服务人员月服务费暂时没用
					/*Item item=reItemMapper.loadItemByOrderId(orderId);
					String productCode=item.getProductCode();*/
                    //activityMoneyGrade1.setProductCode(productCode);


                    //返回状态
					/*String data="{'status':'1','msg':'参加满减'}";
					JSONObject jsonObject2=JSONObject.fromObject(data);*/
                    BigDecimal money = accountpay1.getAccountAmount(); //客户实际缴费
                    if ("1".equals(data)) {
                        ActivityMoneyGrade activityMoneyGrade1 = new ActivityMoneyGrade();
                        activityMoneyGrade1.setId(activityId);//档位id
                        //查询查询金额档次减免钱数
                        ActivityMoneyGrade activityMoneyGrades = reActivityMoneyGradeMapper.queryActivityMoneyGrade(activityMoneyGrade1);
                        balance = activityMoneyGrades.getBalance();
                        realityMoney = money.subtract(balance);
                    } else if ("0".equals(data)) {
                        realityMoney = money; //返回客户缴费不优惠
                        balance = new BigDecimal("0");
                    } else if ("fail".equals(data)) {
                        throw new BaseException("生成缴费失败!");
                    } else {
                        throw new BaseException("程序出错!");
                    }
                    result = "{'code':'0','msg':'请求成功','realityMoney':'" + realityMoney + "','balance':'" + balance + "','accountId':'" + accountId + "'}";
                } else {
                    throw new BaseException("绑定活动结算单失败!");
                }
            }
        } catch (Exception e) {
            LogHelper.info(getClass() + ".insertActivityAccount()错误信息", e.getMessage());
        } finally {
            pool.close();
        }
        LogHelper.info(getClass() + ".insertActivityAccount()返回数据", result);
        return result;
    }

    /**
     * 回购售后
     *
     * @param afterSalesNewJson json字符串
     * @Author zhanghao
     * @Date 20180609
     */
    @Override
    @Transactional
    public void buyBackAfterSales(String afterSalesNewJson) {
        LogHelper.info(OrderAfterSalesServiceImpl.class, "触发buyBackAfterSales方法，请求参数：" + afterSalesNewJson);
        //封装请求参数
        OrderAfterSales afterSalesNew = JSON.parseObject(afterSalesNewJson, OrderAfterSales.class);
        //保存结算单对象
        Long accountId = saveAccountPay(afterSalesNew);

        //保存售后单对象
        afterSalesNew.setAccountPayId(accountId);
        Long keyId = saveAfterSales(afterSalesNew);

        //保存缴费单对象
        savePayFee(afterSalesNew);
    }

    /**
     * 保存缴费单
     *
     * @param afterSalesNew
     * @Author zhanghao
     * @Date 20180609
     */
    private void savePayFee(OrderAfterSales afterSalesNew) {
        //创建缴费单对象
        Payfee payfee = new Payfee();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //补全参数信息
        payfee.setOrderId(afterSalesNew.getAccountPayId());//结算单ID
        String afterSalesKind = afterSalesNew.getAfterSalesKind();
        if ("19".equals(afterSalesKind)) {
            payfee.setFeePost(20250033);//支付类型
            payfee.setFeeType(5);//缴费类型
        } else {
            payfee.setFeePost(20250027);
            payfee.setFeeType(6);
        }
        payfee.setIsBackType(2);//缴费或退费类型
        payfee.setFeeSum(afterSalesNew.getRefundTotal());//缴费金额
        payfee.setAccountStatus(2);//对账状态
        payfee.setCreateTime(dateFormat.format(new Date()));//创建时间
        payfee.setCreateBy(1l);//创建人
        payfee.setSplitStatus("2");//分账状态
        payfee.setValid(1);//有效标识
        payfee.setPayStatus(20110001);//支付状态
        wrAfterSalesMapper.savePayFee(payfee);
    }

    /**
     * 保存结算单
     *
     * @param afterSalesNew
     * @return
     * @Author zhanghao
     * @Date 20180609
     */
    @Transactional
    private Long saveAccountPay(OrderAfterSales afterSalesNew) {
        Payfee payfee = new Payfee();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        payfee.setOrderId(afterSalesNew.getOrderId());//订单ID
        payfee.setAccountAmount(afterSalesNew.getRefundTotal());//退款金额
        payfee.setCreateTime(dateFormat.format(new Date()));//创建时间
        payfee.setCreateBy(afterSalesNew.getCreateBy());//创建人
        payfee.setUpdateBy(afterSalesNew.getCreateBy());//更新人
        payfee.setUpdateTime(dateFormat.format(new Date()));//更新时间
        payfee.setVersion(afterSalesNew.getVersion());//版本号
        payfee.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08));//结算单支付状态(update 20190118 zhanghao 20130001>20130008退款成功)
        String afterSalesKind = afterSalesNew.getAfterSalesKind();
        if ("19".equals(afterSalesKind)) {//分期回购海金保理
            payfee.setPayType("15");
            payfee.setRefundObject(3);//海金保理退费对象
        } else if ("20".equals(afterSalesKind)) {//分期回购唯品会
            payfee.setPayType("16");
            payfee.setRefundObject(1);//唯品会退费对象
        }
        payfee.setIsBackType(1);//退款结算单
        payfee.setValid(1);//有效状态
        payfee.setPayKind(2);//结算单关联种类为：订单
        payfee.setBussStatus(2);//业务未处理状态
        payfee.setRemark(afterSalesNew.getRemark());//备注信息
        payfee.setIsOldData(null);//不是老数据
        payfee.setIsManual(1);//后台录入
        payfee.setMemberFee(null);//老数据割接存信息费临时用
        payfee.setFeeToDate(null);//结算对应日期（老数据用）
        payfee.setFinishRefundTime(null);//退款成功时间
        payfee.setCustomerFee(null);//预计客户信息费
        payfee.setPersonalFee(null);//预计服务人员信息费
        payfee.setPersontAndDate(null);//平台实付总金额
        payfee.setOldAccountId(null);//老结算ID
        payfee.setPlatformAllFee(null);//平台实付总金额
        payfee.setVphStatus(null);//管家帮分期取消状态
        payfee.setVphBackStatus(null);//管家帮分期退费状态
        payfee.setOaNumber(null);//OA单号
        payfee.setPayBanknum(null);//打款银行账号
        payfee.setBankFlowNum(null);//银行流水ID号
        payfee.setRemark2(null);//财务审核退费导入需要一个备注
        payfee.setCreateType(null);//生成类型
        payfee.setLog(null);//日志信息

        //执行保存
        wrAfterSalesMapper.saveAccountPay(payfee);
        Long keyId = payfee.getId();
        return keyId;
    }

    /**
     * 保存售后单对象
     *
     * @param afterSalesNew 售后单对象
     * @Author zhanghao
     * @Date 20180609
     */
    @Transactional
    private Long saveAfterSales(OrderAfterSales afterSalesNew) {
        //创建售后单对象
        OrderAfterSales orderAfterSales = new OrderAfterSales();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //补全信息
        afterSalesNew.setCreateBy(1l);//系统创建保存为1
        afterSalesNew.setUpdateBy(1l);//系统创建保存为1
        afterSalesNew.setUpdateTime(dateFormat.format(new Date()));//修改时间
        afterSalesNew.setCreateTime(dateFormat.format(new Date()));
        afterSalesNew.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);//退款单审核状态(update 20190118 zhanghao 20130011>20130008退款成功)
        String afterSalesKind = afterSalesNew.getAfterSalesKind();//售后单类型
        if ("19".equals(afterSalesKind)) {//海金保理
            afterSalesNew.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_HJBL);//银行卡
            afterSalesNew.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_HJBL);//城市
            afterSalesNew.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
            afterSalesNew.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_HJBL);//开户行名称
            afterSalesNew.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_09));//银行类型ID
            afterSalesNew.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_HJBL);//开户人姓名
            afterSalesNew.setRefundObject(3);//退费对象：海金保理
        } else {
            afterSalesNew.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_VPH);//银行卡
            afterSalesNew.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_VPH);//城市
            afterSalesNew.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
            afterSalesNew.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_VPH);//开户行名称
            afterSalesNew.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_05));//银行类型ID
            afterSalesNew.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_VPH);//开户人姓名
            afterSalesNew.setRefundObject(1);//退费对象：唯品会
        }
        wrOrderAfterSalesMapper.insertOrderAfterSales(afterSalesNew);
        Long keyId = orderAfterSales.getId();
        return keyId;
    }

    /**
     * 离职交接
     * 批量转订单
     *
     * @param json
     * @return JSONObject
     */
    @Override
    public String updateOrderHandover(String json) {
        LogHelper.info(getClass() + ".updateOrderHandover()", "接收的数据:" + json);
        JSONObject ret = new JSONObject();
        try {
            JSONObject jsonObj = JSONObject.fromObject(json);
            String msg = checkHandoverParam(jsonObj);
            if (msg != null && !"".equals(msg)) {
                return "{'rechargeBy':'','code':'1','msg':'" + msg + "'}";
            }
            Order order = new Order();
            order.setUpdateBy(jsonObj.getLong("updateBy"));//操作人id
            order.setFollowBy(jsonObj.getLong("followBy"));//离职人id
            order.setRechargeBy(jsonObj.getLong("rechargeBy"));//接收人id
            order.setRechargeDept(jsonObj.getLong("rechargeDept"));//接收人部门id
            Map<String, String> orderUserMobile = this.reOrderMapper.queryOrderUserMobile(order);//客户手机号
            List<Order> list = this.reOrderMapper.queryOrderRechargeBy(order);
            int count = this.wrOrderMapper.updateOrderHandover(order);
            if (count > 0 && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    OrderTurnLog orderTurnLog = new OrderTurnLog();
                    orderTurnLog.setOrderId(list.get(i).getId());
                    orderTurnLog.setTurnBy(jsonObj.getLong("followBy"));//离职人id
                    orderTurnLog.setTurnDept(jsonObj.getLong("followDept"));//离职人部门id
                    orderTurnLog.setRechargeBy(jsonObj.getLong("rechargeBy"));//接收人id
                    orderTurnLog.setRechargeDept(jsonObj.getLong("rechargeDept"));//接收人部门id
                    orderTurnLog.setCreateBy(jsonObj.getLong("updateBy"));//操作人id
                    orderTurnLog.setCreateDept(jsonObj.getLong("updateDept"));//操作人部门id
                    this.wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);
                }
            }
            ret.accumulate("count", count);
            ret.accumulate("mobiles", orderUserMobile != null ? orderUserMobile.get("userMobile") : "");
            ret.accumulate("rechargeBy", order.getRechargeBy());
            ret.accumulate("code", 0);
            ret.accumulate("msg", "请求操作成功");
        } catch (Exception e) {
            LogHelper.info(getClass() + ".updateOrderHandover()", "异常:" + e);
            return "{'rechargeBy':'','code':'1','msg':'请求操作失败'}";
        }
        LogHelper.info(getClass() + ".updateOrderHandover()", "返回的数据:" + ret);
        return ret.toString();
    }

    /**
     * 离职交接
     * 批量转解决方案
     *
     * @param json
     * @return JSONObject
     */
    @Override
    public String updateSolutionHandover(String json) {
        LogHelper.info(getClass() + ".updateSolutionHandover()", "接收的数据:" + json);
        JSONObject ret = new JSONObject();
        try {
            JSONObject jsonObj = JSONObject.fromObject(json);
            String msg = checkHandoverParam(jsonObj);
            if (msg != null && !"".equals(msg)) {
                return "{'rechargeBy':'','code':'1','msg':'" + msg + "'}";
            }
            Order order = new Order();
            order.setUpdateBy(jsonObj.getLong("updateBy"));//操作人id
            order.setFollowBy(jsonObj.getLong("followBy"));//离职人部门id
            order.setRechargeBy(jsonObj.getLong("rechargeBy"));//接收人id
            order.setRechargeDept(jsonObj.getLong("rechargeDept"));//接收人部门id
            Map<String, String> solutionUserMobile = this.reOrderMapper.querySolutionUserMobile(order);//客户手机号
            int count = this.wrOrderMapper.updateSolutionHandover(order);
            ret.accumulate("count", count);
            ret.accumulate("mobiles", solutionUserMobile != null ? solutionUserMobile.get("userMobile") : "");
            ret.accumulate("rechargeBy", order.getRechargeBy());
            ret.accumulate("code", 0);
            ret.accumulate("msg", "请求操作成功");
        } catch (Exception e) {
            LogHelper.info(getClass() + ".updateSolutionHandover()", "异常:" + e);
            return "{'rechargeBy':'','code':'1','msg':'请求操作失败'}";
        }
        LogHelper.info(getClass() + ".updateSolutionHandover()", "返回的数据:" + ret);
        return ret.toString();
    }

    /**
     * 离职交接字段验证
     *
     * @param jsonObj
     * @return
     */
    public static String checkHandoverParam(JSONObject jsonObj) {
        String result = "";
        Object followBy = jsonObj.get("followBy");//离职人id
        Object followDept = jsonObj.get("followDept");//离职人部门id
        Object rechargeBy = jsonObj.get("rechargeBy");//接收人id
        Object rechargeDept = jsonObj.get("rechargeDept");//接收人部门id
        Object updateBy = jsonObj.get("updateBy");//操作人id
        Object updateDept = jsonObj.get("updateDept");//操作人部门id
        if (followBy == null || "".equals(followBy.toString())) {
            result = "离职人id不能为空";
        } else if (followDept == null || "".equals(followDept.toString())) {
            result = "离职人部门id不能为空";
        } else if (rechargeBy == null || "".equals(rechargeBy.toString())) {
            result = "接收人id不能为空";
        } else if (rechargeDept == null || "".equals(rechargeDept.toString())) {
            result = "接收人部门id不能为空";
        } else if (updateBy == null || "".equals(updateBy.toString())) {
            result = "操作人id不能为空";
        } else if (updateDept == null || "".equals(updateDept.toString())) {
            result = "操作人部门id不能为空";
        }
        return result;
    }

    /**
     * 功能描述: app提交售后
     *
     * @param: [json]
     * @return: java.lang.String
     * @auther: lenovo
     * @date: 2018/7/13 14:16
     */
    @Transactional
    @Override
    public String saveAfterSalesForAPP(String json) {
        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "接收的数据:" + json);
        JSONObject result = new JSONObject();
        HashMap<String, Object> map = new HashMap<>();
        String msg = "";
        try {
            //解析请求参数
            OrderAfterSales orderAfterSales = JSON.parseObject(json, OrderAfterSales.class);

            //根据订单ID查询订单对象
            Order order = reOrderMapper.loadOrder(orderAfterSales.getOrderId());

            if (order != null) {
                /*
                    自营延续类型订单：[未支付]
                    自营单次类型订单：[待支付]
                    自营商品类型订单：[新建]状态
                    以上状态直接取消订单不创建售后单
                */
                String payStatus = order.getPayStatus();//支付状态
                Integer cateType = order.getCateType();//订单类型
                Integer orderStatus = order.getOrderStatus();//订单状态
                if ((cateType == 2 && "20110001".equals(payStatus))
                        || (cateType == 1 && "20110001".equals(payStatus))
                        || (cateType == 3 && orderStatus == 1)) {

                    /**
                     * FA取消、单次取消类型售后，增加删除结算缴费功能 add 20190218 zhanghao
                     *      校验规则：
                     *          1、结算单状态：未结算、部分结算（可以删除）
                     *          2、支付方式：内部支付（卡、券、余额支付，可以删除）
                     *          3、调用财务接口：返还卡余额、返还余额账户、券置为可用（成功后可以删除）
                     */
                    if("1".equals(orderAfterSales.getAfterSalesKind()) || "4".equals(orderAfterSales.getAfterSalesKind())){
                        Long orderId = order.getId();
                        //查询订单下结算状态为结算完成的结算单
                        int accountPayFinishCount = reAccountPayMapper.findAccountByOrderIdAndPayStatus(orderId);
                        if(accountPayFinishCount != 0){//存在结算完成的结算单，返回信息
                            result.accumulate("code", "-1");
                            result.accumulate("msg", "此订单存在结算完成结算单，不可取消！");
                            return result.toString();
                        }else{//下一步校验
                            //查询订单下是否存在第三方支付缴费
                            int threePayFeeCount = rePayfeeMapper.findPayFeeByOrderIdAndFeePost(orderId);
                            if(threePayFeeCount != 0){
                                result.accumulate("code", "-1");
                                result.accumulate("msg", "此订单存在存在第三方支付方式的缴费单,不可取消！");
                                return result.toString();
                            }else{//下一步校验
                                //查询符合条件的结算单信息
                                List<Long> accountIds = reAccountPayMapper.findAccountForAppCheck(orderId);
                            }
                        }
                    }

                    //取消订单
                    wrOrderMapper.chengeOrderStauts(order.getId(), "10");
                    orderAfterSalesService.updateAgreementAndEmpScheduleForApp(order.getId(), order.getRechargeBy());
                } else {
                    //校验数据唯一性
                    Integer check = reOrderAfterSalesMapper.checkOnlyAfterSales(order.getId());
                    if (check > 0) {
                        result.accumulate("code", "-1");
                        result.accumulate("msg", "此订单已经申请售后，请等待审核结果！");
                        return result.toString();
                    }
                    //标记售后来源为移动端添加
                    orderAfterSales.setIsApp(1);
                    orderAfterSales.setAuditFlag("20130013");//审核状态为新建
                    orderAfterSales.setCreateBy(order.getRechargeBy());//移动端添加售后默认由订单负责人创建
                    orderAfterSales.setCreateDept(order.getRechargeDept());//移动端添加售后默认填写订单负责人部门ID
                    orderAfterSales.setOrderType("2");//默认关联的订单类型为订单

                    //创建售后单信息
                    wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);

                    if (order.getCateType() == 3 || order.getCateType() == 7) {
                        //调用接口，取消包裹
                        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "开始请求取消包裹接口【.cancelPackage】");
                        String subPackageMessage = subPackageService.cancelPackage(order.getId(), order.getRechargeBy());
                        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "订单ID:" + order.getId() + "取消包裹接口返回值:" + subPackageMessage);
                        if ("error".equals(subPackageMessage)) {
                            result.accumulate("code", "-3");
                            result.accumulate("msg", "取消包裹操作失败！");
                            //取消包裹失败，手动回滚数据，不插入售后信息
                            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                            return result.toString();
                        }
                    }

                    //查询负责管家信息
                    Manager manager = reOrderMapper.findManagerById(order.getRechargeBy());

                    //管家信息校验
                    if (manager != null) {
                        //用户信息
                        String userName = "";
                        if (StringUtils.isNotBlank(order.getUserName())) {
                            userName = order.getUserName();
                        }

                        //订单信息
                        String orderCateType = "";
                        if (cateType == 1) {
                            orderCateType = "自营单次";
                        } else if (cateType == 2) {
                            orderCateType = "自营延续";
                        } else if (cateType == 3) {
                            orderCateType = "自营商品";
                        } else if (cateType == 4) {
                            orderCateType = "他营单次";
                        } else {
                            orderCateType = "解决方案订单";
                        }

                        //创建短信发送内容
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.accumulate("mobiles", manager.getPhone());
                        jsonObject.accumulate("userIds", manager.getId());
                        jsonObject.accumulate("userNames", manager.getRealName());
                        jsonObject.accumulate("templet", "order_705");
                        jsonObject.accumulate("channel", "sys");
                        jsonObject.accumulate("system", "order");
                        jsonObject.accumulate("rate", "1");
                        jsonObject.accumulate("source", "订单系统|接口|移动端添加售后");
                        jsonObject.accumulate("params", new String[]{userName, order.getUserMobile(), orderCateType, order.getOrderCode()});
                        String msgStr = jsonObject.toString().replace("null", "\"\"");
                        String returnmsg = this.smsDubbo.send(msgStr);
                        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "售后单提交短信发送结果:" + returnmsg);
                        result.accumulate("afterSaleId", orderAfterSales.getId().toString());
                    } else {
                        result.accumulate("code", "-1");
                        result.accumulate("msg", "该订单无管家信息！");
                        result.accumulate("afterSaleId", orderAfterSales.getId().toString());
                        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "移动端添加售后返回结果:" + result.toString());
                        return result.toString();
                    }
                }
            }
            result.accumulate("code", "0");
            result.accumulate("msg", "操作成功！");
        } catch (Exception e) {
            result.accumulate("code", "-2");
            result.accumulate("msg", "DUBBO请求失败！");
            LogHelper.error(getClass() + ".saveAfterSalesForAPP()", "异常信息:" + ExceptionUtils.getStackTrace(e));
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        LogHelper.info(getClass() + ".saveAfterSalesForAPP()", "移动端添加售后返回结果:" + result.toString());
        return result.toString();
    }

    public void payfeeAddInkafka(String payfeeId) {
        try {
            LogHelper.info(getClass(), "放入kafka中的值:t_order_payfee" + payfeeId);
            KafkaFactory.getProducerHandler().produce("t_order_payfee", "", payfeeId);
            LogHelper.info(getClass(), "放入kafka中的值:" + payfeeId + "成功");
        } catch (Exception e) {
            LogHelper.error(getClass(), "payfeeAddInkafka:" + e);
        }
    }

    /**
     * 根据结算单ID删除结算缴费
     *
     * @param json
     * @return
     * @Author zhanghao
     * @Date 20190118
     */
    @Override
    public String deleteAccountAndPayFeeByAccountId(String json) {
        JSONObject jsonObject = new JSONObject();
        String code = "";
        String msg = "";
        try {
            //解析参数
            JSONObject param = JSONObject.fromObject(json);
            Long accountId = param.getLong("accountId");
            Long updateBy = param.getLong("updateBy");
            int accountCount = reAccountPayMapper.findCountByAccountIdForDelete(accountId);
            if(accountCount == 0){//没有符合条件的结算单
                code = "01";//标记失败
                msg = "结算单结算状态不符合删除条件";
            }else{//有结果集，下一步校验
                int orderPayFeeCount = rePayfeeMapper.findCountByAccountIdForDelete(accountId);
                if(orderPayFeeCount != 0){//有结果集，存在不符合条件记录
                    code = "01";//标记失败
                    msg = "存在第三方支付方式的缴费单,不可删除";
                }else{//没有结果集,调用财务接口
                    //调用财务接口获取结果集
                    String result = activitiesEmottePayService.reBackCardInfo(accountId);
                    JSONObject resultJson = JSONObject.fromObject(result);
                    String resultCode = resultJson.getString("code");
                    if("0".equals(resultCode)){
                        AccountPay accountPay = new AccountPay();
                        accountPay.setId(accountId);
                        accountPay.setUpdateBy(updateBy);
                        accountPay.setValid(2l);
                        //删除结算
                        wrAccountPayMapper.deleteAccountPayById(accountPay);
                        //删除缴费
                        wrPayfeeMapper.deletePayfeeByAccountId(accountPay);
                        code = "00";
                        msg = "结算缴费删除成功!";
                    }
                }
            }
        } catch (Exception e) {
            code = "02";
            msg = "deleteAccountAndPayFeeByAccountId程序错误!";
            LogHelper.error(getClass(), "deleteAccountAndPayFeeByAccountId方法执行失败,参数信息:"+json+",错误信息:" + e);
        }
        jsonObject.accumulate("code",code);
        jsonObject.accumulate("msg",msg);
        return jsonObject.toString();
    }
}
