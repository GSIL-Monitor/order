package com.emotte.order.timer;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.PayfeeService;

@Component
public class BecomeVipTask {
	@Resource
	private OrderBaseService orderBaseService;
	@Resource
	private PayfeeService payfeeService;
	@Resource
	private OrderService orderService;
	/**
	 * 成为会员
	 * @param data
	 * @return
	 * 2017年6月2日 
	 */
	public void becomeVip(){
		LogHelper.info(getClass() + "becomeVip()", "成为会员定时任务开始！" );
		//设置3000元为默认会员值
		BigDecimal vipMoney = new BigDecimal(3000);
		//直接查客户缴费
		List<Map<String, Object>> feeList = this.orderService.queryOrderUserAllFee();
		LogHelper.info(getClass() + "becomeVip()", "符合成为会员条件的人数:" + feeList.size() +"个。");
		try {
			if (feeList.size() != 0 && !feeList.isEmpty()) {
				//循环遍历比较
				for (Map<String, Object> map : feeList) {
					//所有缴费金额
					BigDecimal allFee = new BigDecimal(0);
					//用户ID
					Long userId = null;
					for (Map.Entry<String, Object> entry : map.entrySet()) {
						if ("FEE".equals(entry.getKey())) {
							allFee =(BigDecimal) entry.getValue();
						}else if ("USERID".equals(entry.getKey())) {
							userId = Long.valueOf( entry.getValue().toString());
						} 
					}
					//判断累加的缴费，是否超过3000，是则将客户置为永久会员，不是则继续
					if (allFee.compareTo(vipMoney) != -1) {
						LogHelper.info(getClass() + "becomeVip()", "更新成为会员的用户ID:" + userId);
						OrderUser oUser = new OrderUser();
						oUser.setId(userId);
						oUser.setIsVip(1);
						oUser.setVipStartTime(DateUtil.getCurrDateTime());
						oUser.setVipEndTime("2099-12-30 23:59:59");
						oUser.setUpdateBy(1l);//管理员修改
						this.orderBaseService.updateOrderUser(oUser);
					}
				}
			}
		} catch (BaseException e) {
			LogHelper.error(getClass(), e.getMessage() , e);
		}
		LogHelper.info(getClass() + "becomeVip()", "成为会员定时任务结束！" );
	}
	
}
