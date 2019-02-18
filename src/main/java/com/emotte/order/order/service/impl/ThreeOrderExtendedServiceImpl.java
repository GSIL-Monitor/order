package com.emotte.order.order.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.mapper.writer.WrThreeOrderItemDetailMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderItemMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderMapper;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.model.ThreeOrderItemDetailServer;
import com.emotte.order.order.service.ThreeOrderExtendedService;

@Service("threeOrderExtendedService")
@Transactional
public class ThreeOrderExtendedServiceImpl implements ThreeOrderExtendedService {
	
	@Resource
	private WrThreeOrderMapper orderExtendedMapper;
	@Resource
	private WrThreeOrderItemMapper orderItemExtendedMapper;
	@Resource
	private WrThreeOrderItemDetailMapper orderItemDetailMapper;
	@Override
	public Long insertThreeOrder(ThreeOrderInModel order) {
		//常用信息
		DateFormat dfDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = Calendar.getInstance().getTime();
		
		order.setCreateTime(dfDateFormat.format(date));
		order.setOrderStatus(order.getOrderStatus()==null?1:order.getOrderStatus());
		order.setVersion(0L);
    	this.orderExtendedMapper.insertThreeOrder(order);
    	return order.getId();
	}

	@Override
	public Long insertThreeOrderItem(Item item) {
		this.orderItemExtendedMapper.insertThreeOrderItem(item);
		return item.getId();
	}

	@Override
	public void insertItemDetailServer(ThreeOrderItemDetailServer threeOrderItemDetailServer) {
		this.orderItemDetailMapper.insertThreeOrderItemDetail(threeOrderItemDetailServer);
	}

}
