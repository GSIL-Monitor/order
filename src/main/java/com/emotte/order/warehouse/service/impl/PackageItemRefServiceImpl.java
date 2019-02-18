package com.emotte.order.warehouse.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.warehouse.model.PackageItemRef;
import com.emotte.order.warehouse.mapper.reader.RePackageItemRefMapper;
import com.emotte.order.warehouse.mapper.writer.WrPackageItemRefMapper;
import com.emotte.order.warehouse.service.PackageItemRefService;

@Service("packageItemRefService")
@Transactional
public class PackageItemRefServiceImpl implements PackageItemRefService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private RePackageItemRefMapper rePackageItemRefMapper;

	@Resource
	private WrPackageItemRefMapper wrPackageItemRefMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public PackageItemRef loadPackageItemRef(Long id) {
		return this.rePackageItemRefMapper.loadPackageItemRef(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<PackageItemRef> queryPackageItemRef(PackageItemRef packageItemRef, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(rePackageItemRefMapper.countPackageItemRef(packageItemRef));
		}
		packageItemRef.setBeginRow(page.getFilterRecord().toString());
		packageItemRef.setPageSize(page.getPageCount().toString());
		return this.rePackageItemRefMapper.queryPackageItemRef(packageItemRef);
	}

	@Override
	public void insertPackageItemRef(PackageItemRef packageItemRef) {
		this.wrPackageItemRefMapper.insertPackageItemRef(packageItemRef);
	}
	/**
	 * 多余包裹关联商品,包裹id变合并的id
	 * @author 王世博
	 */
	@Override
	public void updatePackageItemRef(PackageItemRef packageItemRef) {
		int returnValue;
		try {
			returnValue = this.wrPackageItemRefMapper.updatePackageItemRef(packageItemRef);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updatePackageItemRef:" + e);
			throw new BaseException(e);
		}
	}

}
