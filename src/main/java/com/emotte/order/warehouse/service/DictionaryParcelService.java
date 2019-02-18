package com.emotte.order.warehouse.service;

import java.util.List;

import org.wildhorse.server.core.model.Page;

import com.emotte.order.warehouse.model.Dictionary;

public interface DictionaryParcelService {

	public List<Dictionary> queryDictionary(Dictionary dictionary, Page page);


}