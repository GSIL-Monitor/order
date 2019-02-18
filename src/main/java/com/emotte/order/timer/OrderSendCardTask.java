package com.emotte.order.timer;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.emotte.dubbo.account.AccountService;
import com.emotte.dubbo.activities.service.GJBAPPCardInterfaceService;
import com.emotte.dubbo.activities.service.SolutionDubboCardService;
import com.emotte.dubbo.activities.solution.SolutionOrderInterfaceService;
import com.emotte.dubbo.custom.VipRecordService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.AccountPayService;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.DateUtil;


/**
 * 
 * @author lilang
 * @emai  lilang@95081.com
 * @time 2016-7-18 下午7:04:48
 * @订单缴费后赠送十元消费券相关任务
 */
@Component
public class OrderSendCardTask {
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
	 * 每天20:53执行
	 */
//	@Scheduled(cron = "0 58 11 * * ?")  
//	public void solutionTask(){
//		//SpringUtil.init();
//		
//		System.out.println("定时任务-------------------------任务结束！");
//	}
	
	
	/**
	 * 每隔57秒执行一次
	 */
//	@Scheduled(cron = "3/57 * * * * ?")  
	/**
	 * 已作废，移到AccountPayTask.doBuss
	 * 2017年5月17日
	 */
	@Deprecated
	public void test() {
		List<AccountPay> listAccountPay = null;
		AccountPay accountPay = new AccountPay();
		accountPay.setIsBackType(2l);
		accountPay.setBussStatus("1");
		accountPay.setValid(1L);
		//accountPay.setPayKind("3");
		listAccountPay = this.accountPayService.queryAccountPay(accountPay);
		System.out.println("listAccountPay:" +listAccountPay.size());
		
		Long orderid = 0l;
		
		if(listAccountPay!=null&&listAccountPay.size()>0){
			List<AccountPay> lists = new ArrayList(); // 用于修改接口业务处理完以后，结算单的业务处理状态
			for (int j = 0; j < listAccountPay.size(); j++) {				
				AccountPay accountPayTem = listAccountPay.get(j);
				if(accountPayTem.getOrderId() != null && orderid.toString().equals(accountPayTem.getOrderId().toString()) ){
					continue;
				}
				orderid = accountPayTem.getOrderId();
				
				JSONObject jsonStu = JSONObject.fromObject(accountPayTem);
				System.out.println("accountPayJSON:"+jsonStu.toString());
				String payKind = accountPayTem.getPayKind();
				//Long orderId = accountPayTem.getOrderId();
				if(payKind!=null){	
					System.out.println("accountPayTem:"+accountPayTem.toString());
					//账户充值
					if(payKind.equals("1") && "20110003".equals(accountPayTem.getPayStatus())){
						try{
							String result = this.accountService.updateBalance(jsonStu.toString());
							System.out.println("账户充值：" +result);
							lists.add(accountPayTem);
						}catch(Exception e){
							continue;
						}
					}
					//订单
					if(payKind.equals("2")){
						try{
							String orderJson = "{'handle':'2','order':[{'id':'" +accountPayTem.getOrderId() +"'";
							String re = "" ;
							// FA商品入录后可以进行业务处理及分包
							if(accountPayTem.getCateType()!=null && "3".equals(accountPayTem.getCateType()) && "20110003".equals(accountPayTem.getPayStatus())){
								orderJson +=",'orderStatus':" +(accountPayTem.getOrderStatus()==null?"1":accountPayTem.getOrderStatus()) ;
								orderJson +=",'cateType':91,'payStatus':'" +accountPayTem.getPayStatus() +"'}]}" ;
								re = orderInterfaceService.insertOrUpdateOrder(orderJson);
							}
							// 他营单次订单业务处理
							if(accountPayTem.getCateType()!=null && "4".equals(accountPayTem.getCateType()) && "20110003".equals(accountPayTem.getPayStatus())){
								orderJson +=",'orderStatus':" +(accountPayTem.getOrderStatus()==null?"1":accountPayTem.getOrderStatus()) ;
								orderJson +=",'cateType':92,'payStatus':'20110003'}]}" ;
								re = orderInterfaceService.insertOrUpdateOrder(orderJson);
							}
							// 自营单次订单业务处理
							if(accountPayTem.getCateType()!=null && "1".equals(accountPayTem.getCateType()) && "20110003".equals(accountPayTem.getPayStatus())){
								// 如果订单是下户状态，在录入缴费对账完成以后，订单状态改为已完成
								if("11".equals(accountPayTem.getOrderStatus())){
									orderJson +=",'orderStatus':9,'cateType':93,'payStatus':'20110003'}]}" ;
									re = orderInterfaceService.insertOrUpdateOrder(orderJson);
								}else{
									orderJson +=",'cateType':93,'payStatus':'20110002'}]}" ;
									re = orderInterfaceService.insertOrUpdateOrder(orderJson);
								}
							}
							// 自营延续订单业务处理
							if(accountPayTem.getCateType()!=null && "2".equals(accountPayTem.getCateType()) && "20110003".equals(accountPayTem.getPayStatus())){
								orderJson +=",'cateType':94,'payType':" +accountPayTem.getPayType() +",'accountAmount':" +accountPayTem.getAccountAmount();
								orderJson +=",'totalPay':" +accountPayTem.getTotalPay() +",'payStatus':'20110002'}]}" ;
								re = orderInterfaceService.insertOrUpdateOrder(orderJson);
							}
							if(!"".equals(re)){
								JSONObject jobRe = JSONObject.fromObject(re);
								if("0".equals(jobRe.get("code"))){
									lists.add(accountPayTem);
								}
							}
						}catch(Exception e){
							continue;
						}
					}
					//解决方案
					if(payKind.equals("3") && "20110003".equals(accountPayTem.getPayStatus())){
						try{
							String result = this.solutionDubboCardService.insertSolutionCard(jsonStu.toString());	
							JSONObject jsonSuCard = JSONObject.fromObject(result);
							//解决方案代扣卡卡号
							String msg = jsonSuCard.optString("msg");
							if(msg=="00"||msg.equals("00")){								
								String feeCardNum = jsonSuCard.optString("cardNumb");
								String sellRecordId = jsonSuCard.optString("sellRecordId");
								//将解决方案单号为orderId的代扣卡卡号置为 feeCardNum
								//Long solutionOrderId = jsonObj.getLong("solutionOrderId");//解决方案订单ID
								//String feeCardNum = jsonObj.getString("feeCardNum");//解决方案对应代扣卡号
								Map map = new HashMap();
								map.put("solutionOrderId", accountPayTem.getOrderId());
								map.put("feeCardNum", feeCardNum);
								
								JSONObject jsonSo = JSONObject.fromObject(map);
								String result2 =  solutionOrderInterfaceService.updateSolutionFeeCardNum(jsonSo.toString());
								
								Order orderSu = new Order();
								orderSu.setId(accountPayTem.getOrderId());
								orderSu.setOrderStatus(2);
								orderSu.setPayStatus("20110003");
								orderSu.setRechargeBy(12901567l);//负责人由陈爱华改为陈华
								orderSu.setRechargeDept(12619003l);
								this.orderService.updateSolutionOrders(orderSu);
								//将accountPayTem结算单的  关联的订单号 改成卡消费记录号 sellRecordId
								//accountPayTem.setOrderId(Long.parseLong(sellRecordId));
								lists.add(accountPayTem);
							}else{
								continue;
							}
						
						}catch(Exception e){
							continue;
						}		
					}
					//礼品卡
					if(payKind.equals("4") && "20110003".equals(accountPayTem.getPayStatus())){
						try{
							String reJson = this.gJBAPPCardInterfaceService.paySuccess(jsonStu.toString());	
							JSONObject jobj = JSONObject.fromObject(reJson);
							if(jobj.getInt("code")==1){
								lists.add(accountPayTem);
							}
						}catch(Exception e){
							continue;
						}
					}
					//会员卡
					if(payKind.equals("5") && "20110003".equals(accountPayTem.getPayStatus())){
						try{
							this.vipRecordService.updateVipRecordFlag(jsonStu.toString());
							System.out.println("会员卡");
							lists.add(accountPayTem);
						}catch(Exception e){
							continue;
						}			
					}
				}
				if(lists.size()>0){
					this.accountPayService.updateAccountList(lists);
				}
			}
		}
		
//		System.out.println("每分钟执行的任务！"+new	Date());
		
		 
		
	}
	/*public static void main(String[] args) {
		OrderSendCardTask st = new OrderSendCardTask();
		st.solutionTask();
	}*/
	
	/**
	 * 每天11:08执行
	 */
	//@Scheduled(cron = "0 48 11 * * ?")  
	public void solutionTask(){
		String startDateStr = "2016-05-02";
		Long order_id = 1l;
		Date startDate = DateUtil.str2Date(startDateStr);//订单上户开始时间
		System.out.println(startDate);
		
		Date dateToday = new Date(); 
		Date dateTodayDay = DateUtil.stringDayToDate(DateUtil.dateToStringDay(new Date())) ; //当期日期 年月日
		
		String linshi= startDateStr;
		System.out.println(dateTodayDay);
		List listMap =null;
		for(int i=1;i<30;i++){
			Date tempDate = DateUtil.addDay(DateUtil.addMonth(startDate, i),-1);
			if(dateTodayDay.getTime()-tempDate.getTime()<0){
				break;
			}else{
				HashMap mapCondition = new HashMap();
				mapCondition.put("start_time", linshi);
				mapCondition.put("end_time", DateUtil.getYmd(tempDate));
				mapCondition.put("order_id", order_id);				
				
				listMap = this.itemInterviewService.querySalaryCondition(mapCondition);
				System.out.println( "订单ID"+order_id+"的第 "+i+"个月的服务人员服务费开始日期:"+linshi+" 到结束日期："+DateUtil.getYmd(tempDate));
				if(listMap!=null&&listMap.size()>0){
					for (int j = 0; j < listMap.size(); j++) {
						Map map = (Map) listMap.get(j);
						System.out.println("服务人员ID："+map.get("PERSONID")+"开始时间："+map.get("STARTTIME")+" 结束时间："+map.get("ENDTIME"));
					}
				}
				
				System.out.println(listMap);
			}
			
			linshi = DateUtil.getYmd(DateUtil.addMonth(startDate, i));
		}
		
		System.out.println("定时任务-------------------------任务结束！");
	}
	
}
