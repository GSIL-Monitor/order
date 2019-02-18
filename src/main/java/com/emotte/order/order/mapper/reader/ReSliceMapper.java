package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.Slice;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

@Component("reSliceMapper")
public interface ReSliceMapper extends ReBaseMapper {

	public Slice loadSlice(Long id);

	public List<Slice> querySlice(Slice slice);

	public Integer countSlice(Slice slice);
	
	/**
	 * 通过条件查询对应的分层id(此分层是三方合作的分层)
	 * @param productCode 产品code码
	 * @param priceType	  价格体系code码
	 * @return
	 */
	public Map<String,Object> queryOtherDetail(String productCode,String priceType);

	/**
	 * 通过条件查找对应的分层信息id（此分层是家政类服务的二次分层记录）
	 * @param productCode 产品code码
	 * @param priceType	  格体系code码
	 * @param month		 分层月份
	 * @param personType 服务人员类型   1.员工制 2.非员工制
	 * @return
	 */
	public List<Map<String,Object>> querySplitDetail(String productCode,String priceType,String personType);
	
	/**
	 * 通过条件查找对应的分层信息id（此分层是家政类服务的二次分层记录）查询的是默认的分层
	 * @param productCode 产品code码
	 * @param priceType	  格体系code码
	 * @param personType 服务人员类型   1.员工制 2.非员工制
	 * @return
	 */
	public Map<String,Object> queryDefaultDetail(String productCode,String priceType,String personType);
	
}