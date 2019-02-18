package com.emotte.order.authmaner.mapper.reader;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.authmaner.model.Manager;


@Component("reManagerMapper")
public interface ReManagerMapper extends ReBaseMapper {
	/**
	 * 加载
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