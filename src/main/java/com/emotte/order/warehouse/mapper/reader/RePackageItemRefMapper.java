package com.emotte.order.warehouse.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.warehouse.model.PackageItemRef;

@Component("rePackageItemRefMapper")
public interface RePackageItemRefMapper extends ReBaseMapper {

	public PackageItemRef loadPackageItemRef(Long id);

	public List<PackageItemRef> queryPackageItemRef(PackageItemRef packageItemRef);

	public Integer countPackageItemRef(PackageItemRef packageItemRef);

}