<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#checkWeeks li ,#checkHours li {
	line-height:40px;
}
#checkWeeks li{
	width:14.28%;
}

.week_pc{
	display: block;
	float:left;
	width:70px;
	font-size:14px;
	font-weight: bold;
	line-height:40px;
}
#twoHours li,#threeHours li,#fourHours li{
	line-height:19px;
}
#SelectTime{
  float:left;
  width:70%;
  font-size:14px;
  line-height:24px;
}
#Hourswrap{
	margin-top:15px;
}
#checkHours li:nth-child(1){
	border-left:1px solid #ccc!important;
}
#checkWeeks li:nth-child(1){
	border-left:1px solid #ccc!important;
}
#checkHours,#checkWeeks{
	border-top:1px solid #ccc!important;
}
#Hourswrap ul li{
	border-top:1px solid #ccc!important;
}
</style>
</head>
<body>
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">需求排期</h4>
            </div>
            <input type="hidden" value="${param.type}" id="typeServer">
            <input type="hidden"  id="serverLinedIdNew">
            <input type="hidden" id="linedId">
            <input type="hidden" id="orId">
            <input type="hidden" id="serverLinedVersion">
            <span  type="hidden" id="SelectWeek" style="display: none;"></span>
            <input type="hidden"  id="SelectHours">
            <input type="hidden" id="SelectTime">
           
            
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post" id="updateOrderPq">
            	<div class="form-group  col-xs-12" style="color:red;line-height:20px;">
                  		<h5>订单排期填写规则：</h5>
					<ul>
					  <li class="text-justify">1.订单排期不是某一个服务人员排期，而是应包含所有服务人员排期</li>
					  <li class="text-justify">2.若订单上过户，且未填报过服务人员服务费，开始日期应早于或等于首位上户服务人员的上户日期</li>
					  <li class="text-justify">3.若订单上过户，且己填报过服务人员服务费，开始日期应和当前订单原有开始日期相同([订单管理]-[基本信息]-[排期时间])</li>
					</ul>
	             </div>
	            <div class="form-group  col-xs-12" style="height:15px"></div>
                <div class="row pt10" id="NotZhong">
                   	<div class="form-group  col-xs-12">
                   		<label><em style="color: red">*</em>
                   		起始日期：
                   		<input id="startTimeLined" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLined\')}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	</div>
                   	<div class="form-group  col-xs-12">
                   		<label><em style="color: red">*</em>
                   		结束日期：
						<input id="endTimeLined" class="Wdate form-control" type="text" 
                     		onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLined\')}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	
                   	</div>
                </div>
                <div class="row pt10" id="ZhongWork">
                   	<div class="form-group  col-xs-12">
                   		<label><p><em style="color: red">*</em>选择日期:</p>
                   		<input id="startTimeLinedZhong" class="Wdate form-control" type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTimeLinedZhong\')}',dateFmt:'yyyy-MM-dd'})" />至
						<input id="endTimeLinedZhong" class="Wdate form-control" type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTimeLinedZhong\')}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	</div>
                   	<div class="form-group col-xs-12">
                   		<label><p><em style="color: red">*</em>选择时段:</p>
                   		    <select class="form-control" name="selZdgLinedTime" id="zdgLinedStartTime"></select>至
	                   		<select class="form-control" name="selZdgLinedTime" id="zdgLinedEndTime"></select>
                   		</label>
                   	</div>
                   	<div class="form-group col-xs-12">
                   		<label><p><em style="color: red">*</em>时长:</p>
                   			<input id="selZdgLinedTimeLong" class="form-control" type="text" readonly="readonly" placeholder="自动计算" data-num="0"/>
                   		</label>
                   	</div>
                   	<div class="form-group col-xs-12"></div>
                   	<div class="form-group col-xs-12" id="btnToolbarWeeks">
                   		<label><p><em style="color: red">*</em>选择频次(周):</p></label>
						  <button type="button" class="btn btn-sm" data-week="星期一">星期一</button>
						  <button type="button" class="btn btn-sm" data-week="星期二">星期二</button>
						  <button type="button" class="btn btn-sm" data-week="星期三">星期三</button>
						  <button type="button" class="btn btn-sm" data-week="星期四">星期四</button>
						  <button type="button" class="btn btn-sm" data-week="星期五">星期五</button>
						  <button type="button" class="btn btn-sm" data-week="星期六">星期六</button>
						  <button type="button" class="btn btn-sm" data-week="星期天">星期天</button>
					</div>
					
                   	
                   	
	                   	<div class="form-group  col-xs-12" style="display: none;">
	                   	<div  class="week" style="margin-left:10px;">
	                   		<span class="week_pc">选择频次</span>
		                    <ul id="checkWeeks" style="width:73%;">
		                        <li><span id="Monday">星期一</span></li>
		                        <li><span id="Tuesday">星期二</span></li>
		                        <li><span id="Wednesday">星期三</span></li>
		                        <li><span id="Thursday">星期四</span></li>
		                        <li><span id="Friday">星期五</span></li>
		                        <li><span id="Saturday">星期六</span></li>
		                        <li><span id="Sunday">星期天</span></li>
		                    </ul>
		                  	 
	                   	</div>
	                   	<div id="checkMonths" class="day" style="margin: 20px 0 0 12px;">
	                   		<span class="week_pc" id="week_ps">选择时长</span>
	                   		<ul id="checkHours"  style="width:36%;border:0;">
		                        <li><span>2小时</span></li>
		                        <li><span>3小时</span></li>
		                        <li><span>4小时</span></li>
	                    	</ul>
	                    	<div id="Hourswrap" class="hours">
		                    	 <ul id="twoHours"  style="width:407px;float:left;">
		                    	 <span class="week_pc">选择时段</span>
			                        <li style="border-left: 1px solid #ccc;"><span>7:00-</br>9:00</span></li>
			                        <li><span>11:00-</br>13:00</span></li>
			                        <li><span>15:00-</br>17:00</span></li>
			                        <li><span>19:00-</br>21:00</span></li>
		                    	</ul>
		                    	<ul id="threeHours" style="display:none;width:407px;float:left;">
		                    	<span class="week_pc">选择时段</span>
		                    	    <li style="border-left: 1px solid #ccc;"><span>7:00-</br>10:00</span></li>
			                        <li><span>12:00-</br>15:00</span></li>
			                        <li><span>17:00-</br>20:00</span></li>
		                    	</ul> 
		                    	<ul id="fourHours" style="display:none;width:407px;float:left;">
		                    	<span class="week_pc">选择时段</span>
		                    		<li style="border-left: 1px solid #ccc;"><span>7:00-</br>11:00</span></li>
			                        <li><span>13:00-</br>17:00</span></li>
			                        <li><span>19:00-</br>23:00</span></li>
		                    	</ul> 
	                   		</div>
	                   	</div>
	                </div>
	                
	                
              </div>
              </form>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-primary" id="clearModel"  onclick="clearModel();" style="display: none;">清空</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="setBasicServerLindButton"  onclick="saveLined();">保存</button>
            </div>
        </div>
    </div>
</div>

</body>
<script>
$(function (){
	/* SelectType(2)//默认设置两小时 */
	if($("#typeServer").val()=='钟点工'){
		//钟点工
		$("#ZhongWork").show();
		$("#NotZhong").hide();
		$("#clearModel").show();
		createSelZdgLinedTime();
	}else{
		//延续、单次
		$("#ZhongWork").hide();
		$("#NotZhong").show();
	}
	
	
	/**
	 * 钟点工类型
	 * 创建时段
	 */
	function createSelZdgLinedTime(){
		var html = "<option value='' data-num='0'>--请选择--</option>";
		for (var i = 7; i<=23; i++) {
			html+="<option value='"+i+":00' data-num='"+i+"' >"+i+":00</option>";
		}
		$("#zdgLinedStartTime,#zdgLinedEndTime").html(html);
	}

	/**
	 * 钟点工类型
	 * 时段选择
	 */
	 $("#zdgLinedStartTime,#zdgLinedEndTime").change(function(v){
		var t = $(this);
		var tid = t.prop("id");
		var tnum = t.children("option:selected").data("num")*1;
		if(!t || t.val()==""){
			$("#zdgLinedStartTime,#zdgLinedEndTime").val("").children("option").show();
			$("#selZdgLinedTimeLong").val("");
			return;
		}
		if(tid == "zdgLinedStartTime"){
			var o = $("#zdgLinedEndTime");
			var onum = o.children("option:selected").data("num")*1;
			var startNum = tnum;
			var endNum = tnum+5;//时长最大间隔4小时
			o.children("option").each(function(i,v){
				var vnum = $(v).data("num")*1;
				if($(v).val() == ""){
					$(v).show();
				}else if(vnum > startNum && vnum < endNum){
					$(v).show();
				}else{
					$(v).hide();
				}
			});
			if(o.val()!=""){
				$("#selZdgLinedTimeLong").val((onum-tnum)+"小时").attr("data-num",(onum-tnum));
			}
		}else if(tid == "zdgLinedEndTime"){
			var o = $("#zdgLinedStartTime");
			var onum = o.children("option:selected").data("num")*1;
			var startNum = tnum-5;//时长最大间隔4小时
			var endNum = tnum;
			o.children("option").each(function(i,v){
				var vnum = $(v).data("num")*1;
				if($(v).val() == ""){
					$(v).show();
				}else if(vnum > startNum && vnum < endNum){
					$(v).show();
				}else{
					$(v).hide();
				}
			});
			if(o.val()!=""){
				$("#selZdgLinedTimeLong").val((tnum - onum)+"小时").attr("data-num",(tnum - onum));
			}
		} 
	 })
		
 	/**
     *钟点工类型
 	 *切换选择周
 	 */
	$("#btnToolbarWeeks").find("button").click(function(){
		$(this).toggleClass("btn-success");
	});
    loadOrderServerLined(parent.$("#checkedOrderId").val());
})

/**
 * 保存排期
 */
function saveLined(){
	var ctx = $("#ctx").val();
	var orderId = parent.$("#checkedOrderId").val();
	var emp_id="${param.personId}";
	var personStatus="${param.personStatus}";
	var orderPersionLine=$("#order_person_Line").text();
	var orderPersionDate=orderPersionLine.split("------");
	var orderPersionStDate=orderPersionDate[0]
	var orderPersionEnDate=orderPersionDate[1]
	if($("#typeServer").val()!='钟点工'){
		/*非钟点工类型*/
		var startTime = $("#startTimeLined").val();
		var endTime = $("#endTimeLined").val();
		if(startTime==null||startTime==""||endTime==null||endTime==""){
			$.alert({ text : "请选择开始时间和结束时间！" });
			return ;
		}
		try{
			var st = new Date(startTime.replace(/\-/g, "\/"));  
			var et = new Date(endTime.replace(/\-/g, "\/"));  
			if(et<st){
				$.alert({text:"开始时间大于结束时间。"});
				return;
			}
		}catch(e){
			$.alert({text:"时间格式不正确"});
			return;
		}
		if(orderPersionLine!=null && orderPersionLine!=""){
			try{
				var std = new Date(orderPersionStDate.replace(/\-/g, "\/"));  
				var end = new Date(orderPersionEnDate.replace(/\-/g, "\/"));  
				if(et<end){
					$.alert({text:"订单排期结束时间应大于等于服务人员排期结束时间。"});
					return;
				}
				if(st>std){
					$.alert({text:"订单排期开始时间应大于等于服务人员排期开始时间。"});
					return;
				}
			}catch(e){
				$.alert({text:"时间格式不正确。"});
				return;
			}
		}
		var id = "" ;
		var version = 0;
		if(($("#orId").val()=="start"||$("#orId").val()=="end")&&(orderId!=null||orderId!="")){
			$.ajax({
			url: ctx +"/itemDetailServer/deleteSchedule",
			data:{
				orId:orderId
			},
			type:'post',
			success:function(data){
				if(data.msg=="00"){
					$.ajax({
						url: ctx +"/itemDetailServer/insertOrderServerLined",
						data:{
							id:id,
							version:version,
							orderId:orderId,
							startTime:startTime,
							endTime:endTime,
							linedType:3,
							cityCode:1
						},
						type:'post',
						async:false,
						success:function(data){
							$.alert({text:"排期设置完成"});
							closeModule("ServedLine");
							parent.getLineTime();
					 	}
					 	});
				}
			}
		}); 
		}
		if($("#linedId").val()!=''){
    		id = $("#linedId").val();
    		version = $("#serverLinedVersion").val() ;
    		 $.ajax({
    			url: ctx +"/itemDetailServer/queryPersonSchedule" ,
    			data:{
    				birthDate:emp_id,
    				orderId :orderId,
    				startDate:startTime.replace(new RegExp("-","gm"),""),
    				endDate:endTime.replace(new RegExp("-","gm"),"")
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
		    									endTime:endTime,
		    									linedType:3,
		    									birthDate:contradictionPai,
		    									orderFenfa:emp_id,
		    									markType:10,
		    									cityCode:1
		    								},
		    								type:'post',
		    								async:false,
		    								success:function(data){
		    									$.alert({text:"排期设置完成"});
		    									closeModule("ServedLine");
		    									parent.getLineTime();
		    									parent.getPersonInfo();
		    									parent.getHistoryPerson();
		    							 	}
		    							 	});
		    						} 
		    			    	}
		    				 });
		    	 	}
		    	 	}); 	
    	}else{
			$.ajax({
				url: ctx +"/itemDetailServer/insertOrderServerLined",
				data:{
					id:id,
					version:version,
					orderId:orderId,
					startTime:startTime,
					endTime:endTime,
					linedType:3,
					cityCode:1
				},
				type:'post',
				async:false,
				success:function(data){
					$.alert({text:"排期设置完成"});
					closeModule("ServedLine");
					parent.getLineTime();
			 	}
			 	});
    	}
	}else{
		/*钟点工类型*/
 		var startTime = $("#startTimeLinedZhong").val();//开始日期
 		var endTime = $("#endTimeLinedZhong").val();//结束日期
       	var daysStart = $("#zdgLinedStartTime").val();//时段开始
       	var daysEnd = $("#zdgLinedEndTime").val();//时段结束
       	var daysStartNum = $("#zdgLinedStartTime>option:selected").data("num");//时段开始数值
       	var daysEndNum = $("#zdgLinedEndTime>option:selected").data("num");//时段结束数值
       	var hours = daysStart+"-"+daysEnd;
       	var days = $("#selZdgLinedTimeLong").val();//时段间隔
    	var daysNum = $("#selZdgLinedTimeLong").attr("data-num");//时段间隔数值
        var weeksSel = $("#btnToolbarWeeks").find("button.btn-success");//周
 		if(!startTime || !endTime){
			$.alert({text:"请选择日期"});
			return ;
		}
       	if(!daysStart || !daysEnd){
       		$.alert({text:"请选择时段"});
			return ;
       	}
        if(!days){
       		$.alert({text:"时长不能为空"});
			return ;
       	}else{
       		//判断时段和时长是否一致
       		if((daysEndNum*1-daysStartNum*1) != daysNum*1){
       			$.alert({text:"时段和时长不一致,请重新选择时段"});
    			return ;
       		}
       	}
       	if(weeksSel.size() == 0){
       		$.alert({text:"请选择频次"});
			return ;
       	}
        var weeksArr = [];
       	weeksSel.each(function(i,v){
       		var week = $(v).attr("data-week")||"";
       		weeksArr.push(week);
       	})
       	var weeks = weeksArr.join(",");
        if(orderPersionLine!=null && orderPersionLine!=""){
			try{
				var std = new Date(orderPersionStDate.replace(/\-/g, "\/"));  
				var end = new Date(orderPersionEnDate.replace(/\-/g, "\/"));  
				if(et<end){
					$.alert({text:"订单排期结束时间应大于等于服务人员排期结束时间。"});
					return;
				}
				if(st>std){
					$.alert({text:"订单排期开始时间应大于等于服务人员排期开始时间。"});
					return;
				}
			}catch(e){
				$.alert({text:"时间格式不正确。"});
				return;
			}
		}
        if(($("#orId").val()=="start"||$("#orId").val()=="end")&&(orderId!=null||orderId!="")){
			$.ajax({
			url: ctx +"/itemDetailServer/deleteSchedule",
			data:{
				orId:orderId
			},
			type:'post',
			success:function(data){
				if(data.msg=="00"){
					$.ajax({
						url:ctx+"/itemDetailServer/insertOrderServerLined",
						type:"post",
						datetype:"json",
						async : false,
						data:{
							id : id,
							orderId : orderId,
							startTime : startTime,
							endTime : endTime,
							weeks : weeks,
							days : days,
							hours : hours,
							version:version,
							linedType:2,
							cityCode:1
						},
						success : function(data){
							if (data.msg == "00") {
								closeModule("ServedLine");
								$.alert({text:"排期设置完成"});
								parent.getLineTime();
						}
						}
					});
				}
			}
		}); 
		}
		var id = "" ;
    	var version = 0;
    	var urls="/itemDetailServer/insertOrderServerLined";
    	if($("#linedId").val()!=''){
    		id = $("#linedId").val() ;
    		version = $("#serverLinedVersion").val() ;
    		
    		$.ajax({
    			url: ctx +"/itemDetailServer/queryPersonSchedule" ,
    			data:{
    				birthDate:emp_id,
    				orderId : orderId,
    				startDate:startTime.replace(new RegExp("-","gm"),""),
    				endDate:endTime.replace(new RegExp("-","gm"),""),
    				updateTime:hours,
    				weeks:weeks
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
		    									id : id,
		    				    				orderId : orderId,
		    				    				startTime : startTime,
		    				    				endTime : endTime,
		    				    				weeks : weeks,
		    				    				days : days,
		    				    				hours : hours,
		    				    				version:version,
		    				    				linedType:2,
		    				    				cityCode:1,
		    				    				birthDate:contradictionPai,
		    									orderFenfa:emp_id
		    								},
		    								type:'post',
		    								async:false,
		    								success:function(data){
		    									$.alert({text:"排期设置完成"});
		    									closeModule("ServedLine");
		    									parent.getLineTime();
		    							 	}
		    							 	});
		    						} 
		    			    	}
		    				 });
		    	 	}
		    	 	}); 
    	}else{
			$.ajax({
			url:ctx+"/itemDetailServer/insertOrderServerLined",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				id : id,
				orderId : orderId,
				startTime : startTime,
				endTime : endTime,
				weeks : weeks,
				days : days,
				hours : hours,
				version:version,
				linedType:2,
				cityCode:1
			},
			success : function(data){
				if (data.msg == "00") {
					closeModule("ServedLine");
					$.alert({text:"排期设置完成"});
					parent.getLineTime();
			}
			}
		});
    	}
	}
	parent.getPersonInfo();
	closeModule();
}

/**
 * 
 */
function loadOrderServerLined(orderId){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderServerLined",
		data:{
			orderId:orderId
		},
		type:'post',
		async:false,
		success:function(data){
			if(data.msg=="00"){
				$.each(data.list,function(i,v){
				$("#linedId").val(v.id);
				$("#orId").val(v.id);
				var orderPersionLine=$("#order_person_Line").text();
				if($("#typeServer").val() != '钟点工'){
					/*非钟点工*/
					$("#startTimeLined").val(numberToDatestr(v.startTime,8));
					if($("#startTimeLined").val()!=null && $("#startTimeLined").val()!="" && (orderPersionLine!=null && orderPersionLine!="")){
						$("#startTimeLined").attr("disabled",true);
					}else{
						$("#orId").val("start");
					}
					$("#endTimeLined").val(numberToDatestr(v.endTime,8));
					if($("#endTimeLined").val()!=null && $("#endTimeLined").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#endTimeLined").attr("disabled",true);
					}else{
						$("#orId").val("end");
					}
					$("#serverLinedVersion").val(v.version);
				}else{
					/*钟点工*/
					$("#startTimeLinedZhong").val(numberToDatestr(v.startTime,8));
					if($("#startTimeLinedZhong").val()!=null && $("#startTimeLinedZhong").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#startTimeLinedZhong").attr("disabled",true);
					}else{
						$("#orId").val("start");
					} 
					$("#endTimeLinedZhong").val(numberToDatestr(v.endTime,8));
					if($("#endTimeLinedZhong").val()!=null && $("#endTimeLinedZhong").val()!="" && orderPersionLine!=null && orderPersionLine!=""){
						$("#endTimeLinedZhong").attr("disabled",true);
					} else{
						$("#orId").val("end");
					}
					$("#serverLinedVersion").val(v.version);
					var weeks = v.weeks;
					if(weeks){
						var weeksArr = weeks.split(",");
						var weeksBtn = $("#btnToolbarWeeks");
						for (var i = 0; i < weeksArr.length; i++) {
							weeksBtn.find("button[data-week='"+weeksArr[i]+"']").addClass("btn-success");
						}
					}
					var hours = v.hours;
					if(hours){
						var hoursArr = hours.split("-");
						var sCount = $("#zdgLinedStartTime").find("option[value='"+hoursArr[0]+"']").size();
						var eCount = $("#zdgLinedEndTime").find("option[value='"+hoursArr[1]+"']").size();
						if(sCount == 1){
							$("#zdgLinedStartTime").val(hoursArr[0]).trigger("change");
						}
						if(eCount == 1){
							$("#zdgLinedEndTime").val(hoursArr[1]).trigger("change");
						}
					}
					var numReg=new RegExp("\\d+");
					var daysNum = numReg.exec(v.days);
					$("#selZdgLinedTimeLong").val(v.days||"").attr("data-num",daysNum||0);
				}
				})
			}
	 	}
	 	}); 
}

/**
 * 表单重置
 */
function clearModel(){
	$("#startTimeLinedZhong").val("");
	$("#endTimeLinedZhong").val("");
	$("#SelectWeek").text("");
	$("#SelectHours").val("");
	$("#SelectTime").val("");
	$(".week > ul > li").removeClass("select");
	$(".day > ul > li").removeClass("select");
	$(".hours > ul > li").removeClass("select");
	$("#zdgLinedStartTime,#zdgLinedEndTime").val("").trigger("change");
	$("#btnToolbarWeeks").find("button").removeClass("btn-success");
}


</script>
</html>