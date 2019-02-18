package com.emotte.order.order.controller;

import java.math.BigDecimal;
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

import com.emotte.order.order.model.AfterFees;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.service.AfterFeesService;
import com.emotte.order.util.Constants;

@Controller
@RequestMapping("/afterFees")
public class AfterFeesController extends BaseController {

	@Resource
	private AfterFeesService afterFeesService;

	@RequestMapping(value = "/loadAfterFees", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadAfterFees(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			AfterFees afterFees = this.afterFeesService.loadAfterFees(id);
			if (afterFees == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("afterFees", afterFees);
		} catch (Exception e) {
			log.error("loadAfterFees:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_afterFees");
		return mv;
	}

	@RequestMapping(value = "/queryAfterFees", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAfterFees(HttpServletRequest request,
			HttpServletResponse response, AfterFees afterFees, Page page)
			throws Exception {
		List<AfterFees> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.afterFeesService.queryAfterFees(afterFees, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAfterFees:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertAfterFees", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertAfterFees(HttpServletRequest request,
			HttpServletResponse response, AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.afterFeesService.insertAfterFees(afterFees);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAfterFees:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateAfterFees", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAfterFees(HttpServletRequest request,
			HttpServletResponse response, AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.afterFeesService.updateAfterFees(afterFees);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAfterFees:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询可退金额
	 * @author lidenghui  
	 * @date 2018年4月25日 下午7:24:03
	 */
	@RequestMapping(value = "/queryPayMoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPayMoney(HttpServletRequest request, HttpServletResponse response,
			AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		Double payfeeMoney=null;
		try {
			AfterFees afterFees1 = this.afterFeesService.queryPayMoney(afterFees);
			if(null != afterFees1) {
				 payfeeMoney=afterFees1.getPayfeeMoney();
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryPayMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("payfeeMoney", payfeeMoney);
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询结算单类型为10
	 * @author lidenghui  
	 * @date 2018年4月26日 上午11:52:05
	 */
	@RequestMapping(value = "/queryAccountPay", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAccountPay(HttpServletRequest request, HttpServletResponse response,
			AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		BigDecimal accountAmount=null;
		try {
			//查看订单下面是否有缴费
			Payfee payfee = this.afterFeesService.queryAccountPay(afterFees);
			if(null != payfee) {
				 accountAmount=payfee.getFeeSum();//已扣款
			}else {
				 accountAmount=new BigDecimal(0);
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryAccountPay:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("accountAmount", accountAmount);
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 可扣信息费
	 * @author lidenghui  
	 * @date 2018年4月26日 下午5:23:01
	 */
	@RequestMapping(value = "/queryMembershipFee", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryMembershipFee(HttpServletRequest request, HttpServletResponse response,
			AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<AfterFees> list=null;
		try {
			//查看订单下面是否有缴费
			list = this.afterFeesService.queryMembershipFee(afterFees);
			if(null!=list && list.size()>0) {
				msg = Constants.SCUUESS;
			}else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAccountPay:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 
	 * @author zs  
	 * 查询非白条可扣预存服务费金额
	 */
	@RequestMapping(value = "/queryNotIousSalaryMoney", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryNotIousSalaryMoney(HttpServletRequest request, HttpServletResponse response,AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<AfterFees> list = null;
		try {
			list = this.afterFeesService.queryNotIousSalaryMoney(afterFees);
			if(list != null && list.size() > 0) {
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryNotIousSalaryMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询分期手续费
	 * @author lidenghui  
	 * @date 2018年4月27日 下午7:02:39
	 */
	@RequestMapping(value = "/queryInstallmentFee", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryInstallmentFee(HttpServletRequest request, HttpServletResponse response,AfterFees afterFees) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<AfterFees> list = null;
		try {
			list = this.afterFeesService.queryInstallmentFee(afterFees);
			if(list != null && list.size() > 0) {
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryInstallmentFee:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

}
