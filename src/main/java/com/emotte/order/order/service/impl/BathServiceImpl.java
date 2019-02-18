package com.emotte.order.order.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.utils.encryptUtils.MD5;

import com.emotte.order.order.mapper.reader.ReBathMapper;
import com.emotte.order.order.mapper.writer.WrOrderBaseMapper;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.BathInserService;
import com.emotte.order.util.HttpUtil;

@Service("bathService")
public class BathServiceImpl implements BathInserService {
	
	private static String URL="http://api.map.baidu.com/geocoder/v2/";
	
	
	@Resource(name="reBathMapper")
	private ReBathMapper reBathMapper;
	
	@Resource(name="wrOrderBaseMapper")
	private WrOrderBaseMapper wrOrderBaseMapper;
	
	/**
	 * 根据商品code码和商品的价格体系，来获取对应的商品详细
	 * @param product_code 城市商编码
	 * @param price_code   商品价格体系  如（会员价、解决方案价 等）
	 * @return 返回查询到的商品详细
	 */
	@Override
	public Map<String, Object> getDetail(String product_code, String price_code) {
		if(product_code==null||product_code.trim().equals("")) return null;
		if(price_code==null||price_code.trim().equals("")) price_code="20000002";
		
		return reBathMapper.getDetail(product_code, price_code);
	}
	@Override
	public String queryIsSingleBatch(String product_code) {
		if(product_code==null||product_code.trim().equals("")) return null;
		
		return reBathMapper.queryIsSingleBatch(product_code);
	}
	/**
	 * 根据客户电话，获取客户的信息，如果客户已存在，则获取对应的地址信息，如果不存在，则新建客户和地址信息
	 * @param mobile 	客户电话
	 * @param name 		 客户姓名
	 * @param province 	省
	 * @param city		市
	 * @param district	区县
	 * @param address   地址
	 * @return  返回查询的结果 
	 */
	@Override
	@Transactional(rollbackFor={Exception.class},propagation=Propagation.REQUIRED)
	public Map<String, Object> getUserDetail(String mobile, String name,
			String province, String city, String district, String address) {
		if(mobile==null||mobile.trim().equals("")||mobile.trim().length()!=11) return null;
		
		String addr=city.replace("市", "")+"市"+district+address;//防止有的城市没有写市
		
		//通过客户电话和地址获取信息（地址与已有地址完全匹配，则算不需要增加）
		Map<String, Object> user = reBathMapper.getUseDetail(mobile, address);
		if(user==null||user.isEmpty()){//如果电话号码没有查询到，则说明此用户不存在需要创建
			/*Map<String, String> map = reBathMapper.getUseDetailNew(orderChannelTemp, address);
			String channel =  null;
			Long channelId =  null;
			if(map != null) {
				channel = map.get("CHANNEL");
				channelId = Long.valueOf(map.get("CHANNELID"));
			}*/
			user=new HashMap<String, Object>();
			//插入用户
			OrderUser orderUser=new  OrderUser();
			/*orderUser.setChannel(channel);
			orderUser.setChannelId(channelId);*/
			orderUser.setAddressDetail(address);
			orderUser.setLoginName(mobile);
			orderUser.setLoginPwd(MD5.MD5Encode("123456"));//默认密码 :123456
			orderUser.setRealName(name);
			orderUser.setUserMobile(mobile);
			orderUser.setOrigin("20180007");//统一为第三方订单
			orderUser.setChannel("第三方订单");//统一为第三方订单
			orderUser.setUserProvince(province);
			orderUser.setUserCity(city);
			orderUser.setUserCountry(district);
			orderUser.setUserAddress(address);
			orderUser.setCreateBy(1l);
			wrOrderBaseMapper.insertOrderUser(orderUser);
			
			
			
			//插入地址信息
			orderUser.setContactName(name);
			orderUser.setContactPhone(mobile);
			long start = System.currentTimeMillis();
			JSONObject json = getLC(addr, city);
			long end = System.currentTimeMillis();
			System.out.println("查询地址时间:"+(end-start));
			if(json!=null&&json.getInt("status")==0){
				orderUser.setLatitude(json.getJSONObject("result").getJSONObject("location").optString("lat"));
				orderUser.setLongitude(json.getJSONObject("result").getJSONObject("location").optString("lng"));
				user.put("LONGITUDE", json.getJSONObject("result").getJSONObject("location").optString("lng"));
				user.put("LATITUDE", json.getJSONObject("result").getJSONObject("location").optString("lat"));
			}
			orderUser.setUserId(orderUser.getId());
			orderUser.setProvince(province);
			orderUser.setCity(city);
			orderUser.setCountry(district);
			
			wrOrderBaseMapper.insertOrderUserAddress(orderUser);
			//赋值map
			user.put("ID", orderUser.getId());
			user.put("REAL_NAME", orderUser.getRealName());
			
			
		}else{//存在则不需要创建
			if(user.get("LONGITUDE")==null||user.get("LATITUDE")==null){//经纬度为空，说明地址没有查询到
					
					try {
						JSONObject json = getLC(addr, city);
						
						if(json!=null&&json.getInt("status")==0){
							user.put("LONGITUDE", json.getJSONObject("result").getJSONObject("location").optString("lng"));
							user.put("LATITUDE", json.getJSONObject("result").getJSONObject("location").optString("lat"));
						}
						//插入地址表
						OrderUser orderUser=new  OrderUser();
						orderUser.setUserId(Long.parseLong(user.get("ID")+""));
						orderUser.setProvince(province);
						orderUser.setCity(city);
						orderUser.setCountry(district);
						orderUser.setCreateBy(1l);
						orderUser.setAddressDetail(address);
						orderUser.setUserProvince(province);
						orderUser.setUserCity(city);
						orderUser.setUserCountry(district);
						orderUser.setLatitude(json.getJSONObject("result").getJSONObject("location").optString("lat"));
						orderUser.setLongitude(json.getJSONObject("result").getJSONObject("location").optString("lng"));
						orderUser.setContactName(name);
						orderUser.setContactPhone(mobile);
						wrOrderBaseMapper.insertOrderUserAddress(orderUser);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		
		return user;
	}

	/**
	 * 获取百度的经纬度
	 * @param addr 地址
	 * @param city 城市
	 * @return
	 */
	public JSONObject getLC(String addr,String city){
		String result = HttpUtil.get(URL+"?address="+addr.trim()+"&city="+city.replace("市", "").trim()+"市&output=json&ak=9ebfe4a42b36ab1969bf8dbe877a51a6", 
				"getlc", "UTF-8");
		
		if(result!=null&&!result.trim().equals("")){
			try {
				JSONObject json = JSONObject.fromObject(result);
				return json;
			} catch (Exception e) {
				e.printStackTrace();
			}

	}
		return null;
	}
	@Override
	public Map<String, Object> getUserDetailNew(String orderCode, String mobile, String name, String province,
			String city, String district, String address) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
