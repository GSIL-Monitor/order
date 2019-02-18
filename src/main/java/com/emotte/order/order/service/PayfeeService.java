package com.emotte.order.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.Payfee;
import org.wildhorse.server.core.model.Page;

public interface PayfeeService {

	public Payfee loadPayfee(Long id);

	public List<Payfee> queryPayfee(Payfee payfee, Page page);

	public List<Payfee> queryAccount(Payfee payfee);

	public void insertPayfee(Payfee payfee);
	public void insertPayfeeList(String data, Long accountId, Long createBy, Long orderId, int cateType);

	public void updatePayfee(Payfee payfee);

	public Payfee loadAccount(Long id);

	public void insertAccount(Payfee payfee, String jsonStr);

	public void updateAccount(Payfee payfee);
	
	public List<Payfee> queryPayfeeLpk(Payfee payfee);
	
	public int countPayfee(Payfee payfee);

	public String loadPayfeeMinCreTime(Long accountId);

	public Payfee loadIOUsByUserId(Long userId);

	public BigDecimal queryPayfeeByType(Map<String, Object> map);

	public List<Payfee> queryPosCheck(Payfee payfee, Page page);
	
	public List<Map<String, Object>> queryPosCheckExcel(Payfee payfee);

	public Integer queryUncheck(Payfee payfee);

	public List<Payfee> queryOtherDeal(Payfee payfee);

	public void updateOtherDeal(Payfee payfee);

	public List<Map<String, Object>> queryPosCheckImg(Payfee payfee);

	public Long queryChannel(Long orderId);

	public List<Payfee> queryAccountList(Payfee payfee);

	public Double queryPayfeeDetail(Payfee payfee);

	public Long queryTypeByParentId(Long parentId);

	/**
	 * 查询订单缴费是否大于1000
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20180927
     */
	boolean checkAccountTotal(Long orderId);

	/**
	 * 根据结算单ID校验财务系统汇总表信息
	 *
	 * @param accountId     结算单ID
	 * @Author zhanghao
	 * @Date 20181031
	 */
	boolean checkFinSummaryForAccount(Long accountId);
	/**
	 * 根据结算单ID ,查询审核状态
	 * 操作人：周鑫
	 * 2018年12月27日下午12:02:16
	 */
	boolean checkAccountReviewState(Long accountId);

	public Double getHistoryAfterCharge(Long orderId);
	
	/**
	 * 
	 * @Description: 修改结算单 
	 * @param accountId      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	void updateAccount(Long accountId);

}