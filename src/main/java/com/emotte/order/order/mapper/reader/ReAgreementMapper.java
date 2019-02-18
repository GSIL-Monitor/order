package com.emotte.order.order.mapper.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.model.AgreementPerformance;
import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.DataDictionaryModel;
import com.emotte.order.order.model.Order;

@Component("reAgreementMapper")
public interface ReAgreementMapper extends ReBaseMapper {

	public Agreement loadAgreement(Long id);

	public List<Agreement> queryAgreement(Agreement agreement);

	public Integer countAgreement(Agreement agreement);

	public Map queryAgreementById(Long id);
	
	public List<HashMap<String, Object>> queryCheckAgreement_listPage(Agreement agreement);
	
	public List<HashMap<String, Object>> queryServiceStation(Agreement agreement);
	
	public List<HashMap<String,Object>> queryPersonnel(Long orderId);
	
	public List<BaseCity> queryCitys(BaseCity baseCity);

	public List<Agreement> queryCanCopyAgreement(Agreement sagreement);

	public Map<String, String> queryAgreementHeader(Long deptId);

	public List<Map<String, Object>> queryExportCpeList(Agreement agreement);

	public String checkCardName(Long orderId);

	public List<Map<String, String>> showCustomerManager(Long userId);

	public List<DataDictionaryModel> getDictionaryInfo(DataDictionaryModel dictObj);
	
	public Agreement queryAgreementcreatime(String id);
	
	public Agreement queryAgreementcreatime1(String id);
	
	public Agreement loadAgreementcreatime(Long id);

	/**
	 *
	 * 功能描述: 根据合同ID查询合同信息
	 *
	 * @param: orderId	合同ID
	 * @return:
	 * @auther: lenovo
	 * @date: 2018/7/19 20:12
	 */
	int findContractById(Long contractId);

	/**
	 *
	 * 功能描述: 根据合同ID查询合同编号
	 *
	 * @param: contractId 合同ID
	 * @return:
	 * @auther: lenovo
	 * @date: 2018/7/20 16:23
	 */
	String findAgreementCodeById(Long contractId);

	/**
	 * 查询法定代表人
	 * @param orderId
	 * @return
	 */
	public String queryLegalRepresentative(Long orderId);

	
	public Long getDeptId(Long createBy);

	
	public Long getOrderCode(Long orderId);

	/**
	 * 查询合同
	 * @param agreement
	 * @return
	 */
	public Agreement selectAgreement(Agreement agreement);

	/**
	 * 根据ID查询合同对象
	 *
	 * @param id
	 * @return
	 * @Author zhanghao
	 * @date 20181030
     */
	Agreement findOne(Long id);

	/**
	 * 根据订单ID或者合同ID查询合同是否存在
	 *
	 * @param culomnName    查询列名称
	 * @param id            查询参数
	 * @return
	 * @Author zhanghao
	 * @Date 20181114
	 */
	List<Agreement> findAgreementByOrderIdOrAgreementId(@Param("culomnName") String culomnName, @Param("id") Long id);

	/**
	 * 根据订单ID查询最后一个订单的结束时间
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181115
	 */
	Agreement findEndTimeByOrderId(Long orderId);

	/**
	 * 根据订单ID查询合同列表
	 *
	 * @param agreement
	 * @return
	 * @Author zhanghao
	 * @Date 20181116
	 */
	List<Agreement> findAgreementListByOrderId(Agreement agreement);

	/**
	 * 查询合同是都是海金合同
	 * @param  id  订单ID
	 * 操作人：周鑫
	 * 2018年11月15日下午1:04:59
	 */
	int findAgreementByOrderIdGold(@Param("id") Long id);

	/**
	 * 单次服务订单查询三方协议详情
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20190111
	 */
	Agreement queryAgreementServerOneDetail(Long orderId);

	/**
	 * 根据合同ID查询对象信息
	 *
	 * @param id
	 * @return
	 * @Author zhanghao
	 * @Date 20190111
	 */
	Agreement findAgreementById(Long id);

	public Agreement queryIsPeContract(Long id);

	public Order loadOrder(Long orderId);

	public AgreementPerformance queryAgreementPerformance(@Param("city") String city);
}