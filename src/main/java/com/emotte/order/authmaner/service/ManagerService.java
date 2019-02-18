package com.emotte.order.authmaner.service;

import java.util.List;
import java.util.Map;

import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.authmaner.model.Manager;
public interface ManagerService {
	/**
	 * 加载数据
	 * @param id
	 * @return
	 * 2017年01月09日
	 */
	public Manager load(Long id);
	/**
	 * 查询
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public List<Manager> query(Manager manager);
	/**
	 * 分页查询
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public List<Manager> queryByPage(Manager manager);
	/**
	 * 查询导出数据
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public List<Map<String, Object>> queryExport(Manager manager);
	/**
	 * 插入数据
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public boolean insert(Manager manager) throws BaseException;
	/**
	 * 更新数据
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public boolean update(Manager manager) throws BaseException;
	/**
	 * 删除
	 * @param ids
	 * @return
	 * 2017年01月09日
	 */
	public boolean multiDelete(String[] ids) throws BaseException;
	/**
	 * 批量插入
	 * @param data
	 * @return
	 * 2017年01月09日
	 */
	public boolean multiInsert(List<Map<String, String>> data) throws BaseException;
	/**
	 * 加载管理员信息
	 * @param recorder
	 * @return
	 * 2017年1月9日
	 */
	public Map<String, Object> loadManagerInfo(Long recorder);
	
	/**
	 * 加载所有管理员信息
	 * @return
	 * 2017年11月15日
	 */
	public List<Map<String, Object>> loadAllAuthManager();


}