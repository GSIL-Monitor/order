<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<!-- 商品订单基本信息 -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style>
        .tabOrder {
            border-left: 0px;
            border-top: 1px solid #CCC;
            border-right: 0px;
            border-bottom: 1px solid #CCC;
            margin: auto auto auto 5px
        }
    </style>
    <script src="${ctx}/js/main.js"></script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<input type="hidden" name="orderId" id="orderIdBasicsItem">
<input type="hidden" name="orderStatusBasicsItem" id="orderStatusBasicsItem">
<div class="mytabs-wrap">
    <ul class="mytabs">
        <li class="tab-item tab-active" id="itemBasic1" onclick="itemBasic(1);">基本信息</li>
        <li class="tab-item" id="itemBasic2" onclick="itemBasic(2);">缴费</li>
        <li class="tab-item" id="itemBasic3" onclick="itemBasic(3);">转单记录</li>
    </ul>
</div>
<div id="arr">
    <span id="left"><</span><span id="right">></span>
</div>
<div class="tab-content">
    <div class="tab-selected" id="basicItem">
        <div class="widget" id="orderItemaddressAddButton" style="hieght:30px; display:none;">
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs"
                    onclick="openUserAddress();">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                   title="修改收货地址">修改收货地址</i></button>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <h4>详细信息</h4>
            </div>
        </div>
        <div class="widget-content">
            <input type="hidden" name="receiverId" id="receiverIdBasicItem">
            <input type="hidden" name="userId" id="userIdBasicItem">
            <input type="hidden" id="accountCreateTimeBasicItem" name="accountCreateTimeBasicItem"/>
            <input type="hidden" id="accountSystemDateBasicItem" name="accountSystemDateBasicItem"/>
            <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
                <tr>
                    <td width="55%" colspan="2">订单编号：<span id="orderCodes"></span>
                    </td>
                </tr>
                <tr>
                    <td width="45%" colspan="2">创建时间：<span id="createTimes"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        订单省份：<span id="receiverProvinceBasicItem"></span>
                    </td>
                    <td>
                        订单城市：<span id="receiverCityBasicItem"></span>
                    </td>
                </tr>
                <tr>
                    <td width="50%">订单运费：<span id="orderDeliverPay"></span>
                    </td>
                    <td width="50%">紧急程度：<span id="orderCritical"></span>
                    </td>
                </tr>
                <tr>
                    <td width="50%">订单状态：<span id="orderstatus"></span>
                    </td>
                    <td width="50%">支付状态：<span id="payTextItem"></span>
                    </td>
                </tr>
                <tr>
                    <% if (!CookieUtils.getJSONKey(request, "managerType").equals("2")) { %>
                    <td width="50%">订单来源：<span id="ordersourceid"></span>
                    </td>
                    <%} %>
                    <td>
                        订单渠道：<span id="channelTextBasicitem"></span>
                    </td>
                </tr>
                <tr>
                    <td>
                        带单人员：<span id="personitem"></span>
                    </td>
                    <td>
                        带单人电话：<span id="personmobileitem"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">方案编号：<span id="basicOneSolutionItem"></span></td>
                </tr>
                <tr>
                    <td colspan="2">订单备注：<span id="orderRemark"></span></td>
                </tr>
            </table>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <h4>客户信息</h4>
            </div>
        </div>
        <div>
            <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
                <tr>
                    <td width="50%">
                        客户姓名：<span id="nameBasicsItem"></span>
                    </td>
                    <td>
                        <button type="button" class="btn btn-sm btn-default fr mr20"
                                onclick="javascript:parent.editUserHref();">完善客户资料
                        </button>
                    </td>
                </tr>
                <tr>
                    <td width="50%">
                        客户电话：<span id="mobileBasicsItem"></span>
                    </td>
                    <td width="50%">
                        性别：<span id="sexBasicsItem"></span>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        家庭地址：<span id="userAddressBasicsItem"></span>
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
                        <tbody id="orderInforItem">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <h4>收货人信息</h4>
            </div>
        </div>
        <div>
            <table class="table table-condensed">
                <tr>
                    <td width="40%">收货人姓名：<span id="receiverName"></span>
                    </td>
                    <td width="60%">收货人电话：<span id="receiverMobile"></span>
                    </td>
                </tr>
                <tr>
                    <td width="50%">收货所在省份：<span id="receiverProvince"></span>
                    </td>
                    <td width="50%">收货所在城市：<span id="receiverCity"></span>
                    </td>
                </tr>
                <tr>
                    <td width="50%" colspan="2" rowspan="2">收货人详细地址：<span id="receiverAddress"></span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="feesItem" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="addAccount();"
                    id="addAccountBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i>
            </button>
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="updateAccount();"
                    id="updateAccountBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i>
            </button>
            <!-- 删除结算单，结算中心特殊权限 -->
            <auth:menu menuId="1150">
                <button type="button" class="btn btn-primary btn-xs" onclick="deleteAccount();" id="deleteAccountBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="删除结算">删除结算</i></button>
            </auth:menu>
        </div>
        <div class="widget">
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="addPayfee();">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i>
            </button>
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="updatePayfee()"
                    id="updatePayfeeItemBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i>
            </button>
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="addPayfeeWeiXin()"
                    id="addPayfeeWeiXinBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="微信扫码支付">微信扫码支付</i>
            </button>
            <button type="button" name="orderButtonsAllItem" class="btn btn-primary btn-xs" onclick="addPayfeeJiaLian()"
                    id="addPayfeeJiaLianBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="嘉联二维码">嘉联二维码</i>
            </button>
            <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                    onclick="addPayfeeLianDongServer(2)" id="">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="联动二维码">联动二维码</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="project-order-news">
                <div class="row">
                    <div class="col-xs-12"><h4>缴费信息</h4></div>
                </div>
                <div id="accountListBodyItem">
                </div>
            </div>
        </div>
    </div>
    <div id="turnOrderLogItem" style="display:none; border:1px solid #CCC;">
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
                <tbody id="turnOrderLogListItem">

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function setBasicItem(orderId, userMobile, cateType, totalPay, rechargeBy) {
        $("#orderIdBasicsItem").val(orderId);
        $("#userMobileBasicsItem").val(userMobile);
        getItemBasics(orderId);
        $("input:radio[name=radioAccount]").each(function () {
            $(this).val("");
        });
        queryAccount(orderId, "accountListBodyItem", cateType, totalPay);
        // 订单状态控制按钮显示与隐藏
        showBtn(cateType, orderId);
        setOrderButtonsAllItem();
        setOrderLoginitem();
        queryOrderTurnLog(orderId, 'turnOrderLogListItem');
    }
    function setOrderLoginitem() {
        var rechargeBy = $("#checkedRechargeBy").val();
        if (rechargeBy == null || rechargeBy == '') {
            //alert("登录人："+loginBy+"负责人"+rechargeBy);
            $("#itemBasic2").css("display", "none");
        } else {
            $("#itemBasic2").css("display", "block");
        }
    }
    function setOrderButtonsAllItem() {
        var loginByOrNot = $("#checkedLoginByOrNot").val();
        var orderStatus = $("#checkedOrderStatus").val();
        var loginRechargeOrNot = $("#checkedLoginRechargeOrNot").val();
        var rechargeBy = $("#checkedRechargeBy").val();
        // 当前登录人员操作按钮权限控制
        // 按是否已经分包分为两种不同控制方式
        if (rechargeBy == null || rechargeBy == "" || rechargeBy == 0) { // 未分包
            if (loginByOrNot == null || loginByOrNot == "" || loginByOrNot == 0) {
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).hide();
                });
                // 对于FA和三方订单，需要对订单状态进行判断
            } else {
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).show();
                });
            }
        } else { // 已分包,负责人对应的操作权限
            if (loginRechargeOrNot == null || loginRechargeOrNot == "" || loginRechargeOrNot == 0) {
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).hide();
                });
            } else {
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).show();
                });
            }
        }

    }
    //取到订单详细信息
    function getItemBasics(orderId) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/order/queryOrderBasicItem",
            data: {
                id: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var html = "";
                    var num = $.each(data.list, function (i, v) {
                        //订单信息
                        $("#basicOneSolutionItem").text(v.solutionCode);
                        $("#orderCodes").text(v.orderCode);
                        $("#payTextItem").text(v.payText);
                        $("#createTimes").text(v.createTime);
                        $("#orderDeliverPay").text(v.deliverPay);
                        $("#orderCritical").text(v.criticalText);
                        $("#orderRemark").text(v.remark);
                        $("#receiverProvinceBasicItem").text(v.receiverProvince);
                        $("#receiverCityBasicItem").text(v.receiverCity);
                        $("#channelTextBasicitem").text(v.channelText);
                        $("#personitem").text(v.person);
                        $("#personmobileitem").text(v.personmobile);
                        // 客户信息
                        $("#nameBasicsItem").text(v.userName);
                        $("#mobileBasicsItem").text(v.userMobile);
                        $("#sexBasicsItem").text(v.sex == 1 ? "男" : "女");
                        $("#birthBasicsItem").text(v.birth == "" ? "" : numberToDatestr(v.birth, 8));
                        $("#userAddressBasicsItem").text(v.userAddress);
                        // 基础信息：收货人信息
                        $("#userIdBasicItem").val(v.userId);
                        $("#receiverIdBasicItem").val(v.receiverId);
                        $("#orderStatusBasicsItem").val(v.orderStatus);
                        // 1新建，2受理、18待受理
                        if (v.orderStatus == 1 || v.orderStatus == 2 || v.orderStatus == 18) {
                            $("#orderItemaddressAddButton").css("display", "block");
                        } else {
                            $("#orderItemaddressAddButton").css("display", "none");
                        }
                        $("#receiverName").text(v.receiverName);
                        $("#receiverMobile").text(v.receiverMobile);
                        $("#receiverAddress").text(v.receiverArea + v.receiverAddress);
                        $("#receiverZipcode").text(v.receiverZipcode);
                        $("#receiverProvince").text(v.receiverProvince);
                        $("#receiverCity").text(v.receiverCity);
                        $("#orderstatus").text(v.statusText);
                        $("#ordersourceid").text(v.sourceText);
                        $("#ordersourceid").text(v.sourceText);
                        html += "<tr style='height:25px;'>";
                        html += "<td>" + v.item.productName + "(" + v.item.productUnitText + ")</td>";
                        html += "<td>" + v.item.productPriceTypeText + "</td>";
                        html += "<td>" + v.item.nowPrice + "</td>";
                        html += "<td>" + v.item.quantity + "</td>";
                        html += "<td>" + v.item.productSpec + "</td>";
                        html += "</tr>";
                    })
                    html += "<tr></tr>";
                    document.getElementById("orderInforItem").innerHTML = html;
                }
            }
        });
    }
    function itemBasic(num) {
        if (num == 1) {
            $("#itemBasic1").addClass("tab-selected");
            $("#itemBasic2").removeClass("tab-selected");
            $("#itemBasic3").removeClass("tab-selected");
            $("#basicItem").css("display", "block");
            $("#feesItem").css("display", "none");
            $("#turnOrderLogItem").css("display", "none");
        } else if (num == 2) {
            $("#itemBasic1").removeClass("tab-selected");
            $("#itemBasic2").addClass("tab-selected");
            $("#itemBasic3").removeClass("tab-selected");
            $("#basicItem").css("display", "none");
            $("#feesItem").css("display", "block");
            $("#turnOrderLogItem").css("display", "none");
        } else if (num == 3) {
            $("#itemBasic1").removeClass("tab-selected");
            $("#itemBasic2").removeClass("tab-selected");
            $("#itemBasic3").addClass("tab-selected");
            $("#basicItem").css("display", "none");
            $("#feesItem").css("display", "none");
            $("#turnOrderLogItem").css("display", "block");
            queryOrderTurnLog($("#orderIdBasicsItem").val(), 'turnOrderLogListItem');
        }
        setOrderButtonsAllItem();
    }
    // 新增结算
    function addAccount() {

        orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), 0, parent.$("#checkedIscollection").val());
    }
    // 修改结算
    function updateAccount() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择要修改的结算单！"
            });
            return;
        }
        var feePost = $("#feePostPayfee" + radioAccount).val();
        //使用“他人代收”支付方式的缴费和结算单，如果在财务系统中汇总单状态为“未生成”，则可以修改结算单信息
        if (feePost != "20250014") {
            if ($("#accountListBodyItem" + radioAccount).find("tr").length != 0) {
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
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicItem");
            querySysdate("accountSystemDateBasicItem");
            var updateItemCreTime = $("#accountCreateTimeBasicItem").val();
            var updateItemSysdate = $("#accountSystemDateBasicItem").val();
            deleteAccountTimeRule(updateItemCreTime, updateItemSysdate);
            if (timeRuleFlag) {
                orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        } else {
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicItem");
            querySysdate("accountSystemDateBasicItem");
            var updateItemCreTime = $("#accountCreateTimeBasicItem").val();
            var updateItemSysdate = $("#accountSystemDateBasicItem").val();
            deleteAccountTimeRule(updateItemCreTime, updateItemSysdate);
            if (timeRuleFlag) {
                $.ajax({
                    url: ctx + "/payfee/checkFinSummaryForAccount",
                    data: {
                        accountId: radioAccount
                    },
                    type: "post",
                    async: false,
                    success: function (data) {
                        if (data.msg == "00") {
                            updateAccountForTRDS(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
                        } else if (data.msg == "02") {
                            $.alert({text: "汇总单已生成,无法修改!"});
                        } else {
                            $.alert({text: "系统错误!"});
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
    function deleteAccount() {
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
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicItem");
            querySysdate("accountSystemDateBasicItem");
            var itemCreTime = $("#accountCreateTimeBasicItem").val();
            var itemSysdate = $("#accountSystemDateBasicItem").val();
            deleteAccountTimeRule(itemCreTime, itemSysdate);
            if (timeRuleFlag) {
                $.confirm({
                    text: "是否确认删除结算？", callback: function (re) {
                        if (re) {
                            deleteAccountById(radioAccount);
                            queryAccount($("#checkedOrderId").val(), "accountListBodyItem", $("#checkedCateType").val(), $("#checkedTotalPay").val());
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
        var result = checkAccountReviewState(radioAccount);
        if (result != "00") {
            $.alert({
                text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
            });
            return;
        }
    }
    // 新增缴费
    function addPayfee() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyItem" + radioAccount).find("tr").length > 0) {
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
    function updatePayfee() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyItem" + radioAccount).find("tr").length == 0) {
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
        var accountAmount = $("#accountAmount" + radioAccount).text();
        var userMobile = parent.$("#checkedUserMobile").val();
        //结算单类型
        var payType = $("#accountpayType" + radioAccount).val();

        loadPayfeeMinCreTime(radioAccount, "accountCreateTimeBasicItem");
        querySysdate("accountSystemDateBasicItem");
        var itemMinCreTime = $("#accountCreateTimeBasicItem").val();
        var itemMinSysdate = $("#accountSystemDateBasicItem").val();
        deleteAccountTimeRule(itemMinCreTime, itemMinSysdate);
        var result = checkAccountReviewState(radioAccount);
        if (result != "00") {
            $.alert({
                text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
            });
            return;
        }
        if (timeRuleFlag) {
            orderBasicPayfee(radioAccount, parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), accountAmount, userMobile, 2, parent.$("#checkedOrderChannel").val(), parent.$("#checkedUserId").val(), payType);
        } else {
            $.alert({
                text: "当前缴费已过有效操作时间，不可操作！"
            });
            return;
        }

    }
    // FA订单 修改
    function openUserAddress() {
        var userId = $('#checkedUserId').val();
        var city = $('#checkedCity').val();
        var cityName = $('#receiverCity').text();
        var orderId = $('#checkedOrderId').val();
        var userName = $("#nameBasicsItem").text();
        if (city == null) {
            city = "";
        }
        if (cityName == null) {
            cityName = "";
        }
        openModule('${ctx }/jsp/orderItemEdit.jsp', {
            'userId': userId,
            'city': city,
            'cityName': cityName,
            'orderId': orderId,
            'userName': userName
        }, '', '', 'orderItemEdit');
    }

    //新增微信扫码支付
    function addPayfeeWeiXin() {
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
    function addPayfeeJiaLian() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        var payStatus = $("#payStatusAccount" + radioAccount).val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择结算单！"
            });
            return;
        }
        if ($("#accountListBodyItem" + radioAccount).find("tr").length > 0) {
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