package com.emotte.order.warehouse.service;

import java.util.List;
import com.emotte.order.warehouse.model.PackageItemRef;
import org.wildhorse.server.core.model.Page;

public interface PackageItemRefService {

	public PackageItemRef loadPackageItemRef(Long id);

	public List<PackageItemRef> queryPackageItemRef(PackageItemRef packageItemRef, Page page);

	public void insertPackageItemRef(PackageItemRef packageItemRef);

	public void updatePackageItemRef(PackageItemRef packageItemRef);

}