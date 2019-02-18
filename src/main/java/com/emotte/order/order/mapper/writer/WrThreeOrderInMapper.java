package com.emotte.order.order.mapper.writer;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.ThreeOrderIn;

@Component("wrThreeOrderInMapper")
public interface WrThreeOrderInMapper extends WrBaseMapper {

	public void insertOrder(Order order);

	public int updateOrder(Order order);

	/**
	 * 
	 * saveThreeOrderIn(这里用一句话描述这个方法的作用)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * 
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 *
	 * 
	 * @Title: saveThreeOrderIn
	 * @Description: TODO
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void saveThreeOrderIn(List<Order> orders);

	public void saveThreeOrderInItem(List<Order> orders);

	public void saveThreeOrderInServerDetail(List<Order> orders);

	public void saveThreeOrder(ThreeOrderIn order);
	
	public void saveThreeOrderItem(ThreeOrderIn order);
	
	public void saveThreeOrderServerDetailServer(ThreeOrderIn order);

	public void saveFaItemDetail(ThreeOrderIn order);

	public void saveUser(ThreeOrderIn order);

	public void savaExcelRecord(@Param("url")String url, @Param("createBy")Long createBy,@Param("fileName") String fileName);

	public void saveReceiver(ThreeOrderIn order);

}