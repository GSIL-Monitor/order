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
import com.emotte.order.order.model.PayfeeDetail;
import com.emotte.order.order.service.PayfeeDetailService;


    @Controller
 @RequestMapping("/payfeeDetail")
public class PayfeeDetailController extends BaseController{
     
           @Resource
	private PayfeeDetailService payfeeDetailService;
	
	
	   
	    @RequestMapping(value = "/loadPayfeeDetail", method={RequestMethod.POST,RequestMethod.GET})
  public ModelAndView loadPayfeeDetail(HttpServletRequest request,HttpServletResponse response, Long id)throws Exception{
	    ModelAndView mv = new ModelAndView();
	  try {
		 PayfeeDetail payfeeDetail=this.payfeeDetailService.loadPayfeeDetail(id);
		  if (payfeeDetail==null) {
		        msg=BaseConstants.NULL;
				
			}else{
				msg=BaseConstants.SCUUESS;
			}
		    request.setAttribute("payfeeDetail", payfeeDetail);			
	} catch (Exception e) {
		 log.error("loadPayfeeDetail:"+e);
		 msg=BaseConstants.FAIL;
	}
	  request.setAttribute("msg", msg);
   mv.setViewName("order/load_payfeeDetail");
   return mv;
  }
	   
	
	  @RequestMapping(value = "/queryPayfeeDetail", method={RequestMethod.POST,RequestMethod.GET})
	public void queryPayfeeDetail(HttpServletRequest request,HttpServletResponse response, PayfeeDetail payfeeDetail,Page page)throws Exception{
		  List<PayfeeDetail> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.payfeeDetailService.queryPayfeeDetail(payfeeDetail,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryPayfeeDetail:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/insertPayfeeDetail", method={RequestMethod.POST,RequestMethod.GET})
  public void insertPayfeeDetail(HttpServletRequest request,HttpServletResponse response, PayfeeDetail payfeeDetail)throws Exception{
	    JSONObject jsonObject=new JSONObject();
	  try {
		this.payfeeDetailService.insertPayfeeDetail(payfeeDetail);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
		 log.error("insertPayfeeDetail:"+e);
		 msg=BaseConstants.FAIL;
	}
	jsonObject.accumulate("msg", msg);
	responseSendMsg(response, jsonObject.toString());
  }
  @RequestMapping(value = "/updatePayfeeDetail", method={RequestMethod.POST,RequestMethod.GET})
  public void updatePayfeeDetail(HttpServletRequest request,HttpServletResponse response, PayfeeDetail payfeeDetail)throws Exception{
	  JSONObject jsonObject=new JSONObject();
	  try {
		this.payfeeDetailService.updatePayfeeDetail(payfeeDetail);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
	     log.error("updatePayfeeDetail:"+e);
		 msg=BaseConstants.FAIL;
	}
	 jsonObject.accumulate("msg", msg);
	 responseSendMsg(response, jsonObject.toString());
  } 
   
}
