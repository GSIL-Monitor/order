package com.emotte.order.warehouse.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.warehouse.model.PackageItemRef;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrPackageItemRefMapper")
public interface WrPackageItemRefMapper extends WrBaseMapper {

	public void insertPackageItemRef(PackageItemRef packageItemRef);

	public int updatePackageItemRef(PackageItemRef packageItemRef);
	
	public int splitParcelItemRef(PackageItemRef packageItemRef);

}