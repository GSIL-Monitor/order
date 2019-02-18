<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
.table {
    font-size: 14px;
}
.wrap_select>div{
	display:block;
	border: 1px solid #ddd;
    width: 120px;
    height: 28px;
    line-height: 28px;
    margin: 10px 0;
}
.wrap_select{
	padding: 10px 30%;
	height:auto;
	overflow:hidden;
}
.wrap_select span{
	display:block;
	float:left;
	width:41%;
	height:24px;
	line-height:24px;
	/* border:1px solid #ddd; */
	text-indent:10px;
	font-size:14px;
}
#month ,#upMonth{
    margin-left: 15%;
}
select {
    color: #000;
    width: 20%;
    height: 25px;
    line-height: 25px;
	}
.wrap_data,.wrap_ent{
    padding: 0 10px;
    height: 40px;
    border-top: 1px solid #ccc;
}
.wrap_data p ,.wrap_ent p ,.hl_scrool_leftbtn ,.hl_scrool_rightbtn{
    font-size: 20px;
    font-family:simsun;
    margin-top: 6px;
}
.hl_scrool_leftbtn ,.hl_scrool_rightbtn{
    margin-top: 9px;
}
.whole_day{
    float: left;
    width: 90%;
    height: 40px;
    overflow: hidden;
    margin-left: 4%;
}
.pc{
    float: left;
}
.pc1{
    float: right;
}
.whole_day a{
    float: left;
    line-height: 40px;
    width: 40px;
    text-align: center;
    color: #000;
}
.tablese{
    width: 100%;
}
.tablese ul{
    height: 40px;
    border-top: 1px solid #ddd;
    border-bottom: 1px solid #ddd;
}

.tablese ul li{
    float: left;
    width: 16.6%;
    height: 40px;
    line-height: 40px;
    font-size: 14px;
    text-align: center;
    border-right:1px solid #ddd;
    font-weight: 600;
}
.tablese ul li:nth-child(6){
    border-right:0;
}
#min , #add , #app ,#mint ,#pll ,#num , #addd ,#minn{
    float: left;
    width: 25px;
    background: #fff;
    line-height: 20px;
    height: 26px;
    border: 1px solid #ddd;
    margin-top: 3px;
}
#text_box , #text_ol ,#wis ,#text_boxx{
    width: 46px;
    height: 26px;
    border-right:0;
    border: 1px solid #ddd;
    border-left: 0;
    float: left;
    margin-top: 3px;
    text-align: center;
}
.numbers{
    display: block;
    width: 50px;
    line-height: 24px;
    border: 1px solid #ccc;
    margin: 0 auto;
}
.pon tbody tr{
    height: 40px;
    line-height: 40px;
    font-size: 14px;
}
.pon{
    width: 100%;
    display: none;
}
.table thead tr th ,.table tbody tr td{
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}
.Exhibitor{ width:100%; overflow:hidden;border-top: 1px solid #ddd; }
.hl_main5_content{width:100%; height:40px;float:left;padding: 0 10px;}
.hl_main5_content1{width:90%; overflow:hidden; float:left; margin-left:20px;}
.hl_main5_content1 ul{width:1600px; overflow:hidden;}
.hl_main5_content1 ul li{ float:left; width:40px;height: 40px; display:inline;line-height: 40px;text-align: center;}
.hl_main5_content1 ul li img{ width:90px; }
.demoList { width:1229px; overflow:hidden;}
.demoList li { float:left; width:275px;background:#fff; _display:inline; position:relative; z-index:0; overflow:hidden;}
/*.demoList li:hover { border:1px solid #000; background:#222; }*/
.demoList li a { display:block; overflow:hidden; cursor:pointer;color: #000;}
.demoList li samp,.demoList li div { height:150px; width:275px; position:absolute; left:5px; top:5px; z-index:1; }
.demoList li samp { background:url(images1/zoom.png) no-repeat center center; }
.demoList li div { background:#000; }
.demoList li span,.demoList li b {  display:block; position:absolute; left:5px; bottom:5px; width:90px; z-index:1; height:20px; text-align:center; line-height:20px; font-size:12px; color:#fff; text-shadow:1px 1px 0 #000; font-weight:normal; }
.demoList li span { background:#000; }
/* jQuery jcLightBox css */
#jc-Mod { position:absolute; z-index:99; display:none; left:0; right:0; bottom:0; top:0; cursor:pointer; }
#jc-Box { position:absolute; left:0; top:0; z-index:100; display:none; }
#box-top { height:24px; position:relative; background:url(images1/box-top.png) repeat-x 0 0; }
#box-top-left { background:url(images1/box-ra.png) no-repeat 0 0; height:24px; width:24px; position:absolute; left:-24px; top:0; }
#box-top-right { background:url(images1/box-ra.png) no-repeat -25px 0; height:24px; width:24px; position:absolute; right:-24px; top:0; }
#box-btm { height:24px; position:relative;  background:url(images1/box-bottom.png) repeat-x 0 0; }
#box-btm-left { background:url(images1/box-ra.png) no-repeat 0 -25px; height:22px; width:24px; position:absolute; left:-24px; top:0; }
#box-btm-right { background:url(images1/box-ra.png) no-repeat -25px -25px; height:22px; width:24px; position:absolute; right:-24px; top:0; }
#box-cen { background:url(images1/box-left.png) repeat-y 0 0; position:relative; left:-24px;}
#box-cen-right { background:url(images1/box-right.png) repeat-y right 0; position:relative; right:-48px;}
#box-cen-img { position:relative; left:-24px; background:#000; }
#box-pn { position:absolute; left:10px; top:32px; height:30px; width:70px; z-index:101; }
#box-prev { background:#000; float:left; width:32px; height:30px; cursor:pointer; }
#box-next { background:#000; float:right; width:32px; height:30px; cursor:pointer;}
#box-pn a { display:block; height:20px; position:relative; top:5px; left:5px; background:url(images1/sprite.png) no-repeat;}
#box-next a { background-position:0 -20px; }
#box-prev a { background-position:0 -40px; }
#box-close { background:url(images1/box-close.png) no-repeat; height:60px; width:42px; position:absolute; top:14px; right:-50px; cursor:pointer; z-index:101; }
#box-close a { display:block; height:20px; position:relative; top:20px; left:8px; background:url(images1/sprite.png) no-repeat 0 0;}
#box-text { height:50px; position:absolute; left:0; }
#box-text samp { display:block; position:absolute; background:url(img/arrow.png) no-repeat; height:12px; width:12px; left:48%; top:-8px; z-index:1;}
#box-text-left { background:url(images1/box-text.png) no-repeat; height:50px; width:10px; position:absolute; left:0; top:0;}
#box-text-cen { color:#fff; text-shadow:1px 1px 0 #000; text-align:center; background:url(images1/box-text-cen.png) repeat-x; height:50px; line-height:50px; margin:0 10px; padding:0 30px; font-size:15px;overflow:hidden;}
#box-text-right { background:url(images1/box-text.png) no-repeat right 0; height:50px; width:10px; position:absolute; right:0; top:0;}
  .select-actived{background: #8b005a}
  #schedulingNew_day thead tr th{cursor: pointer}
.clear{ clear:both; height:0; width:0; overflow:hidden;}
.rollBoxBig a.hid{
	background:#DEDEDE;
}
.roll{
	width: 100%px;
	position: relative;
}
.rollBox{
	width: 88%;
	height: 40px;
	overflow: hidden;
	margin-left: 5%;
}
.rollBoxBig{
    width: 600px;
    overflow: hidden;
   	position: relative;
}
.rollBoxBig a{
	float: left;
	width:40px;
	height:40px;
	line-height: 40px;
	display: block;
	text-align: center;
	color:#337ab7;
}
.rollBoxBig a.on{
       background: blue;
   }
.pc{ position: absolute; left: 2%; top: 11px; width: 20px; height: 20px; cursor: pointer;font-family:simsun;font-size:18px;}
.pc1{ position: absolute; right: 1%; top: 11px; width: 20px; height: 20px;cursor: pointer;font-family:simsun;font-size:18px;}
.select-actived {
	color:#fff;
    background:#CCCCCC!important;
}
.bggrey{
    width:20px;
    height:20px;
    background-color: #7EB3B9;
      
}
.bgredreserve{
    width:20px;
    height:20px;
    background-color: #A52A2A;
}
.bgblueholiday{
    width:20px;
    height:20px;
    background-color: #0000AA;
}
</style>
</head>
<body>
<input  id="ctx" name="ctx" value="${ctx}" />
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">排期信息</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <div class="info clearfix">
                	<input type="hidden" id="ctx" value="${ctx }">
					<input type="hidden" id="data_time" >
					<input type="hidden"  id="personId" >
					<div> 
							<div align="center" style="margin:20px 0 10px 0;line-height:25px;">
					            <form name="form1" method="post" action="">
					                <select name="year" id="selYear" name="selYear" >
					                </select>年
					                <select name="month" id="selMon" name="selMon"  >
					                </select>月
					                <select name="day" id="selDay"  name="selDay"  onchange="selTime()">
					                </select>日
					            </form>
				        	</div> 
							<div class="wrap_select clearfix">
				                 <span id="year">2017</span>
				                 <span id="month">06</span>
				             </div>
				             
							<div class="row">
								<div class="form-group col-xs-12">
									<div class="rollBox">
										<div class="rollBoxBig" id="time">
											<!-- 14天 -->
										</div>
									</div>
									<p class="pc rollLeft"><</p>
									<p class="pc1 rollRight ">></p>
								</div>
							</div>
							
				            <table class="table table-condensed" id='schedulingNew_day'>
				              		<thead>
				              			<tr>
				               			<th>6:00</th>
				               			<th>6:30</th>
				               			<th>7:00</th>
				               			<th>7:30</th>
				               			<th>8:00</th>
				               			<th>8:30</th>
				               		</tr>
				               		<tr>
				               			<th>9:00</th>
				               			<th>9:30</th>
				               			<th>10:00</th>
				               			<th>10:30</th>
				               			<th>11:00</th>
				               			<th>11:30</th>
				              			</tr>
				               		<tr>
				               			<th>12:00</th>
				               			<th>12:30</th>
				               			<th>13:00</th>
				               			<th>13:30</th>
				               			<th>14:00</th>
				               			<th>14:30</th>
				              		</tr>
				              		<tr>
				               			<th>15:00</th>
				               			<th>15:30</th>
				               			<th>16:00</th>
				               			<th>16:30</th>
				               			<th>17:00</th>
				               			<th>17:30</th>
				              		</tr>
				              		<tr>
				               			<th>18:00</th>
				               			<th>18:30</th>
				               			<th>19:00</th>
				               			<th>19:30</th>
				               			<th>20:00</th>
				               			<th>20:30</th>
				              		</tr>
				              		<tr>
				               			<th>21:00</th>
				               			<th>21:30</th>
				               			<th>22:00</th>
				               			<th>22:30</th>
				               			<th>23:00</th>
				               			<th>23:30</th>
				              		</tr>
				              		</thead>
				             </table>
				      </div>
					
					<div class="row">
				         <div class="hint-lis">
					         <div class="col-xs-3 text-c">
				                 <div class="news-hint">
				                     <p class="time-hint">请假</p>
				                     <div class="bgblueholiday"></div>
				                 </div>
					         </div>
				             <div class="col-xs-3 text-c">
				                 <div class="news-hint">
				                     <p class="time-hint">预留</p>
				                     <div class="bgorange"></div>
				                 </div>
				             </div> 
				             <div class="col-xs-3 text-c">
				                 <div class="news-hint">
				                     <p class="time-hint">已约</p>
				                     <div class="bgredreserve"></div>
				                 </div>
				             </div>
				              <div class="col-xs-3 text-c">
				                 <div class="news-hint">
				                     <p class="time-hint">空闲</p>
				                     <div class="bggreen"></div>
				                 </div>
				             </div> 
				         </div>
				    </div>
                </div>
            </div>
            <div class="modal-footer">
             <!--  <button type="button" class="btn btn-primary btn-sm" onclick="clear_Scheduling();">清空选择</button> -->
             <!--  <button type="button" class="btn btn-primary btn-sm" onclick="updateSchedulings(3);">请假</button>
              <button type="button" class="btn btn-primary btn-sm" onclick="updateSchedulings(1);">取消请假</button> -->
			</div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/script.js"></script>
<script type="text/javascript" src="${ctx}/js/time.js"></script> 
<script type="text/javascript">
$(function(){
	CreateTime("year","month","time",new Date() );
	scheduleDay("${param.personId}",new Date());  
	origonFan();
	
   var start;
   var parentStart;
   var beginIndex;
   var td = $("#schedulingNew_day th");
   	   $("#schedulingNew_day thead tr th").on("click",function(){
   		   var count = $("#schedulingNew_day thead tr  .select-actived").size();
   		   if(count==0){
				$(this).addClass("select-actived");
	    		  start = $(this).index() + 1;
	    		  parentStart = $(this).parent().index() + 1 ;
	    		  beginIndex = start + 6 * (parentStart>1?parentStart-1:0) -1;
	    		 // alert("start"+start+"parentStart"+parentStart+"beginIndex"+beginIndex)
   		   }else{
				var end = $(this).index() + 1 ;
       			var parentEnd = $(this).parent().index() + 1 ;
       			var endIndex = end + 6 * ( parentEnd>1?parentEnd-1:0 ) ;
   				var sub = endIndex - beginIndex;
  	    		 // alert("end"+end+"parentEnd"+parentEnd+"endIndex"+endIndex+"sub"+sub)
   					for(var i = 0;i < Math.abs(sub-1);i++){
   						if(sub>0){
   							beginIndex++;
   						}else{
   							beginIndex--;
   						}
   						$("#schedulingNew_day thead tr th").eq(beginIndex).addClass("select-actived");
   					}
   		   }
   		   
   	   });
      })
	//格式化时间
	function formatTime(time){
    	   return time % 1 == 0 ? time + ':00' : Math.floor(time) + ':30';
       }
	//清空
	function clear_Scheduling(){
    	   $("#schedulingNew_day thead tr th").removeClass("select-actived");
       }
	function yearChange(){
		var year_id=$("#year_id").val();
		var html ="";
			if(year_id!=""){
				html +="<option value='' >请选择</option>";
				html +="<option value='1' >1</option>";
				html +="<option value='2' >2</option>";
				html +="<option value='3' >3</option>";
				html +="<option value='4' >4</option>";
				html +="<option value='5' >5</option>";
				html +="<option value='6' >6</option>";
				html +="<option value='7' >7</option>";
				html +="<option value='8' >8</option>";
				html +="<option value='9' >9</option>";
				html +="<option value='10'>10</option>";
				html +="<option value='11'>11</option>";
				html +="<option value='12'>12</option>";
			}
		$("#month_id").html(html);
	}
	/**
	 * 生成时间
	 * @param yearId
	 * @param monthId
	 * @param timeId
	 */
	function CreateTime(yearId,monthId,timeId,date){
		$("#"+yearId).html("");
    	$("#"+monthId).html("");
    	$("#"+timeId).html("");
		for (var i = 0; i < 14; i++) {
			var ss = "";
			if(i > 0 ){
				ss = AddDays(date ,1)
			}else{
				ss = date;
			}
		    var year= ss.getFullYear();
		    var month = ss.getMonth()+1;
		    var day = ss.getDate(); 
		    var time = year+"-"+month+"-"+day;
		    var html = '<a class="hid" data-time='+time+' data-year='+year+' data-month='+month+'>'+day+'</a>';
	    	$("#"+yearId).html(year);
	    	$("#"+monthId).html(month);
			$("#"+timeId).append(html);
		}
		origonFan();
	}
	function scheduleDay(emp_id,time){
		if(time==""||time==null||time==undefined){
			time=new Date().format("yyyy")+GetFullMonth(new Date())+GetFullDate(new Date());
		} else{
			time=new Date(time).format("yyyy")+GetFullMonth(new Date(time))+GetFullDate(new Date(time));
		} 
		$("#personId").val(emp_id);
		 $.ajax({
				url : ctx+"/itemDetailServer/scheduleDay",
				type : "POST",
				data : {
					empId:emp_id,
					time:time
					},
				dataType : "json",
				async : false,
				success : function(data) {
					var dataObj=eval("("+data+")");//转换为json对象 
					$("#schedulingNew_day tr th").removeClass("scheduling-holiday");
					$("#schedulingNew_day tr th").removeClass("scheduling-fill");
					$("#schedulingNew_day tr th").removeClass("scheduling-stay");
					$("#schedulingNew_day tr th").removeClass("scheduling-underfill");
					$("#schedulingNew_day tr th").each(function(i){
						var h_hour = $(this).html();
						var td = $(this);
						$(dataObj).each(function(){
							if(time == this.D && h_hour == formatTime(this.H)){
								var status = this.STATUS;
								if(status==3){//1未满
									$(td).addClass("scheduling-holiday").css({'height':'38px','width':'38px','line-height': '29px'});
								}else if(status == 2){//2预留
									$(td).addClass("scheduling-stay").css({'height':'38px','width':'38px','line-height': '29px'});
								}else if(status == 4){//已约
									$(td).addClass("scheduling-fill").css({'height':'38px','width':'38px','line-height': '29px'});
								}else{
									$(td).addClass("scheduling-underfill").css({'height':'38px','width':'38px','line-height': '29px'});
								} 
							 }
						});
					});  
				}
			});   
		
	}
	 function updateSchedulings(status){
	  var beginText = $("#schedulingNew_day .select-actived").eq(0).html();
   	  var bs = beginText.split(":");
   	  var beginTime = bs[0]+bs[1]+'00';
   	  var endText = $("#schedulingNew_day thead tr  .select-actived").eq(-1).html();
   	  var es = endText.split(":");
   	  var endTime = parseInt(es[0])+1+es[1]+'00';
   	  var time=$("#data_time").val();
   	  var beginDate="";
   	  var endDate="";
	   	if(time==""||time==null||time==undefined){
		   	 beginDate=new Date().format("yyyy")+GetFullMonth(new Date())+GetFullDate(new Date());
		   	 endDate=new Date().format("yyyy")+GetFullMonth(new Date())+GetFullDate(new Date());
	   	}else{
			 beginDate = new Date(time).format("yyyy")+GetFullMonth(new Date(time))+GetFullDate(new Date(time));
			 endDate = new Date(time).format("yyyy")+GetFullMonth(new Date(time))+GetFullDate(new Date(time));
		} 
   	  var empId = $("#personId").val();
	  var count = $("#schedulingNew_day thead tr .select-actived:not(.scheduling-holiday)").size();
	   	  if(status==1 && count!=0){
	   		  $.alert({text:"请选择休假时间取消休假！"});
	   		  return;
	   	  }
   	  $.ajax({
				type : "POST",
				url : ctx+"/schedule/updateSchedule",
				data : {
					startDate:beginDate,
					endDate:endDate,
					startTime:parseInt(beginTime),
					endTime:parseInt(endTime),
					status:status,
					empId:empId
					},
				dataType : "json",
				async : true,
				success : function(data) {
					var text = '';
					if(status==3){
						text = "请假";
					}else if(status==1){
						text = "休假"
					}
					if(data == '00'){
						$.alert({text:"设置"+text+"成功！"});
						scheduleDay($("#personId").val(),$("#data_time").val())
						closeModule();
					}else{
						$.alert({text:"设置"+text+"失败！"});	
					}
				}
			}); 
      }
	//格式化时间
	function format(fmt){
		var o = { 
		        "M+" : this.getMonth()+1,                 //月份 
		        "d+" : this.getDate(),                    //日 
		        "h+" : this.getHours(),                   //小时 
		        "m+" : this.getMinutes(),                 //分 
		        "s+" : this.getSeconds(),                 //秒 
		        "q+" : Math.floor((this.getMonth()+3)/3), //季度 
		        "S"  : this.getMilliseconds()             //毫秒 
		    }; 
		    if(/(y+)/.test(fmt)) {
		            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
		    }
		     for(var k in o) {
		        if(new RegExp("("+ k +")").test(fmt)){
		             fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? ("0"+o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
		         }
		     }
		    return fmt; 
		
	}
	//返回月份（修正为两位数） 
	function GetFullMonth(date) {
		var v = date.getMonth() + 1;
		if (v > 9){
			return v.toString();
		}
		return "0" + v;
	}
	//返回日 （修正为两位数） 
	function GetFullDate(date) {
		var v = date.getDate();
		if (v > 9){
			return v.toString();
		}
		return "0" + v;
	}
	$(function(){
    	/*
         * 生成级联菜单
         */
         
         
        var i=2017;
        var date = new Date();
        year = date.getFullYear();//获取当前年份
        month_d = date.getMonth()+1;//获取当前月份
        day_d = date.getDate();  //获取当前日期
        var dropList;
       
        for(i;i<2020;i++){
            if(i == year){
                dropList = dropList + "<option value='"+i+"' selected>"+i+"</option>";
            }else{
                dropList = dropList + "<option value='"+i+"'>"+i+"</option>";
            }
        }       
        $('select[name=year]').html(dropList);//生成年份下拉菜单
        
        var monthly;
        for(month=1;month<13;month++){
        	if(month == month_d){
            	monthly = monthly + "<option value='"+month+"' selected>"+month+"</option>";        		
        	}else{
        		monthly = monthly + "<option value='"+month+"'>"+month+"</option>";
        	}
        }
        $('select[name=month]').html(monthly);//生成月份下拉菜单
        var dayly;
        for(day=1;day<=31;day++){
        	if(day == day_d){
	            dayly = dayly + "<option value='"+day+"' selected>"+day+"</option>";      		
        	}else{
        		dayly = dayly + "<option value='"+day+"'>"+day+"</option>";
        	}
        }
        $('select[name=day]').html(dayly);//生成天数下拉菜单
        /*
         * 处理每个月有多少天---联动
         */
          $('select[name=month]').change(function(){
            var currentDay ="<option>请选择</option>";
            var Flag = $('select[name=year]').val();
            var currentMonth = $('select[name=month]').val();
            switch(currentMonth){
                case "1" :
                case "3" :
                case "5" :
                case "7" :
                case "8" :
                case "10" :
                case "12" :total = 31;break;
                case "4" :
                case "6" :
                case "9" :
                case "11" :total = 30;break;
                case "2" :
                    if((Flag%4 == 0 && Flag%100 != 0) || Flag%400 == 0){
                        total = 29;
                    }else{
                        total = 28;
                    }
                default:break;
            }
             for(day=1;day <= total;day++){
                currentDay = currentDay + "<option value='"+day+"'>"+day+"</option>";
            } 
            $('select[name=day]').html(currentDay);//生成日期下拉菜单
            }); 
    }); 
    function selTime(){
			var year = $("#selYear").val();
			var month= $("#selMon").val()
			var day  = $("#selDay").val()
			//var thisDay=new Date(date).format("yyyy")+GetFullMonth(new Date(date))+GetFullDate(new Date(date))
		    var time = year+-+month+-+day;
			CreateTime("year","month","time",new Date(time));
	}
	function origonFan(){
		$('.tablese ul li').on('click',function(){
	    	$(this).addClass('sioent').siblings().removeClass('sioent');
	    })
	    //左右切换日期
	    var length = $('.rollBoxBig a').size();
	  	var allLi = $(".rollBoxBig").html();
	  	$(".rollBoxBig").html(allLi+allLi);
	  	$(".rollBoxBig").append(allLi);
	  	var l = $(".rollBoxBig a").length;   
	  	var left = "-"+0+"px"; 
	  	$(".rollBoxBig").width(40*l);
	  	$(".rollBoxBig").css("left",left);
	  	$(".rollRight").click(function(){
	    	$(".rollBoxBig").animate({ "left" : "-=" + "40px"},500);
	  	});
	  	$(".rollLeft").click(function(){
	    	$(".rollBoxBig").animate({ "left" : "+=" + "40px"},500);
	  	});
		$('.rollBoxBig a').on('click',function(){
			$('.table-condensed thead tr th').removeClass('select-actived');
			//alert(1)
		})
	  	//单机时间加背景
	  	$('.rollBoxBig>a').on('click',function(){
	    	$("#year").html($(this).attr("data-year"));
	     	$("#month").html($(this).attr("data-month"));
	     	$(this).addClass('hid').siblings().removeClass('hid');
	    })
	    //单击日期查询详情
	    $('#time a').on('click',function(){
	    	$("#data_time").val($(this).attr("data-time"));
	    	var time=$(this).attr("data-time");
	    	scheduleDay($("#personId").val(),time);
	    })

		$(".wrap").rollmodule();
	}
</script>
</body>
</html>