<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>驳回</title>
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">驳回</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div  class="form-group">
							<label for="checkInstructions" style="font-size: 15px"><span style="color:red">*</span>驳回原因：</label>
							<textarea  name="checkInstructions" id="checkInstructions" placeholder="请输入原因……" class="form-control" rows="10" ></textarea>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" id="close">关闭</button>
					<button type="button" class="btn btn-primary btn-sm" id="agreementDeny">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	
	<script type="text/javascript">
		$("#agreementDeny").on("click",function(){
			var checkInstructions = $("#checkInstructions").val();
			if(checkInstructions){
				agreementDeny(checkInstructions);
			}else{
				$.alert({millis:2000,text:"驳回原因不能为空!"});
				return;
			}
			/**驳回**/
			function agreementDeny(checkInstructions){
				var ctx = $("#ctx").val();
				var data = {};
				data.id = ${param.id};
				data.version =${param.version};
				data.checkStatus = 3;
				var agreemenetModel = "${param.agreemenetModel}";
				if(agreemenetModel != null && agreemenetModel != ""){
					data.elecClientState = 1;
					data.elecOtherState = 1;
					data.elecServeState = 1;
					data.agreementState = 1;
				}
				data.checkInstructions = checkInstructions;
				$.ajax({
					url:ctx+"/agreement/checkAgreement",
			        data:data,
			        type:'post',
			        async:false,
			        dataType:"json",
			        success:function(data){
			        	if(data.msg == "00"){
			        		$.alert({millis:3000,text:"提交成功!"});
			        		$("#close").trigger("click");
			        		queryCheckAgreement_listPage(1, 10);
			        	}else{
			        		$.alert({millis:3000,text:"提交失败!"});
			        	}
			        }
			    })
			}
		})
	</script>
</body>
</html>