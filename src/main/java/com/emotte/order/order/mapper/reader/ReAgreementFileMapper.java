package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.AgreementFile;

@Component("reAgreementFileMapper")
public interface ReAgreementFileMapper extends ReBaseMapper {

	public AgreementFile loadAgreementFile(Long id);

	public List<AgreementFile> queryAgreementFile(AgreementFile agreementFile);

	public Integer countAgreementFile(AgreementFile agreementFile);

	public AgreementFile queryAgreementFileByRankNumber(AgreementFile agreementFile);

}