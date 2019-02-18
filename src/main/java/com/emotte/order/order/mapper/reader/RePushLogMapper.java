package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.PushLog;

@Component("rePushLogMapper")
public interface RePushLogMapper extends ReBaseMapper {

	public PushLog loadPushLog(Long id);

	public List<PushLog> queryPushLog(PushLog pushLog);

	public Integer countPushLog(PushLog pushLog);

}