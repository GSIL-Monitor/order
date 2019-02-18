<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.modal-user-account {height: 220px; width: 400px; margin: 30px auto;}
	</style>
	<script type="text/javascript">
		<%
		String orderId = request.getParameter("orderId");
		String accountsId = request.getParameter("accountsId");
		String accountAmount = request.getParameter("accountAmount");
		String userId = request.getParameter("userId");
		%>
	</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">新增结算单</h4>
				</div>
				<div class="modal-body">
					<div class="info clearfix">
						<form class="form-inline">
							<input type="hidden" id="threeOrderPayfeeOrderId" name="threeOrderPayfeeOrderId" value="<%=orderId%>" />
							<input type="hidden" id="threeOrderPayfeeAccountsId" name="threeOrderPayfeeAccountsId" value="<%=accountsId%>" />
							<input type="hidden" id="threeOrderPayfeeAccountsAmount" name="threeOrderPayfeeAccountsAmount" value="<%=accountAmount%>" />
							<input type="hidden" id="threeOrderPayfeeUserId" name="threeOrderPayfeeUserId" value="<%=userId%>" />
							<div class="row" style="border-bottom: 1px solid #CCC;">
								<div class="form-group col-xs-12">
									<label>
										<p>缴费方式：</p>
									</label>
									<label>
										<input type="checkbox" name="feePost" value="20250002" onclick="setThreeOrderPayfee(this);">银行转账&nbsp;
									</label>
									<label>
										<input type="checkbox" name="feePost" value="20250005" onclick="setThreeOrderPayfee(this);">POS机&nbsp;
									</label>
									<label>
										<input type="checkbox" name="feePost" value="20250008" onclick="setThreeOrderPayfee(this);">融汇天下POS机&nbsp;
									</label>
									<label>
										<input type="checkbox" name="feePost" value="20250010" onclick="setThreeOrderPayfee(this);">礼品卡&nbsp;
									</label>
									<label>
										<input type="checkbox" name="feePost" value="20250014" onclick="setThreeOrderPayfee(this);">他人代收&nbsp;
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<p>缴费时间：</p>
										<input id="threeOrderPayfeeFeeToDate" name="threeOrderPayfeeFeeToDate" class="Wdate form-control" 
											type="text" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									</label>
								</div>
							</div>
							<br>
							<div class="row" id="payfeeYhzz" style="border-bottom: 1px solid #CCC; display: none;">
								<div class="form-group col-xs-12">
									<label>
										<p>银行转账</p>
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumYhzz" name="threeOrderPayfeeFeeSumYhzz" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>银行：</p>
										<input type="text" id="threeOrderPayfeePostBankYhzz" name="threeOrderPayfeePostBankYhzz" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>流水号：</p>
										<input type="text" id="threeOrderPayfeeBankFlowNumYhzz" name="threeOrderPayfeeBankFlowNumYhzz" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>汇款人：</p>
										<input type="text" id="threeOrderPayfeePostUserYhzz" name="threeOrderPayfeePostUserYhzz" class="form-control">
									</label>
								</div>
							</div>
							<br>
							<div class="row" id="payfeePos" style="border-bottom: 1px solid #CCC; display: none;">
								<div class="form-group col-xs-12">
									<label>
										<p>POS机</p>
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumPos" name="threeOrderPayfeeFeeSumPos" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>流水号：</p>
										<input type="text" id="threeOrderPayfeeBankFlowNumPos" name="threeOrderPayfeeBankFlowNumPos" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>卡号后四位：</p>
										<input type="text" id="threeOrderPayfeePostNumPos" name="threeOrderPayfeePostNumPos" class="form-control">
									</label>
								</div>
							</div>
							<br>
							<div class="row" id="payfeeRhPos" style="border-bottom: 1px solid #CCC; display: none;">
								<div class="form-group col-xs-12">
									<label>
										<p>融汇天下POS机</p>
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumRhPos" name="threeOrderPayfeeFeeSumRhPos" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>终端编号：</p>
										<input type="text" id="threeOrderPayfeePostTerminalNoRhPos" name="threeOrderPayfeePostTerminalNoRhPos" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>交易参考号：</p>
										<input type="text" id="threeOrderPayfeeBankFlowNumRhPos" name="threeOrderPayfeeBankFlowNumRhPos" class="form-control">
									</label>
								</div>
							</div>
							<br>
							<div class="row" id="payfeeLpk" style="border-bottom: 1px solid #CCC; display: none;">
								<div class="form-group col-xs-12">
									<label>
										<p>礼品卡</p>
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumLpk" name="threeOrderPayfeeFeeSumLpk" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-12">
									<table id="threeOrderPayfeeCardList" style="margin: 10px 0px;"></table>
								</div>
							</div>
							<br>
							<div class="row" id="payfeeOther" style="border-bottom: 1px solid #CCC; display: none;">
								<div class="form-group col-xs-12">
									<label>
										<p>他人代收</p>
									</label>
								</div>
								<div class="form-group col-xs-12">
									<label>
										<p>缴费金额：</p>
										<input type="text" id="threeOrderPayfeeFeeSumOther" name="threeOrderPayfeeFeeSumOther" class="form-control">
									</label>
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>收款单位：</p>
									</label>
									<input type="radio" name="threeOrderPayfeeCollectionEntity" value="1"> 个人
									<input type="radio" name="threeOrderPayfeeCollectionEntity" value="2"> 企业
								</div>
								<div class="form-group col-xs-6">
									<label>
										<p>汇款人：</p>
										<input type="text" id="threeOrderPayfeePostUserOther" name="threeOrderPayfeePostUserOther" class="form-control">
									</label>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('threeOrderPayfee');">取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="saveThreeOrderPayfee()">提交</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			initThreeOrderPayfee();
			queryThreeOrderPayfeeCards()
		});
		function initThreeOrderPayfee(){
			var feePostFirst = $("input[name=feePost]").eq(0);
			feePostFirst.attr("checked", "checked");
			$("#payfeeYhzz").css("display", "block");
		}
		function queryThreeOrderPayfeeCards(){
			var ctx = $("#ctx").val();
			var userId = $("#threeOrderPayfeeUserId").val();
			$.ajax({
				url: ctx +"/threeOrderOut/queryCards",
				data:{
					userId : userId
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					var html="";
					if (data.msg =="00") {
						$.each(data.list,function(i,v){
							html +="<tr>";
							html +="<td>";
							html +="<input type='radio' name='threeOrderPayfeeCardNum' value='"+v.cardNumb+"'>";
							html +="<input type='hidden' name='threeOrderPayfeeCardBalance' value='"+v.cardBalance+"'>";
							html +="</td>";
							html +="<td>"+v.cardNumb+"</td>";
							html +="<td>余额："+v.cardBalance+"</td>";
							html +="<td>卡名：会员卡</td>";
							html +="<td>有效期至："+v.validTime+"</td>";
							html +="</tr>";
						})
					}else if(data.msg =="02"){
						html +="<tr><td colspan='4'>暂无可用的礼品卡</td></tr>";
					}else{
						html +="<tr><td colspan='4'>查询错误</td></tr>";
					}
					$("#threeOrderPayfeeCardList").html(html);
				}
			});
		}
		function setThreeOrderPayfee(fee) {
			if (fee.value == 1002) {
				if (fee.checked) {
					$("#payfeeYhzz").css("display", "block");
				} else {
					$("#payfeeYhzz").css("display", "none");
				}
			} else if (fee.value == 1005) {
				if (fee.checked) {
					$("#payfeePos").css("display", "block");
				} else {
					$("#payfeePos").css("display", "none");
				}
			} else if (fee.value == 1008) {
				if (fee.checked) {
					$("#payfeeRhPos").css("display", "block");
				} else {
					$("#payfeeRhPos").css("display", "none");
				}
			} else if (fee.value == 1010) {
				if (fee.checked) {
					$("#payfeeLpk").css("display", "block");
				} else {
					$("#payfeeLpk").css("display", "none");
				}
			} else if (fee.value == 1101) {
				if (fee.checked) {
					$("#payfeeOther").css("display", "block");
				} else {
					$("#payfeeOther").css("display", "none");
				}
			}
		}
		function saveThreeOrderPayfee() {
			var ctx = $("#ctx").val();
			var orderId = $("#threeOrderPayfeeOrderId").val();
			var accountsId = $("#threeOrderPayfeeAccountsId").val();
			var feeToDate = $("#threeOrderPayfeeFeeToDate").val();
			var feeSumYhzz = $("#threeOrderPayfeeFeeSumYhzz").val();
			var postBankYhzz = $("#threeOrderPayfeePostBankYhzz").val();
			var bankFlowNumYhzz = $("#threeOrderPayfeeBankFlowNumYhzz").val();
			var postUserYhzz = $("#threeOrderPayfeePostUserYhzz").val();
			var feeSumPos = $("#threeOrderPayfeeFeeSumPos").val();
			var bankFlowNumPos = $("#threeOrderPayfeeBankFlowNumPos").val();
			var postNumPos = $("#threeOrderPayfeePostNumPos").val();
			var feeSumRhPos = $("#threeOrderPayfeeFeeSumRhPos").val();
			var postTerminalNoRhPos = $("#threeOrderPayfeePostTerminalNoRhPos").val();
			var bankFlowNumRhPos = $("#threeOrderPayfeeBankFlowNumRhPos").val();
			var feeSumLpk = $("#threeOrderPayfeeFeeSumLpk").val();
			var cardNum = $("input[name=threeOrderPayfeeCardNum]:checked").val();
			var cardBalance = $("input[name=threeOrderPayfeeCardNum]:checked").siblings("input[name=threeOrderPayfeeCardBalance]").val();
			var feeSumOther = $("#threeOrderPayfeeFeeSumOther").val();
			var collectionEntity = $("input[name=threeOrderPayfeeCollectionEntity]:checked").val();
			var postUserOther = $("#threeOrderPayfeePostUserOther").val();
			var feePosts = "";
			$("input[name=feePost]:checked").each(function(){
				feePosts = feePosts + this.value+",";
			});
			var params = {
				orderId : $.trim(orderId),
				accountsId : $.trim(accountsId),
				feeToDate : $.trim(feeToDate),
				feeSumYhzz : $.trim(feeSumYhzz),
				postBankYhzz : $.trim(postBankYhzz),
				bankFlowNumYhzz : $.trim(bankFlowNumYhzz),
				postUserYhzz : $.trim(postUserYhzz),
				feeSumPos : $.trim(feeSumPos),
				bankFlowNumPos : $.trim(bankFlowNumPos),
				postNumPos : $.trim(postNumPos),
				feeSumRhPos : $.trim(feeSumRhPos),
				postTerminalNoRhPos : $.trim(postTerminalNoRhPos),
				bankFlowNumRhPos : $.trim(bankFlowNumRhPos),
				feeSumLpk : $.trim(feeSumLpk),
				cardNum : $.trim(cardNum),
				cardBalance : $.trim(cardBalance),
				feeSumOther : $.trim(feeSumOther),
				collectionEntity : $.trim(collectionEntity),
				postUserOther : $.trim(postUserOther),
				feePosts : $.trim(feePosts)
			}
			if(checkThreeOrderPayfeeData(params)){
				$.ajax({
					url: ctx +"/threeOrderOut/savePayfee",
					data : params,
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if (data.msg =="00") {
							var order = $("#threeOrderPayfeeOrderId").val();
							queryThreeOrderOneAccounts(order);
							queryOrdersByLike(0,10);
							closeModule('threeOrderPayfee');
						}
					}
				});
			}
		}
		function checkThreeOrderPayfeeData(params){
			var feePostsArray = $("input[name=feePost]:checked");
			if(feePostsArray.length==0){
				$.alert({text:"请选择缴费类型！"});
				return false;
			}
			if(params.feeToDate==null||params.feeToDate==""){
				$.alert({text:"请输入缴费时间！"});
				return false;
			}
			var feeSumTotal = 0;
			for(var i=0;i<feePostsArray.length;i++){
				if(feePostsArray[i].value == "1002"){
					feeSumTotal = feeSumTotal + parseInt(params.feeSumYhzz);
					if(params.feeSumYhzz==null||params.feeSumYhzz==""){
						$.alert({text:"请输入缴费金额（银行转账）！"});
						return false;
					}
					if(params.postBankYhzz==null||params.postBankYhzz==""){
						$.alert({text:"请输入银行（银行转账）！"});
						return false;
					}
					if(params.bankFlowNumYhzz==null||params.bankFlowNumYhzz==""){
						$.alert({text:"请输入流水号（银行转账）！"});
						return false;
					}
					if(params.postUserYhzz==null||params.postUserYhzz==""){
						$.alert({text:"请输入汇款人（银行转账）！"});
						return false;
					}
				}
				if(feePostsArray[i].value == "1005"){
					feeSumTotal = feeSumTotal + parseInt(params.feeSumPos);
					if(params.feeSumPos==null||params.feeSumPos==""){
						$.alert({text:"请输入缴费金额（POS机）！"});
						return false;
					}
					if(params.bankFlowNumPos==null||params.bankFlowNumPos==""){
						$.alert({text:"请输入流水号（POS机）！"});
						return false;
					}
					if(params.postNumPos==null||params.postNumPos==""){
						$.alert({text:"请输入卡号后四位（POS机）！"});
						return false;
					}
				}
				if(feePostsArray[i].value == "1008"){
					feeSumTotal = feeSumTotal + parseInt(params.feeSumRhPos);
					if(params.feeSumRhPos==null||params.feeSumRhPos==""){
						$.alert({text:"请输入缴费金额（融汇天下POS机）！"});
						return false;
					}
					if(params.postTerminalNoRhPos==null||params.postTerminalNoRhPos==""){
						$.alert({text:"请输入终端编号（融汇天下POS机）！"});
						return false;
					}
					if(params.postTerminalNoRhPos.length!=8){
						$.alert({text:"输入的终端编号错误（融汇天下POS机）！"});
						return false;
					}
					if(params.bankFlowNumRhPos==null||params.bankFlowNumRhPos==""){
						$.alert({text:"请输入交易参考号（融汇天下POS机）！"});
						return false;
					}
				}
				if(feePostsArray[i].value == "1010"){
					feeSumTotal = feeSumTotal + parseInt(params.feeSumLpk);
					if(params.feeSumLpk==null||params.feeSumLpk==""){
						$.alert({text:"请输入缴费金额（礼品卡）！"});
						return false;
					}
					if(params.cardNum==null||params.cardNum==""){
						$.alert({text:"请选择礼品卡（礼品卡）！"});
						return false;
					}
					if(parseInt(params.cardBalance) < parseInt(params.feeSumLpk)){
						$.alert({text:"输入缴费金额不可大于礼品卡金额（礼品卡）！"});
						return false;
					}
				}
				if(feePostsArray[i].value == "1101"){
					feeSumTotal = feeSumTotal + parseInt(params.feeSumOther);
					if(params.feeSumOther==null||params.feeSumOther==""){
						$.alert({text:"请输入缴费金额（他人代收）！"});
						return false;
					}
					if(params.collectionEntity==null||params.collectionEntity==""){
						$.alert({text:"请输入收款单位（他人代收）！"});
						return false;
					}
					if(params.postUserOther==null||params.postUserOther==""){
						$.alert({text:"请输入汇款人（他人代收）！"});
						return false;
					}
				}
			}
			var accountsAmount = $("#threeOrderPayfeeAccountsAmount").val();
			if(parseInt(feeSumTotal)!=parseInt(accountsAmount)){
				$.alert({text:"汇款金额与结算单金额不相等，不可提交！"});
				return false;
			}
			return true;
		}
	</script>
</body>
</html>