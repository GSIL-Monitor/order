package com.emotte.order.order.mapper.writer;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderImport;

@Component("wrOrderMapper")
public interface WrOrderMapper extends WrBaseMapper {

    public void insertOrder(Order order);

    public int updateOrder(Order order);

    public int updateOrderFollow(Order order);

    public int updateOrderFollowUpdate(Order order);

    public int updateOrderFollowUpdate_solution(Order order);

    /**
     * 更新订单可用余额
     *
     * @param orderId
     * @param accountAmount
     * @return 2017年6月9日
     */
    public int updateTotalPay(Long orderId, Double accountAmount);

    /**
     * 订单可用余额记录
     *
     * @param map
     * @return 2017年9月14日
     */
    public int inserOrderTotalPayLog(Map<String, Object> map);

    public void saveOrderImport(OrderImport order);

    public void saveOrderImportItem(OrderImport order);

    public void saveOrderImportDetailServer(OrderImport order);

    /**
     * 切换发服务人员服务费方式
     *
     * @param order
     * @return
     */
    public int updateSalaryStatus(Order order);

    /**
     * 修改服务人员状态
     *
     * @param status      状态
     * @param personnelId 服务人员ID
     */
    void updatePersonnalStatus(@Param("status") Long status, @Param("personnelId") Long personnelId);

    public int updateOrderHandover(Order order);

    public int updateSolutionHandover(Order order);

    /**
     * 根据订单ID修改订单状态【order_status】为【已取消】
     *
     * @param id    订单ID
     * @param orderStatus   订单状态
     * @auther: lenovo
     * @date: 2018/7/16 11:28
     */
    void chengeOrderStauts(@Param("id") Long id, @Param("orderStatus") String orderStatus);
}