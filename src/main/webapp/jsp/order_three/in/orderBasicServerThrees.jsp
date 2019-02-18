<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!-- 服务订单基本信息 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
	.tabOrder{width:100%; border-left:0px;border-top:1px solid #CCC;border-right:0px;border-bottom:1px solid #CCC;margin:auto auto auto 5px}
	.tabOrder tr td{text-align:left;}
	
</style>
</head>
<body>
	<input type="hidden" name="orderId" id="orderIdBasicThree">
	<input type="hidden" name="orderCateTypeBasic" id="orderCateTypeBasicThree">
	<div class="mytabs-wrap">
		<ul class="mytabs">
			<li class="tab-item tab-active" id="serverBasicThree1" onclick="serverBasicThree(1);">基本信息</li>
			<li class="tab-item" id="serverBasicThree2" onclick="serverBasicThree(2);">缴费</li>
		</ul>
	</div> 
	<div id="arr">
		<span id="left"><</span><span id="right">></span>
	</div>
	<div class="tab-content">
		<div class="tab-selected" id="basicServerThree">
<%-- 			<div class="widget" id="setBasicServerThreeEditButton" style="hieght:30px; display:none;" >
				<button type="button" class="btn btn-primary btn-xs" onclick="openModule('${ctx }/jsp/server/orderServerEdit.jsp','','',{width:'50%'},'orderServerEdit')">
					<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i></button>
			</div>
 --%>			<div class="widget-content">
				<div class="row">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<input type="hidden" id="serverTypeBasicThree" name="serverType"/>
				<div>
	                <table class="table table-condensed">
	                    <tr>
	                        <td width="55%">
	                        	订单编号：<span id="orderCodeBasicThree"></span>
	                        </td>
	                        <td width="45%">
	                        	服务类型：<span id="servserTextBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
							<td width="45%" colspan="2">创建时间：<span id="createTimesServerThree"></span>
							</td>
						</tr>
	                    <tr>
	                        <td >
	                        	订单状态：<span id="statusTextBasicThree"></span>
	                        </td>
	                        <td >
	                        	支付状态：<span id="payTextServerThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                        	订单来源：<span id="sourceTextBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td >
	                        	开始时间：<span id="startTimeBasicThree"></span>
	                        </td>
	                        <td >
	                        	结束时间：<span id="endTimeBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	服务地址：<span id="addressBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	订单备注：<span id="remarkBasicThree"></span>
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
	           		<table class="table table-condensed">
	                    <tr>
	                        <td width="50%">
	                        	客户姓名：<span id="nameBasicThree"></span>
	                        </td>
	                        <td width="50%">
	                        	客户电话：<span id="mobileBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	性别：<span id="sexBasicThree"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	家庭地址：<span id="userAddressBasicThree"></span>
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
			                    <tbody id="orderInforServerThree">
			                    </tbody>
			                </table>
	                    </div>
                    </div>
				</div>
			</div>
		</div>
		<div id="feesServerThree" style="display:none; border:1px solid #CCC;">
            <div class="widget">
	            	<button type="button" class="btn btn-primary btn-xs" onclick="addAccountServerThree();" id="addAccountServerThreeBtn">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updateAccountServerThree();" id="updateAccountServerThreeBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServerThree();" id="deleteAccountServerThreeBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除结算">删除结算</i></button>
	            </div>
	            <div class="widget">
					<button type="button" class="btn btn-primary btn-xs" onclick="addPayfeeServerThree();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updatePayfeeServerThree()" id="updatePayfeeServerThreeBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i></button>
	            </div>
	            <div class="widget-content">
	                <div class="project-order-news">
	                    <div class="row">
	                        <div class="col-xs-12"><h4>缴费信息</h4></div>
	                    </div>
	                    <div id="accountListBodyThree">
	                	</div>
	                </div>
	            </div>
		</div>
	</div>
</body>
<script>
	function setBasicServerThree(orderId,cateType,totalPay,orderStatus){
		$("#orderIdBasicThree").val(orderId);
		$("#orderCateTypeBasicThree").val(cateType);
		// 判断哪些状态下订单可以修改
		if(orderStatus==1 || orderStatus==2 || orderStatus==18){
			$("#setBasicServerThreeEditButton").css("display","block");
		}else{
			$("#setBasicServerThreeEditButton").css("display","none");
		}
		getServerBasicThree(orderId);
		//queryAccount(orderId,"accountListBodyThree",cateType,totalPay);
		//showBtn(cateType,orderId);
	}
	//取到订单详细信息
	function getServerBasicThree(orderId){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/orderbase/queryOrderBasicServer",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg ="00") {
					var html = "";
					var num =  $.each(data.list,function(i,v){
						$("#orderCodeBasicThree").text(v.orderCode);
						$("#serverTypeBasicThree").val(v.serverType);
						$("#servserTextBasicThree").text(v.typeText);
						$("#startTimeBasicThree").text(numberToDatestr(v.startTime,12));
						$("#endTimeBasicThree").text(numberToDatestr(v.endTime,12));
						$("#statusTextBasicThree").text(v.statusText);
						$("#payTextServerThree").text(v.payText);
						$("#sourceTextBasicThree").text(v.sourceText);
						$("#addressBasicThree").text(v.address);
						$("#remarkBasicThree").text(v.remark);
						$("#checkedTotalPay").text(v.totalPay);
						$("#createTimesServerThree").text(numberToDatestr(v.createTime,12));
						// 客户信息
						$("#nameBasicThree").text(v.order.userName);
						$("#mobileBasicThree").text(v.order.userMobile);
						$("#sexBasicThree").text(v.sex==1?"男":"女");
						$("#birthBasicThree").text(v.birth==""?"":numberToDatestr(v.birth,8));
						$("#userAddressBasicThree").text(v.userAddress);
						
						// 商品信息
						html +="<tr style='height:25px;'>";
						html +="<td>"+v.productName +"(" +v.productUnitText+")</td>";
						html +="<td>"+v.productPriceTypeText+"</td>";
						html +="<td>"+v.nowPrice+"</td>";
						html +="<td>"+v.quantity+"</td>";
						html +="<td>"+v.productSpec+"</td>";
						html += "</tr>";
						
					})
					html += "<tr></tr>" ;
					$("#orderInforServerThree").html(html);
				}
			}
		});
	}
	// 调区显示区域
	function serverBasicThree(num){
		if(num==1){
			$("#serverBasicThree1").addClass("tab-selected");
			$("#serverBasicThree2").removeClass("tab-selected");
			$("#basicServerThree").css("display","block");
			$("#feesServerThree").css("display","none");
		}else if(num==2){
			$("#serverBasicThree1").removeClass("tab-selected");
			$("#serverBasicThree2").addClass("tab-selected");	
			$("#basicServerThree").css("display","none");
			$("#feesServerThree").css("display","block");
		}
	}
	
	// 新增结算
	function addAccountServerThree(){
		orderBasicAccount(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),0,parent.$("#checkedIscollection").val());
	}
	// 修改结算
	function updateAccountServerThree(){
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
		orderBasicAccount(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),radioAccount);
	}
	// 删除结算
	function deleteAccountServerThree(){
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
		deleteAccountById(radioAccount);
		//queryAccount(parent.$("#checkedOrderId").val(),"accountListBodyThree");
	}
	// 新增缴费
	function addPayfeeServerThree(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyThree"+radioAccount).find("tr").length>0){
			$.alert({
				text : "当前结算单已经生成缴费信息，无法新增！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfee(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,1);
	}
	// 修改缴费
	function updatePayfeeServerThree(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyThree"+radioAccount).find("tr").length==0){
			$.alert({
				text : "无可修改缴费信息！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfee(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,2);
	}
	
</script>
</html>