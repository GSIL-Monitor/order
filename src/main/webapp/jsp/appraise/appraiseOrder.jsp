<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>

</head>
<style>
.help-block{
    width: 23%!important;
	left:458px!important;
    top: 68px!important;
	background:none!important;
}
</style>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
       <div class="modal fade">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
		        <h4 class="modal-title">评论回复</h4>
		     </div> 
		 <form id="add_appraiseReplyForm" action="" method="post" class="form-inline">
	      <div class="modal-body">
		       <div class="row">
				   <div class="form-group col-xs-12">
				   	<label class="has-feedback">
					<textarea  rows="10" cols="40" style="width:436px" name="appraiseReply" id="add_appraiseReply" class= "form-control form-textarea" ></textarea>
					 </label>
					</div>
				</div>
		  </div>
		  </form>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>  
	        <button type="button" class="btn btn-sm btn-primary" onclick="saveAppraiseReply();">提交</button>
	      </div>  
	    </div>
	  </div>
	</div>  
</body>
	<script type="text/javascript">
	$('#add_appraiseReplyForm').bootstrapValidator({
	    message: 'This value is not valid',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         
	    	appraiseReply: {
	            message: '回复无效！',
	            validators: {
	                notEmpty: {
	                    message: '回复不能为空！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 500,
	                    message: '回复最多为500个字！'
	                }
	            }
	        },
	    }
	    }).on('success.form.bv', function(e) {
		    // 阻止表单提交【submit】【必填】
		    e.preventDefault();
		    //保存
		    saveEvaluateBack();
		}); 
	function saveAppraiseReply(){
		var ctx=$("#ctx").val();
		var orderId = '${param.orderId}';//订单ID
		var empId = '${param.empId}'; //服务人员ID
		var type = '${param.type}';//1评价回复 2 评论回复
		var eleId = '${param.eleId}';//回显ID
		var ids = '${param.ids}';//回复IDs
		var evaluateBack = $("#add_appraiseReply").val();//回复内容
		$.ajax({
			url : ctx+"/appraise/insertAppraiseReply",
			data : {ids:ids ,reContent : evaluateBack},
			type : "POST",
			async : false,
			traditional: true,
			success : function(data) {
				var msg = data.msg;
				if(msg == "00"){
					$.alert({millis:2000,top:'30%',text:"回复成功!！"});
					closeModule('appraiseReplyAdd');
					if(type == 2){
						queryEvaluate(orderId,eleId,0,5);
					}else{
						queryEvaluateBack(orderId,eleId,0,5);
					}
				}else{
					$.alert({millis:2000,top:'30%',text:"回复失败！"});
					closeModule('appraiseReplyAdd');
					if(type == 2){
						queryEvaluate(orderId,eleId,0,5);
					}else{
						queryEvaluateBack(orderId,eleId,0,5)
					}
				}
			}
		});
	}
	
	</script>
</html>

