package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.ItemDetailFa;
import com.emotte.order.order.model.ItemFaQuery;

  @Component("reItemDetailFaMapper")
public interface ReItemDetailFaMapper extends ReBaseMapper{
    
	public ItemDetailFa loadItemDetailFa(Long id);
	
	public List<ItemDetailFa> queryItemDetailFa(ItemDetailFa itemDetailFa);
	
	public Integer countItemDetailFa(ItemDetailFa itemDetailFa);
	
	public List<ItemFaQuery> queryItemQuery(ItemFaQuery itemFaQuery);

	public Integer countOrder(ItemFaQuery itemFaQuery);

	public List<ItemFaQuery> queryOrderList(ItemFaQuery itemFaQuery);


    }