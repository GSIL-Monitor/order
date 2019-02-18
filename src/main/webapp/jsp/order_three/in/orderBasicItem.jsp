<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- 商品订单基本信息 -->
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
</style>

</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<input type="hidden" name="orderId" id="orderIdBasicsItem">
		<div class="mytabs-wrap">
			<ul class="mytabs">
				<li class="tab-item tab-active" id="itemBasic1" onclick="itemBasic(1);">基本信息</li>
				<li class="tab-item" id="itemBasic2" onclick="itemBasic(2);">缴费</li>
			</ul>
		</div>
		<div class="tab-content" id="basicItem">
			<div class="tab-selected" id="person">
				<div class="row info-select">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<div class="widget-content">
				<input type="hidden" name="receiverId" id="receiverIdBasicItem">
				<input type="hidden" name="userId" id="userIdBasicItem">
					<table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
						<tr>
							<td colspan="2">订单编号：<span id="orderCodes"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2">创建时间：<span id="createTimes"></span>
							</td>
						</tr>
						<tr>
							<td width="50%">订单状态：<span id="orderstatus"></span>
							</td>
							<td width="50%">订单渠道：<span id="ordersourceid"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="2">订单备注：<span id="orderRemark"></span>
							</td>
						</tr>
					</table>
				</div>
				<div class="row info-select">
					<div class="col-xs-12">
						<h4>商品信息</h4>
					</div>
				</div>
<!-- 				<div class="widget-content" style="height:25px; border-bottom: 1px solid #CCC;">&nbsp; &nbsp; </div> -->
				<div>
					<table class="table" style="margin-bottom: 1px">
						<thead>
						<tr style="height:25px;">
							<td width="30%">商品名称
							</td>
							<td width="20%">商品价格
							</td>
							<td width="20%">商品数量 
							</td>
						</tr>
						</thead>
						<tbody style="height: 100px; border-bottom: 1px solid #CCC;" id="orderInfor"></tbody>
					</table>
				</div>
<!-- 				<div style="height: 100px;" id="orderInfor"> -->
					<!-- <table class="table table-condensed"  style="margin-bottom: 4px">
						<tr>
							<td width="25%"><span id="orderProductCode"></span>
							</td>
							<td width="25%"><span id="orderProductName"></span>
							</td>
							<td width="25%"><span id="orderNowPrice"></span>
							</td>
							<td width="25%"><span id="orderQuantity"></span>
							</td>
						</tr>
					</table> -->
<!-- 				</div> -->
				
				<div class="row info-select">
					<div class="col-xs-12">
						<h4>收货人信息</h4>
					</div>
				</div>
<!-- 				<div class="widget-content" style="height:25px; border-bottom: 1px solid #CCC;">&nbsp; &nbsp; </div> -->
				<div>
					<table class="table table-condensed">
						<tr>
							<td width="40%">收货人姓名：<span id="receiverName"></span>
							</td>
							<td width="60%">收货人电话：<span id="receiverMobile"></span>
							</td>
						</tr>
						<tr>
							<td width="50%" >收货所在省份：<span id="receiverProvince"></span>
							</td>
							<td width="50%" >收货所在城市：<span id="receiverCity"></span>
							</td>
							
<!-- 							<td width="50%">收货人的邮编：<span id="receiverZipcode"></span> -->
<!-- 							</td> -->
						</tr>
						<tr>
							<td width="50%" colspan="2" rowspan="2">收货人详细地址：<span id="receiverAddress"></span>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="row info-select">
					<div class="col-xs-12">
						<h4>管家信息</h4>
					</div>
				</div>
<!-- 				<div class="widget-content" style="height:25px; border-bottom: 1px solid #CCC;">&nbsp; &nbsp; </div> -->
				<div>
					<table class="table table-condensed">
						<tr>
							<td width="40%">所属管家：<span id="orderItemAuthManagerName"></span>
							</td>
							<td width="60%">联系方式：<span id="orderItemAuthManagertPhone"></span>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div id="feesItem" style="display:none; border:1px solid #CCC;">
<!-- 		<div class="main"> -->
             <div class="main tab-selected">
			<div class="widget-content">
				<div class="row info-select" style="margin-top: 10px;">
					<div class="form-group  col-xs-12">
						<button class="btn btn-primary btn-sm" type="button" onclick="addAccount()">
							<em class="add glyphicon glyphicon-plus-sign"></em>新增结算单
						</button>
					</div>
				</div>
				<!-- 循环结算单信息开始 -->
				<div id="threeOrderFaAccountsList">
					
				</div>
				<!-- 循环结算单信息结束 -->
			</div>
		</div>
		</div>
</body>
<script type="text/javascript">
	function setBasicItem(orderId) {
		$("#orderIdBasicsItem").val(orderId);
		getItemBasics(orderId);
		queryThreeOrderAccounts(orderId);
	}
	//取到订单详细信息
	function getItemBasics(orderId) {
		var ctx = $("#ctx").val();
		$.ajax({
			url : ctx + "/threeOrderIn/queryOrderBasicItem",
			data : {
				id : orderId
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg = "00") {
					var html="";
					var num = $.each(data.list, function(i, v) {
						//订单信息
						$("#orderCodes").text(v.orderCode);
						$("#createTimes").text(v.createTime);
						$("#orderRemark").text(v.remark);
						// 基础信息：收货人信息
						$("#userIdBasicItem").val(v.userId);
						$("#receiverIdBasicItem").val(v.receiverId);
						$("#receiverName").text(v.receiverName);
						$("#receiverMobile").text(v.receiverMobile);
						$("#receiverAddress").text(v.receiverAddress);
						$("#receiverZipcode").text(v.receiverZipcode);
						$("#receiverProvince").text(v.receiverProvince);
						$("#receiverCity").text(v.receiverCity);
						$("#orderstatus").text(v.statusText);
						$("#ordersourceid").text(v.sourceText);
						$("#orderItemAuthManagerName").text(v.authManagerName);
						$("#orderItemAuthManagertPhone").text(v.authManagertPhone);
						
// 						 html +="<table class=\"table table-condensed\" style=\"margin-bottom: 4px\"><tr>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.productCode+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.productName+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.nowPrice+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.quantity+"</span></td>";
						 html +="<tr style='height:25px;'>";
						 html +="<td>"+v.item.productName+"</td>";
						 html +="<td>"+v.item.nowPrice+"</td>";
						 html +="<td>"+v.item.quantity+"</td>";
						 html += "</tr>";
					})
					document.getElementById("orderInfor").innerHTML=html;
				}
			}
		});
	}
	function itemBasic(num){
		if(num==1){
			$("#itemBasic1").addClass("tab-selected");
			$("#itemBasic1").addClass("tab-active");
			$("#itemBasic2").removeClass("tab-selected");	
			$("#itemBasic2").removeClass("tab-active");	
			$("#basicItem").css("display","block");
			$("#feesItem").css("display","none");
		}else if(num==2){
			$("#itemBasic1").removeClass("tab-selected");
			$("#itemBasic1").removeClass("tab-active");	
			$("#itemBasic2").addClass("tab-active");
			$("#itemBasic2").addClass("tab-selected");	
			$("#basicItem").css("display","none");
			$("#feesItem").css("display","block");
		}
	}
	function addAccount(){
		orderBasicAccount(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val());
	}
	
</script>
</html>