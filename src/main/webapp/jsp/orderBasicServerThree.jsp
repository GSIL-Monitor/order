<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- 服务订单基本信息 -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        .tabOrder {
            width: 100%;
            border-left: 0px;
            border-top: 1px solid #CCC;
            border-right: 0px;
            border-bottom: 1px solid #CCC;
            margin: auto auto auto 5px
        }
        .tabOrder tr td {
            text-align: left;
        }
    </style>
</head>
<body>
<input type="hidden" name="orderId" id="orderIdBasicThree">
<input type="hidden" name="orderCateTypeBasic" id="orderCateTypeBasicThree">
<div class="mytabs-wrap">
    <ul class="mytabs">
        <li class="tab-item tab-active" id="serverBasicThree1" onclick="serverBasicThree(1);">基本信息</li>
        <li class="tab-item" id="serverBasicThree2" onclick="serverBasicThree(2);">缴费</li>
        <li class="tab-item" id="serverBasicThree3" onclick="serverBasicThree(3);">转单记录</li>
    </ul>
</div>
<div id="arr">
    <span id="left"><</span><span id="right">></span>
</div>
<div class="tab-content">
    <div class="tab-selected" id="basicServerThree">
        <div class="widget" id="setBasicServerThreeEditButton" style="hieght:30px; display:none;">
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="openModule('${ctx }/jsp/server/orderServerEdit.jsp','','',{width:'50%'},'orderServerEdit')">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="row">
                <div class="col-xs-12">
                    <h4>详细信息</h4>
                </div>
            </div>
            <input type="hidden" id="serverTypeBasicThree" name="serverType"/>
            <input type="hidden" id="accountCreateTimeBasicThree" name="accountCreateTimeBasicThree"/>
            <input type="hidden" id="accountSystemDateBasicThree" name="accountSystemDateBasicThree"/>
            <div>
                <table class="table table-condensed">
                    <tr>
                        <td width="55%">
                            订单编号：<span id="orderCodeBasicThree"></span>
                        </td>
                        <td width="45%">
                            服务类型：<span id="servserTextBasicThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="45%" colspan="2">创建时间：<span id="createTimesServerThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            订单省份：<span id="receiverProvinceBasicThree"></span>
                        </td>
                        <td>
                            订单城市：<span id="receiverCityBasicThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            订单状态：<span id="statusTextBasicThree"></span>
                        </td>
                        <td>
                            支付状态：<span id="payTextServerThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <% if (!CookieUtils.getJSONKey(request, "managerType").equals("2")) { %>
                        <td>
                            订单来源：<span id="sourceTextBasicThree"></span>
                        </td>
                        <%} %>
                        <td>
                            订单渠道：<span id="channelTextBasicthree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            带单人员：<span id="personthree"></span>
                        </td>
                        <td>
                            带单人电话：<span id="personmobilethree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            开始时间：<span id="startTimeBasicThree"></span>
                        </td>
                        <td>
                            结束时间：<span id="endTimeBasicThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            服务地址：<span id="addressBasicThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            订单备注：<span id="remarkBasicThree"></span>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <h4>客户信息</h4>
                </div>
            </div>
            <div>
                <table class="table table-condensed">
                    <tr>
                        <td width="50%">
                            客户姓名：<span id="nameBasicThree"></span>
                        </td>
                        <td>
                            <button type="button" class="btn btn-sm btn-default fr mr20"
                                    onclick="javascript:parent.editUserHref();">完善客户资料
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                            客户电话：<span id="mobileBasicThree"></span>
                        </td>
                        <td width="50%">
                            性别：<span id="sexBasicThree"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            家庭地址：<span id="userAddressBasicThree"></span>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <h4>商品信息</h4>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table table-responsive">
                        <table class="table focus-table" style="width:680px;">
                            <thead>
                            <tr>
                                <th width="30%">商品名称(单位)</th>
                                <th width="20%">价格体系</th>
                                <th width="10%">商品价格</th>
                                <th width="10%">商品数量</th>
                                <th width="20%">规格</th>
                            </tr>
                            </thead>
                            <tbody id="orderInforServerThree">
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <h4>需求信息</h4>
                </div>
            </div>
            <div>
                <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
                    <tr>
                        <td width="50%" colspan="2">
                            性别要求：<span id="xqSexTextBasicsThree"></span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div id="feesServerThree" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="addAccountServerThree();" id="addAccountServerThreeBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i>
            </button>
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="updateAccountServerThree();" id="updateAccountServerThreeBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i>
            </button>
            <!-- 删除结算单，结算中心特殊权限 -->
            <auth:menu menuId="1150">
                <button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServerThree();"
                        id="deleteAccountServerThreeBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="删除结算">删除结算</i></button>
            </auth:menu>
        </div>
        <div class="widget">
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="addPayfeeServerThree();">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i>
            </button>
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="updatePayfeeServerThree()" id="updatePayfeeServerThreeBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i>
            </button>
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="addPayfeeWeiXinServerThree()" id="addPayfeeWeiXinServerThreeBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="微信扫码支付">微信扫码支付</i>
            </button>
            <button type="button" name="orderButtonsAllThree" class="btn btn-primary btn-xs"
                    onclick="addPayfeeJiaLianServerThree()" id="addPayfeeJiaLianServerThreeBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="嘉联二维码">嘉联二维码</i>
            </button> 
            <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                    onclick="addPayfeeLianDongServer(4)" id="">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="联动二维码">联动二维码</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="project-order-news">
                <div class="row">
                    <div class="col-xs-12"><h4>缴费信息</h4></div>
                </div>
                <div id="accountListBodyThree">
                </div>
            </div>
        </div>
    </div>
    <div id="turnOrderLogThree" style="display:none; border:1px solid #CCC;">
        <div class="panel-content table-responsive">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>转单时间</th>
                    <th>操作人</th>
                    <th>操作人部门</th>
                    <th>转单人</th>
                    <th>转单人部门</th>
                    <th>负责人</th>
                    <th>负责人部门</th>
                    <th>备注</th>
                </tr>
                </thead>
                <tbody id="turnOrderLogListThree">

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script>
    function setBasicServerThree(orderId, cateType, totalPay, orderStatus, rechargeBy) {
        $("#orderIdBasicThree").val(orderId);
        $("#orderCateTypeBasicThree").val(cateType);
        $("#productCode").val("")
        // 判断哪些状态下订单可以修改
        if (orderStatus == 1 || orderStatus == 2 || orderStatus == 18) {
            $("#setBasicServerThreeEditButton").css("display", "block");
        } else {
            $("#setBasicServerThreeEditButton").css("display", "none");
        }
        getServerBasicThree(orderId);
        $("input:radio[name=radioAccount]").each(function () {
            $(this).val("");
        });
        queryAccount(orderId, "accountListBodyThree", cateType, totalPay);
        showBtn(cateType, orderId);
        // 当前登录人员操作按钮权限控制
        setOrderButtonsAllThree();
        setOrderLogiThree();
        queryOrderTurnLog(orderId, 'turnOrderLogListThree');
    }
    function setOrderLogiThree() {
        var rechargeBy = $("#checkedRechargeBy").val();
        var loginBy = $("#checkedLoginBy").val();
        if (rechargeBy == null || rechargeBy == '') {
            //alert("登录人："+loginBy+"负责人"+rechargeBy);
            $("#serverBasicThree2").css("display", "none");
        } else {
            $("#serverBasicThree2").css("display", "block");
        }
    }
    function setOrderButtonsAllThree() {
        var loginByOrNot = $("#checkedLoginByOrNot").val();
        var orderStatus = $("#checkedOrderStatus").val();
        var loginRechargeOrNot = $("#checkedLoginRechargeOrNot").val();
        var rechargeBy = $("#checkedRechargeBy").val();
        // 当前登录人员操作按钮权限控制
        if (rechargeBy == null || rechargeBy == "" || rechargeBy == 0) { // 未分包
            if (loginByOrNot == null || loginByOrNot == "" || loginByOrNot == 0) {
                $("button:button[name=orderButtonsAllThree]").each(function () {
                    $(this).hide();
                });
            } else {
                $("button:button[name=orderButtonsAllThree]").each(function () {
                    $(this).show();
                });
            }
        } else { // 已分包,负责人对应的操作权限
            if (loginRechargeOrNot == null || loginRechargeOrNot == "" || loginRechargeOrNot == 0) {
                $("button:button[name=orderButtonsAllThree]").each(function () {
                    $(this).hide();
                });
            } else {
                $("button:button[name=orderButtonsAllThree]").each(function () {
                    $(this).show();
                });
            }
        }

    }
    //取到订单详细信息
    function getServerBasicThree(orderId) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/orderbase/queryOrderBasicServer",
            data: {
                id: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg = "00") {
                    var html = "";
                    var num = $.each(data.list, function (i, v) {
                        $("#orderCodeBasicThree").text(v.orderCode);
                        $("#serverTypeBasicThree").val(v.serverType);
                        $("#servserTextBasicThree").text(v.typeText);
                        $("#startTimeBasicThree").text(numberToDatestr(v.startTime, 12));
                        $("#endTimeBasicThree").text(numberToDatestr(v.endTime, 12));
                        $("#statusTextBasicThree").text(v.statusText);
                        $("#payTextServerThree").text(v.payText);
                        $("#sourceTextBasicThree").text(v.sourceText);
                        $("#channelTextBasicthree").text(v.channelText);
                        $("#personthree").text(v.order.person);
                        $("#personmobilethree").text(v.order.personmobile);
                        $("#addressBasicThree").text(v.address);
                        $("#remarkBasicThree").text(v.remark);
                        $("#checkedTotalPay").text(v.totalPay);
                        $("#createTimesServerThree").text(numberToDatestr(v.createTime, 12));
                        $("#receiverProvinceBasicThree").text(v.order.receiverProvince);
                        $("#receiverCityBasicThree").text(v.order.receiverCity);
                        // 客户信息
                        $("#nameBasicThree").text(v.order.userName);
                        $("#mobileBasicThree").text(v.order.userMobile);
                        $("#sexBasicThree").text(v.sex == 1 ? "男" : "女");
                        $("#birthBasicThree").text(v.birth == "" ? "" : numberToDatestr(v.birth, 8));
                        $("#userAddressBasicThree").text(v.userAddress);

                        // 商品信息
                        html += "<tr style='height:25px;'>";
                        html += "<td>" + v.productName + "(" + v.productUnitText + ")</td>";
                        html += "<td>" + v.productPriceTypeText + "</td>";
                        html += "<td>" + v.nowPrice + "</td>";
                        html += "<td>" + v.quantity + "</td>";
                        html += "<td>" + v.productSpec + "</td>";
                        html += "</tr>";

                    })
                    html += "<tr></tr>";
                    $("#orderInforServerThree").html(html);
                    $.each(data.list, function (i, v) {
                        if (i == 0) {
                            var xqSex = "";
                            if (v.xqSex == "1") {
                                xqSex = "男";
                            } else if (v.xqSex == "2") {
                                xqSex = "女";
                            } else {
                                xqSex = "不限";
                            }
                            $("#xqSexTextBasicsThree").text(xqSex);
                        }
                    })
                }
            }
        });
    }
    // 调区显示区域
    function serverBasicThree(num) {
        if (num == 1) {
            $("#serverBasicThree1").addClass("tab-selected");
            $("#serverBasicThree2").removeClass("tab-selected");
            $("#serverBasicThree3").removeClass("tab-selected");
            $("#basicServerThree").css("display", "block");
            $("#feesServerThree").css("display", "none");
            $("#turnOrderLogThree").css("display", "none");
        } else if (num == 2) {
            $("#serverBasicThree1").removeClass("tab-selected");
            $("#serverBasicThree2").addClass("tab-selected");
            $("#serverBasicThree3").removeClass("tab-selected");
            $("#basicServerThree").css("display", "none");
            $("#feesServerThree").css("display", "block");
            $("#turnOrderLogThree").css("display", "none");
        } else if (num == 3) {
            $("#serverBasicThree1").removeClass("tab-selected");
            $("#serverBasicThree2").removeClass("tab-selected");
            $("#serverBasicThree3").addClass("tab-selected");
            $("#basicServerThree").css("display", "none");
            $("#feesServerThree").css("display", "none");
            $("#turnOrderLogThree").css("display", "block");
            queryOrderTurnLog($("#orderIdBasicThree").val(), 'turnOrderLogListThree');
        }
        setOrderButtonsAllThree();
    }

    // 新增结算
    function addAccountServerThree() {
        orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), 0, parent.$("#checkedIscollection").val());
    }
    // 修改结算
    function updateAccountServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择要修改的结算单！"
            });
            return;
        }
        var feePost = $("#feePostPayfee"+radioAccount).val();
        //使用“他人代收”支付方式的缴费和结算单，如果在财务系统中汇总单状态为“未生成”，则可以修改结算单信息
        if(feePost != "20250014"){
            if ($("#accountListBodyThree" + radioAccount).find("tr").length != 0) {
                $.alert({
                    text: "已有缴费信息，不可修改结算单！"
                });
                return;
            }
            var payStatus = $("#payStatusAccount" + radioAccount).val();
            if (payStatus == 20110002 || payStatus == 20110003) {
                $.alert({
                    text: "当前结算单已对账，无法修改！"
                });
                return;
            }
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicThree");
            querySysdate("accountSystemDateBasicThree");
            var updateServerThreeCreTime = $("#accountCreateTimeBasicThree").val();
            var updateServerThreeSysdate = $("#accountSystemDateBasicThree").val();
            deleteAccountTimeRule(updateServerThreeCreTime, updateServerThreeSysdate);
            if (timeRuleFlag) {
                orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        }else{
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicThree");
            querySysdate("accountSystemDateBasicThree");
            var updateServerThreeCreTime = $("#accountCreateTimeBasicThree").val();
            var updateServerThreeSysdate = $("#accountSystemDateBasicThree").val();
            deleteAccountTimeRule(updateServerThreeCreTime, updateServerThreeSysdate);
            if (timeRuleFlag) {
                $.ajax({
                    url:ctx+"/payfee/checkFinSummaryForAccount",
                    data:{
                        accountId:radioAccount
                    },
                    type:"post",
                    async:false,
                    success:function(data){
                        if(data.msg == "00"){
                            updateAccountForTRDS(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
                        }else if(data.msg == "02"){
                            $.alert({text:"汇总单已生成,无法修改!"});
                        }else{
                            $.alert({text:"系统错误!"});
                        }
                    }
                });
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        }
        
    }
    // 删除结算
    function deleteAccountServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择要删除的结算单！"
            });
            return;
        }
        var payStatus = $("#payStatusAccount" + radioAccount).val();
        if (payStatus == 20110002 || payStatus == 20110003) {
            $.alert({
                text: "当前结算单已对账，无法删除！"
            });
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "当前订单已取消，不能删除！"});
        } else if (orderStatus == 12) {
            $.alert({text: "当前订单已终止，不能删除！"});
        } else {
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicThree");
            querySysdate("accountSystemDateBasicThree");
            var serverThreeCreTime = $("#accountCreateTimeBasicThree").val();
            var serverThreeSysdate = $("#accountSystemDateBasicThree").val();
            deleteAccountTimeRule(serverThreeCreTime, serverThreeSysdate);
            if (timeRuleFlag) {
                $.confirm({
                    text: "是否确认删除结算？", callback: function (re) {
                        if (re) {
                            deleteAccountById(radioAccount);
                            queryAccount(parent.$("#checkedOrderId").val(), "accountListBodyThree");
                        } else {
                            return;
                        }
                    }
                });
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        }
        /** 周鑫 2018-12-27  校验审核状态 是否是 已确认  **/
        var result=checkAccountReviewState(radioAccount);
        if(result!="00"){
        	 $.alert({
                 text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
             });
             return;
        } 
        /** 周鑫 2018-12-27 **/
    }
    // 新增缴费
    function addPayfeeServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyThree" + radioAccount).find("tr").length > 0) {
            $.alert({
                text: "当前结算单已经生成缴费信息，无法新增！"
            });
            return;
        }
        //结算单类型
        var payType = $("#accountpayType" + radioAccount).val();
        var accountAmount = $("#accountAmount" + radioAccount).text();
        var userMobile = parent.$("#checkedUserMobile").val();
        orderBasicPayfee(radioAccount, parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), accountAmount, userMobile, 1, parent.$("#checkedOrderChannel").val(), parent.$("#checkedUserId").val(), payType);
    }
    // 修改缴费
    function updatePayfeeServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyThree" + radioAccount).find("tr").length == 0) {
            $.alert({
                text: "无可修改缴费信息！"
            });
            return;
        }
        //查询缴费是否对账，未对账可修改，已对账不可修改
        var tip = checkAccountStatus(radioAccount);
        if (tip) {
            $.alert({text: "请修改未对账缴费！"});
            return;
        }
        //结算单类型
        var payType = $("#accountpayType" + radioAccount).val();
        var accountAmount = $("#accountAmount" + radioAccount).text();
        var userMobile = parent.$("#checkedUserMobile").val();

        loadPayfeeMinCreTime(radioAccount, "accountCreateTimeBasicThree");
        querySysdate("accountSystemDateBasicThree");
        var serverThreeMinCreTime = $("#accountCreateTimeBasicThree").val();
        var serverThreeMinSysdate = $("#accountSystemDateBasicThree").val();
        deleteAccountTimeRule(serverThreeMinCreTime, serverThreeMinSysdate);
        /** 周鑫 2018-12-27  校验审核状态 是否是 已确认  **/
        var result=checkAccountReviewState(radioAccount);
        if(result!="00"){
        	 $.alert({
                 text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
             });
             return;
        }
        /** 周鑫 2018-12-27 **/
        if (timeRuleFlag) {
            orderBasicPayfee(radioAccount, parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), accountAmount, userMobile, 2, parent.$("#checkedOrderChannel").val(), parent.$("#checkedUserId").val(), payType);
        } else {
            $.alert({
                text: "当前缴费已过有效操作时间，不可操作！"
            });
            return;
        }
         
    }
    //新增微信扫码支付
    function addPayfeeWeiXinServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择结算单！"
            });
            return;
        }
        var message = "<h4>各位管家好:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信扫码支付完成后<span style='color:red'><strong>不需要进行任何操作，系统自动对账！二维码只有两小时的实效！</strong></span></h4>";
        $.alert({
            text: message,
            callback: function (re) {
                if (re) {
                    weiXinQRCode(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
                }
            }
        });
    }
    //新增嘉联二维码支付
    function addPayfeeJiaLianServerThree() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        var payStatus = $("#payStatusAccount" + radioAccount).val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择结算单！"
            });
            return;
        }
        if ($("#accountListBodyThree" + radioAccount).find("tr").length > 0) {
            $.alert({
                text: "当前结算单已经生成缴费信息，不能生成二维码！"
            });
            return;
        }
        var message = "<h4>各位管家好:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;嘉联二维码扫码支付完成后<span style='color:red'><strong>不需要进行任何操作，系统自动对账！</strong></span></h4>";
        $.alert({
            text: message,
            callback: function (re) {
                if (re) {
                    JiaLianQRCode(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount, payStatus);
                }
            }
        });
    }
</script>
</html>