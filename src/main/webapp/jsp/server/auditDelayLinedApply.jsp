<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">审核延长申请</h4>
            </div>
            <input type="hidden" id="auditId">
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post" id="audit">                
                <div class="row pt10" id="delayPerson">
                   	<div class="form-group  col-xs-12">
                   		<table class="table table-condensed">
	                        <tr>
	                        	<p>订单编号：<span id="order_code"></span></p>	                        	
	                        	<p>申请管家：
	                        		<span id="orgName"></span>--
	                        		<span id="applyHouseKeeper"></span>--
	                        		<span id="managerPhone"></span>
	                        	</p>
	                        	<p>申请延长时间段：
	                        		<span id="applyDelayS"></span>--
	                        		<span id="applyDelayE"></span>
	                        	</p>
	                        	<p>本订单服务人员排期：
	                        		<span id="orderPersonLinedS"></span>--
	                        		<span id="orderPersonLinedE"></span>
	                        	</p>
	                        </tr>	                   
	            	    </table>                 	
                   	</div>
                </div>                
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"  onclick="audit(1)">通过</button>
                <button type="button" class="btn btn-primary"  onclick="audit(2)">不通过</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
	function audit(val){
		var checkStatus = "";
		if(val == 1){
			checkStatus = 2;
		}else{
			checkStatus = 3;
		}
		var id = $("#auditId").val();
		$.ajax({
			url:ctx+"/delay/audit",
			data:{
				checkStatus : checkStatus,
				id : id
			},
			type:'post',
			dataType:'json',
			success:function(data){
				if(data.code == "0"){
					
				}else if(data.code == "-1"){
					$.alert({text:data.msg});
				}else if(data.code == "-2"){
					$.alert({text:"请稍后再试"});
				}
			}
		});
		closeModule();
	}
</script>
</body>
</html>