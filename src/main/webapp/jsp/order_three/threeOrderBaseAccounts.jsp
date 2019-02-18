<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
	<%
	        String orderId = request.getParameter("orderId");
			String cateType = request.getParameter("cateType");
			String accountId = request.getParameter("accountId");
			String totalPay = request.getParameter("totalPay");
			String iscollection = request.getParameter("iscollection");
	%>
</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="模态框" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">结算单信息</h4>
				</div>
				<div class="modal-body new-sort" id="new_sort">
					<div class="info clearfix">
						<input type="hidden" id="accountAmountadOld" />
						<form id="balanceAddForm" action="" class="form-inline"
							method="post"
							data-validator-option="{theme:'yellow_left_effect',stopOnError:true}">

                           <div class="form-group col-xs-12"  >
								   <span id="prompt_Msg" style="color:red!important"></span>
                           </div>

							<div class="row">
								<div class="form-group col-xs-12">
									<label>
										<p>结算单时间：</p> <input id="accountStartTimead" name="startTime"
										class="form-control Wdate" type="text"
										onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
										至<input id="accountEndTimead" name="endTime"
										class="form-control Wdate" type="text"
										onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
										<p>结算单金额：</p> <input class="form-control" id="accountAmountad"
										value="<%=totalPay%>" />元
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
										<p>结算单类型：</p> <select id="accountTypead" name="payType"
										class="form-control">
											<option value="1">定金</option>
											<option value="2">押金</option>
											<option value="3" selected="selected">预收</option>
											<option value="4">实收
												</optaion>
											<option value="5">补缴</option>
											<!-- <option value="6">退款</option> -->
									</select>
									</label>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="insertAccountThree();">保存</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		var iscollection="<%=iscollection%>";
		loadAccountThree();
		if(iscollection==1){
			 $("#prompt_Msg").html("请将订单是他人代收缴费方式的金额与渠道售价金额录入一致, 不确认金额请与该渠道经理联系确认后填写。");
			    $('#accountAmountad').removeAttr("readonly");
				$('#accountAmountad').val("");
		}
	})
	function loadAccountThree(){
		var ctx = $("#ctx").val();
		var accountId = "<%=accountId%>" ;
		var cateType="<%=cateType%>";
		if(cateType == 3 || cateType == 4){
			$('#accountAmountad').attr("readonly",true);
		}
		$.ajax({
			url: ctx +"/payfee/loadAccount",
			data:{
				id:accountId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$("#accountStartTimead").val(numberToDatestr(data.payfee.startTime,14));
					$("#accountEndTimead").val(numberToDatestr(data.payfee.endTime,14));
					$("#accountAmountad").val(data.payfee.accountAmount);
					$("#accountAmountadOld").val(data.payfee.accountAmount);
					$("#accountTypead option[value='" +data.payfee.payType +"']").attr("selected","selected");
					//$("#accountTypead option:selected").val();
					
				}
			}
		});
	}
	function insertAccountThree(){
		var ctx = $("#ctx").val();
		var accountStartTimead=$("#accountStartTimead").val();
		var accountEndTimead=$("#accountEndTimead").val();
		var accountAmountad=$("#accountAmountad").val();
		if (!accountStartTimead || $.trim(accountStartTimead) == '' || !accountEndTimead
				|| $.trim(accountEndTimead) == '') {
			$.alert({
				text : "请选择开始、结束时间。"
			});
			return;
		}
		try {
			var st = new Date(accountStartTimead.replace(/\-/g, "\/"));
			var et = new Date(accountEndTimead.replace(/\-/g, "\/"));
			if (et < st) {
				$.alert({
					text : "开始时间大于结束时间。"
				});
				return;
			}
		} catch (e) {
			$.alert({
				text : "时间格式不正确。"
			});
			return;
		}
		if(accountAmountad<=0){
			$.alert({ text : "结算单金额不正确！" });
			return;
		}
		var accountTypead=$("#accountTypead option:selected").val();
		var orderId="<%=orderId%>";
		var cateType="<%=cateType%>";
		var totalPay="<%=totalPay%>";
		var url = "/payfee/insertAccount" ;
		var accountId = "<%=accountId%>";
	
		var accountStatus = 0;
		
		if (accountId != 0) {
			url = "/payfee/updateAccount";
			var accountAmountadOld = $("#accountAmountadOld").val();
			if (accountAmountad != accountAmountadOld) {
				accountStatus = 1;
			}
		}
		if (accountAmountad >= 9999999) {
			$.alert({
				text : "结算单金额过大！"
			});
		} else {
			$.ajax({
				url : ctx + url,
				data : {
					id : accountId,
					orderId : orderId,
					cateType : cateType,
					startTime : accountStartTimead,
					endTime : accountEndTimead,
					accountAmount : accountAmountad,
					payType : accountTypead,
					payStatus : "20110001",
					isBackType : 2,
					bussStatus : 1,
					payKind : 2,
					accountStatus : accountStatus
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.msg == '00') {
						if (cateType == 1) {
							queryAccountThree(orderId, "accountListBodyOne",
									cateType, totalPay);
							showBtnThree(cateType, orderId);
						} else if (cateType == 2) {
							queryAccountThree(orderId, "accountListBodyCont",
									cateType, totalPay);
							showBtnThree(cateType, orderId);
						} else if (cateType == 3) {
							queryAccountThree(orderId, "accountListBodyItem",
									cateType, totalPay);
							showBtnThree(cateType, orderId);
						} else if (cateType == 4) {
							queryAccountThree(orderId, "accountListBodyOne",
									cateType, totalPay);
							showBtnThree(cateType, orderId);
						}
						closeModule("orderBasicAccounts");
					} else {
						$.alert({
							text : "保存失败！"
						});
					}
				}
			});
		}

	}
</script>
</html>