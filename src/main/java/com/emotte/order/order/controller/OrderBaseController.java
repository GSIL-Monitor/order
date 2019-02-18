package com.emotte.order.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.Category;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.Managers;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/orderbase")
public class OrderBaseController extends BaseController{
	
	@Resource
	private OrderBaseService orderBaseService;
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 查询 t_base_dictionary
	 * @param dictCode  数据字典上级编码
	 * @param dictLength 数据字典需要的编码长度
	 * @param dictLength 页面需要加载的下拉框ID
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryBaseDictionary", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryBaseDictionary(HttpServletRequest request,
			HttpServletResponse response, String dictCode,int dictLength) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderBaseService.queryBaseDictionary(dictCode, dictLength);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询 t_base_dictionary
	 * @param dictCode  数据字典上级编码
	 * @param dictLength 数据字典需要的编码长度
	 * @param dictLength 页面需要加载的下拉框ID
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryServerType", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryServerType(HttpServletRequest request,
			HttpServletResponse response, String dictCode,int dictLength) throws Exception {
		JSONObject jsonObject=new JSONObject();
		  List<Dictionary> list=null;  
		try{
			list=this.orderBaseService.queryServerType(dictCode, dictLength);
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("list", list);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询 t_custom_channel
	 * @param dictCode  数据字典上级编码
	 * @param dictLength 数据字典需要的编码长度
	 * @param typeId 页面需要加载的下拉框ID
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryServerChannel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryServerChannel(HttpServletRequest request,
			HttpServletResponse response,  String dkey,Integer housekeeperAvailable) throws Exception {
		JSONObject jsonObject=new JSONObject();
		List<Dictionary> list = null;
		try{
			if(null!=housekeeperAvailable){
				list = this.orderBaseService.queryServerChannel(dkey,housekeeperAvailable);
			}else{
				list = this.orderBaseService.queryServerChannel(dkey);
			}
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
			
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("dict", list);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryServerChannelBak", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryServerChannelBak(HttpServletRequest request,
			HttpServletResponse response,  String dkey) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderBaseService.queryServerChannelBak(dkey);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询门店基地  
	 * t_auth_org
	 */
	@RequestMapping(value = "/querydeptname", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void querydeptname(HttpServletRequest request,HttpServletResponse response,Dictionary  dictionary) throws Exception {
		List<Dictionary> list=null;
		JSONObject jsonObject=new JSONObject();
		try{
			list = this.orderBaseService.querydeptname(dictionary);
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("list", list);
			responseSendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询门店基地 带部门
	 * t_auth_org
	 */
	@RequestMapping(value = "/querydeptnameWithDept", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void querydeptnameWithDept(HttpServletRequest request,HttpServletResponse response,Dictionary  dictionary) throws Exception {
		List<Dictionary> list=null;
		JSONObject jsonObject=new JSONObject();
		try{
			list = this.orderBaseService.querydeptnameWithDept(dictionary);
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("list", list);
			responseSendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询管家  
	 * t_auth_manager
	 */
	@RequestMapping(value = "/queryguanjia", method={RequestMethod.POST,RequestMethod.GET})
	public void queryChannel(HttpServletRequest request,HttpServletResponse response, Managers managers)throws Exception{
		  List<Managers> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.orderBaseService.queryguanjia(managers);
			if(list.size()>0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryChannel:"+e);
			msg=Constants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 查询 t_base_city
	 * @param dkey: city_code 
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryOriginDictionary", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryOriginDictionary(HttpServletRequest request,
			HttpServletResponse response, String dkey) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderBaseService.queryOriginDictionary(dkey);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryAllSelect", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAllModuleNoFilter(HttpServletRequest request,
			HttpServletResponse response, Long pid,String types) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderBaseService.queryDictionaryMapper(pid, types);
			jsonObject.accumulate("se",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/insertUser", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertUser(HttpServletRequest request,HttpServletResponse response, OrderUser orderUser) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			orderUser.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			orderUser.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			orderUser.setDeptId(CookieUtils.getJSON(request).getLong("deptId"));
			orderUser.setLoginName(CookieUtils.getJSON(request).getString("realName"));
			orderUser.setLoginDeptName(CookieUtils.getJSON(request).getString("orgName"));
			int orgType = CookieUtils.getJSON(request).getInt("orgType");
			if(orgType==5||orgType==7){
				orderUser.setOusekeeperOrChanne(1);
			}else{
				orderUser.setOusekeeperOrChanne(2);
			}
			if(orderUser.getId()!=null){
				// 正常订单修改时，不修改部门
				orderUser.setDeptId(null);
			}
			this.orderBaseService.insertUser(orderUser);
			msg = BaseConstants.SCUUESS;
			jsonObject.accumulate("orderUser",orderUser);
		}catch(Exception e){
			log.error("insertUser方法错误,错误信息:"+ ExceptionUtils.getStackTrace(e));
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/checkUserByUser", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void checkUserByUser(HttpServletRequest request,
			HttpServletResponse response, OrderUser user) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			OrderUser orderUser = new OrderUser();
			orderUser.setContactPhone(user.getContactPhone());
			orderUser.setValid(1);
			List<OrderUser> listUser = null; 
			if(orderUser.getContactPhone()!=null && orderUser.getContactPhone().length()>=5){
				listUser = orderBaseService.queryUserAddressByMobile(orderUser);
			}
			if(listUser!=null && listUser.size()>0){
				jsonObject.accumulate("msg", BaseConstants.SCUUESS);
				jsonObject.accumulate("list", listUser);
			}else{
				jsonObject.accumulate("msg", BaseConstants.NULL);
				
			}
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryUserMapper", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryUserMapper(HttpServletRequest request,
			HttpServletResponse response, OrderUser orderUser,Page page) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			orderUser.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			orderUser.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			orderUser.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel()); 
			// 超级用户有查询所有用户权限
			if(orderUser.getLoginLevel()!=null&&orderUser.getLoginLevel()==99){
				orderUser.setAdValid(1);
			}
			List<OrderUser> listUser = orderBaseService.queryUserMapper(orderUser,page);
			if(listUser!=null && listUser.size()>0){
				jsonObject.accumulate("msg", BaseConstants.SCUUESS);
				jsonObject.accumulate("list", listUser);
			}else{
				jsonObject.accumulate("msg", BaseConstants.NULL);
				
			}
			jsonObject.accumulate("page", page);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/insertUserAddress", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertUserAddress(HttpServletRequest request,
			HttpServletResponse response, OrderUser orderUser) throws Exception {
		try{
			if(orderUser.getId()==0){
				orderUser.setId(null);
				orderUser.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
				this.orderBaseService.insertUserAddress(orderUser);
			}else{
				orderUser.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
				this.orderBaseService.updateUserAddress(orderUser);
			}
			OrderUser oUser = new OrderUser();
			List<OrderUser> olList = new ArrayList<OrderUser>();
			olList.add(this.orderBaseService.queryUserAddressMapper(oUser).get(0));
			JSONObject jsonObject=new JSONObject();
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("list", olList);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryUserAddressMapper", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryUserAddressMapper(HttpServletRequest request,
			HttpServletResponse response, OrderUser oUser) throws Exception {
		try{
			List<OrderUser> olList = new ArrayList<OrderUser>();
			olList = this.orderBaseService.queryUserAddressMapper(oUser);
			JSONObject jsonObject=new JSONObject();
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("list", olList);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryBaseCity", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryBaseCity(HttpServletRequest request,
			HttpServletResponse response, String code, int length){
		try{
			JSONObject jsonObject=new JSONObject();
			List<OrderBaseModel> list = this.orderBaseService.queryBaseCity(code,length);
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("list", list);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author wn
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderBasicServer", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryOrderBasicServer(HttpServletRequest request,HttpServletResponse response, Long id){
		JSONObject jsonObject=new JSONObject();
		List<OrderBaseModel> list = null;
		Integer orgType = 1;
		try{
			if(id!=null && id>0L){
				list = this.orderBaseService.queryOrderBasicServer(id);
				//获取登录人部门类型
				Managers managers = new Managers();
				managers.setId(CookieReaderUtil.cookieReader(request).getId());
				List<Managers> managerList = this.orderBaseService.queryguanjia(managers);
				if (managerList.size() != 0 && !managerList.isEmpty()) {
					orgType = managerList.get(0).getType2();
				}
				msg = BaseConstants.SCUUESS;
			}
		}catch(Exception e){
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("orgType", orgType);
		responseSendMsg(response, jsonObject.toString());
		
	}
	@RequestMapping(value = "/queryOrderBasicServerType", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryOrderBasicServerType(HttpServletRequest request,HttpServletResponse response, Long id){
		JSONObject jsonObject=new JSONObject();
		List<OrderBaseModel> list = null;
		try{
			if(id!=null && id>0L){
				list = this.orderBaseService.queryOrderBasicServertype(id);
				msg = BaseConstants.SCUUESS;
			}
		}catch(Exception e){
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
		
	}
	
	/**
	 * 查询肖前手机注册用户是否存在
	 * @param request
	 * @param response
	 * @param mobile
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/checkedUserOrNot", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void checkedUserOrNot(HttpServletRequest request,
			HttpServletResponse response, String mobile){
		try{
			JSONObject jsonObject=new JSONObject();
			int aa = this.orderBaseService.checkedUserOrNot(mobile);
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("userCount", aa);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
	
	
	
	/**
	 * 渠道经理
	 * 查询渠道
	 * @param request
	 * @param response
	 * @param dkey
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrderChannel", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryOrderChannel(HttpServletRequest request,HttpServletResponse response,Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		List<Dictionary> list = new ArrayList<Dictionary>();
		try{
			Long loginBy = CookieReaderUtil.cookieReader(request).getId();
			Integer channelManagerType = this.orderService.queryChannelManagerType(loginBy);
			if(channelManagerType==1){
				dictionary.setPartnerManagerId(loginBy);
				list = this.orderBaseService.queryOrderChannel(dictionary);
			}else if(channelManagerType==2){
				dictionary.setManagerId(loginBy);
				list = this.orderBaseService.queryOrderChannel(dictionary);
			}
			if(list.size() > 0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		}catch(Exception e){
			msg = Constants.FAIL;
			log.error("queryOrderChannel"+e);
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询城市,商品 配置库存
	 * @param cityCode cateCode
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectStock", method = { RequestMethod.POST,RequestMethod.GET })
	public void selectStock(HttpServletRequest request,HttpServletResponse response,Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		List<Dictionary> list = new ArrayList<Dictionary>();
		int count = 0;
		try{
			count = this.orderBaseService.selectStock(dictionary);
			
			if(count > 0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		}catch(Exception e){
			msg = Constants.FAIL;
			log.error("selectStock"+e);
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("count", count);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询库存数量
	 * @param cityCode cateCode
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkNumStock", method = { RequestMethod.POST,RequestMethod.GET })
	public void checkNumStock(HttpServletRequest request,HttpServletResponse response,Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		int count = 0;
		try{
			count = this.orderBaseService.checkNumStock(dictionary);
			
			if(count > 0) {
				msg=Constants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		}catch(Exception e){
			msg = Constants.FAIL;
			log.error("selectStock"+e);
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("count", count);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询城市关联分类
	 * @author 张顺
	 * @param request
	 * @param response
	 * @param categoryCity
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCategoryCity", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryCategoryCity(HttpServletRequest request, HttpServletResponse response, Category category) throws Exception {
		List<Category> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderBaseService.queryCategoryCity(category);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCategoryCity:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 
	 * 查询渠道 2018
	 * 条件：已启用、管家可见、业务类型为订单和通用
	 * @param request
	 * @param response
	 * @param dictionary
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChannels", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryChannels(HttpServletRequest request, HttpServletResponse response,Dictionary dictionary) throws Exception {
		List<Dictionary> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderBaseService.queryChannels(dictionary);
			if (list != null && list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryChannels:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
}
