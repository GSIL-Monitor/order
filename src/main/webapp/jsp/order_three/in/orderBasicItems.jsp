<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
<script src="${ctx}/js/main.js"></script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<input type="hidden" name="orderId" id="orderIdBasicsItem">
	<input type="hidden" name="orderStatusBasicsItem" id="orderStatusBasicsItem">
		<div class="mytabs-wrap">
			<ul class="mytabs">
				<li class="tab-item tab-active" id="itemBasic1" onclick="itemBasic(1);">基本信息</li>
				<li class="tab-item" id="itemBasic2" onclick="itemBasic(2);">缴费</li>
			</ul>
		</div>
		<div id="arr">
			<span id="left"><</span><span id="right">></span>
		</div>
		<div class="tab-content">
			<div class="tab-selected" id="basicItem">
				<!-- <div class="widget" id="orderItemaddressAddButton" style="hieght:30px; display:none;" >
	                <button type="button" class="btn btn-primary btn-xs" 
	                	onclick="openUserAddress();">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改收货地址">修改收货地址</i></button>
				</div> -->
				<div class="row">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<div class="widget-content">
				<input type="hidden" name="receiverId" id="receiverIdBasicItem">
				<input type="hidden" name="userId" id="userIdBasicItem">
					<table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
						<tr>
							<td width="55%" colspan="2">订单编号：<span id="orderCodes"></span>
							</td>
						</tr>
						<tr>
							<td width="45%" colspan="2">创建时间：<span id="createTimes"></span>
							</td>
						</tr>
						<tr>
							<td width="50%">订单运费：<span id="orderDeliverPay"></span>
							</td>
							<td width="50%">紧急程度：<span id="orderCritical"></span>
							</td>
						</tr>
						<tr>
							<td width="50%">订单状态：<span id="orderstatus"></span>
							</td>
							<td width="50%">支付状态：<span id="payTextItem"></span>
							</td>
						</tr>
						<tr>
							<td width="50%" colspan="2">订单来源：<span id="ordersourceid"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" rowspan="2">订单备注：<span id="orderRemark"></span>
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
	                        <td width="50%">
	                        	客户姓名：<span id="nameBasicsItem"></span>
	                        </td>
	                        <td width="50%">
	                        	客户电话：<span id="mobileBasicsItem"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	性别：<span id="sexBasicsItem"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	家庭地址：<span id="userAddressBasicsItem"></span>
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
			                        <th width="10%">商品数量 </th>
			                        <th width="20%">规格</th>
			                    </tr>
			                    </thead>
			                    <tbody id="orderInforItem">
			                    </tbody>
			                </table>
	                    </div>
                    </div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>收货人信息</h4>
					</div>
				</div>
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
						</tr>
						<tr>
							<td width="50%" colspan="2" rowspan="2">收货人详细地址：<span id="receiverAddress"></span>
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div id="feesItem" style="display:none; border:1px solid #CCC;">
	            <div class="widget">
	            	<button type="button" class="btn btn-primary btn-xs" onclick="addAccountItems();" id="addAccountBtn">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updateAccountItems();" id="updateAccountBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountItems();" id="deleteAccountBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除结算">删除结算</i></button>
	            </div>
	            <div class="widget">
					<button type="button" class="btn btn-primary btn-xs" onclick="addPayfeeItems();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updatePayfeeItems()" id="updatePayfeeItemBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i></button>
	            </div>
	            <div class="widget-content">
	                <div class="project-order-news">
	                    <div class="row">
	                        <div class="col-xs-12"><h4>缴费信息</h4></div>
	                    </div>
	                    <div id="accountListBodyItem">
	                	</div>
	                </div>
	            </div>
			</div>
		</div>
</body>
<script type="text/javascript">
	function setBasicItem(orderId,userMobile,cateType,totalPay) {
		$("#orderIdBasicsItem").val(orderId);
		$("#userMobileBasicsItem").val(userMobile);
		getItemBasics(orderId);
		queryAccountThree(orderId,"accountListBodyItem",cateType,totalPay);
		showBtnThree(cateType,orderId);
	}
	//取到订单详细信息
	function getItemBasics(orderId) {
		var ctx = $("#ctx").val();
		$.ajax({
			url : ctx + "/order/queryOrderBasicItem",
			data : {
				id : orderId
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == "00") {
					var html="";
					var num = $.each(data.list, function(i, v) {
						//订单信息
						$("#orderCodes").text(v.orderCode);
						$("#payTextItem").text(v.payText);
						$("#createTimes").text(v.createTime);
						$("#orderDeliverPay").text(v.deliverPay);
						$("#orderCritical").text(v.criticalText);
						$("#orderRemark").text(v.remark);
						// 客户信息
						$("#nameBasicsItem").text(v.userName);
						$("#mobileBasicsItem").text(v.userMobile);
						$("#sexBasicsItem").text(v.sex==1?"男":"女");
						$("#birthBasicsItem").text(v.birth==""?"":numberToDatestr(v.birth,8));
						$("#userAddressBasicsItem").text(v.userAddress);
						// 基础信息：收货人信息
						$("#userIdBasicItem").val(v.userId);
						$("#receiverIdBasicItem").val(v.receiverId);
						$("#orderStatusBasicsItem").val(v.orderStatus); 
						// 1新建，2受理、18待受理
						if(v.orderStatus==1 || v.orderStatus ==2 || v.orderStatus==18){
							$("#orderItemaddressAddButton").css("display","block") ;
						}else{
							$("#orderItemaddressAddButton").css("display","none") ;
						}
						$("#receiverName").text(v.receiverName);
						$("#receiverMobile").text(v.receiverMobile);
						$("#receiverAddress").text(v.receiverProvince +v.receiverCity +v.receiverArea +v.receiverAddress);
						$("#receiverZipcode").text(v.receiverZipcode);
						$("#receiverProvince").text(v.receiverProvince);
						$("#receiverCity").text(v.receiverCity);
						$("#orderstatus").text(v.statusText);
						$("#ordersourceid").text(v.sourceText);
						$("#ordersourceid").text(v.sourceText);
						
// 						 html +="<table class=\"table table-condensed\" style=\"margin-bottom: 4px\"><tr>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.productCode+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.productName+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.nowPrice+"</span></td>";
// 						 html +="<td width=\"25%\"><span id=\"\">"+v.item.quantity+"</span></td>";
						 html +="<tr style='height:25px;'>";
// 						 html +="<td>"+v.item.productCode+"</td>";
						 html +="<td>"+v.item.productName +"(" +v.item.productUnitText+")</td>";
						 html +="<td>"+v.item.productPriceTypeText+"</td>";
						 html +="<td>"+v.item.nowPrice+"</td>";
						 html +="<td>"+v.item.quantity+"</td>";
						 html +="<td>"+v.item.productSpec+"</td>";
						 html += "</tr>";
					})
					html += "<tr></tr>" ;
					document.getElementById("orderInforItem").innerHTML=html;
				}
			}
		});
	}
	function itemBasic(num){
		if(num==1){
			$("#itemBasic1").addClass("tab-selected");
			$("#itemBasic2").removeClass("tab-selected");	
			$("#basicItem").css("display","block");
			$("#feesItem").css("display","none");
		}else if(num==2){
			$("#itemBasic1").removeClass("tab-selected");
			$("#itemBasic2").addClass("tab-selected");	
			$("#basicItem").css("display","none");
			$("#feesItem").css("display","block");
		}
	}
	// 新增结算
	function addAccountItems(){
		orderBasicAccountThree(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),0,parent.$("#checkedIscollection").val());
	}
	// 修改结算
	function updateAccountItems(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if(radioAccount==null || radioAccount=="" || radioAccount==0){
			$.alert({
				text : "请选择要修改的结算单！"
			});
			return ;
		}
		var payStatus = $("#payStatusAccount" +radioAccount).val();
		if(payStatus==20110002||payStatus==20110003){
			$.alert({
				text : "当前结算单已对账，无法修改！"
			});
			return ;
		}
		orderBasicAccountThree(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),radioAccount);
	}
	// 删除结算
	function deleteAccountItems(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if(radioAccount==null || radioAccount=="" || radioAccount==0){
			$.alert({
				text : "请选择要删除的结算单！"
			});
			return ;
		}
		var payStatus = $("#payStatusAccount" +radioAccount).val();
		if(payStatus==20110002||payStatus==20110003){
			$.alert({
				text : "当前结算单已对账，无法删除！"
			});
			return ;
		}
		deleteAccountByIdThree(radioAccount);
		queryAccountThree(parent.$("#checkedOrderId").val(),"accountListBodyItem");
	}
	// 新增缴费
	function addPayfeeItems(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyItem"+radioAccount).find("tr").length>0){
			$.alert({
				text : "当前结算单已经生成缴费信息，无法新增！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfeeThree(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,1);
	}
	// 修改缴费
	function updatePayfeeItems(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyItem"+radioAccount).find("tr").length==0){
			$.alert({
				text : "无可修改缴费信息！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfeeThree(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,2);
	}
	// FA订单 修改
	/* function openUserAddress(){
		var userId = $('#checkedUserId').val();
		var city=$('#checkedCity').val();
		var cityName=$('#checkedCityName').val();
		var orderId=$('#checkedOrderId').val();
		if(city==null){ city=""; }
		if(cityName==null){ cityName=""; }
		openModule('${ctx }/jsp/orderItemEdit.jsp',{'userId':userId,'city':city,'cityName':cityName,'orderId':orderId},'','','orderItemEdit');
	} */
</script>
</html>