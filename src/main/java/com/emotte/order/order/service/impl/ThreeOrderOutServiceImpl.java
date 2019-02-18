package com.emotte.order.order.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.mapper.reader.ReThreeOrderOutMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderOutMapper;
import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.model.ThreeOrderAccounts;
import com.emotte.order.order.model.ThreeOrderAddress;
import com.emotte.order.order.model.ThreeOrderCard;
import com.emotte.order.order.model.ThreeOrderDictionary;
import com.emotte.order.order.model.ThreeOrderPayfee;
import com.emotte.order.order.model.ThreeOrderCategory;
import com.emotte.order.order.model.ThreeOrderPrice;
import com.emotte.order.order.model.ThreeOrderProduct;
import com.emotte.order.order.model.ThreeOrderUser;
import com.emotte.order.order.service.ThreeOrderOutService;

@Service("threeOrderOutService")
@Transactional
public class ThreeOrderOutServiceImpl implements ThreeOrderOutService {
	
	@Resource
	private ReThreeOrderOutMapper reThreeOrderOutMapper;
	@Resource
	private WrThreeOrderOutMapper wrThreeOrderOutMapper;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderCategory> queryOrderType(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryOrderThirdType(order);
	}
	
	@Override
	public List<ThreeOrderDictionary> queryBaseDictionary(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryBaseDictionary(order);
	}
	
	@Override
	public List<ThreeOrderDictionary> queryBaseCity(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryBaseCity(order);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryOrder(ThreeOrder order, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reThreeOrderOutMapper.countOrder(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreeOrderOutMapper.queryOrder(order);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryOrderOneDetail(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryOrderOneDetail(order);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderAccounts> queryOrderOneAccounts(ThreeOrderAccounts order) {
		// TODO Auto-generated method stub
		List<ThreeOrderAccounts> listAccounts = reThreeOrderOutMapper.queryOrderOneAccounts(order);
		for(ThreeOrderAccounts accounts : listAccounts){
			order.setAccountsId(accounts.getAccountsId());;
			List<ThreeOrderAccounts> listPayfee = reThreeOrderOutMapper.queryOrderOnePayfee(order);
			accounts.setChildren(listPayfee);
		}
		return listAccounts;
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int saveOrderAccounts(ThreeOrderAccounts accounts) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.saveOrderAccounts(accounts);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderUser> queryOrderUser(ThreeOrderUser order, Page page) {
		if(page.needQueryPading()){
			page.setTotalRecord(reThreeOrderOutMapper.countOrderUser(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreeOrderOutMapper.queryOrderUser(order);
	}
	
	@Override
	public int queryOrderUserByMobile(ThreeOrderUser orderUser) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryOrderUserByMobile(orderUser);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int saveOrderUser(ThreeOrderUser orderUser) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.saveOrderUser(orderUser);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderCategory> queryCategory(ThreeOrderCategory category) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryCategory(category);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderProduct> queryProduct(ThreeOrderProduct product) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryProduct(product);
	}
	
	@Override
	public List<ThreeOrderAddress> queryAddress(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryAddress(order);
	}
	
	@Override
	public int saveAddress(ThreeOrderAddress order) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.saveAddress(order);
	}
	
	@Override
	public int updateAddress(ThreeOrderAddress address) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.updateAddress(address);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ThreeOrderUser queryUserDetail(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryUserDetail(order);
	}
	
	@Override
	public ThreeOrderPrice queryProductPrice(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryProductPrice(order);
	}
	
	@Override
	public ThreeOrderAddress queryUserAddressDetail(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryUserAddressDetail(order);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int insertThreeOrder(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.insertThreeOrder(order);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public ThreeOrderProduct queryProductDetail(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.reThreeOrderOutMapper.queryProductDetail(order);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int insertThreeOrderItem(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.insertThreeOrderItem(order);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int insertThreeOrderItemDetailServer(ThreeOrder order) {
		// TODO Auto-generated method stub
		return this.wrThreeOrderOutMapper.insertThreeOrderItemDetailServer(order);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public int checkMatch(String[] orderIds) {
		// TODO Auto-generated method stub
		List<String> orderIdList = new ArrayList<String>();
		for(int i=0;i<orderIds.length;i++){
			orderIdList.add(orderIds[i]);
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("orderIds", orderIdList);
		map.put("orderStatus", 2);
		return reThreeOrderOutMapper.checkOrderStatus(map);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int doMatch(String[] orderIds) {
		// TODO Auto-generated method stub
		List<String> orderIdList = new ArrayList<String>();
		for(int i=0;i<orderIds.length;i++){
			orderIdList.add(orderIds[i]);
		}
		return this.wrThreeOrderOutMapper.doMatch(orderIdList);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public int checkBilling(String[] orderIds) {
		// TODO Auto-generated method stub
		List<String> orderIdList = new ArrayList<String>();
		for(int i=0;i<orderIds.length;i++){
			orderIdList.add(orderIds[i]);
		}
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("orderIds", orderIdList);
		map.put("orderStatus", 4);
		return reThreeOrderOutMapper.checkOrderStatus(map);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int doBilling(String[] orderIds) {
		// TODO Auto-generated method stub
		List<String> orderIdList = new ArrayList<String>();
		for(int i=0;i<orderIds.length;i++){
			orderIdList.add(orderIds[i]);
		}
		return this.wrThreeOrderOutMapper.doBilling(orderIdList);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int matchSuccess(ThreeOrder order) {
		// TODO Auto-generated method stub
		order.setOrderStatus("4");
		return this.wrThreeOrderOutMapper.saveMatch(order);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int matchFail(ThreeOrder order) {
		// TODO Auto-generated method stub
		order.setRemark("【匹配失败：】"+order.getRemark());
		return this.wrThreeOrderOutMapper.matchFail(order);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderCard> queryCards(ThreeOrder order) {
		// TODO Auto-generated method stub
		return reThreeOrderOutMapper.queryCards(order);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int savePayfee(ThreeOrder order) {
		// TODO Auto-generated method stub
		//更新"新单"变成"已受理"状态
		this.wrThreeOrderOutMapper.updateOrderPayfee(order);
		//插入缴费记录
		String[] feePostArray = order.getFeePosts().split(",");
		int flag = 0;
		for(int i=0;i<feePostArray.length;i++){
			ThreeOrderPayfee payfee = new ThreeOrderPayfee();
			if(feePostArray[i].equals("1002")){
				payfee.setFeePost(feePostArray[i]);
				payfee.setFeeSum(order.getFeeSumYhzz());
				payfee.setPostBank(order.getPostBankYhzz());
				payfee.setBankFlowNum(order.getBankFlowNumYhzz());
				payfee.setPostUser(order.getPostUserYhzz());
			}else if(feePostArray[i].equals("1005")){
				payfee.setFeePost(feePostArray[i]);
				payfee.setFeeSum(order.getFeeSumPos());
				payfee.setBankFlowNum(order.getBankFlowNumPos());
				payfee.setPostNum(order.getPostNumPos());
			}else if(feePostArray[i].equals("1008")){
				payfee.setFeePost(feePostArray[i]);
				payfee.setFeeSum(order.getFeeSumRhPos());
				payfee.setPostTerminalNo(order.getPostTerminalNoRhPos());
				payfee.setBankFlowNum(order.getBankFlowNumRhPos());
			}else if(feePostArray[i].equals("1010")){
				payfee.setFeePost(feePostArray[i]);
				payfee.setFeeSum(order.getFeeSumLpk());
				payfee.setCardsNum(order.getCardNum());
			}else if(feePostArray[i].equals("1101")){
				payfee.setFeePost(feePostArray[i]);
				payfee.setFeeSum(order.getFeeSumOther());
				payfee.setCollectionEntity(order.getCollectionEntity());
				payfee.setPostUser(order.getPostUserOther());
			}
			payfee.setAccountsId(order.getAccountsId());
			payfee.setFeeToDate(order.getFeeToDate());
			payfee.setFeeType("1");
			payfee.setIsBackType("1");
			payfee.setFeeToDate(order.getFeeToDate());
			payfee.setAccountStatus("2");
			payfee.setCreateBy(order.getUserId());
			payfee.setUpdateBy(order.getUserId());
			payfee.setVersion(0L);
			payfee.setValid("1");
			payfee.setPayStatus("20010001");
			flag++;
			this.wrThreeOrderOutMapper.savePayfee(payfee);
		}
		return flag;
	}

	/**
	 * 查询导出订单列表
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryOrderList(ThreeOrder order) {
		// TODO Auto-generated method stub
		return reThreeOrderOutMapper.queryOrderList(order);
	}

	@Override
	public List<Map<String, Object>> queryOrderCodeList() {
		return reThreeOrderOutMapper.queryOrderCodeList();
	}
	
	@Override
	public Map<String, String> queryDeptName(Map<String, String> map) {
		return reThreeOrderOutMapper.queryDeptName(map);
	}
	
	@Override
	public List<String> queryThreeOrderCodeList() {
		// TODO Auto-generated method stub
		return reThreeOrderOutMapper.queryThreeOrderCodeList();
	}

	@Override
	public List<Map<String, Object>> loadBaseCity() {
		// TODO Auto-generated method stub
		return reThreeOrderOutMapper.loadBaseCity();
	}
	
	@Override
	public int updateByPrimaryKeySelective(ThreeOrder order) {
		// TODO Auto-generated method stub
		return wrThreeOrderOutMapper.updateByPrimaryKeySelective(order);
	}

	/**
	 * 校验字段，并更新到相应表中
	 */
	@Override
	public void insertOrder(Map<String, String> map, Long createBy,List<Map<String, Object>> orderTypes) throws Exception{
		ThreeOrder order = new ThreeOrder();
		String city = map.get("city");
		String orderCode = map.get("orderCode");
		//根据订单编号获取相应的orderItemServiceId
		Long orderItemServiceId = reThreeOrderOutMapper.queryIdByOrderCode(orderCode);
		String productName = map.get("productName");
		String userName = map.get("userName");
		String userMobile = map.get("userMobile");
		String receiverName = map.get("receiverName");
		String receiverMobile = map.get("receiverMobile");
		String typeText = map.get("typeText");
		String receiverProvince = map.get("receiverProvince");
		String receiverCity = map.get("receiverCity");
		String receiverArea = map.get("receiverArea");
		String receiverAddress = map.get("receiverAddress");
		String startTime = map.get("startTime");
		String endTime = map.get("endTime");
		String createTime = map.get("createTime");
		String personName = map.get("personName");
		String postId = map.get("postId");
		String totalPay = map.get("totalPay");
		String orderStatus = map.get("orderStatus");
		String remark = map.get("remark");
		String isrepeat = map.get("isrepeat");
		StringBuffer errormsg = new StringBuffer();
		if(isrepeat != null && isrepeat.equals("1")){//有重复直接跳出，进入下一循环
			return;
		}
		int flag = 0;
		if(StringUtils.isNotBlank(city)){
			order.setCity(city);
		}else{
			if(flag == 0){
				errormsg.append("该行的城市为空");
				flag = 1;
			}else{
				errormsg.append(",城市为空");
			}
		}
		if(StringUtils.isNotBlank(orderCode)){
			if(orderCode.getBytes().length <= 20){
				order.setOrderCode(orderCode);
			}else{
				if(flag == 0){
					errormsg.append("该行订单编号超出长度，请修改！");
					flag = 1;
				}
			}
		}else{
			if(flag == 0){
				errormsg.append("该行订单编号为空");
				flag = 1;
			}
		}
		if(StringUtils.isNotBlank(productName)){
			order.setProductName(productName);
		}else{
			if(flag == 0){
				errormsg.append("该行商品名称为空");
				flag = 1;
			}else{
				errormsg.append(",商品名称为空");
			}
		}
		if(StringUtils.isNotBlank(userName)){
			order.setUserName(userName);
		}else{
			if(flag == 0){
				errormsg.append("该行下单人姓名为空");
				flag = 1;
			}else{
				errormsg.append(",下单人姓名为空");
			}
		}
		if(StringUtils.isNotBlank(userMobile)){
			if(userMobile.getBytes().length <= 11){
				order.setUserMobile(userMobile);
			}else{
				if (flag == 0) {
					errormsg.append("该行的下单人号码太长");
					flag = 1;
				} else {
					errormsg.append(",下单人电话号码太长");
				}
			}
		}	
		if(StringUtils.isNotBlank(receiverName)){
			if(receiverName.getBytes().length <=100){
				order.setReceiverName(receiverName);
			}else{
				if(flag == 0){
					errormsg.append("该行的接收人姓名太长");
					flag = 1;
				}else{
					errormsg.append(",接收人姓名太长");
				}
			}
		}else{
			if(flag == 0){
				errormsg.append("该行的收货人为空");
				flag = 1;
			}else{
				errormsg.append(",收货人为空");
			}
		}
		if(StringUtils.isNotBlank(receiverMobile)){
			order.setReceiverMobile(receiverMobile);
		}else{
			if(flag == 0){
				errormsg.append("该行的收货人号码为空");
				flag = 1;
			}else{
				errormsg.append(",收货人号码为空");
			}
		}
		if(StringUtils.isNotBlank(typeText)) {
			String orderTypeId = null;
			for (Map<String, Object> dc : orderTypes) {
				if (dc.get("CNAME").equals(typeText)) {
					orderTypeId = dc.get("CODE").toString();
					break;
				}
			}
			if (orderTypeId != null) {
				order.setOrderType(orderTypeId);
				order.setTypeText(typeText);
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
		if (StringUtils.isNotBlank(receiverProvince)) {
			if(receiverProvince.getBytes().length <= 100){
				order.setReceiverProvince(receiverProvince);
			}else{
				if (flag == 0) {
					errormsg.append("该行的服务地址省份太长");
					flag = 1;
				} else {
					errormsg.append(",服务地址省份太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的服务地址省份为空");
				flag = 1;
			} else {
				errormsg.append(",服务地址省份为空");
			}
		}
		if (StringUtils.isNotBlank(receiverCity)) {
			if(receiverCity.getBytes().length <= 100){
				order.setReceiverCity(receiverCity);
			}else{
				if (flag == 0) {
					errormsg.append("该行的服务地址城市太长");
					flag = 1;
				} else {
					errormsg.append(",服务地址城市太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的服务地址城市为空");
				flag = 1;
			} else {
				errormsg.append(",服务地址城市为空");
			}
		}
		if (StringUtils.isNotBlank(receiverArea)) {
			if(receiverArea.getBytes().length <= 100){
				order.setReceiverArea(receiverArea);
			}else{
				if (flag == 0) {
					errormsg.append("该行的服务地址地区太长");
					flag = 1;
				} else {
					errormsg.append(",服务地址地区太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的服务地址区域为空");
				flag = 1;
			} else {
				errormsg.append(",服务地址区域为空");
			}
		}
		if (StringUtils.isNotBlank(receiverAddress)) {
			if(receiverAddress.getBytes().length <= 200){
				order.setReceiverAddress(receiverAddress);
			}else{
				if (flag == 0) {
					errormsg.append("该行的客户地址太长");
					flag = 1;
				} else {
					errormsg.append(",客户地址太长");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的客户地址为空");
				flag = 1;
			} else {
				errormsg.append(",客户地址为空");
			}
		}
		if (StringUtils.isNotBlank(startTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(startTime);

				order.setStartTime(startTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的服务开始时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",服务开始时间格式不正确");
				}

			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的服务开始时间为空");
				flag = 1;
			} else {
				errormsg.append(",服务开始时间为空");
			}
		}
		if (StringUtils.isNotBlank(endTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				sdf.parse(endTime);
				order.setEndTime(endTime);
			} catch (ParseException e) {
				// e.printStackTrace();
				// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
				if (flag == 0) {
					errormsg.append("该行的服务结束时间格式不正确");
					flag = 1;
				} else {
					errormsg.append(",服务结束时间格式不正确");
				}
			}
		} else {
			if (flag == 0) {
				errormsg.append("该行的服务结束时间为空");
				flag = 1;
			} else {
				errormsg.append(",服务结束时间为空");
			}
		}
		if(StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
				sdf.setLenient(false);
				Date startDate =sdf.parse(startTime);
				Date endDate =sdf.parse(endTime);
				if(endDate.before(startDate)){
					if (flag == 0) {
						errormsg.append("该行的服务开始时间不能大于服务结束时间,请修改为正确的时间");
						flag = 1;
					} else {
						errormsg.append(",服务开始时间不能大于服务结束时间");
					}
				}
			} catch (ParseException e) {
				 e.printStackTrace();
			}
		}
		if (StringUtils.isNotBlank(createTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
		if(StringUtils.isNotBlank(personName)){
			order.setPersonName(personName);
		}else{
			if(flag==0){
				errormsg.append("该行服务人员名称为空");
				flag = 1;
			}else{
				errormsg.append(",服务人员名称为空");
			}
		}
		if(StringUtils.isNotBlank(postId)){
			order.setPostId(postId);
		}else{
			if(flag==0){
				errormsg.append("该行快递单号为空");
				flag = 1;
			}else{
				errormsg.append(",快递单号为空");
			}
		}
		if(StringUtils.isNotBlank(totalPay)){
			order.setTotalPay(totalPay);
		}else{
			if(flag==0){
				errormsg.append("该行订单金额为空");
				flag = 1;
			}else{
				errormsg.append(",订单金额为空");
			}
		}
		if (StringUtils.isNotBlank(orderStatus)) {
			String orderStatusId = null;
			if(orderStatus.equals("新单")){
				orderStatusId = "1";
			}else if(orderStatus.equals("已受理")){
				orderStatusId = "2";
			}else if(orderStatus.equals("匹配中")){
				orderStatusId = "3";
			}else if(orderStatus.equals("已匹配")){
				orderStatusId = "4";
			}else if(orderStatus.equals("待面试")){
				orderStatusId = "5";
			}else if(orderStatus.equals("面试成功")){
				orderStatusId = "6";
			}else if(orderStatus.equals("已签约")){
				orderStatusId = "7";
			}else if(orderStatus.equals("已上户")){
				orderStatusId = "8";
			}else if(orderStatus.equals("已完成")){
				orderStatusId = "9";
			}else if(orderStatus.equals("已取消")){
				orderStatusId = "10";
			}else if(orderStatus.equals("已下户")){
				orderStatusId = "11";
			}else if(orderStatus.equals("已终止")){
				orderStatusId = "12";
			}else if(orderStatus.equals("捡货中")){
				orderStatusId = "13";
			}else if(orderStatus.equals("配送中")){
				orderStatusId = "14";
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
		if (StringUtils.isNotBlank(remark)) {
			order.setRemark(remark);
		}
		order.setCreateBy(createBy);
		order.setOrderItemServiceId(orderItemServiceId);
		if(flag == 0){
			//更新相应字段状态到order表
			wrThreeOrderOutMapper.updateByPrimaryKeySelective(order);
			//更新相应字段状态到order_item_service表
			wrThreeOrderOutMapper.updateItemServiceByOrderCode(order);
			map.put("success", "成功");
			map.put("errormsg", "");
		}else{
			errormsg.append(";");
			map.put("success", "失败");
			map.put("errormsg", errormsg.toString());
		}
	}

	/**
	 * 保存excel导入记录url
	 */
	@Override
	public void savaExcelRecord(String url, Long createBy,String fileName) {
		wrThreeOrderOutMapper.savaExcelRecord(url,createBy,fileName);
	}
	
	/**
	 * 保存excel导入记录url
	 */
	@Override
	public void savaOrderImportRecord(String url, Long createBy,String fileName) {
		wrThreeOrderOutMapper.savaOrderImportRecord(url,createBy,fileName);
	}

	/**
	 * 查询导入记录列表
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryThreeOrderOutRecord(ThreeOrder order, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reThreeOrderOutMapper.countThreeOrderOutRecord(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreeOrderOutMapper.queryThreeOrderOutRecord(order);
	}
	
	/**
	 * 查询订单导入记录列表
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrder> queryOrderImportRecord(ThreeOrder order, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reThreeOrderOutMapper.countOrderImportRecord(order));
		}
		order.setBeginRow(page.getFilterRecord().toString());
		order.setPageSize(page.getPageCount().toString());
		return this.reThreeOrderOutMapper.queryOrderImportRecord(order);
	}

	@Override
	/**
	 * 导出
	 */
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order,
			List<ThreeOrder> dataList, Long loginBy) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("三方订单订单列表");
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
		for(int i=0;i<dataList.size()+1;i++){
			sheet.createRow(i);
			for(int j=0;j<=20;j++){
				sheet.getRow(i).createCell(j);
			}
		}
        sheet.getRow(0).getCell(0).setCellValue("序号");
		sheet.getRow(0).getCell(1).setCellValue("*城市");
		sheet.getRow(0).getCell(2).setCellValue("*订单编号");
		sheet.getRow(0).getCell(3).setCellValue("*商品名称");
		sheet.getRow(0).getCell(4).setCellValue("*下单人姓名");
		sheet.getRow(0).getCell(5).setCellValue("*下单人电话");
		sheet.getRow(0).getCell(6).setCellValue("*接收人姓名");
		sheet.getRow(0).getCell(7).setCellValue("*接收人电话");
		sheet.getRow(0).getCell(8).setCellValue("*需求类型");
		sheet.getRow(0).getCell(9).setCellValue("*服务地址省份");
		sheet.getRow(0).getCell(10).setCellValue("*服务地址城市");
		sheet.getRow(0).getCell(11).setCellValue("*服务地址地区");
		sheet.getRow(0).getCell(12).setCellValue("*客户地址");
		sheet.getRow(0).getCell(13).setCellValue("*服务开始时间");
		sheet.getRow(0).getCell(14).setCellValue("*服务结束时间");
		sheet.getRow(0).getCell(15).setCellValue("*创建时间");
		sheet.getRow(0).getCell(16).setCellValue("*服务人员");
		sheet.getRow(0).getCell(17).setCellValue("*快递单号");
		sheet.getRow(0).getCell(18).setCellValue("*订单金额");
		sheet.getRow(0).getCell(19).setCellValue("*订单状态");
		sheet.getRow(0).getCell(20).setCellValue("备注");
		for (int i = 0,j=1; i < dataList.size(); i++,j++) {
			
			sheet.getRow(j).getCell(1).setCellValue(dataList.get(i).getCity()!=null ? dataList.get(i).getCity() : null);
			sheet.getRow(j).getCell(2).setCellValue(dataList.get(i).getOrderCode()!=null ? dataList.get(i).getOrderCode() : null);
			sheet.getRow(j).getCell(3).setCellValue(dataList.get(i).getProductName()!=null ? dataList.get(i).getProductName() : null);
			sheet.getRow(j).getCell(4).setCellValue(dataList.get(i).getUserName()!=null ? dataList.get(i).getUserName() : null);
			sheet.getRow(j).getCell(5).setCellValue(dataList.get(i).getUserMobile()!=null ? dataList.get(i).getUserMobile() : null);
			sheet.getRow(j).getCell(6).setCellValue(dataList.get(i).getReceiverName()!=null ? dataList.get(i).getReceiverName() : null);
			sheet.getRow(j).getCell(7).setCellValue(dataList.get(i).getReceiverMobile()!=null ? dataList.get(i).getReceiverMobile() : null);
			sheet.getRow(j).getCell(8).setCellValue(dataList.get(i).getTypeText()!=null ? dataList.get(i).getTypeText() : null);
			/*sheet.getRow(j).getCell(9).setCellValue(dataList.get(i).getCateType()!=null ? list.get(i).getRechargeDeptText() : null);
			sheet.getRow(j).getCell(10).setCellValue(dataList.get(i).getOrderType()!=null ? list.get(i).getStatusText() : null);*/
			sheet.getRow(j).getCell(9).setCellValue(dataList.get(i).getReceiverProvince()!=null ? dataList.get(i).getReceiverProvince() : null);
			sheet.getRow(j).getCell(10).setCellValue(dataList.get(i).getReceiverCity()!=null ? dataList.get(i).getReceiverCity() : null);
			sheet.getRow(j).getCell(11).setCellValue(dataList.get(i).getReceiverArea()!=null ? dataList.get(i).getReceiverArea() : null);
			sheet.getRow(j).getCell(12).setCellValue(dataList.get(i).getReceiverAddress()!=null ? dataList.get(i).getReceiverAddress() : null);
			sheet.getRow(j).getCell(13).setCellValue(dataList.get(i).getStartTime()!=null ? dataList.get(i).getStartTime() : null);
			sheet.getRow(j).getCell(14).setCellValue(dataList.get(i).getEndTime()!=null ? dataList.get(i).getEndTime() : null);
			sheet.getRow(j).getCell(15).setCellValue(dataList.get(i).getCreateTime()!=null ? dataList.get(i).getCreateTime() : null);
			sheet.getRow(j).getCell(16).setCellValue(dataList.get(i).getPersonName()!=null ? dataList.get(i).getPersonName() : null);
			sheet.getRow(j).getCell(17).setCellValue(dataList.get(i).getPostId()!=null ? dataList.get(i).getPostId() : null);
			sheet.getRow(j).getCell(18).setCellValue(dataList.get(i).getTotalPay()!=null ? dataList.get(i).getTotalPay() : null);
			sheet.getRow(j).getCell(19).setCellValue(dataList.get(i).getOrderStatus()!=null ? dataList.get(i).getOrderStatus() : null);
			sheet.getRow(j).getCell(20).setCellValue(dataList.get(i).getRemark()!=null ? dataList.get(i).getRemark() : null);
			sheet.setColumnWidth(1, 2000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 3000);
			sheet.setColumnWidth(8, 3000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 3000);
			sheet.setColumnWidth(13, 3000);
			sheet.setColumnWidth(14, 3000);
			sheet.setColumnWidth(15, 3000);
			sheet.setColumnWidth(16, 3000);
			sheet.setColumnWidth(17, 3000);
			sheet.setColumnWidth(18, 2000);
			sheet.setColumnWidth(19, 3000);
			sheet.setColumnWidth(20, 3000);
		}
		return workbook;
		
	}


}
