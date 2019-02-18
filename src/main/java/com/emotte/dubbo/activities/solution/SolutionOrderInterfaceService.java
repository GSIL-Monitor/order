package com.emotte.dubbo.activities.solution;

public interface SolutionOrderInterfaceService {
	/**
	 * 套餐修改接口
	 * @param jsonstr
	 * @return
	 * @author sunruiying
	 */
	public String solutionItemUpdate(String jsonstr);
	/**
	 * 我的解决方案暂停配接口
	 * @param jsonstr
	 * @return
	 */
	public String suspendSoluItem(String jsonstr);
	/**
	 * 增加排期接口
	 * @param jsonstr
	 * @return
	 */
	public String increasePlan(String jsonstr);
	/**
	 * 减少排期接口
	 * @param jsonstr
	 * @return
	 */
	public String reducePlan(String jsonstr);
	/**
	 * 排期修改接口
	 * @param jsonstr
	 * @return
	 */
	public String planUpdate(String jsonstr);
	/**
	 * 解决方案下单接口
	 * @param jsonstr
	 * @return
	 */
	public String insertSolutionOrder(String jsonstr);
	/**
	 * 更新解决方案代扣卡
	 * @param jsonstr
	 * @return
	 */
	public String updateSolutionFeeCardNum(String jsonstr);
	/**
	 * 解决方案订单套餐添加
	 * @param jsonstr
	 * @return
	 */
	public String solutionOrderItemAdd(String jsonstr);
	/**
	 * 开启服务接口（用于前端app我要服务功能）
	 * @param jsonstr
	 * @return
	 */
	public String startService(String jsonstr);
	
	/**
	 * 修改解决方案状态
	 * @param jsonstr
	 * @return
	 */
	public String updateCustSolutionStatus(String jsonstr);
	
	/**
	 * 删除解决方案订单
	 * @param jsonstr
	 * @return
	 */
	public String delectCustSolution(String jsonstr);

	/**
	 * 解决方案执行订单取消
	 * （执行订单对应排期删除，当前排期总金额退入解决方案订单的可用余额）
	 * @param jsonstr
	 * @return
	 */
	public String cancelSolutionPlanOrder(String jsonstr);
	/**
	 * 我的解决方案暂停配接口
	 * @param jsonstr
	 * @return
	 */
	public String suspendSolution(String jsonstr);
	/**
	 * 生成解决方案执行订单接口
	 * 注：（调用定时器生成执行订单方法）
	 * @return
	 */
	public String createSolutionPlanOrderNew(String jsonstr);
}
