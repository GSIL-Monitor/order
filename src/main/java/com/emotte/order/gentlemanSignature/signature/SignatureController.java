package com.emotte.order.gentlemanSignature.signature;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;

import com.emotte.gentlemanSignature.core.controller.LocalSignController;
import com.emotte.gentlemanSignature.core.controller.SignApplyHashController;
import com.emotte.gentlemanSignature.core.extra.AbstractSignatory;
import com.emotte.gentlemanSignature.core.model.GSResult;
import com.emotte.gentlemanSignature.core.model.Hash;

import com.emotte.gentlemanSignature.core.model.LocalSign_Signatory;
import com.emotte.gentlemanSignature.core.model.LocalSign_fileType1or2;
import com.emotte.gentlemanSignature.http.HttpClient;
import com.emotte.gentlemanSignature.properties.GSPropertiesUtil;
import com.emotte.order.authorg.service.OrgService;
import com.emotte.order.gentlemanSignature.Util;
import com.emotte.order.gentlemanSignature.model.AgreementGs;
import com.emotte.order.gentlemanSignature.service.OrderAgreementGsService;
import com.emotte.order.gentlemanSignature.service.UpdateContractStatusService;

import com.emotte.order.order.mapper.reader.ReAgreementMapper;
import com.emotte.order.order.mapper.writer.WrAgreementMapper;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.JsonUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
@Controller
@RequestMapping("signature")
public class SignatureController extends BaseController{

	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private SignApplyHashController sAHC;
	@Resource
	private LocalSignController LocalSignController;
	@Resource
	private UpdateContractStatusService updateContractStatusService;
	@Resource
	private ReAgreementMapper reAgreementMapper;
	@Resource
	private WrAgreementMapper wrAgreementMapper;
	@Resource
	private OrgService orgService;
	
	@Resource
	private AgreementService agreementService;
	
	@Resource
	private OrderAgreementGsService orderAgreementGsService;
	/**
	 * 
	 * @Title: signatureHash
	 * @Description: 调用   第三方接口的   哈希保全   接口
	 * @author dengyouqian@95081.com
	 * @date 2018年5月23日
	 * @param request
	 * @param response
	 * @param hash
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "/signatureHash", method = {RequestMethod.POST, RequestMethod.GET }) 
	public void signatureHash(HttpServletRequest request, HttpServletResponse response,Hash hash) {
		log.info("SignatureController------signatureHash开始执行");
		JSONObject jsonObject = new JSONObject();
		GSResult gSResult=null;
		String info="";
		try {
//			String realPath = request.getSession().getServletContext().getRealPath("/");// 项目根路径
			Agreement agreement = new Agreement();
			agreement.setId(Long.valueOf(hash.getContractName()));
			List<Agreement> queryAgreement = agreementService.queryAgreement(agreement, page);
			if(queryAgreement !=null && queryAgreement.size()>0){
				Integer elecOtherState = queryAgreement.get(0).getElecOtherState();
				Integer agreementState = queryAgreement.get(0).getAgreementState();
				if(elecOtherState !=null && 3 ==elecOtherState 
						&& agreementState !=null && 6== agreementState){
					String str = queryAgreement.get(0).getNormalContactFile();
					
					//hash.setHashData(MD5HashCodeUtil.md5HashCode(str));
					hash.setHashData(str);
					gSResult = sAHC.process(sAHC.createCommonParam(hash));
					log.info("SignatureController------gSResult"+gSResult.toString());
					 if(gSResult.isSuccess()){
						 Object data = gSResult.getData();
							if (null != data) {
								MorphDynaBean MorphDynaBean = (MorphDynaBean) data;
								if (null != MorphDynaBean && null != MorphDynaBean.get("applyNo")) {
									 msg = Constants.SCUUESS;
									 jsonObject.accumulate("applyNo", MorphDynaBean.get("applyNo"));
								} 
							} 
					  }else{
						  info = "调用第三方接口失败！";
						  msg = Constants.FAIL;
					  }
				}else{
					info = "合同尚未三方签约！";
					 msg = Constants.FAIL;
				}
			}
			jsonObject.accumulate("info", info);
			jsonObject.accumulate("msg", msg);
		} catch (Exception e) {
			  log.error("signatureHash:" + e);
			  e.printStackTrace();
			  info = "签约失败了";
			  msg = Constants.FAIL;
			  jsonObject.accumulate("msg", msg);
			  jsonObject.accumulate("info", info);
		}
		log.info("SignatureController------signatureHash执行结束");
		
   	    responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * contractName 合同Id
	 * @param localSign
	 * @return
	 */
	@RequestMapping("signatureProcess")
	public void signatureProcess(LocalSign_fileType1or2 localSign,HttpServletRequest request,HttpServletResponse response,Long role) {
		log.info("SignatureController------signatureProcess开始执行");
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		GSResult gsResult = null;
		JSONObject result = new JSONObject();
		result.accumulate("success", false);
		try {
			if(null!=localSign.getContractName()) {
	
				Agreement agreement = new Agreement();
				agreement.setId(Long.valueOf(localSign.getContractName()));
				List<Agreement> agreements = reAgreementMapper.queryAgreement(agreement);
				
				if(null!=agreements && agreements.size()!=0) {
					String fileName = agreements.get(0).getNormalContactFile();
					localSign.setFileData(fileName);
				
					String signatories =localSign.getSignatories();
					if(null!=signatories || !"".equals(signatories)) {
						
						List<LocalSign_Signatory> signatorys_ =JsonUtil.jsonToList(signatories, LocalSign_Signatory.class);
						
						AbstractSignatory as = new AbstractSignatory() {
							@Override
							public List<LocalSign_Signatory> signatories(List<LocalSign_Signatory> signatorys) throws Exception {
							
								return signatorys;
							}
						};
						localSign.setSignatories(as.loadToJsonArray(signatorys_));
					}
					
					gsResult = LocalSignController.process(LocalSignController.createCommonParam(localSign));
					
					if (null != gsResult && gsResult.isSuccess()) {
						Object data = gsResult.getData();
						if (null != data) {
							MorphDynaBean MorphDynaBean = (MorphDynaBean) data;
							if (null != MorphDynaBean && null != MorphDynaBean.get("signData")) {
								String IPFromSig = GSPropertiesUtil.getProperties("IPFromSig");
								String filePathFromSig = GSPropertiesUtil.getProperties("filePathFromSig");

								final String tempFilePath = MorphDynaBean.get("signData").toString();
								
								String copyFileUsingFileChannel = GSPropertiesUtil.getProperties("copyFileUsingFileChannel");
								
								String destPathEnd = Util.Instance.getPath()+"/"+agreements.get(0).getAgreementCode()+".pdf";
								final String destPath =  filePathFromSig+destPathEnd;
								
								HttpClient.doPost(copyFileUsingFileChannel, new HashMap<String,Object>() {{
									put("sourcePath", tempFilePath);
									put("destPath",destPath);
								}});
								
								agreement.setNormalContactFile(IPFromSig+destPathEnd);
								wrAgreementMapper.updateAgreement(agreement);
								result.accumulate("signData", IPFromSig+destPathEnd);
								result.remove("success");
								result.accumulate("success", true);
							}
						} else {
							throw new Exception("签章数据异常");
						}
					} else {
						throw new Exception("签章未成功");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("SignatureController------signature执行结束");
		responseSendMsg(response, result.toString());
	}
	
	
	 @RequestMapping(value = "/viewPDF", method = RequestMethod.GET)  
	    public void pdfStreamHandeler(HttpServletRequest request, HttpServletResponse response,String filePath) {
		    log.info("SignatureController------viewPDF开始执行");
			log.info("设置跨域开始");
			response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
			response.setHeader("Access-Control-Allow-Credentials", "true");
			if(null!=filePath){
				try {
					if(filePath.indexOf(GSPropertiesUtil.getProperties("IPFromSig"))!=-1) {
						   byte[] bytes =  HttpClient.readBytes(filePath);  
				            response.getOutputStream().write(bytes); 
					}else {
						    File file = new File(filePath);  
				            byte[] data = null;   
				            FileInputStream input = new FileInputStream(file);
				            byte[] bytes =  HttpClient.readBytes(filePath);
				            data = new byte[input.available()];  
				            input.read(data);  
				            response.getOutputStream().write(bytes);  
				            input.close();  
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				log.info("filepath is null");
			}
			
			 log.info("SignatureController------viewPDF执行结束");
	    } 
	 
	 
	 /**
	  * 手机端签字
	  * @param request
	  * @param response
	  */
	 @RequestMapping("signatureByClient")
	 public void signatureByClient(LocalSign_fileType1or2 localSign,Agreement agreement,AgreementGs agreementGs,String base64ImgStr,
			 HttpServletRequest request,HttpServletResponse response,Long role) {
		log.info("SignatureController------signatureByClient开始执行");
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject result = new JSONObject();
		try {
			agreement.setId(agreementGs.getContactId());
			localSign.setContractName(String.valueOf(agreementGs.getContactId()));
			result = orderAgreementGsService.signature(localSign,agreementGs,base64ImgStr,agreement,role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("SignatureController------signatureByClient执行结束");
		responseSendMsg(response, result.toString());
	 }
}
