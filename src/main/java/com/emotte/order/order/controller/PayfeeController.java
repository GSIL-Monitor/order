package com.emotte.order.order.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import com.alibaba.fastjson.JSON;
import com.emotte.dubbo.activities.service.CardPayDubboService;
import com.emotte.dubbo.finance.BalanceAccountDubbo;
import com.emotte.dubbo.finance.IsPosSuccessService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.file.rw.IFileRWService;
import com.emotte.kernel.file.rw.impl.excel.ExcelServiceImpl;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.service.PayfeeService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.ImgUploadUtil;
import com.emotte.order.util.LoginCookieInfo;
import com.emotte.order.util.PropertiesHelper;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/payfee")
public class PayfeeController extends BaseController {

    @Resource
    private PayfeeService payfeeService;
    @Resource
    private IsPosSuccessService isPosSuccessService;
    @Resource
    private OrderInterfaceService orderInterfaceService;
    @Resource
    private SMSServiceDubbo sMSServiceDubbo;
    @Resource
    private BalanceAccountDubbo balanceAccountDubbo;
    @Resource
    private CardPayDubboService cardPayDubboService;
    

    @RequestMapping(value = "/loadPayfee", method = {RequestMethod.POST, RequestMethod.GET})
    public void loadPayfee(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            Payfee payfee = this.payfeeService.loadPayfee(id);
            if (payfee == null) {
                msg = BaseConstants.NULL;

            } else {
                msg = BaseConstants.SCUUESS;
            }
            jsonObject.accumulate("payfee", payfee);
        } catch (Exception e) {
            log.error("loadPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/queryPayfee", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryPayfee(HttpServletRequest request, HttpServletResponse response, Payfee payfee, Page page)
            throws Exception {
        List<Payfee> list = null;
        JSONObject jsonObject = new JSONObject();
        try {
            list = this.payfeeService.queryPayfee(payfee, page);
            if (list != null && list.size() > 0) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("list", list);
        jsonObject.accumulate("page", page);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/insertPayfee", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertPayfee(HttpServletRequest request, HttpServletResponse response, Payfee payfee) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
            payfee.setIsManualFee(1);//后台录入默认为自动
            this.payfeeService.insertPayfee(payfee);
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("insertPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/insertPayfeeList", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertPayfeeList(HttpServletRequest request, HttpServletResponse response,
                                 String data, Long accountId, Long orderId, int cateType, String userMobile,
                                 String message, String cityCode) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String oneMsg=null;
        try {
            this.payfeeService.insertPayfeeList(data, accountId, CookieUtils.getJSON(request).getLong("id"), orderId, cateType);
            String jsonStr = "{\"id\":\"" + accountId + "\",\"createBy\":\"" + CookieUtils.getJSON(request).getLong("id") + "\"}";
            //String jsonStr = "{\"id\":\"188409469048772\",\"createBy\":\"1\"}" ;
            this.orderInterfaceService.updateOrderByAccount(jsonStr);
            try {
            	/** 周鑫  自动20250052 管家帮收款码，自动对账 **/
                JSONArray array = JSONArray.fromObject(data);  
        		Object[] obj = new Object[array.size()];
        		for(int i = 0; i < array.size(); i++){  
                   JSONObject JB = array.getJSONObject(i);
                   //将建json对象转换为Object对象
                   obj[i] = JSONObject.toBean(JB, Payfee.class);  
                }
        		if (obj.length>0) {
        			Payfee payfee = (Payfee) obj[0];
        			if (payfee.getFeePost()==20250052) {
        				Map<String, String> map=new HashMap<String, String>();
        				//构造参数
        				map.put("bankFlowNum", payfee.getBankFlowNum());
        				map.put("createBy", CookieUtils.getJSON(request).getLong("id")+"");
        				map.put("feePost", payfee.getFeePost().toString());
        				map.put("feeSum",payfee.getFeeSum().toString());
        				map.put("feeToDate", payfee.getFeeToDate());
        				//map.put("postNum", null);
        				//map.put("postUser", null);
        				JSONObject jsonObject3 = JSONObject.fromObject(map);
        				String oneString = balanceAccountDubbo.one2OneInComeBalanceAccount(jsonObject3.toString());
        				JSONObject jsonObject2 = JSONObject.fromObject(oneString);
        				Map<String, String> bean = (Map<String, String>) JSONObject.toBean(jsonObject2, Map.class);
        				String  result= bean.get("result");
        				oneMsg=result;
    				}
    			}
               /** 结束 **/ 
			} catch (Exception e) {
				 log.error("insertPayfee:" + e);
				 msg = BaseConstants.FAIL;
			}
            
            if ("101001001".equals(cityCode)) {
                try {
                    JSONObject smsData = new JSONObject();
                    smsData.accumulate("mobiles", userMobile);
                    smsData.accumulate("templet", "order_40"); //
                    smsData.accumulate("channel", "sys");
                    smsData.accumulate("system", "order");
                    smsData.accumulate("rate", "1"); //1即时发送，2定时发送
                    smsData.accumulate("params", new String[]{message});
                    // 返回结果(seccess/fail:失败原因)
                    String res = this.sMSServiceDubbo.send(smsData.toString());
                    //System.out.println("sMSServiceDubbo:" +smsData.toString());
                    //System.out.println("sMSServiceDubbo:" +res);
                } catch (Exception e) {
                }
            }
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("insertPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("oneMsg", oneMsg);
        
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/updatePayfee", method = {RequestMethod.POST, RequestMethod.GET})
    public void updatePayfee(HttpServletRequest request, HttpServletResponse response, Payfee payfee) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            //查询ids共有多少是缴费表里面的数据
            String[] idStrings = null;
            String[] idStrings2 = null;
            Page page = new Page();
            payfee.setFlagPage(-1);
            payfee.setPage(page);
            List<Payfee> payfeesList = this.payfeeService.queryPayfee(payfee, page);
            List<Payfee> payfeesList2 = this.payfeeService.queryOtherDeal(payfee);
            //更新字段设置
            payfee.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
            if (payfee.getPosCheckStatus() != null && payfee.getIsHandle() == null) {
                payfee.setPosCheckBy(CookieUtils.getJSON(request).getLong("id"));
                payfee.setPosCheckDept(CookieUtils.getJSON(request).getLong("deptId"));
                payfee.setPosCheckDate(DateUtil.getCurrDateTime());
            }
            //缴费个数不为空
            if (!payfeesList.isEmpty() && payfeesList.size() != 0) {
                //更新缴费表部分
                this.payfeeService.updatePayfee(payfee);
            }
            //其他缴费个数不为空
            if (!payfeesList2.isEmpty() && payfeesList2.size() != 0) {
                //更新其他缴费表部分
                this.payfeeService.updateOtherDeal(payfee);
            }
//			this.payfeeService.updatePayfee(payfee);
            /*if("101001001".equals(payfee.getCityCode())){
				try{
					JSONObject smsData = new JSONObject();
					smsData.accumulate("mobiles", payfee.getUserMobile());
					smsData.accumulate("templet", "order_40"); // 
					smsData.accumulate("channel", "sys");
					smsData.accumulate("system", "order"); 
					smsData.accumulate("rate", "1"); //1即时发送，2定时发送
					smsData.accumulate("params", new String[]{payfee.getMessage()}); 
					// 返回结果(seccess/fail:失败原因)
					String res = this.sMSServiceDubbo.send(smsData.toString());
					//System.out.println("sMSServiceDubbo:" +smsData.toString());
					//System.out.println("sMSServiceDubbo:" +res);
				}catch(Exception e){}
			}*/
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("updatePayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/loadAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public void loadAccount(HttpServletRequest request, HttpServletResponse response, Long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            Payfee payfee = this.payfeeService.loadAccount(id);
            if (payfee == null) {
                msg = BaseConstants.NULL;

            } else {
                msg = BaseConstants.SCUUESS;
            }
            jsonObject.accumulate("payfee", payfee);
        } catch (Exception e) {
            log.error("loadPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/queryAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryAccount(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        List<Payfee> list = null;
        JSONObject jsonObject = new JSONObject();
        try {
            list = this.payfeeService.queryAccount(payfee);
            if (list.size() > 0) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryAccount:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("list", list);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/insertAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertAccount(HttpServletRequest request, HttpServletResponse response, Payfee payfee, String jsonStr) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setCreateBy(CookieUtils.getJSON(request).getLong("id")); // 得到当前录入 人ID
            payfee.setIsManual(1); // 设置由后台录入的状态为 1
            this.payfeeService.insertAccount(payfee, jsonStr);
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("insertPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    @RequestMapping(value = "/updateAccount", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateAccount(HttpServletRequest request, HttpServletResponse response, Payfee payfee) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setUpdateBy(CookieUtils.getJSON(request).getLong("id"));
            this.payfeeService.updateAccount(payfee);
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("updatePayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    // 查询当前订单用户下邦定的礼品卡
    @RequestMapping(value = "/queryPayfeeLpk", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryPayfeeLpk(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        List<Payfee> list = null;
        JSONObject jsonObject = new JSONObject();
        try {
            list = this.payfeeService.queryPayfeeLpk(payfee);
            if (list.size() > 0) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryAccount:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("list", list);
        responseSendMsg(response, jsonObject.toString());
    }

    //

    /**
     * 发送短信
     *
     * @param request
     * @param response
     * @param card
     * @throws Exception
     */
    @RequestMapping(value = "/sendMessage", method = {RequestMethod.POST, RequestMethod.GET})
    public void sendMessage(HttpServletRequest request, HttpServletResponse response, String phone) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String code = "";
        try {
            if (!phone.equals("")) {
                String jsonStr = null;
                @SuppressWarnings({"resource", "deprecation"})
                HttpClient httpclient = new DefaultHttpClient();
                code = getSixRandomCode();
                String modle = "您的礼品卡正在绑定此手机号，验证码" + code + "【管家帮】";
                HttpPost httppost = new HttpPost(
                        "http://m.95081.com/sms.jsp?mobile=" + phone + "&message=" + modle + "");
                HttpResponse responsee = httpclient.execute(httppost);
                System.out.println(responsee + "responsee");
                HttpEntity resEntity = responsee.getEntity();
                if (resEntity != null) {
                    jsonStr = EntityUtils.toString(resEntity);
                }
                System.out.println(jsonStr + "jsonStr");
                if (jsonStr.contains("success")) {
                    msg = BaseConstants.SCUUESS;
                } else {
                    msg = BaseConstants.FAIL;
                }
            }
        } catch (Exception e) {
            log.error("sendMass:" + e);
            msg = BaseConstants.FAIL;
        }
		/*code = MD5Util.str2MD5(code);*///MD5加密
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("code", code);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 生成随机六位数字
     *
     * @return
     */
    public static String getSixRandomCode() {
        Random rad = new Random();
        String result = rad.nextInt(1000000) + "";
        if (result.length() != 6) {
            return getSixRandomCode();
        }
        return result;
    }

    @RequestMapping(value = "/posSuccessService", method = {RequestMethod.POST, RequestMethod.GET})
    public void posSuccessService(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            String result = this.isPosSuccessService.isSuccess(data);
            System.out.println("result:" + result);
            if (result != null && result.equals("ok")) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.FAIL;
            }
        } catch (Exception e) {
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询结算单对应缴费最小时间
     */
    @RequestMapping(value = "/loadPayfeeMinCreTime", method = {RequestMethod.POST, RequestMethod.GET})
    public void loadPayfeeMinCreTime(HttpServletRequest request, HttpServletResponse response, Long accountId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            String minCreTime = this.payfeeService.loadPayfeeMinCreTime(accountId);
            msg = BaseConstants.SCUUESS;
            jsonObject.accumulate("minCreTime", minCreTime);
        } catch (Exception e) {
            log.error("loadPayfeeMinCreTime:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 根据客户ID查询客户的白条信息（一个客户只有一个白条）
     *
     * @param request
     * @param response
     * @param userId   客户ID
     * @throws Exception
     */
    @RequestMapping(value = "/loadIOUsByUserId", method = {RequestMethod.POST, RequestMethod.GET})
    public void loadIOUsByUserId(HttpServletRequest request, HttpServletResponse response,
                                 Long userId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            Payfee iouPayfee = this.payfeeService.loadIOUsByUserId(userId);
            if (iouPayfee != null) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
            jsonObject.accumulate("iouPayfee", iouPayfee);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("loadIOUsByUserId:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 上传图片返回url
     *
     * @param request
     * @param response
     * @author 王世博
     */
    @UserAnnotation(methodName = "上传图片")
    @RequestMapping(value = "/insertImgUrl", method = {RequestMethod.POST, RequestMethod.GET})
    public void insertImgUrl(HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        String imgInfo = null;
        try {
            imgInfo = ImgUploadUtil.upload(request, "ORIGINAL");
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("insertProductImg:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("imgInfo", imgInfo);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询POS审核数据(管理员)
     *
     * @param request
     * @param response
     * @param payfee
     * @param page
     * @throws Exception
     */
    @RequestMapping(value = "/queryPosCheck", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryPosCheck(HttpServletRequest request, HttpServletResponse response, Payfee payfee, Page page)
            throws Exception {
        List<Payfee> list = null;
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setFlagPage(1);
            payfee.setPage(page);
            payfee.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
            payfee.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
            if (payfee.getLoginLevel() != null) {
                payfee.setLoginLevel(1);
            }
            list = this.payfeeService.queryPosCheck(payfee, page);
            if (list != null && list.size() > 0) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryPayfee:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("list", list);
        jsonObject.accumulate("page", page);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 导出符合条件的POS凭条图片
     *
     * @param request  请求
     * @param response 响应
     * @param payfee   POS缴费凭条信息
     * @throws Exception
     */
    @UserAnnotation(methodName = "导出符合条件的POS凭条图片")
    @RequestMapping(value = "/exportPosCheck", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportPosCheck(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        String fileName = "";
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
            payfee.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
            if (payfee.getLoginLevel() != null) {
                payfee.setLoginLevel(1);
            }
            String ids = request.getParameter("ids");
            if (!StringUtils.isBlank(ids)) payfee.setIds_(ids.split(","));
            String realPath = request.getSession().getServletContext().getRealPath("/");//项目根路径
            List<Map<String, Object>> agrList = this.payfeeService.queryPosCheckImg(payfee);
            if (agrList != null && agrList.size() > 0) {
                msg = BaseConstants.SCUUESS;
                jsonObject.accumulate("list", agrList);
            } else {
                msg = Constants.FAIL;
            }
        } catch (Exception e) {
            log.error("exportPosCheck:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }


    @UserAnnotation(methodName = "下载文件")
    @RequestMapping(value = "/downloadFile", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setContentType("application/octet-stream");
            String realPath = request.getSession().getServletContext().getRealPath("/");//项目根路径
            realPath = realPath.replaceAll("\\\\", "/");
            String allPath = realPath + fileName;
            File f = new File(allPath);
            FileInputStream inputStream = new FileInputStream(f);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[2048];
            int iLength = 0;
            while ((iLength = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, iLength);
            }
            out.flush();
            out.close();
            inputStream.close();
        } catch (Exception e) {
            log.error("downloadFile:" + e);
        }
    }

    /**
     * 导出符合条件的POS凭条Excel
     *
     * @param request  请求
     * @param response 响应
     * @param payfee   POS缴费凭条信息
     * @throws Exception
     */
    @UserAnnotation(methodName = "导出符合条件的POS凭条Excel")
    @RequestMapping(value = "/exportPosCheckExcel", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportPosCheckExcel(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        String path = request.getSession().getServletContext().getRealPath("/");
        String name = "dataFile/contract/" + request.getSession().getId() + "_" + System.currentTimeMillis() + "_pos.xls";
        String filePath = path + name;
        try {
            payfee.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
            payfee.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
            if (payfee.getLoginLevel() != null) {
                payfee.setLoginLevel(1);
            }
            // 设置标题
            String titles = PropertiesHelper.getValue("props/export.properties", "posCheck");
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) JSON.parse(titles);
            String ids = request.getParameter("ids");
            if (!StringUtils.isBlank(ids)) payfee.setIds_(ids.split(",")); // 存储前台传递过来的ids
            List<Map<String, Object>> data = this.payfeeService.queryPosCheckExcel(payfee);
            if (null != data && !data.isEmpty()) {
                data.add(0, map); // 存入标题
                IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> fileService = new ExcelServiceImpl();
                fileService.write2File(data, filePath); // 将内容存入excel
                jsonObject.accumulate("url", name); // 将url返回到页面，以便于下载使用
                msg = Constants.SCUUESS;
            } else {
                msg = "导出失败！";
            }
        } catch (Exception e) {
            log.error("exportPosCheckExcel:" + e);
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询POS凭条未处理项
     *
     * @param request  请求
     * @param response 响应
     * @param payfee   POS缴费凭条信息
     * @throws Exception
     */
    @UserAnnotation(methodName = "查询POS凭条未处理项")
    @RequestMapping(value = "/queryUncheck", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryUncheck(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        Integer count = 0;
        JSONObject jsonObject = new JSONObject();
        try {
            payfee.setLoginBy(CookieReaderUtil.cookieReader(request).getId());
            payfee.setLoginDept(CookieReaderUtil.cookieReader(request).getDeptId());
            if (payfee.getLoginLevel() != null) {
                payfee.setLoginLevel(1);
            }
            count = this.payfeeService.queryUncheck(payfee);
            if (count != null && count != 0) {
                jsonObject.accumulate("count", count);
                msg = Constants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryUncheck:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询渠道
     *
     * @param request
     * @param response
     * @param orderId
     * @throws Exception void
     * @Description: TODO
     * @author lidenghui
     * @date 2018年4月20日 下午3:36:44
     * @version
     */
    @UserAnnotation(methodName = "查询渠道id")
    @RequestMapping(value = "/queryChannel", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryChannel(HttpServletRequest request, HttpServletResponse response, Long orderId)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        try {
            Long channelId = payfeeService.queryChannel(orderId);
            if (null != channelId) {
                msg = Constants.SCUUESS;
                jsonObject.accumulate("channelId", channelId);
            }
        } catch (Exception e) {
            log.error("queryChannel:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 订单页面缴费列表回显
     *
     * @param request
     * @param response
     * @param payfee
     * @throws Exception
     */
    @RequestMapping(value = "/queryAccountList", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryAccountList(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        List<Payfee> list = null;
        JSONObject jsonObject = new JSONObject();
        try {
            list = this.payfeeService.queryAccountList(payfee);
            if (list.size() > 0) {
                msg = BaseConstants.SCUUESS;
            } else {
                msg = BaseConstants.NULL;
            }
        } catch (Exception e) {
            log.error("queryAccount:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("list", list);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询缴费已使用明细金额
     *
     * @param request
     * @param response
     * @param payfee
     * @throws Exception
     */
    @RequestMapping(value = "/queryPayfeeDetail", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryPayfeeDetail(HttpServletRequest request, HttpServletResponse response, Payfee payfee)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        Double salaryFee = 0D;
        try {
            salaryFee = this.payfeeService.queryPayfeeDetail(payfee);
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("queryPayfeeDetail:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("salaryFee", salaryFee);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 查询订单父订单赠送解决方案类型
     *
     * @param request
     * @param response
     * @param payfee
     * @throws Exception
     */
    @RequestMapping(value = "/queryTypeByParentId", method = {RequestMethod.POST, RequestMethod.GET})
    public void queryTypeByParentId(HttpServletRequest request, HttpServletResponse response, Long parentId)
            throws Exception {
        JSONObject jsonObject = new JSONObject();
        Long type = null;
        try {
            type = this.payfeeService.queryTypeByParentId(parentId);
            msg = BaseConstants.SCUUESS;
        } catch (Exception e) {
            log.error("queryTypeByParentId:" + e);
            msg = BaseConstants.FAIL;
        }
        jsonObject.accumulate("msg", msg);
        jsonObject.accumulate("type", type);
        responseSendMsg(response, jsonObject.toString());
    }

    /**
     * 根据订单ID查询缴费信息
     *
     * @param request
     * @param response
     * @param orderId
     * @Author zhanghao
     * @Date 20180927
     */
    @RequestMapping("/checkAccountTotal")
    public void checkAccountTotal(HttpServletRequest request,HttpServletResponse response,Long orderId){
        JSONObject jsonObject = new JSONObject();
        try {
            boolean check = payfeeService.checkAccountTotal(orderId);
            if(check){//总计金额大于1000
                msg = Constants.SCUUESS;
            }else{//总计金额小于1000
                msg = Constants.FAIL;
            }
        } catch (Exception e) {
            log.error("checkAccountTotal方法查询失败,错误信息:{}"+e);
            msg = Constants.RET_FAILED;//执行失败
        }
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }

    /**
     * 根据结算单ID校验财务系统汇总表信息
     *
     * @param request       请求对象
     * @param response      应答对象
     * @param accountId     结算单ID
     * @Author zhanghao
     * @Date 20181031
     */
    @RequestMapping("/checkFinSummaryForAccount")
    public void checkFinSummaryForAccount(HttpServletRequest request,HttpServletResponse response,Long accountId){
        JSONObject jsonObject = new JSONObject();
        try {
            boolean check = payfeeService.checkFinSummaryForAccount(accountId);
            if(check){
                msg = Constants.SCUUESS;
            }else{
                msg = Constants.NULL;
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }
    
    
    /**
     * 
     * 操作人：周鑫
     * 2018年12月27日上午11:55:21
     */
    @RequestMapping("/checkAccountReviewState")
    public void checkAccountReviewState(HttpServletRequest request,HttpServletResponse response,Long accountId){
        JSONObject jsonObject = new JSONObject();
        String reslut;
        try {
            boolean check = payfeeService.checkAccountReviewState(accountId);
            if(check){
                msg = Constants.SCUUESS;
            }else{
                msg = Constants.NULL;
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("msg",msg);
        responseSendMsg(response,jsonObject.toString());
    }
    
    
    @RequestMapping("/historyAfterCharge")
    public void historyAfterCharge(HttpServletRequest request,HttpServletResponse response,Long orderId){
        JSONObject jsonObject = new JSONObject();
        Double reslut = 0.0;
        try {
             reslut = payfeeService.getHistoryAfterCharge(orderId);
             msg = Constants.SCUUESS;
        } catch (Exception e) {
            e.printStackTrace();
            msg = Constants.FAIL;
        }
        jsonObject.accumulate("msg",msg);
        jsonObject.accumulate("result",reslut);
        responseSendMsg(response,jsonObject.toString());
    }
    
    /**
	 * 
	 * @Description: 获取验证码   
	 * @param: @param request
	 * @param: @param response
	 * @param: @param mobile
	 * @param: @throws Exception      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月16日
	 * @throws
	 */
	@RequestMapping(value = "/sendVerifyCode", method = { RequestMethod.POST,RequestMethod.GET })
	public void sendVerifyCode(HttpServletRequest request,HttpServletResponse response, String mobile)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobiles", mobile);
			map.put("templet", "other_33"); // 模板码
			map.put("channel", "yzm"); // 通道码
			map.put("rate", "1");
			map.put("system", "order");
			map.put("defined", 1);
			map.put("params", new ArrayList<String>());
			net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(map);
			String reStr = sMSServiceDubbo.send(object.toString());
			net.sf.json.JSONObject js = net.sf.json.JSONObject.fromObject(reStr);
			String re = js.getString("result");
			if (re.equals("success")) {
				msg = Constants.SCUUESS;
			} else {
				msg = Constants.FAIL;
			}
		} catch (Exception e) {
			log.error("sendVerifyCode:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
    
	
	/**
	 * 
	 * @Description: 验证验证码   
	 * @param request
	 * @param response
	 * @param mobile
	 * @param code
	 * @throws Exception      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月16日
	 * @throws
	 */
	@RequestMapping(value = "/verifyCode", method = { RequestMethod.POST,RequestMethod.GET })
	public void verifyCode(HttpServletRequest request,HttpServletResponse response, String mobile,String code)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", mobile);
			map.put("code", code);
			map.put("system", "order");
			map.put("templet", "other_33");
			map.put("defined", "1");
			net.sf.json.JSONObject object = net.sf.json.JSONObject.fromObject(map);
			String data = sMSServiceDubbo.verify(object.toString());
			net.sf.json.JSONObject js = net.sf.json.JSONObject.fromObject(data);
			String re = js.getString("result");
			if (re.equals("success")) {
				msg = Constants.SCUUESS;
			} else {
				msg = Constants.FAIL;
			}
		} catch (Exception e) {
			log.error("verifyCode:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	/**
	 * 
	 * @Description: 卡扣费
	 * @param request
	 * @param response
	 * @param mobile
	 * @param code
	 * @throws Exception      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	@RequestMapping(value = "/cardDeduction", method = { RequestMethod.POST,RequestMethod.GET })
	public void cardDeduction(HttpServletRequest request,HttpServletResponse response,String orderId,String cardNumb,String feeSum,String accountId,String orderCode,String mobile)throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			LoginCookieInfo lk = CookieReaderUtil.cookieReader(request);
			map.put("orderId", orderId);
			map.put("cardNumb", cardNumb);
			map.put("feeSum", feeSum);
			map.put("accountId", accountId);
			map.put("orderCode", orderCode);
			map.put("agentUser", lk.getId());
			map.put("mobile", mobile);
			
			JSONObject fromObject = JSONObject.fromObject(map);
			
			String erpCardPay = cardPayDubboService.erpCardPay(fromObject.toString());
			
			JSONObject resultJson = JSONObject.fromObject(erpCardPay);
			String resultCode = resultJson.getString("code");
			if ("0".equals(resultCode)) {
				//修改结算单
				payfeeService.updateAccount(Long.parseLong(accountId));
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("cardDeduction:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
}
