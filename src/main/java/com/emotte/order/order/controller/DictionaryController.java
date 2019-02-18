package com.emotte.order.order.controller;

import java.util.List;
import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.service.DictionaryService;


    @Controller
 @RequestMapping("/dictionary")
public class DictionaryController extends BaseController{
     
           @Resource
	private DictionaryService dictionaryService;
	
	
	   
	    @RequestMapping(value = "/loadDictionary", method={RequestMethod.POST,RequestMethod.GET})
  public ModelAndView loadDictionary(HttpServletRequest request,HttpServletResponse response, Long id)throws Exception{
	    ModelAndView mv = new ModelAndView();
	  try {
		 Dictionary dictionary=this.dictionaryService.loadDictionary(id);
		  if (dictionary==null) {
		        msg=BaseConstants.NULL;
				
			}else{
				msg=BaseConstants.SCUUESS;
			}
		    request.setAttribute("dictionary", dictionary);			
	} catch (Exception e) {
		 log.error("loadDictionary:"+e);
		 msg=BaseConstants.FAIL;
	}
	  request.setAttribute("msg", msg);
   mv.setViewName("order/load_dictionary");
   return mv;
  }
	   
	
	  @RequestMapping(value = "/queryDictionary", method={RequestMethod.POST,RequestMethod.GET})
	public void queryDictionary(HttpServletRequest request,HttpServletResponse response, Dictionary dictionary,Page page)throws Exception{
		  List<Dictionary> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.dictionaryService.queryDictionary(dictionary,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryDictionary:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/insertDictionary", method={RequestMethod.POST,RequestMethod.GET})
  public void insertDictionary(HttpServletRequest request,HttpServletResponse response, Dictionary dictionary)throws Exception{
	    JSONObject jsonObject=new JSONObject();
	  try {
		this.dictionaryService.insertDictionary(dictionary);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
		 log.error("insertDictionary:"+e);
		 msg=BaseConstants.FAIL;
	}
	jsonObject.accumulate("msg", msg);
	responseSendMsg(response, jsonObject.toString());
  }
  @RequestMapping(value = "/updateDictionary", method={RequestMethod.POST,RequestMethod.GET})
  public void updateDictionary(HttpServletRequest request,HttpServletResponse response, Dictionary dictionary)throws Exception{
	  JSONObject jsonObject=new JSONObject();
	  try {
		this.dictionaryService.updateDictionary(dictionary);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
	     log.error("updateDictionary:"+e);
		 msg=BaseConstants.FAIL;
	}
	 jsonObject.accumulate("msg", msg);
	 responseSendMsg(response, jsonObject.toString());
  } 
  

	/**
	 * 查询字黄表
	 * @param request
	 * @param response
	 * @param mobile
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/checkedDictionary", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void checkedDictionary(HttpServletRequest request,
			HttpServletResponse response, String dictJson){
		try{
			JSONObject jsonObject=new JSONObject();
			String result = this.dictionaryService.checkedDictionary(dictJson);
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("dict", result);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   
}
