package com.emotte.order.order.mapper.reader;

import java.util.List;

import com.emotte.order.order.model.Personnel;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

@Component("rePersonnelMapper")
public interface RePersonnelMapper extends ReBaseMapper {

	public Personnel loadPersonnel(Long id);

	public List<Personnel> queryPersonnel(Personnel personnel);

	public Integer countPersonnel(Personnel personnel);

	/**
	 * 通过服务人员id查找服务人员信息
	 * @param id 服务人员ID
	 * @return 返回查到的服务人员信息
	 */
	public Personnel querypersonInfo(Long id);

	/**
	 * 根据订单id，查询服务人员
	 * @param id 订单id
	 * @return
	 */
	public List<Personnel> queryPersonnelByOrderId(Long id);
}