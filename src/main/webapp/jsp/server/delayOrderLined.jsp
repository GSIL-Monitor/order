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
                <h4 class="modal-title">延长订单排期</h4>
            </div>
            <%-- <input type="hidden" value="${param.type}" id="typeServer">
            <input type="hidden"  id="serverLinedIdNew">
            <input type="hidden" id="linedId">
            <input type="hidden" id="serverLinedVersion"> --%>
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post">
                <div class="row pt10" id="NotZhong">
                   	<div class="form-group  col-xs-12">
                   		<label><span style="color: red"></span>
                   		开始日期：
                   		<input id="delayStartTimeLined" name="startDate" value="${param.startTime}" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" disabled/>						
                   		</label>
                   	</div>
                   	<div class="form-group  col-xs-12" id="endTimeLinedOneDiv">
                   		<label><span style="color: red"></span>
                   		结束日期：
                   		<input id="delayEndTimeLined" name="endDate" value="${param.endTime}" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({minDate:'${param.endTime}',Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	</div>                  	
                </div>              
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="sureDelayOrderLined();">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>

function sureDelayOrderLined(){	
	var ctx = $("#ctx").val();
	var orderId = parent.$("#checkedOrderId").val();
	var empId="${param.personId}";
	var startDate =$("#delayStartTimeLined").val();
	var delayEndTime =$("#delayEndTimeLined").val();
	var endDate= "${param.endTime}";
	var startLined ="${param.startTime}";
	var weeks = $("#benWorkWeek").text();	//钟点工星期
	var zhongHours = $("#benWorkHours").val();//钟点工小时
	var hoursStart = zhongHours.split("-")[0];
	var hoursEnd = zhongHours.split("-")[1];
	/* if(delayEndTime < endDate ){
		$.alert({text:"结束时间不得早于原来的结束时间!"});
	} */
	 try{
		var st = new Date(startLined.replace(/\-/g, "\/"));  
		var et = new Date(delayEndTime.replace(/\-/g, "\/"));  
		if(et<st){
			$.alert({text:"结束时间应大于开始时间。"});
			return;
		}
	}catch(e){
		$.alert({text:"时间格式不正确。"});
		return;
	} 
	$.ajax({
		url:ctx+"/delay/delayOrder",
		data:{
			startDate:startDate,
			orderId:orderId,
			empId:empId,
			endDate:delayEndTime,
			startTime:hoursStart,
			endTime:hoursEnd,
			week:weeks
		},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.code == "0"){
				$("#delayEndTime").html();
				$.alert({text:"延长订单排期成功！"});
				getLineTime();//查询排期时间
				getPersonLineTime();//查询服务人员排期
			}else if(data.code == "-1"){
				$.alert({text:data.msg});
			}else if(data.code == "-2"){
				$.alert({text:data.msg});
			}		
		}
	});
	closeModule("delayOrderLined");
}

	

</script>
</html>