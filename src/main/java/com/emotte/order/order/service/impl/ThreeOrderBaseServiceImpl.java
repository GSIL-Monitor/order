package com.emotte.order.order.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.reader.ReThreeOrderBaseMapper;
import com.emotte.order.order.mapper.writer.WrOrderBaseMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.ThreeOrderBaseService;

@Service("threeOrderBaseService")
@Transactional

public class ThreeOrderBaseServiceImpl implements ThreeOrderBaseService{
	Logger log=Logger.getLogger(this.getClass()); 
	
	@Resource
	private ReThreeOrderBaseMapper reThreeOrderBaseMapper;
	@Resource
	private WrOrderBaseMapper wrOrderBaseMapper;
	@Resource
	private WrOrderMapper wrOrderMapper;
	@Resource
	private OrderService orderService;
	

	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public StringBuffer queryDictionaryMapper(Long pid,String types){
		StringBuffer jsonstr = new StringBuffer("[{");
		Dictionary dictionary = new Dictionary();
		dictionary.setPid(pid);
		dictionary.setTypes(types);
		dictionary.setValid(1);
		List<Dictionary> list = this.reThreeOrderBaseMapper.queryDictionaryMapper(dictionary);
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Dictionary dict = list.get(i);
				if(i>0){
					jsonstr.append(",{");
				}
				jsonstr.append("'key':'" +dict.getDkey()+"'");
				jsonstr.append(",");
				jsonstr.append("'value':'" +dict.getDvalue()+"'");
				jsonstr.append("}");
			}
		}
		jsonstr.append("]");
		return jsonstr;
	}


	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserMapper(OrderUser orderUser,Page page){
		if(page.needQueryPading()){
			page.setTotalRecord(reThreeOrderBaseMapper.countUserMapper(orderUser));
		}
		orderUser.setBeginRow(page.getFilterRecord().toString());
		orderUser.setPageSize(page.getPageCount().toString());
		List<OrderUser> listUser = this.reThreeOrderBaseMapper.queryUserMapper(orderUser);
		return listUser;
		
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser){
		List<OrderUser> listUser = null;
		// 如果没传ID，直接查订单的收货人信息
		if((null==orderUser.getId()||orderUser.getId()==0) && null==orderUser.getUserId()&& null!=orderUser.getOrderId()){
			listUser = this.reThreeOrderBaseMapper.queryUserAddressOrder(orderUser);
		}else{
			listUser = this.reThreeOrderBaseMapper.queryUserAddressMapper(orderUser);
		}
		return listUser;
		
	}
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser){
		List<OrderUser> listUser = this.reThreeOrderBaseMapper.queryUserAddressByMobile(orderUser);
		List<OrderUser> listUser2 = new ArrayList<OrderUser>();
		for (OrderUser orderUser2 : listUser) {
			Long unicode = orderUser2.getUnicode();
			orderUser2.setUnicodes(unicode.toString());
			listUser2.add(orderUser2);
		}
		orderUser.setUsValid(1);
		orderUser.setAdValid(1);
		return listUser2;
		
	}
	
	@Override
	public void insertUser(OrderUser orderUser){
		this.wrOrderBaseMapper.insertOrderUser(orderUser);
		orderUser.setCreateBy(1L);
		orderUser.setVersion(0L);
		orderUser.setValid(1);
	}
	
	@Override
	public void insertUserAddress(OrderUser orderUser){
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderUser.setCreateTime(dFormat.format(Calendar.getInstance().getTime()));
		orderUser.setVersion(1L);
		orderUser.setValid(1);
		orderUser.setIsDefault(2);
		orderUser.setCreateBy(1L);
		orderUser.setUpdateTime(orderUser.getCreateTime());
//		Map map = LngAndLatUtil.getLngAndLat(orderUser.getAddressDetail());
//		orderUser.setLongitude(map.get("longitude").toString());
//		orderUser.setLatitude(map.get("latitude").toString());
		this.wrOrderBaseMapper.insertOrderUserAddress(orderUser);
	}
	
	@Override
	public void updateUserAddress(OrderUser orderUser){
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderUser.setUpdateBy(1L);
		orderUser.setUpdateTime(dFormat.format(Calendar.getInstance().getTime()));
		if(null!=orderUser.getId() && orderUser.getId()!=0){
			this.wrOrderBaseMapper.updateOrderUserAddress(orderUser);
		}
		// 商器订单地址修改时，需要修改订单里的地址信息
		if(orderUser.getOrderId() !=null && orderUser.getOrderId()>0L){
			Order order = new Order();
			order.setId(orderUser.getOrderId());
			order.setReceiverName(orderUser.getContactName());
			order.setReceiverMobile(orderUser.getContactPhone());
			order.setReceiverProvince(orderUser.getProvince());
			order.setReceiverCity(orderUser.getCity());
			order.setReceiverArea(orderUser.getCountry());
			order.setReceiverAddress(orderUser.getAddressDetail());
			orderService.updateOrder2(order);
		}
	}
	
	@Override
	public List<OrderBaseModel> queryBaseCity(String code,int length){
		List<OrderBaseModel> list = null;
		OrderBaseModel baseModel = new OrderBaseModel();
		baseModel.setAddress(code);
		baseModel.setAge(length);
		list = this.reThreeOrderBaseMapper.queryBaseCity(baseModel);
		return list;
	}
	
	@Override
	public List<OrderBaseModel> queryOrderBasicServer(Long id){
		List<OrderBaseModel> list = null;
		list = this.reThreeOrderBaseMapper.queryOrderBasicServer(id);
		return list;
	}
	
	@Override
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel,Page page){
		List<OrderBaseModel> list = null;
		if(orderBaseModel ==null) orderBaseModel = new OrderBaseModel();
		if(orderBaseModel.getType()!=null && orderBaseModel.getType()==1){
			// 自动匹配
			list = this.reThreeOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==2){
			// 手动匹配条件查询
			if(page.needQueryPading()){
				page.setTotalRecord(this.reThreeOrderBaseMapper.countOrderNeedServer(orderBaseModel));
			}
			orderBaseModel.setBeginRow(page.getFilterRecord().toString());
			orderBaseModel.setPageSize(page.getPageCount().toString());
			orderBaseModel.setFlagPage(0);// 表示分页查询
			list = this.reThreeOrderBaseMapper.queryOrderNeedServer(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==3){
			// 查询单个明细
			Long id = orderBaseModel.getId();
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setId(id);
			orderBaseModel.setValid(1);
			orderBaseModel.setFlagPage(1); // 表示不分页查询
			list = this.reThreeOrderBaseMapper.queryOrderNeedServer(orderBaseModel);
		}else if(orderBaseModel.getType()!=null && orderBaseModel.getType()==4){
			// 查询选定的两条
			String ids = orderBaseModel.getIds();
			orderBaseModel = new OrderBaseModel();
			orderBaseModel.setIdsAry(ids.split(","));
			orderBaseModel.setValid(1);
			list = this.reThreeOrderBaseMapper.queryOrderNeeds(orderBaseModel);
		}
		return list;
	}
	

}
