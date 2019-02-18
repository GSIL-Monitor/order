package com.emotte.order.warehouse.controller;

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

import com.emotte.order.util.Constants;
import com.emotte.order.warehouse.model.PackageItemRef;
import com.emotte.order.warehouse.service.PackageItemRefService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/packageItemRef")
public class PackageItemRefController extends BaseController {

	@Resource
	private PackageItemRefService packageItemRefService;

	@RequestMapping(value = "/loadPackageItemRef", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadPackageItemRef(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			PackageItemRef packageItemRef = this.packageItemRefService.loadPackageItemRef(id);
			if (packageItemRef == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("packageItemRef", packageItemRef);
		} catch (Exception e) {
			log.error("loadPackageItemRef:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_packageItemRef");
		return mv;
	}

	@RequestMapping(value = "/queryPackageItemRef", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryPackageItemRef(HttpServletRequest request,
			HttpServletResponse response, PackageItemRef packageItemRef, Page page)
			throws Exception {
		List<PackageItemRef> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.packageItemRefService.queryPackageItemRef(packageItemRef, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryPackageItemRef:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertPackageItemRef", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertPackageItemRef(HttpServletRequest request,
			HttpServletResponse response, PackageItemRef packageItemRef) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.packageItemRefService.insertPackageItemRef(packageItemRef);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertPackageItemRef:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updatePackageItemRef", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updatePackageItemRef(HttpServletRequest request,
			HttpServletResponse response, PackageItemRef packageItemRef) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.packageItemRefService.updatePackageItemRef(packageItemRef);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updatePackageItemRef:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

}
