package com.emotte.order.order.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;

import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.mapper.reader.ReItemMapper;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.writer.WrItemMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.Product;
import com.emotte.order.order.model.ProductCategory;
import com.emotte.order.order.service.ItemService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReItemMapper reItemMapper;
	
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;

	@Resource
	private WrItemMapper wrItemMapper;
	
	@Resource
    private OrderServiceImpl orderService ;
	
	@Resource
	private WrOrderMapper wrOrderMapper;
  	@Resource
  	private ReOrderMapper reOrderMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Item loadItem(Long id) {
		return this.reItemMapper.loadItem(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Item> queryItem(Item item, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reItemMapper.countItem(item));
		}
		item.setBeginRow(page.getFilterRecord().toString());
		item.setPageSize(page.getPageCount().toString());
		return this.reItemMapper.queryItem(item);
	}

	@Override
	public Long insertItem(Item item) {
		this.wrItemMapper.insertItem(item);
		return item.getId();
	}

	@Override
	public void updateItem(Item item) {
		int returnValue;
		try {
			returnValue = this.wrItemMapper.updateItem(item);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateItem:" + e);
			throw new BaseException(e);
		}
	}

	@Override
	public List<CityProduct> queryCityAndProduct(CityProduct cityProduct) {
		try {
			return this.reItemMapper.queryCityAndProduct(cityProduct);
		} catch (Exception e) {
			log.error("queryCityAndProduct:" + e);
			throw new BaseException(e);
		}
			
	}

	@Override
	public List<ProductCategory> queryproductCategory(ProductCategory productCategory) {
		return this.reItemMapper.queryproductCategory(productCategory);
	}

	@Override
	public List<ProductCategory> queryproductClassify(ProductCategory productCategory) {
		return this.reItemMapper.queryproductClassify(productCategory);
	}

	
	/**
	 *  线下保存商品订单用方法
	 */
	@Override
	public Long insertItems(String data,String manager) {
		Long createBy = Long.valueOf(manager.split(",")[0]);
		Long deptId = Long.valueOf(manager.split(",")[1]);
		JSONObject jobj = JSONObject.fromObject(data);
        Order order = new Order();
        order.setUserId(Long.valueOf((String) jobj.get("userId")));
        order.setReceiverId(Long.valueOf((String) jobj.get("receiverId")));
        order.setReceiptTime(String.valueOf(jobj.get("receiptTime")));
        order.setCritical(Integer.valueOf(jobj.get("critical").toString()));
        order.setOrderChannel(String.valueOf(jobj.get("orderChannel")));
        order.setRemark(String.valueOf(jobj.get("remark")));
        order.setCity((String) jobj.get("city"));
        order.setCateType(Integer.valueOf(jobj.get("cateType").toString()));
        order.setOrderType("1");
        order.setCreateBy(createBy);
        order.setUpdateBy(createBy);
        order.setCreateDept(deptId);
        order.setOrderSourceId("20180005");
        order.setEmp_id(Long.valueOf((String) jobj.get("emp_id")));
        order.setThreeOrderCode(jobj.get("threeOrderCode")!=null?jobj.get("threeOrderCode").toString():"");
        
        String deliverPay = "0" ;
        if(jobj.get("deliverPay")!=null && !"".equals(jobj.get("deliverPay"))){
        	deliverPay = (String)jobj.get("deliverPay"); 
        }
        order.setDeliverPay(new BigDecimal(deliverPay));
        order.setPriceType(String.valueOf(jobj.get("priceType")));
		order.setId(this.reOrderMapper.queryOrderId());
		Long orderid = order.getId();
		BigDecimal pay = new BigDecimal(0);
		JSONArray jsonItem = JSONArray.fromObject(jobj.get("item"));
		LogHelper.info(getClass(),"商品订单item信息："+jsonItem.toString());
		for (Object object : jsonItem) {
			JSONObject jobjItem = JSONObject.fromObject(object);
			Object obj = JSONObject.toBean(jobjItem, Item.class);
			Item item = new Item();
			item = (Item)obj;
			item.setOrderId(orderid);
			item.setCreateBy(createBy);
			item.setUpdateBy(createBy);
			if(null != item.getProductCode()){
				CityProduct cp = new CityProduct();
				cp.setDictCode(order.getPriceType());
				cp.setCityCode(order.getCity());
				cp.setProductCode(item.getProductCode());
				List<CityProduct> productList = this.reItemMapper.queryForDictPriceFA(cp);
				if(productList != null && productList.size() > 0){
					CityProduct cpt = productList.get(0);
					item.setNowPrice(cpt.getMarketPrice());
					item.setProductImg(cpt.getProductImg());
					item.setProductSpec(cpt.getProductSpec());
					pay = pay.add(cpt.getMarketPrice().multiply(new BigDecimal(item.getQuantity())));
					this.wrItemMapper.insertItem(item);
				}
			}
		}
		order.setDeliverPay(new BigDecimal(deliverPay));
		order.setTotalPay(pay.add(order.getDeliverPay()));
		this.orderService.insertOrder(order);
    	return orderid;
	}
	
	@Override
	public String insertItemsForeign(Order order) {
//		JSONArray array = JSONArray.fromObject(order.getListItem());  
//		Object[] obj = new Object[array.size()];  
//        for(int i = 0; i < array.size(); i++){  
//             JSONObject JB = array.getJSONObject(i);
//           //将建json对象转换为Object对象
//            obj[i] = JSONObject.toBean(JB, Item.class);  
//        }  
        
		this.wrOrderMapper.insertOrder(order);
		Long orderId = order.getId();
		
		for (Object object : order.getListItem()) {
			JSONObject jite=JSONObject.fromObject(object);
			Object oite = JSONObject.toBean(jite, Item.class);
			Item item = new Item();
			item = (Item)oite;
			item.setOrderId(orderId);
			item.setVersion((long) 1);
			item.setValid(1);
			this.wrItemMapper.insertItem(item);
		}
		return String.valueOf(orderId) ;
	}
	
	/**
	 * 查询服务类城市商品
	 */
	@Override
	public List<CityProduct> queryCityServerProduct(CityProduct cityProduct){
		return this.reItemMapper.queryCityServerProduct(cityProduct);
	}
	/**
	 * 查询服规格
	 */
	@Override
	public List<CityProduct> queryProductCodeSpec(CityProduct cityProduct){
		return this.reItemMapper.queryProductCodeSpec(cityProduct);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Item> queryAllItem(Item item) {
		return this.reItemMapper.queryAllItem(item);
	}
	/**
	 * 新增订单查询基础商品
	 * @author 王世博
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Product> queryBaseProduct(Product product) {
		return this.reItemMapper.queryBaseProduct(product);
	}


	@Override
	public HashMap<String, Object> selectOrderClash(String data) {
		JSONObject jobj = JSONObject.fromObject(data);
	//	String cityCode=this.reItemMapper.selectUser(jobj.getString("receiverId"));
		String receiverId=jobj.getString("receiverId");
		String[] productCode=jobj.getString("productCode").split(",");
		HashMap<String, Object> map=new HashMap<String, Object>();
		for (String productCode1 : productCode) {
			map=this.reItemMapper.selectOrderClash(productCode1,receiverId);
			if(null!=map&&null!=map.get("IS_ENABLEIS")&&map.get("IS_ENABLEIS").equals(2)){
				break;
			}
		}
		return map;
	}

}