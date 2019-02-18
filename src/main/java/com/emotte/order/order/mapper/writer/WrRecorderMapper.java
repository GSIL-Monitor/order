package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Recorder;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrRecorderMapper")
public interface WrRecorderMapper extends WrBaseMapper {

	public void insertRecorder(Recorder recorder);

	public int updateRecorder(Recorder recorder);

}