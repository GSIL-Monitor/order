package com.emotte.order.warehouse.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.warehouse.model.UploadLog;

@Component("reUploadLogMapper")
public interface ReUploadLogMapper extends ReBaseMapper {

	public UploadLog loadUploadLog(Long id);

	public List<UploadLog> queryUploadLoglistPage(UploadLog uploadLog);

	public Integer countUploadLog(UploadLog uploadLog);

}