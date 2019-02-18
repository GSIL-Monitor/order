<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="${ctx}/js/orderBase.js"></script>
    <script type="text/javascript" src="${ctx}/js/order_three/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${ctx}/js/order_three/common.js"></script>
    <style>
        .orderlisttable tr td {
            text-align: left
        }

        .feesOnediv {
            margin: 20px auto auto 15px
        }

        .table thead tr th, .table tbody tr td {
            font-family: Arial;
            font-size: 12px;
            font-weight: normal;
        }

        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 12px;
            font-weight: normal;
        }

        .excelFileDiv {
            position: relative;
            width: 340px
        }

        .excelFilebtn {
            height: 30px;
            width: 90px;
        }

        .excelFile {
            position: absolute;
            top: 0;
            left: 110px;
            height: 28px;
            filter: alpha(opacity:0);
            opacity: 0;
            width: 105px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<!-- /.col-lg-12 -->
<div class="row">
    <div class="col-lg-12">
        <ol class="page-header breadcrumb">
            <li><a href="#">Home</a></li>
            <li><a href="#">订单管理系统</a></li>
            <li><a href="#">订单管理</a></li>
            <li class="active">订单管理</li>
        </ol>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-8">
        <div class="panel panel-default">
            <!-- /.panel-heading -->
            <div class="panel-body">
                <tbody class="dataTable_wrapper">
                <div class="dataTable-info">
                    <header>
                        <h4>查询</h4>
                        <!--表单显示及隐藏-->
                        <div class="drop-on">
                            <span><i class="glyphicon glyphicon-chevron-up"></i></span>
                        </div>
                        <div class="drop-down">
                            <span><i class="glyphicon glyphicon-chevron-down"></i></span>
                        </div>
                    </header>
                    <div class="info-select clearfix">
                        <form class="form-inline">
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">订单编号:</span>
                                        <input type="text" name="orderCode" id="orderCode" class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">客户姓名:</span>
                                        <input type="text" name="userName" id="userNameOrder" class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">客户电话:</span>
                                        <input type="text" name="userMobile" id="userMobileOrder" class="form-control"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">订单来源:</span>
                                        <select name="orderSourceId" id="orderSourceId" class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">订单类型:</span>
                                        <select name="serverType" id="serverType"
                                                onchange="javascript:checkedOrderStatusSelect(this)"
                                                class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">订单状态:</span>
                                        <select name="orderStatus" id="orderStatus" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:</span>
                                        <select name="orderCheckedProvinces" id="orderCheckedProvinces"
                                                onclick="setSelCity('orderCheckedProvinces','orderCheckedCitys')"
                                                class="form-control"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</span>
                                        <select name="orderCheckedCitys" id="orderCheckedCitys" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">支付状态:</span>
                                        <select name="orderPay" id="orderPay" class="form-control">
                                            <option value="">...请选择...</option>
                                            <option value="20110001">未支付</option>
                                            <option value="20110002">部分支付</option>
                                            <option value="20110003">支付完成</option>
                                            <option value="20130003">确认有效</option>
                                            <option value="20130002">确认无效</option>
                                            <option value="20130008">退款成功</option>
                                        </select>
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label><span style="font-weight:bold;">订单渠道:</span>
                                        <select name="orderChannel" id="orderChannel" class="form-control"
                                                style="width:220px;"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label><span style="font-weight:bold;">负责部门:</span>
                                        <select id="mendianSelect" class="form-control" style="width:220px;">
                                            <option value="-1">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label><span style="font-weight:bold;">负责人&nbsp;&nbsp;&nbsp;&nbsp;:</span>
                                        <select id="guanjiaSelect" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>

                            </div>
                            <div class="row" style="display:none" id="birthTimeDiv">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">预产期:&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <input id="birthTimeOrder" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <input id="birthTimeOrderEnd" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">创建时间:</span>
                                        <input id="createTimeOrder" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <input id="createTimeEnd" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">三方平台订单号:</span>
                                        <input type="text" name="threeOrderCode" id="threeOrderCode" class="form-control"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">排期时间:</span>
                                        <input name="startLinedTime" id="startLinedTime" type="text" class="Wdate form-control"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endLinedTime\'||\'%y-%M-{%d}\')}',readOnly:true})">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                        <input name="endLinedTime" id="endLinedTime" type="text" class="Wdate form-control"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startLinedTime\')}',readOnly:true})">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label><span style="font-weight:bold;">服务对象:</span>
                                        <select id="serviceObjectQuery"  class="form-control" onchange="selectOnceAgreementCheckStatus()">
                                            <option value="" selected="selected">...请选择...</option>
                                            <option value="2">个人</option>
                                            <option value="1" >企业</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <div id="onceAgreementCheckStatusDiv" class="form-group"style="display: none">
                                        <label><span style="font-weight:bold;">合同审核状态:</span>
                                            <select id="onceAgreementCheckStatus"  class="form-control">
                                                <option value="" selected="selected">...请选择...</option>
                                                <option value="1">未处理</option>
                                                <option value="2" >审核通过</option>
                                                <option value="3" >驳回</option>
                                            </select>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group col-xs-8" align="center">
                                    <button type="button" class="btn btn-sm btn-default" onclick="queryOrdersByLike(0,10);">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <button type="button" class="btn btn-sm btn-default" onclick="resetOrders();">重置 </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel-heading panel-new" id="orderLeftPanels">
                    <button class="xinzeng btn btn-sm btn-default" type="button"
                            onclick="openModule('/order/jsp/orderUser.jsp',{},{},{},'orderUser')">
                        <em class="add glyphicon glyphicon-plus-sign"></em>新增
                    </button>
                    <button id="openOrderFollow" class="xinzeng btn btn-sm btn-default" type="button"
                            onclick="selectPrincipal();">
                        <em class="add glyphicon glyphicon-plus-sign"></em>转单
                    </button>
                    <!--  <div class="excelFileDiv">
                           <button class="xinzeng btn btn-sm btn-default excelFilebtn" type="button">
                           <em class="add glyphicon glyphicon-plus-sign "></em>导入订单</button>
                           <input type="file" name="excelFile" class="excelFile" id="excelFile" onchange="uploadFile()" />
                       </div> -->
                    <div class="excelFileDiv">
                        <button onclick="uploadFileNew();" class="xinzeng btn btn-sm btn-default excelFilebtn"
                                type="button">
                            <em class="add glyphicon glyphicon-plus-sign "></em>批量下单
                        </button>
                    </div>
                    <button class="btn btn-sm btn-default" type="button" onclick="downloadOrderImportRecord()">
                        <em class="add glyphicon glyphicon-plus-sign"></em>导入记录信息
                    </button>

                    <button id="excleOrder" class="btn btn-sm btn-default" type="button" onclick="exportOrders()">
                        <em class="add glyphicon glyphicon-plus-sign"></em>导出订单
                    </button>
                </div>

                <!--列表开始-->
                <div class="panel-content table-responsive">
                    <table class="table table-hover table-striped" style="width:1400px;" id="tablecode">
                        <thead>
                        <tr>
                            <th width="4%">序号</th>
                            <th width="8%">订单编号</th>
                            <th width="6%">客户姓名</th>
                            <th width="7%">订单类型</th>
                            <th width="7%">创建时间</th>
                            <th width="6%">录入人</th>
                            <th width="7%">录入部门</th>
                            <th width="6%">负责人</th>
                            <th width="7%">负责部门</th>
                            <th width="7%">订单状态</th>
                            <th width="7%">支付状态</th>
                            <% if (!CookieUtils.getJSONKey(request, "managerType").equals("2")) { %>
                            <th width="7%">订单渠道</th>
                            <th width="7%">订单来源</th>
                            <%} %>
                            <th width="8%">三方订单编号</th>
                            <th width="6%">服务对象</th>

                        </tr>
                        </thead>
                        <tbody id="listBodyOrder" style="width:100%">
                        </tbody>
                    </table>
                </div>
                <ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
                </tbody>
            </div>
        </div>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4" id="orderRightTabs">
        <div class="aside-tab">
            <input type="hidden" id="checkedOrderId" name="checkedOrderId">
            <input type="hidden" id="checkedUserMobile" name="checkedUserMobile">
            <input type="hidden" id="checkedOrderType" name="checkedOrderType">
            <input type="hidden" id="checkedCateType" name="checkedCateType">
            <input type="hidden" id="checkedUserId" name="checkedUserId">
            <input type="hidden" id="checkedCity" name="checkedCity">
            <input type="hidden" id="checkedCityName" name="checkedCityName">
            <input type="hidden" id="checkedTotalPay" name="checkedTotalPay">
            <input type="hidden" id="checkedPayStatus" name="checkedPayStatus">
            <input type="hidden" id="checkedOrderStatus" name="checkedOrderStatus">
            <input type="hidden" id="checkedLoginByOrNot" name="checkedLoginByOrNot">
            <input type="hidden" id="checkedLoginRechargeOrNot" name="checkedLoginRechargeOrNot">
            <input type="hidden" id="checkedRechargeBy" name="checkedRechargeBy">
            <input type="hidden" id="checkedLoginBy" name="checkedLoginBy">
            <input type="hidden" id="checkedOrderChannel" name="checkedOrderChannel">
            <input type="hidden" id="selectCode" name="selectCode">
            <input type="hidden" id="checkedIscollection" name="checkedIscollection">
            <input type="hidden" id="checkedServiceObject" name="checkedServiceObject">

            <div id="orderBasicServerCont" style="display:none;">
                <%@ include file="orderBasicServer.jsp" %>
            </div>
            <div id="orderBasicServerOne" style="display:none;">
                <%@ include file="orderBasicServerOne.jsp" %>
            </div>
            <div id="orderBasicItem" style="display:none;">
                <%@ include file="orderBasicItem.jsp" %>
            </div>
            <div id="orderBasicServerThree" style="display:none;">
                <%@ include file="orderBasicServerThree.jsp" %>
            </div>
            <div id="orderBasic" style="display:none;">
                <%@ include file="orderBasic.jsp" %>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#excleOrder").css("display", "none");
        queryOrderServerTypeNew("1002", 12, "serverType");
        queryBaseDictionary("2018", 8, "orderSourceId");
        queryChannels("orderChannel", {"valid": 1});
        setSelProvinceCitys("101", 6, "orderCheckedProvinces");
        selectOrderDeptName("mendianSelect");
        queryOrders(0, 10);//查询订单列表

        $("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
        checkOrderBasic($("#checkedOrderId").val());
        /*loading*/
        $('#shclDefault').shCircleLoader();
        /*电话号码显示隐藏*/
        /* show(".phone",".phone-show"); */
        tabx(".tab-item", ".main");
        /*表单合并*/
        drop(".drop-on", ".drop-down", ".info-select");
        /*阻止冒泡*/
        $("#listBodyOrder > tr input[type=checkbox]").click(function (event) {
            event.stopPropagation();
        });
    })


    function checkedOrderStatusSelect(status) {
        var values = status.options[status.options.selectedIndex].value.split("-");
        //当订单类型为月嫂或者育婴师
        if (values[0] == 100200010001 || values[0] == 100200010002) {
            $("#birthTimeDiv").css("display", "block")
        } else {
            $("#birthTimeDiv").css("display", "none")
        }
        var html = "<option style='color:blue;' value='' >...请选择...</option>";
        //1 新建,2已受理,3匹配中, 4已匹配, 5待面试, 6面试成功, 7已签约, 8已上户, 9已完成, 10已取消, 11已下户,12已终止,13捡货中, 14配送中 16 三方订单匹配失败
        if (values[1] == 1) {
            html += "<option value='1' >新建</option>";
            html += "<option value='2' >已受理</option>";
            html += "<option value='3' >匹配中</option>";
            html += "<option value='4' >已匹配</option>";
            html += "<option value='8' >已上户</option>";
            html += "<option value='11' >已下户</option>";
            html += "<option value='9' >已完成</option>";
            html += "<option value='10' >已取消</option>";
        } else if (values[1] == 2) {
            html += "<option value='1' >新建</option>";
            html += "<option value='2' >已受理</option>";
            html += "<option value='3' >匹配中</option>";
            html += "<option value='4' >已匹配</option>";
            html += "<option value='5' >待面试</option>";
            html += "<option value='6' >面试成功</option>";
            html += "<option value='7' >已签约</option>";
            html += "<option value='8' >已上户</option>";
            html += "<option value='9' >已完成</option>";
            html += "<option value='10' >已取消</option>";
            html += "<option value='11' >已下户</option>";
            html += "<option value='12' >已终止</option>";
        } else if (values[1] == 3) {
            html += "<option value='1' >新建</option>";
            html += "<option value='18' >待受理</option>";
            html += "<option value='2' >已受理</option>";
            html += "<option value='13' >捡货中</option>";
            html += "<option value='14' >配送中 </option>";
            html += "<option value='9' >已完成</option>";
            html += "<option value='10' >已取消</option>";
        } else {
            // html ="<option style='color:blue;' value='' >...无可选项...</option>";
            html += "<option value='18' >待受理</option>";
            html += "<option value='2' >已受理</option>";
            html += "<option value='9' >已完成</option>";
        }
        $("#orderStatus").html(html);
    }
    function queryOrders(num, pageCount) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/order/queryOrder?curPage=" + num + "&pageCount=" + pageCount + "&cateTypeNot=5,6,7,8",
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                showMsg(data.msg);
                $("#pageInfoDiv").html("");
                $("#pageInfoDiv").pagination1(data.page, listBodyOrder);
                var pageCount = data.page.pageCount;
                var curPage = data.page.curPage;
                var total = curPage * pageCount;

                var html = "";
                if (data.msg == "00") {
                    var num =
                            $.each(data.list, function (i, v) {
                                if (i == 0) {
                                    $("#checkedOrderId").val(v.id);
                                    $("#checkedLoginBy").val(v.loginBy);
                                }
                                num = i + 1;
                                if (<%=CookieUtils.getJSONKey(request, "managerType") %>==2
                                )
                                {
                                    html += "<tr onclick=\"checkOrderBasic(" + v.id + ")\">";
                                    html += "<td>" + (total + num - pageCount);
                                    html += "</td><td align='left' id='selectCode'>" + v.orderCode;
                                    html += "</td><td align='left' >" + v.userName;
                                    html += "</td><td>" + v.typeText;
                                    html += "</td><td>" + numberToDatestr(v.createTime, 12);
                                    html += "</td><td align='left'>" + v.createByText;
                                    html += "</td><td align='left'>" + v.createDeptText;
                                    html += "</td><td align='left'>" + v.rechargeByText;
                                    html += "</td><td align='left'>" + v.rechargeDeptText;
                                    html += "</td><td align='left'>" + v.statusText;
                                    html += "</td><td align='left'>" + v.payText;
                                    html += "</td><td align='left'>" + v.threeOrderCode;
                                    html += "</td><td align='left'>" + v.serviceObject;
                                    html += "</td></tr>";
                                }
                                else
                                {
                                    html += "<tr onclick=\"checkOrderBasic(" + v.id + ")\">";
                                    html += "<td>" + (total + num - pageCount);
                                    html += "</td><td align='left' id='selectCode'>" + v.orderCode;
                                    html += "</td><td align='left'>" + v.userName;
                                    html += "</td><td>" + v.typeText;
                                    html += "</td><td>" + numberToDatestr(v.createTime, 12);
                                    html += "</td><td align='left'>" + v.createByText;
                                    html += "</td><td align='left'>" + v.createDeptText;
                                    html += "</td><td align='left'>" + v.rechargeByText;
                                    html += "</td><td align='left'>" + v.rechargeDeptText;
                                    html += "</td><td align='left'>" + v.statusText;
                                    html += "</td><td align='left'>" + v.payText;
                                    html += "</td><td align='left'>" + v.channelText;
                                    html += "</td><td align='left'>" + v.sourceText;
                                    html += "</td><td align='left'>" + v.threeOrderCode;
                                    html += "</td><td align='left'>" + v.serviceObject;
                                    html += "</td></tr>";
                                }

                            })
                    $("#pageInfoDiv").show();
                } else {
                    html = "<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
                    $("#pageInfoDiv").hide();
                    checkOrderBasic("");
                }
                $("#listBodyOrder").html(html);
                if (data.msg == "00") {
                    trColorOrder("#listBodyOrder tr");
                    var orderId = $("#checkedOrderId").val();
                    checkOrderBasic(orderId);
                    $("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
                }
            }
        });
    }

    /**
     * 分页跳转使用
     */
    function listBodyOrder(num, pageCount) {
        $("#listBodyOrder").empty();
        queryOrdersByLike(num, pageCount);

    }


    //根据菜单地址，链接，介绍进行模糊查询
    function queryOrdersByLike(num, pageCount) {
        //var options=$("#serverType option:selected");  //获取选中的项
        var ctx = $("#ctx").val();
        var orderCode = $("#orderCode").val();
        var userName = $("#userNameOrder").val();
        var createTime = $("#createTimeOrder").val();
        var createTimeEnd = $("#createTimeEnd").val();
        var userMobile = $("#userMobileOrder").val();
        var serverType = $("#serverType").val();
        var orderType = serverType.split("-")[0];
        var cateType = serverType.split("-")[1];
        var orderStatus = $("#orderStatus").val();
        var orderSourceId = $("#orderSourceId").val();
        var orderChannel = $("#orderChannel").val();
        var critical = $("#critical").val();
        var orderGroupid = $("#orderGroupid").val();
        var managerId = $("#managerId").val();
        var city = $("#orderCheckedCitys").val();
        var mendian = $("#mendianSelect").find("option:selected").val();
        var rechargeByGuan = $("#guanjiaSelect").find("option:selected").val();
        var orderPay = $("#orderPay").val();
        var birthTimeOrder = $("#birthTimeOrder").val();
        var birthTimeOrderEnd = $("#birthTimeOrderEnd").val();
        var threeOrderCode = $("#threeOrderCode").val();
        /** 周鑫 2019-01-22 服务对象  **/
        var serviceObject = $("#serviceObjectQuery").val();
        /** 周鑫 2019-01-22 服务对象  **/
        var reg = new RegExp("-","g");
        var startLinedTime = $("#startLinedTime").val().replace(reg,"");//排期起始时间
        var endLinedTime = $("#endLinedTime").val().replace(reg,"");//排期结束时间
        var checkStatus = $("#onceAgreementCheckStatus").val();//单次合同审核状态
        if (city == null || city == "" || city == -1) {
            city = $("#orderCheckedProvinces").val();
        }
        $.ajax({
            url: ctx + "/order/queryOrder?curPage=" + num + "&pageCount=" + pageCount,
            data: {
                orderCode: orderCode,
                userName: userName,
                createTime: createTime,
                createTimeEnd: createTimeEnd,
                userMobile: userMobile,
                cateType: cateType,
                orderType: orderType,
                orderStatus: orderStatus,
                orderSourceId: orderSourceId,
                orderChannel: orderChannel,
                critical: critical,
                serviceObject:serviceObject,
                orderGroupid: orderGroupid,
                managerId: managerId,
                city: city,
                cateTypeNot: "5,6,7,8",
                threeOrderItemId: rechargeByGuan,
                payText: orderPay,
                birthTimeOrder: birthTimeOrder,
                birthTimeOrderEnd: birthTimeOrderEnd,
                threeOrderCode: threeOrderCode,
                rechargeDept: mendian,
                startLinedTime:startLinedTime,
                endLinedTime:endLinedTime,
                checkStatus:checkStatus
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                showMsg(data.msg);
                var html = "";
                $("#pageInfoDiv").pagination1(data.page, listBodyOrder);
                var pageCount = data.page.pageCount;
                var curPage = data.page.curPage;
                var total = curPage * pageCount;

                if (data.msg == "00") {
                    var num =
                            $.each(data.list, function (i, v) {
                                if (i == 0) {
                                    $("#checkedOrderId").val(v.id);
                                    $("#checkedLoginBy").val(v.loginBy);
                                }
                                num = i + 1;
                                //审计账号判断
                                if (<%=CookieUtils.getJSONKey(request, "managerType") %>==2
                                )
                                {
                                    html += "<tr onclick=\"checkOrderBasic(" + v.id + ")\">";
                                    html += "<td>" + (total + num - pageCount);
                                    html += "</td><td align='left'>" + v.orderCode;
                                    html += "</td><td align='left'>" + v.userName;
                                    html += "</td><td>" + v.typeText;
                                    html += "</td><td>" + numberToDatestr(v.createTime, 8);
                                    html += "</td><td align='left'>" + v.createByText;
                                    html += "</td><td align='left'>" + v.createDeptText;
                                    html += "</td><td align='left'>" + v.rechargeByText;
                                    html += "</td><td align='left'>" + v.rechargeDeptText;
                                    html += "</td><td align='left'>" + v.statusText;
                                    html += "</td><td align='left'>" + v.payText;
                                    html += "</td><td align='left'>" + v.threeOrderCode;
                                    html += "</td><td align='left'>" + v.serviceObject;
                                    html += "</td></tr>";
                                }
                                else
                                {
                                    html += "<tr onclick=\"checkOrderBasic(" + v.id + ")\">";
                                    html += "<td>" + (total + num - pageCount);
                                    html += "</td><td align='left'>" + v.orderCode;
                                    html += "</td><td align='left'>" + v.userName;
                                    html += "</td><td>" + v.typeText;
                                    html += "</td><td>" + numberToDatestr(v.createTime, 8);
                                    html += "</td><td align='left'>" + v.createByText;
                                    html += "</td><td align='left'>" + v.createDeptText;
                                    html += "</td><td align='left'>" + v.rechargeByText;
                                    html += "</td><td align='left'>" + v.rechargeDeptText;
                                    html += "</td><td align='left'>" + v.statusText;
                                    html += "</td><td align='left'>" + v.payText;
                                    html += "</td><td align='left'>" + v.channelText;
                                    html += "</td><td align='left'>" + v.sourceText;
                                    html += "</td><td align='left'>" + v.threeOrderCode;
                                    html += "</td><td align='left'>" + v.serviceObject;
                                    html += "</td></tr>";
                                }

                            })
                    $("#pageInfoDiv").show();
                } else {
                    html = "<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
                    $("#pageInfoDiv").hide();
                    checkOrderBasic("");
                }
                $("#listBodyOrder").html(html);

                if (data.msg == "00") {
                    trColorOrder("#listBodyOrder tr");
                    var orderId = $("#checkedOrderId").val();
                    checkOrderBasic(orderId);
                    $("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
                }
            }
        });
    }

    // 基本信息显示
    function checkOrderBasic(orderId) {
        if (orderId != null && orderId > 0) {
            $.ajax({
                url: ctx + "/order/loadOrderByOrder",
                data: {
                    id: orderId
                },
                type: "post",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.msg == "00") {
                        $("#checkedOrderId").val(data.order.id);
                        $("#checkedOrderType").val(data.order.orderType);
                        $("#checkedCateType").val(data.order.cateType);
                        $("#checkedUserMobile").val(data.order.userMobile);
                        $("#checkedUserId").val(data.order.userId);
                        $("#checkedCity").val(data.order.city);
                        $("#checkedCityName").val(data.order.cityName);
                        $("#checkedTotalPay").val(data.order.totalPay);
                        $("#checkedPayStatus").val(data.order.payStatus);
                        $("#checkedOrderStatus").val(data.order.orderStatus);
                        $("#checkedLoginByOrNot").val(data.order.loginByOrNot);
                        $("#checkedLoginRechargeOrNot").val(data.order.loginRechargeOrNot);
                        $("#checkedRechargeBy").val(data.order.rechargeBy);
                        $("#checkedLoginBy").val(data.order.loginBy);
                        $("#checkedOrderChannel").val(data.order.orderChannel);
                        $("#selectCode").val(data.order.orderCode);
                        $("#checkedServiceObject").val(data.order.serviceObject);
                        //20180704 添加
                        $("#checkedIscollection").val(data.order.isCollection);
                        var orderId = $("#checkedOrderId").val();
                        var orderType = $("#checkedOrderType").val();
                        var cateType = $("#checkedCateType").val();
                        var userMobile = $("#checkedUserMobile").val();
                        var userId = $("#checkedUserId").val();
                        var city = $("#checkedCity").val();
                        var cityName = $("#checkedCityName").val();
                        var totalPay = $("#checkedTotalPay").val();
                        var payStatus = $("#checkedPayStatus").val();
                        var orderStatus = $("#checkedOrderStatus").val();
                        var loginByOrNot = $("#checkedLoginByOrNot").val();
                        var loginRechargeOrNot = $("#checkedLoginRechargeOrNot").val();
                        var rechargeBy = $("#checkedRechargeBy").val();
                        var loginBy = $("#checkedLoginBy").val();
                        // 当前登录人员操作按钮权限控制 负责人和登录人
                        if (rechargeBy == loginBy || rechargeBy == '' || rechargeBy == '0') {
                            $("#openOrderFollow").css("display", "block");
                        } else {
                            $("#openOrderFollow").css("display", "none");
                        }

                        if (cateType == 1) {
                            $("#orderBasicServerOne").css("display", "block");
                            $("#orderBasicServerCont").css("display", "none");
                            $("#orderBasicItem").css("display", "none");
                            $("#orderBasicServerThree").css("display", "none");
                            $("#orderBasic").css("display", "none");
                            setBasicServerOne(orderId, cateType, totalPay, orderStatus, rechargeBy);
                            if(data.order.serviceObject == 1){
                                $("#serverBasicOne8").css("display","block");
                            }else{
                                $("#serverBasicOne8").css("display","none");
                            }
                        } else if (cateType == 2) {
                            $("#orderBasicServerOne").css("display", "none");
                            $("#orderBasicServerCont").css("display", "block");
                            $("#orderBasicItem").css("display", "none");
                            $("#orderBasicServerThree").css("display", "none");
                            $("#orderBasic").css("display", "none");
                            setBasicServer(orderId, cateType, totalPay, orderStatus, rechargeBy);
                        } else if (cateType == 3) {
                            $("#orderBasicServerOne").css("display", "none");
                            $("#orderBasicServerCont").css("display", "none");
                            $("#orderBasicItem").css("display", "block");
                            $("#orderBasicServerThree").css("display", "none");
                            $("#orderBasic").css("display", "none");
                            setBasicItem(orderId, userMobile, cateType, totalPay, rechargeBy);
                        } else {
                            $("#orderBasicServerOne").css("display", "none");
                            $("#orderBasicServerCont").css("display", "none");
                            $("#orderBasicItem").css("display", "none");
                            $("#orderBasicServerThree").css("display", "block");
                            $("#orderBasic").css("display", "none");
                            if (orderId != null && orderId != "" && cateType != null && cateType != "") {
                                setBasicServerThree(orderId, cateType, totalPay, orderStatus, rechargeBy);
                            }
                        }
                    }
                }
            })
        } else {
            $("#orderBasicServerOne").css("display", "none");
            $("#orderBasicServerCont").css("display", "none");
            $("#orderBasicItem").css("display", "none");
            $("#orderBasicServerThree").css("display", "none");
            $("#orderBasic").css("display", "block");
        }
        //setBtnHideByLoginBy();
    }

    function createOrderTest() {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/order/createOrderTest",
            sync: false,
            success: function (data) {
                if (data.msg = "00") {
                    var ary = data.num.split(",");
                    alert("本次本生成订单编号：" + ary[0] + "条，耗时：" + ary[1] + "秒.");
                }
            }
        });
    }

    // 订单转单
    function openOrderFollow() {
        var orderId = $("#checkedOrderId").val();
        if (orderId == null || orderId == "") {
            $.alert({text: "请选择需要转单的订单！"});
            return;
        }
        openModule('/order/jsp/orderFollow.jsp', {'id': orderId}, {}, {}, 'orderFollow');
    }

    //查看服务人员详细信息
    function checkPersons(id) {
        openModule("${ctx}/jsp/server/orderNeedsPerson.jsp", {"id": id}, '', '', 'checkPersonsModle');
    }
    //较验当前是否可以匹配人员和推送需求
    function queryNeedPersons(type) {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var n = 0;
        $.ajax({
            url: ctx + "/itemInterview/queryNeedPersons",
            data: {
                orderId: orderId,
                type: type
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    n = data.list.length;
                }
            }
        })
        return n;
    }

    //重置查询订单条件
    function resetOrders() {
        $("#orderCode").val('');
        $("#userNameOrder").val('');
        $("#userMobileOrder").val('');
        $("#orderSourceId").val('');
        $("#serverType").val('');
        $("#orderStatus").val("");
        $("#orderCheckedProvinces").val('');
        $("#orderCheckedCitys").val("");
        $("#createTimeOrder").val('');
        $("#createTimeEnd").val('');
        $("#orderChannel").val('');
        $("#mendianSelect").val('');
        $("#guanjiaSelect").val('');
        $("#birthTimeOrder").val('');
        $("#birthTimeOrderEnd").val('');
        $("#threeOrderCode").val('');
        $("#orderPay").val('');
        $("#serviceObjectQuery").val('');
        $("#startLinedTime").val('');
        $("#endLinedTime").val('');
        $("#onceAgreementCheckStatus").val('');
    }


    function editUserHref() {
        var cust_phone = $("#checkedUserMobile").val();
        var cust_id = $("#checkedUserId").val();
        var ctx = $("#ctx").val();
        var url = "http://" + window.location.host + "/catchUsersData?cust_id=" + cust_id;
        window.open(url);

    }

    /*表格点击行高亮*/
    function trColorOrder(trli) {
        $(trli).on("click", function () {
            $(this).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
        })

    }
    //加载管家
    function selguanjia() {
        var ctx = $("#ctx").val();
        var deptId = $("#mendianSelect option:selected").val();
        var guanjiaSelectval = $("#guanjiaSelect").find("option:selected").val();
        if (deptId == "" || deptId == null) {
            $("#guanjiaSelect").empty();
            $("#guanjiaSelect").html("<option>...请选择...</option>");
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
                            html += "<option   value='" + v.id + "'>" + v.realName + "</option>";
                        });

                    } else if (data.msg == "02") {
                        $.alert({millis: 2000, text: "门店无管家!"});
                    } else {
                        $.alert({millis: 2000, text: "查询出错，请稍后再试!"});
                    }
                    $("#guanjiaSelect").html(html);
                }
            });
        }

    }
    /**
     * 查询转单日志列表
     */
    function queryOrderTurnLog(orderId, tagId) {
        if (!tagId || !orderId) {
            return;
        }
        var $tid = $("#" + tagId);
        $tid.empty();
        $.ajax({
            url: ctx + "/orderTurnLog/queryOrderTurnLog",
            data: {"orderId": orderId},
            type: "post",
            async: false,
            dataType: "json",
            success: function (data) {
                if (data.msg == "00") {
                    var html = "";
                    $.each(data.list, function (i, v) {
                        html += "<tr>";
                        html += "<td>" + (i + 1) + "</td>";
                        html += "<td>" + v.createTime + "</td>";
                        html += "<td>" + v.createByText + "</td>";
                        html += "<td>" + v.createDeptText + "</td>";
                        html += "<td>" + v.turnByText + "</td>";
                        html += "<td>" + v.turnDeptText + "</td>";
                        html += "<td>" + v.rechargeByText + "</td>";
                        html += "<td>" + v.rechargeDeptText + "</td>";
                        html += "<td>" + v.remark + "</td>";
                        html += "</tr>";
                    })
                    $tid.append(html);
                } else if (data.msg == "02") {
                    $tid.append("<tr><td colspan='9'>暂无数据</td></tr>")
                } else {
                    $tid.append("<tr><td colspan='9'>查询异常</td></tr>")
                }
                trColorOrder("#" + tagId + " tr");
            }
        })
    }
    function exportOrders() {
        var ctx = $("#ctx").val();
        var orderCode = $("#orderCode").val();
        var userName = $("#userNameOrder").val();
        var createTime = $("#createTimeOrder").val();
        var createTimeEnd = $("#createTimeEnd").val();
        var userMobile = $("#userMobileOrder").val();
        var serverType = $("#serverType").val();
        var orderType = serverType.split("-")[0];
        var cateType = serverType.split("-")[1];
        var orderStatus = $("#orderStatus").val();
        var orderSourceId = $("#orderSourceId").val();
        var orderChannel = $("#orderChannel").val();
        var critical = $("#critical").val();
        var orderGroupid = $("#orderGroupid").val();
        var managerId = $("#managerId").val();
        var city = $("#orderCheckedCitys").val();
        var mendian = $("#mendianSelect").find("option:selected").val();
        var rechargeByGuan = $("#guanjiaSelect").find("option:selected").val();
        var orderPay = $("#orderPay").val();
        var birthTimeOrder = $("#birthTimeOrder").val();
        var birthTimeOrderEnd = $("#birthTimeOrderEnd").val();
        if (orderType == 100200010001 || orderType == 100200010002) {
            $.ajax({
                type: "post",
                url: ctx + "/order/exportExcel",
                data: {
                    orderCode: orderCode,
                    userName: userName,
                    createTime: createTime,
                    createTimeEnd: createTimeEnd,
                    userMobile: userMobile,
                    cateType: cateType,
                    orderStatus: orderStatus,
                    orderSourceId: orderSourceId,
                    orderChannel: orderChannel,
                    critical: critical,
                    orderGroupid: orderGroupid,
                    managerId: managerId,
                    city: city,
                    cateTypeNot: "5,6,7,8",
                    threeOrderItemId: rechargeByGuan,
                    payText: orderPay,
                    orderType: orderType,
                    birthTimeOrder: birthTimeOrder,
                    birthTimeOrderEnd: birthTimeOrderEnd
                },
                dataType: "json",
                type: "POST",
                success: function (data) {
                    window.open(ctx + "/order/downExcel?filename=" + data.a, "_self");
                },
                error: function () {
                    $.alert({text: "失败！"});
                }
            });

        } else {
            $.alert({text: "请选择订单类型为月嫂/育婴师的订单！！！！"});
            return;
        }

    }
    /*查询回访记录  */
    function queryReturn(orderId, returnInfoOne) {
        var orderProgressStr = {
            1: '正准备签合同',
            2: '已面试考虑中',
            3: '已面试需重新匹配',
            4: '已约好面试',
            5: '正匹配人员',
            6: '已联系有意向',
            7: '已联系意向不强',
            8: '最近没时间考虑',
            9: '只是咨询',
            10: '不需要服务',
            11: '一直联系不上',
            12: '已经找到服务',
            13: '距离远服务不到',
            14: '提供不了合适人员',
            15: '觉得信息费太贵',
            16: '觉得人员服务费太高',
            17: '对管家不认同',
            18: '对管家帮不认同',
            19: '其他情况',
            20: '不想预存',
            21: '不接受预存及白条',
            22: '不接受收费模式',
            23: '提供不了初级服务人员',
            24: '提供不了中级服务人员',
            25: '提供不了高级及以上服务人员',
            26: '正在匹配初级服务人员',
            27: '正在匹配中级服务人员',
            28: '正在匹配高级及以上服务人员',
            29: '咨询价格及政策',
            30: '有意向跟进中',
            31: '远期意向',
            32: '正准备签合同',
            33: '已签约'
        };
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/recorder/queryRecorder",
            data: {
                orderId: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var html = "";
                    var num = $.each(data.list, function (i, v) {
                        var checked = "";
                        html += "<div class='row'>";
                        html += "<div class='col-xs-6'>";
                        html += "<input type='hidden' value='" + v.recorder + "' id='recorder'>";
                        html += "<input data-recorder='" + v.recorder + "' type='radio' " + checked + " name='returnrecord_id' value='" + v.id + "' ${(state=='" +v.id +"')?'checked' : ''}>";
                        html += "回访记录人：<span id='accountId" + v.id + "'>" + v.realname + "</span>";
                        html += "</div><div class='col-xs-6'>";
                        html += "<span>记录时间：</span><span>" + numberToDatestr(v.createTime, 14) + "</span>";
                        html += "</div></div>";
                        html += "<div class='row'>";
                        html += "<div class='col-xs-6'>";
                        html += "<span>服务满意度：</span><span>" + v.satis + "</span>";
                        html += "</div>";
                        html += "<div class='col-xs-6'>";
                        html += "<span>销售意向：</span><span>" + v.threeclass + " " + v.mean + " " + v.unintentionalReasonStr + "</span>";
                        html += "</div></div>";
                        html += "<div class='row'>";
                        html += "<div class='col-xs-12'>";
                        html += "<span>下次回访时间：</span><span>" + v.nextTime + "</span>";
                        html += "</div></div>";
                        html += "<div class='row'>";
                        html += "<div class='col-xs-12'>";
                        if (v.orderProgress == 19) {
                            html += "<span>订单进度：</span><span>" + orderProgressStr[v.orderProgress] + "(" + v.orderProgressInfo + ")</span>";
                        } else {
                            html += "<span>订单进度：</span><span>" + orderProgressStr[v.orderProgress] + "</span>";
                        }
                        html += "</div></div>";
                        html += "<div class='row'>";
                        html += "<div class='col-xs-12'>";
                        html += "<span>备注：</span><span>" + v.remark + "</span>";
                        html += "</div></div><hr>";
                        $("#" + returnInfoOne).html(html);
                    })
                    $("#" + returnInfoOne).find("input[type=radio]").eq(0).prop("checked", true);
                } else {
                    $("#" + returnInfoOne).html("");
                }
            }
        });

    }
    //修改回访记录
    function updatereturnrecord() {
        var ctx = $("#ctx").val();
        orderId = parent.$("#checkedOrderId").val()
        var record = $("input[name='returnrecord_id']:checked").attr("data-recorder");
        RechargeBy = parent.$("#checkedRechargeBy").val()
        var recorder = $("input[name='returnrecord_id']:checked").val();
        var loginId =
        <%=CookieUtils.getJSONKey(request, "id") %>
        if (!recorder) {
            $.alert({millis: 2000, text: "请选择回访信息"});
            return;
        }
        if (loginId == RechargeBy) {
            openModule(ctx + "/jsp/returnrecord/editReturn.jsp", {
                orderId: orderId,
                record: recorder
            }, {}, {}, "orderReturn");
        } else {
            $.alert({millis: 5000, text: "回访信息须由订单责任人修改"});
            return;
        }
    }

    //查看负责人
    function selectPrincipal() {
        var orderId = $("#checkedOrderId").val();
        //var rechargeBy = $("#checkedRechargeBy").val();
        if (orderId == null || orderId == "") {
            $.alert({text: "请选择需要转单的订单！"});
            return;
        }
        $.ajax({
            url: ctx + "/orderTurnLog/selectPrincipal",
            type: "post",
            data: {
                orderId: orderId
            },
            orderId: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    openOrderFollow();
                } else if (data.msg == "02") {
                    $.alert({text: "订单创建时间超过1小时方可转单!"});
                }
            }
        })
    }

    /**
     *导入订单
     */
    function uploadFile() {
        var ctx = $("#ctx").val();
        var fileName = $('#excelFile').val();
        var ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        if (ext != 'xlsx' && ext != 'xls') {
            $.alert({text: "请选择正确的excel文件"});
            return;
        } else {
            $.ajaxFileUpload
            (
                    {
                        url: ctx + '/order/excelToList', //用于文件上传的服务器端请求地址
                        secureuri: false, //是否需要安全协议，一般设置为false
                        fileElementId: 'excelFile', //文件上传域的ID
                        dataType: 'json', //返回值类型 一般设置为json
                        success: function (data, status)  //服务器成功响应处理函数
                        {
                            if (data.success) {
                                $.alert({text: "导入完成,请点击导入记录查看结果!"});
                                queryOrders(0, 10);
                            } else {
                                $.alert({text: "导入失败，请检查excel表格式或表中有无数据！"});
                            }
                        },
                    }
            )
            return false;
        }
    }

    /**
     *导入订单
     */
    function uploadFileNew() {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/order/queryDeptName",
            type: "post",
            data: "",
            orderId: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var temp = data.data.DEPTNAME;
                    openModule(ctx + "/jsp/order/batchCreateSummarySheet.jsp?data=" + temp + "", '', '', {width: '50%'}, "");
                } else {
                    $.alert({millis: 5000, text: "下载失败"});
                }
            }
        })
    }

    /*
     *导入记录信息
     */
    function downloadOrderImportRecord() {
        var ctx = $("#ctx").val();
        openModule(ctx + '/jsp/orderImportRecord.jsp', '', '', {width: '50%'}, 'orderImportRecord');
    }


    /**下载档案卡-列表页*/
    function downloadEmpFileCard(empId) {
        if (empId) {
            $.ajax({
                url: ctx + "/order/downloadEmpFileCard",
                type: "post",
                data: {empId: empId},
                orderId: "json",
                async: false,
                success: function (data) {
                    if (data.msg == "00") {
                        window.location.href = ctx + "/order/downloadFile?pathname=" + data.returnUrl
                    } else {
                        $.alert({millis: 5000, text: "下载失败"});
                    }
                }
            })
        }
    }
    /**下载档案卡-推送页 */
    function downloadEmpFileCardById() {
        var empId = $("#downloadEmpFileCardById").val();
        if (empId) {
            downloadEmpFileCard(empId);
        } else {
            $.alert({millis: 5000, text: "请选择服务人员"});
        }
    }

    /*2018-05-07完善客户账户信息*/
    function toCustomerBankCard() {
        var ctx = $("#ctx").val();
        var cusId = $("#checkedUserId").val();
        var cusName = $.trim($("#nameBasics").text());
        if (cusId) {
            openModule(ctx + "/jsp/server/customerBankCard.jsp", {
                'cusId': cusId,
                "cusName": cusName
            }, null, {"width": "45%"});
        } else {
            $.alert({millis: 5000, text: "未获取到客户信息"});
        }
    }

    /**根据特殊登录人,设置某一区域内功能按钮是否隐藏*/
    function setBtnHideByLoginBy() {
        var loginBy = $("#checkedLoginBy").val();
        if (loginBy == '10944') {
            $("#orderRightTabs").find(":button").hide();//隐藏右边栏按钮
            $("#orderLeftPanels").find(":button").hide();//隐藏左边栏按钮
        }
    }


    /**自动填报服务费*/
    function itemInterviewInsertSalary(orderId) {
        if (orderId) {
            $.ajax({
                url: ctx + "/itemInterview/insertSalary",
                type: "post",
                data: {"orderId": orderId},
                orderId: "json",
                async: false,
                success: function (data) {
                    if (data.msg == "00") {

                    } else {

                    }
                }
            })
        }
    }
</script>
</html>

