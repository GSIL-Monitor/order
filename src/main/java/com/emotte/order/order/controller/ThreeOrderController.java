package com.emotte.order.order.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.ThreeOrderCategory;
import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
import com.emotte.order.order.model.ThreeOrderInProductModel;
import com.emotte.order.order.service.ThreeOrderOutService;
import com.emotte.order.order.service.ThreeOrderService;
import com.emotte.order.util.Tools;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/threeOrder/")
public class ThreeOrderController extends BaseController {
	@Resource
	private ThreeOrderService threeOrderService;
	@Resource
	private ThreeOrderOutService threeOrderOutService;
	
	/**
	 * 查询用户
	 * @param request
	 * @param response
	 * @param orderUser
	 * @param page
	 */
	@RequestMapping(value = "queryThreePartUserMapper", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryThreePartUserMapper(HttpServletRequest request,
			HttpServletResponse response, ThreeOrderCustomerModel orderCustomer,Page page){
		JSONObject jsonObject=new JSONObject();
		try{
			List<ThreeOrderCustomerModel> listUser = threeOrderService.getCustomMapper(orderCustomer,page);
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
	 * 保存客户
	 * @param request
	 * @param response
	 * @param pid
	 * @param types
	 */
	@RequestMapping(value="saveOrEditUserAddress",method={ RequestMethod.POST, RequestMethod.GET })
	public void saveOrEditUserAddress(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderCustomerModel orderUser){
		JSONObject jsonObject=new JSONObject();
		try{
			//字段验证
			if(orderUser==null){
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "参数错误");
				sendMsg(response, jsonObject.toString());
				return;
			}
			
			if(Tools.isEmpty(orderUser.getRealName())){
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "客户姓名不能为空");
				sendMsg(response, jsonObject.toString());
				return;
			}
			
			if(Tools.isEmpty(orderUser.getUserMobile())){
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "客户手机号码不能为空");
				sendMsg(response, jsonObject.toString());
				return;
			}
			
			if(Tools.isEmpty(orderUser.getUserProvince())){
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "客户地址省份不能为空");
				sendMsg(response, jsonObject.toString());
				return;
			}
			
			if(Tools.isEmpty(orderUser.getUserCity())){
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "客户地址城市不能为空");
				sendMsg(response, jsonObject.toString());
				return;
			}
			/**
			 * 根据手机号码查选此用户是否存在 
			 */
			ThreeOrderCustomerModel checkIsExist = new ThreeOrderCustomerModel();
			checkIsExist.setUserMobile(orderUser.getUserMobile());
			
			boolean isExist =this.threeOrderService.isExsitCustomerByMoblie(checkIsExist);
			if(!isExist){
				//手机号码不存在
				Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
				String Stringid = String.valueOf(Cookieid);
				Long createBy = Long.valueOf(Stringid);
				orderUser.setCreateBy(createBy);
				orderUser.setId(null);
				this.threeOrderService.saveOrderUser(orderUser);
				jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			}else{
				// 手机已经存在,不可以添加
				jsonObject.accumulate("msg", BaseConstants.FAIL);
				jsonObject.accumulate("errInfo", "手机号码已经被占用");
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonObject.accumulate("errInfo", "未知错误");
		}
		sendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 获得产品类别 
	 */
	@RequestMapping(value="queryProductCategory", method={ RequestMethod.POST , RequestMethod.GET})
	public void queryProductCateFory(HttpServletRequest request,
			HttpServletResponse response, ThreeOrderCategory p_Category){
		
		JSONObject jsonObject = new JSONObject();
		
		if(p_Category==null){
			jsonObject.accumulate("msg", BaseConstants.FAIL);
			jsonObject.accumulate("errInfo", "参数错误");
			sendMsg(response, jsonObject.toString());
			return;
		}
		
		if(Tools.isEmpty(p_Category.getCityCode())){
			jsonObject.accumulate("msg", BaseConstants.FAIL);
			jsonObject.accumulate("errInfo", "请选择城市");
			sendMsg(response, jsonObject.toString());
			return;
		}
		
		try{
			if(p_Category != null ){
				//获得产品分别类（借用三方给出）
				List<ThreeOrderCategory> list =this.threeOrderOutService.queryCategory(p_Category);
				if (list.size() > 0) {
					msg = BaseConstants.SCUUESS;
				} else {
					msg = BaseConstants.NULL;
				}
				jsonObject.accumulate("msg", msg);
				jsonObject.accumulate("list", list);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			jsonObject.accumulate("msg", BaseConstants.FAIL);
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	/**
	 * 获得客户收货地址
	 */
	@RequestMapping(value="getCustomerAddrList", method={ RequestMethod.POST , RequestMethod.GET})
	public void getCustomerAddrList(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderCustomerAddrModel orderCustomer){
		JSONObject jsonobject = new JSONObject();
		
		if(orderCustomer == null){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "参数错误");
			sendMsg(response, jsonobject.toString());
			return;
		}
		
		if(orderCustomer.getUserId()==null){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "请选择用户");
			sendMsg(response, jsonobject.toString());
			return;
		}
		
		if(Tools.isEmpty(orderCustomer.getCityCode())){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "请选择城市");
			sendMsg(response, jsonobject.toString());
			return;
		}
		try{
			List<ThreeOrderCustomerAddrModel> list = this.threeOrderService.getOrderThreeCustomerAddrList(orderCustomer);
			jsonobject.accumulate("msg", BaseConstants.SCUUESS);
			jsonobject.accumulate("list", list);
		}catch(Exception e){
			e.printStackTrace();
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "未知错误");
		}
		responseSend(response, jsonobject.toString());
	}
	/**
	 * 客户收货地址的添加，编辑 
	 */
	@RequestMapping(value="saveOrEidtCustomerAddr",method={RequestMethod.POST,RequestMethod.GET})
	public void saveOrEidtCustomerAddr(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderCustomerAddrModel orderCustomer ){
		
		JSONObject jsonobject = new JSONObject();
		try{
			Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			Long createBy = Long.valueOf(Stringid);
			
			if(orderCustomer.getId()!=null){
					//修改客户的收货地址
					orderCustomer.setUpdateBy(createBy);
					this.threeOrderService.editOrderThreeCustomerAddr(orderCustomer);
			}else{
				//添加客户的收货地址
				orderCustomer.setCreateBy(createBy);
				this.threeOrderService.saveOrderThreeCustomerAddr(orderCustomer);
			}
			jsonobject.accumulate("msg", BaseConstants.SCUUESS);
		}catch(Exception e){
			e.printStackTrace();
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "未知错误");
		}
		responseSend(response, jsonobject.toString());
	}
	
	/**
	 * 获得指定收货地址(服务地址信息)的详细信息，
	 * 编辑收货地址数据的代入
	 */
	@RequestMapping(value="getCustomerAddrByAddrId",method={RequestMethod.POST,RequestMethod.GET})
	public void getCustomerAddrByAddrId(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderCustomerAddrModel orderCustomer){
		JSONObject jsonobject = new JSONObject();
		try{
			if(orderCustomer !=null){
				if(orderCustomer.getId() != null){
					ThreeOrderCustomerAddrModel fa_addr = this.threeOrderService.getOrderThreeCustomerAddrById(orderCustomer);
					jsonobject.accumulate("list", fa_addr);
					jsonobject.accumulate("msg", BaseConstants.SCUUESS);
				}else{
					jsonobject.accumulate("msg", BaseConstants.FAIL);
				}
			}else{
				jsonobject.accumulate("msg", BaseConstants.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonobject.accumulate("msg", BaseConstants.FAIL);
		}
		responseSend(response, jsonobject.toString());
	}
	
	/**
	 * 根据三级目录获得产品 
	 */
	@RequestMapping(value="getThreeOrderProductByCategroy",method={RequestMethod.POST,RequestMethod.GET})
	public void getThreeOrderProductByCategroy(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderInProductModel threeOrderInProductModel){
		JSONObject jsonobject = new JSONObject();
		/**
		 * 参数验证 
		 */
		if(threeOrderInProductModel==null){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "参数不正确");
			responseSend(response, jsonobject.toString());
			return ;
		}
		
		/**
		 * 用户验证 
		 */
		if(threeOrderInProductModel.getUser_id() == null ){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "请选择用户");
			responseSend(response, jsonobject.toString());
			return;
		}
		
		/**
		 * cityCode验证 
		 */
		if(threeOrderInProductModel.getCity_code() == null 
				|| "".equals(threeOrderInProductModel.getCity_code())){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "您选择的用户没有城市信息，请确认后在操作");
			responseSend(response, jsonobject.toString());
			return;
		}
		
		/**
		 * 目录验证
		 */
		if(threeOrderInProductModel.getCategory_code()==null 
				|| "".equals(threeOrderInProductModel.getCategory_code())){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "请选择三级分类");
			responseSend(response, jsonobject.toString());
			return;
		}
		
		try{
			/**
			 * 获得选择三级分类中的商品信息 
			 */
			List<ThreeOrderInProductModel> list = this.threeOrderService.getThreeOrderInProductList(threeOrderInProductModel);
			
			if(list != null && list.size()>0){
				jsonobject.accumulate("msg", BaseConstants.SCUUESS);
				jsonobject.accumulate("list", list);
			}else{
				jsonobject.accumulate("msg", BaseConstants.FAIL);
				jsonobject.accumulate("errInfo", "您所在的城市没有相关商品");
			}
			responseSend(response, jsonobject.toString());
		}catch(Exception e){
			e.printStackTrace();
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "未知错误");
			responseSend(response, jsonobject.toString());
		}
	}
	/**
	 * 重新计算价格 
	 */
	@RequestMapping(value="reCalculateTOIProduct",method={RequestMethod.POST,RequestMethod.GET})
	public void reCalculateTOIProduct(HttpServletRequest request,
			HttpServletResponse response, ThreeOrderInProductModel threeOrderInProductModel){
		JSONObject jsonobject = new JSONObject();
		/**
		 * 参数验证 
		 */
		if(threeOrderInProductModel==null){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "参数不正确");
			responseSend(response, jsonobject.toString());
			return ;
		}
		
		/**
		 * 用户验证 
		 */
		if(threeOrderInProductModel.getUser_id() == null ){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "请选择用户");
			responseSend(response, jsonobject.toString());
			return;
		}
		
		/**
		 * cityCode验证 
		 */
		if(Tools.isEmpty(threeOrderInProductModel.getCity_code())){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "您选择的用户没有城市信息，请确认后在操作");
			responseSend(response, jsonobject.toString());
			return;
		}
		
		/**
		 * 商品验证 
		 */
		if(Tools.isEmpty(threeOrderInProductModel.getProduct_code_count_json())){
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "您选择的用户没有城市信息，请确认后在操作");
			responseSend(response, jsonobject.toString());
			return;
		}
		try{
			
			Map<String,BigDecimal> amount_map= new HashMap<String,BigDecimal>();
			//BigDecimal amount = new BigDecimal(0);
			List<ThreeOrderInProductModel> list = 
					this.threeOrderService.reCalculateThreeOrderInPrice(threeOrderInProductModel,amount_map);
			jsonobject.accumulate("msg", BaseConstants.SCUUESS);
			jsonobject.accumulate("list", list);
			jsonobject.accumulate("amount", amount_map.get("amount"));
			responseSend(response, jsonobject.toString());
		}catch (Exception e){
			e.printStackTrace();
			jsonobject.accumulate("msg", BaseConstants.FAIL);
			jsonobject.accumulate("errInfo", "未知错误");
			responseSend(response, jsonobject.toString());
		}
	}
}
