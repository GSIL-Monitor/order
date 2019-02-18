package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ThreeOrder;

@Component("reThreePartyOrderMapper")
public interface ReThreePartyOrderMapper extends ReBaseMapper{

	
	/*
	 * 查询三方订单（对内）
	 */
	public  List<ThreeOrder> queryPartyOrder(ThreeOrder order);
	/*
	 * 查询三方订单（对内）单条数据
	 */
	public List<ThreeOrder> loadPartyOrderId(ThreeOrder order);
	/*
	 *  查询三方订单（对内）条数
	 */
	public Integer countPartyOrder(ThreeOrder order);
	
	/*
	 * 查询三方订单（对内）负责部门
	 */
	public List<ThreeOrder> queryPartyOrderDeptName(ThreeOrder order); 
	/*
	 * 通过条件进行导出
	 */
	public List<ThreeOrder> queryPartyOrderList(ThreeOrder order);
}
