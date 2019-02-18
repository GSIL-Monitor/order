package com.emotte.order.order.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.beust.jcommander.internal.Lists;
import com.emotte.dubbo.installment.CustomerInfoService;
import com.emotte.dubbo.installment.InstallmentService;
import com.emotte.order.order.model.AfterSalesNew;
import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.CustSolutionPlan;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderAfterSales;
import com.emotte.order.order.model.Solution;
import com.emotte.order.order.service.AfterSalesService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.LoginCookieInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/afterSalesNew")
public class AfterSalesController extends BaseController {

	@Resource
	private AfterSalesService afterSalesService;

	@Resource
	private OrderService orderService;

	@Resource
	private InstallmentService installmentService;
	
	@Resource
	private CustomerInfoService customerInfoService;

	@RequestMapping(value = "/loadAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadAfterSales(HttpServletRequest request,
									   HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			AfterSalesNew afterSalesNew = this.afterSalesService.loadAfterSales(id);
			if (afterSalesNew == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("afterSalesNew", afterSalesNew);
		} catch (Exception e) {
			log.error("loadAfterSales:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_afterSales");
		return mv;
	}

	@RequestMapping(value = "/queryAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAfterSales(HttpServletRequest request,
								HttpServletResponse response, AfterSalesNew afterSalesNew, Page page)
			throws Exception {
		List<AfterSalesNew> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.afterSalesService.queryAfterSales(afterSalesNew, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 新增或修改售后记录
	 * @param request
	 * @param response
	 * @param afterSalesNew
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertAfterSales", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertAfterSales(HttpServletRequest request, HttpServletResponse response, AfterSalesNew afterSalesNew) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			afterSalesNew.setUpdateBy(lk.getId());
			afterSalesNew.setCreateBy(lk.getId());
			afterSalesNew.setCreateDept(lk.getDeptId());
			this.afterSalesService.insertAfterSales(afterSalesNew);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateAfterSales", method = { RequestMethod.POST,RequestMethod.GET })
	public void updateAfterSales(HttpServletRequest request,HttpServletResponse response, AfterSalesNew afterSalesNew) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			afterSalesNew.setUpdateBy(lk.getId());
			this.afterSalesService.updateAfterSales(afterSalesNew);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 根据用户手机号查询关联方案订单表
	 *
	 * @param request		请求对象
	 * @param response		相应对象
	 * @param phoneNumber	用户手机号
	 */
	@RequestMapping("/findSolveScheme")
	public void findSolveScheme(HttpServletRequest request,HttpServletResponse response,@RequestParam("phoneNumber") String phoneNumber){
		JSONObject jsonObject = new JSONObject();
		List<Solution> solutions = Lists.newArrayList();
		//执行查询
		try {
			solutions = afterSalesService.findSolutionByPhone(phoneNumber);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("findSolveScheme："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("data",solutions);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 查询订单列表
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping("/queryOrder")
	public void queryOrder(HttpServletRequest request,HttpServletResponse response,Order order){
		JSONObject jsonObject = new JSONObject();
		List<Order> list = null;
		try {
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			if(order.getLoginLevel() == null){
				order.setLoginLevel(lk.getPermissionLevel());
			}
			order.setLoginBy(lk.getId());
			order.setLoginDept(lk.getDeptId());
			order.setLoginOrgCode(lk.getOrgCode());
			list = afterSalesService.queryOrder(order);
			if(list != null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 根据订单号查询分期缴费明细
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping("/findPayFeeDetail")
	public void findPayFeeDetail(HttpServletRequest request,HttpServletResponse response,Long orderId,Long payFeeId ){
		JSONObject jsonObject = new JSONObject();
		List<AfterSalesNew> list = null;
		try {
			list = afterSalesService.findPayFeeDetail(orderId,payFeeId);
			if(list != null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("findPayFeeDetail："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 按id查询售后单
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryFqafterSales")
	public void queryFqafterSales(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject jsonObject = new JSONObject();
		AfterSalesNew afterSalesNew = null;
		try {
			afterSalesNew = afterSalesService.queryFqafterSales(id);
			if(afterSalesNew != null){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryFqafterSales："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("afterSalesNew",afterSalesNew);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 根据订单ID查询手续费退费明细列表
	 *
	 * @param request		请求对象
	 * @param response		相应对象
	 * @param orderId		订单ID
	 */
	@RequestMapping("/queryServiceCharge")
	public void queryServiceCharge(HttpServletRequest request,HttpServletResponse response,AfterSalesNew afterSalesNew){
		JSONObject jsonObject = new JSONObject();
		List<AfterSalesNew> afterSalesNews = Lists.newArrayList();
		try {
			afterSalesNews = afterSalesService.findServiceChargeList(afterSalesNew);
			if(CollectionUtils.isNotEmpty(afterSalesNews)){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryServiceCharge:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("afterSalesNews",afterSalesNews);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 查询城市列表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/queryCity")
	public void queryCity(HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		List<BaseCity> list = null;
		try {
			list = afterSalesService.queryCity();
			if(list != null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCity："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 根据订单id、服务人员服务费批次查询售后单信息
	 *
	 * @param orderId	订单ID
	 * @param bathno	服务人员服务费批次
	 * @param request	请求对象
	 * @param response	相应对象
	 */
	@RequestMapping("/findBuyBackAfterSales/{orderId}/{bathno}")
	public void findBuyBackAfterSales(@PathVariable("orderId") Long orderId,@PathVariable("bathno") Long bathno,HttpServletRequest request,HttpServletResponse response){
		JSONObject jsonObject = new JSONObject();
		OrderAfterSales orderAfterSales = new OrderAfterSales();
		//执行查询
		try {
			orderAfterSales = afterSalesService.findBuyBackAfterSales(orderId,bathno);
			if(orderAfterSales == null){
				msg = BaseConstants.NULL;
			}else{
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("findBuyBackAfterSales："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("orderAfterSales",orderAfterSales);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 查询解决方案订单
	 * @Description: TODO
	 * @author lidenghui
	 * @date 2018年6月15日 下午3:50:33
	 * @version
	 * @param request
	 * @param response
	 * @param solution void
	 */
	@RequestMapping("/querySolution")
	public void querySolution(HttpServletRequest request,HttpServletResponse response,Solution solution){
		JSONObject jsonObject = new JSONObject();
		List<Solution> list = null;
		try {
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			if(solution.getLoginLevel() == null){
				solution.setLoginLevel(lk.getPermissionLevel());
			}
			solution.setLoginBy(lk.getId());
			solution.setLoginDept(lk.getDeptId());
			solution.setLoginOrgCode(lk.getOrgCode());
			list = afterSalesService.querySolution(solution);
			if(list != null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("querySolution："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 按id查询解决方案排期
	 * @param request
	 * @param response
	 */
	@RequestMapping("/querySolutionSchedule")
	public void querySolutionSchedule(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject jsonObject = new JSONObject();
		List<CustSolutionPlan> list = null;
		try {
			list = afterSalesService.querySolutionSchedule(id);
			if(list != null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("querySolutionSchedule："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 *
	 * 功能描述: 根据用户手机号、卡号查询卡片详情
	 *
	 * @param: [request, response, afterSalesNew]
	 * @return: void
	 * @auther: lenovo
	 * @date: 2018/6/19 17:39
	 */
	@RequestMapping(value = "/findCardDetailByCardNumOrMoblie", method = { RequestMethod.POST,RequestMethod.GET })
	public void findCardDetailByCardNumOrMoblie(HttpServletRequest request,HttpServletResponse response,AfterSalesNew afterSalesNew){
		JSONObject jsonObject = new JSONObject();
		List<AfterSalesNew> afterSalesNews = Lists.newArrayList();
		//查询结果集
		try {
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			afterSalesNew.setLoginBy(lk.getId());
			afterSalesNews = afterSalesService.findCardDetailForCardAfterSales(afterSalesNew);
			if(CollectionUtils.isNotEmpty(afterSalesNews)){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("findCardDetailByCardNumOrMoblie："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",afterSalesNews);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 *
	 * 功能描述: 根据卡ID查询卡售后详情
	 *
	 * @param: [cardId, request, response]
	 * @return: void
	 * @auther: lenovo
	 * @date: 2018/6/21 16:09
	 */
	@RequestMapping("/findCardAfterSalesDetail/{cardId}")
	public void findCardAfterSalesDetail(@PathVariable("cardId") Long cardId,HttpServletRequest request,HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		AfterSalesNew afterSalesNew = new AfterSalesNew();
		try {
			afterSalesNew = afterSalesService.findCardAfterSalesDetail(cardId);
			if (afterSalesNew == null) {
				msg = BaseConstants.NULL;
			} else {
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("findCardAfterSalesDetail：" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("afterSalesNew", afterSalesNew);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 按id查询解决方案可退金额
	 * @param request
	 * @param response
	 */
	@RequestMapping("/querySolutionMoney")
	public void querySolutionMoney(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject jsonObject = new JSONObject();
		AfterSalesNew afterSalesNew = null;
		try {
			afterSalesNew = afterSalesService.querySolutionMoney(id);
			if(afterSalesNew != null){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("querySolutionSchedule："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("afterSalesNew",afterSalesNew);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 页面订单回购按钮
	 *
	 * @param response		相应对象
	 * @param request		请求对象
	 * @param afterSalesNew	请求参数
	 * @auther: lenovo
	 * @date: 2018/7/11 20:26
	 */
	@RequestMapping("/buyBackOrder")
	public void buyBackOrder(HttpServletResponse response,HttpServletRequest request,AfterSalesNew afterSalesNew){
		JSONObject jsonObject = new JSONObject();
		try {
			Long orderId = afterSalesNew.getOrderId();
			Order order = orderService.loadOrder(orderId);
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(currentDate);
			jsonObject.accumulate("orderId",order.getOrderCode());
			jsonObject.accumulate("date",date);
			String result = installmentService.haijinblAgreementEnd(jsonObject.toString());
			com.alibaba.fastjson.JSONObject jsonResult = com.alibaba.fastjson.JSONObject.parseObject(result);
			result = jsonResult.getString("code");
			if("0".equals(result)){//终止成功
				msg = "00";
			}else if("1".equals(result)){//未找到相应信息
				msg = "01";
			}else if("-2".equals(result)){//接口执行失败
				msg = "02";
			}
		} catch (Exception e) {
			log.error("buyBackOrder方法执行失败,错误信息:"+e);
			msg = "03";//系统失败提示前台
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 检查服务分期退费和分期服务费退费是否有，并且成功的记录
	 * 操作人：周鑫
	 * 2018年12月12日下午4:31:05
	 */
	@RequestMapping(value = "/premiumCheck", method = { RequestMethod.POST,RequestMethod.GET })
	public void premiumCheck(HttpServletRequest request,HttpServletResponse response,String orderId,String afterSalesKind){
		JSONObject jsonObject = new JSONObject();
		Integer count=0;
		//查询结果集
		try {
			count=afterSalesService.selectPremiumCheck(orderId,afterSalesKind);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("premiumCheck："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("count",count);
		responseSendMsg(response,jsonObject.toString());
	}

	@RequestMapping(value = "/queryAfterReturnableSalary", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryAfterReturnableSalary(HttpServletRequest request,HttpServletResponse response,AfterSalesNew afterSalesNew){
		JSONObject jsonObject = new JSONObject();
		HashMap<String, Object> result = new HashMap<>();
		try {
			result = afterSalesService.queryAfterReturnableSalary(afterSalesNew);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryAfterReturnableSalary："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("result",result);
		responseSendMsg(response,jsonObject.toString());
	}

	@RequestMapping(value = "/queryAfterReturnableMessageFee", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryAfterReturnableMessageFee(HttpServletRequest request,HttpServletResponse response,AfterSalesNew afterSalesNew){
		JSONObject jsonObject = new JSONObject();
		HashMap<String, Object> result = new HashMap<>();
		try {
			result = afterSalesService.queryAfterReturnableMessageFee(afterSalesNew);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryAfterReturnableMessageFee："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("result",result);
		responseSendMsg(response,jsonObject.toString());
	}
	/**
	 * 
	 * @Description: 验证工资是否有海金缴费  
	 * @param request
	 * @param response
	 * @param payfeeId
	 * @param orderCode      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	@RequestMapping(value = "/agreementSeaGlodPayFree", method = { RequestMethod.POST,RequestMethod.GET })
	public void agreementSeaGlodPayFree(HttpServletRequest request,HttpServletResponse response,String payfeeId,String orderCode){
		JSONObject jsonObject = new JSONObject();
		Integer count=0;
		//查询结果集
		try {
			count=afterSalesService.selectPremiumCheck(payfeeId,orderCode);
			if(count==0){
				String validaExistBill = customerInfoService.validaExistBill(orderCode);
				com.alibaba.fastjson.JSONObject jsonResult = com.alibaba.fastjson.JSONObject.parseObject(validaExistBill);
				String  result = jsonResult.getString("code");
				count=Integer.parseInt(result);
			}else{
				count=1;
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("premiumCheck："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("count",count);
		responseSendMsg(response,jsonObject.toString());
	}
}
