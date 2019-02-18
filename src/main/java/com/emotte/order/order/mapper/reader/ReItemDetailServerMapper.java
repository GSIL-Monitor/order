package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.PersonaLevel;
import com.emotte.order.order.model.PersonnelSchedule;

  @Component("reItemDetailServerMapper")
public interface ReItemDetailServerMapper extends ReBaseMapper{
    
	public ItemDetailServer showTime(Long id);
	
	public ItemDetailServer loadItemDetailServer(Long id);
	public ItemDetailServer loadItemDetailServerByOrderId(Long orderId);
	
	public List<ItemDetailServer> queryItemDetailServer(ItemDetailServer itemDetailServer);
	
	public Integer countItemDetailServer(ItemDetailServer itemDetailServer);
	
	public ItemDetailServer loadOrderServerLined(Long id);
	
	public List<ItemDetailServer> queryOrderServerLined(ItemDetailServer itemDetailServer);
	
	public List<Dictionary> queryWorkTypeLevel(Dictionary dictionary);
	public List<Dictionary> queryWorkTypeAll(Dictionary dictionary);
	public List<ItemDetailServer> queryPersonSchedule(ItemDetailServer itemDetailServer);
	public int checkNumStock(Dictionary dictionary);//查询服务人员库存数量
	
	public List<ItemDetailServer> loadPersonServerLined(ItemDetailServer itemDetailServers);//查询服务人员排期
	//查询服务人员排期
	public List<ItemDetailServer> queryEmpSchedule(ItemDetailServer itd);
	
	public List<ItemDetailServer> queryOrderDetailServer(ItemDetailServer itemDetailServer);//查询订单上户服务人员

	public List<ItemDetailServer> queryPersonLineTime(ItemDetailServer itemDetailServers);
	
	//查询订单工种
	public com.emotte.order.order.model.OrderBaseModel selectOrderWorkType(OrderBaseModel orderBaseModel);

	public List<PersonaLevel> selectPersonnelLevel();

	public List<BaseCity> queryCitys(BaseCity baseCity);

	public List<PersonnelSchedule> selectPersonnelSchedules(Long orderId);
	
}