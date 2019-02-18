<%@ page language="java"
	import="java.util.*,com.emotte.server.util.CookieUtils"
	pageEncoding="UTF-8"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调岗下户/返岗上户</title>
</head>
<body>
	<div class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="模态框" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="it_header"></h4>
				</div>
				<div class="modal-body new-sort" id="new_sort">
					<div class="info clearfix">
					    <input type="hidden" id="ctx" name="ctx" value="${ctx}" />
						<input type="hidden" id="it_orderId" value="${param.orderId}"/>
						<input type="hidden" id="time" value="${param.time}"/>
						<input type="hidden" id="it_interviewType" value="${param.interviewType }"/>
						<input type="hidden" id="it_personId" value="${param.personId }"/>
						<input type="hidden" id="it_minDate" value="${param.minDate }"/>
						<input type="hidden" id="it_inwid" value="${param.inwid }"/>
						<form class="form-horizontal" role="form" id="balanceAddForm"  method="post">
							<div class="form-group">
								<label for="interview_time" class="col-sm-3 col-sm-offset-1 control-label">
									<strong style="color: red;">*</strong><span id="personTimess"></span>
								</label>
								<div class="col-sm-4">
									<input id="interview_time" name="interview_time" class="form-control Wdate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${param.minDate}'})">
								</div>
							</div>
						</form>
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="updateRjpInterviewType">保存</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var iType = $("#it_interviewType").val();
		var time = $("#time").val();
		if (iType == "8") {
			$("#personTimess").text("返岗上户时间：");
			$("#it_header").text("返岗上户");
			$("#interviewStartTimead").val("${param.startTime}");//上户时间
		} else {
			$("#personTimess").text("调岗下户时间:");
			$("#it_header").text("调岗下户");
		}
		$("#updateRjpInterviewType").on("click",function(){
			var interviewType = $("#it_interviewType").val();
			var orderId = $("#it_orderId").val();
			var personId = $("#it_personId").val();
			var interviewTime = $("#interview_time").val();
			if (!interviewTime) {
				$.alert({millis:5000,text:"请选择时间!"});
				return;
			}
			var startTime = interviewTime,endTime = interviewTime;
			if (interviewType == 8) {
				$.ajax({
					url : "${ctx}/itemInterview/workGoHourse",
					data : {
						orderId : orderId,
						personId : personId,
						starTime : startTime
					},
					type : "post",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.msg == "00") {
							var code = data.result.data.code;
							var msg = data.result.data.msg;
							if(code == "0"){
								$.alert({millis:5000,text:msg});
								closeModule();
								//刷新
								parent.serverInterviews();
								parent.getPersonInfo();
								parent.getHistoryPerson();
							}else{
								$.alert({millis:5000,text:msg});
							}
						} else {
							$.alert({millis:5000,text : "返岗上户失败！"});
						}
					}
				});
			} else if (interviewType == 9) {
				var ids = $("#it_inwid").val();
				if(interviewTime >= time){
					$.alert({millis:5000,text:"调岗下户时间请早于顶岗时间!"});
					return;
				}
				//返岗上户
				$.ajax({
					url : ctx + "/itemInterview/workOutHourse",
					data : {
						orderId : orderId,
						personId : personId,
						endTime : endTime
					},
					type : "post",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.msg == "00") {
							var code = data.result.data.code;
							var msg = data.result.data.msg;
							if(code == "0"){
								smss(ids);
								$.alert({millis:5000,text:msg});
								closeModule();
								//刷新
								parent.serverInterviews();
								parent.getPersonInfo();
								parent.getHistoryPerson();
							}else{
								$.alert({millis:5000,text:msg});
							}
						} else {
							$.alert({millis:5000,text : "调岗下户失败！"});
						}
					}
				})
			}
		})
	});
	
	
	//下户发送短息，@author 周鑫  2018-10-30
	 function smss(ids){
		$.ajax({
			url : "${ctx}/outHouseSendSmss/sendSmss",
			data : {
				ids:ids,
			},
			type : "POST",
			async : false,
			traditional: true,
			success : function(data) {
				if(data.msg == '00'){
					//成功
				}else{
					//失败
				}
			}
		});
	};
</script>
</body>
</html>