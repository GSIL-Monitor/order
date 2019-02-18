package com.emotte.order.order.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.mapper.reader.ReDictionaryMapper;
import com.emotte.order.order.mapper.writer.WrDictionaryMapper;
import com.emotte.order.order.service.DictionaryService;

@Service("dictionaryService")
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReDictionaryMapper reDictionaryMapper;

	@Resource
	private WrDictionaryMapper wrDictionaryMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Dictionary loadDictionary(Long id) {
		return this.reDictionaryMapper.loadDictionary(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Dictionary> queryDictionary(Dictionary dictionary, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reDictionaryMapper.countDictionary(dictionary));
		}
		dictionary.setBeginRow(page.getFilterRecord().toString());
		dictionary.setPageSize(page.getPageCount().toString());
		return this.reDictionaryMapper.queryDictionary(dictionary);
	}

	@Override
	public void insertDictionary(Dictionary dictionary) {
		this.wrDictionaryMapper.insertDictionary(dictionary);
	}

	@Override
	public void updateDictionary(Dictionary dictionary) {
		int returnValue;
		try {
			returnValue = this.wrDictionaryMapper.updateDictionary(dictionary);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateDictionary:" + e);
			throw new BaseException(e);
		}
	}
	
	@Override
	public String checkedDictionary(String dicJson){
		String result = "" ;
		Dictionary dict = new Dictionary();
		List<Dictionary> list = this.reDictionaryMapper.queryDictionary(dict);
		return result;
	}

}
