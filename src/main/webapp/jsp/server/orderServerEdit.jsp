<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageEditcext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <style>
        .modal-order-edit {
            height: 500px;
            width: 850px;
            margin: 30px auto;
        }

        .order-body-edit {
            height: 380px;
            width: 780px;
            margin: 10px auto;
        }

        .table-condensed tr td {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false"
     id="modelFrameOrderServerEdit">
    <div class="modal-dialog modal-order-edit">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        onclick="setOrderCloseModule('orderServerEdit',1);">×
                </button>
                <h4 class="modal-title">订单修改</h4>
            </div>
            <div class="modal-body">
                <div class="modal-content-basic">
                    <div class="info-select clearfix">
                        <input type="hidden" name="id" id="orderIdEditc">
                        <input type="hidden" name="code" id="codeEditc">
                        <input type="hidden" name="version" id="versionEditc">
                        <input type="hidden" id="orderEditcReceiverId">
                        <input type="hidden" id="oldTime">
                        <input type="hidden" id="oldTimeSolt">
                        <input type="hidden" id="orderEditcReceiverId">
                        <input type="hidden" id="cityCode">
                        <input type="hidden" id="onesKu">
                        <input type="hidden" id="personNumber">
                        <input type="hidden" id="numStock">
                        <form id="serverEditCont" class="form-inline" method="post" style="display:none;">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>客户姓名：</p>
                                        <input id="orderUserAddServer_edit_cont" type="text" class="form-control"
                                               name="totalPayAddServer">
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p style="width: 150px">是否享受解决方案价格：</p>
                                        <select id="orderAddSolutionOrNot_edit_cont" class="form-control"
                                                style="width:112px;"
                                                onchange="selectOrderAddSolutionOrNot(this,'_edit_cont')">
                                            <option value="20000005">是</option>
                                            <option value="20000002">否</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>三级分类：</p>
                                        <input id="threeCategory_edit_cont" class="form-control" readOnly>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>商品：</p>
                                        <select id="serverProduct_edit_cont" class="form-control"
                                                onchange="javascript:setCityServerProduct(this,'_edit_cont')"></select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <input type="hidden" id="productUnitAddServer_edit_cont">
                                    <input type="hidden" id="productPriceTypeAddServer_edit_cont">
                                    <label><p>总数量(<span id="productUnitTextAddServer_edit_cont"></span>):</p>
                                        <input id="pickQuantityServer_edit_cont" type="text" class="form-control"
                                               style="ime-mode:Disabled;"
                                               name="pickQuantityServer"
                                               onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);"
                                        >
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>价格(元)：</p>
                                        <input id="totalPayAddServer_edit_cont" type="text" class="form-control"
                                               name="totalPayAddServer" readOnly>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label><p>价格体系：</p> <input id="priceTextAddServer_edit_cont"
                                                               type="text" class="form-control" name="totalPayAddServer"
                                                               readonly="true">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>规格：</p> <input id="productSpecAddServer_edit_cont"
                                                             type="text" class="form-control" readonlrey="true"
                                    >
                                    </label>
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label><span style="color:red">*</span>开始时间：
                                        <input id="startTimeEditc" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" disabled="true"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label><span style="color:red">*</span>结束时间：
                                        <input id="endTimeEditc" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" disabled="true"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>面试时间：
                                        <input id="interviewTimeEditc" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>面试地址：
                                        <input id="interviewAddressEditc" type="text" class="form-control"
                                               style="width:430px;" name="interviewAddress">
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>学历要求：
                                        <select id="educationEditc" class="form-control">
                                            <option style='color:blue;' value=''>...请选择...</option>
                                            <option value='1'>无学历</option>
                                            <option value='2'>小学</option>
                                            <option value='3'>初中</option>
                                            <option value='4'>高中/中专</option>
                                            <option value='5'>本科/大专</option>
                                            <option value='6'>研究生</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4">
                                    <label> 等级要求：
                                        <select name="levelEditc" id="levelEditc" class="form-control">
                                        </select>
                                    </label>
                                </div>
                                <div class="col-xs-4">
                                    <label>籍贯要求：
                                        <select id="originEditc" class="form-control"></select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>年龄要求： <input id="minAgeEditc" maxlength="2"
                                                        class="form-control" style="width: 60px;"
                                                        onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
                                                        onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                        至<input
                                                id="maxAgeEditc" maxlength="2" class="form-control" style="width: 60px;"
                                                onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
                                                onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
                                    </label>
                                </div>
                                <div class="col-xs-4">
                                    <label>居家面积：
                                        <input id="homeForestsEditc" class="form-control"
                                               onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);"
                                               style="ime-mode:Disabled">
                                    </label>
                                </div>
                                <div class="col-xs-4">
                                    <label>家里人口：
                                        <input id="familysEditc" class="form-control"
                                               onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);"
                                               style="ime-mode:Disabled">
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-4">
                                    <label>性别要求：
                                        <select id="updateSex" class="form-control">
                                            <option style='color:blue;' value=''>...请选择...</option>
                                            <option value='-1'>不限</option>
                                            <option value='1'>男</option>
                                            <option value='2'>女</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label id="toubaoCont">
                                        是否投保:
                                        <select id="insureCont" class="form-control" style="width:120px;">
                                            <option value="2">否</option>
                                            <option value="1">是</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label id="toubaoAmountCont" style="display: none;">
                                        <span style="color:red">*</span>投保金额(元):
                                        <input type="text" id="insureAmountCont" name="insureAmountCont" class="form-control" style="width: 120px;" value="" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);">
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label><p>备注：</p></label>
                                    <textarea rows="2" id="remarkEditc" name="remark" style="height:50px;"
                                              class="form-control form-textarea" maxLength="500"
                                              style="behavior:url(maxLength.htc)"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-12">
                                    <label><p>服务地址：</p>
                                        <input id="serverAddressEditCont" name="serverAddressEditCont"
                                               type="text" class="form-control" style="width:430px;"
                                               readonly="readonly">
                                    </label>
                                </div>
                            </div>
                            <div style="height:10px;"></div>
                            <div class="row">
                                <div class="form-group col-xs-2">
                                    <label>
                                        <p>服务选择地址：</p>
                                    </label>
                                </div>
                                <div class="form-group col-xs-8">
                                    <div class="panel-content" style="height:200px; overflow:auto;">
                                        <table id="listBodyAddressEditcCont" class="table order-table"
                                               style="margin-bottom:0">
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group col-xs-2">
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:7,userId:parent.$('#checkedUserId').val(),userAddressId:0},'','','orderItemaddressAdd')">
                                        新增地址
                                    </button>
                                </div>
                            </div>
                        </form>

                        <form id="serverEditOne" class="form-inline" method="post" style="display:none;">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>客户姓名：</p>
                                        <input id="orderUserAddServer_edit_one" type="text" class="form-control"
                                               name="totalPayAddServer">
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p style="width: 150px">是否享受解决方案价格：</p>
                                        <select id="orderAddSolutionOrNot_edit_one" class="form-control"
                                                style="width:112px;"
                                                onchange="selectOrderAddSolutionOrNot(this,'_edit_one')">
                                            <option value="20000005">是</option>
                                            <option value="20000002">否</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>三级分类：</p>
                                        <input id="threeCategory_edit_one" class="form-control" readOnly>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>商品：</p>
                                        <select id="serverProduct_edit_one" class="form-control"
                                                onchange="javascript:setCityServerProduct(this,'_edit_one')"
                                                disabled></select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <input type="hidden" id="productUnitAddServer_edit_one">
                                    <input type="hidden" id="productPriceTypeAddServer_edit_one">
                                    <label><p>数量(<span id="productUnitTextAddServer_edit_one"></span>):</p>
                                        <input id="pickQuantityServer_edit_one" type="text" class="form-control"
                                               name="pickQuantityServer"
                                               onchange="setTotalPayAddServer(this,'_edit_one')"
                                               style="ime-mode:Disabled;"
                                               onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>价格(元)：</p>
                                        <input id="totalPayAddServer_edit_one" type="text" class="form-control"
                                               name="totalPayAddServer" readonly>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label><p>价格体系：</p> <input id="priceTextAddServer_edit_one"
                                                               type="text" class="form-control" name="totalPayAddServer"
                                                               readonly>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label><p>规格：</p> <input id="productSpecAddServer_edit_one"
                                                             type="text" class="form-control" readonly
                                    >
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="notStock" style="display:none">
                                <div class="form-group col-xs-6">
                                    <label><p><span style="color:red">*</span>开始时间：</p>
                                        <input id="startTimeEditcOne" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})"
                                               disabled="true"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label><p><span style="color:red">*</span>结束时间：</p>
                                        <input id="endTimeEditcOne" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})"
                                               disabled="true"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="isStockDate" style="display:none">
                                <div class="col-xs-6">
                                    <label>
                                        <p>服务日期：</p>
                                        <input id="timeServer" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({onpicked:selUpTimes,Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})"
                                               disabled="true"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="isStockTime" style="display:none">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <span style="font-weight:bold;">单人服务时长 ：</span>
                                        <select name="servicehours" id="servicehours" class="form-control"
                                                onchange="changeTimes()" disabled="true">
                                            <option style="color:blue;" value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <span style="font-weight:bold;">服务时间段 ：</span>
                                        <select name="timesoltKun" id="timesoltKun" class="form-control"
                                                onchange="checkNumStock()" disabled="true">
                                            <option style="color:blue;" value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-4">
                                    <label>性别要求：
                                        <select id="danUpdateSex" class="form-control">
                                            <option style='color:blue;' value=''>...请选择...</option>
                                            <option value='-1'>不限</option>
                                            <option value='1'>男</option>
                                            <option value='2'>女</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label id="toubaoOne">
                                        是否投保:
                                        <select id="insureOneUp" class="form-control" style="width:120px;">
                                            <option value="2">否</option>
                                            <option value="1">是</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-4">
                                    <label id="toubaoAmountOne" style="display: none;">
                                        <span style="color:red">*</span>投保金额(元):
                                        <input type="number" id="insureAmountOne" name="insureAmountOne" style="width: 120px;" class="form-control" value="" onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);">
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label><p>备注：</p></label>
                                    <textarea rows="2" id="remarkEditcOne" name="remark"
                                              class="form-control form-textarea"
                                              style="behavior:url(maxLength.htc)"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label><p>服务地址：</p>
                                        <input id="serverAddressEditOne" name="serverAddressEditOne"
                                               type="text" class="form-control" style="width:280px;"
                                               readonly="readonly">
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6" id="ForestsServerEditDiv" style="display:none">
                                    <label><p>服务面积：</p>
                                        <input id="ForestsServerEdit" name="ForestsServer"
                                               type="text" class="form-control">
                                    </label>
                                </div>
                            </div>
                            <div style="height:10px;"></div>
                            <div class="row">
                                <div class="form-group col-xs-2">
                                    <label>
                                        <p>服务选择地址：</p>
                                    </label>
                                </div>
                                <div class="form-group col-xs-8">
                                    <div class="panel-content" style="height:200px; overflow:auto;">
                                        <table id="listBodyAddressEditcOne" class="table order-table"
                                               style="margin-bottom:0">
                                        </table>
                                    </div>
                                </div>
                                <div class="form-group col-xs-2">
                                    <button type="button" class="btn btn-primary btn-sm"
                                            onclick="openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:7,userId:parent.$('#checkedUserId').val(),userAddressId:0},'','','orderItemaddressAdd')">
                                        新增地址
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-primary"
                        onclick="setOrderCloseModule('orderServerEdit',2);">取消
                </button>
                <button type="button" class="btn btn-sm btn-primary" onclick="saveServerEditc()">提交</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var keyIsNum = true;
    function inputOnlyNum(myEvent) {
        var keys = myEvent.keyCode;
        if (!((keys >= 48 && keys <= 57) || (keys >= 96 && keys <= 105)
                || (keys == 8) || (keys == 46) || (keys == 37) || (keys == 39)
                || (keys == 13) || (keys == 229) || (keys == 190) || (keys == 110))) {
            keyIsNum = false;
        } else {
            keyIsNum = true;
        }

    }
    function checkIfNum(myEvent) {
        if (!keyIsNum) {
            $.alert({
                text: "请输入数字！"
            });
            if (document.all)
                myEvent.returnValue = false;//ie
            else
                myEvent.preventDefault();//ff
        }

    }
    $(function () {
        var cateType = parent.$("#checkedCateType").val();
        if (cateType == 1 || cateType == 4) {
            $("#serverEditOne").css("display", "block");
            orderServerEdit(cateType);
        } else if (cateType == 2) {
            $("#serverEditCont").css("display", "block");
            orderServerEdit(cateType);
        }
        isStock($("#cityCode").val(), parent.$("#productCode").val());
        checkOrderAddUserAddressAddressEditcOne();
    })
    function selUpTimes() {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderKucun",
            data: {
                cityCode: $("#cityCode").val(),
                productCode: parent.$("#productCode").val(),
                startTimeLinedOne: $("#timeServer").val()
            },
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                var option = "<option style='color:blue;' value='0'>...请选择...</option>";
                for (i = 0; i < data.hours.length; i++) {
                    option += "<option value=" + data.hours[i].name + " >" + data.hours[i].name + "</option>";
                    $("#servicehours").html(option);
                }
                $("#servicehours option[value=" + data.hours[0].name + "]").prop("selected", true);

                var options = "<option style='color:blue;' value='0'>...请选择...</option>";
                for (i = 0; i < data.quantums.length; i++) {
                    options += "<option value=" + data.quantums[i] + ">" + data.quantums[i] + "</option>";
                    $("#timesoltKun").html(options);
                }
                $("#timesoltKun option[value=" + data.quantums[0] + "]").prop("selected", true);

            }
        });
    }
    function changeTimes() {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderKucun1",
            data: {
                cityCode: $("#cityCode").val(),
                productCode: parent.$("#productCode").val(),
                startTimeLinedOne: $("#timeServer").val(),
                serviceHours: $("#servicehours").val()
            },
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                var option = "<option style='color:blue;' value='0'>...请选择...</option>";
                for (i = 0; i < data.quantums.length; i++) {
                    option += "<option value=" + data.quantums[i] + ">" + data.quantums[i] + "</option>";
                    $("#timesoltKun").html(option);
                }
            }
        });
    }
    //验证当前是否有缴费
    function validPay(orderId) {
        var flag = false;
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/payfee/queryAccount",
            data: {
                orderId: orderId,
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00') {
                    if (data.list.length > 0) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            }
        })
        return flag;
    }
    //取到订单详细信息
    function orderServerEdit(cateType) {
        var orderId = parent.document.getElementById("checkedOrderId").value;
        var orderType = parent.document.getElementById("checkedOrderType").value;
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/orderbase/queryOrderBasicServer",
            data: {
                id: orderId,
                orderType: orderType
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        $("#orderIdEditc").val(v.id); // 订单表id
                        $("#codeEditc").val(v.code); // server表id
                        $("#personNumber").val(v.personNumber);//服务人员数量
                        $("#versionEditc").val(v.version);
                        if (cateType == 1 || cateType == 4) {
                            $("#danUpdateSex").val(v.xqSex);//男女
                            $("#serverAddressEditOne").val(v.address);
                            $("#remarkEditcOne").val(v.remark);//
                            $("#orderUserAddServer_edit_one").val(v.order.userName);//f
                            $("#ForestsServerEdit").val(v.forestsserver);//服务面积 （擦玻璃）
                            if (v.productCategoryName == "擦玻璃") {
                                $("#ForestsServerEditDiv").css("display", "block");
                            } else {
                                $("#ForestsServerEditDiv").css("display", "none");
                            }
                            // 是否享受解决方案价
                            var priceType = v.order ? v.order.priceType : "";
                            if (priceType != "" && priceType != null) {
                                if (priceType == 20000005) {
                                    $("#orderAddSolutionOrNot_edit_one option[value='20000005']").prop("selected", true);
                                } else {
                                    $("#orderAddSolutionOrNot_edit_one option[value='20000002']").prop("selected", true);
                                }
                            }
                            //基础商品三级分类
                            $("#threeCategory_edit_one").val(v.productCategoryName).attr("data-productCategoryCode", v.productCategoryCode);
                            //查询三级分类下的商品
                            queryCityServerProduct(v.productCategoryCode, v.city, "_edit_one");
                            //选中商品
                            $("#serverProduct_edit_one option[value^='" + v.productCode + "']").prop("selected", true).trigger("change");
                            //数量
                            $("#pickQuantityServer_edit_one").val(v.quantity).trigger("change");
                            //验证当前是否有缴费
                            if (validPay(orderId) == true) {
                                $("#orderAddSolutionOrNot_edit_one").attr("disabled", "disabled");
                                $("#pickQuantityServer_edit_one").attr("disabled", "disabled");
                            }
                            //所属城市
                            $("#cityCode").val(v.city);
                            var result = isStock($("#cityCode").val(), parent.$("#productCode").val());
                            if (result == 1 || result == "1") {
                                if (v.servicehours != "" && v.servicehours != null) {
                                    $("#timeServer").val(numberToDatestr(v.startTime, 12));
                                    selUpTimes()
                                    $("#servicehours option[value='" + v.servicehours + "']").prop("selected", true);
                                    changeTimes();
                                    $("#timesoltKun option[value='" + v.timeslot + "']").prop("selected", true);
                                }
                                //库存处理
                                $("#oldTime").val(numberToDatestr(v.startTime, 12));
                                $("#oldTimeSolt").val(v.timeslot);
                                $("#onesKu").val("1");
                            } else if (result == 2 || result == "2") {
                                $("#startTimeEditcOne").val(numberToDatestr(v.startTime, 12));
                                $("#endTimeEditcOne").val(numberToDatestr(v.endTime, 12));
                            }
                            debugger;
                            //是否投保选项
                            var itemProduct = v.productCategoryCode.substring(0,4);
                            var cateTypeOne = v.cateType;
                            var insure = v.insure;
                            if(itemProduct == "1002" && (cateType == "1" || cateType == "2" || cateType == "4")){
                                if (insure != "") {
                                    $("#insureOneUp option").each(function (i, n) {
                                        if (n.value == insure) {
                                            n.selected = true;
                                        }
                                    });
                                    $("#insureAmountOne").val(v.insureAmount);//投保金额回显
                                    $("#toubaoAmountOne").css("display", "block")
                                }
                            }else {
                                $("#toubaoOne").css("display", "none");
                            }
                        } else {
                            $("#serverAddressEditCont").val(v.address);
                            $("#startTimeEditc").val(numberToDatestr(v.startTime, 12));
                            $("#endTimeEditc").val(numberToDatestr(v.endTime, 12));
                            $("#interviewTimeEditc").val(numberToDatestr(v.interviewTime, 12));
                            $("#interviewAddressEditc").val(v.interviewAddress);
                            //$("#educationEditc option[value='" +v.education +"']").attr("selected","selected");
                            $("#educationEditc").val(v.education);//
                            //$("#levelEditc").val(v.personLevel);//
                            queryWorkTypeLevel("levelEditc", v.personLevel);
                            $("#minAgeEditc").val(v.minAge);
                            $("#maxAgeEditc").val(v.maxAge);
                            $("#homeForestsEditc").val(v.homeForests);//
                            $("#familysEditc").val(v.familys);//
                            $("#remarkEditc").val(v.remark);//
                            $("#orderUserAddServer_edit_cont").val(v.order.userName);//
                            queryOriginDictionary("", "originEditc", v.origin);
                            $("#updateSex").val(v.xqSex);//男女
                            // 是否享受解决方案价
                            var priceType = v.order ? v.order.priceType : "";
                            if (priceType != "" && priceType != null) {
                                if (priceType == 20000005) {
                                    $("#orderAddSolutionOrNot_edit_cont option[value='20000005']").prop("selected", true);
                                } else {
                                    $("#orderAddSolutionOrNot_edit_cont option[value='20000002']").prop("selected", true);
                                }
                            }
                            //基础商品三级分类
                            $("#threeCategory_edit_cont").val(v.productCategoryName).attr("data-productCategoryCode", v.productCategoryCode);
                            //查询三级分类下的商品
                            queryCityServerProduct(v.productCategoryCode, v.city, "_edit_cont");
                            //选中商品
                            $("#serverProduct_edit_cont option[value^='" + v.productCode + "']").prop("selected", true).trigger("change");
                            //数量
                            $("#pickQuantityServer_edit_cont").val(v.quantity).trigger("change");
                            //验证当前是否有缴费
                            if (validPay(orderId) == true) {
                                $("#orderAddSolutionOrNot_edit_cont").attr("disabled", "disabled");
                                $("#serverProduct_edit_cont").attr("disabled", "disabled");
                                $("#pickQuantityServer_edit_cont").attr("disabled", "disabled");
                            }
                            //所属城市
                            $("#cityCode").val(v.city);
                            debugger;
                            //是否投保选项
                            var itemProduct = v.productCategoryCode.substring(0,4);
                            var cateTypeCont = v.cateType;
                            var insure = v.insure;
                            if(itemProduct == "1002" && (cateType == "1" || cateType == "2" || cateType == "4")){
                                if (insure != "") {
                                    $("#insureCont option").each(function (i, n) {
                                        if (n.value == insure) {
                                            n.selected = true;
                                        }
                                    });
                                    $("#insureAmountCont").val(v.insureAmount);//投保金额回显
                                    $("#toubaoAmountCont").css("display", "block")
                                }
                            }else {
                                $("#toubaoCont").css("display", "none");
                            }
                        }
                    })
                }
            }
        });
    }

    //查询服务商品(tagIdSuffix:标签id后缀)
    function queryCityServerProduct(categoryCode, cityCode, tagIdSuffix) {
        var ctx = $("#ctx").val();
        var priceType = $("#orderAddSolutionOrNot" + tagIdSuffix).prop("value");
        // 查询是否享受解决方案价格
        $.ajax({
            url: ctx + "/item/queryCityServerProduct",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                categoryCode: categoryCode,
                cityCode: cityCode,
                status: 1,
                dictCode: priceType,
                typeSpecId: 242340276249476
            },
            success: function (data) {
                if (data.msg == "00") {
                    var result = data.list;
                    var html = "<option style='color:blue;' value='0'>...请选择...</option>";
                    $.each(result, function (i, p) {
                        html += "<option value='" + p.productCode + "," + p.marketPrice + "," + p.productUnit + "," + p.productUnitText;
                        html += "," + p.productPriceType + "," + p.priceText + "," + p.productSpec;
                        html += "'>" + p.productName + "</option>";
                    });
                    $("#serverProduct" + tagIdSuffix).html(html);
                } else {
                    $("#serverProduct" + tagIdSuffix).html("<option style='color:blue;' value='0' >...无可选项...</option>");
                }
            }
        });
    }

    //选择商品(tagIdSuffix:标签id后缀)
    function setCityServerProduct(item, tagIdSuffix) {
        var items = item.options[item.options.selectedIndex].value;
        $("#pickQuantityServer" + tagIdSuffix).val(items == 0 ? "" : 1);
        $("#totalPayAddServer" + tagIdSuffix).val(items.split(",")[1]);
        $("#productUnitAddServer" + tagIdSuffix).val(items.split(",")[2]);
        $("#productUnitTextAddServer" + tagIdSuffix).text(items == 0 ? "" : items.split(",")[3]);
        $("#productPriceTypeAddServer" + tagIdSuffix).val(items.split(",")[4]);
        $("#priceTextAddServer" + tagIdSuffix).val(items.split(",")[5]);
        $("#productSpecAddServer" + tagIdSuffix).val(items.split(",")[6]);
    }

    //选中【是否享受解决方案价格】
    function selectOrderAddSolutionOrNot(thiz, tagIdSuffix) {
        //获取城市商品code
        if (tagIdSuffix == "_edit_one") {
            var item = $("#serverProduct_edit_one")[0];
            var items = item.options[item.options.selectedIndex].value;
            var cityProductCode = items.split(",")[0]
        }
        var categoryCode = $("#threeCategory" + tagIdSuffix).attr("data-productCategoryCode");//三级分类code
        var cityCode = $("#cityCode").val();//城市code
        //查询商品(下拉框)
        queryCityServerProduct(categoryCode, cityCode, tagIdSuffix);
        $("#serverProduct" + tagIdSuffix).trigger("change");
        //如果为单次服务,商品不能修改
        if (tagIdSuffix == "_edit_one") {
            $("#serverProduct" + tagIdSuffix).find("option[value^='" + cityProductCode + "']")
                    .prop("selected", true).trigger("change");
        }
    }
    //提交修改
    function saveServerEditc() {
        var cateType = parent.$("#checkedCateType").val();
        var ctx = $("#ctx").val();
        var orderId = $("#orderIdEditc").val();
        var id = $("#codeEditc").val();
        var version = $("#versionEditc").val();
        var interviewTime = $("#interviewTimeEditc").val();
        var interviewAddress = $("#interviewAddressEditc").val();
        var origin = $("#originEditc").val();
        var education = $("#educationEditc option:selected").val();
        var minAge = $("#minAgeEditc").val();
        var maxAge = $("#maxAgeEditc").val();
        var homeForests = $("#homeForestsEditc").val();
        var familys = $("#familysEditc").val();
        var startTime = $("#startTimeEditc").val();
        var endTime = $("#endTimeEditc").val();
        var remark = $("#remarkEditc").val();
        var personLevel = $("#levelEditc").val();
        var priceType = $("#orderAddSolutionOrNot_edit_cont").val();//是否享受解决方案价
        var pickQuantity = $("#pickQuantityServer_edit_cont").val();//数量
        var productName = $("#serverProduct_edit_cont option:selected").text();//商品名称
        var itemC = $("#serverProduct_edit_cont")[0];
        var itemsC = itemC.options[itemC.options.selectedIndex] ? itemC.options[itemC.options.selectedIndex].value : "";
        var nowPrice = itemsC.split(",")[1];//价格
        var productCode = itemsC.split(",")[0];//城市商品code
        var userName = $("#orderUserAddServer_edit_cont").val();
        var cateType = parent.$("#checkedCateType").val();
        var ForestsServer = $("#ForestsServerEdit").val();
        if (cateType == 1 || cateType == 4) {
            var xqSex = $("#danUpdateSex").val();//男女
            var insure = $("#insureOneUp").val();//是否投保
            var insureAmount = $("#insureAmountOne").val();//投保金额
        } else {
            var xqSex = $("#updateSex").val();//男女
            var insure = $("#insureCont").val();//是否投保
            var insureAmount = $("#insureAmountCont").val();//投保金额
        }
        debugger;
        if(parseFloat(insureAmount) <= 0 && insure == "1"){//如投保，金额必填
            $.alert({text:"请填写投保金额!"});
            return;
        }else if(insure == "2"){//如不投保，金额置为0
            var insureAmount = "0";
        }
        //需要的服务人员数量
        var personNumber = $("#personNumber").val();
        //库存
        var numStock = $("#numStock").val();
        if (cateType == 1 || cateType == 4) {
            startTime = $("#startTimeEditcOne").val();
            endTime = $("#endTimeEditcOne").val();
            remark = $("#remarkEditcOne").val();
            priceType = $("#orderAddSolutionOrNot_edit_one").val();
            pickQuantity = $("#pickQuantityServer_edit_one").val();
            productName = $("#serverProduct_edit_one option:selected").text();
            var itemO = $("#serverProduct_edit_one")[0];
            var itemsO = itemO.options[itemO.options.selectedIndex].value;
            productCode = itemsO.split(",")[0];
            nowPrice = itemsO.split(",")[1];
            userName = $("#orderUserAddServer_edit_one").val();
        }
        if (priceType == "") {
            $.alert({millis: 2000, text: "【是否享受解决方案价格】不能为空！"});
            return;
        }
        if (productCode == 0) {
            $.alert({millis: 2000, text: "【商品】不能为空！"});
            return;
        }
        if (pickQuantity == "") {
            $.alert({millis: 2000, text: "【数量】不能为空！"});
            return;
        }
        var receiverId = $("#orderEditcReceiverId").val();
        if ($("#onesKu").val() == 1 || $("#onesKu").val() == "1") {
            //单次有库存
            startTime = $("#timeServer").val();
            var servicehours = $("#servicehours").val();
            var timesolt = $("#timesoltKun").val();
            //原来的时间，需要释放库存
            var oldTime = $("#oldTime").val();
            var oldTimeSolt = $("#oldTimeSolt").val();
            if (startTime == "" || startTime == null) {
                $.alert({text: "请输入服务日期!"});
                return;
            }
            if (servicehours == "" || servicehours == null) {
                $.alert({text: "请选择服务时长!"});
                return;
            }
            if (timesolt == "" || timesolt == null) {
                $.alert({text: "请选择时间段 !"});
                return;
            }
            if (parseInt(numStock) < parseInt(personNumber)) {
                $.alert({text: "库存不足,请重试!"});
                return;
            }
        } else {
            if (!startTime || $.trim(startTime) == ''
                    || !endTime || $.trim(endTime) == '') {
                $.alert({text: "请选择开始、结束时间。"});
                return;
            }
            try {
                var st = new Date(startTime.replace(/\-/g, "\/"));
                var et = new Date(endTime.replace(/\-/g, "\/"));
                if (et < st) {
                    $.alert({text: "开始时间大于结束时间。"});
                    return;
                }
            } catch (e) {
                $.alert({text: "时间格式不正确。"});
                return;
            }
        }
        $.ajax({
            url: ctx + "/itemDetailServer/updateItemDetailServer",
            data: {
                id: id,
                orderId: orderId,
                version: version,
                startTime: startTime,
                endTime: endTime,
                interviewTime: interviewTime,
                interviewAddress: interviewAddress,
                origin: origin,
                education: education,
                minAge: minAge,
                maxAge: maxAge,
                homeForests: homeForests,
                familys: familys,
                remark: remark,
                personNumber: $("#personNumber").val(),
                productName: productName,
                priceType: priceType,
                productCode: productCode,
                pickQuantity: pickQuantity,
                nowPrice: nowPrice,
                cateType: cateType,
                receiverId: receiverId,
                personLevel: personLevel,
                userName: userName,
                forestsServer: ForestsServer,
                serviceHours: servicehours,
                timeSlot: timesolt,
                oldTime: oldTime,
                oldTimeSolt: oldTimeSolt,
                cityCode: $("#cityCode").val(),
                cateCode: parent.$("#productCode").val(),
                onesKu: $("#onesKu").val(),
                sex: xqSex,
                insure: insure,
                insureAmount:insureAmount
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == '00') {
                    var userMobile = parent.$("#checkedUserMobile").val();
                    var userId = parent.$("#checkedUserId").val();
                    var city = parent.$("#checkedCity").val();
                    var cityName = parent.$("#checkedCityName").val();
                    var totalPay = parent.$("#checkedTotalPay").val();
                    var payStatus = parent.$("#checkedPayStatus").val();
                    var orderStatus = parent.$("#checkedOrderStatus").val();
                    parent.checkOrderBasic(orderId, cateType, userMobile, userId, city, cityName, totalPay, 0, payStatus, orderStatus);
                    setOrderCloseModule('orderServerEdit');
                    $.alert({text:"修改成功!"});
                }else{
                    $.alert({text:"修改失败!"});
                }
            }
        });
    }

    //通过电话号码取到送贷地址
    function checkOrderAddUserAddressAddressEditcOne() {
        var ctx = $("#ctx").val();
        var userId = parent.$("#checkedUserId").val();
        var adressId = "listBodyAddressEditcOne";
        var cateType = parent.$("#checkedCateType").val();
        if (cateType == 2) {
            adressId = "listBodyAddressEditcCont";
        }
        var city = parent.$("#checkedCity").val();
        if (userId == null || userId == "" || city == null || city == "") {
            $("#" + adressId).html("");
            return;
        }
        $.ajax({
            url: ctx + "/orderbase/queryUserAddressMapper",
            data: {
                userId: userId,
                /* cityCode:city, */
                valid: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        var address = v.province + v.city + v.country + v.addressChoose + v.addressDetail;
                        num = i + 1;
                        html += "<tbody><tr></tr>";
                        html += "<tr><td rowspan='2' class='bor-r'>";
                        html += "<input type='radio' data-adderss='" + address + "' name='orderEditcReceiverId' onclick='setOrderEditcReceiverId(this);' value='" + v.id + "'/></td>";
                        html += "<td>" + v.contactName + "</td>";
                        html += "<td>" + v.contactPhone + "</td>";
                        html += "<td rowspan='2' class='bor-l'>";
                        html += "<div><a href='#' onclick='orderEditcUpdateAddress(" + v.id + ")'>修改</a></div>";
                        html += "</td></tr><tr>";
                        html += "<td colspan='2'>" + address + "</td>";
                        html += "</tr></tbody>";
                    });
                }
                $("#" + adressId).html(html);
// 				var $inputs =  $("#" +adressId).find("input");//服务地址列表
// 				var thizAdd = $("#serverAddressEditOne").val();//订单当前服务地址
// 				if(cateType==2){
// 					thizAdd = $("#serverAddressEditCont").val();
// 				}
// 				ok:
// 				for (var i = 0; i < $inputs.length; i++) {
// 					var inputAdd = $($inputs[i]).attr("data-adderss");
// 					if(thizAdd == inputAdd){
// 						$($inputs[i]).prop("checked",true);
// 						$("#orderEditcReceiverId").val($($inputs[i]).val())
// 						break ok;
// 					}
// 				}

            }
        });
    }

    function setOrderEditcReceiverId(checkbox) {
        $("#orderEditcReceiverId").val($(checkbox).val());
    }
    function orderEditcUpdateAddress(userAddressId) {
        var userId = parent.$("#checkedUserId").val();
        openModule('/order/jsp/orderItemaddressAdd.jsp', {
            lx: 8,
            userId: userId,
            userAddressId: userAddressId
        }, '', '', 'orderItemaddressAdd');
    }

    // 计算总价格
    function setTotalPayAddServer(thiz, tagIdSuffix) {
        var pickQuantity = $("#pickQuantityServer" + tagIdSuffix).val();
        var reg = /^[0-9]{1,}$/;
        if (pickQuantity == "") {
            return;
        }
        if (!reg.test(pickQuantity)) {
            $.alert({millis: 2000, text: "请输入大于0的整数数字！"});
            $("#pickQuantityServer" + tagIdSuffix).val("");
            return;
        }
        if (pickQuantity == 0) {
            $.alert({millis: 2000, text: "请输入大于0的整数数字！"});
            $("#pickQuantityServer" + tagIdSuffix).val("");
            return;
        }
        $("#totalPayAddServer" + tagIdSuffix).val($("#serverProduct" + tagIdSuffix + " option:selected").val().split(",")[1] * $("#pickQuantityServer" + tagIdSuffix).val());
    }
    //查询商品是否配置库存
    function isStock(cityCode, cateCode) {
        var result = "";
        //判断是否是它营单次
        if (cateCode == "" || cateCode == null) {
            $("#notStock").css("display", "block");
            $("#isStockDate").css("display", "none");
            $("#isStockTime").css("display", "none");
            result = "2";
        } else {
            $.ajax({
                url: ctx + "/orderbase/selectStock",
                data: {
                    dkey: cityCode,
                    dvalue: cateCode
                },
                type: 'post',
                async: false,
                dataType: "json",
                success: function (data) {
                    var html = "";
                    if (data.msg == "00") {
                        //有库存
                        $("#notStock").css("display", "none");
                        $("#isStockDate").css("display", "block");
                        $("#isStockTime").css("display", "block");
                        result = "1";
                    } else {
                        //没库存
                        $("#notStock").css("display", "block");
                        $("#isStockDate").css("display", "none");
                        $("#isStockTime").css("display", "none");
                        result = "2";
                    }
                }
            });
        }
        return result;
    }
    /**
     * 查询服务人员库存数量
     * @date:2017年7月21日
     * @author YUNNA
     *
     */
    function checkNumStock() {
        // alert($("#timeServer").val().substr(0,10))
        $.ajax({
            url: ctx + "/orderbase/checkNumStock",
            data: {
                dkey: $("#cityCode").val(),
                dvalue: parent.$("#productCode").val(),
                remarks: $("#timeServer").val().substr(0, 10),//截取日期的前十位
                level: $("#timesoltKun").val().split("-")[0],
                types: $("#timesoltKun").val().split("-")[1]
            },
            type: 'post',
            async: false,
            dataType: 'json',
            success: function (data) {
                $("#numStock").val(data.count);

            }
        });
    }
</script>
</html>

