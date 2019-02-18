package com.emotte.order.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 解决方案封装类
 * Created by lenovo on 2018/5/24.
 */
public class ModeSolutionCustSolution implements Serializable{

    private Long id;//主键
    private Long solution_code;//解决方案号
    private String order_source_id;//订单来源ID
    private Long cust_id;//订单客户ID
    private Long manager_id;//管家ID
    private Long is_invoice;//是否开发票
    private String invoice_type;//发票类型
    private String invoice_memo;//发票详细
    private Long solution_status;//解决方案状态
    private String pay_status;//支付状态
    private BigDecimal total_pay;//总金额
    private BigDecimal real_pay;//实际支付金额
    private BigDecimal had_pay;//已支付金额
    private String receiver_name;//接收人名称
    private String receiver_province;//接收人省份
    private String receiver_city;//接收人城市
    private String receiver_area;//接收人地区
    private String receiver_zipCode;//接收人邮编
    private String receiver_address;//接收人详细地址
    private String receiver_mobile;//接收人移动电话
    private String receiver_email;//接收人邮箱
    private String payer_name;//支付人名称
    private String payer_province;//支付人省份
    private String payer_city;//支付人城市
    private String payer_area;//支付人地区
    private String payer_zipCode;//支付人邮编
    private String payer_address;//支付人详细地址
    private String payer_mobile;//支付人移动电话
    private String payer_email;//支付人邮箱
    private String memo;//方案说明
    private Date start_service_time;//首次服务时间
    private Date end_time;//结束时间
    private Date start_time;//开始时间
    private String ip;//订单创建者所在ip
    private Integer type;//解决方案订单类型
    private String invite_code;//邀请码
    private Long cid;//门店ID
    private Date create_time;//创建时间
    private Long create_by;//创建者
    private Date update_time;//修改时间
    private Long update_by;//修改者
    private Long version;//版本号
    private String wcode;//市场渠道来源编码
    private BigDecimal active_money;//可活动余额
    private String blessing;//祝福语
    private String remark;//备注
    private String receiver_city_code;//接收人地区编码
    private String user_city_code;//支付人地区编码
    private String fee_card_num;//代扣卡卡号
    private Long receiver_longitude;//经度
    private Long receiver_latitude;//纬度
    private Long show_flag;//前端客户是否展示
    private String is_old_data;//是否老数据
    private Long iou_flag;//白条状态
    private String old_card;//新卡备份
    private String log;//日志
    private Long is_freeze;//是否冻结
    private Long has_solution;//是否有特殊方案
    private Long order_id;//关联订单ID

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSolution_code() {
        return solution_code;
    }

    public void setSolution_code(Long solution_code) {
        this.solution_code = solution_code;
    }

    public String getOrder_source_id() {
        return order_source_id;
    }

    public void setOrder_source_id(String order_source_id) {
        this.order_source_id = order_source_id;
    }

    public Long getCust_id() {
        return cust_id;
    }

    public void setCust_id(Long cust_id) {
        this.cust_id = cust_id;
    }

    public Long getManager_id() {
        return manager_id;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }

    public Long getIs_invoice() {
        return is_invoice;
    }

    public void setIs_invoice(Long is_invoice) {
        this.is_invoice = is_invoice;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_memo() {
        return invoice_memo;
    }

    public void setInvoice_memo(String invoice_memo) {
        this.invoice_memo = invoice_memo;
    }

    public Long getSolution_status() {
        return solution_status;
    }

    public void setSolution_status(Long solution_status) {
        this.solution_status = solution_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public BigDecimal getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(BigDecimal total_pay) {
        this.total_pay = total_pay;
    }

    public BigDecimal getReal_pay() {
        return real_pay;
    }

    public void setReal_pay(BigDecimal real_pay) {
        this.real_pay = real_pay;
    }

    public BigDecimal getHad_pay() {
        return had_pay;
    }

    public void setHad_pay(BigDecimal had_pay) {
        this.had_pay = had_pay;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_province() {
        return receiver_province;
    }

    public void setReceiver_province(String receiver_province) {
        this.receiver_province = receiver_province;
    }

    public String getReceiver_city() {
        return receiver_city;
    }

    public void setReceiver_city(String receiver_city) {
        this.receiver_city = receiver_city;
    }

    public String getReceiver_area() {
        return receiver_area;
    }

    public void setReceiver_area(String receiver_area) {
        this.receiver_area = receiver_area;
    }

    public String getReceiver_zipCode() {
        return receiver_zipCode;
    }

    public void setReceiver_zipCode(String receiver_zipCode) {
        this.receiver_zipCode = receiver_zipCode;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_email() {
        return receiver_email;
    }

    public void setReceiver_email(String receiver_email) {
        this.receiver_email = receiver_email;
    }

    public String getPayer_name() {
        return payer_name;
    }

    public void setPayer_name(String payer_name) {
        this.payer_name = payer_name;
    }

    public String getPayer_province() {
        return payer_province;
    }

    public void setPayer_province(String payer_province) {
        this.payer_province = payer_province;
    }

    public String getPayer_city() {
        return payer_city;
    }

    public void setPayer_city(String payer_city) {
        this.payer_city = payer_city;
    }

    public String getPayer_area() {
        return payer_area;
    }

    public void setPayer_area(String payer_area) {
        this.payer_area = payer_area;
    }

    public String getPayer_zipCode() {
        return payer_zipCode;
    }

    public void setPayer_zipCode(String payer_zipCode) {
        this.payer_zipCode = payer_zipCode;
    }

    public String getPayer_address() {
        return payer_address;
    }

    public void setPayer_address(String payer_address) {
        this.payer_address = payer_address;
    }

    public String getPayer_mobile() {
        return payer_mobile;
    }

    public void setPayer_mobile(String payer_mobile) {
        this.payer_mobile = payer_mobile;
    }

    public String getPayer_email() {
        return payer_email;
    }

    public void setPayer_email(String payer_email) {
        this.payer_email = payer_email;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getStart_service_time() {
        return start_service_time;
    }

    public void setStart_service_time(Date start_service_time) {
        this.start_service_time = start_service_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Long create_by) {
        this.create_by = create_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Long getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Long update_by) {
        this.update_by = update_by;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getWcode() {
        return wcode;
    }

    public void setWcode(String wcode) {
        this.wcode = wcode;
    }

    public BigDecimal getActive_money() {
        return active_money;
    }

    public void setActive_money(BigDecimal active_money) {
        this.active_money = active_money;
    }

    public String getBlessing() {
        return blessing;
    }

    public void setBlessing(String blessing) {
        this.blessing = blessing;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiver_city_code() {
        return receiver_city_code;
    }

    public void setReceiver_city_code(String receiver_city_code) {
        this.receiver_city_code = receiver_city_code;
    }

    public String getUser_city_code() {
        return user_city_code;
    }

    public void setUser_city_code(String user_city_code) {
        this.user_city_code = user_city_code;
    }

    public String getFee_card_num() {
        return fee_card_num;
    }

    public void setFee_card_num(String fee_card_num) {
        this.fee_card_num = fee_card_num;
    }

    public Long getReceiver_longitude() {
        return receiver_longitude;
    }

    public void setReceiver_longitude(Long receiver_longitude) {
        this.receiver_longitude = receiver_longitude;
    }

    public Long getReceiver_latitude() {
        return receiver_latitude;
    }

    public void setReceiver_latitude(Long receiver_latitude) {
        this.receiver_latitude = receiver_latitude;
    }

    public Long getShow_flag() {
        return show_flag;
    }

    public void setShow_flag(Long show_flag) {
        this.show_flag = show_flag;
    }

    public String getIs_old_data() {
        return is_old_data;
    }

    public void setIs_old_data(String is_old_data) {
        this.is_old_data = is_old_data;
    }

    public Long getIou_flag() {
        return iou_flag;
    }

    public void setIou_flag(Long iou_flag) {
        this.iou_flag = iou_flag;
    }

    public String getOld_card() {
        return old_card;
    }

    public void setOld_card(String old_card) {
        this.old_card = old_card;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Long getIs_freeze() {
        return is_freeze;
    }

    public void setIs_freeze(Long is_freeze) {
        this.is_freeze = is_freeze;
    }

    public Long getHas_solution() {
        return has_solution;
    }

    public void setHas_solution(Long has_solution) {
        this.has_solution = has_solution;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }
}
