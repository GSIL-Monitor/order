package com.emotte.order.order.service.impl;

import com.emotte.dubbo.activities.service.SolutionDubboCardService;
import com.emotte.dubbo.activities.solution.SolutionOrderInterfaceService;
import com.emotte.dubbo.service.PersonnelInterfaceService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.dubbo.wms.SubPackageService;
import com.emotte.eclient.EClientContext;
import com.emotte.interf.EScheduleService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.constant.AfterSaleConstant;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.order.mapper.reader.*;
import com.emotte.order.order.mapper.writer.*;
import com.emotte.order.order.model.*;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderAfterSalesService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Constants;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("orderAfterSalesService")
@Transactional
public class OrderAfterSalesServiceImpl implements OrderAfterSalesService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReOrderAfterSalesMapper reOrderAfterSalesMapper;

	@Resource
	private WrOrderAfterSalesMapper wrOrderAfterSalesMapper;

	@Resource
	private RePayfeeMapper rePayfeeMapper;

	@Resource
	private WrPayfeeMapper wrPayfeeMapper;

	@Resource
	private ReOrderMapper reOrderMapper;

	@Resource
	private ReAgreementMapper reAgreementMapper;

	@Resource
	private WrAgreementMapper wrAgreementMapper;

	@Resource
	private WrOrderMapper WrOrderMapper;
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private SolutionOrderInterfaceService activitiesInterfaceService;
	@Resource
	private OrderAfterSalesService orderAfterSalesService;
	@Resource
	private ReSolutionMapper reSolutionMapper;
	@Resource
	private WrSolutionMapper wrSolutionMapper;
	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private WrItemInterviewMapper wrItemInterviewMapper;
	@Resource
	private ItemInterviewServiceImpl itemInterviewServiceImpl;
	@Resource
	private SubPackageService subPackageService;
	@Resource
	private WrAccountPayBankcardMapper wrAccountPayBankcardMapper;
	@Resource
	private SolutionDubboCardService solutionDubboCardService;
	@Resource
	private ReItemDetailServerMapper reItemDetailServerMapper;
	@Resource
	private OrderService orderService;
	@Resource
	private RedisClient redisClient;
	@Resource
	private SMSServiceDubbo smsDubbo;
	@Resource
	private PersonnelInterfaceService personnelInterfaceService;
	@Resource
	private ReAfterSalesMapper reAfterSalesMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public OrderAfterSales loadOrderAfterSales(Long id) {
		return this.reOrderAfterSalesMapper.loadAfterSales(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<OrderAfterSales> queryOrderAfterSales(OrderAfterSales orderAfterSales, Page page) {
		//return this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(orderAfterSales);
		return this.reOrderAfterSalesMapper.queryAfterSalesNewlistPage(orderAfterSales);
	}

	@Override
	@Transactional
	public String insertOrderAfterSales(OrderAfterSales orderAfterSales) throws Exception {
		String afterSalesId = "";
		//查询订单信息
		Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
		//根据订单id，查询售后单是否已存在，已存在则不添加新售后单
		Page page = new Page();
		//根据订单ID查询是否有售后单
		OrderAfterSales sales = new OrderAfterSales();
		sales.setFlagPage(-1);
		sales.setPage(page);
		sales.setLoginLevel(orderAfterSales.getLoginLevel());
		sales.setLoginBy(orderAfterSales.getLoginBy());
		sales.setLoginOrgCode(orderAfterSales.getLoginOrgCode());
		sales.setLoginDept(orderAfterSales.getLoginDept());
		sales.setOrderId(orderAfterSales.getOrderId());
		page.setPageCount(-1);
		List<OrderAfterSales> salesList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(sales);
		if (salesList.size() != 0 && salesList != null && !"10".equals(orderAfterSales.getAfterSalesKind())) {
			LogHelper.info(getClass()+".insertOrderAfterSales()","售后单id为:"+salesList.get(0).getId()+"的售后单已存在!");
			return afterSalesId;
		} else {
			String auditFlag = "" ;    //售后单状态
			int accountPayStatus = 0 ; //结算单支付状态
			int orderStatus = 2 ;	   //订单状态
			String orderType = "";     //订单类型
			int payKind = 2 ;	   	   //结算单关联种类，默认为订单
			boolean result = false;    //是否成功标记
			boolean solutionItemResult = true;  //是否新增结算单标记
			boolean payFeeResult = true; //解决方案大订单是否有售后单标记
			Long vipShopId = null;       //唯品会结算单ID
			//新增缴费
			Payfee payfee = new Payfee();
			payfee.setOrderId(orderAfterSales.getOrderId());
			payfee.setIsBackType(2);//不是退款结算单
			payfee.setValid(1);
			payfee.setFeePost(orderAfterSales.getFeePost());
			//新增结算
			Payfee accounts = new Payfee();
			//定义费用
			BigDecimal allfeeSum = new BigDecimal(0);//所有结算单总和
			BigDecimal ticketfeeSum = new BigDecimal(0);//券缴费总和
			BigDecimal accountFeeSum = new BigDecimal(0); //退款结算单总费用
			//迁单设置默认银行卡号设置
			if (orderAfterSales.getMoveToOrderId() != null) {
				orderAfterSales.setBankCard("QD-0000");
				//判断由A迁单到B的，A单缴费是否全部分账
				List<Payfee> splitPayfee = this.rePayfeeMapper.queryPayfeeSplit(orderAfterSales.getOrderId());
				if (splitPayfee.size() != 0 && !splitPayfee.isEmpty()) {
					throw new Exception("被迁单的订单有缴费未分账，请分账后再迁单！");
				}
			}
			//售后单类型为1、4、5、6、8、9、10，需要判断是否支付
			if ("1".equals(orderAfterSales.getAfterSalesKind()) || "4".equals(orderAfterSales.getAfterSalesKind())
					||"5".equals(orderAfterSales.getAfterSalesKind()) || "6".equals(orderAfterSales.getAfterSalesKind())
					||"8".equals(orderAfterSales.getAfterSalesKind()) ||"9".equals(orderAfterSales.getAfterSalesKind())
					||"10".equals(orderAfterSales.getAfterSalesKind())) {
				//已支付订单
				if (order != null && ("20110002".equals(order.getPayStatus()) || "20110003".equals(order.getPayStatus())) && order.getPayStatus() != null) {
					//查询是否有结算单
					List<Payfee> accountList = this.rePayfeeMapper.queryAccount(payfee);
					if (accountList.size() != 0) {
						//创建默认管家帮分期缴费
						Payfee vphFee = new Payfee();
						//计算结算单金额
						for (Payfee account : accountList) {

							//查询是否有券缴费
							payfee.setOrderId(account.getId());
							payfee.setIsBackType(1);//设置正向缴费
							payfee.setValid(1);//可用
							payfee.setFeePost(account.getFeePost());
							List<Payfee> payfeeSearchList = this.rePayfeeMapper.queryPayfee(payfee);
							if (payfeeSearchList.size() != 0) {//有缴费
								for (Payfee feeSearch : payfeeSearchList) {
									if (feeSearch.getPayStatus() == 20110002 ) {
										//已支付缴费单
										BigDecimal allfees = new BigDecimal(feeSearch.getFeeSum().toString());
										allfeeSum = allfeeSum.add(allfees);//计算结算单总额
									}
									if (feeSearch.getPayStatus() == 20110002 && feeSearch.getAccountStatus() == 1) {
										//已支付、已对账的缴费单
										if (feeSearch.getFeeSum() != null && feeSearch.getFeePost() != null && feeSearch.getFeePost() == 20250011 ) {
											//券支付,券缴费单总和
											ticketfeeSum = ticketfeeSum.add(feeSearch.getFeeSum()).setScale(2, BigDecimal.ROUND_HALF_UP);
										}
										if (feeSearch.getFeeSum() != null && feeSearch.getFeePost() != null && feeSearch.getFeePost() == 20250027 ) {
											//管家帮分期支付
											//将分期缴费信息，赋值给vphFee
											vphFee = feeSearch;
										}
									}
								}
							}
						}
						//查询执行订单、及解决方案大订单信息
						OrderAfterSales oas = new OrderAfterSales();
						//根据执行订单的parentID，查询解决方案大订单是否有售后单
						oas.setOrderId(order.getParentId());
						Page serverPage = new Page();
						oas.setFlagPage(-1);
						oas.setPage(serverPage);
						oas.setLoginBy(orderAfterSales.getLoginBy());
						oas.setLoginDept(orderAfterSales.getLoginDept());
						serverPage.setPageCount(-1);
						if (order.getCateType() == 2 && orderAfterSales.getRefundTotal().compareTo(BigDecimal.ZERO) == 0 && allfeeSum.compareTo(BigDecimal.ZERO) == 0) {
							//自营延续,金额为0
							orderType = "2";//订单类型
							auditFlag = "20260001";//售后单:已取消
							order.setOrderStatus(10);//订单状态:已取消
							updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getCreateBy());//终止合同
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());//释放服务人员排期
						}else if (order.getCateType() == 2 && "5".equals(orderAfterSales.getAfterSalesKind())){
							//自营延续取消,服务订单状态为已受理、匹配中、待面试、面试成功、已签约,可以取消订单
							if (order.getOrderStatus() == 2 || order.getOrderStatus() == 3 || order.getOrderStatus() == 5 || order.getOrderStatus() == 6 || order.getOrderStatus() == 7) {
								orderType = "2";
								auditFlag = "20130001";//售后单待确认
								accountPayStatus = 20130001;//结算单支付状态：待确认
								accounts.setPayType("3");//延续的退款结算单类型为预收
								result = true;
							}
						}else if (order.getCateType() == 2 && "8".equals(orderAfterSales.getAfterSalesKind())) {
							if(order.getOrderStatus() == 8 || order.getOrderStatus() == 11){
								//自营延续终止,已上户和已下户状态可以终止
								orderType = "2";//售后单关联订单类型
								auditFlag = "20130001"; //售后单待确认
								accountPayStatus = 20130001; //结算单支付状态：待确认
								accounts.setPayType("3");//延续的退款结算单类型为预收
								result = true;
							}
						}else if(order.getCateType() == 1 || order.getCateType() == 3 || order.getCateType() == 4 || order.getCateType() == 8 ){
							//FA与单次服务,解决方案（仅限客户APP）
							//解决方案价格体系判断大订单取消（客户APP）
							if (order.getCateType() == 8) {
									orderType = "1";//订单类型
									auditFlag = "20130001";	//售后单待确认
									accountPayStatus = 20130001; //结算单支付状态：待确认
									accounts.setPayType("3");//结算类型：预收
							}else {
								//结算金额（实收）
								if(orderAfterSales.getSolutionType() != null && orderAfterSales.getSolutionType() == 20120008L){
									accountFeeSum = new BigDecimal(0);//赠送解决方案执行订单退款金额为0
								}else{
									accountFeeSum = allfeeSum.subtract(ticketfeeSum);//券缴费不给退
								}
								orderType = "2";                //订单类型
								auditFlag = "20130001";		    //售后单待确认
								accountPayStatus = 20130001;    //结算单支付状态：待确认
								orderStatus = 10;	            //订单已取消
								accounts.setPayType("4");       //结算类型：实收
							}
							// 单次服务订单取消
							if (order.getCateType() == 1 || order.getCateType() == 4) {
								//自营单次订单：已受理、匹配中、已匹配 以上状态可以取消
								if (order.getCateType() == 1 && (order.getOrderStatus() == 2 || order.getOrderStatus() == 3 || order.getOrderStatus() == 4)) {
									order.setOrderStatus(orderStatus);//订单状态
									result = true;
								}
								//他营单次订单：服务开始前4个小时 可以取消
								if (order.getCateType() == 4) {
									order.setOrderStatus(orderStatus);//订单状态
									result = true;
								}
								//调用释放排期接口，释放排期金额
								if (result && order.getParentId() != null && order.getParentId() != 0) {
									List<OrderAfterSales> serverSaleList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(oas);
									//解决方案大订单有退费售后单，且售后单状态不是管家审核失败
									if (serverSaleList.size() == 0 || "20130005".equals(serverSaleList.get(0).getAuditFlag())) {
										auditFlag = "20130008";	//售后单退款成功
										JSONObject sParam = new JSONObject();
										sParam.accumulate("orderId", order.getId());
										sParam.accumulate("updateBy", orderAfterSales.getCreateBy());
										LogHelper.info(getClass()+".insertOrderAfterSales()", order.getId()+"解决方案执行订单释放排期金额参数"+sParam.toString());
										String updateDCSolution = this.activitiesInterfaceService.cancelSolutionPlanOrder(sParam.toString());
										JSONObject jsonObjDc = JSONObject.fromObject(updateDCSolution);
										LogHelper.info(getClass()+".insertOrderAfterSales()", order.getId()+"解决方案执行订单释放排期金额返回"+jsonObjDc);
										if ("1".equals(String.valueOf(jsonObjDc.get("code")))) {
											result = true;//是否成功标记
											solutionItemResult = false; //是否新增结算单标记
										} else {
											result = false;//是否成功标记
										}
									}
								}
							} else if(order.getCateType() == 3 && (order.getOrderStatus() == 1 || order.getOrderStatus() == 2 || order.getOrderStatus() == 18)){
								//FA订单状态为新建、待受理、已受理状态可以取消
								order.setOrderStatus(orderStatus);//订单状态
								result = true;
								try {
									//取消包裹
									String pResult = this.subPackageService.cancelPackage(order.getId(), orderAfterSales.getCreateBy());
									LogHelper.info(getClass()+".insertOrderAfterSales()", order.getId()+"包裹取消返回"+pResult);
								} catch (Exception e) {
									LogHelper.error(getClass()+".insertOrderAfterSales()","取消包裹失败"+e.getMessage());
								}
								//调用释放排期接口，释放排期金额
								if (result && order.getParentId() != null && order.getParentId() != 0) {
									List<OrderAfterSales> faSaleList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(oas);
									if (faSaleList.size() == 0 || "20130005".equals(faSaleList.get(0).getAuditFlag())) {
										auditFlag = "20130008";		//售后单退款成功
										JSONObject sParam = new JSONObject();
										sParam.accumulate("orderId", order.getId());
										sParam.accumulate("updateBy", orderAfterSales.getCreateBy());
										LogHelper.info(getClass()+".insertOrderAfterSales()", order.getId()+"解决方案执行订单释放排期金额参数"+sParam.toString());
										String updateFASolution = this.activitiesInterfaceService.cancelSolutionPlanOrder(sParam.toString());
										JSONObject jsonObjFA = JSONObject.fromObject(updateFASolution);
										LogHelper.info(getClass()+".insertOrderAfterSales()", order.getId()+"解决方案执行订单释放排期金额返回"+jsonObjFA);
										if ("1".equals(String.valueOf(jsonObjFA.get("code")))) {
											result = true;
											solutionItemResult = false;
										} else {
											result = false;
										}
									}
								}
							}else if(order.getCateType() == 8 && order.getOrderStatus() != 1 ){
								// 解决方案大订单状态为：其他状态均可以退费,新建订单只能取消
								Solution solution = new Solution();
								solution.setId(orderAfterSales.getOrderId());
								solution.setDelFlag(2);//大订单正常
								payKind = 3 ; //设置结算单状态为解决方案
								//获得解决方案未生成执行订单的排期总余额
								BigDecimal planFeeSum = this.reSolutionMapper.querySolutionPlanFee(solution);
								if (planFeeSum == null) {
									planFeeSum = BigDecimal.ZERO;
								}
								//获得解决方案余额
								solution = this.reSolutionMapper.loadSolution(orderAfterSales.getOrderId());
								BigDecimal solutionActiveMoney = solution.getActiveMoney();
								if (solutionActiveMoney == null) {
									solutionActiveMoney = BigDecimal.ZERO;
								}
								//解决方案退款总额
								accountFeeSum = planFeeSum.add(solutionActiveMoney);
								//解决方案订单暂停中
								String solutionJson = "{\"solutionOrderId\":"+order.getId()+",\"updateBy\":"+orderAfterSales.getCreateBy()+"}";
								String updateSolution = this.activitiesInterfaceService.suspendSolution(solutionJson);
								JSONObject jsonObj = JSONObject.fromObject(updateSolution);
								System.out.println("是否取消解决方案订单状态:"+jsonObj.get("code"));
								if ("1".equals(String.valueOf(jsonObj.get("code")))) {
									order.setOrderStatus(10);//已取消
									result = true;
									try {
										String disableCardJson = "{\"cardNum\":"+solution.getFeeCardNum()+"}";
										String disableCard = this.solutionDubboCardService.disable(disableCardJson);
										JSONObject disableObj = JSONObject.fromObject(disableCard);
										System.out.println("是否作废代扣卡:"+disableObj.get("code"));
									} catch (Exception e) {}
								} else {
									result = false;
								}

							}else{
								result = false;   //其它状态不能取消订单
							}
						}
							if (result) {
								//延续服务终止退费，已上户或者已下户费用计算
								if ("8".equals(orderAfterSales.getAfterSalesKind()) && (order.getOrderStatus() == 8 || order.getOrderStatus() == 11)){
									accountFeeSum = orderAfterSales.getRefundTotal();
									if ("0.00".equals(accountFeeSum) || "0".equals(accountFeeSum)) {
										auditFlag = "20260001";//已取消
									}
									accounts.setPayType("3");//结算类型为预收
								}else if("5".equals(orderAfterSales.getAfterSalesKind())){
									accountFeeSum = orderAfterSales.getRefundTotal();
									//设置结算单类型
									accounts.setPayType("3");//结算类型为预收
								}else{
									accounts.setPayType("4");//结算类型为收入
								}
								if (solutionItemResult) {
									//查询结算单对应的缴费单
									if (order.getParentId() != null) {
										OrderAfterSales oasFirst = new OrderAfterSales();
										//根据执行订单的parentID，查询解决方案大订单是否有售后单
										oasFirst.setOrderId(order.getParentId());
										Page oasPage = new Page();
										oasFirst.setFlagPage(-1);
										oasFirst.setPage(oasPage);
										oasFirst.setLoginBy(orderAfterSales.getLoginBy());
										oasFirst.setLoginDept(orderAfterSales.getLoginDept());
										oasPage.setPageCount(-1);
										 //查询执行订单的大订单是否有售后单
										 List<OrderAfterSales> oasList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(oasFirst);
										 //解决方案大订单有售后单
										 if (oasList.size() != 0) {
											 payFeeResult = true;
										 } else {
											 payFeeResult = false;
										 }
									}
								}
								LogHelper.info(getClass()+".insertOrderAfterSales()",orderAfterSales.getOrderId()+"普通退款结算单费用："+accountFeeSum);
								LogHelper.info(getClass()+".insertOrderAfterSales()",orderAfterSales.getOrderId()+"唯品会退款结算单费用："+orderAfterSales.getVphFee());
								if (null != accountFeeSum && accountFeeSum.compareTo(BigDecimal.ZERO) == 1  && payFeeResult) {
									//结算金额大于0,生成退款结算单
									accounts = afterSaleAccount(orderAfterSales,accounts,accountFeeSum,accountPayStatus,payKind);
									//记录退款结算单id
									orderAfterSales.setAccountPayId(accounts.getId());
									//保存银行信息表的银行卡内容
									if (orderAfterSales.getBankCard() != null && !"".equals(orderAfterSales.getBankCard())) {
									    saveBankCard(orderAfterSales,accounts);
									}
								}
								if (null != orderAfterSales.getVphFee() && orderAfterSales.getVphFee().compareTo(BigDecimal.ZERO) == 1 && payFeeResult ) {
									//管家帮分期，另生成结算单和缴费，并同步分期退款状态
									vipShopId =  insertVipShopAccountFee(orderAfterSales.getOrderId(),accountPayStatus,orderAfterSales.getVphFee(),orderAfterSales.getCreateBy(),vphFee);
									if (vipShopId != null) {
										orderAfterSales.setVphAccountId(vipShopId);//将分期结算单ID放入售后单
										orderAfterSales.setVphCancleStatus(auditFlag);//同步分期取消状态
										orderAfterSales.setVphBackStatus(auditFlag);//同步分期退款状态
									}
								}
							}

					}else{
						if (orderAfterSales.getRefundTotal().compareTo(BigDecimal.ZERO) == 0 ) {
							orderType = "2"; //订单类型
							auditFlag = "20260001";//已取消
							orderStatus = 10;	//订单已取消
							order.setOrderStatus(10);//已取消
							result = cancleOrders(order);//取消订单操作
						}
					}
			//未支付订单
			} else if (order != null && "20110001".equals(order.getPayStatus()) && order.getPayStatus() != null) {
				try {
					auditFlag = "20260001";//售后单已取消
					orderStatus = 10;	//订单已取消
					order.setOrderStatus(10);//取消订单操作
					updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());//锁定服务人员排期
					updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getCreateBy());//终止合同
					//查询是否有未支付的结算单
					payfee.setPayStatus(20110001);
					List<Payfee> notPayAccountList = this.rePayfeeMapper.queryAccount(payfee);
					if (notPayAccountList != null && notPayAccountList.size() != 0) {
						for (Payfee noAccount : notPayAccountList) {
							noAccount.setValid(2);
							this.wrPayfeeMapper.updateAccount(noAccount);
							this.wrPayfeeMapper.updateAccountById(noAccount.getId());
						}
					}
					result = true;
					//addInventory(order);//增加库存
					//解决方案订单取消
				    if ("6".equals(orderAfterSales.getAfterSalesKind())) {
						orderType = "1"; //解决方案类型
						if (result) {
							//取消解决方案订单
							String solutionJson = "{\"id\":"+order.getId()+",\"updateBy\":"+orderAfterSales.getCreateBy()+",\"solutionStatus\":6,\"payStatus\":\"\"}";
							String updateSolution = this.activitiesInterfaceService.updateCustSolutionStatus(solutionJson);
							JSONObject jsonObj = JSONObject.fromObject(updateSolution);
							System.out.println("是否取消解决方案订单状态:"+jsonObj.get("code"));
							if ("1".equals(String.valueOf(jsonObj.get("code")))) {
								result = true;
							} else {
								result = false;
							}
						}
					}else {
						orderType = "2"; //订单类型
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				//已上户状态可以换人
				if (order.getOrderStatus() == 8) {
					orderType = "2"; //售后单关联订单类型
					auditFlag = "20130001"; //售后单待确认
					result = true;
				}
			}
			result = cancleOrders(order);
			if (result) {
				orderAfterSales.setOrderType(orderType);
				orderAfterSales.setAuditFlag(auditFlag);
				orderAfterSales.setVersion((long)1);
				orderAfterSales.setCreateTime(DateUtil.getCurrDateTime());
				orderAfterSales.setRefundTotal(accountFeeSum);
				this.wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);
				afterSalesId = orderAfterSales.getId().toString();
				//判断退款金额大于0，则发短信，否则不发短信
				if (accountFeeSum.compareTo(BigDecimal.ZERO) == 1) {
					sendAfterSaleSms(order,orderAfterSales,"order_262",1);
				}
			}else {
				LogHelper.info(getClass()+".insertOrderAfterSales()","新增售后单失败");
				throw new BaseException("新增售后单失败！");
			}
		  }
		}
		return afterSalesId;
	}
	/**
	 * 生成退款结算单
	 * @param orderAfterSales 售后单信息
	 * @param accounts 结算单信息
	 * @param accountFeeSum  退款金额
	 * @param accountPayStatus 支付状态
	 * @param payKind 支付类型
	 */
	private Payfee afterSaleAccount(OrderAfterSales orderAfterSales, Payfee accounts,BigDecimal accountFeeSum, int accountPayStatus, int payKind) {
		accounts.setId(null);
		accounts.setOrderId(orderAfterSales.getOrderId());//订单id
		accounts.setAccountAmount(accountFeeSum);
		accounts.setPayStatus(accountPayStatus); //退款结算单支付状态
		accounts.setPayKind(payKind);   //结算单关联订单类型
		accounts.setCreateBy(orderAfterSales.getCreateBy());
		accounts.setIsBackType(1);//是退款结算单
		accounts.setBussStatus(1);//业务未处理状态
		if (orderAfterSales.getLoginLevel() != null && orderAfterSales.getLoginLevel() == 99) {
			accounts.setIsManual(2);//APP录入
		}else {
			accounts.setIsManual(1);//后台录入
		}
		this.wrPayfeeMapper.insertAccount(accounts);//生成新结算单信息
		return accounts;
	}

	/**
	 * 保存银行信息表的银行卡内容
	 * @param orderAfterSales
	 * @param accounts
	 */
	private void saveBankCard(OrderAfterSales orderAfterSales, Payfee accounts) {
		AccountPayBankcard accountPayBankcard = new AccountPayBankcard();
		accountPayBankcard.setBankCard(orderAfterSales.getBankCard());
		accountPayBankcard.setBankUserName(orderAfterSales.getBankUserName());
		accountPayBankcard.setBankMainName(orderAfterSales.getBankMainName());
		accountPayBankcard.setBankName(orderAfterSales.getBankName());
		accountPayBankcard.setBankSupportId(orderAfterSales.getBankSupportId());
		accountPayBankcard.setAccountPayId(accounts.getId());
		accountPayBankcard.setCity(orderAfterSales.getBankCityCode());
		accountPayBankcard.setUserId(orderAfterSales.getCreateBy());
		accountPayBankcard.setValid(1);
		accountPayBankcard.setCreateBy(orderAfterSales.getCreateBy());
		this.wrAccountPayBankcardMapper.insertAccountPayBankcard(accountPayBankcard);
	}

	/**
	 * 售后发送短信
	 * @param order 订单信息
	 * @param orderAfterSales 售后信息
	 * @param templet 模板编号
	 * @param flag 短信参数种类
	 */
	private void sendAfterSaleSms(Order order, OrderAfterSales orderAfterSales,String templet,Integer flag) {
		//获取客户姓名
		String lastName = "";
		if (order.getUserName() != null && order.getUserName() != "") {
			lastName = order.getUserName().substring(0, 1);
		}
		// 设置短信内容
		JSONObject msg = new JSONObject();
		msg.accumulate("mobiles", order.getUserMobile());
		msg.accumulate("templet", templet);//模板编号
		msg.accumulate("channel", "sys");
		msg.accumulate("system", "act");
		msg.accumulate("rate", 1);
		if (flag == 1) {
			msg.accumulate("params", new String[] {lastName,orderAfterSales.getId().toString(),order.getOrderCode(),orderAfterSales.getRefundTotal().toString() });
		} else if (flag == 2)  {
			msg.accumulate("params", new String[] {lastName,order.getOrderCode() });
		}
		String msgStr = msg.toString().replace("null", "\"\"");
		String returnmsg = this.smsDubbo.send(msgStr);
		LogHelper.info(getClass() + ".insertOrderAfterSales()", "售后单提交短信发送结果:" + returnmsg);
	}

	/**
	 * 生成售后退费
	 * @param orderAfterSales 售后单信息
	 * @param accountList 结算单list
	 * @param accounts  退款结算单信息
	 * @param accountFeeSum 退费金额
	 * @param payFeeResult 是否是解决方案退费标记
	 */
	private void afterSalePayfee(OrderAfterSales orderAfterSales, List<Payfee> accountList, Payfee accounts,
			BigDecimal accountFeeSum, boolean payFeeResult) {
		//判断退费类型是否是延续订单的终止和退费
		//若是，则退到银行卡里，不是，则按要求退费
		if (accountFeeSum.compareTo(BigDecimal.ZERO) == 1 && ("8".equals(orderAfterSales.getAfterSalesKind()) || "10".equals(orderAfterSales.getAfterSalesKind()))) {
			//新增银行打卡退费缴费单
			insertAfterPayfee(orderAfterSales,accounts,orderAfterSales.getRefundTotal(),20250002,null);
		} else if (accountFeeSum.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal yxqxFeeSum = accountFeeSum;
			//查询结算单是否有缴费
			for (Payfee accList : accountList) {
				Payfee payfee = new Payfee();
				payfee.setOrderId(accList.getId());
				payfee.setIsBackType(1);//设置正向缴费
				payfee.setValid(1);//可用
				List<Payfee> payfeeList = this.rePayfeeMapper.queryPayfee(payfee);

				if (payfeeList.size() != 0 && payFeeResult ) {//有缴费
					for (Payfee fee : payfeeList) {
						//10.26修改
						if (fee.getPayStatus() == 20110002 && fee.getAccountStatus() == 1) {//已支付的缴费单
							if (!"5".equals(orderAfterSales.getAfterSalesKind())
									&& ((fee.getFeePost() == 20250001 && fee.getFeeSum() != null ) //支付宝
									|| (fee.getFeePost() == 20250002 && fee.getFeeSum() != null ) //银联
									|| (fee.getFeePost() == 20250005 && fee.getFeeSum() != null ) //pos机
									|| (fee.getFeePost() == 20250006 && fee.getFeeSum() != null ) //淘宝支付
									|| (fee.getFeePost() == 20250007 && fee.getFeeSum() != null ) //微信
									|| (fee.getFeePost() == 20250009 && fee.getFeeSum() != null ) //建行
									|| (fee.getFeePost() == 20250008 && fee.getFeeSum() != null ) //融汇天下POS
									|| (fee.getFeePost() == 20250014 && fee.getFeeSum() != null ) //他人代收（三方订单）
									|| (fee.getFeePost() == 20250015 && fee.getFeeSum() != null ) //微信扫码支付（兴业银行）
									|| (fee.getFeePost() == 20250017 && fee.getFeeSum() != null ) //嘉联pos微信支付
									|| (fee.getFeePost() == 20250018 && fee.getFeeSum() != null )//嘉联pos支付宝支付
									|| (fee.getFeePost() == 20250019 && fee.getFeeSum() != null )//嘉联pos刷卡支付
									|| (fee.getFeePost() == 20250020 && fee.getFeeSum() != null )//嘉联微信支付
									|| (fee.getFeePost() == 20250021 && fee.getFeeSum() != null )//嘉联支付宝支付
									|| (fee.getFeePost() == 20250022 && fee.getFeeSum() != null )//嘉联快捷支付
									|| (fee.getFeePost() == 20250023 && fee.getFeeSum() != null )//招商银行一网通支付
									|| (fee.getFeePost() == 20250024 && fee.getFeeSum() != null )//支付宝到位
									|| (fee.getFeePost() == 20250026 && fee.getFeeSum() != null ))) { //旺财支付
								//新增银行打卡退费缴费单
								insertAfterPayfee(orderAfterSales,accounts,fee.getFeeSum(),20250002,null);//银行卡类型
							}
							if (fee.getFeeSum() != null && fee.getFeePost() == 20250010 ) {//卡类型
								//新增卡缴费单
								insertAfterPayfee(orderAfterSales,accounts,fee.getFeeSum(),20250010,fee.getCardsNum());//卡类型
								yxqxFeeSum = yxqxFeeSum.subtract(fee.getFeeSum());
							}
							if (fee.getFeeSum() != null && fee.getFeePost() == 20250013 ) {//账户
								//新增账户缴费单
								insertAfterPayfee(orderAfterSales,accounts,fee.getFeeSum(),20250013,null);
								yxqxFeeSum = yxqxFeeSum.subtract(fee.getFeeSum());
							}
							if (fee.getFeeSum() != null && fee.getFeePost() == 20250016 ) {//白条支付
								//新增白条缴费单
								insertAfterPayfee(orderAfterSales,accounts,fee.getFeeSum(),20250016,null);
							}
							if (fee.getFeeSum() != null && fee.getFeePost() == 20250025 ) {//微指数余额
								//新增微指数余额缴费单
								insertAfterPayfee(orderAfterSales,accounts,fee.getFeeSum(),20250025,null);
								yxqxFeeSum = yxqxFeeSum.subtract(fee.getFeeSum());
							}
						}
					}
				}
			}
			//延续订单取消退费到银行卡
			if ("5".equals(orderAfterSales.getAfterSalesKind()) && yxqxFeeSum.compareTo(BigDecimal.ZERO) == 1) {
				insertAfterPayfee(orderAfterSales,accounts,yxqxFeeSum,20250002,null);
			}

		}
	}
	/**
	 * 新增售后缴费
	 * @param orderAfterSales 售后信息
	 * @param accounts 结算单
	 * @param newfee 缴费金额
	 * @param type 缴费类型
	 * @param cardNum 卡号
	 */
	private void insertAfterPayfee(OrderAfterSales orderAfterSales, Payfee accounts,BigDecimal newfee, Integer type,String cardNum) {
		Payfee fee = new Payfee();
	    fee.setId(null);
	    if (accounts.getId() != null) {
			fee.setOrderId(accounts.getId());//新增的退款结算单id
		}
	    fee.setCardsNum(cardNum);
	    fee.setFeeSum(newfee);
		fee.setFeePost(type);
		fee.setIsBackType(2);     //退款缴费
		fee.setFeeType(1);        //新增缴费
		fee.setAccountStatus(2);  //未对账
		fee.setValid(1);
		fee.setVersion((long)1);
		fee.setPayStatus(20110002);
		fee.setCreateTime(DateUtil.getCurrDateTime());
		fee.setCreateBy(orderAfterSales.getCreateBy());
		this.wrPayfeeMapper.insertPayfee(fee);//生成新缴费单信息
	}

	@Override
	@Transactional
	public void updateOrderAfterSales(OrderAfterSales orderAfterSales) {
		LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单更新:" + orderAfterSales.getAuditFlag()+"-"+orderAfterSales.getOrderId());
		try {
			if (StringUtils.isNotBlank(orderAfterSales.getCustMobile())) {
				orderAfterSales.setCustMobile(orderAfterSales.getCustMobile().trim());
			}
			if (StringUtils.isNotBlank(orderAfterSales.getCustName())) {
				orderAfterSales.setCustName(orderAfterSales.getCustName().trim());
			}
			orderAfterSales.setUpdateTime(DateUtil.getCurrDateTime());
			int returnValue;
			boolean cancleOrder = false;
			boolean cancleAgreement = false;
			boolean updateAccount = false;
			Payfee payfee = new Payfee();
			payfee.setOrderId(orderAfterSales.getOrderId());

			if (orderAfterSales.getAccountPayId() != null && (orderAfterSales.getVphAccountId() == null || orderAfterSales.getVphAccountId() == 0)) {
				payfee.setId(orderAfterSales.getAccountPayId());
			}else if ((orderAfterSales.getAccountPayId() == null || orderAfterSales.getAccountPayId() == 0) && orderAfterSales.getVphAccountId() != null) {
				payfee.setId(orderAfterSales.getVphAccountId());
			}else if (orderAfterSales.getAccountPayId() == null ) {
				payfee.setId(orderAfterSales.getVphAccountId());
			}else if (orderAfterSales.getVphAccountId() == null ) {
				payfee.setId(orderAfterSales.getAccountPayId());
			}else {
				payfee.setId(orderAfterSales.getAccountPayId());
			}
			payfee.setIsType("1");//退款标记
			payfee.setValid(1);

			//查询退款结算单
			List<Payfee> accountList = this.rePayfeeMapper.queryAccount(payfee);

			//查询订单信息
			Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
			//根据订单id查询合同
			Agreement agreement = new Agreement();
			agreement.setOrderId(orderAfterSales.getOrderId());
			agreement.setValid(1);
			List<Agreement> orderAgreementList = this.reAgreementMapper.queryAgreement(agreement);
			//判断是否是接口调用，handle为2位接口调用
			if (orderAfterSales.getHandle() == null) {
				if ("20130002".equals(orderAfterSales.getAuditFlag())) {
					/*确认无效*/
				 if("16".equals(orderAfterSales.getAfterSalesKind()) || "9".equals(orderAfterSales.getAfterSalesKind())){ //16卡退费 和9解决方案
					 orderAfterSales.setAuditFlag("20130002");//售后单状态:确认无效
				 }else{
					for (Payfee pay : accountList) {
						//更新退款结算单
						Payfee accounts = new Payfee();
						accounts.setId(pay.getId());
						boolean hasVph = queryVphPay(pay.getId());
						if(hasVph){
							//if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
							accounts.setAccountAmount(orderAfterSales.getVphFee());//管家帮分期退款金额
							if (orderAfterSales.getMakesureFlag() != null && orderAfterSales.getMakesureFlag() == 2) {
								accounts.setVphBackStatus("20130002");
								accounts.setVphStatus("20130002");
								accounts.setPayStatus(20130002);
								orderAfterSales.setAuditFlag("20130002");
								orderAfterSales.setVphBackStatus("20130002");//售后单状态:确认无效
								orderAfterSales.setVphCancleStatus("20130002");//售后单状态:确认无效
								orderAfterSales.setAccountPayId(20130002l);
							}else{
								accounts.setVphBackStatus("20130001");
								accounts.setVphStatus("20130001");
								accounts.setPayStatus(20130001);
								orderAfterSales.setAuditFlag("20130001");
								orderAfterSales.setVphBackStatus("20130001");//售后单状态:确认无效
								orderAfterSales.setVphCancleStatus("20130001");//售后单状态:确认无效
								orderAfterSales.setAccountPayId(20130001l);
							}
						}else {
							if (orderAfterSales.getMakesureFlag() != null && orderAfterSales.getMakesureFlag() == 2) {
								orderAfterSales.setAuditFlag("20130002");//售后单状态:确认无效
								accounts.setPayStatus(20130002);//退款状态：确认无效
							} else {
								orderAfterSales.setAuditFlag("20130001");//售后单状态:待确认
								accounts.setPayStatus(20130001);//退款状态：待确认
							}
							accounts.setAccountAmount(orderAfterSales.getRefundTotal());//银行卡退款金额
						}
						//更新退费状态
						updateQualityAccount(accounts);
						/*//修改售后手续费类型
						OrderAfterSales orderAfterSales2 = this.reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
						if(null != orderAfterSales2.getFeeAcountId()) {
							Payfee account = new Payfee();
							account.setId(orderAfterSales2.getFeeAcountId());//结算单ID
							account.setPayStatus(20130002);//退款状态：确认无效
							updateQualityAccount(account);
						}*/
					}
				 }
				}

				if ("20130012".equals(orderAfterSales.getAuditFlag())) {
					/*结算中心驳回*/
					try {
						int isBackType=1;
						if("11".equals(orderAfterSales.getAfterSalesKind())) {
							isBackType=3;
						}
						orderAfterSales.setAuditFlag("20130012");//售后单状态
						for (Payfee pay : accountList) {
							//更新退款结算单
							Payfee accounts = new Payfee();
							accounts.setId(pay.getId());
							boolean hasVph = queryInstalmentsPay(pay.getId(),pay.getFeePost() != null?pay.getFeePost():0,isBackType);
							if(hasVph){
								//if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
								accounts.setAccountAmount(orderAfterSales.getVphFee());//管家帮分期退款金额
								if (orderAfterSales.getMakesureFlag() != null && orderAfterSales.getMakesureFlag() == 2) {
									if("11".equals(orderAfterSales.getAfterSalesKind())) {
										accounts.setVphBackStatus("20130012");
										accounts.setVphStatus("20130012");
										accounts.setPayStatus(20130012);
										orderAfterSales.setVphBackStatus("20130012");//售后单状态:确认无效
										orderAfterSales.setVphCancleStatus("20130012");//售后单状态:确认无效
									}else {
										accounts.setPayStatus(20130012);
									}

								}
							}else {
								if (orderAfterSales.getMakesureFlag() != null && orderAfterSales.getMakesureFlag() == 2) {
									orderAfterSales.setAuditFlag("20130012");//售后单状态:结算中心驳回
									accounts.setPayStatus(20130012);//退款状态：结算中心驳回
								}
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());//银行卡退款金额
							}
							//更新退费状态
							updateQualityAccount(accounts);
						}
					} catch (Exception e) {
						log.error("结算中心驳回失败！");
						throw new BaseException("结算中心驳回失败!");
					}

				}

				if ("20130011".equals(orderAfterSales.getAuditFlag())) {
					/*结算中心审核中*/
					try {
						int isBackType=1;
						if("11".equals(orderAfterSales.getAfterSalesKind())) {
							isBackType=3;
						}
						orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
						//更新退费状态
						for (Payfee pay : accountList) {
							//更新退款结算单
							Payfee accounts = new Payfee();
							accounts.setId(pay.getId());//结算单ID
							accounts.setPayStatus(20130004);//退款状态：审核中
							boolean hasVph = queryInstalmentsPay(pay.getId(),pay.getFeePost() != null?pay.getFeePost():0,isBackType);
							if(hasVph){
								if("11".equals(orderAfterSales.getAfterSalesKind())) {
									accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
									accounts.setVphBackStatus("20130004");
									accounts.setVphStatus("20130004");
									orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
									orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
								}else {
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());//同步结算金额
									accounts.setPayStatus(20130004);
								}
							}else {
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
							}
							//更新退款结算单和退费
							updateQualityAccount(accounts);
						}
					} catch (Exception e) {
						log.error("结算中心确认失败！");
						throw new BaseException("结算中心确认失败!");
					}
				}

				if ("20130003".equals(orderAfterSales.getAuditFlag())) {
					/*确认有效*/
					if (accountList.size() != 0 && !accountList.isEmpty()) {
						//延续服务订单终止，或者 迁单功能
						if("8".equals(orderAfterSales.getAfterSalesKind()) || "12".equals(orderAfterSales.getAfterSalesKind())){
							orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
							//新订单余额
							BigDecimal oldTotalPay = order.getTotalPay();
							BigDecimal newTotalPay = new BigDecimal(0);
							OrderAfterSales oaSales = this.reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
							//订单余额不为空
							if (oldTotalPay != null ) {
								//重新计算订单余额
								newTotalPay = newTotalPay(oldTotalPay,oaSales);
								if (newTotalPay.compareTo(BigDecimal.ZERO) == -1) {
									newTotalPay = BigDecimal.ZERO;
								}
							} else {
								newTotalPay = BigDecimal.ZERO;
							}
							//将新订单余额存入订单
							order.setTotalPay(newTotalPay);
							order.setUpdateBy(orderAfterSales.getUpdateBy());
							try{
								//更新退款结算单
								for (Payfee pay : accountList) {
									Payfee accounts = new Payfee();
									//更新退费状态
									if("12".equals(orderAfterSales.getAfterSalesKind())){
										accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08));//退款结算单状态：退款成功
									}else {
										accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04));//退款结算单状态：审核中
									}
//									accounts.setPayStatus(20130004);//退款状态：审核中
									accounts.setId(pay.getId());//结算单ID
									//如果退费金额为0，则订单履约状态和合同状态更新为已完成，否则为已终止
									if (pay.getFeeSum() != null && pay.getFeeSum().compareTo(BigDecimal.ZERO) == 0) {
										order.setOrderStatus(9);//订单已完成
										agreement.setAgreementState(4);//合同已完成
									} else {
										order.setOrderStatus(12);//订单已终止
										agreement.setAgreementState(3);//合同已终止
									}
									boolean hasVph = queryVphPay(pay.getId());
									if(hasVph){
										accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
										if("12".equals(orderAfterSales.getAfterSalesKind())){
											accounts.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);
											accounts.setVphStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);
											orderAfterSales.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);//售后单状态:退款成功
											orderAfterSales.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);//售后单状态:退款成功
										}else {
											accounts.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);
											accounts.setVphStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);
											orderAfterSales.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
											orderAfterSales.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
										}
									}else {
										accounts.setAccountAmount(orderAfterSales.getRefundTotal());
									}
									//更新退款结算单和退费
									updateQualityAccount(accounts);
								}
								//更新订单状态
								cancleOrders(order);
								//根据售后单id，更新迁单和迁至订单备注信息
								if("12".equals(orderAfterSales.getAfterSalesKind())){
									updateOrderRemark(orderAfterSales.getId());
									//确认有效直接改成退款成功
									orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_08);
									//将售后单id放入redis，生成缴费明细
									/*try {
										redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
									} catch (RedisAccessException e) {
										e.printStackTrace();
									}*/
								}
								//更改合同状态
								if (orderAgreementList.size() != 0 && !orderAgreementList.isEmpty()) {
									for (Agreement agr : orderAgreementList) {
										//合同为新增或者已确认
										if (agr.getAgreementState() == 1 || agr.getAgreementState() == 2 ) {
											agr.setAgreementState(agreement.getAgreementState());
											agr.setUpdateBy(orderAfterSales.getUpdateBy());
											//更新合同
											cancleAgreement(agr);
										}
									}
								}
							}catch(Exception e){

							}
							//服务人员下户并释放排期
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
							//解决方案退费
						}
/*						else if("9".equals(orderAfterSales.getAfterSalesKind())){
							orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
							if (order.getOrderStatus() != 9) {//订单状态不为已完成
								order.setOrderStatus(12);//订单已终止
								order.setUpdateBy(orderAfterSales.getUpdateBy());
								cancleOrder = cancleOrders(order);//更新订单状态
								if (cancleOrder) {//更新订单成功
									if (accountList.size() != 0 && !accountList.isEmpty()) {
										//更新退款结算单
										Payfee accounts = new Payfee();
										accounts.setId(accountList.get(0).getId());
										accounts.setPayStatus(20130004);//退款状态：审核中
										//更新退费状态
										updateAccount = updateQualityAccount(accounts);
									}
								}
								if (updateAccount) {
									Solution solution = new Solution();
									solution.setId(order.getId());
									List<Solution> agreementList = this.reSolutionMapper.querySolutionAgreement(solution);
									if (agreementList.size() != 0 && !agreementList.isEmpty()) {
										solution.setAgreementId(agreementList.get(0).getAgreementId());
										solution.setAgreementStatus(3);//合同状态：已终止
										solution.setUpdateBy(orderAfterSales.getUpdateBy());
										this.wrSolutionMapper.updateSolution(solution);//更新合同
										cancleAgreement = true;
									} else {
										cancleAgreement = true;
									}
									//取消解决方案订单
									try {
										String solutionJson = "{\"id\":"+order.getId()+",\"updateBy\":"+orderAfterSales.getUpdateBy()+",\"solutionStatus\":6,\"payStatus\":\"\"}";
										String updateSolution = this.activitiesInterfaceService.updateCustSolutionStatus(solutionJson);
										JSONObject jsonObj = JSONObject.fromObject(updateSolution);
										System.out.println("是否取消解决方案订单状态:"+jsonObj.get("code"));
									} catch (Exception e) {
										System.out.println("取消解决方案订单失败！");
										log.error("取消解决方案订单失败！");
										throw new BaseException("取消解决方案订单失败！");
									}
									
								}
							}
							//延续性订单取消
						}*/
						else if("5".equals(orderAfterSales.getAfterSalesKind())){
                            /**
                             * 单次服务订单取消，延续性服务订单取消，解决方案订单取消 审核后，售后单、结算单状态置为回访中 add 20181228 zhanghao
                             */
							//延续订单取消类型或终止类型
							OrderAfterSales oaSales = this.reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
							BigDecimal refundSalaryFee =  oaSales.getRefundSalaryFee();//修改后应退服务人员服务费
							if(refundSalaryFee != null && refundSalaryFee.compareTo(new BigDecimal(0)) > 0){
								BigDecimal oldTotalPay = order.getTotalPay();
								if(oldTotalPay != null){
									order.setTotalPay(oldTotalPay.subtract(refundSalaryFee));
									LogHelper.info(getClass()+".updateOrderAfterSales()", orderAfterSales.getOrderId()+"售后修改订单余额,差额"+refundSalaryFee);
								}
							}
//							orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
							orderAfterSales.setAuditFlag("20130014");//售后单状态:审核中
							order.setOrderStatus(10);//订单已取消
							//order.setTotalPay(BigDecimal.ZERO);//设置订单余额为零
							cancleOrders(order);
							// 释放服务人员排期
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
							//已签约的订单状态，改变合同状态为已终止
							if (order.getOrderStatus() == 7) {
								updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getCreateBy());
							}
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
//								accounts.setPayStatus(20130004);//退款状态：审核中
                                accounts.setPayStatus(20130014);//退款状态：回访中
								accounts.setId(pay.getId());//结算单ID
								//判断管家帮分期字段是否为空，不为空则同步状态
								boolean hasVph = queryVphPay(pay.getId());
								if(hasVph){
//									accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
									accounts.setVphBackStatus("20130004");
									accounts.setVphStatus("20130004");
									orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
									orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
								}else {
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								}
								//更新退款结算单和退费
								updateQualityAccount(accounts);
							}

							//单次服务订单，如果有排期则释放排期
						}else if("4".equals(orderAfterSales.getAfterSalesKind())){
							/**
							 * 单次服务订单取消，延续性服务订单取消，解决方案订单取消 审核后，售后单、结算单状态置为回访中 add 20181228 zhanghao
							 */
//							orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
							orderAfterSales.setAuditFlag("20130014");//售后单状态:回访中
							// 释放服务人员排期
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
							//增加库存
							//addInventory(order);
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
//								accounts.setPayStatus(20130004);//退款状态：审核中
								accounts.setPayStatus(20130014);//退款状态：回访中
								accounts.setId(pay.getId());//结算单ID
								//判断管家帮分期字段是否为空，不为空则同步状态
								boolean hasVph = queryVphPay(pay.getId());
								if(hasVph){
									accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
									accounts.setVphBackStatus("20130004");
									accounts.setVphStatus("20130004");
									orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
									orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
								}else {
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								}
								//更新退款结算单和退费
								updateQualityAccount(accounts);
							}
						}else if ("11".equals(orderAfterSales.getAfterSalesKind()) || "18".equals(orderAfterSales.getAfterSalesKind()) || "17".equals(orderAfterSales.getAfterSalesKind())) {
							int isBackType=1;
							orderAfterSales.setAuditFlag("20130011");//售后单状态:结算中心审核中
							if("11".equals(orderAfterSales.getAfterSalesKind())) {
								isBackType=3;
							}
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								accounts.setPayStatus(20130011);//退款状态：审核中
								//判断管家帮分期字段是否为空，不为空则同步状态
								//boolean hasVph = queryVphPay(pay.getId());
								boolean hasVph = queryInstalmentsPay(pay.getId(),pay.getFeePost() != null?pay.getFeePost():0,isBackType);
								if(hasVph){
									if("11".equals(orderAfterSales.getAfterSalesKind())) {

										accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
										accounts.setVphBackStatus("20130011");
										accounts.setVphStatus("20130011");
										orderAfterSales.setVphBackStatus("20130011");//售后单状态:结算中心审核中
										orderAfterSales.setVphCancleStatus("20130011");//售后单状态:结算中审核中
									}else {
										accounts.setAccountAmount(orderAfterSales.getRefundTotal());
									}
								}else {
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								}
								//更新退款结算单和退费
								updateQualityAccount(accounts);
							}
						}else if ("13".equals(orderAfterSales.getAfterSalesKind()) || "14".equals(orderAfterSales.getAfterSalesKind()) || "15".equals(orderAfterSales.getAfterSalesKind())) {
							orderAfterSales.setAuditFlag("20130011");//售后单状态:结算中心审核中
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								accounts.setPayStatus(20130011);//退款状态：审核中
								//判断管家帮分期字段是否为空，不为空则同步状态
								//boolean hasVph = queryVphPay(pay.getId());
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								//更新退款结算单和退费
								updateQualityAccount(accounts);
							}
						}else if("9".equals(orderAfterSales.getAfterSalesKind())){ //9解决方案
							Payfee payfee2 =accountList.get(0);
							if(payfee2.getFeeSum().compareTo(BigDecimal.ZERO)!=0){
                                /**
                                 * 单次服务订单取消，延续性服务订单取消，解决方案订单取消 审核后，售后单、结算单状态置为回访中 add 20181228 zhanghao(deleted 20190108 zhanghao 由于解决方案售后无法修改，暂时取消)
                                 */
								orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
//								orderAfterSales.setAuditFlag("20130014");//售后单状态:回访中
								for (Payfee pay : accountList) {
									//更新退款结算单
									Payfee accounts = new Payfee();
									accounts.setId(pay.getId());//结算单ID
									accounts.setPayStatus(20130004);//退款状态：审核中
//                                    accounts.setPayStatus(20130014);//退款状态：回访中
									//判断管家帮分期字段是否为空，不为空则同步状态
									boolean hasVph = queryVphPay(pay.getId());
									if(hasVph){
										accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
										accounts.setVphBackStatus("20130004");
										accounts.setVphStatus("20130004");
										orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
										orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
									}else {
										accounts.setAccountAmount(orderAfterSales.getRefundTotal());
									}
									//更新退款结算单和退费
									updateQualityAccount(accounts);
								}
							}else{
								orderAfterSales.setAuditFlag("20260001");//售后单状态:已取消
								for (Payfee pay : accountList) {
									//更新退款结算单
									Payfee accounts = new Payfee();
									accounts.setId(pay.getId());//结算单ID
									accounts.setPayStatus(20260001);//退款状态：已取消
									//判断管家帮分期字段是否为空，不为空则同步状态
									boolean hasVph = queryVphPay(pay.getId());
									if(hasVph){
										accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
										accounts.setVphBackStatus("20130004");
										accounts.setVphStatus("20130004");
										orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
										orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
									}else {
										accounts.setAccountAmount(orderAfterSales.getRefundTotal());
									}
									//更新退款结算单和退费
									updateQualityAccount(accounts);
								}
							}


						}else{
							orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								accounts.setPayStatus(20130004);//退款状态：审核中
								//判断管家帮分期字段是否为空，不为空则同步状态
								boolean hasVph = queryVphPay(pay.getId());
								if(hasVph){
									accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
									accounts.setVphBackStatus("20130004");
									accounts.setVphStatus("20130004");
									orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
									orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
								}else {
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								}
								//更新退款结算单和退费
								updateQualityAccount(accounts);
							}
						}
			/*			//修改售后手续费类型
						OrderAfterSales orderAfterSales2 = this.reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
						if(null != orderAfterSales2.getFeeAcountId()) {
							Payfee account = new Payfee();
							account.setId(orderAfterSales2.getFeeAcountId());//结算单ID
							account.setPayStatus(20130004);//退款状态：审核中
							updateQualityAccount(account);
						}*/
						//将结算单id放入redis，更新缴费明细
						try {
							if(!"16".equals(orderAfterSales.getAfterSalesKind()) && !"9".equals(orderAfterSales.getAfterSalesKind())){
								redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
							}
						} catch (RedisAccessException e) {
							e.printStackTrace();
						}
					}
				}

				if("20130005".equals(orderAfterSales.getAuditFlag())) {
					/*审核失败*/
					if (accountList.size() != 0 && !accountList.isEmpty()) {
						if("5".equals(orderAfterSales.getAfterSalesKind()) || "8".equals(orderAfterSales.getAfterSalesKind())){
							//延续订单取消类型或终止类型
							BigDecimal oldRefundSalaryFee =  orderAfterSales.getOldRefundSalaryFee();//原应退服务人员服务费
							BigDecimal refundSalaryFee =  orderAfterSales.getRefundSalaryFee();//修改后应退服务人员服务费
							if(oldRefundSalaryFee != null && refundSalaryFee != null && oldRefundSalaryFee.compareTo(new BigDecimal(0)) >= 0 && oldRefundSalaryFee.compareTo(new BigDecimal(0)) >= 0){
								if(oldRefundSalaryFee.compareTo(refundSalaryFee) != 0){
									BigDecimal difference = oldRefundSalaryFee.subtract(refundSalaryFee);//计算差额,原应退服务人员服务费-修改后应退服务人员服务费
									this.wrOrderAfterSalesMapper.updateOrderTotalPay(orderAfterSales.getOrderId(),orderAfterSales.getUpdateBy(),difference);
									LogHelper.info(getClass()+".updateOrderAfterSales()", "审核失败修改订单余额,差额"+difference);
								}
							}
						}
						orderAfterSales.setAuditFlag("20130004");//售后单状态:审核中
						//更新退费状态
						for (Payfee pay : accountList) {
							//更新退款结算单
							Payfee accounts = new Payfee();
							accounts.setId(pay.getId());//结算单ID
							accounts.setPayStatus(20130004);//退款状态：审核中
							//判断管家帮分期字段是否为空，不为空则同步状态
							boolean hasVph = queryVphPay(pay.getId());
							if(hasVph){
								accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
								accounts.setVphBackStatus("20130004");
								accounts.setVphStatus("20130004");
								orderAfterSales.setVphBackStatus("20130004");//售后单状态:审核中
								orderAfterSales.setVphCancleStatus("20130004");//售后单状态:审核中
							}else {
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
							}
							//更新退款结算单和退费
							updateQualityAccount(accounts);
						}
						//将结算单id放入redis，更新缴费明细
						try {
							if(!"16".equals(orderAfterSales.getAfterSalesKind()) && !"9".equals(orderAfterSales.getAfterSalesKind())){
								redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId());
							}
						} catch (RedisAccessException e) {
							e.printStackTrace();
						}
					}
				}
				LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单auditFlag:" + orderAfterSales.getAuditFlag());

				if ("20130009".equals(orderAfterSales.getAuditFlag()) || (!"".equals(orderAfterSales.getVphBackStatus()) && "20130009".equals(orderAfterSales.getVphBackStatus()))){
					/*退款失败*/
					if (accountList.size() != 0 && !accountList.isEmpty()) {
						for (Payfee pay : accountList) {
							//更新退款结算单
							Payfee accounts = new Payfee();
							accounts.setId(pay.getId());//结算单ID
							if(!"".equals(orderAfterSales.getAuditFlag()) && "20130009".equals(orderAfterSales.getAuditFlag())){
								accounts.setPayStatus(20130006);//退款状态：审核成功
							}
							//判断管家帮分期字段是否为空，不为空则同步状态
							boolean hasVph = queryVphPay(pay.getId());
							if(hasVph){
								//if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
								accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额

								if (!"".equals(orderAfterSales.getAuditFlag()) && !"20130009".equals(orderAfterSales.getAuditFlag())
										&& !"".equals(orderAfterSales.getVphBackStatus()) && "20130009".equals(orderAfterSales.getVphBackStatus())) {
									accounts.setVphBackStatus("20130006");//结算单管家帮分期退款状态:审核成功
									accounts.setVphStatus("20130006");//结算单管家帮分期取消状态:审核成功
									orderAfterSales.setVphBackStatus("20130006");//售后单管家帮分期退款状态:审核成功
									orderAfterSales.setVphCancleStatus("20130006");//售后单管家帮分期取消状态:审核成功
								}
							}else {
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
							}
							updateQualityAccount(accounts);
						}
					}
					if ("20130009".equals(orderAfterSales.getAuditFlag())){
						orderAfterSales.setAuditFlag("20130006");//售后单状态:审核成功
					}else {
						orderAfterSales.setAuditFlag(null);//不更新售后单状态
					}
				}

				if ("20130010".equals(orderAfterSales.getAuditFlag())) {
					/*已删除*/
					if (accountList.size() != 0 && !accountList.isEmpty()) {
						for (Payfee pay : accountList) {
							//更新退款结算单
							Payfee accounts = new Payfee();
							accounts.setPayStatus(20130010);//退款状态：已删除
							accounts.setId(pay.getId());//结算单ID
							//判断管家帮分期字段是否为空，不为空则同步状态
							boolean hasVph = queryVphPay(pay.getId());
							if(hasVph){
								//if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
								accounts.setVphBackStatus("20130010");
								accounts.setVphStatus("20130010");
								orderAfterSales.setVphBackStatus("20130010");//售后单状态:已删除
								orderAfterSales.setVphCancleStatus("20130010");//售后单状态:已删除
							}else {
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
							}
							accounts.setValid(2);//置为无效
							updateQualityAccount(accounts);
						}
						orderAfterSales.setValid(2);//售后单设置为不可用
					}
				}

                /**
                 * 回访失败修改售后，状态变为回访中，add 20181229 zhanghao
                 */
                if("20130015".equals(orderAfterSales.getAuditFlag())) {
                    if (accountList.size() != 0 && !accountList.isEmpty()) {
                    	if("5".equals(orderAfterSales.getAfterSalesKind()) || "8".equals(orderAfterSales.getAfterSalesKind())){
							//延续订单取消类型或终止类型
							BigDecimal oldRefundSalaryFee =  orderAfterSales.getOldRefundSalaryFee();//原应退服务人员服务费
							BigDecimal refundSalaryFee =  orderAfterSales.getRefundSalaryFee();//修改后应退服务人员服务费
							if(oldRefundSalaryFee != null && refundSalaryFee != null && oldRefundSalaryFee.compareTo(new BigDecimal(0)) >= 0 && oldRefundSalaryFee.compareTo(new BigDecimal(0)) >= 0){
								if(oldRefundSalaryFee.compareTo(refundSalaryFee) != 0){
									BigDecimal difference = oldRefundSalaryFee.subtract(refundSalaryFee);//计算差额,原应退服务人员服务费-修改后应退服务人员服务费
									this.wrOrderAfterSalesMapper.updateOrderTotalPay(orderAfterSales.getOrderId(),orderAfterSales.getUpdateBy(),difference);
									LogHelper.info(getClass()+".updateOrderAfterSales()", "回访失败修改订单余额,差额"+difference);
								}
							}
						}
                        orderAfterSales.setAuditFlag("20130014");//售后单状态:回访中
                        //更新退费状态
                        for (Payfee pay : accountList) {
                            //更新退款结算单
                            Payfee accounts = new Payfee();
                            accounts.setId(pay.getId());//结算单ID
                            accounts.setPayStatus(20130014);//退款状态：回访中
                            //判断管家帮分期字段是否为空，不为空则同步状态
                            boolean hasVph = queryVphPay(pay.getId());
                            if(hasVph){
                                accounts.setAccountAmount(orderAfterSales.getVphFee());//同步结算金额
                                accounts.setVphBackStatus("20130014");
                                accounts.setVphStatus("20130014");
                                orderAfterSales.setVphBackStatus("20130014");//售后单状态:审核中
                                orderAfterSales.setVphCancleStatus("20130014");//售后单状态:审核中
                            }else {
                                accounts.setAccountAmount(orderAfterSales.getRefundTotal());
                            }
                            //更新退款结算单和退费
                            updateQualityAccount(accounts);
                        }
                        try {
							if(!"16".equals(orderAfterSales.getAfterSalesKind()) && !"9".equals(orderAfterSales.getAfterSalesKind())){
								redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
							}
						} catch (RedisAccessException e) {
							e.printStackTrace();
						}
                    }
                }
				try {
					/*更新银行信息表的银行卡内容*/
					if (!"".equals(orderAfterSales.getBankCard()) && !"20130010".equals(orderAfterSales.getAuditFlag()) && !"7".equals(orderAfterSales.getAfterSalesKind()) && accountList.size() != 0) {
						updateBankCard(orderAfterSales, accountList.get(0));
					}
				} catch (Exception e) {
				}
			} else {
				/*调用接口修改售后及结算单状态*/
				AfterSalesNew afterSalesNew  =this.reAfterSalesMapper.queryFqafterSales(orderAfterSales.getId());
				/*for (Payfee pay : accountList) {
					//判断管家帮分期字段是否为空，不为空则同步状态
					if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
						if(pay.getIsBackType()==3) {
							orderAfterSales.setVphBackStatus(pay.getPayStatus().toString());
							orderAfterSales.setVphCancleStatus(pay.getPayStatus().toString());
						}else {
							orderAfterSales.setVphBackStatus("");
							orderAfterSales.setVphCancleStatus("");
						}
					}
				}*/
				if(afterSalesNew!=null && afterSalesNew.getAfterSalesKind().equals("11")) {
					orderAfterSales.setVphBackStatus(orderAfterSales.getAuditFlag());
					orderAfterSales.setVphCancleStatus(orderAfterSales.getAuditFlag());
				}else {
					orderAfterSales.setVphBackStatus("");
					orderAfterSales.setVphCancleStatus("");
				}
				/*for (Payfee pay : accountList) {
					//判断管家帮分期字段是否为空，不为空则同步状态
					if (pay.getVphBackStatus() != null && !"".equals(pay.getVphBackStatus()) && pay.getVphStatus() != null && !"".equals(pay.getVphStatus())) {
						if(pay.getIsBackType()==3) {
							orderAfterSales.setVphBackStatus(pay.getPayStatus().toString());
							orderAfterSales.setVphCancleStatus(pay.getPayStatus().toString());
						}else {
							orderAfterSales.setVphBackStatus("");
							orderAfterSales.setVphCancleStatus("");
						}
					}
				}*/
				//退款成功
				if (orderAfterSales.getAuditFlag() != null && "20130008".equals(orderAfterSales.getAuditFlag())) {
					LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单客户名称:" + order.getUserName());
					LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单订单编号:" + order.getOrderCode());
					//发送提示短信
					sendAfterSaleSms(order,orderAfterSales,"order_30",2);
				}
			}
			//查询旧售后单信息,如果是移动端添加更改订单状态
			OrderAfterSales oldOrderAfterSales = reAfterSalesMapper.findAfterSales(orderAfterSales.getId());
			if(oldOrderAfterSales.getIsApp() !=null && oldOrderAfterSales.getIsApp() == 1 && oldOrderAfterSales.getAuditFlag() == "20130013"){
				orderAfterSales.setAuditFlag("20130001");
			}
			//更新售后单信息
			returnValue = this.wrOrderAfterSalesMapper.updateOrderAfterSales(orderAfterSales);
			if (1 != returnValue) {
				throw new BaseException("更新售后单失败!");
			}
		} catch (Exception e) {
			LogHelper.error(getClass(),".updateOrderAfterSales():"+e.getMessage(),e);
		}
	}
	//根据售后单id，更新迁单和迁至订单备注信息
	private void updateOrderRemark(Long id) {
		try {
			OrderAfterSales oas = this.reOrderAfterSalesMapper.loadAfterSales(id);
			if (oas.getOrderId() != null ) {
				Order o1 = new Order();
				o1.setId(oas.getOrderId());
				o1.setRemark(" 该订单已迁单至订单号:"+oas.getChangeCode()+"。");
				o1.setUpdateBy(oas.getUpdateBy());
				int i = wrOrderAfterSalesMapper.updateOrderRemark(o1);
				if (i != 1) {
					throw new Exception("更新订单失败");
				}
			}
			if (oas.getMoveToOrderId() != null ) {
				Order o2 = new Order();
				o2.setId(oas.getMoveToOrderId());
				o2.setRemark(" 该订单由订单号:"+oas.getOrderCode()+" 迁单。");
				o2.setUpdateBy(oas.getUpdateBy());
				int j = wrOrderAfterSalesMapper.updateOrderRemark(o2);
				if (j != 1) {
					throw new Exception("更新订单失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新银行信息表的银行卡内容
	 * @param orderAfterSales
	 * @param account  结算单
	 */
	private void updateBankCard(OrderAfterSales orderAfterSales, Payfee account) {
		AccountPayBankcard accountPayBankcard = new AccountPayBankcard();
		accountPayBankcard.setBankCard(orderAfterSales.getBankCard());
		accountPayBankcard.setBankUserName(orderAfterSales.getBankUserName());
		accountPayBankcard.setBankMainName(orderAfterSales.getBankMainName());
		accountPayBankcard.setBankName(orderAfterSales.getBankName());
		accountPayBankcard.setBankSupportId(orderAfterSales.getBankSupportId());
		accountPayBankcard.setAccountPayId(account.getId());
		accountPayBankcard.setCity(orderAfterSales.getBankCityCode());
		accountPayBankcard.setUpdateBy(orderAfterSales.getUpdateBy());
		this.wrAccountPayBankcardMapper.updateAccountPayBankcard(accountPayBankcard);
	}

	/**
	 * 计算新订单余额
	 * @param oldTotalPay 原订单金额
	 * @param oaSales	  售后单
	 * @return
	 */
	private BigDecimal newTotalPay(BigDecimal oldTotalPay, OrderAfterSales oaSales) {
		BigDecimal totalPay = new BigDecimal(0);
		if (oaSales.getRefundTotal() != null && oaSales.getVphFee() != null) {
			//订单余额=原订单余额-应退服务人员服务费-分期退费
			totalPay = oldTotalPay.subtract(oaSales.getRefundSalaryFee()).subtract(oaSales.getVphFee()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if (oaSales.getRefundTotal() != null && oaSales.getVphFee() == null) {
			//订单余额=原订单余额-应退服务人员服务费
			totalPay = oldTotalPay.subtract(oaSales.getRefundSalaryFee()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else if (oaSales.getRefundTotal() == null && oaSales.getVphFee() != null){
			//订单余额=原订单余额-分期退费
			totalPay = oldTotalPay.subtract(oaSales.getVphFee()).setScale(2, BigDecimal.ROUND_HALF_UP);
		}else {
			//订单余额=原订单余额
			totalPay = oldTotalPay;
		}

		return totalPay;
	}

	/**
	 * 查询合同，并更新合同状态
	 * @param orderId 订单ID
	 * @param state 合同状态
	 * @param updateBy 更新人
	 */
	private void updateAgreement(Long orderId, int state,Long updateBy) {
		//查询合同
		try {
			//根据订单id查询合同
			Agreement agreement = new Agreement();
			agreement.setOrderId(orderId);
			agreement.setValid(1);
			List<Agreement> agreementList = this.reAgreementMapper.queryAgreement(agreement);
			if (agreementList.size() != 0 && !agreementList.isEmpty()) {
				for (Agreement agr : agreementList) {
					//合同为新增或者已确认
					if (agr.getAgreementState() == 1 || agr.getAgreementState() == 2 ) {
						agr.setAgreementState(state);//合同状态
						agr.setUpdateBy(updateBy);
						//更新合同
						cancleAgreement(agr);
					}
				}
			}
		} catch (Exception e) {
			LogHelper.error(getClass(),".updateAgreement():"+e.getMessage(),e);
		}
	}

	//取消订单操作
	public Boolean cancleOrders(Order order){
		Boolean result = false;
		try {
			order.setUpdateTime(DateUtil.getCurrDateTime());
			result = orderService.updateOrder2(order);
		} catch (Exception e) {
			LogHelper.error(getClass(),".cancleOrders():"+e.getMessage(),e);
		}
		return result;
	}
	//取消合同操作
	public Boolean cancleAgreement(Agreement agreement){
		int agreementMsg = 0;//1：成功 0：失败
		Boolean result = false;
		try {
			agreement.setUpdateTime(DateUtil.getCurrDateTime());
			agreementMsg = this.wrAgreementMapper.updateAgreement(agreement);
			if (agreementMsg != 1) {
				result = false;
			}else {
				result = true;
			}
		} catch (Exception e) {
			LogHelper.error(getClass(),".cancleAgreement():"+e.getMessage(),e);
		}
		return result;
	}
		//更新结算单状态操作
		public Boolean updateQualityAccount(Payfee payfee){
			int accountMsg = 0;//1：成功 0：失败
			Boolean result = false;
			try {
				payfee.setUpdateTime(DateUtil.getCurrDateTime());
				accountMsg = this.wrPayfeeMapper.updateAccount(payfee);
				Payfee payfee2 = new Payfee();
				payfee2.setOrderId(payfee.getId());
				payfee2.setValid(1);
				List<Payfee> pList = this.rePayfeeMapper.queryPayfee(payfee2);
				if (pList.size() != 0  && !pList.isEmpty()) {
					for (Payfee payfee3 : pList) {
						payfee3.setFeeSum(payfee.getAccountAmount());
						payfee3.setUpdateBy(payfee.getUpdateBy());
						payfee3.setValid(payfee.getValid());
						payfee3.setPayStatus(20110002);
						this.wrPayfeeMapper.updatePayfee(payfee3);
					}
				}
				if (accountMsg != 1) {
					result = false;
				}else {
					result = true;
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".updateQualityAccount():"+e.getMessage(),e);
			}
			return result;
		}

		//更新结算单状态操作
		public Boolean updateSaleAccount(Payfee payfee){
			int accountMsg = 0;//1：成功 0：失败
			Boolean result = false;
			try {
				payfee.setUpdateTime(DateUtil.getCurrDateTime());
				accountMsg = this.wrPayfeeMapper.updateAccount(payfee);
				if (accountMsg != 1) {
					result = false;
				}else {
					result = true;
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".updateQualityAccount():"+e.getMessage(),e);
			}
			return result;
		}

		@Override
		public List<OrderAfterSales> queryAfterSalesApprove(OrderAfterSales orderAfterSales, Page page) {
			//return this.reOrderAfterSalesMapper.queryAfterSalesApprovelistPage(orderAfterSales);
			return this.reOrderAfterSalesMapper.queryAfterSalesApproveNewlistPage(orderAfterSales);
		}

		public void updateEmpSchedule(Long orderId,Long createBy){
			//释放排期标记
			Integer flag = 1;
			try {
				// 锁定服务人员排期
				ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(orderId);
				if (itd != null) {//面试成功、已签约状态，释放服务人员的排期
					ItemInterview  itemInterview = new ItemInterview();
					itemInterview.setOrderId(orderId);
					itemInterview.setValid(1);
					List<ItemInterview> itemList = this.itemInterviewService.queryNeedPersons(itemInterview);
					if (itemList.size() != 0 && !itemList.isEmpty()) {
						for (ItemInterview interview : itemList) {//服务人员已上户、顶岗上户、调岗下户
							if (interview.getInterviewType() == 8 || interview.getInterviewType() == 12 || interview.getInterviewType() == 13) {
								flag = 1;
								if (interview.getInterviewType() == 8 || interview.getInterviewType() == 12) {
									interview.setInterviewType(9);//已下户
								}
								itd.setEmp_id(interview.getPersonId());//服务人员ID
								List<ItemDetailServer> scheduleList = this.reItemDetailServerMapper.queryEmpSchedule(itd);
								if (scheduleList.size() != 0 && !scheduleList.isEmpty()) {
									for (ItemDetailServer itemDetailServer : scheduleList) {
										Integer sysDate =Integer.valueOf(DateUtil.getCurrDate().replace("-", "")) ;//系统时间
										Integer endDate = Integer.valueOf(itemDetailServer.getEndDate().replace("-", ""));//排期结束时间
										//人员排期结束时间大于系统时间，释放系统时间到排期结束时间的服务人员排期
										//人员排期结束时间小于等于系统时间,不做操作
										if ( endDate > sysDate) {
											String startDate =com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(DateUtil.getCurrDate()), 1));
											itd.setStartTime(startDate);
											itd.setEndTime(itemDetailServer.getEndDate());
										}
									}
									interview.setEndTime(DateUtil.getCurrDate());//下户时间默认为确认有效时间
								}
							} else {
								//更新服务人员状态
								if (interview.getInterviewType() == 9) {//服务人员为下户状态
									flag = 2;
								}else {
									interview.setInterviewType(10);//已取消
									flag = 3;//正常情况释放排期
									interview.setEndTime(DateUtil.getCurrDate());//下户时间默认为确认有效时间
								}
							}
							interview.setUpdateBy(createBy);
							//释放排期
							try{
								this.wrItemInterviewMapper.updateItemInterview(interview);
								if (itd != null && flag == 1) {
									String json = "{\"startDate\":" +Integer.valueOf(itd.getStartTime().replace("-", "").substring(0, 8))
									+",\"endDate\":" +Integer.valueOf(itd.getEndTime().replace("-", "").substring(0, 8))
									+",\"orderId\":" +itd.getOrderId()
									+",\"status\":1,\"empId\":\"" +interview.getPersonId() +"\"}";
									LogHelper.info(getClass()+ ".lindDays()","调用接口json串："+json);
									//调用服务人员排期修改接口方法
									EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
									String result = service.updateSchedule("[" +json +"]");
//									boolean result = this.personnelInterfaceService.updateSchedule("[" +json +"]");
									LogHelper.info(getClass()+ ".lindDays()","排期是否更新成功："+result);
								}else if (itd != null && flag == 3){
									this.itemInterviewServiceImpl.lindDays(itd, interview.getPersonId(), 1);
								}
							} catch (Exception e) {
								continue;
							}
						}
					}
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".updateEmpSchedule():"+e.getMessage(),e);
			}
		}
		//新增管家帮分期结算单和缴费
		private Long insertVipShopAccountFee(Long orderId, int accountPayStatus, BigDecimal vipshopFee, Long createBy, Payfee vphFee) {
			Payfee account = new Payfee();
			try {
				if (vipshopFee.compareTo(BigDecimal.ZERO) == 1) {
					LogHelper.info(getClass()+".insertVipShopAccountFee()","参数接收orderId："+orderId+" accountPayStatus:"+accountPayStatus+" vipshopFee:"+vipshopFee);
					account.setOrderId(orderId);//订单id
					account.setAccountAmount(vipshopFee);
					account.setPayStatus(accountPayStatus); //退款结算单支付状态
					account.setVphStatus(String.valueOf(accountPayStatus));
					account.setVphBackStatus(String.valueOf(accountPayStatus));
					account.setPayType("3");//默认预收
					account.setPayKind(2);   //结算单关联订单类型
					account.setCreateBy(createBy);
					account.setIsBackType(1);//是退款结算单
					account.setBussStatus(1);//业务未处理状态
					account.setIsManual(1);//后台录入
					this.wrPayfeeMapper.insertAccount(account);//生成新结算单信息

					//新增管家帮分期余额缴费单
					Payfee fee = new Payfee();
					fee.setOrderId(account.getId());//新增的退款结算单id
					fee.setFeeSum(vipshopFee);//退费金额
					fee.setFeePost(20250027); //管家帮分期余额
					fee.setCreateTime(DateUtil.getCurrDateTime());
					fee.setBankFlowNum(vphFee.getBankFlowNum());//流水号
					fee.setFeeToDate(vphFee.getFeeToDate());//缴费对应日期
					fee.setIsManualFee(vphFee.getIsManualFee());//是否手动创建
					//fee.setIsHandle(vphFee.getIsHandle());//是否手动处理
					fee.setCreateBy(createBy);
					fee.setIsBackType(2);    //退款缴费
					fee.setFeeType(1);       //新增缴费
					fee.setAccountStatus(2); //未对账
					fee.setValid(1);
					fee.setVersion((long)1);
					this.wrPayfeeMapper.insertPayfee(fee);//生成新缴费单信息
				}
			} catch (BaseException e) {
				LogHelper.error(getClass(),".insertVipShopAccountFee():"+e.getMessage(),e);
			}
			//返回结算单ID
			return account.getId();
		}

		//增加库存调用方法
		private void addInventory(Order order) {
			try {
				String twoCateCode = order.getOrderType().toString().substring(0,8);
				String endTime = "";
				//单次服务，保洁类
				if(order.getCateType() == 1 && twoCateCode.equals(CommonConstant.ORDER_TWO_CATEGORYCODE)){
					ItemDetailServer itd = this.reItemDetailServerMapper.loadItemDetailServerByOrderId(order.getId());
					String cityCode = order.getCity();//获取城市编码
					String threeCateCode = order.getOrderType(); //获取三级分类编码
					String selectionTime = itd.getStartTime().substring(0, 10);//取月日年
					String startTime = itd.getStartTime().substring(11, 16);//时间段开始时间
					if (itd.getEndTime() != null) {
						 endTime = itd.getEndTime().substring(11, 16);//时间段结束时间
					}
					Integer num = itd.getPersonNumber();
					Long orderId = order.getId();
					LogHelper.info(getClass()+ ".addInventory()","获取服务日期的参数selectionTime:"+selectionTime+",startTime:"+startTime+",endTime:"+endTime+",num:"+num);
					//减库存调用方法
					String back = orderService.increaseInventory(cityCode, threeCateCode, selectionTime, startTime, endTime,num,orderId);
					LogHelper.info(getClass()+".addInventory()", "增加库存结果："+back);
				}
			} catch (BaseException e) {
				LogHelper.error(getClass(),".addInventory():"+e.getMessage(),e);
			}
		}

		private Boolean queryVphPay(Long accountId){
			boolean result = false;
			try {
				Long  payId  = rePayfeeMapper.queryVphPay(accountId);
				if ( payId != null) {
					result = true;
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".queryVphPay():"+e.getMessage(),e);
			}
			return result ;
		}

		@Override
		public List<OrderAfterSales> queryVPHSales(OrderAfterSales orderAfterSales, Page page) {
			return this.reOrderAfterSalesMapper.queryVPHSaleslistPage(orderAfterSales);
		}

		@Override
		public List<Order> queryVPHOrder(Order order, Page page) {
			return this.reOrderAfterSalesMapper.queryVPHOrderlistPage(order);
		}
		/**
		 * 新增唯品会分期退款售后单
		 */
		@Override
		public void insertVphAfterSales(OrderAfterSales orderAfterSales){
			try {
				Long vphAccountId = 0l;
				//添加退款结算单
				Payfee accounts = new Payfee();
				accounts.setId(null);
				accounts.setOrderId(orderAfterSales.getOrderId());//订单id
				accounts.setAccountAmount(orderAfterSales.getVphFee());
				accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04)); //退款结算单支付状态:审核中
				accounts.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//唯品会退款结算单支付状态:审核中
				accounts.setVphStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//唯品会退款结算单取消状态:审核中
				accounts.setPayType("3");//结算类型默认预收
				accounts.setPayKind(2);     //结算单关联订单类型
				accounts.setCreateBy(orderAfterSales.getCreateBy());
				accounts.setIsBackType(1);//是退款结算单
				accounts.setBussStatus(1);//业务未处理状态
				accounts.setIsManual(1);//后台录入
				accounts.setRefundObject(orderAfterSales.getRefundObject());//退费对象
				this.wrPayfeeMapper.insertAccount(accounts);//生成新结算单信息
				vphAccountId = accounts.getId();
				//根据订单ID查询最近的唯品会分期订单ID号
				String bankFlowNum = "";
				bankFlowNum = this.rePayfeeMapper.queryVphBankFlowNum(orderAfterSales.getOrderId());
				//添加退费
				/*Payfee fee = new Payfee();
				fee.setOrderId(accounts.getId());//新增的退款结算单id
				fee.setFeePost(20250027); //唯品会分期退款
				fee.setFeeSum(orderAfterSales.getVphFee());//退费金额
				fee.setIsBackType(2);     //退款缴费
				fee.setFeeType(1);        //新增缴费
				fee.setAccountStatus(2);  //未对账
				fee.setCreateBy(orderAfterSales.getCreateBy());
				fee.setIsManual(2);//自动创建
				fee.setBankFlowNum(bankFlowNum);//唯品会订单ID
				this.wrPayfeeMapper.insertPayfee(fee);//生成新缴费单信息
*/				//添加售后单
				OrderAfterSales oas = new OrderAfterSales();
				oas.setOrderId(orderAfterSales.getOrderId());
				oas.setOrderType("2");
				oas.setVphFee(orderAfterSales.getVphFee());//分期退费金额
				oas.setVphAccountId(vphAccountId);//分期退款结算单ID
				oas.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//分期退款结算单状态：审核中
				oas.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//唯品会退款结算单取消状态:审核中
				oas.setReason(orderAfterSales.getReason());//退款原因
				oas.setCreateBy(orderAfterSales.getCreateBy());
				oas.setCreateDept(orderAfterSales.getCreateDept());//创建人部门
				oas.setCustMobile(orderAfterSales.getCustMobile());//客户电话
				oas.setCustName(orderAfterSales.getCustName());//客户姓名
				oas.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//添加即审核中
				oas.setAfterSalesKind("11");//延续订单分期退费
				oas.setIsAT(1);//手动录入
				//判断退款给唯品会或者客户，唯品会默认银行信息保存
				if(orderAfterSales.getRefundObject() != null && orderAfterSales.getRefundObject() == 2){
					oas.setBankCard(orderAfterSales.getBankCard());//银行卡
					oas.setBankCityCode(orderAfterSales.getBankCityCode());//城市
					oas.setBankMainName(orderAfterSales.getBankMainName());//银行名称
					oas.setBankName(orderAfterSales.getBankName());//开户行名称
					oas.setBankSupportId(orderAfterSales.getBankSupportId());//银行类型ID
					oas.setBankUserName(orderAfterSales.getBankUserName());//开户人姓名
				}else {
					oas.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_VPH);//银行卡
					oas.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_VPH);//城市
					oas.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
					oas.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_VPH);//开户行名称
					oas.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_05));//银行类型ID
					oas.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_VPH);//开户人姓名
				}
				oas.setRefundObject(orderAfterSales.getRefundObject());//唯品会退费对象
				oas.setReasonFlag(orderAfterSales.getReasonFlag());//退款原因标记
				oas.setIsStop(2);//是否终止 设置为否
				this.wrOrderAfterSalesMapper.insertOrderAfterSales(oas);
				//更新新订单余额
				updateTotalPayById(oas);
				//将结算单id放入redis，生成缴费明细
				try {
					redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+oas.getId()+"");
				} catch (RedisAccessException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogHelper.error(getClass(),".insertVphAfterSales()"+e.getMessage(),e);
			}

		}
		/**
		 * 更新唯品会售后信息
		 */
		@Override
		public void updateVphAfterSales(OrderAfterSales orderAfterSales) {
			try {
				OrderAfterSales oas = reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
				Payfee account = new Payfee();
				account.setId(oas.getVphAccountId());//退款结算单id
				account.setAccountAmount(orderAfterSales.getVphFee());//退款结算单金额
				account.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);
				account.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04));
				account.setIsBackType(3);//退款标记
				//更新退款结算单金额和退费金额
				updateQualityAccount(account);
				//售后信息
				OrderAfterSales oas2 = new OrderAfterSales();
				oas2.setId(orderAfterSales.getId());
				oas2.setOrderId(oas.getOrderId());
				oas2.setVphFee(orderAfterSales.getVphFee());
				oas2.setUpdateBy(orderAfterSales.getUpdateBy());
				oas2.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//审核中
				oas2.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//审核中
				//判断退款给唯品会或者客户，唯品会默认银行信息保存
				if(orderAfterSales.getRefundObject() != null && orderAfterSales.getRefundObject() == 2){
					oas2.setBankCard(orderAfterSales.getBankCard());//银行卡
					oas2.setBankCityCode(orderAfterSales.getBankCityCode());//城市
					oas2.setBankMainName(orderAfterSales.getBankMainName());//银行名称
					oas2.setBankName(orderAfterSales.getBankName());//开户行名称
					oas2.setBankSupportId(orderAfterSales.getBankSupportId());//银行类型ID
					oas2.setBankUserName(orderAfterSales.getBankUserName());//开户人姓名
				}else {
					oas2.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_VPH);//银行卡
					oas2.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_VPH);//城市
					oas2.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
					oas2.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_VPH);//开户行名称
					oas2.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_05));//银行类型ID
					oas2.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_VPH);//开户人姓名
				}
				oas2.setRefundObject(orderAfterSales.getRefundObject());//唯品会退费对象
				oas2.setReason(orderAfterSales.getReason());//退款原因
				oas2.setReasonFlag(orderAfterSales.getReasonFlag());
				//更新新订单余额
				updateTotalPayById(oas2);
				//更新售后信息
				this.wrOrderAfterSalesMapper.updateOrderAfterSales(oas2);
				//将结算单id放入redis，更新缴费明细
				try {
					redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+oas2.getId()+"");
				} catch (RedisAccessException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogHelper.error(getClass(),".updateVphAfterSales()"+e.getMessage(),e);
			}

		}
		/**
		 * 更新白条订单余额
		 */
		@Override
		public void updateTotalPayById(OrderAfterSales orderAfterSales){
			Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
			BigDecimal oldTotalPay = order.getTotalPay();
			BigDecimal newTotalPay = new BigDecimal(0);
			if (oldTotalPay != null && orderAfterSales.getVphFee() != null) { //订单余额不为空
				//订单余额=原订单余额-应退服务人员服务费
				newTotalPay = oldTotalPay.subtract(orderAfterSales.getVphFee()).setScale(2, BigDecimal.ROUND_HALF_UP);
				if (newTotalPay.compareTo(BigDecimal.ZERO) == -1) {
					newTotalPay = BigDecimal.ZERO;
				}
			} else {
				newTotalPay = BigDecimal.ZERO;
			}
			Order order2 = new Order();
			//将新订单余额存入订单
			order2.setId(order.getId());
			order2.setTotalPay(newTotalPay);
			order2.setUpdateBy(orderAfterSales.getUpdateBy());
			//更新订单金额
			cancleOrders(order2);
		}
		/**
		 * 更新订单可用余额
		 */
		public void updateTotalPay(OrderAfterSales orderAfterSales){
			Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
			BigDecimal oldTotalPay = order.getTotalPay();
			BigDecimal newTotalPay = new BigDecimal(0);
			if (oldTotalPay != null && orderAfterSales.getRefundTotal() != null) { //订单余额不为空
				//订单余额=原订单余额-应退服务人员服务费
				newTotalPay = oldTotalPay.subtract(orderAfterSales.getRefundTotal()).setScale(2, BigDecimal.ROUND_HALF_UP);
				if (newTotalPay.compareTo(BigDecimal.ZERO) == -1) {
					newTotalPay = BigDecimal.ZERO;
				}
			} else {
				newTotalPay = BigDecimal.ZERO;
			}
			Order order2 = new Order();
			//将新订单余额存入订单
			order2.setId(order.getId());
			order2.setTotalPay(newTotalPay);
			order2.setUpdateBy(orderAfterSales.getUpdateBy());
			//更新订单金额
			cancleOrders(order2);
		}
		/**
		 * 查询白条最大退款金额
		 */
		@Override
		public String queryVPHMaxMoney(OrderAfterSales orderAfterSales) {
			return this.reOrderAfterSalesMapper.queryVPHMaxMoney(orderAfterSales);
		}
		/**
		 * 查询信息费最大退款金额
		 * @param orderId
		 * @return
		 */
		@Override
		public String queryMemberMoney(Long orderId) {
			//根据订单id查询合同一次分账总金额
			String memberAccount = this.reOrderAfterSalesMapper.queryOrderMemberAccount(orderId);
			//根据订单id查询缴费明细扣除的信息费(售后之前退过信息费)
			String memberDetailFee = this.reOrderAfterSalesMapper.queryMemberDetailFee(orderId);
			BigDecimal c = new BigDecimal(0);
			if(memberAccount != null && memberAccount != "" &&  memberDetailFee != null && memberDetailFee != ""){
				BigDecimal a = new BigDecimal(memberAccount);
				BigDecimal b = new BigDecimal(memberDetailFee);
				 c = a.subtract(b).setScale(2, BigDecimal.ROUND_HALF_UP);
				//相减小于0，设置额度为0
				if (c.compareTo(BigDecimal.ZERO) == -1) {
					c = new BigDecimal(0);
				}
			}else if (memberAccount != null && memberAccount != "" &&  (memberDetailFee == null || memberDetailFee != "")) {
				BigDecimal a = new BigDecimal(memberAccount);
				c = a.setScale(2, BigDecimal.ROUND_HALF_UP);
			}else  {
				c = new BigDecimal(0);
			}
			return c.toString();
		}
		/**
		 * 查询应退服务人员服务费最大退款金额
		 * @param orderId
		 * @return
		 */
		@Override
		public String querySalaryMoney(Long orderId) {
			//根据订单id查询用缴费发的服务人员服务费明细
			String salaryDetail = this.reOrderAfterSalesMapper.querySalaryDetail(orderId);
			//根据订单id查询用缴费发的服务人员服务费明细(售后之前退的服务人员服务费)
			String salaryDetailFee = this.reOrderAfterSalesMapper.querySalaryDetailFee(orderId);
			BigDecimal c = new BigDecimal(0);
			if(salaryDetail != null && salaryDetail != "" &&  salaryDetailFee != null && salaryDetailFee != ""){
				BigDecimal a = new BigDecimal(salaryDetail);
				BigDecimal b = new BigDecimal(salaryDetailFee);
				 c = a.subtract(b).setScale(2, BigDecimal.ROUND_HALF_UP);
				//相减小于0，设置额度为0
				if (c.compareTo(BigDecimal.ZERO) == -1) {
					c = new BigDecimal(0);
				}
			}else if (salaryDetail != null && salaryDetail != "" &&  (salaryDetailFee == null || salaryDetailFee != "")) {
				BigDecimal a = new BigDecimal(salaryDetail);
				c = a.setScale(2, BigDecimal.ROUND_HALF_UP);
			}else  {
				c = new BigDecimal(0);
			}
			return c.toString();
		}

		@Override
		public void insertSaleServer(OrderAfterSales orderAfterSales) throws Exception {
			String auditFlag = "" ;    //售后单状态
			//新增结算
			Payfee accounts = new Payfee();
			//查询订单信息
			Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
			if(orderAfterSales.getHandle() != null && orderAfterSales.getHandle() == 1){
				if (order.getCateType() == 2 && orderAfterSales.getRefundTotal().compareTo(BigDecimal.ZERO) == 0 ) {
					auditFlag = AfterSaleConstant.AFTERSALE_AUDIT_FLAG_11;      //售后单已取消
					//设置订单取消操作状态
					order.setOrderStatus(10);//订单状态
					//更新合同
					updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getCreateBy());
					//释放服务人员排期
					updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
					//取消订单
					cancleOrders(order);
				}
				if (orderAfterSales.getRefundTotal() != null && orderAfterSales.getRefundTotal().compareTo(BigDecimal.ZERO) == 1 ) {//结算金额大于0
					accounts.setPayType("3");
					orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//售后单待确认
					orderAfterSales.setIsAT(1);//手动添加
					//生成退款结算单
					accounts = afterSaleAccount(orderAfterSales,accounts,orderAfterSales.getRefundTotal(),20130001,2);
					//记录退款结算单id
					orderAfterSales.setAccountPayId(accounts.getId());
					//保存银行信息表的银行卡内容
					if (orderAfterSales.getBankCard() != null && !"".equals(orderAfterSales.getBankCard())) {
					    saveBankCard(orderAfterSales,accounts);
					}
					Payfee payfee = new Payfee();
					payfee.setOrderId(orderAfterSales.getOrderId());
					payfee.setIsBackType(2);//不是退款结算单
					payfee.setValid(1);
					List<Payfee> accountList = this.rePayfeeMapper.queryAccount(payfee);
					//生成退费
					//afterSalePayfee(orderAfterSales,accountList,accounts,orderAfterSales.getRefundTotal(),true);
					orderAfterSales.setOrderType("2");//订单类型
					this.wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);
					//更新新订单余额
					updateTotalPay(orderAfterSales);
					//将结算单id放入redis，生成缴费明细
					try {
						redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
					} catch (RedisAccessException e) {
						e.printStackTrace();
					}
				}

			}else if (orderAfterSales.getHandle() == 2){
				updateAfterSales(orderAfterSales);
				//将结算单id放入redis，生成缴费明细
				try {
					redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
				} catch (RedisAccessException e) {
					e.printStackTrace();
				}
			}
		}

		public void updateAfterSales(OrderAfterSales orderAfterSales) {
			LogHelper.info(getClass() + ".updateOrderAfterSales()", "售后单更新:" + orderAfterSales.getAuditFlag()+"-"+orderAfterSales.getOrderId());
			try {
				if (StringUtils.isNotBlank(orderAfterSales.getCustMobile())) {
					orderAfterSales.setCustMobile(orderAfterSales.getCustMobile().trim());
				}
				if (StringUtils.isNotBlank(orderAfterSales.getCustName())) {
					orderAfterSales.setCustName(orderAfterSales.getCustName().trim());
				}
				orderAfterSales.setUpdateTime(DateUtil.getCurrDateTime());
				int returnValue = 0;
				Payfee payfee = new Payfee();
				payfee.setOrderId(orderAfterSales.getOrderId());
				payfee.setIsBackType(1);
				payfee.setValid(1);
				//查询退款结算单
				List<Payfee> accountList = this.rePayfeeMapper.queryAccount(payfee);
				//查询订单信息
				Order order = this.reOrderMapper.loadOrder(orderAfterSales.getOrderId());
				//确认无效
				if (AfterSaleConstant.AFTERSALE_AUDIT_FLAG_02.equals(orderAfterSales.getAuditFlag())) {
					for (Payfee pay : accountList) {
						//更新退款结算单
						Payfee accounts = new Payfee();
						accounts.setId(pay.getId());
						if (orderAfterSales.getMakesureFlag() != null && orderAfterSales.getMakesureFlag() == 2) {
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_02);//售后单状态:确认无效
							accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_02));//退款状态：确认无效
						} else {
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//售后单状态:待确认
							accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01));//退款状态：待确认
						}
						accounts.setAccountAmount(orderAfterSales.getRefundTotal());//银行卡退款金额
						//更新退费状态
						updateSaleAccount(accounts);
					}
				}
				//确认有效
				if (AfterSaleConstant.AFTERSALE_AUDIT_FLAG_03.equals(orderAfterSales.getAuditFlag())) {
					if (accountList.size() != 0 && !accountList.isEmpty()) {
						//延续服务订单终止
						if("8".equals(orderAfterSales.getAfterSalesKind())){
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
							//新订单余额
							BigDecimal oldTotalPay = order.getTotalPay();
							BigDecimal newTotalPay = new BigDecimal(0);
							OrderAfterSales oaSales = this.reOrderAfterSalesMapper.loadAfterSales(orderAfterSales.getId());
							//订单余额不为空
							if (oldTotalPay != null ) {
								//重新计算订单余额
								newTotalPay = newTotalPay(oldTotalPay,oaSales);
								if (newTotalPay.compareTo(BigDecimal.ZERO) == -1) {
									newTotalPay = BigDecimal.ZERO;
								}
							} else {
								newTotalPay = BigDecimal.ZERO;
							}
							//将新订单余额存入订单
							order.setTotalPay(newTotalPay);
							order.setUpdateBy(orderAfterSales.getUpdateBy());
							//合同
							Agreement agreement = new Agreement();
							agreement.setOrderId(orderAfterSales.getOrderId());
							try{
								//更新退款结算单
								for (Payfee pay : accountList) {
									Payfee accounts = new Payfee();
									//更新退费状态
									accounts.setPayStatus(20130004);//退款状态：审核中
									accounts.setId(pay.getId());//结算单ID
									//如果退费金额为0，则订单履约状态和合同状态更新为已完成，否则为已终止
									if (pay.getFeeSum() != null && pay.getFeeSum().compareTo(BigDecimal.ZERO) == 0) {
										order.setOrderStatus(9);//订单已完成
										agreement.setAgreementState(4);//合同已完成
									} else {
										order.setOrderStatus(12);//订单已终止
										agreement.setAgreementState(3);//合同已终止
									}
									accounts.setAccountAmount(orderAfterSales.getRefundTotal());
									//更新退款结算单和退费
									updateSaleAccount(accounts);
								}
								//更新订单状态
								cancleOrders(order);
								//更改合同状态
								updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getUpdateBy());
							}catch(Exception e){}
							//服务人员下户并释放排期
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
						}else if("5".equals(orderAfterSales.getAfterSalesKind())){
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
							order.setOrderStatus(10);//订单已取消
							order.setTotalPay(BigDecimal.ZERO);//设置订单余额为零
							cancleOrders(order);
							// 释放服务人员排期
							updateEmpSchedule(orderAfterSales.getOrderId(),orderAfterSales.getCreateBy());
							//已签约的订单状态，改变合同状态为已终止
							if (order.getOrderStatus() == 7) {
								updateAgreement(orderAfterSales.getOrderId(),3,orderAfterSales.getCreateBy());
							}
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04));//退款状态：审核中
								accounts.setId(pay.getId());//结算单ID
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								//更新退款结算单和退费
								updateSaleAccount(accounts);
							}
						}else{
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04));//退款状态：审核中
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								//更新退款结算单和退费
								updateSaleAccount(accounts);
							}
						}
					}
				}

					//审核失败
					if(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_05.equals(orderAfterSales.getAuditFlag())) {
						if (accountList.size() != 0 && !accountList.isEmpty()) {
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04);//售后单状态:审核中
							//更新退费状态
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_04));//退款状态：审核中
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								//更新退款结算单和退费
								updateSaleAccount(accounts);
							}
						}
					}

					//退款失败
					if (AfterSaleConstant.AFTERSALE_AUDIT_FLAG_09.equals(orderAfterSales.getAuditFlag())
							|| (!"".equals(orderAfterSales.getVphBackStatus()) && AfterSaleConstant.AFTERSALE_AUDIT_FLAG_09.equals(orderAfterSales.getVphBackStatus()))) {
						if (accountList.size() != 0 && !accountList.isEmpty()) {
							for (Payfee pay : accountList) {
								//更新退款结算单
								Payfee accounts = new Payfee();
								accounts.setId(pay.getId());//结算单ID
								if(!"".equals(orderAfterSales.getAuditFlag()) && AfterSaleConstant.AFTERSALE_AUDIT_FLAG_09.equals(orderAfterSales.getAuditFlag())){
									accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_06));//退款状态：审核成功
								}
								accounts.setAccountAmount(orderAfterSales.getRefundTotal());
								updateSaleAccount(accounts);
							}
						}
						if (AfterSaleConstant.AFTERSALE_AUDIT_FLAG_09.equals(orderAfterSales.getAuditFlag())){
							orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_06);//售后单状态:审核成功
						}else {
							orderAfterSales.setAuditFlag(null);//不更新售后单状态
						}
					}
					//更新银行信息表的银行卡内容
					try {
						if (!"".equals(orderAfterSales.getBankCard()) && !AfterSaleConstant.AFTERSALE_AUDIT_FLAG_10.equals(orderAfterSales.getAuditFlag())
								&& !"7".equals(orderAfterSales.getAfterSalesKind()) && accountList.size() != 0) {
							updateBankCard(orderAfterSales, accountList.get(0));
						}
					} catch (Exception e) {
					}
				//更新售后单信息
				returnValue = this.wrOrderAfterSalesMapper.updateOrderAfterSales(orderAfterSales);
				if (1 != returnValue) {
					throw new BaseException("更新售后单失败!");
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".updateAfterSales():"+e.getMessage(),e);
			}
		}

		@Override
		public String queryVphSum(Long orderId) {
			return this.reOrderAfterSalesMapper.queryVphSum(orderId);
		}

		@Override
		public String insertChangeOrder(OrderAfterSales orderAfterSales){
			int flag = 0;
			Long accountId = 0l;
			//唯品会迁单金额
			BigDecimal fee = null;
			String message = null;
			String msg = null;
			JSONObject obj = new JSONObject();
			try {
				//迁单设置默认银行卡号设置
				if (orderAfterSales.getMoveToOrderId() != null) {
					orderAfterSales.setBankCard("QD-0000");
					//判断由A迁单到B的，A单缴费是否全部分账
					List<Payfee> splitPayfee = this.rePayfeeMapper.queryPayfeeSplit(orderAfterSales.getOrderId());
					if (splitPayfee.size() != 0 && !splitPayfee.isEmpty()) {
						throw new BaseException("被迁单的订单有缴费未分账，请分账后再迁单！");
					}
					//保存售后银行卡
					//新增银行卡结算单
					orderAfterSales.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);
					orderAfterSales.setOrderType("2");
					if (orderAfterSales.getRefundTotal() != null  && orderAfterSales.getRefundTotal().compareTo(BigDecimal.ZERO) == 1) {
						accountId = insertAccountPay(orderAfterSales,1);
						orderAfterSales.setAccountPayId(accountId);
						orderAfterSales.setAfterSalesKind("12");//迁单状态
						//将唯品会金额保存到指定参数
						if (orderAfterSales.getVphFee() != null) {
							fee = orderAfterSales.getVphFee();
							orderAfterSales.setVphFee(null);
						}
						//添加售后
						flag = this.wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);
						if (flag != 1) {
							throw new BaseException("新增银行卡退款售后单失败！");
						}
						/*//将售后单id放入redis，生成缴费明细
						try {
							redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
						} catch (RedisAccessException e) {
							e.printStackTrace();
						}*/
					}
					//保存售后白条
//				if (orderAfterSales.getVphFee() != null  && orderAfterSales.getVphFee().compareTo(BigDecimal.ZERO) == 1) {
					if (fee != null  && fee.compareTo(BigDecimal.ZERO) == 1) {
						//根据结算单白条个数，判断每个白条订单，可迁金额，并生成退款结算单和售后单
						List<Payfee> accountList = this.reOrderAfterSalesMapper.queryVPHAccount(orderAfterSales.getOrderId());
						if (accountList.size() != 0 && !accountList.isEmpty()) {
							for (Payfee account : accountList) {
								orderAfterSales.setId(null);//清空售后id
								orderAfterSales.setAccountPayId(null);//清空结算单id
								orderAfterSales.setRefundTotal(null);//清空银行卡退款金额
								orderAfterSales.setRefundMembershipFee(null);//清空应退信息费
								orderAfterSales.setRefundSalaryFee(null);//清空应退服务人员服务费
								//根据结算单id，计算此结算单白条应退金额
								String vphFee = this.reOrderAfterSalesMapper.queryVPHFee(account.getId());
								if (vphFee != null && !"0".equals(vphFee)) {
									orderAfterSales.setVphFee(BigDecimal.valueOf(Double.parseDouble(vphFee)));
									//新增白条退款结算单
									accountId = insertAccountPay(orderAfterSales,3);
									//添加售后
									orderAfterSales.setVphAccountId(accountId);
									/*orderAfterSales.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);
									orderAfterSales.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);*/
									flag = this.wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);
									if (flag != 1) {
										throw new BaseException("新增白条退款售后单失败！");
									}
									/*//将售后单id放入redis，生成缴费明细
									try {
										redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+orderAfterSales.getId()+"");
									} catch (RedisAccessException e) {
										e.printStackTrace();
									}*/
								}
							}
						}
					}
				}
				msg = Constants.SCUUESS;
				message = "请求成功";
			} catch (BaseException e) {
				LogHelper.error(getClass(),".insertChangeOrder():"+e.getMessage(),e);
				msg = Constants.RET_ERROR;
				message = e.getMessage();
			} catch (Exception e1) {
				msg = Constants.RET_FAILED;
				message = e1.getMessage();
				LogHelper.error(getClass(),".insertChangeOrder():"+e1.getMessage(),e1);
			}
			obj.put("msg", msg);
			obj.put("message", message);
			return obj.toString();
		}

		public Long insertAccountPay(OrderAfterSales orderAfterSales,Integer isBackType){
			Long accountId = 0l;
			try {
				//添加退款结算单
				Payfee accounts = new Payfee();
				accounts.setId(null);
				accounts.setOrderId(orderAfterSales.getOrderId());//订单id
				accounts.setAccountAmount(orderAfterSales.getRefundTotal() != null?orderAfterSales.getRefundTotal():orderAfterSales.getVphFee());
				accounts.setPayStatus(Integer.valueOf(orderAfterSales.getAuditFlag())); //退款结算单支付状态
				if (isBackType != null && isBackType == 3) {
					accounts.setVphBackStatus(orderAfterSales.getAuditFlag());//唯品会退款结算单支付状态
					accounts.setVphStatus(orderAfterSales.getAuditFlag());//唯品会退款结算单取消状态
				}
				accounts.setPayType("3");//结算类型默认预收
				accounts.setPayKind(2);     //结算单关联订单类型
				accounts.setCreateBy(orderAfterSales.getCreateBy());
				accounts.setIsBackType(isBackType);//是退款结算单类型
				accounts.setBussStatus(1);//业务未处理状态
				accounts.setIsManual(1);//后台录入
				accounts.setRefundObject(orderAfterSales.getRefundObject());//退费对象
				this.wrPayfeeMapper.insertAccount(accounts);//生成新结算单信息
				accountId = accounts.getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return accountId;
		}

		@Override
		public List<Order> queryOrderA(Order order) {
			return this.reOrderAfterSalesMapper.queryOrderA(order);
		}

		@Override
		public List<Map<String, String>> isOrderSale(Long orderId) {
			return this.reOrderAfterSalesMapper.isOrderSale(orderId);
		}

		@Override
		public Order queryOrder(Long orderId) {
			return this.reOrderAfterSalesMapper.queryOrder(orderId);
		}

		@Override
		public Long queryDepartment(Long deptId) {
			return this.reOrderAfterSalesMapper.queryDepartment(deptId);
		}

		@Override
		public List<OrderAfterSales> queryAfterSalesByDepartment(OrderAfterSales orderAfterSales, Page page) {
				return this.reOrderAfterSalesMapper.queryAfterSalesByDepartmentlistPage(orderAfterSales);
		}

	private Boolean queryInstalmentsPay(Long accountId,int feepost, int isBackType){
			boolean result = false;
			try {
				Long  payId  = rePayfeeMapper.queryInstalmentsPay(accountId,feepost,isBackType);
				if ( payId != null) {
					result = true;
				}
			} catch (Exception e) {
				LogHelper.error(getClass(),".queryInstalmentsPay():"+e.getMessage(),e);
			}
			return result ;
		}

	/**
	 * 根据ID删除售后单
	 *
	 * @param id
	 * @param i
	 * @return
	 */
	@Override
	public void changeAfterSalesValid(Long id, int i) {
		wrOrderAfterSalesMapper.changeAfterSalesValid(id,i);
	}

	/**
	 * 移动端售后终止合同释放排期
	 *
	 * @param orderId
	 * @param createBy
	 */
	@Override
	public void updateAgreementAndEmpScheduleForApp(Long orderId,Long createBy){
		//更新合同
		updateAgreement(orderId,3,createBy);
		//释放服务人员排期
		updateEmpSchedule(orderId,createBy);
	}

	@Transactional
	public JSONObject insertOrderAfterSalesNew(OrderAfterSales orderAfterSales) throws Exception {
		JSONObject resultObj = new JSONObject();//返回提示信息
		String afterSalesKind = orderAfterSales.getAfterSalesKind();//售后类型
		Long orderId = orderAfterSales.getOrderId();//订单id
		Long moveToOrderId = orderAfterSales.getMoveToOrderId();//缴费迁移到的目标订单ID
		Long loginBy = orderAfterSales.getLoginBy();//登录人信息
		Long loginDept = orderAfterSales.getLoginDept();//登录人信息
		String loginOrgCode= orderAfterSales.getLoginOrgCode();//登录人信息
		Integer loginLevel = orderAfterSales.getLoginLevel();//登录人信息
		OrderAfterSales sales = new OrderAfterSales();//根据订单id,查询售后单是否已存在,只能申请一次售后
		sales.setOrderId(orderId);
		sales.setFlagPage(-1);
		List<OrderAfterSales> salesList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(sales);
		if (salesList != null && salesList.size() > 0) {
			resultObj.put("code","01");
			resultObj.put("msg","当前订单已存在售后单");
			LogHelper.info(getClass(),resultObj.toString());
			return resultObj;
		}
		if (moveToOrderId != null) {
			orderAfterSales.setBankCard("QD-0000");//迁单设置默认银行卡号设置
			List<Payfee> notspfList = this.rePayfeeMapper.queryPayfeeSplit(orderId);//判断由A迁单到B的，A单缴费是否全部分账
			if (notspfList != null && notspfList.size() > 0) {
				resultObj.put("code","01");
				resultObj.put("msg","被迁单的订单有未分账缴费,需分账后才能迁单");
				LogHelper.info(getClass(),resultObj.toString());
				return resultObj;
			}
		}

		Order order = this.reOrderMapper.loadOrder(orderId);//查询售后订单信息
		Long parentId = order.getParentId();//执行订单关联的解决方案id
		if(parentId != null && parentId != 0){
			OrderAfterSales oas = new OrderAfterSales();
			oas.setOrderId(parentId);
			oas.setFlagPage(-1);
			List<OrderAfterSales> serverSaleList = this.reOrderAfterSalesMapper.queryOrderAfterSaleslistPage(oas);
			if(serverSaleList != null && serverSaleList.size() > 0){
				resultObj.put("code","01");
				resultObj.put("msg","当前执行订单对应的解决方案订单已申请售后");
				LogHelper.info(getClass(),resultObj.toString());
				return resultObj;
			}else{
				JSONObject solutionParam = new JSONObject();
				solutionParam.put("orderId", orderId);
				solutionParam.put("updateBy",loginBy);
				LogHelper.info(getClass(),"执行订单释放排期参数："+solutionParam.toString());
				String solutionResult = this.activitiesInterfaceService.cancelSolutionPlanOrder(solutionParam.toString());
				LogHelper.info(getClass(),"执行订单释放排期返回："+solutionResult);
				JSONObject solutionResultJson = JSONObject.fromObject(solutionResult);
				String code = solutionResultJson.get("code")!=null?solutionResultJson.getString("code"):"";//1:成功,2:失败
				if(!"1".equals(code)){
					resultObj.put("code","01");
					resultObj.put("msg","执行订单释放排期失败");
					LogHelper.info(getClass(),resultObj.toString());
					return resultObj;
				}
			}
		}
		if("1".equals(afterSalesKind)){
			//FA订单取消
			//取消包裹
		}else if("4".equals(afterSalesKind)){
			//单次服务订单取消
		}else if("5".equals(afterSalesKind)){
			//延续性服务订单取消
		}else if("6".equals(afterSalesKind)){
			//解决方案订单取消
		}else if("8".equals(afterSalesKind)){
			//延续性服务订单终止
		}else if("9".equals(afterSalesKind)){
			//解决方案订单退费
		}else if("10".equals(afterSalesKind)){
			//延续性订单退费
		}
		return resultObj;
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<HashMap<String, Object>> queryPayGroupBy(Long orderId) {
		return this.reAfterSalesMapper.queryPayGroupBy(orderId);
	}

	/**
	 * 普通售后根据订单ID查询退款金额
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181214
	 */
	@Override
	public Map<String,BigDecimal> queryAfterSalesAmountByOrderId(Long orderId) {
		Map<String,BigDecimal> map = reAfterSalesMapper.queryAfterSalesAmountByOrderId(orderId);
		return map;
	}
	
	public static void main(String[] args) {
		BigDecimal a = new BigDecimal(100);
		BigDecimal b = new BigDecimal(1000);
		System.err.println(a.compareTo(b));
	}
	/**
	 * 
	 * @Description: 查询商品编码  
	 * @param orderId
	 * @return      
	 * @return: List<String>
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	@Override
	public List<String> queryProductByOrderId(Long orderId) {
		//Map<String,BigDecimal> map = reAfterSalesMapper.queryAfterSalesAmountByOrderId(orderId);
		List<String> queryProductByOrderId = reAfterSalesMapper.queryProductByOrderId(orderId);
		return queryProductByOrderId;
	}
	
	/**
	 * 
	 * @Description:根据订单ID 查询是否包含制定的三级分类商品 
	 * @param orderId
	 * @return      
	 * @return: List<String>
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	@Override
	public Integer selectProductThreeCode(Long orderId) {
		//Map<String,BigDecimal> map = reAfterSalesMapper.queryAfterSalesAmountByOrderId(orderId);
		Integer code = reAfterSalesMapper.selectProductThreeCode(orderId);
		return code;
	}
	
	
}
