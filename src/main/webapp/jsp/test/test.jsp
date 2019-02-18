<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

</head>
<body>
<h1>测试售后手续费</h1>
	<div class="row" id="after-sale-service-charge">
		<div class="form-group col-xs-12">
			<label>
				<p style="width: 15em;">应缴售后手续费金额(元)：</p>
				<span name="payfeeMoney" id="payfeeMoney" ></span>
		</div>
		<div class="form-group col-xs-12">
			<label>
				<p style="width: 15em;">已缴售后手续费金额(元)：</p>
				<span name="accountAmount" id="accountAmount" ></span>
		</div>
		<div class="form-group col-xs-12">
			<label>
				<p style="width: 15em;">分期手续费可用金额(元)：</p>
				<span name="installmentFee" id="installmentFee" ></span>
		</div>
		<div class="form-group col-xs-12">
			<label>
				<p style="width: 15em;">信息费可用金额(元)：</p>
				<span name="MembershipFee" id="MembershipFee" ></span>
		</div>
		<div class="form-group col-xs-12">
			<label>
				<p style="width: 15em;">非白条预存服务人员服务费可用金额(元)：</p>
				<span name="notIousSalaryMoney" id="notIousSalaryMoney" ></span>
		</div>
	</div>
	
	<div class="row" id="after-sale-service-charge-hidden">
		<div id="payMoney"></div>
		<div id="accountPay"></div>
		<div id="MembershipFee_hidden"></div>
		<div id="installmentFee_hidden"></div>
		<div id="notIousSalaryMoney_hidden"></div>
	</div>
</body>




<script src="${ctx}/js/jquery/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		queryPayMoney();
		queryAccountPay();
		queryMembershipFee();
		queryNotIousSalaryMoney();
		queryInstallmentFee();
		//he();
	})

		//非白条预存服务费可用金额
		function queryNotIousSalaryMoney() {
			var orderId = 788925605644487;
			$.ajax({
				url : "/order/afterFees/queryNotIousSalaryMoney",
				data : {"orderId" : orderId},
				type : "POST",
				datetype : "json",
				async : false,
				success : function(data) {
					if (data.msg == "00") {
						var total = 0;
						$.each(data.list,function(i,v){
							total += v.feeSumC;
						})
						$("#notIousSalaryMoney_hidden").html(JSON.stringify(data.list));
						$("#notIousSalaryMoney").html(total);
					}
				}
			})
		}
	//查询总手续费
	function queryPayMoney() {
		var orderId = 788925605644487;
		$.ajax({
			url : "/order/afterFees/queryPayMoney",
			type : "POST",
			datetype : "json",
			async : false,
			data : {
				"orderId" : orderId
			},
			success : function(data) {
				if (data.msg == "00" && data.payfeeMoney != "null") {
					$("#payfeeMoney").html(data.payfeeMoney);
				} else {
					$("#payfeeMoney").html(data.payfeeMoney);
				}
			}
		})
	}

	//查询扣减已缴手续费
	function queryAccountPay() {
		var orderId = 788925605644487;
		$.ajax({
			url : "/order/afterFees/queryAccountPay",
			type : "POST",
			datetype : "json",
			async : false,
			data : {
				"orderId" : orderId
			},
			success : function(data) {
				if (data.msg == "00") {
					$("#accountAmount").html(data.accountAmount);
				} else {
					$("#accountAmount").html(data.accountAmount);
				}
			}
		})
	}

	//查询信息费可退金额
	function queryMembershipFee() {
		var orderId = 188807002237223;
		$.ajax({
			url : "/order/afterFees/queryMembershipFee",
			type : "POST",
			datetype : "json",
			async : false,
			data : {
				"orderId" : orderId
			},
			success : function(data) {
				if (data.msg == "00") {
					var total=0;
					$.each(data.list,function(i,v){
						total+=v.feeSumC;
					})
					$("#MembershipFee").html(total);
					$("#MembershipFee_hidden").html(JSON.stringify(data.list))
				} else {
					$("#MembershipFee").html(total);
				}
			}
		})
	}
	
	
	//查询信息费可退金额
	function queryInstallmentFee() {
		var orderId = 965024094726711;
		$.ajax({
			url : "/order/afterFees/queryInstallmentFee",
			type : "POST",
			datetype : "json",
			async : false,
			data : {
				"orderId" : orderId
			},
			success : function(data) {
				if (data.msg == "00") {
					var total=0;
					$.each(data.list,function(i,v){
						total+=v.feeSumC;
					})
					$("#installmentFee").html(total);
					$("#installmentFee_hidden").html(JSON.stringify(data.list))
				} else {
					$("#installmentFee").html(total);
				}
			}
		})
	}
	
	function he(){
		var arr1=JSON.parse($("#notIousSalaryMoney_hidden").html());
		var arr2=JSON.parse($("#MembershipFee_hidden").html());
		var arr3=JSON.parse($("#installmentFee_hidden").html());
		var arr4=arr1.concat(arr2,arr3);
		console.log(arr4)
	}
</script>
</html>