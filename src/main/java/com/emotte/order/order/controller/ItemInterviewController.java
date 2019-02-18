package com.emotte.order.order.controller;

import com.emotte.dubbo.service.PushInterfaceService; 
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.eclient.EClientContext;
import com.emotte.interf.ESalaryService;
import com.emotte.interf.EScheduleService;
/*import com.emotte.kernel.sms.SMSRedisHelper;*/
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderPushLog;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderPushLogService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.LoginCookieInfo;
import com.emotte.server.util.CookieUtils;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/itemInterview")
public class ItemInterviewController extends BaseController {

	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private PushInterfaceService pushInterfaceService;
	
	@Resource
	private SMSServiceDubbo sMSServiceDubbo; 
	
	@Resource
	private OrderPushLogService orderPushLogService;
	
	@Resource
	private OrderService orderService;
	
	/*@Resource
	private SMSRedisHelper sMSRedisHelper;*/

	@RequestMapping(value = "/loadItemInterview", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadItemInterview(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			ItemInterview itemInterview = this.itemInterviewService.loadItemInterview(id);
			if (itemInterview == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			request.setAttribute("itemInterview", itemInterview);
		} catch (Exception e) {
			log.error("loadItemInterview:" + e);
			msg = BaseConstants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("order/load_itemInterview");
		return mv;
	}

	@RequestMapping(value = "/queryItemInterview", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryItemInterview(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview, Page page) throws Exception {
		List<ItemInterview> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemInterviewService.queryItemInterview(itemInterview, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryItemInterview:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryNeedPersons", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryNeedPersons(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		List<ItemInterview> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemInterviewService.queryNeedPersons(itemInterview);
			if(itemInterview.getType()!=null && (itemInterview.getType()==13 ||itemInterview.getType()==14)){
				// 单次服务订单 上户及取消专用校验查询
				if (itemInterview.getType()==13 && list.size() > 0) {
					msg = BaseConstants.SCUUESS;
				} else if(itemInterview.getType()==14 && list.size() > 1){
					msg = BaseConstants.SCUUESS;
				}else{
					msg = BaseConstants.NULL;
				}
			}else{
				if (list.size() > 0) {
					msg = BaseConstants.SCUUESS;
				} else {
					msg = BaseConstants.NULL;
				}
				jsonObject.accumulate("list", list);
				jsonObject.accumulate("page", page);
			}
		} catch (Exception e) {
			log.error("queryNeedPersons:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/insertItemInterview", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertItemInterview(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			itemInterview.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			itemInterview.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemInterviewService.insertItemInterview(itemInterview);
			// 如果是自营单次服务，需推送服务人员APP
			try{
				if(itemInterview.getType()==1 || itemInterview.getType()==11){
					String[] idst = itemInterview.getIds().split(",");
					for (int i = 0; i < idst.length; i++) {				
						//this.pushInterfaceService.pushOrder(Long.valueOf(idst[i]), itemInterview.getOrderId());
						// userId:服务人员ID,appId:1,
						String title="您有一条新的管家帮订单，请到订单列表里查询";
						String description="您有一条新的\"管家帮\"订单，请到订单列表里查询";
						// msg:您有一条新的管家帮订单，请到订单列表里查询
						String pushmsg = "您有一条新的管家帮订单，请到订单列表里查询" ;
						this.pushInterfaceService.push(Long.valueOf(idst[i]), 1L, title, description, pushmsg) ;
					}
				}
			}catch(Exception e){}
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("insertItemInterview:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/updateItemInterview", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateItemInterview(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			itemInterview.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			itemInterview.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemInterviewService.updateItemInterview(itemInterview);
			jsonObject.accumulate("code", "00");
			msg = BaseConstants.SCUUESS;
		} catch (RuntimeException e) {
			log.error("lindDays:" + e);
			msg = BaseConstants.FAIL; // 需求排期不正确
		} catch (Exception e) {
			jsonObject.accumulate("msg", e.getMessage());
			log.error("updateItemInterview:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询所有匹配的服务人员
	 * @param itemInterview
	 * @author YUNNA
	 * @param itemInterview 
	 * @date 2017年07月27日
	 * @return 
	 */
	@RequestMapping(value = "/queryMatchingPersonId", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryMatchingPersonId(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview, Object list) throws Exception {		
		JSONObject jsonObject = new JSONObject();
		int count=0;
		try {
			count= this.itemInterviewService.queryMatchingPersonId(itemInterview);
			if (count > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryMatchingPersonId:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);		
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 调岗下户
	 * @param request
	 * @param response
	 * @param itemInterview
	 * @throws Exception
	 */
	@RequestMapping(value = "/workOutHourse", method = { RequestMethod.POST, RequestMethod.GET })
	public void workOutHourse(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {		
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
			JSONObject param = new JSONObject();
			param.accumulate("personId", itemInterview.getPersonId());
			param.accumulate("orderId", itemInterview.getOrderId());
			param.accumulate("endTime", itemInterview.getEndTime());
			result = service.workOutHourse(param.toString());
			if (result != null && !result.equals("")) {
				msg = BaseConstants.SCUUESS;
				//20180614调岗下户操作更新personnel表人员状态为在校待岗
				itemInterviewService.updatePersonnalStatus(10l,itemInterview.getPersonId());
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("workOutHourse:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);		
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 返岗上户
	 * @param request
	 * @param response
	 * @param itemInterview
	 * @throws Exception
	 */
	@RequestMapping(value = "/workGoHourse", method = { RequestMethod.POST, RequestMethod.GET })
	public void workGoHourse(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {		
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			EScheduleService service = (EScheduleService)EClientContext.getBean(EScheduleService.class);
			JSONObject param = new JSONObject();
			param.accumulate("personId", itemInterview.getPersonId());
			param.accumulate("orderId", itemInterview.getOrderId());
			param.accumulate("starTime", itemInterview.getStarTime());
			result = service.workGoHourse(param.toString());
			if (result != null && !result.equals("")) {
				msg = BaseConstants.SCUUESS;
				//20180614返岗上户操作更新personnel表人员状态为在户服务
				itemInterviewService.updatePersonnalStatus(1l,itemInterview.getPersonId());
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("workGoHourse:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);		
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param itemInterview
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryInterviews", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryInterviews(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		List<ItemInterview> list = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemInterviewService.queryInterviews(itemInterview);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryInterviews:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 返岗上户时查询最小可上户时间
	 * @param request
	 * @param response
	 * @param itemInterview
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRetuningJobsUp", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkRetuningJobsUp(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		ItemInterview  result = new ItemInterview();
		JSONObject jsonObject = new JSONObject();
		try {
			result = this.itemInterviewService.checkRetuningJobsUp(itemInterview);
			if (result != null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("checkRetuningJobsUp:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/selectConflictSchedule", method = { RequestMethod.POST, RequestMethod.GET })
	public void selectConflictSchedule(HttpServletRequest request, HttpServletResponse response, ItemInterview  itemInterview)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		ItemInterview  itemInterviewNew= null ;
		try {
			itemInterviewNew = this.itemInterviewService.selectConflictSchedule(itemInterview);
			if (itemInterviewNew == null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("selectConflictSchedule:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("data",itemInterviewNew);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	/**
	 * 点击发送派工单按钮 验证是否订单已发送过派工单
	 * @author xyp
	 * 20180706
	 */
	/*@RequestMapping(value = "/validateSendpies", method = { RequestMethod.POST, RequestMethod.GET })
	public void validateSendpies(HttpServletRequest request, HttpServletResponse response,ItemInterview itemInterview) throws Exception {
		Integer viewcount;
		JSONObject jsonObject = new JSONObject();
		Map map = new HashMap<String, Object>();
		map.put("receivePhone", itemInterview.getMobilePhone());
		map.put("orderId", itemInterview.getOrderId());
		List<Map> list = new ArrayList<Map>();
		boolean flag = true;
		boolean flag1;
		try {
			//判断是否有短信记录
			flag1 = itemInterviewService.checkOrderId(map);
			list = itemInterviewService.queryShortRecode(map);
			
		if(flag1) {
			if(CollectionUtils.isNotEmpty(list)) {
				for (int i = 0; i < list.size(); i++) {
					String message = (String) ((Map) list.get(i)).get("MESSAGE");
					if(message.indexOf("本次服务已完成")!=-1) {
						//短信表里存在
						flag = false;
					}
				}
			}
		}else {
			
		}
		if(flag!=false) {
			//ItemInterview itemInterviewtmp=itemInterviewService.loadItemInterview(itemInterview.getOrderId());	
			viewcount = this.itemInterviewService.validateSendpies(itemInterview);
			log.info("validateSendpies----》查询订单是否存在于T_ORDER_ASSIGN_BILL表中"+viewcount);
			if (viewcount>=0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
			jsonObject.accumulate("viewcount", viewcount);
		}else {
			msg = BaseConstants.SCUUESS;
			jsonObject.accumulate("validflag", 1);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error("validateSendpies:》》验证是否订单已发送过派工单异常" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}*/
	
	
	
	@RequestMapping(value = "/validateSendpies", method = { RequestMethod.POST, RequestMethod.GET })
	public void validateSendpies(HttpServletRequest request, HttpServletResponse response,ItemInterview itemInterview) throws Exception {
		Integer viewcount;
		JSONObject jsonObject = new JSONObject();
		try {
			//ItemInterview itemInterviewtmp=itemInterviewService.loadItemInterview(itemInterview.getOrderId());
			Order ordertmp=orderService.loadOrder(itemInterview.getOrderId());
			itemInterview.setCustomID(ordertmp.getUserId());
			viewcount = this.itemInterviewService.validateSendpies(itemInterview);
			log.info("validateSendpies----》查询订单是否存在于T_ORDER_PUSH_LOG表中"+viewcount);
			if (viewcount>=0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
			jsonObject.accumulate("viewcount", viewcount);
		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("validateSendpies:》》订单已发送过派工单异常" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	
	
	/**
	 * 点击派工单按钮  发送短信
	 * @author xyp
	 * 20180706 
	 */
	@RequestMapping(value = "/sendpiesinfo", method = { RequestMethod.POST, RequestMethod.GET })
	public void sendpiesinfo(HttpServletRequest request, HttpServletResponse response,
			ItemInterview itemInterview) throws Exception {
		LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
		Integer viewcount;
		JSONObject jsonObject = new JSONObject();
		JSONObject messageParams = new JSONObject();
		try {
			Order ordertmp=orderService.loadOrder(itemInterview.getOrderId());
			itemInterview.setCustomID(ordertmp.getUserId());
			viewcount = this.itemInterviewService.validateSendpies(itemInterview);
			log.info("sendpiesinfo----》查询订单是否存在于T_ORDER_PUSH_LOG表中（viewcount>0表示存在）"+viewcount);
			if (viewcount>0) {
				msg = BaseConstants.SCUUESS;
				jsonObject.accumulate("msg", msg);
				jsonObject.accumulate("myvalida","0");
		    } else {
		    	log.info("sendpiesinfo----发送单次派工单短信开始");
		    	//String mesgtmp="http://t.erp.95081.com/jzjy-wechat/html/singleSurvey/index.html?id="+itemInterview.getOrderId();
		    	String messageUrl = itemInterview.getShortUrl();
		    	String[] params = new String[] {messageUrl};
		    	messageParams.accumulate("system", "order");
		    	messageParams.accumulate("channel", "sys");
				messageParams.accumulate("mobiles",itemInterview.getMobilePhone());
				messageParams.accumulate("rate", "1");
				messageParams.accumulate("templet", "other_499");
				//messageParams.accumulate("templet", "other_684");
				messageParams.accumulate("params", params);
				log.info("发送短信参数"+messageParams.toString());
				String res = this.sMSServiceDubbo.send(messageParams.toString());
//				String res = sMSRedisHelper.send(messageParams.toString());
				JSONObject smsObject = JSONObject.fromObject(res);
				String str = smsObject.get("result").toString();
				if(null!=smsObject && !smsObject.isEmpty() && str.equals("success")){
					msg = BaseConstants.SCUUESS;
			    	jsonObject.accumulate("msg", msg);
			    	jsonObject.accumulate("myvalida","1");
			    	log.info("短信发送结果》》》"+str);
			    	//短信发送成功增加记录（用于判断是否已经发送）
			    	OrderPushLog orderpush=new OrderPushLog();
			    	orderpush.setOrderId(itemInterview.getOrderId());
			    	orderpush.setManagerId(ordertmp.getUserId());
			    	orderpush.setMsg(null);
			    	orderpush.setLog("erp系统为订单ID是"+itemInterview.getOrderId()+"的客户手机"+itemInterview.getMobilePhone()+"发送单次派工单短信");
			    	orderpush.setType(Long.valueOf(2)); //推送方式 短信
			    	orderpush.setUpdateTime(null);
			    	orderpush.setUpdateBy(null);
			    	orderpush.setCreateBy(Long.valueOf(cookie.getId())); //创建者
			    	orderpush.setValid(Long.valueOf(1)); //有效
			    	orderpush.setDistinguish(3);
			    	orderpush.setVersion(Long.valueOf(1));
			    	orderPushLogService.insertOpushlog(orderpush);
			    	
				}else {
					msg = BaseConstants.NULL;
			    	jsonObject.accumulate("msg", msg);
			    	jsonObject.accumulate("myvalida","2");
			    	log.info("短信发送失败");
				}
		    
		    }
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonObject.accumulate("msg", e.getMessage());
			log.error("sendpiesinfo:》》》短信发送异常" + e);
			msg = BaseConstants.FAIL;
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 自动填报服务人员服务费
	 * @param request
	 * @param response
	 * @param orderId
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertSalary", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertSalary(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String result = null;
		try {
			ESalaryService service = (ESalaryService)EClientContext.getBean(ESalaryService.class);
			JSONObject  j = new JSONObject();
			j.put("orderId", orderId);
			j.put("createSource", "20500001");
			result = service.insertSalary1(j.toString());
			if (result != null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("insertSalary" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 根据上户判断订单是否已下户
	 * @param request
	 * @param response
	 * @param orderId
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderStatus(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ItemInterview> list = new ArrayList<ItemInterview>();
		try {
			list = this.itemInterviewService.queryOrderStatus(orderId);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrderStatus" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

}
