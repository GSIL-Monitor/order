package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.Dictionary;
import org.wildhorse.server.core.model.Page;

public interface DictionaryService{
    
	public Dictionary loadDictionary(Long id);
	
	public List<Dictionary> queryDictionary(Dictionary dictionary,Page page);
	
	public void insertDictionary(Dictionary dictionary);
	
    public void updateDictionary(Dictionary dictionary);
    
    public String checkedDictionary(String dictJson);

    }