package com.emotte.order.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.emotte.order.order.mapper.reader.RePersonnelMapper;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.mapper.reader.ReSliceMapper;
import com.emotte.order.order.mapper.writer.WrSliceMapper;
import com.emotte.order.order.model.Personnel;
import com.emotte.order.order.model.Slice;
import com.emotte.order.order.service.SliceService;

@Service("sliceService")
@Transactional
public class SliceServiceImpl implements SliceService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private RePersonnelMapper rePersonnelMapper;
	@Resource
	private ReSliceMapper reSliceMapper;

	@Resource
	private WrSliceMapper wrSliceMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Slice loadSlice(Long id) {
		return this.reSliceMapper.loadSlice(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Slice> querySlice(Slice slice, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reSliceMapper.countSlice(slice));
		}
		slice.setBeginRow(page.getFilterRecord().toString());
		slice.setPageSize(page.getPageCount().toString());
		return this.reSliceMapper.querySlice(slice);
	}

	@Override
	public void insertSlice(Slice slice) {
		this.wrSliceMapper.insertSlice(slice);
	}

	@Override
	public void updateSlice(Slice slice) {
		int returnValue;
		try {
			returnValue = this.wrSliceMapper.updateSlice(slice);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateSlice:" + e);
			throw new BaseException(e);
		}
	}
	/**
	 * 插入订单分层信息方法
	 * @param orderId 	订单ID
	 * @param isOther 	分层类型 （1，三方合作分层，2，易盟二次分账分层）
	 * @param personId 	服务人员ID（三方合作的时候，没有此id，延续性服务二次分账的时候，需要记录此ID）
	 * @param month		分层月份
	 * @param productCode  产品code码
	 * @param priceType    价格体系
	 * @return 返回是否完成 成功返回success  其余为失败
	 */
	@Override
	public String insertSliceByOther(String orderId,int isOther,String personId,String productCode,String priceType,Long createBy) {
		//先根据是什么样的分层，来查询对应的表相关的信息
		if(isOther==1){//三方合作分层
			Map<String, Object>  map = reSliceMapper.queryOtherDetail(productCode, priceType);//查询对应的三方分成详细
			if(map==null){//如果未查询到三方分成的记录，则直接返回失败
				return "fail";
			}
			Slice slice = new Slice();
			slice.setOrderId(Long.parseLong(orderId));
			slice.setIsOther((byte)1);
			slice.setCreateBy(createBy);
			slice.setDivideId(Long.parseLong(map.get("ID").toString()));
			wrSliceMapper.insertSlice(slice);
			return "success";
		}else if(isOther==2){//二次分账分层
			Personnel person = rePersonnelMapper.querypersonInfo(Long.parseLong(personId));//查询该服务人员是否为员工制 
			if(person==null){
				return "fail";
			}
			Long personType = person.getStaffType();
			List<Map<String, Object>> split = reSliceMapper.querySplitDetail(productCode, priceType,  personType+"");
			if(split==null){
				return "fail";
			}
			for (Map<String, Object> map : split) {
				Slice slice = new Slice();
				slice.setOrderId(Long.parseLong(orderId));
				slice.setIsOther((byte)2);
				slice.setCreateBy(createBy);
				slice.setDivideId(Long.parseLong(map.get("ID").toString()));
				slice.setIsDefault(Byte.parseByte(map.get("IS_DEFAULT").toString()));
				wrSliceMapper.insertSlice(slice);
			}
			
			//员工类型 1.员工制 2.非员工制 
		}
		return "success";
	}
}
