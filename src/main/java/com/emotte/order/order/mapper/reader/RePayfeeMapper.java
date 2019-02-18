package com.emotte.order.order.mapper.reader;

import com.emotte.order.order.model.Payfee;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component("rePayfeeMapper")
public interface RePayfeeMapper extends ReBaseMapper {

    public Payfee loadPayfee(Long id);

    public List<Payfee> queryPayfee(Payfee payfee);

    public List<Payfee> queryAccount(Payfee payfee);

    public Integer countPayfee(Payfee payfee);

    public Payfee loadAccount(Long id);

    public List<Payfee> queryPayfeeLpk(Payfee payfee);

    public Payfee queryAccountByOrderId(Payfee payfee);

    public String loadPayfeeMinCreTime(Long accountId);

    public Payfee loadIOUsByUserId(Long userId);

    public BigDecimal queryPayfeeByType(Map<String, Object> map);

    public Long queryVphPay(Long accountId);

    public String queryVphBankFlowNum(Long orderId);

    public List<Payfee> queryPosChecklistPage(Payfee payfee);

    public List<Map<String, Object>> queryPosCheck(Payfee payfee);

    public List<Payfee> updatePayfeeOther(Payfee payfee);

    public List<Payfee> queryPayfeeSplit(Long orderId);

    public List<Map<String, Object>> queryPosCheckExcel(Payfee payfee);

    public Integer queryUncheck(Payfee payfee);

    public List<Payfee> queryOtherDeal(Payfee payfee);

    public Payfee queryPayCreateTime(Long accountId);

    public Long queryChannel(Long orderId);

    public Long queryInstalmentsPay(Long accountId, int feepost, int isBackType);

    public List<Payfee> queryAccountList(Payfee payfee);

    public Double queryPayfeeDetail(Payfee payfee);

    public Long queryTypeByParentId(Long parentId);

    /**
     * 根据结算单ID校验财务系统汇总表信息
     *
     * @param accountId 结算单ID
     * @Author zhanghao
     * @Date 20181031
     */
    int checkFinSummaryForAccount(Long accountId);

    /**
     * 根据结算单ID ,查询审核状态
     * 操作人：周鑫
     * 2018年12月27日下午12:10:12
     */
    int checkAccountReviewState(Long accountId);

    /**
     * 查询售后的历史退费
     * 操作人：周鑫
     * 2019年1月7日上午10:32:23
     */
    public Double getHistoryAfterCharge(Long orderId);

    /**
     * 根据结算单ID查询支付方式为【第三方支付（区别于：卡、券、余额支付）】的缴费单信息
     *
     * @param accountId
     * @return
     * @Author zhanghao
     * @Date 20190118
     */
    int findCountByAccountIdForDelete(Long accountId);

    /**
     * 根据订单ID查询是否存在第三方支付缴费
     *
     * @param orderId
     * @return
     * @Author zhanghao
     * @Date 20180218
     */
    int findPayFeeByOrderIdAndFeePost(Long orderId);
}