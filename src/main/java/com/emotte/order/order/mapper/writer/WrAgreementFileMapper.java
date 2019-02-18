package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.Agreement;
import org.springframework.stereotype.Component;
import com.emotte.order.order.model.AgreementFile;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrAgreementFileMapper")
public interface WrAgreementFileMapper extends WrBaseMapper {

	public void insertAgreementFile(AgreementFile agreementFile);

	public int updateAgreementFile(AgreementFile agreementFile);

	/**
	 * 删除文件
	 *
	 * @param agreement
	 * @Author zhanhao
	 * @Date 20190114
     */
	void deleteOldOnceServerAgreementCopy(Agreement agreement);
}