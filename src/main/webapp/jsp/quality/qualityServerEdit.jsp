<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">
        var orderId = '${param.orderId}';
        var cateType = '${param.cateType}';
        var afterSalesId = '${param.afterSalesId}';
        var vphBackStatus = '${param.vphBackStatus}';
        var auditFlag = '${param.auditFlag}';
        var orderStatus = {
            1: "新单",
            2: "已受理",
            3: "匹配中",
            4: "已匹配",
            5: "待面试",
            6: "面试成功",
            7: "已签约",
            8: "已上户",
            9: "已完成",
            10: "已取消",
            11: "已下户",
            12: "已终止",
            13: "捡货中",
            14: "配送中",
            16: "三方订单匹配失败",
            17: "工作完成",
            18: "待受理"
        };
        var orderTypeCode = '${param.orderTypeCode}';//订单类型
    </script>
</head>
<body>
<div class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" id="uyanxvTitle">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        onclick="closeModule('qualityServerEdit')">×
                </button>
                <h4 class="modal-title">修改售后>>修改延续服务类售后单</h4>
            </div>
            <div class="modal-header" id="udanciTitle">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        onclick="closeModule('qualityServerEdit')">×
                </button>
                <h4 class="modal-title">修改售后>>修改单次服务类售后单</h4>
            </div>
            <input type="hidden" id="userverPayStatus"></input>
            <form class="form-inline" id="uqualityYXServerForm" action="">
                <div class="modal-body">
                    <div class="modal-content-basic">
                        <input type="hidden" id="userverNowPrice"></input>
                        <input type="hidden" id="ucurrentTime"></input>
                        <input type="hidden" id="userverOrderTotalPay"></input>
                        <input type="hidden" id="uyxcustomerManageMoney"></input>
                        <input type="hidden" id="uyxPrePareMoney"></input>
                        <input type="hidden" id="uyxHasVipshopFee"></input>
                        <input type="hidden" id="uyxSaleMoney"></input>
                        <input type="hidden" id="uyxBankCard"></input>
                        <input type="hidden" id="uyxServerIsApp"/>
                        <input type="hidden" id="uyxServerAfterSalesKind"/>
                        <input type="hidden" id="uyxServerPersonnelStatus"/>
                        <input type="hidden" id="uyxServerImgUrl"/>
                        <input type="hidden" id="historyAfterCharge" value="0">
                        <header><h4>客户信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>客户姓名：</p>
                                        <span id="uyxServerUserName"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>联系方式：</p>
                                        <span id="uyxServerMobile"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>性别：</p>
                                        <span id="uyxServerSex"></span>
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
                                        <span id="uyxServerOrderCode"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单类型：</p>
                                        <span id="uyxServerOrderType"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>开始时间：</p>
                                        <span id="uyxServerStartTime"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>结束时间：</p>
                                        <span id="uyxServerEndTime"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>服务地址：</p>
                                        <span id="uyxServerReceiverAddress"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单状态：</p>
                                        <span id="uyxServerOrderStatus"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单渠道：</p>
                                        <span id="uyxServerOrderChannel"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单来源：</p>
                                        <span id="uyxServerOrderSource"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>订单备注：</p>
                                        <textarea rows="2" id="uyxServerRemark" style="height:50px;"
                                                  class="form-control form-textarea" readonly="readonly"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <!--  <header id="arterFeeHeader" ><h4>售后手续费</h4></header>
                        <div class="info-select clearfix" id="arterFeeDiv">
                               <div class="form-group col-xs-12">
                                <label>
                                    <p style="width: 15em;">应缴售后手续费(元)：</p>
                                    <span name="payfeeMoney" id="payfeeMoney" ></span>
                                    </label>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    <p style="width: 15em;">信息费可用(元)：</p>
                                    <span name="MembershipFee" id="MembershipFee" ></span>
                                    </label>
                            </div>
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width: 15em;">已缴售后手续费(元)：</p>
                                    <span name="accountAmount" id="accountAmount" ></span>
                                    </label>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    <p style="width: 15em;">非白条预存服务费可用(元)：</p>
                                    <span name="notIousSalaryMoney" id="notIousSalaryMoney" ></span>
                                    </label>
                            </div>
                            <div class="form-group col-xs-12">
                                <label>
                                    <p style="width: 15em;">未缴售后手续费(元)：</p>
                                    <span name="payfeeMoneyC" id="payfeeMoneyC" ></span>
                                    </label>
                            </div>
                            <div class="form-group col-xs-6">
                                <label>
                                    <p style="width: 15em;">分期手续费可用(元)：</p>
                                    <span name="installmentFee" id="installmentFee" ></span>
                                </label>
                            </div>
                            <div class="row" id="after-sale-service-charge-hidden" style="display:none">
                                <div id="MembershipFee_hidden"></div> 分期手续费可用缴费
                                <div id="installmentFee_hidden"></div> 信息费可用缴费
                                <div id="notIousSalaryMoney_hidden"></div> 非白条预存服务费可用缴费
                            </div>
                       </div>  -->

                        <header><h4>售后信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>售后类型：</p>
                                        <input type="text" class="form-control" id="uyxServer_afterSalesKind"
                                               readonly="readonly"></input>
                                        <input type="hidden" id="uyxServer_afterSalesKindHidden"></input>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批部门:</p>
                                        <select id="uyxApproveDeptSelect" name="approveDept" class="form-control"
                                                style="width:260px">
                                            <option value="-1">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批人:</p>
                                        <select id="uyxApproveBySelect" name="approveBy" class="form-control">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <hr>
                            <div class="row" id="refundMembershipFeeDiv">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p style="width: 150px;"><span style="color:red">*</span>应退信息费(元)：</p>
                                        <input type="text" name="refundMembershipFee" id="uyxMembershipFee"
                                               class="form-control"></input>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="refundSalaryFeeDiv">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p style="width: 150px;"><span style="color:red">*</span>应退服务人员服务费(元)：</p>
                                        <input type="text" name="refundSalaryFee" id="uyxSalaryFee"
                                               class="form-control"></input>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>退费总额（元）：</p>
                                        <span id="uyxServerRefund"></span>&nbsp;&nbsp;
                                        <span id="uyxServerMoneyDetail" style="display: none;"></span><span style="color:red;" id="uyxWarn">退款总额不能超过最大退款金额，请重新填写！</span>
                                        <input type="hidden" id="uyxServerMoneyHidden" class="form-control"/>
                                        <input type="hidden" name="accountPayId" id="uyxAccountPayId"  placeholder="售后结算单id">
                                        <input type="hidden" name="oldRefundMembershipFee" id="oldRefundMembershipFee" placeholder="原应退信息费">
                                        <input type="hidden" name="oldRefundSalaryFee" id="oldRefundSalaryFee" placeholder="原应退服务人员服务费">
                                    </label>
                                </div>
                            </div>
                            <!-- <div class="row" id="urefundVipShopFeeDiv">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p><span style="color:red">*</span>应退分期金额(元)：</p>
                                        <input type="text" name="urefundVipShopFee" id="uyxVipShopFee"  class="form-control"></input>
                                        &nbsp;&nbsp;<span id="uyxVipShopMoneyDetail"></span><span style="color:red;" id="uyxVipShopWarn">分期退款总额不能超过最大退款金额，请重新填写！</span>
                                        <input type="hidden"  id="uyxVipShopMoneyHidden"  class="form-control"></input>
                                    </label>
                                </div>
                            </div> -->
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>退费明细（元）：</p>
                                        <span id="uyxRefundTotalDetail"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row text-center" id="uafterReturnableDiv">
			               		<div class="form-group col-xs-12">
			                        <label>
								          <header>
									          <h4>
										       	   信息费最大可退：<span id="uafterReturnableMessageFee" value="0"></span>元&nbsp;&nbsp;&nbsp;&nbsp;
										                       服务费最大可退：<span id="uafterReturnableSalary" value="0"></span>元
									          </h4>
								          </header>
			                        </label>
			                    </div>
			                </div>
                            <hr>
                            <div class="row" id="uyxServer_bank">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>银行：</p>
                                        <select class="form-control" name="bankSupportId" id="uyxServer_bankSupportId">
                                            <option value="">...请选择...</option>
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
                                <div class="form-group  col-xs-6" id="uyxServer_bankUn">
                                    <label>
                                        <p>持卡人：</p>
                                        <input type="text" name="bankUserName" class="form-control"
                                               id="uyxServer_bankUserName"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="uyxServer_bankCity">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>开户省份：</p>
                                        <select class="form-control" name="validateProvince"
                                                id="uyxServer_bankProvinceCode"
                                                onclick="setSelCity('uyxServer_bankProvinceCode','uyxServer_bankCityCode')">
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p>开户城市：</p>
                                        <select class="form-control" name="bankCityCode" id="uyxServer_bankCityCode">
                                            <option value="">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="uyxServer_bankCN">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>银行卡号：</p>
                                        <input type="text" name="bankCard" style="width:200px" class="form-control"
                                               id="uyxServer_bankCard"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p>开户行：</p>
                                        <input type="text" name="bankName" class="form-control"
                                               id="uyxServer_bankName"/>
                                    </label>
                                </div>
                            </div>
                            <!-- <div class="row">
                                <div class="form-group col-xs-12">
                                        <label>
                                            <p><span style="color:red">*</span>取消原因：</p>
                                            <select class="form-control" id="uyxReasonSelect" >
                                                        <option value="">...请选择...</option>
                                                     <option value="1" data-quality="客户咨询">客户咨询</option>
                                                     <option value="2" data-quality="不需要服务">不需要服务</option>
                                                     <option value="3" data-quality="已经找到服务">已经找到服务</option>
                                                     <option value="4" data-quality="距离远服务不到">距离远服务不到</option>
                                                     <option value="5" data-quality="提供不了合适人员">提供不了合适人员</option>
                                                     <option value="6" data-quality="觉得信息费太贵">觉得信息费太贵</option>
                                                     <option value="7" data-quality="人员服务费太高">人员服务费太高</option>
                                                     <option value="8" data-quality="对管家不认同">对管家不认同</option>
                                                     <option value="9" data-quality="对管家帮不认同">对管家帮不认同</option>
                                                     <option value="10"data-quality="其它原因">其它原因</option>
                                             </select>
                                        </label>
                                    </div>
                            </div> -->
                            <div class="row" id="uyxReasonDiv">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>原因：</p>
                                        <textarea rows="3" name="reason" style="height:50px;" id="uyxServerReason"
                                                  class="form-control form-textarea"></textarea>
                                    </label>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal"
                            onclick="closeModule('qualityServerEdit')">取消
                    </button>
                    <button type="submit" class="btn btn-sm btn-primary" id="uqualityServerBtn">保存</button>
                </div>
            </form>
            <form class="form-inline" id="uqualityDCServerForm" action="">
                <input type="hidden" id="udcServerCardMoney"></input>
                <input type="hidden" id="udcServerRemainMoney"></input>
                <input type="hidden" id="udcServerIsApp"/>
                <input type="hidden" id="udcServerAfterSalesKind"/>
                <input type="hidden" id="udcServerPersonnelStatus"/>
                <div class="modal-body">
                    <div class="modal-content-basic">
                        <header><h4>客户信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>客户姓名：</p>
                                        <span id="udcServerUserName"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>联系方式：</p>
                                        <span id="udcServerMobile"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>性别：</p>
                                        <span id="udcServerSex"></span>
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
                                        <span id="udcServerOrderCode"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单类型：</p>
                                        <span id="udcServerOrderType"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>开始时间：</p>
                                        <span id="udcServerStartTime"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>结束时间：</p>
                                        <span id="udcServerEndTime"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>服务地址：</p>
                                        <span id="udcServerReceiverAddress"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单状态：</p>
                                        <span id="udcServerOrderStatus"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单渠道：</p>
                                        <span id="udcServerOrderChannel"></span>
                                    </label>
                                </div>
                                <div class="form-group col-xs-4">
                                    <label>
                                        <p>订单来源：</p>
                                        <span id="udcServerOrderSource"></span>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>订单备注：</p>
                                        <textarea rows="2" id="udcServerRemark" style="height:50px;"
                                                  class="form-control form-textarea" readonly="readonly"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <header><h4>售后信息</h4></header>
                        <div class="info-select clearfix">
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p>售后类型：</p>
                                        <input class="form-control" id="udcServer_afterSalesKind"
                                               readonly="readonly"></input>
                                        <input type="hidden" id="udcServer_afterSalesKindHidden"></input>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批部门:</p>
                                        <select id="udcApproveDeptSelect" name="approveDept" class="form-control"
                                                style="width:260px">
                                            <option value="-1">...请选择...</option>
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>审批人:</p>
                                        <select id="udcApproveBySelect" name="approveBy" class="form-control">
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
                                        <span id="udcServerReturnTotal"></span>
                                        <input type="hidden" name="accountPayId" id="udcAccountPayId">
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label><p>退费明细（元）：</p>
                                        <span id="udcReturnTotalDetail"></span>
                                    </label>
                                </div>
                            </div>
                            <hr>
                            <div class="row" id="udcServer_bank">
                                <div class="form-group col-xs-12">
                                    <label>
                                        <p><span style="color:red">*</span>银行：</p>
                                        <select class="form-control" name="bankSupportId" id="udcServer_bankSupportId">
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
                            <div class="row" id="udcServer_bankCity">
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>开户省份：</p>
                                        <select class="form-control" name="validateProvince"
                                                id="udcServer_bankProvinceCode"
                                                onclick="setSelCity('udcServer_bankProvinceCode','udcServer_bankCityCode')">
                                        </select>
                                    </label>
                                </div>
                                <div class="form-group col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>开户城市：</p>
                                        <select class="form-control" name="bankCityCode" id="udcServer_bankCityCode">
                                            <option value="">--请选择--</option>
                                        </select>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="udcServer_bankCN">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>银行卡号：</p>
                                        <input type="text" name="bankCard" style="width:200px" class="form-control"
                                               id="udcServer_bankCard"/>
                                    </label>
                                </div>
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>开户行：</p>
                                        <input type="text" name="bankName" class="form-control"
                                               id="udcServer_bankName"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row" id="udcServer_bankUN">
                                <div class="form-group  col-xs-6">
                                    <label>
                                        <p><span style="color:red">*</span>持卡人：</p>
                                        <input type="text" name="bankUserName" class="form-control"
                                               id="udcServer_bankUserName"/>
                                    </label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-xs-12">
                                    <label class="form-label">
                                        <p>原因：</p>
                                        <textarea rows="3" name="reason" style="height:50px;" id="udcServerReason"
                                                  class="form-control form-textarea"></textarea>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal"
                            onclick="closeModule('qualityServerEdit')">取消
                    </button>
                    <button type="submit" class="btn btn-sm btn-primary" id="udcqualityServerBtn">保存</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${ctx}/js/afterSale/qualityServerUpdate.js"></script>
<script type="text/javascript">
    $(function () {
        /*售后手续费2-开始*/
        /* if(cateType == 2 && orderTypeCode != 100200120003 && orderTypeCode != 100200010001){
         $("#arterFeeHeader").show();
         $("#arterFeeDiv").show();
         var num1=queryPayMoney(orderId);//应缴售后手续费
         var num2=queryAccountPay(orderId);//已缴售后手续费
         var num3=queryMembershipFee(orderId);//信息费可用
         var num4=queryNotIousSalaryMoney(orderId);//非白条预存服务费可用
         var num5=queryInstallmentFee(orderId);//分期手续费可用
         var num6 = num1-num2;
         $("#payfeeMoneyC").html(num6 > 0 ? (Math.round(num6 * 100) / 100):0);//未缴售后手续费
         }else{
         $("#arterFeeHeader").hide();
         $("#arterFeeDiv").hide();
         } */
        /*售后手续费2-结束*/
        getQualityServer(orderId, cateType);
		/** 周鑫 2019-01-07  查询历史服务费退费  **/
		var gha=getHistoryAfterCharge(orderId);
		$("#historyAfterCharge").val(gha);
		/** 周鑫 2019-01-07 查询历史服务费退费 **/
        var smoney = queryUAfterReturnableSalary({"orderId":orderId,"id":afterSalesId});
		var mmoney = queryUAfterReturnableMessageFee({"orderId":orderId,"id":afterSalesId});
		$("#uafterReturnableSalary").attr("value",smoney).html(smoney*1);
		$("#uafterReturnableMessageFee").attr("value",mmoney).html(mmoney*1);
		$("#uyxMembershipFee").on("input",function(){
			inputUMembershipFeeOrSalaryFee();
		});
		$("#uyxSalaryFee").on("input",function(){
			inputUMembershipFeeOrSalaryFee();
		});
    });

    //查询审批人
    function queryGuanjiaName() {
        var ctx = $("#ctx").val();
        var deptId = "";
        var cateType = '${param.cateType}';
        if (cateType == 2) {
            deptId = $("#uyxApproveDeptSelect option:selected").val();
        } else {
            deptId = $("#udcApproveDeptSelect option:selected").val();
        }
        if (deptId == "" || deptId == null) {
            if (cateType == 2) {
                $("#uyxApproveBySelect").empty();
                $("#uyxApproveBySelect").html("<option value=''>...请选择...</option>");
            } else {
                $("#udcApproveBySelect").empty();
                $("#udcApproveBySelect").html("<option value=''>...请选择...</option>");
            }
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
                    if (cateType == 2) {
                        $("#uyxApproveBySelect").html(html);
                        $("#uyxApproveBySelect").siblings("div").remove();
						$("#uyxApproveBySelect").checkSelect();
                    } else {
                        $("#udcApproveBySelect").html(html);
                        $("#udcApproveBySelect").siblings("div").remove();
						$("#udcApproveBySelect").checkSelect();
                    }
                }
            });
        }
    }


    //非白条预存服务费可用金额
    function queryNotIousSalaryMoney(orderId) {
        var total = 0;
        $.ajax({
            url: ctx + "/afterFees/queryNotIousSalaryMoney",
            data: {"orderId": orderId},
            type: "POST",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        total += v.feeSumC;
                    })
                    $("#notIousSalaryMoney_hidden").html(JSON.stringify(data.list));
                    $("#notIousSalaryMoney").html(total);
                } else {
                    $("#notIousSalaryMoney").html(total);
                }
            }
        })
        return total;
    }
    //查询总手续费
    function queryPayMoney(orderId) {
        var money = 0;
        $.ajax({
            url: ctx + "/afterFees/queryPayMoney",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "orderId": orderId
            },
            success: function (data) {
                if (data.msg == "00" && data.payfeeMoney != null) {
                    money = data.payfeeMoney;
                    $("#payfeeMoney").html(money);
                } else {
                    $("#payfeeMoney").html(money);
                }
            }
        })
        return money;
    }

    //查询扣减已缴手续费
    function queryAccountPay(orderId) {
        var money = 0;
        $.ajax({
            url: ctx + "/afterFees/queryAccountPay",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "orderId": orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    money = data.accountAmount;
                    $("#accountAmount").html(money);
                } else {
                    $("#accountAmount").html(money);
                }
            }
        })
        return money;
    }

    //查询信息费可退金额
    function queryMembershipFee(orderId) {
        var total = 0;
        $.ajax({
            url: ctx + "/afterFees/queryMembershipFee",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "orderId": orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        total += v.feeSumC;
                    })
                    $("#MembershipFee").html(total);
                    $("#MembershipFee_hidden").html(JSON.stringify(data.list))
                } else {
                    $("#MembershipFee").html(total);
                }
            }
        })
        return total;
    }


    //查询分期手续费
    function queryInstallmentFee(orderId) {
        var total = 0;
        $.ajax({
            url: "/order/afterFees/queryInstallmentFee",
            type: "POST",
            dataType: "json",
            async: false,
            data: {
                "orderId": orderId
            },
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        total += v.feeSumC;
                    })
                    $("#installmentFee").html(total);
                    $("#installmentFee_hidden").html(JSON.stringify(data.list))
                } else {
                    $("#installmentFee").html(total);
                }
            }
        })
        return total;
    }
</script>
</html>

