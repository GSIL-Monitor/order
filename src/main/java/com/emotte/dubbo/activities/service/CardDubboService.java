package com.emotte.dubbo.activities.service;

public interface CardDubboService {

	/**
	 * 根据商品code,分类code,地区code,用户id/用户手机号查询可用卡券
	 * @return
	 */
	public String getTicketsByCodeAndMobile(String json);
	
	
	/**
	 * 根据手机号查询可用/不可用券
	 */
	public String getTicketsByMobile(String json);
	
	/**
	 * 查询用户优惠券列表.productList和status作为筛选参数,优惠券需满足所有的商品才是可用优惠券,否则归为不可用优惠券;若productList为空,则不需要对商品做筛选.要做分页
	 * @param json
	 * @return
	 */
	public String getTicketsByProductCodes(String json);
	
	/**
	 * 查询用户优惠券可用和不可用数量.productList作为筛选参数,优惠券需满足所有的商品才是可用优惠券,否则归为不可用优惠券;若productList为空,则不需要对商品做筛选
	 * @param json
	 * @return
	 */
	public String getUseableTicketCounts(String json);
	
	/**
	 * 查询用户管家卡列表.productList和status作为筛选参数,管家卡卡需满足所有的商品才是可用,否则归为不可用;若productList为空,则不需要对商品做筛选.要做分页
	 * @param json
	 * @return
	 */
	public String getCardByProductCodes(String json);
	
	/**
	 * 询用户管家卡可用和不可用数量.productList作为筛选参数,管家卡需满足所有的商品才是可用,否则归为不可用;若productList为空,则不需要对商品做筛选
	 * @param json
	 * @return
	 */
	public String getUseableCardCounts(String json);
	
	/**
	 * 查询用户管家卡,可用的管家卡
	 * @param json
	 * @return
	 */
	public String getCardByProductCodesAndMobile(String json);
}
