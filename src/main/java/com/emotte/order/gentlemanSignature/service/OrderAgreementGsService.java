package com.emotte.order.gentlemanSignature.service;

import java.util.List;
import java.util.Map;

import com.emotte.gentlemanSignature.core.model.LocalSign_fileType1or2;
import com.emotte.order.gentlemanSignature.model.AgreementGs;
import com.emotte.order.order.model.Agreement;

import net.sf.json.JSONObject;

public interface OrderAgreementGsService {

	public int insertOrderAgreementGs(AgreementGs agreementGs);
	
	public int updateOrderAgreementGs(AgreementGs agreementGs);
	
	public List<AgreementGs> queryOrderAgreementGs(AgreementGs agreementGs);
	
	public Map<String,Object> contractRemark(Agreement agreement,Long role,String signatories) throws Exception;
	
	public JSONObject signature(LocalSign_fileType1or2 localSign,AgreementGs agreementGs,String base64ImgStr,Agreement agreement,Long role)throws Exception;
}
