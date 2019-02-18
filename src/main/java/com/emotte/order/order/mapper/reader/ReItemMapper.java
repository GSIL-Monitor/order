package com.emotte.order.order.mapper.reader;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.CityProduct;
import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.Product;
import com.emotte.order.order.model.ProductCategory;

@Component("reItemMapper")
public interface ReItemMapper extends ReBaseMapper {

	public Item loadItem(Long id);

	public List<Item> queryItem(Item item);

	public Integer countItem(Item item);

	public List<CityProduct> queryCityAndProduct(CityProduct cityProduct);

	public Integer countCityAndProduct(CityProduct cityProduct);

	public List<ProductCategory> queryproductCategory(ProductCategory productCategory);

	public List<ProductCategory> queryproductClassify(ProductCategory productCategory);

	public int City(CityProduct cityProduct);
	
	public List<CityProduct> queryCityServerProduct(CityProduct cityProduct);
	
	public List<CityProduct> queryProductCodeSpec(CityProduct cityProduct);
	
	public List<CityProduct> queryForDictPrice(CityProduct cityProduct);
	public List<CityProduct> queryForDictPriceFA(CityProduct cityProduct);
	//根据订单id，查询商品信息
	public Item loadItemByOrderId(Long orderId);
	//根据商品编码和商品价格体系，查询id
	public CityProduct queryItemByCodeAndType(CityProduct cityProduct);

	public List<Item> queryAllItem(Item item);
	
	public List<Product> queryBaseProduct(Product product);//新增订单查询基础商品

	//查询客户返回客户二级城市
	public String selectUser(String receiverId);

	public HashMap<String, Object> selectOrderClash(String productCode1, String cityCode);

	public String selectUser(Long oo);

	/**
	 * 根据商品code查询商品市场价
	 *
	 * @param productCode		商品CODE
	 * @return
	 * @Author zhanghao
	 * @Date 20181113
     */
	BigDecimal findMarketPriceByProductCode(String productCode);

	/**
	 * 根据订单ID查询商品市场价汇总
	 *
	 * @param orderId		订单ID
	 * @return
	 * @Author zhanghao
	 * @Date 20181113
	 */
	BigDecimal findMarketPriceByOrderId(Long orderId);
}
