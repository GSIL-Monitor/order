<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/agreement.js"></script>
<style>
		/*解决第二个modal关闭后，第一个modal不能上下滑动*/
		body{
			color:#000!important;
			font-size:12px!important;
			font-family: "Helvetica Neue",Helvetica,Arial,sans-serif!important;
		}	
</style>



<title>更新合同签约日期</title> 

</head>
<body>

	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					
				</div>
					<form id="update_agreementCretime" action="" method="post" class="form-inline">
					<!-- <input type="hidden" id="ucpecurrentTime"></input> -->
					<div class="modal-body">
						<div class="modal-content-basic">
						<div class="info-select clearfix">
							
							<div class="row"  style="vertical-align: middle !important;text-align: center;">
							     
							     <h4 class="modal-title">海金更新合同签约日期</h4> 
								
							</div>
							  
							  <hr>
							  
							 <div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback">&nbsp;&nbsp;<p>当前签约时间为：</p>
								 	<input type="text" id="createtime1" name="createtime1"  class="form-control" />
								 	<input type="hidden" id="agreementId" name="agreementId"  class="form-control"/>
								 	<input type="hidden" id="updateflag" name="updateflag"  class="form-control"/>
								 </label>
								</div>
								
							</div> 
							  
                             <div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback">&nbsp;&nbsp;<p>要更新的时间为：</p>
								 	<input type="text" class="Wdate form-control" id="contract_date"  name="contract_date" data-type="date">
									
								 </label>
								</div>
							</div> 
							
							
							<div class="form-group col-xs-12" style="color: red">
	                    
								<h5>注:线下海金合同因签约日期验证失败的在此更改. 使用此功能者请确认您已经获得结算中心授权并知晓需要更改的日期,否则请勿使用。</h5>
	            </div> 
	                       
					  </div>
                     </div> 
                   </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">关闭</button>
				<!-- 	<button type="button" class="btn btn-sm btn-primary" onclick="updateTime()">保存</button> -->
					<button type="submit" class="btn btn-sm btn-primary" id="ucontradtSubmit">保存</button> 
				</div>
			</form>
			</div>
		</div>
	</div>
	
</body>


   <script type="text/javascript">
	var id = '${param.agreementId}';
	var orderId = '${param.orderId}';
	var contractId = '${param.contractId}';
	var orderType = '${param.orderType}';
	var orgType = '${param.orgType}';
	var createtimeNew = '${param.createtime}';
	var createtime = '${param.createtime1}';
	
	
	$(function(){
		     
	 	$.ajax({
			url : ctx+"/agreement/queryAgreementcreateDate",
			data : {id:contractId,orderId:orderId},
			type : "POST",
			async : false,
			success : function(data) {
		        
				if(data.msg=='01'){
					$('#createtime1').val("无数据"); 
					$('#ucontradtSubmit').prop('disabled', true);
				}else{
					if(data.data.contractDate==null||data.data.contractDate==undefined||data.data.contractDate==""){
						$('#createtime1').val("无"); 
						$('#ucontradtSubmit').prop('disabled', true);
					}else{
						$('#createtime1').val(data.data.contractDate); 
						$('#agreementId').val(data.data.id);
						$('#updateflag').val(data.updateflag); 
					}
				}
				
			},
			error:function(data){
			}
	});
	
	});
	
	
	 $('#update_agreementCretime').bootstrapValidator({
         message: 'This value is not valid',
         excluded: ':disabled',
         feedbackIcons: {
             valid: 'glyphicon glyphicon-ok',
             invalid: 'glyphicon glyphicon-remove',
             validating: 'glyphicon glyphicon-refresh'
         },
         fields: {
        	 contract_date: {
 	            validators: {
 	                notEmpty: {
 	                    message: '必填！'
 	                },
 	            }
 	        }
         }
     }).on('success.form.bv', function(e) {
     	 e.preventDefault();
     	 agreement.updateTime($('#agreementId').val(),$('#createtime1').val(),$('#contract_date').val(),$('#updateflag').val());
     });
	
	
</script>


</html>