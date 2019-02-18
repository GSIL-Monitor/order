package com.emotte.order.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.eclient.EClientContext;
import com.emotte.interf.ECommentService;
import com.emotte.interfac.comment.model.Appraise;
import com.emotte.interfac.comment.model.AppraiseParaVo;
import com.emotte.interfac.comment.model.AppraiseReply;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.CommentResultVo;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.JsonUtil;
import com.emotte.util.page.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/appraise")
public class AppraiseController extends BaseController {

	@RequestMapping(value = "/queryAppraise", method = { RequestMethod.POST})
	public void queryAppraise(HttpServletRequest request,
			HttpServletResponse response, Appraise appraise, Page page,boolean reply)
			throws Exception {
		List<Appraise> list = new ArrayList<Appraise>();
		JSONObject jsonObject = new JSONObject();
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			ECommentService commentService=(ECommentService) EClientContext.getBean(ECommentService.class);
			//Page page1 = new Page();
			//page1.setCurPage(page.getCurPage());
			//page1.setPageCount(page.getPageCount());
			//page1.setTotalRecord(page.getTotalRecord());
			Appraise para = new Appraise();
			para.setIsFisrt(appraise.getIsFisrt());//初评
			para.setIsShow(appraise.getIsShow());//显示
			para.setOrdId(appraise.getOrdId());//订单ID
			para.setReId(appraise.getReId());//服务人员ID
			AppraiseParaVo apv = new AppraiseParaVo();
			apv.setPage(page);
			apv.setPara(para);
			apv.setReply(true);
			String json = new org.json.JSONObject(apv).toString();
			String appraiseStr = commentService.queryAppraiseListPage(json);
			String s=new String(appraiseStr.getBytes(),"utf-8");
			LogHelper.info(getClass()+ "queryAppraise()","获取的queryAppraise:"+appraiseStr);
			CommentResultVo addAppraise = JsonUtil.fromJson(s,CommentResultVo.class);
			if (addAppraise.getData().getData().size() > 0) {
				System.out.println(addAppraise.getData().getData().get(0).getReply());
				list = addAppraise.getData().getData();
				page = addAppraise.getData().getPage();
			}
			if (list.size() > 0 ) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAppraise:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertAppraiseReply", method = { RequestMethod.POST})
	public void insertAppraiseReply(HttpServletRequest request,
			HttpServletResponse response, AppraiseReply para,String ids) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			ECommentService commentService=(ECommentService) EClientContext.getBean(ECommentService.class);
			List<AppraiseReply> list = new ArrayList<AppraiseReply>();
			String[] id = ids.split(",");
		    for (int i = 0; i < id.length; i++) { 
		    	AppraiseReply appraiseReply = new AppraiseReply();
		    	appraiseReply.setApprId(Long.parseLong(id[i]));//评价、评论ID
		    	appraiseReply.setReContent(para.getReContent());//回复内容
		    	appraiseReply.setReplyUserId(CookieReaderUtil.cookieReader(request).getId());//回复人ID
		    	appraiseReply.setReplyUserName(CookieReaderUtil.cookieReader(request).getRealName());//回复人名称
		    	list.add(appraiseReply);
		    }
			String appraiseReplyStr = commentService.insertAppraiseReply(new JSONArray(list).toString());
			LogHelper.info(getClass()+ "insertAppraiseReply()","获取的appraiseReplyStr:"+appraiseReplyStr);
			JSONObject  jasonObject = JSONObject.fromObject(appraiseReplyStr);
			Map map = jasonObject;
			LogHelper.info(getClass()+ "insertAppraiseReply()","获取的code:"+map.get("code"));
			if (map.get("code") != null && "1000".equals(map.get("code").toString())) {
				msg = Constants.SCUUESS;
			}else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("insertAppraiseReply:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateAppraise", method = { RequestMethod.POST})
	public void updateAppraise(HttpServletRequest request,
			HttpServletResponse response, Appraise appraise,String ids) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			ECommentService commentService=(ECommentService) EClientContext.getBean(ECommentService.class);
			List<Long> list = new ArrayList<Long>();
			Appraise para = new Appraise();
			para.setIsShow(appraise.getIsShow());//显示、隐藏
			String[] id = ids.split(",");
		    for (int i = 0; i < id.length; i++) { 
		    	list.add(Long.parseLong(id[i]));
		    }
		    para.setIds(list);//评价ID
		    
			String json = new org.json.JSONObject(para).toString();
			String updateAppraise = commentService.updateAppraise(json);
			LogHelper.info(getClass()+ "updateAppraise()","获取的updateAppraise:"+updateAppraise);
			JSONObject  jasonObject = JSONObject.fromObject(updateAppraise);
			Map map = jasonObject;
			LogHelper.info(getClass()+ "updateAppraise()","获取的code:"+map.get("code"));
			if (map.get("code") != null && "1000".equals(map.get("code").toString())) {
				msg = Constants.SCUUESS;
			}else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("updateAppraise:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
}
