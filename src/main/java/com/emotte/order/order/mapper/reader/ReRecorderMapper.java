package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.Recorder;

@Component("reRecorderMapper")
public interface ReRecorderMapper extends ReBaseMapper {

	public Recorder loadRecorder(Long id);

	public List<Recorder> queryRecorder(Recorder recorder);

	public Integer countRecorder(Recorder recorder);

	public List<Recorder> queryRecorderInfo();

}