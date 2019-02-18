package com.emotte.order.order.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.model.ThreeOrderAccounts;
import com.emotte.order.order.model.ThreeOrderAddress;
import com.emotte.order.order.model.ThreeOrderCard;
import com.emotte.order.order.model.ThreeOrderCategory;
import com.emotte.order.order.model.ThreeOrderDictionary;
import com.emotte.order.order.model.ThreeOrderProduct;
import com.emotte.order.order.model.ThreeOrderUser;
import com.emotte.order.order.service.ThreeOrderInService;
import com.emotte.order.order.service.ThreeOrderOutService;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.ExcelUtil;
import com.emotte.order.util.ExportExcelUtil;
import com.emotte.server.util.CookieUtils;

@Controller
@RequestMapping("/threeOrderOut")
public class ThreeOrderOutController extends BaseController {

	@Resource
	private ThreeOrderOutService threeOrderOutService;
	
	@Resource
	private ThreeOrderInService threeOrderInService;
	
	/**
	 * 查询三级分类
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping(value = "/queryOrderType", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryOrderType(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderCategory> list = null;
		try {
			list = threeOrderOutService.queryOrderType(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询数据字典表
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping(value = "/queryBaseDictionary", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryBaseDictionary(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderDictionary> list = null;
		try {
			list = threeOrderOutService.queryBaseDictionary(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询省市列表
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping(value = "/queryBaseCity", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryBaseCity(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderDictionary> list = null;
		try {
			list = threeOrderOutService.queryBaseCity(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}

	/**
	 * 给出订单列表
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrder(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order, Page page) {
		List<ThreeOrder> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			list = this.threeOrderOutService.queryOrder(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询单个订单信息
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/queryOrderOneDetail", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryOrderOneDetail(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		ThreeOrder orderObj = null;
		try {
			if (order != null) {
				List<ThreeOrder> list = threeOrderOutService.queryOrderOneDetail(order);
				if (list.size() > 0) {
					orderObj = list.get(0);
					msg = BaseConstants.SCUUESS;
				} else {
					msg = BaseConstants.NULL;
				}
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("order", orderObj);
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询单个订单结算信息
	 * @param request
	 * @param response
	 * @param order
	 */
	@RequestMapping(value = "/queryOrderOneAccounts", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryOrderOneAccounts(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrderAccounts accounts) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderAccounts> list = null;
		try {
			list = threeOrderOutService.queryOrderOneAccounts(accounts);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 保存结算单
	 * @param request
	 * @param response
	 * @param accounts
	 */
	@RequestMapping(value = "/saveOrderAccounts", method = {RequestMethod.POST, RequestMethod.GET })
	public void saveOrderAccounts(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrderAccounts accounts) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderAccounts> list = null;
		try {
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			accounts.setVersion(0L);
			accounts.setPayStatus("20110001");
			accounts.setIsBackType("2");
			accounts.setValid(1);
			accounts.setPayKind("2");
			accounts.setBussStatus("1");
			accounts.setCreateBy(Long.valueOf(userId));
			accounts.setUpdateBy(Long.valueOf(userId));
			threeOrderOutService.saveOrderAccounts(accounts);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}

	/**
	 * 选择客户列表
	 * @param request
	 * @param response
	 * @param orderCustomer
	 * @param page
	 */
	@RequestMapping(value = "queryOrderUser", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryOrderUser(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrderUser order, Page page) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderUser> list = null;
		try {
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel()); 
			// 超级用户有查询所有用户权限
			if(order.getLoginLevel()!=null&&order.getLoginLevel()==99){
				order.setAdValid(1);
			}
		   	list= threeOrderOutService.queryOrderUser(order, page);
			if (list != null && list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询客户详情
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 */
	@RequestMapping(value = "queryUserDetail", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryUserDetail(HttpServletRequest request, HttpServletResponse response, ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		ThreeOrderUser orderUser = null;
		try {
			orderUser = threeOrderOutService.queryUserDetail(order);
			if (orderUser != null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("orderUser", orderUser);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 保存添加客户
	 * @param request
	 * @param response
	 * @param orderUser
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrderUser", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertUser(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrderUser orderUser) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			int exist = threeOrderOutService.queryOrderUserByMobile(orderUser);
			if(0==exist){
				String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
				orderUser.setVersion(0L);
				orderUser.setValid(1);
				orderUser.setLoginName(orderUser.getRealName());
				orderUser.setCreateBy(Long.valueOf(userId));
				orderUser.setUpdateBy(Long.valueOf(userId));
				threeOrderOutService.saveOrderUser(orderUser);
				msg = BaseConstants.SCUUESS;
			}else{
				msg = "06";		//手机号码已存在
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询商品的一级分类
	 * @param request
	 * @param response
	 * @param ProductCategory
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCategory", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryCategory(HttpServletRequest request, HttpServletResponse response, ThreeOrderCategory category,
			Page page) throws Exception {
		List<ThreeOrderCategory> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = threeOrderOutService.queryCategory(category);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 根据分类查询商品
	 * @param request
	 * @param response
	 * @param product
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryProduct", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryProduct(HttpServletRequest request, HttpServletResponse response, ThreeOrderProduct product,
			Page page) throws Exception {
		List<ThreeOrderProduct> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			product.setOrgId(String.valueOf(CookieReaderUtil.cookieReader(request).getDeptId()));
			list = threeOrderOutService.queryProduct(product);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询用户收货地址
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAddress", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAddress(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		List<ThreeOrderAddress> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = threeOrderOutService.queryAddress(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询用户收货地址详情
	 * @param request
	 * @param response
	 * @param orderAddress
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAddressDetail", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAddressDetail(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		ThreeOrderAddress userAddress = null;
		JSONObject jsonObject = new JSONObject();
		try {
			userAddress = threeOrderOutService.queryUserAddressDetail(order);
			if (null!=userAddress) {
				String code = userAddress.getCityCode();
				if(code.length()==12){
					userAddress.setCountryCode(code);
					userAddress.setCityCode(code.substring(0, 9));
					userAddress.setProvinceCode(code.substring(0, 6));
				}else if(code.length()==9){
					userAddress.setCountryCode("");
					userAddress.setCityCode(code);
					userAddress.setProvinceCode(code.substring(0, 6));
				}
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("userAddress", userAddress);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 保存用户收货地址
	 * @param request
	 * @param response
	 * @param address
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveAddress", method = { RequestMethod.POST, RequestMethod.GET })
	public void saveUserAddress(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrderAddress address) throws Exception {
		List<ThreeOrderAddress> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			if(StringUtils.isNotBlank(address.getCountryCode())){
				address.setCityCode(address.getCountryCode());
			}
			if(address.getId()==null){
				address.setVersion(0L);
				address.setValid("1");
				address.setIsDefault("1");
				address.setIsFront("2");
				address.setCreateBy(Long.valueOf(userId));
				address.setUpdateBy(Long.valueOf(userId));
				threeOrderOutService.saveAddress(address);
			}else{
				threeOrderOutService.updateAddress(address);
			}
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 保存订单
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void saveOrder(HttpServletRequest request, HttpServletResponse response, ThreeOrder order,
			Page page) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			order.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			order.setCreateDept(CookieUtils.getJSON(request).getString("deptId"));
			order.setRechargeBy(CookieUtils.getJSON(request).getLong("id"));
			order.setRechargeDept(CookieUtils.getJSON(request).getString("deptId"));
//			order.setFollowBy(CookieUtils.getJSON(request).getLong("id"));
//			order.setFollowDept(CookieUtils.getJSON(request).getString("deptId"));
			
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			ThreeOrderUser orderUser = threeOrderOutService.queryUserDetail(order);
			if("1".equals(orderUser.getIsVip())){
				//如果是vip用户，则是会员价
				order.setDictCode("20000006");
			}else if("1".equals(orderUser.getIsBigCust())){
				//如果是大客户，则是大客户价
				order.setDictCode("20000003");
			}else {
				//否则是市场价
				order.setDictCode("20000002");
			}
			//ThreeOrderPrice orderProductPrice = threeOrderOutService.queryProductPrice(order);
			ThreeOrderAddress orderUserAddress = threeOrderOutService.queryUserAddressDetail(order);
			//if(null!=orderProductPrice.getPrice()){
			//	order.setTotalPay(String.valueOf(orderProductPrice.getPrice().doubleValue()*1));
			//}
			//order.setPrice(orderProductPrice.getPrice());
			order.setAddress(orderUserAddress.getProvince()+orderUserAddress.getCity()+orderUserAddress.getCountry()
			+orderUserAddress.getChooseAddress()+(orderUserAddress.getAddressDetail()==null?"":orderUserAddress.getAddressDetail()));
			order.setAuthManagerId(userId);
			order.setUserId(orderUser.getId());
			order.setUserName(orderUser.getRealName());
			order.setUserProvince(orderUser.getUserProvince());
			order.setUserCity(orderUser.getUserCity());
			order.setUserArea(orderUser.getUserDistrict());
			order.setUserAddress(orderUser.getUserProvince()+orderUser.getUserCity()+orderUser.getUserDistrict()+
					(orderUser.getUserAddress()==null ? "":orderUser.getUserAddress()));
			order.setUserMobile(orderUser.getUserMobile());
			order.setUserEmail(orderUser.getUserEmail());
			order.setUserSex(orderUser.getUserSex());
			order.setUserCityCode(orderUser.getCityCode());
			order.setCity(orderUser.getUserCity());
			order.setReceiverName(orderUserAddress.getContactName());
			order.setReceiverMobile(orderUserAddress.getContactPhone());
			order.setReceiverProvince(orderUserAddress.getProvince());
			order.setReceiverCity(orderUserAddress.getCity());
			order.setReceiverArea(orderUserAddress.getCountry());
			order.setReceiverAddress(orderUserAddress.getAddressDetail());
			order.setReceiverCityCode(orderUserAddress.getCityCode());
			order.setLatitude(orderUserAddress.getLatitude());
			order.setLongitude(orderUserAddress.getLatitude());
			order.setCateType("4");
			order.setOrderStatus("4");
			order.setVersion(1L);
			order.setCritical("2");
			order.setIsInvoice("2");
			order.setPayStatus("20110001");
			order.setThreematchlock(3);
			order.setCreateBy(CookieReaderUtil.cookieReader(request).getId());
			order.setUpdateBy(CookieReaderUtil.cookieReader(request).getId());
		//	order.setCreateBy(Long.valueOf(userId));
	    //	order.setUpdateBy(Long.valueOf(userId));
			order.setValid("1");
			//验证用户是否分配有管家
			Long marketBy=threeOrderInService.checkUserKeeper(order.getUserId());
			if(marketBy!=null){
				order.setRechargeBy(marketBy);
				order.setRechargeDept(String.valueOf(threeOrderInService.queryManagerDeptId(marketBy)));
//				order.setFollowBy(marketBy);
//				order.setFollowDept(order.getRechargeDept());
				order.setCreateDept(String.valueOf(threeOrderInService.queryManagerDeptId(order.getCreateBy())));
			}
			threeOrderOutService.insertThreeOrder(order);//order表
			ThreeOrderProduct orderProduct = threeOrderOutService.queryProductDetail(order);
			order.setOrderId(order.getId().toString());
			order.setProductName(orderProduct.getName());
			//order.setQuantity(1);
			order.setSaleType("1");
			threeOrderOutService.insertThreeOrderItem(order);//item表
			order.setOrderItemId(order.getId().toString());
			threeOrderOutService.insertThreeOrderItemDetailServer(order);//detail_server表
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 去匹配订单
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/doMatch", method = { RequestMethod.POST, RequestMethod.GET })
	public void doMatch(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String[] orderIds = order.getOrderIds().split(",");
			int count = threeOrderOutService.checkMatch(orderIds);
			if(count==orderIds.length){
				threeOrderOutService.doMatch(orderIds);
				msg = BaseConstants.SCUUESS;
			}else{
				msg = "02";
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 订单结单
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/doBilling", method = { RequestMethod.POST, RequestMethod.GET })
	public void doBilling(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String[] orderIds = order.getOrderIds().split(",");
			int count = threeOrderOutService.checkBilling(orderIds);
			if(count==orderIds.length){
				threeOrderOutService.doBilling(orderIds);
				msg = BaseConstants.SCUUESS;
			}else{
				msg = "02";
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 匹配成功
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/matchSuccess", method = { RequestMethod.POST, RequestMethod.GET })
	public void matchSuccess(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			order.setUpdateBy(Long.valueOf(userId));
			threeOrderOutService.matchSuccess(order);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 匹配失败
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/matchFail", method = { RequestMethod.POST, RequestMethod.GET })
	public void matchFail(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			order.setUpdateBy(Long.valueOf(userId));
			threeOrderOutService.matchFail(order);
			/**************发送站内消息****************/
			//此处执行匹配失败操作之后，发送站内消息给客户
			/**************发送站内消息****************/
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询客户所含的消费卡
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCards", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryCards(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrderCard> list = null;
		try {
			list = threeOrderOutService.queryCards(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 添加缴费
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "/savePayfee", method = { RequestMethod.POST, RequestMethod.GET })
	public void savePayfee(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String userId =String.valueOf(CookieUtils.getJSONKey(request,"id"));
			order.setUpdateBy(Long.valueOf(userId));
			threeOrderOutService.savePayfee(order);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("queryCategory:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 订单管理(给出)
	 * 导出订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exportOrderExcel")
	public void exportOrderExcel(HttpServletRequest request, HttpServletResponse response,ThreeOrder order)
			throws IOException {
		order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
		order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
		order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
		order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
		JSONObject jsonObject = new JSONObject();
		//生成时间戳：年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date  =new Date();
		//生成0~999之间的随机数
		int num=(int)(Math.random()*999);
		//设置excel文件名：
		String fileName = "三方订单(给出)"+sdf.format(date) +"_"+ num;
		//excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
		String[] excelHeader = {"*城市#city","*订单编号#orderCode","*商品名称#productName","*下单人姓名#userName","*下单人电话#userMobile",
				"*接收人姓名#receiverName","*接收人电话#receiverMobile","*需求类型#typeText", 
				"*服务地址省份#receiverProvince","*服务地址城市#receiverCity","*服务地址地区#receiverArea","*客户地址#receiverAddress",
				"*服务开始时间#startTime","*服务结束时间#endTime","*创建时间#createTime","*服务人员#personName", 
				"*快递单号#postId","*订单金额#totalPay","*订单状态#orderStatus","备注#remark" };
		//查询所有商家订单列表,只有【4.己匹配】状态,可以导出。
		order.setOrderStatus("4");
		List<ThreeOrder> dataList = threeOrderOutService.queryOrderList(order);
		if(dataList.size() == 0){
			msg = BaseConstants.NULL;
		}
		try {
			//生成商家订单导出excel表格
			ExportExcelUtil.export(response, fileName, excelHeader, dataList);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("Error:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
	//	responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 订单管理(给出)
	 * 导入订单
	 * @param uploadFile
	 * @param response
	 * @param request
	 */
	@RequestMapping(value = "/excelToList", method = { RequestMethod.POST, RequestMethod.GET })
	public void excelToList(@RequestParam("excelFile") MultipartFile uploadFile, 
			HttpServletResponse response,HttpServletRequest request) {
		ExcelUtil excel = new ExcelUtil();
		JSONObject jsonObject = new JSONObject();
		try {
			//导入excel表头数组对应数据库的字段
			String[] parm = { "city", "orderCode","productName", "userName", "userMobile", "receiverName", "receiverMobile",
					"typeText", "receiverProvince", "receiverCity", "receiverArea", "receiverAddress", "startTime",
					"endTime", "createTime", "personName", "postId", "totalPay", "orderStatus", "remark" };
			//生成导入记录excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
			String[] exportParm = {"城市#city","订单编号#orderCode","*商品名称#productName","下单人姓名#userName","下单人电话#userMobile",
					"接收人姓名#receiverName", "接收人电话#receiverMobile","需求类型#typeText", 
					"服务地址省份#receiverProvince","服务地址城市#receiverCity", "服务地址地区#receiverArea", "客户地址#receiverAddress",
					"服务开始时间#startTime", "服务结束时间#endTime","创建时间#createTime","服务人员#personName", 
					"快递单号#postId", "订单金额#totalPay","订单状态#orderStatus", "备注#remark","结果#success","错误信息#errormsg"};
			InputStream input = uploadFile.getInputStream();
			String fileName = uploadFile.getOriginalFilename();
			// 将excel转换成list数据
			Map<String, List<Map<String, String>>> resultMap = excel.excelToList(parm, input, fileName);
			List<Map<String, String>> list = resultMap.get("list");
			if(list == null || list.size() == 0){
				jsonObject.accumulate("success", false);
				jsonObject.accumulate("errormsg", "excel格式错误或excel表中没有数据!");
				responseSendMsg(response, jsonObject.toString());
			}else{
				log.info("-------正在导入数据-------");
				Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
				String Stringid = String.valueOf(Cookieid);
				Long createBy = Long.valueOf(Stringid);
				
				//获取数据库中订单类型三级分类list
				List<Map<String, Object>> orderTypes = threeOrderInService.queryOrderType();
				//获取数据库中orderCode、orderStatus  list
				List<Map<String, Object>> orderCodes = threeOrderOutService.queryOrderCodeList();
				if(!list.isEmpty()){
					for(int i=0;i<list.size();i++){
						String orderCodeTmp = list.get(i).get("orderCode");
						if(orderCodes.contains(orderCodeTmp)){
							list.get(i).put("isrepeat", "1");
							list.get(i).put("success", "失败");
							list.get(i).put("errormsg", "订单为" + orderCodeTmp + "的与数据库重复");
							continue;
						}
						for(int j=i+1;j<list.size();j++){
							if(orderCodeTmp.equals(list.get(j).get("orderCode"))){
								list.get(i).put("isrepeat", "1");
								list.get(j).put("isrepeat", "1");
								list.get(i).put("success", "失败");
								list.get(j).put("success", "失败");
								list.get(i).put("errormsg", "订单为" + orderCodeTmp + "的与数据库重复");
								list.get(j).put("errormsg", "订单为" + orderCodeTmp + "的与数据库重复");
							}
						}
					}
					for(Map<String,String> map : list){
						try {
							//循环校验excel数据，并在数据库中更新相应状态
							threeOrderOutService.insertOrder(map,createBy,orderTypes);
							jsonObject.accumulate("success", true);
							jsonObject.accumulate("errormsg", "导入完成");
						} catch (Exception e) {
							jsonObject.accumulate("success", false);
							jsonObject.accumulate("errormsg", "未知错误");
						}
					}
				}
				//将校验后的excel数据添加校验状态以及原因，生成一个新的excel文档上传到服务器。
				String url = ExportExcelUtil.exportList(response, fileName, exportParm, list,request);
				if(url != null){
					//保存结果excel
					threeOrderOutService.savaExcelRecord(url,createBy,fileName);
				}
				responseSendMsg(response, jsonObject.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 给出订单导入记录
	 * @param request
	 * @param response
	 * @param recordStartTime
	 * @param recordEndTime
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryThreeOrderOutRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryThreeOrderRecord(HttpServletRequest request, HttpServletResponse response,
			ThreeOrder order,Page page) throws Exception {
		List<ThreeOrder> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			//查询订单导入记录列表
			list = this.threeOrderOutService.queryThreeOrderOutRecord(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 单次订单导入记录
	 * @param request
	 * @param response
	 * @param recordStartTime
	 * @param recordEndTime
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderImportRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderImportRecord(HttpServletRequest request, HttpServletResponse response,
			ThreeOrder order,Page page) throws Exception {
		List<ThreeOrder> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			//查询单次订单导入记录列表
			list = this.threeOrderOutService.queryOrderImportRecord(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/*导出订单列表*/
	@RequestMapping(value = "/exportExcelOut", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order, Page page) {
		
		JSONObject jsonObject = new JSONObject();
		Long a = System.currentTimeMillis();
		try {
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			request.setCharacterEncoding("utf-8");
			
			List<ThreeOrder> dataList = threeOrderOutService.queryOrderList(order);
			order.setOrderStatus("4");
			Workbook excel = this.threeOrderOutService.queryExcel(request, response, order, dataList, order.getLoginBy());
			String path = request.getRealPath("/");
			File filea = new File(path+"/excel");
			if (!filea.exists()) {
					filea.mkdir();
			}
			FileOutputStream file = new FileOutputStream(path+"/excel/"+a+".xlsx",true);
		    excel.write(file);
		    file.close();
		    msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		System.out.println(a);
		 jsonObject.accumulate("msg", msg); 
		 jsonObject.accumulate("a",a); 
		 responseSendMsg(response, jsonObject.toString());}
	/*下载表格*/
	@RequestMapping(value = "/downExcel", method={RequestMethod.POST,RequestMethod.GET})	
	public void downExcel(HttpServletRequest request,HttpServletResponse response, String filename){
			try {
				request.setCharacterEncoding("utf-8");
			  	@SuppressWarnings("deprecation")
				String path = request.getRealPath("/");
			  	FileInputStream file = new  FileInputStream(path + "/excel/" + filename + ".xlsx");
			    OutputStream output = response.getOutputStream();
				response.reset();
			    response.setHeader("Content-disposition", "attachment;filename=excel.xls");
			    response.setContentType("application/msexcel"); 
			    byte[] b = new byte[526];
			    while((file.read(b)) != -1){
			    	output.write(b);
			    }
			    file.close();
			    output.flush();
			    output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
