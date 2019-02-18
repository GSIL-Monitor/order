package com.emotte.dubbo.order;

import com.emotte.kernel.exception.BaseException;

import net.sf.json.JSONObject;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;

public interface OrderInterfaceService {

    /**
     * 订单新增、修改和删除接口
     *
     * @param json
     * @return
     */
    public String insertOrUpdateOrder(String json);

    /**
     * 订单批量转单用接口
     *
     * @param json
     * @return
     */
    @Deprecated
    public JSONObject updateOrderForTrack(String json);
    /*public String updateOrderForTrack(String json);*/

    /**
     * 结算单新增、修改和删除接口
     *
     * @param json
     * @return
     */
    public String insertOrUpdateAccount(String json);

    /**
     * 缴费单新增、修改和删除接口
     *
     * @param json
     * @return
     */
    public String insertOrUpdatePayfee(String json);

    /**
     * 结算和缴费同时操作
     *
     * @param json
     * @return
     */
    public String insertOrUpdateAccountAndPayfee(String json);

    /**
     * 售后单状态更新接口
     *
     * @param afterSaleJson
     * @return
     */
    public String updateAfterSales(String afterSaleJson);

    /**
     * 新增售后单接口
     *
     * @param afterSaleJson
     * @return
     */
    public String insertAfterSales(String afterSaleJson);

    /**
     * json包含一个参数 custId 用户ID
     *
     * @param json
     * @return
     */
    public String updateOrderFollow(String json);

    /**
     * 新增FA与他营单次结算单后，修改订单状态接口
     *
     * @param json
     * @return
     */
    public String updateOrderByAccount(String json);

    /**
     * 新增阿里云用户
     *
     * @param json
     * @return
     */
    public String insertOrderAliUser(String json);

    /**
     * 订单分发
     *
     * @param json <p>
     *             {<br>
     *             city: String 城市码<br>
     *             orderChannel: String 渠道ID<br>
     *             productCode : String 产品码<br>
     *             }
     *             </p>
     * @return <p>
     * {<br>
     * flag: 处理标识，1:成功，0:失败<br>
     * msg:消息<br>
     * data:{ rechargeBy: 负责人ID, rechargeDept: 负责部门ID}<br>
     * }
     * </p>
     * 2017年1月13日
     */
    public String distribute(String json);

    /**
     * 微指数订单、结算单、缴费接口
     *
     * @param json
     * @return
     */
    public String insertVmengOrder(String json);

    /**
     * 同步三方平台订单状态
     *
     * @param data
     * @return
     */
    public String updatePlateOrder(String data) throws BaseException;

    /**
     * 通过售后单状态同步三方平台订单状态
     *
     * @param data
     * @return
     */
    public String updatePlateOrderByAfterSale(String data);

    /**
     * 售后单状态更新接口（对外）
     *
     * @param afterSaleJson
     * @return
     */
    public String updateAfterSalesForOutside(String afterSaleJson);

    /**
     * @param json
     * @return String
     * @Description: 绑定活动结算单接口
     * @author lidenghui
     * @date 2018年4月2日 下午5:50:54
     * @version
     */
    public String insertActivityAccount(String json);

    /**
     * 分期回购新增售后
     *
     * @param afterSalesNew 售后单JSON字符串
     * @throws RedisAccessException
     */
    void buyBackAfterSales(String afterSalesNew) throws RedisAccessException;

    /**
     * 离职交接
     * 批量转订单
     *
     * @param json
     * @return
     */
    public String updateOrderHandover(String json);

    /**
     * 离职交接
     * 批量转解决方案
     *
     * @param json
     * @return
     */
    public String updateSolutionHandover(String json);

    /**
     * 功能描述: APP提交售后
     *
     * @param: json字符串售后单对象
     * @return:
     * @auther: lenovo
     * @date: 2018/7/20 15:02
     */
    String saveAfterSalesForAPP(String json);

    /**
     * 根据结算单ID删除结算缴费
     *
     * @param json
     * @return
     * @Author zhanghao
     * @Date 20190118
     */
    String deleteAccountAndPayFeeByAccountId(String json);
}
