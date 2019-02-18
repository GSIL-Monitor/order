package com.emotte.order.order.service;

import java.util.List;
import com.emotte.order.order.model.PushLog;
import org.wildhorse.server.core.model.Page;

public interface PushLogService {

	public PushLog loadPushLog(Long id);

	public List<PushLog> queryPushLog(PushLog pushLog, Page page);

	public void insertPushLog(PushLog pushLog);

	public void updatePushLog(PushLog pushLog);

}