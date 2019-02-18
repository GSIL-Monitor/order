package com.emotte.order.order.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收支表封装类
 * Created by zhanghao on 2018/5/23.
 */
public class ModeServeBalancePayment {

    /**
     * 主键
     */
    private Long id;

    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 类型  1:收入  2:支出
     */
    private Integer type;

    /**
     * 类型详情  1:奖励  2:提现  3:充值  4:消费
     */
    private Long typeDetail;

    /**
     * 创建者
     */
    private Long createBy;

    /**
     * 修改者
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 有效性
     */
    private Integer vaild;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 日志
     */
    private String log;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 1:已读  2:未读
     */
    private Integer read;

    /**
     * 奖励类型
     * 1:身份认证
     * 2:上户7天
     * 3:推卡
     * 4:注册成功
     * 5:延续上户满26天/单次满十次
     * 6:红包
     */
    private Integer awardType;

    /**
     * 邀请表id
     *  2:类型为提现时关联T_PARTNER_WITHDRAWALS表id
     *  3:奖励类型为推卡时对应的ID为t_card_sell_record的ID
     *  4:奖励类型为红包时对应t_activity_statistics表id
     */
    private Long inviteId;

    /**
     * 每笔收入或支出之后的余额
     */
    private BigDecimal banlance;

    /**
     * 业务类型
     *  204100010001：储蓄卡充值
     *  204100010002：信用卡充值
     *  204100010003：分享
     *  204100010004：合伙人
     *  204100010005：推卡
     *  204100010006：充值返现
     *  204100020001：消费
     *  204100020002：提现
     *  204100010008：红包奖励
     */
    private String busiType;

    /**
     * 退卡编号
     */
    private String cardNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(Long typeDetail) {
        this.typeDetail = typeDetail;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVaild() {
        return vaild;
    }

    public void setVaild(Integer vaild) {
        this.vaild = vaild;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public BigDecimal getBanlance() {
        return banlance;
    }

    public void setBanlance(BigDecimal banlance) {
        this.banlance = banlance;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
