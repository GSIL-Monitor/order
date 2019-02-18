package com.emotte.order.order.service;

import java.util.List;
import com.emotte.order.order.model.AgreementFile;

public interface AgreementFileService {

	public AgreementFile loadAgreementFile(Long id);

	public List<AgreementFile> queryAgreementFile(AgreementFile agreementFile);

	public void insertAgreementFile(AgreementFile agreementFile);

	public void updateAgreementFile(AgreementFile agreementFile);

	/**
	 * 单次服务订单上传合同扫描件
	 *
	 * @param agreementFile
	 * @Author zhanghao
	 * @Date 20190115
     */
	void saveOnceServerAgreementCopy(AgreementFile agreementFile);
}