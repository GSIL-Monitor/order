package com.emotte.order.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.emotte.order.order.model.Agreement;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import com.emotte.order.order.mapper.reader.ReAgreementFileMapper;
import com.emotte.order.order.mapper.writer.WrAgreementFileMapper;
import com.emotte.order.order.model.AgreementFile;
import com.emotte.order.order.service.AgreementFileService;

@Service("agreementFileService")
@Transactional
public class AgreementFileServiceImpl implements AgreementFileService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReAgreementFileMapper reAgreementFileMapper;

	@Resource
	private WrAgreementFileMapper wrAgreementFileMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public AgreementFile loadAgreementFile(Long id) {
		return this.reAgreementFileMapper.loadAgreementFile(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<AgreementFile> queryAgreementFile(AgreementFile agreementFile) {
		return this.reAgreementFileMapper.queryAgreementFile(agreementFile);
	}

	@Override
	public void insertAgreementFile(AgreementFile agreementFile) {
		List<AgreementFile> list = agreementFile.getList();
		try {
			if(list != null && list.size() > 0){
				for (AgreementFile a : list) {
					AgreementFile af = this.reAgreementFileMapper.queryAgreementFileByRankNumber(a);
					if(af != null){
						a.setId(af.getId());
						a.setUpdateBy(agreementFile.getCreateBy());
						this.wrAgreementFileMapper.updateAgreementFile(a);
					}else{
						a.setCreateBy(agreementFile.getCreateBy());
						this.wrAgreementFileMapper.insertAgreementFile(a);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateAgreementFile(AgreementFile agreementFile) {
		int returnValue;
		try {
			returnValue = this.wrAgreementFileMapper.updateAgreementFile(agreementFile);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateAgreementFile:" + e);
			throw new BaseException(e);
		}
	}

	/**
	 * 单次服务订单上传合同扫描件
	 *
	 * @param agreementFile
	 * @Author zhanghao
	 * @Date 20190115
	 */
	@Override
	public void saveOnceServerAgreementCopy(AgreementFile agreementFile) {
		//先将旧的扫描件置为无效
		Agreement agreement = new Agreement();
		agreement.setId(agreementFile.getAgreementId());
		agreement.setUpdateBy(agreementFile.getCreateBy());
		agreement.setValid(2);
		wrAgreementFileMapper.deleteOldOnceServerAgreementCopy(agreement);
		//保存新的扫描件
		wrAgreementFileMapper.insertAgreementFile(agreementFile);
	}
}
