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
                <h4 class="modal-title">延长服务人员排期</h4>
            </div>
            <input type="hidden" value="${param.type}" id="typeServer">
            <input type="hidden"  id="serverLinedIdNew">
            <input type="hidden" id="linedId">
            <input type="hidden" id="serverLinedVersion"> 
            <input type="hidden" id="managerId"> 
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post" id="delay_person">                
                <div class="row pt10" id="delayPerson">
                   	<div class="form-group  col-xs-12">
                   		<label><span style="color: red"></span>
                   		开始日期：
                   		<input id="startTimeLined" name="startTime" value="${param.startTime }" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" disabled/>
                   		</label>
                   	</div>
                   	<div class="form-group  col-xs-12">
                   		<label><span style="color: red"></span>
                   		结束日期：
						<input id="endTimeLined" name="endTime" value="${param.endTime }" class="Wdate form-control" type="text" 
						onfocus="WdatePicker({minDate:'${param.endTime}',Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})"  />
                   		</label>                  	
                   	</div>
                </div>
                <p style="color:red">延长服务人员排期需要经过服务人员下一订单管家审核，</p><p style="color:red">请提前联系确认
                    <span id="orgName"></span><span>-</span>
                    <span id="managerName"></span><span>-</span>
                    <span id="managerPhone"></span>
                    <input type="hidden" id="newOrderId">
                    </p><p style="color:red">冲突订单编号：
                    <span id="newOrderCode"></span></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary"  onclick="sureDelayLined()">确定</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
$(function(){
	selectOrderBy();
})

function sureDelayLined (){
	var ctx = $("#ctx").val();
	var checkBy = $("#managerId").val();
	var personStatus = $("#personLinedStatuss").text();
	var endTimeLined = $("#endTimeLined").val();//paiqijieshushijian
	var startTime = "${param.startTime }";
	var endTime ="${param.endTime }";
	var personId = "${param.personId}";
	var orderId = parent.$("#checkedOrderId").val();
	var clashOrderId = $("#newOrderId").val();
	if(personStatus != 8){
		$.alert({text:"上户之后可以延长服务人员的排期！"});
		return;
	}
	/* if(endTimeLined < endTime ){
		$.alert({text:"结束时间不得早于原来的结束时间!"});
	} */
	 try{
		var st = new Date(startTime.replace(/\-/g, "\/"));  
		var et = new Date(endTimeLined.replace(/\-/g, "\/"));  
		if(et<st){
			$.alert({text:"结束时间应大于开始时间。"});
			return;
		}
	}catch(e){
		$.alert({text:"时间格式不正确。"});
		return;
	} 
	$.ajax({
		url:ctx+"/delay/delayPersonLined",
		data:{
			startTime:startTime,
			orderId:orderId,
			personId:personId,
			endTime:endTimeLined,
			clashOrderId : clashOrderId ,
			checkBy : checkBy
		},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.code == 0){
				$("#endTimeLined").html();
				//$.alert({text:"延长订单排期成功！"});
				parent.getLineTime();
				delayApplyLined();//查询延长申请
			}else if(data.code == -1){
				$.alert({text:data.msg});
			}else if(data.code == -2){
				$.alert({text:"发生异常，请稍后再试！"});
			}
		}
	});
	closeModule("delayPersonLined");
}

//查询服务人员下一个订单负责人
function selectOrderBy(){
	var personId = "${param.personId}";
	var orderId = parent.$("#checkedOrderId").val();
	$("#orgName").html("");
	$("#managerName").html("");
	$("#managerPhone").html("");
	$("#newOrderId").val("");
	$.ajax({
		url:ctx+"/order/queryRechargeBy",
		data:{
			orderId : orderId,
			personId : personId
		},
		type:'post',
		dataType:'json',
		success:function(data){
			if(data.msg == "00"){
				$.each(data.list,function(i,v){
					$("#orgName").text(v.orgName);
					$("#managerName").text(v.managerName);
					$("#managerPhone").text(v.managerPhone);
					$("#newOrderId").val(v.orderId);
					$("#newOrderCode").text(v.orderCode);
					$("#managerId").val(v.managerId);
				})
			}else if(data.msg == "01"){
				$.alert({text:data.msg});
			}
		}
	});
}
</script>
</html>