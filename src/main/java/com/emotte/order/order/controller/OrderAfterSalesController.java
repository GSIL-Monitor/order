package com.emotte.order.order.controller;

import com.alibaba.fastjson.JSONArray;
import com.emotte.dubbo.activities.service.CardDubboService;
import com.emotte.dubbo.activities.service.CardPayDubboService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderAfterSales;
import com.emotte.order.order.service.OrderAfterSalesService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.PayfeeService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.LoginCookieInfo;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/afterSales")
public class OrderAfterSalesController extends BaseController {

	@Resource
	private OrderAfterSalesService orderAfterSalesService;
	@Resource
	private OrderInterfaceService orderInterfaceService;
	@Resource
	private PayfeeService payfeeService;
	@Resource
	private OrderService orderService;
	@Resource
	private SMSServiceDubbo sMSServiceDubbo;
	@Resource
	private CardDubboService cardDubboService;
	@Resource
	private CardPayDubboService cardPayDubboService;

	@RequestMapping(value = "/loadAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void loadAfterSales(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			OrderAfterSales orderAfterSales = this.orderAfterSalesService.loadOrderAfterSales(id);
			if (orderAfterSales == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			jsonObject.accumulate("afterSales", orderAfterSales);
		} catch (Exception e) {
			log.error("loadAfterSales:" + e);
			msg = Constants.FAIL;
		}
		 jsonObject.accumulate("msg", msg);
    	 responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/queryAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales, Page page)
			throws Exception {
		List<OrderAfterSales> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo lc = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setFlagPage(1);
			orderAfterSales.setPage(page);
			orderAfterSales.setLoginBy(lc.getId());
			orderAfterSales.setLoginDept(lc.getDeptId());
			orderAfterSales.setLoginOrgCode(lc.getOrgCode());
			orderAfterSales.setLoginLevel(lc.getPermissionLevel());//默认权限级别
			if(lc.getId() == 10943 || lc.getId() == 95249 || lc.getId() == 95233){
				//10943 结算中心审核人
				//95249,95233 售后回访人
				orderAfterSales.setLoginLevel(10);
			}else{
				String orgCode = lc.getOrgCode();
				if (null != orgCode && !"".equals(orgCode)){
					if(orgCode.length() >= 12){
						String orgCodeSub = orgCode.substring(0,12);
						if (orgCodeSub != "" && "100100470008".equals(orgCodeSub)) {
							//客户服务部
							orderAfterSales.setLoginLevel(10);
						}
					}
				}
			}
			list = this.orderAfterSalesService.queryOrderAfterSales(orderAfterSales, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("list", list);
			jsonObject.accumulate("page", page);
			jsonObject.accumulate("loginLevel", orderAfterSales.getLoginLevel());
		} catch (BaseException e) {
			jsonObject.put("code", Constants.RET_ERROR);
			jsonObject.put("msg", e.getMessage());
		} catch (Exception e1) {
			jsonObject.put("code", Constants.RET_ERROR);
			jsonObject.put("msg", e1.getMessage());
		}
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertAfterSales", method = { RequestMethod.POST,RequestMethod.GET })
	public void insertAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		Long saleId = 0l;
		String saleKind = "";
		String auditFlag = "";
		String tip = "";
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setCreateBy(cookie.getId());
			orderAfterSales.setCreateDept(cookie.getDeptId());
			orderAfterSales.setLoginBy(cookie.getId());
			orderAfterSales.setLoginDept(cookie.getDeptId());
			orderAfterSales.setLoginLevel(cookie.getPermissionLevel());
			orderAfterSales.setIsAT(1);//手动录入
			//客户姓名
			if (orderAfterSales.getCustName() != null && orderAfterSales.getCustName() != "") {
				String custName = URLDecoder.decode(orderAfterSales.getCustName(),"UTF-8");
				orderAfterSales.setCustName(custName);
			}
			//主行名称
			if (orderAfterSales.getBankMainName() != null && orderAfterSales.getBankMainName() != "") {
				String bankMainName = URLDecoder.decode(orderAfterSales.getBankMainName(),"UTF-8");
				orderAfterSales.setBankMainName(bankMainName);
			}
			String message = this.orderAfterSalesService.insertOrderAfterSales(orderAfterSales);
			saleId = orderAfterSales.getId();
			saleKind = orderAfterSales.getAfterSalesKind();
			auditFlag = orderAfterSales.getAuditFlag();
			if (orderAfterSales.getId() == null) {
				msg = BaseConstants.NULL;
			}else if("".equals(message)){
				msg = Constants.HAVE_AFTERSALE;
			} else {
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("insertAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("saleId", saleId);
		jsonObject.accumulate("saleKind", saleKind);
		jsonObject.accumulate("auditFlag", auditFlag);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/*@RequestMapping(value = "/insertAfterSalesImg", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertAfterSalesImg(HttpServletRequest request, HttpServletResponse response,OrderAfterSales orderAfterSales) {
		JSONObject obj = new JSONObject();
		String imgInfo = null;
		try {
			imgInfo = ImgUploadUtil.upload(request,"AfterSalesImgs");
			System.out.println("imgInfo="+imgInfo);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAfterSalesImg:" + e);
			msg = Constants.FAIL;
		}
		obj.accumulate("msg", msg);
		obj.accumulate("imgInfo", imgInfo);
		responseSendMsg(response, obj.toString());
	}*/

	@RequestMapping(value = "/updateAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		Long saleId = 0l;
		String saleKind = "";
		String auditFlag = "";
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setUpdateBy(cookie.getId());
			orderAfterSales.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			orderAfterSales.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			orderAfterSales.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			//客户姓名
			if (orderAfterSales.getCustName() != null && orderAfterSales.getCustName() != "") {
				String custName = URLDecoder.decode(orderAfterSales.getCustName(),"UTF-8");
				orderAfterSales.setCustName(custName);
			}
			//主行名称
			if (orderAfterSales.getBankMainName() != null && orderAfterSales.getBankMainName() != "") {
				String bankMainName = URLDecoder.decode(orderAfterSales.getBankMainName(),"UTF-8");
				orderAfterSales.setBankMainName(bankMainName);
			}
			this.orderAfterSalesService.updateOrderAfterSales(orderAfterSales);
			saleId = orderAfterSales.getId();
			saleKind = orderAfterSales.getAfterSalesKind();
			auditFlag = orderAfterSales.getAuditFlag();
			/*Payfee account = new Payfee();
			account.setOrderId(orderAfterSales.getOrderId());
			account.setIsBackType(1);
			account.setValid(1);
			List<Payfee> accountList = this.payfeeService.queryAccount(account);
			if (accountList.size() != 0 && !accountList.isEmpty()) {
				Long accountId = accountList.get(0).getId();
				account.setId(accountId);
				if (orderAfterSales.getAuditFlag() != null) {
					account.setPayStatus(Integer.valueOf(orderAfterSales.getAuditFlag()));
				}
				account.setUpdateBy(cookie.getId());
				account.setAccountAmount(orderAfterSales.getRefundTotal());
				//将结算单判断状态，设置为是修改预估，缴费不用删除
				account.setIsYg("1");
				this.payfeeService.updateAccount(account);
			}*/
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("saleId", saleId);
		jsonObject.accumulate("saleKind", saleKind);
		jsonObject.accumulate("auditFlag", auditFlag);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertjiekouAfterSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertjiekouAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			//String afterSaleJson = "{\"orderId\":\"841345833914596\",\"createBy\":\"123456\"}";
			String afterSaleJson = "{\"reason\":\"今天\",\"createBy\":525940806048660,\"custMobile\":\"15321534279\",\"orderId\":915763450874532,\"loginLevel\":99,\"custName\":\"吴先生\",\"afterSalesKind\":5}";
			this.orderInterfaceService.insertAfterSales(afterSaleJson);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertjiekouAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryAfterSalesApprove", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryAfterSalesApprove(HttpServletRequest request,HttpServletResponse response, OrderAfterSales orderAfterSales, Page page)
			throws Exception {
		List<OrderAfterSales> list = null;
		JSONObject jsonObject = new JSONObject();
		int flag=0;
		try {
			LoginCookieInfo lc = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setFlagPage(1);
			orderAfterSales.setPage(page);
			orderAfterSales.setLoginBy(lc.getId());
			orderAfterSales.setLoginDept(lc.getDeptId());
			//判断部门code是否是客户服务部(下级含呼叫中心管家部)
			String orgCode = lc.getOrgCode();
			int orgCodeLen = orgCode.length() ;
			if (orgCodeLen >= 12 ) {
				String orgCodeSub = orgCode.substring(0,12);
				if (orgCodeSub != "" && "100100470008".equals(orgCodeSub) ) {
					orderAfterSales.setLoginLevel(10);//查看全部
				} else {
					orderAfterSales.setLoginLevel(lc.getPermissionLevel());
				}
			} else {
				orderAfterSales.setLoginLevel(lc.getPermissionLevel());
			}
			orderAfterSales.setLoginOrgCode(lc.getOrgCode());
			//判断是不是结算中心(990022905588415l),指定账号id(10943)
			long id=lc.getId();
			if(id == 10943) {
				//根据部门查询售后
				list = this.orderAfterSalesService.queryAfterSalesByDepartment(orderAfterSales, page);
				flag=1;
			}else {
				list = this.orderAfterSalesService.queryAfterSalesApprove(orderAfterSales, page);
			}
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAfterSalesApprove:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		jsonObject.accumulate("loginLevel", orderAfterSales.getLoginLevel());
		jsonObject.accumulate("flag", flag);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryVPHSales", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryVPHSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales, Page page)
			throws Exception {
		List<OrderAfterSales> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			orderAfterSales.setFlagPage(1);
			orderAfterSales.setPage(page);
			orderAfterSales.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			orderAfterSales.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			//判断部门code是否是客户服务部
			String orgCode = CookieReaderUtil.cookieReader(request).getOrgCode();
			int orgCodeLen = orgCode.length() ;
			if (orgCodeLen >= 12 ) {
				String orgCodeSub = orgCode.substring(0,12);
				if (orgCodeSub != "" && "100100470008".equals(orgCodeSub) ) {
					orderAfterSales.setLoginLevel(10);
				} else {
					orderAfterSales.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
				}
			} else {
				orderAfterSales.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			}
			orderAfterSales.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			list = this.orderAfterSalesService.queryVPHSales(orderAfterSales, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryVPHSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		jsonObject.accumulate("loginLevel", orderAfterSales.getLoginLevel());
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryVPHOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryVPHOrder(HttpServletRequest request, HttpServletResponse response, Order order, Page page)
			throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			order.setPage(page);
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			if(order.getLoginLevel()==null){
				order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			}
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			order.setUserName(order.getUserName()==null?"":order.getUserName().trim());
			order.setUserMobile(order.getUserMobile()==null?"":order.getUserMobile().trim());
			order.setOrderCode(order.getOrderCode()==null?"":order.getOrderCode().trim());
			order.setVphOrderId(order.getVphOrderId()==null?"":order.getVphOrderId().trim());
			list = this.orderAfterSalesService.queryVPHOrder(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryVPHOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/insertVphAfterSales", method = { RequestMethod.POST,RequestMethod.GET })
	public void insertVphAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setCreateBy(cookie.getId());
			orderAfterSales.setCreateDept(cookie.getDeptId());
			this.orderAfterSalesService.insertVphAfterSales(orderAfterSales);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertVphAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/updateVphAfterSales", method = { RequestMethod.POST,RequestMethod.GET })
	public void updateVphAfterSales(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setUpdateBy(cookie.getId());
			//查询订单信息
			Order order = this.orderService.loadOrder(orderAfterSales.getOrderId()); 
			//判断订单余额是否大于等于白条退款金额，是则允许退款，否则不新增售后
			if (order.getTotalPay() != null && orderAfterSales.getVphFee().compareTo(order.getTotalPay()) != 1) {
				this.orderAfterSalesService.updateVphAfterSales(orderAfterSales);
				msg = Constants.SCUUESS;
			} else {
				msg = "订单余额不足，不可退款！";
			}
		} catch (Exception e) {
			log.error("updateVphAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryVPHMaxMoney", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryVPHMaxMoney(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String maxMoney = "0";
		try {
			maxMoney = this.orderAfterSalesService.queryVPHMaxMoney(orderAfterSales);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryVPHMaxMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("money", maxMoney);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryBankMaxMoney", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryBankMaxMoney(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String memberShipMoney = "0";
		String salaryMoney = "0";
		try {
			//查询信息费最大退款金额
			memberShipMoney = this.orderAfterSalesService.queryMemberMoney(orderAfterSales.getOrderId());
			//查询应退服务人员服务费最大退款金额
			salaryMoney = this.orderAfterSalesService.querySalaryMoney(orderAfterSales.getOrderId());
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryBankMaxMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("memberShipMoney", memberShipMoney);
		jsonObject.accumulate("salaryMoney", salaryMoney);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/insertSaleServer", method = { RequestMethod.POST,RequestMethod.GET })
	public void insertSaleServer(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setCreateBy(cookie.getId());
			orderAfterSales.setCreateDept(cookie.getDeptId());
			//主行名称
			if (orderAfterSales.getBankMainName() != null && orderAfterSales.getBankMainName() != "") {
				String bankMainName = URLDecoder.decode(orderAfterSales.getBankMainName(),"UTF-8");
				orderAfterSales.setBankMainName(bankMainName);
			}
			this.orderAfterSalesService.insertSaleServer(orderAfterSales);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertSaleServer:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/queryVphSum", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryVphSum(HttpServletRequest request,
			HttpServletResponse response, Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String vphSumMoney = "0";
		try {
			
			//白条余额
			vphSumMoney = this.orderAfterSalesService.queryVphSum(orderId);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryBankMaxMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("vphSumMoney", vphSumMoney);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询要迁订单可迁缴费金额
	 * @param request
	 * @param response
	 * @param orderId
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChangeFee", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryChangeFee(HttpServletRequest request,
			HttpServletResponse response, Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String memberShipMoney = "0";
		String salaryMoney = "0";
		String vphSumMoney = "0";
		try {
			//查询信息费最大退款金额
			memberShipMoney = this.orderAfterSalesService.queryMemberMoney(orderId);
			//查询应退服务人员服务费最大退款金额
			salaryMoney = this.orderAfterSalesService.querySalaryMoney(orderId);
			//应退服务人员服务费 - 应退信息费  = 应退服务人员服务费最大退款金额
			BigDecimal memberShipFee = new BigDecimal(memberShipMoney);
			BigDecimal salaryFee = new BigDecimal(salaryMoney);
			salaryMoney = salaryFee.subtract(memberShipFee).toString();
			//白条余额
			vphSumMoney = this.orderAfterSalesService.queryVphSum(orderId);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryBankMaxMoney:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("memberShipMoney", memberShipMoney);
		jsonObject.accumulate("salaryMoney", salaryMoney);
		jsonObject.accumulate("vphSumMoney", vphSumMoney);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 新建迁单
	 * @param request
	 * @param response
	 * @param orderAfterSales
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertChangeOrder", method = { RequestMethod.POST,RequestMethod.GET })
	public void insertChangeOrder(HttpServletRequest request,
			HttpServletResponse response, OrderAfterSales orderAfterSales){
		JSONObject jsonObject = new JSONObject();
		String message = null;
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setCreateBy(cookie.getId());
			orderAfterSales.setCreateDept(cookie.getDeptId());
			orderAfterSales.setIsAT(1);//手动录入
			message = this.orderAfterSalesService.insertChangeOrder(orderAfterSales);
		}catch (BaseException e) {
			log.error("insertChangeOrder:" + e);
			message = e.getMessage();
			msg = Constants.HAVE_AFTERSALE;
			jsonObject.put("msg", msg);
			jsonObject.put("message", message);
			message = jsonObject.toString();
		}catch (Exception e1) {
			log.error("insertChangeOrder:" + e1);
			message = e1.getMessage();
			msg = Constants.FAIL;
			jsonObject.put("msg", msg);
			jsonObject.put("message", message);
			message = jsonObject.toString();
		}
		responseSendMsg(response, message);
	}
	
	/**
	 * 查询要迁至订单
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderA", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderA(HttpServletRequest request, HttpServletResponse response, Order order)
			throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			order.setFlagPage(1);
			order.setPage(page);
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			if(order.getLoginLevel()==null){
				order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			}
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			order.setOrderCode(order.getOrderCode()==null?"":order.getOrderCode().trim());
			list = this.orderAfterSalesService.queryOrderA(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrderA:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 验证当前订单是否已操作过一次迁单
	 * @param request
	 * @param response
	 * @param orderId
	 * @throws Exception
	 */
	@RequestMapping(value = "/isOrderSale", method = { RequestMethod.POST, RequestMethod.GET })
	public void isOrderSale(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = this.orderAfterSalesService.isOrderSale(orderId);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("isOrderSale:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询是否是非月嫂和延续订单
	 * @author lidenghui  
	 * @date 2018年4月27日 下午8:25:18
	 */
	@RequestMapping(value = "/queryOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrder(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		int flag = 0;
		try {
			Order order = this.orderAfterSalesService.queryOrder(orderId);
			if (null != order) {
				flag=1;  //符合条件
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("isOrderSale:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("flag", flag);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 新增方法-移动端添加售后申请
	 *
	 * @param request
	 * @param response
	 * @param orderAfterSales
	 * @throws Exception
     */
	@RequestMapping(value = "/saveAfterSales", method = { RequestMethod.POST,RequestMethod.GET })
	public void saveAfterSales(HttpServletRequest request, HttpServletResponse response, OrderAfterSales orderAfterSales){
		JSONObject jsonObject = new JSONObject();
		Long saleId = 0l;
		String saleKind = "";
		String auditFlag = "";
		String tip = "";
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			orderAfterSales.setCreateBy(cookie.getId());
			orderAfterSales.setCreateDept(cookie.getDeptId());
			orderAfterSales.setLoginBy(cookie.getId());
			orderAfterSales.setLoginDept(cookie.getDeptId());
			orderAfterSales.setLoginLevel(cookie.getPermissionLevel());
			orderAfterSales.setIsAT(1);//手动录入
			//客户姓名
			if (orderAfterSales.getCustName() != null && orderAfterSales.getCustName() != "") {
				String custName = URLDecoder.decode(orderAfterSales.getCustName(),"UTF-8");
				orderAfterSales.setCustName(custName);
			}
			//主行名称
			if (orderAfterSales.getBankMainName() != null && orderAfterSales.getBankMainName() != "") {
				String bankMainName = URLDecoder.decode(orderAfterSales.getBankMainName(),"UTF-8");
				orderAfterSales.setBankMainName(bankMainName);
			}
			//移动端提交售后修改时走新增流程，删除原有售后单
			orderAfterSalesService.changeAfterSalesValid(orderAfterSales.getId(),2);
			String message = this.orderAfterSalesService.insertOrderAfterSales(orderAfterSales);
			saleId = orderAfterSales.getId();
			saleKind = orderAfterSales.getAfterSalesKind();
			auditFlag = orderAfterSales.getAuditFlag();
			if (orderAfterSales.getId() == null) {
				msg = BaseConstants.NULL;
				orderAfterSalesService.changeAfterSalesValid(orderAfterSales.getId(),1);
			}else if("".equals(message)){
				msg = Constants.HAVE_AFTERSALE;
				orderAfterSalesService.changeAfterSalesValid(orderAfterSales.getId(),1);
			} else {
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			//出现异常数据回滚
			orderAfterSalesService.changeAfterSalesValid(orderAfterSales.getId(),1);
			log.error("insertAfterSales:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("saleId", saleId);
		jsonObject.accumulate("saleKind", saleKind);
		jsonObject.accumulate("auditFlag", auditFlag);
		responseSendMsg(response, jsonObject.toString());
	}


	/**
	 * 普通售后,按缴费方式汇总缴费明细
	 * @author zs
	 */
	@RequestMapping(value = "/queryPayGroupBy", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPayGroupBy(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, Object>> list = null;
		try {
			list = this.orderAfterSalesService.queryPayGroupBy(orderId);
			if (null != list) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryPayGroupBy:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 普通售后查询退款金额
	 *
	 * @param request
	 * @param response
	 * @param orderId
	 * @Author zhanghao
	 * @Date 20181214
     */
	@RequestMapping("/queryAfterSalesAmount")
	public void queryAfterSalesAmount(HttpServletRequest request,HttpServletResponse response,Long orderId){
		LogHelper.info(getClass(),"queryAfterSalesAmount,请求参数:"+orderId);
		JSONObject jsonObject = new JSONObject();
		Map<String,BigDecimal> map = null;
		try {
			map = orderAfterSalesService.queryAfterSalesAmountByOrderId(orderId);
			if(MapUtils.isNotEmpty(map)){
				msg = Constants.SCUUESS;
			}else{
				msg = Constants.NULL;
			}
		} catch (Exception e) {
			msg = Constants.FAIL;
			LogHelper.info(getClass(),"queryAfterSalesAmount,方法执行失败,订单ID:"+orderId+",错误信息:"+ ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("map",map);
		jsonObject.accumulate("msg",msg);
	}
	
	
	
	/**
	 * 
	 * @Description: 查询用户管家卡,可用的管家卡  
	 * @param request
	 * @param response
	 * @param payfeeId
	 * @param orderCode      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@RequestMapping(value = "/cardByProductCodesAndMobile", method = { RequestMethod.POST,RequestMethod.GET })
	public void agreementSeaGlodPayFree(HttpServletRequest request,HttpServletResponse response,String bindUserMobile,String orderId){
		JSONObject jsonObject = new JSONObject();
		List<Map<String, String>> list=new ArrayList<>();
		Integer count=0;
		//查询结果集
		try {
			//根据订单查询商品
			List<String> queryProductByOrderId = orderAfterSalesService.queryProductByOrderId(Long.parseLong(orderId));
			//queryProductByOrderId.add("101001001578532250120869");
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("mobile", bindUserMobile);
			map.put("productList", queryProductByOrderId);
			JSONObject json = JSONObject.fromObject(map);
			String cardByProductCodesAndMobile = cardDubboService.getCardByProductCodesAndMobile(json.toString());
			com.alibaba.fastjson.JSONObject jsonResult = com.alibaba.fastjson.JSONObject.parseObject(cardByProductCodesAndMobile);
			
			//{"code":"0","data":[{"money":"1000","numb":"p11111"},{"money":"1000","numb":"p11111"}]}
			JSONArray jsonArray = jsonResult.getJSONArray("data");
			for (int i = 0; i < jsonArray.size(); i++) {
				com.alibaba.fastjson.JSONObject Jr = jsonArray.getJSONObject(i);
				Map mapJr=Jr;
				list.add(mapJr);
			}
			System.out.println(list);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("premiumCheck："+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("count",count);
		jsonObject.accumulate("list",list);
		responseSendMsg(response,jsonObject.toString());
	}
	
	
	
	/**
	 * 
	 * @Description: 根据订单ID 查询是否包含制定的三级分类商品 
	 * @param request
	 * @param response
	 * @param orderId      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	@RequestMapping(value = "/selectProductThreeCode", method = { RequestMethod.POST,RequestMethod.GET })
	public void selectProductThreeCode(HttpServletRequest request,HttpServletResponse response,String orderId){
		JSONObject jsonObject = new JSONObject();
		Integer count=0;
		//查询结果集
		try {
			count = orderAfterSalesService.selectProductThreeCode(Long.parseLong(orderId));
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
