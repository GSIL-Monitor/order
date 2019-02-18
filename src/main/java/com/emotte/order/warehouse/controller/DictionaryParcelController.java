package com.emotte.order.warehouse.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.util.Constants;
import com.emotte.order.warehouse.model.Dictionary;
import com.emotte.order.warehouse.service.DictionaryParcelService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/dict")
public class DictionaryParcelController extends BaseController {

	@Resource
	private DictionaryParcelService dictionaryParcelService;

	@RequestMapping(value = "/queryDictionary", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryDictionary(HttpServletRequest request,
			HttpServletResponse response, Dictionary dictionary, Page page)
			throws Exception {
		List<Dictionary> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.dictionaryParcelService.queryDictionary(dictionary, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryDictionary:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

}
