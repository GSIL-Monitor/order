<%@ page language="java"
	import="java.util.*,com.emotte.server.util.CookieUtils"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link href="${ctx}/css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	color: #000 !important;
	font-size: 12px !important;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif !important;
}
</style>
<title>新增缴费</title>
<script type="text/javascript">
	var orderId = '${param.orderId}';
	var cateType = '${param.cateType}';
	var accountId = '${param.accountId}';
	var accountAmount = '${param.accountAmount}';
	var userMobile = '${param.userMobile}';
	var userId = '${param.userId}';
	var payStatus = '${param.payStatus}';
	var flag = '${param.flag}';
	var orderChannel = '${param.orderChannel}';
	var payType = '${param.payType}';//结算单类型
	$(function() {
		if (flag == 1) {
			$("#newPayFee").show();
			$("#editPayFee").hide();
		} else {
			$("#newPayFee").hide();
			$("#editPayFee").show();
		}
		var orderId=$("#checkedOrderId").val();
		var resultType=selectProductThreeCode(orderId);
		if(resultType==0){
			//显示
			$("#labelDJKZF").removeAttr("style");
		}
	});
	/** 根据订单Id 查询出此订单所有的三级 商品分类码   周鑫  2019-01-21  **/
	function selectProductThreeCode(orderId){
		var result=1;
		$.ajax({
			url : ctx + "/afterSales/selectProductThreeCode",
			data : {
				orderId : orderId,
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					result=data.count;
				}
			}
		});
		return result;
	}
	/*--------------------手机发送验证码操作-----------------------*/
	var InterValObj; //timer变量，控制时间
	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数
	var codeLength = 6;//验证码长度
	var veriCode = "";
	function sendMessage() {
		curCount = count;
		if (userMobile != "") {
			//设置button效果，开始计时
			$("#btnSendCode").attr("disabled", "true");
			$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
			InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
			$.ajax({
				type : "post", //用POST方式传输
				dataType : "json", //数据格式:JSON
				url : ctx + "/custSolution/sendMessage", //目标地址
				data : {
					phone : ""
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					showMsg("发送验证码失败！");
				},
				success : function(data) {
					if (data.msg == "00") {
						veriCode = data.code;
						//$("#checkCode").val(veriCode);
					}
				}
			});
		} else {
			showMsg("手机号码不能为空！");
		}
	}
	//timer处理函数
	function SetRemainTime() {
		if (curCount == 0) {
			window.clearInterval(InterValObj);//停止计时器
			$("#btnSendCode").removeAttr("disabled");//启用按钮
			$("#btnSendCode").val("重新发送验证码");
			code = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
		} else {
			curCount--;
			$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
		}
	}

	//白条支付，只针对正常用户
	var managerType =
<%=CookieUtils.getJSONKey(request, "managerType")%>
	;
	$("#managerTypePayfee").val(managerType);
</script>
</head>
<body id="orderBasicPayFee">
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<input type="hidden" id="managerTypePayfee" />
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="模态框" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="newPayFee">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">新增缴费</h4>
					<span style="font-size: 12px; color: red; padding-left: 0px;">注意：为保证顺利对账，请确保您录入的缴费的【交易时间】、【缴费方式】和客户真实的【交易时间】、【缴费方式】保持一致。</span>
				</div>
				<div class="modal-header" id="editPayFee">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">修改缴费</h4>
					<span style="font-size: 12px; color: red; padding-left: 0px;">注意：为保证顺利对账，请确保您录入的缴费的【交易时间】、【缴费方式】和客户真实的【交易时间】、【缴费方式】保持一致。</span>
				</div>
				<div class="modal-body new-sort" id="new_sort">
					<form id="payfeeAddForm" action="" class="form-inline"
						method="post">
						<div class="modal-content-basic">
							<div class="info-select clearfix">
								<header>
									<h4>（结算总金额：${param.accountAmount}元）</h4>
								</header>
								<input type="hidden" id="payment_accountpayOrderId" />
								<!-- 缴费对应结算单ID -->
								<input type="hidden" id="payment_solutionOrderId" />
								<!-- 缴费对应订单ID -->
								<input type="hidden" id="payfeeIdTRDS"> <input
									type="hidden" id="TRDSChannelId"> <input type="hidden"
									id="TRDSIsCollection">
								<div class="row">
									<div class="form-group col-xs-12">
										<label> <input type="checkbox" id="checkPayfeePOS"
											name="feePosts" value="20250005"
											onclick="checkPayfees(this);">POS机
										</label><label> <input type="checkbox" id="checkPayfeeRhtxPOS"
											name="feePosts" value="20250008"
											onclick="checkPayfees(this);">融汇天下POS
										</label><label> <input type="checkbox"
											id="checkPayfeeBanktrans" name="feePosts" value="20250002"
											onclick="checkPayfees(this);">银行转账
										</label>
										<!-- <label id="labelPayfeeCard"> 
								<input type="checkbox" id="checkPayfeeCard"  value="20250010" >卡支付
 								</label>  -->
										<label id="labelPayfeeTicket"> <input type="checkbox"
											id="checkPayfeeTicket" value="20250011">券支付
										</label> <label id="labelPayfeeRemain"> <input type="checkbox"
											id="checkPayfeeRemain" value="20250013">账户支付
										</label> <label id="labelPayfeeTRDS"> <input type="checkbox"
											id="checkPayfeeTRDS" name="feePosts" value="20250014"
											onclick="checkPayfees(this);">他人代收
										</label> <label> <input type="checkbox" id="checkPayfeeWechat"
											name="feePosts" value="20250015"
											onclick="checkPayfees(this);">兴业银行微信扫码支付
										</label> <label> <input type="checkbox"
											id="checkJiaLianPosWechat" name="feePosts" value="20250017"
											onclick="checkPayfees(this);">嘉联POS微信支付
										</label> <label> <input type="checkbox"
											id="checkJiaLianPosAliPay" name="feePosts" value="20250018"
											onclick="checkPayfees(this);">嘉联POS支付宝支付
										</label> <label> <input type="checkbox"
											id="checkJiaLianPosBank" name="feePosts" value="20250019"
											onclick="checkPayfees(this);">嘉联POS刷卡支付
										</label>
										<%
											if (CookieUtils.getJSONKey(request, "managerType").equals("2")) {
										%>
										<label> <input type="checkbox" id="checkBaiTiaoPayfee"
											name="feePosts" value="20250016"
											onclick="checkPayfees(this);">白条支付
										</label>
										<%
											}
										%>
										<label id="labelPayfeeInstallment"> <input
											type="checkbox" id="checkPayfeeInstallment" name="feePosts"
											value="20250027" onclick="checkPayfees(this);">唯品会白条
										</label> <label id="labelPayfeeCMB"> <input type="checkbox"
											id="checkPayfeeCMB" name="feePosts" value="20250031"
											onclick="checkPayfees(this);">招行分期
										</label> <label id="labelHjbl"> <input type="checkbox"
											id="checkHjbl" name="feePosts" value="20250033"
											onclick="checkPayfees(this);">管家帮分期
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongWechat" name="feePosts" value="20250036"
											onclick="checkPayfees(this);">联动微信支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongPosWechat" name="feePosts" value="20250037"
											onclick="checkPayfees(this);">联动POS微信支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongPosBank" name="feePosts" value="20250038"
											onclick="checkPayfees(this);">联动POS刷卡支付
										</label> <label id=""> <input type="checkbox"
											id="checkZhangYiWechat" name="feePosts" value="20250039"
											onclick="checkPayfees(this);">掌易收微信支付
										</label> <label id=""> <input type="checkbox"
											id="checkZhangYiAliPay" name="feePosts" value="20250040"
											onclick="checkPayfees(this);">掌易收支付宝支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongPosAliPay" name="feePosts" value="20250041"
											onclick="checkPayfees(this);">联动POS支付宝支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongweixinPay" name="feePosts" value="20250042"
											onclick="checkPayfees(this);">联动微信扫码支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongAliPay" name="feePosts" value="20250043"
											onclick="checkPayfees(this);">联动支付宝扫码支付
										</label> <label id=""> <input type="checkbox"
											id="checkLianDongJingTai" name="feePosts" value="20250046"
											onclick="checkPayfees(this);">联动静态扫码支付
										</label> <label id=""> <input type="checkbox"
											id="checkGuanJiaBangShouKuanMa" name="feePosts"
											value="20250052" onclick="checkPayfees(this);">管家帮收款码
										<!-- </label> <label id="labelDJKZF" style="display:none;"> <input type="checkbox"
											id="checkGuanJiaKaZhiFu" name="feePosts" value="20250010"
											onclick="checkPayfees(this);" >管家卡支付
										</label> -->
									</div>
								</div>
								<div class="row mb10">
									<div class="form-group col-xs-12">
										<label> <!-- <span style="color:red!important;">*</span> -->
											<p>缴费时间：</p> <input id="payment_feeToDate" name="feeToDate"
											class="Wdate form-control" type="text"
											onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
										</label>
									</div>
								</div>

							</div>
						</div>
						<div id="payfeePos" style="display: none;" name="payFeeDiv">
							<header>
								<h4>POS机</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdPos">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important;">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumPos"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<!-- <label>
                                    <span style="color:red!important;">*</span><p>流水号：</p>
                                    <input type="text" id="bankFlowNumPos" name="bankFlowNumPos" class="form-control">
                                </label> -->
											<label> <span style="color: red !important">*</span>
											<p>卡号后四位：</p> <input type="text" id="postNumPos"
												name="postNumPos" class="form-control"
												style="ime-mode: Disabled" maxlength="4"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editPayfeePos"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250005);" style="display: none">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important;">*</span>
											<p>交易参考号：</p> <input type="text" id="bankFlowNumPos"
												name="bankFlowNumPos" class="form-control"
												style="ime-mode: Disabled"
												onkeyup="value=value.replace(/[^\A-Za-z0-9]+$/g,'')">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 112px; display: inline-block; font-weight: bold;">
															<span style="color: red !important;">*</span>pos凭条:
														</p></label> <input type="file" id="file_pos" name="file"
														class="button-file hide" /> <img id="img_file_pos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_pos')"> <span
														class="pull-left" id="img_url_file_pos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="payfeeRhtxPos" style="display: none;" name="payFeeDiv">
							<header>
								<h4>融汇天下POS</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdRhtxPos">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumRhtxPos"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="bankFlowNumRhtxPos"
												name="bankFlowNumRhtxPos" class="form-control"
												style="ime-mode: Disabled">
											</label> <label> <span style="color: red !important">*</span>
											<p>终端编号：</p> <input type="text" id="postTerminalNoRhtxPos"
												name="postTerminalNoRhtxPos" class="form-control"
												style="ime-mode: Disabled">
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editPayfeeRhtxPos"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250008);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">
															<span style="color: red !important;">*</span>pos凭条:
														</p></label> <input type="file" id="file_rhtxpos" name="file"
														class="button-file hide" /> <img id="img_file_rhtxpos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_rhtxpos')"> <span
														id="img_url_file_rhtxpos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="payfeeBanktrans" style="display: none;" name="payFeeDiv">
							<header>
								<h4>银行转账</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdBanktrans">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumBanktrans"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> &nbsp;&nbsp;
												<p>银行：</p> <input type="text" id="postBankBanktrans"
												name="postBankBanktrans" class="form-control">
											</label> <label> &nbsp;&nbsp;
												<p>流水号：</p> <input type="text" id="bankFlowNumBanktrans"
												name="bankFlowNumBanktrans" class="form-control"
												style="ime-mode: Disabled" placeholder="无流水号，填写银行名称">
											</label> <label> <span style="color: red !important">*</span>
											<p>汇款人：</p> <input type="text" id="postUserBanktrans"
												name="postUserBanktrans" class="form-control">
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editPayfeeBanktrans"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250002);" style="display: none">
											</label>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div id="payfeeWechat" style="display: none;" name="payFeeDiv">
							<header>
								<h4>微信扫码(兴业银行)</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdWechat">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumWechat"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label> <label> <span style="color: red !important">*</span>
											<p style="width: 120px;">订单编号(后五位)：</p> <input type="text"
												id="bankFlowNumWechat" name="bankFlowNumWechat"
												class="form-control" style="ime-mode: Disabled"
												maxlength="5"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editPayfeeWechat"
												class="btn btn-primary btn-xs" name="editPayfee" value="保存"
												onclick="editPayfeeSave(20250015);" style="display: none">
											</label>
										</div>
									</div>

								</div>
							</div>
						</div>
						<div id="payfeeCard" style="display: none;" name="payFeeDiv">
							<header>
								<h4>卡支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdCard">
										<div class="form-group col-xs-12">
											<label> <span style="color: red">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumCard"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
											<!-- <label>
									<span style="color:red">*</span><p>验证码：</p>
									<input type='text' id='checkCode' name='checkCode' class='form-control' /> 
									<input class='xinzeng btn btn-sm btn-default' id='btnSendCode' type='button' value='发送验证码' onclick='sendMessage()' />
								</label> -->

										</div>
									</div>
									<!-- <div class="row" >
                            <div class="form-group col-xs-12">
                            	<table id='cardListBody'>
                            		
                            	</table>
                            </div>
                        </div> -->
									<!--                         <div class="row" > -->
									<!--                             <div class="form-group col-xs-12"> -->
									<!--                             	<a helf='javascript:void(0);' class='xinzeng btn btn-sm btn-default' onclick='getCardNumDiv();' >新增礼品卡</a> -->
									<!--                             </div> -->
									<!--                         </div> -->
									<!-- <div id="cardBindDiv" style=" display:none;">
							<div class='row'>
								<div class='form-group col-xs-12'>
									<label><span style="color:red">*</span><p>卡号：</p>
										<input type='text' id='payment_cardNumb_bind' name='cardNumb' class='form-control'  style="ime-mode:Disabled" />
									</label>
								</div>
							</div>

							<div class='row'>
								<div class='form-group col-xs-12'>
									<label><span style="color:red">*</span><p>密码：</p>
										<input type='text' id='payment_cardPass_bind' name='cardPass' class='form-control' />
										<a helf='' class='xinzeng btn btn-sm btn-default' onclick='bingCard();'>提交</a>
									</label>
								</div>
							</div>
						</div> -->
								</div>
							</div>
						</div>
						<div id="payfeeTicket" style="display: none;" name="payFeeDiv">
							<header>
								<h4>券支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdTicket">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumTicket"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="payfeeRemain" style="display: none;" name="payFeeDiv">
							<header>
								<h4>账户支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdRemain">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumRemain"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="payfeeTRDS" style="display: none;" name="payFeeDiv">
							<header>
								<h4>他人代收</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red">*</span>
											<p>订单金额：</p> <input type="text" id="feeSumTRDS_DC"
												name="feeSumAll" class="form-control" readonly="readonly" />
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>平台实付金额：</p> <input type="text" id="feeSumTRDS_PT"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>代收渠道：</p> <input type="text" id="feeSumTRDS_channel"
												class="form-control" readonly="readonly" />
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>平台订单编号：</p> <input type="text" id="feeSumTRDS_orderId"
												name="platformOrderId" class="form-control">
											</label>
										</div>
									</div>
									<div class="row">
										<label><span
											style="font-size: 12px; color: red; padding-left: 20px;">据业务规定,
												平台实付金额暂时统一由渠道经理整理, 无需管家填写.</span></label>
									</div>
								</div>
							</div>
						</div>
						<div id="jiaLianPosWechat" style="display: none;" name="payFeeDiv">
							<header>
								<h4>嘉联POS微信支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="jiaLianIdPosWechat">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="jialianPosWechatFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="jialianWechatPostNum"
												name="jialianWechatPostNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button"
												id="editJiaLianPosWechat" name="editPayfee"
												class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250017);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">&nbsp;&nbsp;pos凭条:</p></label>
													<input type="file" id="file_wechatpos" name="file"
														class="button-file hide" /> <img id="img_file_wechatpos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_wechatpos')"> <span
														id="img_url_file_wechatpos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="jiaLianPosAliPay" style="display: none;" name="payFeeDiv">
							<header>
								<h4>嘉联POS支付宝支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="jiaLianIdPosAliPay">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="jialianPosAliPayFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="jialianAliPayPostNum"
												name="jialianAliPayPostNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button"
												id="editJiaLianPosAliPay" name="editPayfee"
												class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250018);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">&nbsp;&nbsp;pos凭条:</p></label>
													<input type="file" id="file_alipaypos" name="file"
														class="button-file hide" /> <img id="img_file_alipaypos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_alipaypos')"> <span
														id="img_url_file_alipaypos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="jiaLianPosBank" style="display: none;" name="payFeeDiv">
							<header>
								<h4>嘉联POS刷卡支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="jiaLianIdPosBank">
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="jialianPosBankFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="jialianBankPostCKNum"
												name="jialianBankPostCKNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> &nbsp;&nbsp;
												<p>卡号后四位：</p> <input type="text" id="jialianBankPostNum"
												name="jialianBankPostNum" class="form-control"
												style="ime-mode: Disabled" maxlength="4"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editJiaLianPosBank"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250019);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">
															<span style="color: red !important;">*</span>pos凭条:
														</p></label> <input type="file" id="file_cardpos" name="file"
														class="button-file hide" /> <img id="img_file_cardpos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_cardpos')"> <span
														id="img_url_file_cardpos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
							if (CookieUtils.getJSONKey(request, "managerType").equals("2")) {
						%>
						<div id="payfeeBaitiao" style="display: none;" name="payFeeDiv">
							<header>
								<h4>白条支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="baitiaoIdPayfee">
									<!-- <input type="hidden" id="baitiaoState" > -->
									<!-- <input type="hidden" id="baitiaoAvaliable" > -->
									<!-- 获取解决方案用户白条信息 -->
									<div class="row" id="payfeeBaitiaoNull">
										<div class="form-group col-xs-12">
											<label><span style="color: red">该用户白条支付未激活！</span></label>
										</div>
									</div>
									<div class="row" id="payfeeBaitiaoNotNull">
										<div class="form-group col-xs-12">
											<label> <span style="color: red">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumBaitiao"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<%
							}
						%>
						<div id="gjbInstallmentDiv" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>唯品会白条</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="gjbInstallmentId">
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red">*</span>
											<p>缴费金额：</p> <input type="text" id="gjbInstallmentFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label> <span style="color: red">*</span>
											<p>唯品会订单号：</p> <input type="text"
												id="gjbInstallment_Agreement" name="feeSumAll"
												class="form-control" />
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>签约人姓名：</p> <input type="text"
												id="gjbInstallment_vipShopName"
												name="gjbInstallment_vipShopName" class="form-control">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="CMBDiv" style="display: none;" name="payFeeDiv">
							<header>
								<h4>招行分期</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="CMBId">
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="CMBFee" name="feeSumAll"
												class="form-control" style="ime-mode: Disabled"
												maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>交易流水码/订单号：</p> <input type="text" id="CMBOrderId"
												name="feeSumAll" class="form-control" />
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="hjbl_div" style="display: none;" name="payFeeDiv">
							<header>
								<h4>管家帮分期</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>扣款金额：</p> <input type="text" id="hjblFee" name="hjblFee"
												class="form-control" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>扣款人姓名：</p> <input type="text" id="hjblName"
												name="hjblName" class="form-control">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="lianDongWechatDiv" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动微信支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="jiaLianIdWechat">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongWechatFee"
												name="lianDongWechatFee" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="lianDongWechatNum"
												name="lianDongWechatNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editJiaLianWechat"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250036);" style="display: none">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="lianDongPosWechatDiv" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动POS微信支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="lianDongIdPosWechat">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongPosWechatFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label>
												<p>交易参考号：</p> <input type="text" id="lianDongWechatPostNum"
												name="lianDongWechatPostNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button"
												id="editlianDongPosWechat" name="editPayfee"
												class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250037);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">pos凭条:</p></label>
													<input type="file" id="file_Ldwechatpos" name="file"
														class="button-file hide" /> <img
														id="img_file_Ldwechatpos" alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_Ldwechatpos')"> <span
														id="img_url_file_Ldwechatpos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="lianDongPosBankDiv" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动POS刷卡支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="lianDongIdPosBank">
									<div class="row">
										<div class="form-group col-xs-6">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongPosBankFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
										<div class="form-group col-xs-6">
											<label>
												<p>交易参考号：</p> <input type="text" id="lianDongBankPostCKNum"
												name="lianDongBankPostCKNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> &nbsp;&nbsp;
												<p>卡号后四位：</p> <input type="text" id="lianDongBankPostNum"
												name="lianDongBankPostNum" class="form-control"
												style="ime-mode: Disabled" maxlength="4"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editlianDongPosBank"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250038);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">pos凭条:</p></label>
													<input type="file" id="file_Ldcardpos" name="file"
														class="button-file hide" /> <img id="img_file_Ldcardpos"
														alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_Ldcardpos')"> <span
														id="img_url_file_Ldcardpos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="zhangYiWechatDiv" style="display: none;" name="payFeeDiv">
							<header>
								<h4>掌易收微信支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="zhangYiIdWechat">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="zhangYiWechatFee"
												name="zhangYiWechatFee" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="zhangYiWechatNum"
												name="zhangYiWechatNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editzhangYiWechat"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250039);" style="display: none">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="zhangYiAliPayDiv" style="display: none;" name="payFeeDiv">
							<header>
								<h4>掌易收支付宝支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="zhangYiIdAliPayt">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="zhangYiAliPayFee"
												name="zhangYiAliPayFee" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>交易参考号：</p> <input type="text" id="zhangYiAliPayNum"
												name="zhangYiAliPayNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button" id="editZhangYiAliPay"
												name="editPayfee" class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250040);" style="display: none">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="lianDongPosAliPay" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动POS支付宝支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="lianDongIdPosAliPay">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongPosAliPayFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-xs-10">
											<label>
												<p>交易参考号：</p> <input type="text" id="lianDongAliPayPostNum"
												name="lianDongAliPayPostNum" class="form-control"
												style="ime-mode: Disabled"
												onkeyup='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'
												onpaste='this.value=this.value.replace(/[^\A-Za-z0-9]/g,"")'>
											</label>
										</div>
										<div class="form-group col-xs-2">
											<label> <input type="button"
												id="editlianDongPosAliPay" name="editPayfee"
												class="btn btn-primary btn-xs" value="保存"
												onclick="editPayfeeSave(20250041);" style="display: none">
											</label>
										</div>
									</div>
									<div class="media-left  col-md-12"
										style="padding-left: 0; width: 100%;">
										<div class="form-group col-xs-12">
											<div class="modify_mess">
												<p class="clearfix int">
													<label><p
															style="width: 130px; display: inline-block; font-weight: bold;">pos凭条:</p></label>
													<input type="file" id="file_Ldalipaypos" name="file"
														class="button-file hide" /> <img
														id="img_file_Ldalipaypos" alt="点击上传图片"
														style="border-radius: 4px; border: 1; width: 200px; height: 100px; margin: -22px;"
														src="${ctx}/images/big.png"
														onclick="uploadfile('file_Ldalipaypos')"> <span
														id="img_url_file_Ldalipaypos" type="hidden"
														style="display: none;"></span> <span
														style="font-size: 12px; color: red !important; padding-left: 0px; margin-right: 36%; line-height: 58px;"
														class="pull-right">请上传JPG格式的图片!</span>
												</p>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="lianDongWeixinPay" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动微信扫码支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="lianDongIdWeixinPay">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongWeixinPayFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="lianDongAliPay" style="display: none;" name="payFeeDiv">
							<header>
								<h4>联动支付宝扫码支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<input type="hidden" id="lianDongIdAliPay">
									<div class="row">
										<div class="form-group col-xs-12">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="lianDongAliPayFee"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div id="payfeeLanDongJingTai" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>联动静态扫码支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdLanDongJingTai">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text" id="feeSumLanDongJingTai"
												name="feeSumAll" class="form-control"
												style="ime-mode: Disabled" maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label> <label> <span style="color: red !important">*</span>
											<p style="width: 120px;">商户订单号(后五位)：</p> <input type="text"
												id="bankFlowNumLanDongJingTai" name="bankFlowNumWechat"
												class="form-control" style="ime-mode: Disabled"
												maxlength="5"
												onkeyup='this.value=this.value.replace(/\D/gi,"")'>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>


						<div id="payfeeGuanJiaBangShouKuanMa" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>管家帮收款码</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdGuanJiaBangShouKuanMa">
										<div class="form-group col-xs-10">
											<label> <span style="color: red !important">*</span>
											<p>缴费金额：</p> <input type="text"
												id="feeSumGuanJiaBangShouKuanMa" name="feeSumAll"
												class="form-control" style="ime-mode: Disabled"
												maxlength="9"
												onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
											</label> <label> <span style="color: red !important">*</span>
											<p style="width: 120px;">交易参考号(后六位)：</p> <input type="text"
												id="bankFlowNumGuanJiaBangShouKuanMa"
												name="bankFlowNumWechat" class="form-control"
												style="ime-mode: Disabled" maxlength="6"
												placeholder="流水号/商品 后六位">
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div id="payfeeGuanJiaKaZhiFu" style="display: none;"
							name="payFeeDiv">
							<header>
								<h4>管家卡支付</h4>
							</header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
										<input type="hidden" id="payfeeIdGuanJiaKaZhiFu">
										<div class="form-group col-xs-12">
											<label>
												<p style="width: 130px;">缴费金额：</p> <input type="number"
												id="feeSumGuanJiaKaZhiFu" class="form-control" style="ime-mode: Disabled"
												 onkeypress="if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d\d{1}$/.test(value))event.returnValue=false"  >
											</label>
										</div>
										<div class="form-group col-xs-12">
											<label>
												<p style="width: 130px;">绑卡手机号：</p> <input type="text"
												class="form-control"  id="empMobile" maxlength="11">
												<button type="button" class="btn btn-primary btn-xs"
													id="addQueryCard"
													onclick="queryCard()">查询卡</button>
											</label>
										</div>
										<div class="form-group col-xs-12">
											<label>
												<p style="width: 130px;">
													<span style="color: red">*</span>管家卡：
												</p> <select class="form-control" name="card" id="card"
												onclick="putMobile('card','empMobile')" onblur="hideTitle()"></select>
												<button type="button" class="btn btn-primary btn-xs"
													id="addCardButton"
													onclick="sendVerificationCode('card','empMobile','addCardButton','addQueryCard')">获取验证码</button>
												<span id="span_card" style="display: none;">管家卡不能为空</span>
											</label>
										</div>
										<div class="form-group col-xs-12">
											<label>
												<p style="width: 130px;">
													<span style="color: red">*</span>验证码：
												</p> <input type="text" class="form-control" value=""
												id="verificationCode"> <input type="hidden"
												class="form-control" value="1" id="effective">
												<button type="button" class="btn btn-primary btn-xs" onclick="verification('verificationCode','card','effective')">验证有效性</button>
												<input type="hidden" id="formController" value="01" />
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">关闭</button>
							<button type="button" class="btn btn-primary"
								id="insertPayfeeButton" onclick="insertPayfee()">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="${ctx}/js/jquery-extends-base.js"></script>
<script type="text/javascript" src="${ctx}/js/ajax-fileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/orderPayfee.js"></script>
<script type="text/javascript">
	$(function() {
		$("#labelPayfeeTicket").hide();
		$("#labelPayfeeRemain").hide();
		$("#labelPayfeeCard").hide();
		checkPayfeeChannel(orderChannel);
		var isCollection = $("#TRDSIsCollection").val();
		if (isCollection != "" && isCollection != null && isCollection != 1) {
			$("#labelPayfeeTRDS").hide();
		}
		if (payType != 3) {
			$("#labelPayfeeInstallment").hide();
		}

		if (payType == 10) {
			$("#labelPayfeeCMB").hide();
			$("#labelHjbl").hide();
		}

		if (payType == 5 || payType == 8 || payType == 11 || payType == 12) {
			$("#labelPayfeeCMB").hide();
			$("#labelHjbl").hide();
			$("#labelPayfeeInstallment").hide();
		}

		/**管家帮分期(海金保理),只对指定账号开发20180906*/
		function getPayFeeLoginby() {
			var manager = JSON.parse($.cookie('manager'));
			if (manager.id == '1') {
				$("#labelHjbl").show();
			} else {
				$("#labelHjbl").hide();
			}
		}
		getPayFeeLoginby();
		queryOrderAgreement(orderId);
		queryPayfeeByAccountId();

		if (flag == 1) {
			$("#checkPayfeeBanktrans").attr("checked", "checked");
			$("#payfeeBanktrans").css("display", "block");
			$("#checkPayfeeTicket").css("display", "none");
			$("#checkPayfeeRemain").css("display", "none");
			$("#checkPayfeeCard").css("display", "none");
			$("#payfeeCard").hide();
			$("#payfeeTicket").hide();
			$("#payfeeRemain").hide();
			$("#labelPayfeeCard").hide();
			$("#labelPayfeeTicket").hide();
			$("#labelPayfeeRemain").hide();
		}
		/* else{
			$("#payment_feeToDate").attr("disabled",true);
		} */
		if (payStatus == 20110002 || payStatus == 20110003) {
			$("input[name=feeSumAll]").prop('disabled', 'disabled');
			$("input[type=checkbox]").prop('disabled', 'disabled');
		}
		if (payStatus == 20110001) {
			$("#insertPayfeeButton").show();
		} else if (payStatus == 20110002 || payStatus == 20110003) {
			$("#insertPayfeeButton").css("display", "none");
		}
	});
	//获取验证码
	function sendVerificationCode(id,tId,bId,qId) {
		var mobile = $("#empMobile").val();
		var card=$("#card").val();
		if(card=='1'){
			$.alert({text : "请先选择要扣费的卡"});
			return;
		}
		if(mobile == undefined){
			$.alert({text : "请选择管家卡后再获取验证码"});
			return;
		}
		$.ajax({
			
			url : ctx + "/payfee/sendVerifyCode",
			data : {
				mobile : mobile
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					countDown('addCardButton');
					$.alert({millis:2000,text : "获取成功"});
					$("#empMobile").attr("disabled","disabled");  
					$("#addQueryCard").attr("disabled","disabled");  
					$("#card").attr("disabled","disabled");
					$("#formController" ).val("03");
				}else{
					$.alert({millis:2000,text : "获取失败请稍后再试！"});
				}
			}
		});
	}
	//验证验证码
	function verification(id,tId,vId) {
		var mobile =$("#empMobile").val();
		var code = $("#verificationCode").val();
		var formController=$("#formController" ).val();
		if(formController!='03'){
			$.alert({text : "请先获取验证码"});
			return;
		}
		if(!code){
			$.alert({text : "请输入验证码"});
			return;
		}
		$.ajax({
			url : ctx + "/payfee/verifyCode",
			data : {
				mobile : mobile,
				code : code
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$.alert({millis:2000,text : "验证成功"});
					$("#"+id).attr("disabled","disabled");  
					$("#formController" ).val("00");
				}else{
					$.alert({millis:2000,text : "验证码错误，请重新输入"});
				}
			}
		});
	}
	//倒计时
	var buttonId;
	function countDown(id){
		var timer;
		var time = 60;
		buttonId = id;
		timer = setInterval(function() {
			time--;
			if(time == 0) {
				clearInterval(timer);
				$('#'+id).html("获取验证码");
				$('#'+id).attr("disabled", false);
			} else {
				$('#'+id).html(time + "秒后重试");
				$('#'+id).attr("disabled", true);
			}
		}, 1000)
	}
	
	
	
	//查询卡
	function queryCard() {
		var mobile =$("#empMobile").val();
		var oId=$("#checkedOrderId").val();
		if(mobile==""||mobile==null){
			$.alert({text : "请先输入手机号"});
			return;
		}
		if(mobile.length<11){
			$.alert({text : "请先输入正确的手机号"});
			return;
		}
		var html = "";
		html += "<option value='1'>--请选择--</option>";
		$.ajax({
			url : ctx + "/afterSales/cardByProductCodesAndMobile",
			data : {
				bindUserMobile : mobile,
				orderId:oId,
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					var a=data.list;
					$.each(a,function(i,v){
						html += "<option value='"+v.numb+"'>"+v.numb +"(￥"+ v.money +")"+"</option>";
					});
				}
				
			}
		});
		$("#card").append(html);
	}
</script>
</html>