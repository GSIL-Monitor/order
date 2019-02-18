package com.emotte.order.order.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.PushLog;
import com.emotte.order.order.mapper.reader.RePushLogMapper;
import com.emotte.order.order.mapper.writer.WrPushLogMapper;
import com.emotte.order.order.service.PushLogService;

@Service("pushLogService")
@Transactional
public class PushLogServiceImpl implements PushLogService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private RePushLogMapper rePushLogMapper;

	@Resource
	private WrPushLogMapper wrPushLogMapper;

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public PushLog loadPushLog(Long id) {
		return this.rePushLogMapper.loadPushLog(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<PushLog> queryPushLog(PushLog pushLog, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(rePushLogMapper.countPushLog(pushLog));
		}
		pushLog.setBeginRow(page.getFilterRecord().toString());
		pushLog.setPageSize(page.getPageCount().toString());
		return this.rePushLogMapper.queryPushLog(pushLog);
	}

	public void insertPushLog(PushLog pushLog) {
		this.wrPushLogMapper.insertPushLog(pushLog);
	}

	public void updatePushLog(PushLog pushLog) {
		int returnValue;
		try {
			returnValue = this.wrPushLogMapper.updatePushLog(pushLog);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updatePushLog:" + e);
			throw new BaseException(e);
		}
	}

}
