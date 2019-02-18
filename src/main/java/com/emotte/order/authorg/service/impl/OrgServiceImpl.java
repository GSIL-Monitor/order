package com.emotte.order.authorg.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.authorg.mapper.reader.ReOrgMapper;
import com.emotte.order.authorg.mapper.writer.WrOrgMapper;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.authorg.service.OrgService;
import com.emotte.order.order.model.Order;

@Service("orgService")
@Transactional
public class OrgServiceImpl implements OrgService {
	@Resource
	private ReOrgMapper reOrgMapper;
	@Resource
	private WrOrgMapper wrOrgMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Org load(Long id) {
		return this.reOrgMapper.load(id);
	}
	@Override
	public List<Org> query(Org org) {
		return this.reOrgMapper.query(org);
	}
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Org> queryByPage(Org org) {
		return this.reOrgMapper.queryByPage(org);
	}
	@Override
	public List<Map<String, Object>> queryExport(Org org) {
		return reOrgMapper.queryExport(org);
	}
	
	@Override
	public boolean insert(Org org) {
		int count = this.wrOrgMapper.insert(org);
		if (1 != count) {
			throw new BaseException("插入失败！");
		}
		return true;
	}
	@Override
	public boolean update(Org org) throws BaseException{
		int count = this.wrOrgMapper.update(org);
		if (1 != count) {
			throw new BaseException("更新失败！");
		}
		return true;
	}

	@Override
	public boolean multiDelete(String[] ids) throws BaseException {
		int count = wrOrgMapper.multiDelete(ids);
		if (count == 0) {
			throw new BaseException("删除失败");
		} else if (count < ids.length) {
			throw new BaseException("部分删除成功");
		}
		return true; // 删除成功
	}

	@Override
	public boolean multiInsert(List<Map<String, String>> data) throws BaseException {
		int count = wrOrgMapper.multiInsert(data);
		if (count == 0) {
			throw new BaseException("导入失败");
		} else if (count < data.size()) {
			throw new BaseException("部分导入成功");
		}
		return true; // 导入成功
	}
	@Override
	public List<Org> queryByChannel(String channel, String cityCode, List<Org> list) {
		return reOrgMapper.queryByChannel(channel, cityCode, list);
	}
	@Override
	public List<Org> queryByProduct(String channel, String cityCode, List<Org> list) {
		return reOrgMapper.queryByProduct(channel, cityCode, list);
	}
	@Override
	public Org queryByDistance(String longitude, String latitude, String city, List<Org> list) {
		return reOrgMapper.queryByDistance(longitude, latitude, city, list);
	}
	@Override
	public List<Org> queryCallCenter() {
		return reOrgMapper.queryCallCenter();
	}
	@Override
	public List<Org> queryByChannelAndProduct(String orderChannel, String productCode, String city, List<Org> list) {
		return reOrgMapper.queryByChannelAndProduct(orderChannel, productCode, city, list);
	}
	@Override
	public Long queryProduct(String productCode) {
		return reOrgMapper.queryProduct(productCode);
	}
	@Override
	public List<Org> queryAllOrgByDistance(String longitude, String latitude, String city, List<Org> list) {
		return reOrgMapper.queryAllOrgByDistance(longitude, latitude, city, list);
	}
	
	@Override
	public Org queryByAcceptRange(String acceptRange, String longitude, String latitude, String city, List<Org> list) {
		return reOrgMapper.queryByAcceptRange(acceptRange,longitude, latitude, city,list);
	}
	@Override
	public List<Org> distributeInPeking() {
		return reOrgMapper.distributeInPeking();
	}
	@Override
	public int insertPollRecord(Org org) {
		return wrOrgMapper.insertPollRecord(org);
	}
	@Override
	public Order queryLastRechargeUser(Order order) {
		return reOrgMapper.queryLastRechargeUser(order);
	}
	@Override
	public List<Org> queryAllOrgByDistance3(Order order) {
		return reOrgMapper.queryAllOrgByDistance3(order);
	}
	@Override
	public Long distributeInPeking3(Integer cateType,Long deptId) {
		return reOrgMapper.distributeInPeking3(cateType,deptId);
	}
	@Override
	public int updateDistribute(Long managerId) {
		return wrOrgMapper.updateDistribute(managerId);
	}
	@Override
	public boolean queryGuanjia(Long createBy) {
		Long managerId = reOrgMapper.queryGuanjia(createBy);
		if(managerId!=null){
			return true;
		}else{
			return false;
		}
	}
}
