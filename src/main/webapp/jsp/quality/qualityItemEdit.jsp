<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" href="${ctx}/css/fileinput.css" media="all" rel="stylesheet"/>
    <script src="${ctx}/js/fileinput.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<style>
    .form-inline .input-group > .file-caption {
        width: 100%;
        height: 33px;
    }

    .form-inline .input-group .input-group-btn {
        width: 1%;
        height: 30px;
    }
</style>
<body>
<div class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        onclick="closeModule('qualityItemEdit')">×
                </button>
                <h4 class="modal-title" id="uitemModalTitle">修改售后</h4>
            </div>
            <form class="form-inline" id="uqualityItemForm">
                <input type="hidden" id="uitemStatusHidden"/>
                <input type="hidden" id="uItemBankCardMoney"/>
                <input type="hidden" id="uItemRemainRefundTotal"/>
                <input type="hidden" id="uItemIsApp"/>
                <input type="hidden" id="uItemAfterSalesKind"/>
                <input type="hidden" id="uItemPersonnelStatus"/>
                <input type="hidden" id="uItemAfterSalesImgs"/>
                <div class="modal-body">
                    <div class="modal-content-basic">
                        <header><h4>客户信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>客户姓名：</p>
                                        <span id="uitemUserName" name="custName"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>联系方式：</p>
                                        <span id="uitemMobile" name="custMobile"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <header><h4>订单信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单编号：</p>
                                        <span id="uitemOrderCode"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单类型：</p>
                                        <span id="uitemOrderType"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>支付状态：</p>
                                        <span id="uitemPayText"></span>
                                    </label>
                                </div>

                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>收货人姓名：</p>
                                        <span id="uitemReceiptName"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>收货人电话：</p>
                                        <span id="uitemReceiptMobile"></span>
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>收货人地址：</p>
                                        <span id="uitemReceiptAddress"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单总金额：</p>
                                        <span id="uitemTotalPay"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>配送金额：</p>
                                        <span id="uitemDeliver"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单状态：</p>
                                        <span id="uitemStatus"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单渠道：</p>
                                        <span id="uitemOrderChannel"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单来源：</p>
                                        <span id="uitemOrderSource"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>订单备注：</p>
                                        <textarea rows="2" id="uitemOrderRemark" style="height:50px;"
                                                  class="form-control form-textarea" readonly="readonly"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <header><h4>需求信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped">
                                    <thead>
                                    <tr>
                                        <th style="width:15%">序号</th>
                                        <th style="width:35%">商品名称</th>
                                        <th style="width:25%">价格</th>
                                        <th style="width:25%">数量</th>
                                    </tr>
                                    </thead>
                                    <tbody id="uFAInfo">
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <header><h4>售后信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>售后类型：</p>
                                        <input class="form-control" id="uitem_afterSalesKind"
                                               readonly="readonly"></input>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批部门:</p>
                                        <select id="uitemApproveDeptSelect" name="approveDept" class="form-control"
                                                style="width:260px">
                                            <option value="-1">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批人:</p>
                                        <select id="uitemApproveBySelect" name="approveBy" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>退费总额（元）：</p>
                                        <span id="uitemReturnTotal" name="refundTotal"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>退款明细（元）：
	                            <span id="urefundDetail">
	                        </span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        是否填写银行信息：
								   <span>
									   <label>
                                           <input type="radio" id="trueBank" name="selectBank" value="1" checked>是
                                       </label>
									   &nbsp;&nbsp;&nbsp;&nbsp;
									   <label>
                                           <input type="radio" id="falseBank" name="selectBank" value="2">否
                                       </label>
								   </span>
                                    </label>
                                </div>
                            </div>
                            <hr>
                            <div class="row" id="uItem_bank">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>&nbsp;&nbsp;银行：</p>
                                        <select class="form-control" name="bankSupportId" id="uitem_bankSupportId">
                                            <option value="">--请选择--</option>
                                            <option value="1">工商银行</option>
                                            <option value="2">建设银行</option>
                                            <option value="3">农业银行</option>
                                            <option value="4">中国银行</option>
                                            <option value="5">兴业银行</option>
                                            <option value="6">民生银行</option>
                                            <option value="7">招商银行</option>
                                            <option value="8">交通银行</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="uItem_bankCity">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>&nbsp;&nbsp;开户省份：</p>
                                        <select class="form-control" name="validateProvince" id="uitem_bankProvinceCode"
                                                onclick="setSelCity('uitem_bankProvinceCode','uitem_bankCityCode')">
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>开户城市：</p>
                                        <select class="form-control" name="bankCityCode" id="uitem_bankCityCode">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="uItem_bankCN">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>&nbsp;&nbsp;银行卡号：</p>
                                        <input type="text" name="bankCard" style="width:200px" class="form-control"
                                               id="uitem_bankCard"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>开户行：</p>
                                        <input type="text" name="bankName" class="form-control" id="uitem_bankName"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="uItem_bankUn">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>&nbsp;&nbsp;持卡人：</p>
                                        <input type="text" name="bankUserName" class="form-control"
                                               id="uitem_bankUserName"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>&nbsp;&nbsp;原因：</p>
                                        <textarea rows="3" name="reason" style="height:50px;" id="uitem_reason"
                                                  class="form-control form-textarea"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal"
                            onclick="closeModule('qualityItemEdit')">取消
                    </button>
                    <button type="submit" class="btn btn-sm btn-primary" id="uqualityItemBtn">保存</button>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        var orderId = '${param.orderId}';
        var afterSalesId = '${param.afterSalesId}';
    	var cateType = '${param.cateType}';
    	if(cateType == 1 || cateType == 4){
    		$("#uitemModalTitle").html("修改售后>>修改单次售后单");
    	}else if(cateType == 3){
    		$("#uitemModalTitle").html("修改售后>>修改FA售后单");
    	}
        //加载省份
        setSelProvinceCitys('101', 6, 'uitem_bankProvinceCode');
        // 查询订单信息
        ugetQualityItem(orderId);
        //查询审核部门
        queryApproveDeptName("uitemApproveDeptSelect");
        //展示售后单信息
        ushowAfterSales(afterSalesId);
        //退款总额显示
        //ushowTotal(orderId);
        //缴费信息
        uItemqueryPayFeeById(orderId);
        //20180612他人代收类型可不填写银行账户信息，提交表单js绕过校验
        $(":radio").click(function () {
            var check = $(this).val();
            if (check == "2") {//不填写银行信息
                $("#uItem_bank").hide();
                $("#uItem_bankCity").hide();
                $("#uItem_bankCN").hide();
                $("#uItem_bankUn").hide();
                $.confirm({
                    text: "您选择了不填写银行信息!只有在对所需退费金额为0的订单取消时才操作此项,否则会因财务无法退费驳回!", callback: function (r) {
                        if (r == false) {
                            $("#trueBank").click();
                        } else {
                            $("#uitem_bankSupportId").val("");
                            $("#uitem_bankProvinceCode").val("");
                            $("#uitem_bankCityCode").val("");
                            $("#uitem_bankCard").val("");
                            $("#uitem_bankName").val("");
                            $("#uitem_bankUserName").val("");
                        }
                    }
                })
            } else {
                $("#uItem_bank").show();
                $("#uItem_bankCity").show();
                $("#uItem_bankCN").show();
                $("#uItem_bankUn").show();
            }
        });
    });
    //展示售后单信息
    function ushowAfterSales(afterSalesId) {
        var auditFlag = '${param.auditFlag}';
        $.ajax({
            url: ctx + '/afterSales/loadAfterSales',
            data: {
                id: afterSalesId,
            },
            type: 'post',
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var afterSalesKindArr={1:"FA订单取消",2:"FA订单退货/退款",3:"FA订单换货重发",4:"单次服务订单取消",5:"延续性服务订单取消",6:"解决方案订单取消",7:"延续性服务订单换人",8:"延续性服务订单终止",9:"解决方案订单退费",10:"延续性服务订单退费",11:"唯品会白条分期退费",12:"迁单",13:"唯品会白条分期服务费退费",14:"海金保理白条分期服务费退费",15:"招行分期分期服务费退费",17:"海金保理白条分期退费",18:"招行分期退费",19:"分期回购海金保理",20:"分期回购唯品会 ",16:"卡退费 ",21:"汇嘉分期退费"};
                    var asObj = data.afterSales;
                    //其他信息
                    $("#uitem_afterSalesKind").val(asObj.afterSalesKind);
                    //银行
                    if (asObj.bankSupportId != "" && asObj.bankSupportId != null) {
                        $("#uitem_bankSupportId").val(asObj.bankSupportId);
                    } else {
                        $("#uitem_bankSupportId").val("");
                    }
                    //省、市
                    if (asObj.bankCityCode != "" && asObj.bankCityCode != null) {
                        var code = asObj.bankCityCode.substr(0, 6);
                        $("#uitem_bankProvinceCode option[value='" + code + "']").attr("selected", "selected");
                        setSelProvinceCitys(asObj.bankCityCode, 9, "uitem_bankCityCode");
                        $("#uitem_bankCityCode option[value='" + asObj.bankCityCode + "']").attr("selected", "selected");
                    } else {
                        $("#uitem_bankCityCode").val("");
                    }
                    $("#uitem_bankCard").val(asObj.bankCard);
                    $("#uitem_bankName").val(asObj.bankName);
                    $("#uitem_bankUserName").val(asObj.bankUserName);
                    $("#uitem_reason").val(asObj.reason);
                    $("#uitemReturnTotal").text(intToDecimal(asObj.refundTotal)); //退款总额
//                    $("#uitem_afterSalesKind").val("FA订单取消");
                    $("#uitem_afterSalesKind").val(afterSalesKindArr[asObj.afterSalesKind]);//update 20190108 zhanghao
                    if (asObj.auditFlag == "20130013") {//如果售后是新建状态，不允许更改银行信息
                        $("#uitem_bankSupportId").attr("disabled", true);
                        $("#uitem_bankCard").attr("disabled", true);
                        $("#uitem_bankName").attr("disabled", true);
                        $("#uitem_bankUserName").attr("disabled", true);
                        $("#uitem_reason").attr("disabled", true);
                        $("#uitem_bankProvinceCode").attr("disabled", true);
                        $("#uitem_bankCityCode").attr("disabled", true);
                    }
                    //审核部门回显
                    $("#uitemApproveDeptSelect option").each(function (i) {
                        var udept = $(this).val();
                        if (udept != undefined && udept != "") {
                            if (udept == asObj.approveDept) {
                                $(this).attr("selected", true);
                            }
                        }
                    })
                    //审核人回显
                    $("#uitemApproveBySelect").html("<option value='" + asObj.approveBy + "' >" + asObj.approveByText + "</option>");
                    if (auditFlag == '20130002' || auditFlag == '20130005' || auditFlag == '20130009') {
                        $("#uitemApproveDeptSelect").attr("disabled", "disabled");
                        $("#uitemApproveBySelect").attr("disabled", "disabled");
                    } else {
                        $("#uitemApproveDeptSelect").removeAttr("disabled");
                        $("#uitemApproveBySelect").removeAttr("disabled");
                    }
                    //隐藏域赋值
                    $("#uItemIsApp").val(asObj.isApp);//是否移动端售后
                    $("#uItemAfterSalesKind").val(asObj.afterSalesKind);//售后类型
                    $("#uItemPersonnelStatus").val(asObj.personnelStatus);//移动端服务状态
                    $("#uItemAfterSalesImgs").val(asObj.afterSalesImgs);//移动端服务状态
                }
            }
        })
    }
    //订单信息
    function ugetQualityItem(orderId) {
        $.ajax({
            url: ctx + "/order/queryOrderBasicItem",
            data: {
                id: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        $("#uitemUserName").html(v.userName);
                        $("#uitemMobile").html(v.userMobile);
                        $("#uitemReceiptName").html(v.receiverName);
                        $("#uitemReceiptMobile").html(v.receiverMobile);
                        $("#uitemReceiptAddress").html(v.receiverAddress);
                        $("#uitemStatusHidden").val(v.orderStatus);
                        $("#uitemOrderCode").html(v.orderCode);
                        $("#uitemOrderType").html(v.typeText);
                        $("#uitemPayText").html(v.payText);
                        $("#uitemStatus").html(v.statusText);
                        $("#uitemDeliver").html(intToDecimal(v.deliverPay));
                        $("#uitemOrderChannel").html(v.channelText);
                        $("#uitemOrderSource").html(v.sourceText);
                        $("#uitemOrderRemark").html(v.item.remark);
                        $("#uitemTotalPay").html(intToDecimal(v.totalPay));
                        num = i + 1;
                        html += "<tr><td>" + num;
                        html += "</td><td>" + v.item.productName;
                        html += "</td><td>" + intToDecimal(v.item.nowPrice);
                        html += "</td><td>" + v.item.quantity;
                        html += "</td></tr><tr></tr>";
                    });
                    $("#uFAInfo").html(html);
                }
            }
        });
    }
    //缴费
    function uItemqueryPayFeeById(orderId) {
        var auditFlag = '${param.auditFlag}';
        $.ajax({
            url: ctx + "/payfee/queryAccount",
            data: {
                orderId: orderId,
                isBackType: 2,
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00') {
                    var ualiPayRefundTotal = 0;
                    var ubankRefundTotal = 0;
                    var udhRefundTotal = 0;
                    var uticketRefundTotal = 0;
                    var uposRefundTotal = 0;
                    var utaobaofundTotal = 0;
                    var uweixinRefundTotal = 0;
                    var urhPosRefundTotal = 0;
                    var uccbcRefundTotal = 0;
                    var ucardRefundTotal = 0;
                    var utyqRefundTotal = 0;
                    var uremainRefundTotal = 0;
                    var uotherRefundTotal = 0;
                    var uweixinSearchRefundTotal = 0;
                    var ubaitiaoRefundTotal = 0;
                    var ujialianPosweixinRefundTotal = 0;
                    var ujialianPosalipayRefundTotal = 0;
                    var ujialianPoscardRefundTotal = 0;
                    var ujialianweixinRefundTotal = 0;
                    var ujialianalipayRefundTotal = 0;
                    var ujialiancardRefundTotal = 0;
                    var uyiwangtongRefundTotal = 0;
                    var udaoweiRefundTotal = 0;
                    var uweizhishuRefundTotal = 0;
                    var uwangcaiRefundTotal = 0;
                    var html = "";
                    var dcAccountPayId = [];//结算单id,防止重复累加缴费
                    $.each(data.list, function (j, w) {
                        if(dcAccountPayId.contains(w.id)){
                            //防止重复累加缴费
                            return true;
                        }
                        dcAccountPayId.push(w.id);
                        $.ajax({
                            url: ctx + "/payfee/queryPayfee",
                            data: {
                                orderId: w.id,
                                isBackType: 1,
                                valid: 1
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.msg == '00') {
                                    $.each(data.list, function (i, x) {
                                        if (x.feePost == "20250001") {//支付宝
                                            ualiPayRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250002") {//银行卡
                                            ubankRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250003") {//电汇
                                            udhRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250004") {//支票
                                            uticketRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250005") {//pos机
                                            uposRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250006") {//淘宝支付
                                            utaobaofundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250007") {//微信退费
                                            uweixinRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250008") {//融汇天下POS
                                            urhPosRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250009") {//建行
                                            uccbcRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250010") {//卡支付
                                            ucardRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250011") {//体验券
                                            utyqRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250013") {//账户
                                            uremainRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250014") {//他人代收（三方订单）
                                            uotherRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250015") {//微信扫码支付
                                            uweixinSearchRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250016") {//白条支付
                                            ubaitiaoRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250017") {//嘉联pos微信支付
                                            ujialianPosweixinRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250018") {//嘉联pos支付宝支付
                                            ujialianPosalipayRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250019") {//嘉联pos刷卡支付
                                            ujialianPoscardRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250020") {//嘉联微信支付
                                            ujialianweixinRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250021") {//嘉联支付宝支付
                                            ujialianalipayRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250022") {//嘉联快捷支付
                                            ujialiancardRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250023") {//招商银行一网通支付
                                            uyiwangtongRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250024") {//支付宝到位
                                            udaoweiRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250025") {//微指数余额
                                            uweizhishuRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                        if (x.feePost == "20250026") {//旺财支付
                                            uwangcaiRefundTotal += intToDecimal(x.feeSum) * 1;
                                        }
                                    });
                                }
                            }
                        });
                    });
                    var totalPay = 0;//退费总计
                    if (ualiPayRefundTotal != "0") {
                        html += "<label>支付宝退费：<span>" + intToDecimal(ualiPayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ualiPayRefundTotal) * 1;
                    }
                    if (ubankRefundTotal != "0") {
                        html += "<label>银行打卡退费合计：<span>" + intToDecimal(ubankRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ubankRefundTotal) * 1;
                    }
                    if (udhRefundTotal != "0") {
                        html += "<label>电汇退费：<span>" + intToDecimal(udhRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(udhRefundTotal) * 1;
                    }
                    if (uticketRefundTotal != "0") {
                        html += "<label>支票退费：<span>" + intToDecimal(uticketRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uticketRefundTotal) * 1;
                    }
                    if (uposRefundTotal != "0") {
                        html += "<label>POS机退费：<span>" + intToDecimal(uposRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uposRefundTotal) * 1;
                    }
                    if (utaobaofundTotal != "0") {
                        html += "<label>淘宝退费：<span>" + intToDecimal(utaobaofundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(utaobaofundTotal) * 1;
                    }
                    if (uweixinRefundTotal != "0") {
                        html += "<label>微信退费：<span>" + intToDecimal(uweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uweixinRefundTotal) * 1;
                    }
                    if (urhPosRefundTotal != "0") {
                        html += "<label>融汇天下POS退费：<span>" + intToDecimal(urhPosRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(urhPosRefundTotal) * 1;
                    }
                    if (uccbcRefundTotal != "0") {
                        html += "<label>建行退费：<span>" + intToDecimal(uccbcRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uccbcRefundTotal) * 1;
                    }
                    if (ucardRefundTotal != "0") {
                        html += "<label>卡退费：<span>" + intToDecimal(ucardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ucardRefundTotal) * 1;
                    }
                    if (utyqRefundTotal != "0") {
                        html += "<label>体验券(不退)：<span>" + intToDecimal(utyqRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(utyqRefundTotal) * 1;
                    }
                    if (uremainRefundTotal != "0") {
                        html += "<label>账户退费：<span>" + intToDecimal(uremainRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uremainRefundTotal) * 1;
                    }
                    if (uotherRefundTotal != "0") {
                        html += "<label>他人代收退费：<span>" + intToDecimal(uotherRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uotherRefundTotal) * 1;
                    }
                    if (uweixinSearchRefundTotal != "0") {
                        html += "<label>微信扫码支付退费：<span>" + intToDecimal(uweixinSearchRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uweixinSearchRefundTotal) * 1;
                    }
                    if (ubaitiaoRefundTotal != "0") {
                        html += "<label>白条退费：<span>" + intToDecimal(ubaitiaoRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ubaitiaoRefundTotal) * 1;
                    }
                    if (ujialianPosweixinRefundTotal != "0") {
                        html += "<label>嘉联pos微信支付退费：<span>" + intToDecimal(ujialianPosweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialianPosweixinRefundTotal) * 1;
                    }
                    if (ujialianPosalipayRefundTotal != "0") {
                        html += "<label>嘉联pos支付宝支付退费：<span>" + intToDecimal(ujialianPosalipayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialianPosalipayRefundTotal) * 1;
                    }
                    if (ujialianPoscardRefundTotal != "0") {
                        html += "<label>嘉联pos刷卡支付退费：<span>" + intToDecimal(ujialianPoscardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialianPoscardRefundTotal) * 1;
                    }
                    if (ujialianweixinRefundTotal != "0") {
                        html += "<label>嘉联微信支付退费：<span>" + intToDecimal(ujialianweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialianweixinRefundTotal) * 1;
                    }
                    if (ujialianalipayRefundTotal != "0") {
                        html += "<label>嘉联支付宝支付退费：<span>" + intToDecimal(ujialianalipayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialianalipayRefundTotal) * 1;
                    }
                    if (ujialiancardRefundTotal != "0") {
                        html += "<label>嘉联快捷支付退费：<span>" + intToDecimal(ujialiancardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(ujialiancardRefundTotal) * 1;
                    }
                    if (uyiwangtongRefundTotal != "0") {
                        html += "<label>招商银行一网通支付退费：<span>" + intToDecimal(uyiwangtongRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uyiwangtongRefundTotal) * 1;
                    }
                    if (udaoweiRefundTotal != "0") {
                        html += "<label>支付宝到位退费：<span>" + intToDecimal(udaoweiRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(udaoweiRefundTotal) * 1;
                    }
                    if (uweizhishuRefundTotal != "0") {
                        html += "<label>微指数余额退费：<span>" + intToDecimal(uweizhishuRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uweizhishuRefundTotal) * 1;
                    }
                    if (uwangcaiRefundTotal != "0") {
                        html += "<label>旺财支付退费：<span>" + intToDecimal(uwangcaiRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                        totalPay += intToDecimal(uwangcaiRefundTotal) * 1;
                    }
                    //退款明细
                    $("#urefundDetail").html(html);
                    $("#uItemBankCardMoney").val(ucardRefundTotal);
                    $("#uItemRemainRefundTotal").val(uremainRefundTotal);
                    if(auditFlag == 20130013){//新增状态退款总额自动带入
                        $("#uitemReturnTotal").text(intToDecimal(totalPay));
                    }
                    var uitemReturnTotal = $("#uitemReturnTotal").text();
                    if (ucardRefundTotal != "0" && uitemReturnTotal == 0) {
                        $("#uItem_bank").hide();
                        $("#uItem_bankCity").hide();
                        $("#uItem_bankCN").hide();
                        $("#uItem_bankUn").hide();
                    } else if (utyqRefundTotal != "0" && uitemReturnTotal == 0) {
                        $("#uItem_bank").hide();
                        $("#uItem_bankCity").hide();
                        $("#uItem_bankCN").hide();
                        $("#uItem_bankUn").hide();
                    } else if (uremainRefundTotal != "0" && uitemReturnTotal == 0) {
                        $("#uItem_bank").hide();
                        $("#uItem_bankCity").hide();
                        $("#uItem_bankCN").hide();
                        $("#uItem_bankUn").hide();
                    }
                }
            }
        });
    }
    //更新操作
    function updateQualityItem() {
        var data = $("#uqualityItemForm").serialize();
        var afterSalesId = '${param.afterSalesId}';
        var custName = $("#uitemUserName").text();
        var custMobile = $("#uitemMobile").text();
        var itemReturnTotal = $("#uitemReturnTotal").text();
        var itemStatus = $("#uitemStatus").val();
        var uitemBankMainName = $("#uitem_bankSupportId").find("option:selected").text();
        var orderId = '${param.orderId}';
        var auditFlag = '${param.auditFlag}';
        var url = "";
        var ucardRefundTotal = $("#uItemBankCardMoney").val();
        var uremainRefundTotal = $("#uItemRemainRefundTotal").val();
        //验证审批部门
        var uitemApproveDept = $("#uitemApproveDeptSelect").find("option:selected").val();
        if (uitemApproveDept == "" || uitemApproveDept == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批部门！"});
            $("#uqualityItemBtn").removeAttr("disabled");
            return;
        }
        //验证审批人
        var uitemApproveBy = $("#uitemApproveBySelect").find("option:selected").val();
        if (uitemApproveBy == "" || uitemApproveBy == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批人！"});
            $("#uqualityItemBtn").removeAttr("disabled");
            return;
        }
        if (uitemBankMainName == "...请选择...") {
            uitemBankMainName = "";
        }
        //判断是否移动端售后
        if($("#uItemIsApp").val() == 1 && auditFlag == "20130013"){//订单是移动端售后并且状态为新增，跳转新增方法，触发售后流程
            var isApp = $("#uItemIsApp").val();
            var personnelStatus = $("#uItemPersonnelStatus").val();//服务状态
            var afterSalesImgs = $("#uItemAfterSalesImgs").val();
            var refundTotal = $("#uitemReturnTotal").text();
            var afterSalesKind = $("#uItemAfterSalesKind").val();
//            var bankMainName = encodeURI(encodeURI(uitemBankMainName));//主行名称
            var bankCard = $("#uitem_bankCard").val();//银行卡卡号
            var bankName = $("#uitem_bankName").val();//开户行名称
            var bankUserName = $("#uitem_bankUserName").val();//开户人姓名
            var reason = $("#uitem_reason").val();//原因
            var bankSupportId = $("#uitem_bankSupportId").val();
            var bankCityCode = $("#uitem_bankCityCode").val();//开户城市
            url = ctx + "/afterSales/saveAfterSales?id=" + afterSalesId
                    + "&orderId=" + orderId + "&bankMainName=" + encodeURI(encodeURI(uitemBankMainName))
                    + "&auditFlag=" + auditFlag + "&isApp=" + isApp + "&personnelStatus=" + personnelStatus
                    + "&AfterSalesImgs=" + afterSalesImgs + "&refundTotal=" + refundTotal + "&afterSalesKind=" + afterSalesKind
                    + "&bankCard=" + bankCard + "&bankName=" + bankName + "&bankUserName=" + bankUserName + "&reason=" + reason
                    + "&bankSupportId=" + bankSupportId + "&custMobile=" + custMobile + "&custName=" + encodeURI(encodeURI(custName))
                    + "&bankCityCode=" + bankCityCode;
//            var orderAfterSales = new Object();
//            orderAfterSales.isApp = //是否移动端添加
//            orderAfterSales.id = afterSalesId;//原售后单ID
//            orderAfterSales.orderId = orderId;//订单ID
//            orderAfterSales.custName = encodeURI(encodeURI(custName));//客户姓名
//            orderAfterSales.custMobile = custMobile;//客户电话
//            orderAfterSales.refundTotal = $("#uitemReturnTotal").text();;//退款总额
//            orderAfterSales.bankMainName = encodeURI(encodeURI(uitemBankMainName));//主行名称
//            orderAfterSales.approveBy = uitemApproveBy;//审核人ID
//            orderAfterSales.approveDept = uitemApproveDept;//审核部门ID
//            orderAfterSales.afterSalesKind = $("#uItemAfterSalesKind").val();//售后单类型
//            orderAfterSales.bankCard = $("#uitem_bankCard").val();//银行卡卡号
//            orderAfterSales.bankName = $("#uitem_bankName").val();//开户行名称
//            orderAfterSales.bankUserName = $("#uitem_bankUserName").val();//开户人姓名
//            orderAfterSales.reason = $("#uitem_reason").val();//原因
//            orderAfterSales.bankSupportId = $("#uitem_bankSupportId").val();//银行类型
//            orderAfterSales.bankCityCode = $("#uitem_bankCityCode").val();//开户城市
//            orderAfterSales.personnelStatus = $("#uItemPersonnelStatus").val();//服务状态
            $.ajax({
                url: url,
                data:data,
                type:"post",
                dataType:"json",
                async:false,
                success:function(data){
                    if (data.msg == "00") {
                        $.alert({millis: 3000, top: '30%', text: "修改成功！"});
                        queryAfterSalesByLike(0, 10);
                        closeModule('qualityItemEdit');
                    } else {
                        $.alert({millis: 3000, top: '30%', text: "修改失败！"});
                        closeModule('qualityItemEdit');
                    }
                }
            });
        }else{
            //url售后单状态修改
            url = ctx + "/afterSales/updateAfterSales?id=" + afterSalesId + "&orderId=" + orderId + "&bankMainName=" + encodeURI(encodeURI(uitemBankMainName)) + "&auditFlag=" + auditFlag;
            if ((ucardRefundTotal == "0.00" && ucardRefundTotal != null) || (uremainRefundTotal == "0.00" && uremainRefundTotal != null)) {
                var itembanksupportId = $("#uitem_bankSupportId").val();
                var itembankCityCode = $("#uitem_bankCityCode").val();
                var itembankCard = $("#uitem_bankCard").val();
                var itembankName = $("#uitem_bankName").val();
                var itembankUserName = $("#uitem_bankUserName").val();
                if (itembanksupportId == null || itembanksupportId == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写银行！"});
                    flag = true;
                } else if (itembankCityCode == null || itembankCityCode == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写城市！"});
                    flag = true;
                } else if (itembankCard == null || itembankCard == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写银行卡号！"});
                    flag = true;
                } else if (itembankName == null || itembankName == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写开户行！"});
                    flag = true;
                } else if (itembankUserName == null || itembankUserName == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写持卡人！"});
                    flag = true;
                } else {
                    flag = true;
                    if (flag) {
                        updateItemEdit(url, data);
                    }
                }
            } else {
                flag = true;
                if (flag) {
                    updateItemEdit(url, data);
                }
            }
        }
        $("#uqualityItemBtn").removeAttr("disabled");
    }

    function updateItemEdit(url, data) {
        $.ajax({
            url: url,//"&afterSalesImgs="+imgUrls+
            data: data,
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.alert({millis: 3000, top: '30%', text: "修改成功！"});
                    queryAfterSalesByLike(0, 10);
                    //showQualityDetail(afterSalesId,orderId,1,"");
                    closeModule('qualityItemEdit');
                } else {
                    $.alert({millis: 3000, top: '30%', text: "修改失败！"});
                    closeModule('qualityItemEdit');
                }
            }
        });
    }
    //查询审批人
    function queryGuanjiaName() {
        var ctx = $("#ctx").val();
        var deptId = $("#uitemApproveDeptSelect option:selected").val();
        ;
        if (deptId == "" || deptId == null) {
            $("#uitemApproveBySelect").empty();
            $("#uitemApproveBySelect").html("<option value=''>...请选择...</option>");
        } else {
            $.ajax({
                url: ctx + "/orderbase/queryguanjia",
                data: {
                    deptId: deptId
                },
                type: 'post',
                async: false,
                dataType: "json",
                success: function (data) {
                    var html = "";
                    html += "<option style='color:blue;' value='' >...请选择...</option>";
                    if (data.msg == "00") {
                        $.each(data.list, function (i, v) {
                            html += "<option   value='" + v.id + "'>" + v.realName + "（" + v.userName + "）</option>";
                        });
                    } else if (data.msg == "02") {
                        $.alert({millis: 2000, text: "无记录!"});
                    } else {
                        $.alert({millis: 2000, text: "查询出错，请稍后再试!"});
                    }
                    $("#uitemApproveBySelect").html(html);
                }
            });
        }
    }
    //进行验证的方法
    $('#uqualityItemForm').bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {         //fields下的是表单name属性
            reason: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 200,
                        message: '描述不超过200字！'
                    },
                }
            },
        }
    }).on('success.form.bv', function (e) {
        // 阻止表单提交【submit】【必填】
        e.preventDefault();
        //保存售后单
        updateQualityItem();
    });
</script>
</html>

