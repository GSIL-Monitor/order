package com.emotte.order.order.service;

import com.emotte.order.order.model.AfterCallBack;
import com.emotte.order.order.model.OrderAfterSales;

import java.util.List;

/**
 * Created by lenovo on 2018/12/26.
 */
public interface AfterCallBackService {

    /**
     * 根据订单ID，售后单ID，查询回访信息列表
     *
     * @param afterCallBack
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    List<AfterCallBack> findCallBackList(AfterCallBack afterCallBack);

    /**
     * 查询售后单银行账户信息
     *
     * @param afterSalesId
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    OrderAfterSales findCustomerByAfterSalesId(Long afterSalesId);

    /**
     * 保存回访信息
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    void addCallBack(AfterCallBack afterCallBack);

    /**
     * 根据ID查询回访信息
     *
     * @param id
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    AfterCallBack findOneById(Long id);

    /**
     * 更新
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    void update(AfterCallBack afterCallBack);
}
