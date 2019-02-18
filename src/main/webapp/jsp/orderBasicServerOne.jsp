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

        .tabOrder tr td {
            text-align: left;
        }

        #widget {
            padding: 0 4%;
            height: auto;
            overflow: hidden;
        }

        .clearfix:after {
            content: ".";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden
        }

        .clearfix {
            *+height: 1%;
        }
    </style>
</head>
<body>
<input name="orderId" id="orderIdBasicOne" type="hidden">
<input name="orderCateTypeBasic" id="orderCateTypeBasicOne" type="hidden">
<div class="mytabs-wrap">
    <ul class="mytabs">
        <li class="tab-item tab-active" id="serverBasicOne1" onclick="serverBasicOne(1);">基本信息</li>
        <li class="tab-item" id="serverBasicOne7" onclick="serverBasicOne(7);">服务人员</li>
        <li class="tab-item" id="serverBasicOne8" onclick="serverBasicOne(8);" style="display: none;">三方协议</li>
        <li class="tab-item" id="serverBasicOne3" onclick="serverBasicOne(3);">缴费</li>
        <li class="tab-item" id="serverBasicOne5" onclick="serverBasicOne(5);">回访记录</li>
        <li class="tab-item" id="serverBasicOne4" onclick="serverBasicOne(4);">转单记录</li>
        <li class="tab-item" id="serverBasicOne6" onclick="serverBasicOne(6);">用户评价</li>
    </ul>
</div>
<div id="arr">
    <span id="left"><</span><span id="right">></span>
</div>
<div class="tab-content">
    <div id="basicServerOne" class="tab-selected">
        <div class="widget" id="setBasicServerOneEditButton" style="hieght:30px; display:none;">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
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
            <input type="hidden" id="serverTypeBasicOne" name="serverTypeBasicOne"/>
            <input type="hidden" id="personNumberBasicOne" name="personNumberBasicOne"/>
            <input type="hidden" id="personNumberWrokBasicOne" name="personNumberWrokBasicOne"/>
            <input type="hidden" id="accountCreateTimeBasicOne" name="accountCreateTimeBasicOne"/>
            <input type="hidden" id="accountSystemDateBasicOne" name="accountSystemDateBasicOne"/>
            <input type="hidden" id="orderCityCode"/>
            <input type="hidden" id="productCode"/>
            <input type="hidden" id="hisPersonLineOne"/>
            <input type="hidden" id="rightPersonLineOne"/>
            <span style="display:none;" id="inwxiaID"></span>
            <span style="display:none;" id="personLinedId"></span>
            <span style="display:none;" id="personLinedStatus"></span>
            <input type="hidden" id="once_server_agreement_id" value="">
            <input type="hidden" id="once_server_agreement_check_status" value="">
            <!--  <span    id=interviewType></span> -->
            <input id="personPP" type="hidden">

            <div class="row">
                <div class="col-xs-12">
                    <table class="table table-condensed">
                        <tr>
                            <td width="55%">
                                订单编号：<span id="orderCodeBasicOne"></span>
                            </td>
                            <td width="45%">
                                服务类型：<span id="servserTextBasicOne"></span>
                            </td>
                        </tr>
                        <tr>
                            <td width="45%" colspan="2">创建时间：<span id="createTimesServerOne"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                订单省份：<span id="receiverProvinceBasicOne"></span>
                            </td>
                            <td>
                                订单城市：<span id="receiverCityBasicOne"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                订单状态：<span id="statusTextBasicOne"></span>
                            </td>
                            <td>
                                支付状态：<span id="payTextServerOne"></span>
                            </td>
                        </tr>
                        <% if (!CookieUtils.getJSONKey(request, "managerType").equals("2")) { %>
                        <tr>
                            <td>
                                订单来源：<span id="sourceTextBasicOne"></span>
                            </td>
                            <%} %>
                            <td>
                                订单渠道：<span id="channelTextBasicOne"></span>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                带单人员：<span id="person"></span>
                            </td>
                            <td>
                                带单人电话：<span id="personmobile"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                排期时间：<span id="startTimeBasicOne"></span><br>
                                联系人:<span id="linkManOne"></span><br>
                                联系电话:<span id="linkMobleOne"></span><br>
                            </td>
                                
                            
                        </tr>
                        
                     
                     
                        
                        <tr>
                            <td colspan="2">
                                服务地址：<span id="addressBasicOne"></span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">订单备注：<span id="remarkBasicOne"></span></td>
                        </tr>
                        <tr>
                            <td colspan="2">服务面积：<span id="ForestsServer"></span></td>
                        </tr>
                        <tr>
                            <td colspan="2">方案编号：<span id="basicOneSolutionCode"></span></td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                是否投保：<span id="insureOne"></span>
                            </td>
                        </tr>
                    </table>
                </div>
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
                            客户姓名：<span id="nameBasicOne"></span>
                        </td>
                        <td>
                            <button type="button" class="btn btn-sm btn-default fr mr20"
                                    onclick="javascript:parent.editUserHref();">完善客户资料
                            </button>
                        </td>

                    </tr>
                    <tr>
                        <td width="50%">
                            客户电话：<span id="mobileBasicOne"></span>
                        </td>
                        <td width="50%">
                            性别：<span id="sexBasicOne"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            家庭地址：<span id="userAddressBasicOne"></span>
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
                            <tbody id="orderInforServerOne">
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
                            性别要求：<span id="xqSexTextBasicsOne"></span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div id="personInfoOne" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="setOrderLinedOneNew()">
                <i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="新增">订单排期</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="project-order-news">
                <div class="row">
                    <div class="col-xs-12"><h4>服务排期</h4></div>
                    <div class="col-xs-12">
                        <h5>订单排期</h5>
                        <div id="orderLineOne"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="widget clearfix" id="widget">
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="openOrderNeedsOne();">
                            <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="推送">推送</i>
                        </button>
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="setPersonStartOne(8)">
                            <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="上户">上户</i></button>
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="setPersonStartOne(9)">
                            <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="下户">下户</i></button>
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="setPersonStartOne(11)">
                            <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="完成">完成</i>
                        </button>
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="setPersonLinedOne()">
                            <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                               title="查看服务人员排期">查看服务人员排期</i></button>
                        <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                                onclick="cancelPersonOne()">
                            <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="取消">取消</i></button>
                        <button type="button" name="orderButtonSendpies" class="btn btn-primary btn-xs"
                                onclick="setPersonStartOne(10)">
                            <i class="glyphicon" data-toggle="tooltip" data-placement="top" title="发送派工单">发送派工单</i>
                        </button>
                    </div>
                    <div class="col-xs-12"><h4>当前服务人员(需要<span id="personNumberBasicOneShow"></span>人 )</h4></div>
                    <div id="rightNowPersonOne">
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12"><h4>历史服务人员</h4></div>
                    <div id="historyPersonOne">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="personServerOne" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <input type="hidden" id="checkedOneNeedsIds">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="openOrderNeedsOne();">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="手动匹配">手动匹配</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="row">
                <div class="col-xs-12">
                    <h4>匹配人员信息</h4>
                </div>
            </div>
            <div class="widget">
                <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                        onclick="setPersonStartOne(8)">
                    <i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="上户">上户</i>
                </button>
                <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                        onclick="setPersonStartOne(9)">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="下户">下户</i>
                </button>
                <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                        onclick="setPersonStartOne(11)">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="完成">完成</i>
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-12">
                <div id="needsPersonalsOne">
                </div>
            </div>
        </div>
    </div>
    <div id="feesServerOne" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="addAccountServerOne();">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i>
            </button>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="updateAccountServerOne();" id="updateAccountServerOneBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i>
            </button>
            <!-- 删除结算单，结算中心特殊权限 -->
            <auth:menu menuId="1150">
                <button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServerOne();"
                        id="deleteAccountServerOneBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="删除结算">删除结算</i></button>
            </auth:menu>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="updateygAccountServerOne();" id="updateygAccountServerOneBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="查看预估">查看预估</i>
            </button>
            <!-- 查看预估，结算中心特殊权限 -->
            <auth:menu menuId="1033">
                <button type="button" class="btn btn-primary btn-xs" onclick="updateygAccountServerOne();"
                        id="updateygAccountServerOneOtherBtn">
                    <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top"
                       title="查看预估">查看预估</i></button>
            </auth:menu>
        </div>
        <div class="widget">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="addPayfeeServerOne();">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i>
            </button>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="updatePayfeeServerOne()" id="updatePayfeeServerOneBtn">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i>
            </button>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="addPayfeeWeiXinServerOne()" id="addPayfeeWeiXinServerOneBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="微信扫码支付">微信扫码支付</i>
            </button>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="addPayfeeJiaLianServerOne()" id="addPayfeeJiaLianServerOneBtn">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="嘉联二维码">嘉联二维码</i>
            </button>
            <button type="button" name="orderButtonsAllCont" class="btn btn-primary btn-xs"
                    onclick="addPayfeeLianDongServer(3)" id="">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="联动二维码">联动二维码</i>
            </button>
        </div>
        <div class="widget-content">
            <div class="project-order-news">
                <div class="row">
                    <div class="col-xs-12"><h4>缴费信息</h4></div>
                </div>
                <div id="accountListBodyOne">
                </div>
            </div>
        </div>
    </div>
    <div id="ReturnRecordinfo" style="display:none; border:1px solid #CCC;">
        <div class="widget">
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs" onclick="addreturnrecord();">
                <i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增">新增</i>
            </button>
            <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs"
                    onclick="updatereturnrecord();">
                <i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i>
            </button>
            <!-- <button type="button" name="orderButtonsAllOne" class="btn btn-primary btn-xs" onclick="addreturnrecord();">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除">删除</i></button> -->
        </div>
        <div class="widget-content">
            <div class="project-order-news">
                <div class="row">
                    <div class="col-xs-12"><h4>回访信息</h4></div>
                </div>
                <div id="returnInfoOne">
                </div>
            </div>
        </div>
    </div>
    <div id="turnOrderLogOne" style="display:none; border:1px solid #CCC;">
        <div class="panel-content table-responsive widget-content">
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
                <tbody id="turnOrderLogListOne">

                </tbody>
            </table>
        </div>
    </div>
    <div id="appraiseOrderServerOne" style="display:none; border:1px solid #CCC;">
        <div class="panel-content table-responsive widget-content">
            <div style="height: 800px; overflow: auto;">
                <div id="appraiseServerOneList">
                </div>
                <div id="appraiseReplyServerOneList">
                </div>
                <div class='dotted'></div>
                <div style="width:500px;height:30px;overflow:hidden;margin: 0 0 30px auto;">
                    <ul class='pagination pagination-sm navbar-right' style="margin:0;"
                        id='appraiseServerOnePageDiv'></ul>
                </div>
            </div>
        </div>
    </div>
    <%--服务对象为企业的单次服务订单，增加“三方协议”窗口 add 20180110 zhanghao--%>
    <div id="agreementServerOne" style="display:none; border:1px solid #CCC;">
        <div class="row">
            <div class="col-xs-12">
                <table class="table table-condensed">
                    <tr>
                        <td></td>
                        <td style="text-align: right">
                            <button type="button" class="btn btn-default btn-sm" title="新增" onclick="addAgreementOne();">新增</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-default btn-sm" title="修改" onclick="updateAgreementOne();">修改</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button id="delete_once_agreement" type="button" class="btn btn-default btn-sm" title="删除" onclick="deleteAgreementOne();">删除</button>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <strong>
                                <h4>
                                    合同信息
                                </h4>
                            </strong>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            公司名称:<span id="partyA"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            营业执照:<span id="cardNum"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            合同号:<span id="agreementCode"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            钉钉审批单号:<span id="dingTalkAuditCode"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            合同金额:<span id="allPay"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            合同开始时间:<span id="effectDate"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            合同结束时间:<span id="finishDate"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>
                                扫描合同:<span id="agreementCopy"></span>
                            </strong>
                        </td>
                        <td style="text-align: center">
                            <input type="hidden" name="returnURL" id="returnURL">
                            <button class="btn btn-info btn-xs" type="button"  name="attachmentUpload" id="attachmentUpload" onclick="uploadOnce(this)" data-complete-text="重新上传">上传</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-info btn-xs" type="button"  name="attachmentPreview" id="attachmentPreview" onclick="previewOnce(this)" >预览</button>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button class="btn btn-info btn-xs" type="button"  name="attachmentDownload" id="attachmentDownload" onclick="downloadOnce(this)">下载</button>
                        </td>
                    </tr>
                </table>


                <table class="table table-condensed">
                    <tr>
                        <td colspan="2">
                            <strong>
                                <h4>
                                    审核信息
                                </h4>
                            </strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            审核状态:
                            <strong>
                                <span id="auditStatus"></span>
                            </strong>
                        </td>
                        <td style="text-align: center">
                            <auth:menu menuId="1364">
                                <button class="btn btn-info btn-xs" type="button"  name="auditPass" id="auditPass" onclick="auditPass()" >审核通过</button>
                            </auth:menu>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <auth:menu menuId="1365">
                                <button class="btn btn-info btn-xs" type="button"  name="turnDown" id="turnDown" onclick="onceAgreementTurnDown()">驳回</button>
                            </auth:menu>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            驳回原因:<span id="turnDownReason"></span>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function setBasicServerOne(orderId, cateType, totalPay, orderStatus, rechargeBy, personNumber) {
        $("#orderIdBasicOne").val(orderId);
        $("#orderIdBasicOne").val(orderId);
        $("#persopnNumber").text(personNumber)
        $("#orderCateTypeBasicOne").val(cateType);
        $("#productCode").val("")
        if (orderStatus == 1 || orderStatus == 2 || orderStatus == 3) {
            $("#setBasicServerOneEditButton").css("display", "block");
        } else {
            $("#setBasicServerOneEditButton").css("display", "none");
        }
        getServerBasicOne(orderId);
        $("input:radio[name=radioAccount]").each(function () {
            $(this).val("");
        });
        queryAccount(orderId, "accountListBodyOne", cateType, totalPay);
        queryReturn(orderId, "returnInfoOne")
        showBtn(cateType, orderId);
        //getPersionHandsOne("");
        $("#personLinedId").text("");
        $("#personLinedStatus").text("");
        getPersonInfoOne()//当前匹配服务人员
        getPersonInfoHisOne()//历史服务人员
        setOrderLogin();
        getLineOneTime();//查询排期
        // 当前登录人员操作按钮权限控制
        setOrderButtonsAllCont1();
        //compareYgDate();
        queryOrderTurnLog(orderId, 'turnOrderLogListOne');

        //查询最近的下户人员
        checkServerOnePersonType(orderId);
        queryEvaluate(orderId, 'appraiseServerOneList', 0, 5);
        queryEvaluateBack(orderId, 'appraiseReplyServerOneList', 0, 5);

        /**
         * 默认显示基本信息 add 20190116 zhanghao
         */
        serverBasicOne(1);
    }

    function setOrderLogin() {
        var rechargeBy = $("#checkedRechargeBy").val();
        if (rechargeBy == null || rechargeBy == '') {
            //	alert("登录人："+loginBy+"负责人"+rechargeBy);
            $("#serverBasicOne2").css("display", "none");
            $("#serverBasicOne3").css("display", "none");
            $("#serverBasicOne5").css("display", "none");
        } else {
            $("#serverBasicOne2").css("display", "block");
            $("#serverBasicOne3").css("display", "block");
            $("#serverBasicOne5").css("display", "block");
        }

    }
    function setOrderButtonsAllCont1() {
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
                $("button:button[name=orderButtonsAllOne]").each(function () {
                    $(this).hide();
                });
                $("input:button[name=orderButtonsAllItem]").each(function () {
                    $(this).hide();
                });
            } else { // 已分包,负责人对应的操作权限
                $("button:button[name=orderButtonsAllOne]").each(function () {
                    $(this).show();
                });

                $("input:button[name=orderButtonsAllItem]").each(function () {
                    $(this).show();
                });
                //20180706 添加
                $("input:button[name=orderButtonSendpies]").show();
                if (rechargeBy != loginBy) {
                    $("button:button[name=orderButtonsAllOne]").each(function () {
                        $(this).hide();
                    });
                    $("input:button[name=orderButtonsAllItem]").each(function () {
                        $(this).hide();
                    });
                } else {
                    $("button:button[name=orderButtonsAllOne]").each(function () {
                        $(this).show();
                    });
                    $("input:button[name=orderButtonsAllItem]").each(function () {
                        $(this).show();
                    });
                    //20180706 添加
                    $("input:button[name=orderButtonSendpies]").show();
                }
            }
        } else {
            $("button:button[name=orderButtonsAllOne]").each(function () {
                $(this).show();
            });

            $("input:button[name=orderButtonsAllItem]").each(function () {
                $(this).show();
            });
            //20180706 添加
            $("input:button[name=orderButtonSendpies]").show();
        }
    }
    //取到订单详细信息
    function getServerBasicOne(orderId) {
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
                $("#inwxiaID").empty();//当前服务人员id
                //$("#interviewType").empty();//服务人员受理类型
                if (data.msg == "00") {
                    var html = "";
                    var num = $.each(data.list, function (i, v) {
                        // 详细信息
                        $("#basicOneSolutionCode").text(v.solutionCode);
                        $("#orderCodeBasicOne").text(v.orderCode);
                        $("#serverTypeBasicOne").val(v.serverType);
                        $("#personNumberBasicOne").val(v.personNumber);
                        $("#personNumberBasicOneShow").text(v.personNumber);
                        $("#servserTextBasicOne").text(v.typeText);
                        /** 周鑫  2019-01-22 **/
                        $("#linkManOne").text(v.order.receiverName);
                        $("#linkMobleOne").text(v.order.receiverMobile);
                        /** 周鑫  2019-01-22 **/
                        $("#startTimeBasicOne").text(numberToDatestr(v.startTime, 12) + "—" + numberToDatestr(v.endTime, 12));
                        $("#statusTextBasicOne").text(v.statusText);
                        $("#payTextServerOne").text(v.payText);
                        $("#sourceTextBasicOne").text(v.sourceText);
                        $("#channelTextBasicOne").text(v.channelText);
                        $("#person").text(v.order.person);
                        $("#personmobile").text(v.order.personmobile);
                        $("#addressBasicOne").text(v.address);
                        $("#remarkBasicOne").text(v.remark || "");
                        $("#checkedTotalPay").text(v.totalPay);
                        $("#createTimesServerOne").text(numberToDatestr(v.createTime, 12));
                        $("#receiverProvinceBasicOne").text(v.order.receiverProvince);
                        $("#receiverCityBasicOne").text(v.order.receiverCity);
                        $("#orderCityCode").val(v.city);
                        $("#productCode").val(v.productCategoryCode);
                        //是否投保
                        var insure = v.insure;
                        if (insure == 1) {
                            $("#insureOne").text("是");
                        } else if (insure == 2) {
                            $("#insureOne").text("否");
                        } else {
                            $("#insureOne").text("暂无信息");
                        }
                        // 客户信息
                        $("#nameBasicOne").text(v.order.userName);
                        $("#mobileBasicOne").text(v.order.userMobile);
                        $("#sexBasicOne").text(v.sex == 1 ? "男" : "女");
                        $("#birthBasicOne").text(v.birth == "" ? "" : numberToDatestr(v.birth, 8));
                        $("#userAddressBasicOne").text(v.userAddress);
                        $("#ForestsServer").text(v.forestsserver);

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
                    $("#orderInforServerOne").html(html);
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
                            $("#xqSexTextBasicsOne").text(xqSex);
                        }
                    })
                }
            }
        });
    }
    // 调区显示区域
    function serverBasicOne(num) {
        if (num == 1) {
            $("#serverBasicOne1").addClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne6").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "block");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#personInfoOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
        } else if (num == 2) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").addClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne6").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "block");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#personInfoOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
            getPersionHandsOne("");
        } else if (num == 3) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").addClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne6").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "block");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#personInfoOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
        } else if (num == 4) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").addClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne6").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "block");
            $("#ReturnRecordinfo").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#personInfoOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
            queryOrderTurnLog($("#orderIdBasicOne").val(), 'turnOrderLogListOne');
        } else if (num == 5) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").addClass("tab-selected");
            $("#serverBasicOne6").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "block");
            $("#personInfoOne").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
        } else if (num == 6) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne6").addClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "block");
            $("#personInfoOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
        } else if (num == 7) {
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne7").addClass("tab-selected");
            $("#serverBasicOne8").removeClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#personInfoOne").css("display", "block");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#agreementServerOne").css("display", "none");
        } else if (num == 8) {//add 20180109 zhanghao 单次服务添加三方协议标签
            $("#serverBasicOne1").removeClass("tab-selected");
            $("#serverBasicOne2").removeClass("tab-selected");
            $("#serverBasicOne3").removeClass("tab-selected");
            $("#serverBasicOne4").removeClass("tab-selected");
            $("#serverBasicOne5").removeClass("tab-selected");
            $("#serverBasicOne7").removeClass("tab-selected");
            $("#serverBasicOne8").addClass("tab-selected");
            $("#basicServerOne").css("display", "none");
            $("#personServerOne").css("display", "none");
            $("#feesServerOne").css("display", "none");
            $("#turnOrderLogOne").css("display", "none");
            $("#ReturnRecordinfo").css("display", "none");
            $("#personInfoOne").css("display", "none");
            $("#appraiseOrderServerOne").css("display", "none");
            $("#agreementServerOne").css("display", "block");
            var orderId = $("#orderIdBasicOne").val();
            queryAgreementServerOneDetail(orderId);
        }
        //setOrderButtonsAllOne();
    }

    // 自动匹配服务人员
    function getPersonAccordOne() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var num = queryNeedPersons(1);
        if (num >= 1) {
            $.alert({text: "当前订单已经完成需求推送，请选择手动匹配人员！"});
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
                        html += "</td></tr><tr><td>";
                        html += "<input name='odIdNeedsOne' type='radio' value='" + v.id + "-" + v.interviewType + "'>&nbsp;序号" + (i + 1);
                        html += "&nbsp;序号" + (i + 1);
                        html += "</td><td>产品管家：" + v.realName;
                        html += "</td></tr><tr><td></td><td>产品管家电话：" + v.phone;
                        html += "</td></tr><tr><td></td><td>匹配状态：" + v.typeText;
                        html += "</td></tr></table>";
                    })
                } else {
                    $.alert({text: "匹配不到合适的服务人员！"});
                }
                $("#needsPersonalsOne").html(html);
            }
        });
        $("#checkedOneNeedsIds").val(ids);

    }
    // 打开手动匹配列表
    function openOrderNeedsOne() {
        var orderLineDay = $("#orderLineOne").text().split("--");
        if (orderLineDay[0] == "" || orderLineDay[1] == "") {
            $.alert({text: "请完善排期！"});
            return;
        }
        var orderId = $("#checkedOrderId").val();
        var num = queryNeedPersons(1);
        var orderstatus = $("#checkedOrderStatus").val();
        if (orderstatus == 10 || orderstatus == "10") {
            $.alert({text: "订单已取消 不能推送！"});
            return;
        }
        if (orderstatus == 12 || orderstatus == "12") {
            $.alert({text: "当前订单已经终止，无法匹配人员！"});
            return;
        }
        if (orderstatus == 9 || orderstatus == "9") {
            $.alert({text: "当前订单已经完成，无法匹配服务人员！"});
            return;
        }
        var wn = $("#personNumberWrokBasicOne").val();
        //检查订单是否生成排期
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
                    lind = 1
                }
            }
        });
        if (lind == 0) {
            $.alert({text: "请先生成需求排期！"});
            return;
        }
        openModule('${ctx}/jsp/server/orderNeeds.jsp',
                {
                    "orderId": orderId,
                    "cateType": 1,
                    "orderLineDay": orderLineDay[0],
                    "orderLineTime": orderLineDay[1],
                    "cityCode": $("#checkedCity").val(),
                    "personNumber": $("#personNumberBasicOneShow").text(),
                    "orderType": $("#serverOrderType").val()//20180927增加订单类型参数
                }, '', '', 'orderNeedsModule');
    }
    /* new 1,2,4,6,8,11,12*/
    function getPersonInfoOne() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $("#rightPersonLineOne").val("");
        $("#personPP").val("");
        $.ajax({
            url: ctx + "/itemDetailServer/queryOrderNeedServer",
            data: {
                orderId: orderId,
                type: 11
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                html = "";
                if (data.msg == "00") {
                    $("#rightPersonLineOne").val("1");
                    var typeText = "";
                    $("#personPP").val(data.list.length);
                    var num = $.each(data.list, function (i, v) {
                        if (v.interviewType != 2) {
                            $("#personLinedId").append(v.id + ",");
                            $("#inwxiaID").append(v.userId + ",");
                            $("#personLinedStatus").text(v.interviewType);
                            html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                            html += "<td rowspan='7'>";
                            html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                            html += " style='width:80px; height:80px;'/>";
                            //html += "<div><button style='width:80px;' class='btn btn-primary btn-xs' type='button'  onclick='downloadEmpFileCard("+v.id+");'>下载信息卡</button></div>" ;
                            html += "</td>";
                            html += "<td>姓名：" + v.name + "</td></tr>";
                            html += "<tr><td><td>证件号：" + v.idCardNum + "</td></td></tr>";
                            html += "<tr><td><input type='checkbox' name='personCheck' person-id='" + v.id + "' startTime='" + numberToDatestr(v.startTime, 12) + "' value='" + v.userId + "' data-status='" + v.interviewType + "'>";
                            +(i + 1);
                            html += "</td><td>产品管家：" + v.realName + "(" + v.phone + ")" + "</td></tr>";
                            html += "<tr><td></td><td>匹配方式：" + v.matchMethod;
                            html += "<tr><td>" + "</td><td>匹配状态：" + v.typeText + "</td></tr>";
                            html += "<tr><td>";
                            html += "</td><td>上户时间：" + numberToDatestr(v.startTime, 12);
                            html += "</td></tr><tr><td>";
                            html += "</td><td>下户时间：" + numberToDatestr(v.endTime, 12);
                            html += "</td></tr><tr><td>";
                            html += "</td></tr>";
                            html += "</table>";
                        } else {
                            if (v.typeText == null || v.typeText == "") {
                                typeText = "";
                            } else {
                                if (v.interviewType == 4) {
                                    typeText = "匹配状态：<span style='color:#008B00'>" + v.typeText + "</span>";
                                } else {
                                    typeText = "匹配状态：" + v.typeText;
                                }
                            }
                            html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                            html += "<td rowspan='4'>";
                            html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                            html += " style='width:80px; height:80px;'/>";
                            //html += "<div><button style='width:80px;' class='btn btn-primary btn-xs' type='button'  onclick='downloadEmpFileCard("+v.id+");'>下载信息卡</button></div>" ;
                            html += "</td>";
                            html += "<td>服务人员姓名：" + v.name;
                            html += "</td></tr><tr><td><input  name='personCheck' type=checkbox value=" + v.userId + " person-id=" + v.id + " data-status=" + v.interviewType + ">";
                            //html += "<input name='odIdNeedsOne' type='checkbox' value='" +v.id +"-" +v.interviewType +"'>&nbsp;序号" +(i+1) ;
                            html += "&nbsp;序号" + (i + 1);
                            html += "</td><td>产品管家：" + v.realName;
                            html += "</td></tr><tr><td>";
                            html += "</td><td>产品管家电话：" + v.phone;
                            html += "</td></tr><tr><td>";
                            html += "</td><td>" + typeText;
                            html += "</td></tr>";
                            if (v.interviewType == 4) {
                                html += "<tr><td></td><td></td><td style='text-align:right'>";
                                html += "<input type='button' name='setPersonEndOne' class='btn btn-primary btn-xs' value='取消' onclick='setPersonEndOne(" + v.userId + ")'/>";
                                html += "&nbsp;&nbsp;</td></tr>";
                            }
                            if (v.interviewType == 2) {
                                html += "<tr><td></td><td></td><td style='text-align:right'>";
                                html += "<input type='button' class='btn btn-primary btn-xs' value='匹配' onclick='setPersonLastOne(" + v.userId + ")'/>";
                                html += "&nbsp;&nbsp;</td></tr>";
                            }
                            html += "</table>";
                            if (v.interviewType == 9) {
                                html += "<tr><td><input type='hidden' id='empIdHide'/><input type='hidden' name='empIdsHidden' value='" + v.interviewType + "_" + v.id + "'/></td></tr>";
                                workPersons++;
                            }
                        }
                    });
                }
                $("#rightNowPersonOne").html(html);
            }
        });

    }
    //new
    function getPersonInfoHisOne() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $("#hisPersonLineOne").val("");
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
                    $("#hisPersonLineOne").val("2");
                    var typeText = "";
                    var num = $.each(data.list, function (i, v) {
                        html += "<table class='tabOrder'><tr><td style='width:60px;' ></td>";
                        html += "<td rowspan='7'>";
                        html += "<img src='" + v.originalAddress + "' onclick='checkPersons(" + v.id + ")' title='点击查看详细信息' ";
                        html += " style='width:80px; height:80px;'/>";
                        //html += "<div><button style='width:80px;' class='btn btn-primary btn-xs' type='button'  onclick='downloadEmpFileCard("+v.id+");'>下载信息卡</button></div></td>" ;
                        html += "<td>姓名：" + v.name + "</td></tr>";
                        html += "<tr><td><td>证件号：" + v.idCardNum + "</td></td></tr>";
                        html += "<tr><td><input type='checkbox' name='personCheck' person-id=" + v.id + " value=" + v.userId + " data-status=" + v.interviewType + ">";
                        +(i + 1);
                        html += "</td><td>产品管家：" + v.realName + "(" + v.phone + ")" + "</td></tr>";
                        html += "<tr><td></td><td>匹配方式：" + v.matchMethod;
                        html += "<tr><td>" + "</td><td>匹配状态：" + v.typeText + "</td></tr>";
                        html += "<tr><td>";
                        html += "</td><td>上户时间：" + numberToDatestr(v.startTime, 12);
                        html += "</td></tr><tr><td>";
                        html += "</td><td>下户时间：" + numberToDatestr(v.endTime, 12);
                        html += "</td></tr><tr><td>";
                        html += "</td></tr>";
                        html += "</table>";
                        if (v.interviewType == 9) {
                            html += "<tr><td><input type='hidden' id='empIdHide" + orderId + "'/><input type='hidden' name='serverOneEmpId' value='" + v.interviewType + "_" + v.id + "'/></td></tr>";
                        }
                    })
                }
                $("#historyPersonOne").html(html);
            }
        });

    }


    /**
     *上户/下户/发送派工单/完成
     *@num 8：上户/ 9：下户 /10：发送派工单
     */
    function setPersonStartOne(num) {
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "订单已取消,无法上下户"});
            return;
        } else if (orderStatus == 12) {
            $.alert({text: "订单已终止,无法上下户"});
            return;
        }
        var orderId = $("#checkedOrderId").val();
        var mobileBasicinfos = $("#mobileBasicOne").text();//客户电话
        var result = new Array();//选中的上户记录id
        var person = new Array();//选中的上户记录对应的服务人员id
        var personStatus = new Array();//选中的上户记录状态
        var startTime = new Array();
        $("[name = personCheck]:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).prop("value"));
                person.push($(this).attr("person-id"));
                personStatus.push($(this).attr("data-status"));
            }
        });
        var inwid = result.join(",");
        var personid = person.join(",");
        var personLine = $("#personLinedId").text();//当前服务人员id
        if (num == 8) {
            /* 上户 */
            if (personid == null || personid == "") {
                $.alert({text: "请选择服务人员"});
                return;
            }
            $.ajax({
                url: ctx + "/itemInterview/queryNeedPersons",
                data: {
                    orderId: orderId,
                    type: 13
                },
                type: "post",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.msg == "00") {
                        if (orderStatus == 3 || orderStatus == 4 || orderStatus == 8) {
                            var params = {"interviewType": 8, "orderId": orderId, "ids": inwid, "personid": personid};
                            openModule("${ctx}/jsp/server/orderPersonStartOne.jsp", params, "", {}, "orderPersonStartOne");
                        } else {
                            $.alert({text: "当前订单无法上户！"});
                            return;
                        }
                    } else {
                        $.alert({text: "当前订单无可上户人员！"});
                        return;
                    }
                }
            });
        } else if (num == 9) {
            /*下户*/
            var personStatus = new Array();
            $("#rightNowPersonOne [name = personCheck]").each(function () {
                if ($(this).attr("data-status") == 8) {
                    personStatus.push($(this).attr("data-status"))
                    result.push($(this).attr("value"));
                    person.push($(this).attr("person-id"))
                    startTime.push($(this).attr("startTime"))
                }
            });
            var a = startTime[0];
            var inwid = result.join(",");
            var personid = person.join(",");
            for (i = 0; i < personStatus.length; i++) {
                if (personStatus[i] != 8) {
                    $.alert({text: "正在上户中的服务人员才可以下户！"});
                    return;
                }
            }
            if (orderStatus == 8) {
                var params = {
                    "interviewType": 9,
                    "orderId": orderId,
                    "ids": inwid,
                    "dotime": startTime[0],
                    "personid": personid,
                    "mobileBasicinfos": mobileBasicinfos
                }
                openModule("${ctx}/jsp/server/orderPersonStartOne.jsp", params, "", {}, "orderPersonStartOne");
            } else {
                $.alert({text: "当前订单无法下户！"});
                return;
            }

        } else if (num == 10) {
            //订单下户后,发送派工单
            var npCount = $("#rightNowPersonOne").find("[name='personCheck']").length;//当前服务人员
            var hpCount = $("#historyPersonOne").find("[name='personCheck']").length;//历史服务人员
            if ((npCount + hpCount) == 0) {
                $.alert({text: "当前订单未推送服务人员,不能发送派工单"});
                return;
            }
            var list = queryOrderStatus(orderId);
            if (list != null && list.length > 0) {
                $.alert({text: "请确认当前订单所有服务人员已下户,才能发送派工单"});
                return;
            }
            sendpies();//发送派工单
        } else {
            /*完成*/
            if (personStatus.join(",").split(",")[0] != 9) {
                $.alert({text: "已经下户的服务人员才可以完成"});
                return;
            }
            if (orderStatus != 11) {
                $.alert({text: "当前订单无法完成！"});
                return;
            }
            $.confirm({
                text: "此功能用于订单金额和缴费金额不符，请慎用。请确认服务人员已下户，缴费金额填写正确并且对账已完成。确认完成将不能新增、修改结算单。是否继续？", callback: function (re) {
                    if (re) {
                        $.ajax({
                            url: ctx + "/order/updateOrderPay",
                            data: {
                                id: orderId,
                                cateType: 1,
                                orderStatus: 9
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.msg == '001') {
                                    $.alert({text: "订单未交费，无法完成"});
                                    return;
                                } else {
                                    $("#checkedOrderStatus").val(9);
                                }
                                getPersonInfoHisOne();
                                getPersonInfoOne();
                                //点击完成按钮发送派工单 20180706 xyp add
                                sendpies();
                            }
                        });
                    } else {
                        return;
                    }
                }
            });
        }
    }

    // 单次服务人员匹配
    function setPersonLastOne(intvId) {
        var ctx = $("#ctx").val();
        $.ajax({
            url: ctx + "/itemInterview/updateItemInterview",
            data: {
                id: intvId,
                interviewType: 4,
                type: 99
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    //getPersionHandsOne("");
                    getPersonInfoHisOne();
                    getPersonInfoOne();
                }
            }
        })
    }

    // 取消单次服务已匹配的服务人员
    function setPersonEndOne(intvId) {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.confirm({
            text: "是否确定取消？", callback: function (re) {
                if (re) {
                    $.ajax({
                        url: ctx + "/itemInterview/queryNeedPersons",
                        data: {
                            orderId: orderId,
                            type: 14
                        },
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (data.msg == "00") {
                                $.ajax({
                                    url: ctx + "/itemInterview/updateItemInterview",
                                    data: {
                                        id: intvId,
                                        orderId: orderId,
                                        interviewType: 10,
                                        type: 14


                                    },
                                    type: "post",
                                    dataType: "json",
                                    async: false,
                                    success: function (data) {
                                        html = "";
                                        if (data.msg == "00") {
                                            $.alert({text: "已取消！"});
                                            getPersonInfoOne();
                                            changeOrderState();
                                        }
                                    }
                                })
                            } else {
                                $.alert({text: "请先匹配他人，再取消当前服务人员!"});
                            }
                        }
                    })

                } else {
                    return;
                }
            }
        });
    }

    // 取消单次服务已匹配的服务人员   new
    function cancelPersonOne() {
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        var PersonInfoStatus = new Array();
        var result = new Array();
        var person = new Array();
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10) {
            $.alert({text: "订单已取消，无法取消服务人员"});
            return;
        }
        if (orderStatus == 12) {
            $.alert({text: "订单已终止，无法取消服务人员！"});
            return;
        }
        $("[name = personCheck]:checkbox").each(function () {
            if ($(this).is(":checked")) {
                result.push($(this).attr("value"));
                PersonInfoStatus.push($(this).attr("data-status"));
                person.push($(this).attr("person-id"));
            }
        });
        for (i = 0; i < PersonInfoStatus.length; i++) {
            if (PersonInfoStatus[i] > 6) {
                $.alert({text: "上下户的服务人员不可以取消！"});
                return;
            }
        }
        var inwid = result.join(",");
        $.confirm({
            text: "是否确定取消？", callback: function (re) {
                if (re) {
                    $.ajax({
                        url: ctx + "/itemInterview/updateItemInterview",
                        data: {
                            ids: inwid,
                            personids: person.join(","),
                            orderId: orderId,
                            interviewType: 10,
                            type: 14
                        },
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            html = "";
                            if (data.msg == "00") {
                                getPersonInfoOne();
                                changeOrderState();
                                $.alert({text: "已取消！"});
                            }
                        }
                    });

                } else {
                    return;
                }
            }
        });
    }

    //取消当前服务人员匹配时，将订单状态改回匹配中
    function changeOrderState() {
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemInterview/queryMatchingPersonId",
            data: {
                orderId: orderId,
                interviewType: 4
            },
            type: "post",
            dateType: "json",
            async: false,
            success: function (data) {
                if (data.msg != "00") {
                    $.alert({text: "服务人员取消成功，订单状态已改为匹配中！"});
                }
            }
        });
    }

    //需求推送,保存匹配的服务人员信息
    function insertInterviewPersonOne() {
        var ids = $("#checkedOneNeedsIds").val();
        var orderStatus = $("#checkedOrderStatus").val();
        if (orderStatus == 10 || orderStatus == "10") {
            $.alert({text: "订单已取消 不能推送！"});
            return;
        }
        if (orderStatus == 12 || orderStatus == "12") {
            $.alert({text: "当前订单已经终止，无法匹配人员！"});
            return;
        }
        if (orderStatus == 9 || orderStatus == "9") {
            $.alert({text: "当前订单已经完成，无法匹配服务人员！"});
            return;
        }
        if (ids == null || ids == "") {
            $.alert({text: "请先匹配服务人员！"});
            return;
        }
        var wn = $("#personNumberWrokBasicOne").val();
        var ctx = $("#ctx").val();
        var orderId = $("#checkedOrderId").val();
        $.ajax({
            url: ctx + "/itemInterview/insertItemInterview",
            data: {
                orderId: orderId,
                ids: ids,
                orderStatus: orderStatus,
                type: 1
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.alert({text: "需求推送成功！"});
                    //getPersionHandsOne("");
                }
            }
        })
    }

    /**长链接转短链接-20180706  xyp 添加*/
    var messageShortUrls;
    function sendpiesgetShortUrl(linkinfo, messageUrl) {
        //长链接参数
        $.ajax({
            url: linkinfo,
            data: {url: messageUrl},
            type: "POST",
            async: false,
            success: function (data) {
                if (data.msg == '00') {
                    messageShortUrls = data.shortUrl;
                } else {
                    messageShortUrls = "";
                }
            }
        });
    }

    /**发送派工单验证 该订单是否已发送-20180706  xyp 添加*/
    function checkpiesInfos() {
        var orderId = $("#checkedOrderId").val();
        var mobileBasicinfos = $("#mobileBasicOne").text();
        var count = 0;
        $.ajax({
            url: "${ctx}/itemInterview/validateSendpies",
            data: {
                orderId: orderId,
                mobilePhone: mobileBasicinfos,
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    count = data.viewcount;
                } else {
                    count = -1;
                }
            }
        })
        return count;
    }

    //20180706 xyp 添加-手动发送派工单方法
    function sendpies() {
        var orderId = $("#checkedOrderId").val();
        var mobileBasicinfos = $("#mobileBasicOne").text();
        var num = checkpiesInfos();
        if (num > 0) {
            $.alert({millis: 5000, text: "该订单已发送过派工单,发送客户电话为:" + mobileBasicinfos + ",请勿重复发送！"});
            return;
        } else if (num < 0) {
            $.alert({millis: 3000, text: "查询异常，请稍后处理！"});
            return;
        }

        //弹出确认窗口 给提示
        $.confirm({
            text: "即将为电话为:" + mobileBasicinfos + "的客户发送派工单,请确认.", callback: function (re) {
                if (re) {

                    var messageUrl = "http://" + window.location.host + "/jzjy-wechat/html/singleSurvey/index.html?id=" + orderId;
                    var linkinfo = "http://" + window.location.host + "/sms/su/ts";
                    //长连接转短链接
                    sendpiesgetShortUrl(linkinfo, messageUrl);
                    $.ajax({
                        url: ctx + "/itemInterview/sendpiesinfo",
                        data: {
                            orderId: orderId,
                            mobilePhone: mobileBasicinfos,
                            shortUrl: messageShortUrls
                        },
                        type: "post",
                        dataType: "json",
                        async: false,
                        success: function (data) {
                            if (data.myvalida == "1") {
                                $.alert({millis: 5000, text: "短信已发送！"});
                            } else if (data.myvalida == "0") {
                                $.alert({millis: 5000, text: "前端已发送！"});
                            } else if (data.myvalida == "2") {
                                $.alert({millis: 3000, text: "短信发送失败！"});
                            } else {
                                $.alert({millis: 3000, text: "短信发送异常！"});
                            }
                        }
                    });

                } else {
                    return;
                }
            }
        });
    }

    // 新增结算
    function addAccountServerOne() {
        orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), 0, parent.$("#checkedIscollection").val());
    }

    // 修改结算
    function updateAccountServerOne() {
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
            if ($("#accountListBodyOne" + radioAccount).find("tr").length != 0) {
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
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicOne");
            querySysdate("accountSystemDateBasicOne");
            var updateServerOneCreTime = $("#accountCreateTimeBasicOne").val();
            var updateServerOneSysdate = $("#accountSystemDateBasicOne").val();
            deleteAccountTimeRule(updateServerOneCreTime, updateServerOneSysdate);
            if (timeRuleFlag) {
                orderBasicAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
            } else {
                $.alert({
                    text: "当前结算单已过有效操作时间，不可操作！"
                });
                return;
            }
        } else {
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicOne");
            querySysdate("accountSystemDateBasicOne");
            var updateServerOneCreTime = $("#accountCreateTimeBasicOne").val();
            var updateServerOneSysdate = $("#accountSystemDateBasicOne").val();
            deleteAccountTimeRule(updateServerOneCreTime, updateServerOneSysdate);
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
    //修改预估
    function updateygAccountServerOne() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择要修改的结算单！"
            });
            return;
        }
        orderBasicYgAccount(parent.$("#checkedOrderId").val(), parent.$("#checkedCateType").val(), parent.$("#checkedTotalPay").val(), radioAccount);
    }
    // 删除结算
    function deleteAccountServerOne() {
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
            loadAccountCreTime(radioAccount, "accountCreateTimeBasicOne");
            querySysdate("accountSystemDateBasicOne");
            var serverOneCreTime = $("#accountCreateTimeBasicOne").val();
            var serverOneSysdate = $("#accountSystemDateBasicOne").val();
            deleteAccountTimeRule(serverOneCreTime, serverOneSysdate);
            /* if(timeRuleFlag){  判断封账*/
            if (true) {
                $.confirm({
                    text: "是否确认删除结算？", callback: function (re) {
                        if (re) {
                            deleteAccountById(radioAccount);
                            queryAccount(parent.$("#checkedOrderId").val(), "accountListBodyOne");
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
        var result = checkAccountReviewState(radioAccount);
        if (result != "00") {
            $.alert({
                text: "缴费已确认，无法删除！如有疑问，请联系结算中心"
            });
            return;
        }
        /** 周鑫 2018-12-27    **/
    }

    // 新增缴费
    function addPayfeeServerOne() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyOne" + radioAccount).find("tr").length > 0) {
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
    function updatePayfeeServerOne() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        if ($("#accountListBodyOne" + radioAccount).find("tr").length == 0) {
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

        loadPayfeeMinCreTime(radioAccount, "accountCreateTimeBasicOne");
        querySysdate("accountSystemDateBasicOne");
        var serverOneMinCreTime = $("#accountCreateTimeBasicOne").val();
        var serverOneMinSysdate = $("#accountSystemDateBasicOne").val();
        deleteAccountTimeRule(serverOneMinCreTime, serverOneMinSysdate);
        /** 周鑫 2018-12-27  校验审核状态 是否是 已确认  **/
        var result = checkAccountReviewState(radioAccount);
        if (result != "00") {
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
    function addPayfeeWeiXinServerOne() {
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
    function getLineOneTime() {
        $("#orderLineOne").empty();
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
                if (data.msg == "00") {
                    //
                    /* debugger
                    var checkedServiceObject=$("#checkedServiceObject").val();
                    if(checkedServiceObject==1){
                    	 $("#orderLineOne").html(numberToDatestr(data.itemDetailServer.startTime, 14) +'------'+numberToDatestr(data.itemDetailServer.endTime, 14)+ "    " + data.itemDetailServer.hours);
                    }else{ */
                    	$("#orderLineOne").html(numberToDatestr(data.itemDetailServer.startTime, 14) + "--" + data.itemDetailServer.hours);
                   	// }
                 }
                }
        });
    }

    //查看服务人员排期
    function setPersonLinedOne() {
        var ctx = $("#ctx").val();
        var person = new Array();
        $("[name = personCheck]:checkbox").each(function () {
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

    //受理类型:1.未处理 2.已推送 3.已拒绝 4.已匹配 5.已调换 6.面试成功. 7.面试失败 8.上户 9.下户 10.已取消,11顶岗预备,12顶岗上户,13调岗下户
    function setOrderLinedOne() {
        var payStatus = $("#checkedPayStatus").val();//订单支付状态
        var his = $("#hisPersonLineOne").val();
        var right = $("#rightPersonLineOne").val();
        var personId = $("#personLinedId").text();
        var personStatus = $("#personLinedStatus").text();
        var personCheck12 = $("input[name='personCheck'][type='checkbox']");
        var pflag = queryPersonLineTime();
        var flag = selLine_rjp_server1();
        if (flag == 1 || flag == 3) {
            openModule('${ctx}/jsp/server/ServedLineOne.jsp', {
                cityCode: $('#orderCityCode').val(),
                productCode: $('#productCode').val(),
                personId: personId,
                personStatus: personStatus,
                personNumber: $("#personNumberBasicOne").val()
            }, {}, {}, 'ServedLineOne');
        } else {
            if (payStatus != '20110001') {
                $.alert({text: "当前订单已支付,不能修改"});
                return;
            } else if (pflag) {
                $.alert({text: "当前订单已存在服务人员排期,不能修改"});
                return;
            } else {
                openModule('${ctx}/jsp/server/ServedLineOne.jsp', {
                    cityCode: $('#orderCityCode').val(),
                    productCode: $('#productCode').val(),
                    personId: personId,
                    personStatus: personStatus,
                    personNumber: $("#personNumberBasicOne").val()
                }, {}, {}, 'ServedLineOne');
            }
        }
    }

    /**查看订单排期*/
    function selLine_rjp_server1() {
        var flag = 0;
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
                    if ((v.startTime == null || v.startTime == "") || (v.endTime == null || v.endTime == "") || !v.hours) {
                        flag = 1;
                    }
                    if (v.linedType == 2) {
                        if (!v.hours) {
                            flag = 2;
                        }
                    }
                } else if (data.msg == "02") {
                    flag = 3;
                }
            }
        });
        return flag;
    }

    //新增嘉联二维码支付
    function addPayfeeJiaLianServerOne() {
        var radioAccount = $('input:radio[name=radioAccount]:checked').val();
        var payStatus = $("#payStatusAccount" + radioAccount).val();
        if (radioAccount == null || radioAccount == "" || radioAccount == 0) {
            $.alert({
                text: "请选择结算单！"
            });
            return;
        }
        if ($("#accountListBodyOne" + radioAccount).find("tr").length > 0) {
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

    //查询单次服务，服务人员已下户ID
    function checkServerOnePersonType(orderId) {
        $("#empIdHide" + orderId).val("");
        var arr1 = [];
        var arr2 = [];
        $("input[name='serverOneEmpId']").each(function () {
            var empStr = $(this).val();
            if (empStr != null && empStr != "") {
                var empInfo = empStr.split("_");
                arr1.push(empInfo[0]);
                arr2.push(empInfo[1]);
            }
        });
        if (arr1.length != 0) {
            var onType = $.inArray("9", arr1);
            if (onType == 0) {
                $.each(arr1, function (i, num) {
                    if (num == 9)  $("#empIdHide" + orderId).val(arr2[0]);
                });
            }
        } else {
            $("#empIdHide" + orderId).val("");
        }
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

    /**根据上户判断订单是否已下户*/
    function queryOrderStatus(orderId) {
        var list = null;
        $.ajax({
            url: ctx + "/itemInterview/queryOrderStatus",
            data: {"orderId": orderId},
            type: "POST",
            datetype: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    list = data.list;//还未下户
                }
            }
        })
        return list;
    }

    //受理类型:1.未处理 2.已推送 3.已拒绝 4.已匹配 5.已调换 6.面试成功. 7.面试失败 8.上户 9.下户 10.已取消,11顶岗预备,12顶岗上户,13调岗下户
    //支付状态:20110001"未支付" ,20110002 "部分支付" , 20110003 "支付完成" ,20130002 确认无效 ,20130003 确认有效 ,20130008"退款成功"
    function setOrderLinedOneNew() {
        var orderstatus = $("#checkedOrderStatus").val();
        var payStatus = $("#checkedPayStatus").val();//订单支付状态
        var personId = $("#personLinedId").text();
        var personStatus = $("#personLinedStatus").text();
        if (orderstatus < 8) {
            openModule('${ctx}/jsp/server/ServedLineOne.jsp', {
                cityCode: $('#orderCityCode').val(),
                productCode: $('#productCode').val(),
                personId: personId,
                personStatus: personStatus,
                personNumber: $("#personNumberBasicOne").val(),
                payStatus: payStatus,
                orderstatus: orderstatus
            }, {}, {}, 'ServedLineOne')
        } else {
            $.alert({text: "当前订单已上过户,不能修改"});
        }
    }

    //查询单次合同 add 20180111 zhanghao
    function queryAgreementServerOneDetail(orderId){
        $.ajax({
            url:ctx+"/agreement/queryAgreementServerOneDetail",
            data:{orderId:orderId},
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    var agreement = data.agreement;
                    $("#partyA").text(agreement.partyA);//公司名称
                    $("#cardNum").text(agreement.cardNum);//营业执照
                    $("#agreementCode").text(agreement.agreementCode);//合同号
                    $("#dingTalkAuditCode").text(agreement.dingTalkAuditCode);//钉钉审批单号
                    $("#allPay").text(agreement.allPay);//合同金额
                    $("#effectDate").text(agreement.effectDate);//起始日期
                    $("#finishDate").text(agreement.finishDate);//结束日期
                    var havePDF = agreement.havePDF;//扫描件状态
                    if(havePDF != null && havePDF != ""){
                        $("#agreementCopy").text("已上传");
                        $("#attachmentUpload").button("complete");
                    }else{
                        $("#agreementCopy").text("未上传");
                        $("#attachmentUpload").button("reset");
                    }
                    $("#returnURL").val(havePDF);
                    $("#once_server_agreement_id").val(agreement.id);//合同id隐藏域
                    var checkStatus = agreement.checkStatus;//合同审核状态
                    var checkStatusText = "";
                    if(checkStatus == 1){
                        checkStatusText = "未处理";
                    }else if(checkStatus == 2){
                        checkStatusText = "审核通过";
                    }else if(checkStatus == 3){
                        checkStatusText = "驳回";
                    }else{
                        checkStatusText = "";
                    }
                    $("#auditStatus").text(checkStatusText);
                    $("#once_server_agreement_check_status").val(checkStatus);//合同审核状态隐藏域
                    $("#turnDownReason").text(agreement.reason);
                }else{
                    $("#partyA").text("暂无信息");
                    $("#cardNum").text("暂无信息");
                    $("#agreementCode").text("暂无信息");
                    $("#dingTalkAuditCode").text("暂无信息");
                    $("#allPay").text("暂无信息");
                    $("#effectDate").text("暂无信息");
                    $("#finishDate").text("暂无信息");
                    $("#agreementCopy").text("暂无信息");
                    $("#auditStatus").text("");
                    $("#turnDownReason").text("");
                    $("#returnURL").val("");
                    $("#once_server_agreement_id").val("");//合同id隐藏域
                    $("#once_server_agreement_check_status").val("");//合同审核状态隐藏域
                }
            }
        });
    }

    //新增单次合同 add 20180110 zhanghao
    function addAgreementOne(){
        var checkedOrderStatus = $("#checkedOrderStatus").val();
        if(checkedOrderStatus == "10"){
            $.alert({text:"该订单已取消,不可进行操作!",millis:3000});
            return;
        }
        var agreementId = $("#once_server_agreement_id").val();
        if(agreementId != null && agreementId != ""){
            $.alert({text:"单笔订单只能添加一份合同!",millis:3000});
        }else{
            var orderId = $("#orderIdBasicOne").val();
            openModule("${ctx}/jsp/agreement/onece_agreement_add.jsp",{"orderId":orderId},null, {width:'40%'},"onece_agreement_add");
        }
    }

    //修改单次合同 add 20180110 zhanghao
    function updateAgreementOne(){
        var checkedOrderStatus = $("#checkedOrderStatus").val();
        if(checkedOrderStatus == "10"){
            $.alert({text:"该订单已取消,不可进行操作!",millis:3000});
            return;
        }
        var agreementId = $("#once_server_agreement_id").val();
        if(agreementId == null || agreementId == ""){
            $.alert({text:"请先添加合同信息!",millis:3000});
        }else{
            openModule("${ctx}/jsp/agreement/onece_agreement_edit.jsp",{"agreementId":agreementId},null, {width:'40%'},"onece_agreement_edit");
        }
    }

    //删除单次合同 add 20180110 zhanghao
    function deleteAgreementOne(){
        var checkedOrderStatus = $("#checkedOrderStatus").val();
        if(checkedOrderStatus == "10"){
            $.alert({text:"该订单已取消,不可进行操作!",millis:3000});
            return;
        }

        $("#delete_once_agreement").css("disable",true);
        var orderId = $("#orderIdBasicOne").val();
        var agreementId = $("#once_server_agreement_id").val();
        if(agreementId == null || agreementId == ""){
            $.alert({text:"请先添加合同信息!",millis:3000});
            $("#delete_once_agreement").removeAttr("disable");
        }else{
            $.confirm({text:"确认删除合同吗？",callback:function(r){
                if(r){
                    $.ajax({
                        url:ctx + "/agreement/deleteOnceAgreement",
                        data:{id:agreementId},
                        type:"post",
                        async:false,
                        success:function(data){
                            if(data.msg == "00"){
                                $("#once_server_agreement_id").val("");//清空合同ID隐藏域
                                $("#returnURL").val("");//清空文件保存路径隐藏域
                                $.alert({text:"删除成功!",millis:3000});
                                $("#delete_once_agreement").removeAttr("disable");
                                queryAgreementServerOneDetail(orderId);//刷新列表
                            }else{
                                $.alert({text:"删除失败!",millis:3000});
                                $("#delete_once_agreement").removeAttr("disable");
                            }
                        }
                    });
                }
            }});
        }
    }

    //上传文件 add 20180116 zhanghao
    function uploadOnce(thiz) {
        var checkedOrderStatus = $("#checkedOrderStatus").val();
        if(checkedOrderStatus == "10"){
            $.alert({text:"该订单已取消,不可进行操作!",millis:3000});
            return;
        }
        var agreementId = $("#once_server_agreement_id").val();
        var orderId = $("#orderIdBasicOne").val();
        if(agreementId != null && agreementId != ""){
            var returnInputId = $(thiz).siblings("input").prop("id");
            var url = ctx+"/jsp/agreement/attendance_once_upload.jsp";
            var param = {
                "orderId":orderId,
                "returnInputId" : returnInputId,
                "agreementId" : agreementId,
                "attachmentSpan" : $(thiz).parent().parent().find("[id^='attachment_span']").prop("id"),
                "buttonId" : thiz.id
            };
            openModule(url, param);
        }else{
            $.alert({text:"请先添加合同信息!",millis:3000});
        }
    }

    //预览文件 add 20180116 zhanghao
    function previewOnce(thiz){
        var returnInputId = $(thiz).siblings("input").prop("id");
        var url  = $("#"+returnInputId).val();
        if(url){
            window.open(url);
        }else{
            $.alert({millis:3000,text:"未上传合同扫描件!"});
        }
    }

    //下载文件 add 20180116 zhanghao
    function downloadOnce(thiz){
        var returnInputId = $(thiz).siblings("input").prop("id");
        var url  = $("#"+returnInputId).val();
        if(url){
            var tranUrl = "";
            var ext = url.substring(url.lastIndexOf("."),url.length);
            if (ext == ".jpg" || ext == ".png"|| ext == ".gif"|| ext == ".jpeg") {
                tranUrl = encodeURIComponent(url.substring(url.indexOf('i'),url.length));
            } else {
                tranUrl = encodeURIComponent(url.substring(url.indexOf('f'),url.length));
            }
            window.open("http://erp.95081.com/img_service/file_download?url="+tranUrl)
        } else {
            $.alert({millis : 3000,text : "未上传合同扫描件!"});
        }
    }

    //单次合同审核驳回操作 add 20190212 zhanghao
    function onceAgreementTurnDown(){
        //合同扫描件信息校验
        var url  = $("#returnURL").val();
        if(url == null || url == ""){
            $("#auditPass").removeAttr("disable");
            $.alert({millis:3000,text:"请先上传合同扫描件!"});
            return;
        }

        //合同状态校验
        var checkStatus = $("#once_server_agreement_check_status").val();
        if(checkStatus != 1 && checkStatus != 3){
            $("#auditPass").removeAttr("disable");
            $.alert({millis:3000,text:"只有未处理、驳回状态的合同可以进行审核操作!"});
            return;
        }

        //驳回页面
        var agreementId = $("#once_server_agreement_id").val();
        var orderId = $("#orderIdBasicOne").val();
        openModule("${ctx}/jsp/agreement/onece_agreement_turnDown.jsp",{"agreementId":agreementId,"orderId":orderId},null, {width:'30%'},"onece_agreement_turnDown");
    }

    //单次合同审核通过 add 20190212 zhanghao
    function auditPass(){
        debugger;
        //修改按钮样式，防止重复提交
        $("#auditPass").css("disable",true);

        //合同扫描件信息校验
        var url  = $("#returnURL").val();
        if(url == null || url == ""){
            $("#auditPass").removeAttr("disable");
            $.alert({millis:3000,text:"请先上传合同扫描件!"});
            return;
        }

        //合同状态校验
        var checkStatus = $("#once_server_agreement_check_status").val();
        if(checkStatus != 1 && checkStatus != 3){
            $("#auditPass").removeAttr("disable");
            $.alert({millis:3000,text:"只有未处理、驳回状态的合同可以进行审核操作!"});
            return;
        }

        //审核操作
        var agreementId = $("#once_server_agreement_id").val();
        $.ajax({
            url:ctx + "/agreement/auditPassOnceAgreement",
            data:{id:agreementId},
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    var orderId = $("#orderIdBasicOne").val();
                    queryAgreementServerOneDetail(orderId);
                    $.alert({millis:3000,text:"合同审核成功!"});
                }else{
                    $("#auditPass").removeAttr("disable");
                    $.alert({millis:3000,text:"合同审核失败!"});
                }
            }
        });
    }
</script>
</html>