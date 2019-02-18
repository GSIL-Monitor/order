package com.emotte.order.authmaner.mapper.writer;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.authmaner.model.Manager;
@Component("wrManagerMapper")
public interface WrManagerMapper extends WrBaseMapper {
	/**
	 * 插入
	 * @param manager
	 * 2017年01月09日
	 */
	public int insert(Manager manager);
	/**
	 * 更新
	 * @param manager
	 * @return
	 * 2017年01月09日
	 */
	public int update(Manager manager);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * 2017年01月09日
	 */
	public int multiDelete(String[] ids);
	/**
	 * 批量插入
	 * @param data
	 * @return
	 * 2017年01月09日
	 */
	public int multiInsert(List<Map<String, String>> data);

}