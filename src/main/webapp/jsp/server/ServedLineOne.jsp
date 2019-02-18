<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
     var payStatus='${param.payStatus}';
     var orderstatus='${param.orderstatus}';
</script>
<body>
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">需求排期</h4>
            </div>
            <input type="hidden"  value="${param.cityCode}" id="cityCode" >
            <input type="hidden"  value="${param.productCode}" id="productCode" >
            <input  type="hidden" id="linedId" >
            <input  type="hidden" id="Version" >
            <input  type="hidden" id="personId"  value="${param.personId}">
            <input  type="hidden" id="personStatus" value="${param.personStatus}">
            <input  type="hidden"  id="hours"  ">
            <input  type="hidden"  id="time" ">
            <input  type="hidden" id="personNumber"  value="${param.personNumber}">
            <input  type="hidden" id="numStock">
            <input  type="hidden" id="endTime">
            <input type="hidden" id="StartTimeSoltOne"/>
			 <input type="hidden" id="EndTimeSoltOne">
			 <input type="hidden" id="StartTimeOne">
			 <input type="hidden" id="EndTimeOne">
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post">
                <div class="row pt10" >
                <div class="form-group  col-xs-12" style="color:red;line-height:20px;">
                  		<h5>订单排期填写规则：</h5>
					<ul>
					  <li class="text-justify"> 1.若订单上过户，且未填报过服务人员服务费，开始日期应早于或等于首位上户服务人员的上户日期</li>
					  <li class="text-justify"> 2.订单排期不是某一个服务人员排期，而是应包含所有服务人员排期</li>
					  <li class="text-justify">3.若订单上过户，且己填报过服务人员服务费，开始日期应和当前订单原有开始日期相同([订单管理]-[基本信息]-[排期时间])</li>
					</ul>
	             </div>
	             <div class="form-group  col-xs-12" style="height:15px"></div>
                   	<div class="form-group  col-xs-12">
                   		<label><span style="color: red">*</span>
                   		<p>开始日期：</p>
                   		<input id="startTimeLinedOne" name="startTimeLinedOne" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked: function(){selUpTime();startTimeFunc();isDisabled();}})" />
						<input id="startTimeLinedOneOld" name="startTimeLinedOneOld" type="hidden">
                   		</label>
                   	</div>
                   	<div class="form-group  col-xs-12" style="height:15px"></div>
                   	<div class="form-group  col-xs-12" id="endTimeLinedOneDiv">
                   		<label><span style="color: red">*</span>
                   		<p>结束日期：</p>
                   		<input id="endTimeLinedOne" name="endTimeLinedOne" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',onpicked:function(){selUpTime();endTimeFunc();}})" />
							<input id="endTimeLinedOneOld" name="endTimeLinedOneOld" type="hidden">
                   		</label>
                   	</div>
                   	<div class="form-group  col-xs-12" id="serviceHoursDiv">
                   		<label><span style="color: red">*</span>
                   		<p>时长：</p>
							<select id="serviceHours" class="form-control" onchange="changeTime()">
								<option value=''>...请选择...</option>
							</select> 
                   		</label>
                   	
                   	</div>
                   	<div class="form-group  col-xs-12" id="timeSoltDiv">
                   		<label><span style="color: red">*</span>
	                   		<p>时间段：</p>
							<select id="timeSolt" class="form-control" onchange="checkNumStock()">
								<option value=''>...请选择...</option>							
							</select> 
                   		</label>
                   	
                   	</div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="setBasicServerLindButton"  onclick="saveLinedOne();">保存</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
$(function (){
	/* loadOrderServerLinedOne(parent.$("#checkedOrderId").val()); */
	loadOrderServerLinedOneNew(parent.$("#checkedOrderId").val());
	showTime(parent.$("#checkedOrderId").val());
})
function selUpTime(){
	var ctx = $("#ctx").val();
	var cityCode = $("#cityCode").val();
	var productCode = $("#productCode").val();
	var startTimeLinedOne = $("#startTimeLinedOne").val();
	if(startTimeLinedOne!=null||startTimeLinedOne!=""){
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderKucun",
		data:{
			cityCode : cityCode,
			productCode : productCode,
			startTimeLinedOne : startTimeLinedOne 
		},
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			var option = "<option style='color:blue;' value='0'>...请选择...</option>";
			for(i=0;i<data.hours.length;i++){
				 option += "<option value="+data.hours[i].name+" >"+data.hours[i].name+"</option>";
					$("#serviceHours").html(option); 
				}
			
			var option1= "<option style='color:blue;' value='0'>...请选择...</option>";
			if(data.quantums != null){
				for(i=0;i<data.quantums.length;i++){
						option1 += "<option value="+data.quantums[i]+">"+data.quantums[i]+"</option>";
					$("#timeSolt").html(option1);
				}
			}
	 	}
	 	});
	}
}
function changeTime(){
	var ctx = $("#ctx").val();
	var cityCode = $("#cityCode").val();
	var productCode = $("#productCode").val();
	var startTimeLinedOne = $("#startTimeLinedOne").val();
	var serviceHours = $("#serviceHours").val();
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderKucun1",
		data:{
			cityCode : cityCode,
			productCode : productCode,
			startTimeLinedOne : startTimeLinedOne ,
			serviceHours : serviceHours
		},
		type:'post',
		async:false,
		dataType:'json',
		success:function(data){
			//var	option="";
			var optionaa = "<option style='color:blue;' value='0'>...请选择...</option>";
			if(data.quantums != null){ 
				for(i=0;i<data.quantums.length;i++){
					optionaa += "<option value="+data.quantums[i]+">"+data.quantums[i]+"</option>";
				$("#timeSolt").html(optionaa);
			}
		}
		}
	 	});
}
function saveLinedOne(){
	var ctx = $("#ctx").val();
	var startTime = $("#startTimeLinedOne").val();
	var endTimeHide = $("#endTime").val();
	var endTime = $("#endTimeLinedOne").val();
	var personNumber = $("#personNumber").val();//需要的服务人员数量
	var numStock = $("#numStock").val();//库存
	if(startTime==null||startTime==""){
		$.alert({ text : "请选择开始时间！" });
		return ;
	}
	//无库存
	if(numStock==0||numStock==""||numStock==null){
		if(endTimeHide != null && endTimeHide != "" || (endTime==null||endTime=="")){
			$.alert({ text : "请选择结束时间！" });
			return ;
		}
		debugger
		var verification = $("#endTimeLinedOne").val().split(" ")[0].split("-")[1] - $("#startTimeLinedOne").val().split(" ")[0].split("-")[1];
		if(verification != 0){
			$.alert({text:"暂不支持隔天服务。"});
			return;
		}else{
			var verification = $("#endTimeLinedOne").val().split(" ")[0].split("-")[2] - $("#startTimeLinedOne").val().split(" ")[0].split("-")[2];
			if(verification != 0){
				$.alert({text:"暂不支持隔天服务。"});
				return;
			}
		}
		var startHour = startTime.split(" ")[1].split(":")[0]
		var endHour = endTime.split(" ")[1].split(":")[0]
		var subtractionHour = endHour-startHour;
		if(subtractionHour < 1){
			$.alert({text:"服务时间不能小于一小时。"});
			return;
		}	
	}else{
		if(parseInt(personNumber)>parseInt(numStock)){
			$.alert({text:"库存不足!"});
			return;
		}	
	}
	//判断开始时间大于结束时间
	if(startTime != null && startTime !="" && endTime != null && endTime != ""){
		var regEx = new RegExp("\\-","gi");
		startTime=startTime.replace(regEx,"/");
		endTime=endTime.replace(regEx,"/");
		var startTimeVal=Date.parse(startTime);
		var endTimeVal=Date.parse(endTime);
		if(endTimeVal < startTimeVal){
			$.alert({text : "开始时间小于结束时间!"});
			return;
		} 
	}
	
	var orderId = parent.$("#checkedOrderId").val();
	var serviceHours = $("#serviceHours").val();
	if(serviceHours == ""){
		serviceHours = subtractionHour+"小时"
	}
	var timeSolt = $("#timeSolt").val();
	var urls = "/itemDetailServer/insertOrderServerLined" ;
	var id = "" ;
	var version = 0;
	var linedId = $("#linedId").val();
	if(linedId != null && linedId != ""){
		//修改排期
		urls = "/itemDetailServer/updateOrderServerLined" ;
		id = $("#linedId").val() ;
		version = $("#Version").val() ;
		
		var emp_id="${param.personId}";
		var personStatus="${param.personStatus}";
		if(startTime != null && startTime !="" && endTime != null && endTime != ""){
			$.confirm({text:"是否确定修改？",callback:function(re){
				 if(re){
					 $.ajax({
							url: ctx +"/itemDetailServer/updateOrderServerLined",
							data:{
								id:id,
								version:version,
								orderId:orderId,
								days:serviceHours,
								startTime:startTime,
								endTime:endTime,
								linedType:1,
								cityCode:1,
								personNumber:"${param.personNumber}"
							},
							type:'post',
							async:false,
							success:function(data){
								if(data.msg == '01'){
									console.log(data.strMsg.list[0]);
									$.ajax({
										url: ctx +"/itemDetailServer/queryShowMsg",
										data:data.strMsg.list[0],
										type:'post',
										async:false,
										success:function(data){
											if(data.msg == '00'){
												$.alert({text:"修改时间与服务人员["+data.personnelSchedule.name+"]的排期冲突<br/>相关订单号:"+data.personnelSchedule.orderCode+"<br/>管家姓名:"+data.personnelSchedule.rechargeByName});
											}else{
											$.alert({text:"修改失败!!"});
											}
									 	}
									 	}); 
								}else{ 
								$.alert({text:"排期设置完成"});
								closeModule("ServedLineOne");
								parent.getLineOneTime();
								}
						 	}
						 	}); 
					} 
		    	}
			 });
		}else{
		 $.ajax({
			url: ctx +"/itemDetailServer/queryPersonSchedule" ,
			data:{
				birthDate:emp_id,
				startDate:startTime.replace(new RegExp("-","gm"),""),
				endDate:startTime.replace(new RegExp("-","gm"),""),
				//endDate:endTime.replace(new RegExp("-","gm"),""),
				updateTime:timeSolt
				},
		type:'post',
		async:false,
		success:function(data){
				var userName="";
				var contradictionPai="";
				$.each(data.list,function(i,v){
					userName+=v.userName+",";
					contradictionPai+=v.emp_id+",";
				})
			    var str="";
			    if(data.msg=="00"){
			    	str=userName+"的排期有冲突，请先删除该服务人员";
			    }else{
			    	str="服务人员排期是设定为订单排期，是否修改？"
			    }
				$.confirm({text:str,callback:function(re){
					 if(re){
						 $.ajax({
								url: ctx +"/itemDetailServer/updateOrderServerLined",
								data:{
									id:id,
									version:version,
									orderId:orderId,
									startTime:startTime,
									days:serviceHours,
									hours:timeSolt,
									linedType:1,
									birthDate:contradictionPai,
									orderFenfa:emp_id,
									cityCode:$("#cityCode").val(),
									cateCode:$("#productCode").val(),
									hoursTime:$("#hours").val(),
									time:$("#time").val(),
									personNumber:"${param.personNumber}"
								},
								type:'post',
								async:false,
								success:function(data){
									closeModule("ServedLineOne");
									$.alert({text:"排期设置完成"});
									parent.getLineOneTime();
							 	}
							 	}); 
						} 
			    	}
				 });
	 	}
	 	}); 	
			
		}
	}else{
		//服务时长
		//serviceHours =$("#EndTimeOne").val()*1-$("#StartTimeOne").val()*1+"小时";
		//时间段
		//timeSolt = $("#StartTimeSoltOne").val()+"-"+$("#EndTimeSoltOne").val();
		
		//var startTime = $("#StartTimeSoltOne").val();
		//var endTime = $("#EndTimeOne").val();
		 var timesolt1 = parseInt(startTime.substring(14,17));
		 var timesolt2 = parseInt(endTime.substring(14,17));
		 if(15<=timesolt1&&timesolt1<=45){
			 timesolt1="30"
		 }else if((timesolt1>45&&timesolt1<60)||(timesolt1<15&&timesolt1>0)||(timesolt1==0)){
			 timesolt1="00"
		 }
		 
		 if(15<=timesolt2&&timesolt2<=45){
			 timesolt2="30"
		 }else if((timesolt2>45&&timesolt2<60)||(timesolt2<15&&timesolt2>0)||(timesolt2==0)){
			 timesolt2="00"
		 }
		 timeSolt = startTime.substring(11,13)+":"+timesolt1+"-"+endTime.substring(11,13)+":"+timesolt2;
	  	 if(timesolt2==timesolt1){
			 serviceHours =endTime.substring(11,13)-startTime.substring(11,13)+"小时";
	  	 }else if(timesolt2<timesolt1){
	  		serviceHours =(endTime.substring(11,13)-startTime.substring(11,13)-1)+".5"+"小时";
	  	 }else if(timesolt2>timesolt1){
	  		serviceHours =(endTime.substring(11,13)-startTime.substring(11,13))+".5"+"小时";
	  	 }
	  	
		$.ajax({
			url: ctx +urls,
			data:{
				id:id,
				version:1,
				orderId:orderId,
				startTime:startTime,
				endTime:endTime,
				days:serviceHours,
				hours:timeSolt,
				linedType:1,
				cityCode:1
			},
			type:'post',
			async:false,
			success:function(data){
				closeModule("ServedLineOne");
				$.alert({text:"排期设置完成"});
				parent.getLineOneTime();
		 	}
		 	});
	}
	parent.getPersonInfoOne();
	closeModule();
}

function loadOrderServerLinedOne(orderId){
	var ctx = $("#ctx").val();
	var time = $("#orderLineOne").text().split("--");
	var b = showTime(orderId);
	if(b=="undefined" || b==null || b==""){
		b=",";
	}
	var a=b.split(","); 
	if(time[0] == "" || time[1] == ""){
		$("#setBasicServerLindButton").show();
	}else{
		$("#setBasicServerLindButton").hide();
	}
	$.ajax({
		url: ctx +"/itemDetailServer/loadOrderServerLined",
		data:{
			id:orderId
		},
		type:'post',
		async:false,
		success:function(data){
			if(data.msg=="00"){
				$("#linedId").val(data.itemDetailServer.id);
				$("#Version").val(data.itemDetailServer.version);
				var orderPersionLine=$("#order_person_Line").text();
				var startTime=numberToDatestr(data.itemDetailServer.startTime,12);
				$("#startTimeLinedOne").val(a[0]);
					if($("#startTimeLinedOne").val()!=null && $("#startTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#startTimeLinedOne").attr("disabled",true);
					}else{
						$("#setBasicServerLindButton").show();
					}
				checkStock();
				var stock =  $("#numStock").val();
				if(stock != null && stock != "" && stock != 0){//
					selUpTime();
					$("#serviceHours").val(data.itemDetailServer.days);
					changeTime();
					$("#timeSolt option[value='" +data.itemDetailServer.hours+"']").prop("selected",true);
					$("#hours").val(data.itemDetailServer.hours);//释放库存处理
					$("#time").val(numberToDatestr(data.itemDetailServer.startTime,12))
					$("#endTimeLinedOneDiv").hide();
					$("#serviceHoursDiv").show();
					$("#timeSoltDiv").show();
					/* $("#setBasicServerLindButton").hide(); */
				}else{
					var endTime = numberToDatestr(data.itemDetailServer.endTime,12);
					$("#endTimeLinedOne").val(a[1]);
					if($("#endTimeLinedOne").val()!=null && $("#endTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#endTimeLinedOne").attr("disabled",true);
					}else{
						$("#setBasicServerLindButton").show();
					} 
					$("#endTimeLinedOneDiv").show();
					$("#serviceHoursDiv").hide();
					$("#timeSoltDiv").hide();
					/* $("#setBasicServerLindButton").show(); */
				}
				}else{
					$("#serviceHoursDiv").hide();
					$("#timeSoltDiv").hide();
					/* $("#setBasicServerLindButton").show(); */
				}
	 		}
	 	}); 
}

function showTime(orderId){
	var ctx = $("#ctx").val();
	var time ;
	$.ajax({
		url: ctx +"/itemDetailServer/showTime",
	    async:false, 
		data:{
			id:orderId
		},
		dataType:'json',
		type:'post',
		success:function(data){
			if(data.msg=="00"){
				time = data.itemDetailServer.startTime+","+data.itemDetailServer.endTime;
			}
		}
	});
	return time;
}
/**
 * 查询服务人员库存数量
 * @date:2017年7月21日
 * @author YUNNA
 * 
 */
function checkNumStock(){
	$.ajax({
        url: ctx +"/orderbase/checkNumStock",
        data:{
            dkey    : $("#cityCode").val(),
            dvalue  : $("#productCode").val(),
            remarks : $("#startTimeLinedOne").val().substr(0,10),//截取日期的前十位
            level   : $("#timeSolt").val().split("-")[0],
            types   : $("#timeSolt").val().split("-")[1]
        },	       
        type:'post',
        async:false,
        dataType:'json',
        success:function(data){
            $("#numStock").val(data.count);
       	 }
     });	 
}
//
function checkStock(){
	$.ajax({
		url:ctx+"/orderbase/selectStock",
		data : {
			 dkey    : $("#cityCode").val(),
	         dvalue  : $("#productCode").val(),
		},
		type : 'post',
		async : false,
		dataType : "json",
		success : function(data) {
			if (data.msg == "00") {
				$("#numStock").val(data.count);
			}else{
				$("#numStock").val(0);
			}
		}
		
     });	 
}
	//计算开始时间和结束时间的timeSlot
	function startTimeFunc(){
		var min=parseInt($dp.cal.getP('m'));
		if(15<=min&&min<=45){
			$("#StartTimeOne").val(parseInt($dp.cal.getP('H')));
			$("#StartTimeSoltOne").val($dp.cal.getP('H')+":"+"30");
		}
		if((min>45&&min<60)||(min<15&&min>0)){
			$("#StartTimeOne").val(parseInt($dp.cal.getP('H')));
			$("#StartTimeSoltOne").val($dp.cal.getP('H')+":"+"00");
		}  
	}
	function endTimeFunc(){
		var min=parseInt($dp.cal.getP('m'));
		if(15<=min&&min<=45){
			$("#EndTimeOne").val(parseInt($dp.cal.getP('H')));
			$("#EndTimeSoltOne").val($dp.cal.getP('H')+":"+"30");
		}
		if((min>45&&min<60)||(min<15&&min>0)){
			$("#EndTimeOne").val(parseInt($dp.cal.getP('H')));
			$("#EndTimeSoltOne").val($dp.cal.getP('H')+":"+"00");
		}  
	}
	
	
function updateOnesScheduleTime(){
		var ctx = $("#ctx").val();
		var time = $("#orderLineOne").text().split("--");
		var b = showTime(orderId);
		if(b=="undefined" || b==null || b==""){
			b=",";
		}
		var a=b.split(","); 
		if(time[0] == "" || time[1] == ""){
			$("#setBasicServerLindButton").show();
		}else{
			$("#setBasicServerLindButton").hide();
		}
		$.ajax({
			url: ctx +"/itemDetailServer/loadOrderServerLined",
			data:{
				id:orderId
			},
			type:'post',
			async:false,
			success:function(data){
				if(data.msg=="00"){
					$("#linedId").val(data.itemDetailServer.id);
					$("#Version").val(data.itemDetailServer.version);
					var orderPersionLine=$("#order_person_Line").text();
					var startTime=numberToDatestr(data.itemDetailServer.startTime,12);
					$("#startTimeLinedOne").val(a[0]);
						if($("#startTimeLinedOne").val()!=null && $("#startTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
							$("#startTimeLinedOne").attr("disabled",true);
						}else{
							$("#setBasicServerLindButton").show();
						}
					checkStock();
					var stock =  $("#numStock").val();
					if(stock != null && stock != "" && stock != 0){//
						selUpTime();
						$("#serviceHours").val(data.itemDetailServer.days);
						changeTime();
						$("#timeSolt option[value='" +data.itemDetailServer.hours+"']").prop("selected",true);
						$("#hours").val(data.itemDetailServer.hours);//释放库存处理
						$("#time").val(numberToDatestr(data.itemDetailServer.startTime,12))
						$("#endTimeLinedOneDiv").hide();
						$("#serviceHoursDiv").show();
						$("#timeSoltDiv").show();
						/* $("#setBasicServerLindButton").hide(); */
					}else{
						var endTime = numberToDatestr(data.itemDetailServer.endTime,12);
						$("#endTimeLinedOne").val(a[1]);
						if($("#endTimeLinedOne").val()!=null && $("#endTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
							$("#endTimeLinedOne").attr("disabled",true);
						}else{
							$("#setBasicServerLindButton").show();
						} 
						$("#endTimeLinedOneDiv").show();
						$("#serviceHoursDiv").hide();
						$("#timeSoltDiv").hide();
						/* $("#setBasicServerLindButton").show(); */
					}
					}else{
						$("#serviceHoursDiv").hide();
						$("#timeSoltDiv").hide();
						/* $("#setBasicServerLindButton").show(); */
					}
		 		}
		 	}); 
	}
	
	
function loadOrderServerLinedOneNew(orderId){
	var ctx = $("#ctx").val();
	var time = $("#orderLineOne").text().split("--");
	var b = showTime(orderId);
	if(b=="undefined" || b==null || b==""){
		b=",";
	}
	var a=b.split(","); 
	if(time[0] == "" || time[1] == ""){
		$("#setBasicServerLindButton").show();
	}else{
		$("#setBasicServerLindButton").hide();
	}
	$.ajax({
		url: ctx +"/itemDetailServer/loadOrderServerLined",
		data:{
			id:orderId
		},
		type:'post',
		async:false,
		success:function(data){
			if(data.msg=="00"){
				$("#linedId").val(data.itemDetailServer.id);
				$("#Version").val(data.itemDetailServer.version);
				var orderPersionLine=$("#order_person_Line").text();
				var startTime=numberToDatestr(data.itemDetailServer.startTime,12);
				$("#startTimeLinedOne").val(a[0]);
				$("#startTimeLinedOneOld").val(a[0]);
					if($("#startTimeLinedOne").val()!=null && $("#startTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#startTimeLinedOne").attr("disabled",true);
					}else{
						$("#setBasicServerLindButton").show();
					}
				checkStock();
				var stock =  $("#numStock").val();
				//单次：1新建,2已受理,3匹配中,4已匹配,8已上户,9已完成,10已取消,18,待受理 20撤销 
				//支付状态:20110001"未支付" ,20110002 "部分支付" , 20110003 "支付完成" ,20130002 确认无效 ,20130003 确认有效 ,20130008"退款成功"
				if(payStatus == '20110001' && (orderstatus == 3 || orderstatus == 4  || orderstatus == 2 )){
					
				}else{
					$("#endTimeLinedOne").attr("disabled",true);
				}
				if(stock != null && stock != "" && stock != 0){//
					selUpTime();
					$("#serviceHours").val(data.itemDetailServer.days);
					changeTime();
					$("#timeSolt option[value='" +data.itemDetailServer.hours+"']").prop("selected",true);
					$("#hours").val(data.itemDetailServer.hours);//释放库存处理
					$("#time").val(numberToDatestr(data.itemDetailServer.startTime,12))
					$("#endTimeLinedOneDiv").hide();
					$("#serviceHoursDiv").show();
					$("#timeSoltDiv").show();
					/* $("#setBasicServerLindButton").hide(); */
				}else{
					var endTime = numberToDatestr(data.itemDetailServer.endTime,12);
					if(endTime == ""){
						$("#endTimeLinedOne").removeAttr("disabled");
					}
					$("#endTimeLinedOne").val(a[1]);
					$("#endTimeLinedOneOld").val(a[1]);
					if($("#endTimeLinedOne").val()!=null && $("#endTimeLinedOne").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#endTimeLinedOne").attr("disabled",true);
					}else{
						$("#setBasicServerLindButton").show();
					} 
					$("#endTimeLinedOneDiv").show();
					$("#serviceHoursDiv").hide();
					$("#timeSoltDiv").hide();
					/* $("#setBasicServerLindButton").show(); */
				}
				}else{
					$("#serviceHoursDiv").hide();
					$("#timeSoltDiv").hide();
					/* $("#setBasicServerLindButton").show(); */
				}
	 		}
	 	}); 
}

function isDisabled(){
	var flag=$("#endTimeLinedOne").prop("disabled");
    var endTime1=$("#endTimeLinedOne").val();
    if(endTime1 != ""){
        if(flag){
            var startTime=new Date($("#startTimeLinedOne").val());
            var startTimeOld=new Date($("#startTimeLinedOneOld").val());
            var endTime=new Date($("#endTimeLinedOne").val());

            minus = Math.abs(startTime.getTime()-startTimeOld.getTime());
            if(startTime>startTimeOld){
                var newEndTime=endTime.getTime()+minus;
                var time=formatDateTime(new Date(newEndTime));
                $("#endTimeLinedOne").val(time)
            }else{
                var newEndTime=endTime-minus;
                var time=formatDateTime(new Date(newEndTime));
                $("#endTimeLinedOne").val(time)
            }
            $("#startTimeLinedOneOld").val(startTime)
        }
	}else{
        $("#endTimeLinedOne").removeAttr("disabled");
	}

}
var formatDateTime = function (date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();  
    minute = minute < 10 ? ('0' + minute) : minute;  
    return y + '-' + m + '-' + d+' '+h+':'+minute;  
};  

</script>
</html>