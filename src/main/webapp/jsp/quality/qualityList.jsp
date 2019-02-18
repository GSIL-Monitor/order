<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <%-- 	<script type="text/javascript">
            var managerType = <%=CookieUtils.getJSONKey(request, "managerType") %>;
            $("#managerTypeQualityList").val(managerType);
        </script> --%>
    <script type="text/javascript" src="${ctx}/js/orderBase.js"></script>
    <script type="text/javascript" src="${ctx}/js/quality.js"></script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}"/>
<!-- <input type="hidden" id="managerTypeQualityList" name="managerTypeQualityList" /> -->
<!-- /.col-lg-12 -->
<div class="row">
    <div class="col-lg-12">
        <ol class="page-header breadcrumb">
            <li><a href="#">Home</a></li>
            <li><a href="#">订单管理系统</a></li>
            <li><a href="#">售后管理</a></li>
            <li class="active">我的售后</li>
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
                        <input type="hidden" id="userLoginLevel">
                        <form class="form-inline">
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单ID：</p>
                                        <input type="text" class="form-control" id="afterSaleIdSearch"/>
                                    </label>
                                </div>
                                <!--  <div class="form-group  col-xs-6">
                                     <label>
                                         <p>订单编号：</p>
                                         <input type="text" class="form-control" id="orderCodeSearch"/>
                                     </label>
                                 </div> -->
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>关联编号：</p>
                                        <input type="text" class="form-control" id="afterSales_fkcode"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>客户姓名：</p>
                                        <input type="text" class="form-control" id="userNameOrder"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>客户电话：</p>
                                        <input type="text" class="form-control" id="userMobileOrder"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单状态：</p>
                                        <select id="orderStatus" class="form-control">
                                            <option value="" selected="selected">...请选择...</option>
                                            <option value="20130013">新建</option>
                                            <option value="20260001">已取消</option>
                                            <option value="20130001">待确认</option>
                                            <option value="20130002">确认无效</option>
                                            <option value="20130003">确认有效</option>
                                            <option value="20130004">审核中</option>
                                            <option value="20130005">审核失败</option>
                                            <option value="20130006">审核成功</option>
                                            <option value="20130007">退款中</option>
                                            <option value="20130008">退款成功</option>
                                            <option value="20130009">退款失败</option>
                                            <option value="20130010">已删除</option>
                                            <option value="20130011">结算中心审核中</option>
                                            <option value="20130012">结算中心驳回</option>
                                            <option value="20130014">回访中</option>
                                            <option value="20130015">回访失败</option>
                                        </select>

                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单类型：</p>
                                        <select id="serverType" class="form-control">
                                            <option value="" selected="selected">...请选择...</option>
                                            <option value="1">FA订单取消</option>
                                            <!-- <option value="2">FA订单退货/退款</option> -->
                                            <!-- <option value="3">FA订单换货重发</option> -->
                                            <option value="4">单次服务订单取消</option>
                                            <option value="5">延续性服务订单取消</option>
                                            <!-- <option value="7">延续性服务订单换人</option> -->
                                            <option value="8">延续性服务订单终止</option>
                                            <!-- <option value="10">延续性服务订单退费</option> -->
                                            <option value="6">解决方案订单取消</option>
                                            <option value="13">唯品会白条分期服务费退费</option>
                                            <option value="14">海金保理白条分期服务费退费</option>
                                            <option value="15">招行分期分期服务费退费</option>
                                            <option value="11">唯品会白条分期退费</option>
                                            <option value="17">海金保理白条分期退费</option>
                                            <option value="18">招行分期退费</option>
                                            <option value="21">汇嘉分期退费</option>
                                            <option value="19">分期回购海金保理</option>
                                            <option value="20">分期回购唯品会</option>
                                            <option value="9">解决方案订单退费</option>
                                            <option value="16">卡退费</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-10">
                                    <label>
                                        <p>创建时间：</p>
                                        <input id="creStart" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                        &nbsp;至&nbsp;
                                        <input id="creEnd" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-1">
                                    <button type="button" class="btn btn-sm btn-default fr mr20"
                                            onclick="queryAfterSalesByLike(0,10);">查询
                                    </button>
                                </div>
                                <div class="form-group col-xs-1">
                                    <button type="button" class="btn btn-sm btn-default fr mr20"
                                            onclick="resetSales();">重置
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel-heading panel-new">
                    <button class="xinzeng btn btn-sm btn-default" onclick="insertAfterSalesBtn();">
                        <em class="widget-new glyphicon glyphicon glyphicon-plus-sign"></em><span>新增</span>
                    </button>
                    <button class="xinzeng btn btn-sm btn-default" onclick="updateAfterSales();">
                        <em class="widget-new glyphicon glyphicon glyphicon-ok-sign"></em><span>修改</span>
                    </button>
                    <button class="xinzeng btn btn-sm btn-default" onclick="deleteAfterSales();">
                        <em class="widget-new glyphicon glyphicon glyphicon-remove-sign"></em><span>删除</span>
                    </button>
                    <!-- <button class="xinzeng btn btn-sm btn-default"  onclick="changeOrderSales();"><em
                    class="widget-new glyphicon glyphicon glyphicon-share"></em><span>迁单</span></button> -->
                    <button class="xinzeng btn btn-sm btn-default" onclick="insertAfterSalesNew();">
                        <em class="widget-new glyphicon glyphicon glyphicon-plus-sign"></em><span>新增非订单售后</span>
                    </button>
                    <button class="xinzeng btn btn-sm btn-default" onclick="updateAfterSalesNew();">
                        <em class="widget-new glyphicon glyphicon glyphicon-ok-sign"></em><span>修改非订单售后</span>
                    </button>
                </div>
                <!--列表开始-->
                <div class="panel-content table-responsive">
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <!--  <th width="2%"><input name="odIds" id="odIds" type="checkbox"></th> -->
                            <th></th>
                            <th>序号</th>
                            <th>售后单ID</th>
                            <th>售后状态</th>
                            <!-- <th >管家帮分期退款状态</th>
                            <th >管家帮分期取消状态</th> -->
                            <th>关联编号</th>
                            <th>客户姓名</th>
                            <%-- <% if( !CookieUtils.getJSONKey(request, "managerType").equals("2")){ %>
                            <th width="10%">客户电话</th>
                            <%} %> --%>
                            <th>售后类型</th>
                            <th>终止订单</th>
                            <th>创建时间</th>
                            <th>备注</th>
                        </tr>
                        </thead>
                        <tbody id="listBodyQuality">
                        </tbody>
                    </table>
                </div>
                <ul class="pagination pagination-sm navbar-right" id="pageQualityDiv"></ul>
                </tbody>
            </div>
        </div>
    </div>
    <!-- /.col-lg-4 -->
    <div class="col-lg-4">
        <div class="aside-tab">
            <jsp:include page="/jsp/quality/qualityRight.jsp"></jsp:include>
        </div>
    </div>
</div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        queryAfterSalesByLike(0, 10);

        /*提示框*/
        /*   $('[data-toggle="tooltip"]').tooltip() */
        /*loading*/
        $('#shclDefault').shCircleLoader();
        /*tab栏选择*/
        tabx(".tab-item", ".main")
        /*表单合并*/
        drop(".drop-on", ".drop-down", ".info-select");
        /*Tab栏滑动*/
        tabs(".mytabs-wrap");
        /*表格单选*/
        //radioColor("#listBodyQuality > tr");

        //回访信息卡显示
        $("#callBackDetail").show();
    })
    function insertAfterSalesBtn() {
        var loginLevel = $("#userLoginLevel").val();
        openModule('/order/jsp/quality/qualityOrderList.jsp', {loginLevel: loginLevel}, {}, {width: '70%'}, 'qulityOrderList');
    }
    function updateAfterSales() {
        var orderTypeCode = $("input[name='quaRadio']:checked").attr("data-orderTypeCode");
        var asInfo = $("input[name='quaRadio']:checked").val();
        var asArr = asInfo.split("-");
        var afterSalesId = asArr[0];  //售后单id
        var orderId = asArr[1];		  //订单id
        var auditFlag = asArr[2];	  //审批状态
        var cateType = asArr[3];	  //订单类型
        var afterSaleKind = asArr[4]; //售后单类型
        var vphBackStatus = asArr[5]; //管家帮分期退款状态
        if (afterSaleKind == 13 || afterSaleKind == 14 || afterSaleKind == 15 || afterSaleKind == 21) {
            $.alert({millis: 3000, top: '30%', text: "分期服务费退费类型不可以修改！"});
            return;
        }
        if (afterSaleKind == '11' || afterSaleKind == '17' || afterSaleKind == '18') {
            $.alert({millis: 5000, top: '30%', text: "此功能不可修改当前记录"});
            return;
        }
        if(afterSaleKind == "9" || afterSaleKind == "6"){
            $.alert({millis: 5000, top: '30%', text: "解决方案售后不支持修改功能!"});
            return;
        }

        if (afterSaleKind != 7 && afterSaleKind != 12) {
            if (auditFlag == "20130001" || auditFlag == "20130002" || auditFlag == "20130005" || auditFlag == "20130009"
                    || (vphBackStatus != null && vphBackStatus != "" && vphBackStatus == "20130009") || auditFlag == "20130013" || auditFlag == "20130015") {//新建、待确认、确认无效、审核失败和退费失败,管家帮分期退款失败,回访失败可以修改
                if (cateType == 2) {
                    openModule('${ctx}/jsp/quality/qualityServerEdit.jsp', {
                        afterSalesId: afterSalesId,
                        orderId: orderId,
                        cateType: cateType,
                        auditFlag: auditFlag,
                        flag: 2,
                        orderTypeCode: orderTypeCode
                    }, {}, {width: '70%'}, 'qualityServerEdit');
                    //openModule('${ctx}/jsp/quality/afterSaleServer.jsp',{afterSalesId:afterSalesId,orderId:orderId,cateType:cateType,auditFlag:auditFlag,flag:2},{},{width:'70%'},'afterSaleServer');
                } else if (cateType == 1 || cateType == 4) {
                    openModule('${ctx}/jsp/quality/qualityItemEdit.jsp', {
                        afterSalesId: afterSalesId,
                        orderId: orderId,
                        cateType: cateType,
                        auditFlag: auditFlag
                    }, {}, {width: '70%'}, 'qualityItemEdit');
                } else if (cateType == 3) {
                    openModule('${ctx}/jsp/quality/qualityItemEdit.jsp', {
                        afterSalesId: afterSalesId,
                        orderId: orderId,
                        cateType: cateType,
                        auditFlag: auditFlag
                    }, {}, {width: '70%'}, 'qualityItemEdit');
                } else {
                    openModule('${ctx}/jsp/quality/qualitySolutionEdit.jsp', {
                        afterSalesId: afterSalesId,
                        orderId: orderId,
                        cateType: cateType,
                        auditFlag: auditFlag
                    }, {}, {width: '70%'}, 'qualitySolutionEdit');
                }
            } else {
                $.alert({millis: 3000, top: '30%', text: "请选择待确认、回访失败、审核失败和退费失败的售后单！"});
            }
        }
        if (afterSaleKind != 7 && afterSaleKind == 12) {
            $.alert({millis: 3000, top: '30%', text: "迁单售后单不能修改！"});
        }
        /* else{
         $.alert({millis:3000,top:'30%',text:"延续性服务订单换人售后单不能修改！"});
         } */
    }

    function deleteAfterSales() {
        var delasInfo = $("input[name='quaRadio']:checked").val();
        var delasArr = delasInfo.split("-");
        var afterSalesId = delasArr[0];  //售后单id
        var orderId = delasArr[1];		  //订单id
        var auditFlag = delasArr[2];	 //审核状态
        var accountPayId = delasArr[6];//银行卡结算单id
        var vphAccountId = delasArr[7];//唯品会结算单id
        var afterSaleKind = delasArr[4]; //售后单类型
        if (afterSaleKind == 13 || afterSaleKind == 14 || afterSaleKind == 15) {
            $.alert({millis: 3000, top: '30%', text: "分期服务费退费类型不可以删除！"});
            return;
        }
        if (afterSaleKind == 11 || afterSaleKind == 17 || afterSaleKind == 18) {
            $.alert({millis: 3000, top: '30%', text: "分期退费类型不可以删除！"});
            return;
        }
        if (auditFlag == "20130001") {
            $.confirm({
                text: "确定要删除吗？", callback: function (y) {
                    if (y) {
                        $.ajax({
                            url: ctx + "/afterSales/updateAfterSales",
                            data: {
                                id: afterSalesId,
                                auditFlag: 20130010,   //已删除
                                orderId: orderId,
                                accountPayId: accountPayId,
                                vphAccountId: vphAccountId
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.msg == "00") {
                                    $.alert({millis: 2000, top: '30%', text: "删除成功！"});
                                    queryAfterSalesByLike(0, 10);
                                } else {
                                    $.alert({millis: 2000, top: '30%', text: "删除失败！"});
                                }
                            }
                        });
                    }
                }
            })
        } else {
            $.alert({millis: 3000, top: '30%', text: "请选择待确认的售后单！"});
        }
    }


    /*判断数组是否包含指定元素*/
    Array.prototype.contains = function (needle) {
        for (i in this) {
            if (this[i] == needle) return true;
        }
        return false;
    }

    /* 2018-05-22 新增卡与解决方案售后*/
    function insertAfterSalesNew() {
        //operationType 1:新增操作 2:修改操作
        var data = {"operationType": 1};
        openModule('${ctx}/jsp/quality/insertAfterSalesNew.jsp', data, null, {"width": "50%"});
    }
    /* 2018-05-22 修改卡与解决方案售后*/
    function updateAfterSalesNew() {
        var delasInfo = $("input[name='quaRadio']:checked").val();
        var delasArr = delasInfo.split("-");
        var afterSalesId = delasArr[0];  //售后单id
        var payFeeId = delasArr[8];//缴费id
        var afterSalesKind = delasArr[4]; //售后单类型
        var auditFlag = delasArr[2];	  //审批状态
        var vphBackStatus = delasArr[5]; //管家帮分期退款状态
        var afterSalesKindArr = ['11', '17', '18', '13', '14', '15','21'];
        //var afterSalesKindArr = ['11','17','18'];
        if (afterSalesKindArr.contains(afterSalesKind)) {
            var vphBackStatusArr = ['20260001', '20130001', '20130002', '20130005', '20130009', '20130012','20250054'];
            if (!vphBackStatusArr.contains(auditFlag)) {
                $.alert({millis: 5000, top: '30%', text: "当前状态不可修改"});
                return;
            }
        } else {
            $.alert({millis: 5000, top: '30%', text: "此功能不可修改当前记录"});
            return;
        }
        //operationType 1:新增操作 2:修改操作
        var data = {
            "operationType": 2,
            "afterSalesId": afterSalesId,
            "payFeeId": payFeeId,
            "afterSalesKind": afterSalesKind
        };
        openModule('${ctx}/jsp/quality/insertAfterSalesNew.jsp', data, {}, {"width": "50%"});
    }

    /**
     * 查询缴费已使用明细金额
     * @param PayfeeId 缴费id
     */
    function queryPayfeeDetail(PayfeeId) {
        var salaryFee = 0;
        $.ajax({
            url: "/order/payfee/queryPayfeeDetail",
            data: {"id": PayfeeId},
            type: "POST",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    salaryFee = data.salaryFee || 0;
                }
            }
        })
        return salaryFee;
    }

    /**
     * 查询订单父订单解决方案是否为赠送解决方案类型
     *
     * @param 订单父订单id parentId
     */
    function queryTypeByParentId(parentId) {
        var type = null;
        $.ajax({
            url: "/order/payfee/queryTypeByParentId",
            data: {"parentId": parentId},
            type: "POST",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    type = data.type || null;
                }
            }
        })
        return type;
    }
</script>
</html>

