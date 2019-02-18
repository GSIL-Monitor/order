package com.emotte.order.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emotte.dubbo.activities.service.SolutionDubboCardService;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.constant.AfterSaleConstant;
import com.emotte.order.order.mapper.reader.ReAfterSalesMapper;
import com.emotte.order.order.mapper.reader.ReOrderAfterSalesMapper;
import com.emotte.order.order.mapper.reader.RePayfeeMapper;
import com.emotte.order.order.mapper.reader.ReSolutionMapper;
import com.emotte.order.order.mapper.writer.WrAfterSalesMapper;
import com.emotte.order.order.mapper.writer.WrOrderAfterSalesMapper;
import com.emotte.order.order.mapper.writer.WrPayfeeMapper;
import com.emotte.order.order.model.*;
import com.emotte.order.order.service.AfterSalesService;
import com.emotte.order.order.service.OrderAfterSalesService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("afterSalesService")
@Transactional
public class AfterSalesServiceImpl implements AfterSalesService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReAfterSalesMapper reAfterSalesMapper;

	@Resource
	private WrAfterSalesMapper wrAfterSalesMapper;

	@Resource
	private ReSolutionMapper reSolutionMapper;

	@Resource
	private SolutionDubboCardService solutionDubboCardService;


	@Resource
	private WrPayfeeMapper wrPayfeeMapper;

	@Resource
	private RePayfeeMapper rePayfeeMapper;

	@Resource
	private WrOrderAfterSalesMapper wrOrderAfterSalesMapper;

	@Resource
	private OrderAfterSalesService orderAfterSalesService;

	@Resource
	private RedisClient redisClient;

	@Resource
	private ReOrderAfterSalesMapper reOrderAfterSalesMapper;
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public AfterSalesNew loadAfterSales(Long id) {
		return this.reAfterSalesMapper.loadAfterSales(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<AfterSalesNew> queryAfterSales(AfterSalesNew afterSalesNew, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reAfterSalesMapper.countAfterSales(afterSalesNew));
		}
		afterSalesNew.setBeginRow(page.getFilterRecord().toString());
		afterSalesNew.setPageSize(page.getPageCount().toString());
		return this.reAfterSalesMapper.queryAfterSales(afterSalesNew);
	}

	/**
	 * 保存售后单
	 *
	 * @param afterSalesNew		售后数据封装对象
     */
	@Override
	public void insertAfterSales(AfterSalesNew afterSalesNew) {
		try {
			String result  =  updateOrderBalanceForBaiTiao(afterSalesNew);
			if(result != null && "SUCCESS".equals(result)){
				this.insertOrUpdateInstallmentAfterSales(afterSalesNew);
			}
		} catch (Exception e) {
			log.error("updateAfterSales:" + e);
 			throw new BaseException(e);
		}
	}

	@Override
	public void updateAfterSales(AfterSalesNew afterSalesNew) {
		int returnValue;
		try {
			returnValue = this.wrAfterSalesMapper.updateAfterSales(afterSalesNew);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateAfterSales:" + e);
			throw new BaseException(e);
		}
	}

	/**
	 * 根据用户手机号查询关联方案订单表
	 *
	 * @param phoneNumber	用户手机号
	 * @return
	 */
	@Override
	public List<Solution> findSolutionByPhone(String phoneNumber) {
		List<Solution> solutions = reSolutionMapper.findSolutionByPhone(phoneNumber);
		return solutions;
	}

	/**
	 * 业务冻结
	 *
	 * @param afterSalesNew		售后单对象
	 * @return
     */
	public String updateOrderBalanceForBaiTiao(AfterSalesNew afterSalesNew){
		//定义变量
		int count = 0;
		String afterSalesKind = afterSalesNew.getAfterSalesKind();
		if("11".equals(afterSalesKind) || "17".equals(afterSalesKind) || "18".equals(afterSalesKind)||"21".equals(afterSalesKind)){
			if(afterSalesNew.getId() == null){//没有主键代表新增售后
				//修改订单余额
				count = wrAfterSalesMapper.subtractionOrderBalanceForBaiTiao(afterSalesNew.getOrderId(),afterSalesNew.getVphFee(),afterSalesNew.getUpdateBy());
			}else{//有主键代表修改售后
				//查询原售后减除金额
				BigDecimal oldReAmount = null;
				if("11".equals(afterSalesKind)){
					oldReAmount = reAfterSalesMapper.findVPHById(afterSalesNew.getId());
				}else{
					oldReAmount = reAfterSalesMapper.findOtherAmountById(afterSalesNew.getId());
				}
				//获取本次申请售后金额
				BigDecimal baiTiaoAmount = afterSalesNew.getVphFee();
				if(oldReAmount.compareTo(baiTiaoAmount) > 0){//修改前金额大于修改后金额
					//订单补充差额
					BigDecimal addAmount = oldReAmount.subtract(baiTiaoAmount);
					count = wrAfterSalesMapper.addOrderBalanceForBaiTiao(afterSalesNew.getOrderId(),addAmount,afterSalesNew.getUpdateBy());
				}else if(oldReAmount.compareTo(baiTiaoAmount) < 0){//修改前金额小于修改后金额
					//订单减除差额
					BigDecimal subAmount = baiTiaoAmount.subtract(oldReAmount);
					count = wrAfterSalesMapper.subtractionOrderBalanceForBaiTiao(afterSalesNew.getOrderId(),subAmount,afterSalesNew.getUpdateBy());
				}else if(oldReAmount.compareTo(baiTiaoAmount) == 0){//修改前金额等于修改后金额
					return "SUCCESS";
				}
			}
		}else if("13".equals(afterSalesKind) || "14".equals(afterSalesKind) || "15".equals(afterSalesKind)){
			count = 1;
		}else if("16".equals(afterSalesKind)){//卡退费
			if(afterSalesNew.getId() == null){//没有主键代表新增售后
				//根据卡号修改卡片状态为已售后退卡
				wrAfterSalesMapper.discard(afterSalesNew.getCardNumber(),afterSalesNew.getUpdateBy());
				//扣除管家提成
				//查询销售记录
				ModeServeBalancePayment modeServeBanlancePayment = reAfterSalesMapper.findMonyAndAccountByCardNumber(afterSalesNew.getCardNumber());
				modeServeBanlancePayment.setCardNum(afterSalesNew.getCardNumber());
				if(modeServeBanlancePayment != null){
					modeServeBanlancePayment.setUpdateBy(afterSalesNew.getUpdateBy());
					//修改账户信息表
					wrAfterSalesMapper.updateBanlance(modeServeBanlancePayment);
					//添加信息
					wrAfterSalesMapper.addBanlancePayment(modeServeBanlancePayment);
				}
				count = 1;
			}
		}else if("9".equals(afterSalesKind)){//解决方案退费
			if(afterSalesNew.getId() == null){//没有主键代表新增售后
				//校验方案类型
				Long solutionId = afterSalesNew.getSolutionId();//方案ID
				ModeSolutionCustSolution modeSolutionCustSolution = reAfterSalesMapper.checkSolutionIsFree(solutionId);
				if(modeSolutionCustSolution != null){//赠送方案
					//排期订单剩余总金额
					BigDecimal totalBalance = reAfterSalesMapper.findPlanBalanceBySolutionId(solutionId);
					//取消方案
					wrAfterSalesMapper.updateSolutionStatus(solutionId,afterSalesNew.getUpdateBy());
					//排期订单释放
					wrAfterSalesMapper.updatePlanStatus(solutionId,afterSalesNew.getUpdateBy());
					//排期占用金额回冲解决方案
					wrAfterSalesMapper.chargeActiveMonry(totalBalance==null?BigDecimal.ZERO:totalBalance,solutionId,afterSalesNew.getUpdateBy());
//					wrAfterSalesMapper.removeCardBalance(modeSolutionCustSolution.getFee_card_num());
					count = 1;
				}else{//非赠送
					//根据方案id查询是否有代扣卡编号
					String cardNumber = reAfterSalesMapper.findCardNumberForSolution(solutionId);
					if(StringUtils.isNotBlank(cardNumber)){//已支付
						//排期订单剩余总金额
						BigDecimal totalBalance = reAfterSalesMapper.findPlanBalanceBySolutionId(solutionId);
						//取消方案
						wrAfterSalesMapper.updateSolutionStatus(solutionId,afterSalesNew.getUpdateBy());
						//排期占用金额回冲解决方案并扣除售后金额
						BigDecimal totalAmount = totalBalance.subtract(afterSalesNew.getVphFee());
						wrAfterSalesMapper.chargeActiveMonry(totalAmount,solutionId,afterSalesNew.getUpdateBy());
//						//余额回冲代扣卡
//						wrAfterSalesMapper.updateCardBalance(cardNumber,totalBalance);
						//排期订单释放
						wrAfterSalesMapper.updatePlanStatus(solutionId,afterSalesNew.getUpdateBy());
						count = 1;
					}else{//未支付
						//取消方案
						wrAfterSalesMapper.updateSolutionStatus(solutionId,afterSalesNew.getUpdateBy());
						count = 1;
					}
				}
			}
		}
		if(count == 1){
			return "SUCCESS";
		}else{
			return "DEFEATED";
		}
	}

	/**
	 *添加/修改分期白条售后（新增售后）
	 */
	@Override
	public void insertOrUpdateInstallmentAfterSales(AfterSalesNew afterSalesNew) {
		try {
			if(afterSalesNew.getId() == null || afterSalesNew.getId() == 0L) {//新增售后
				OrderAfterSales oas = new OrderAfterSales();
				Long vphAccountId = 0l;
				//添加退款结算单
				if(afterSalesNew.getAfterSalesKind().equals("11") ||afterSalesNew.getAfterSalesKind().equals("17") ||afterSalesNew.getAfterSalesKind().equals("18")||afterSalesNew.getAfterSalesKind().equals("21")){//白条分期
					afterSalesNew.setPayType("3");
					oas.setPayFeeId(afterSalesNew.getPayFeeId());
					oas.setOrderType("2");
				}else if(afterSalesNew.getAfterSalesKind().equals("14")){//海金保理白条分期服务费退费
					afterSalesNew.setPayType("11");
					oas.setPayFeeId(afterSalesNew.getPayFeeId());
					oas.setOrderType("2");
				}else if(afterSalesNew.getAfterSalesKind().equals("13")){//唯品会白条分期服务费退费
					afterSalesNew.setPayType("8");
					oas.setPayFeeId(afterSalesNew.getPayFeeId());
					oas.setOrderType("2");
				}else if(afterSalesNew.getAfterSalesKind().equals("15")){//招行分期分期服务费退费
					afterSalesNew.setPayType("12");
					oas.setPayFeeId(afterSalesNew.getPayFeeId());
					oas.setOrderType("2");
				}else if("16".equals(afterSalesNew.getAfterSalesKind())){//卡退费
					oas.setCardNumb(afterSalesNew.getCardNumber());
					afterSalesNew.setPayType("3");
					oas.setOrderType("3");
				}else if("9".equals(afterSalesNew.getAfterSalesKind())){//解决方案退费
					afterSalesNew.setPayType("3");
					oas.setOrderType("1");
				}
				vphAccountId=insertAccount(afterSalesNew);//生成新结算单信息
				oas.setOrderId(afterSalesNew.getOrderId());
				if(afterSalesNew.getAfterSalesKind().equals("11")) {
					oas.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//分期退款结算单状态：审核中
					oas.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//唯品会退款结算单取消状态:审核中
					oas.setVphFee(afterSalesNew.getVphFee());//分期退费金额
					oas.setVphAccountId(vphAccountId);//分期退款结算单ID
				}else {
					oas.setRefundTotal(afterSalesNew.getVphFee());//其他售后类型退费金额
					oas.setAccountPayId(vphAccountId);
				}
				oas.setReason(afterSalesNew.getReason());//退款原因
				oas.setCreateBy(afterSalesNew.getCreateBy());
				oas.setCreateDept(afterSalesNew.getCreateDept());//创建人部门
				oas.setCustMobile(afterSalesNew.getCustMobile());//客户电话
				oas.setCustName(afterSalesNew.getCustName());//客户姓名
				oas.setApproveBy(afterSalesNew.getApproveBy());//审批人
				oas.setApproveDept(afterSalesNew.getApproveDept());//审批部门
				oas.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//添加即审核中
				oas.setIsAT(1);//手动录入
				oas.setAfterSalesKind(afterSalesNew.getAfterSalesKind());
				//判断退款给唯品会或者客户，唯品会默认银行信息保存
				if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 2){//客户
					oas.setBankCard(afterSalesNew.getBankCard());//银行卡
					oas.setBankCityCode(afterSalesNew.getBankCityCode());//城市
					oas.setBankMainName(afterSalesNew.getBankMainName());//银行名称
					oas.setBankName(afterSalesNew.getBankName());//开户行名称
					oas.setBankSupportId(afterSalesNew.getBankSupportId());//银行类型ID
					oas.setBankUserName(afterSalesNew.getBankUserName());//开户人姓名
				}else if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 1) {//唯品会
					oas.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_VPH);//银行卡
					oas.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_VPH);//城市
					oas.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
					oas.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_VPH);//开户行名称
					oas.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_05));//银行类型ID
					oas.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_VPH);//开户人姓名
				}else if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 3) {//海金保理
					oas.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_HJBL);//银行卡
					oas.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_HJBL);//城市
					oas.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
					oas.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_HJBL);//开户行名称 
					oas.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_09));//银行类型ID
					oas.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_HJBL);//开户人姓名
				}else if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 4) {//招行分期
					oas.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_ZSYH);//银行卡
					oas.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_ZSYH);//城市
					oas.setBankMainName(AfterSaleConstant.BANK_NAME_07);//银行名称
					oas.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_ZSYH);//开户行名称
					oas.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_07));//银行类型ID
					oas.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_ZSYH);//开户人姓名
				}else if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 5) {//汇嘉分期
					oas.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_HJ);//银行卡
					oas.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_HJ);//城市
					oas.setBankMainName(AfterSaleConstant.BANK_NAME_10);//银行名称
					oas.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_HJ);//开户行名称
					/** 2019-01-16 周鑫   银行ID没有用  **/
					//oas.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_10));//银行类型ID
					/** 2019-01-16 周鑫  **/
					oas.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_HJ);//开户人姓名
				}
				oas.setRefundObject(afterSalesNew.getRefundObject());//退费对象
				oas.setReasonFlag(afterSalesNew.getReasonFlag());//退款原因标记
				oas.setIsStop(2);//是否终止 设置为否
				this.wrOrderAfterSalesMapper.insertOrderAfterSales(oas);
				//将售后单id放入redis，生成缴费明细
				if("9".equals(afterSalesNew.getAfterSalesKind())){
					Long redisResult = redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_SOLUTION_PAYFEE_DETAIL, ""+oas.getId()+"");
				}else if("16".equals(afterSalesNew.getAfterSalesKind())){ 
					//卡自动生成缴费
					insertPayfee(oas); 
				}else{
					Long redisResult = redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, ""+oas.getId()+"");
				}
				
			}else{//修改售后
				/** 周鑫  20190115 修改的结算单是支付类型为空的bug **/
				if(afterSalesNew.getAfterSalesKind().equals("11") ||afterSalesNew.getAfterSalesKind().equals("17") ||afterSalesNew.getAfterSalesKind().equals("18")||afterSalesNew.getAfterSalesKind().equals("21")){//白条分期
					afterSalesNew.setPayType("3");
				}else if(afterSalesNew.getAfterSalesKind().equals("14")){//海金保理白条分期服务费退费
					afterSalesNew.setPayType("11");
				}else if(afterSalesNew.getAfterSalesKind().equals("13")){//唯品会白条分期服务费退费
					afterSalesNew.setPayType("8");
				}else if(afterSalesNew.getAfterSalesKind().equals("15")){//招行分期分期服务费退费
					afterSalesNew.setPayType("12");
				}else if("16".equals(afterSalesNew.getAfterSalesKind())){//卡退费
					afterSalesNew.setPayType("3");
				}else if("9".equals(afterSalesNew.getAfterSalesKind())){//解决方案退费
					afterSalesNew.setPayType("3");
				}
				/** 周鑫  20190115 **/
				Long vphAccountId = 0l;
				//添加退款结算单
				vphAccountId=insertAccount(afterSalesNew);//生成新结算单信息
				OrderAfterSales oas = this.reOrderAfterSalesMapper.loadAfterSales(afterSalesNew.getId());
				Payfee account = new Payfee();
				OrderAfterSales oas2 = new OrderAfterSales();
				if(oas.getAfterSalesKind().equals("11")) {
					account.setId(oas.getVphAccountId());//退款结算单id
					oas2.setVphFee(afterSalesNew.getVphFee());
					oas2.setVphAccountId(vphAccountId);//分期退款结算单ID
					oas2.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//审核中
					oas2.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//审核中
				}else {
					oas2.setRefundTotal(afterSalesNew.getVphFee());
					oas2.setAccountPayId(vphAccountId);
					account.setId(oas.getAccountPayId());//退款结算单id
				}
				account.setUpdateBy(afterSalesNew.getCreateBy());
				//将结算单置为无效
				boolean flag=updateAccount(account);
				//售后信息

				oas2.setId(afterSalesNew.getId());
				oas2.setOrderId(oas.getOrderId());
				/*if(oas.getAfterSalesKind().equals("11")) {
					oas2.setVphFee(afterSalesNew.getVphFee());
				}else {
					oas2.setRefundTotal(afterSalesNew.getVphFee());
				}*/

				oas2.setUpdateBy(afterSalesNew.getUpdateBy());
				/*if(afterSalesNew.getAfterSalesKind().equals("11")) {
					oas2.setVphAccountId(vphAccountId);//分期退款结算单ID
					oas2.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//审核中
					oas2.setVphCancleStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//审核中
				}else {
					oas2.setAccountPayId(vphAccountId);
				}*/
				System.out.println(vphAccountId +" fdsafasd     "+oas2.getAccountPayId());
				oas2.setAuditFlag(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//审核中

				oas2.setReason(afterSalesNew.getReason());//退款原因
				oas2.setReasonFlag(afterSalesNew.getReasonFlag());
				oas2.setApproveBy(afterSalesNew.getApproveBy());//审批人
				oas2.setApproveDept(afterSalesNew.getApproveDept());//审批部门
				if(afterSalesNew.getRefundObject() != null && afterSalesNew.getRefundObject() == 2){//客户
					oas2.setBankCard(afterSalesNew.getBankCard());//银行卡
					oas2.setBankCityCode(afterSalesNew.getBankCityCode());//城市
					oas2.setBankMainName(afterSalesNew.getBankMainName());//银行名称
					oas2.setBankName(afterSalesNew.getBankName());//开户行名称
					oas2.setBankSupportId(afterSalesNew.getBankSupportId());//银行类型ID
					oas2.setBankUserName(afterSalesNew.getBankUserName());//开户人姓名
				}
				//更新售后信息
				this.wrOrderAfterSalesMapper.updateOrderAfterSales(oas2);
				//将退款單id放入redis，更新缴费明细
				redisClient.lpush(AfterSaleConstant.REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL, ""+oas2.getId()+"");
			}
		}catch (RedisAccessException e) {
			e.printStackTrace();
			LogHelper.error(getClass(),".insertOrUpdateInstallmentAfterSales()"+e.getMessage(),e);
			throw new BaseException(e);
		} catch (Exception e) {
			e.printStackTrace();
			LogHelper.error(getClass(),".insertOrUpdateInstallmentAfterSales()"+e.getMessage(),e);
			throw new BaseException(e);
		}
}

	//更新结算单状态操作
	public Boolean updateAccount(Payfee payfee){
		int accountMsg = 0;//1：成功 0：失败
		Boolean result = false;
		try {
			payfee.setUpdateTime(DateUtil.getCurrDateTime());
			payfee.setValid(2);
			accountMsg = this.wrPayfeeMapper.updateAccount(payfee);
			Payfee payfee2 = new Payfee();
			payfee2.setOrderId(payfee.getId());
			payfee2.setValid(1);
			List<Payfee> pList = this.rePayfeeMapper.queryPayfee(payfee2);
			if (pList.size() != 0  && !pList.isEmpty()) {
				for (Payfee payfee3 : pList) {
					payfee3.setUpdateBy(payfee.getUpdateBy());
					payfee3.setValid(payfee.getValid());
					this.wrPayfeeMapper.updatePayfee(payfee3);
				}
			}
			if (accountMsg != 1) {
				result = false;
			}else {
				result = true;
			}
		} catch (Exception e) {
			LogHelper.error(getClass(),".updateAccount():"+e.getMessage(),e);
		}
		return result;
	}
	
	/**
	 * 新增结算单
	 * @author lidenghui  
	 * @date 2018年5月29日 上午9:41:07
	 */
	public Long insertAccount(AfterSalesNew  afterSalesNew) {
		Long accountId=0l;
		try {
			Payfee accounts = new Payfee();
			accounts.setId(null);
			accounts.setOrderId(afterSalesNew.getOrderId());//订单id
			accounts.setAccountAmount(afterSalesNew.getVphFee());//结算总额
			accounts.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01)); //退款结算单支付状态:审核中
			accounts.setPayType(afterSalesNew.getPayType());//结算类型默认预收
			accounts.setPayKind(2);     //结算单关联订单类型
			accounts.setCreateBy(afterSalesNew.getCreateBy());
			if(afterSalesNew.getAfterSalesKind().equals("11")) {
				accounts.setVphBackStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//唯品会退款结算单支付状态:审核中
				accounts.setVphStatus(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01);//唯品会退款结算单取消状态:审核中
				accounts.setIsBackType(3);
			}else {
				accounts.setIsBackType(1);//是退款结算单
			}
			accounts.setBussStatus(1);//业务未处理状态
			accounts.setIsManual(1);//后台录入
			accounts.setRefundObject(afterSalesNew.getRefundObject());//退费对象
			this.wrPayfeeMapper.insertAccount(accounts);//生成新结算单信息
			accountId=accounts.getId();
		} catch (Exception e) {
			LogHelper.error(getClass(),".insertAccount():"+e.getMessage(),e);
			throw new BaseException(e);
		}
		return accountId;
	}
	/**
	 * 根据订单号查询缴费明细
	 *
	 * @param orderId	订单ID
	 * @return
     */
	@Override
	public List<AfterSalesNew> findPayFeeDetail(Long orderId,Long payFeeId){
		List<AfterSalesNew> afterSalesNews = reAfterSalesMapper.findPayFeeDetail(orderId,payFeeId);
		return afterSalesNews;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Order> queryOrder(Order order) {
		return this.reAfterSalesMapper.queryOrder(order);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public AfterSalesNew queryFqafterSales(Long id) {
		return this.reAfterSalesMapper.queryFqafterSales(id);
	}

	/**
	 * 查询分期手续费列表
	 *
	 * @param afterSalesNew		参数封装对象
	 * @return
     */
	@Override
	public List<AfterSalesNew> findServiceChargeList(AfterSalesNew afterSalesNew){
		//获取订单ID
		Long orderId = afterSalesNew.getOrderId();
		Long payFeeId = afterSalesNew.getPayFeeId();
		Long bType = afterSalesNew.getbType();
		//查询列表
		List<AfterSalesNew> afterSalesNews = reAfterSalesMapper.findServiceChargeList(orderId, payFeeId);
		return afterSalesNews;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<BaseCity> queryCity() {
		return this.reAfterSalesMapper.queryCity();
	}

	/**
	 * 回购售后
	 *
	 * @param afterSalesNewJson		json字符串
     */
	@Override
	public void buyBackAfterSales(String afterSalesNewJson) throws RedisAccessException {
		AfterSalesNew afterSalesNew = JSON.parseObject(afterSalesNewJson, AfterSalesNew.class);

		//保存结算单对象
		Long accountId = saveAccountPay(afterSalesNew);

		//保存售后单对象
		afterSalesNew.setAccountPayId(accountId);
		Long keyId = saveAfterSales(afterSalesNew);
		//将售后单id放入redis，生成缴费明细
		redisClient.lpush(AfterSaleConstant.REDIS_AFTERSALE_PAYFEE_DETAIL, String.valueOf(keyId));
	}

	/**
	 * 保存结算单
	 *
	 * @param afterSalesNew
	 * @return
     */
	private Long saveAccountPay(AfterSalesNew afterSalesNew) {
		Payfee payfee = new Payfee();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		payfee.setOrderId(afterSalesNew.getOrderId());//订单ID
		payfee.setAccountAmount(afterSalesNew.getRefundTotal());//退款金额
		payfee.setCreateTime(dateFormat.format(new Date()));//创建时间
		payfee.setCreateBy(afterSalesNew.getCreateBy());//创建人
		payfee.setUpdateBy(afterSalesNew.getCreateBy());//更新人
		payfee.setUpdateTime(dateFormat.format(new Date()));//更新时间
		payfee.setVersion(afterSalesNew.getVersion());//版本号
		payfee.setPayStatus(Integer.valueOf(AfterSaleConstant.AFTERSALE_AUDIT_FLAG_01));//结算单支付状态
		String afterSalesKind = afterSalesNew.getAfterSalesKind();
		if("19".equals(afterSalesKind)){//分期回购海金保理
			payfee.setPayType("13");
		}else if("20".equals(afterSalesKind)){//分期回购唯品会
			payfee.setPayType("14");
		}
		payfee.setIsBackType(1);//退款结算单
		payfee.setValid(1);//有效状态
		payfee.setPayKind(2);//结算单关联种类为：订单
		payfee.setBussStatus(1);//业务未处理状态
		payfee.setRemark(afterSalesNew.getRemark());//备注信息
		payfee.setIsOldData(null);//不是老数据
		payfee.setIsManual(1);//后台录入
		payfee.setMemberFee(null);//老数据割接存信息费临时用
		payfee.setFeeToDate(null);//结算对应日期（老数据用）
		payfee.setFinishRefundTime(null);//退款成功时间
		payfee.setCustomerFee(null);//预计客户信息费
		payfee.setPersonalFee(null);//预计服务人员信息费
		payfee.setPersontAndDate(null);//平台实付总金额
		payfee.setOldAccountId(null);//老结算ID
		payfee.setPlatformAllFee(null);//平台实付总金额
		payfee.setVphStatus(null);//管家帮分期取消状态
		payfee.setVphBackStatus(null);//管家帮分期退费状态
		payfee.setOaNumber(null);//OA单号
		payfee.setPayBanknum(null);//打款银行账号
		payfee.setBankFlowNum(null);//银行流水ID号
		payfee.setRefundObject(null);//唯品会退费对象
		payfee.setRemark2(null);//财务审核退费导入需要一个备注
		payfee.setCreateType(null);//生成类型
		payfee.setLog(null);//日志信息

		//执行保存
		wrAfterSalesMapper.saveAccountPay(payfee);
		Long keyId = payfee.getId();
		return keyId;
	}

	/**
	 * 保存售后单对象
	 *
	 * @param afterSalesNew	售后单对象
     */
	private Long saveAfterSales(AfterSalesNew afterSalesNew) {
		//创建售后单对象
		OrderAfterSales orderAfterSales = new OrderAfterSales();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//补全信息
		orderAfterSales.setAccountPayId(afterSalesNew.getAccountPayId());//结算单ID
		orderAfterSales.setCreateBy(1l);//系统创建保存为1
		orderAfterSales.setUpdateBy(1l);//系统创建保存为1
		orderAfterSales.setUpdateTime(dateFormat.format(new Date()));//修改时间
		orderAfterSales.setCreateTime(dateFormat.format(new Date()));
		orderAfterSales.setAuditFlag("20130011");//退款单审核状态
		String afterSalesKind = afterSalesNew.getAfterSalesKind();
		if("19".equals(afterSalesKind)){//海金保理
			orderAfterSales.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_HJBL);//银行卡
			orderAfterSales.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_HJBL);//城市
			orderAfterSales.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
			orderAfterSales.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_HJBL);//开户行名称
			orderAfterSales.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_09));//银行类型ID
			orderAfterSales.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_HJBL);//开户人姓名
		}else{
			orderAfterSales.setBankCard(AfterSaleConstant.AFTERSALE_BANK_CARD_VPH);//银行卡
			orderAfterSales.setBankCityCode(AfterSaleConstant.AFTERSALE_BANK_CITY_CODE_VPH);//城市
			orderAfterSales.setBankMainName(AfterSaleConstant.BANK_NAME_05);//银行名称
			orderAfterSales.setBankName(AfterSaleConstant.AFTERSALE_BANK_NAME_VPH);//开户行名称
			orderAfterSales.setBankSupportId(Long.parseLong(AfterSaleConstant.BANK_SUPPORT_ID_05));//银行类型ID
			orderAfterSales.setBankUserName(AfterSaleConstant.AFTERSALE_BANK_USER_NAME_VPH);//开户人姓名
		}
		wrOrderAfterSalesMapper.insertOrderAfterSales(orderAfterSales);
		Long keyId = orderAfterSales.getId();
		return keyId;
	}

	/**
	 * 根据订单id、服务人员服务费批次查询售后单信息
	 *
	 * @param orderId
	 * @param bathno
	 * @return
	 */
	@Override
	public OrderAfterSales findBuyBackAfterSales(Long orderId, Long bathno) {
		OrderAfterSales orderAfterSales = reAfterSalesMapper.findBuyBackAfterSales(orderId,bathno);
		return orderAfterSales;
	}

	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Solution> querySolution(Solution solution) {
		return this.reAfterSalesMapper.querySolution(solution);
	}

	
	@Override
	public List<CustSolutionPlan> querySolutionSchedule(Long id) {
		List<CustSolutionPlan> list = reAfterSalesMapper.querySolutionSchedule(id);
		return list;
	}

	/**
	 *
	 * 功能描述:
	 *
	 * @param: [afterSalesNew]									查询参数封装对象
	 * @return: com.emotte.order.order.model.AfterSalesNew		卡售后信息封装对象
	 * @auther: lenovo
	 * @date: 2018/6/19 16:34
	 */
	@Override
	public List<AfterSalesNew> findCardDetailForCardAfterSales(AfterSalesNew afterSalesNew){
		List<AfterSalesNew> list = reAfterSalesMapper.findCardDetailForCardAfterSales(afterSalesNew);
		return list;
	}

	/**
	 * 根据卡ID查询卡售后详情
	 *
	 * @param cardId	卡ID
	 * @return
	 * @auther: lenovo
	 * @date: 2018/6/21 16:09
	 */
	@Override
	public AfterSalesNew findCardAfterSalesDetail(Long cardId) {
		AfterSalesNew afterSalesNew = reAfterSalesMapper.findCardAfterSalesDetail(cardId);
		return afterSalesNew;
	}

	@Override
	public AfterSalesNew querySolutionMoney(Long id) {
		
		return reAfterSalesMapper.querySolutionMoney(id);
	}
	
	public int insertPayfee(OrderAfterSales afterSalesNew){
		int num=0;
			//添加退费
			Payfee fee = new Payfee();
			fee.setOrderId(afterSalesNew.getAccountPayId());//新增的退款结算单id
			fee.setFeePost(20250010); //卡退费
			fee.setFeeSum(afterSalesNew.getRefundTotal());//退费金额
			fee.setIsBackType(2);     //退款缴费
			fee.setFeeType(1);        //新增缴费
			fee.setAccountStatus(2);  //未对账
			fee.setCreateBy(afterSalesNew.getCreateBy());
			fee.setIsManual(2);//自动创建
			fee.setCardsNum(afterSalesNew.getCardNumb());
			this.wrPayfeeMapper.insertPayfee(fee);//生成新缴费单信息
			if(null != fee.getId()){
				num=1;
			}
			return num;
	}

	@Override
	public Integer selectPremiumCheck(String orderId, String afterSalesKind) {
		return reAfterSalesMapper.queryPremiumCheck(orderId,afterSalesKind);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public HashMap<String, Object> queryAfterReturnableSalary(AfterSalesNew afterSalesNew) {
		return this.reAfterSalesMapper.queryAfterReturnableSalary(afterSalesNew);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public HashMap<String, Object> queryAfterReturnableMessageFee(AfterSalesNew afterSalesNew) {
		return this.reAfterSalesMapper.queryAfterReturnableMessageFee(afterSalesNew);
	}

	@Override
	public Integer selectAgreementSeaGlodPayFree(String payfeeId) {
		return reAfterSalesMapper.queryAgreementSeaGlodPayFree(payfeeId);
	}

}
