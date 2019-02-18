package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.PersonaLevel;

import org.wildhorse.server.core.model.Page;

public interface ItemDetailServerService{
    
	public ItemDetailServer loadItemDetailServer(Long id);
	
	public List<ItemDetailServer> queryItemDetailServer(ItemDetailServer itemDetailServer,Page page);
	
	public void insertItemDetailServer(ItemDetailServer itemDetailServer);
	
	public String insertItemDetailServerForeign(Order order);
	
    public void updateItemDetailServer(ItemDetailServer itemDetailServer);
    
    public ItemDetailServer loadOrderServerLined(Long id);
	
	public List<ItemDetailServer> queryOrderServerLined(ItemDetailServer itemDetailServer);
	
	public void insertOrderServerLined(ItemDetailServer itemDetailServer);
	
    public void updateOrderServerLined(ItemDetailServer itemDetailServer);
    
    public StringBuffer queryWorkTypeLevel(Dictionary dictionary);
    public StringBuffer queryWorkTypeAll(Dictionary dictionary);
	public void insertServerOneSlice(Long orderId, Long createBy);

	public void updateItemDetailServerType(ItemDetailServer itemDetailServer);

	public List<ItemDetailServer> queryPersonSchedule(ItemDetailServer itemDetailServer);
	public int checkNumStock(Dictionary dictionary);//查询服务人员库存数量
	
	public List<ItemDetailServer> loadPersonServerLined(ItemDetailServer itemDetailServers);//查询服务人员排期

	public com.emotte.order.order.model.OrderBaseModel matchingPersonnel(OrderBaseModel orderBaseModel);

	public ItemDetailServer showTime(Long id);
	
	public String deleteSchedule(Long orId);

	public List<ItemDetailServer> queryPersonLineTime(ItemDetailServer itemDetailServers);
	
	//查询订单工种
	public com.emotte.order.order.model.OrderBaseModel selectOrderWorkType(OrderBaseModel orderBaseModel);
	
	//查询服务人员等级
	public List<PersonaLevel> selectPersonnelLevel();

	public List<BaseCity> queryCitys(BaseCity baseCity);
	
    }