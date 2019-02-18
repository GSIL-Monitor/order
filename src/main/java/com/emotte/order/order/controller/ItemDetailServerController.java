package com.emotte.order.order.controller;

import com.beust.jcommander.internal.Lists;
import com.emotte.dubbo.custom.addSubcontractLogService;
import com.emotte.dubbo.order.PersonOrderService;
import com.emotte.dubbo.service.PersonnelInterfaceService;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.model.*;
import com.emotte.order.order.service.*;
import com.emotte.order.util.Constants;
import com.emotte.order.util.JSONObjectDeleteEmpty;
import com.emotte.order.util.Tools;
import com.emotte.server.util.CookieUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/itemDetailServer")
public class ItemDetailServerController extends BaseController {

	@Resource
	private ItemDetailServerService itemDetailServerService;
	@Resource
	private OrderService orderService;
	@Resource
	private ItemService itemService ;
	@Resource
	private OrderBaseService orderBaseService ;
	@Resource
	private PersonnelInterfaceService personnelInterfaceService;
	@Resource
	private addSubcontractLogService subcontractLogService;
	@Resource
	private PersonOrderService personOrderService;
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private PersonnelInterfaceService personnelDubboService;
	@Resource
	private ItemInterviewService itemInterviewService;
	
	

	@RequestMapping(value = "/loadItemDetailServer", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadItemDetailServer(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			ItemDetailServer itemDetailServer = this.itemDetailServerService.loadItemDetailServer(id);
			if (itemDetailServer == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			request.setAttribute("itemDetailServer", itemDetailServer);
		} catch (Exception e) {
			log.error("loadItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("order/load_itemDetailServer");
		return mv;
	}

	@RequestMapping(value = "/queryItemDetailServer", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryItemDetailServer(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer, Page page) throws Exception {
		List<ItemDetailServer> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemDetailServerService.queryItemDetailServer(itemDetailServer, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/hours", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryItemDetailServerHours(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer) throws Exception {
		List<ItemDetailServer> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			//list = this.itemDetailServerService.queryItemDetailServerHours(itemDetailServer, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertItemDetailServer", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertItemDetailServer(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer,String cityCode,String categoryCode,String ifKunCun ) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			//单次服务减少库存
			/*if(itemDetailServer.getCateType()==1&&ifKunCun!=""&&!(ifKunCun.equals(""))){
				orderService.reduceInventory(cityCode, categoryCode, itemDetailServer.getStartTime().substring(0,10), itemDetailServer.getTimeSlot().split("-")[0], itemDetailServer.getTimeSlot().split("-")[1],itemDetailServer.getPersonNumber());
			}*/
			itemDetailServer.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			itemDetailServer.setDeptId(CookieUtils.getJSON(request).getLong("deptId"));
			// 判断如果是门店的人登录的，调用保存分包日志接口
			int orgType = CookieUtils.getJSON(request).getInt("orgType");
			// 取用户信息
			OrderUser user = new OrderUser();
			if(orgType==5||orgType==7){
				user.setUsValid(1);
				user.setAdValid(1);
				user.setId(itemDetailServer.getReceiverId());
				user.setUserId(itemDetailServer.getUserId());
				user = this.reOrderBaseMapper.querLoadUser(user);
			}
			// 保存服务类订单
			this.itemDetailServerService.insertItemDetailServer(itemDetailServer);
			// 服务人员服务费分成
			// this.itemDetailServerService.insertServerOneSlice(itemDetailServer.getOrderId(),itemDetailServer.getCreateBy());
			// 确定是否需要调用保存分包日志接口
			/*if((orgType==5||orgType==7)&&user.getMarketBy()==null){
				this.subcontractLogService.addSubcontractLog(itemDetailServer.getCreateBy(), itemDetailServer.getUserId());
			}*/
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("insertItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateItemDetailServer", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateItemDetailServer(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer,String cityCode,String cateCode,String oldTime,String oldTimeSolt,String onesKu) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			itemDetailServer.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemDetailServerService.updateItemDetailServer(itemDetailServer);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/updateItemDetailServerType", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateItemDetailServerType(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			itemDetailServer.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemDetailServerService.updateItemDetailServerType(itemDetailServer);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateItemDetailServer:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @author t
	 * @param request
	 * @param response
	 * @param module
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryOrderNeedServer", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryOrderNeedServer(HttpServletRequest request, HttpServletResponse response,
			OrderBaseModel orderBaseModel,Page page){
		JSONObject jsonObject=new JSONObject();
		try{
			List<OrderBaseModel> list = this.orderBaseService.queryOrderNeedServer(orderBaseModel,page);
			OrderBaseModel orderBaseModel2 = this.reOrderBaseMapper.queryOrdertypeLevelWorkTypeId(orderBaseModel.getOrderId());
			if (list!=null&&list.size() > 0) {
				jsonObject.accumulate("list", list);
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
			jsonObject.accumulate("orderType", orderBaseModel2.getOrderType());
			jsonObject.accumulate("grade", orderBaseModel2.getGrade());
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("page", page);
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
			log.info(e.getMessage());
			jsonObject.accumulate("msg", "02");
			sendMsg(response, jsonObject.toString());
		}
	}
	
	/**
	 * *****************************************   排期功能   **********************
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/loadOrderServerLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void loadOrderServerLined(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			ItemDetailServer itemDetailServer = this.itemDetailServerService.loadOrderServerLined(id);
			if (itemDetailServer == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			jsonObject.accumulate("itemDetailServer", itemDetailServer);
		} catch (Exception e) {
			log.error("loadOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询时间
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/showTime", method = { RequestMethod.POST, RequestMethod.GET })
	public void showTime(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			ItemDetailServer itemDetailServer = this.itemDetailServerService.showTime(id);
			if (itemDetailServer == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			jsonObject.accumulate("itemDetailServer", itemDetailServer);
		} catch (Exception e) {
			log.error("loadOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/queryOrderServerLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderServerLined(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer) throws Exception {
		List<ItemDetailServer> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemDetailServerService.queryOrderServerLined(itemDetailServer);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertOrderServerLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertOrderServerLined(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer,String cityCode, String cateCode) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			itemDetailServer.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			/*if(cityCode!="1"&&!(cityCode.equals("1"))){
				orderService.reduceInventory(cityCode,  cateCode, itemDetailServer.getStartTime(), itemDetailServer.getHours().split("-")[0],
						itemDetailServer.getHours().split("-")[1],itemDetailServer.getPersonNumber());//减少库存
 			}*/
			this.itemDetailServerService.insertOrderServerLined(itemDetailServer);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("insertOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateOrderServerLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateOrderServerLined(HttpServletRequest request, HttpServletResponse response,
			ItemDetailServer itemDetailServer,String cityCode,String cateCode,String hoursTime,String time,String personNumber) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if(cityCode!="1"&&!(cityCode.equals("1"))){
				//减少库存
				orderService.reduceInventory(cityCode,  cateCode, itemDetailServer.getStartTime().substring(0,10), itemDetailServer.getHours().split("-")[0],
						itemDetailServer.getHours().split("-")[1],Integer.valueOf(personNumber),itemDetailServer.getOrderId());
				//增加库存
				orderService.increaseInventory(cityCode,  cateCode, time.substring(0,10), hoursTime.split("-")[0],
						hoursTime.split("-")[1],Integer.valueOf(personNumber),itemDetailServer.getOrderId());
			}
			if(itemDetailServer.getOrderFenfa() != null && itemDetailServer.getOrderFenfa()!=""&&!(itemDetailServer.getOrderFenfa().equals(""))){
				itemInterviewService.contradictionPai(itemDetailServer);//做服务人员排期操作
			}
			if(itemDetailServer.getHours() != null && !"".equals(itemDetailServer.getHours())){
				itemDetailServer.setHours(Tools.ChangeTime(itemDetailServer.getHours()));//钟点工时间段格式转换
			}
			itemDetailServer.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemDetailServerService.updateOrderServerLined(itemDetailServer);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询 t_base_work_type_level
	 * @param dictCode  数据字典上级编码
	 * @param dictLength 数据字典需要的编码长度
	 * @param dictLength 页面需要加载的下拉框ID
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryWorkTypeLevel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryWorkTypeLevel(HttpServletRequest request,
			HttpServletResponse response, Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.itemDetailServerService.queryWorkTypeLevel(dictionary);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询 t_base_work_type_all
	 * 工种类型
	 */
	@UserAnnotation(methodName = "")
	@RequestMapping(value = "/queryWorkTypeAll", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryWorkTypeAll(HttpServletRequest request,
			HttpServletResponse response, Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.itemDetailServerService.queryWorkTypeAll(dictionary);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/queryOrderKucun", method = { RequestMethod.POST, RequestMethod.GET })
	public void selLibTime(HttpServletRequest request, HttpServletResponse response,
			String cityCode ,String productCode ,String startTimeLinedOne ) throws Exception {
		JSONObject json = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		List<String> list = null;
		List<String> list1 = null;
		try {
			jsonObject.accumulate("cityCode", cityCode);
			jsonObject.accumulate("cateCode", productCode);
			jsonObject.accumulate("startDate",startTimeLinedOne.substring(0,10).replace("-", ""));
			System.err.println(jsonObject.toString());
			//调用查询库存的接口
			String Seltime=personOrderService.onceInventory(jsonObject.toString());
			System.err.println(Seltime);
			JSONObject jsonObject1 = JSONObject.fromObject(Seltime);
			JSONArray quantums = jsonObject1.getJSONArray("quantums");
			JSONArray hours = jsonObject1.getJSONArray("hours");
			list  = JSONArray.toList(quantums); 
			list1 = JSONArray.toList(hours); 
			System.err.println(jsonObject1);
			System.err.println(quantums);
		} catch (Exception e) {
			log.error("insertOrderServerLined:" + e);
		}
		json.accumulate("quantums", list);
		json.accumulate("hours", list1);
		responseSendMsg(response, json.toString());
	}
	@RequestMapping(value = "/queryOrderKucun1", method = { RequestMethod.POST, RequestMethod.GET })
	public void selLibTime1(HttpServletRequest request, HttpServletResponse response,
			String serviceHours ,String cityCode ,String productCode ,String startTimeLinedOne ) throws Exception {
		JSONObject json = new JSONObject();
		JSONObject jsonObject = new JSONObject();
		List<String> list = null;
		try {
			String type="";
			if("2小时".equals(serviceHours)){
				type="1";
			}else if("2.5小时".equals(serviceHours)){
				type="2";
			}else if("3小时".equals(serviceHours)){
				type="3";
			}else if("4小时".equals(serviceHours)){
				type="4";
			}else if("5小时".equals(serviceHours)){
				type="5";
			}else if("6小时".equals(serviceHours)){
				type="6";
			}
			jsonObject.accumulate("cityCode", cityCode);
			jsonObject.accumulate("cateCode", productCode);
			jsonObject.accumulate("startDate",startTimeLinedOne.substring(0,10).replace("-", ""));
			jsonObject.accumulate("type", type);
			//调用时间段查询的接口
			String Seltime=personOrderService.onceQuantums(jsonObject.toString());
			JSONObject jsonObject1 = JSONObject.fromObject(Seltime);
			JSONArray quantums = jsonObject1.getJSONArray("quantums");
			list  = JSONArray.toList(quantums); 
		} catch (Exception e) {
			log.error("insertOrderServerLined:" + e);
		}
		json.accumulate("quantums", list);
		responseSendMsg(response, json.toString());
	}
	/**
	 * 日排期
	 * @TODO TODO
	 * @author admin
	 * @createTime 2016年8月25日 上午10:13:27
	 * @param empId
	 * @param time
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/scheduleDay")
	public String queryScheduleByDay(
			@RequestParam("empId")String empId,
			@RequestParam("time")String time
			){
			JSONObject json = new JSONObject();
			String scheduleDay = personnelDubboService.queryScheduleByHour(Long.valueOf(empId), Long.valueOf(time));
			//String scheduleDay = personnelDubboService.queryScheduleByHour(400124944724372L, 20160801L);
		//json.accumulate("scheduleDay", scheduleDay);
		//responseSendMsg(response, json.toString());
		
		return JSONObjectDeleteEmpty.toJson(scheduleDay);
	}
	/**
	 * 查询服务人员所有排期
	 * @TODO TODO
	 */
	@RequestMapping(value = "/queryPersonSchedule", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPersonSchedule(HttpServletRequest request, HttpServletResponse response,ItemDetailServer itemDetailServer){
		JSONObject jsonObject = new JSONObject();
		List<ItemDetailServer> list = null ;
		try {
			list = this.itemDetailServerService.queryPersonSchedule(itemDetailServer);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrderServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询服务人员排期
	 * @Date 2017年08月23日
	 * @param Long id
	 * @return 
	 * @author YUNNA
	 */
	@RequestMapping(value = "/loadPersonServerLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void loadPersonServerLined(HttpServletRequest request, HttpServletResponse response, ItemDetailServer itemDetailServers)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ItemDetailServer> list = null ;
		try {
			list = this.itemDetailServerService.loadPersonServerLined(itemDetailServers);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("loadPersonServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 单独查询服务人员排期
	 * @param request
	 * @param response
	 * @param itemDetailServers
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryPersonLineTime", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPersonLineTime(HttpServletRequest request, HttpServletResponse response, ItemDetailServer itemDetailServers)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ItemDetailServer> list = null ;
		try {
			list = this.itemDetailServerService.queryPersonLineTime(itemDetailServers);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("loadPersonServerLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 修改订单排期时如果有就删除
	 * @param request
	 * @param response
	 * @param itemDetailServers
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteSchedule", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteSchedule(HttpServletRequest request, HttpServletResponse response, Long orId)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String str=this.itemDetailServerService.deleteSchedule(orId);
			if(str.equals("成功")){
				msg = BaseConstants.SCUUESS;
			}else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("deleteSchedule:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/matchingPersonnel", method = { RequestMethod.POST, RequestMethod.GET })
	public void matchingPersonnel(HttpServletRequest request, HttpServletResponse response, OrderBaseModel orderBaseModel)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		OrderBaseModel  orderBaseModel2= null ;
		try {
			orderBaseModel2 = this.itemDetailServerService.matchingPersonnel(orderBaseModel);
			if (null!=orderBaseModel2.getIsSchedule() && orderBaseModel2 != null ) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("matchingPersonnel:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("orderBaseModel",orderBaseModel2);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询订单工种
	 * @param request
	 * @param response
	 * @param orderBaseModel
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectOrderWorkType", method = { RequestMethod.POST, RequestMethod.GET })
	public void selectOrderWorkType(HttpServletRequest request, HttpServletResponse response, OrderBaseModel orderBaseModel)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		OrderBaseModel  orderBaseModel2= null ;
		try {
			orderBaseModel2	= this.itemDetailServerService.selectOrderWorkType(orderBaseModel);
			if (orderBaseModel2 != null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("matchingPersonnel:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("orderBaseModel",orderBaseModel2);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询服务人员等级
	 * @param request
	 * @param response
	 * @param orderBaseModel
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectPersonnelLevel", method = { RequestMethod.POST, RequestMethod.GET })
	public void selectPersonnelLevel(HttpServletRequest request, HttpServletResponse response, OrderBaseModel orderBaseModel)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		  List<PersonaLevel> list=null;
		try {
			list= this.itemDetailServerService.selectPersonnelLevel();
			if (list != null) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("matchingPersonnel:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("list",list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询籍贯
	 * @param request
	 * @param response
	 * @param baseCity
	 */
	@RequestMapping(value="/queryCitys",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCitys(HttpServletRequest request,HttpServletResponse response,BaseCity baseCity ){
		JSONObject jsonObject = new JSONObject();
		List<BaseCity> list = new ArrayList<BaseCity>();
		try {
			list = this.itemDetailServerService.queryCitys(baseCity);
			if(list !=null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCitys"+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 根据ids查询服务人员信息
	 *
	 * @param request
	 * @param response
	 * @param personnelIds
	 * @Author zhanghao
	 * @Date 20180927
     */
	@RequestMapping("/checkPersonnelText")
	public void checkPersonnelText(HttpServletRequest request,HttpServletResponse response,@RequestParam("personnelIds[]") List<Long> personnelIds){
		JSONObject jsonObject = new JSONObject();
		List<Personnel> personnels = Lists.newArrayList();
		try {
			personnels = orderBaseService.checkPersonnelText(personnelIds);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("checkPersonnelText方法查询失败,查询参数:"+personnelIds.toString()+",错误信息:{}"+ ExceptionUtils.getStackTrace(e));

		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("personnels",personnels);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 确定匹配时[延续性非月嫂]订单校验选择服务人员管理状态是否可用
	 *
	 * @param request
	 * @param response
	 * @param orderBaseModel
	 * @Author zhanghao
	 * @Date 20180928
     */
	@RequestMapping("/checkOrderTrue")
	public void checkOrderTrue(HttpServletRequest request,HttpServletResponse response,OrderBaseModel orderBaseModel){
		JSONObject jsonObject = new JSONObject();
		List<Personnel> personnels = Lists.newArrayList();
		String text = "";
		String msg = "00";
		try {
			personnels = orderBaseService.checkOrderTrue(orderBaseModel);
			for(Personnel personnel : personnels){
				if(personnel.getManageMentStatus() != 17){
					msg = Constants.FAIL;
					text = "服务人员:"+personnel.getName()+"不是可匹配状态!请联系产品管家调整!";
					break;
				}
			}
		} catch (Exception e) {
			msg = Constants.RET_FAILED;
			text = "程序错误!";
			log.error("checkOrderTrue方法查询失败,查询参数:"+orderBaseModel.getIds()+",错误信息:{}"+ ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("text",text);
		responseSendMsg(response,jsonObject.toString());
	}

	@RequestMapping("/queryShowMsg")
	public void queryShowMsg(HttpServletRequest request,HttpServletResponse response,PersonnelSchedule personnelSchedule){
		JSONObject jsonObject = new JSONObject();
		String msg = "00";
		try {
			Map map= orderBaseService.queryShowMsg(personnelSchedule);
			String name=(String) map.get("PERSONNAME");
			String orderCode=(String) map.get("ORDERCODE");
			String realName = (String) map.get("REALNAME");
			personnelSchedule.setOrderCode(orderCode);
			personnelSchedule.setName(name);
			personnelSchedule.setRealName(realName);
		} catch (Exception e) {
			msg = Constants.RET_FAILED;
			log.error("queryShowMsg方法查询失败,错误信息:{}"+ ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("personnelSchedule",personnelSchedule);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 推送页面查看服务人员信息卡片
	 *
	 * @param orderId
	 * @param personnelId
	 * @param response
     * @param request
	 * @Author zhanghao
	 * @Date 20181011
     */
	@RequestMapping("/personnelDetailCard/{orderId}/{personnelId}")
	public void personnelDetailCard(@PathVariable("orderId") Long orderId,@PathVariable("personnelId") Long personnelId, HttpServletResponse response,HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		Personnel personnels = new Personnel();
		try {
			personnels = orderBaseService.findPersonnelDetailCard(orderId,personnelId);
			if(personnels != null){
				msg = Constants.SCUUESS;
			}else{
				msg = Constants.NULL;
			}
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("查询服务人员信息卡片失败,错误信息:"+ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("personnels",personnels);
		responseSendMsg(response,jsonObject.toString());
	}

}
