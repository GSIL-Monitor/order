<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增缴费</title>
<script type="text/javascript">
	var orderId = '${param.orderId}';
	var cateType = '${param.cateType}';
	var accountId = '${param.accountId}';
	var accountAmount = '${param.accountAmount}';
	var userMobile = '${param.userMobile}';
	var payStatus = '${param.payStatus}';
	var flag = '${param.flag}';
	$(function(){
		if(flag == 1){
			$("#newPayFee").show();
			$("#editPayFee").hide();
		}else{
			$("#newPayFee").hide();
			$("#editPayFee").show();
		}
	});
	
	/*--------------------手机发送验证码操作-----------------------*/
	var InterValObj; //timer变量，控制时间
	var count = 60; //间隔函数，1秒执行
	var curCount;//当前剩余秒数
	var codeLength = 6;//验证码长度
	var veriCode = "";
	function sendMessage() {
		curCount = count;
		if(userMobile != ""){
			//设置button效果，开始计时
			$("#btnSendCode").attr("disabled", "true");
			$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
			InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
			$.ajax({
				type: "post", //用POST方式传输
				dataType: "json", //数据格式:JSON
				url: ctx + "/custSolution/sendMessage", //目标地址
				data: {phone : phone},
				error: function (XMLHttpRequest, textStatus, errorThrown) {showMsg("发送验证码失败！");},
				success: function (data){ 
					if(data.msg=="00"){
						veriCode = data.code;
						//$("#checkCode").val(veriCode);
					}
				}
			});
		}else{
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
		}
		else {
			curCount--;
			$("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
		}
	}
</script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header" id="newPayFee">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">新增缴费</h4>
            </div>
            <div class="modal-header" id="editPayFee">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">修改缴费</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <form id="payfeeAddForm" action="" class="form-inline" method="post" >
	            <div class="modal-content-basic">
	            	<div class="info-select clearfix">
                        <header><h4>（结算总金额：${param.accountAmount}元）</h4></header>
                    	<input type="hidden" id="payment_accountpayOrderId" /><!-- 缴费对应结算单ID -->
                    	<input type="hidden" id="payment_solutionOrderId" /><!-- 缴费对应订单ID -->
                        <div class="row">
                            <div class="form-group col-xs-12">
								<label>
								<input type="checkbox" id="checkPayfeePOS" name="feePosts" value="20250005" onclick="checkPayfeesThree(this);">POS机 
								</label><label>
								<input type="checkbox" id="checkPayfeeRhtxPOS" name="feePosts" value="20250008" onclick="checkPayfeesThree(this);">融汇天下POS
								</label><label>
								<input type="checkbox" id="checkPayfeeBanktrans" name="feePosts" value="20250002" onclick="checkPayfeesThree(this);">银行转账
								</label>
<!-- 								<label> -->
<!-- 								<input type="checkbox" id="checkPayfeeCard" name="feePosts" value="20250010" onclick="checkPayfeesThree(this);">卡缴费  -->
<!-- 								</label> -->
								<label>
								<input type="checkbox" id="checkPayfeeWechat" name="feePosts" value="20250015" onclick="checkPayfeesThree(this);">微信扫码支付 
								</label>
								<label>
								<input type="checkbox" id="checkPayfeeOthers" name="feePosts" value="20250014" onclick="checkPayfeesThree(this);">他人代收 
								</label>
							</div>
                        </div>
                        <div class="row mb10" >
                        	<div class="form-group col-xs-12">
	                       		<label>
                                   <span style="color:red">*</span><p>缴费时间：</p>
                                   <input id="payment_feeToDate" name="feeToDate" class="Wdate form-control" type="text" 
                                   	 	onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
	                            </label>
                            </div>
                        </div>
                        
                        </div>
                        </div>
                        <div id="payfeePos" style="display:none;">
                        <header><h4>POS机</h4></header>
                        <div class="modal-content-basic">
	                	<div class="info-select clearfix">
                        <div class="row" >
                        	<input type="hidden" id="payfeeIdPos" >
                            <div class="form-group col-xs-12">
                                <label>
                                    <span style="color:red">*</span><p>缴费金额：</p>
                                    <input type="text" id="feeSumPos" name="feeSumAll" class="form-control"    style="ime-mode:disabled" 
                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
                                </label>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="form-group col-xs-10">
                                <!-- <label>
                                    <span style="color:red">*</span><p>流水号：</p>
                                    <input type="text" id="bankFlowNumPos" name="bankFlowNumPos" class="form-control">
                                </label> -->
                                <label>
                                    <span style="color:red">*</span><p>卡号后四位：</p>
                                    <input type="text" id="postNumPos" name="postNumPos" class="form-control"  maxlength="4"
                                         onkeyup='this.value=this.value.replace(/\D/gi,"")'    style="ime-mode:disabled" >
                                </label>
                            </div>
                            <div class="form-group col-xs-2">
                                <label>
                                    <input type="button" id="editPayfeePos" name="editPayfee" class="btn btn-primary btn-xs" value="保存" 
                                    	onclick="editPayfeeSaveThree(20250005);" style="display:none">
                                </label>
                            </div>
                        </div>
                        </div>
                        </div>
                        </div>
                        <div id="payfeeRhtxPos"   style="display:none;">
                        <header><h4>融汇天下POS</h4></header>
                        <div class="modal-content-basic">
	                	<div class="info-select clearfix">
                        <div class="row" >
                            <input type="hidden" id="payfeeIdRhtxPos" >
                            <div class="form-group col-xs-12">
                                <label>
                                    <span style="color:red">*</span><p>缴费金额：</p>
                                    <input type="text" id="feeSumRhtxPos" name="feeSumAll" class="form-control"
                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"   style="ime-mode:disabled" >
                                </label>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="form-group col-xs-10">
                                <label>
                                    <span style="color:red">*</span><p>交易参考号：</p>
                                    <input type="text" id="bankFlowNumRhtxPos" name="bankFlowNumRhtxPos" class="form-control"   style="ime-mode:disabled" >
                                </label>
                                <label>
                                    <span style="color:red">*</span><p>终端编号：</p>
                                    <input type="text" id="postTerminalNoRhtxPos" name="postTerminalNoRhtxPos" class="form-control" style="ime-mode:disabled" >
                                </label>
                            </div>
                            <div class="form-group col-xs-2">
                                <label>
                                    <input type="button" id="editPayfeeRhtxPos" name="editPayfee" class="btn btn-primary btn-xs" value="保存" 
                                    	onclick="editPayfeeSaveThree(20250008);" style="display:none">
                                </label>
                            </div>
                        </div>
                        </div>
                        </div>
                        </div>
                        <div id="payfeeBanktrans" style=" display:none;">
                        <header><h4>银行转账</h4></header>
                        <div class="modal-content-basic">
	                	<div class="info-select clearfix">
                        <div class="row" >
                        	<input type="hidden" id="payfeeIdBanktrans" >
                            <div class="form-group col-xs-12">
                                <label>
                                    <span style="color:red">*</span><p>缴费金额：</p>
                                    <input type="text" id="feeSumBanktrans" name="feeSumAll" class="form-control"
                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"   style="ime-mode:disabled" >
                                </label>
                            </div>
                        </div>
                        <div class="row" >
                            <div class="form-group col-xs-10">
                           		<label>
                                    <span style="color:red">*</span><p>银行：</p>
                                    <input type="text" id="postBankBanktrans" name="postBankBanktrans" class="form-control">
                                </label>
                                <label>
                                    <span style="color:red">*</span><p>流水号：</p>
                                    <input type="text" id="bankFlowNumBanktrans" name="bankFlowNumBanktrans" class="form-control"   style="ime-mode:disabled" >
                                </label>
                                <label>
                                    <span style="color:red">*</span><p>汇款人：</p>
                                    <input type="text" id="postUserBanktrans" name="postUserBanktrans" class="form-control">
                                </label>
                            </div>
                            <div class="form-group col-xs-2">
                                <label>
                                    <input type="button" id="editPayfeeBanktrans" name="editPayfee" class="btn btn-primary btn-xs" value="保存" 
                                    	onclick="editPayfeeSaveThree(20250002);" style="display:none">
                                </label>
                            </div>
                        </div>
                        
                        </div>
                        </div>
                        </div>
                        <div id="payfeeWechat" style=" display:none;">
                        <header><h4>微信扫码支付</h4></header>
                        <div class="modal-content-basic">
	                	<div class="info-select clearfix">
                        <div class="row" >
                            <input type="hidden" id="payfeeIdWechat" >
                            <div class="form-group col-xs-10">
                                <label>
                                    <span style="color:red">*</span><p>缴费金额：</p>
                                    <input type="text" id="feeSumWechat" name="feeSumAll" class="form-control"
                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"    style="ime-mode:disabled" >
                                </label>
                           		<label>
                                    <span style="color:red">*</span><p style="width:120px;">订单编号(后四位或后五位)：</p>
                                    <input type="text" id="bankFlowNumWechat" name="bankFlowNumWechat" class="form-control"  maxlength="5"
                                          onkeyup='this.value=this.value.replace(/\D/gi,"")'      style="ime-mode:disabled" >
                                </label>
                            </div>
                            <div class="form-group col-xs-2">
                                <label>
                                    <input type="button" id="editPayfeeWechat" class="btn btn-primary btn-xs" name="editPayfee" value="保存" 
                                    	onclick="editPayfeeSaveThree(20250015);" style="display:none">
                                </label>
                            </div>
                        </div>
                        
                        </div>
                        </div>
                        </div>
                        <div  id="payfeeOthers" style="border-bottom: 1px solid #CCC; display: none;">
                         <header><h4>他人代收</h4></header>
							<div class="modal-content-basic">
								<div class="info-select clearfix">
									<div class="row">
									<input type="hidden" id="payfeeIdOthers" >
								<div class="form-group col-xs-12">
									<label>
										<span style="color:red">*</span><p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumOther" name="threeOrderPayfeeFeeSumOther" class="form-control"
										onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"    style="ime-mode:disabled" >
									</label>
									<label>
										<span style="color:red">*</span><p>汇款人：</p>
										<input type="text" id="threeOrderPayfeePostUserOther" name="threeOrderPayfeePostUserOther" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<span style="color:red">*</span><p>收款单位：</p>
									</label>
									<input type="radio" name="threeOrderPayfeeCollectionEntity" value="1"> 个人
									<input type="radio" name="threeOrderPayfeeCollectionEntity" value="2"> 企业
								</div>
									</div>
								</div>
							</div>
							</div>
                        <div id="payfeeCard" style=" display:none;">
                        <header><h4>礼品卡</h4></header>
                         <div class="modal-content-basic">
	                	<div class="info-select clearfix">
                        <div class="row" >
                            <div class="form-group col-xs-12">
                                <label>
                                    <span style="color:red">*</span><p>缴费金额：</p>
                                    <input type="text" id="feeSumCard" name="feeSumAll" class="form-control"
                                    onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')">
                                </label>
                                <label>
									<span style="color:red">*</span><p>验证码：</p>
									<input type='text' id='checkCode' name='checkCode' class='form-control' /> 
									<input class='xinzeng btn btn-sm btn-default' id='btnSendCode' type='button' value='发送验证码' onclick='sendMessage()' />
								</label>
								
							</div>
                        </div>
                        <div class="row" >
                            <div class="form-group col-xs-12">
                            	<table id='cardListBody'>
                            		
                            	</table>
                            </div>
                        </div>
<!--                         <div class="row" > -->
<!--                             <div class="form-group col-xs-12"> -->
<!--                             	<a helf='javascript:void(0);' class='xinzeng btn btn-sm btn-default' onclick='getCardNumDiv();' >新增礼品卡</a> -->
<!--                             </div> -->
<!--                         </div> -->
						<div id="cardBindDiv" style=" display:none;">
							<div class='row'>
								<div class='form-group col-xs-12'>
									<label><span style="color:red">*</span><p>卡号：</p>
										<input type='text' id='payment_cardNumb_bind' name='cardNumb' class='form-control' />
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
						</div>
						</div>
						</div>
					</div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="insertPayfeeButton" onclick="insertPayfeeThree()">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#checkPayfeeBanktrans").attr("checked","checked");
		$("#payfeeBanktrans").css("display","block");
		queryPayfeeByAccountIdThree();
		if(payStatus==20110002 || payStatus==20110003){
			$("#insertPayfeeButton").css("display","none");
			$("input[name=feeSumAll]").prop('disabled','disabled');
			$("input[type=checkbox]").prop('disabled','disabled');
		}
	});
	function queryPayfeeByAccountIdThree(){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/payfee/queryPayfee",
			data:{
				orderId:accountId,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					var num = $.each(data.list,function(i,v){
						if(i==0){
							$("#payment_feeToDate").val(v.feeToDate);
						}
						if(v.feePost==20250002){
							$("#checkPayfeeBanktrans").attr("checked","checked");
							$("#payfeeBanktrans").css("display","block");
							$("#payfeeIdBanktrans").val(v.id);
							$("#feeSumBanktrans").val(v.feeSum);
							$("#postBankBanktrans").val(v.postBank);
							$("#bankFlowNumBanktrans").val(v.bankFlowNum);
							$("#postUserBanktrans").val(v.postUser);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeBanktrans").css("display","block");
							}
						}else if(v.feePost==20250005){
							$("#checkPayfeePOS").attr("checked","checked");
							$("#payfeePos").css("display","block");
							$("#payfeeIdPos").val(v.id);
							$("#feeSumPos").val(v.feeSum);
							//$("#bankFlowNumPos").val(v.bankFlowNum);
							$("#postNumPos").val(v.postNum);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeePos").css("display","block");
							}
						}else if(v.feePost==20250008){
							$("#checkPayfeeRhtxPOS").attr("checked","checked");
							$("#payfeeRhtxPos").css("display","block");
							$("#payfeeIdRhtxPos").val(v.id);
							$("#feeSumRhtxPos").val(v.feeSum);
							$("#bankFlowNumRhtxPos").val(v.bankFlowNum);
							$("#postTerminalNoRhtxPos").val(v.postTerminalNo);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeRhtxPos").css("display","block");
							}
						}
// 						else if(v.feePost==20250010){
// 							$("#checkPayfeeCard").attr("checked","checked");
// 							$("#payfeeCard").css("display","block");
// 							$("#feeSumCard").val(v.feeSum);
// 						}
						else if(v.feePost==20250015){
							$("#checkPayfeeWechat").attr("checked","checked");
							$("#payfeeWechat").css("display","block");
							$("#payfeeIdWechat").val(v.id);
							$("#feeSumWechat").val(v.feeSum);
							$("#bankFlowNumWechat").val(v.bankFlowNum);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeWechat").css("display","block");
							}
						}else if(v.feePost==20250014){
							//他人代收
							$("#checkPayfeeOthers").attr("checked","checked");
							$("#payfeeOthers").css("display","block");
							$("#threeOrderPayfeeFeeSumOther").val(v.feeSum);
							$("#threeOrderPayfeePostUserOther").val(v.postUser) ;
							$("input[name='threeOrderPayfeeCollectionEntity'][value='"+v.collectionEntity+"']").prop("checked",true);
							$("#payfeeIdOthers").val(v.id);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#checkPayfeeOthers").css("display","block");
							}
						}
					});
				}
			}
		});
	}
	function checkPayfeesThree(fee){
		if(payStatus==20110002){
			return ;
		}
		if(fee.value==20250002){
			if(fee.checked){
				$("#payfeeBanktrans").css("display","block");
			}else{
				$("#payfeeBanktrans").css("display","none");
			}
		}else if(fee.value==20250005){
			if(fee.checked){
				$("#payfeePos").css("display","block");
			}else{
				$("#payfeePos").css("display","none");
			}
		}else if(fee.value==20250008){
			if(fee.checked){
				$("#payfeeRhtxPos").css("display","block");
			}else{
				$("#payfeeRhtxPos").css("display","none");
			}
		}
// 		else if(fee.value==20250010){
// 			if(fee.checked){
// 				$("#payfeeCard").css("display","block");
// 			}else{
// 				$("#payfeeCard").css("display","none");
// 			}
// 			queryPayfeeLpkThree();
// 		}
		else if(fee.value==20250015){
			if(fee.checked){
				$("#payfeeWechat").css("display","block");
			}else{
				$("#payfeeWechat").css("display","none");
			}
		}else if(fee.value==20250014){
			//他人代收
			if(fee.checked){
				$("#payfeeOthers").css("display","block");
			}else{
				$("#payfeeOthers").css("display","none");
			}
		}
	}
	
	function queryPayfeeLpkThree(){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/payfee/queryPayfeeLpk",
			data:{
				orderId:""
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html="";
				if (data.msg =="00") {
					var num = $.each(data.list,function(i,v){
						html += "<tr onclick='checkCardsThree(this);'><td width='120px;'>" ;
						html += "<input type='radio' name='radiopayfeeCard' " ;
						html += "value='" +v.id +"," +v.cardNumb +"," +v.cardBalance +"' >" ;
						html += v.cardNumb +"</td>";
						html += "<td width='120px;'>余额：<span>" +v.cardBalance +"</span>元</td>";
						html += "<td width='120px;'>卡名：</td>" ;
						html += "<td width='120px;'>有效期：" +v.validTime +"</td>" ;
					});
				}
				$("#cardListBody").html(html);
			}
		});
		
	}
	function insertPayfeeThree(){
		var  checkPayfeePOS= document.getElementById('checkPayfeePOS');
		if(checkPayfeePOS.checked === true){
			 //pos机
			var feeSumPos = $("#feeSumPos").val();
			var postNumPos = $("#postNumPos").val();
			if(feeSumPos==null||feeSumPos==""){
				$.alert({text:"(pos机)pos机缴费金额不能为空！"});
				return; 
			}
			if(postNumPos==null||postNumPos==""){
				$.alert({text:"(pos机)卡号后四位不能为空！"});
				return; 
			}
		}
		
		 var  checkPayfeeBanktrans= document.getElementById('checkPayfeeBanktrans');
			if(checkPayfeeBanktrans.checked === true){
				//银行转账
				var feeSumBanktrans = $("#feeSumBanktrans").val();
				var postBankBanktrans = $("#postBankBanktrans").val();
				var bankFlowNumBanktrans = $("#bankFlowNumBanktrans").val();
				var postUserBanktrans = $("#postUserBanktrans").val();
				if(feeSumBanktrans==null||feeSumBanktrans==""){
					$.alert({text:"(银行转账)银行缴费金额不可以为空！"});
					return; 
				}
				if(postBankBanktrans==null||postBankBanktrans==""){
					$.alert({text:"(银行转账)银行名称不能为空！"});
					return; 
				}  if(bankFlowNumBanktrans==null||bankFlowNumBanktrans==""){
					$.alert({text:"(银行转账)流水号不能为空！"});
					return; 
				}
				if(postUserBanktrans==null||postUserBanktrans==""){
					$.alert({text:"(银行转账)汇款人不能为空！"});
					return; 
				}  
			}
		
		 var  checkPayfeeWechat= document.getElementById('checkPayfeeWechat');
		 if(checkPayfeeWechat.checked === true){
			//微信扫码支付
				var feeSumWechat = $("#feeSumWechat").val();
				var bankFlowNumWechat = $("#bankFlowNumWechat").val();
				if(feeSumWechat==null||feeSumWechat==""){
					$.alert({text:"(微信扫码支付)微信缴费金额不能为空！"});
					return; 
				}
				if(bankFlowNumWechat==null||bankFlowNumWechat==""){
					$.alert({text:"(微信扫码支付)商户单号不能为空！"});
					return; 
				}  
			}
		
		 var  checkPayfeeOthers= document.getElementById('checkPayfeeOthers');
			if(checkPayfeeOthers.checked === true){
				//payfeeOthers
				//他人代收
				var threeOrderPayfeeFeeSumOther = $("#threeOrderPayfeeFeeSumOther").val();
				var threeOrderPayfeePostUserOther = $("#threeOrderPayfeePostUserOther").val();
				var threeOrderPayfeeCollectionEntity = $("[name='threeOrderPayfeeCollectionEntity']:not(:checked)");
				if(threeOrderPayfeeFeeSumOther==null||threeOrderPayfeeFeeSumOther==""){
					$.alert({text:"(他人代收)缴费金额不能为空！"});
					return; 
				}
				if(threeOrderPayfeePostUserOther==null||threeOrderPayfeePostUserOther==""){
					$.alert({text:"(他人代收)汇款人不能为空！"});
					return; 
				} 
				
				if(threeOrderPayfeeCollectionEntity.length==2){
					$.alert({text:"(他人代收)收款单位不能为空！"});
					return; 
				}
			}
		
		  
		  
		var ctx = $("#ctx").val();
		//提示信息
		var message = "录入缴费";
		var feesum = 0;
		var feeToDate = $("#payment_feeToDate").val();
		if(feeToDate==null|| feeToDate==""){
			$.alert({text:"缴费时间不能为空！"});
			return;
		}
		var fee = "[" ;
		// 融汇天下pos作单独校验
		var rhtx = false ;
		$('input[name="feePosts"]:checked').each(function(i){ 
			if(i>0){
				fee += "," ;
			}
			if($(this).val()==20250002 && $("#feeSumBanktrans").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250002 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumBanktrans").val() +"'" ;
				fee += ",'postBank':'" +$("#postBankBanktrans").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#bankFlowNumBanktrans").val() +"'" ;
				fee += ",'postUser':'" +$("#postUserBanktrans").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'" +payStatus +"'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumBanktrans").val());
				
				message +="银行转账:"+intToDecimalThree($("#feeSumBanktrans").val()) +"元，" ;
			}else if($(this).val()==20250005 && $("#feeSumPos").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250005 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumPos").val() +"'" ;
				//fee += ",'bankFlowNum':'" +$("#bankFlowNumPos").val() +"'" ;
				fee += ",'postNum':'" +$("#postNumPos").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumPos").val());
				message +="POS机:"+intToDecimalThree($("#feeSumPos").val()) +"元，" ;
			}else if($(this).val()==20250008 && $("#feeSumRhtxPos").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250008 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumRhtxPos").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#bankFlowNumRhtxPos").val() +"'" ;
				fee += ",'postTerminalNo':'" +$("#postTerminalNoRhtxPos").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				var data = "{'jiaofei':'" +$("#feeSumRhtxPos").val()+"','terminalNo':'" +$("#postTerminalNoRhtxPos").val()+"','referenceNo':'" +$("#bankFlowNumRhtxPos").val()+"'}" ;
				$.ajax({
					url: ctx +"/payfee/posSuccessService",
					data:{
						data:data
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						var html="";
						if (data.msg !="00") {
							rhtx = true;
						}
					}
				});
				feesum += parseFloat($("#feeSumRhtxPos").val());
				message +="融汇天下POS:"+intToDecimalThree($("#feeSumRhtxPos").val()) +"元，" ;
			}else if($(this).val()==20250010 && $("#feeSumCard").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250010 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumCard").val() +"'" ;
				var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
				fee += ",'cardsNum':'" +card.children("td").eq(0).text() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumCard").val());
				
			}else if($(this).val()==20250015 && $("#feeSumWechat").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250015 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumWechat").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#bankFlowNumWechat").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumWechat").val());
				message +="微信扫码支付:"+intToDecimalThree($("#feeSumWechat").val()) +"元，" ;
			} else if($(this).val()==20250014 && $("#threeOrderPayfeeFeeSumOther").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250014 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#threeOrderPayfeeFeeSumOther").val() +"'" ;
				fee += ",'postUser':'" +$("#threeOrderPayfeePostUserOther").val() +"'" ;
				fee += ",'collectionEntity':'" +$("input[name='threeOrderPayfeeCollectionEntity']:checked").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'1'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#threeOrderPayfeeFeeSumOther").val());
				message +="他人代收支付:"+intToDecimalThree($("#threeOrderPayfeeFeeSumOther").val()) +"元，" ;
			} 
			i ++;
		});
		if(rhtx){
			$.alert({text:"融汇天下pos信息校验未通过！"});
			return ;
		}
		fee += "]";
		//alert(feesum +" and " +accountAmount);
		if(feesum!=accountAmount){
			$.alert({
				text : "当前缴费金额与结算单总金额不等！"
			});
			return ;
		}
		message += "录入总金额"+'${param.accountAmount}'+"元，将以短信形式告知客户，是否确认？"; 
		$.confirm({text:message,callback:function(y){
			if(y){
				$.ajax({
					url: ctx +"/payfee/insertPayfeeList",
					data:{
						data:fee,
						accountId : accountId,
						orderId : orderId,
						cateType : cateType,
						userMobile :userMobile,
						message : message
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						var html="";
						if (data.msg =="00") {
							if(cateType==1){
								queryAccountThree(orderId,"accountListBodyOne");
								getServerBasicOne(orderId);
								showBtnThree(cateType,orderId);
							}else if(cateType==2){
								queryAccountThree(orderId,"accountListBodyCont");
								getServerBasics(orderId);
								showBtnThree(cateType,orderId);
							}else if(cateType==3){
								queryAccountThree(orderId,"accountListBodyItem");
								getItemBasics(orderId);
								showBtnThree(cateType,orderId);
							}else if(cateType==4){
								queryAccountThree(orderId,"accountListBodyOne",4,0);
								getServerBasicThree(orderId);
								showBtnThree(cateType,orderId);
							}
							closeModule("orderBasicPayfeeThree");
						}
					}
				});
			  }
			}
		})
	}
	// 查询卡片
	function checkCardsThree(card){
		var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
		var je = card.children("td").eq(1).children("span").text();
		$("#feeSumCard").val(je);
	}
	// 保存结算单的修改
	function editPayfeeSaveThree(pn){
		var ctx = $("#ctx").val();
		var feeToDate = $("#payment_feeToDate").val();
		var feePost = "" ;
		var postBank = "" ;
		var bankFlowNum = "" ;
		var postUser = "" ;
		var postTerminalNo = "";
		var postNum = "";
		var id = "" ;
		//提示信息
		var editmessage = "修改缴费";
		if(pn==20250002 && $("#feeSumBanktrans").val()>0.0){
			feePost=20250002;
			id=$("#payfeeIdBanktrans").val();
			postBank=$("#postBankBanktrans").val();
			bankFlowNum=$("#bankFlowNumBanktrans").val();
			postUser=$("#postUserBanktrans").val();
			editmessage +="银行转账:"+intToDecimalThree($("#feeSumBanktrans").val()) +"元，" ;
		}else if(pn==20250005 && $("#feeSumPos").val()>0.0){
			feePost=20250005;
			id=$("#payfeeIdPos").val();
			//bankFlowNum=$("#bankFlowNumPos").val();
			postNum=$("#postNumPos").val();
			editmessage +="POS机:"+intToDecimalThree($("#feeSumPos").val()) +"元，" ;
		}else if(pn==20250008 && $("#feeSumRhtxPos").val()>0.0){
			feePost=20250008;
			id=$("#payfeeIdRhtxPos").val();
			bankFlowNum=$("#bankFlowNumRhtxPos").val();
			postTerminalNo=$("#postTerminalNoRhtxPos").val();
			var rhtx = false ;
			$.ajax({
				url: ctx +"/payfee/posSuccessService",
				data:{
					jiaofei : $("#feeSumRhtxPos").val(),
					terminalNo : $("#postTerminalNoRhtxPos").val(),
					referenceNo : $("#bankFlowNumRhtxPos").val()
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					var html="";
					if (data.msg !="00") {
						rhtx = true;
					}
				}
			});
			if(rhtx){
				$.alert({text:"融汇天下pos信息校验未通过！"});
				return ;
			}
			editmessage +="融汇天下POS:"+intToDecimalThree($("#feeSumRhtxPos").val()) +"元，" ;
		}else if(pn==20250010 && $("#feeSumCard").val()>0.0){
			//feePost=20250010;
			//var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
			//fee += ",'cardsNum':'" +card.children("td").eq(0).text() +"'" ;
			
		}else if(pn==20250015 && $("#feeSumWechat").val()>0.0){
			feePost=20250015;
			id=$("#payfeeIdWechat").val();
			bankFlowNum=$("#bankFlowNumWechat").val();
			editmessage +="微信扫描支付:"+intToDecimalThree($("#feeSumWechat").val()) +"元，" ;
		}
		if(id=="" || feePost==""){
			$.alert({text:"数据出错，无法保存！"});
			return ;
		}
		editmessage += "录入总金额"+'${param.accountAmount}'+"元，将以短信形式告知客户，是否确认？"; 
		$.confirm({text:editmessage,callback:function(y){
			if(y){ 
				$.ajax({
					url: ctx +"/payfee/updatePayfee",
					data:{
						id : id,
						feePost : feePost,
						feeToDate : feeToDate,
						postBank : postBank,
						bankFlowNum : bankFlowNum,
						postNum : postNum,
						postUser : postUser,
						postTerminalNo : postTerminalNo
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						console.log(data)
						if (data.msg =="00") {
							$.alert({text:"保存成功！"});
						}
					}
				});
				}
			}
		})
	}
</script>
</html>