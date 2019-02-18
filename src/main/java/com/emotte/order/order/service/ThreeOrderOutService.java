package com.emotte.order.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.model.ThreeOrderAccounts;
import com.emotte.order.order.model.ThreeOrderAddress;
import com.emotte.order.order.model.ThreeOrderCard;
import com.emotte.order.order.model.ThreeOrderCategory;
import com.emotte.order.order.model.ThreeOrderDictionary;
import com.emotte.order.order.model.ThreeOrderPrice;
import com.emotte.order.order.model.ThreeOrderProduct;
import com.emotte.order.order.model.ThreeOrderUser;

public interface ThreeOrderOutService {
	
	public List<ThreeOrderCategory> queryOrderType(ThreeOrder order);
	
	public List<ThreeOrderDictionary> queryBaseDictionary(ThreeOrder order);
	
	public List<ThreeOrderDictionary> queryBaseCity(ThreeOrder order);

	public List<ThreeOrder> queryOrder(ThreeOrder order, Page page);
	
	public List<ThreeOrder> queryOrderOneDetail(ThreeOrder order);
	
	public List<ThreeOrderAccounts> queryOrderOneAccounts(ThreeOrderAccounts order);
	
	public int saveOrderAccounts(ThreeOrderAccounts accounts);
	
	public int queryOrderUserByMobile(ThreeOrderUser orderUser);
	
	public int saveOrderUser(ThreeOrderUser orderUser);

	public List<ThreeOrderUser> queryOrderUser(ThreeOrderUser order, Page page);

	public List<ThreeOrderCategory> queryCategory(ThreeOrderCategory category);

	public List<ThreeOrderProduct> queryProduct(ThreeOrderProduct product);
	
	public List<ThreeOrderAddress> queryAddress(ThreeOrder order);
	
	public int saveAddress(ThreeOrderAddress order);
	
	public int updateAddress(ThreeOrderAddress address);

	public ThreeOrderUser queryUserDetail(ThreeOrder order);
	
	public ThreeOrderPrice queryProductPrice(ThreeOrder order);
	
	public ThreeOrderAddress queryUserAddressDetail(ThreeOrder order);

	public int insertThreeOrder(ThreeOrder order);

	public ThreeOrderProduct queryProductDetail(ThreeOrder order);

	public int insertThreeOrderItem(ThreeOrder order);

	public int insertThreeOrderItemDetailServer(ThreeOrder order);
	
	public int checkMatch(String[] orderIds);
	
	public int doMatch(String[] orderIds);
	
	public int checkBilling(String[] orderIds);
	
	public int doBilling(String[] orderIds);
	
	public int matchSuccess(ThreeOrder order);
	
	public int matchFail(ThreeOrder order);
	
	public List<ThreeOrderCard> queryCards(ThreeOrder order);
	
	public int savePayfee(ThreeOrder order);

	public List<ThreeOrder> queryOrderList(ThreeOrder order);

	public List<Map<String, Object>> queryOrderCodeList();
	
	public Map<String, String> queryDeptName(Map<String, String> map);
	
	public List<String> queryThreeOrderCodeList();
	
	public List<Map<String, Object>> loadBaseCity();

	public int updateByPrimaryKeySelective(ThreeOrder order);

	public void insertOrder(Map<String, String> map, Long createBy,List<Map<String, Object>> orderTypes)throws Exception;

	public void savaExcelRecord(String url, Long createBy,String fileName);
	
	public void savaOrderImportRecord(String url, Long createBy,String fileName);

	public List<ThreeOrder> queryThreeOrderOutRecord(ThreeOrder order, Page page);
	
	public List<ThreeOrder> queryOrderImportRecord(ThreeOrder order, Page page);

	public Workbook queryExcel(HttpServletRequest request, HttpServletResponse response, ThreeOrder order,
			List<ThreeOrder> dataList, Long loginBy);

}
