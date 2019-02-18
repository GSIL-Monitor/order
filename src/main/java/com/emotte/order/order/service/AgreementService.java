package com.emotte.order.order.service;

import com.emotte.gentlemanSignature.core.model.LocalSign_fileType0;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.DataDictionaryModel;
import net.sf.json.JSONObject;
import org.wildhorse.server.core.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface AgreementService {

	public Agreement loadAgreement(Long id);
	
	public Map queryAgreementById(Long id);

	public List<Agreement> queryAgreement(Agreement agreement, Page page);

	public void insertAgreement(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response)throws Exception;
	public void insertAgreementNew(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response)throws Exception;

	public void updateAgreement(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response);
	public void updateAgreementNew(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response);
	//预览三方合同
	public String queryContractPDF(HttpServletRequest request, HttpServletResponse response, String previewFlag, String contractId,String homePage,LocalSign_fileType0 ls,String agreementModel);

	public List<HashMap<String, Object>> queryCheckAgreement_listPage(Agreement agreement,Page page);

	public void checkAgreement(Agreement agreement);

	public List<HashMap<String, Object>> queryServiceStation(Agreement agreement);

	public List<HashMap<String, Object>> queryPersonnel(Long orderId);
	
	public List<BaseCity> queryCitys(BaseCity baseCity);

	public List<Agreement> queryCanCopyAgreement(Agreement agreement);

	public Map<String, String> queryAgreementHeader(Long deptId);
	//导出符合条件审核成功的合同
	public List<Map<String, Object>> queryExportCpeList(Agreement agreement);
	
	//查询有没有已确认合同
	public void updateAgreementTwo(Agreement agreement);

	/**
	 * 查询卡开户人
	 * @author lidenghui  
	 * @date 2018年5月8日 上午1:58:52
	 */
	public String checkCardName(Long orderId);
	
	/**
	 * 查询客户信息
	 * @author lidenghui  
	 * @date 2018年5月15日 下午8:45:28
	 */
	public List<Map<String, String>> showCustomerManager(Long userId);
	
	/**
	 * 
	 * @Title: getDictionaryInfo
	 * @Description: 查询数据字典表
	 * @author dengyouqian@95081.com
	 * @date 2018年5月15日
	 * @param dictObj
	 * @return
	 * @return List<HashMap<String,Object>>
	 * @throws
	 */
	public List<DataDictionaryModel> getDictionaryInfo(DataDictionaryModel dictObj,HttpServletRequest request);
	
	
	
	/**
	 * 海金更新签约时间方法(下五)——20180630添加   
	 */
	public Agreement loadAgreementcreatime(Long id);
	
	public Agreement queryAgreementcreatime(String id);
	
	public Agreement queryAgreementcreatime1(String id);
	
	public void updateAgreementCreatime(Agreement agreement);
	
	public void updateAgreementCreatimeNew(Agreement agreement);

	/**
	 *
	 * 功能描述: 根据合同ID查询合同信息
	 *
	 * @param: orderId	订单ID
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
	
	public Agreement selectAgreement(Agreement agreement);

	/**
	 * 根据订单ID或者合同ID查询合同是否存在
	 *
	 * @param culomnName    查询列名称
	 * @param id            查询参数
     * @return
	 * @Author zhanghao
	 * @Date 20181114
     */
	List<Agreement> findAgreementByOrderIdOrAgreementId(String culomnName, Long id);

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
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181116
     */
	List<Agreement> findAgreementListByOrderId(Agreement agreement);
	
	/**
	 * 判断是否是海金合同，如过要是海金合同删除操作，直接清除海金合同的申请
	 * 操作人：周鑫
	 * 2018年11月15日下午1:41:37
	 */
	public int findAgreementByOrderIdGold(Long orderId);
	
	/**
	 * 查询合同code和订单code
	 * @param orderId 订单ID
	 * @param id 合同ID
	 * 操作人：周鑫
	 * 2018年11月15日下午1:40:24
	 */
	public Map<String, String> queryOrderIdAndId(Long orderId, Long id);
	
	/**
	 * 
	 * @Title: updateInsurance
	 * @Description: 点击签署同意保险委托书
	 * @author dengyouqian@95081.com
	 * @date 2018年12月25日
	 * @param agreement
	 * @return
	 * @return JSONObject
	 * @throws
	 */
	public JSONObject updateInsurance(Agreement agreement);
	
	/**
	 * 修改合同的审核状态
	 * 操作人：周鑫
	 * 2019年1月2日下午1:27:41
	 */
	public JSONObject updateAgreementCheckStatus(Long checkAgreementId,Long loginId);

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
	 * 保存单次服务合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190111
     */
	void addOneceAgreement(Agreement agreement);

	/**
	 * 根据合同ID查询对象信息
	 *
	 * @param id
	 * @return
	 * @Author zhanghao
	 * @Date 20190114
     */
	Agreement findAgreementById(Long id);

	/**
	 * 更新单次合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190114
     */
	void updateOneceAgreement(Agreement agreement);

	/**
	 * 删除单次合同
	 *
	 * @Author zhanghao
	 * @Date 20190114
	 * @param agreement
     */
	void deleteOnceAgreement(Agreement agreement);

	/**
	 * 确定是否为绩效合同,如果是,保存比例和标识
	 * @param agreement
	 */
	public Boolean checkIsPeContract(Agreement agreement);

	/**
	 * 根据订单ID，修改单次合同审核状态
	 *
	 * @param orderId
	 * @Author zhanghao
	 * @Date 20190212
     */
	void changeOnceAgreementAuditStatus(Agreement agreement);

	/**
	 * 驳回单次合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
     */
	void turnDownOneceAgreement(Agreement agreement);

	/**
	 * 单次合同审核通过
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
     */
	void auditPassOnceAgreement(Agreement agreement);
}