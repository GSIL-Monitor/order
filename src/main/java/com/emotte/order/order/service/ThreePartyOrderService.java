package com.emotte.order.order.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.ThreeOrder;

public interface ThreePartyOrderService {

	/*
	 * 查询三方订单（对内）
	 */
	public  List<ThreeOrder> queryPartyOrder(ThreeOrder order, Page page);
	/*
	 * 查询三方订单（对内）单条数据
	 */
	public List<ThreeOrder> loadPartyOrderId(ThreeOrder order);
	
	/*
	 * 查询三方订单（对内）负责部门
	 */
	public List<ThreeOrder> queryPartyOrderDeptName(ThreeOrder order);  
	/*
	 * 导出（三方订单）按照条件
	 */
	public List<ThreeOrder> queryPartyOrderList(ThreeOrder order);
	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order,
			List<ThreeOrder> dataList, Long loginBy);
}
