package com.emotte.order.warehouse.service;

import java.util.List;
import com.emotte.order.warehouse.model.UploadLog;
import org.wildhorse.server.core.model.Page;

public interface UploadLogService {

	public UploadLog loadUploadLog(Long id);

	public List<UploadLog> queryUploadLog(UploadLog uploadLog, Page page);

	public void insertUploadLog(UploadLog uploadLog);

	public void updateUploadLog(UploadLog uploadLog);

}