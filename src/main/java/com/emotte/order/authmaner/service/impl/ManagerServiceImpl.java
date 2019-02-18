package com.emotte.order.authmaner.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.authmaner.mapper.reader.ReManagerMapper;
import com.emotte.order.authmaner.mapper.writer.WrManagerMapper;
import com.emotte.order.authmaner.model.Manager;
import com.emotte.order.authmaner.service.ManagerService;

@Service("managerService")
@Transactional
public class ManagerServiceImpl implements ManagerService {
	@Resource
	private ReManagerMapper reManagerMapper;
	@Resource
	private WrManagerMapper wrManagerMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Manager load(Long id) {
		return this.reManagerMapper.load(id);
	}
	@Override
	public List<Manager> query(Manager manager) {
		return this.reManagerMapper.query(manager);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Manager> queryByPage(Manager manager) {
		return this.reManagerMapper.queryByPage(manager);
	}
	@Override
	public List<Map<String, Object>> queryExport(Manager manager) {
		return reManagerMapper.queryExport(manager);
	}
	
	@Override
	public boolean insert(Manager manager) {
		int count = this.wrManagerMapper.insert(manager);
		if (1 != count) {
			throw new BaseException("插入失败！");
		}
		return true;
	}
	@Override
	public boolean update(Manager manager) throws BaseException{
		int count = this.wrManagerMapper.update(manager);
		if (1 != count) {
			throw new BaseException("更新失败！");
		}
		return true;
	}

	@Override
	public boolean multiDelete(String[] ids) throws BaseException {
		int count = wrManagerMapper.multiDelete(ids);
		if (count == 0) {
			throw new BaseException("删除失败");
		} else if (count < ids.length) {
			throw new BaseException("部分删除成功");
		}
		return true; // 删除成功
	}

	@Override
	public boolean multiInsert(List<Map<String, String>> data) throws BaseException {
		int count = wrManagerMapper.multiInsert(data);
		if (count == 0) {
			throw new BaseException("导入失败");
		} else if (count < data.size()) {
			throw new BaseException("部分导入成功");
		}
		return true; // 导入成功
	}
	@Override
	public Map<String, Object> loadManagerInfo(Long recorder) {
		return reManagerMapper.loadManagerInfo(recorder);
	}
	@Override
	public List<Map<String, Object>> loadAllAuthManager() {
		return reManagerMapper.loadAllAuthManager();
	}

}
