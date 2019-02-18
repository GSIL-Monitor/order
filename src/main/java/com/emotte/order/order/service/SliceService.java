package com.emotte.order.order.service;

import java.util.List;

import com.emotte.order.order.model.Slice;

import org.wildhorse.server.core.model.Page;

public interface SliceService {

	public Slice loadSlice(Long id);

	public List<Slice> querySlice(Slice slice, Page page);

	public void insertSlice(Slice slice);

	public void updateSlice(Slice slice);
	
	
	
	/**
	 * 插入订单分层信息方法
	 * @param orderId 	订单ID
	 * @param isOther 	分层类型 （1，三方合作分层，2，易盟二次分账分层）
	 * @param personId 	服务人员ID（三方合作的时候，没有此id，延续性服务二次分账的时候，需要记录此ID）
	 * @param month		分层月份
	 * @param productCode  产品code码
	 * @param priceType    价格体系
	 * @return 返回是否完成 成功返回success  其余为失败
	 */
	public String insertSliceByOther(String orderId,int isOther,String personId,String productCode,String priceType,Long createBy);  

}