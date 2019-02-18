package com.emotte.order.order.mapper.writer;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.model.AgreementAssistant;

import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrAgreementMapper")
public interface WrAgreementMapper extends WrBaseMapper {

	public void insertAgreement(Agreement agreement);

	public int updateAgreement(Agreement agreement);

	public void checkAgreement(Agreement agreement);
	
	public int updateAgreementTime(Agreement agreement);
	
	public int updateAgreementTimeNew(Agreement agreement);
	
	public void insertAgreementAssistant(AgreementAssistant agreement);
	
	public int updateAgreementAssistant(AgreementAssistant agreement);
	
	public int updateAgreementState(Agreement agreement);
	
	public int updateAgreementType(Agreement agreement);

	/**
	 * 根据订单ID，修改单次合同审核状态
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
	 */
	void changeOnceAgreementAuditStatus(Agreement agreement);

	/**
	 * 新增单次订单合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190215
     */
	void insertOnceAgreement(Agreement agreement);
}