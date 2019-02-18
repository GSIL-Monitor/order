package com.emotte.order.order.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.alibaba.fastjson.JSON;
import com.emotte.dubbo.emottepay.EmottePayService;
import com.emotte.eserver.core.helper.PropertiesHelper;
import com.emotte.kernel.file.rw.IFileRWService;
import com.emotte.kernel.file.rw.impl.excel.ExcelServiceImpl;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.authmaner.service.ManagerService;
//import com.emotte.order.order.file.rw.IFileRWService;
//import com.emotte.order.order.file.rw.impl.excel.ExcelServiceImpl;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.ThreeOrderInService;
import com.emotte.order.order.service.ThreeOrderOutService;
import com.emotte.order.order.service.chain.BalanceEngine;
import com.emotte.order.order.service.chain.model.ChainModel;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.ExcelUtil;
import com.emotte.order.util.ExportExcelUtil;
import com.emotte.order.util.LoginCookieInfo;
import com.emotte.server.util.CookieUtils;
import com.swetake.util.Qrcode;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Resource
	private OrderService orderService;
	@Resource
	private ReOrderMapper reOrderMapper;
	@Resource
	private BalanceEngine balance;
	
	@Resource
	private ThreeOrderOutService threeOrderOutService;
	
	@Resource
	private ThreeOrderInService threeOrderInService;
	
	@Resource
	private ManagerService authManagerService;
	
	@Resource
	private EmottePayService emottePayService;

	@RequestMapping(value = "/loadOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadOrder(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Order order = this.orderService.loadOrder(id);
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
	
	@RequestMapping(value = "/loadOrderByOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void loadOrderByOrder(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try {
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			Order orderRe = this.orderService.loadOrderByOrder(order);
			if (orderRe == null) {
				msg = BaseConstants.NULL;
			} else {
				msg = BaseConstants.SCUUESS;
			}
			jsonObject.accumulate("order", orderRe);
		} catch (Exception e) {
			log.error("loadOrderByOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询订单列表
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrder(HttpServletRequest request, HttpServletResponse response, Order order, Page page)
			throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
		try {
			if(lk.getId() == 10944){
				//2018年08月,对特定账号(10944,hjzx-tuihui,呼叫中心退回)放开订单数据查询权限,可以查询到所有订单数据
				//该需求是业务管理部王延芝授权给呼叫中心作为跟踪回访进度使用。
				order.setLoginLevel(99);
			}else{
				if(order.getLoginLevel() == null){
					order.setLoginLevel(lk.getPermissionLevel());
				}
			}
			order.setLoginBy(lk.getId());
			order.setLoginDept(lk.getDeptId());
			order.setLoginOrgCode(lk.getOrgCode());
			order.setOrderCode(order.getOrderCode()==null?"":order.getOrderCode().trim());
			order.setUserName(order.getUserName()==null?"":order.getUserName().trim());
			order.setUserMobile(order.getUserMobile()==null?"":order.getUserMobile().trim());
			list = this.orderService.queryOrder(order, page);
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

	@RequestMapping(value = "/insertOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertOrder(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// JSONObject obj = CookieUtils.getJSON(request);
		// System.out.println("CookieUtils:" +obj.get("cityCode")
		// +obj.get("userName") +obj.get("realName"));
		try {
			order.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
			this.orderService.insertOrder(order);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("insertOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateOrder(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			order.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.orderService.updateOrder(order);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 查询单条商品订单的信息
	 */
	@RequestMapping(value = "/queryOrderBasicItem", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderBasicItem(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			if (id != null && id > 0L) {
				list = this.orderService.queryOrderBasicItem(id);
				msg = BaseConstants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("queryproductClassify:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 测试订单编号生成专用方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 * 
	 * @RequestMapping(value = "/createOrderTest",
	 *                       method={RequestMethod.POST,RequestMethod.GET})
	 *                       public void createOrderTest(HttpServletRequest
	 *                       request,HttpServletResponse response)throws
	 *                       Exception{ JSONObject jsonObject=new JSONObject();
	 *                       try { String num =
	 *                       this.orderService.createOrderTest();
	 *                       jsonObject.accumulate("num", num);
	 *                       msg=BaseConstants.SCUUESS; } catch (Exception e) {
	 *                       log.error("createOrderTest:"+e);
	 *                       msg=BaseConstants.FAIL; }
	 *                       jsonObject.accumulate("msg", msg);
	 *                       responseSendMsg(response, jsonObject.toString()); }
	 * 
	 */

	/**
	 * 考勤管理-服务人员服务费填报 查询列表操作
	 * 
	 * @author 张顺
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAttendanceOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAttendanceOrder(HttpServletRequest request, HttpServletResponse response, Order order, Page page)
			throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		Long authManagerId = Long.valueOf(String.valueOf(CookieUtils.getJSONKey(request,"id")));
		try {
			order.setAuthManagerId(authManagerId);
			list = this.orderService.queryAttendanceOrder(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAttendanceOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询 t_auth_org
	 */
	@RequestMapping(value = "/queryFollowDept", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryFollowDept(HttpServletRequest request,
			HttpServletResponse response, String data) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderService.queryFollowDept(data);
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 查询 t_auth_manager
	 */
	@RequestMapping(value = "/queryFollowBy", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryFollowBy(HttpServletRequest request,
			HttpServletResponse response, Dictionary dictionary) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try{
			StringBuffer strDict = this.orderService.queryFollowBy(dictionary);
			System.out.println("strDict:    " +strDict.toString());
			jsonObject.accumulate("dict",strDict.toString());
			sendMsg(response, jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 处理订单服务
	 */
	@RequestMapping(value = "/updateOrderSalary", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateOrderSalary(HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		JSONObject jsonObject=new JSONObject();
		try{
			System.out.println("自营单次服务人员服务人员服务费预算定时任务开始>>>>");
			List<Order> orderList = this.reOrderMapper.queryOrderNotInItem();
			System.out.println("未处理单次服务已完成订单：["+(orderList==null ? 0:orderList.size())+"] 个。");
			if (orderList != null && !orderList.isEmpty()) { 
				this.orderService.insertOrderSlice(orderList);
				this.orderService.updateOrderSalary(orderList);
			}
			System.out.println("服务人员服务人员服务费预算已全部生成！");
			System.out.println("<<<<<<<自营单次服务人员服务人员服务费预算定时任务结束！");
			
			//this.orderService.updateOrderSalary();
			System.out.println("验证方法是否成功 ");
			msg = BaseConstants.SCUUESS;
		}catch(Exception e){
			
			
			log.error("queryAttendanceOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		
		jsonObject.accumulate("msg", msg);
	}
    /**
     * 查询系统时间
     */
	@RequestMapping(value = "/querySysdate", method = { RequestMethod.POST, RequestMethod.GET })
	public void querySysdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject jsonObject=new JSONObject();
		try {
			String systemDate = this.orderService.querySysdate();
			msg = BaseConstants.SCUUESS;
			jsonObject.accumulate("systemDate", systemDate);
		} catch (Exception e) {
			log.error("querySysdate:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/*查询服务人员*/
	@RequestMapping(value = "/queryPerson", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryPerson(HttpServletRequest request, HttpServletResponse response, Order order, Page page) {
		List<Map<String, Object>> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderService.Person(order, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryPerson:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());

	}
	/*导出订单列表*/
	@RequestMapping(value = "/exportExcel", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Order order, Page page) {
		
		JSONObject jsonObject = new JSONObject();
		Long a = System.currentTimeMillis();
		try {
			order.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			if(order.getLoginLevel()==null){
				order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			}
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			request.setCharacterEncoding("utf-8");
			
			List<Order> list  = this.reOrderMapper.exoutOrder(order);
			Workbook excel = this.orderService.queryExcel(request, response, order, list, order.getLoginBy());
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
	
	/**
	 * 查询渠道订单列表
	 * @author 张顺
	 * @param request
	 * @param response
	 * @param order
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChannelOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryChannelOrder(HttpServletRequest request, HttpServletResponse response, Order order, Page page)
			throws Exception {
		List<Order> list = new ArrayList<Order>();
		Integer channelManagerType = 3;
		JSONObject jsonObject = new JSONObject();
		try {
			Long loginBy = CookieReaderUtil.cookieReader(request).getId();
			channelManagerType = this.orderService.queryChannelManagerType(loginBy);
			order.setLoginBy(loginBy);
			order.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
			order.setLoginOrgCode(CookieReaderUtil.cookieReader(request).getOrgCode());
			order.setLoginLevel(CookieReaderUtil.cookieReader(request).getPermissionLevel());
			order.setOrderCode(order.getOrderCode()==null?"":order.getOrderCode().trim());
			order.setUserName(order.getUserName()==null?"":order.getUserName().trim());
			order.setUserMobile(order.getUserMobile()==null?"":order.getUserMobile().trim());
			if(channelManagerType == 1){
				//合作方渠道经理
				order.setPartnerManagerId(loginBy);
				list = this.orderService.queryChannelOrder(order, page);
			}else if(channelManagerType == 2){
				//易盟内部渠道经理
				order.setManagerId(loginBy);
				list = this.orderService.queryChannelOrder(order, page);
			}else{
				//不是渠道经理
				order.setPartnerManagerId(null);
				order.setManagerId(null);
			}
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryChannelOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		jsonObject.accumulate("channelManagerType", channelManagerType);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 查询渠道经理类型
	 * 1合作方2易盟内部
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChannelManagerType", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryChannelManagerType(HttpServletRequest request, HttpServletResponse response, Order order)
			throws Exception {
		Integer channelManagerType = null;
		JSONObject jsonObject = new JSONObject();
		try {
			Long loginBy = CookieReaderUtil.cookieReader(request).getId();
			channelManagerType = this.orderService.queryChannelManagerType(loginBy);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryChannelManagerType:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("channelManagerType", channelManagerType);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 通过链接生成二维码图片，并且把这个图片返回到页面
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@UserAnnotation(methodName="通过链接生成二维码")
	@RequestMapping(value="/jl/",method={RequestMethod.POST,RequestMethod.GET})
	public void gopay(HttpServletResponse response,HttpServletRequest request)throws Exception{
		
		String code_url =request.getParameter("orderId");
		LogHelper.info(getClass(),"code_url---********"+code_url);
		System.setProperty("java.awt.headless","true");
		//生成短连接二维码
		int width = 6;
		Qrcode testQrcode =new Qrcode();
		testQrcode.setQrcodeErrorCorrect('M');
		testQrcode.setQrcodeEncodeMode('B');
		testQrcode.setQrcodeVersion(7);
		byte[] d = code_url.getBytes("UTF-8");
		BufferedImage image = new BufferedImage(width*45, width * 45, BufferedImage.TYPE_INT_RGB);
	
		Graphics2D g = image.createGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, 360, 360);
		g.setColor(Color.BLACK);
		
		
		if (d.length>0){
			boolean[][] s = testQrcode.calQrcode(d);
			for (int i=0;i<s.length;i++){
				for (int j=0;j<s.length;j++){
					if (s[j][i]) {
						g.fillRect(j*width,i*width,width,width);
					}
				}
			}
		}
		  g.dispose();
		  image.flush();
		  ImageIO.write(image, "jpg", response.getOutputStream());
	}
	
	/**
	 * 查询当前订单服务人员下一订单负责人
	 * @date 2017年08月22日
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryRechargeBy", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryRechargeBy(HttpServletRequest request, HttpServletResponse response, ItemInterview itemInterview)throws Exception {
		List<ItemInterview> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderService.queryRechargeBy(itemInterview);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryLabel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询当前订单服务人员是否与下一个订单冲突
	 * @date 2017年08月22日
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryByConflict", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryByConflict(HttpServletRequest request, HttpServletResponse response, ItemInterview itemInterview)throws Exception {
		List<ItemInterview> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderService.queryByConflict(itemInterview);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryLabel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询符合转单条件的订单
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryChangeOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryChangeOrder(HttpServletRequest request, HttpServletResponse response, Order order)throws Exception {
		List<Order> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.orderService.queryChangeOrder(order);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryChangeOrder:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 订单管理
	 * 导入单次服务
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
			String[] parm = {"orderCode","productName", "userName", "userMobile", "receiverProvince"
					, "receiverCity", "receiverArea","receiverAddress", "orderChannelText","rechargeByName"
					,"startTime","endTime", "productCode", "quantity","personNumber"
					,"remark" };
			
			String[] exportParm = 
			{"三方订单号#orderCode","商品名称#productName","下单人姓名#userName","下单人电话#userMobile", "服务省份#receiverProvince"
			,"服务城市#receiverCity", "服务区县#receiverArea", "客户地址#receiverAddress","订单渠道#orderChannelText","订单负责人#rechargeByName"
			,"服务开始时间#startTime", "服务结束时间#endTime","城市商品编码#productCode","商品数量#quantity", "服务人数号#personNumber"
			,"备注#remark","结果#success","错误信息#errormsg"};
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
				CookieReaderUtil.cookieReader(request).getDeptId();
				Long createBy = Long.parseLong(CookieUtils.getJSONKey(request, "id").toString());
				Long createDept = Long.parseLong(CookieUtils.getJSONKey(request, "deptId").toString());
				//获取数据库中orderCode、orderStatus  list
				List<String> orderCodes = threeOrderOutService.queryThreeOrderCodeList();
				List<Map<String, Object>> dictionary = threeOrderInService.queryOrderChannel(); 
				List<Map<String, Object>>  authManager= authManagerService.loadAllAuthManager();
				
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
								list.get(i).put("errormsg", "订单为" + orderCodeTmp + "与其他行的三方订单编号重复");
								list.get(j).put("errormsg", "订单为" + orderCodeTmp + "与其他行的三方订单编号重复");
							}
						}
					}
					
					for(Map<String,String> map : list){
						try {
							//循环校验excel数据，并在数据库中更新相应状态
							orderService.insertImportOrder(map,createBy,createDept,dictionary,authManager);
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
					threeOrderOutService.savaOrderImportRecord(url,createBy,fileName);
				}
				responseSendMsg(response, jsonObject.toString());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/queryDeptName", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryDeptName(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		Map<String, String> mapDb = null;
		Map<String,String> map = new HashMap<>();
		JSONObject cookies = CookieUtils.getJSON(request);
		String userId = cookies.getString("id");
		map.put("userId", userId);
		try {
			mapDb = threeOrderOutService.queryDeptName(map);
			if (mapDb != null && mapDb.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("data", mapDb);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @author weixd 20180731
	 * @deprecated 这个功能有待优化,着急上线,先实现功能上线。以后会把读取excel内容和excel必填项验证抽取出来,用责任链模式,这个方法只做业务处理
	 * 订单管理
	 * 导入单次服务
	 * @param uploadFile
	 * @param response
	 * @param request
	 */
	@Deprecated
	@RequestMapping(value = "/excelToListNew", method = { RequestMethod.POST, RequestMethod.GET })
	public void excelToListNew(@RequestParam(value="file",required = true) MultipartFile uploadFile, 
			HttpServletResponse response,MultipartHttpServletRequest request) {
		String[] exportParm = 
			{"三方订单号#orderCode","客户手机号#userMobile","客户姓名#userName","区县#receiverArea", "区县code#receiverAreaCode"
			,"详细地址#receiverAddress", "订单渠道#orderChannelText", "订单渠道code#orderChannel","商品名#productName","商品名code#productCode"
			,"开始时间(选填)#startTime", "结束时间(选填)#endTime","总数量(选填)#quantity","服务人数(选填)#personNumber"
			,"备注#remark","录入人erp账号#createByText","负责人erp账号#rechargeByText","结果#success","错误信息#errormsg"};
		String templetName = "orderTemplet";
		JSONObject cookies = CookieUtils.getJSON(request);
		String fileName = uploadFile.getOriginalFilename();
		String userId = null;
		String userName = null;
		List<Map<String, String>> list = null;
		Map<String, String> mapper = new HashMap<String, String>();
		try {
			userId = cookies.getString("id");
			userName = cookies.getString("realName");
			if(StringUtils.isNotBlank(userId)) {
				ChainModel model = new ChainModel();
				model.setChannelName(templetName);
				model.setFile(uploadFile);
				try {
					MultipartFile file = model.getFile();
					String json = PropertiesHelper.getValue("props/importTemplet.properties", "orderTemplet");
					mapper = JSON.parseObject(json, mapper.getClass());
//					IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> excelService = new ExcelServiceImpl();
					IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> excelService = new ExcelServiceImpl();
					list = excelService.readContent(file.getInputStream(), file.getOriginalFilename(), 0, 1, "#", mapper);
//					list = balance.doBalance(model);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			JSONObject jsonObject = new JSONObject();
			log.info("-------正在导入数据-------");
			CookieReaderUtil.cookieReader(request).getDeptId();
			Long createBy = Long.parseLong(CookieUtils.getJSONKey(request, "id").toString());
			Long createDept = Long.parseLong(CookieUtils.getJSONKey(request, "deptId").toString());
			//获取数据库中orderCode、orderStatus  list
			List<String> orderCodes = threeOrderOutService.queryThreeOrderCodeList();
			List<Map<String, Object>> dictionary = threeOrderInService.queryOrderChannel(); 
			List<Map<String, Object>>  authManager= authManagerService.loadAllAuthManager();
			List<Map<String, Object>> proviceCityAreaMap = threeOrderOutService.loadBaseCity();
			if(CollectionUtils.isNotEmpty(list)) {
				for(Map<String,String> map : list){
					try {
						//循环校验excel数据，并在数据库中更新相应状态
						Map<String, String> map2 = orderService.insertImportOrderNew(map,createBy,createDept,dictionary,authManager,proviceCityAreaMap);
						if(StringUtils.isNotBlank(map2.get("errormsg"))) {
							jsonObject.accumulate("success", false);
							jsonObject.accumulate("errormsg", map2.get("errormsg"));
						}else {
							jsonObject.accumulate("success", true);
							jsonObject.accumulate("errormsg", "导入完成");
						}
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
				threeOrderOutService.savaOrderImportRecord(url,createBy,fileName);
			}
			responseSendMsg(response, jsonObject.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	

	
	/**
	 * 下载服务人员档案卡
	 * @param request
	 * @param response
	 * @param empId
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadEmpFileCard", method = { RequestMethod.POST, RequestMethod.GET })
	public void downloadEmpFileCard(HttpServletRequest request, HttpServletResponse response,Long empId)throws Exception {
		JSONObject jsonObject = new JSONObject();
		String path = request.getSession().getServletContext().getRealPath("/");
		String returnUrl = "";
		try {
			returnUrl = this.orderService.downloadEmpFileCard(empId,path);
			if (returnUrl != null && !"".equals(returnUrl)) {
			    msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("downloadEmpFileCard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("returnUrl", returnUrl);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 下载文件
	 * 
	 * @param response
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "下载文件")
	@RequestMapping(value = "/downloadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String pathname) {
		try {
			File f = new File(pathname);
			//设置响应参数
			FileInputStream inStream = new FileInputStream(f);
			response.setCharacterEncoding("utf-8");
			response.addHeader("Content-Disposition", "abcd;filename=" +f.getName());
			response.setContentType("application/x-download");
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[2048];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			inStream.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			log.error("downloadFile:" + e);
		}
	}
	/**
	 * 切换发服务人员服务费方式
	 * @param request
	 * @param response
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSalaryStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateSalaryStatus(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			order.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.orderService.updateSalaryStatus(order);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateSalaryStatus:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@UserAnnotation(methodName="通过链接生成联动二维码")
	@RequestMapping(value="/ld",method={RequestMethod.POST,RequestMethod.GET})
	public void goLdpay(HttpServletResponse response,HttpServletRequest request)throws Exception{
		String code=null; 
		 String msg=null; 
		 String url=null; 
		JSONObject jsonResult=new JSONObject();
		String payFeeId =request.getParameter("orderPayFeeId");
		String payMode =request.getParameter("payMode");
		LogHelper.info(getClass(),"payFeeId---payMode********"+payFeeId+payMode);
		try {
			String json="{'payFeeId':'"+payFeeId+"','payMode':'"+payMode+"'}";
			String result=emottePayService.lianDongZhuSaoPay(json);
			 if(null != result){
				 JSONObject jsonObject=JSONObject.fromObject(result);
				   code=(String) jsonObject.get("code");
				   if(code.equals("0")){
					   msg=(String) jsonObject.get("msg");
					   url=(String) jsonObject.get("url");
				   }else{
					   msg=(String) jsonObject.get("msg");
				   }
			 }else{
				 code = Constants.FAIL;
			 }
		} catch (Exception e) {
			LogHelper.error(getClass(),"payFeeId---payMode********"+payFeeId+payMode+"异常信息"+e);
			code = BaseConstants.FAIL;
		}
		jsonResult.accumulate("code", code);
		jsonResult.accumulate("url", url);
		jsonResult.accumulate("msg", msg);
		responseSendMsg(response, jsonResult.toString());
	}
	/**
	 * 点击完成的时候，根据是否缴费，进行判断是否缴费
	 * 操作人：周鑫
	 * 2018年11月7日上午9:15:33
	 */
	@RequestMapping(value = "/updateOrderPay", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateOrderPay(HttpServletRequest request, HttpServletResponse response, Order order) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			order.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			/** @author 周鑫    2018-10-31  验证是否有缴费信息，无缴费信息不能完成 **/
			if (order!=null&&order.getOrderStatus()==9) {
				if (null!=order) {
					//这里是验证,缴费金额是否大于订单金额
					boolean flag = orderService.queryPayCompareFeeSum(order.getId());
					if (flag) {
						//大于。正常执行
						this.orderService.updateOrder(order);
						msg = BaseConstants.SCUUESS;
					}else{
						//不进行修改返回相关信息
						msg = "001";
					}
				}
			}else{
				this.orderService.updateOrder(order);
				msg = BaseConstants.FAIL;
			}
		} catch (Exception e) {
			log.error("updateOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	/**
	 * 查询操作：
	 * 		查询订单的有效合同（T.Agreement_state IN(2,3) 是否与当前时间在30天之内
	 * @param   orderid  订单ID
	 * 操作人：周鑫
	 * 2018年11月14日下午2:34:41
	 */
	@RequestMapping(value = "/queryAgreenmentDate", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAgreenmentDate(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			boolean flag =this.orderService.queryAgreenmentDate(orderId);
			if (flag) {
				msg = BaseConstants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAgreenmentDate:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	@RequestMapping(value = "/queryOrderEcho", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrder(HttpServletRequest request, HttpServletResponse response, Long  orderId, Page page)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		log.info(getClass()+"   参数:"+orderId);
		Map<String,Object> map=new HashMap<>();
		try {
			map=orderService.queryOrderEcho(orderId);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("queryOrder:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("map", map);
		responseSendMsg(response, jsonObject.toString());
	}
}
