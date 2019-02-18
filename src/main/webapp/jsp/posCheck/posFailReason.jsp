<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<title>驳回原因新增页</title>
</head>
<body>
		<div class="modal fade" >  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >×</button>  
		      	<h4 class="modal-title"></h4>
		     </div> 
	      <div class="modal-body">
	      		<div class="info-select clearfix">
                 <input type="hidden" id="userLoginLevel">
                     <form class="form-inline" id = "posFail_form">
                      <div class="row">
                          <div class="form-group  col-xs-12">
                              <label class="form-label">
                                  <p>驳回原因：</p>
                                  <textarea rows="3"  name="remark" style="height:50px;" id="unuse_reason" class= "form-control form-textarea" ></textarea>
                              </label>
                          </div>
                       </div>
                      </form>
                  </div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal"  >取消</button>  
	        <button type="button" class="btn btn-sm btn-primary" onclick="updateFailReason();" >保存</button>
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript" src="${ctx}/js/posCheck/posCheck.js"></script>
<script type="text/javascript" src="${ctx}/js/posCheck/posCheckCommon.js"></script>
<script type="text/javascript">
	var id = '${param.id}';
	//var flag = "${param.flag}";//上传标记
	//更新驳回原因
	function updateFailReason(){
		var remark = $("#unuse_reason").val();
		$.ajax({
			url: ctx+"/payfee/updatePayfee",
			data:{
				id:id,
				posCheckStatus : 3,
				posFailReason : remark
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$.alert({millis:3000,top:'30%',text:"提交成功！"});
					closeModule('posFailReason');
					queryPosCheckByLike(0,10);
				}else{
					 $.alert({millis:3000,top:'30%',text:"提交失败！"});
				}
			}
		});
	}
	
	//延续性服务验证
	$('#posFail_form').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
	    	remark: {
	            message: '驳回原因无效！',
	            validators: {
	                stringLength: {
	                    min: 1,
	                    max: 20,
	                    message: '驳回原因必须为1-20个字！'
	                },
	            }
	        },
	    }
	}).on('success.form.bv', function(e,data) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    updateFailReason();
	});
</script>
</html>