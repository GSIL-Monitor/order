package com.emotte.order.order.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.mapper.reader.ReItemMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderCustomerMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderInMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderOutMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderProCategoryMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderInMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderItemInFaMapper;
import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
import com.emotte.order.order.model.ThreeOrderIn;
import com.emotte.order.order.model.ThreeOrderInFaItemModel;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.model.ThreeOrderInProductModel;
import com.emotte.order.order.model.ThreeOrderItemDetailServer;
import com.emotte.order.order.model.ThreeOrderUser;
import com.emotte.order.order.service.ItemService;
import com.emotte.order.order.service.ThreeOrderExtendedService;
import com.emotte.order.order.service.ThreeOrderFaService;
import com.emotte.order.order.service.ThreeOrderInService;
import com.emotte.order.order.service.ThreeOrderService;
import com.emotte.order.util.Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("threeOrderInService")
@Transactional
public class ThreeOrderInServiceImpl implements ThreeOrderInService {

	@Resource
	private ReThreeOrderInMapper reThreeOrderInMapper;
	@Resource
	private ReThreeOrderCustomerMapper re_t_o_c_Mapper;
	@Resource
	private ThreeOrderExtendedService extendedService;
	@Resource
	private WrThreeOrderInMapper wrThreeOrderInMapper;
	@Resource
	private WrThreeOrderItemInFaMapper wr_FaItemMapper;
	@Resource
	private ThreeOrderFaService faService;
	@Resource
	private ThreeOrderService threeOrderService;
	@Resource
	private ReThreeOrderProCategoryMapper re_o_t_p_c_mapper;
	@Resource
	private ReThreeOrderOutMapper reThreeOrderOutMapper;
	@Resource
	private ItemService itemService;
	@Resource
	private ReItemMapper reItemMapper;
	@Override
	public Order loadOrder(Long id) {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.loadThreeOrder(id);
	}

	@Override
	public List<Order> queryOrder(Order order, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reThreeOrderInMapper.countOrder(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreeOrderInMapper.queryOrder(order);
	}

	/*
	 * (non-Javadoc)
	 * @see com.emotte.order.order.service.ThreeOrderInService#loadThreeOrderId()
	 */
	@Override
	public List<String> loadThreeOrderId() {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.loadThreeOrderId();
	}


	@Override
	public Map<String, Object> checkList(List<Map<String, String>> list) {
		Map<String, Object> result = new HashMap<>();
		List<Order> orders = new ArrayList<Order>();
		StringBuffer errormsg = new StringBuffer();
		for (Map<String, String> map : list) {
			Order order = new Order();
			int flag = 0;// 字段是否正确 0 为正确，1为有错
			List<Map<String, Object>> dictionary = reThreeOrderInMapper.queryDictionary();
//			List<Map<String, Object>> addresses = reThreeOrderInMapper.queryBaseCity();
			List<Map<String, Object>> orderTypes = reThreeOrderInMapper.queryOrderType();
			String row = map.get("row");
			String orderCode = map.get("orderCode");
			String receiverName = map.get("receiverName");
			String receiverMobile = map.get("receiverMobile");
			String cateType = map.get("cateType");
			String orderType = map.get("orderType");
			String receiverProvince = map.get("receiverProvince");
			String receiverCity = map.get("receiverCity");
			String receiverArea = map.get("receiverArea");
			String receiverAddress = map.get("receiverAddress");
			String startTime = map.get("startTime");
			String endTime = map.get("endTime");
			String createTime = map.get("createTime");
			String totalPay = map.get("totalPay");
			String orderStatus = map.get("orderStatus");
			String remark = map.get("remark");
			if (orderCode != null && orderCode != "") {
				order.setThreeOrderCode(orderCode);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的订单编号为空");
					flag = 1;
				}
			}
			if (receiverName != null && receiverName != "") {
				order.setReceiverName(receiverName);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的收货人为空");
					flag = 1;
				} else {
					errormsg.append(",收货人为空");
				}
			}
			if (receiverMobile != null && receiverMobile != "") {
				order.setReceiverMobile(receiverMobile);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的收货人电话为空");
					flag = 1;
				} else {
					errormsg.append(",收货人电话为空");
				}
			}
			if (cateType != null && cateType != "") {
				Integer cateTypeId = null;
//				for (Map<String, Object> dc : dictionary) {
//					if (dc.get("DVALUE").equals(cateType)) {
//						cateTypeId = Integer.valueOf(dc.get("DKEY").toString());
//						break;
//					}
//				}
//				if (cateTypeId != null) {
//					order.setCateType(cateTypeId);
//				} else {
//					if (flag == 0) {
//						errormsg.append("第" + row + "行的订单类型错误");
//						flag = 1;
//					} else {
//						errormsg.append(",订单类型错误");
//					}
//				}
				if(cateType.trim().equals("自营单次")){
					cateTypeId=1;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("自营延续")){
					cateTypeId=2;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("自营商品")){
					cateTypeId=3;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("他营单次")){
					cateTypeId=4;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("预存卡")){
					cateTypeId=5;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("礼品卡")){
					cateTypeId=6;
					order.setCateType(cateTypeId);
				}else if(cateType.trim().equals("账户充值")){
					cateTypeId=7;
					order.setCateType(cateTypeId);
				}else{
					if (flag == 0) {
						errormsg.append("第" + row + "行的订单类型错误");
						flag = 1;
					} else {
						errormsg.append(",订单类型错误");
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的订单类型为空");
					flag = 1;
				} else {
					errormsg.append(",订单类型为空");
				}
			}
			if (orderType != null && orderType != "") {
				String orderTypeId = null;
				for (Map<String, Object> dc : orderTypes) {
					if (orderType.equals(dc.get("CNAME"))) {
						orderTypeId = dc.get("CODE").toString();
						break;
					}
				}

				if (orderTypeId != null) {
					order.setOrderType(orderTypeId);
					order.setServerText(orderType);
				} else {
					if (flag == 0) {
						errormsg.append("第" + row + "行的需求类型错误");
						flag = 1;
					} else {
						errormsg.append(",需求类型错误");
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的需求类型为空");
					flag = 1;
				} else {
					errormsg.append(",需求类型为空");
				}
			}
			if (receiverProvince != null && receiverProvince != "") {
				order.setReceiverProvince(receiverProvince);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的接收省份为空");
					flag = 1;
				} else {
					errormsg.append(",接收省份为空");
				}
			}
			if (receiverCity != null && receiverCity != "") {
				order.setReceiverCity(receiverCity);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的接收城市为空");
					flag = 1;
				} else {
					errormsg.append(",接收城市为空");
				}
			}
			if (receiverArea != null && receiverArea != "") {
				order.setReceiverArea(receiverArea);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的接收区域为空");
					flag = 1;
				} else {
					errormsg.append(",接收区域为空");
				}
			}
			if (receiverAddress != null && receiverAddress != "") {
				order.setReceiverAddress(receiverAddress);
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的详细地址为空");
					flag = 1;
				} else {
					errormsg.append(",详细地址为空");
				}
			}
			if (startTime != null && startTime != "") {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
					sdf.setLenient(false);
					sdf.parse(startTime);

					order.setStartTime(startTime);
				} catch (ParseException e) {
					// e.printStackTrace();
					// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
					if (flag == 0) {
						errormsg.append("第" + row + "行的开始时间格式不正确");
						flag = 1;
					} else {
						errormsg.append(",开始时间格式不正确");
					}

				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的开始时间为空");
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
						errormsg.append("第" + row + "行的结束时间格式不正确");
						flag = 1;
					} else {
						errormsg.append(",结束时间格式不正确");
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的结束时间为空");
					flag = 1;
				} else {
					errormsg.append(",结束时间为空");
				}
			}
			if (createTime != null && !createTime.equals("")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
					sdf.setLenient(false);
					sdf.parse(createTime);

					order.setCreateTime(createTime);

				} catch (ParseException e) {
					// e.printStackTrace();
					// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
					if (flag == 0) {
						errormsg.append("第" + row + "行的创建时间格式不正确");
						flag = 1;
					} else {
						errormsg.append(",创建时间格式不正确");
					}

				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的创建时间为空");
					flag = 1;
				} else {
					errormsg.append(",创建时间为空");
				}
			}
			if (totalPay != null && totalPay != "") {
				try {
					BigDecimal totalMoney = new BigDecimal(totalPay);

					order.setTotalPay(totalMoney);
				} catch (Exception e) {
					if (flag == 0) {
						errormsg.append("第" + row + "行的订单金额格式不正确");
						flag = 1;
					} else {
						errormsg.append(",创建订单金额不正确");
					}
				}
			}
			if (orderStatus != null && !orderStatus.equals("")) {
				Integer orderStatusId = null;
				for (Map<String, Object> dc : dictionary) {
					if (dc.get("DVALUE").equals(orderStatus)) {
						orderStatusId = Integer.valueOf(dc.get("DKEY").toString());
						break;
					}
				}
				if (orderStatusId != null) {
					order.setOrderStatus(orderStatusId);
				} else {
					if (flag == 0) {
						errormsg.append("第" + row + "行的订单状态错误");
						flag = 1;
					} else {
						errormsg.append(",订单状态错误");
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("第" + row + "行的订单状态为空");
					flag = 1;
				} else {
					errormsg.append(",订单状态为空");
				}
			}
			if (remark != null && remark != "") {
				order.setRemark(remark);
			}
			if (flag == 0) {
				order.setOrderChannel("7");
				orders.add(order);
			} else {
				errormsg.append(";");
			}
		}
		for (int i = 0; i < orders.size(); i++) {
			String orderCode1 = orders.get(i).getOrderCode();
			for (int j = i + 1; j < orders.size(); j++) {
				String orderCode2 = orders.get(j).getOrderCode();
				if (orderCode1.equals(orderCode2)) {
					if (errormsg == null || errormsg.equals("")) {
						errormsg.append("订单编号为" + orders.get(i) + "的数据有重复;");
						orders.remove(j);
						orders.remove(i);
					}
				}
			}
		}

		result.put("orderList", orders);
		result.put("errormsg", errormsg);
		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see com.emotte.order.order.service.ThreeOrderInService#saveThreeOrderIn()
	 */
	@Override
	public void saveThreeOrderIn(List<Order> orders) {
		wrThreeOrderInMapper.saveThreeOrderIn(orders);
	}

	@Override
	public void saveThreeOrderInItem(List<Order> orders) {
		wrThreeOrderInMapper.saveThreeOrderInItem(orders);
		
	}

	@Override
	public void saveThreeOrderInServerDetail(List<Order> orders) {
		wrThreeOrderInMapper.saveThreeOrderInServerDetail(orders);
		
	}

	@Override
	public void createId(List<Order> orders) {
		for (Order order : orders) {
			Long id = reThreeOrderInMapper.getRandomId();
			order.setId(id);
		}
	}

	@Override
	public void createItemId(List<Order> orders) {
		for (Order order : orders) {
			Long id = reThreeOrderInMapper.getRandomId();
			order.setThreeOrderItemId(id);
		}
	}

	@Override
	public List<ThreeOrderIn> exportToExcel(Order order) {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.exportList(order);
	}

	

	@Override
	@Transactional
	public void insertOrder(Map<String, String> map, Long createBy, List<Map<String, Object>> dictionary, List<Map<String, Object>> addresses, List<Map<String, Object>> orderTypes) throws Exception {
		ThreeOrderIn order = new ThreeOrderIn();
		String orderCode = map.get("orderCode");
		String cityName = map.get("cityName");
		String userName = map.get("userName");
		String userMobile = map.get("userMobile");
		String receiverName = map.get("receiverName");
		String receiverMobile = map.get("receiverMobile");
		String cateType = map.get("cateType");
		String orderType = map.get("orderType");
		String productCode = map.get("productCode");
		String receiverProvince = map.get("receiverProvince");
		String receiverCity = map.get("receiverCity");
		String receiverArea = map.get("receiverArea");
		String receiverAddress = map.get("receiverAddress");
		String productName = map.get("productName");
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		String sevicerName = map.get("sevicerName");
		String createTime = map.get("createTime");
		String postId = map.get("postId");
		String totalPay = map.get("totalPay");
		String orderStatus = map.get("orderStatus");
		String orderChannelText = map.get("orderChannelText");
		String remark = map.get("remark");
		String isrepeat = map.get("isrepeat");
		StringBuffer errormsg = new StringBuffer();
		if(isrepeat!=null && isrepeat.equals("1")){   //有重复直接跳出，进入下一个循坏
			return;
		}
		// TODO Auto-generated method stub
		int flag = 0;
		if (orderCode != null && !orderCode.equals("")) {
			if(orderCode.getBytes().length <= 20){
				order.setThreeOrderCode(orderCode);
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单编号太长");
					flag = 1;
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的订单编号为空");
				flag = 1;
			}
		}
		String cityCode = null;
		if(cityName != null && !cityName.equals("")){
			for (Map<String, Object> address : addresses) {
				if((cityName.equals(address.get("NAME")) || cityName.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 9){
					cityCode = address.get("CODE").toString();
					order.setCityCode(cityCode);
					break;
				}
			}
			if(cityCode == null){
				if (flag == 0) {
					errormsg.append("该行的城市有误");
					flag = 1;
				} else {
					errormsg.append(",城市有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的城市为空");
				flag = 1;
			} else {
				errormsg.append(",城市为空");
			}
		}
		if(userName!=null && !userName.equals("")){
			if(userName.getBytes().length <= 100){	
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
		if(userMobile !=null && !userMobile.equals("")){
			if(userMobile.getBytes().length <= 11){
				order.setUserMobile(userMobile);
			}else{
				if (flag == 0) {
					errormsg.append("该行的用户手机太长");
					flag = 1;
				} else {
					errormsg.append(",用户手机太长");
				}
			}
			
			
		}
		
		String provinceCode = null;
		if (receiverProvince != null && !receiverProvince.equals("")) {
			if(receiverProvince.getBytes().length <= 100){
				for (Map<String, Object> address : addresses) {
					if( (receiverProvince.equals(address.get("NAME")) || receiverProvince.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 6){
						provinceCode = address.get("CODE").toString();
						order.setReceiverProvinceCode(provinceCode);
						order.setReceiverProvince(receiverProvince);
						break;
					}
				}
				if(provinceCode == null){
					if (flag == 0) {
						errormsg.append("该行的接收人省份有误");
						flag = 1;
					} else {
						errormsg.append(",接收人省份有误");
					}
				}
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
		
		String receiverCityCode = null;
		if (receiverCity != null && !receiverCity.equals("")) {
			if(receiverCity.getBytes().length <= 100){
				for (Map<String, Object> address : addresses) {
					if( (receiverCity.equals(address.get("NAME")) || receiverCity.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 9){
						receiverCityCode = address.get("CODE").toString();
						order.setReceiverCityCode(receiverCityCode);
						order.setReceiverCity(receiverCity);
						break;
					}
				}
				if(receiverCityCode == null){
					if (flag == 0) {
						errormsg.append("该行的接收人城市有误");
						flag = 1;
					} else {
						errormsg.append(",接收人城市有误");
					}
				}
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
		//省份名称和城市名称与用户名、电话，同时验证，并保存
		if(userName != null && !userName.equals("")&& userName.getBytes().length <= 100 
				&& userMobile != null && !userMobile.equals("") &&userMobile.getBytes().length <= 11 
				&& cityCode !=null && !cityCode.equals("")){
			Long userId = reThreeOrderInMapper.findUserIdByUserMobile(userMobile);
			if(userId != null){
				order.setUserId(userId);
			}else{
				wrThreeOrderInMapper.saveUser(order);
			}
		}
		if (receiverName != null && !receiverName.equals("")) {
			if(receiverName.getBytes().length <= 100){
				order.setReceiverName(receiverName);
			}else{
				if (flag == 0) {
					errormsg.append("该行的收货人姓名太长");
					flag = 1;
				} else {
					errormsg.append(",收货人姓名太长");
				}
			}
		
		} else {
			if (flag == 0) {
				errormsg.append("该行的收货人为空");
				flag = 1;
			} else {
				errormsg.append(",收货人为空");
			}
		}
		if (receiverMobile != null && !receiverMobile.equals("")) {
			if(receiverMobile.getBytes().length <= 100 ){
				order.setReceiverMobile(receiverMobile);
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
				errormsg.append("该行的收货人电话为空");
				flag = 1;
			} else {
				errormsg.append(",收货人电话为空");
			}
		}
		if (cateType != null && !cateType.equals("")) {
			Integer cateTypeId = null;
//			for (Map<String, Object> dc : dictionary) {
//				if (dc.get("DVALUE").equals(cateType)) {
//					cateTypeId = Integer.valueOf(dc.get("DKEY").toString());
//					break;
//				}
//			}
//			if (cateTypeId != null) {
//				order.setCateType(cateTypeId);
//			} else {
//				if (flag == 0) {
//					errormsg.append("第" + row + "行的订单类型错误");
//					flag = 1;
//				} else {
//					errormsg.append(",订单类型错误");
//				}
//			}
			if(cateType.trim().equals("自营单次")){
				cateTypeId=1;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("自营延续")){
				cateTypeId=2;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("自营商品")){
				cateTypeId=3;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("他营单次")){
				cateTypeId=4;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("预存卡")){
				cateTypeId=5;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("礼品卡")){
				cateTypeId=6;
				order.setCateType(cateTypeId);
			}else if(cateType.trim().equals("账户充值")){
				cateTypeId=7;
				order.setCateType(cateTypeId);
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单类型错误");
					flag = 1;
				} else {
					errormsg.append(",订单类型错误");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的订单类型为空");
				flag = 1;
			} else {
				errormsg.append(",订单类型为空");
			}
		}
		if (orderType != null && !orderType.equals("")) {
			String orderTypeId = null;
			for (Map<String, Object> dc : orderTypes) {
				if (orderType.equals(dc.get("CNAME"))) {
					orderTypeId = dc.get("CODE").toString();
					break;
				}
			}

			if (orderTypeId != null) {
				order.setOrderType(orderTypeId);
				order.setServerText(orderType);
			} else {
				if (flag == 0) {
					errormsg.append("该行的需求类型错误");
					flag = 1;
				} else {
					errormsg.append(",需求类型错误");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的需求类型为空");
				flag = 1;
			} else {
				errormsg.append(",需求类型为空");
			}
		}
		
		if(productCode != null && !productCode.equals("")){
			String id = reThreeOrderInMapper.checkProductCode(productCode);
			if(id==null){
				if (flag == 0) {
					errormsg.append("该行的服务编码错误");
					flag = 1;
				} else {
					errormsg.append(",服务编码错误");
				}
			}else{
				order.setProductCode(productCode);
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的服务编码为空");
				flag = 1;
			} else {
				errormsg.append(",服务编码为空");
			}
		}

		/*if(receiverName!=null && !receiverName.equals("") &&receiverName.getBytes().length <= 100
				&&receiverMobile != null && !receiverMobile.equals("")
				&&receiverCityCode!=null && !receiverCityCode.equals("")
				){
			Long userId = reThreeOrderInMapper.findUserIdByUserMobile(receiverMobile);
			if(userId != null){
				order.setReceiverId(userId);
			}else{ 
				if(receiverMobile == userMobile){
					order.setReceiverId(order.getUserId());
				}else{
					wrThreeOrderInMapper.saveReceiver(order);
				}
			}
		}*/
		String receiverAreaCode= null;
		if (receiverArea != null && !receiverArea.equals("")) {
			if(receiverArea.getBytes().length <= 100){
				for (Map<String, Object> address : addresses) {
					if( (receiverArea.equals(address.get("NAME")) || receiverArea.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 12){
						receiverAreaCode = address.get("CODE").toString();
						order.setReceiverAreaCode(receiverAreaCode);
						order.setReceiverArea(receiverArea);
						break;
					}
				}
				if(receiverAreaCode == null){
					if (flag == 0) {
						errormsg.append("该行的接收人地区有误");
						flag = 1;
					} else {
						errormsg.append(",接收人地区有误");
					}
				}
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
		if(productName != null){
			if(productName.getBytes().length <= 100){
				order.setProductName(productName);
			}else{
				if (flag == 0) {
					errormsg.append("该行的商品名称太长");
					flag = 1;
				} else {
					errormsg.append(",商品名称太长");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的服务项目为空");
				flag = 1;
			} else {
				errormsg.append(",服务项目为空");
			}
		}
		
		if (startTime != null && !startTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(startTime);

				order.setStartTime(startTime);
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
		if(startTime != null && !startTime.equals("") && endTime != null && endTime != ""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				Date startDate =sdf.parse(startTime);
				Date endDate =sdf.parse(endTime);
				if(endDate.before(startDate)){
					if (flag == 0) {
						errormsg.append("该行的开始时间不能大于结束时间");
						flag = 1;
					} else {
						errormsg.append(",开始时间不能大于结束时间");
					}
				}
			} catch (ParseException e) {
				 e.printStackTrace();
			}
			
		}
		if (createTime != null && !createTime.equals("")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				Date date =sdf.parse(createTime);
			    if(date.before(new Date())){
			    	order.setCreateTime(createTime);
			    }else{
			    	if (flag == 0) {
						errormsg.append("该行的创建时间不正确");
						flag = 1;
					} else {
						errormsg.append(",创建时间不正确");
					}
			    }
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的创建时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",创建时间格式不正确");
				}

			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的创建时间为空");
				flag = 1;
			} else {
				errormsg.append(",创建时间为空");
			}
		}
		if(sevicerName!=null && !sevicerName.equals("")){
			if(sevicerName.getBytes().length <= 100){
				order.setSevicerName(sevicerName);
			}else{
				if (flag == 0) {
					errormsg.append("该行的服务人员太长");
					flag = 1;
				} else {
					errormsg.append(",服务人员太长");
				}
			}
		}
		if (totalPay != null && !totalPay.equals("")) {
			try {
				BigDecimal totalMoney = new BigDecimal(totalPay);
				order.setTotalPay(totalMoney);
			} catch (Exception e) {
				if (flag == 0) {
					errormsg.append("该行的订单金额格式不正确");
					flag = 1;
				} else {
					errormsg.append(",订单金额不正确");
				}
			}
		}
		if (orderStatus != null && !orderStatus.equals("")) {
			Integer orderStatusId = null;
			if(orderStatus.equals("新单")){
				orderStatusId = 1;
			}else if(orderStatus.equals("已受理")){
				orderStatusId = 2;
			}else if(orderStatus.equals("匹配中")){
				orderStatusId = 3;
			}else if(orderStatus.equals("已匹配")){
				orderStatusId = 4;
			}else if(orderStatus.equals("待面试")){
				orderStatusId = 5;
			}else if(orderStatus.equals("面试成功")){
				orderStatusId = 6;
			}else if(orderStatus.equals("已签约")){
				orderStatusId = 7;
			}else if(orderStatus.equals("已上户")){
				orderStatusId = 8;
			}else if(orderStatus.equals("已完成")){
				orderStatusId = 9;
			}else if(orderStatus.equals("已取消")){
				orderStatusId = 10;
			}else if(orderStatus.equals("已下户")){
				orderStatusId = 11;
			}else if(orderStatus.equals("已终止")){
				orderStatusId = 12;
			}else if(orderStatus.equals("捡货中")){
				orderStatusId = 13;
			}else if(orderStatus.equals("配送中")){
				orderStatusId = 14;
			}else if(orderStatus.equals("三方订单匹配失败")){
				orderStatusId = 16;
			}
			if (orderStatusId != null) {
				order.setOrderStatus(orderStatusId);
			} else {
				if (flag == 0) {
					errormsg.append("该行的订单状态错误");
					flag = 1;
				} else {
					errormsg.append(",订单状态错误");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的订单状态为空");
				flag = 1;
			} else {
				errormsg.append(",订单状态为空");
			}
		}
		if(postId != null && !postId.equals("")){
			if(postId.getBytes().length <= 20){
				order.setPostId(postId);
			}else{
				if (flag == 0) {
					errormsg.append("该行的快递编号太长");
					flag = 1;
				} else {
					errormsg.append(",快递编号太长");
				}
			}
		}
		if (remark != null && !remark.equals("")) {
			order.setRemark(remark);
		}
		
		if(orderChannelText!=null && !orderChannelText.equals("")){
			Integer orderChannel = null;
			for (Map<String, Object> dc : dictionary) {
				if (dc.get("DICT_NAME").equals(orderChannelText)) {
					orderChannel = Integer.valueOf(dc.get("DICT_CODE").toString());
					break;
				}
			}
			if(orderChannel != null){
				order.setOrderChannel(orderChannel);
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单来源有误");
					flag = 1;
				} else {
					errormsg.append(",订单来源有误");
				}
			}
		}else{
			if (flag == 0) {
				errormsg.append("该行的订单来源为空");
				flag = 1;
			} else {
				errormsg.append(",订单来源为空");
			}
		}
		order.setOrderSourceId(20180007);
		order.setCreateBy(createBy);
		if (flag == 0) {
			wrThreeOrderInMapper.saveThreeOrder(order);
			wrThreeOrderInMapper.saveThreeOrderItem(order);
			wrThreeOrderInMapper.saveThreeOrderServerDetailServer(order);
			map.put("success", "成功");
			map.put("errormsg", "");
		} else {
			errormsg.append(";");
			map.put("success", "失败");
			map.put("errormsg", errormsg.toString());
		}
	}
	
	@Override
	@Transactional
	public void insertFaOrder(Map<String, String> map, Long createBy, List<Map<String, Object>> dictionary,
			List<Map<String, Object>> addresses, List<Map<String, Object>> orderTypes)  throws Exception {
			ThreeOrderIn order = new ThreeOrderIn();
			String orderCode = map.get("orderCode");
			String cityName = map.get("cityName");
			String userName = map.get("userName");
			String userMobile = map.get("userMobile");
			String receiverName = map.get("receiverName");
			String receiverMobile = map.get("receiverMobile");
			String receiverProvince = map.get("receiverProvince");
			String receiverCity = map.get("receiverCity");
			String receiverArea = map.get("receiverArea");
			String receiverAddress = map.get("receiverAddress");
			String productNames = map.get("productNames");
			String productCode = map.get("productCode");
			String productNumbers = map.get("productNumbers");
			String productPrices = map.get("productPrices");
			String totalPay = map.get("totalPay");
			String orderStatus = map.get("orderStatus");
			String orderChannelText = map.get("orderChannelText");
			String postId = map.get("postId");
			String receiveTime = map.get("receiveTime");
			String createTime = map.get("createTime");
			String remark = map.get("remark");
			String isrepeat=map.get("isrepeat");
			StringBuffer errormsg =new StringBuffer("");
			if(isrepeat!=null && isrepeat.equals("1")){   //有重复直接跳出，进入下一个循坏
				return;
			}
			int flag = 0;
			if (orderCode != null && !orderCode.equals("")) {
				if(orderCode.getBytes().length <= 20){
					order.setThreeOrderCode(orderCode);
				}else{
					if (flag == 0) {
						errormsg.append("该行的订单编号太长");
						flag = 1;
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("该行的订单编号为空");
					flag = 1;
				}
			}
			String cityCode = null;
			if(cityName != null && !cityName.equals("")){
				for (Map<String, Object> address : addresses) {
					if( (cityName.equals(address.get("NAME")) || cityName.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 9){
						cityCode = address.get("CODE").toString();
						order.setCityCode(cityCode);
						break;
					}
				}
				if(cityCode == null){
					if (flag == 0) {
						errormsg.append("该行的城市有误");
						flag = 1;
					} else {
						errormsg.append(",城市有误");
					}
				}
				
			}else{
				if (flag == 0) {
					errormsg.append("该行的城市为空");
					flag = 1;
				} else {
					errormsg.append(",城市为空");
				}
			}
			if(userName!=null && !userName.equals("")){
				if(userName.getBytes().length <= 100){	
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
			if(userMobile !=null && !userMobile.equals("")){
				if(userMobile.getBytes().length <= 11){
					order.setUserMobile(userMobile);
				}else{
					if (flag == 0) {
						errormsg.append("该行的用户手机太长");
						flag = 1;
					} else {
						errormsg.append(",用户手机太长");
					}
				}
			}
			
			String provinceCode = null;
			if (receiverProvince != null && !receiverProvince.equals("")) {
				if(receiverProvince.getBytes().length <= 100){
					for (Map<String, Object> address : addresses) {
						if( ( receiverProvince.equals(address.get("NAME")) || receiverProvince.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 6){
							provinceCode = address.get("CODE").toString();
							order.setReceiverProvinceCode(provinceCode);
							order.setReceiverProvince(receiverProvince);
							break;
						}
					}
					if(provinceCode == null){
						if (flag == 0) {
							errormsg.append("该行的接收人省份有误");
							flag = 1;
						} else {
							errormsg.append(",接收人省份有误");
						}
					}
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
			String receiverCityCode = null;
			if (receiverCity != null && !receiverCity.equals("")) {
				if(receiverCity.getBytes().length <= 100){
					for (Map<String, Object> address : addresses) {
						if( (receiverCity.equals(address.get("NAME")) || receiverCity.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 9){
							receiverCityCode = address.get("CODE").toString();
							order.setReceiverCityCode(receiverCityCode);
							order.setReceiverCity(receiverCity);
							break;
						}
					}
					if(receiverCityCode == null){
						if (flag == 0) {
							errormsg.append("该行的接收人城市有误");
							flag = 1;
						} else {
							errormsg.append(",接收人城市有误");
						}
					}
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
			//fa省份名称和城市名称与用户名、电话，同时验证，并保存
			if(userName != null && !userName.equals("") && userName.getBytes().length <= 100 
					&& userMobile != null && !userMobile.equals("") &&userMobile.getBytes().length <= 11
					&& cityCode !=null && !cityCode.equals("")){
				Long userId = reThreeOrderInMapper.findUserIdByUserMobile(userMobile);
				if(userId != null){
					order.setUserId(userId);
				}else{
					wrThreeOrderInMapper.saveUser(order);
				}
			}
			if (receiverName != null && !receiverName.equals("")) {
				if(receiverName.getBytes().length <= 100){
					order.setReceiverName(receiverName);
				}else{
					if (flag == 0) {
						errormsg.append("该行的收货人姓名太长");
						flag = 1;
					} else {
						errormsg.append(",收货人姓名太长");
					}
				}
			
			} else {
				if (flag == 0) {
					errormsg.append("该行的收货人为空");
					flag = 1;
				} else {
					errormsg.append(",收货人为空");
				}
			}
			if (receiverMobile != null && !receiverMobile.equals("")) {
				if(receiverMobile.getBytes().length <= 100 ){
					order.setReceiverMobile(receiverMobile);
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
					errormsg.append("该行的收货人电话为空");
					flag = 1;
				} else {
					errormsg.append(",收货人电话为空");
				}
			}

/*			if(receiverName!=null && !receiverName.equals("") &&receiverName.getBytes().length <= 100
					&&receiverMobile != null && !receiverMobile.equals("") 
					&&receiverCityCode!=null && !receiverCityCode.equals("")
					){
				Long userId = reThreeOrderInMapper.findUserIdByUserMobile(receiverMobile);
				if(userId != null){
					order.setReceiverId(userId);
				}else{ 
					if(receiverMobile == userMobile){
						order.setReceiverId(order.getUserId());
					}else{
						wrThreeOrderInMapper.saveReceiver(order);
					}
				}
			}*/
			String receiverAreaCode = null;
			if (receiverArea != null && !receiverArea.equals("")) {
				if(receiverArea.getBytes().length <= 100){
					for (Map<String, Object> address : addresses) {
						if( (receiverArea.equals(address.get("NAME")) || receiverArea.equals(address.get("SHORT_NAME"))) && address.get("CODE").toString().length() == 12){
							receiverAreaCode = address.get("CODE").toString();
							order.setReceiverAreaCode(receiverAreaCode);
							order.setReceiverArea(receiverArea);
							break;
						}
					}
					if(receiverAreaCode == null){
						if (flag == 0) {
							errormsg.append("该行的接收人地区有误");
							flag = 1;
						} else {
							errormsg.append(",接收人地区有误");
						}
					}
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
			order.setOrderType("1");
			if (totalPay != null && !totalPay.equals("")) {
				try {
					BigDecimal totalMoney = new BigDecimal(totalPay);

					order.setTotalPay(totalMoney);
				} catch (Exception e) {
					if (flag == 0) {
						errormsg.append("该行的订单金额格式不正确");
						flag = 1;
					} else {
						errormsg.append(",创建订单金额不正确");
					}
				}
			}
			if (orderStatus != null && !orderStatus.equals("")) {
				Integer orderStatusId = null;
				if(orderStatus.equals("新单")){
					orderStatusId = 1;
				}else if(orderStatus.equals("已受理")){
					orderStatusId = 2;
				}else if(orderStatus.equals("匹配中")){
					orderStatusId = 3;
				}else if(orderStatus.equals("已匹配")){
					orderStatusId = 4;
				}else if(orderStatus.equals("待面试")){
					orderStatusId = 5;
				}else if(orderStatus.equals("面试成功")){
					orderStatusId = 6;
				}else if(orderStatus.equals("已签约")){
					orderStatusId = 7;
				}else if(orderStatus.equals("已上户")){
					orderStatusId = 8;
				}else if(orderStatus.equals("已完成")){
					orderStatusId = 9;
				}else if(orderStatus.equals("已取消")){
					orderStatusId = 10;
				}else if(orderStatus.equals("已下户")){
					orderStatusId = 11;
				}else if(orderStatus.equals("已终止")){
					orderStatusId = 12;
				}else if(orderStatus.equals("捡货中")){
					orderStatusId = 13;
				}else if(orderStatus.equals("配送中")){
					orderStatusId = 14;
				}else if(orderStatus.equals("三方订单匹配失败")){
					orderStatusId = 16;
				}
				if (orderStatusId != null) {
					order.setOrderStatus(orderStatusId);
				} else {
					if (flag == 0) {
						errormsg.append("该行的订单状态错误");
						flag = 1;
					} else {
						errormsg.append(",订单状态错误");
					}
				}
			} else {
				if (flag == 0) {
					errormsg.append("该行的订单状态为空");
					flag = 1;
				} else {
					errormsg.append(",订单状态为空");
				}
			}
			if(orderChannelText!=null && !orderChannelText.equals("")){
				Integer orderChannel = null;
				for (Map<String, Object> dc : dictionary) {
					if (dc.get("DICT_NAME").equals(orderChannelText)) {
						orderChannel = Integer.valueOf(dc.get("DICT_CODE").toString());
						break;
					}
				}
				if(orderChannel != null){
					order.setOrderChannel(orderChannel);
				}else{
					if (flag == 0) {
						errormsg.append("该行的订单来源有误");
						flag = 1;
					} else {
						errormsg.append(",订单来源有误");
					}
				}
			}else{
				if (flag == 0) {
					errormsg.append("该行的订单来源为空");
					flag = 1;
				} else {
					errormsg.append(",订单来源为空");
				}
			}
			if(postId != null && !postId.equals("")){
				order.setPostId(postId);
			}
			if(receiveTime != null && !receiveTime.equals("")){	
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
					sdf.setLenient(false);
					sdf.parse(receiveTime);
					order.setReceiveTime(receiveTime);
				} catch (ParseException e) {
					// e.printStackTrace();
					// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
					if (flag == 0) {
						errormsg.append("该行的接收时间格式不正确");
						flag = 1;
					} else {
						errormsg.append(",接收时间格式不正确");
					}
				}
			}
			if(createTime !=null && !createTime.equals("")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
					sdf.setLenient(false);
					Date date = sdf.parse(createTime);
					if(date.before(new Date())){
						order.setCreateTime(createTime);
					}else{
						if (flag == 0) {
							errormsg.append("该行的创建时间不正确");
							flag = 1;
						} else {
							errormsg.append(",创建时间不正确");
						}
					}
				} catch (ParseException e) {
					// e.printStackTrace();
					// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
					if (flag == 0) {
						errormsg.append("该行的创建时间格式不正确");
						flag = 1;
					} else {
						errormsg.append(",创建时间格式不正确");
					}
				}
			}else{
				if (flag == 0) {
					errormsg.append("该行的创建时间为空");
					flag = 1;
				} else {
					errormsg.append(",创建时间为空");
				}
			}
			if(remark != null && remark != ""){
				order.setRemark(remark);
			}
			order.setCateType(3);
			order.setOrderSourceId(20180007);
			order.setCreateBy(createBy);
			if(productNames == null){
				if (flag == 0) {
					errormsg.append("该行的商品名称为空");
					flag = 1;
				} else {
					errormsg.append(",商品名称为空");
				}
			}
			if(productCode == null ){
				if (flag == 0) {
					errormsg.append("该行的商品编号为空");
					flag = 1;
				} else {
					errormsg.append(",商品编号为空");
				}
			}
			if(productNumbers == null){
				if (flag == 0) {
					errormsg.append("该行的商品数量为空");
					flag = 1;
				} else {
					errormsg.append(",商品数量为空");
				}
			}
			if(productPrices == null){
				if (flag == 0) {
					errormsg.append("该行的商品数量为空");
					flag = 1;
				} else {
					errormsg.append(",商品数量为空");
				}
			}
			if(productNames != null && productCode != null && productNumbers != null && productPrices != null){
				String[] productNameArray = productNames.split(",");
				String[] productCodeArray = productCode.split(",");
				String[] productNumberArray = productNumbers.split(",");
				String[] productPrice = productPrices.split(",");
				if(productNameArray.length == productCodeArray.length 
					&& productNameArray.length ==productNumberArray.length 
					&& productNameArray.length == productPrice.length){ //判断商品字段数组是否一一对应
						for (int i = 0; i < productNameArray.length; i++) {
							String productName = productNameArray[i];
							String code = productCodeArray[i];
							String quantity = productNumberArray[i];
							String price = productPrice[i];
							String id = reThreeOrderInMapper.checkProductCode(code);
							if(id == null){
								if (flag == 0) {
									errormsg.append("该行的商品编码:"+code+"错误");
									flag = 1;
								} else {
									errormsg.append(",商品编码:"+code+"错误");
								}
							}
							if(productName.getBytes().length > 100){
								if (flag == 0) {
									errormsg.append("该行的商品名称:"+productName+"太长");
									flag = 1;
								} else {
									errormsg.append(",商品名称:"+productName+"太长");
								}
							}
							if(quantity.getBytes().length <= 12){
								try{
									Long.valueOf(quantity);
								}catch(Exception e){
									if (flag == 0) {
										errormsg.append("该行的商品个数:"+quantity+"格式不正确");
										flag = 1;
									} else {
										errormsg.append(",商品个数:"+quantity+"格式不正确");
									}
								}
							}else{
								if (flag == 0) {
									errormsg.append("该行的商品个数:"+quantity+"太长");
									flag = 1;
								} else {
									errormsg.append(",商品个数:"+quantity+"太长");
								}
							}
							if(price.getBytes().length <= 12){
								 try{
									 new BigDecimal(price);
								 }catch(Exception e){
									 if (flag == 0) {
											errormsg.append("该行的商品单价:"+price+"格式不正确");
											flag = 1;
										} else {
											errormsg.append(",商品单价:"+price+"格式不正确");
										} 
								 }
							}else{
								if (flag == 0) {
									errormsg.append("该行的商品单价:"+price+"太长");
									flag = 1;
								} else {
									errormsg.append(",商品单价:"+price+"太长");
								} 
							}
						}
				}else{
					if (flag == 0) {
						errormsg.append("该行商品内容没有一一对应");
						flag = 1;
					} else {
						errormsg.append(",商品内容没有一一对应");
					}
				}
			}
			if (flag == 0) {
				String[] productNameArray = productNames.split(",");
				String[] productCodeArray = productCode.split(",");
				String[] productNumberArray = productNumbers.split(",");
				String[] productPrice = productPrices.split(",");
				wrThreeOrderInMapper.saveThreeOrder(order);
				for(int i = 0 ; i<productNameArray.length;i++){
					order.setProductName(productNameArray[i]);
					order.setProductCode(productCodeArray[i]);
					order.setQuantity(Long.valueOf(productNumberArray[i]));
					order.setProductPrice(new BigDecimal(productPrice[i]));
					wrThreeOrderInMapper.saveThreeOrderItem(order);
					wrThreeOrderInMapper.saveFaItemDetail(order);
				}
				map.put("success", "成功");
				map.put("errormsg", "");
			} else {
				errormsg.append(";");
				map.put("success", "失败");
				map.put("errormsg", errormsg.toString());
			}
			
	}

	@Override
	public List<Map<String, Object>> queryDictionary() {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.queryDictionary();
	}

	@Override
	public List<Map<String, Object>> queryBaseCity() {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.queryBaseCity();
	}

	@Override
	public List<Map<String, Object>> queryOrderType() {
		// TODO Auto-generated method stub
		return reThreeOrderInMapper.queryOrderType();
	}
	
	@Override
	public void saveOrderAndItem(ThreeOrderItemDetailServer threeOrderItemDetailServer){
		// 订单信息--start
		ThreeOrderInModel order = new ThreeOrderInModel();
			
		order.setCreateBy(threeOrderItemDetailServer.getCreateBy());
			//订单类型:服务的三级分类，商品分类是固定值
		order.setOrderType(threeOrderItemDetailServer.getServerType());
			// 订单来源：第三方订单 7
		order.setOrderSourceId("20180007");
			// 用户Id
		order.setUserId(threeOrderItemDetailServer.getUserId());
			// 接受人Id
		order.setReceiverId(threeOrderItemDetailServer.getReceiverId());
			// 紧急程度：1紧急 2不紧急
		order.setCritical(2);
			// 订单状态： 1--新建
		order.setOrderStatus(1);
			// 1.自营单次 2.自营延续 3.自营商品 4.他营单次
		order.setCateType(threeOrderItemDetailServer.getCateType());
			//订单渠道
		order.setOrderChannel(threeOrderItemDetailServer.getOrderChannel());
			//设置用户信息
		ThreeOrderCustomerModel user = setThreeOrderCustomer(order);
			
			//订单支付状态1--"未支付"， 4--"部分支付"， 2--"已支付"， 3--"退款"
		order.setPayStatus(20110001);
			//价格计算-- 自营单次、他营单次	
		//if(threeOrderItemDetailServer.getCateType()==1){
		//	calculateSingleOrderPrice(threeOrderItemDetailServer, order, user);
		//}
		order.setCity(threeOrderItemDetailServer.getCity());
			//自营延续
		if(threeOrderItemDetailServer.getCateType()==2){
			System.out.println(threeOrderItemDetailServer.getPriceSystem());
			calculateExtendsOrderPrice(threeOrderItemDetailServer, order, user);
		}
		if(threeOrderItemDetailServer.getCateType()==4 || threeOrderItemDetailServer.getCateType()==1){
			order.setTotalPay(threeOrderItemDetailServer.getTotalPay());
		}
		order.setPriceType(threeOrderItemDetailServer.getPriceType());
		order.setValid(1);
			//订单备注
		order.setRemark(threeOrderItemDetailServer.getRemark());
		//验证用户是否分配有管家
		Long marketBy=checkUserKeeper(threeOrderItemDetailServer.getUserId());
		if(marketBy!=null){
			order.setRechargeBy(marketBy);
			order.setRechargeDept(String.valueOf(queryManagerDeptId(marketBy)));
//			order.setFollowBy(marketBy);
//			order.setFollowDept(order.getRechargeDept());
			order.setCreateDept(String.valueOf(queryManagerDeptId(order.getCreateBy())));
		}
			//保存订单获得订单Id   
		Long orderId = this.extendedService.insertThreeOrder(order);
		// 订单信息--end

		// 订单项--start
		Item item = new Item();
		item.setProductCode(threeOrderItemDetailServer.getProductCode());//商品code
		item.setProductName(threeOrderItemDetailServer.getProductName());// 商品名称
		item.setNowPrice(threeOrderItemDetailServer.getNowPrice());//商品单价
		item.setQuantity(threeOrderItemDetailServer.getPickQuantity());//商品数量
		item.setProductUnit(threeOrderItemDetailServer.getProductUnit());//最小单位
		item.setProductPriceType(threeOrderItemDetailServer.getPriceType());//价格体系
		item.setProductSpec(threeOrderItemDetailServer.getProductSpec());//商品规格
		item.setCategoryCode(threeOrderItemDetailServer.getCateCode());
		item.setOrderId(orderId);
		item.setCreateBy(threeOrderItemDetailServer.getCreateBy());
		item.setVersion(1L);
		item.setSaleType("1");
		item.setValid(1);
		Long itemId = this.extendedService.insertThreeOrderItem(item);
		// 订单项--end
		// 订单详情 -- start
		threeOrderItemDetailServer.setOrderId(orderId);
		threeOrderItemDetailServer.setItemId(itemId);
		threeOrderItemDetailServer.setCreateBy(threeOrderItemDetailServer.getCreateBy());
		threeOrderItemDetailServer.setVersion(1L);
		threeOrderItemDetailServer.setValid(1);
		threeOrderItemDetailServer.setPickQuantity(1f);
		//前端不再直接传服务地址，而是使用接受人的地址
		threeOrderItemDetailServer.setAddress(order.getReceiverAddress());
		this.extendedService.insertItemDetailServer(threeOrderItemDetailServer);
		//订单详情 -- end
	}

	//商品
	@Override
	public void saveThreeOrderFaItem(ThreeOrderInModel order) throws Exception {
		JSONArray array = JSONArray.fromObject(order.getListItem());  
		Object[] obj = new Object[array.size()];  
        for(int i = 0; i < array.size(); i++){  
        	JSONObject JB = array.getJSONObject(i);
        	//将建json对象转换为Object对象
            obj[i] = JSONObject.toBean(JB, Item.class);  
        }  
        //FA商品orderType使用固定值
		order.setOrderType("1");
		//Fa订单来源
		order.setOrderSourceId("20180007");
		order.setOrderStatus(1);
		order.setCritical(2);
		//订单备注--目前前台没有显示的字段
		order.setRemark(order.getRemark());
		//计算订单价格
		ThreeOrderInProductModel threeOrderInProductModel = new ThreeOrderInProductModel();
		threeOrderInProductModel.setUser_id(order.getUserId());
		threeOrderInProductModel.setCity_code(order.getUserCity());
		threeOrderInProductModel.setProduct_code_count_json(order.getListItem().toString());
		Map<String,BigDecimal>amount_map = new HashMap<String,BigDecimal>();
		List<ThreeOrderInProductModel> list = 
				this.threeOrderService.reCalculateThreeOrderInPrice(threeOrderInProductModel,amount_map);
		BigDecimal amount = amount_map.get("amount");
		if(amount.compareTo(new BigDecimal(0))<1){
			throw new Exception("总价小于0"); 
		}
		order.setDeliverPay(new BigDecimal(15));
		//未支付
		order.setPayStatus(20110001);
		//应付金额
		order.setTotalPay(amount);
		//价格体系
		order.setPriceType(threeOrderInProductModel.getDict_code());
		//设置用户信息
		setThreeOrderCustomer(order);
		
		//验证用户是否分配有管家
		Long marketBy=checkUserKeeper(order.getUserId());
		if(marketBy!=null){
			order.setRechargeBy(marketBy);
			order.setRechargeDept(String.valueOf(queryManagerDeptId(marketBy)));
//			order.setFollowBy(marketBy);
//			order.setFollowDept(order.getRechargeDept());
			order.setCreateDept(String.valueOf(queryManagerDeptId(order.getCreateBy())));
		}
		order.setValid(1);
		Long orderid = this.faService.insertThreeOrderFa(order);
		
		for(Iterator<ThreeOrderInProductModel> iter= list.iterator();iter.hasNext();){
			ThreeOrderInProductModel pm = iter.next();
			ThreeOrderInFaItemModel item = new ThreeOrderInFaItemModel();
			item.setOrderId(orderid);
			item.setCategoryCode(pm.getCategory_code());
			item.setProductCode(pm.getProduct_code());
			CityProduct cityProduct = new CityProduct();
			cityProduct.setProductCode(pm.getProduct_code());
			List<CityProduct> lists = this.itemService.queryProductCodeSpec(cityProduct);
			item.setProductSpec(lists.get(0).getProductSpec());
			item.setProductUnit(lists.get(0).getProductUnit());
			item.setProductName(pm.getProduct_name());
			item.setNowPrice(pm.getPrice());
			item.setQuantity(pm.getProduct_count());
			item.setProductPriceType(order.getPriceSystem());
			item.setSaleType("1");
			item.setCreateBy(order.getCreateBy());
			item.setUpdateBy(order.getCreateBy());
			item.setProduct_img(pm.getProduct_img());
			item.setCategoryCode(pm.getCategory_code());
			item.setValid(1);
			item.setVersion(1L);
			this.wr_FaItemMapper.insertThreeOrderItemInFa(item);
		}
	}
	
	private ThreeOrderCustomerModel setThreeOrderCustomer(ThreeOrderInModel order) {
		// 取用户信息
		ThreeOrderCustomerModel user = new ThreeOrderCustomerModel();
		user.setValid(1);
		//user.setAdValid(1);
		user.setId(order.getUserId());
		user = this.re_t_o_c_Mapper.getCustomer(user);
		//用户
		order.setUserId(user.getUserId());
		order.setUserName(user.getRealName());
		order.setUserProvince(user.getUserProvince());
		order.setUserCity(user.getUserCity());
		order.setUserArea(user.getUserDistrict());
		order.setUserAddress(user.getUserAddress());
		order.setUserMobile(user.getUserMobile());
		order.setUserEmail(user.getUserEmail());
		order.setUserSex(user.getUserSex());
		order.setUserBirth(user.getUserBirth());
		order.setUserCityCode(user.getUserCityCode());
		//接收人--三方订单服务项目的接受人信息和下单人信息一致
		ThreeOrderCustomerAddrModel userAddr = new ThreeOrderCustomerAddrModel();
		userAddr.setId(order.getReceiverId());
		userAddr.setUserId(order.getUserId());
		userAddr = threeOrderService.getOrderThreeCustomerAddrById(userAddr);
		order.setReceiverId(userAddr.getId());
		order.setReceiverName(userAddr.getContactName());
		order.setReceiverMobile(userAddr.getContactPhone());
		order.setReceiverProvince(userAddr.getProvince());
		order.setReceiverCity(userAddr.getCity());
		order.setReceiverArea(userAddr.getCountry());
		order.setReceiverAddress(userAddr.getProvince()+userAddr.getCity()+userAddr.getCountry()+userAddr.getAddressChooseAddress()+userAddr.getAddressDetail());
		order.setLongitude(userAddr.getLongitude());
		order.setLatitude(userAddr.getLatitude());
		order.setReceiverCityCode(userAddr.getCityCode());
		
		return user;
	}
	//计算他营单次，自营单次产品价格
	private void calculateSingleOrderPrice(ThreeOrderItemDetailServer threeOrderItemDetailServer,
			ThreeOrderInModel order, ThreeOrderCustomerModel user) {
		//计算价格
		ThreeOrderInProductModel toi_p_mo = new ThreeOrderInProductModel();
		if(user.getIs_vip() != null && user.getIs_vip().intValue()==1){
			toi_p_mo.setDict_code("20000006");
		}else if(user.getIs_big_cust() !=null && user.getIs_big_cust().intValue() == 1){
			toi_p_mo.setDict_code("20000003");
		}else{
			toi_p_mo.setDict_code("20000002");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> product_code_list = new ArrayList<String>();
		product_code_list.add(threeOrderItemDetailServer.getProductCode());
		params.put("DICT_CODE", toi_p_mo.getDict_code());
		params.put("product_code_list", product_code_list);
		params.put("category_code", threeOrderItemDetailServer.getServerType());
		List<ThreeOrderInProductModel> list= this.re_o_t_p_c_mapper.getProductListByCodes(params);
		BigDecimal amount = new BigDecimal(0);
		if(list!=null && list.size()>0){
			ThreeOrderInProductModel pd = list.get(0);
			BigDecimal price = pd.getPrice();
			
			String unit_code = pd.getLeastUnit();
			Integer min_unit_count = pd.getMinUnitCount();
			if(min_unit_count==null || min_unit_count<1 ){
				min_unit_count = 1;
			}
			BigDecimal qty = new BigDecimal(0);
			BigDecimal min_unit_count_b = new BigDecimal(min_unit_count);
			//按小时计算
			if("20070010".equals(unit_code)){
				Date date_s = Tools.str2Date(threeOrderItemDetailServer.getStartTime()+":00");
				Date date_e = Tools.str2Date(threeOrderItemDetailServer.getEndTime()+":00");
				Long start = date_s.getTime();
				Long end = date_e.getTime();
				long serviceTime = end - start ;
				Double m_h = Math.ceil(serviceTime/(1000*60*60*1.0));
				//服务时长 ,服务时长小于产品最小起订量，按最小起订量算
				qty = new BigDecimal(m_h);
				if(qty.compareTo(min_unit_count_b)==-1){
					qty = min_unit_count_b;
				}
				amount = price.multiply(qty);
				
			}else if("20070011".equals(unit_code)){
				//按平方，小于产品最小起订量，按最小起订量算
				qty = threeOrderItemDetailServer.getHomeForests();
				if(qty.compareTo(new BigDecimal(min_unit_count)) ==-1 ){
					qty = min_unit_count_b;
				}
				amount = price.multiply(qty);
			}else {
				qty = min_unit_count_b;
				amount = price.multiply(new BigDecimal(min_unit_count));
			}
			//总价
			threeOrderItemDetailServer.setPickQuantity(qty.floatValue());
			threeOrderItemDetailServer.setSettlePrice(price);
			order.setTotalPay(amount);
			order.setPriceType(toi_p_mo.getDict_code());
		}
	}
	
	private void calculateExtendsOrderPrice(ThreeOrderItemDetailServer threeOrderItemDetailServer,
			ThreeOrderInModel order, ThreeOrderCustomerModel user) {
		//计算价格
		System.out.println(threeOrderItemDetailServer.getPriceSystem());
		ThreeOrderInProductModel toi_p_mo = new ThreeOrderInProductModel();
		if(user.getIs_vip() != null && user.getIs_vip().intValue()==1){
			toi_p_mo.setDict_code("20000006");
		}else if(user.getIs_big_cust() !=null && user.getIs_big_cust().intValue() == 1){
			toi_p_mo.setDict_code("20000003");
		}else{
			toi_p_mo.setDict_code("20000002");
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		List<String> product_code_list = new ArrayList<String>();
		product_code_list.add(threeOrderItemDetailServer.getProductCode());
		params.put("dict_code", toi_p_mo.getDict_code());
		params.put("product_code_list", product_code_list);
		params.put("category_code", threeOrderItemDetailServer.getServerType());
		List<ThreeOrderInProductModel> list= this.re_o_t_p_c_mapper.getProductListByCodes(params);
		if(list!=null && list.size()>0){
			ThreeOrderInProductModel pd = list.get(0);
			BigDecimal price = pd.getPrice();
			
			Integer min_unit_count = pd.getMinUnitCount();
			if(min_unit_count==null || min_unit_count<1 ){
				min_unit_count = 1;
			}
			//数量
			threeOrderItemDetailServer.setPickQuantity(new BigDecimal(min_unit_count).floatValue());
			//单价
			threeOrderItemDetailServer.setSettlePrice(price);
		}
		order.setPriceType(toi_p_mo.getDict_code());
	}

	@Override
	public void savaExcelRecord(String url,Long createBy,String fileName) {
		wrThreeOrderInMapper.savaExcelRecord(url,createBy,fileName);
		
	}

	@Override
	public List<Order> queryOrderBasicItem(Long id) {
		List<Order> list = null;
		list = this.reThreeOrderInMapper.queryOrderBasicItem(id);
		return list;
	}

	@Override
	public List<ThreeOrderIn> queryThreeOrderRecord(String recordStartTime, String recordEndTime, Page page) {
		if(page.needQueryPading()){//分页
			page.setTotalRecord(reThreeOrderInMapper.countThreeOrderRecord(recordStartTime,recordEndTime));
		}
		String beginRow = page.getFilterRecord().toString();
		String pageSize = page.getPageCount().toString();
		List<ThreeOrderIn> list = null;
		list = this.reThreeOrderInMapper.queryThreeOrderRecord(beginRow,pageSize,recordStartTime,recordEndTime);
		return list;
	}

	@Override
	public List<Map<String, Object>> queryChannel() {
		// TODO Auto-generated method stub
		return this.reThreeOrderInMapper.queryChannel();
	}
	
	@Override
	public List<Map<String, Object>> queryOrderChannel() {
		// TODO Auto-generated method stub
		return this.reThreeOrderInMapper.queryOrderChannel();
	}
	
	
	/**
	 * 验证用户是否有管家
	 * @param userId
	 * @return
	 */
	@Override
	public Long checkUserKeeper(Long userId){
		ThreeOrderUser user=reThreeOrderOutMapper.queryUserDetailByUserId(userId);
		if(user!=null&&user.getMarketBy()!=null&&!user.getMarketBy().equals("")){
			return user.getMarketBy();
		}
		return null;
	}
	
	/**
	 * 获取管理员部门ID
	 * @param id
	 * @return
	 */
	@Override
	public Long queryManagerDeptId(Long id){
		return reThreeOrderOutMapper.queryManagerDeptId(id);
	} 
}
