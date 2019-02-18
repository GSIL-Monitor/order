package com.emotte.order.order.service.impl;

import javax.annotation.Resource; 

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderSlice;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.model.PersonaLevel;
import com.emotte.order.order.model.PersonnelSchedule;
import com.emotte.order.order.model.ProductDivide;
import com.emotte.order.order.model.ProductThirdDivide;
import com.emotte.dubbo.order.PersonnelScheduleService;
import com.emotte.eclient.EClientContext;
import com.emotte.interf.EScheduleService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.order.mapper.reader.ReItemDetailServerMapper;
import com.emotte.order.order.mapper.reader.ReItemMapper;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.reader.ReProductDivideMapper;
import com.emotte.order.order.mapper.reader.ReProductThirdDivideMapper;
import com.emotte.order.order.mapper.writer.WrItemDetailServerMapper;
import com.emotte.order.order.mapper.writer.WrItemInterviewMapper;
import com.emotte.order.order.mapper.writer.WrItemMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.mapper.writer.WrOrderSliceMapper;
import com.emotte.order.order.mapper.writer.WrPersonnelScheduleMapper;
import com.emotte.order.order.service.ItemDetailServerService;
import com.emotte.order.order.service.ItemService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Tools;

import net.sf.json.JSONObject;

@Service("itemDetailServerService")
@Transactional
public class ItemDetailServerServiceImpl implements ItemDetailServerService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReItemDetailServerMapper reItemDetailServerMapper;
	@Resource
	private WrItemDetailServerMapper wrItemDetailServerMapper;
	@Resource
	private OrderService orderService;
	@Resource
	private ItemService itemService;
	@Resource
	private ReOrderMapper reOrderMapper;
	@Resource
	private WrOrderMapper wrOrderMapper;
	@Resource
	private WrItemMapper wrItemMapper;
	@Resource
	private WrItemInterviewMapper wrItemInterviewMapper;
	@Resource
	private ReItemMapper reItemMapper;
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private ReProductDivideMapper reProductDivideMapper;
	@Resource
	private ReProductThirdDivideMapper reProductThirdDivideMapper;
	@Resource
	private WrOrderSliceMapper wrOrderSliceMapper;
	@Resource
	private PersonnelScheduleService personnelScheduleService;
	@Resource
	private WrPersonnelScheduleMapper wrPersonnelScheduleMapper;
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ItemDetailServer loadItemDetailServer(Long id) {
		return this.reItemDetailServerMapper.loadItemDetailServer(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemDetailServer> queryItemDetailServer(ItemDetailServer itemDetailServer, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reItemDetailServerMapper.countItemDetailServer(itemDetailServer));
		}
		itemDetailServer.setBeginRow(page.getFilterRecord().toString());
		itemDetailServer.setPageSize(page.getPageCount().toString());
		return this.reItemDetailServerMapper.queryItemDetailServer(itemDetailServer);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertItemDetailServer(ItemDetailServer itemDetailServer) {
		// DateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// 淇濆瓨涓昏鍗�
		Order order = new Order();
//		Map map = LngAndLatUtil.getLngAndLat(itemDetailServer.getAddress());
//		order.setLongitude(map.get("longitude") == null ? "" : map.get("longitude").toString());
//		order.setLatitude(map.get("latitude") == null ? "" : map.get("latitude").toString());
		order.setCreateBy(itemDetailServer.getCreateBy());
		order.setCreateDept(itemDetailServer.getDeptId());
		order.setOrderSourceId("20180005");
		order.setUserId(itemDetailServer.getUserId());
		order.setReceiverId(itemDetailServer.getReceiverId());
		order.setCritical(itemDetailServer.getCritical());
		order.setCateType(itemDetailServer.getCateType());
		order.setOrderType(itemDetailServer.getServerType());
		order.setReceiverProvince(itemDetailServer.getProvince());
		order.setReceiverCity(itemDetailServer.getCity());
		order.setReceiverArea(itemDetailServer.getCountry());
		order.setReceiverCityCode(itemDetailServer.getCityCode());
		order.setCity(itemDetailServer.getCityCode());
		order.setReceiverAddress(itemDetailServer.getAddress());
		order.setLongitude(itemDetailServer.getLongitude());
		order.setLatitude(itemDetailServer.getLatitude());
		order.setPriceType(itemDetailServer.getPriceType());
		order.setOrderChannel(itemDetailServer.getOrderChannel());
		order.setRemark(itemDetailServer.getRemark());
		order.setEmp_id(itemDetailServer.getEmp_id());
		order.setThreeOrderCode(itemDetailServer.getThreeOrderCode());
		order.setServiceObject(itemDetailServer.getServiceObject());
		//获得负责部门和负责人
		order.setRechargeDept(itemDetailServer.getRechargeDept());
		order.setRechargeBy(itemDetailServer.getRechargeBy());
		if(null != order.getRechargeDept() ){
			order.setOrderFenfa("1");
		}else{
			order.setOrderFenfa("2");
		}
		
		if(order.getCateType()==1||order.getCateType()==2){
			order.setOrderStatus(2);
		}else{
			order.setOrderStatus(1);
		}
		// 计算商品价格
		Item item = new Item();
		BigDecimal pay = new BigDecimal(0);
		if(null != itemDetailServer.getProductCode()){
			CityProduct cp = new CityProduct();
			cp.setDictCode(itemDetailServer.getPriceType());
			cp.setCityCode(order.getCity());
			cp.setProductCode(itemDetailServer.getProductCode());
			List<CityProduct> productList = this.reItemMapper.queryForDictPrice(cp);
			if(productList != null && productList.size() > 0){
				CityProduct cpt = productList.get(0);
				if (itemDetailServer.getServerType() != null && itemDetailServer.getServerType() != ""
						&& (itemDetailServer.getServerType().equals(CommonConstant.ORDER_DAILY_CLEANNING_CATEGORYCODE)//日常保洁
								|| itemDetailServer.getServerType().equals(CommonConstant.ORDER_CLEAN_THE_GLASS_CATEGORYCODE))) {//擦玻璃
					pay = pay.add(cpt.getMarketPrice().multiply(new BigDecimal(itemDetailServer.getPickQuantity()).multiply(new BigDecimal(itemDetailServer.getPersonNumber()))));
				}else {
					pay = pay.add(cpt.getMarketPrice().multiply(new BigDecimal(itemDetailServer.getPickQuantity())));
				}
				item.setNowPrice(cpt.getMarketPrice());
				item.setProductImg(cpt.getProductImg());
				item.setQuantity(itemDetailServer.getPickQuantity());
			}
		}
		if(order.getCateType()==2){
			order.setTotalPay(new BigDecimal(0));
		}else{
			order.setTotalPay(pay);
		}
		order.setProductCode(itemDetailServer.getProductCode());
		order = this.orderService.insertOrder(order);
		
		//单次服务减少库存
		itemDetailServer.setOrderId(order.getId());
		if(itemDetailServer.getCateType()==1&&!(itemDetailServer.getIfKunCun().equals(""))){
			orderService.reduceInventory(itemDetailServer.getCityCode(), itemDetailServer.getServerType(), itemDetailServer.getStartTime().substring(0,10), itemDetailServer.getTimeSlot().split("-")[0],
					itemDetailServer.getTimeSlot().split("-")[1],itemDetailServer.getPersonNumber(),itemDetailServer.getOrderId());
		}
		// 保存服务类商品
		item.setOrderId(order.getId());
		item.setCreateBy(itemDetailServer.getCreateBy());
		item.setVersion(0L);
		item.setProductCode(itemDetailServer.getProductCode());
		item.setProductName(itemDetailServer.getProductName());
		item.setQuantity(itemDetailServer.getPickQuantity());
		item.setSaleType("1");
		item.setCategoryCode(itemDetailServer.getServerType());
		item.setProductPriceType(itemDetailServer.getProductPriceType());
		item.setProductUnit(itemDetailServer.getProductUnit());
		item.setProductSpec(itemDetailServer.getProductSpec());
		
		Long itemId = this.itemService.insertItem(item);
		
		// 保存需求信息
		itemDetailServer.setOrderId(order.getId());
		itemDetailServer.setItemId(itemId);
		itemDetailServer.setVersion(0L);
		itemDetailServer.setValid(1);
		if(!"-".equals(itemDetailServer.getTimeSlot()) && itemDetailServer.getTimeSlot()!=null&&!(itemDetailServer.getTimeSlot().equals(""))){
			itemDetailServer.setTimeSlot(Tools.ChangeTime(itemDetailServer.getTimeSlot()));
		}
		itemDetailServer.setAddress(order.getReceiverProvince() +order.getReceiverCity() +order.getReceiverArea() +order.getReceiverAddress());
		this.wrItemDetailServerMapper.insertItemDetailServer(itemDetailServer);
		//添加订单排期
		itemDetailServer.setVersion(0L);
		itemDetailServer.setOrderId(order.getId());
		//自营单次和自营延续可以有排期
		if(order.getCateType()==1||order.getCateType()==2){
			if(order.getCateType()==1){
				itemDetailServer.setDays(itemDetailServer.getServiceHours());
				itemDetailServer.setHours(itemDetailServer.getTimeSlot());
				itemDetailServer.setLinedType(1);
			}else if(order.getCateType()==2){
				//钟点工
				if(itemDetailServer.getServerType().equals("100200040002")){
					itemDetailServer.setLinedType(2);
				}else{
					itemDetailServer.setLinedType(3);
				}
			}
			this.wrItemDetailServerMapper.insertOrderServerLined(itemDetailServer);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public String insertItemDetailServerForeign(Order order) {
//		Map map = LngAndLatUtil.getLngAndLat(order.getAddress());
//		order.setLongitude(map.get("longitude") == null ? "" : map.get("longitude").toString());
//		order.setLatitude(map.get("latitude") == null ? "" : map.get("latitude").toString());
		order.setVersion(0L);
		this.wrOrderMapper.insertOrder(order);
		Long orderId = order.getId();
		// 商品
		Item item = new Item();
		item.setOrderId(orderId);
		item.setVersion(0L);
		item.setProductCode(String.valueOf(order.getOrderType()));
		item.setProductName("");
		item.setQuantity(1f);
		item.setSaleType("1");
		Long itemId = this.itemService.insertItem(item);
		// 服务需求
		ItemDetailServer itemDetailServer = new ItemDetailServer();
		itemDetailServer.setOrderId(orderId);
		itemDetailServer.setItemId(itemId);
		itemDetailServer.setVersion(0L);
		itemDetailServer.setValid(1);
		itemDetailServer.setPickQuantity(1f);
		itemDetailServer.setAddress(order.getAddress());
		itemDetailServer.setStartTime(order.getStartTime());
		itemDetailServer.setEndTime(order.getEndTime());
		itemDetailServer.setInterviewTime(order.getInterviewTime());
		itemDetailServer.setInterviewAddress(order.getInterviewAddress());
		itemDetailServer.setRemark(order.getRemark());
		this.wrItemDetailServerMapper.insertItemDetailServer(itemDetailServer);
		
		// 保存上户表信息
		if(null != order.getListInterview() && !"".equals(order.getListInterview())){
			for (Object object : order.getListInterview()) {
				JSONObject jinterView=JSONObject.fromObject(object);
				Object ointerView = JSONObject.toBean(jinterView, ItemInterview.class);
				ItemInterview itemInterview = new ItemInterview();
				itemInterview = (ItemInterview)ointerView;
				itemInterview.setOrderId(orderId);
				itemInterview.setVersion((long) 1);
				itemInterview.setValid(1);
				itemInterview.setOrderItemId(itemDetailServer.getId());
				this.wrItemInterviewMapper.insertItemInterview(itemInterview);
			}
		}
		
		return String.valueOf(orderId);

	}
	
	@Override
	public void updateItemDetailServer(ItemDetailServer itemDetailServer) {
		int returnValue;
		try {
			Order order = new Order();
			order.setId(itemDetailServer.getOrderId());
			order.setUserName(itemDetailServer.getUserName());
			OrderUser user = new OrderUser();
			if(itemDetailServer.getReceiverId()!=null && itemDetailServer.getReceiverId()>0){
				// 取用户信息
				user.setUsValid(1);
				user.setAdValid(1);
				user.setId(itemDetailServer.getReceiverId());
				user.setUserId(itemDetailServer.getUserId());
				user = this.reOrderBaseMapper.querLoadUser(user);
				
				// 接收人
				order.setReceiverName(user.getContactName());
				order.setReceiverMobile(user.getContactPhone());
				order.setReceiverSex(user.getSex());
				order.setReceiverBirth(user.getBirth());
				order.setReceiverProvince(user.getProvince());
				order.setReceiverCity(user.getCity());
				order.setReceiverArea(user.getCountry());
				order.setReceiverCityCode(user.getCityCode());
				order.setReceiverAddress(user.getAddressChoose()+user.getAddressDetail());
				//order.setReceiverAddress(user.getAddressChoose() +user.getAddressDetail());
				order.setLongitude(user.getLongitude());
				order.setLatitude(user.getLatitude());
				order.setReceiverCityCode(user.getCityCode());
			}
			order.setUpdateBy(itemDetailServer.getUpdateBy());
			order.setPriceType(itemDetailServer.getPriceType());
			order.setRemark(itemDetailServer.getRemark());
			//计算商品价格
			BigDecimal pay = new BigDecimal(0);
			if(itemDetailServer.getNowPrice() !=null && itemDetailServer.getPickQuantity()>0 ){
					pay = pay.add(itemDetailServer.getNowPrice().multiply(new BigDecimal(itemDetailServer.getPickQuantity())));
			}
			if(itemDetailServer.getCateType()==2){
				order.setTotalPay(new BigDecimal(0));
			}else{
				order.setTotalPay(pay);
			}
			orderService.updateOrder2(order);
			Item item = new Item();
			item.setOrderId(itemDetailServer.getOrderId());
			//按订单id查询item
			List<Item> itemList = this.itemService.queryAllItem(item);
			if(itemDetailServer.getCateType() != 3){
				for ( Item i : itemList) {
					i.setUpdateBy(itemDetailServer.getUpdateBy());
					i.setProductCode(itemDetailServer.getProductCode());
					i.setProductName(itemDetailServer.getProductName());
					i.setNowPrice(itemDetailServer.getNowPrice());
					i.setQuantity(itemDetailServer.getPickQuantity());
					i.setProductPriceType(itemDetailServer.getPriceType());
					this.itemService.updateItem(i);
				}
			}
			// 修改服务地址
			if(itemDetailServer.getReceiverId()!=null && itemDetailServer.getReceiverId()>0){
				itemDetailServer.setAddress(user.getProvince()+user.getCity()+user.getCountry()+user.getAddressChoose()+user.getAddressDetail());
			}
			//单次修改时间段类型
			if(itemDetailServer.getTimeSlot()!=null){
				itemDetailServer.setTimeSlot(Tools.ChangeTime(itemDetailServer.getTimeSlot()));
			}
			returnValue = this.wrItemDetailServerMapper.updateItemDetailServer(itemDetailServer);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateItemDetailServer:" + e);
			throw new BaseException(e);
		}
	}
	@Override
	public void updateItemDetailServerType(ItemDetailServer itemDetailServer) {
		try {
			Order order = new Order();
			order.setOrderType(itemDetailServer.getProductUnit());
			order.setId(itemDetailServer.getOrderId());
			orderService.updateOrder2(order);
			Item item = new Item();
			item.setOrderId(itemDetailServer.getOrderId());
			//按订单id查询item
			List<Item> itemList = this.itemService.queryAllItem(item);
				for ( Item i : itemList) {
					i.setUpdateBy(itemDetailServer.getUpdateBy());
					i.setCategoryCode(itemDetailServer.getProductUnit());
					i.setProductCode(itemDetailServer.getProductCode());
					i.setProductName(itemDetailServer.getProductName());
					i.setNowPrice(itemDetailServer.getNowPrice());
					i.setQuantity(itemDetailServer.getPickQuantity());
					i.setProductSpec(itemDetailServer.getProductSpec());
					  wrItemMapper.updateItemtype(i);
				}
		} catch (Exception e) {
			log.error("updateItemDetailServer:" + e);
			throw new BaseException(e);
		}
	}
	
	/**
	 * *********************************  鎺掓湡鍔熻兘    ***************
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ItemDetailServer loadOrderServerLined(Long id) {
		ItemDetailServer itemDetailServer =this.reItemDetailServerMapper.loadOrderServerLined(id);
		String maxString = null;
		if(itemDetailServer != null && itemDetailServer.getHours()!=null&&!(itemDetailServer.getHours().equals(""))){
			for(int i=0;i<=itemDetailServer.getHours().split(",").length-1;i++){
				if(i==itemDetailServer.getHours().split(",").length-1){
					maxString=itemDetailServer.getHours().split(",")[i];
				}
			}
			if(maxString.split(":")[1].equals("00")){
				maxString=maxString.split(":")[0]+":"+"30";
			}else{
				maxString=String.valueOf((Long.valueOf(maxString.split(":")[0])+1))+":"+"00";
			}
			itemDetailServer.setHours(itemDetailServer.getHours().split(",")[0]+'-'+maxString);
		}
		return  itemDetailServer;
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemDetailServer> queryOrderServerLined(ItemDetailServer itemDetailServer) {
		List<ItemDetailServer> list=this.reItemDetailServerMapper.queryOrderServerLined(itemDetailServer);
		String maxString = null;
		for (ItemDetailServer itemDetailServer2 : list) {
			if(itemDetailServer2.getHours()!=null&&!(itemDetailServer2.getHours().equals(""))){
				for(int i=0;i<=itemDetailServer2.getHours().split(",").length-1;i++){
					if(i==itemDetailServer2.getHours().split(",").length-1){
						maxString=itemDetailServer2.getHours().split(",")[i];
					}
				}
				if(maxString.split(":")[1].equals("00")){
					maxString=maxString.split(":")[0]+":"+"30";
				}else{
					maxString=String.valueOf((Long.valueOf(maxString.split(":")[0])+1))+":"+"00";
				}
				itemDetailServer2.setHours(itemDetailServer2.getHours().split(",")[0]+'-'+maxString);
			}
		}
		return list;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertOrderServerLined(ItemDetailServer itemDetailServer) {
		ItemDetailServer Serverlist = new ItemDetailServer();
		Serverlist.setOrderId(itemDetailServer.getOrderId());
		List<ItemDetailServer> listDetail = this.reItemDetailServerMapper.queryOrderServerLined(Serverlist);
		itemDetailServer.setVersion(0L);
		String hours = "";
		if(itemDetailServer.getLinedType() == 2 || itemDetailServer.getLinedType() == 1){
			hours = itemDetailServer.getHours();
			itemDetailServer.setHours(Tools.ChangeTime(itemDetailServer.getHours()));
		}
		this.wrItemDetailServerMapper.insertOrderServerLined(itemDetailServer);
		Serverlist.setOrderId(itemDetailServer.getOrderId());
		Serverlist.setStartTime(itemDetailServer.getStartTime());
		Serverlist.setEndTime(itemDetailServer.getEndTime());
		this.wrItemDetailServerMapper.insertUpdateOrderDetailServer(Serverlist);
		if(listDetail.size() <= 0){
			ItemDetailServer itemDetailServers = new ItemDetailServer();
			itemDetailServers.setOrderId(itemDetailServer.getOrderId());
			List<ItemDetailServer> list = this.reItemDetailServerMapper.queryOrderDetailServer(itemDetailServers);
			if(list.size() > 0){
				for (int i = 0; i < list.size(); i++) {
					EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
					String startDate=itemDetailServer.getStartTime().replace(" ", "").replace("-", "").replace(":", "");
					String endDate=itemDetailServer.getEndTime().replace(" ", "").replace("-", "").replace(":", "");
					startDate = startDate.replace(" ", "").replace("-", "").replace(":", "");
					endDate = endDate.replace(" ", "").replace("-", "").replace(":", "");
					String startDateLeft = com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itemDetailServer.getStartTime()), -7));
					String endDateLeft = com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itemDetailServer.getStartTime()), -1));
					startDateLeft = startDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
					endDateLeft = endDateLeft.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
					String startDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itemDetailServer.getEndTime()), 1));
					String endDateRight=com.emotte.order.util.DateUtil.dateToString(com.emotte.order.util.DateUtil .addDay(com.emotte.order.util.DateUtil.stringDayToDate(itemDetailServer.getEndTime()), 7));
					startDateRight = startDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
					endDateRight = endDateRight.replace(" ", "").replace("-", "").replace(":", "").substring(0, 8);
					String json = "";
					if(itemDetailServer.getLinedType() == 2){
						String startTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[0]; //时间段转换
						String endTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[1];
						String weeks = itemDetailServer.getWeeks();
						weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
						weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
						             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
						             .replaceAll("天", "7");
						
						String json1 = "{\"startDate\":" +startDate.substring(0, 8)
						+",\"endDate\":" +endDate.substring(0,8) 
						+",\"status\":" +4 
						+",\"startTime\":" +startTime 
						+",\"endTime\":" +endTime
						+",\"weekday\":\"" +weeks+"\""
						+",\"empId\":" +list.get(i).getEmp_id() 
						+",\"orderId\":" +itemDetailServer.getOrderId() 
						+"}";
						json= "{\"startDate\":" +startDateLeft
								+",\"endDate\":" +endDateLeft
								+",\"startTime\":" +startTime
								+",\"endTime\":" +endTime
								+",\"weekday\":\"" +weeks+"\""
								+",\"orderId\":" +itemDetailServer.getOrderId()
								+",\"status\":" +2 +",\"empId\":\"" +list.get(i).getEmp_id() +"\"},"
								+json1
								+",{\"startDate\":" +startDateRight
								+",\"endDate\":" +endDateRight
								+",\"startTime\":" +startTime
								+",\"endTime\":" +endTime
								+",\"weekday\":\"" +weeks+"\""
								+",\"orderId\":" +itemDetailServer.getOrderId()
								+",\"status\":" +2 +",\"empId\":\"" +list.get(i).getEmp_id() +"\"}";
					}else if(itemDetailServer.getLinedType() == 3){
						String json1 = "{\"startDate\":" +startDate.substring(0, 8)
						+",\"endDate\":" +endDate.substring(0,8) 
						+",\"status\":" +4 
						+",\"empId\":" +list.get(i).getEmp_id() 
						+",\"orderId\":" +itemDetailServer.getOrderId() 
						+"}";
						json= "{\"startDate\":" +startDateLeft
								+",\"endDate\":" +endDateLeft
								+",\"orderId\":" +itemDetailServer.getOrderId()
								+",\"status\":" +2 +",\"empId\":\"" +list.get(i).getEmp_id() +"\"},"
								+json1
								+",{\"startDate\":" +startDateRight
								+",\"endDate\":" +endDateRight
								+",\"orderId\":" +itemDetailServer.getOrderId()
								+",\"status\":" +2 +",\"empId\":\"" +list.get(i).getEmp_id() +"\"}";
					}else if(itemDetailServer.getLinedType() == 1){
						String startTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[0]; //时间段转换
						String endTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[1];
						String startDates = itemDetailServer.getStartTime().replace(" ", "").replace("/", "").replace(":", "");
						json = "{\"startDate\":" +startDates.substring(0, 8) 
						+",\"endDate\":" +startDates.substring(0,8) 
						+",\"startTime\":" + startTime 
						+",\"endTime\":" + endTime
						+",\"status\":" +4 
						+",\"empId\":" +list.get(i).getEmp_id() 
						+",\"orderId\":" +itemDetailServer.getOrderId() 
						+"}";
					}
					System.out.println(json);
					String jsonStr = service.insertSchedule("[" +json +"]");
					JSONObject jsons = JSONObject.fromObject(jsonStr);
					boolean result = jsons.getBoolean("data");
					if(result == false){
						throw new BaseException("新增排期失败");
					}
				}
			}
		}
		/*//保姆类延续
		if(itemDetailServer.getWeeks()==null||itemDetailServer.getWeeks()==""){
		}else{
		//钟点工
			String[] weekTime=itemDetailServer.getSelectTime().split("<div>");
			for (int i = 1; i < weekTime.length; i++) {
				System.err.println(weekTime[i]);
				String[] type=weekTime[i].split(";</div>");
				itemDetailServer.setWeeks(type[0]);
				itemDetailServer.setHours(type[1]);
				System.err.println(itemDetailServer.getHours());
				this.wrItemDetailServerMapper.insertOrderServerLined(itemDetailServer);
			}
			
		}*/

	}

	@Override
	public void updateOrderServerLined(ItemDetailServer itemDetailServer) {
		String json=null;
		try {
			
			if(itemDetailServer.getHours()!=null&&!(itemDetailServer.getHours().equals(""))){			
				//单次服务接口订单排期时间显示
				if(null==itemDetailServer.getWeeks()||"".equals(itemDetailServer.getWeeks())){
					itemDetailServer.setEndTime(itemDetailServer.getStartTime());
					//itemDetailServer.setHours(Tools.ChangeTime(itemDetailServer.getHours()));
				}
			}
			if ((null==itemDetailServer.getHours()   || "".equals(itemDetailServer.getHours())) 
					&& itemDetailServer.getEndTime() != null && itemDetailServer.getLinedType() == 1) {
				String startTime = itemDetailServer.getStartTime().substring(10,16);
				String endTime = itemDetailServer.getEndTime().substring(10,16);
				String hours = startTime +"-"+endTime;
				itemDetailServer.setHours(Tools.ChangeTime(hours));
			}
			this.wrItemDetailServerMapper.updateOrderServerLined(itemDetailServer);
			itemDetailServer.setServiceHours(itemDetailServer.getDays());
			itemDetailServer.setTimeSlot(itemDetailServer.getHours());
			this.wrItemDetailServerMapper.updateItemDetailServerLine(itemDetailServer);
			
			List<PersonnelSchedule> personnelSchedules=reItemDetailServerMapper.selectPersonnelSchedules(itemDetailServer.getOrderId());
			
			boolean result=false;
			String startDate=itemDetailServer.getStartTime().substring(0,10).replace(" ", "").replace("/", "");
			System.err.println(startDate);
			String startTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[0]; //时间段转换
			String endTime=Tools.lindHours(itemDetailServer.getHours()).split("-")[1];
			EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
			
			for (PersonnelSchedule personnelSchedule : personnelSchedules) {
				//查询服务人员单次排期是否冲突
				String datas="{'personnelSchedule':{'empId':'"+personnelSchedule.getEmpId()+"','startDate':'"+startDate+"','endDate':'"+startDate+"','startTime':'"+startTime+"','endTime':'"+endTime+"'}}";
				System.err.println(datas);
				String result1 = this.personnelScheduleService.queryConflictSchedule(datas);
				JSONObject jsons1 = JSONObject.fromObject(result1);
				String code=jsons1.getString("code");
				if(code.equals("0")){
					String list=jsons1.getString("list");
					List<PersonnelSchedule> personnelScheduleList=com.alibaba.fastjson.JSONObject.parseArray(list,PersonnelSchedule.class);
					for (PersonnelSchedule personnelSchedule3 : personnelScheduleList) {
						//排除自己
						if(personnelSchedule3.getOrderId().equals(personnelSchedule.getOrderId())){
								//修改排期
								PersonnelSchedule personnelSchedule2 = new PersonnelSchedule();
								personnelSchedule2.setId(personnelSchedule.getId());
								personnelSchedule2.setStartDate(Long.valueOf(startDate.substring(0, 8)));
								personnelSchedule2.setEndDate(Long.valueOf(startDate.substring(0, 8)));
								personnelSchedule2.setStartTime(Long.valueOf(startTime));
								personnelSchedule2.setEndTime(Long.valueOf(endTime));
								personnelSchedule2.setEmpId(personnelSchedule.getEmpId());
								personnelSchedule2.setOrderId(personnelSchedule.getOrderId());
								wrPersonnelScheduleMapper.updatePersonnelSchedule(personnelSchedule2);
								LogHelper.info(getClass()+ ".updateOrderServerLined()","排期是否更新成功："+result);
						}else{
							throw new BaseException(jsons1.toString());
						}
					}
				}else if(code.equals("2")){
							//修改排期
							PersonnelSchedule personnelSchedule2 = new PersonnelSchedule();
							personnelSchedule2.setId(personnelSchedule.getId());
							personnelSchedule2.setStartDate(Long.valueOf(startDate.substring(0, 8)));
							personnelSchedule2.setEndDate(Long.valueOf(startDate.substring(0, 8)));
							personnelSchedule2.setStartTime(Long.valueOf(startTime));
							personnelSchedule2.setEndTime(Long.valueOf(endTime));
							personnelSchedule2.setEmpId(personnelSchedule.getEmpId());
							personnelSchedule2.setOrderId(personnelSchedule.getOrderId());
							wrPersonnelScheduleMapper.updatePersonnelSchedule(personnelSchedule2);
							LogHelper.info(getClass()+ ".updateOrderServerLined()","排期是否更新成功："+result);
						}else{
							throw new BaseException(jsons1.toString());
						}
					}
		} catch (Exception e) {
			log.error("updateOrderServerLined():" + e);
			throw new BaseException(e.getMessage());
		}
		
			/*//保姆类延续
			if(itemDetailServer.getSelectTime()==null||itemDetailServer.getSelectTime()==""){
			}else{
			//钟点工
				itemDetailServer.setValid(2);
				System.err.println(itemDetailServer.getOrderId());
				this.wrItemDetailServerMapper.updateOrderServerLinedNew(itemDetailServer);
				
				String[] weekTime=itemDetailServer.getSelectTime().split("<div>");
				for (int i = 1; i < weekTime.length; i++) {
					System.err.println(weekTime[i]);
					String[] type=weekTime[i].split(";</div>");
					itemDetailServer.setWeeks(type[0]);
					itemDetailServer.setHours(type[1]);
					System.err.println(itemDetailServer.getHours());
					this.wrItemDetailServerMapper.insertOrderServerLined(itemDetailServer);
				}
			}*/
	}

	// 工种等级
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryWorkTypeLevel(Dictionary dictionary){
		StringBuffer jsonstr = new StringBuffer("[{");
		List<Dictionary> list = this.reItemDetailServerMapper.queryWorkTypeLevel(dictionary);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getId()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getLevel()+"'");
				jsonstr.append("}");
			}
		}
		jsonstr.append("]");
		return jsonstr;
	}
	// 工种
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryWorkTypeAll(Dictionary dictionary){
		StringBuffer jsonstr = new StringBuffer("[{");
		List<Dictionary> list = this.reItemDetailServerMapper.queryWorkTypeAll(dictionary);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getId()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
		}
		jsonstr.append("]");
		return jsonstr;
	}
	//保存他营和自营单次生成订单时的分成依据
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertServerOneSlice(Long orderId,Long createBy) {
		//根据订单id查询订单信息
		Order order = this.reOrderMapper.loadOrder(orderId);
		//根据订单id查询商品表
		Item item = this.reItemMapper.loadItemByOrderId(orderId);
		String productCode = item.getProductCode();
		String productPriceType = item.getProductPriceType();
		//根据商品编码和商品价格体系，查询关联表id
		CityProduct cityProduct = new CityProduct();
		cityProduct.setProductCode(productCode);
		cityProduct.setProductPriceType(productPriceType);
		if (order.getCateType() == 1 && order.getPriceType() != null) {//自营
			cityProduct = this.reItemMapper.queryItemByCodeAndType(cityProduct);
			if (cityProduct != null) {
				ProductDivide productdivide = new ProductDivide();
				productdivide.setProductDictPriceId(cityProduct.getId());
				//查询自营分成表
				List<ProductDivide> dividesList = this.reProductDivideMapper.queryProductDivide(productdivide);
				if (dividesList != null && !dividesList.isEmpty()) {
					for (ProductDivide proDivide : dividesList) {
						OrderSlice oSlice = new OrderSlice();
						oSlice.setOrderId(order.getId());
						oSlice.setIsOther(2);//二次分账分层
						oSlice.setIsDefault(2);//非默认
						oSlice.setDivideId(proDivide.getId());//分成信息表id
						oSlice.setCreateBy(createBy);
						this.wrOrderSliceMapper.insertOrderSlice(oSlice);
					}
				}
			}
		}else if (order.getCateType() == 4  && order.getPriceType() != null) {//他营
			cityProduct = this.reItemMapper.queryItemByCodeAndType(cityProduct);
			if (cityProduct != null) {
				ProductThirdDivide productThirdDivide = new ProductThirdDivide();
				productThirdDivide.setProductDictPriceId(cityProduct.getId());
				//查询他营分成表
				List<ProductThirdDivide> thirdDividesList = this.reProductThirdDivideMapper.queryProductThirdDivide(productThirdDivide);
				if (thirdDividesList != null && !thirdDividesList.isEmpty()) {
					for (ProductThirdDivide proThirdDivide : thirdDividesList) {
						OrderSlice oSlice = new OrderSlice();
						oSlice.setOrderId(order.getId());
						oSlice.setIsOther(1);//三方合作分层
						oSlice.setIsDefault(2);//非默认
						oSlice.setDivideId(proThirdDivide.getId());//分成信息表id
						oSlice.setCreateBy(createBy);
						this.wrOrderSliceMapper.insertOrderSlice(oSlice);
					}
				}
			}
		}
	}

	@Override
	public List<ItemDetailServer> queryPersonSchedule(ItemDetailServer itemDetailServer) {
		//钟点工和单次服务
		if(itemDetailServer.getUpdateTime()!=null){
			itemDetailServer.setStartLineTime(Tools.lindHoursNew(itemDetailServer.getUpdateTime()).split("-")[0]);
			itemDetailServer.setEndLineTime(Tools.lindHoursNew(itemDetailServer.getUpdateTime()).split("-")[1]);
		}
		if(itemDetailServer.getWeeks()!=null){
			String weeks= itemDetailServer.getWeeks();
			System.out.println(weeks);
			weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
			weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
			             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
			             .replaceAll("天", "7");
			System.err.println(weeks);
			itemDetailServer.setWeeky(weeks.split(","));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
		Calendar Starttime = Calendar.getInstance(); 
		Calendar Endtime = Calendar.getInstance(); 
		Date Enddate = null;
		Date Startdate = null;
		try {
			Startdate = sdff.parse(sdff.format(sdf.parse(itemDetailServer.getStartDate())));
			Starttime.setTime(Startdate);
			Starttime.add(Calendar.DATE,-7);
			itemDetailServer.setStartDate(sdff.format(Starttime.getTime()).replace("-", ""));
			Enddate = sdff.parse(sdff.format(sdf.parse(itemDetailServer.getEndDate())));
			Endtime.setTime(Enddate);
			Endtime.add(Calendar.DATE,7);
			itemDetailServer.setEndDate(sdff.format(Endtime.getTime()).replace("-", ""));
			System.out.println(sdff.format(Starttime.getTime()).replace("-", ""));
			System.out.println(sdff.format(Endtime.getTime()).replace("-", ""));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		itemDetailServer.setPersonId(itemDetailServer.getBirthDate().split(","));
		return reItemDetailServerMapper.queryPersonSchedule(itemDetailServer);
	}
    /**
     * 查询服务人员库存数量
     * @date:2017年7月21日
     * @author YUNNA
     */
	@Override
	public int checkNumStock(Dictionary dictionary) {
		
		return this.reItemDetailServerMapper.checkNumStock(dictionary);
	}
    
	/**
	 * 查询服务人员排期
	 * @Date 2017年08月23日
	 * @param Long id
	 * @return 
	 * @author YUNNA
	 */
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<ItemDetailServer> loadPersonServerLined(ItemDetailServer itemDetailServers) {	
		List<ItemDetailServer> list =this.reItemDetailServerMapper.loadPersonServerLined(itemDetailServers);
		return list;
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date startDate = sdf.parse("20171101");
		System.out.println(startDate.getTime() > (System.currentTimeMillis() + 7 * 24 * 3600 * 1000));
		System.out.println((System.currentTimeMillis() + 7 * 24 * 3600));
		System.out.println(startDate.getTime());
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public OrderBaseModel matchingPersonnel(OrderBaseModel orderBaseModel) {
		if(orderBaseModel.getOrderId()!=null){
			OrderBaseModel orderBaseModel2 = this.reOrderBaseMapper.queryOrdertypeLevelWorkTypeId(orderBaseModel.getOrderId());
			orderBaseModel.setOrderType(orderBaseModel2.getOrderType());
			orderBaseModel.setGrade(orderBaseModel2.getGrade());
		}
		// 手动匹配条件查询
		System.err.println(orderBaseModel.getTimeslot());
		ItemInterview itemInterview = new ItemInterview();
		itemInterview.setOrderId(orderBaseModel.getOrderId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd");
		if(orderBaseModel.getLinedType() != 1){
			List<ItemInterview> lists = this.reOrderBaseMapper.queryOrderItemInterview(itemInterview);//查询订单是否有下户人员
			boolean flag = false;
			try {
				Date startDate = sdf.parse(orderBaseModel.getStartTime()); // 判断所选时间是否大于当前时间+7天，如果大于则+冗余时间，原则就是距离比较近的排期不需要冗余时间
				flag = startDate.getTime() > (System.currentTimeMillis() + 7 * 24 * 3600 * 1000);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			//判断是否有下户
			if(lists.size() == 0 && flag){
				//没有下户加冗余,g
				try {
					Date Enddate = null;
					Date Startdate = null;
					Calendar Starttime = Calendar.getInstance(); 
					Calendar Endtime = Calendar.getInstance(); 
					Startdate = sdff.parse(sdff.format(sdf.parse(orderBaseModel.getStartTime())));
					Starttime.setTime(Startdate);
					//Starttime.add(Calendar.DATE,-7);
					orderBaseModel.setStartTime(sdff.format(Starttime.getTime()).replace("-", ""));
					Enddate = sdff.parse(sdff.format(sdf.parse(orderBaseModel.getEndTime())));
					Endtime.setTime(Enddate);
					//Endtime.add(Calendar.DATE,7);
					orderBaseModel.setEndTime(sdff.format(Endtime.getTime()).replace("-", ""));
					System.out.println(sdff.format(Starttime.getTime()).replace("-", ""));
					System.out.println(sdff.format(Endtime.getTime()).replace("-", ""));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				//有下户不加冗余
				if (lists !=null && lists.size() > 0) {
					Date endTimedate = null;
					Calendar Endtimes = Calendar.getInstance();
					String listendTime = lists.get(0).getEndTime();
					String endTime = listendTime.replace("-", "");
					int result = orderBaseModel.getStartTime().compareTo(endTime);//对比时间
					try {
						endTimedate = sdff.parse(listendTime);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					Endtimes.setTime(endTimedate);
					Endtimes.add(Calendar.DATE,1);
					if (result <= 0){
						orderBaseModel.setStartTime(sdff.format(Endtimes.getTime()).replace("-", ""));
					}
				}
			}
		}
		
		if(orderBaseModel.getTimeslot()!="1"&&!(orderBaseModel.getTimeslot().equals("1"))){
			orderBaseModel.setStartTimeSolt(orderBaseModel.getTimeslot().split("-")[0].replace(":", "")+"00");
			orderBaseModel.setEndTimeSolt(orderBaseModel.getTimeslot().split("-")[1].replace(":", "")+"00");
			if(orderBaseModel.getWeek() != ("") && orderBaseModel.getWeek() != null){
				String weeks = orderBaseModel.getWeek();
				weeks = weeks.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
				weeks = weeks.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
				             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
				             .replaceAll("天", "7");
				orderBaseModel.setWeek(weeks+",0");
			}
		}
		return this.reOrderBaseMapper.matchingPersonnel(orderBaseModel);
	}
	

//查询时间
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public ItemDetailServer showTime(Long id) {
		ItemDetailServer itemDetailServer =this.reItemDetailServerMapper.showTime(id);
		return itemDetailServer;
	}
	//在修改时间段是先删除在修改
	@Override
	public String deleteSchedule(Long orId) {
		String str="";
		int num=this.wrItemDetailServerMapper.deleteSchedule(orId);
		if(num>0){
			str="成功";
		}else{
			str="失败";
		}
		 return str;
	}
	//查询服务人员排期
	@Override
	public List<ItemDetailServer> queryPersonLineTime(ItemDetailServer itemDetailServers) {
		List<ItemDetailServer> list =this.reItemDetailServerMapper.queryPersonLineTime(itemDetailServers);
		System.err.println("lala");
		return list;
	}

		@Override
		public OrderBaseModel selectOrderWorkType(OrderBaseModel orderBaseModel) {
			OrderBaseModel	orderBaseModel2=reItemDetailServerMapper.selectOrderWorkType(orderBaseModel);
			return orderBaseModel2;
		}

		@Override
		public List<PersonaLevel> selectPersonnelLevel() {
			List<PersonaLevel> list=reItemDetailServerMapper.selectPersonnelLevel();
			System.out.println(list);
			return list;
		}

		@Override
		@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
		public List<BaseCity> queryCitys(BaseCity baseCity) {
			return this.reItemDetailServerMapper.queryCitys(baseCity);
		}
}
