<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html> 
<html>
<style>
	.orderlinedul li{float:left; list-style:none;}
	.orderlinedhourul li{font-size:12px;}
</style>
<body>
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">需求排期</h4>
            </div>
            <input type="hidden" name="id" id="serverLinedId">
            <input type="hidden" name="version" id="serverLinedVersion">
            <div class="modal-body Scheduling" id="demo_box">
            	<form class="form-inline" action="" method="post">
<!--                 <div style="height:29px;"> -->
<!--                     <ul class="orderlinedul"> -->
<!--                         <li>起始日期：<input id="startTimeLined" class="Wdate" type="text" style="width: 100px" -->
<!--                                     onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" /></li> -->
<!--                        	<li style="width:30px;"></li> -->
<!--                         <li>结束日期：<input id="endTimeLined" class="Wdate" type="text" style="width: 100px" -->
<!--                                     onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" /></li> -->
<!--                     </ul> -->
<!--                 </div> -->
                <div class="row pt10">
                   	<div class="form-group  col-xs-12">
                   		<label><span style="color: red">*</span>起始日期：
                   		<input id="startTimeLined" class="Wdate form-control" type="text" 
							onfocus="WdatePicker({Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                             &nbsp;<span style="color: red">*</span>结束日期：&nbsp;
						<input id="endTimeLined" class="Wdate form-control" type="text" 
                     		onfocus="WdatePicker({Date:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                   		</label>
                   	</div>
                </div>
                <div class="row pt10">
                	<div class="form-group  col-xs-12"><label>预约周期：</label>
                		<input type="radio" name="orderLinedType" style="display:none;" value="1" onclick="setLinedType(1);">
<!--                 		按月 -->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="orderLinedType" value="2" onclick="setLinedType(2);" checked="checked" > 按周
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="orderLinedType" value="3" onclick="setLinedType(3);"> 全天
                	</div>
                </div>
                <div style="height:5px;"></div>
                <div id="checkWeeks" class="week">
                    <label><span>周：</span></label>
                    <ul id="weeks">
                        <li><span>星期一</span></li>
                        <li><span>星期二</span></li>
                        <li><span>星期三</span></li>
                        <li><span>星期四</span></li>
                        <li><span>星期五</span></li>
                        <li><span>星期六</span></li>
                        <li><span>星期天</span></li>
                    </ul>
                </div>
                <div id="checkMonths" class="day" style="height:200px;display:none;">
                    <label><span>日：</span></label>
                    <ul id="days">
                        <li><span>1</span></li>
                        <li><span>2</span></li>
                        <li><span>3</span></li>
                        <li><span>4</span></li>
                        <li><span>5</span></li>
                        <li><span>6</span></li>
                        <li><span>7</span></li>
                        <li><span>8</span></li>
                        <li><span>9</span></li>
                        <li><span>10</span></li>
                        <li><span>11</span></li>
                        <li><span>12</span></li>
                        <li><span>13</span></li>
                        <li><span>14</span></li>
                        <li><span>15</span></li>
                        <li><span>16</span></li>
                        <li><span>17</span></li>
                        <li><span>18</span></li>
                        <li><span>19</span></li>
                        <li><span>20</span></li>
                        <li><span>21</span></li>
                        <li><span>22</span></li>
                        <li><span>23</span></li>
                        <li><span>24</span></li>
                        <li><span>25</span></li>
                        <li><span>26</span></li>
                        <li><span>27</span></li>
                        <li><span>28</span></li>
                        <li><span>29</span></li>
                        <li><span>30</span></li>
                        <li><span>31</span></li>
                    </ul>
                </div>
                <div style="height:5px;"></div>
                <div class="week" id="checkHours">
                	<label><span>预约时间：</span>
                		<br><br>
                	</label>
                    <ul id="hours" class="orderlinedhourul" style="width:481px;">
                    	<li style="width:40px;"><span>0:00</span></li>
                        <li style="width:40px;"><span>0:30</span></li>
                        <li style="width:40px;"><span>1:00</span></li>
                        <li style="width:40px;"><span>1:30</span></li>
                        <li style="width:40px;"><span>2:00</span></li>
                        <li style="width:40px;"><span>2:30</span></li>
                        <li style="width:40px;"><span>3:00</span></li>
                        <li style="width:40px;"><span>3:30</span></li>
                        <li style="width:40px;"><span>4:00</span></li>
                        <li style="width:40px;"><span>4:30</span></li>
                        <li style="width:40px;"><span>5:00</span></li>
                        <li style="width:40px;"><span>5:30</span></li>
                        <li style="width:40px;"><span>6:00</span></li>
                        <li style="width:40px;"><span>6:30</span></li>
                        <li style="width:40px;"><span>7:00</span></li>
                        <li style="width:40px;"><span>7:30</span></li>
                        <li style="width:40px;"><span>8:00</span></li>
                        <li style="width:40px;"><span>8:30</span></li>
                        <li style="width:40px;"><span>9:00</span></li>
                        <li style="width:40px;"><span>9:30</span></li>
                        <li style="width:40px;"><span>10:00</span></li>
                        <li style="width:40px;"><span>10:30</span></li>
                        <li style="width:40px;"><span>11:00</span></li>
                        <li style="width:40px;"><span>11:30</span></li>
                        <li style="width:40px;"><span>12:00</span></li>
                        <li style="width:40px;"><span>12:30</span></li>
                        <li style="width:40px;"><span>13:00</span></li>
                        <li style="width:40px;"><span>13:30</span></li>
                        <li style="width:40px;"><span>14:00</span></li>
                        <li style="width:40px;"><span>14:30</span></li>
                        <li style="width:40px;"><span>15:00</span></li>
                        <li style="width:40px;"><span>15:30</span></li>
                        <li style="width:40px;"><span>16:00</span></li>
                        <li style="width:40px;"><span>16:30</span></li>
                        <li style="width:40px;"><span>17:00</span></li>
                        <li style="width:40px;"><span>17:30</span></li>
                        <li style="width:40px;"><span>18:00</span></li>
                        <li style="width:40px;"><span>18:30</span></li>
                        <li style="width:40px;"><span>19:00</span></li>
                        <li style="width:40px;"><span>19:30</span></li>
                        <li style="width:40px;"><span>20:00</span></li>
                        <li style="width:40px;"><span>20:30</span></li>
                        <li style="width:40px;"><span>21:00</span></li>
                        <li style="width:40px;"><span>21:30</span></li>
                        <li style="width:40px;"><span>22:00</span></li>
                        <li style="width:40px;"><span>22:30</span></li>
                        <li style="width:40px;"><span>23:00</span></li>
                        <li style="width:40px;"><span>23:30</span></li>
                    </ul>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="setBasicServerLindButton" style="display:none;" onclick="saveLined();">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function() {
        $(".week > ul > li").on("click",function(){
            if($(this).hasClass('select')){
                $(this).removeClass('select');
            }else{
                $(this).addClass('select');
            }
        });
        $(".day > ul > li").on("click",function(){
            if($(this).hasClass('select')){
                $(this).removeClass('select');
            }else{
                $(this).addClass('select');
            }
        });
        loadOrderServerLined(parent.$("#checkedOrderId").val());
     	// 判断哪些状态下订单可以修改
        var orderStatus = parent.$("#checkedOrderStatus").val();
		if(orderStatus==1 || orderStatus==2 || orderStatus==3 || orderStatus==4 || orderStatus==5 || orderStatus==11){
			$("#setBasicServerLindButton").removeAttr("style");
		}else{
			$("#setBasicServerLindButton").css("display","none");
		}
    });
    
    function saveLined(){
    	// 得到月份、星期、日期等
		var weeks = "";
		var days = "";
		var hours = "";
		$("#weeks").children("li").each(function(){
    		if($(this).hasClass('select')){
    			weeks += $(this).text() +"," ;
    		}
    	});
		$("#days").children("li").each(function(){
    		if($(this).hasClass('select')){
    			days += $(this).text() +"," ;
    		}
    	});
		$("#hours").children("li").each(function(){
    		if($(this).hasClass('select')){
    			hours += $(this).text() +"," ;
    		}
    	});
		weeks = weeks.substring(0, weeks.length-1);
		days = days.substring(0, days.length-1);
		hours = hours.substring(0, hours.length-1);
		
		var linedType = $('input:radio[name=orderLinedType]:checked').val();
		
		if(linedType==1){
			weeks = "";
		}else if(linedType){
			days = "" ;
		}
    	var ctx = $("#ctx").val();
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
			$.alert({text:"时间格式不正确。"});
			return;
		}
    	//var remark = $("#remark").val();
    	var orderId = parent.$("#checkedOrderId").val();
    	var urls = "/itemDetailServer/insertOrderServerLined" ;
    	var id = "" ;
    	var version = 0;
    	if($("#serverLinedId").val()!=''){
    		urls = "/itemDetailServer/updateOrderServerLined" ;
    		id = $("#serverLinedId").val() ;
    		version = $("#serverLinedVersion").val() ;
    	}
		$.ajax({
			url: ctx +urls,
			data:{
				id:id,
				version:version,
				orderId:orderId,
				startTime:startTime,
				endTime:endTime,
				weeks:weeks,
				days:days,
				hours:hours,
				linedType:linedType
			},
			type:'post',
			async:false,
			success:function(data){
		 	}
   	 	});
    	closeModule("orderLined");
    }
    
    function loadOrderServerLined(orderId){
    	var ctx = $("#ctx").val();
    	$.ajax({
			url: ctx +"/itemDetailServer/loadOrderServerLined",
			data:{
				id:orderId
			},
			type:'post',
			async:false,
			success:function(data){
				if(data.msg=="00"){
					$("#serverLinedId").val(data.itemDetailServer.id);
					$("#serverLinedVersion").val(data.itemDetailServer.version);
					$("#startTimeLined").val(data.itemDetailServer.startTime);
					$("#endTimeLined").val(data.itemDetailServer.endTime);
					$("input:radio[name=orderLinedType]:eq(" +(data.itemDetailServer.linedType-1) +")").attr("checked","checked");
					setLinedType(data.itemDetailServer.linedType);
					$("#weeks").children("li").each(function(){
			    		if(data.itemDetailServer.weeks.indexOf($(this).text())>=0){
			    			$(this).addClass('select');
			    		}
			    	});
					var days = data.itemDetailServer.days.split(",");
					$("#days").children("li").each(function(){
						for(var i=0; i<days.length; i++){
							if(days[i]==$(this).text()){
								$(this).addClass('select');
							}
						}
			    	});
					var hours = data.itemDetailServer.hours.split(",");
					$("#hours").children("li").each(function(){
						for(var i=0; i<hours.length; i++){
							if(hours[i]==$(this).text()){
								$(this).addClass('select');
							}
						}
			    	});
					//$("#hour_star option[value='" +data.itemDetailServer.startTime +"']").attr("selected","selected");
				}
		 	}
   	 	}); 
    }
    
    function setLinedType(type){
    	if(type==1){
    		$("#checkMonths").css("display","inline");
    		$("#checkWeeks").css("display","none");
    		$("#checkHours").css("display","inline");
    	}else if(type==2){
    		$("#checkMonths").css("display","none");
    		$("#checkWeeks").css("display","inline");
    		$("#checkHours").css("display","inline");
    	}else if(type==3){
    		$("#checkMonths").css("display","none");
    		$("#checkWeeks").css("display","none");
    		$("#checkHours").css("display","none");
    	}
    }
</script>

</html>

