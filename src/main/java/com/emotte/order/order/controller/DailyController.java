package com.emotte.order.order.controller;

import java.util.List;
import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;
import org.wildhorse.server.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.emotte.order.order.model.Daily;
import com.emotte.order.order.service.DailyService;


    @Controller
 @RequestMapping("/daily")
public class DailyController extends BaseController{
     
           @Resource
	private DailyService dailyService;
	
	
	   
	    @RequestMapping(value = "/loadDaily", method={RequestMethod.POST,RequestMethod.GET})
  public ModelAndView loadDaily(HttpServletRequest request,HttpServletResponse response, Long id)throws Exception{
	    ModelAndView mv = new ModelAndView();
	  try {
		  Daily daily=this.dailyService.loadDaily(id);
		  if (daily==null) {
		        msg=BaseConstants.NULL;
				
			}else{
				msg=BaseConstants.SCUUESS;
			}
		    request.setAttribute("daily", daily);			
	} catch (Exception e) {
		 log.error("loadDaily:"+e);
		 msg=BaseConstants.FAIL;
	}
	  request.setAttribute("msg", msg);
   mv.setViewName("order/load_log");
   return mv;
  }
	   
	
	  @RequestMapping(value = "/queryDaily", method={RequestMethod.POST,RequestMethod.GET})
	public void queryDaily(HttpServletRequest request,HttpServletResponse response, Daily daily,Page page)throws Exception{
		  List<Daily> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.dailyService.queryDaily(daily,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryDaily:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/insertDaily", method={RequestMethod.POST,RequestMethod.GET})
  public void insertDaily(HttpServletRequest request,HttpServletResponse response, Daily daily)throws Exception{
	    JSONObject jsonObject=new JSONObject();
	  try {
		this.dailyService.insertDaily(daily);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
		 log.error("insertDaily:"+e);
		 msg=BaseConstants.FAIL;
	}
	jsonObject.accumulate("msg", msg);
	responseSendMsg(response, jsonObject.toString());
  }
  @RequestMapping(value = "/updateDaily", method={RequestMethod.POST,RequestMethod.GET})
  public void updateDaily(HttpServletRequest request,HttpServletResponse response, Daily daily)throws Exception{
	  JSONObject jsonObject=new JSONObject();
	  try {
		this.dailyService.updateDaily(daily);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
	     log.error("updateDaily:"+e);
		 msg=BaseConstants.FAIL;
	}
	 jsonObject.accumulate("msg", msg);
	 responseSendMsg(response, jsonObject.toString());
  } 
   
}
