package com.emotte.order.warehouse.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.warehouse.model.Parcel;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrParcelMapper")
public interface WrParcelMapper extends WrBaseMapper {

	public void insertParcel(Parcel parcel);

	public int updateParcel(Parcel parcel);

	public int updateParcelLogistion(Parcel parcel);
}