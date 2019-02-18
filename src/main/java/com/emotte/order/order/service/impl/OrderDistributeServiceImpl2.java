package com.emotte.order.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.authorg.model.Org;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.AbstractOrderDistribute;
import com.emotte.order.util.LogHelper;

@Service
@Transactional
public class OrderDistributeServiceImpl2 extends AbstractOrderDistribute {

	private Long rechargeUser;
	private Long rechargeOrg;
	private String rechargeOrgName;
	private List<Org> list = new ArrayList<Org>(); // 用于存储筛选出来的门店，基地

	@Override
	public boolean distributeOnSelfYanxu (Order order) throws BaseException {
		try {
			/**
			 * 取消线上订单的特殊分单规则，add20181224 zhanghao
			 */
			//1.查询客户最后一次下单的管家(仅延续)
//			Order lastRechargeUser = queryLastRechargeUser(order);
//			if (lastRechargeUser!=null&&"2".equals(order.getCateType().toString())) {
//				rechargeUser = lastRechargeUser.getRechargeBy();
//				rechargeOrg = lastRechargeUser.getRechargeDept();
//				rechargeOrgName = lastRechargeUser.getRechargeDeptText();
//			}else{
				//2.判断符合所有订单分发的门店
				filterAllOrgByDistance(order, list);
				//3.判断最近的一个门店或基地
				filterOrgByTypeAndDistance(order,list);
				//4.分发管家(轮单)
				rechargeUser = distributeInPeking(order.getCateType(),list);
				//5.处理结束
				if (list.isEmpty()) {
					if(callCenter(list)) {
//						rechargeUser = distributeInPeking(order.getCateType(),list);
						rechargeOrg = list.get(0).getId();
						rechargeOrgName = list.get(0).getName();
					} else {
						return false; // 获取负责人，负责部门失败
					}
				} else {
					rechargeOrg = list.get(0).getId();
					rechargeOrgName = list.get(0).getName();
				}
//			}
			order.setRechargeBy(rechargeUser);
			order.setRechargeDept(rechargeOrg);
			order.setRechargeDeptName(rechargeOrgName);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error(getClass(), "分发失败：" + e.getMessage());
			throw new BaseException("分发失败： " + e.getMessage());
		}
		return true;
	}
	@Override
	public boolean distributeOnSelfOnce (Order order) {
		try {
			//1.判断符合所有订单分发的门店
			filterAllOrgByDistance(order, list);
			//2.判断最近的一个门店或基地
			filterOrgByTypeAndDistance(order,list);
			//3.分发管家(轮单)
			rechargeUser = distributeInPeking(order.getCateType(),list);
			//4.处理结束
			if (list.isEmpty()) {
				if(callCenter(list)) {
//					rechargeUser = distributeInPeking(order.getCateType(),list);
					rechargeOrg = list.get(0).getId();
					rechargeOrgName = list.get(0).getName();
				} else {
					return false; // 获取负责人，负责部门失败
				}
			} else {
				rechargeOrg = list.get(0).getId();
				rechargeOrgName = list.get(0).getName();
			}
			order.setRechargeBy(rechargeUser);
			order.setRechargeDept(rechargeOrg);
			order.setRechargeDeptName(rechargeOrgName);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error(getClass(), "分发失败：" + e.getMessage());
			throw new BaseException("分发失败： " + e.getMessage());
		}
		return true;
	}

	@Override
	public boolean filterAllOrgByDistance(Order order, List<Org> list) {
		List<Org> tmp = orgService.queryAllOrgByDistance3 (order);
		list.clear();
		if (tmp != null && !tmp.isEmpty()) {
			list.addAll(tmp);
			return false;
		} else {
			return true; // 如果为空直接跳转到呼叫中心
		}
	}

	public Long distributeInPeking(Integer cateType,List<Org> list) {
		if (list!=null&& !list.isEmpty()) {
			Long managerId = orgService.distributeInPeking3(cateType,list.get(0).getId());
			orgService.updateDistribute(managerId);
			return managerId;
		}else{
			return null;
		}
	}
}
