package com.emotte.order.order.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * T_SOLUTION_CUST_SOLUTION_PLAN
 * Created by lenovo on 2018/5/25.
 */
public class ModeSolutionCustSolutionPlan implements Serializable{

    private Long id;//主键ID
    private Long cust_solution_item_id;//套餐ID
    private Long solution_cust__solution_id;//解决方案ID
    private Long order_id;//订单ID
    private Long type_id;//类型ID
    private Date service_date;//送货时间
    private String customer_name;//接收人姓名
    private String customer_phone;//接收人手机号
    private String customer_province;//接收人省份
    private String customer_city;//接收人城市
    private String customer_area;//接收人地区
    private String customer_address;//接收人地址
    private String blessing;//祝福语
    private String remark;//备注
    private Date create_time;//创建时间
    private Long create_by;//创建者
    private Date update_time;//修改时间
    private Long update_by;//修改人
    private Long version;//版本号
    private String mcode;//市场渠道来源编码
    private Long del_flag;//排期删除标识
    private Long qty_once;//单次配送数量
    private BigDecimal once_prirce;//商品单价
    private String receiver_city_code;//接收人地区编码
    private Long customer_longitude;//经度
    private Long customer_latitude;//纬度
    private String is_old_data;//是否老数据
    private Long temp_delflag;//排期删除标识：1删除 2 正常 3 暂停 4售后取消
    private String log;//日志
    private Long plan_freeze;//排期是否冻结：1是，2否

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCust_solution_item_id() {
        return cust_solution_item_id;
    }

    public void setCust_solution_item_id(Long cust_solution_item_id) {
        this.cust_solution_item_id = cust_solution_item_id;
    }

    public Long getSolution_cust__solution_id() {
        return solution_cust__solution_id;
    }

    public void setSolution_cust__solution_id(Long solution_cust__solution_id) {
        this.solution_cust__solution_id = solution_cust__solution_id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Date getService_date() {
        return service_date;
    }

    public void setService_date(Date service_date) {
        this.service_date = service_date;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_province() {
        return customer_province;
    }

    public void setCustomer_province(String customer_province) {
        this.customer_province = customer_province;
    }

    public String getCustomer_city() {
        return customer_city;
    }

    public void setCustomer_city(String customer_city) {
        this.customer_city = customer_city;
    }

    public String getCustomer_area() {
        return customer_area;
    }

    public void setCustomer_area(String customer_area) {
        this.customer_area = customer_area;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
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

    public String getMcode() {
        return mcode;
    }

    public void setMcode(String mcode) {
        this.mcode = mcode;
    }

    public Long getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Long del_flag) {
        this.del_flag = del_flag;
    }

    public Long getQty_once() {
        return qty_once;
    }

    public void setQty_once(Long qty_once) {
        this.qty_once = qty_once;
    }

    public BigDecimal getOnce_prirce() {
        return once_prirce;
    }

    public void setOnce_prirce(BigDecimal once_prirce) {
        this.once_prirce = once_prirce;
    }

    public String getReceiver_city_code() {
        return receiver_city_code;
    }

    public void setReceiver_city_code(String receiver_city_code) {
        this.receiver_city_code = receiver_city_code;
    }

    public Long getCustomer_longitude() {
        return customer_longitude;
    }

    public void setCustomer_longitude(Long customer_longitude) {
        this.customer_longitude = customer_longitude;
    }

    public Long getCustomer_latitude() {
        return customer_latitude;
    }

    public void setCustomer_latitude(Long customer_latitude) {
        this.customer_latitude = customer_latitude;
    }

    public String getIs_old_data() {
        return is_old_data;
    }

    public void setIs_old_data(String is_old_data) {
        this.is_old_data = is_old_data;
    }

    public Long getTemp_delflag() {
        return temp_delflag;
    }

    public void setTemp_delflag(Long temp_delflag) {
        this.temp_delflag = temp_delflag;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Long getPlan_freeze() {
        return plan_freeze;
    }

    public void setPlan_freeze(Long plan_freeze) {
        this.plan_freeze = plan_freeze;
    }
}
