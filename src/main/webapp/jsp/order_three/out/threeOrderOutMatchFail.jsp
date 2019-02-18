<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%String orderId = request.getParameter("orderId");%>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">匹配失败</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" name="threeOrderOutUserAddId" id="threeOrderOutUserAddId">
					<input type="hidden" name="threeOrderOutUserAddVersion" id="threeOrderOutUserAddVersion">
					<div class="info clearfix">
						<form class="form-inline" action="" method="post">
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
										<p>输入失败原因：</p>
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<input type="hidden" id="orderId" name="orderId" value="<%=orderId%>"/>
									<textarea class="form-control" id="remark" name="remark" style="width: 100%;height: 100px"></textarea>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="saveMatchFail();">保存</button>
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('threeOrderOutMatchFail');">取消</button>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function saveMatchFail(){
			var ctx = $("#ctx").val();
			var orderId = $("#orderId").val();
			var remark = $("#remark").val();
			$.ajax({
				url: ctx +"/threeOrderOut/matchFail",
				data:{
					orderId:orderId,
					remark:$.trim(remark)
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg =="00") {
						queryOrdersByLike(0,10);
						checkThreeOrderOne(orderId);
						closeModule('threeOrderOutMatchFail');
					}
				}
			});
		}
	</script>
</body>
</html>

