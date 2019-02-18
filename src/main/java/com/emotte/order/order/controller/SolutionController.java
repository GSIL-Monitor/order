package com.emotte.order.order.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.Solution;
import com.emotte.order.order.service.SolutionService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.LoginCookieInfo;

import net.sf.json.JSONObject;


    @Controller
 @RequestMapping("/solution")
public class SolutionController extends BaseController{
     
           @Resource
	private SolutionService solutionService;

   	    @RequestMapping(value = "/loadSolution", method={RequestMethod.POST,RequestMethod.GET})
  public void loadSolution(HttpServletRequest request,HttpServletResponse response, Long id, Integer delFlag)throws Exception{
    	JSONObject jsonObject = new JSONObject();
    	String managerCityCode="";
		try {
			//获取当前登录人员信息
			 LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			//根据登录人获取城市编码
			managerCityCode =  cookie.getCityCode();
			Solution solution = this.solutionService.loadSolution(id);
			solution.setDelFlag(delFlag);
			//获得解决方案未生成执行订单的排期总余额
			BigDecimal planFeeSum = this.solutionService.querySolutionPlanFee(solution);
			if (solution == null) {
				msg = BaseConstants.NULL;
			} else {
				msg = Constants.SCUUESS;
			}
			jsonObject.accumulate("solution", solution);
			jsonObject.accumulate("planFeeSum", planFeeSum);
		} catch (Exception e) {
			log.error("loadSolution:" + e);
			msg = Constants.FAIL;
		}
	  jsonObject.accumulate("msg", msg);
	  jsonObject.accumulate("cityCode", managerCityCode);
	  responseSendMsg(response, jsonObject.toString());
  }
	   
	/**
	 * 
	 * @param request
	 * @param response
	 * @param custSolution
	 * @param page
	 * @throws Exception
	 * 查询解决方案套餐
	 */
	  @RequestMapping(value = "/querySolutionItem", method={RequestMethod.POST,RequestMethod.GET})
	public void querySolutionItem(HttpServletRequest request,HttpServletResponse response, Solution solution,Page page)throws Exception{
		  List<Solution> list=null;
		  JSONObject jsonObject=new JSONObject();
		 try {
			//设置分页
			 solution.setFlagPage(1);
			 solution.setPage(page);
			list=this.solutionService.querySolutionItem(solution);
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("querySolutionItem:"+e);
			msg=Constants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
}
