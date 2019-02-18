package com.emotte.order.order.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.ThreeOrderIn;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.model.ThreeOrderItemDetailServer;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.order.service.ThreeOrderInService;
import com.emotte.order.util.ExcelUtil;
import com.emotte.order.util.ExportExcelUtil;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

/**
 * 第三方订单(给入订单)
 */
@Controller
@RequestMapping("/threeOrderIn/")
public class ThreeOrderInController extends BaseController {
	@Resource
	private OrderBaseService orderBaseService;

	@Resource
	private ThreeOrderInService threeOrderInService;

	@RequestMapping(value = "/loadOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadOrder(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Order order = this.threeOrderInService.loadOrder(id); 
			if (order == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			request.setAttribute("order", order);
		} catch (Exception e) {
			log.error("loadOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("order/load_order");
		return mv;
	}

	@RequestMapping(value = "/queryOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrder(HttpServletRequest request, HttpServletResponse response, Order order, Page page) throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.threeOrderInService.queryOrder(order, page);
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

	@RequestMapping(value = "excelToList", method = { RequestMethod.POST, RequestMethod.GET })
	public void excelToList(@RequestParam("excelFile") MultipartFile uploadFile, HttpServletResponse response,HttpServletRequest request) {
		ExcelUtil excel = new ExcelUtil();
		JSONObject jsonObject = new JSONObject();
		try {
			String[] parm = {  "cityName","orderCode","userName","userMobile","receiverName","receiverMobile", "cateType", "orderType","productName","productCode","receiverProvince", "receiverCity", "receiverArea", "receiverAddress", "startTime", "endTime", "createTime", "sevicerName", "postId", "totalPay",
					"orderStatus","orderChannelText","remark" };
			String[] faParm = { "cityName","orderCode","userName","userMobile","receiverName", "receiverMobile",  "receiverProvince", "receiverCity", "receiverArea", "receiverAddress","productNames","productCode","productNumbers", "productPrices", "totalPay","orderStatus","orderChannelText","postId","receiveTime",
					"createTime","remark" };
			String[] exportParm = {  "城市#cityName","订单编号#orderCode","下单人姓名#userName","下单人电话#userMobile","接收人姓名#receiverName","接收人电话#receiverMobile", "订单类型#cateType", "需求类型#orderType","服务项目#productName","服务编码#productCode","省份#receiverProvince", "城市#receiverCity", "地区#receiverArea", "详细地址#receiverAddress", "服务开始时间#startTime", "服务结束时间#endTime", "创建时间#createTime", "服务人员#sevicerName", "快递单号#postId", "订单金额#totalPay",
					"订单状态#orderStatus","订单来源#orderChannelText","备注#remark","结果#success","错误信息#errormsg" };
			String[] faExportParm = { "城市#cityName","订单编号#orderCode","下单人姓名#userName","下单人电话#userMobile","收货人姓名#receiverName", "收货人电话#receiverMobile",  "省份#receiverProvince", "城市#receiverCity", "地区#receiverArea", "详细地址#receiverAddress","商品名称#productNames","商品编码#productCode","商品数量#productNumbers", "商品单价#productPrices", "订单总金额#totalPay","订单状态#orderStatus","订单来源#orderChannelText","快递单号#postId","收货时间#receiveTime",
						"创建时间#createTime","备注#remark","结果#success","错误信息#errormsg" };
 			InputStream input = uploadFile.getInputStream();
			String fileName = uploadFile.getOriginalFilename();
			// 将excel转换成list数据
			Map<String,List<Map<String, String>>> resultMap =excel.excelToList(parm, faParm, input, fileName);
			List<Map<String, String>> list = resultMap.get("list"); //服务类订单
			List<Map<String, String>> faList = resultMap.get("faList");// fa订单
			if ((list == null || list.size() == 0) && (faList == null ||faList.size()==0)) {
				jsonObject.accumulate("success", false);
				jsonObject.accumulate("msg", "模版格式错误或excel中没有数据");
			} else {
				log.info("------正在导入数据------");
				jsonObject.accumulate("success", true);
				Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
				String Stringid = String.valueOf(Cookieid);
				Long createBy = Long.valueOf(Stringid);
				List<Map<String, Object>> dictionary = threeOrderInService.queryChannel(); // 订单渠道
				List<Map<String, Object>> addresses = threeOrderInService.queryBaseCity();	//城市信息
				List<Map<String, Object>> orderTypes = threeOrderInService.queryOrderType(); //得到三级分类
				List<String> threeOrderCodes = threeOrderInService.loadThreeOrderId();		//得到导入的订单的三方订单号，检验重复用
				if(!list.isEmpty()){	 //检验服务类订单是否和数据库重复
					for (int i = 0; i < list.size(); i++) {
						String threeOrderCode = list.get(i).get("orderCode");
						if(threeOrderCodes.contains(threeOrderCode)){
							list.get(i).put("isrepeat", "1");
							list.get(i).put("success", "失败");
							list.get(i).put("errormsg", "订单为"+threeOrderCode+"与数据库重复");
							continue;
						}
						for (int j = i + 1; j < list.size(); j++) {
							if(threeOrderCode.equals(list.get(j).get("orderCode"))){
								list.get(i).put("isrepeat", "1");
								list.get(j).put("isrepeat", "1");
								list.get(i).put("success", "失败");
								list.get(j).put("success", "失败");
								list.get(i).put("errormsg", "订单为"+threeOrderCode+"订单有重复");
								list.get(j).put("errormsg", "订单为"+threeOrderCode+"订单有重复");
							}
						}
					}	
					for (Map<String, String> map : list) {
						try{
							threeOrderInService.insertOrder(map,createBy,dictionary,addresses,orderTypes); //保存订单
							}catch(Exception e){
								e.printStackTrace();
								map.put("success", "失败");
								map.put("errormsg", "未知错误");
							}
					}
				}
				if(!faList.isEmpty()){  //检验fa类订单是否和数据库重复
					for (int i = 0; i < faList.size(); i++) {
						String threeOrderCode = faList.get(i).get("orderCode");
						if(threeOrderCodes.contains(threeOrderCode)){
							faList.get(i).put("isrepeat", "1");
							faList.get(i).put("success", "失败");
							faList.get(i).put("errormsg", "订单为"+threeOrderCode+"与数据库重复");
							continue;
						}
						for (int j = i + 1; j < faList.size(); j++) {
							if(threeOrderCode.equals(faList.get(j).get("orderCode"))){
								faList.get(i).put("isrepeat", "1");
								faList.get(j).put("isrepeat", "1");
								faList.get(i).put("success", "失败");
								faList.get(j).put("success", "失败");
								faList.get(i).put("errormsg", "订单为"+threeOrderCode+"订单有重复");
								faList.get(j).put("errormsg", "订单为"+threeOrderCode+"订单有重复");
							}
						}
					}
					
					for (Map<String, String> map : faList) {
						try{
							threeOrderInService.insertFaOrder(map,createBy,dictionary,addresses,orderTypes); //保存fa订单
						}catch(Exception e){
							e.printStackTrace();
							map.put("success", "失败");
							map.put("errormsg", "未知错误");
						}
					}
				}
					String url = ExportExcelUtil.exportList(response, fileName, exportParm, list, faExportParm, faList,request); // 将处理过的list集合 转成 excel 表格 并上传，返回上传后的url 地址
					if(url !=null){
						threeOrderInService.savaExcelRecord(url,createBy,fileName); // 保存 url地址
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value="exportToExcel")
	public void exportToExcel(HttpServletResponse response,Order order){
		String fileName = "三方订单给入导出";
		String[] excelHeader = { "城市#cityName","订单编号#orderCode","三方订单编号#threeOrderCode", "客户姓名#userName", "客户手机#userMobile","收货人姓名#receiverName", "收货人电话#receiverMobile", "订单类型#cateTypeName", "服务类型#serverText","服务项目#productName","服务编号#productCode", "省份#receiverProvince", "城市#receiverCity", "区域#receiverArea", "详细地址#receiverAddress", "服务开始时间#startTime", "服务结束时间#endTime", "创建时间#createTime", "服务人员#sevicerName", "快递单号#postId", "订单金额#totalPay", "订单状态#statusText","订单来源#orderChannelText","备注#remark" };
		String[] faExcelHeader = { "城市#cityName","订单编号#orderCode","三方订单编号#threeOrderCode","下单人姓名#userName","下单人电话#userMobile","收货人姓名#receiverName", "收货人电话#receiverMobile",  "省份#receiverProvince", "城市#receiverCity", "地区#receiverArea", "详细地址#receiverAddress", "商品名称#productName","商品编码#productCode","商品数量#quantity", "商品单价#productPrice", "订单总金额#totalPay","订单状态#statusText","订单来源#orderChannelText","快递单号#expressCode","收货时间#receiveTime",
				"创建时间#createTime","备注#remark"};
		List<ThreeOrderIn> orders = threeOrderInService.exportToExcel(order);
		List<ThreeOrderIn> faOrders = new ArrayList<ThreeOrderIn>();
		for (ThreeOrderIn order2 : orders) {
			if(order2.getCateType() == 3){
				faOrders.add(order2);
			}
		}
		orders.removeAll(faOrders);
		try {
			ExportExcelUtil.export(response, fileName, excelHeader, orders,faExcelHeader,faOrders); // 导出excel 表格
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 保存订单信息，
	 * 包括，订单、订单项、订单详情表 
	 */
	@RequestMapping(value = "saveThreeOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void saveThreeOrderServer(HttpServletRequest request, HttpServletResponse response,
			ThreeOrderItemDetailServer threeOrderItemDetailServer) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			System.out.println("-------------------"+Stringid);
			Long createBy = Long.valueOf(Stringid);
			threeOrderItemDetailServer.setCreateBy(createBy);
			this.threeOrderInService.saveOrderAndItem(threeOrderItemDetailServer);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 保存FA订单 
	 */
	@RequestMapping(value="saveThreeOrderFa", method = { RequestMethod.POST, RequestMethod.GET } )
	public void saveThreeOrderFa(HttpServletRequest request,
			HttpServletResponse response,ThreeOrderInModel order){
		JSONObject jsonObject = new JSONObject();
		try {
			Integer Cookieid =(Integer)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			Long createBy = Long.valueOf(Stringid);
			order.setCreateBy(createBy);
			order.setUpdateBy(createBy);
			
			if(order.getReceiptTime() == null || "".equals(order.getReceiptTime().trim())){
				order.setReceiptTime(null);
			}
			boolean isvaliddate = isValidDate(order.getReceiptTime(),true);
			if(isvaliddate){
				this.threeOrderInService.saveThreeOrderFaItem(order);
				msg = BaseConstants.SCUUESS;
			}else{
				msg = BaseConstants.FAIL;
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/queryOrderBasicItem", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderBasicItem(HttpServletRequest request, HttpServletResponse response,
			 Long id) throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if(id!=null && id>0L){
				list = this.threeOrderInService.queryOrderBasicItem(id);
				msg=BaseConstants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("queryproductClassify:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryThreeOrderRecord", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryThreeOrderRecord(HttpServletRequest request, HttpServletResponse response,
			 String recordStartTime,String recordEndTime,Page page) throws Exception {
		List<ThreeOrderIn> list = null;
		JSONObject jsonObject = new JSONObject();
		list = this.threeOrderInService.queryThreeOrderRecord(recordStartTime,recordEndTime,page);
		if(list != null && list.size() > 0){
			jsonObject.accumulate("msg", BaseConstants.SCUUESS);
			jsonObject.accumulate("list", list);
		}else{
			jsonObject.accumulate("msg", BaseConstants.NULL);
		}
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	public  boolean isValidDate(String str,boolean isAllowEmpty) {
       boolean convertSuccess=true;
       //指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
       if(isAllowEmpty){
    	   if(str == null){
    		   return true;
    	   }
       }
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       try {
    	   // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
    	   format.setLenient(false);
    	   format.parse(str);
	   } catch (ParseException e) {
		   e.printStackTrace();
		   // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
		   convertSuccess=false;
	   } 
       return convertSuccess;
	}
}
