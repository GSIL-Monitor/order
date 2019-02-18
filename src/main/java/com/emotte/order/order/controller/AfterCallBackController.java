package com.emotte.order.order.controller;

import com.beust.jcommander.internal.Lists;
import com.emotte.order.order.model.AfterCallBack;
import com.emotte.order.order.model.OrderAfterSales;
import com.emotte.order.order.service.AfterCallBackService;
import com.emotte.order.order.service.OrderAfterSalesService;
import com.emotte.order.util.Constants;
import com.emotte.server.util.CookieUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lenovo on 2018/12/26.
 */
@Controller
@RequestMapping("/callBack")
public class AfterCallBackController extends OrderBaseController{

    private static final Logger LOG = LoggerFactory.getLogger(AfterCallBackController.class);

    @Resource
    private AfterCallBackService afterCallBackService;

    @Resource
    private OrderAfterSalesService orderAfterSalesService;

    /**
     * 查询回访记录
     *
     * @param request
     * @param response
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    @RequestMapping(value = "/findCallBackList",method = RequestMethod.POST)
    public void findCallBackList(HttpServletRequest request, HttpServletResponse response, AfterCallBack afterCallBack){
        JSONObject jsonObject = new JSONObject();
        List<AfterCallBack> afterCallBacks = Lists.newArrayList();
        OrderAfterSales orderAfterSales = afterCallBackService.findCustomerByAfterSalesId(afterCallBack.getAfterSalesId());
        try {
            afterCallBacks = afterCallBackService.findCallBackList(afterCallBack);
            if(CollectionUtils.isNotEmpty(afterCallBacks)){
                msg = Constants.SCUUESS;
            }else{
                msg = Constants.NULL;
            }
        } catch (Exception e) {
            LOG.error("findCallBackList方法执行失败,参数信息:{orderId:"+afterCallBack.getOrderId()+",afterSalesId:"+afterCallBack.getAfterSalesId()+
                    "},异常信息:{}", ExceptionUtils.getStackTrace(e));
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("orderAfterSales",orderAfterSales);
        jsonObject.accumulate("afterCallBacks",afterCallBacks);
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }

    /**
     * 保存
     *
     * @param request
     * @param response
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    @RequestMapping(value = "/addCallBack",method = RequestMethod.POST)
    public void addCallBack(HttpServletRequest request,HttpServletResponse response,AfterCallBack afterCallBack){
        JSONObject jsonObject = new JSONObject();
        Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
        afterCallBack.setCreateBy(loginId);
        afterCallBack.setUpdateBy(loginId);
        afterCallBack.setValid(1);
        try {
            //查询结算单ID
            OrderAfterSales orderAfterSales = orderAfterSalesService.loadOrderAfterSales(afterCallBack.getAfterSalesId());
            afterCallBack.setAccountId(orderAfterSales.getAccountPayId());
            afterCallBackService.addCallBack(afterCallBack);
            msg = Constants.SCUUESS;
        } catch (Exception e) {
            msg = Constants.FAIL;
            LOG.error("addCallBack方法执行失败,错误信息:{}",ExceptionUtils.getStackTrace(e));
        }
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }

    /**
     * 修改
     *
     * @param request
     * @param response
     * @param id
     * @Author zhanghao
     * @Date 20181227
     */
    @RequestMapping(value = "/update/{id}",method = RequestMethod.GET)
    public void update(HttpServletRequest request,HttpServletResponse response,@PathVariable("id") Long id){
        JSONObject jsonObject = new JSONObject();
        AfterCallBack afterCallBack = new AfterCallBack();
        try {
            afterCallBack = afterCallBackService.findOneById(id);
        } catch (Exception e) {
            LOG.error("update修改方法执行失败,错误信息:{}",ExceptionUtils.getStackTrace(e));
        }
        jsonObject.accumulate("afterCallBack",afterCallBack);
        responseSendMsg(response,jsonObject.toString());
    }

    /**
     * 更新
     *
     * @param request
     * @param response
     * @param afterCallBack
     * @Author zhanghao
     * @Date 20181227
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public void update(HttpServletRequest request,HttpServletResponse response,AfterCallBack afterCallBack){
        JSONObject jsonObject = new JSONObject();
        Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
        afterCallBack.setUpdateBy(loginId);
        try {
            afterCallBackService.update(afterCallBack);
            msg = Constants.SCUUESS;
        } catch (Exception e) {
            LOG.error("update更新方法执行失败,错误信息:{}",ExceptionUtils.getStackTrace(e));
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }
}
