package com.emotte.order.order.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.kernel.redis.EJedisPool;
import com.emotte.order.order.model.RedisModel;
import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.service.ThreePartyOrderService;
import com.emotte.order.util.ExportExcelUtil;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/partyOrder")
public class ThreePartyOrderController extends BaseController{
	
	@Resource
	private ThreePartyOrderService threePartyOrderService;
	@Autowired
	EJedisPool eJedisPool;
	/*
	 * 查询三方订单
	 */
	@RequestMapping(value = "/queryPartyOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPartyOrder(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order, Page page) {
		List<ThreeOrder> list = null;

		JSONObject jsonObject = new JSONObject();
		try {
			list = this.threePartyOrderService.queryPartyOrder(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryPartyOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询单个三方订单信息
	 * @param request
	 * @param response
	 * @param id
	 */
	@RequestMapping(value = "/queryPartyOrderId", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryPartyOrderId(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		ThreeOrder orderObj = null;
		try {
			if (order != null) {
				List<ThreeOrder> list = threePartyOrderService.loadPartyOrderId(orderObj);
				if (list.size() > 0) {
					orderObj = list.get(0);
					msg = BaseConstants.SCUUESS;
				} else {
					msg = BaseConstants.NULL;
				}
			}
		} catch (Exception e) {
			log.error("queryPartyOrderId:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("order", orderObj);
		sendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询三方订单负责部门
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/queryPartyOrderDeptName", method = {RequestMethod.POST, RequestMethod.GET })
	public void queryPartyOrderDeptName(HttpServletRequest request, HttpServletResponse response, 
			ThreeOrder order) {
		JSONObject jsonObject = new JSONObject();
		List<ThreeOrder> list = null;
		try {
			list = threePartyOrderService.queryPartyOrderDeptName(order);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryPartyOrderDeptName:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", BaseConstants.SCUUESS);
		jsonObject.accumulate("list", list);
		sendMsg(response, jsonObject.toString());
	}
	/**
	 * 订单管理(给出)
	 * 导出订单
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/exportPartyOrderExcel")
	public void exportPartyOrderExcel(HttpServletRequest request, HttpServletResponse response,ThreeOrder order)
			throws IOException {
		JSONObject jsonObject = new JSONObject();
		//生成时间戳：年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date  =new Date();
		//生成0~999之间的随机数
		int num=(int)(Math.random()*999);
		//设置excel文件名：
		String fileName = "三方订单(对内)"+sdf.format(date) +"_"+ num;
		//excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
		String[] excelHeader = {"*城市#city","*订单编号#orderCode","*商品名称#productName","*下单人姓名#userName","*下单人电话#userMobile",
				"*接收人姓名#receiverName","*接收人电话#receiverMobile","*需求类型#typeText", 
				"*服务地址省份#receiverProvince","*服务地址城市#receiverCity","*服务地址地区#receiverArea","*客户地址#receiverAddress",
				"*服务开始时间#startTime","*服务结束时间#endTime","*创建时间#createTime","*服务人员#personName", 
				"*快递单号#postId","*订单金额#totalPay","*订单状态#orderStatus","备注#remark" };
		//查询所有商家订单列表,
	//  order.setOrderStatus("4");
		List<ThreeOrder> dataList = threePartyOrderService.queryPartyOrderList(order);
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
		responseSendMsg(response, jsonObject.toString());
	}
	/*导出订单列表*/
	@RequestMapping(value = "/exportExcelIN", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order, Page page) {
		
		JSONObject jsonObject = new JSONObject();
		Long a = System.currentTimeMillis();
		try {
			request.setCharacterEncoding("utf-8");
			
			List<ThreeOrder> dataList = threePartyOrderService.queryPartyOrderList(order);
			Workbook excel = this.threePartyOrderService.queryExcel(request, response, order, dataList, order.getLoginBy());
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
	@RequestMapping(value = "/queryRedis", method={RequestMethod.POST,RequestMethod.GET})	
	public void queryRedis(HttpServletRequest request,HttpServletResponse response,
			String mobile){
		JSONObject jsonObject = new JSONObject();
		Jedis jedis = null;
		//dist:log:1111111111111:1581445647 
		//dist:err:1111111111111:1581445647
		//dist:id:1111111111111:1581445647
		//dist:order:mobile:" + mobile
    	jedis  = eJedisPool.getResource();
    	String keys = "dist:*:*:";
    	List<RedisModel>  list = new ArrayList<>();
		try {
			//查询项为空
			if("".equals(mobile)){
				 msg = BaseConstants.NULL;
			}else{
				Set<String> logs = jedis.keys(keys+mobile);
				if(logs.size()>0){
					for (String string : logs) {
						RedisModel redisModel = new RedisModel();
						redisModel.setKey(string);
						redisModel.setValue(jedis.get(string));
						list.add(redisModel);
					}
					msg = BaseConstants.SCUUESS;
				}else{
					msg = BaseConstants.FAIL;
				}
			}
		} catch (Exception e) {
			 msg = BaseConstants.FAIL;
			log.error("queryRedis:" + e);
		}finally {
			if(jedis!=null){
				jedis.close();
			}
		}
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("msg", msg);
		sendMsg(response, jsonObject.toString());
	}
}