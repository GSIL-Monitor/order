package com.emotte.order.order.service;
import java.util.HashMap;
import java.util.List;

import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.Product;
import com.emotte.order.order.model.ProductCategory;

public interface ItemService{
    
	public Item loadItem(Long id);
	
	public List<Item> queryItem(Item item,Page page);
	
	public Long insertItem(Item item);
	
    public void updateItem(Item item);

	public List<CityProduct> queryCityAndProduct(CityProduct cityProduct);

	public List<ProductCategory> queryproductCategory(ProductCategory productCategory);

	public List<ProductCategory> queryproductClassify(ProductCategory productCategory);

	/**
	 * 线下保存商品订单用方法
	 * @param data
	 */
	 
	public Long insertItems(String data,String manager);
	public String insertItemsForeign(Order order);
	
	public List<CityProduct> queryCityServerProduct(CityProduct cityProduct);
	
	public List<CityProduct> queryProductCodeSpec(CityProduct cityProduct);
	
	
	//查询全部
	public List<Item> queryAllItem(Item item);
	
	public List<Product> queryBaseProduct(Product product); //新增订单查询基础商品


	public HashMap<String, Object> selectOrderClash(String data);
    }

