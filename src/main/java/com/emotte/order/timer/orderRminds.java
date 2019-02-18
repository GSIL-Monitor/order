package com.emotte.order.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.emotte.dubbo.manager.NewsService;
import com.emotte.dubbo.manager.OrderNewsService;
import com.emotte.order.order.service.OrderService;

/**
 * 
 * @author zhanghun
 *
 */
@Component
public class orderRminds {

	@Resource
	private NewsService newsService;

	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderNewsService orderNewsService;

	/**
	 * 服务人员面试通知
	 * 通知产品管家
	 * 每5分钟
	 */
	public void orderEmpPushRminds() {
		System.err.println("=============orderEmpPushRminds开始=================");
		List<Map<String, Object>> list = this.orderService.orderEmpPushRminds();
		if(list != null && list.size() > 0){
			for (Map<String, Object> map : list) {
				List<String> managerIds = new ArrayList<String>();
				managerIds.add(map.get("RECHARGE_BY").toString());
				String XINXI = map.get("XINXI").toString();
				if(managerIds.size() > 0 && null != XINXI && !"".equals(XINXI)){
					String xx_type = map.get("XX_TYPE")==null?"":map.get("XX_TYPE").toString();
					Long order_id = map.get("ORDER_ID")==null?null:Long.valueOf(map.get("ORDER_ID").toString());
					Long person_id = map.get("PERSON_ID")==null?null:Long.valueOf(map.get("PERSON_ID").toString());
					this.orderNewsService.addNewsAboutOrder(XINXI,"", managerIds,1L, xx_type, order_id, person_id);
				}
			}
		}
		System.err.println("=============orderEmpPushRminds结束=================");
	}
}
