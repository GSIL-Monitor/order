package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.AfterCallBack;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrAfterCallBackMapper")
public interface WrAfterCallBackMapper extends WrBaseMapper {

    /**
     * 保存回访信息
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    void addCallBack(AfterCallBack afterCallBack);

    /**
     * 更新
     *
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    void update(AfterCallBack afterCallBack);

    /**
     * 更新回访记录为历史数据
     *
     *
     * @param updateBy
     * @param afterSalesId
     * @param orderId
     * @Author zhanghao
     * @Date 20190104
     */
    void updateToHistory(@Param("afterSalesId") Long afterSalesId, @Param("orderId") Long orderId,@Param("updateBy") Long updateBy);
}