package com.emotte.order.order.mapper.writer;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.model.ThreeOrderAccounts;
import com.emotte.order.order.model.ThreeOrderAddress;
import com.emotte.order.order.model.ThreeOrderPayfee;
import com.emotte.order.order.model.ThreeOrderUser;

@Component("wrThreeOrderOutMapper")
public interface WrThreeOrderOutMapper extends WrBaseMapper{
	
	public int saveOrderAccounts(ThreeOrderAccounts accounts);
	
	public int saveAddress(ThreeOrderAddress order);
	
	public int updateAddress(ThreeOrderAddress address);

	public int saveOrderUser(ThreeOrderUser orderUser);

	public int insertThreeOrder(ThreeOrder order);

	public int insertThreeOrderItem(ThreeOrder order);

	public int insertThreeOrderItemDetailServer(ThreeOrder order);

	public int doMatch(List<String> list);
	
	public int doBilling(List<String> orderIdList);

	public int saveMatch(ThreeOrder order);

	public int matchFail(ThreeOrder order);
	
	public int updateOrderPayfee(ThreeOrder order);

	public int savePayfee(ThreeOrderPayfee payfee);
	
	public int updateByPrimaryKeySelective(ThreeOrder order);

	public void savaExcelRecord(@Param("url")String url, @Param("createBy")Long createBy,@Param("fileName")String fileName);
	
	public void savaOrderImportRecord(@Param("url")String url, @Param("createBy")Long createBy,@Param("fileName")String fileName);

	public void updateItemServiceByOrderCode(ThreeOrder order);

}
