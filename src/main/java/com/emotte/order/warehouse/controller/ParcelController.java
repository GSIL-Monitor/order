package com.emotte.order.warehouse.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.emotte.order.util.Constants;
import com.emotte.order.warehouse.model.Parcel;
import com.emotte.order.warehouse.service.PackageItemRefService;
import com.emotte.order.warehouse.service.ParcelService;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/parcel")
public class ParcelController extends BaseController {

	@Resource
	private ParcelService parcelService;
	
	@Resource
	private PackageItemRefService packageItemRefService;

	@RequestMapping(value = "/loadParcel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadParcel(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Parcel parcel = this.parcelService.loadParcel(id);
			if (parcel == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("parcel", parcel);
		} catch (Exception e) {
			log.error("loadParcel:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_parcel");
		return mv;
	}
	/**
	 * 查询包裹
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryParcel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryParcel(HttpServletRequest request,
			HttpServletResponse response, Parcel parcel, Page page)
			throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		String orgocde = String.valueOf(CookieUtils.getJSONKey(request,"orgCode"));
		try {
			parcel.setCitycode(orgocde);
			parcel.setFlagPage(1);
			parcel.setPage(page);
			list = this.parcelService.queryParcel(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 发货查询详情
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryDeliveryReturn", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryDeliveryReturn(HttpServletRequest request,HttpServletResponse response, Parcel parcel, Page page)throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			parcel.setFlagPage(1);
			parcel.setPage(page);
			list = this.parcelService.queryDeliveryReturn(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 下单拆分包裹
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertOrderParcel", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String insertOrderParcel(Long orderId) throws Exception {
		String returnValue = "";
		try {
			returnValue = this.parcelService.insertOrderParcel(orderId);
		} catch (Exception e) {
			log.error("insertParcel:" + e);
			returnValue = "baseException";
		}
		System.out.println(returnValue);
		return returnValue;
	}
	/**
	 * 拆分包裹
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @throws Exception
	 */
	@RequestMapping(value = "/splitParcel", method = { RequestMethod.POST,RequestMethod.GET })
	public void splitParcel(HttpServletRequest request,HttpServletResponse response, Parcel parcel) throws Exception {
		JSONObject jsonObject = new JSONObject();
		Number Cookieid =(Number)CookieUtils.getJSONKey(request,"id");
		String Stringid = String.valueOf(Cookieid);
		Long createBy = Long.valueOf(Stringid);
		try {
			this.parcelService.splitParcel(parcel,createBy);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 合并包裹
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateParcel", method = { RequestMethod.POST,RequestMethod.GET })
	public void updateParcel(HttpServletRequest request,
			HttpServletResponse response, Parcel parcel) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Number Cookieid =(Number)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			Long updateBy = Long.valueOf(Stringid);
			this.parcelService.updateParcel(parcel,updateBy);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 发货
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateDelivergoods", method = { RequestMethod.POST,RequestMethod.GET })
	public void updateDelivergoods(HttpServletRequest request,HttpServletResponse response, Parcel parcel) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Number Cookieid =(Number)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			Long updateBy = Long.valueOf(Stringid);
			parcel.setUpdateBy(updateBy);
			parcel.setUpdateTime(DateUtil.getCurrDateTime());
			this.parcelService.updateDelivergoods(parcel,updateBy);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询今天昨天前天新订单条数
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryNewOrderCount", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryNewOrderCount(HttpServletRequest request,HttpServletResponse response, Parcel parcel, Page page)throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.parcelService.queryNewOrderCount(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询今天昨天前天待发货订单条数
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryTobeshippedOrderCount", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryTobeshippedOrderCount(HttpServletRequest request,
			HttpServletResponse response, Parcel parcel, Page page)
			throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.parcelService.queryTobeshippedOrderCount(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询今天昨天前天已发货订单条数
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAlreadyshippedOrderCount", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAlreadyshippedOrderCount(HttpServletRequest request,
			HttpServletResponse response, Parcel parcel, Page page)
			throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.parcelService.queryAlreadyshippedOrderCount(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询包裹详情
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryParcelDetails", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryParcelDetails(HttpServletRequest request,
			HttpServletResponse response, Parcel parcel, Page page)
					throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.parcelService.queryParcelDetails(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 查询包裹物流
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryParcelLogistics", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryParcelLogistics(HttpServletRequest request,HttpServletResponse response, Parcel parcel, Page page)throws Exception {
		List<Parcel> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.parcelService.queryParcelLogistics(parcel, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 导出保存在本地（导出前查询）
	 * @param request
	 * @param response
	 * @param parcel
	 */
	@RequestMapping(value = "/queryExcel", method={RequestMethod.POST,RequestMethod.GET})	
	  public void queryExcel(HttpServletRequest request,HttpServletResponse response, Parcel parcel){
			List<Parcel> list = null;
			JSONObject jsonObject = new JSONObject();
			Long a = System.currentTimeMillis();
		  try {
			  String orgocde = String.valueOf(CookieUtils.getJSONKey(request,"orgCode"));
			  parcel.setCitycode(orgocde);
			  Number Cookieid =(Number)CookieUtils.getJSONKey(request,"id");
			  String Stringid = Cookieid.toString();
			  Long updateBy = Long.valueOf(Stringid);
			  request.setCharacterEncoding("utf-8");
			  list = parcelService.queryExcelList(parcel);
			  Workbook excel = this.parcelService.queryExcel(request, response,list,updateBy);
			  String path = request.getRealPath("/");
			  File filea = new File(path+"/excel");
			  if (!filea.exists()) {
				   filea.mkdir();
			  }
			  FileOutputStream file = new FileOutputStream(path+"/excel/"+a+".xls",true);
			  excel.write(file);
			  file.close();
			  msg = Constants.SCUUESS;
		} catch (Exception e) {
			e.printStackTrace();
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg); 
		jsonObject.accumulate("a",a); 
		responseSendMsg(response, jsonObject.toString());
	  }
	/**
	 * 导出
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param filename
	 */
	@RequestMapping(value = "/downExcel", method={RequestMethod.POST,RequestMethod.GET})	
	public void downExcel(HttpServletRequest request,HttpServletResponse response, String filename){
			try {
				request.setCharacterEncoding("utf-8");
			  	@SuppressWarnings("deprecation")
				String path = request.getRealPath("/");
			  	FileInputStream file = new  FileInputStream(path + "/excel/" + filename + ".xls");
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
	 * 上传物流
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param parcel
	 * @throws Exception
	 */
	@RequestMapping(value = "/importExcel", method={RequestMethod.POST,RequestMethod.GET})
	  public void importExcel(HttpServletRequest request,HttpServletResponse response, Parcel parcel,@RequestParam("excelfile")MultipartFile excelfile){
		  InputStream inputStream;
		  JSONObject jsonObject = new JSONObject();
		try {
			Number Cookieid =(Number)CookieUtils.getJSONKey(request,"id");
			String Stringid = String.valueOf(Cookieid);
			Long updateBy = Long.valueOf(Stringid);
			parcel.setUpdateBy(updateBy);
			if(null != excelfile){
				String fileName = excelfile.getOriginalFilename();//获取文件名
				String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());//获取文件名后缀
				if (suffix == null) {
					msg = "error";//后缀为null，格式错误
				} else {
					suffix = suffix.toLowerCase();
					if (suffix.equals(".xls")) {//后缀为xls，开始解析
						inputStream = excelfile.getInputStream();
						POIFSFileSystem fs = new POIFSFileSystem(inputStream);
						HSSFWorkbook excel = new HSSFWorkbook(fs);
						boolean returnValue = this.parcelService.importCustomExcel(excel,updateBy);
						if(returnValue){
							msg = Constants.SCUUESS;
						}else{
							msg = Constants.FAIL;
						}
					} else {
						msg = "error";
					}
				}
			}else{
				msg = "isnull";//文件为空
			}
		} catch (Exception e) {
			log.error("updateParcel:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 验证上传文件的拓展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean verifyExt(String fileName) {
		try {
			String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if (ext == null) {
				return false;
			} else {
				ext = ext.toLowerCase();
				if (ext.equals(".xls")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
	}
}
