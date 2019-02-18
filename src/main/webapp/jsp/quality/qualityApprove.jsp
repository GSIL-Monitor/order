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
            <li class="active">售后审核</li>
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
                        <input type="hidden" id="userLoginLevel_approve">
                        <form class="form-inline">
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单ID：</p>
                                        <input type="text" class="form-control" id="afterSaleIdSearch_approve"/>
                                    </label>
                                </div>
                                <!--  <div class="form-group  col-xs-6">
                                     <label>
                                         <p>订单编号：</p>
                                         <input type="text" class="form-control" id="orderCodeSearch_approve"/>
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
                                        <input type="text" class="form-control" id="userNameOrder_approve"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>客户电话：</p>
                                        <input type="text" class="form-control" id="userMobileOrder_approve"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单状态：</p>
                                        <select id="orderStatus_approve" class="form-control">
                                            <option value="" selected="selected">...请选择...</option>
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
                                            <option value="20130013">新建</option>
                                            <option value="20130014">回访中</option>
                                            <option value="20130015">回访失败</option>
                                        </select>

                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>售后单类型：</p>
                                        <select id="serverType_approve" class="form-control">
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
                                            <option value="9">解决方案订单退费</option>
                                            <option value="11">唯品会白条分期退费</option>
                                            <option value="13">唯品会白条分期服务费退费</option>
                                            <option value="14">海金保理白条分期服务费退费</option>
                                            <option value="15">招行分期分期服务费退费</option>
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
                                        <input id="creStart_approve" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                        &nbsp;至&nbsp;
                                        <input id="creEnd_approve" class="Wdate form-control" type="text"
                                               onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"/>
                                    </label>
                                </div>
                                <div class="form-group col-xs-1">
                                    <button type="button" class="btn btn-sm btn-default fr mr20"
                                            onclick="queryAfterSalesApprove(0,10);">查询
                                    </button>
                                </div>
                                <div class="form-group col-xs-1">
                                    <button type="button" class="btn btn-sm btn-default fr mr20"
                                            onclick="resetSalesApprove();">重置
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="panel-heading panel-new" id="makeSureBtn" style="display: none;">
                    <button class="xinzeng btn btn-sm btn-default" onclick="makeSureBtn(1);"><em
                            class="widget-new glyphicon glyphicon glyphicon-ok"></em><span>确认有效</span></button>
                    <button class="xinzeng btn btn-sm btn-default" onclick="makeSureBtn(2);"><em
                            class="widget-new glyphicon glyphicon glyphicon-remove"></em><span>确认无效</span></button>
                </div>

                <div class="panel-heading panel-new" id="accountMakeSureBtn" style="display: none;">
                    <button class="xinzeng btn btn-sm btn-default" onclick="accountMakeSureBtn(1);"><em
                            class="widget-new glyphicon glyphicon glyphicon-ok"></em><span>确认有效</span></button>
                    <button class="xinzeng btn btn-sm btn-default" onclick="accountMakeSureBtn(2);"><em
                            class="widget-new glyphicon glyphicon glyphicon-remove"></em><span>确认无效</span></button>
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
                            <th>管家帮分期退款状态</th>
                            <th>管家帮分期取消状态</th>
                            <th>关联编号</th>
                            <th>客户姓名</th>
                            <%-- <% if( !CookieUtils.getJSONKey(request, "managerType").equals("2")){ %>
                            <th width="10%">客户电话</th>
                            <%} %> --%>
                            <th>售后类型</th>
                            <th>审核人</th>
                            <th>审核部门</th>
                            <th>录入人</th>
                            <th>录入部门</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                        <tbody id="listBodyQualityApprove">
                        </tbody>
                    </table>
                </div>
                <ul class="pagination pagination-sm navbar-right" id="pageQualityDiv_approve"></ul>
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
        queryAfterSalesApprove(0, 10);
        /*loading*/
        $('#shclDefault').shCircleLoader();
        /*tab栏选择*/
        tabx(".tab-item", ".main")
        /*表单合并*/
        drop(".drop-on", ".drop-down", ".info-select");
        /*Tab栏滑动*/
        tabs(".mytabs-wrap");
    })
</script>
</html>

