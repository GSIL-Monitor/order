package com.emotte.order.gentlemanSignature.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emotte.order.constant.AfterSaleConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.gentlemanSignature.service.QueryAgreementPreviewService;
import com.emotte.order.gentlemanSignature.service.UpdateContractStatusService;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.order.service.OrderService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("updateContractStatus")
public class UpdateContractStatusController extends BaseController{
	
	@Resource
	private UpdateContractStatusService updateContractStatusService;
	
	@Resource 
	private AgreementService agreementService;
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private QueryAgreementPreviewService queryAgreementPreviewService;

	@Resource
	private RedisClient redisClient;

	@RequestMapping(value = "/updateContractStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateContractStatus(HttpServletRequest request, HttpServletResponse response,Agreement agreement){
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
        
		String data=null;
		JSONObject jsonObject = new JSONObject();
		Integer i=0;
		//页面传来的参数
		Integer agreeServe=0;
		Integer agreeClient=0;
		Integer agreeOther=0;
		//查询后的参数
		Integer judementServe=0;
		Integer judementClient=0;
		Integer judementOther=0;
	 try {			
		 		   //电子签章服务人员状态  1,服务人员待签约   2,服务人员已签约  3,服务人员已驳回
				   agreeServe = agreement.getElecServeState();
				   //电子签章客户状态  1,客户待签约  2,客户已签约   3,客户已驳回  
				   agreeClient = agreement.getElecClientState();
				   //电子签章其他状态     1,合同待签约   2,已推送  3,三方已签约  4, 已电子签章认证  5,签章已驳回
				   agreeOther = agreement.getElecOtherState();
				//返回判断
				//查询出当前合同
				Agreement judement = updateContractStatusService.judementContract(agreement);
				Integer agreementState = judement.getAgreementState();//合同状态
				 judementOther = judement.getElecOtherState();//其他签约状态
				 judementServe = judement.getElecServeState();//服务人员签约状态
				 judementClient = judement.getElecClientState();//客户签约状态
				String agreementModel = judement.getAgreementModel();//合同形式
			
     if(judement.getValid()!=2 && agreementModel.equals("20520001")){
    	  if(agreementState!=null && agreementState==1){//如果是新建合同
	    		//新合同
	    		if(judementClient==3){
	    			data="客户已驳回";
	    		}else if(judementServe==3){
	    			data="服务人员已驳回";
	    		}else if((agreeClient!=null&&agreeClient==2) || (agreeServe!=null&&agreeServe==2)){
	    			agreement.setAgreementState(6);//合同状态改为2
	    		}else if((agreeClient!=null&&agreeClient==3) || (agreeServe!=null&&agreeServe==3)){
	    			agreement.setElecOtherState(5);
	    		}
	    		i = updateContractStatusService.updateContractStatus(agreement);
	      }else if(agreementState!=null && agreementState==6){//如果是签约中的合同
	    		if(judementOther==4){
	    			 data = "合同已电子认证";
	    		}else if(judementClient==3){	
	    			 data="客户已驳回";
	    		}else if(judementServe==3){
	    			 data="服务人员已驳回";
	    		}else if(agreeOther!=null&&agreeOther==4){
	    			//同一订单下其它已确认改为已终止
	   				 if(agreement.getOrderId()!=null){
	   					 Agreement b = new Agreement();
	   					 b.setOrderId(agreement.getOrderId());  
	   					 b.setValid(1);
	   				   List<Agreement> list= queryAgreementPreviewService.queryAgreementPreview(b); 
	   				   for(Agreement a:list){
	   					    if(a.getAgreementState()==2&&a.getId()!=agreement.getId()){
	   					    	a.setAgreementState(3);
	   					     i = updateContractStatusService.updateContractStatus(a);	    	
	   					    }
	   				   }
	   				 }
	   				 //电子签章确认后,改订单状态,而且合同状态变为2,并保存hash值
	   			     page.setCurPage(1);page.setPageCount(5);
	   			     
	   			     Agreement am = new Agreement();
	   			     am.setId(agreement.getId());
	   			     am.setOrderId(agreement.getOrderId());
	   			     List<Agreement> result = agreementService.queryAgreement(am, page);
	   			     /**@author 周鑫  @Date 2018-10-25 **/
	   			     /**
	   			      * 修改bug，不是面试成功，不应该修改订单状态
	   			      */
	   			     Order loadOrder = orderService.loadOrder(agreement.getOrderId());
	   			     if (null!=loadOrder&&loadOrder.getOrderStatus()==6) {
	   			    	Order order = new Order();
		   			     order.setId(result.get(0).getOrderId());
		   			     order.setOrderStatus(7);
		   			     //原方法： orderService.updateOrder ，
		   			     //原方法中，也是调用updateOrder2，去实现功能，所以直接换成updateOrder2
		   			     orderService.updateOrder2(order);
	   			     }
	   			     /**@author 周鑫**/
	   			     agreement.setAgreementState(2);
	   			//如客户与服务人员都签约,则三方已签约
	    		}else if(((agreeServe!=null&&agreeServe==2) && judementClient==2) || 
						((agreeClient!=null&&agreeClient==2) && judementServe==2)){
	    			agreement.setElecOtherState(3);
    			//如驳回则更新合同状态为1
	    		}else if((agreeServe!=null&&agreeServe==3) || (agreeClient!=null&&agreeClient==3)){
	    			agreement.setAgreementState(1);
					agreement.setElecOtherState(5);
	    		}
	    		i = updateContractStatusService.updateContractStatus(agreement);
	     } 
			    if(i>0){
				    msg = BaseConstants.SCUUESS;
				}else{
				    msg = BaseConstants.FAIL;
				}
     }else if(judement.getAgreementState()==5 && agreementModel.equals("20520001")){
    	 msg = BaseConstants.SCUUESS;
    	 data="电子合同已删除";
     }else{
		 msg = BaseConstants.FAIL;
		 data="电子合同不存在";
		  } 	 
	 } catch (Exception e) {
		log.error("updateContractStatus:" + e);
	    msg = BaseConstants.FAIL;
		}
		//整理状态
		Agreement result = updateContractStatusService.judementContract(agreement);
		    jsonObject.accumulate("msg", msg);
		    jsonObject.accumulate("agreementState",result.getAgreementState());
		    jsonObject.accumulate("elecServeState",result.getElecServeState());
		    jsonObject.accumulate("elecClientState",result.getElecClientState());
		    jsonObject.accumulate("elecOtherState",result.getElecOtherState());
		    jsonObject.accumulate("comment",data);
		 responseSendMsg(response, jsonObject.toString());
	}
	
	
	/*@RequestMapping(value = "/updateContractStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateContractStatus(HttpServletRequest request, HttpServletResponse response,Agreement agreement){
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		String data=null;
		JSONObject jsonObject = new JSONObject();
		Integer i=0;
		//页面传来的参数
		Integer agreeServe=0;
		Integer agreeClient=0;
		Integer agreeOther=0;
		//查询后的参数
		Integer judementServe=0;
		Integer judementClient=0;
		Integer judementOther=0;
		try {
				   agreeServe = agreement.getElecServeState();
				   agreeClient = agreement.getElecClientState();
				   agreeOther = agreement.getElecOtherState();
				//返回判断
				Agreement judement = updateContractStatusService.judementContract(agreement);
				Integer agreementState = judement.getAgreementState();//合同状态
				 judementOther = judement.getElecOtherState();//其他签约状态
				 judementServe = judement.getElecServeState();//服务人员签约状态
				 judementClient = judement.getElecClientState();//客户签约状态
				String agreementModel = judement.getAgreementModel();//合同形式
			
     if(judement.getValid()!=2 && agreementModel.equals("20520001")){
		    	if(agreementState!=null && agreementState==6){//如果是签约中的合同
		    		if(judementOther==4){
		    			 data = "合同已电子认证";
		    		}else if(judementClient==3){	
		    			 data="客户已驳回";
		    		}else if(judementServe==3){
		    			 data="服务人员已驳回";
		    		}else{
		    			 i = updateContractStatusService.updateContractStatus(agreement);
		    			 if(i>0&&agreeOther!=null&&agreeOther==4){
		    				 //同一订单下其它已确认改为已终止
		    				 if(agreement.getOrderId()!=null){
		    					 Agreement b = new Agreement();
		    					 b.setOrderId(agreement.getOrderId());  
		    					 b.setValid(1);
		    				   List<Agreement> list= queryAgreementPreviewService.queryAgreementPreview(b); 
		    				   for(Agreement a:list){
		    					    if(a.getAgreementState()==2&&a.getId()!=agreement.getId()){
		    					    	a.setAgreementState(3);
		    					     i = updateContractStatusService.updateContractStatus(a);	    	
		    					    }
		    				   }
		    				 }
		    				 //电子签章确认后,合同状态变为2,并保存hash值
		    				 agreement.setAgreementState(2);
		    			     i = updateContractStatusService.updateContractStatus(agreement);	 
		    			     page.setCurPage(1);page.setPageCount(5);
		    			     Order order = new Order();
		    			     List<Agreement> result = agreementService.queryAgreement(agreement, page);
		    			     order.setId(result.get(0).getOrderId());
		    			     order.setOrderStatus(7);
		    			     orderService.updateOrder(order);
		    			 }
		    				//如客户与服务人员都签约,则三方已签约
		    			 if(i>0 && ((agreeServe!=null&&agreeServe==2) && judementClient==2) || 
		    							((agreeClient!=null&&agreeClient==2) && judementServe==2)){
			    				agreement.setElecOtherState(3);
			    				i = updateContractStatusService.updateContractStatus(agreement);
			    			 }
		    				//如驳回则更新合同状态为1
		    			 if(i>0 && ((agreeServe!=null&&agreeServe==3) || (agreeClient!=null&&agreeClient==3))){
		    					agreement.setAgreementState(1);
		    					agreement.setElecOtherState(5);
		    					i = updateContractStatusService.updateContractStatus(agreement);
		    				}
		    			}
		    	}else if(agreementState!=null && agreementState==1){//如果是新建合同
		    		//新合同
		    		if(judementClient==3){
		    			data="客户已驳回";
		    		}else if(judementServe==3){
		    			data="服务人员已驳回";
		    		}else if((agreeClient!=null&&agreeClient==2) || (agreeServe!=null&&agreeServe==2)){
		    			agreement.setAgreementState(6);//合同状态改为2
		    			i = updateContractStatusService.updateContractStatus(agreement);
		    		}else if(agreeOther!=null&&agreeOther==2){//推送合同
			    		i = updateContractStatusService.updateContractStatus(agreement);
		    		}else if((agreeClient!=null&&agreeClient==3) || (agreeServe!=null&&agreeServe==3)){
		    			agreement.setElecOtherState(5);
		    			i = updateContractStatusService.updateContractStatus(agreement);
		    		}
		    	}
		    	
			    if(i>0){
				    msg = BaseConstants.SCUUESS;
				}else{
				    msg = BaseConstants.FAIL;
				}
     }else if(judement.getValid()==2 && agreementModel.equals("20520001")){
    	 msg = BaseConstants.SCUUESS;
    	 data="电子合同已删除";
     }else{
		 msg = BaseConstants.FAIL;
		 data="电子合同不存在";
		  } 	 
	 } catch (Exception e) {
		log.error("updateContractStatus:" + e);
	    msg = BaseConstants.FAIL;
		}
		//整理状态
		Agreement result = updateContractStatusService.judementContract(agreement);
		    jsonObject.accumulate("msg", msg);
		    jsonObject.accumulate("agreementState",result.getAgreementState());
		    jsonObject.accumulate("elecServeState",result.getElecServeState());
		    jsonObject.accumulate("elecClientState",result.getElecClientState());
		    jsonObject.accumulate("elecOtherState",result.getElecOtherState());
		    jsonObject.accumulate("comment",data);
		 responseSendMsg(response, jsonObject.toString());
	}*/

	/**
	 *
	 * 功能描述:电子合同确认
	 *
	 * @param: [request, response, orderId]
	 * @return: void
	 * @auther: lenovo
	 * @date: 2018/7/19 20:02
	 */
	@RequestMapping("/checkContractDate")
	public void checkContractDate(HttpServletRequest request,HttpServletResponse response,Long contractId){
		JSONObject jsonObject = new JSONObject();
		try {
			//根据合同ID查询，审核通过、修改时间与当前时间差在14d内的合同信息
			int count = agreementService.findContractById(contractId);
			if(count == 0){//没有符合的记录，从redis中拿取信息
				//查询合同编号
				String agreementCode = agreementService.findAgreementCodeById(contractId);
				String redisMsg = redisClient.get(AfterSaleConstant.ELECTRONIC_CONTRACT_KEY + ":" + agreementCode);
				if(StringUtils.isNotBlank(redisMsg)){//redis存在信息，返回成功
					msg = BaseConstants.SCUUESS;
				}else{//redis不存在信息，返回失败
					msg = BaseConstants.FAIL;
				}
			}else{//该合同有效，返回成功信息
                msg = BaseConstants.SCUUESS;
            }
		} catch (RedisAccessException e) {
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}
}
