package com.emotte.order.authorg.mapper.writer;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.authorg.model.Org;
@Component("wrOrgMapper")
public interface WrOrgMapper extends WrBaseMapper {
	/**
	 * 插入
	 * @param org
	 * 2017年01月09日
	 */
	public int insert(Org org);
	/**
	 * 更新
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public int update(Org org);
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
	/**
	 * 插入订单轮询负责管家记录
	 * @param org
	 * @return
	 */
	public int insertPollRecord(Org org);
	/**
	 * 更新论单次数
	 * @param managerId
	 * @return
	 */
	public int updateDistribute(@Param("managerId")Long managerId);

}