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
			String id = request.getParameter("id");
		%>
	</script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('orderFollow','','defaultManagerAll')">×</button>  
		        <h4 class="modal-title">订单转单</h4>
		     </div> 
	      <div class="modal-body">
	       <form action="" method="post" class="form-inline">
	      		<input type="hidden" id="orderRechargeId" value="<%=id%>">
					<div class="row">
						<div class="form-group col-xs-12">
							<label><b style="color: red">*</b><p style="width:110px;">门店/门店基地：</p>
							<select  id="orderRechargeDept" class="form-control" onclick="queryRechargeBy();" style="width:300px;">
								<option value="-1">---请选择---</option>
							</select>
							</label>
						</div>
					</div>
					<div class="row">&nbsp;</div>
					<div class="row">
						<div class="form-group col-xs-12">
							<label><b style="color: red">*</b><p style="width:110px;">管&nbsp;&nbsp;&nbsp;家：</p>
							<select  id="orderRechargeBy" class="form-control" style="width:300px;">
								<option value="-1">---请选择---</option>
							</select>
							</label>
						</div>
					</div>
			</form>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('orderFollow','','defaultManagerAll')" >取消</button>  
	        <button type="button" class="btn btn-sm btn-primary" onclick="saveOrderRecharge();">提交</button>
	      </div>  
	    </div>
	  </div>
	</div>    
  
</body>
<script type="text/javascript">

$(function() {
	queryRechargeDept();
});

function queryRechargeDept(){
	var ctx = $("#ctx").val();
	$.ajax({
		url:ctx+"/order/queryFollowDept",
		type:"post",
		datetype:"json",
		async : false,
		data:{
		},
		success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			$.each(obj.dict,function(i,v){
				if(v.key==null||v.key==""||v.key=="undefined"){
					return false;
				}
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#orderRechargeDept").html(html); 
			$("#orderRechargeDept").checkSelect(function(a,b,c){
				queryRechargeBy();
    });
		}
	})
}

function queryRechargeBy(){
	var ctx = $("#ctx").val();
    var pid=$("#orderRechargeDept option:selected").val();
	$.ajax({
		url:ctx+"/order/queryFollowBy",
		type:"post",
		datetype:"json",
		async : false,
		data:{
			pid:pid
		},
		success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			html +="<option style='color:blue;' value='' >...请选择...</option>";
			$.each(obj.dict,function(i,v){
				if(v.key==null||v.key==""||v.key=="undefined"){
					return false;
				}
				html +="<option value='" +v.key +"' keyValue='"+v.value +"'>"+v.value+"</option>";
			})
			$("#orderRechargeBy").html(html); 
		}
	})
}

//确定\保存按钮方法
function saveOrderRecharge(){
	var ctx = $("#ctx").val();
	var id = "<%=id%>" ;
    var rechargeBy=$("#orderRechargeBy option:selected").val();
    var rechargeDept=$("#orderRechargeDept option:selected").val();
    if(rechargeBy==null||rechargeBy==""){
    	$.alert({text:"请选择负责人！"});
    	return ;
    }
    if(rechargeDept==null||rechargeDept==""){
    	$.alert({text:"请选择负责人部门！"});
    	return ;
    }
	$.ajax({
		url:ctx+"/order/updateOrder",
		type:"post",
		datetype:"json",
		async : false,
		data:{
			id:id,
			rechargeBy:rechargeBy,
			rechargeDept:rechargeDept
		},
		success:function(data){
			if (data.msg=="00") {
				insertOrderTurnLog(id,rechargeBy,rechargeDept);
				closeModule('orderFollow','','defaultManagerAll');
				$.alert({millis:5000,text:"添加转单成功!"});
			}
		}
	})
}
/**
 * 新增订单转单日志
 *@param orderId 订单id
 *@param rechargeBy  负责人id
 *@param orderId 负责人部门id
 */
function insertOrderTurnLog(orderId,rechargeBy,rechargeDept){
	var ctx = $("#ctx").val();
	$.ajax({
		url:ctx+"/orderTurnLog/insertOrderTurnLog",
		data : {
			"orderId":orderId,
			"rechargeBy":rechargeBy,
			"rechargeDept":rechargeDept
		},
		type : 'post',
		async : false,
		dataType : "json",
		success : function(data) {
			if(data.msg != "00"){
				$.alert({millis:5000,text:"添加转单日志失败!"});
			}
		}
	});
}


</script>
</html>

