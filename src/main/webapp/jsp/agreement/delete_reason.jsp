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
                <h4 class="modal-title">合同删除</h4>
            </div>
            <input type="hidden" id="orderId" value="${param.orderId }">           
                <div class="modal-body Scheduling id="demo_box"">
                    <form class="form-horizontal" role="form">
				        <div class="form-group">
				            <label for="reason" class="col-sm-3 control-label">理由：</label>
				            <div class="col-sm-9">
				            <textarea class="form-control" placeholder="请输入理由……" rows="5" id="reason" name="reason" onblur="this.value=this.value.replace(/\s+/g,'')"></textarea>
				        </div>
				        </div>
			        </form>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="sureReason()">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>

function sureReason(){
	var reason = $("#reason").val();
	var contract_id = $("input[name='selectCheckBox']:checked").val();
	var orderId="${param.orderId }";	
	if(reason == null || reason == ""){
		$.alert({text:"请填写删除合同理由！"});
		return;
	}else{		
		var message = "确认删除该合同？";
		$.confirm({text:message,callback:function(a){
			debugger
			 if(a){
				 $.ajax({
						url : ctx+"/agreement/deleteIsGold",
						data : {orderId:orderId,
							    id:contract_id,
						},
						type : "POST",
						async : false,
						traditional: true,
						success : function(data) {
							var msg = data.code;
							if(msg == "00" ||msg == "0"){
								$.ajax({
									url : ctx+"/agreement/updateAgreement",
									data : {orderId:orderId,
										    id:contract_id,
										    reason:reason,
										    agreementState:5
									},
									type : "POST",
									async : false,
									traditional: true,
									success : function(data) {
										var msg = data.msg;
										if(msg == "00"){
											$.alert({millis:2000,top:'30%',text:"删除成功！"});
											closeModule("delete_reason");							
											queryAgreement();
										}else{
											$.alert({millis:2000,top:'30%',text:"删除失败！"});
											closeModule("delete_reason");
										}
									}
								});
							}else{
								$.alert({text:data.result});
							}
						}
					});
			}
			 closeModule("delete_reason");
		  }
	   });
	}	
}
</script>
</html>