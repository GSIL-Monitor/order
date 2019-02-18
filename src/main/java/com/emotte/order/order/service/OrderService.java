package com.emotte.order.order.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.wildhorse.server.core.model.Page;

import com.emotte.kernel.exception.BaseException;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Managers;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.Serial;

public interface OrderService{
    
	public Order loadOrder(Long id);
	
	public Order loadOrderByOrder(Order order);
	
	public List<Order> queryOrder(Order order,Page page);
	
	public List<Order> queryOrderNoPage(Order order);
	
	public Order insertOrder(Order order);
	
    public boolean updateOrder(Order order);
    
    public boolean updateOrder2(Order order);
    /**
     * 取订单ID方法 
     * @param index 传入0，防止死循环,如果递归50次还没成功取出，返回出错
     * @return
     */
    public Serial getOrderSerail(int index);
 // 订单编号生成
    public String createOrderTest();

	public List<Order> queryOrderBasicItem(Long id);
	
	//考勤管理-服务人员服务费填报(查询列表)
	public List<Order> queryAttendanceOrder(Order order, Page page);
	
	// 查询门店，基地
	public StringBuffer queryFollowDept(String data);
	// 查询管家
	public StringBuffer queryFollowBy(Dictionary dictionary);
	//单次服务订单的slice保存
	public void insertOrderSlice(List<Order> orderList);
	//单次服务订单的服务人员服务费分账
	public void updateOrderSalary(List<Order> orderList);
	
	public Boolean updateSolutionOrders(Order order);
	//查询系统时间
	public String querySysdate();
	//查询服务人员
	public List<Map<String, Object>> Person(Order order, Page page);
	//导出
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response,Order order,List<Order> list,Long updateBy);

	/**
	 * 更新订单可用余额
	 * @param pay
	 * @return
	 * 2017年6月9日
	 */
	public Boolean updateOrderTotalPay(AccountPay pay) throws BaseException;
	/**
	 * 根据用户ID查询订单
	 * @param id
	 * @return
	 */
	public List<Order> queryOrderByUserId(Long userId);
	/**
	 * 查询客户所有符合条件的缴费
	 * @return
	 */
	public List<Map<String, Object>> queryOrderUserAllFee();
	
	/**
	 * 查询渠道订单列表
	 * @param order
	 * @param page
	 * @return
	 */
	public List<Order> queryChannelOrder(Order order, Page page);
	/**
	 * 根据订单ID查询服务开始时间
	 * @param orderId
	 * @return
	 */
	public String queryServiceStartTime(Long orderId);
	/**
	 * 查询登录用户渠道经理类型
	 * @param id
	 * @return
	 */
	public Integer queryChannelManagerType(Long id);
	/**
	 * 查询呼叫中心所有人员
	 * @return
	 */
	public List<Managers> queryCallCenter();
	/**
	 * 减少库存
	 * @param orderId 
	 * @return
	 */
	public String  reduceInventory(String cityCode,String cateCode,String selectionTime,String startTime,String endTime,Integer num,Long orderId);
	/**
	 * 增加库存
	 * @return
	 */
	public String  increaseInventory(String cityCode,String cateCode,String selectionTime,String startTime,String endTime,Integer num,Long orderId);
	/**
	 * 查询符合条件的管家帮分期缴费客户ID和费用
	 * @return
	 */
	public List<Map<String, Object>> queryUserGJBInstallmentFee();
	/**
	 * 查询当前订单服务人员下一订单负责人
	 *
	 */
	public List<ItemInterview> queryRechargeBy(ItemInterview itemInterview);
	/**
	 * 查询当前订单服务人员是否与下一个订单冲突
	 *
	 */
	public List<ItemInterview> queryByConflict(ItemInterview itemInterview);

	/**
	 * 根据订单编号和品类查询订单
	 * @param orderCode 订单编号
	 * @param category 品类名称
	 * @return	
	 * 2017年9月10日
	 */
	public Order loadOrderByCodeAndCategory(Long orderCode, String category);
	/**
	 * 根据客户电话，查询订单
	 * @param order
	 * @return
	 */
	public List<Order> queryChangeOrder(Order order);

	public List<Map<String, Object>> orderEmpPushRminds();

	public String downloadEmpFileCard(Long empId,String path);
	
	public Map<String, String> insertImportOrderNew(Map<String, String> map, Long createBy,Long createDept,  List<Map<String, Object>> dictionary,List<Map<String, Object>> authManager,List<Map<String, Object>> proviceCityAreaMap)throws Exception;
	public void insertImportOrder(Map<String, String> map, Long createBy,Long createDept,  List<Map<String, Object>> dictionary,List<Map<String, Object>> authManager)throws Exception;
	
	/**
	 * 切换发服务人员服务费方式
	 * @param order
	 */
	public void updateSalaryStatus(Order order);
	/**
	 * 根据客户电话，查询订单负责人、负责部门
	 * @param userMobile
	 * @return
	 */
	public List<Order> queryOrderByUserMobile(String userMobile);

	/**
	 * 查询解决方案负责人和负责部门
	 * @param orderId 
	 * @return
	 */
	public Map<String, Object> querySolutionResponsiblePerson(Long orderId);
	
	/**
	 * 根据订单ID，查询缴费金额是否大于订单的金额
	 * @param orderId  订单ID
	 * @return  true：缴费金额大于订单金额， false:缴费金额小于订单金额
	 * 操作人：周鑫
	 * 2018年10月31日下午3:43:19
	 */
	public boolean  queryPayCompareFeeSum(Long orderId);
	
	/**
	 * 查询订单的有效合同（T.Agreement_state IN(2,3) 是否与当前时间在30天之内
	 * @param  orderId 订单ID
	 * 操作人：周鑫
	 * 2018年11月14日下午2:42:21
	 */
	public boolean queryAgreenmentDate(Long orderId);
	
	/**
	 * 查询，录入订单的相关回显信息
	 * 操作人：周鑫
	 */
	public Map<String, Object> queryOrderEcho(Long orderId);
}