<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript">
        <%
            String orderId = request.getParameter("orderId");
            String cateType = request.getParameter("cateType");
            String accountId = request.getParameter("accountId");
            String totalPay = request.getParameter("totalPay");
            String isYg = request.getParameter("isYg");
            String iscollection=request.getParameter("checkedIscollection");
            String feePost=request.getParameter("feePost");
        %>
    </script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">结算单信息</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <div class="info clearfix">
                    <input type="hidden" id="accountAmountadOld"/>
                    <input type="hidden" id="accountCustomerManageMoney"/>
                    <form id="balanceAddForm" action="" class="form-inline" method="post"
                          data-validator-option="{theme:'yellow_left_effect',stopOnError:true}">


                        <div class="form-group col-xs-12">
                            <span id="prompt_Msg" style="color:red!important"></span>
                        </div>


                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:150px;">结算单时间：</p>
                                    <input id="accountStartTimead" name="startTime" class="form-control Wdate"
                                           type="text"
                                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                                    至<input id="accountEndTimead" name="endTime" class="form-control Wdate" type="text"
                                            onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:150px;">结算单金额：</p>
                                    <input class="form-control" id="accountAmountad"
                                           onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"
                                           value="<%=totalPay %>"/>元
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:150px;">结算单类型：</p>
                                    <select id="accountTypead" name="payType" class="form-control">
                                        <option value="1">定金</option>
                                        <option value="2">押金</option>
                                        <option value="3">预收</option>
                                        <option value="4">收入</optaion>
                                        <option value="5">客户信息费</option>
                                        <option value="6">信息费收入</option>
                                        <option value="88">分期服务费</option>  <!-- 88标识为分期服务费类型用来判断 -->
                                        <option value="10" style="display: none">售后手续费</option>
                                    </select>
                                </label>
                                <label>
                                    <select id="fenqiBaitiao" class="form-control" style="display: none">
                                        <option value="">请选择</option>
                                        <option value="8">唯品会白条分期服务费</option>
                                        <option value="11">海金保理白条分期服务费</option>
                                        <option value="12">招行分期分期服务费</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="row" id="customerFeeadDiv">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:150px;">预计客户信息费：</p>
                                    <input class="form-control" id="customerFeead"
                                           onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" value=""/>元
                                </label>
                            </div>
                        </div>
                        <div class="row" id="personalFeeadDiv">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width:150px;">预计服务人员信息费：</p>
                                    <input class="form-control" id="personalFeead"
                                           onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')" value=""/>元
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="insertAccount();" id="orderAccountSubmit">保存
                </button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var cateType = "<%=cateType%>";
    var accountId = "<%=accountId %>";
    var orderId = "<%=orderId %>";
    var isYg = "<%=isYg %>";
    var totalPay = "<%=totalPay %>";
    var iscollection = "<%=iscollection %>";
    var feePost = "<%=feePost %>";
    var hasFlag = true; //是否有合同标记
    var hasAccountFlag = false; //是否有客户信息费结算单标记
    $(function () {
        var checkedOrderType = $("#checkedOrderType").val();
        if (cateType == 2) {
            $("#customerFeeadDiv").show();
            $("#personalFeeadDiv").show();
            $("#accountTypead option[value='1']").show();
            $("#accountTypead option[value='2']").hide();
            $("#accountTypead option[value='3']").show();
            if (accountId == 0) {
                $("#accountTypead option[value='3']").attr("selected", "selected");
            }
            /* if (checkedOrderType != 100200120003 && checkedOrderType != 100200010001) {
                $("#accountTypead option[value='10']").show();
            } */
            $("#accountTypead option[value='4']").hide();
            $("#accountTypead option[value='5']").show();
            $("#accountTypead option[value='6']").hide();
            $("#accountTypead option[value='88']").show();
            //根据订单id，查询合同客户信息费
            queryAgreementCustomerMoney(orderId);
        } else {
            if (cateType == 1) {
                $("#customerFeeadDiv").show();
                $("#personalFeeadDiv").show();
                $("#accountTypead option[value='2']").hide();
            } else {
                $("#customerFeeadDiv").hide();
                $("#personalFeeadDiv").hide();
            }
            $("#accountTypead option[value='1']").hide();
            $("#accountTypead option[value='3']").hide();
            $("#accountTypead option[value='4']").show();
            if (accountId == 0) {
                $("#accountTypead option[value='4']").attr("selected", "selected");
            }
            $("#accountTypead option[value='5']").hide();
            $("#accountTypead option[value='6']").hide();
            $("#accountTypead option[value='88']").hide();
            //	$("#accountTypead option[value='10']").hide();
        }
        loadAccount();
        if (iscollection == 1) {
            $("#prompt_Msg").html("请将订单是他人代收缴费方式的金额与渠道售价金额录入一致, 不确认金额请与该渠道经理联系确认后填写。");
            $('#accountAmountad').removeAttr("readonly");
            $('#accountAmountad').val("");
        }
        //他人代收缴费类型，修改结算单时全都可修改。
        if(feePost == 20250014){
            $("#accountStartTimead").removeAttr("disabled");
            $("#accountEndTimead").removeAttr("disabled");
            $("#accountAmountad").removeAttr("disabled");
            $("#accountAmountad").removeAttr("readonly");
            $("#accountTypead").removeAttr("disabled");
        }
    })
    function loadAccount() {
        var ctx = $("#ctx").val();
        if (cateType == 3 || cateType == 4) {
            $('#accountAmountad').attr("readonly", true);
        }
        $.ajax({
            url: ctx + "/payfee/loadAccount",
            data: {
                id: accountId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00') {
                    if (data.payfee.payStatus == '20110001') {
                        $("#accountStartTimead").val(numberToDatestr(data.payfee.startTime, 14));
                        $("#accountEndTimead").val(numberToDatestr(data.payfee.endTime, 14));
                        $("#accountAmountad").val(data.payfee.accountAmount);
                        $("#accountAmountadOld").val(data.payfee.accountAmount);
                        $("#customerFeead").val(data.payfee.customerFee);
                        $("#personalFeead").val(data.payfee.personalFee);
                        var payType = data.payfee.payType;
                        if (payType == 12 || payType == 11 || payType == 8) {
                            $("#accountTypead option[value='88']").attr("selected", "selected");
                            $("#fenqiBaitiao").show();
                            $("#fenqiBaitiao option[value='" + payType + "']").attr("selected", "selected");
                            $("#customerFeead").attr("readonly", true);
                            $("#personalFeead").attr("readonly", true);
                        } else {
                            $("#accountTypead option[value='" + payType + "']").attr("selected", "selected");
                        }
                        //$("#accountTypead option:selected").val();
                        if (data.payfee.payType == 5) {
                            $("#accountAmountad").attr("readonly", true);
                        }
                    } else {
                        $("#accountStartTimead").val(numberToDatestr(data.payfee.startTime, 14));
                        $("#accountEndTimead").val(numberToDatestr(data.payfee.endTime, 14));
                        $("#accountAmountad").val(data.payfee.accountAmount);
                        $("#accountAmountadOld").val(data.payfee.accountAmount);
                        $("#customerFeead").val(data.payfee.customerFee);
                        $("#personalFeead").val(data.payfee.personalFee);
                        var payType = data.payfee.payType;
                        if (payType == 12 || payType == 11 || payType == 8) {
                            $("#accountTypead option[value='88']").attr("selected", "selected");
                            $("#fenqiBaitiao").show();
                            $("#fenqiBaitiao option[value='" + payType + "']").attr("selected", "selected");
                            $("#customerFeead").attr("readonly", true);
                            $("#personalFeead").attr("readonly", true);
                        } else {
                            $("#accountTypead option[value='" + payType + "']").attr("selected", "selected");
                        }
                        $("#accountStartTimead").attr("disabled", true);
                        $("#accountEndTimead").attr("disabled", true);
                        $("#accountAmountad").attr("disabled", true);
                        $("#accountAmountadOld").attr("disabled", true);
                        //$("#customerFeead").attr("disabled",true);
                        //$("#personalFeead").attr("disabled",true);
                        $("#accountTypead").attr("disabled", true);
                        $("#fenqiBaitiao").attr("disabled", true);
                        $("#orderAccountSubmit").show();
                    }
                    //查看预估按钮
                    if (isYg != null && isYg != "" && isYg == 1) {
                        $("#accountStartTimead").attr("disabled", true);
                        $("#accountEndTimead").attr("disabled", true);
                        $("#accountAmountad").attr("disabled", true);
                        $("#accountAmountadOld").attr("disabled", true);
                        $("#accountTypead").attr("disabled", true);
                        $("#customerFeead").attr("disabled", true);
                        $("#personalFeead").attr("disabled", true);
                        $("#orderAccountSubmit").hide();
                    }
                } else {
                    if (cateType == 2) {
                        $("#accountAmountad").val(0);
                    }
                }

            }
        });
    }
    //判断是否可以修改预估
    /* function compareYgDate(createTime){
     var systemTime = new Date();
     //当前时间
     var newSystemTime =new Date(systemTime).format("yyyy-MM-dd hh:mm:ss");
     //格式化时间
     var regEx = new RegExp("\\-","gi");
     createTime=createTime.replace(regEx,"/");
     newSystemTime=newSystemTime.replace(regEx,"/");
     alert(createTime);
     alert(newSystemTime);
     var orderStartTimeVal=Date.parse(orderStartTime);
     var orderCancleTimeVal=Date.parse(orderCancleTime);
     if(orderStartTimeVal < orderCancleTimeVal){
     $.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
     $(this).val("");
     }
     return false;
     } */
    //查询合同客户信息费
    function queryAgreementCustomerMoney(orderId) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/agreement/queryAgreement",
            data: {
                orderId: orderId,
                //agreementState:2,
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00' && data.list.length > 0) {
                    $.each(data.list, function (j, w) {
                        if (w.agreementState != 2) {
                            $("#accountCustomerManageMoney").val("0.00");
                            hasFlag = false;
                        } else {
                            $("#accountCustomerManageMoney").val(w.customerManageMoney);
                            hasFlag = true;
                            return false;
                        }
                    });
                } else {
                    $("#accountCustomerManageMoney").val("");
                }
            }
        });
    }
    //查询是否有客户信息费结算单
    function queryPayTypeAccount(orderId) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/payfee/queryAccount",
            data: {
                orderId: orderId,
                feeType: 5,
                isBackType: 2,
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00' && data.list.length > 0) {
                    hasAccountFlag = true;
                } else {
                    hasAccountFlag = false;
                }
            }
        });
    }

    //选择客户信息费，判断是否有合同，金额固定为合同中的客户信息费
    $("#accountTypead").change(function () {
        //客户信息费
        var accountCustomerManageMoney = $("#accountCustomerManageMoney").val();
        if ($(this).find("option:selected").val() == 5) {//选择客户信息费
            if (accountCustomerManageMoney != "" && accountCustomerManageMoney != null) {
                //判断是否有已确认合同
                if (hasFlag) {
                    //查询该订单是否有客户信息费的结算单
                    queryPayTypeAccount(orderId);
                    if (!hasAccountFlag) {
                        //金额固定为客户信息费
                        $("#accountAmountad").val(accountCustomerManageMoney);
                        $('#accountAmountad').attr("readonly", true);
                    } else {
                        $.alert({millis: 3000, top: '30%', text: "已有客户信息费结算单！"});
                        $(this).val("3");//设置成预收类型
                        $('#accountAmountad').val("0");
                        $('#accountAmountad').removeAttr("readonly");
                    }
                } else {
                    $.alert({millis: 3000, top: '30%', text: "合同未确认，不能新增结算单！"});
                    $(this).val("3");//设置成预收类型
                }
            } else {
                $.alert({millis: 3000, top: '30%', text: "没有合同！"});
                $(this).val("3");//设置成预收类型
            }
        } else if ($(this).find("option:selected").val() == 88) {
            $("#fenqiBaitiao").show();
            $("#customerFeead").val(0);
            $("#personalFeead").val(0);
            $("#customerFeead").attr("readonly", true);
            $("#personalFeead").attr("readonly", true);
        } else if ($(this).find("option:selected").val() == 4) {
            $('#accountAmountad').val(totalPay);
            $('#accountAmountad').attr("readonly", true);
        } else {
            //重新加载结算单金额
            $('#accountAmountad').val("0");
            $('#accountAmountad').removeAttr("readonly");
        }
        if ($(this).find("option:selected").val() != 88) {
            //隐藏分期服务费二级菜单
            $("#fenqiBaitiao").val("").hide();
        }
    });
    function insertAccount() {
        var ctx = $("#ctx").val();
        var accountStartTimead = $("#accountStartTimead").val();
        var accountEndTimead = $("#accountEndTimead").val();
        var accountAmountad = $("#accountAmountad").val();
        var customerFee = $("#customerFeead").val();
        var personalFee = $("#personalFeead").val();
        var payStatus = "20110001";
        var orderId = "<%=orderId%>";
        var cateType = "<%=cateType%>";
        var totalPay = "<%=totalPay%>";
        var accountId = "<%=accountId %>";
        if (accountAmountad <= 0) {
            $.alert({text: "结算单金额不正确！"});
            return;
        }
        if (cateType == 1 || cateType == 2) {
            if (customerFee == null || customerFee == "") {
                $.alert({text: "预计客户信息费不能为空！"});
                return;
            }
            if (personalFee == null || personalFee == "") {
                $.alert({text: "预计服务人员信息费不能为空！"});
                return;
            }
        }
        if (parseFloat(accountAmountad) < parseFloat(customerFee) + parseFloat(personalFee)) {
            $.alert({
                text: "预计客户信息费与预计服务人员信息费的和不能超过结算单金额！"
            });
            return;
        }
        var accountTypead = $("#accountTypead option:selected").val();
        if (accountTypead == 88) {
            var fenqiBaitiao = $("#fenqiBaitiao option:selected").val();
            if (fenqiBaitiao == "") {
                $.alert({
                    text: "请选择分其服务费类型！"
                });
                return;
            } else {
                accountTypead = fenqiBaitiao;
            }
        }
        var data = {id: accountId,
            orderId: orderId,
            cateType: cateType,
            startTime: accountStartTimead,
            endTime: accountEndTimead,
            accountAmount: accountAmountad,
            payType: accountTypead,
            payStatus: payStatus,
            isBackType: 2,
            bussStatus: 1,
            payKind: 2,
            accountStatus: accountStatus,
            customerFee: customerFee,
            personalFee: personalFee,
            isYg: isYg};//参数对象
        var url = "/payfee/insertAccount";
        var accountStatus = 0;
        if (isYg != 1) {
            if (!accountStartTimead || $.trim(accountStartTimead) == '' || !accountEndTimead
                    || $.trim(accountEndTimead) == '') {
                $.alert({
                    text: "请选择开始、结束时间。"
                });
                return;
            }
            try {
                var st = new Date(accountStartTimead.replace(/\-/g, "\/"));
                var et = new Date(accountEndTimead.replace(/\-/g, "\/"));
                if (et < st) {
                    $.alert({
                        text: "开始时间大于结束时间。"
                    });
                    return;
                }
            } catch (e) {
                $.alert({
                    text: "时间格式不正确。"
                });
                return;
            }
        }
        if (accountId != 0) {
            url = "/payfee/updateAccount";
            var accountAmountadOld = $("#accountAmountadOld").val();
            if (accountAmountad != accountAmountadOld) {
                accountStatus = 1;
            }
            payStatus = "";
            if(feePost == 20250014){
                data.feePost = feePost;
            }
        }
        if (accountAmountad >= 9999999) {
            $.alert({text: "结算单金额过大！"});
        } else {
            $.ajax({
                url: ctx + url,
                data: data,
                type: "post",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.msg == '00') {
                        if (cateType == 1) {
                            queryAccount(orderId, "accountListBodyOne", cateType, totalPay);
                            showBtn(cateType, orderId);
                        } else if (cateType == 2) {
                            queryAccount(orderId, "accountListBodyCont", cateType, totalPay);
                            showBtn(cateType, orderId);
                        } else if (cateType == 3) {
                            queryAccount(orderId, "accountListBodyItem", cateType, totalPay);
                            showBtn(cateType, orderId);
                        } else if (cateType == 4) {
                            queryAccount(orderId, "accountListBodyThree", cateType, totalPay);
                            showBtn(cateType, orderId);
                        }
                        closeModule("orderBasicAccount");
                    } else {
                        $.alert({
                            text: "保存失败！"
                        });
                    }
                }
            });
        }
    }
</script>
</html>