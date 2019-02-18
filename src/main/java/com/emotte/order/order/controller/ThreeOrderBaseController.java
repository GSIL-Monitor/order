package com.emotte.order.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.ThreeOrderBaseService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/threeorderbase")
public class ThreeOrderBaseController extends BaseController{
	
	@Resource
	private ThreeOrderBaseService threeOrderBaseService;
	
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
			StringBuffer strDict = this.threeOrderBaseService.queryDictionaryMapper(pid, types);
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
	public void insertUser(HttpServletRequest request,
			HttpServletResponse response, OrderUser orderUser) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			if(orderUser.getId()==null){
				this.threeOrderBaseService.insertUser(orderUser);
			}
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
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
				listUser = threeOrderBaseService.queryUserAddressByMobile(orderUser);
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
			 List<OrderUser> listUser = threeOrderBaseService.queryUserMapper(orderUser,page);
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
			if(orderUser.getId()==0 && (null==orderUser.getOrderId() || orderUser.getOrderId()==0)){
				orderUser.setId(null);
				this.threeOrderBaseService.insertUserAddress(orderUser);
			}else{
				this.threeOrderBaseService.updateUserAddress(orderUser);
			}
			OrderUser oUser = new OrderUser();
			List<OrderUser> olList = new ArrayList<OrderUser>();
			olList.add(this.threeOrderBaseService.queryUserAddressMapper(oUser).get(0));
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
			olList = this.threeOrderBaseService.queryUserAddressMapper(oUser);
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
			List<OrderBaseModel> list = this.threeOrderBaseService.queryBaseCity(code,length);
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
		try{
			if(id!=null && id>0L){
				list = this.threeOrderBaseService.queryOrderBasicServer(id);
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
	
	
	
	
	
	
}
