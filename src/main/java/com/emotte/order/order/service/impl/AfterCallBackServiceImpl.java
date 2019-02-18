package com.emotte.order.order.service.impl;

import com.emotte.order.order.mapper.reader.ReAfterCallBackMapper;
import com.emotte.order.order.mapper.reader.ReAfterSalesMapper;
import com.emotte.order.order.mapper.writer.WrAccountPayMapper;
import com.emotte.order.order.mapper.writer.WrAfterCallBackMapper;
import com.emotte.order.order.mapper.writer.WrOrderAfterSalesMapper;
import com.emotte.order.order.model.AfterCallBack;
import com.emotte.order.order.model.OrderAfterSales;
import com.emotte.order.order.service.AfterCallBackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2018/12/26.
 */
@Service
public class AfterCallBackServiceImpl implements AfterCallBackService{

    @Resource
    private ReAfterCallBackMapper reAfterCallBackMapper;

    @Resource
    private WrAfterCallBackMapper wrAfterCallBackMapper;

    @Resource
    private ReAfterSalesMapper reAfterSalesMapper;

    @Resource
    private WrOrderAfterSalesMapper wrOrderAfterSalesMapper;

    @Resource
    private WrAccountPayMapper wrAccountPayMapper;

    /**
     * 根据订单ID，售后单ID，查询回访信息列表
     *
     * @param afterCallBack
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    @Override
    public List<AfterCallBack> findCallBackList(AfterCallBack afterCallBack) {
        List<AfterCallBack> afterCallBacks = reAfterCallBackMapper.findCallBackList(afterCallBack);
        return afterCallBacks;
    }

    /**
     * 查询售后单银行账户信息
     *
     * @param afterSalesId
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    @Override
    public OrderAfterSales findCustomerByAfterSalesId(Long afterSalesId) {
        OrderAfterSales orderAfterSales = reAfterSalesMapper.findCustomerByAfterSalesId(afterSalesId);
        return orderAfterSales;
    }

    /**
     * 保存回访信息
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    @Override
    public void addCallBack(AfterCallBack afterCallBack) {
        /**
         * 判断回访结果
         *      1、回访成功，修改售后单审核状态为审核中
         *      2、回访失败，修改售后单审核状态为回访失败
         *      3、继续回访，无修改
         */
        Integer status = afterCallBack.getStatus();
        if(status == 1){//回访成功，修改售后单/结算单审核状态为审核中
            wrOrderAfterSalesMapper.updateAuditFlagByCallBackStatus(afterCallBack.getAfterSalesId(),"20130004",afterCallBack.getUpdateBy());
            wrAccountPayMapper.updateAuditFlagByCallBackStatus(afterCallBack.getAccountId(),"20130004",afterCallBack.getUpdateBy());
        }else if(status == 2){//回访失败，修改售后单/结算单审核状态为回访失败,本次及之前的回访记录置为历史记录
            wrOrderAfterSalesMapper.updateAuditFlagByCallBackStatus(afterCallBack.getAfterSalesId(),"20130015",afterCallBack.getUpdateBy());
            wrAccountPayMapper.updateAuditFlagByCallBackStatus(afterCallBack.getAccountId(),"20130015",afterCallBack.getUpdateBy());
            //更新之前的回访记录为历史数据
            wrAfterCallBackMapper.updateToHistory(afterCallBack.getAfterSalesId(),afterCallBack.getOrderId(),afterCallBack.getUpdateBy());
            afterCallBack.setIsOld(1);
        }
        wrAfterCallBackMapper.addCallBack(afterCallBack);
    }

    /**
     * 根据ID查询回访信息
     *
     * @param id
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    @Override
    public AfterCallBack findOneById(Long id) {
        AfterCallBack afterCallBack = reAfterCallBackMapper.findOneById(id);
        return afterCallBack;
    }

    /**
     * 更新
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    @Override
    public void update(AfterCallBack afterCallBack) {
        wrAfterCallBackMapper.update(afterCallBack);
    }
}
