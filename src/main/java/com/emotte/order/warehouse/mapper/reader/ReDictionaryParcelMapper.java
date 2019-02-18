package com.emotte.order.warehouse.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.warehouse.model.Dictionary;

@Component("reDictionaryParcelMapper")
public interface ReDictionaryParcelMapper extends ReBaseMapper {

	public Dictionary loadDictionary(Long id);

	public List<Dictionary> queryDictionary(Dictionary dictionary);

	public Integer countDictionary(Dictionary dictionary);

}