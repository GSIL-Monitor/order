<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%-- 	<script type="text/javascript">
		var managerType = <%=CookieUtils.getJSONKey(request, "managerType") %>;
		$("#managerTypeCustList").val(managerType);
	</script> --%>
</head>
<!-- <input type="hidden" id="managerTypeCustList" name="managerTypeCustList" /> -->
<body>
  <div class="modal fade">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeModule('orderUser')">×</button>  
	        <h4 class="modal-title">查询订单</h4>
	      </div> 
	      <div class="modal-body" >
	      	<div class="modal-content-basic">
	      	<div class="info-select clearfix">
	      	 <form  class="form-inline" action="" method="post">
	      	 	<div class="row">
				   <div class="form-group col-xs-4">
					 <label><p>客户姓名:</p>
					 	<input type="text" class="form-control" name="userName" id="userNameSearch"/>
					 </label>
					</div>
				   <div class="form-group col-xs-4">
					 <label><p>联系方式:</p>
					 	<input type="text" class="form-control" name="userMobile" id="userMobileSearch"/>
					 </label>
					</div>
	            	<div class="form-group col-xs-1">
	                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryOrders(0,10);">查询</button>
					</div>
	            	<div class="form-group col-xs-1">
	                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="resetOrderSearch();">重置</button>
					</div>
				</div>
            	</form>
			</div>
			</div>
			<div class="panel-content " >
				<input type="hidden" id="checkedOrderId" name="checkedOrderId">
            	<input type="hidden" id="checkedCateType" name="checkedCateType">
            	<input type="hidden" id="checkedPriceType" name="checkedPriceType">
            	<input type="hidden" id="checkedOrderStatus" name="checkedOrderStatus">
            	<input type="hidden" id="checkedUserName" name="checkedUserName">
            	<input type="hidden" id="checkedUserNum" name="checkedUserNum">
            	<input type="hidden" id="checkedOrderType" name="checkedOrderType">
				<table class="table table-hover" style="width:100%;">
	                <thead>
	                	<tr>
							<th width="10%">序号</th>
							<th width="10%">订单编号</th>
							<th width="10%">客户姓名</th>
							<%-- <% if( !CookieUtils.getJSONKey(request, "managerType").equals("2")){ %>
							<th width="15%">客户电话</th>
							<%} %> --%>
							<th width="15%">订单类型</th>
							<th width="15%">创建时间</th>
							<th width="10%">订单状态</th>
							<th width="15%">订单来源</th>
	                   </tr>
	                  </thead>
	                  <tbody id="listBodyQualityUser" style="width:100%;">
	                </tbody>
	            </table>
	            <!-- <ul class="pagination pagination-sm navbar-right" id="pagequality"></ul> -->
			</div>
			<div class="row">
					<div class="panel-heading panel-new">
						<ul class="pagination pagination-sm navbar-right" id="pagequality"></ul>
					</div>
				</div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('orderUser')" >取消</button>  
	      	<button type="button" class="btn btn-sm btn-primary" onclick="nextModule()" >下一步</button>
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
<script type="text/javascript">
$(function(){
	queryOrders(0,10);
	//updateColorOrder($("#checkedOrderId").val(),$("#checkedCateType").val(),0,$("#checkedPriceType").val());
})
/**
 * 分页跳转使用
 */
function listBodyOrder(num,pageCount) {
	$("#listBodyQualityUser").empty();
	queryOrders(num,pageCount);
}

//点击选中一行
function updateColorOrder(id,cateType,value,priceType,orderStatus,userName,orderType){
	$("#checkedOrderId").val(id);
	$("#checkedCateType").val(cateType);
	$("#checkedPriceType").val(priceType);
	$("#checkedOrderStatus").val(orderStatus);
	$("#checkedUserName").val(userName);
	$("#checkedOrderType").val(orderType);
		if($("#listBodyQualityUser > tr").eq(value).attr("class")=="info"){
			$("#checkedUserNum").val("");
		}else{
			$("#checkedUserNum").val(value);
		}
} 
//重置查询条件
	function resetOrderSearch(){
		$("#userNameSearch").val('');
		$("#userMobileSearch").val('');
		queryOrders(0,10);
	}


//查询订单
function queryOrders(num,pageCount){
	var ctx = $("#ctx").val();
	var userName = $("#userNameSearch").val();
	var userMobile = $("#userMobileSearch").val();
	var orderStatusOther = "9,10";//已完成，已取消订单不显示
	//var loginLevel = '${param.loginLevel}';
	var loginLevel = 99;
	//var managerType = $("#managerTypeCustList").val();
	var cateTypeNot = 8;//排除解决方案订单,解决方案订单已有新功能
	$.ajax({
		url: ctx +"/order/queryOrder?curPage="+num+"&pageCount="+pageCount,
		data:{
			userName:userName,
			userMobile:userMobile,
			orderStatusOther:orderStatusOther,
			loginLevel:loginLevel,
			cateTypeNot:cateTypeNot
			//orderThreeStartFlag: 1  //显示他营单次服务，服务开始前4个小时的订单
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			$("#pagequality").pagination(data.page,listBodyOrder);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			
			var html="";
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						if(i==0){
							$("#checkedOrderId").val(v.id);
							$("#checkedCateType").val(v.cateType);
							$("#checkedPriceType").val(v.priceType);
							$("#checkedOrderStatus").val(v.orderStatus);
							$("#checkedUserName").val(v.userName);
							$("#checkedOrderType").val(v.orderType);
						}
							num=i+1;
							html +="<tr onclick='updateColorOrder(" +v.id +"," +v.cateType +"," +i +"," +v.priceType +"," +v.orderStatus+",\""+v.userName+"\","+v.orderType+")'>";
							html +="<td>" +(total + num - pageCount);
							html +="</td><td align='left'>"+v.orderCode;
							html +="</td><td align='left'>"+v.userName;
							/* if(managerType != null && managerType==2){
								html += "";
							}else{
								html +="</td><td>"+v.userMobile;
							} */
							html +="</td><td>"+v.typeText;
							html +="</td><td>"+numberToDatestr(v.createTime,8); 
							html +="</td><td align='left'>"+v.statusText;
							html +="</td><td align='left'>"+v.sourceText;
							html +="</td></tr>";
					})
					$("#pagequality").show();
			}else{
				html += "<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
				$("#pagequality").hide();
		   }
			$("#listBodyQualityUser").html(html);
			 /* //设置默认行背景色
			$("#listBodyQualityUser tr").each(function(n) {
				if(n==0){
					$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer'); return;
				}
			});  */
			/*表格点击行高亮*/
		    trColor("#listBodyQualityUser > tr");
		}
	});
}
//根据订单id和类型，跳转页面
function nextModule(){
	var orderId = $("#checkedOrderId").val();
	var cateType = $("#checkedCateType").val();
	var priceType = $("#checkedPriceType").val();
	var userNum = $("#checkedUserNum").val();
	var orderStatus = $("#checkedOrderStatus").val();
	var userName = $("#checkedUserName").val();
	var orderType = $("#checkedOrderType").val();
	if(userNum!=""){
		if(cateType==2){
			//跳转新增服务售后单
			  /* closeModule("qulityOrderList",function(){openModule('/order/jsp/quality/afterSaleServer.jsp',
					{"orderId":orderId,"cateType":cateType,"priceType":priceType,"flag":1,"userName":userName},
					{},{width:'70%'},'afterSaleServer')});   */
			  closeModule("qulityOrderList",function(){openModule('/order/jsp/quality/qualityServer.jsp',
					{"orderId":orderId,"cateType":cateType,"priceType":priceType,"orderType":orderType},
					{},{width:'70%'},'qualityServer')});  
		}else if(cateType==1 || cateType==4){
			//跳转新增服务售后单
			closeModule("qulityOrderList",function(){openModule('/order/jsp/quality/qualityServer.jsp',
					{"orderId":orderId,"cateType":cateType,"priceType":priceType,"orderType":orderType},
					{},{width:'70%'},'qualityServer')});
		}else if(cateType==3){
			//跳转新增FA售后单
			closeModule("qulityOrderList",function(){openModule('/order/jsp/quality/qualityItem.jsp',
					{"orderId":orderId,"cateType":cateType,"priceType":priceType},
					{},{width:'70%'},'qualityItem')});
		}else if(cateType==8 ){
			$.alert({millis:5000,top:'30%',text:"解决方案退费请在非订单售后中进行操作!"});
			//跳转新增解决方案大订单售后单
			/* closeModule("qulityOrderList",function(){openModule('/order/jsp/quality/qualitySolution.jsp',
					{"orderId":orderId,"cateType":cateType,"orderStatus":orderStatus},
					{},{width:'70%'},'qualitySolution')}); */
		}
		
	}else{
		$.alert({millis:3000,top:'30%',text:"请先选择订单！"});
	}
}

</script>
</html>

