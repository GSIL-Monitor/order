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
		%>
	</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content" id="modalCPM">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">新增结算单</h4>
				</div>
				<div class="modal-body">
					<div class="info clearfix">
						<form class="form-inline">
							<input type="hidden" id="threeOrderAccountsOrderId" name="threeOrderAccountsOrderId" value="<%=orderId%>"/>
							<div class="row">
								<div class="form-group  col-xs-12">
									<label>
										<p>结算时间:</p>
										<input id="threeOrderAccountsStartTime" class="Wdate form-control" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<input id="threeOrderAccountsEndTime" class="Wdate form-control" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6">
									<label>
										<p>结算金额：</p>
										<input type="text" class="form-control" name="threeOrderAccountsAmount" id="threeOrderAccountsAmount" />
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6">
									<label>
										<p>结算类型：</p>
										<select id="threeOrderAccountsPayType" name="threeOrderAccountsPayType" class="form-control">
											<option value="1">定金</option>
											<option value="2">押金</option>
											<option value="3" selected="selected">预收</option>
											<option value="4">实收</option>
											<option value="5">补缴</option>
										</select>
									</label>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('threeOrderAccounts');">取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="saveThreeOrderAccounts()">提交</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function saveThreeOrderAccounts(){
			var ctx = $("#ctx").val();
			var orderId = $("#threeOrderAccountsOrderId").val();
			var startTime = $("#threeOrderAccountsStartTime").val();
			var endTime = $("#threeOrderAccountsEndTime").val();
			var accountsAmount = $("#threeOrderAccountsAmount").val();
			var payType = $("#threeOrderAccountsPayType").val();
			var params = {
				orderId : orderId,
				startTime : $.trim(startTime),
				endTime : $.trim(endTime),
				accountAmount : $.trim(accountsAmount),
				payType : $.trim(payType)
			};
			if(checkThreeOrderAccountsData(params)){
				$.ajax({
					url : ctx + "/threeOrderOut/saveOrderAccounts",
					data : params,
					type : "post",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.msg == "00") {
							parent.queryThreeOrderOneAccounts(orderId);
							closeModule('threeOrderAccounts');
						}
					}
				});
			}
		}
		
		function checkThreeOrderAccountsData(params){
			if(params.startTime==null||params.startTime==""){
				$.alert({text:"请输入结算开始时间！"});
				return false;
			}
			if(params.endTime==null||params.endTime==""){
				$.alert({text:"请输入结算结束时间！"});
				return false;
			}
			if(params.accountAmount==null||params.accountAmount==""){
				$.alert({text:"请输入结算金额！"});
				return false;
			}
			if(params.payType==null||params.payType==""){
				$.alert({text:"请选择结算类型！"});
				return false;
			}
			return true;
		}
	</script>
</body>
</html>