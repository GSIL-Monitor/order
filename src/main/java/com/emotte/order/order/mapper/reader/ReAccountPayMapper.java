package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.AccountPay;
@Component("reAccountPayMapper") 
public interface ReAccountPayMapper extends ReBaseMapper{ 
	public List<AccountPay> queryAccountPay(AccountPay accountpay);

	public List<AccountPay> queryAccountById(AccountPay accountPay);

	public Map<String, Object> queryAccountInfo(Long accountId);

	/**
	 * 查询符合条件的结算单
	 * ①延续订单
	 * ②结算状态为已完成
	 * ③非逆向结算单
	 * ④业务状态为未处理
	 * @return
	 * 2017年6月9日
	 */
	public List<AccountPay> queryNoBuss();

	/**
	 * TODO
	 * @param id
	 * @return
	 * 2017年6月9日
	 */
	public Map<String, Object> isAllPayFeeSplit(Long id);
	
	/**
	 * 
	* @Description: 单独查询结算单
	* @author lidenghui
	* @date 2018年4月9日 下午5:22:09 
	* @version
	* @param accountpay
	* @return AccountPay
	 */
	public AccountPay selectAccountPay(AccountPay accountpay);

	/**
	 * 根据结算单ID查询支付方式没有【第三方支付（区别于：卡、券、余额支付）】的结算单信息
	 *
	 * @param accountId
	 * @return
	 * @Author zhanghao
	 * @Date 20190118
     */
	int findCountByAccountIdForDelete(Long accountId);

	/**
	 * 根据结算单ID查询订单ID
	 *
	 * @param accountId
	 * @return
	 * @Author zhanghao
	 * @Date 20190214
     */
	Long findOrderIdByAccountId(Long accountId);

	/**
	 * 根据订单ID查询结算完成的结算单
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20190218
	 */
	int findAccountByOrderIdAndPayStatus(Long orderId);

	/**
	 * 根据订单ID查询结算状态为：未结算，部分结算/支付方式为内部支付的结算单信息
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20190218
     */
	List<Long> findAccountForAppCheck(Long orderId);
}
