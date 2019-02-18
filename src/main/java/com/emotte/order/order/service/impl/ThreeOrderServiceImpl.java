package com.emotte.order.order.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.emotte.order.order.mapper.reader.ReThreeOrderBaseCityMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderCustomerMapper;
import com.emotte.order.order.mapper.reader.ReThreeOrderProCategoryMapper;
import com.emotte.order.order.mapper.writer.WrThreeOrderCustomerMapper;
import com.emotte.order.order.model.ThreeOrderBaseCity;
import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
import com.emotte.order.order.model.ThreeOrderInProductModel;
import com.emotte.order.order.service.ThreeOrderService;
import com.emotte.order.util.Tools;

@Service("threeOrderService")
@Transactional
public class ThreeOrderServiceImpl implements ThreeOrderService {

	@Resource
	private ReThreeOrderCustomerMapper re_t_o_c_Mapper;
	@Resource
	private WrThreeOrderCustomerMapper wr_t_o_c_Mapper;
	@Resource
	private ReThreeOrderProCategoryMapper re_o_t_p_c_mapper;
	@Resource
	private ReThreeOrderBaseCityMapper re_t_o_b_c_mapper;
	
	@Override
	@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<ThreeOrderCustomerModel> getCustomMapper(ThreeOrderCustomerModel orderUser, Page page) {
		if(page.needQueryPading()){//分页
			page.setTotalRecord(re_t_o_c_Mapper.countOrderThreeCustomer(orderUser));
		}
		orderUser.setBeginRow(page.getFilterRecord().toString());
		orderUser.setPageSize(page.getPageCount().toString());
		List<ThreeOrderCustomerModel> listUser = this.re_t_o_c_Mapper.getOrderThreeCustomer(orderUser);
		return listUser;
	}

	@Override
	public void saveOrderUser(ThreeOrderCustomerModel orderUser) {
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		orderUser.setCreateTime(dFormat.format(Calendar.getInstance().getTime()));
		orderUser.setVersion(1L);
		orderUser.setValid(1);
		orderUser.setCreateBy(1L);
		orderUser.setUpdateTime(orderUser.getCreateTime());
		String address = orderUser.getUserCity();
		if(!Tools.isEmpty(orderUser.getUserAddress())){
			address += orderUser.getUserAddress();
		}
//		Map<String,Double> map = LngAndLatUtil.getLngAndLat(address);
//		orderUser.setLongitude(map.get("longitude").toString());
//		orderUser.setLatitude(map.get("latitude").toString());
		wr_t_o_c_Mapper.insertThreeOrderCustomer(orderUser);
	}
	
	@Override
	public boolean isExsitCustomerByMoblie(ThreeOrderCustomerModel orderUser) {
		Integer count = re_t_o_c_Mapper.isExsitCustomerByMoblie(orderUser);
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public List<ThreeOrderCustomerAddrModel> getOrderThreeCustomerAddrList(ThreeOrderCustomerAddrModel customer) {
		return re_t_o_c_Mapper.queryOrderThreeAddrByUser(customer);
	}

	@Override
	public void saveOrderThreeCustomerAddr(ThreeOrderCustomerAddrModel customer) {
		customer.setVersion(1L);
		//可用
		customer.setValid(1);
		customer.setIsFront(2);
		//创建者--应该是当时登录的用户，暂时使用默认1L表示
		customer.setCreateBy(customer.getCreateBy());
		
		//获得地址的经纬度现在是前台带入
		//String addr = customer.getProvince() + customer.getCity() + customer.getCountry() + customer.getAddressDetail();
		/*Map<String, Double> map = LngAndLatUtil.getLngAndLat(addr);
		customer.setLongitude(map.get("longitude").toString());
		customer.setLatitude(map.get("latitude").toString());*/
		this.wr_t_o_c_Mapper.insertThreeOrderFaAddr(customer);
	}

	@Override
	public void editOrderThreeCustomerAddr(ThreeOrderCustomerAddrModel customer) {
		
		customer.setUpdateTime(Tools.date2Str(new Date()));
		customer.setIsFront(2);
		//获得地址的经纬度现在是前台带入
		/*Map<String, Double> map = LngAndLatUtil.getLngAndLat(customer.getAddressDetail());
		customer.setLongitude(map.get("longitude").toString());
		customer.setLatitude(map.get("latitude").toString());*/
		this.wr_t_o_c_Mapper.updateThreeOrderFaAddr(customer);
	}

	@Override
	public Boolean isExistCustomerAddr(ThreeOrderCustomerAddrModel customer) {
		Integer count = this.re_t_o_c_Mapper.isExistCustomerAddrById(customer);
		if(count!=null && count.intValue() == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public ThreeOrderCustomerAddrModel getOrderThreeCustomerAddrById(ThreeOrderCustomerAddrModel customer) {
		List<ThreeOrderCustomerAddrModel> list = this.re_t_o_c_Mapper.queryOrderThreeAddrByUser(customer);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ThreeOrderBaseCity getAddrCode(ThreeOrderBaseCity addrBaseCity) {
		return this.re_t_o_b_c_mapper.getBaseCityCodeByName(addrBaseCity );
	}

	@Override
	public List<ThreeOrderInProductModel> getThreeOrderInProductList(
			ThreeOrderInProductModel threeOrderInProductModel) {
		//价格体系
		getPriceSystem(threeOrderInProductModel);
		
		List<ThreeOrderInProductModel> orderInProductList = re_o_t_p_c_mapper.getProductList(threeOrderInProductModel);
		for(Iterator<ThreeOrderInProductModel> iter = orderInProductList.iterator();iter.hasNext();){
			//将最小单位起订量为null，或小于0的设置成默认值1
			ThreeOrderInProductModel pd = iter.next();
			Integer pro_min_count = pd.getMinUnitCount();
			if(pro_min_count == null || pro_min_count.intValue() < 1){
				pro_min_count = 1;
				pd.setMinUnitCount(pro_min_count);
			}
		}
		return orderInProductList;
	}

	@Override
	public List<ThreeOrderInProductModel> reCalculateThreeOrderInPrice(
			ThreeOrderInProductModel threeOrderInProductModel,Map<String,BigDecimal> amount_map) {
		//价格体系
		getPriceSystem(threeOrderInProductModel);
		
		//此json用于存放前台传入的商品
		String json = threeOrderInProductModel.getProduct_code_count_json();
		JSONArray json_arr = JSON.parseArray(json);
		//将json中的数据备份到map结构中--避免在计算总价时嵌套循环
		Map<String,Integer> product_count_map = new HashMap<String,Integer>();
		//存放前台传入的商品的所有product_code,用于数据库查询
		List<String> product_code_list = new ArrayList<String>();
		for(Iterator<Object> iter = json_arr.iterator();iter.hasNext();){
			JSONObject obj =  (JSONObject) iter.next();
			String product_code = obj.getString("product_code");
			Integer product_count = obj.getInteger("product_count");
			
			product_count_map.put(product_code, product_count);
			product_code_list.add(product_code);
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		
		params.put("dict_code", threeOrderInProductModel.getDict_code());
		//params.put("city_code", threeOrderInProductModel.getCity_code());
		params.put("product_code_list", product_code_list);
		/*params.put("category_code", threeOrderInProductModel.getCategory_code());*/
		List<ThreeOrderInProductModel> list= this.re_o_t_p_c_mapper.getProductListByCodes(params);
		BigDecimal amount = new BigDecimal(0);
		//计算总价
		for(Iterator<ThreeOrderInProductModel> iter = list.iterator();iter.hasNext();){
			ThreeOrderInProductModel pd = iter.next();
			//product_code
			String pro_code = pd.getProduct_code();
			//此商品的数量
			Integer pro_count = product_count_map.get(pro_code);
			//此商品的最小起订量
			Integer pro_min_count = pd.getMinUnitCount();
			//前台传入的商品数量小于最小起订量，将此商品删除
			if(pro_min_count == null || pro_min_count.intValue() < 1){
				pro_min_count = 1;
				pd.setMinUnitCount(pro_min_count);
			}
			if(pro_count.intValue()<pro_min_count.intValue()){
				iter.remove();
			}else{
				pd.setProduct_count(pro_count);
				BigDecimal pro_pri = pd.getPrice();
				pd.setProduct_amount(pro_pri.multiply(new BigDecimal(pro_count)));
				amount = amount.add(pd.getProduct_amount());
			}
		}
		amount_map.put("amount", amount);
		return list;
	}
	
	//价格体系
	private void getPriceSystem(ThreeOrderInProductModel threeOrderInProductModel) {
		ThreeOrderInProductModel ip = re_t_o_c_Mapper.gerCustomerIs_Vip_BigCustById(threeOrderInProductModel);
		if(ip!=null){
			if(ip.getIs_vip() != null && ip.getIs_vip().intValue()==1){
				threeOrderInProductModel.setDict_code("20000006");
			}else if(ip.getIs_big_cust() !=null && ip.getIs_big_cust().intValue() == 1){
				threeOrderInProductModel.setDict_code("20000003");
			}else{
				threeOrderInProductModel.setDict_code("20000002");
			}
		}
	}
}
