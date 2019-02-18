<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body> 
  <div class="modal fade">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >×</button>  
	        <h4 class="modal-title">查询订单</h4>
	      </div> 
	      <div class="modal-body" >
	      	<div class="modal-content-basic">
	      	<div class="info-select clearfix">
	      	 <form  class="form-inline" action="" method="post" id="vph_cust_form">
	      	 	<div class="row">
				   <div class="form-group col-xs-6">
					 <label><p>客户姓名:</p>
					 	<input type="text" class="form-control" name="userName" id="vph_userNameSearch"/>
					 </label>
					</div>
				   <div class="form-group col-xs-6">
					 <label><p>联系方式:</p>
					 	<input type="text" class="form-control" name="userMobile" id="vph_userMobileSearch"/>
					 </label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-6">
					 <label><p>订单编号:</p>
					 	<input type="text" class="form-control" name="orderCode" id="vph_orderCode"/>
					 </label>
					</div>
					<div class="form-group col-xs-6">
					 <label><p>唯品会订单号:</p>
					 	<input type="text" class="form-control" name="vphOrderId" id="vph_vphOrderId"/>
					 </label>
					</div>
				</div>
				<div class="row">
	            	<div class="form-group col-xs-3 col-xs-offset-9">
	                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryVPHPay(0,10);">查询</button>
	                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="resetVPHSearch();">重置</button>
					</div>
				</div>
            	</form>
			</div>
			</div>
			<div class="panel-content " >
				<input type="hidden" id="vph_checkedId">
            	<input type="hidden" id="vph_checkedUserNum">
            	<input type="hidden" id="vph_UserMobile">
            	<input type="hidden" id="vph_UserName">
				<table class="table table-hover" style="width:100%;">
	                <thead>
	                	<tr>
							<th>序号</th>
							<th>订单编号</th>
							<th>唯品会订单号</th>
							<th>客户姓名</th>
							<th>订单类型</th>
							<th>创建时间</th>
							<th>订单状态</th>
							<th>订单来源</th>
	                   </tr>
	                  </thead>
	                  <tbody id="listBodyVPHUser" style="width:100%;">
	                </tbody>
	            </table>
			</div>
			<div class="row">
					<div class="panel-heading panel-new">
						<ul class="pagination pagination-sm navbar-right" id="pagevph"></ul>
					</div>
				</div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('orderUser')" >取消</button>  
	      	<button type="button" class="btn btn-sm btn-primary" onclick="vphnext()" >下一步</button>
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
<script type="text/javascript">
	var flag = '${param.flag}';
	var afterSaleId = '${param.afterSaleId}';
	$(function(){
		queryVPHPay(0,10);
	})
	/**
	 * 分页跳转使用
	 */
	function listBodyOrder(num,pageCount) {
		$("#listBodyVPHUser").empty();
		queryVPHPay(num,pageCount);
	}

	//点击选中一行
	function updateColor(id,value,userName,userMobile){
		$("#vph_checkedId").val(id);
		$("#vph_UserMobile").val(userMobile);
		$("#vph_UserName").val(userName);
		if($("#listBodyVPHUser > tr").eq(value).attr("class")=="info"){
			$("#vph_checkedUserNum").val("");
		}else{
			$("#vph_checkedUserNum").val(value);
		}
	} 
	//重置查询条件
	function resetVPHSearch(){
		$("#vph_userNameSearch").val('');
		$("#vph_userMobileSearch").val('');
		$("#vph_orderCode").val('');
		$("#vph_vphOrderId").val('');
		//queryVPHPay(0,10);
	}


//查询订单
function queryVPHPay(num,pageCount){
	var ctx = $("#ctx").val();
	$("#pagevph").show();
	var data = $("#vph_cust_form").serialize();
	$.ajax({
		url: ctx +"/afterSales/queryVPHOrder?curPage="+num+"&pageCount="+pageCount,
		data:data,
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			$("#pagevph").pagination(data.page,listBodyOrder);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			
			var html="";
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						if(i==0){
							$("#vph_checkedId").val(v.id);
							$("#vph_UserMobile").val(v.userMobile);
							$("#vph_UserName").val(v.userName);
						}
							num=i+1;
							html +="<tr onclick='updateColor(" +v.id +"," + i +",\"" + (v.userName==null ?"":v.userName)+"\",\"" + (v.userMobile==null ?"":v.userMobile) +"\")'>";
							html +="<td>" +(total + num - pageCount);
							html +="</td><td>"+v.orderCode;
							html +="</td><td>"+v.vphOrderId;
							html +="</td><td>"+v.userName;
							html +="</td><td>"+v.typeText;
							html +="</td><td>"+numberToDatestr(v.createTime,8); 
							html +="</td><td>"+v.statusText;
							html +="</td><td>"+v.sourceText;
							html +="</td></tr>";
					})
					$("#pagevph").show();
			}else{
				html += "<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
				$("#pagevph").hide();
		   }
			$("#listBodyVPHUser").html(html);
			/*表格点击行高亮*/
		    trColor("#listBodyVPHUser > tr");
		}
	});
}
//根据订单id和类型，跳转页面
function vphnext(){
	var orderId = $("#vph_checkedId").val();
	var userNum = $("#vph_checkedUserNum").val();
	var userName = $("#vph_UserName").val();
	var userMobile = $("#vph_UserMobile").val();
	if(userNum!=""){
		//跳转新增服务售后单
		closeModule("vipshopList",function(){
				openModule('/order/jsp/vipshop/vipshopEdit.jsp',
				{"orderId":orderId,"afterSaleId":afterSaleId,"flag":flag,"userName":userName,"userMobile":userMobile},
				{},{width:'70%'},'vipshopEdit')});
	}else{
		$.alert({millis:3000,top:'30%',text:"请先选择订单！"});
	}
}

</script>
</html>

