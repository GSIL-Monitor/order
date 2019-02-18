<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8" %>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
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
        .orderLeftSpan {
            width: 30px;
            font-weight: bold;
            font-family: '黑体';
            font-size: 20px;
            margin-left: 5px;
        }
        .orderCounttab {
        }
    </style>
    <link href="${ctx}/css/star-rating.min.css" rel="stylesheet" media="all" type="text/css">
    <link href="${ctx}/css/rotate.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/css/swiper.min.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/js/agreement.js"></script>
    <script type="text/javascript" src="${ctx}/js/appraise.js"></script>
</head>
<body>
<div>
    <div class="mytabs-wrap">
        <ul class="mytabs mytabsss" id="mytabsss">
            <li class="tab-item orderCounttab tab-active" id="basicInformation">基本信息</li>
            <li class="tab-item orderCounttab" onclick="getPersonInfo();getHistoryPerson()">服务人员</li>
            <li class="tab-item orderCounttab" id="sanfangxieyi" onclick="queryAgreement();">三方协议</li>
            <li class="tab-item orderCounttab" id="jiaofei">缴费</li>
            <li class="tab-item orderCounttab" id="serverBasicOne5" onclick="serverBasicOne(5);">回访记录</li>
            <li class="tab-item orderCounttab" id="turnOrderLogTab" onclick="queryOrderTurnLog($('#checkedOrderId').val(),'turnOrderLogList');">转单记录</li>
            <li class="tab-item orderCounttab" id="evaluateTab" onclick="checkServerPersonType($('#checkedOrderId').val());queryEvaluate($('#checkedOrderId').val(),'appraiseServerList',0,5);queryEvaluateBack($('#checkedOrderId').val(),'appraiseReplyServerList',0,5);">用户评价</li>
        </ul>
    </div>
    <div id="arr">
        <span id="left"><</span><span id="right">></span>
    </div>
    <div class="tab-content">
        <div class="main tab-selected" id="basicCont">
            <div class="widget">
                <%-- <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
						onclick="openModule('${ctx }/jsp/server/lined.jsp','','','','orderLined')">
						<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="排期">排期</i></button> --%>
            </div>
            <div class="widget" id="setBasicServerEditButton" style="hieght:30px; display:none;">
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="openModule('${ctx }/jsp/server/orderServerEdit.jsp','','','','orderServerEdit')">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i>
                </button>
            </div>
            <div class="widget" id="setBasicServertypeEditButton" style="hieght:30px; display:none; ">
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="openModule('${ctx}/jsp/server/orderServer.jsp','','','','orderServerEdit_type')">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="修改">修改订单类型</i></button>
            </div>
            <!-- 提前发服务费按钮，权限开给结算中心 -->
            <auth:menu menuId="1134">
                <div class="widget" style="hieght:30px; display:block;">
                    <button type="button" id="updateSalaryStatusBtn" class="btn btn-primary btn-xs"
                            onclick="updateSalaryStatus();">
                        <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                           title="切换服务人员服务费方式">切换服务人员服务费方式</i>
                    </button>
                </div>
            </auth:menu>
            <div class="row">
                <div class="col-xs-12">
                    <h4>详细信息</h4>
                </div>
            </div>
            <div class="widget-content">
                <input type="hidden" id="serverTypeBasics" name="serverType"/>
                <input type="hidden" id="serverOrderType"/>
                <input type="hidden" id="managerOrgType"/>
                <input type="hidden" id="accountCreateTimeBasic" name="accountCreateTimeBasic"/>
                <input type="hidden" id="accountSystemDateBasic" name="accountSystemDateBasic"/>
                <input type="hidden" id="hisPersonLine"/>
                <input type="hidden" id="rightPersonLine"/>
                <input type="hidden" id="catecode">
                <input type="hidden" id="startTimeShanghu">
                <input type="hidden" id="startDate">
                <input type="hidden" id="endDate">
                <span style="display:none;" id="benWorkWeek"></span>
                <input type="hidden" id="benWorkHours">
                <span style="display:none; " id="personLinedIds"></span>
                <span style="display:none; " id="personLinedStatuss"></span>
                <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
                    <tr>
                        <td width="55%">
                            订单编号：<span id="orderCodeBasics"></span>
                        </td>
                        <td width="45%">
                            服务类型：<span id="servserTextBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="45%" colspan="2">创建时间：<span id="createTimesServer"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            订单省份：<span id="receiverProvinceBasics"></span>
                        </td>
                        <td>
                            订单城市：<span id="receiverCityBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            订单状态：<span id="statusTextBasics"></span>
                        </td>
                        <td>
                            支付状态：<span id="payTextServer"></span>
                        </td>
                    </tr>
                    <tr>
                        <%-- <% if (!CookieUtils.getJSONKey(request, "managerType").equals("2")) { %>
                        <td>
                            订单来源：<span id="sourceTextBasics"></span>
                        </td>
                        <%} %> --%>
                        <td>
                            订单来源：<span id="sourceTextBasics"></span>
                        </td>
                        <td>
                            订单渠道：<span id="channelTextBasicServer"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            带单人员：<span id="personServer"></span>
                        </td>
                        <td>
                            带单人电话：<span id="personmobileServer"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            排期开始时间：<span id="startTimeBasics"></span>
                        </td>
                        <td>
                            排期结束时间：<span id="endTimeBasics"></span><br>
                           
                        </td>
                    </tr>
                <tr>
                        <td colspan="2">
                                联系人:<span id="linkMan"></span><br>
                                联系电话:<span id="linkMoble"></span><br>
                        </td>
                    <tr>    
                    
                    
                    <tr id="birthDateDiv">
                        <td colspan="2">
                            预产期：<span id="birthDate_show"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            服务地址：<span id="addressBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            面试时间：<span id="interviewTimeBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            面试地址：<span id="interviewAddressBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            发服务人员服务费方式：<span id="salaryStatus"></span>
                        </td>
                    </tr>
                    <tr colspan="2">
                        <td>方案编号：<span id="basicSolutionCodeServer"></span></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            订单备注：<span id="remarkBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            是否投保：<span id="insureBasics"></span>
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
                <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
                    <tr>
                        <td>
                            客户姓名：<span id="nameBasics" name="nameBasics"></span>
                        </td>
                        <td colspan="2">
                            <button type="button" class="btn btn-sm btn-default"
                                    onclick="javascript:parent.toCustomerBankCard();">完善客户账户信息
                            </button>
                            <button type="button" class="btn btn-sm btn-default fr mr20"
                                    onclick="javascript:parent.editUserHref();">完善客户资料
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            客户电话：<span id="mobileBasics"></span>
                        </td>
                        <td colspan="2">
                            性别：<span id="sexBasics"></span>
                        </td>
                    </tr>
                    <!--  <tr>
	                        <td colspan="2" >
	                        	家庭地址：<span id="userAddressBasics"></span>
	                        </td>
	                    </tr> -->
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
                            <tbody id="orderInforServerCont">
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
                        <td width="50%">
                            年龄要求：<span id="minAgeBasics"></span>
                            ~<span id="maxAgeBasics"></span>
                        </td>
                        <td width="50%">
                            籍贯要求：<span id="originBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%">
                            等级要求：<span id="personLevelTextBasics"></span>
                        </td>
                        <td width="50%">
                            学历要求：<span id="educationTextBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="50%" colspan="2">
                            性别要求：<span id="xqSexTextBasics"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            备注：<span id="remarkBasics2"></span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="main" id="dfdfdfdfdf">
            <div id="dfdfdfdf" style="border:1px solid #CCC;">
                <div class="widget-content">
                    <div class="project-order-news">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget">
                                    <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                            onclick="setOrderLined()">
                                        <i class="glyphicon glyphicon-refresh" data-toggle="tooltip"
                                           data-placement="top" title="排期">订单排期</i></button>
                                    <!-- <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs" onclick="delayOrderLined()">
						<i class="glyphicon " data-toggle="tooltip" data-placement="top" title="延长订单排期">延长订单排期</i></button>
						<button id="delayPerson" type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs" onclick="delayPersonLined()">
						<i class="glyphicon " data-toggle="tooltip" data-placement="top" title="延长服务人员排期">延长服务人员排期</i></button>
						<button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs" onclick="auditDelayLinedApply()">
						<i class="glyphicon " data-toggle="tooltip" data-placement="top" title="审核延长申请">审核延长申请</i></button> -->
                                </div>
                            </div>
                            <div class="col-xs-12"><h4>服务排期</h4></div>
                            <div class="col-xs-12">
                                <label> 订单排期:</label>
                                <span id="orderLineDay"></span>
                                <div id="orderLine"></div>
                                <div id="personLineDiv">
                                    <label> 服务人员排期:</label>
                                    <span id="order_person_Line"></span>
                                    <div id="orderPersonLine"></div>
                                </div>
                                <div id="dealyOrderLineDay">
                                    <label> 延长服务申请:
                                        <span id="dealyOrderLine"></span>
                                        <input type="hidden" id="checkStatus">
                                        <input type="hidden" id="dealyId">
                                        <div id="delayOrderLine"></div>
                                    </label>
                                    <label>
                                        <button id="cancleApply" type="button" class="btn btn-primary btn-xs"
                                                value="取消申请" onclick="cancelDelayLined()">取消申请
                                        </button>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="widget">
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="openOrderNeeds();">
                                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                                       title="推送">推送</i></button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="makeSuerPersonsNow()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="确定人员匹配">确定人员匹配</i>
                                </button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="setPersonStartNow()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="上户">上户</i>
                                </button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="setPersonEndNow()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="下户">下户</i>
                                </button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="cancelPerson()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="取消">取消</i>
                                </button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="setPersonLined()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="查看服务人员排期">查看服务人员排期</i>
                                </button>
                            </div>
                            <div class="widget">
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="toReplaceJobsPush();">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top"
                                       title="顶岗推送">顶岗推送</i></button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="changingJobsDown()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top"
                                       title="调岗下户">调岗下户</i></button>
                                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                                        onclick="retuningJobsUp()">
                                    <i class="glyphicon" data-toggle="tooltip" data-placement="top"
                                       title="返岗上户">返岗上户</i></button>
                            </div>
                            <div class="col-xs-12"><h4>当前服务人员</h4></div>
                            <div id="rightNowPerson">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12"><h4>历史服务人员</h4></div>
                            <div id="historyPersonServer">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="main" id="agreements">
            <%-- <%@ include file="orderAgreement.jsp" %> --%>
        </div>
        <div class="main" id="fees">
            <div class="widget">
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="addAccountServer();">
                    <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i>
                </button>
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="updateAccountServer();" id="updateAccountServerBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="修改结算">修改结算</i></button>
                <!-- 删除结算单，结算中心特殊权限 -->
                <auth:menu menuId="1150">
                    <button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServer();"
                            id="deleteAccountServerBtn">
                        <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除结算">删除结算</i>
                    </button>
                </auth:menu>
                <!-- <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="updateygAccount();" id="updateygAccountBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="查看预估">查看预估</i></button> -->
                <!-- 查看预估，结算中心特殊权限 -->
                <auth:menu menuId="1033">
                    <button type="button" class="btn btn-primary btn-xs" onclick="updateygAccount();"
                            id="updateygAccountOtherBtn">
                        <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="查看预估">查看预估</i>
                    </button>
                </auth:menu>
            </div>
            <div class="widget">
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="addPayfeeServer();">
                    <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i>
                </button>
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="updatePayfeeServer()" id="updatePayfeeServerBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="修改缴费">修改缴费</i></button>
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="addPayfeeWeiXinServer()" id="addPayfeeWeiXinServerBtn">
                    <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top"
                       title="微信扫码支付">微信扫码支付</i></button>
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="addPayfeeJiaLianServer()" id="addPayfeeJiaLianServerBtn">
                    <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top"
                       title="嘉联二维码">嘉联二维码</i></button>
                <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                        onclick="addPayfeeLianDongServer(1)" id="">
                    <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top"
                       title="联动二维码">联动二维码</i></button>
            </div>
            <div class="widget-content">
                <div class="project-order-news">
                    <div class="row">
                        <div class="col-xs-12"><h4>缴费信息</h4></div>
                    </div>
                    <div id="accountListBodyCont">
                    </div>
                </div>
            </div>
        </div>
        <div class="main" id="interviews">
            <div id="ReturnRecord" style="border:1px solid #CCC;">
                <div class="widget">
                    <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                            onclick="addreturnrecord();">
                        <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增">新增</i>
                    </button>
                    <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                            onclick="updatereturnrecord();">
                        <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                           title="修改">修改</i></button>
                    <!-- <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs" onclick="addreturnrecord();">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除">删除</i></button> -->
                </div>
                <div class="widget-content">
                    <div class="project-order-news">
                        <div class="row">
                            <div class="col-xs-12"><h4>回访信息</h4></div>
                        </div>
                        <div id="returnInfoServer">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="main" id="turnOrderLog">
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
                    <tbody id="turnOrderLogList">

                    </tbody>
                </table>
            </div>
        </div>
        <div class="main" id="appraiseOrderServer">
            <div class="panel-content table-responsive">
                <div style="height: 800px; overflow: auto;">
                    <div id="appraiseServerList">
                    </div>
                    <div id="appraiseReplyServerList">
                    </div>
                    <div class='dotted'></div>
                    <div style="width:500px;height:30px;overflow:hidden;margin: 0 0 30px auto;">
                        <ul class='pagination pagination-sm navbar-right' style="margin:0;"
                            id='appraisePageInfoDiv'></ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>
<script type="text/javascript" src="${ctx}/js/star-rating.js"></script>
<script>
    function authButton(agreementModel, advancePeriod, checkStatus, agreementState) {
        $("#a_insert").removeAttr("disabled"); //新增
        $("#a_details").removeAttr("disabled"); //详情
        $("#a_update").removeAttr("disabled"); //修改
        $("#a_sure").removeAttr("disabled");   //普通合同确认
        $("#a_end").removeAttr("disabled"); // 终止
        $("#a_delete").removeAttr("disabled");  //删除
        $("#a_preview").removeAttr("disabled");	//预览
        $("#a_export").removeAttr("disabled");	//导出
        $("#a_download").removeAttr("disabled");	//下载首页
        $("#a_uploading").removeAttr("disabled");	//上传/修改合同扫描件
        $("#a_look").removeAttr("disabled");  //查看合同扫描件
        $("#a_sendMsg").removeAttr("disabled"); //发送短信
        $("#a_dsure").removeAttr("disabled"); //电子合同确认
        $("#a_date").removeAttr("disabled"); //海金更新合同签约日期
        $("#a_again").removeAttr("disabled"); //合同重签
        $("#a_hjend").removeAttr("disabled"); //海金分期协议终止
        $("#a_againsend").removeAttr("disabled");//重发代扣协议
        if (agreementModel == 20520001 && advancePeriod == 5) {
            if (checkStatus == 1) {//未处理
                $("#a_insert").attr("disabled", true); //新增
                //$("#a_details").attr("disabled", true); //详情
                //$("#a_update").attr("disabled", true); //修改
                $("#a_sure").attr("disabled", true);   //普通合同确认
                $("#a_end").attr("disabled", true); // 终止
//                $("#a_delete").attr("disabled", true);  //删除
                //$("#a_preview").attr("disabled", true);	//预览
                $("#a_export").attr("disabled", true);	//导出
                $("#a_download").attr("disabled", true);	//下载首页
                $("#a_uploading").attr("disabled", true);	//上传/修改合同扫描件
                $("#a_look").attr("disabled", true);  //查看合同扫描件
                //$("#a_sendMsg").attr("disabled",true); //发送短信
                $("#a_dsure").attr("disabled", true); //电子合同确认
                $("#a_date").attr("disabled", true); //海金更新合同签约日期
                $("#a_again").attr("disabled", true); //合同重签
                $("#a_hjend").attr("disabled", true); //海金分期协议终止
            } else if (checkStatus == 2) {//审核通过
                $("#a_insert").attr("disabled", true); //新增
                $("#a_update").attr("disabled", true); //修改
                $("#a_sure").attr("disabled", true);   //普通合同确认
                $("#a_look").attr("disabled", true);  //查看合同扫描件
                $("#a_againsend").attr("disabled", true);//补发代扣协议
                $("#a_again").attr("disabled", true); //合同重签

            } else {//驳回
                $("#a_sure").attr("disabled", true);   //普通合同确认
                $("#a_end").attr("disabled", true); // 终止
//                $("#a_delete").attr("disabled", true);  //删除
                $("#a_preview").attr("disabled", true);	//预览
                $("#a_export").attr("disabled", true);	//导出
                $("#a_download").attr("disabled", true);	//下载首页
                $("#a_uploading").attr("disabled", true);	//上传/修改合同扫描件
                $("#a_look").attr("disabled", true);  //查看合同扫描件
                $("#a_dsure").attr("disabled", true); //电子合同确认
                $("#a_date").attr("disabled", true); //海金更新合同签约日期
                //$("#a_again").attr("disabled", true); //合同重签
                $("#a_hjend").attr("disabled", true); //海金分期协议终止
            }
        } else {
            $("#a_insert").removeAttr("disabled"); //新增
            $("#a_details").removeAttr("disabled"); //详情
            $("#a_update").removeAttr("disabled"); //修改
            $("#a_sure").removeAttr("disabled");   //普通合同确认
            $("#a_end").removeAttr("disabled"); // 终止
            $("#a_delete").removeAttr("disabled");  //删除
            $("#a_preview").removeAttr("disabled");	//预览
            $("#a_export").removeAttr("disabled");	//导出
            $("#a_download").removeAttr("disabled");	//下载首页
            $("#a_uploading").removeAttr("disabled");	//上传/修改合同扫描件
            $("#a_look").removeAttr("disabled");  //查看合同扫描件
            $("#a_sendMsg").removeAttr("disabled"); //发送短信
            $("#a_dsure").removeAttr("disabled"); //电子合同确认
            $("#a_date").removeAttr("disabled"); //海金更新合同签约日期
            if (checkStatus == 2 && checkStatus == 1 && advancePeriod == 5) {
                $("#a_again").attr("disabled", true); //合同重签
            } else {
                $("#a_again").removeAttr("disabled"); //合同重签
            }
            $("#a_hjend").removeAttr("disabled"); //海金分期协议终止
            $("#a_againsend").attr("disabled", true);
        }

        /*
        * 合同按钮显示规则，在之前的规则基础上增加：
        *   新建：可删除 可修改
        *   签约中：可删除 可确认
        *   已确认：可终止
        *   已删除：无
        *   已终止：无
        *   查看按钮无限制
        * */
        if(agreementState == 1){//新建
            $("#a_details").removeAttr("disabled"); //详情
            $("#a_delete").removeAttr("disabled");  //删除
            $("#a_update").removeAttr("disabled"); //修改
        }else if(agreementState == 2){//已确认
            $("#a_details").removeAttr("disabled"); //详情
            $("#a_end").removeAttr("disabled"); // 终止
        }else if(agreementState == 6){//签约中
            $("#a_details").removeAttr("disabled"); //详情
            $("#a_delete").removeAttr("disabled");  //删除
            $("#a_sure").removeAttr("disabled");   //普通合同确认
            $("#a_dsure").removeAttr("disabled"); //电子合同确认
        }
    }
    function setBasicServer(orderId, cateType, totalPay, orderStatus, rechargeBy) {
        // 判断哪些状态下订单可以修改
        if (orderStatus == 1 || orderStatus == 2 || orderStatus == 3 || orderStatus == 4 || orderStatus == 5) {
            $("#setBasicServerEditButton").css("display", "block");
            $("#setBasicServertypeEditButton").css("display", "block");
        } else {
            $("#setBasicServerEditButton").css("display", "none");
            $("#setBasicServertypeEditButton").css("display", "none");
        }
        $("#needsPersonals").html("");
        $("#productCode").val("");
        getServerBasics(orderId);
        var typeText = $("#servserTextBasics").text();
        if (typeText != null && typeText != "" && typeText == "月嫂") {
            $("#birthDateDiv").show();
        } else {
            $("#birthDateDiv").hide();
        }
        queryAgreement();
        setOrderButtonsAllCont();
        $("input:radio[name=radioAccount]").each(function () {
            $(this).val("");
        });
        queryAccount(orderId, "accountListBodyCont", cateType, totalPay);
        makeSuerPersons();
        serverInterviews();
        showBtn(cateType, orderId);
        tabsss(".mytabs-wrap");
        setOrderButtonsAllCont();
        //setOrderLoginserver();
        getPersonInfo();//new   查询匹配的服务人员
        getHistoryPerson();//new   查询匹配的历史服务人员
        getLineTime();//查询排期时间
        getPersonLineTime();//查询服务人员排期
        queryPersonLineTime();//单独查询服务人员信息
        delayApplyLined();//查询延长申请
        queryReturn(orderId, "returnInfoServer");
        queryOrderTurnLog(orderId, 'turnOrderLogList');
        checkServerPersonType(orderId);
        queryEvaluate(orderId, 'appraiseServerList', 0, 5);
        queryEvaluateBack(orderId, 'appraiseReplyServerList', 0, 5);
    }
    function setOrderLoginserver() {
        var rechargeBy = $("#checkedRechargeBy").val();
        if (rechargeBy == null || rechargeBy == '') {
            //alert("登录人："+loginBy+"负责人"+rechargeBy);
            $("#xuqiutuisong").css("display", "none");
            $("#quedingrenyuan").css("display", "none");
            $("#sanfangxieyi").css("display", "none");
            $("#jiaofei").css("display", "none");
            $("#shanghu").css("display", "none");
        } else {
            $("#xuqiutuisong").css("display", "block");
            $("#quedingrenyuan").css("display", "block");
            $("#sanfangxieyi").css("display", "block");
            $("#jiaofei").css("display", "block");
            $("#shanghu").css("display", "block");
        }
    }
    function setOrderButtonsAllCont() {
        var loginByOrNot = $("#checkedLoginByOrNot").val();
        var loginRechargeOrNot = $("#checkedLoginRechargeOrNot").val();
        var rechargeBy = $("#checkedRechargeBy").val();
        var loginBy = $("#checkedLoginBy").val();
        //alert(loginByOrNot +" and " +loginRechargeOrNot +" and " +rechargeBy);
        // 当前登录人员操作按钮权限控制
        // 按是否已经分包分为两种不同控制方式
        if (loginBy != "1") {
            if (rechargeBy == null || rechargeBy == "" || rechargeBy == 0) { // 未分包
                //负责人为空，没有按钮操作权限
                $("button:button[name=orderButtonsAllCont]").each(function () {
                    $(this).hide();
                });
                $("input:button[name=orderInputButtonsAllCont]").each(function () {
                    $(this).hide();
                });
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).hide();
                });
            } else { // 已分包,负责人对应的操作权限
                $("button:button[name=orderButtonsAllCont]").each(function () {
                    $(this).show();
                });
                $("input:button[name=orderInputButtonsAllCont]").each(function () {
                    $(this).show();
                });
                $("button:button[name=orderButtonsAllItem]").each(function () {
                    $(this).show();
                });
                if (rechargeBy != loginBy) {
                    $("button:button[name=orderButtonsAllCont]").each(function () {
                        $(this).hide();
                    });
                    $("input:button[name=orderInputButtonsAllCont]").each(function () {
                        $(this).hide();
                    });
                    $("button:button[name=orderButtonsAllItem]").each(function () {
                        $(this).hide();
                    });
                } else {
                    $("button:button[name=orderButtonsAllCont]").each(function () {
                        $(this).show();
                    });
                    $("input:button[name=orderInputButtonsAllCont]").each(function () {
                        $(this).show();
                    });
                    $("button:button[name=orderButtonsAllItem]").each(function () {
                        $(this).show();
                    });
                }
            }
        } else {
            $("button:button[name=orderButtonsAllCont]").each(function () {
                $(this).show();
            });
            $("input:button[name=orderInputButtonsAllCont]").each(function () {
                $(this).show();
            });
            $("button:button[name=orderButtonsAllItem]").each(function () {
                $(this).show();
            });
        }
    }
    function tabsss(tabsw) {
        $(document).ready(function () {
            tabWidth = $(tabsw).outerWidth();
            tabw("#right", "#left", ".orderCounttab", ".mytabsss");
        });
        $(window).resize(function () {
            tabWidth = $(tabsw).outerWidth();
            tabw("#right", "#left", ".orderCounttab", ".mytabsss");
        });
        setOrderButtonsAllCont();
    }
    //取到订单详细信息
    function getServerBasics(orderId) {
        $("#serverOrderType").val("");
        $("#managerOrgType").val(0);
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
                if (data.msg == "00") {
                    var html = "";
                    var num = $.each(data.list, function (i, v) {
                        $("#basicSolutionCodeServer").val(v.solutionCode);
                        $("#basicServerInterviewsTolalPay").val(v.order.totalPay);
                        $("#basicServerInterviewsCustomerManageMoney").val(v.order.customerManageMoney);
                        $("#basicServerInterviewsIds").val(v.ids);
                        $("#orderCodeBasics").text(v.orderCode);
                        $("#serverTypeBasics").val(v.serverType);
                        $("#servserTextBasics").text(v.typeText);
                        $("#serverOrderType").val(v.order.orderType);
                        $("#startTimeBasics").text(numberToDatestr(v.startTime, 8));
                        $("#endTimeBasics").text(numberToDatestr(v.endTime, 8));
                        /** 周鑫  2019-01-22 **/
                        $("#linkMan").text(v.order.receiverName);
                        $("#linkMoble").text(v.order.receiverMobile);
                        /** 周鑫  2019-01-22 **/
                        $("#interviewTimeBasics").text(numberToDatestr(v.interviewTime, 12));
                        $("#criticalTextBasics").text(v.criticalText);
                        $("#payTextServer").text(v.payText);
                        $("#catecode").val(v.productCategoryCode);
                        $("#statusTextBasics").text(v.statusText);
                        $("#sourceTextBasics").text(v.sourceText);
                        $("#channelTextBasicServer").text(v.channelText);
                        $("#personServer").text(v.order.person);
                        $("#personmobileServer").text(v.order.personmobile);
                        $("#addressBasics").text(v.address);
                        $("#interviewAddressBasics").text(v.interviewAddress);
                        $("#remarkBasics").text(v.remark);
                        //是否投保
                        var insure = v.insure;
                        if (insure == 1) {
                            $("#insureBasics").text("是");
                        } else if (insure == 2) {
                            $("#insureBasics").text("否");
                        } else {
                            $("#insureBasics").text("暂无信息");
                        }
                        $("#createTimesServer").text(numberToDatestr(v.createTime, 12));
                        $("#receiverProvinceBasics").text(v.order.receiverProvince);
                        $("#receiverCityBasics").text(v.order.receiverCity);
                        $("#birthDate_show").text(v.birthTimeOrder);
                        $("#salaryStatus").text(v.order.salaryStatusText);
                        // 基础信息：需求推送
                        $("#remarkBasics2").text(v.remark);
                        $("#minAgeBasics").text(v.minAge);
                        $("#maxAgeBasics").text(v.maxAge);
                        $("#originBasics").text(v.originText);
                        $("#personLevelTextBasics").text(v.personLevelText);
                        $("#educationTextBasics").text(v.educationText);
                        var xqSex = "";
                        if (v.xqSex == "1") {
                            xqSex = "男";
                        } else if (v.xqSex == "2") {
                            xqSex = "女";
                        } else {
                            xqSex = "不限";
                        }
                        $("#xqSexTextBasics").text(xqSex);
                        // 客户信息
                        $("#nameBasics").text(v.order.userName);
                        $("#mobileBasics").text(v.order.userMobile);
                        $("#sexBasics").text(v.sex == 1 ? "男" : "女");
                        $("#birthBasics").text(v.birth == "" ? "" : numberToDatestr(v.birth, 8));
                        $("#userAddressBasics").text(v.userAddress);
                        // 需求推送：需求推送
                        $("#remarkBasicNeeds").text(v.remark);
                        $("#minAgeBasicNeeds").text(v.minAge);
                        $("#maxAgeBasicNeeds").text(v.maxAge);
                        $("#originBasicNeeds").text(v.originText);
                        $("#personLevelTextBasicNeeds").text(v.personLevelText);
                        $("#educationTextBasicNeeds").text(v.educationText);

                        // 商品信息
                        html += "<tr style='height:25px;'>";
                        html += "<td>" + v.productName + "(" + v.productUnitText + ")</td>";
                        html += "<td>" + v.productPriceTypeText + "</td>";
                        html += "<td>" + v.nowPrice + "</td>";
                        html += "<td>" + v.quantity + "</td>";
                        html += "<td>" + v.productSpec + "</td>";
                        html += "</tr>";
                    })
                    $("#managerOrgType").val(data.orgType);
                    html += "<tr></tr>";
                    document.getElementById("orderInforServerCont").innerHTML = html;
                } else {
                    $("#basicServerInterviewsTolalPay").val();
                    $("#basicServerInterviewsCustomerManageMoney").val();
                    $("#basicServerInterviewsIds").val();
                    $("#orderCodeBasics").text();
                    $("#serverTypeBasics").val();
                    $("#servserTextBasics").text();
                    $("#startTimeBasics").text();
                    $("#endTimeBasics").text();
                    $("#interviewTimeBasics").text();
                    $("#criticalTextBasics").text();
                    $("#payTextServer").text();
                    $("#statusTextBasics").text();
                    $("#sourceTextBasics").text();
                    $("#addressBasics").text();
                    $("#interviewAddressBasics").text();
                    $("#remarkBasics").text();
                    $("#createTimesServer").text();
                    $("#receiverProvinceBasics").text();
                    $("#receiverCityBasics").text();
                    $("#salaryStatus").text();
                    // 基础信息：需求推送
                    $("#remarkBasics2").text();
                    $("#minAgeBasics").text();
                    $("#maxAgeBasics").text();
                    $("#originBasics").text();
                    $("#personLevelTextBasics").text();
                    $("#educationTextBasics").text();
                    $("#xqSexTextBasics").text();
                    // 客户信息
                    $("#nameBasics").text();
                    $("#mobileBasics").text();
                    $("#sexBasics").text();
                    $("#birthBasics").text();
                    $("#userAddressBasics").text();
                    // 需求推送：需求推送
                    $("#remarkBasicNeeds").text();
                    $("#minAgeBasicNeeds").text();
                    $("#maxAgeBasicNeeds").text();
                    $("#originBasicNeeds").text();
                    $("#personLevelTextBasicNeeds").text();
                    $("#educationTextBasicNeeds").text();
                    document.getElementById("orderInforServerCont").innerHTML = "<tr></tr>";
                }
            }
        });
    }

    // 自动匹配服务人员
    function getPersonAccord() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var num = queryNeedPersons(1);
        if (num >= 1) {
            $.alert({text: "当前订单已经完成需求推送，无法匹配人员！"});
            return;
        }
        // 校验延续订单是否有生成排期
        var lind = 0;
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderServerLined",
            data: {
                orderId: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    lind = 1;
                }
            }
        });
        if (lind == 0) {
            $.alert({text: "请先生成需求排期！"});
            return;
        }
        var ids = "";
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                type: 1,
                orderId: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        if (ids != "") {
                            ids += ",";
                        }
                        ids += v.id;
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='4'>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                        html += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
                        html += "</td><td>服务人员姓名：" + v.name;
                        html += "</td></tr><tr><td>&nbsp;序号" + (i + 1);
                        html += "</td><td>产品管家：" + v.realName;
                        html += "</td></tr><tr><td></td><td>产品管家电话：" + v.phone;
                        html += "</td></tr><tr><td></td><td>状态：" + v.typeText;
                        html += "</td></tr></table>";
                    })
                } else {
                    $.alert({text: "匹配不到合适的服务人员！"});
                }
                $("#needsPersonals").html(html);
            }
        });
        $("#checkedContNeedsIds").val(ids);

    }
    // 打开手动匹配列表
    function openOrderNeeds() {
        orderId = $("#checkedOrderId").val();
        //var num = queryNeedPersons(1);
        var orderLineDay = $("#orderLineDay").text().split("------");
        if (orderLineDay[0] == "" || orderLineDay[1] == "") {
            $.alert({text: "请完善排期！"});
            return
        }
        //处理5个服务人员
        var PersonStatus = new Array();
        $("#rightNowPerson [name = personCheck]").each(function () {
            if ($(this).attr("data-status") == 1 || $(this).attr("data-status") == 4) { //1未处理
                PersonStatus.push($(this).attr("value"));
            }
        });
        if (PersonStatus.length > 4) {
            $.alert({text: "当前订单最多匹配5个服务人员！"});
            return;
        }
        //面试成功不能推送
        var interview = new Array();
        $("#rightNowPerson [name = personCheck]").each(function () {
            if ($(this).attr("data-status") == 6 || $(this).attr("data-status") == 8 || $(this).attr("data-status") == 11) { //6面试成功 11顶岗预备
                interview.push($(this).attr("value"));
            }
        });
        if (interview.length > 0) {
            $.alert({text: "面试成功或上户不能推送！"});
            return;
        }
        // if(num>=1){
        //	$.alert({ text : "当前订单已经完成需求推送，无法匹配人员！" });
        //return ;
        //	}
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10 || orderStatus == "10") {
            $.alert({text: "当前订单已经取消，无法匹配人员！"});
            return;
        }
        if (orderStatus == 12 || orderStatus == "12") {
            $.alert({text: "当前订单已经终止，无法匹配人员！"});
            return;
        }
        if (orderStatus == 8 || orderStatus == "8") {
            $.alert({text: "当前订单已上户，无法匹配人员！"});
            return;
        }
        // 校验延续订单是否有生成排期
        var lind = 0;
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderServerLined",
            data: {
                orderId: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        //判断钟点工排期是否完整
                        if (v.linedType == 2) {
                            if (v.hours == "" || v.hours == null) {
                                $.alert({text: "当前订单排期不完整，请完善排期！"});
                                return;
                            } else {
                                lind = 1;
                            }
                        } else {
                            lind = 1;
                        }
                        if (lind == 0) {
                            $.alert({text: "请先生成需求排期！"});
                            return;
                        } else {
                            openModule('${ctx }/jsp/server/orderNeeds.jsp',
                                    {
                                        "orderId": orderId,
                                        "cateType": 2,
                                        "orderLineDay":orderLineDay[1],
                                        "cityCode": $("#checkedCity").val(),
                                        "orderType": $("#serverOrderType").val()//20180927增加订单类型参数
                                    }, '', '', 'orderNeedsModule');
                        }
                    });
                }
            }
        });
    }
    /* new */
    function getHistoryPerson() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $("#hisPersonLine").val("")
        //历史服务人员
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                type: 15
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                if (data.msg == "00") {
                    $("#hisPersonLine").val("2")
                    var typeText = "";
                    var num = $.each(data.list, function (i, v) {
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='7'><input type='hidden' id='empIdHide" + orderId + "'/>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息'  style='width:80px; height:80px;'/>";
                        //html += "<div><button style='width:80px;' class='btn btn-primary btn-xs' type='button'  onclick='downloadEmpFileCard("+v.id+");'>下载信息卡</button></div>" ;
                        html += "</td>";
                        html += "<td>姓名：" + v.name + "</td></tr>";
                        html += "<tr><td><input  name='personCheck' type=radio value=" + v.userId + " person-id=" + v.id + " data-status=" + v.interviewType + "  data-startTime='" + numberToDatestr(v.startTime, 12) + "' data-endTime='" + numberToDatestr(v.endTime, 8) + "'>";
                        html += "</td><td>证件号：" + v.idCardNum + "</td></tr>";
                        html += "<tr><td></td><td>产品管家：" + v.realName + "(" + v.phone + ")</td></tr>";
                        html += "<tr><td></td><td>匹配方式：" + v.matchMethod;
                        html += "<tr><td></td><td>匹配状态：" + v.typeText;
                        if (v.interviewType == 8) {
                            html += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                        } else {
                            html += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                        }
                        html += "</td></tr><tr><td></td><td>上户时间：" + numberToDatestr(v.startTime, 8);
                        html += "</td></tr><tr><td></td><td>下户时间：" + numberToDatestr(v.endTime, 8);
                        html += "</td></tr></table>";
                    })
                }
                $("#historyPersonServer").html(html)
            }
        });
    }
    /* new */
    function getPersonInfo() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $("#personLinedIds").text("")
        $("#personLinedStatuss").text("")
        $("#rightPersonLine").val("");
        //当前服务人员
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                type: 5
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                if (data.msg == "00") {
                    $("#rightPersonLine").val("1");
                    var typeText = "";
                    var num = $.each(data.list, function (i, v) {
                        $("#startTimeShanghu").val(numberToDatestr(v.startTime, 12))
                        $("#personLinedIds").append(v.id + ",")
                        $("#personLinedStatuss").text(v.interviewType)
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='7'><input type='hidden' id='empIdHide" + orderId + "'/>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息'  style='width:80px; height:80px;'/>";
                        //html += "<div><button style='width:80px;' class='btn btn-primary btn-xs' type='button'  onclick='downloadEmpFileCard("+v.id+");'>下载信息卡</button></div>" ;
                        html += "</td>";
                        html += "<td>姓名：" + v.name + "</td></tr>";
                        html += "<tr><td><input  name='personCheck' type=radio value=" + v.userId + " person-id=" + v.id + " data-status=" + v.interviewType + " data-startTime='" + numberToDatestr(v.startTime, 8) + "' data-endTime='" + numberToDatestr(v.endTime, 8) + "'>";
                        html += "</td><td>证件号：" + v.idCardNum + "</td></tr>";
                        html += "<tr><td></td><td>产品管家：" + v.realName + "(" + v.phone + ")</td></tr>";
                        html += "<tr><td></td><td>匹配方式：" + v.matchMethod;
                        html += "<tr><td></td><td>匹配状态：" + v.typeText;
                        if (v.interviewType == 8) {
                            html += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                        } else {
                            html += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                        }
                        html += "</td></tr><tr><td></td><td>上户时间：" + numberToDatestr(v.startTime, 8);
                        html += "</td></tr><tr><td></td><td>下户时间：" + numberToDatestr(v.endTime, 8);
                        html += "</td></tr></table>";
                    })
                }
                $("#rightNowPerson").html(html)
            }
        });

    }
    // 回显手动匹配到的服务人员信息
    function getPersionHandsCont(ids) {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $("#checkedContNeedsIds").val(ids);
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                ids: ids,
                orderId: orderId,
                type: 4
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                if (data.msg == "00") {
                    var typeText = "";
                    var num = $.each(data.list, function (i, v) {
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='4'>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                        html += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
                        html += "</td><td>服务人员姓名：" + v.name;
                        /* html += "</td></tr><tr><td>&nbsp;序号" +(i+1) ; */
                        html += "</td></tr><tr><td><input  name='personCheck' type=checkbox value=" + v.id + ">";
                        html += "</td><td>产品管家：" + v.realName;
                        html += "</td></tr><tr><td></td><td>产品管家电话：" + v.phone;
                        html += "</td></tr><tr><td></td><td>" + typeText;
                        html += "</td></tr></table>";
                    })
                }
                $("#needsPersonals").html(html);
                $("#rightNowPerson").html(html)
            }
        });
    }

    //需求推送,保存匹配的服务人员信息
    function insertInterviewPerson() {
        var ids = $("#checkedContNeedsIds").val();
        if (ids == null || ids == "") {
            $.alert({text: "请先匹配服务人员！"});
            return;
        }
        var num = queryNeedPersons(1);
        if (num >= 1) {
            $.alert({text: "当前订单已经完成需求推送！"});
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10 || orderStatus == 20) {
            $.alert({text: "当前订单已经取消，无法需求推送！"});
            return;
        }
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemInterview/insertItemInterview",
            data: {
                orderId: orderId,
                ids: ids,
                type: 2
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.alert({text: "需求推送成功！"});
                    getPersionHandsCont(ids);
                }

            }
        })
    }

    // 待选定服务人员面试通过列表
    function makeSuerPersons() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                type: 5
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                var userId = "";
                var name = "";
                var realName = "";
                var mobile = "";
                var personId = "";
                var interviewType = "";
                var typeText = "";
                var originalAddress = "";
                var phone = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='4'>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                        html += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
                        html += "</td><td>服务人员姓名：" + v.name;
                        html += "</td></tr><tr><td>";
                        html += "<input name=\"checkedPersonsNeedsId\" type=\"radio\"";
                        html += " onclick=\"checkedPersonsNeeds(" + v.userId + ",'" + v.name + "','" + v.realName + "','" + v.mobile + "'," + v.id + "," + v.interviewType + ",'" + v.typeText + "','" + v.originalAddress + "','" + v.phone + "')\">&nbsp;序号" + (i + 1);
                        html += "</td><td>产品管家：" + v.realName;
                        html += "</td></tr><tr><td></td><td>产品管家电话：" + v.phone;
                        html += "</td></tr><tr><td></td><td>状态：" + v.typeText;
                        html += "</td></tr></table>";
                        if (v.interviewType == 6) {
                            userId = v.userId;
                            name = v.name;
                            realName = v.realName;
                            mobile = v.mobile;
                            personId = v.id;
                            interviewType = v.interviewType;
                            typeText = v.typeText;
                            originalAddress = v.originalAddress;
                            phone = v.phone;
                        }
                    })
                }
                $("#makesuersPersonalsView").html(html);
                checkedPersonsNeeds(userId, name, realName, mobile, personId, interviewType, typeText, originalAddress, phone);
            }
        });
        setOrderButtonsAllCont();
    }
    // 待确定服务人员面试通过选定
    function checkedPersonsNeeds(intvId, name, realName, mobile, personId, interviewType, typeText, originalAddress, phone) {
        html = "";
        if (intvId != null && intvId != "") {
            $("#checkedPersonsNeedsIdSuer").val(intvId);
            $("#checkedPersonsNeedsPersonIdSuer").val(personId);
            $("#checkedPersonsNeedsinterviewTypeSuer").val(interviewType);
            html += "<table class='tabOrder'><tr><td rowspan='4'>";
            html += "<img src='" + originalAddress + "' onclick='checkPersons(" + personId + ")' title='点击查看详细信息' ";
            html += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
            html += "</td><td>服务人员姓名：" + name;
            html += "</td><td></td></tr><tr><td>产品管家：" + realName;
            html += "</td><td>";
            // 取消面试成功的服务人员，并释放当前服务人员已经锁定的排期
            if (interviewType == 6) {
                html += "<input type='button' name='orderInputButtonsAllCont' class='btn btn-primary btn-xs' value='取消' onclick='setPersonOver(" + intvId + "," + personId + ")'/>";
            }
            html += "</td></tr><tr><td>产品管家电话：" + phone;
            html += "</td></tr><tr><td>状态：" + typeText;
            html += "</td><td></td></tr></table>";
        } else {
            $("#checkedPersonsNeedsIdSuer").val("");
            $("#checkedPersonsNeedsPersonIdSuer").val("");
            $("#checkedPersonsNeedsinterviewTypeSuer").val("");
        }
        $("#makesuersPersonals").html(html);
    }
    // 确定服务人员面试通过    new
    function makeSuerPersonsNow() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var orderStatus = $("#checkedOrderStatus").val();
        var interviewType = "";
        var interviewTypeBak = "";
        if (orderStatus == 10) {
            $.alert({text: "当前订单已经取消，无法需求推送！"});
            return;
        }
        if (orderStatus == 12) {
            $.alert({text: "当前订单已经终止，无法需求推送！"});
            return;
        }
        var PersonInfoStatus = new Array();
        var person = new Array();
        var result = new Array();
        $("[name = personCheck]:radio").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
                PersonInfoStatus.push($(this).attr("data-status"));
                person.push($(this).attr("person-id"));
            }
        });
        var inwid = result.join(",");
        for (i = 0; i < PersonInfoStatus.length; i++) {
            if (PersonInfoStatus[i] == 1) {
                $.alert({text: "产品管家未确定！"});
                return;
            } else if (PersonInfoStatus[i] > 4) {
                $.alert({text: "当前服务人员已完成匹配！"});
                return;
            }
        }
        if (orderstatus == 10 || orderstatus == "10") {
            $.alert({text: "订单已取消 不能推送！"});
            return;
        }
        if (orderStatus == 12 || orderStatus == "12") {
            $.alert({text: "当前订单已经终止，无法匹配人员！"});
            return;
        }
        if (orderStatus == 8 || orderStatus == "8") {
            interviewType = 11;
            interviewTypeBak = 11;
        } else {
            interviewType = 6;
            interviewTypeBak = 6;
        }
        if (inwid == "" || inwid == null) {
            $.alert({text: "请选择服务人员！"});
            return;
        }
        if (!selectConflictSchedule()) {
            return;
        }
        /* if (!checkAccountTotal()) {
            return;
        } */
        $.confirm({
            text: "确定人员后，将不能再对订单进行修改排期、需求及人员信息。是否继续？", callback: function (re) {
                if (re) {
                    $.ajax({
                        url: ctx + "/itemInterview/updateItemInterview",
                        data: {
                            ids: inwid,
                            orderId: orderId,
                            personids: person.join(","),
                            interviewType: interviewType,
                            type: 6,
                            interviewTypeBak: interviewTypeBak
                        },
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            html = "";
                            if (data.msg == "00") {
                                //$.alert({ text : "服务人员面试成功已确定！" });
                                $.alert({text: "服务人员已匹配成功,请于3天内完成合同录入！"});
                                $("#checkedOrderStatus").val(6);
                                getPersonInfo()//new   查询匹配的服务人员
                                getHistoryPerson()//new   查询匹配的历史服务人员
                            } else if (data.msg == "03") {
                                $.alert({text: "需求排期不正确！"});
                                return;
                            } else {
                                $.alert({text: "确定服务人员出错！"});
                                return;
                            }
                        }
                    })
                } else {
                    return;
                }
            }
        });


    }
    // 确定服务人员面试通过
    function makeSuerPersonsNeeds() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
// 		var id = $("input:radio[name=checkedPersonsNeedsIdSuer]:checked").val();
        var id = $("#checkedPersonsNeedsIdSuer").val(); // t_order_item_interview 表的id"
        var personId = $("#checkedPersonsNeedsPersonIdSuer").val();
        var interviewType = $("#checkedPersonsNeedsinterviewTypeSuer").val();
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "当前订单已取消！"});
            return;
        }
        // alert(orderId +" and " +id +" and " +personId);
        if (id == null || id == "") {
            $.alert({text: "无可确定的服务人员！"});
            return;
        }
        if (interviewType < 4) {
            $.alert({text: "产品管家未确定！"});
            return;
        }
        var num = queryNeedPersons(6);
        if (num >= 1) {
            $.alert({text: "当前订单人员已完成确定！"});
            return;
        }
        if (interviewType < 4) {
            $.alert({text: "产品管家未确定！"});
            return;
        }
        $.confirm({
            text: "确定人员后，将不能再对订单进行修改排期、需求及人员信息。是否继续？", callback: function (re) {
                if (re) {
                    $.ajax({
                        url: ctx + "/itemInterview/updateItemInterview",
                        data: {
                            id: id,
                            orderId: orderId,
                            personId: personId,
                            interviewType: 6,
                            type: 6
                        },
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            html = "";
                            if (data.msg == "00") {
                                $.alert({text: "服务人员面试成功已确定！"});
                                $("#checkedOrderStatus").val(6);
                                makeSuerPersons();
                            } else if (data.msg == "03") {


                                $.alert({text: "需求排期不正确！"});
                                return;
                            } else {
                                $.alert({text: "确定服务人员出错！"});
                                return;
                            }
                        }
                    })
                } else {
                    return;
                }
            }
        });

    }
    // 取消当前服务人员
    function setPersonOver(intvId, personId) {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "当前订单已取消！"});
        } else if (orderStatus == 12) {
            $.alert({text: "当前订单已终止！"});
        } else {
            $.confirm({
                text: "是否确定取消？", callback: function (re) {
                    if (re) {
                        $.ajax({
                            url: ctx + "/itemInterview/updateItemInterview",
                            data: {
                                id: intvId,
                                orderId: orderId,
                                personId: personId,
                                interviewType: 10,
                                markType: 10,
                                type: 10
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                html = "";
                                if (data.msg == "00") {
                                    $.alert({text: "已取消！"});
                                    makeSuerPersons();
                                }
                            }
                        })
                    } else {
                        return;
                    }
                }
            });
        }
    }
    // 待上户人员信息
    function serverInterviews() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                type: 8
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html1 = "";
                var html2 = "";
                if (data.msg == "00") {
                    var num = $.each(data.list, function (i, v) {
                        if (v.interviewType == 6) {
                            html1 += "<table class='tabOrder'><tr>";
                            html1 += "<td rowspan='4'>";
                            html1 += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                            html1 += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
                            html1 += "</td><td>服务人员姓名：" + v.name;
                            html1 += "</td><td></td></tr><tr><td>产品管家：" + v.realName;
                            html1 += "</td><td><input type='button' name='orderInputButtonsAllCont' class='btn btn-primary btn-xs' value='上户' onclick='setPersonStart(" + v.userId + "," + v.id + ")'/>";
                            html1 += "</td></tr><tr><td>产品管家电话：" + v.phone;
                            html1 += "</td><td></td></tr><tr><td>状态：" + v.typeText;
                            html1 += "</td><td></td></tr></table>";
                        } else {
                            html2 += "<table class='tabOrder'><tr>";
                            html2 += "<td rowspan='3'><input type='hidden' id='empIdHide" + orderId + "'/>";
                            html2 += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                            html2 += " style='width:80px; height:80px;'/>&nbsp;&nbsp;";
                            html2 += "</td><td>服务人员姓名：" + v.name;
                            html2 += "</td><td></td></tr><tr><td>状态：";
                            if (v.interviewType == 8) {
                                html2 += "上户中</td><td>";
                                html2 += "<input type='button' name='orderInputButtonsAllCont' class='btn btn-primary btn-xs' value='下户' onclick='setPersonEnd(" + v.userId + "," + v.id + ")'/>";
                                html2 += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                                html2 += "</td></tr><tr><td>上户时间：" + numberToDatestr(v.startTime, 12);
                            } else {
                                html2 += "已下户</td><td>";
                                html2 += "<input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/>";
                                html2 += "</td></tr><tr><td>下户时间：" + numberToDatestr(v.endTime, 12);
                            }
                            html2 += "</td><td></td></tr></table>";
                        }
                    })
                }
                $("#interviewPersonals").html(html1);
                $("#oldNeedsPersonals").html(html2);
            }
        });
        setOrderButtonsAllCont();
    }
    // 上户   new
    function setPersonStartNow() {
        var orderStatus = $("#checkedOrderStatus").val();
        // 为了老数据已经存在的合同，现把订单状态为6的也加入校验通过(现已取消)
        if (orderStatus == 10) {
            $.alert({text: "订单已取消，无法上户！"});
            return;
        } else if (orderStatus != 7 && orderStatus != 11 && orderStatus != 8) {
            $.alert({text: "请先确定三方合同！"});
            return;
        }
        if (orderStatus == 12) {
            $.alert({text: "订单已终止，无法上户！"});
            return;
        }
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var PersonInfoStatus = new Array();
        var result = new Array();
        var person = new Array();
        $("[name = personCheck]:radio").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
                PersonInfoStatus.push($(this).attr("data-status"));
                person.push($(this).attr("person-id"));
            }
        });
        var inwid = result.join(",");
        var personId = person.join(",");
        for (i = 0; i < PersonInfoStatus.length; i++) {
            if (PersonInfoStatus[i] != 6 && PersonInfoStatus[i] != 11) {
                $.alert({text: "面试成功的服务人员才可以上户！"});
                return;
            }
        }
        if (inwid == "" || inwid == null) {
            $.alert({text: "请选择服务人员！"});
            return;
        }
        //顶岗预备回显默认上户时间
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var id = personCheck.val(), startTime = "", nowInterviewType = "";
        var list = queryInterviews({"id": id});
        if (list.length == 1) {
            startTime = list[0].starTime;
            nowInterviewType = list[0].interviewType;
        }
        $.confirm({
            text: "是否确认上户？", callback: function (re) {
                if (re) {
                    openModule("${ctx}/jsp/server/orderPersonStartCont.jsp",
                            {
                                "interviewType": 8,
                                "orderId": orderId,
                                "ids": inwid,
                                "personId": personId,
                                "startTime": startTime,
                                "nowInterviewType": nowInterviewType
                            },
                            "", {}, "orderPersonStartCont");
                }
            }
        });
    }
    //查看服务人员排期
    function setPersonLined() {
        var ctx = $("#ctx").val();
        var person = new Array();
        $("[name = personCheck]:radio").each(function () {
            if ($(this).is(":checked")) {
                person.push($(this).attr("person-id"));
            }
        });
        if (person.length != 1) {
            $.alert({text: "请选择一个服务人员！"});
            return;
        }
        openModule("${ctx}/jsp/server/scheduling.jsp?",
                {"personId": person.join(",")},
                "", {}, "scheduling");
    }
    // 上户
    function setPersonStart(id, personId) {
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "订单已取消，无法上户！"});
            return;
        } else if (orderStatus != 7 && orderStatus != 11) {
            $.alert({text: "请先确定三方合同！"});
            return;
        }
        // 如果是当前订单是第一次有服务人员上户，需判断支付金额是否足够扣除信息费
        var totalPay = $("#basicServerInterviewsTolalPay").val();
        var customerManageMoney = $("#basicServerInterviewsCustomerManageMoney").val();
        var ids = $("#basicServerInterviewsIds").val();
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.confirm({
            text: "是否确认上户？", callback: function (re) {
                if (re) {
                    openModule("${ctx}/jsp/server/orderPersonStartCont.jsp?",
                            {
                                "personId": personId,
                                "interviewType": 8,
                                "orderId": orderId,
                                "totalPay": totalPay,
                                "customerManageMoney": customerManageMoney,
                                "ids": ids
                            },
                            "", {}, "orderPersonStartCont");
                    /**
                     $.ajax({
					url: ctx +"/itemInterview/updateItemInterview",
					data:{
						id:id,
						orderId:orderId,
						personId:personId,
						interviewType:8,
						type:8,
						totalPay:totalPay,
						customerManageMoney:customerManageMoney,
						ids:ids
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						html = "" ;
						if (data.msg =="00") {
							$.alert({ text : "上户成功！" });
							serverInterviews();
						}
					}
				}) **/
                }
            }
        });
    }
    // 下户   new
    function setPersonEndNow() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var PersonInfoStatus = new Array();
        var PersonStatus = new Array();
        var result = new Array();
        var person = new Array();
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "订单已取消，无法上户！"});
            return;
        }
        if (orderStatus == 12) {
            $.alert({text: "订单已终止，无法上户！"});
            return;
        }
        $("[name = personCheck]:radio").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
                PersonInfoStatus.push($(this).attr("data-status"));
                person.push($(this).attr("person-id"));
            }
        });
        //判断当前已有服务人员有没有顶岗预备状态，有顶岗预备不能点下户
        $("[name = personCheck]:radio").each(function () {
            PersonStatus.push($(this).attr("data-status"));
        });
        for (i = 0; i < PersonStatus.length; i++) {
            if (PersonStatus[i] == 11) {
                $.alert({text: "当前状态不能下户，请选择调岗下户！"});
                return;
            }
        }
        var inwid = result.join(",");
        for (i = 0; i < PersonInfoStatus.length; i++) {
            if (PersonInfoStatus[i] != 8 && PersonInfoStatus[i] != 12) {
                $.alert({text: "正在上户中的服务人员才可以下户！"});
                return;
            }
        }
        if (inwid == "" || inwid == null) {
            $.alert({text: "请选择服务人员！"});
            return;
        }
        //顶岗预备回显默认下户时间
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var id = personCheck.val(), endTime = "", nowInterviewType = "";
        var list = queryInterviews({"id": id});
        if (list.length == 1) {
            endTime = list[0].endTime;
            nowInterviewType = list[0].interviewType;
        }
        var dotime=personCheck.attr("data-starttime");
        $.confirm({
            text: "是否确认下户？", callback: function (re) {
                if (re) {
                    openModule("${ctx}/jsp/server/orderPersonStartCont.jsp?",
                            {
                                "interviewType": 9,
                                "orderId": orderId,
                                "ids": inwid,
                                "personId": person.join(","),
                                "endTime": endTime,
                                "dotime":dotime,
                                "nowInterviewType": nowInterviewType
                            },
                            "", {}, "orderPersonStartCont");
                } else {
                    return;
                }
            }
        });
    }
    //取消服务人员   new
    function cancelPerson() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var orderStatus = $("#checkedOrderStatus").val();
        var PersonInfoStatus = new Array();
        var result = new Array();
        var person = new Array();
        $("[name = personCheck]:radio").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
                PersonInfoStatus.push($(this).attr("data-status"));
                person.push($(this).attr("person-id"))
            }
        });
        var inwid = result.join(",");
        if (person.join(",") == "" || person.join(",") == null) {
            $.alert({text: "请选择服务人员！"});
            return;
        }
        for (i = 0; i < PersonInfoStatus.length; i++) {
            if (PersonInfoStatus[i] > 6) {
                $.alert({text: "上下户的服务人员不可以取消！"});
                return;
            }
        }
        if (orderStatus == 10) {
            $.alert({text: "当前订单已取消！"});
        } else if (orderStatus == 12) {
            $.alert({text: "当前订单已终止！"});
        } else {
            //alert(inwid+"personId"+person.join(","))
            $.confirm({
                text: "是否确定取消？", callback: function (re) {
                    if (re) {
                        $.ajax({
                            url: ctx + "/itemInterview/updateItemInterview",
                            data: {
                                ids: inwid,
                                personId: person.join(","),
                                orderId: orderId,
                                interviewType: 10,
                                markType: 10,
                                type: 10
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                html = "";
                                if (data.msg == "00") {
                                    getPersonInfo()
                                    $.alert({text: "已取消！"});
                                }
                            }
                        })
                    } else {
                        return;
                    }
                }
            });
        }

    }
    // 下户
    function setPersonEnd(id, personId) {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.confirm({
            text: "是否确认下户？", callback: function (re) {
                if (re) {
                    openModule("${ctx}/jsp/server/orderPersonStartCont.jsp?",
                            {
                                "id": id,
                                "personId": personId,
                                "interviewType": 9,
                                "orderId": orderId,
                                "totalPay": 0,
                                "customerManageMoney": 0,
                                "ids": 0
                            },
                            "", {}, "orderPersonStartCont");
                } else {
                    return;
                }
            }
        });
    }

    // 合同
    function queryAgreement() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var orderStatus = $("#checkedOrderStatus").val();
        var orderType = $("#serverOrderType").val();
        var orgType = $("#managerOrgType").val();
        if (orgType == null || orgType == "") {
            orgType = "1";
        }
        $.ajax({
            url: ctx + "/agreement/queryAgreement",
            data: {
                orderId: orderId,
                valid: 1			//有效合同
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                var html = "";
                html += "<div class='widget' style='height:70px;'>";
                html += "<button type='button' name='orderButtonsAllCont' id='a_insert'  class='btn btn-primary btn-xs' onclick='agreement.add(" + orderId + "," + orderStatus + "," + orderType + "," + orgType + ");' data-toggle='tooltip' data-placement='bottom' title='新增'>"
                html += "<i class='glyphicon glyphicon-plus' ></i></button> ";
                html += "<button type='button' name='orderButtonsAllCont' id='a_details' class='btn btn-primary btn-xs' onclick='agreement.detail(" + orderId + "," + orderType + "," + orgType + ");' data-toggle='tooltip' data-placement='bottom' title='详情'>"
                html += "<i class='glyphicon glyphicon-search' ></i></button> ";
                html += "<button type='button' name='orderButtonsAllCont' id='a_update' class='btn btn-primary btn-xs'  onclick='agreement.updateForward(" + orderId + "," + orderType + "," + orderStatus + "," + orgType + ");' data-toggle='tooltip' data-placement='bottom' title='修改'>"
                html += "<i class='glyphicon glyphicon-pencil' ></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_sure' class='btn btn-primary btn-xs' onclick='agreement.sure(" + orderId + "," + orderStatus + ");'  data-toggle='tooltip' data-placement='bottom' title='普通合同确认'>"
                html += "<i class='glyphicon glyphicon-ok'></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_end' class='btn btn-primary btn-xs' onclick='agreement.end(" + orderStatus + ");' data-toggle='tooltip' data-placement='bottom' title='终止'>"
                html += "<i class='glyphicon glyphicon-off' ></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_delete' class='btn btn-primary btn-xs' onclick='agreement.deleteCpe(" + orderId + "," + orderStatus + ");' data-toggle='tooltip' data-placement='bottom' title='删除'>"
                html += "<i class='glyphicon glyphicon-remove' ></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_preview' class='btn btn-primary btn-xs' onclick='agreement.lookPDF();'  data-toggle='tooltip' data-placement='bottom' title='预览'>"
                html += "<i class='glyphicon glyphicon-eye-open' ></i></button> ";
                html += "<button type='button' name='orderButtonsAllCont' id='a_export'  class='btn btn-primary btn-xs' onclick='agreement.exportPDF(" + orderId + ");'   data-toggle='tooltip' data-placement='bottom' title='导出'>"
                html += "<i class='glyphicon glyphicon-download-alt'></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_download'  class='btn btn-primary btn-xs' onclick='agreement.exportHomePage(" + orderId + ");'   data-toggle='tooltip' data-placement='bottom' title='下载首页'>"
                html += "<i class='glyphicon glyphicon-save-file'></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_uploading'  class='btn btn-primary btn-xs' onclick='agreement.uploadScanFile();'   data-toggle='tooltip' data-placement='bottom' title='上传/修改合同扫描件'>"
                html += "<i class='glyphicon  glyphicon-file'></i><i class='glyphicon glyphicon-upload'></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_look'  class='btn btn-primary btn-xs' onclick='agreement.viewScanFile(" + orderId + ");'   data-toggle='tooltip' data-placement='bottom' title='查看合同扫描件'>"
                html += "<i class='glyphicon  glyphicon-file'></i><i class='glyphicon glyphicon-search'></i></button>";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id=''  class='btn btn-primary btn-xs' onclick='agreement.sendMessage(" + orderId + "," + orderStatus + "," + orderType + "," + orgType + ");' data-toggle='tooltip' data-placement='bottom' title='发送短信'>"
                html += "<i class='glyphicon glyphicon-envelope' ></i></button> ";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_againsend' class='btn btn-primary btn-xs' onclick='agreement.sendWithholdAgreement(" + orderId + ");' data-toggle='tooltip' data-placement='bottom' title='重发代扣协议'>"
                html += "<i class='glyphicon glyphicon-list-alt' ></i></button> ";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_dsure'  class='btn btn-primary btn-xs' onclick='agreement.hashBaoquan(" + orderId + ");' data-toggle='tooltip' data-placement='bottom' title='电子合同确认'>"
                html += "<i class='glyphicon glyphicon-piggy-bank' ></i></button> ";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_date' class='btn btn-primary btn-xs' onclick='agreement.updateContractDate(" + orderId + "," + orderStatus + "," + orderType + "," + orgType + ");' data-toggle='tooltip' data-placement='bottom' title='海金更新合同签约日期'>"
                html += "<i class='glyphicon glyphicon-header' ></i></button> ";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_again' class='btn btn-primary btn-xs' onclick='agreement.contractRemark(" + orderId + ");' data-toggle='tooltip' data-placement='bottom' title='合同重签'>"
                html += "<i class='glyphicon glyphicon-erase' ></i></button> ";
                html += "&nbsp;<button type='button' name='orderButtonsAllCont' id='a_hjend' class='btn btn-primary btn-xs' onclick='agreement.buyBack(" + orderId + ");' data-toggle='tooltip' data-placement='bottom' title='海金分期协议终止'>"
                html += "<i class='glyphicon glyphicon-off' ></i></button> ";
                html += "</div>";
                if (data.msg == "00") {
                    if (data.list.length > 0) {
                        $.each(data.list, function (i, v) {
                            html += "<div class='widget-content'>";
                            html += "<table class='table table-condensed'><tr><td><p style='width:70%;display:inline-block'>";
                            if (v.agreementState != 5) {
                                html += "<input type='radio' name='selectCheckBox' value='" + v.id + "'   checkStatus='" + v.checkStatus + "' onclick='authButton(" + v.agreementModel + "," + v.advancePeriod + "," + v.checkStatus + "," + v.agreementState + ")'    agreementModel='" + v.agreementModel + "' advancePeriod='" + v.advancePeriod + "' elecOtherState= '" + (v.elecOtherState != 0 ? v.elecOtherState : '') + "'>";
                            }
                            html += "合同编号：        " + v.agreementCode + " </p></td></tr><tr><td>";
                            html += "<input type='hidden' value=" + v.agreementState + " id='agreementState_" + v.id + "' />";
                            html += "<input type='hidden' value=" + v.valid + " id='valid_" + v.id + "' />";
                            html += "<input type='hidden' value=" + v.orderStatus + " id='orderStatus_" + v.id + "' />";//订单状态
                            html += "<input type='hidden' value=" + v.payStatus + " id='payStatus_" + v.id + "' />";//订单支付状态
                            html += "<input type='hidden' value=" + v.customerManageMoney + " id='customerManageMoney' />";//客户信息费
                            html += "<input type='hidden' value=" + v.checkStatusText + " id='checkStatusText' />";//审核状态
                            html += "<p style='width:30%;display:inline-block'>合同状态:";
                            if (v.agreementState == 1) {
                                html += "新增  </p>";
                            } else if (v.agreementState == 2) {
                                html += "已确认 </p>";
                            } else if (v.agreementState == 3) {
                                html += "已终止 </p>";
                            } else if (v.agreementState == 4) {
                                html += "已完成 </p>";
                            } else if (v.agreementState == 5) {
                                html += "已删除 </p>";
                            } else if (v.agreementState == 6) {
                                html += "签约中 </p>";
                            } else {
                                html += "</p></td></td></tr><tr><td>";
                            }
                            //电子签章其他状态     1,合同待签约   2,已推送  3,三方已签约  4, 已电子签章认证  5,签章已驳回
                            if (v.agreementModel == '20520001') {
                                html += "<p class='" + v.elecOtherState + "' style='width:30%;display:inline-block'>签约状态: ";
                                if (v.elecOtherState == 1) {
                                    html += "待签约 </p></td></tr><tr><td>";
                                } else if (v.elecOtherState == 2) {
                                    html += "已推送 </p></td></tr><tr><td>";
                                } else if (v.elecOtherState == 3) {
                                    html += "已签约 </p></td></tr><tr><td>";
                                } else if (v.elecOtherState == 4) {
                                    html += "已签章认证  </p></td></tr><tr><td>";
                                } else if (v.elecOtherState == 5) {
                                    html += "已驳回 </p></td></tr><tr><td>";
                                } else {
                                    html += "</p></td></td></tr><tr><td>";
                                }
                            } else {
                                html += "</td></tr><tr><td>";
                            }
                            html += "合同甲方：" + v.partyA + "</td></tr><tr><td>";
                            if (v.checkStatus == 3) {
                                //驳回状态,显示驳回说明
                                html += "审核状态：" + v.checkStatusText + "<div style='color:red'>" + v.checkInstructions + "</div></td></tr><tr><td>";
                            } else {
                                html += "审核状态：" + v.checkStatusText + "</td></tr><tr><td>";
                            }
                            html += "客户地址： " + v.customerAddress + "    </td></tr><tr><td>";
                            html += "合同乙方： " + v.partyB + "</td></tr><tr><td>";
                            /* html += "签约门店： "+v.serviceGarage+"</td></tr><tr><td>"; */
                            html += "合同丙方：  " + v.partyC + "</td></tr><tr><td>";
                            html += "服务场所：    " + v.serviceAddress + "</td></tr><tr><td>";
                            html += "服务地址：  " + v.platformAddress + "  </td></tr><tr><td>";
                            html += "起止时间：   " + numberToDatestr(v.effectDate, 8) + "  至：  " + numberToDatestr(v.finishDate, 8) + "   </td></tr><tr><td>";
                            html += "签订日期：   " + numberToDatestr(v.contractDate, 8) + "</td></tr><tr><td>";
                            html += "终止原因：   " + v.reason + "</td>";
                            html += "</tr></table></div><br/>";
                        })
                    } else {
                        html += "</td><td>";
                        html += "</td></tr></table></div>";
                        html += "<div class='widget-content'>";
                        html += "<table class='table table-condensed'><tr><td>";
                        html += "您还没有选择合同！";
                        html += "</td></tr></table></div>";
                    }
                } else {
                    html += "</td><td>";
                    html += "</td></tr></table></div>";
                    html += "<div class='widget-content'>";
                    html += "<table class='table table-condensed'><tr><td>";
                    html += "您还没有选择合同！";
                    html += "</td></tr></table></div>";
                }
                $("#agreements").html(html);
                setOrderButtonsAllCont();
            }
        })
    }

    // 新增结算
    function addAccountServer() {
    	/** 2018-11-14 周鑫   限定新增加缴费的时候限定时间不能超过合同的一个  **/
    	var orderId=$("#checkedOrderId").val();
		$.ajax({
	        url: ctx + "/order/queryAgreenmentDate",
	        data: {
	            orderId: orderId,
	        },
	        type: "POST",
	        async: false,
	        success: function (data) {
				//需要解开的	        	
	           if(data.msg=="02"){
	            	 $.alert({
	                     text: "与有效合同相差已经超过一个月"
	                 });
	            	return;
	            }  
	            orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), 0, parent.$("#checkedIscollection").val());
	        }
		});
		/** 结束  **/
    }
    // 修改结算
    function updateAccountServer() {
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
            if ($("#accountListBodyCont" + radioAccount).find("tr").length != 0) {
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
            loadAccountCreTime(radioAccount, "accountCreateTimeBasic");
            querySysdate("accountSystemDateBasic");
            var updateServerCreTime = $("#accountCreateTimeBasic").val();
            var updateServerSysdate = $("#accountSystemDateBasic").val();
            deleteAccountTimeRule(updateServerCreTime, updateServerSysdate);
            if (timeRuleFlag) {
                orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        } else {
            loadAccountCreTime(radioAccount, "accountCreateTimeBasic");
            querySysdate("accountSystemDateBasic");
            var updateServerCreTime = $("#accountCreateTimeBasic").val();
            var updateServerSysdate = $("#accountSystemDateBasic").val();
            deleteAccountTimeRule(updateServerCreTime, updateServerSysdate);
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
    function deleteAccountServer() {
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
                text: "当前结算单已对账，不能删除！"
            });
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "当前订单已取消，不能删除！"});
        } else if (orderStatus == 12) {
            $.alert({text: "当前订单已终止，不能删除！"});
        } else {
            loadAccountCreTime(radioAccount, "accountCreateTimeBasic");
            querySysdate("accountSystemDateBasic");
            var serverCreTime = $("#accountCreateTimeBasic").val();
            var serverSysdate = $("#accountSystemDateBasic").val();
            deleteAccountTimeRule(serverCreTime, serverSysdate);
            /* if(timeRuleFlag){  判断是否封账*/
            if (true) {
                $.confirm({
                    text: "是否确认删除结算？", callback: function (re) {
                        if (re) {
                            deleteAccountById(radioAccount);
                            queryAccount($("#checkedOrderId").val(), "accountListBodyCont", 2, $("#checkedTotalPay").val());
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
        var result=checkAccountReviewState(radioAccount,5);
        if(result!="00"){
        	 $.alert({
                 text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
             });
             return;
        }
        /** 周鑫 2018-12-27    **/
    }
    //查看预估
    function updateygAccount() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择要修改的结算单！"
            });
            return;
        }
        orderBasicYgAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
    }
    // 新增缴费
    function addPayfeeServer() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyCont" + radioAccount).find("tr").length > 0) {
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
    function updatePayfeeServer() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyCont" + radioAccount).find("tr").length == 0) {
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

        loadPayfeeMinCreTime(radioAccount, "accountCreateTimeBasic");
        querySysdate("accountSystemDateBasic");
        var serverMinCreTime = $("#accountCreateTimeBasic").val();
        var serverMinSysdate = $("#accountSystemDateBasic").val();
        deleteAccountTimeRule(serverMinCreTime, serverMinSysdate);
        /** 周鑫 2018-12-27  校验审核状态 是否是 已确认  **/
        var result=checkAccountReviewState(radioAccount,5);
        if(result!="00"){
        	 $.alert({
                 text: "缴费已确认，无法修改！如有疑问，请联系结算中心"
             });
             return;
        }
        /** 周鑫 2018-12-27    **/
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
    function addPayfeeWeiXinServer() {
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
    //新增回访记录
    function addreturnrecord() {
        var ctx = $("#ctx").val();
        var rechargeBY = <%=CookieUtils.getJSONKey(request, "id") %>
                RechargeBy = parent.$("#checkedRechargeBy").val()
        orderId = parent.$("#checkedOrderId").val()
        var customId = parent.$("#checkedUserId").val();//客户id
        if (rechargeBY == RechargeBy) {
            openModule(ctx + "/jsp/returnrecord/editReturn.jsp", {
                orderId: orderId,
                record: "",
                customId: customId
            }, {}, {}, "orderReturn");
        } else {
            $.alert({millis: 5000, text: "回访信息须由责任人新增"});
            return;
        }
    }
    //查询排期时间
    function getLineTime() {
        $("#orderLine").empty();
        $("#orderLineDay").empty();
        $("#benWorkWeek").html("");
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemDetailServer/loadOrderServerLined",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                id: orderId
            },
            success: function (data) {
                $("#orderLineDay").removeData("startTime").removeData("endTime");
                if (data.msg == "00") {
                    var startTime = numberToDatestr(data.itemDetailServer.startTime, 12);
                    var endTime = numberToDatestr(data.itemDetailServer.endTime, 12);
                    if (data.itemDetailServer.weeks == null || data.itemDetailServer.weeks == "") {
                        $("#orderLineDay").html(startTime + "------" + endTime).data({
                            "startTime": startTime,
                            "endTime": endTime
                        });
                        //  $("#order_person_Line").html(numberToDatestr(data.itemDetailServer.startTime,12)+"------"+numberToDatestr(data.itemDetailServer.endTime,12));
                    } else {
                        $("#orderLineDay").html(startTime + "------" + endTime).data({
                            "startTime": startTime,
                            "endTime": endTime
                        });
                        $("#orderLine").append("</br>"
                                + data.itemDetailServer.weeks + ":" + data.itemDetailServer.hours);
                        $("#benWorkWeek").append(data.itemDetailServer.weeks);
                        $("#benWorkHours").val(data.itemDetailServer.hours);
                    }
                }
            }
        });
    }
    //受理类型:1.未处理 2.已推送 3.已拒绝 4.已匹配 5.已调换 6.面试成功. 7.面试失败 8.上户 9.下户 10.已取消,11顶岗预备,12顶岗上户,13调岗下户
    function setOrderLined() {
        var his = $("#hisPersonLine").val()
        var right = $("#rightPersonLine").val();
        var personId = $("#personLinedIds").text();
        var personStatus = $("#personLinedStatuss").text();
        var orderStatus = $("#checkedOrderStatus").val();
        var startTime = $("#orderLineDay").data("startTime");//订单开始时间
        var endTime = $("#orderLineDay").data("endTime");//订单结束时间
        var linedType = $('#servserTextBasics').text();//服务人员类型
        var orderPersionLine = $("#order_person_Line").text();
        var count12 = 0;
        if (startTime && endTime) {
            var personCheck12 = $("input[name='personCheck'][type='radio']");
            for (var i = 0; i < personCheck12.length; i++) {
                var status12 = $(personCheck12[i]).attr("data-status");
                if (status12 == "9") {
                    count12++;
                }
            }
        }
        if (count12 > 0) {
            $.alert({text: "上过户的不可修改订单排期"});
            return;
        }
        if ((startTime == null || startTime == "") || (endTime == null || endTime == "")) {
            openModule('${ctx}/jsp/server/ServedLine.jsp', {
                type: $('#servserTextBasics').text(),
                personId: personId,
                personStatus: personStatus
            }, {}, {}, 'ServedLine')
        } else {
            //受理类型:1.未处理 2.已推送 3.已拒绝 4.已匹配 5.已调换 6.面试成功. 7.面试失败 8.上户 9.下户 10.已取消,11顶岗预备,12顶岗上户,13调岗下户
            if (orderStatus != 6 && orderStatus != 7 && orderStatus != 8 && orderStatus != 9 && orderStatus != 10 && orderStatus != 12) {
                openModule('${ctx}/jsp/server/ServedLine.jsp', {
                    type: $('#servserTextBasics').text(),
                    personId: personId,
                    personStatus: personStatus
                }, {}, {}, 'ServedLine')
            } else {
                $.alert({
                    text: "面试成功以后不能更换排期"
                });
            }
        }
        if (his != "" || right != "") {
        } else {
        }
    }
    //延长订单排期
    function delayOrderLined() {
        /* var Time = $("#orderLineDay").text();
         var startTime = Time.split("------")[0];//订单开始时间
         var endTime = Time.split("------")[1]; //订单结束时间 */
        var startTime = $("#orderLineDay").data("startTime");//订单开始时间
        var endTime = $("#orderLineDay").data("endTime");//订单结束时间
        var personId = parseInt($("#personLinedIds").text());//服务人员id
        var orderId = parseInt($("#checkedOrderId").val());//订单id
        var personStatus = $("#personLinedStatuss").text();
        var weeks = $("#benWorkWeek").text();
        var zhongHours = $("#benWorkHours").val();
        if (!startTime && !endTime) {
            $.alert({text: "排期不完整无法操作"});
            return;
        }
        if (personStatus == 8) {
            var zhong_param = {
                "startTime": startTime,
                "endTime": endTime,
                "personId": personId,
                "orderId": orderId,
                "weeks": weeks,
                "zhongHours": zhongHours
            };
            openModule('${ctx}/jsp/server/delayOrderLined.jsp', zhong_param, {}, {
                "width": 450,
                "height": 200
            }, 'delayOrderLined')
        } else {
            $.alert({text: "上户之后可以延长订单排期！"});
        }
    }

    //延长服务人员排期
    function delayPersonLined() {
        var startTime = $("#orderLineDay").data("startTime");//服务人员开始时间
        var endTime = $("#orderLineDay").data("endTime");//服务人员结束时间
        var personId = parseInt($("#personLinedIds").text());
        var personStatus = $("#personLinedStatuss").text();
        var orderId = parent.$("#checkedOrderId").val();
        var checkStatus = delayLinedType(orderId);
        if (!startTime || !endTime) {
            $.alert({text: "排期不完整无法操作"});
            return;
        }
        if (checkStatus == 1) {
            $.alert({text: "已有正在等待审核的申请"});
            return;
        }
        if (personStatus == 8) {
            var endTimes = endTime.split(" ")[0].replace(new RegExp("-", "gm"), "");
            var startTimes = startTime.split(" ")[0].replace(new RegExp("-", "gm"), "");
            $.ajax({
                url: ctx + "/order/queryByConflict",
                type: "post",
                dataType: "json",
                async: false,
                data: {
                    orderId: orderId,
                    endTime: endTimes,
                    personId: personId,
                    starTime: startTimes
                },
                success: function (data) {
                    if (data.msg == "00") {
                        openModule('${ctx}/jsp/server/delayPersonLined.jsp', {
                            "startTime": startTime,
                            "endTime": endTime,
                            "personId": personId,
                            "personStatus": personStatus
                        }, {}, {"width": 450, "height": 200}, 'delayPersonLined')
                    } else if (data.msg == "02") {
                        $.alert({text: "没有冲突！"});
                    }
                }
            });
        } else {
            $.alert({text: "上户之后可以延长服务人员的排期！"});
        }
    }
    /**
     * 查询审核状态
     */
    function delayLinedType(orderId) {
        var status = "";
        $.ajax({
            url: ctx + "/scheduleCheck/queryDelayLinedType",
            type: "post",
            dataType: "json",
            async: false,
            data: {
                orderId: orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    status = 1;
                } else if (data.msg == "02") {
                    status = 2;
                }
            }
        });
        return status;
    }
    //取消申请
    function cancelDelayLined() {
        $("#checkStatus").empty();
        var id = $("#dealyId").val();
        $.ajax({
            url: ctx + "/delay/audit",
            data: {
                id: id,
                checkStatus: 4
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.code == "0") {
                    delayApplyLined();
                } else if (data.code == "-1") {
                    $.alert({text: data.msg});
                } else if (data.code == "-2") {
                    $.alert({text: "请稍后再试"});
                }
            }
        });
    }

    //审核延长申请
    function auditDelayLinedApply() {
        var startTime = $("#orderLineDay").data("startTime");//订单开始时间
        var endTime = $("#orderLineDay").data("endTime");//订单结束时间
        var orderId = parent.$("#checkedOrderId").val();
        if (!startTime || !endTime) {
            $.alert({text: "排期不完整无法操作"});
            return;
        }
        $.ajax({
            url: ctx + "/scheduleCheck/queryScheduleCheck",
            data: {
                clashOrderId: orderId
            },
            type: 'post',
            dataType: 'json',
            success: function (data) {
                if (data.msg == "00") {
                    openModule(ctx + "/jsp/server/auditDelayLinedApply.jsp", {}, function () {
                        $.each(data.list, function (i, v) {
                            $("#auditId").val(v.id);
                            $("#order_code").text(v.orderCode);
                            $("#applyHouseKeeper").text(v.managerName);
                            $("#managerPhone").text(v.managerPhone);
                            $("#orgName").text(v.orgName);
                            $("#applyDelayS").text(v.detailStartTime);
                            $("#applyDelayE").text(v.detailEndTime);
                            $("#orderPersonLinedS").text(v.startTime);
                            $("#orderPersonLinedE").text(v.endTime);
                        })
                    });
                } else if (data.msg == "02") {
                    $.alert({text: "当前没有需要审核的申请"});
                } else if (data.msg == "01") {
                    $.alert({text: "请稍后再试"});
                }
            }
        });
    }
    //新增嘉联二维码支付
    function addPayfeeJiaLianServer() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        var payStatus = $("#payStatusAccount" + radioAccount).val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择结算单！"
            });
            return;
        }
        if ($("#accountListBodyCont" + radioAccount).find("tr").length > 0) {
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

    //查询服务人员是否上下户
    //有服务人员上下户，将ID放入隐藏域
    function checkServerPersonType(orderId) {
        $("#empIdHide" + orderId).val("");
        var arr1 = [];
        var arr2 = [];
        $("input[name='empIdsHidden']").each(function () {
            var empStr = $(this).val();
            if (empStr != null && empStr != "") {
                var empInfo = empStr.split("_");
                arr1.push(empInfo[0]);
                arr2.push(empInfo[1]);
            }
        });
        if (arr1.length != 0) {
            var onType = $.inArray("8", arr1);
            if (onType == 0) {
                $.each(arr1, function (i, num) {
                    if (num == 8)  $("#empIdHide" + orderId).val(arr2[i]);
                });
            } else if (onType == -1) {
                $("#empIdHide" + orderId).val(arr2[0]);
            }
        } else {
            $("#empIdHide" + orderId).val("");
        }
    }

    //查询服务人员排期
    function getPersonLineTime() {
        $("#orderPersonLine").empty();
        $("#order_person_Line").empty();
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var personId = parseInt($("#personLinedIds").text());
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus != 8) {
            $("#personLineDiv").hide();
            return;
        }
        var startTimeShanghu = $("#startTimeShanghu").val();//上户时间
        $.ajax({
            url: ctx + "/itemDetailServer/loadPersonServerLined",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                orderId: orderId,
                emp_id: personId,
                startDate: startTimeShanghu.substring(0, 10).replace(new RegExp("-", "gm"), "")
            },
            success: function (data) {
                if (data.msg == "00") {
                    $("#personLineDiv").show();
                    $.each(data.list, function (i, v) {
                        $("#order_person_Line").text(v.startDate + "------" + v.endDate);
                        $("#startDate").text(v.startDate);
                        $("#endDate").text(v.endDate);
                    })
                } else if (data.msg == "02") {
                    $("#personLineDiv").hide();
                }
            }
        });
    }
    //查询延长服务申请排期
    function delayApplyLined() {
        var orderId = parent.$("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/scheduleCheck/queryDelayLined",
            data: {
                orderId: orderId
            },
            dataType: "json",
            type: "post",
            success: function (data) {
                if (data.msg == "00") {
                    $("#dealyOrderLineDay").show();
                    $.each(data.list, function (i, v) {
                        $("#dealyId").val(v.id).data("checkStatus", v.checkStatus);
                        $("#dealyOrderLine").text(v.startTime + "------" + v.endTime);
                        $("#checkStatus").val(v.checkStatus)
                    });
                } else if (data.msg == "02") {
                    $("#dealyOrderLineDay").hide();
                } else if (data.msg == "01") {

                }
            }

        });
    }
</script>
<script type="text/javascript">
    /* 1.顶岗推送;2.调岗下户;3.返岗上户 */
    /*跳转顶岗推送 */
    function toReplaceJobsPush() {
        var orderStartTime = $("#orderLineDay").data("startTime");//订单开始时间
        var orderEndTime = $("#orderLineDay").data("endTime");//订单结束时间

        if (!orderStartTime || !orderEndTime) {
            $.alert({millis: 5000, text: "排期不完整无法操作"});
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == "10") {
            $.alert({text: "当前订单已经取消，无法匹配人员！"});
            return;
        }
        if (orderStatus == "12") {
            $.alert({text: "当前订单已经终止，无法匹配人员！"});
            return;
        }
        var flag = selLine_rjp_server();
        if (!flag) {
            return;
        }
        var personCheck8 = $("input[name='personCheck'][type='radio']");
        var count = 0;
        for (var i = 0; i < personCheck8.length; i++) {
            var status8 = $(personCheck8[i]).attr("data-status");
            if (status8 == "8") {
                count++;
            }
        }
        if (count == 0) {
            $.alert({millis: 5000, text: "当前没有上户中的服务人员,不能顶岗推送！"});
            return;
        } else {
            var num = checkReplaceJobsPush();
            if (num >= 1) {
                $.alert({millis: 5000, text: "当前订单已经完成顶岗推送,无法匹配人员！"});
                return;
            }
        }
        var url = "${ctx }/jsp/server/replaceJobsPush.jsp";
        var orderId = $("#checkedOrderId").val();
        var cityCode = $("#checkedCity").val();
        var orderType = $("#serverOrderType").val();
        var data = {"orderId": orderId, "cateType": 2, "cityCode": cityCode, "orderType": orderType}
        openModule(url, data, null, {width: "65%"}, 'orderNeedsModule');
    }

    /*调岗下户 */
    function changingJobsDown() {
        var orderStartTime = $("#orderLineDay").data("startTime");//订单开始时间
        var orderEndTime = $("#orderLineDay").data("endTime");//订单结束时间
        if (!orderStartTime || !orderEndTime) {
            $.alert({millis: 5000, text: "排期不完整无法操作"});
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == "10") {
            $.alert({millis: 5000, text: "订单已取消,无法调岗下户！"});
            return;
        } else if (orderStatus == "12") {
            $.alert({millis: 5000, text: "订单已终止,无法调岗下户！"});
            return;
        }
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var personId = personCheck.attr("person-id");
        var status = personCheck.attr("data-status");
        var inwid = personCheck.val();
        if (personCheck.length == 0) {
            $.alert({millis: 5000, text: "请选择服务人员！"});
            return;
        }
        var time = "";
        if (status == "8") {
            var personCheck11 = $("input[name='personCheck'][type='radio']");
            var count = 0;
            for (var i = 0; i < personCheck11.length; i++) {
                var status11 = $(personCheck11[i]).attr("data-status");
                if (status11 == "11") {
                    var arr = $(personCheck11[i]).parent().parent().siblings();
                    time = $(arr[arr.size() - 2]).find("td").eq(1).html().split("：")[1];
                    count++;
                }
            }
            if (count == 0) {
                //无顶岗预备人员
                $.alert({millis: 5000, text: "请先确定顶岗服务人员!"});
                return;
            }
        } else {
            $.alert({millis: 5000, text: "仅上户的服务人员可操作调岗下户！"});
            return;
        }
        //请先确定顶岗服务人员
        var url = "${ctx}/jsp/server/interviewDownAndUp.jsp";
        var orderId = $("#checkedOrderId").val();
        var data = {"personId": personId, "time": time, "inwid": inwid, "orderId": orderId, "interviewType": 9,};
        openModule(url, data);
    }

    /*返岗上户 */
    function retuningJobsUp() {
        var orderStartTime = $("#orderLineDay").data("startTime");//订单开始时间
        var orderEndTime = $("#orderLineDay").data("endTime");//订单结束时间
        if (!orderStartTime || !orderEndTime) {
            $.alert({millis: 5000, text: "排期不完整无法操作"});
            return;
        }
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == "10") {
            $.alert({millis: 5000, text: "订单已取消,无法返岗上户！"});
            return;
        } else if (orderStatus == "12") {
            $.alert({millis: 5000, text: "订单已终止,无法返岗上户！"});
            return;
        }
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var personId = personCheck.attr("person-id");
        var orderId = $("#checkedOrderId").val();
        var id = personCheck.val();
        var endTime = checkRetuningJobsUp(orderId, id);
        var status = personCheck.attr("data-status");
        if (personCheck.length == 0) {
            $.alert({millis: 5000, text: "请选择服务人员！"});
            return;
        }
        if (status == "13") {
            var personCheck12 = $("input[name='personCheck'][type='radio']");
            var count12 = 0, count11 = 0;
            for (var i = 0; i < personCheck12.length; i++) {
                var status12 = $(personCheck12[i]).attr("data-status");
                if (status12 == "12") {
                    count12++;
                }
                if (status12 == "11") {
                    count11++;
                }
            }
            if (count11 > 0) {
                $.alert({millis: 5000, text: "顶岗服务人员还未上户!"});
                return;
            }
            if (count12 > 0) {
                $.alert({millis: 5000, text: "请先下户顶岗服务人员!"});
                return;
            }
        } else {
            $.alert({millis: 5000, text: "仅调岗下户的服务人员可返岗上户"});
            return;
        }
        var minDateStr = "";
        if (endTime) {
            var minDate = new Date(endTime);
            minDate.setDate(minDate.getDate() + 1);
            minDateStr = minDate.toLocaleDateString();
        }
        var url = "${ctx}/jsp/server/interviewDownAndUp.jsp";
        var data = {
            "personId": personId,
            "orderId": orderId,
            "interviewType": 8,
            "minDate": minDateStr || new Date().toLocaleDateString()
        };
        openModule(url, data);
    }

    /**验证是否己完成顶岗推送*/
    function checkReplaceJobsPush() {
        var orderId = $("#checkedOrderId").val();
        var count = 0;
        $.ajax({
            url: "${ctx}/itemInterview/queryNeedPersons",
            data: {
                orderId: orderId,
                type: '11'
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    count = data.list.length;
                }
            }
        })
        return count;
    }
    /**查看订单排期*/
    function selLine_rjp_server() {
        var flag = false;
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: "${ctx}/itemDetailServer/loadOrderServerLined",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                id: orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    var v = data.itemDetailServer;
                    if (!v.startTime || !v.endTime) {
                        $.alert({millis: 5000, text: "当前订单排期不完整，请完善排期！"});
                        return false;
                    }
                    var end = Date.parse(v.endTime.split(" ")[0].replace(/-/g, '/'));
                    var today = Date.parse(new Date().toLocaleDateString());
                    if (end < today) {
                        $.alert({millis: 5000, text: "订单排期结束日期小于当前日期，无法顶岗推送！"});
                        return false;
                    }
                    if (v.linedType == 2) {
                        if (!v.hours) {
                            $.alert({millis: 5000, text: "当前订单排期不完整，请完善排期！"});
                            return false;
                        }
                    }
                    flag = true;
                } else if (data.msg == "02") {
                    $.alert({millis: 5000, text: "请生成订单排期!"});
                    return false;
                }
            }
        });
        return flag;
    }
    /**查询上户记录*/
    function queryInterviews(options) {
        var list = null;
        $.ajax({
            url: "${ctx}/itemInterview/queryInterviews",
            type: "post",
            datetype: "json",
            async: false,
            data: options,
            success: function (data) {
                if (data.msg == "00") {
                    list = data.list;
                }
            }
        });
        return list;
    }

    /**返岗上户时查询最小可上户时间*/
    function checkRetuningJobsUp(orderId, id) {
        var endTime = "";
        $.ajax({
            url: "${ctx}/itemInterview/checkRetuningJobsUp",
            type: "post",
            datetype: "json",
            async: false,
            data: {"orderId": orderId, "id": id},
            success: function (data) {
                if (data.msg == "00") {
                    endTime = data.result.endTime || "";
                }
            }
        });
        return endTime;
    }
    //查询服务人员排期
    function queryPersonLineTime() {
        var flag = false;
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemDetailServer/queryPersonLineTime",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                orderId: orderId,
            },
            success: function (data) {
                if (data.msg == "00") {
                    var list = data.list;
                    if (list.length > 0) {
                        flag = true;
                    }
                }
            }
        });
        return flag;
    }

    //提前发服务费按钮
    function updateSalaryStatus() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.confirm({
            text: "是否确认切换发服务人员服务费方式？", callback: function (re) {
                if (re) {
                    $.ajax({
                        url: ctx + "/order/updateSalaryStatus",
                        type: "post",
                        datetype: "json",
                        async: false,
                        data: {
                            id: orderId,
                        },
                        success: function (data) {
                            if (data.msg == "00") {
                                getServerBasics(orderId);
                                $.alert({millis: 5000, text: "切换服务人员服务费方式成功!"});
                            } else {
                                $.alert({millis: 5000, text: "切换服务人员服务费方式失败!"});
                            }
                        }
                    });
                }
            }
        });
    }


    //查询服务人员排期是否冲突
    function selectConflictSchedule() {
        var flag = false;
        var ctx = $("#ctx").val();
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var personId = personCheck.attr("person-id");
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemInterview/selectConflictSchedule",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                orderId: orderId,
                personId: personId
            },
            success: function (data) {
                var v = data.data;
                if (data.msg == "00") {
                    flag = true;
                } else if (data.msg == "02") {
                    if (v.msg) {
                        $.alert({text: "服务人员" + v.name + "排期不匹配,不能推送！ 冲突订单信息：" + v.msg});
                    }
                    flag = false;
                } else {
                    $.alert({text: "操作失败"});
                }
            }
        });
        return flag;
    }

    //联动扫码支付   1延续,2商品,3单次,4三方
    function addPayfeeLianDongServer(type) {
        if (type == 1) {
            var radioAccount = $('input:radio[name=radioAccount]:checked').val();
            var payStatus = $("#payStatusAccount" + radioAccount).val();
            var feePostPayfee = $("#accountListBodyCont" + radioAccount).find("input[name=feePostPayfee]").val();
            var money = $("#accountListBodyCont" + radioAccount).find("input[name=money]").val();
            var orderPayFeeId = $("#accountListBodyCont" + radioAccount).find("input[name=orderPayFeeId]").val();
            if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
                $.alert({
                    text: "请选择结算单！"
                });
                return;
            }

            if ($("#accountListBodyCont" + radioAccount).find("tr").length == 0) {
                $.alert({
                    text: "当前结算单没有生成缴费信息，不能生成二维码！"
                });
                return;
            }

            if (feePostPayfee != '20250042' && feePostPayfee != '20250043') {
                $.alert({
                    text: "当前缴费类型，不能生成二维码！"
                });
                return;
            }
        } else if (type == 2) {
            var radioAccount = $('input:radio[name=radioAccount]:checked').val();
            var payStatus = $("#payStatusAccount" + radioAccount).val();
            var feePostPayfee = $("#accountListBodyItem" + radioAccount).find("input[name=feePostPayfee]").val();
            var money = $("#accountListBodyItem" + radioAccount).find("input[name=money]").val();
            var orderPayFeeId = $("#accountListBodyItem" + radioAccount).find("input[name=orderPayFeeId]").val();
            if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
                $.alert({
                    text: "请选择结算单！"
                });
                return;
            }

            if ($("#accountListBodyItem" + radioAccount).find("tr").length == 0) {
                $.alert({
                    text: "当前结算单没有生成缴费信息，不能生成二维码！"
                });
                return;
            }

            if (feePostPayfee != '20250042' && feePostPayfee != '20250043') {
                $.alert({
                    text: "当前缴费类型，不能生成二维码！"
                });
                return;
            }
        } else if (type == 3) {
            var radioAccount = $('input:radio[name=radioAccount]:checked').val();
            var payStatus = $("#payStatusAccount" + radioAccount).val();
            var feePostPayfee = $("#accountListBodyOne" + radioAccount).find("input[name=feePostPayfee]").val();
            var money = $("#accountListBodyOne" + radioAccount).find("input[name=money]").val();
            var orderPayFeeId = $("#accountListBodyOne" + radioAccount).find("input[name=orderPayFeeId]").val();
            if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
                $.alert({
                    text: "请选择结算单！"
                });
                return;
            }

            if ($("#accountListBodyOne" + radioAccount).find("tr").length == 0) {
                $.alert({
                    text: "当前结算单没有生成缴费信息，不能生成二维码！"
                });
                return;
            }

            if (feePostPayfee != '20250042' && feePostPayfee != '20250043') {
                $.alert({
                    text: "当前缴费类型，不能生成二维码！"
                });
                return;
            }
        } else if (type == 4) {
            var radioAccount = $('input:radio[name=radioAccount]:checked').val();
            var payStatus = $("#payStatusAccount" + radioAccount).val();
            var feePostPayfee = $("#accountListBodyThree" + radioAccount).find("input[name=feePostPayfee]").val();
            var money = $("#accountListBodyThree" + radioAccount).find("input[name=money]").val();
            var orderPayFeeId = $("#accountListBodyThree" + radioAccount).find("input[name=orderPayFeeId]").val();
            if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
                $.alert({
                    text: "请选择结算单！"
                });
                return;
            }

            if ($("#accountListBodyThree" + radioAccount).find("tr").length == 0) {
                $.alert({
                    text: "当前结算单没有生成缴费信息，不能生成二维码！"
                });
                return;
            }

            if (feePostPayfee != '20250042' && feePostPayfee != '20250043') {
                $.alert({
                    text: "当前缴费类型，不能生成二维码！"
                });
                return;
            }
        } else {
            $.alert({
                text: "请重新选择！"
            });
            return;
        }
        var message = "<h4>各位管家好:<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联动二维码扫码支付完成后<span style='color:red'><strong>不需要进行任何操作，系统自动对账!</strong></span></h4>";
        $.alert({
            text: message,
            callback: function (re) {
                if (re) {
                    LianDongQRCode(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), money, orderPayFeeId, payStatus, feePostPayfee);
                }
            }
        });
    }


    function selectConflictSchedule() {
        var flag = false;
        var ctx = $("#ctx").val();
        var personCheck = $("input[name='personCheck'][type='radio']:checked");
        var personId = personCheck.attr("person-id");
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemInterview/selectConflictSchedule",
            type: "post",
            datetype: "json",
            async: false,
            data: {
                orderId: orderId,
                personId: personId
            },
            success: function (data) {
                var v = data.data;
                if (data.msg == "00") {
                    flag = true;
                } else if (data.msg == "02") {
                    if (v.msg) {
                        $.alert({text: "服务人员" + v.name + "排期不匹配,不能推送！ 冲突订单信息：" + v.msg});
                    }
                    flag = false;
                } else {
                    $.alert({text: "操作失败"});
                }
            }
        });
        return flag;
    }

    /**
     *确定人员匹配时校验缴费是否大于1000
     *20190213取消此限制
     */
    function checkAccountTotal() {
        var flag = false;
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/payfee/checkAccountTotal",
            async: false,
            type: "post",
            data: {orderId: orderId},
            success: function (data) {
                if (data.msg == "01") {//总计金额小于1000
                    $.alert({text: "该订单合计缴费金额不足1000元,无法确认匹配!"});
                } else if (data.msg == "00") {//总计金额大于1000
                    flag = true;
                } else {
                    $.alert({text: "查询缴费金额失败!"});
                }
            }
        });
        return flag;
    }
</script>
</html>