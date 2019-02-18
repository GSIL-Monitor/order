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
	.orderLeftSpan{width:30px;font-weight:bold;font-family:'黑体';font-size:20px;margin-left:5px;}
	
	.orderCounttab{}
</style>
<script type="text/javascript" src="${ctx}/js/agreement.js"></script>
</head>
<body>
	<div>
		<div class="mytabs-wrap">
			<ul class="mytabs mytabsss" id="mytabsss">
				<li class="tab-item orderCounttab tab-active">基本信息</li>
				<li class="tab-item orderCounttab" onclick="makeSuerPersons();">确定人员</li>
				<li class="tab-item orderCounttab" onclick="queryAgreement();">三方协议</li>
				<li class="tab-item orderCounttab">缴费</li>
				<li class="tab-item orderCounttab" onclick="serverInterviews();">上户</li>
			</ul>
		</div>
		<div id="arr">
			<span id="left"><</span><span id="right">></span>
		</div>
		<div class="tab-content">
			<div class="main tab-selected" id="basicCont">
				<div class="widget" id="setBasicServerEditButton" style="hieght:30px; display:none;" >
					<button type="button" class="btn btn-primary btn-xs" onclick="openModule('${ctx }/jsp/server/lined.jsp','','','','orderLined')">
						<i class="glyphicon glyphicon-refresh" data-toggle="tooltip" data-placement="top" title="排期">排期</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="openModule('${ctx }/jsp/server/orderServerEdit.jsp','','','','orderServerEdit')">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改">修改</i></button>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>详细信息</h4>
					</div>
				</div>
				<div class="widget-content" >
				<input type="hidden" id="serverTypeBasics" name="serverType"/>
	                <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
	                    <tr>
	                        <td width="55%">
	                        	订单编号：<span id="orderCodeBasics"></span>
	                        </td>
	                        <td width="45%">
	                        	服务类型：<span id="servserTextBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
							<td width="45%" colspan="2">创建时间：<span id="createTimesServer"></span>
							</td>
						</tr>
	                    <tr>
	                        <td >
	                        	订单状态：<span id="statusTextBasics"></span>
	                        </td>
	                        <td >
	                        	支付状态：<span id="payTextServer"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                        	订单来源：<span id="sourceTextBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td >
	                        	开始时间：<span id="startTimeBasics"></span>
	                        </td>
	                        <td >
	                        	结束时间：<span id="endTimeBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	服务地址：<span id="addressBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td  colspan="2" >
	                        	面试时间：<span id="interviewTimeBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	面试地址：<span id="interviewAddressBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	订单备注：<span id="remarkBasics"></span>
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
	                        	客户姓名：<span id="nameBasics"></span>
	                        </td>
	                        <td width="50%">
	                        	客户电话：<span id="mobileBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	性别：<span id="sexBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2" >
	                        	家庭地址：<span id="userAddressBasics"></span>
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
			                    <tbody id="orderInforServerCont">
			                    </tbody>
			                </table>
	                    </div>
                    </div>
				</div>
            	<div class="row">
					<div class="col-xs-12">
						<h4>需求信息</h4>
					</div>
				</div>
            	<div>
            		<table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
	                    <tr>
	                        <td width="50%">
	                        	年龄要求：<span id="minAgeBasics"></span>
	                        	~<span id="maxAgeBasics"></span>
	                        </td>
	                        <td width="50%">
	                        	籍贯要求：<span id="originBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td width="50%">
	                        	等级要求：<span id="personLevelTextBasics"></span>
	                        </td>
	                        <td width="50%">
	                        	学历要求：<span id="educationTextBasics"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                        	备注：<span id="remarkBasics2"></span>
	                        </td>
	                    </tr>
	            	</table>
            	</div>
			</div>
			<div class="main" id="fees">
	            <div class="widget">
	            	<button type="button" class="btn btn-primary btn-xs" onclick="addAccountServer();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增结算">新增结算</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updateAccountServer();"id="updateAccountServerBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改结算">修改结算</i></button>
					<button type="button" class="btn btn-primary btn-xs" onclick="deleteAccountServer();" id="deleteAccountServerBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="删除结算">删除结算</i></button>
	            </div>
	            <div class="widget">
					<button type="button" class="btn btn-primary btn-xs" onclick="addPayfeeServer();">
						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="新增缴费">新增缴费</i></button>
	            	<button type="button" class="btn btn-primary btn-xs" onclick="updatePayfeeServer()" id="updatePayfeeServerBtn">
						<i class="glyphicon glyphicon-pencil" data-toggle="tooltip" data-placement="top" title="修改缴费">修改缴费</i></button>
	            </div>
	            <div class="widget-content">
	                <div class="project-order-news">
	                    <div class="row">
	                        <div class="col-xs-12"><h4>缴费信息</h4></div>
	                    </div>
	                    <div id="accountListBodyCont">
	                	</div>
	                </div>
	            </div>
			</div>
			<div class="main" id="interviews">
				<div class="widget">
<!-- 					<button type="button" class="btn btn-primary btn-xs" onclick=""> -->
<!-- 						<i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="top" title="上户">上户</i></button> -->
<!-- 					<button type="button" class="btn btn-primary btn-xs" onclick=""> -->
<!-- 						<i class="glyphicon glyphicon-off" data-toggle="tooltip" data-placement="top" title="下户">下户</i></button> -->
<!-- 					<button type="button" class="btn btn-primary btn-xs" onclick=""> -->
<!-- 						<i class="glyphicon glyphicon-off" data-toggle="tooltip" data-placement="top" title="结单">结单</i></button> -->
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>确定人员</h4>
					</div>
				</div>
				<div id="interviewPersonals">
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>上户人员</h4>
					</div>
				</div>
				<div id="oldNeedsPersonals"></div>
			</div>
			
		</div>
	</div>
</body>
<script>
	function setBasicServer(orderId,cateType,totalPay,orderStatus){
		// 判断哪些状态下订单可以修改
		if(orderStatus==1 || orderStatus==2 || orderStatus==3 || orderStatus==4 || orderStatus==5 ){
			$("#setBasicServerEditButton").css("display","block");
		}else{
			$("#setBasicServerEditButton").css("display","none");
		}
		getServerBasics(orderId);
		//queryAgreement();
		queryAccount(orderId,"accountListBodyCont",cateType,totalPay);
		//makeSuerPersons();
		//serverInterviews();
		showBtn(cateType,orderId);
		tabsss(".mytabs-wrap");
	}
	function tabsss(tabsw){
        $(document).ready(function() {
            tabWidth = $(tabsw).outerWidth();
            tabw("#right","#left",".orderCounttab",".mytabsss");
        });
        $(window).resize(function() {
            tabWidth = $(tabsw).outerWidth();
            tabw("#right","#left",".orderCounttab",".mytabsss");
        });
    }
	//取到订单详细信息
	function getServerBasics(orderId){
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
				if (data.msg =="00") {
					var html = "" ;
					var num =  $.each(data.list,function(i,v){
						$("#orderCodeBasics").text(v.orderCode);
						$("#serverTypeBasics").val(v.serverType);
						$("#servserTextBasics").text(v.typeText);
						$("#startTimeBasics").text(numberToDatestr(v.startTime,12));
						$("#endTimeBasics").text(numberToDatestr(v.endTime,12));
						$("#interviewTimeBasics").text(numberToDatestr(v.interviewTime,12));
						$("#criticalTextBasics").text(v.criticalText);
						$("#payTextServer").text(v.payText);
						$("#statusTextBasics").text(v.statusText);
						$("#sourceTextBasics").text(v.sourceText);
						$("#addressBasics").text(v.address);
						$("#interviewAddressBasics").text(v.interviewAddress);
						$("#remarkBasics").text(v.order.remark);
						$("#createTimesServer").text(numberToDatestr(v.createTime,12));
						// 基础信息：需求推送
						$("#remarkBasics2").text(v.remark);
						$("#minAgeBasics").text(v.minAge);
						$("#maxAgeBasics").text(v.maxAge);
						$("#originBasics").text(v.originText);
						$("#personLevelTextBasics").text(v.personLevelText);
						$("#educationTextBasics").text(v.educationText);
						// 客户信息
						$("#nameBasics").text(v.order.userName);
						$("#mobileBasics").text(v.order.userMobile);
						$("#sexBasics").text(v.sex==1?"男":"女");
						$("#birthBasics").text(v.birth==""?"":numberToDatestr(v.birth,8));
						$("#userAddressBasics").text(v.userAddress);
						// 需求推送：需求推送
						$("#remarkBasicNeeds").text(v.remark);
						$("#minAgeBasicNeeds").text(v.minAge);
						$("#maxAgeBasicNeeds").text(v.maxAge);
						$("#originBasicNeeds").text(v.originText);
						$("#personLevelTextBasicNeeds").text(v.personLevelText);
						$("#educationTextBasicNeeds").text(v.educationText);
						
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
					document.getElementById("orderInforServerCont").innerHTML=html;
				}
			}
		});
	}
	
	
	// 新增结算
	function addAccountServer(){
		orderBasicAccount(parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),parent.$("#checkedTotalPay").val(),0,parent.$("#checkedIscollection").val());
	}
	// 修改结算
	function updateAccountServer(){
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
	function deleteAccountServer(){
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
				text : "当前结算单已对账，无删除！"
			});
			return ;
		}
		deleteAccountById(radioAccount);
		queryAccount(parent.$("#checkedOrderId").val(),"accountListBodyCont");
	}
	// 新增缴费
	function addPayfeeServer(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyCont"+radioAccount).find("tr").length>0){
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
	function updatePayfeeServer(){
		var radioAccount = $('input:radio[name=radioAccount]:checked').val();
		if($("#accountListBodyCont"+radioAccount).find("tr").length==0){
			$.alert({
				text : "无可修改缴费信息！"
			});
			return ;
		}
		var accountAmount = $("#accountAmount"+radioAccount).text();
		var userMobile = parent.$("#checkedUserMobile").val();
        var result=checkAccountReviewState(radioAccount);
        if(result!="00"){
        	 $.alert({
                 text: "缴费已确认，无法修改！如有疑问，请联系结算中心"
             });
             return;
        }
		orderBasicPayfee(radioAccount,parent.$("#checkedOrderId").val(),parent.$("#checkedCateType").val(),accountAmount,userMobile,2);
	}
	
</script>
</html>