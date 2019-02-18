package com.emotte.order.warehouse.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.warehouse.model.Parcel;

public interface ParcelService {

	public Parcel loadParcel(Long id);

	public List<Parcel> queryParcel(Parcel parcel, Page page);
	
	public List<Parcel> mergeQueryParcel(Parcel parcel, Page page);
	
	public List<Parcel> queryDeliveryReturn(Parcel parcel, Page page);
	
	public List<Parcel> queryNewOrderCount(Parcel parcel, Page page);
	
	public List<Parcel> queryTobeshippedOrderCount(Parcel parcel, Page page);
	
	public List<Parcel> queryAlreadyshippedOrderCount(Parcel parcel, Page page);
	
	public List<Parcel> queryParcelDetails(Parcel parcel, Page page);
	
	public List<Parcel> queryParcelLogistics(Parcel parcel, Page page);

	public String insertOrderParcel(Long orderId);
	
	public void splitParcel(Parcel parcel,Long createBy);

	public void updateParcel(Parcel parcel , Long updateBy);
	
	public void updateDelivergoods(Parcel parcel , Long updateBy);

	//导出前查询
	public List<Parcel> queryExcelList(Parcel parcel);
	//导出
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response,List<Parcel> list,Long updateBy);
	//导入
	public boolean importCustomExcel(HSSFWorkbook excel,Long updateBy);
}