package com.emotte.order.order.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.emotte.dubbo.finance.SplitService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.eclient.EClientContext;
import com.emotte.emerp.interf.serviceinventory.EServiceInventory;
import com.emotte.kernel.date.DateUtils;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.authorg.service.OrgService;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.order.mapper.reader.ReItemMapper;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.reader.RePayfeeMapper;
import com.emotte.order.order.mapper.reader.RePersonnelMapper;
import com.emotte.order.order.mapper.reader.ReProductDivideMapper;
import com.emotte.order.order.mapper.reader.ReProductThirdDivideMapper;
import com.emotte.order.order.mapper.reader.ReSerialMapper;
import com.emotte.order.order.mapper.writer.WrOrderBaseMapper;
import com.emotte.order.order.mapper.writer.WrOrderItemSalaryMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.mapper.writer.WrOrderSliceMapper;
import com.emotte.order.order.mapper.writer.WrOrderTurnLogMapper;
import com.emotte.order.order.mapper.writer.WrSerialMapper;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Managers;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderImport;
import com.emotte.order.order.model.OrderItemSalary;
import com.emotte.order.order.model.OrderSlice;
import com.emotte.order.order.model.OrderTurnLog;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.model.Personnel;
import com.emotte.order.order.model.PojoPersonnel;
import com.emotte.order.order.model.ProductDivide;
import com.emotte.order.order.model.ProductThirdDivide;
import com.emotte.order.order.model.Serial;
import com.emotte.order.order.service.AccountPayService;
import com.emotte.order.order.service.BathInserService;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.order.service.OrderDistributeService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.IdCardUtil;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.sf.json.JSONObject;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReOrderMapper reOrderMapper;

	@Resource
	private WrOrderMapper wrOrderMapper;

	@Resource
	private ReSerialMapper reSerialMapper;
	@Resource
	private WrSerialMapper wrSerialMapper;

	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private WrOrderBaseMapper wrOrderBaseMapper;
	@Resource
	private OrderBaseService orderBaseService;
	@Resource
	private SplitService splitService;
	@Resource
	private RePayfeeMapper rePayfeeMapper;
	@Resource
	private RePersonnelMapper rePersonnelMapper;
	@Resource
	private WrOrderItemSalaryMapper wrOrderItemSalaryMapper;
	@Resource
	private ReItemMapper reItemMapper;
	@Resource
	private ReProductDivideMapper reProductDivideMapper;
	@Resource
	private ReProductThirdDivideMapper reProductThirdDivideMapper;
	@Resource
	private WrOrderSliceMapper wrOrderSliceMapper;
	@Resource
	private SMSServiceDubbo sMSServiceDubbo;
	@Resource
	private OrderDistributeService orderDistributeService;
	@Resource
	private AccountPayService accountPayService;
	@Resource
	private OrderService orderService;
	@Resource
	private RedisClient redisClient;
	@Resource
	private BathInserService bathService;
	/*@Resource
	private WrHistoryMapper wrHistoryMapper;*/
	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private OrgService orgService;
	@Resource
	private WrOrderTurnLogMapper wrOrderTurnLogMapper;
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Order loadOrder(Long id) {
		return this.reOrderMapper.loadOrder(id);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Order loadOrderByOrder(Order order) {
		return this.reOrderMapper.loadOrderByOrder(order);
	}
	
	/*@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Order> queryOrder(Order order, Page page) {
		List<Order> list =null;
		long start = Calendar.getInstance().getTimeInMillis();
		if(""==order.getBirthTimeOrder()){
			if (page.needQueryPading()) {
				page.setTotalRecord(reOrderMapper.countOrder(order));
			}
			long end = Calendar.getInstance().getTimeInMillis();
			System.out.println("countOrder:" +(end-start));
			order.setBeginRow(page.getFilterRecord().toString());
			order.setPageSize(page.getPageCount().toString());
			 list = this.reOrderMapper.queryOrder(order);
			System.out.println("queryOrder:" +(Calendar.getInstance().getTimeInMillis()-end));
		}else{
			if (page.needQueryPading()) {
				page.setTotalRecord(reOrderMapper.countOrderBirthDate(order));
			}
			long end = Calendar.getInstance().getTimeInMillis();
			System.out.println("countOrder:" +(end-start));
			order.setBeginRow(page.getFilterRecord().toString());
			order.setPageSize(page.getPageCount().toString());
			 list = this.reOrderMapper.queryOrderBirthDate(order);
			System.out.println("queryOrder:" +(Calendar.getInstance().getTimeInMillis()-end));
		}
		return list;
	}*/
	/**
	 * 新查询订单
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Order> queryOrder(Order order, Page page) {
		List<Order> list =null;
		List<Order> listRe =null;
		int curPage = (page.getCurPage() - 1) * page.getPageCount();
		order.setBeginRow(String.valueOf(curPage));
		order.setPageSize(String.valueOf(page.getPageCount() * 5)); //条数 * 5
		if(""==order.getBirthTimeOrder()){
			list = this.reOrderMapper.queryOrder(order);
		}else{
			list = this.reOrderMapper.queryOrderBirthDate(order);
		}
		page.setTotalRecord(list.size()+curPage);//list条数设置最大条数
		if(list.size() >= page.getPageCount()){
			listRe = list.subList(0, page.getPageCount());//取第0到每页条数
		}else{
			listRe = list;
		}
		return listRe;
	}

	// 保存订单方法
	@Override
	public Order insertOrder(Order order) {
		// 取用户信息
		OrderUser user = new OrderUser();
		user.setUsValid(1);
		user.setAdValid(1);
		user.setId(order.getReceiverId());
		user.setUserId(order.getUserId());
		user = this.reOrderBaseMapper.querLoadUser(user);
		// 用户
		order.setUserCityCode(user.getUserCityCode());
		order.setUserName(user.getRealName());
		order.setUserProvince(user.getUserProvince());
		order.setUserCity(user.getUserCity());
		order.setUserArea(user.getUserCountry());
		order.setUserAddress(user.getUserAddress());
		order.setUserMobile(user.getUserMobile());
		order.setUserEmail(user.getUserEmail());
		order.setUserSex(user.getUserSex());
		order.setUserBirth(user.getUserBirth());

		// 需要通过用户城市订算出运费
		// order.setDeliverPay(new BigDecimal(1.1));
		// 接收人
		order.setReceiverName(user.getContactName());
		order.setReceiverMobile(user.getContactPhone());
		order.setReceiverSex(user.getSex());
		order.setReceiverBirth(user.getBirth());
		order.setReceiverProvince(user.getProvince());
		order.setReceiverCity(user.getCity());
		order.setReceiverArea(user.getCountry());
		order.setReceiverCityCode(user.getCityCode());
		order.setReceiverAddress((user.getAddressChoose()==null?"":user.getAddressChoose()) 
				+(user.getAddressDetail()==null? "":user.getAddressDetail()));
		order.setLongitude(user.getLongitude());
		order.setLatitude(user.getLatitude());
		// 常用信息
		// Serial sel = getOrderSerail(0);
		// order.setOrderCode(String.valueOf(sel.getOrderId()));
		order.setOrderStatus(order.getOrderStatus() == null ? 1 : order.getOrderStatus());
		order.setVersion(0L);
		order.setPayStatus("20110001");
		order.setUpdateBy(order.getCreateBy());
		order.setOtype("1");//后台录入订单
		// 订单分包处理
		orderDistributeService.distribute(order);
		
		
		String phones = null ;
		/*
		if(user.getMarketBy()!=null){
			order.setRechargeBy(user.getMarketBy());
			order.setRechargeDept(user.getDeptId());
			order.setFollowBy(user.getMarketBy());
			order.setFollowDept(user.getDeptId());
		}else{
			if((user.getDeptId()!=null && user.getDeptId()==order.getCreateDept()) || user.getDeptId()==null){
				if(order.getCateType()==1 || order.getCateType()==2 || order.getCateType()==3 || order.getCateType()==4 || order.getCateType()==8){
					OrderUser ou = this.orderBaseService.orderUserFollow(order);
					order.setRechargeBy(ou.getMarketBy());
					order.setRechargeDept(ou.getDeptId());
					order.setFollowBy(ou.getMarketBy());
					order.setFollowDept(ou.getDeptId());
					this.wrOrderBaseMapper.updateOrderUser(ou);
					if(ou.getPhones()!=null&&!"".equals(ou.getPhones())){
						phones = ou.getPhones();
					}
				}
			}else if(user.getDeptId()!=null && user.getDeptId()!=order.getCreateDept()){
				order.setRechargeDept(user.getDeptId());
				order.setFollowDept(user.getDeptId());
			}
		}*/
		// 判断是订单是否有ID存在
		if (null == order.getId()) {
			order.setId(this.reOrderMapper.queryOrderId());
		}
		if(order.getCateType()!=null&&order.getCateType()==2){
			order.setTotalPay(new BigDecimal(0));
			order.setOrderStatus(2);
		}
		//如果是他营三方订单，不给负责人和负责人部门
		if(order.getCateType()!=null&&order.getCateType()==4){
			order.setRechargeBy(null);
			order.setRechargeDept(null);
		}
		this.wrOrderMapper.insertOrder(order);
		//添加转单初始数据
		OrderTurnLog orderTurnLog = new OrderTurnLog();
		orderTurnLog.setOrderId(order.getId());
		orderTurnLog.setRechargeBy(order.getRechargeBy());
		orderTurnLog.setRechargeDept(order.getRechargeDept());
		orderTurnLog.setRemark("初始记录");
		wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);
		//自动分发 插入订单轮询负责管家记录，并给管家发送短信
		if ((order.getIsNewFF() == 1 || order.getIsNewFF() == 3)
				&& order.getRechargeBy() != null && !"".equals(order.getRechargeBy()) && order.getCateType() == 2
				&& order.getOrderFenfa() != null && "2".equals(order.getOrderFenfa())) {
			if (order.getIsNewFF() == 1) {
				Org org = new Org();
				org.setOrderId(order.getId());
				org.setManager(order.getRechargeBy().toString());
				this.orgService.insertPollRecord(org);
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
			if (managerList.size() != 0 &&  !managerList.isEmpty()) {
				JSONObject smsData2 = new JSONObject();
				smsData2.accumulate("mobiles", managerList.get(0).getPhone());
				smsData2.accumulate("templet", "order_423"); // 
				smsData2.accumulate("channel", "sys");
				smsData2.accumulate("system", "order"); 
				smsData2.accumulate("rate", "1"); //1即时发送，2定时发送
				smsData2.accumulate("params", new String[]{userName,order.getUserMobile(),dictionary.getDvalue(),order.getOrderCode(),}); 
				String res = this.sMSServiceDubbo.send(smsData2.toString());
				// 返回结果(success/fail:失败原因)
				LogHelper.info(getClass()+".send()", "发送短信结果："+res);
			}
		}
		
		if(phones!=null){
			JSONObject smsData = new JSONObject();
			smsData.accumulate("mobiles", phones);
			smsData.accumulate("templet", "order_142"); // 
			smsData.accumulate("channel", "sys");
			smsData.accumulate("system", "order"); 
			smsData.accumulate("rate", "1"); //1即时发送，2定时发送
			smsData.accumulate("params", new String[]{order.getOrderCode(),order.getUserName(),order.getUserMobile()}); 
			// 返回结果(seccess/fail:失败原因)
			try{
				// 限制只有北京地区的订单才发送短信通知
				if(order.getCity().substring(0,9).equals("101001001")){
					String res = this.sMSServiceDubbo.send(smsData.toString());
					if("fail".equals(res)){
						System.out.println("orderCode:" +order.getOrderCode() +" sen message fail!");
					}
				}
			}catch(Exception e){}
			
		}
		return order;

	}

	@Override
	public boolean updateOrder(Order order) {
		boolean returnValue;
		try {
			if(order.getUserId()!=null && order.getReceiverId()!=null){
				// 取用户信息
				OrderUser user = new OrderUser();
				user.setUsValid(1);
				user.setAdValid(1);
				user.setId(order.getReceiverId());
				user.setUserId(order.getUserId());
				user = this.reOrderBaseMapper.querLoadUser(user);
				order.setReceiverName(user.getContactName());
				order.setReceiverMobile(user.getContactPhone());
				order.setReceiverSex(user.getSex());
				order.setReceiverBirth(user.getBirth());
				order.setReceiverProvince(user.getProvince());
				order.setReceiverCity(user.getCity());
				order.setReceiverArea(user.getCountry());
				order.setReceiverCityCode(user.getCityCode());
				order.setReceiverAddress(user.getAddressChoose() +user.getAddressDetail());
				order.setLongitude(user.getLongitude());
				order.setLatitude(user.getLatitude());
			}
			// 如果是延续性订单，不修改总金额
			if(order.getCateType()!=null&&order.getCateType()==2){
				order.setTotalPay(null);
			}
			//returnValue = this.wrOrderMapper.updateOrder(order);
			returnValue = orderService.updateOrder2(order);
			if (!returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateOrder:" + e);
			throw new BaseException(e);
		}
		return returnValue;
	}
	
	@Override
	public boolean updateOrder2(Order order) {
		int i = wrOrderMapper.updateOrder(order);
		if (i > 0) {
			if (order.getOrderStatus() != null && order.getId() != null) {
				try {
					redisClient.lpush(CommonConstant.REDIS_ORDER_STATUS, "{orderId:"+order.getId()+",orderStatus:"+order.getOrderStatus()+"}");
					//TODO:大众点评，订单历史状态表记录数据
//					this.insertOrderHistory(order);
				} catch (RedisAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return i > 0 ? true : false;
	}
	//TODO:
	/*private void insertOrderHistory(Order order) {
		Order order2 = this.orderService.loadOrder(order.getId());
		try {
			if (CommonConstant.ORDER_CHANNEL_DAZHONGDIANPINGQINZI.equals(order2.getOrderChannel()) ) {
				History history = new History();
				//单次服务
				if (order2.getCateType() == 1) {
					//关联服务人员信息，保存服务人员id
					ItemInterview itemInterview = new ItemInterview();
					itemInterview.setOrderId(order.getId());
					itemInterview.setType(14);
					List<ItemInterview> list = this.itemInterviewService.queryInterviews(itemInterview);
					List<String> list2 = new ArrayList<String>();
					if (order.getOrderStatus() == 2 || order.getOrderStatus() == 4 || order.getOrderStatus() == 8 || order.getOrderStatus() == 9){
						for (ItemInterview itemInterview2 : list) {
							//已受理，已匹配，已上户，已下户
							list2.add(itemInterview2.getId().toString());
						}
						String[] arr = list2.toArray(new String[0]);
						String str=StringUtils.join(arr, ",");
						if ("".equals(str)) {
							str = null;
						}
						history.setTechnician(str);//服务人员id
					}
				} 
				
				history.setOrderId(order.getId());//订单id
				history.setPayStatus(Long.parseLong(order2.getPayStatus()));//订单支付状态
				history.setOrderStatus(Long.parseLong(order.getOrderStatus().toString()));//订单状态
				history.setChannel(order2.getOrderChannel());//订单渠道
				if (order2.getRechargeBy() != null) {
					history.setRechargeBy(order2.getRechargeBy().toString());//订单负责人
				}
				if (order2.getRechargeDept() != null) {
					history.setRechargeDept(order2.getRechargeDept().toString());//订单负责部门
				}
				history.setOrderSourceId(order2.getOrderSourceId().toString());//订单来源
				history.setCateType(order2.getCateType().toString());//订单类型
				history.setCreateBy(1l);
				this.wrHistoryMapper.insertHistory(history);
				log.info("updateOrder2/insertOrderHistory返回id："+history.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/

	/**
	 * 取订单ID方法
	 * 
	 * @param index
	 *            防止死循环,如果递归50次还没成功取出，返回出错
	 * @return
	 */
	@Override
	public Serial getOrderSerail(int index) {
		if (index >= 50) {
			throw new RuntimeException("无法取出订号编号，请检查库存序列是否充足！");
		}
		DateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Serial serial = new Serial();
		List<Serial> list = this.reSerialMapper.getOrderMinSerial(0L);
		// 有就取值，如果没有，则重新生成
		if (this.listCheckNull(list)) {
			serial = list.get(0);
			serial.setUpdateTime(dfDateFormat.format(Calendar.getInstance().getTime()));
			serial.setVersion(1L);
			// System.out.println("serialVersion:" +serial.getVersion() +" id:"
			// +serial.getId());
			int num = this.wrSerialMapper.updateSerialVersion(serial);
			if (num != 1) {
				index++;
				getOrderSerail(index);
			}
		} else {
			Long begintime = Calendar.getInstance().getTimeInMillis();
			System.out.println("begintime:" + begintime);
			serial.setOrderId(Long.valueOf(createOrderSerial(10001, 89998, 1)));
			Long endtime = Calendar.getInstance().getTimeInMillis();
			System.out.println("endtime:" + endtime);
			System.out.println("本次新订单生成共耗时：" + (endtime - begintime) + "毫秒");

		}
		return serial;
	}

	// 订单编号生成
	@Override
	public String createOrderTest() {
		Long begintime = Calendar.getInstance().getTimeInMillis();
		System.out.println("begintime:" + begintime);
		String num = createOrderSerial(10001, 89998, 2);
		Long endtime = Calendar.getInstance().getTimeInMillis();
		System.out.println("endtime:" + endtime);
		System.out.println("本次新订单生成共耗时：" + (endtime - begintime) + "毫秒");
		return num + "," + (endtime - begintime) / 1000;
	}

	// 生成一批新的订单号
	public String createOrderSerial(int begnum, int endnum, int type) {
		DateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();
		String datestr = dfDateFormat.format(date);
		Random random = new Random();

		// 得到前段5位数集
		StringBuffer frontstr = new StringBuffer("");
		List<Long> listfront = this.reSerialMapper.querySerialFronts();
		if (this.listCheckNull(listfront)) {
			for (Long fLong : listfront) {
				frontstr.append(fLong + ",");
			}
		}
		// 确定前段5位数
		int nowfront = 0;
		int nulfront = 0;
		Long begintime = Calendar.getInstance().getTimeInMillis();
		for (int i = begnum; i <= endnum; i++) {
			int num = begnum + random.nextInt(endnum);
			if (frontstr.toString().contains(String.valueOf(num))) {
				if (nulfront == 0 && !frontstr.toString().contains(String.valueOf(i))) {
					nulfront = i;
				}
				continue;
			} else {
				nowfront = num;
				break;
			}
		}
		Long endtime = Calendar.getInstance().getTimeInMillis();
		System.out.println("前段循环共耗时：" + (endtime - begintime) + "毫秒");
		if (nowfront == 0 && nulfront > 0) {
			nowfront = nulfront;
		} else if (nowfront == 0 && nulfront == 0) {
			// 捡漏掉的数
			for (int i = begnum; i <= endnum; i++) {
				if (!frontstr.toString().contains(String.valueOf(i))) {
					nulfront = i;
					break;
				}
			}
			if (nulfront == 0) {
				/*
				 * 若干年后，当前位数的订单号用完时，订单号自动进一位
				 */
				createOrderSerial(Integer.valueOf("" + begnum + "1"), Integer.valueOf("9" + endnum + ""), 1);
			}
		}
		// 后段5位数生成
		// 转成list保存
		List<Serial> lists = new ArrayList<Serial>();
		int begin = 10001 + random.nextInt(10000);
		int end = 75000 + random.nextInt(4998);
		StringBuffer endstr = new StringBuffer("");
		begintime = Calendar.getInstance().getTimeInMillis();
		String orderCode = String.valueOf(nowfront);
		// 每次亲订单生成之前，如果是每天生成的话，需要清除掉不用的订单编号
		// this.wrSerialMapper.deleteSerialVersion(0L);
		// 生成新订单,需要分批保存s
		int listNum = 0;
		for (int i = begin; i <= end; i++) {
			int num = begin + random.nextInt(end);
			Serial serial = new Serial();
			serial.setOrderIdFront(Long.valueOf(nowfront));
			if (i == begin) {
				// 取出生成的第一个数返回
				orderCode += String.valueOf(num);
				serial.setVersion(1L);
			}
			// 如果不重复，则保存
			if (endstr.toString().contains(String.valueOf(num))) {
				if (!endstr.toString().contains(String.valueOf(i))) {
					serial.setOrderId(Long.valueOf(String.valueOf(serial.getOrderIdFront()) + String.valueOf(i)));
					serial.setOrderIdAfter(Long.valueOf(i));
					serial.setCreateTime(datestr);
					serial.setUpdateTime(datestr);
					serial.setVersion(0L);
					lists.add(serial);
					listNum++;
					endstr.append(String.valueOf(i) + ",");
				}
			} else {
				serial.setOrderId(Long.valueOf(String.valueOf(serial.getOrderIdFront()) + String.valueOf(num)));
				serial.setOrderIdAfter(Long.valueOf(num));
				serial.setCreateTime(datestr);
				serial.setUpdateTime(datestr);
				serial.setVersion(0L);
				lists.add(serial);
				listNum++;
				endstr.append(String.valueOf(num) + ",");
			}
			// 设置每500条保存一次
			if (listNum % 500 == 0 && lists.size() >= 1) {
				// begintime = Calendar.getInstance().getTimeInMillis() ;
				this.wrSerialMapper.insertSerialList(lists);
				// System.out.println("保存insertSerialList到第" +listNum +"条时，共耗时："
				// +(Calendar.getInstance().getTimeInMillis()-begintime) +"毫秒");
				lists = new ArrayList<Serial>();
			}

		}
		// System.out.println("后段第一次循环共耗时："
		// +(Calendar.getInstance().getTimeInMillis()-begintime) +"毫秒, 共生成订单编号："
		// +lists.size() +" 条" );
		// 捡漏掉的数
		for (int i = begin; i <= end; i++) {
			if (!endstr.toString().contains(String.valueOf(i))) {
				Serial serial = new Serial();
				serial.setOrderIdFront(Long.valueOf(nowfront));
				serial.setOrderId(Long.valueOf(String.valueOf(serial.getOrderIdFront()) + String.valueOf(i)));
				serial.setOrderIdAfter(Long.valueOf(i));
				serial.setCreateTime(datestr);
				serial.setUpdateTime(datestr);
				serial.setVersion(0L);
				lists.add(serial);
				listNum++;
				endstr.append(String.valueOf(i) + ",");
			}
			// 设置每500条保存一次
			if (listNum % 500 == 0 && lists.size() >= 1) {
				begintime = Calendar.getInstance().getTimeInMillis();
				this.wrSerialMapper.insertSerialList(lists);
				// System.out.println("保存insertSerialList到第" +listNum +"条时，共耗时："
				// +(Calendar.getInstance().getTimeInMillis()-begintime) +"毫秒");
				lists = new ArrayList<Serial>();
			}
		}
		// 最后保存一次
		if (lists != null && lists.size() >= 1) {
			this.wrSerialMapper.insertSerialList(lists);
		}
		System.out.println("本次共生成订单编号:" + listNum + " 条");
		// System.out.println("后段循环共耗时："
		// +(Calendar.getInstance().getTimeInMillis()-begintime) +"毫秒");

		// begintime = Calendar.getInstance().getTimeInMillis();
		// this.wrSerialMapper.insertSerialList(lists);
		// System.out.println("insertSerialList共耗时："
		// +(Calendar.getInstance().getTimeInMillis()-begintime) +"毫秒");
		if (type == 1) {
			return orderCode;
		} else {
			return String.valueOf(listNum);
		}

	}

	// 如果有值，返回true
	public boolean listCheckNull(List<?> list) {
		boolean tf = false;
		try {
			if (list != null && list.size() > 0 && list.get(0) != null && !"null".equals(list.get(0))) {
				tf = true;
			}
		} catch (Exception e) {
			tf = false;
		}
		return tf;

	}

	@Override
	public List<Order> queryOrderBasicItem(Long id) {
		List<Order> list = null;
		list = this.reOrderMapper.queryOrderBasicItem(id);
		return list;
	}

	/**
	 * 考勤管理-服务人员服务费填报 查询列表操作
	 * 
	 * @author 张顺
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Order> queryAttendanceOrder(Order order, Page page) {
		List<Order> list = new ArrayList<Order>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			order.setPage(page);
			list = this.reOrderMapper.queryAttendanceOrderlistPage(order);
			if(list.size()>0){
				Date dateTodayDay = sdf.parse(DateUtil.getCurrDateTime());//当前日期(格式：年月日)
				Calendar c = Calendar.getInstance();
				for (Order o : list) {
					Order oi = new Order();
					oi.setSalaryOrderId(o.getId());
					Boolean resultInterview = this.reOrderMapper.existsInterview(oi);
					if(!resultInterview){
						//如果上户不存在上户和下户的记录，不可填报
						o.setIsReport(2);
						continue;
					}
					Date StartDate = sdf.parse(o.getStartTime());//开始日期
					Date endDate = new Date();//结束日期
					int maxOrderMonth=0;//可以得到的最大月
					D:
					for (int i = 1; i < 30; i++) {
						c.setTime(StartDate);
						c.add(Calendar.MONTH, 1);
						c.add(Calendar.DAY_OF_YEAR, -1);
						endDate = c.getTime();//第一个月结束日期
						if(dateTodayDay.getTime()-endDate.getTime()>=0){
							//System.err.println(o.getOrderCode()+"第" + i + "个月," + "开始：" + sdf.format(StartDate) + ",结束：" + sdf.format(endDate));
							c.add(Calendar.DAY_OF_YEAR, 1);
							StartDate = c.getTime();// 下一个开始日期
							o.setIsReport(1);//可以填报
							maxOrderMonth= i;
						}else{
							//如果从开始服务日期到当前日期还不足一个月,不可填报。
							if(i == 1){
								o.setIsReport(2);//不可填报
							}
							break D;
						}
					}
					//查询到当前最近一个月（可查询到的最大月），是否己存在服务人员服务费表中。
					if(maxOrderMonth!=0){
						Order os = new Order();
						os.setSalaryOrderMonth(maxOrderMonth);
						os.setSalaryOrderId(o.getId());
						Boolean resultSalary = this.reOrderMapper.existsSalary(os);
						//System.err.println("是否存在："+(resultSalary?"存在":"不存在"));
						if(resultSalary){
							o.setIsReport(2);//不可填报
						}else{
							o.setIsReport(1);//可以填报
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("queryAttendanceOrder" + e);
			throw new BaseException(e);
		}
		return list;
	}

	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryFollowDept(String data){
		StringBuffer jsonstr = new StringBuffer("[{");
		List<Dictionary> list = this.reOrderMapper.queryFollowDept();
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
		}else{
			jsonstr.append("}");
		}
		jsonstr.append("]");
		return jsonstr;
	}
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryFollowBy(Dictionary dictionary){
		StringBuffer jsonstr = new StringBuffer("[{");
		List<Dictionary> list = this.reOrderMapper.queryFollowBy(dictionary);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
		}else{
			jsonstr.append("}");
		}
		jsonstr.append("]");
		return jsonstr;
	}

	@Override
	public List<Order> queryOrderNoPage(Order order) {
		return this.reOrderMapper.queryOrderNoPage(order);
	}

	@Override
	public void insertOrderSlice(List<Order> orderList) {
		try {
				OrderSlice orderSlice = new OrderSlice();
				for (Order ord : orderList) {
					Item item = new Item();
					item.setOrderId(ord.getId());
					List<Item> orderItem = this.reItemMapper.queryAllItem(item);
					System.out.println("当前订单id:"+ord.getId());
					for (Item oItem : orderItem) {
						CityProduct cityProduct = new CityProduct();
						cityProduct.setProductCode(oItem.getProductCode());
						cityProduct.setProductPriceType(oItem.getProductPriceType());
						cityProduct = this.reItemMapper.queryItemByCodeAndType(cityProduct);
						if (ord.getCateType() == 1) {
							ProductDivide productdivide = new ProductDivide();
							productdivide.setProductDictPriceId(cityProduct.getId());
							List<ProductDivide> productDivides = this.reProductDivideMapper.queryProductDivide(productdivide);
							if (productDivides != null && !productDivides.isEmpty()) {
								for (ProductDivide proDivide : productDivides) {
									orderSlice.setOrderId(ord.getId());
									orderSlice.setIsOther(2);
									orderSlice.setDivideId(proDivide.getId());
									orderSlice.setCreateTime(DateUtil.getCurrDateTime());
									orderSlice.setCreateBy(ord.getCreateBy());
									orderSlice.setIsDefault(2);
									this.wrOrderSliceMapper.insertOrderSlice(orderSlice);
									System.out.println("自营单次Slice表Id:"+orderSlice.getId());
								}
							}
						} else {
							ProductThirdDivide productThirdDivide = new ProductThirdDivide();
							productThirdDivide.setProductDictPriceId(cityProduct.getId());
							List<ProductThirdDivide> productThirdDivides = this.reProductThirdDivideMapper.queryProductThirdDivide(productThirdDivide);
							if (productThirdDivides != null && !productThirdDivides.isEmpty()) {
								for (ProductThirdDivide proThirdDivide : productThirdDivides) {
									orderSlice.setOrderId(ord.getId());
									orderSlice.setIsOther(1);
									orderSlice.setDivideId(proThirdDivide.getId());
									orderSlice.setCreateTime(DateUtil.getCurrDateTime());
									orderSlice.setCreateBy(ord.getCreateBy());
									orderSlice.setIsDefault(2);
									this.wrOrderSliceMapper.insertOrderSlice(orderSlice);
									System.out.println("他营单次Slice表Id:"+orderSlice.getId());
								}
							}
						}
					  }
					}
		} catch (Exception e) {
			log.error("insertOrderSlice" + e);
			throw new BaseException(e);
		}
	}

	@Override
	public void updateOrderSalary(List<Order> orderList) {
		try {
			for (Order ord : orderList) { 
				Payfee payfee = new Payfee();
				payfee.setOrderId(ord.getId());
				payfee.setPayStatus(20110003);//结算完成的结算单
				payfee.setValid(1);//可用结算单
				List<Payfee> payfeeList = this.rePayfeeMapper.queryAccount(payfee);
				BigDecimal accountAmount = new BigDecimal(0.00);
				if (payfeeList != null && !payfeeList.isEmpty()) {
					//结算金额
					for (Payfee pf : payfeeList) {
						accountAmount = accountAmount.add(pf.getFeeSum().setScale(2, BigDecimal.ROUND_HALF_UP));
					}
					//查询当前订单的服务人员
					List<Personnel> pList = this.rePersonnelMapper.queryPersonnelByOrderId(ord.getId());
					//服务人员个数
					BigDecimal countPerson = new BigDecimal(pList.size());
					System.out.println("服务人员个数:"+pList.size()+"个。");
					//服务人员平均服务人员服务费
					BigDecimal avgSalary = accountAmount.divide(countPerson);
					System.out.println("服务人员服务人员服务费avgSalary:"+avgSalary+"元。");
					for (Personnel personnel : pList) {
						System.out.println("服务人员id:"+personnel.getId());
						String json = "{\"orderId\":\""+ord.getId()+"\",\"totalMoney\":\""+avgSalary+"\",\"servicePersonId\":\""+personnel.getId()+"\"}";
						//调用单次服务分账接口
						String payJson = this.splitService.singleServerSplit(json);
						JSONObject jsonObj = JSONObject.fromObject(payJson);
						System.out.println("是否分账成功:"+jsonObj.get("result"));
						if ("success".equals(String.valueOf(jsonObj.get("result")))) {
							OrderItemSalary orderItemSalary = new OrderItemSalary();
							orderItemSalary.setOrderId(ord.getId());//订单id
							orderItemSalary.setAccountId(payfeeList.get(0).getId());//结算单id
							orderItemSalary.setPersonId(personnel.getId());//服务人员id
							orderItemSalary.setValid(2);
							orderItemSalary.setCreateBy(ord.getCreateBy());
							orderItemSalary.setVersion((long)1);
							//保存已扫描的订单id、结算单id，服务人员id等
							this.wrOrderItemSalaryMapper.insertOrderItemSalary(orderItemSalary);
						}else{
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("updateOrderSalary" + e);
			throw new BaseException(e);
		}
	}
	
	
	//取消订单操作
	@Override
	public Boolean updateSolutionOrders(Order order) throws BaseException{
		Boolean result = false;
		try {
			order.setUpdateTime(DateUtil.getCurrDateTime());
			result = orderService.updateOrder2(order);
		} catch (Exception e) {
			throw new BaseException("更新订单失败");
		}
		return result;
	}
	
	public static void main(String[] args) {
		String receiverAreaCode = "101001001007";
		System.out.println(receiverAreaCode.substring(0,6));
		System.out.println(receiverAreaCode.substring(0,9));
		
		
	}

	//查询系统时间
	@Override
	public String querySysdate() {
		return this.reOrderMapper.querySysdate();
	}
	//查询服务人员
	@Override
	public List<Map<String, Object>> Person(Order order, Page page) {
		/*if (page.needQueryPading()) {
			page.setTotalRecord(reOrderMapper.countPerson(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());*/
		order.setPage(page);
		order.setFlagPage(1);
		List<Map<String, Object>> list = this.reOrderMapper.queryPersonlistPage(order);
		return list;
	}

	@Override
	/**
	 * 导出
	 */
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response,Order order,List<Order> list,Long updateBy) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("订单列表");
		/*// 设置标题样式
		CellStyle titleStyle = workbook.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		//设置自适应宽度
		sheet.autoSizeColumn(1); 
		sheet.autoSizeColumn(1, true);*/
		
		/**
		 * 创建工作区 长list.size()+1  （即数据长度+一行表头）  宽 10
		 */
		for(int i=0;i<list.size()+1;i++){
			sheet.createRow(i);
			for(int j=0;j<=20;j++){
				sheet.getRow(i).createCell(j);
			}
		}
        sheet.getRow(0).getCell(0).setCellValue("序号");
		sheet.getRow(0).getCell(1).setCellValue("订单编号");
		sheet.getRow(0).getCell(2).setCellValue("客户姓名");
		sheet.getRow(0).getCell(3).setCellValue("客户电话");
		sheet.getRow(0).getCell(4).setCellValue("订单类型");
		sheet.getRow(0).getCell(5).setCellValue("创建时间");
		sheet.getRow(0).getCell(6).setCellValue("录入人");
		sheet.getRow(0).getCell(7).setCellValue("录入部门");
		sheet.getRow(0).getCell(8).setCellValue("负责人");
		sheet.getRow(0).getCell(9).setCellValue("负责部门");
		sheet.getRow(0).getCell(10).setCellValue("订单状态");
		sheet.getRow(0).getCell(11).setCellValue("支付状态");
		sheet.getRow(0).getCell(12).setCellValue("订单渠道");
		sheet.getRow(0).getCell(13).setCellValue("订单来源");
		sheet.getRow(0).getCell(14).setCellValue("宝宝出生日期");
		for (int i = 0,j=1; i < list.size(); i++,j++) {
			sheet.getRow(j).getCell(0).setCellValue(j);
			sheet.getRow(j).getCell(1).setCellValue(list.get(i).getOrderCode()!=null ? list.get(i).getOrderCode() : null);
			sheet.setColumnWidth(1, 5000);
			sheet.getRow(j).getCell(2).setCellValue(list.get(i).getUserName()!=null ? list.get(i).getUserName() : null);
			sheet.getRow(j).getCell(3).setCellValue(list.get(i).getUserMobile()!=null ? list.get(i).getUserMobile() : null);
			sheet.setColumnWidth(1, 4000);
			sheet.getRow(j).getCell(4).setCellValue(list.get(i).getTypeText()!=null ? list.get(i).getTypeText() : null);
			sheet.getRow(j).getCell(5).setCellValue(list.get(i).getCreateTime()!=null ? list.get(i).getCreateTime() : null);
			sheet.getRow(j).getCell(6).setCellValue(list.get(i).getCreateByText()!=null ? list.get(i).getCreateByText() : null);
			sheet.getRow(j).getCell(7).setCellValue(list.get(i).getCreateDeptText()!=null ? list.get(i).getCreateDeptText() : null);
			sheet.setColumnWidth(7, 5000);
			sheet.getRow(j).getCell(8).setCellValue(list.get(i).getRechargeByText()!=null ? list.get(i).getRechargeByText() : null);
			sheet.setColumnWidth(9, 5000);
			sheet.getRow(j).getCell(9).setCellValue(list.get(i).getRechargeDeptText()!=null ? list.get(i).getRechargeDeptText() : null);
			sheet.getRow(j).getCell(10).setCellValue(list.get(i).getStatusText()!=null ? list.get(i).getStatusText() : null);
			sheet.getRow(j).getCell(11).setCellValue(list.get(i).getPayText()!=null ? list.get(i).getPayText() : null);
			sheet.getRow(j).getCell(12).setCellValue(list.get(i).getChannelText()!=null ? list.get(i).getChannelText() : null);
			sheet.getRow(j).getCell(13).setCellValue(list.get(i).getSourceText()!=null ? list.get(i).getSourceText() : null);
			sheet.getRow(j).getCell(14).setCellValue(list.get(i).getBirthTimeOrder()!=null ? list.get(i).getBirthTimeOrder() : null);
			sheet.setColumnWidth(14, 5000);
		}
		return workbook;
		
	}

	@Override
	public List<Order> queryOrderByUserId(Long userId) {
		return this.reOrderMapper.queryOrderByUserId(userId);
	}

	@Override
	public List<Map<String, Object>> queryOrderUserAllFee() {
		return this.reOrderMapper.queryOrderUserAllFee();
	}
	@Override
	public Boolean updateOrderTotalPay(AccountPay pay) throws BaseException {
		try {
			String payType = pay.getPayType();
			Double money = pay.getAccountAmount().doubleValue();
			if ("4|6".contains(payType)) {
				money = -money; // 4,6是发放服务人员服务费和会员服务人员服务费入，需要减去
			}
			int i = wrOrderMapper.updateTotalPay(pay.getOrderId(), money);
			// 更改结算单业务处理状态
			boolean j = accountPayService.updateBussType(pay.getId(), "2");
			// 插入订单可用余额日志
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderId", pay.getOrderId());
			map.put("accountId", pay.getId());
			map.put("money", money);
			int n = wrOrderMapper.inserOrderTotalPayLog(map); 
			if (i > 0 && j && n > 0) return true;
		} catch (Exception e) {
			throw new BaseException("更新订单余额失败"); // 回滚
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Order> queryChannelOrder(Order order, Page page) {
		List<Order> list =null;
		try {
			if (page.needQueryPading()) {
				page.setTotalRecord(reOrderMapper.countChannelOrder(order));
			}
			order.setBeginRow(page.getFilterRecord().toString());
			order.setPageSize(page.getPageCount().toString());
			list = this.reOrderMapper.queryChannelOrder(order);
		} catch (Exception e) {
			log.error("queryChannelOrder"+e);
		}
		return list;
	}

	@Override
	public String queryServiceStartTime(Long orderId) {
		return this.reOrderMapper.queryServiceStartTime(orderId);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Integer queryChannelManagerType(Long id) {
		Integer channelManagerType = null;
		try {
			channelManagerType = this.reOrderMapper.queryChannelManagerType(id);
		} catch (Exception e) {
			log.error("queryChannelManagerType"+e);
		}
		return channelManagerType;
	}

	@Override
	public List<Managers> queryCallCenter() {
		return this.reOrderMapper.queryCallCenter();
	}
	/**
	 * 减少库存
	 * @param data {<br>
	 * 	"cityCode":"100200020001",  // 必填 <br>
	 * 	"cateCode":"101001001", // 必填<br>
	 * 	"selectionTime":"2017-06-19", // 必填<br>
	 * 	"startTime":"10:00", // 必填<br>
	 * 	"endTime":"12:00" // 必填<br>
	 * 	"orderId":"123456789012345" // 必填<br>
	 * }
	 * @return {<br>
	 * 	"code":"1",  // 1 成功，2失败<br>
	 * 	"data": true,  // true 成功，false 失败<br>
	 * 	"msg":"成功"   // 或者失败消息<br>
	 * } <br>
	 */
	@Override
	public String reduceInventory(String cityCode, String cateCode, String selectionTime, String startTime,
			String endTime,Integer num,Long orderId) {
		String result = "";
		LogHelper.info(getClass()+ ".reduceInventory()","减少库存获取的参数：cityCode="+cityCode+""
				+ " ,cateCode="+cateCode+" ,selectionTime="+selectionTime+" ,startTime="+ startTime+""
			    + " ,endTime="+endTime+" ,num="+num+" ,orderId="+orderId);
		try {
			EServiceInventory eServiceInventory =  (EServiceInventory) EClientContext.getBean(EServiceInventory.class);
			String data = "{'cityCode':'"+cityCode+"','cateCode':'"+cateCode+"',"
					+"'selectionTime':'"+selectionTime+"','startTime':'"+startTime+"',"
				    + "'endTime':'"+endTime+"','num':'"+num+"','orderId':'"+orderId+"'}";
			result = eServiceInventory.reduceInventory(data);
			LogHelper.info(getClass()+ ".reduceInventory()","获取返回的参数:"+result);
		} catch (BaseException e) {
			LogHelper.error(getClass(), ".reduceInventory():"+e.getMessage(),e);
		}
		return result;
	}
	/**
	 * 增加库存
	 * @param data {<br>
	 * 	"cityCode":"100200020001",  // 必填 <br>
	 * 	"cateCode":"101001001", // 必填<br>
	 * 	"selectionTime":"2017-06-19", // 必填<br>
	 * 	"startTime":"10:00", // 必填<br>
	 * 	"endTime":"12:00" // 必填<br>
	 * 	"orderId":"123456789012345" // 必填<br>
	 * }
	 * @return {<br>
	 * 	"code":"1",  // 1 成功，2失败<br>
	 * 	"data": true,  // true 成功，false 失败<br>
	 * 	"msg":"成功"   // 或者失败消息<br>
	 * } <br>
	 */
	@Override
	public String increaseInventory(String cityCode, String cateCode, String selectionTime, String startTime,
			String endTime,Integer num,Long orderId) {
		String result = "";
		LogHelper.info(getClass()+ ".increaseInventory()","增加库存获取的参数：cityCode="+cityCode+""
				+ " ,cateCode="+cateCode+" ,selectionTime="+selectionTime+""
				+ " ,startTime="+ startTime+" ,endTime="+endTime+" ,num="+num+" ,orderId="+orderId);
		try {
			EServiceInventory eServiceInventory =  (EServiceInventory) EClientContext.getBean(EServiceInventory.class);
			String data = "{'cityCode':'"+cityCode+"','cateCode':'"+cateCode+"',"
					+"'selectionTime':'"+selectionTime+"','startTime':'"+startTime+"',"
					+ "'endTime':'"+endTime+"','num':'"+num+"','orderId':'"+orderId+"'}";
			result = eServiceInventory.increaseInventory(data);
			LogHelper.info(getClass()+ ".increaseInventory()","获取返回的参数:"+result);
		} catch (BaseException e) {
			LogHelper.error(getClass(), ".increaseInventory():"+e.getMessage(),e);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryUserGJBInstallmentFee() {
		//return this.reOrderMapper.queryUserGJBInstallmentFee();
		return null;
	}
	/**
	 * 查询当前订单服务人员下一订单负责人
	 */
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryRechargeBy(ItemInterview itemInterview){
	  return this.reOrderMapper.queryRechargeBy(itemInterview);
	}
	/**
	 * 查询当前订单服务人员是否与下一个订单冲突
	 */
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<ItemInterview> queryByConflict(ItemInterview itemInterview){
		return this.reOrderMapper.queryByConflict(itemInterview);
	}

	@Override
	public Order loadOrderByCodeAndCategory(Long orderCode, String category) {
		return reOrderMapper.loadOrderByCodeAndCategory(orderCode, category);
	}

	@Override
	public List<Order> queryChangeOrder(Order order) {
		return reOrderMapper.queryChangeOrder(order);
	}

	@Override
	public List<Map<String, Object>> orderEmpPushRminds() {
		return reOrderMapper.orderEmpPushRminds();
	}

	@SuppressWarnings("restriction")
	@Override
	public String downloadEmpFileCard(Long empId,String path) {
		String fileLocation = "";
	 try {
		PojoPersonnel emp = reOrderMapper.downloadEmpFileCard(empId);
		if(emp != null){
			BufferedImage templateImg = ImageIO.read(new File(path+"images/empInfoCard20171120.jpg"));// 底图模板
			Graphics graphics = templateImg.getGraphics();
			try {
				String original_address = emp.getOriginal_address();
				if(original_address != null && !"".equals(original_address)){
					BufferedImage empImg = ImageIO.read(new URL(original_address));// 人员头像
					graphics.drawImage(empImg, 60, 150, 279, 361, null);
				}
				String workLifePhoto = emp.getWorkLifePhoto();
				if(workLifePhoto != null && !"".equals(workLifePhoto)){
					String[] photos = workLifePhoto.split(",");
					for (int i = 0; i < photos.length; i++) {
						BufferedImage empImg1 = ImageIO.read(new URL(photos[i]));// 生活照/工作照
						int x = (i==0?61:(i==1?341:(i==2?621:(i==3?901:-1))));
						if(x != -1){
							graphics.drawImage(empImg1, x, 1029, 279, 334, null);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String idCardNum = emp.getP_idCardNum();
			JSONObject json = IdCardUtil.getInfoByCard(idCardNum);
			Font font = new Font("宋体", Font.BOLD, 24);
			graphics.setColor(Color.black);
			graphics.setFont(font);
			// 第一行
			graphics.drawString(emp.getP_name()==null?"":emp.getP_name(), 400, 185);//姓名
			graphics.drawString(emp.getC_name()==null?"":emp.getC_name(), 686, 185);//籍贯
			graphics.drawString(json.getString("age")==null?"":json.getString("age")+"岁", 978, 185);//年龄
			// 第二行
			graphics.drawString(emp.getBloodText()==null?"":emp.getBloodText(), 400, 245);//血型
			graphics.drawString(emp.getP_workingLife()==null?"":emp.getP_workingLife()+"年", 737, 245);//从业年限
			graphics.drawString(emp.getP_education()==null?"":emp.getP_education(), 978, 245);//学历
			// 第三行
			graphics.drawString(json.getString("zodiac")==null?"":json.getString("zodiac"), 400, 307);//属相
			graphics.drawString(json.getString("constellation")==null?"":json.getString("constellation"), 686, 307);//星座
			// 第四行
			graphics.drawString(emp.getP_homeAddress()==null?"":emp.getP_homeAddress(), 449, 368);//户籍住址
			// 第五行
			graphics.drawString(emp.getTypeCodes()==null?"":emp.getTypeCodes(), 449, 428);//技能证书
			// 第六行
			graphics.drawString("", 521, 485);//个人技能
			// 第七行
			graphics.drawString(emp.getP_isMarry()==null?"":emp.getP_isMarry(), 450, 549);//婚姻状况
			graphics.drawString("", 737, 549);//是否生育
			graphics.drawString(emp.getChildrenNum()==null?"":emp.getChildrenNum(), 1024, 549);//子女数量
			// 第八行
			graphics.drawString("", 450, 610);//子女年龄
			graphics.drawString(emp.getChildInCityText()==null?"":emp.getChildInCityText(), 737, 610);//子女在京
			graphics.drawString(emp.getChildHasJobText()==null?"":emp.getChildHasJobText(), 1024, 610);//子女就业
			// 第九行
			graphics.drawString(emp.getCoupleHereText()==null?"":emp.getCoupleHereText(), 450, 670);//配偶在京
			graphics.drawString(emp.getCoupleProfession()==null?"":emp.getCoupleProfession(), 737, 670);//配偶职业
			graphics.drawString(emp.getParentsAliveText()==null?"":emp.getParentsAliveText(), 1024, 670);//父母健在
			// 第十行
			graphics.drawString(emp.getParentsHereText()==null?"":emp.getParentsHereText(), 450, 730);//父母在京
			// 第十一行
			String str = emp.getP_specialty();
			if(str != null && !"".equals(str)){
				drawLongString(graphics, str, 25, 33, 348, 799);//工作经历和自我介绍
			}
			// 第十二行
			graphics.drawString(emp.getP_salaryExpectation()==null?"":emp.getP_salaryExpectation(), 180, 1400);//期望服务人员服务费
			graphics.drawString(emp.getW_empWorkTypeName()==null?"":emp.getW_empWorkTypeName(), 425, 1400);//工种
			// 第十三行
			graphics.drawString(emp.getProductManagerName()==null?"":emp.getProductManagerName(), 220, 1466);//负责产品管家
			graphics.drawString(emp.getW_workTypeLevelName()==null?"":emp.getW_workTypeLevelName(), 425, 1466);//等级
			File d = new File(path+"temp");
			if(!d.exists()){
				d.mkdirs();
			}
			fileLocation = path+"temp"+File.separator+emp.getP_id()+".jpg"; 
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(templateImg);
			bos.close();
			fos.close();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
		return fileLocation;
	}
	
	/**
	 * 
	 * @param graphics
	 * @param str
	 * @param rowHigh行高
	 *            像素
	 * @param wordCount每行字数
	 * @param x
	 * @param y
	 */
	public static void drawLongString(Graphics graphics, String str, int rowHigh, int wordCount, int x, int y) {
		int row = str.length() / wordCount + (str.length() % wordCount > 0 ? 1 : 0);
		for (int i = 0; i < row; i++) {
			if (i == (row - 1)) {
				graphics.drawString(str.substring(i * wordCount), x, y + (i * rowHigh));
			} else {
				graphics.drawString(str.substring(i * wordCount, (i + 1) * wordCount), x, y + (i * rowHigh));
			}
		}
	}

	/**
	 * 生成jpg图片
	 * 
	 * @param fileLocation
	 * @param image
	 */
	private static void createImage(String fileLocation, BufferedImage image) {
		try {
			FileOutputStream fos = new FileOutputStream(fileLocation);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
			encoder.encode(image);
			bos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 校验字段，并更新到相应表中
	 */
	@Override
	@Transactional
	public void insertImportOrder(Map<String, String> map, Long createBy,Long createDept, List<Map<String, Object>> dictionary,List<Map<String, Object>> authManager) throws Exception {
		OrderImport order = new OrderImport();
		String[] parm = {"orderCode","productName", "userName", "userMobile", "receiverProvince", "receiverCity", "receiverArea",
				"receiverAddress", "startTime","endTime", "createTime", "productCode", "quantity", "personNumber","remark" };
	
		String orderCode =  map.get("orderCode");
		String userName = map.get("userName");
		String userMobile = map.get("userMobile");
		String receiverProvince = map.get("receiverProvince");
		String receiverCity = map.get("receiverCity");
		String receiverArea = map.get("receiverArea");
		String receiverAddress = map.get("receiverAddress");
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		//String createTime = map.get("createTime");
		String productCode = map.get("productCode");
		String remark = map.get("remark");
		String quantity = map.get("quantity");
		String personNumber = map.get("personNumber");
		String isrepeat = map.get("isrepeat");
		String rechargeByName = map.get("rechargeByName");
		String orderChannelText = map.get("orderChannelText");
		StringBuffer errormsg = new StringBuffer();
		if(isrepeat!=null && isrepeat.equals("1")){   //有重复直接跳出，进入下一个循坏
			return;
		}
		
		int flag = 0 ;
		if(userName!=null && !userName.equals("")){
			if(userName.length() <= 100){	
				order.setUserName(userName);
			}else{
				if (flag == 0) {
					errormsg.append("该行的用户名太长");
					flag = 1;
				} else {
					errormsg.append(",用户名太长");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的用户为空");
				flag = 1;
			} else {
				errormsg.append(",用户为空");
			}
		}
		
		if (userMobile != null && !userMobile.equals("")) {
			if(userMobile.getBytes().length <= 11 ){
				order.setReceiverMobile(userMobile);
				order.setUserMobile(userMobile);
			}else{
				if (flag == 0) {
					errormsg.append("该行的收货人手机太长");
					flag = 1;
				} else {
					errormsg.append(",收货人手机太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的客户手机号为空");
				flag = 1;
			} else {
				errormsg.append(",收货人电话为空");
			}
		}
		
		if (receiverProvince != null && !receiverProvince.equals("")) {
			if(receiverProvince.getBytes().length <= 100){
				order.setReceiverProvince(receiverProvince);
				order.setUserProvince(receiverProvince);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收人省份太长");
					flag = 1;
				} else {
					errormsg.append(",接收人省份太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收省份为空");
				flag = 1;
			} else {
				errormsg.append(",接收省份为空");
			}
		}
		
		if (receiverCity != null && !receiverCity.equals("")) {
			if(receiverCity.getBytes().length <= 100){
				order.setReceiverCity(receiverCity);
				order.setUserCity(receiverCity);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收人城市太长");
					flag = 1;
				} else {
					errormsg.append(",接收人城市太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收城市为空");
				flag = 1;
			} else {
				errormsg.append(",接收城市为空");
			}
		}

		if (receiverArea != null && !receiverArea.equals("")) {
			if(receiverArea.getBytes().length <= 100){
				order.setReceiverArea(receiverArea);
				order.setUserArea(receiverArea);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收地区太长");
					flag = 1;
				} else {
					errormsg.append(",接收地区太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收区域为空");
				flag = 1;
			} else {
				errormsg.append(",接收区域为空");
			}
		}
		
		if (receiverAddress != null && !receiverAddress.equals("")) {
			if(receiverAddress.getBytes().length <= 200){
				order.setReceiverAddress(receiverAddress);
				order.setUserAddress(receiverAddress);
			}else{
				if (flag == 0) {
					errormsg.append("该行的详细地址太长");
					flag = 1;
				} else {
					errormsg.append(",详细地址太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的详细地址为空");
				flag = 1;
			} else {
				errormsg.append(",详细地址为空");
			}
		}
		
		if(flag==0){
			try {	
				Map<String,Object> userMap = bathService.getUserDetail(userMobile, userName, receiverProvince, receiverCity, receiverArea, receiverAddress);
				if(userMap!=null&&!userMap.isEmpty()){
					String longitude = userMap.get("LONGITUDE").toString();
					String latitude = userMap.get("LATITUDE").toString();
					String userId = userMap.get("ID").toString();
					order.setUserId(Long.parseLong(userId));
					if(longitude!=null&&latitude!=null&&!"".equals(longitude)&&!"".equals(latitude)){
						order.setLatitude(latitude);
						order.setLongitude(longitude);
					}
				}else{
					if (flag == 0) {
						errormsg.append("该行的用户信息不完整没有生成用户信息");
						flag = 1;
					} else {
						errormsg.append(",用户信息不完整没有生成用户信息");
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				if (flag == 0) {
					errormsg.append("该行的用户信息不完整没有生成用户信息");
					flag = 1;
				} else {
					errormsg.append(",用户信息不完整没有生成用户信息");
				}
			}
		}
		
		if (quantity != null && !quantity.equals("")) {
			try {
				order.setQuantity(Float.valueOf(quantity));;
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的商品数量格式不正确");
					flag = 1;
				} else {
					errormsg.append(",商品数量格式不正确");
				}
			}
		}else {
			if (flag == 0) {
				errormsg.append("该行的商品数量为空");
				flag = 1;
			} else {
				errormsg.append(",商品数量为空");
			}
		}
		
		if (personNumber != null && !personNumber.equals("")) {
			try {
				order.setPersonNumber(Integer.valueOf(personNumber));
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的服务人数格式不正确");
					flag = 1;
				} else {
					errormsg.append(",服务人数格式不正确");
				}
			}
		}else{
			order.setPersonNumber(1);
		}
		
		
		if(productCode!=null&&!productCode.equals("")){
			try {
				Map<String,Object> productMap = bathService.getDetail(productCode, "20000002");
				if(productMap!=null&&!productMap.isEmpty()){
					String productImg = productMap.get("PRODUCT_IMG_MAIN").toString();
					String productUnit = productMap.get("LEAST_UNIT").toString();
					String productName = productMap.get("NAME").toString();
					String productPrice = productMap.get("PRICE").toString();
					String categoryCode = productMap.get("CATEGORY_CODE").toString();
					String productSpec = productMap.get("PRODUCT_SPEC").toString();
					String cityCode = productMap.get("CITY_CODE").toString();
					
					order.setProductImg(productImg);
					order.setProductUnit(productUnit);
					order.setProductCode(productCode);
					order.setProductName(productName);
					order.setProductPrice(new BigDecimal(productPrice));
					order.setCategoryCode(categoryCode);
					order.setProductSpec(productSpec);
					order.setCityCode(cityCode);
					order.setProductPriceType("20000002");
					BigDecimal totalPay = new BigDecimal(quantity).multiply(new BigDecimal(productPrice));
					order.setTotalPay(totalPay);
				}else{
					if (flag == 0) {
						errormsg.append("该行的商品编码有误");
						flag = 1;
					} else {
						errormsg.append(",商品编码有误");
					}
				}
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的商品编码有误");
					flag = 1;
				} else {
					errormsg.append(",商品编码有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的商品编码为空");
				flag = 1;
			} else {
				errormsg.append(",商品编码为空");
			}
		}
		
		if(orderChannelText!=null && !orderChannelText.equals("")){
			Long orderChannel = null;
			for (Map<String, Object> dc : dictionary) {
				if (dc.get("DICT_NAME").equals(orderChannelText)) {
					orderChannel = Long.valueOf(dc.get("DICT_CODE").toString());
					break;
				}
			}
			if(orderChannel != null){
				order.setOrderChannel(orderChannel);
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单渠道有误");
					flag = 1;
				} else {
					errormsg.append(",订单渠道有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的订单渠道为空");
				flag = 1;
			} else {
				errormsg.append(",订单渠道为空");
			}
		}
		
		if(rechargeByName!=null && !rechargeByName.equals("")){
			Long rechargerBy = null;
			Long rechargeDept = null;
			for (Map<String, Object> dc : authManager) {
				System.out.println(dc.get("MANAGER_NAME"));  
				System.out.println(rechargeByName); 
				if (dc.get("MANAGER_NAME").equals(rechargeByName)) {
					rechargerBy = Long.valueOf(dc.get("MANAGER_ID").toString());
					rechargeDept = Long.valueOf(dc.get("ORG_ID").toString());
					break;
				}
			}
			if(rechargerBy != null){
				order.setRechargeBy(rechargerBy);
				order.setRechargeDept(rechargeDept);
			}else{
				if (flag == 0) {
					errormsg.append("该行的负责管家账号有误");
					flag = 1;
				} else {
					errormsg.append(",负责管家账号有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的负责管家账号有误");
				flag = 1;
			} else {
				errormsg.append(",负责管家账号有误");
			}
		}
		if (startTime != null && !startTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(startTime);
				order.setStartTime(startTime);
				//order.setCreateTime(startTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的开始时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",开始时间格式不正确");
				}

			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的开始时间为空");
				flag = 1;
			} else {
				errormsg.append(",开始时间为空");
			}
		}
		if (endTime != null && !endTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(endTime);
				order.setEndTime(endTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的结束时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",结束时间格式不正确");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的结束时间为空");
				flag = 1;
			} else {
				errormsg.append(",结束时间为空");
			}
		}
		order.setRemark(remark);
		order.setOrderSourceId(20180007);
		order.setPayStatus("20110001");
		order.setCateType("1");
		order.setCreateBy(createBy);
		order.setCreateDept(createDept);
		order.setThreeOrderCode(orderCode);
		if(flag == 0){
			try {
				wrOrderMapper.saveOrderImport(order);
				wrOrderMapper.saveOrderImportItem(order);
				wrOrderMapper.saveOrderImportDetailServer(order);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", "失败");
				map.put("errormsg", "数据导入数据库出现异常");
			}
			map.put("success", "成功");
			map.put("errormsg", "");
		}else{
			errormsg.append(";");
			map.put("success", "失败");
			map.put("errormsg", errormsg.toString());
		}
	}

	/**
	 * 校验字段，并更新到相应表中
	 */
	@Override
	@Transactional
	public Map<String, String> insertImportOrderNew(Map<String, String> map, Long createBy,Long createDept, List<Map<String, Object>> dictionary,List<Map<String, Object>> authManager,List<Map<String, Object>> proviceCityAreaMap) throws Exception {
		String str = null;
		Map<String,String> map2 = new HashMap<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm");
		OrderImport order = new OrderImport();
		String orderCode =  map.get("orderCode");
		String userMobile = map.get("userMobile");
		String userName = map.get("userName");
		String receiverArea = map.get("receiverArea");
		String receiverAreaCode = map.get("receiverAreaCode");
		//填充省市
		map = addProvinceAndCity(proviceCityAreaMap,map,receiverAreaCode);
		
		String receiverProvince = map.get("receiverProvince");//省
		String receiverCity = map.get("receiverCity");//市
		String receiverAddress = map.get("receiverAddress");
		String orderChannelText = map.get("orderChannelText");
		String orderChannelTemp = map.get("orderChannel");
		String productNameTemp = map.get("productName");
		String productCode = map.get("productCode");
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		String quantity = map.get("quantity");
		String personNumber = map.get("personNumber");
		String remark = map.get("remark");
		String createByText = map.get("createByText");//录入人erp账号
//		String rechargeBy = map.get("rechargeBy");
		String rechargeByName = map.get("rechargeByText");//负责人erp账号
		String isrepeat = map.get("isrepeat");
		StringBuffer errormsg = new StringBuffer();
		if(isrepeat!=null && isrepeat.equals("1")){   //有重复直接跳出，进入下一个循坏
			return null;
		}
		int flag = 0 ;
		if(userName!=null && !userName.equals("")){
			if(userName.length() <= 100){	
				order.setUserName(userName);
			}else{
				if (flag == 0) {
					errormsg.append("该行的用户名太长");
					flag = 1;
				} else {
					errormsg.append(",用户名太长");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的用户为空");
				flag = 1;
			} else {
				errormsg.append(",用户为空");
			}
		}
		
		if(orderChannelTemp!=null && !orderChannelTemp.equals("")){
			
		}else{
			if (flag == 0) {
				errormsg.append("该行的订单渠道为空");
				flag = 1;
			} else {
				errormsg.append(",订单渠道为空");
			}
		}
		
		if(StringUtils.isNotBlank(receiverAreaCode) && StringUtils.isNotBlank(productCode)){
				//101001001007
			String province = null;
			String provinceTemp = null;
			if(receiverAreaCode.length() > 6) {
				province = receiverAreaCode.substring(0,6);
//				city = receiverAreaCode.substring(0,9);
			}
			if(productCode.length() > 6) {
				provinceTemp = productCode.substring(0,6);
			}
			if(!StringUtils.equals(province, provinceTemp) || province == null || provinceTemp == null) {
				flag = 0;
				errormsg.append("订单城市（区县）与城市商品（商品名）不匹配，请更正后重新上传");
			}
			
		}
		if(productNameTemp!=null && !productNameTemp.equals("")){
			
		}else{
			if (flag == 0) {
				errormsg.append("该行的商品名为空");
				flag = 1;
			} else {
				errormsg.append(",商品名为空");
			}
		}
		
		if (userMobile != null && !userMobile.equals("")) {
			if(userMobile.getBytes().length <= 11 ){
				order.setReceiverMobile(userMobile);
				order.setUserMobile(userMobile);
			}else{
				if (flag == 0) {
					errormsg.append("该行的客户手机号太长");
					flag = 1;
				} else {
					errormsg.append(",客户手机号太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的客户手机号为空");
				flag = 1;
			} else {
				errormsg.append(",客户手机号为空");
			}
		}
		if (receiverProvince != null && !receiverProvince.equals("")) {
			if(receiverProvince.getBytes().length <= 100){
				order.setReceiverProvince(receiverProvince);
				order.setUserProvince(receiverProvince);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收人省份太长");
					flag = 1;
				} else {
					errormsg.append(",接收人省份太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收省份为空");
				flag = 1;
			} else {
				errormsg.append(",接收省份为空");
			}
		}
		
		if (receiverCity != null && !receiverCity.equals("")) {
			if(receiverCity.getBytes().length <= 100){
				order.setReceiverCity(receiverCity);
				order.setUserCity(receiverCity);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收人城市太长");
					flag = 1;
				} else {
					errormsg.append(",接收人城市太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收城市为空");
				flag = 1;
			} else {
				errormsg.append(",接收城市为空");
			}
		}
		if (receiverArea != null && !receiverArea.equals("")) {
			if(receiverArea.getBytes().length <= 100){
				order.setReceiverArea(receiverArea);
				order.setUserArea(receiverArea);
			}else{
				if (flag == 0) {
					errormsg.append("该行的接收地区太长");
					flag = 1;
				} else {
					errormsg.append(",接收地区太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的接收区域为空");
				flag = 1;
			} else {
				errormsg.append(",接收区域为空");
			}
		}
		
		if (receiverAddress != null && !receiverAddress.equals("")) {
			if(receiverAddress.getBytes().length <= 200){
				order.setReceiverAddress(receiverAddress);
				order.setUserAddress(receiverAddress);
			}else{
				/*if (flag == 0) {
					errormsg.append("该行的详细地址太长");
					flag = 1;
				} else {
					errormsg.append(",详细地址太长");
				}*/
			}
		} else {
			/*if (flag == 0) {
				errormsg.append("该行的详细地址为空");
				flag = 1;
			} else {
				errormsg.append(",详细地址为空");
			}*/
		}
		/*
		if(StringUtils.isNotBlank(createByText)) {
			
		}else {
			if(flag == 0) {
				errormsg.append("该行的录入人erp账号为空");
				flag = 1;
			} else {
				errormsg.append(",录入人erp账号为空");
			}
		}
		if(StringUtils.isNotBlank(rechargeByName)) {
//			order.setRechargeBy(Long.valueOf(rechargeByName));
		}else {
			if(flag == 0) {
				errormsg.append("该行的负责人erp账号为空");
				flag = 1;
			} else {
				errormsg.append(",负责人erp账号为空");
			}
		}
		 */
		
		if(flag==0){
			try {	
				Map<String,Object> userMap = bathService.getUserDetail(userMobile, userName, receiverProvince, receiverCity, receiverArea, receiverAddress);
				if(userMap!=null&&!userMap.isEmpty()){
					String longitude = userMap.get("LONGITUDE").toString();
					String latitude = userMap.get("LATITUDE").toString();
					String userId = userMap.get("ID").toString();
					order.setUserId(Long.parseLong(userId));
					if(longitude!=null&&latitude!=null&&!"".equals(longitude)&&!"".equals(latitude)){
						order.setLatitude(latitude);
						order.setLongitude(longitude);
					}
				}else{
					if (flag == 0) {
						errormsg.append("该行的用户信息不完整没有生成用户信息");
						flag = 1;
					} else {
						errormsg.append(",用户信息不完整没有生成用户信息");
					}
				}
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的用户信息不完整没有生成用户信息");
					flag = 1;
				} else {
					errormsg.append(",用户信息不完整没有生成用户信息");
				}
			}
		}
		
		if (quantity != null && !quantity.equals("")) {
			try {
				order.setQuantity(Float.valueOf(quantity));;
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的商品数量格式不正确");
					flag = 1;
				} else {
					errormsg.append(",商品数量格式不正确");
				}
			}
		}else {
			/*if (flag == 0) {
				errormsg.append("该行的商品数量为空");
				flag = 1;
			} else {
				errormsg.append(",商品数量为空");
			}*/
		}
		
		if (personNumber != null && !personNumber.equals("")) {
			try {
				order.setPersonNumber(Integer.valueOf(personNumber));
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的服务人数格式不正确");
					flag = 1;
				} else {
					errormsg.append(",服务人数格式不正确");
				}
			}
		}else{
			order.setPersonNumber(1);
		}
		
		
		if(productCode!=null&&!productCode.equals("")){
			str = bathService.queryIsSingleBatch(productCode);
//			if(map3 != null) {
//				str = map3.get("FA");
//			}
			if(StringUtils.equals("3", str)) {
				errormsg.append("该行的商品是FA商品,暂不支持导入");
				flag = 1;
			}
			try {
				Map<String,Object> productMap = bathService.getDetail(productCode, "20000002");
				if(productMap!=null&&!productMap.isEmpty()){
					String productImg = productMap.get("PRODUCT_IMG_MAIN").toString();
					String productUnit = productMap.get("LEAST_UNIT").toString();
					String productName = productMap.get("NAME").toString();
					String productPrice = productMap.get("PRICE").toString();
					String categoryCode = productMap.get("CATEGORY_CODE").toString();
					String productSpec = productMap.get("PRODUCT_SPEC").toString();
					String cityCode = productMap.get("CITY_CODE").toString();
					
					order.setProductImg(productImg);
					order.setProductUnit(productUnit);
					order.setProductCode(productCode);
					order.setProductName(productName);
					order.setProductPrice(new BigDecimal(productPrice));
					order.setCategoryCode(categoryCode);
					order.setProductSpec(productSpec);
					order.setCityCode(cityCode);
					order.setProductPriceType("20000002");
					BigDecimal totalPay = null;
					/*if(quantity != null && productPrice != null) {*/
					if(StringUtils.isNotEmpty(quantity) && StringUtils.isNotEmpty(productPrice)) {
						totalPay = new BigDecimal(quantity).multiply(new BigDecimal(productPrice));
					}
					order.setTotalPay(totalPay);
				}else{
					if (flag == 0) {
						errormsg.append("该行的商品编码有误");
						flag = 1;
					} else {
						errormsg.append(",商品编码有误");
					}
				}
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的商品编码有误");
					flag = 1;
				} else {
					errormsg.append(",商品编码有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的商品编码为空");
				flag = 1;
			} else {
				errormsg.append(",商品编码为空");
			}
		}
		
		if(orderChannelText!=null && !orderChannelText.equals("")){
			Long orderChannel = null;
			for (Map<String, Object> dc : dictionary) {
				if (dc.get("DICT_NAME").equals(orderChannelText)) {
					orderChannel = Long.valueOf(dc.get("DICT_CODE").toString());
					break;
				}
			}
			if(orderChannel != null){
				order.setOrderChannel(orderChannel);
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单渠道有误");
					flag = 1;
				} else {
					errormsg.append(",订单渠道有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的订单渠道为空");
				flag = 1;
			} else {
				errormsg.append(",订单渠道为空");
			}
		}
		
		if(rechargeByName!=null && !rechargeByName.equals("")){
			Long rechargerBy = null;
			Long rechargeDept = null;
			for (Map<String, Object> dc : authManager) {
				if (dc.get("MANAGER_NAME").equals(rechargeByName)) {
					rechargerBy = Long.valueOf(dc.get("MANAGER_ID").toString());
					rechargeDept = Long.valueOf(dc.get("ORG_ID").toString());
					break;
				}
			}
			if(rechargerBy != null){
				order.setRechargeBy(rechargerBy);
				order.setRechargeDept(rechargeDept);
			}else{
				if (flag == 0) {
					errormsg.append("该行的负责管家账号有误");
					flag = 1;
				} else {
					errormsg.append(",负责管家账号有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的负责管家账号有误");
				flag = 1;
			} else {
				errormsg.append(",负责管家账号有误");
			}
		}
		
		if(createByText!=null && !createByText.equals("")){
			Long rechargerBy = null;
			Long rechargeDept = null;
			for (Map<String, Object> dc : authManager) {
				System.out.println(dc.get("MANAGER_NAME"));  
				System.out.println(createByText); 
				if (dc.get("MANAGER_NAME").equals(createByText)) {
					rechargerBy = Long.valueOf(dc.get("MANAGER_ID").toString());
					rechargeDept = Long.valueOf(dc.get("ORG_ID").toString());
					break;
				}
			}
			if(rechargerBy != null){
				order.setCreateBy(rechargerBy);
				order.setCreateDept(rechargeDept);
			}else{
				if (flag == 0) {
					errormsg.append("该行的录入人erp账号有误");
					flag = 1;
				} else {
					errormsg.append(",负责录入人erp账号有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的录入人erp账号有误");
				flag = 1;
			} else {
				errormsg.append(",录入人erp账号有误");
			}
		}
		
		if (startTime != null && !startTime.equals("")) {
			
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(startTime);
				order.setStartTime(startTime);
				//order.setCreateTime(startTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的开始时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",开始时间格式不正确");
				}
				
			}
		} else {
			/*if (flag == 0) {
				errormsg.append("该行的开始时间为空");
				flag = 1;
			} else {
				errormsg.append(",开始时间为空");
			}*/
		}
		if (endTime != null && !endTime.equals("")) {
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(endTime);
				order.setEndTime(endTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的结束时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",结束时间格式不正确");
				}
			}
		} else {
			/*if (flag == 0) {
				errormsg.append("该行的结束时间为空");
				flag = 1;
			} else {
				errormsg.append(",结束时间为空");
			}*/
		}
		order.setRemark(remark);
		order.setOrderSourceId(20180007);
		order.setPayStatus("20110001");
//		order.setCateType("1");
		order.setCateType(str);
//		order.setCreateBy(createBy);
//		order.setCreateDept(createDept);
		order.setThreeOrderCode(orderCode);
		if(flag == 0){
			try {
				//add by weixd 追加日志 20180815
				order.setLog(order.getLog() + "--从excel导入的订单 ,订单编号【" + order.getOrderCode() + "】" + DateUtils.getCurrentDate());
				wrOrderMapper.saveOrderImport(order);
				if(null != order.getRechargeBy() || null != order.getRechargeDept()){
					OrderTurnLog orderTurnLog = new OrderTurnLog();
					orderTurnLog.setOrderId(order.getId());
					orderTurnLog.setRechargeBy(order.getRechargeBy());
					orderTurnLog.setRechargeDept(order.getRechargeDept());
					orderTurnLog.setRemark("初始记录");
					wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);//添加转单初始数据
				}
				wrOrderMapper.saveOrderImportItem(order);
				wrOrderMapper.saveOrderImportDetailServer(order);
			} catch (Exception e) {
				e.printStackTrace();
				map.put("success", "失败");
				map.put("errormsg", "数据导入数据库出现异常");
			}
			map.put("success", "成功");
			map.put("errormsg", "");
		}else{
			errormsg.append(";");
			map.put("success", "失败");
			map.put("errormsg", errormsg.toString());
		}
		
		map2.put("errormsg",errormsg.toString());
		return map2;
	}
	
	private Map<String, String> addProvinceAndCity(List<Map<String, Object>> proviceCityAreaMap,
			Map<String, String> map, String receiverAreaCode) {
		String province = null;
		String city = null;
		if(StringUtils.isNotBlank(receiverAreaCode)) {//101001001007
			if(receiverAreaCode.length() == 12) {
				province = receiverAreaCode.substring(0,6);
				city = receiverAreaCode.substring(0,9);
			}
		}
		if(CollectionUtils.isNotEmpty(proviceCityAreaMap)) {
			for (Map<String, Object> map2 : proviceCityAreaMap) {
				if(StringUtils.equals(province, (String) map2.get("CODE"))) {
					map.put("receiverProvince", (String) map2.get("NAME"));//省
				}
				if(StringUtils.equals(city, (String) map2.get("CODE"))) {
					map.put("receiverCity", (String) map2.get("NAME"));//市
				}
			}
			
		}
		return map;
	}

	public static void main1(String[] args) {
		String receiverAreaCode = "101001001007";
		System.out.println(receiverAreaCode.substring(6));
		System.out.println(receiverAreaCode.substring(9));
	}
	@Override
	public void updateSalaryStatus(Order order) {
		int returnValue = wrOrderMapper.updateSalaryStatus(order);
		if (returnValue != 1) {
			throw new BaseException("update fail!");
		}
		
	}
	
	@Override
	public List<Order> queryOrderByUserMobile(String userMobile) {
		return reOrderMapper.queryOrderByUserMobile(userMobile);
	}

	@Override
	public Map<String, Object> querySolutionResponsiblePerson(Long orderId) {
		return reOrderMapper.querySolutionResponsiblePerson(orderId);
	}
	
	@Override
	public boolean  queryPayCompareFeeSum(Long orderId) {
		boolean flag=false;
		Long count = reOrderMapper.queryPayCompareFeeSum(orderId);
		if (count>0) {
			//说明缴费金额，大于等于订单金额
			flag=true;
		}
		return flag;
	}

	@Override
	public boolean queryAgreenmentDate(Long orderId) {
		boolean flag=false;
		Long count = reOrderMapper.queryAgreenmentDate(orderId);
		if (count>0) {
			//大于0，说明时间在30天的范围内
			flag=true;
		}
		return flag;
	}

	@Override
	public Map<String, Object> queryOrderEcho(Long orderId) {
		Map<String, Object> queryOrderEcho = reOrderMapper.queryOrderEcho(orderId);
		return queryOrderEcho;
	}
}
