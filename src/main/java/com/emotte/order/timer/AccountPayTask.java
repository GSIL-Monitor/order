package com.emotte.order.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.emotte.dubbo.account.AccountService;
import com.emotte.dubbo.activities.service.GJBAPPCardInterfaceService;
import com.emotte.dubbo.activities.service.SolutionDubboCardService;
import com.emotte.dubbo.activities.solution.SolutionOrderInterfaceService;
import com.emotte.dubbo.custom.VipRecordService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.AccountPayService;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderService;

import net.sf.json.JSONObject;

/**
 * 结算单任务
 * @author ChengJing
 * 2017年5月16日
 */
@Component
public class AccountPayTask {
	@Resource
	private AccountPayService accountPayService;
	@Resource
	private AccountService accountService;
	@Resource
	private GJBAPPCardInterfaceService gJBAPPCardInterfaceService;
	@Resource
	private OrderInterfaceService orderInterfaceService;
	@Resource
	private SolutionDubboCardService solutionDubboCardService;
	@Resource
	private SolutionOrderInterfaceService solutionOrderInterfaceService;
	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private VipRecordService vipRecordService;
	@Resource
	private OrderService orderService;

	/**
	 * 处理业务
	 * 2017年5月16日
	 */
	public void doBuss() {
		List<AccountPay> listAccountPay = null;
		AccountPay accountPay = new AccountPay();
		accountPay.setIsBackType(2l);
		accountPay.setBussStatus("1");
		accountPay.setValid(1L);
		listAccountPay = this.accountPayService.queryAccountPay(accountPay);
		Long orderid = 0l;
		if ( listAccountPay!=null && listAccountPay.size()>0 ) {
			LogHelper.info(getClass() + ".doBuss()", "第1步处理业务的结算单数量：" + listAccountPay.size());
			List<AccountPay> lists = new ArrayList<AccountPay>(); // 用于修改接口业务处理完以后，结算单的业务处理状态
			for (AccountPay accountPayTem : listAccountPay) {
				if ( accountPayTem.getOrderId() != null && orderid.equals(accountPayTem.getOrderId()) ){
					continue;
				}
				orderid = accountPayTem.getOrderId();
				String payKind = accountPayTem.getPayKind();
				if( payKind!=null ) {
					switch (payKind.charAt(0)) {
						case '1': {
							if("20110003".equals(accountPayTem.getPayStatus())) 
								if (accountRechargeBuss(accountPayTem)) 
									lists.add(accountPayTem); 
							break;
						}
						case '2': {
							if (orderBuss(accountPayTem)) 
								lists.add(accountPayTem); 
							break;
						}
						case '3': {
							if("20110003".equals(accountPayTem.getPayStatus())) 
								if (jjfaBuss(accountPayTem)) 
									lists.add(accountPayTem);
							break;
						}
						case '4': {
							if("20110003".equals(accountPayTem.getPayStatus())) 
								if (lpCardBuss(accountPayTem)) 
									lists.add(accountPayTem);
							break;
						}
//						case '5': {
//							if("20110003".equals(accountPayTem.getPayStatus())) 
//								if (vipCardBuss(accountPayTem)) 
//									lists.add(accountPayTem);
//							break;
//						}
					}
				}
			}
			LogHelper.info(getClass() + ".doBuss()", "第2步处理业务的结算单数量：" + lists.size());
			if(lists.size()>0){
				this.accountPayService.updateAccountList(lists);
			}
		}
	}

	/**
	 * 账户充值
	 * @param data
	 * @return true/false
	 * 2017年5月16日
	 */
	private boolean accountRechargeBuss(AccountPay accountPay) {
		JSONObject dataObj = JSONObject.fromObject(accountPay);
		String data = dataObj.toString();
		try {
			LogHelper.debug(getClass() + ".accountRecharge()", "账户充值：" + data);
			String result = this.accountService.updateBalance(data);
			LogHelper.debug(getClass() + ".accountRecharge()", "账户充值：" + result);
		} catch(Exception e) {
			LogHelper.error(getClass() + ".accountRecharge()", data, e);
			return false;
		}
		return true;
	}
	
	/**
	 * 订单业务
	 * @param accountPayTem
	 * @return true/false
	 * 2017年5月16日
	 */
	private boolean orderBuss(AccountPay accountPayTem) {
		try{
			StringBuffer orderJson = new StringBuffer("{'handle':'2','order':[{'id':'" +accountPayTem.getOrderId() +"'");
			String re = "" ;
			if (accountPayTem.getCateType()!=null && "20110003".equals(accountPayTem.getPayStatus())) {
				switch (accountPayTem.getCateType().charAt(0)) {
					case '1': { // 自营单次订单业务处理
						// 如果订单是下户状态，在录入缴费对账完成以后，订单状态改为已完成
						if("11".equals(accountPayTem.getOrderStatus())){
							orderJson.append(",'orderStatus':9,'cateType':93,'payStatus':'20110003'}]}");
						} else {
							orderJson.append(",'cateType':93,'payStatus':'20110002'}]}");
						}
						break;
					}
					case '2': { // 自营延续订单业务处理
						orderJson.append(",'cateType':94,'payType':" +accountPayTem.getPayType() +",'accountAmount':" +accountPayTem.getAccountAmount());
						orderJson.append(",'totalPay':" +accountPayTem.getTotalPay() +",'payStatus':'20110002'}]");
						orderJson.append(",'accountPay':[{'id':"+accountPayTem.getId()+"}]}");
						break;
					}
					case '3': { // FA商品入录后可以进行业务处理及分包
						orderJson.append(",'orderStatus':" +(accountPayTem.getOrderStatus()==null?"1":accountPayTem.getOrderStatus()));
						orderJson.append(",'cateType':91,'payStatus':'" +accountPayTem.getPayStatus() +"'}]}");
						break;
					}
					case '4': { // 他营单次订单业务处理
						orderJson.append(",'orderStatus':" +(accountPayTem.getOrderStatus()==null?"1":accountPayTem.getOrderStatus()));
						orderJson.append(",'cateType':92,'payStatus':'20110003'}]}");
						break;
					}
				}
				LogHelper.info(getClass() + ".orderBuss()","发送参数："+orderJson.toString());
				re = orderInterfaceService.insertOrUpdateOrder(orderJson.toString());
			}
			if(!"".equals(re)){
				JSONObject jobRe = JSONObject.fromObject(re);
				if("0".equals(jobRe.get("code"))){
					return true;
				}
			}
		} catch (Exception e) {
			LogHelper.error(getClass() + ".orderBuss()", accountPayTem.toString(), e);
			return false;
		}
		return false;
	}
	
	/**
	 * 解决方案
	 * @param data
	 * @return true/false
	 * 2017年5月16日
	 */
	private boolean jjfaBuss(AccountPay accountPay) {
		JSONObject dataObj = JSONObject.fromObject(accountPay);
		String data = dataObj.toString();
		try{
			LogHelper.info(getClass() ,"jjfaBuss()开始生成代扣卡参数："+data);
			String result = this.solutionDubboCardService.insertSolutionCard(data);
			LogHelper.info(getClass(), "jjfaBuss():" + result);
			JSONObject jsonSuCard = JSONObject.fromObject(result);
			//解决方案代扣卡卡号
			String msg = jsonSuCard.optString("msg");
			if("00".equals(msg)) {								
				String feeCardNum = jsonSuCard.optString("cardNumb");
//				String sellRecordId = jsonSuCard.optString("sellRecordId");
				//将解决方案单号为orderId的代扣卡卡号置为 feeCardNum
				//Long solutionOrderId = jsonObj.getLong("solutionOrderId");//解决方案订单ID
				//String feeCardNum = jsonObj.getString("feeCardNum");//解决方案对应代扣卡号
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("solutionOrderId", accountPay.getOrderId());
				map.put("feeCardNum", feeCardNum);
				
				JSONObject jsonSo = JSONObject.fromObject(map);
				solutionOrderInterfaceService.updateSolutionFeeCardNum(jsonSo.toString());
				
				Order orderSu = new Order();
				orderSu.setId(accountPay.getOrderId());
				orderSu.setOrderStatus(2);
				orderSu.setPayStatus("20110003");
				//orderSu.setRechargeBy(95215l);
				//orderSu.setRechargeDept(990022905588483l);
				//orderSu.setRechargeBy(96387l);//负责人由陈华改为王增环
				//orderSu.setRechargeDept(990022905588482l);//呼入组新
				//orderSu.setRechargeBy(95898L);//2018年4月23日负责人由王增环改为刘文平
				//orderSu.setRechargeDept(12619003L);//呼叫中心管家部
				try {
					Map<String,Object> rp = this.orderService.querySolutionResponsiblePerson(accountPay.getOrderId());//查询解决方案负责人和负责部门
					if(rp == null || (rp.get("RECHARGEBY") == null || "0".equals(rp.get("RECHARGEBY").toString())) || (rp.get("RECHARGEDEPT") == null || "0".equals(rp.get("RECHARGEDEPT").toString()))){
						//orderSu.setRechargeBy(97261L);//2018年5月负责人由刘文平改为李金生
						//orderSu.setRechargeDept(990022905588716L);//网络运营部
						orderSu.setRechargeBy(95898L);//2018年6月负责人由李金生改为刘文平
						orderSu.setRechargeDept(12619003L);//呼叫中心管家部
					}
				} catch (Exception e) {
					LogHelper.info(getClass(), "定时器更新解决方案负责人失败"+e);
				}
				this.orderService.updateSolutionOrders(orderSu);

				//解决方案订单缴费时扫描最近三天的解决方案，直接生成执行订单
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.accumulate("solutionId",accountPay.getOrderId());
					LogHelper.info(getClass(),".createSolutionPlanOrderNew(),请求参数:"+accountPay.getOrderId());
					String solutionPlanOrderNew = solutionOrderInterfaceService.createSolutionPlanOrderNew(jsonObject.toString());
					LogHelper.info(getClass(),".createSolutionPlanOrderNew(),返回值:"+solutionPlanOrderNew);
				} catch (Exception e) {
					e.printStackTrace();
				}

				//将accountPayTem结算单的  关联的订单号 改成卡消费记录号 sellRecordId
				//accountPayTem.setOrderId(Long.parseLong(sellRecordId));
				return true;
			}
		} catch(Exception e){
			LogHelper.error(getClass() + ".jjfaBuss()", data, e);
			return false;
		}	
		return false;
	}
	
	/**
	 * 礼品卡
	 * @param data
	 * @return
	 * 2017年5月16日
	 */
	private boolean lpCardBuss(AccountPay accountPay) {
		JSONObject dataObj = JSONObject.fromObject(accountPay);
		String data = dataObj.toString();
		try{
			String reJson = this.gJBAPPCardInterfaceService.paySuccess(data);	
			JSONObject jobj = JSONObject.fromObject(reJson);
			if(jobj.getInt("code")==1){
				return true;
			}
		} catch(Exception e){
			LogHelper.error(getClass() + ".lpCardBuss()", data, e);
			return false;
		}
		return false;
	}

	/**
	 * 会员卡
	 * @param data
	 * @return
	 * 2017年5月16日
	 */
	@Deprecated
	private boolean vipCardBuss(AccountPay accountPay) {
		JSONObject dataObj = JSONObject.fromObject(accountPay);
		String data = dataObj.toString();
		try{
			this.vipRecordService.updateVipRecordFlag(data);
		} catch(Exception e){
			LogHelper.error(getClass() + ".vipCardBuss()", data, e);
			return false;
		}	
		return true;
	}
	
}
