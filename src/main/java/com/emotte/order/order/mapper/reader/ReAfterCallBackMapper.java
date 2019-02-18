package com.emotte.order.order.mapper.reader;

import com.emotte.order.order.model.AfterCallBack;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import java.util.List;

@Component("reAfterCallBackMapper")
public interface ReAfterCallBackMapper extends ReBaseMapper {

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
     * 根据ID查询回访信息
     *
     * @param id
     * @return
     * @Author zhanghao
     * @Date 20181227
     */
    AfterCallBack findOneById(Long id);
}