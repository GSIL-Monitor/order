package com.emotte.order.warehouse.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.warehouse.model.Order;
import com.emotte.order.warehouse.model.Parcel;

@Component("reParcelMapper")
public interface ReParcelMapper extends ReBaseMapper {

	public Parcel loadParcel(Long id);

	public List<Parcel> queryParcellistPage(Parcel parcel);
	
	public List<Parcel> queryDeliveryReturn(Parcel parcel);
	
	public List<Parcel> queryNewOrderCount(Parcel parcel);
	
	public List<Parcel> queryTobeshippedOrderCount(Parcel parcel);
	
	public List<Parcel> queryAlreadyshippedOrderCount(Parcel parcel);
	
	public List<Parcel> mergeQueryParcel(Parcel parcel);

	public Integer countParcel(Parcel parcel);
	
	public List<Parcel> queryParcelDetails(Parcel parcel);
	
	public List<Parcel> queryParcelLogistics(Parcel parcel);
	
	public List<Parcel> queryExcelList(Parcel parcel);
	
	public List<Parcel> queryPackageNumberExistence(Parcel parcel);
	
	public List<Parcel> queryParcelJudgment(Parcel parcel);
	
	public List<Order> queryOrderItem(Order order);

}