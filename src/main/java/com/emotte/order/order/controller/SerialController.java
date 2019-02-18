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
import com.emotte.order.order.model.Serial;
import com.emotte.order.order.service.SerialService;


    @Controller
 @RequestMapping("/serial")
public class SerialController extends BaseController{
     
           @Resource
	private SerialService serialService;
	
	
	   
	    @RequestMapping(value = "/loadSerial", method={RequestMethod.POST,RequestMethod.GET})
  public ModelAndView loadSerial(HttpServletRequest request,HttpServletResponse response, Long id)throws Exception{
	    ModelAndView mv = new ModelAndView();
	  try {
		  Serial serial=this.serialService.loadSerial(id);
		  if (serial==null) {
		        msg=BaseConstants.NULL;
				
			}else{
				msg=BaseConstants.SCUUESS;
			}
		    request.setAttribute("serial", serial);			
	} catch (Exception e) {
		 log.error("loadSerial:"+e);
		 msg=BaseConstants.FAIL;
	}
	  request.setAttribute("msg", msg);
   mv.setViewName("order/load_id");
   return mv;
  }
	   
	
	  @RequestMapping(value = "/querySerial", method={RequestMethod.POST,RequestMethod.GET})
	public void querySerial(HttpServletRequest request,HttpServletResponse response, Serial serial,Page page)throws Exception{
		  List<Serial> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.serialService.querySerial(serial,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("querySerial:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/insertSerial", method={RequestMethod.POST,RequestMethod.GET})
  public void insertSerial(HttpServletRequest request,HttpServletResponse response, Serial serial)throws Exception{
	    JSONObject jsonObject=new JSONObject();
	  try {
		this.serialService.insertSerial(serial);;
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
		 log.error("insertSerial:"+e);
		 msg=BaseConstants.FAIL;
	}
	jsonObject.accumulate("msg", msg);
	responseSendMsg(response, jsonObject.toString());
  }
  @RequestMapping(value = "/updateSerial", method={RequestMethod.POST,RequestMethod.GET})
  public void updateSerial(HttpServletRequest request,HttpServletResponse response, Serial serial)throws Exception{
	  JSONObject jsonObject=new JSONObject();
	  try {
		this.serialService.updateSerial(serial);
	} catch (Exception e) {
	     log.error("updateSerial:"+e);
		 msg=BaseConstants.FAIL;
	}
	 jsonObject.accumulate("msg", msg);
	 responseSendMsg(response, jsonObject.toString());
  } 
   
}
