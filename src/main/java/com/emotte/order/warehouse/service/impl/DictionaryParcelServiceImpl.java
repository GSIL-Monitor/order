package com.emotte.order.warehouse.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.warehouse.mapper.reader.ReDictionaryParcelMapper;
import com.emotte.order.warehouse.model.Dictionary;
import com.emotte.order.warehouse.service.DictionaryParcelService;

@Service("dictionaryParcelService")
@Transactional
public class DictionaryParcelServiceImpl implements DictionaryParcelService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReDictionaryParcelMapper reDictionaryParcelMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryDictionary(Dictionary dictionary, Page page) {
		return this.reDictionaryParcelMapper.queryDictionary(dictionary);
	}

}
