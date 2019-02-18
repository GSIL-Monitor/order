<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.modal-order-iframe{height:700px; width: 980px;margin: 30px auto; }
		.otable-tr-top{height:25px; border-top:1px solid #CCC}
		.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
		.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
		.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
	</style>
</head>
<body>
<input type="hidden" id="userId" name="userId">
<input type="hidden" id="makeSureType" name="makeSureType">
  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" id="modelFrame">  
	  <div class="modal-order-iframe" >  
	    <div class="modal-content" id="modalCPM">
	    	<div class="modal-header" style="height:45px;" >  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        <h4 class="modal-title" style="height:30px;" >新增订单</h4>
	      </div> 
	      <div class="modal-body"  style="height:420px;">
	      <iframe src="/order/jsp/orderUserAdd.jsp" 
	      		id="orderAddIframe" name="orderAddIframe" 
	      		frameborder="0" marginwidth="0"
                marginheight="0" height="98%" width="98%" scrolling="yes" allowtransparency="true">
	      
	      </iframe>
	      <iframe src="" 
	      		id="orderServer" name="orderServer" style="display: none;"
	      		frameborder="0" marginwidth="0"
                marginheight="0" height="98%" width="98%" scrolling="yes" allowtransparency="true">
	      
	      </iframe>
	      <iframe src="" 
	      		id="orderItem" name="orderItem" style="display: none;"
	      		frameborder="0" marginwidth="0"
                marginheight="0" height="98%" width="98%" scrolling="yes" allowtransparency="true">
	      
	      </iframe>
		  </div>
	      <div class="modal-footer" style="text-align: center;">  
	      <input type="button" value="下一步" id="buttonNext" onclick="openOrderItem()">
	        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <button type="button" data-dismiss="modal" id="cancel">取消</button>  
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
<script type="text/javascript">

// 复选框设置
function getuservalue(addressId){
	var tf = document.getElementById("address" +addressId +"").checked;
	$("input[name='addressId']:enabled").prop("checked", false);
	$("input[id='address" +addressId +"']:enabled").prop("checked", tf);
}

// 新增框置空
function setUsersNull(){
	$("#contactId").val('');
	$("#contactName").val('');
	$("#contactPhone").val('');
	$("#addressDetail").val('');
	$("#version").val('');
}
// 修改用户地址
function orderUserEdit(id){
	var ctx = "/order" ;
	var mobile = $("#mobile").val();
	$.ajax({
		url: ctx +"/orderbase/checkUserByUser",
		data:{
			id:id
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			if (data.msg ="00") {
				var num = $.each(data.list,function(i,v){
					$("#contactId").val(v.id);
					$("#version").val(v.version);
					$("#contactName").val(v.contactName);
					$("#contactPhone").val(v.contactPhone);
					$("#addressDetail").val(v.addressDetail);
				});
			}
		}
		});
	}
	
//通过电话号码取到送贷地址
function checkUserByMobile(contactPhone){
	var ctx = "/order" ;
	var mobile = $("#mobile").val();
	if(contactPhone!=0){
		var mobile = contactPhone;
	}
	
	$.ajax({
		url: ctx +"/orderbase/checkUserByUser",
		data:{
			contactPhone:mobile
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			if (data.msg ="00") {
				var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						html += "<tr style='height:10px;' ></tr>";
						html += "<tr class='otable-tr-top'>" ;
						html += "<td rowspan='2' class='otable-td-left'>" ;
						html += "<input name='addressId' type='checkbox' id='address" +v.id +"' onclick=getuservalue(" +v.id +")></td>" ;
						html += "<td style='text-align:left;'>" +v.contactName +"</td>" ;
						html += "<td style='text-align:left;'>" +v.contactPhone +"</td>" ;
						html += "<td rowspan='2' class='otable-td-right'><div style='display: inline-block;'><a "
							+ " href='#' onclick='orderUserEdit("+v.id
							+ ")' style='color:#000'>修改</a></div>";
						html +="</td></tr>";
						html += "<tr class='otable-tr-bottom'>" ;
						html += "<td colspan='2' style='text-align:left;'>" +v.addressDetail +"</td>" ;
						html += "</tr>" ;
					})
			}
			$("#listBodyAddress").html(html);
		}
	});
}

// 保存新地址
function saveOrderUser(){
	var ctx = "/order" ; 
	var contactId = $("#contactId").val()==''?0:$("#contactId").val();
	var contactName = $("#contactName").val();
	var contactPhone = $("#contactPhone").val();
	var addressDetail = $("#addressDetail").val();
	var version = $("#version").val();
	//alert(contactId +"," +contactName +"," +contactPhone +"," +addressDetail+"," +version) ;
	$.ajax({
		url: ctx +"/orderbase/insertUserAddress",
		data:{
			id:contactId,
			contactName:contactName,
			contactPhone:contactPhone,
			addressDetail:addressDetail,
			version:version
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg ="00") {
				checkUserByMobile(contactPhone);
				var num = $.each(data.list,function(i,v){
					$("#contactId").val('');
					$("#version").val('');
					$("#contactName").val('');
					$("#contactPhone").val('');
					$("#addressDetail").val('');
				})
			}
		}
	});
}

function openOrderItem(){
	var makeSureType = $("#makeSureType").val();
	if(makeSureType==1){
		document.getElementById('orderServer').contentWindow.makeSure();
	}else if(makeSureType==2){
		document.getElementById('orderItem').contentWindow.makeSure();
	}else{
		// 先判断是否有先择收货地址,这里传的是(t_user_address)表的id
		var uid = document.getElementById('orderAddIframe').contentWindow.getUserAddressId();
		if(uid==0){
			alert("请选择地址！");
			return ;
		}
		// 订单分类
		var orderLx = document.getElementById('orderAddIframe').contentWindow.getOrderlx();
		if(orderLx==0){
			alert("请选择订单类型！");
			return ;
		}
		var url = "";
		document.getElementById('orderAddIframe').style.display="none";
		if(orderLx==1){
			// 服务类型订单
			$("#makeSureType").val(1);
			document.getElementById('buttonNext').value="保存";
			url = "/order/jsp/server/orderItemServer.jsp?userId=" +uid;
			document.getElementById('orderServer').style.display="block";
			document.getElementById('orderServer').src=url;
		}else{
			// 商品类型订单
			$("#makeSureType").val(2);
			document.getElementById('buttonNext').value="确定";
			url = "/order/jsp/orderItem.jsp?userId=" +uid;
			document.getElementById('orderItem').style.display="block";
			document.getElementById('orderItem').src=url;
		}
	}
	
}

// 用于保存订单时页面数据
function reloadParent(){
	window.parent.queryOrders(0,10); 
}
</script>
</html>

