<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
<head>
    <meta charset=utf-8">
    <script type="text/javascript" src="${ctx}/js/quality.js"></script>
    <title>售后信息</title>
</head>
<body>
<div class="widget-content">
    <div id="tableDetail">
        <table class="table table-condensed">
            <input type="hidden" id="saleId"></input>
            <input type="hidden" id="orderId"></input>
            <input type="hidden" id="saleKind"></input>
            <input type="hidden" id="auditFlag"></input>
            <!-- <input type="hidden" id="totalPay"></input> -->
            <tr>
                <td colspan="2"><span><h4>订单信息</h4></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">订单编号：
                    <lable id="order_code"></lable>
                </td>
            </tr>
            <tr>
                <td>订单类型：
                    <lable id="order_type"></lable>
                </td>
                <td>订单状态：
                    <lable id="order_status"></lable>
                </td>
            </tr>
            <tr id="FACreTr">
                <td colspan="2">创建时间：
                    <lable id="order_creTime"></lable>
                </td>
            </tr>

            <tr id="ServerTr1">
                <td colspan="2">开始时间：
                    <lable id="order_startTime"></lable>
                </td>
            </tr>
            <tr id="ServerTr2">
                <td colspan="2">结束时间：
                    <lable id="order_endTime"></lable>
                </td>
            </tr>
            <tr id="ServerTr3">
                <td colspan="2">服务地址：
                    <lable id="order_serverAddress"></lable>
                </td>
            </tr>

            <tr>
                <td>订单来源：
                    <lable id="order_source"></lable>
                </td>
                <td>订单渠道：
                    <lable id="order_channel"></lable>
                </td>
            </tr>
            <tr>
                <td>收货人：
                    <lable id="order_receiverName"></lable>
                </td>
                <td>联系方式：
                    <lable id="order_receiverMobile"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">收货地址：
                    <lable id="order_receiverAddress"></lable>
                </td>
            </tr>
            <!-- <tr>
                <td colspan="3">送货时间：<lable id=""></lable></td>
            </tr> -->
            <tr>
                <td colspan="2"><span><h4>客户信息</h4></span>
                </td>
            </tr>
            <tr>
                <td>客户姓名：
                    <lable id="qd_custName"></lable>
                </td>
                <td>联系方式：
                    <lable id="qd_custMobile"></lable>
                </td>
            </tr>
            <tr id="TKTitle">
                <td colspan="2"><span><h4>退货商品</h4></span>
                </td>
            </tr>
            <tr id="TKFA">
                <td colspan="2">
                    <table class="table table-hover table-striped">
                        <thead>
                        <th style="width:15%">序号</th>
                        <th style="width:35%">商品名称</th>
                        <th style="width:25%">价格</th>
                        <th style="width:25%">数量</th>
                        </thead>
                        <tbody id="refundFAInfo">
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2"><span><h4>售后信息</h4></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后状态：
                    <lable id="qd_auditFlag"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后类型：
                    <lable id="qd_salesKind"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">退费总额：
                    <lable id="qd_total"></lable>
                    元
                </td>
            </tr>
            <tr id="vipshopDetailTr">
                <td colspan="2">分期退款金额：
                    <lable id="qd_vipshop_total"></lable>
                    元
                </td>
            </tr>
            <tr>
                <td colspan="2">原因：
                    <lable id="qd_reason"></lable>
                </td>
                <!-- <textarea cols="50" class="form-control form-textarea" rows="2"  id="" readonly="readonly"></textarea> -->
            </tr>
            <!-- <tr id="qd_afterSalesRemark">
                <td colspan="2"><p class="pb10">驳回/退款失败原因：</p>
               <textarea cols="50" class="form-control form-textarea" rows="2"  id="qd_remark"></textarea> </td>
            </tr> -->

        </table>
    </div>
    <div id="tableSolutionDetail">
        <div class="project-order-news">
            <div class="row">
                <div class="col-xs-12">
                    <h4>方案信息</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <span>创建时间：</span>
                    <lable id="sd_createTime"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-12">
                    <span>客户：</span>
                    <lable id="sd_custName"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-12">
                    <span>客户电话：</span>
                    <lable id="sd_custMobile"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-12">
                    <span>管家：</span>
                    <lable id="sd_payerName"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-12">
                    <span>管家电话：</span>
                    <lable id="sd_payerPhone"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-12">
                    <span>订单金额：</span>
                    <lable id="sd_orderPrice"></lable>
                    元
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-6">
                    <span>状态：</span>
                    <lable id="sd_orderStatus"></lable>
                </div>
                <div class=" col-xs-6">
                    <span>代扣卡号：</span>
                    <lable id="sd_withholdingCard"></lable>
                </div>
            </div>
            <div class="row">
                <div class=" col-xs-6">
                    <span>订单来源：</span>
                    <lable id="sd_order_source_id"></lable>
                </div>
                <div class=" col-xs-6">
                    <span>渠道：</span>
                    <lable id="sd_mcode"></lable>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="panel-content table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                            <tr align="center">
                                <th width="5%">序号</th>
                                <th width="15%" align="center">项目</th>
                                <th width="10%" align="center">数量</th>
                                <th width="10%" align="center">剩余数量</th>
                                <th width="10%" align="center">每次配送数量</th>
                                <th width="25%" align="center">服务/配送频次</th>
                                <th width="25%" align="center">首次/配送时间</th>
                            </tr>
                            </thead>
                            <tbody id="sd_solutionCombos">

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <h4>售后信息</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <span>售后状态：</span>
                    <lable id="sd_auditFlag"></lable>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <span>售后类型：</span>
                    <lable id="sd_salesKind"></lable>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <span>退费总额：</span>
                    <lable id="sd_total"></lable>
                    元
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12 pb10">
                    <span>原因：</span>
                    <lable id="sd_reason"></lable>
                </div>
            </div>
            <!-- <div class="row">
                <div class="col-xs-12 pb10">
                    <span>备注：</span>
                    <textarea cols="50" class="form-control form-textarea" rows="2"  id="sd_remark" ></textarea>
                </div>
            </div> -->
        </div>
    </div>
    <div id="tableNoData"></div>
    <div id="tableBuyBackDetail">
        <table class="table table-condensed">
            <input type="hidden" id="saleId"></input>
            <input type="hidden" id="orderId"></input>
            <input type="hidden" id="saleKind"></input>
            <input type="hidden" id="auditFlag"></input>
            <tr>
                <td colspan="2"><span><h4>回购信息</h4></span></td>
            </tr>
            <tr>
                <td colspan="2">订单编号：
                    <lable id="sc_order_code"></lable>
                </td>
            </tr>
            <%--<tr>--%>
            <%--<td colspan="2">服务费金额：<lable id="sc_salary_amount"></lable></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td colspan="2">服务费批次：<lable id="sc_salary_number"></lable></td>--%>
            <%--</tr>--%>
            <tr>
                <td colspan="2">合同编号：
                    <lable id="sc_agreement_code"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">回购金额：
                    <lable id="sc_buyBack_amount"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2"><span><h4>客户信息</h4></span>
                </td>
            </tr>
            <tr>
                <td>客户姓名：
                    <lable id="sc_custName"></lable>
                </td>
                <td>联系方式：
                    <lable id="sc_custMobile"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2"><span><h4>售后信息</h4></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后状态：
                    <lable id="sc_auditFlag"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后类型：
                    <lable id="sc_salesKind"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">退费总额：
                    <lable id="sc_total"></lable>
                    元
                </td>
            </tr>
            <tr>
                <td colspan="2">原因：
                    <lable id="sc_reason"></lable>
                </td>
            </tr>
        </table>
    </div>
    <div id="tableCardDetail">
        <table class="table table-condensed">
            <tr>
                <td colspan="2"><span><h4>卡信息</h4></span></td>
            </tr>
            <tr>
                <td colspan="2">卡号：
                    <lable id="card_number"></lable>
                </td>
                <td colspan="2">
                    <lable id="card_status"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">卡面值：
                    <lable id="card_amount"></lable>
                </td>
                <td colspan="2">卡余额：
                    <lable id="card_balance"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">购卡来源：
                    <lable id="card_source"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">推卡人：
                    <lable id="card_recommendName"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">卡提成：
                    <lable id="card_give"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">管家账户余额(退卡后)：
                    <lable id="card_accountBalance"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <span><h4>客户信息</h4></span>
                </td>
            </tr>
            <tr>
                <td>客户姓名：
                    <lable id="card_custName"></lable>
                </td>
                <td>联系方式：
                    <lable id="card_custMobile"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2"><span><h4>售后信息</h4></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后状态：
                    <lable id="card_auditFlag"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">售后类型：
                    <lable id="card_salesKind"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">退费总额：
                    <lable id="card_total"></lable>
                    元
                </td>
            </tr>
            <tr>
                <td colspan="2">原因：
                    <lable id="card_reason"></lable>
                </td>
            </tr>
        </table>
    </div>
</div>
<!-- <div class="text-c mt10 mb10">
    <button id="pass" class="btn btn-primary"  type="button" onclick="qualityPass();">审批通过</button>&nbsp;&nbsp;
    <button id="notPass"class="btn btn-primary"  type="button" onclick="qualityNotPass();">审批不通过</button>
    <button id="reSubmitBtn"class="btn btn-primary"  type="button" onclick="qualityReSubmit();">重新提交</button>
</div> -->
</body>
</html>