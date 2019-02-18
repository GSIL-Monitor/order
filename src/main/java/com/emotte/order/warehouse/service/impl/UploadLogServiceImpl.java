package com.emotte.order.warehouse.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.warehouse.model.UploadLog;
import com.emotte.order.warehouse.mapper.reader.ReUploadLogMapper;
import com.emotte.order.warehouse.mapper.writer.WrUploadLogMapper;
import com.emotte.order.warehouse.service.UploadLogService;

@Service("uploadLogService")
@Transactional
public class UploadLogServiceImpl implements UploadLogService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReUploadLogMapper reUploadLogMapper;

	@Resource
	private WrUploadLogMapper wrUploadLogMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public UploadLog loadUploadLog(Long id) {
		return this.reUploadLogMapper.loadUploadLog(id);
	}
	/**
	 * 查询批量发货记录
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<UploadLog> queryUploadLog(UploadLog uploadLog, Page page) {
		return this.reUploadLogMapper.queryUploadLoglistPage(uploadLog);
	}

	@Override
	public void insertUploadLog(UploadLog uploadLog) {
		this.wrUploadLogMapper.insertUploadLog(uploadLog);
	}

	@Override
	public void updateUploadLog(UploadLog uploadLog) {
		int returnValue;
		try {
			returnValue = this.wrUploadLogMapper.updateUploadLog(uploadLog);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateUploadLog:" + e);
			throw new BaseException(e);
		}
	}

}
