package com.emotte.order.order.service.impl;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.reader.ReThreeOrderCustomerMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderMapper;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.service.ThreeOrderFaService;
import com.emotte.order.order.service.ThreeOrderService;

@Service("threeOrderFaService")
@Transactional
public class ThreeOrderFaServiceImpl implements ThreeOrderFaService {
	
	@Resource
	private ReThreeOrderCustomerMapper re_t_o_c_Mapper;
	
	@Resource
	private WrThreeOrderMapper wr_t_o_mapper;
	@Resource
	private ThreeOrderService threeOrderService;
	
	@Override
	public Long insertThreeOrderFa(ThreeOrderInModel order) {
		/*// 取用户信息
		ThreeOrderCustomerModel user = new ThreeOrderCustomerModel();
		user.setValid(1);
		user.setId(order.getUserId());
		user = this.re_t_o_c_Mapper.getCustomer(user);
		//用户
		order.setUserId(user.getUserId());
		order.setUserName(user.getRealName());
		order.setUserProvince(user.getUserProvince());
		order.setUserCity(user.getUserCity());
		order.setUserCountry(user.getUserDistrict());
		order.setUserAddress(user.getUserAddress());
		order.setUserMobile(user.getUserMobile());
		order.setUserEmail(user.getUserEmail());
		order.setUserSex(user.getUserSex());
		order.setUserBirth(user.getUserBirth());
		order.setUserCityCode(user.getUserCityCode());
		//接收人
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
		order.setReceiverAddress(userAddr.getAddressDetail());
		order.setLongitude(userAddr.getLongitude());
		order.setLatitude(userAddr.getLatitude());
		order.setReceiverCityCode(userAddr.getCityCode());*/
		//常用信息
		DateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();

		order.setCreateTime(dfDateFormat.format(date));
		order.setOrderStatus(order.getOrderStatus()==null?1:order.getOrderStatus());
		order.setVersion(1L);
		
    	this.wr_t_o_mapper.insertThreeOrder(order);
    	return order.getId();
	}

}
