package com.emotte.order.order.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.Product;
import com.emotte.order.order.model.ProductCategory;
import com.emotte.order.order.service.ItemService;
import com.emotte.order.order.service.impl.OrderServiceImpl;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

	@Resource
	private ItemService itemService;
	
	@Resource
	private OrderServiceImpl orderserviceimpl;

	@RequestMapping(value = "/loadItem", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadItem(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Item item = this.itemService.loadItem(id);
			if (item == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = BaseConstants.SCUUESS;
			}
			request.setAttribute("item", item);
		} catch (Exception e) {
			log.error("loadItem:" + e);
			msg = BaseConstants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("order/load_item");
		return mv;
	}
	/*
	 * 查询商品的一级分类
	 */
	@RequestMapping(value = "/queryCategory", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryCategory(HttpServletRequest request, HttpServletResponse response, ProductCategory ProductCategory,
			Page page) throws Exception {
		List<ProductCategory> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemService.queryproductCategory(ProductCategory);
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
	/*
	 * 查询商品的二级三级分类
	 */
	@RequestMapping(value = "/queryClassify", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryClassify(HttpServletRequest request, HttpServletResponse response, ProductCategory ProductCategory,
			Page page) throws Exception {
		List<ProductCategory> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemService.queryproductClassify(ProductCategory);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryproductClassify:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/queryProduct", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryProduct(HttpServletRequest request, HttpServletResponse response, CityProduct cityProduct) throws Exception {
		List<CityProduct> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemService.queryCityAndProduct(cityProduct);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCityAndProduct:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());

	}

	@RequestMapping(value = "/queryItem", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryItem(HttpServletRequest request, HttpServletResponse response, Item item, Page page)
			throws Exception {
		List<Item> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.itemService.queryItem(item, page);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryItem:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertItem", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertItem(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// System.out.println(data);
		try {
			String manager = CookieUtils.getJSON(request).getLong("id") +"," +CookieUtils.getJSON(request).getLong("deptId");
			data=data.replace("\n", "\\n");
			this.itemService.insertItems(data,manager);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("insertItem:" + e);
			msg = BaseConstants.FAIL;
		}
		
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateItem", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateItem(HttpServletRequest request, HttpServletResponse response, Item item) throws Exception {
		JSONObject jsonObject = new JSONObject();
		// Manager manager = RequestUtils.getCurrentLoginingManager(request);
		// long createBy = manager.getId();
		try {
			item.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
			this.itemService.updateItem(item);
			msg = BaseConstants.SCUUESS;
		} catch (Exception e) {
			log.error("updateItem:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/queryCityServerProduct", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryCityServerProduct(HttpServletRequest request, HttpServletResponse response, CityProduct cityProduct) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<CityProduct> list = null;
		try {
			 list = this.itemService.queryCityServerProduct(cityProduct);
			if(list==null || list.size()==0){
				msg = "01";
			}else{
				msg = BaseConstants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("queryCityServerProduct:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 新增订单查询基础商品
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param product
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryBaseProduct", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryBaseProduct(HttpServletRequest request, HttpServletResponse response, Product product) throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<Product> list = null;
		try {
			 list = this.itemService.queryBaseProduct(product);
			if(list==null || list.size()==0){
				msg = BaseConstants.NULL;
			}else{
				msg = BaseConstants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("BaseConstants:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/selectOrderClash", method = { RequestMethod.POST, RequestMethod.GET })
	public void selectOrderClash(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
		JSONObject jsonObject = new JSONObject();
		HashMap<String, Object> map=null;
		try {
			 map = this.itemService.selectOrderClash(data);
			if(map==null){
				msg = BaseConstants.NULL;
			}else{
				msg = BaseConstants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("BaseConstants:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("map", map);
		responseSendMsg(response, jsonObject.toString());
	}

}
