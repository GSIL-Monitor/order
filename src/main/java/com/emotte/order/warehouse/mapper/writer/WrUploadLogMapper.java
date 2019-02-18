package com.emotte.order.warehouse.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.warehouse.model.UploadLog;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrUploadLogMapper")
public interface WrUploadLogMapper extends WrBaseMapper {

	public void insertUploadLog(UploadLog uploadLog);

	public int updateUploadLog(UploadLog uploadLog);

}