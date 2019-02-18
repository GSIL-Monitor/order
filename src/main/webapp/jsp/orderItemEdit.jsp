<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript">
		<%
			String userId = request.getParameter("userId");	
			String city = request.getParameter("city");
			String cityName = request.getParameter("cityName");
			String orderId = request.getParameter("orderId");
			String userName = request.getParameter("userName");
		%>
	</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade" id="modelFrameOrderItemEdit">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="setOrderCloseModule('orderItemEdit');">×</button>  
		        <h4 class="modal-title">修改收货地址</h4>
		     </div> 
	      <div class="modal-body">
	       <form action="" method="post" class="form-inline">
	      		<input type="hidden" id="oderItemEditUserId" value="<%=userId %>">
	      		<input type="hidden" id="oderItemEditCity" value="<%=city %>">
	      		<input type="hidden" id="oderItemEditOrderId" value="<%=orderId %>">
	      		<input type="hidden" id="orderItemEditReceiverId">
	            <header class="mb10"><h4>地址信息</h4> </header>
	            <div class="info-select clearfix">
	            	<div class="row">
			            <div class="form-group col-xs-6">
			            	<label>
			                    <p>客户姓名：</p>
			                    <input id="orderItemEditUserName" type="text" class="form-control" 
			                    	style="width:145px;" value="<%=userName %>">
			                </label>
			            </div>
			            <div class="form-group col-xs-6">
			            	<label>
			                    <p style="width:92px;">订单城市：</p>
			                    <span id="orderItemEditCityName"><%=cityName %></span>
			                </label>
			            </div>
			    	</div>
            		<div class="row">
			            <div class="form-group col-xs-2">
			                <label>
			                    <p>收货地址：</p>
			                </label>
			            </div>
						<div class="form-group col-xs-8" >
			                <div class="panel-content">
			                    <table id="listBodyOderItemEditAddress" class="table order-table" style="margin-bottom:0">
								</table>
							</div>
						</div>
						<div class="form-group col-xs-2">
			                <button type="button" class="btn btn-primary btn-sm"
								onclick="openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:5,userId:$('#oderItemEditUserId').val(),userAddressId:0},'','','orderItemaddressAdd')">新增地址</button>
			            </div>
					</div>
                 </div>
			</form>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderCloseModule('orderItemEdit');" >取消</button>  
	        <button type="button" class="btn btn-sm btn-primary" onclick="saveOrderItemEdit();">提交</button>
	      </div>  
	    </div>
	  </div>
	</div>    
  
</body>
<script type="text/javascript">
$(function() {
	orderItemEditCheckAddress();
});

//通过电话号码取到送贷地址
function orderItemEditCheckAddress() {
	var ctx = $("#ctx").val();
	var userId = $("#oderItemEditUserId").val();
	var city = $("#oderItemEditCity").val();
	$.ajax({
		url : ctx + "/orderbase/queryUserAddressMapper",
		data : {
			userId:userId,
			/* cityCode:city, */
			valid : 1
		},
		type : "post",
		dataType : "json",
		async : false,
		success : function(data) {
			var html = "";
			if (data.msg = "00") {
				var num = $.each(data.list,function(i, v) {
					num = i + 1;
					html += "<tbody><tr></tr>" ;
					html += "<tr><td rowspan='2' class='bor-r'>" ;
					html += "<input type='radio' name='orderItemEditReceiverId' onclick='setOderItemEditReceiverId(this);' value='" +v.id +"'/></td>" ;
					html += "<td>" +v.contactName +"</td>" ;
					html += "<td>" +v.contactPhone +"</td>" ;
					html += "<td rowspan='2' class='bor-l'>" ;
					html += "<div><a href='#' onclick='orderItemEditUpdateAddress(" +v.id +")'>修改</a></div>" ;
					html += "</td></tr><tr>" ;
					html += "<td colspan='2'>" +v.province +v.city +v.country +v.addressChoose +v.addressDetail +"</td>" ;
					html += "</tr></tbody>" ;
				});
			}
			$("#listBodyOderItemEditAddress").html(html);
		}
	});
}

function setOderItemEditReceiverId(receiver){
	$("#orderItemEditReceiverId").val($(receiver).val());
}

function orderItemEditUpdateAddress(userAddressId){
	var userId = $("#oderItemEditUserId").val();
	openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:6,userId:userId,userAddressId:userAddressId},'','','orderItemaddressAdd');
}

function saveOrderItemEdit(){
	var ctx = $("#ctx").val();
	var receiverId = $("#orderItemEditReceiverId").val();
	var userId = $("#oderItemEditUserId").val();
	var orderId = $("#oderItemEditOrderId").val();
	var userName = $("#orderItemEditUserName").val();
// 	if(receiverId==null||receiverId==""){
// 		$.alert({text:"请选择收货人地址！"});
// 		return ;
// 	}
	$.ajax({
		url : ctx + "/order/updateOrder",
		data : {
			id:orderId,
			userId:userId,
			cateType:3,
			userName:userName,
			receiverId:receiverId
		},
		type : "post",
		dataType : "json",
		async : false,
		success : function(data) {
			if (data.msg = "00") {
				parent.getItemBasics(orderId);
				//closeModule('orderItemEdit','','defaultManagerAll');
				setOrderCloseModule('orderItemEdit');
			}
		}
	});
}

</script>
</html>

