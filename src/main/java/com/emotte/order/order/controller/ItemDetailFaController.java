package com.emotte.order.order.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import net.sf.json.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;
import org.wildhorse.server.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.emotte.order.order.model.ItemDetailFa;
import com.emotte.order.order.model.ItemFaQuery;
import com.emotte.order.order.service.ItemDetailFaService;
import com.emotte.order.util.ExportExcelUtil;


    @Controller
 @RequestMapping("/itemDetailFa")
public class ItemDetailFaController extends BaseController{
     
           @Resource
	private ItemDetailFaService itemDetailFaService;
	
	
	   
	    @RequestMapping(value = "/loadItemDetailFa", method={RequestMethod.POST,RequestMethod.GET})
  public ModelAndView loadItemDetailFa(HttpServletRequest request,HttpServletResponse response, Long id)throws Exception{
	    ModelAndView mv = new ModelAndView();
	  try {
		 ItemDetailFa itemDetailFa=this.itemDetailFaService.loadItemDetailFa(id);
		  if (itemDetailFa==null) {
		        msg=BaseConstants.NULL;
				
			}else{
				msg=BaseConstants.SCUUESS;
			}
		    request.setAttribute("itemDetailFa", itemDetailFa);			
	} catch (Exception e) {
		 log.error("loadItemDetailFa:"+e);
		 msg=BaseConstants.FAIL;
	}
	  request.setAttribute("msg", msg);
   mv.setViewName("order/load_itemDetailFa");
   return mv;
  }
	   
	
	  @RequestMapping(value = "/queryItemDetailFa", method={RequestMethod.POST,RequestMethod.GET})
	public void queryItemDetailFa(HttpServletRequest request,HttpServletResponse response, ItemDetailFa itemDetailFa,Page page)throws Exception{
		  List<ItemDetailFa> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.itemDetailFaService.queryItemDetailFa(itemDetailFa,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryItemDetailFa:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/insertItemDetailFa", method={RequestMethod.POST,RequestMethod.GET})
  public void insertItemDetailFa(HttpServletRequest request,HttpServletResponse response, ItemDetailFa itemDetailFa)throws Exception{
	    JSONObject jsonObject=new JSONObject();
	  try {
		this.itemDetailFaService.insertItemDetailFa(itemDetailFa);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
		 log.error("insertItemDetailFa:"+e);
		 msg=BaseConstants.FAIL;
	}
	jsonObject.accumulate("msg", msg);
	responseSendMsg(response, jsonObject.toString());
  }
  @RequestMapping(value = "/updateItemDetailFa", method={RequestMethod.POST,RequestMethod.GET})
  public void updateItemDetailFa(HttpServletRequest request,HttpServletResponse response, ItemDetailFa itemDetailFa)throws Exception{
	  JSONObject jsonObject=new JSONObject();
	  try {
		this.itemDetailFaService.updateItemDetailFa(itemDetailFa);
		msg=BaseConstants.SCUUESS;
	} catch (Exception e) {
	     log.error("updateItemDetailFa:"+e);
		 msg=BaseConstants.FAIL;
	}
	 jsonObject.accumulate("msg", msg);
	 responseSendMsg(response, jsonObject.toString());
  } 
  /**
   * 订单fa营养查询
   * */
  @RequestMapping(value = "/queryItemList", method={RequestMethod.POST,RequestMethod.GET})
	public void queryItemList(HttpServletRequest request,HttpServletResponse response, ItemFaQuery itemFaQuery,Page page)throws Exception{
		  List<ItemFaQuery> list=null;
		   JSONObject jsonObject=new JSONObject();
		 try {
			list=this.itemDetailFaService.queryItemQuery(itemFaQuery,page);
			if(list.size()>0) {
				msg=BaseConstants.SCUUESS;
			}else{
				msg=BaseConstants.NULL;
			}
		} catch (Exception e) {
		    log.error("queryItemDetailFa:"+e);
			msg=BaseConstants.FAIL;
		} 
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
  /**
   * 导出fa营养订单
   * */
  @RequestMapping(value = "/exportOrderExcel")
	public void exportOrderExcel(HttpServletRequest request, HttpServletResponse response,ItemFaQuery itemFaQuery)
			throws IOException {
	  JSONObject jsonObject = new JSONObject();
		//生成时间戳：年月日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date  =new Date();
		//生成0~999之间的随机数
		int num=(int)(Math.random()*999);
		//设置excel文件名：
		String fileName = "fa营养订单(导出)"+sdf.format(date) +"_"+ num;
		//excelHeader excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
		String[] excelHeader = {"订单编号#orderCode","收货人姓名#receiverName","收货人电话#receiverMobile","下单电话#userMobile","收货地址#userAddress",
				"下单日期#createTime","配送日期#recetptTime","商品名称#productName", "规格#productSpec","数量#quantity","订单渠道#orderChannel",
				"下单部门#name","订单来源#orderSourceId","备注#remark"	};
		List<ItemFaQuery> dataList = itemDetailFaService.queryItemQuerylist(itemFaQuery);
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
}
