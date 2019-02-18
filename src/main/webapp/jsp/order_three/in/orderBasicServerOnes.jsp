<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!-- 服务订单基本信息 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
	.tabOrder{border-left:0px;border-top:1px solid #CCC;border-right:0px;border-bottom:1px solid #CCC;margin:auto auto auto 5px}
	.tabOrder tr td{text-align:left;}
	
</style>
</head>
<body>
	<input type="hidden" name="orderId" id="orderIdBasicOne">
	<input type="hidden" name="orderCateTypeBasic" id="orderCateTypeBasicOne">
	<div class="mytabs-wrap">
		<ul class="mytabs">
			<li class="tab-item tab-active" id="serverBasicOne1" onclick="serverBasicOne(1);">基本信息</li>
			<li class="tab-item" id="serverBasicOne3" onclick="serverBasicOne(3);">缴费</li>
		</ul>
	</div> 
	<div id="arr">
		<span id="left"><</span><span id="right">></span>
	</div>
	<div class="tab-content">
		<div class="tab-selected" id="basicServerOne">
			<div class="widget" id="setBasicServerOneEditButton" style="hieght:30px; display:none;" >
				<button type="button" class="btn btn-primary btn-xs" onclick="openModule('${ctx }/jsp/server/orderServerEdit.jsp','','',{width:'50%'},'orderServerEdit')">
					<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i></button>
			</div>
			<div class="widget-content">
				<div class="row">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<input type="hidden" id="serverTypeBasicOne" name="serverTypeBasicOne"/>
				<input type="hidden" id="personNumberBasicOne" name="personNumberBasicOne"/>
				<input type="hidden" id="personNumberWrokBasicOne" name="personNumberWrokBasicOne"/>
				<div class="row">
					<div class="col-xs-12">
	                <table class="table table-condensed">
	                    <tr>
	                        <td width="55%">
	                        	订单编号：<span id="orderCodeBasicOne"></span>
	                        </td>
	                        <td width="45%">
	                        	服务类型：<span id="servserTextBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
							<td width="45%" colspan="2">创建时间：<span id="createTimesServerOne"></span>
							</td>
						</tr>
	                    <tr>
	                        <td >
	                        	订单状态：<span id="statusTextBasicOne"></span>
	                        </td>
	                        <td >
	                        	支付状态：<span id="payTextServerOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                        	订单来源：<span id="sourceTextBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td >
	                        	开始时间：<span id="startTimeBasicOne"></span>
	                        </td>
	                        <td >
	                        	结束时间：<span id="endTimeBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	服务地址：<span id="addressBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	订单备注：<span id="remarkBasicOne"></span>
	                        </td>
	                    </tr>
	                </table>
	                </div>
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
	                        	客户姓名：<span id="nameBasicOne"></span>
	                        </td>
	                        <td width="50%">
	                        	客户电话：<span id="mobileBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	性别：<span id="sexBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	家庭地址：<span id="userAddressBasicOne"></span>
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
			                    <tbody id="orderInforServerOne">
			                    </tbody>
			                </table>
	                    </div>
                    </div>
				</div>
			</div>
		</div>
		<div id="personServerOne" style="display:none; border:1px solid #CCC;">
            <div class="widget">
            	<div class="row">
				<input type="hidden" id="checkedOneNeedsIds">
				<div class="widget">
					<button type="button" class="btn btn-primary btn-xs" onclick="getPersonAccordOne()">
						<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="自动匹配">自动匹配</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="openOrderNeedsOne();">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="手动匹配">手动匹配</i></button>
					
				</div>
			</div>
	        </div>
	        <div class="widget-content">
            	<div class="row">
					<div class="col-xs-12">
						<h4>匹配人员信息</h4>
					</div>
				</div>
	        </div>
			<div class="row">
				<div class="col-xs-12">
					<div id="needsPersonalsOne">
					</div>
				</div>
			</div>
	    </div>
		<div id="feesServerOne" style="display:none; border:1px solid #CCC;">
            <div class="widget">
	            	<button type="button" class="btn btn-primary btn-xs" onclick="addAccountServerOneThree();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updateAccountServerOneThree();" id ="updateAccountServerOneBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServerOneThree();" id="deleteAccountServerOneBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除结算">删除结算</i></button>
	            </div>
	            <div class="widget">
					<button type="button" class="btn btn-primary btn-xs" onclick="addPayfeeServerOneThree();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updatePayfeeServerOneThree()" id="updatePayfeeServerOneBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i></button>
	            </div>
	            <div class="widget-content">
	                <div class="project-order-news">
	                    <div class="row">
	                        <div class="col-xs-12"><h4>缴费信息</h4></div>
	                    </div>
	                    <div id="accountListBodyOne">
	                	</div>
	                </div>
	            </div>
		</div>
	</div>
</body>
<script>
	function setBasicServerOne(orderId,cateType,totalPay,orderStatus){
		$("#orderIdBasicOne").val(orderId);
		$("#orderCateTypeBasicOne").val(cateType);
		if(orderStatus==1 || orderStatus==2 || orderStatus==3){
			$("#setBasicServerOneEditButton").css("display","block");
		}else{
			$("#setBasicServerOneEditButton").css("display","none");
		}
		getServerBasicOne(orderId);
		queryAccountThree(orderId,"accountListBodyOne",cateType,totalPay);
		showBtnThree(cateType,orderId);
		//getPersionHandsOne("");
	}
	//取到订单详细信息
	function getServerBasicOne(orderId){
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
						// 详细信息
						$("#orderCodeBasicOne").text(v.orderCode);
						$("#serverTypeBasicOne").val(v.serverType);
						$("#personNumberBasicOne").val(v.personNumber);
						$("#servserTextBasicOne").text(v.typeText);
						$("#startTimeBasicOne").text(numberToDatestr(v.startTime,12));
						$("#endTimeBasicOne").text(numberToDatestr(v.endTime,12));
						$("#statusTextBasicOne").text(v.statusText);
						$("#payTextServerOne").text(v.payText);
						$("#sourceTextBasicOne").text(v.sourceText);
						$("#addressBasicOne").text(v.address);
						$("#remarkBasicOne").text(v.remark);
						$("#checkedTotalPay").text(v.totalPay);
						$("#createTimesServerOne").text(numberToDatestr(v.createTime,12));
						// 客户信息
						$("#nameBasicOne").text(v.order.userName);
						$("#mobileBasicOne").text(v.order.userMobile);
						$("#sexBasicOne").text(v.sex==1?"男":"女");
						$("#birthBasicOne").text(v.birth==""?"":numberToDatestr(v.birth,8));
						$("#userAddressBasicOne").text(v.userAddress);
						
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
					$("#orderInforServerOne").html(html);
					//document.getElementById("orderInforServerOne").innerHTML=html;
				}
			}
		});
	}
	// 调区显示区域
	function serverBasicOne(num){
		if(num==1){
			$("#serverBasicOne1").addClass("tab-selected");
			$("#serverBasicOne2").removeClass("tab-selected");
			$("#serverBasicOne3").removeClass("tab-selected");
			$("#basicServerOne").css("display","block");
			$("#personServerOne").css("display","none");
			$("#feesServerOne").css("display","none");
			
		}else if(num==2){
			$("#serverBasicOne1").removeClass("tab-selected");
			$("#serverBasicOne2").addClass("tab-selected");	
			$("#serverBasicOne3").removeClass("tab-selected");
			$("#basicServerOne").css("display","none");
			$("#personServerOne").css("display","block");
			$("#feesServerOne").css("display","none");
			//getPersionHandsOne("");
		}else if(num==3){
			$("#serverBasicOne1").removeClass("tab-selected");
			$("#serverBasicOne2").removeClass("tab-selected");	
			$("#serverBasicOne3").addClass("tab-selected");
			$("#basicServerOne").css("display","none");
			$("#personServerOne").css("display","none");
			$("#feesServerOne").css("display","block");
		}
	}
	
	/**新增结算*/
	function addAccountServerOneThree(){
		orderBasicAccountThree(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),0,parent.$("#checkedIscollection").val());
	}
	
	/**修改结算*/ 
	function updateAccountServerOneThree(){
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
	
	/**删除结算*/ 
	function deleteAccountServerOneThree(){
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
		queryAccountThree(parent.$("#checkedOrderId").val(),"accountListBodyOne");
	}
	
	/**新增缴费*/ 
	function addPayfeeServerOneThree(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyOne"+radioAccount).find("tr").length>0){
			$.alert({
				text : "当前结算单已经生成缴费信息，无法新增！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfeeThree(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,1);
	}
	
	/**修改缴费*/ 
	function updatePayfeeServerOneThree(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyOne"+radioAccount).find("tr").length==0){
			$.alert({
				text : "无可修改缴费信息！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
		orderBasicPayfeeThree(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,2);
		
	}
	
</script>
</html>