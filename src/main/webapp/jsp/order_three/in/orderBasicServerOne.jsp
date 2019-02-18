<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<input type="hidden" name="orderId" id="orderIdBasicOne">
	<input type="hidden" name="orderCateTypeBasic" id="orderCateTypeBasicOne">
	<input type="hidden" name = "threeOrderUserId" id="threeOrderUserId">
	<div class="mytabs-wrap">
		<ul class="mytabs">
			<li class="tab-item tab-active" id="serverBasicOne1" onclick="serverBasicOne(1);">基本信息</li>
			<li class="tab-item" id="serverBasicOne2" onclick="serverBasicOne(2);">缴费</li>
		</ul>
	</div> 
	<div id="arr">
		<span id="left"><</span><span id="right">></span>
	</div>
	<div class="tab-content">
		<div class="tab-selected" id="basicServerOne">
			<div class="widget-content">
				<div class="row">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<input type="hidden" id="serverTypeBasicOne" name="serverType"/>
				<div>
	                <table class="table table-condensed">
	                    <tr>
	                        <td colspan="2">
	                        	订单ID：<span id="orderCodeBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                    <td width="50%">
	                        	订单分类：<span id="servserTextBasicOne"></span>
	                        </td>
	                    <td width="50%">
	                        	服务名称：<span id="servserProductName"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td >
	                        	订单状态：<span id="statusTextBasicOne"></span>
	                        </td>
	                        <td >
	                        	订单渠道：<span id="sourceTextBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                        	开始时间：<span id="startTimeBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                    	 <td colspan="2">
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
	                        	联系方式：<span id="mobileBasicOne"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	性别：<span id="sexBasicOne"></span>
	                        </td>
	                        <td width="50%">
	                        	出生年月：<span id="birthBasicOne">中年</span>
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
						<h4>管家信息</h4>
					</div>
				</div>
				<div>
	           		<table class="table table-condensed">
	                   <tr>
							<td width="40%">所属管家：<span id="orderServerAuthManagerName"></span>
							</td>
							<td width="60%">联系方式：<span id="orderServerAuthManagertPhone"></span>
							</td>
						</tr>
	            	</table>
		        </div>
			</div>
		</div>
		<div id="feesServerOne" style="display:none; border:1px solid #CCC;">
            <div class="main tab-selected">
			<div class="widget-content">
				<div class="row" style="margin-top: 10px;">
					<div class="form-group  col-xs-12">
						<button class="btn btn-primary btn-sm" type="button" onclick="addAccountThreeOne()">
							<em class="add glyphicon glyphicon-plus-sign"></em>新增结算单
						</button>
					</div>
				</div>
				<!-- 循环结算单信息开始 -->
				<div id="threeOrderAccountsList">
					
				</div>
				<!-- 循环结算单信息结束 -->
			</div>
		</div>
		</div>
	</div>
</body>
<script>
	function setBasicServerOne(orderId,cateType){
		$("#orderIdBasicOne").val(orderId);
		$("#orderCateTypeBasicOne").val(cateType);
		//serverBasicOne(1);
		getServerBasicOne(orderId);
		queryThreeOrderAccounts(orderId);
	}
	// 新增结算
	function addAccountThreeOne(){   
		orderBasicAccount(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val());
	}
	
	
	//取到订单详细信息
	function getServerBasicOne(orderId){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/threeorderbase/queryOrderBasicServer",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg ="00") {
					var num =  $.each(data.list,function(i,v){
						$("#orderCodeBasicOne").text(v.orderCode);
						$("#serverTypeBasicOne").val(v.serverType);
						$("#servserTextBasicOne").text(v.typeText);
						$("#startTimeBasicOne").text(numberToDatestr(v.startTime,12));
						$("#endTimeBasicOne").text(numberToDatestr(v.endTime,12));
						$("#statusTextBasicOne").text(v.statusText);
						$("#sourceTextBasicOne").text(v.sourceText);
						$("#addressBasicOne").text(v.address);
						$("#remarkBasicOne").text(v.remark);
						// 客户信息
						$("#nameBasicOne").text(v.order.userName);
						$("#servserProductName").text(v.productName);
						$("#threeOrderUserId").val(v.userId);
						$("#mobileBasicOne").text(v.order.userMobile);
						$("#sexBasicOne").text(v.sex==null || v.sex ==""?"":(v.sex==1?"男":"女"));
						$("#birthBasicOne").text(v.birth==""?"":numberToDatestr(v.birth,8));
						$("#userAddressBasicOne").text(v.userAddress);
						
						$("#orderItemAuthManagerName").text(v.authManagerName);
						$("#orderItemAuthManagertPhone").text(v.authManagertPhone);
					})
				}
			}
		});
	}
	function getServerFees(orderId){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/itemDetailServer/queryOrderNeedServer",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg ="00") {
					var num =  $.each(data.list,function(i,v){
					})
				}
			}
		});
		
	}
	
	function serverBasicOne(num){
		if(num==1){
			$("#serverBasicOne1").addClass("tab-selected");
			$("#serverBasicOne1").addClass("tab-active");
			$("#serverBasicOne2").removeClass("tab-selected");
			$("#serverBasicOne2").removeClass("tab-active");
			$("#basicServerOne").css("display","block");
			$("#feesServerOne").css("display","none");
		}else if(num==2){
			$("#serverBasicOne1").removeClass("tab-selected");
			$("#serverBasicOne1").removeClass("tab-active");
			$("#serverBasicOne2").addClass("tab-selected");	
			$("#serverBasicOne2").addClass("tab-active");	
			$("#basicServerOne").css("display","none");
			$("#feesServerOne").css("display","block");
		}
	}
	function queryThreeOrderAccounts(orderId){
		var ctx = $("#ctx").val();
		var cateType = $("#checkedCateType").val();
		$.ajax({
			url : ctx + "/threeOrderOut/queryOrderOneAccounts",
			data : {
				orderId : orderId
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				var html="";
				if (data.msg == "00") {
					$.each(data.list,function(i,v){
						html += "<div class='row info-select'>";
						html += "<div class='form-group  col-xs-7'><label><p>结算单号："+v.accountsId+"</p></label></div>";
						html += "<div class='form-group  col-xs-5'><label><p>时间："+v.createTime+"</p></label></div>";
						html += "</div>";
						html += "<div class='row info-select'>";
						html += "<div class='form-group  col-xs-7'><label><p>应收："+v.accountAmount+"</p></label></div>";
						html += "<div class='form-group  col-xs-5'>";
						if(v.children.length==0){
							html += "<button type='button' class='btn btn-primary btn-sm' onclick='addPayfee("+v.accountsId+","+v.accountAmount+")'>新增缴费</button>";
						}else{
							html += "<button type='button' class='btn btn-primary btn-sm' disabled='disabled'>新增缴费</button>";
						}
						html += "</div>";
						html += "</div>";
						
						html += "<div class='panel-content table-responsive'>";
						html += "<table class='table table-hover table-striped' style='width:600px;'>";
						html += "<thead>";
						html += "<tr><th width='5%'>序号</th><th width='5%'>对账状态</th><th width='25%'>缴费人</th><th width='25%'>缴费方式</th><th width='25%'>银行名称</th><th width='25%'>缴费金额</th><th width='25%'>流水号</th></tr>";
						html += "</thead>";
						html += "<tbody style='width: 100%'>";
						if(v.children.length!=0){
							$.each(v.children,function(j,m){
								html += "<tr>";
								html += "<th>"+(j+1)+"</th>";
								if(m.accountStatus==1){
									html += "</td><td style='color:#006400'>已对账";
								}else{
									html += "</td><td style='color:#FF0000'>未对账";
								}
								html += "<th>"+m.postUser+"</th>";
								if(m.feePost == "1002"){
									html += "<th>银行转账</th>";
								}else if(m.feePost == "1005"){
									html += "<th>POS机</th>";
								}else if(m.feePost == "1008"){
									html += "<th>融汇天下POS机</th>";
								}else if(m.feePost == "1010"){
									html += "<th>礼品卡</th>";
								}else if(m.feePost == "1101"){
									html += "<th>他人代收</th>";
								}
								html += "<th>"+m.postBank+"</th>";
								html += "<th>"+m.feeSum+"</th>";
								html += "<th>"+m.bankflowNum+"</th>";
								html += "</tr>";
							})
							
						}else{
							html += "<tr><td colspan='4'>暂无记录</td></tr>";
						}
						html += "";
						html += "</tbody>";
						html += "</table>";
						html += "</div>";
					})
					if(cateType == 1 || cateType ==2 || cateType == 4){
						$("#threeOrderAccountsList").html(html);
					}else if(cateType == 3 ){
						$("#threeOrderFaAccountsList").html(html);
					}
				}
			}
		});
	}
	
	function addPayfee(accountsId,accountAmount) {
		var cateType = $("#checkedCateType").val();
		if(cateType == 1 || cateType ==2 || cateType == 4){
			var orderId = $("#orderIdBasicOne").val();
		}else if(cateType == 3 ){
			var orderId = $("#orderIdBasicsItem").val();
		}
		var userId = $("#threeOrderUserId").val();
		var ctx = $("#ctx").val();
		var data = {
			orderId : orderId,
			accountsId : accountsId,
			userId : userId,
			accountAmount : accountAmount,
		}
		
		openModule(ctx + '/jsp/order_three/in/threeOrderPayfee.jsp', data, '',
				{
					width : '70%'
				}, 'threeOrderPayfee');
	}
	
	
</script>
</html>