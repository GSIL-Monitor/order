package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.ItemDetailFa;
import com.emotte.order.order.model.ItemFaQuery;

import org.wildhorse.server.core.model.Page;

public interface ItemDetailFaService{
    
	public ItemDetailFa loadItemDetailFa(Long id);
	
	public List<ItemDetailFa> queryItemDetailFa(ItemDetailFa itemDetailFa,Page page);
	
	public void insertItemDetailFa(ItemDetailFa itemDetailFa);
	
    public void updateItemDetailFa(ItemDetailFa itemDetailFa);

	public List<ItemFaQuery> queryItemQuery(ItemFaQuery itemFaQuery, Page page);

	public List<ItemFaQuery> queryItemQuerylist(ItemFaQuery itemFaQuery);

    }