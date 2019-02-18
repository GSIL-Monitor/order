<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<script src="${ctx}/js/orderBase.js"></script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12 -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="javascript:void(0)">生产管理</a></li>
                <li><a href="javascript:void(0)">订单管理系统</a></li>
                <li><a href="javascript:void(0)">订单模块管理</a></li>
                <li class="active">渠道订单</li>
            </ol>
        </div>
    </div>
    <!-- /.row -->
        <div class="row">
            <div class="col-lg-8">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <tbody class="dataTable_wrapper">
                            <div class="dataTable-info">
                                <header>
                                    <h4>查询</h4>
                                    <!--表单显示及隐藏-->
                                    <div class="drop-on">
                                        <span><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>
                                    <div class="drop-down">
                                        <span><i class="glyphicon glyphicon-chevron-down"></i></span>
                                    </div>
                                </header>
                                <div class="info-select clearfix">
                                    <form class="form-inline">
                                    <div class="row">
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">订单编号:</span>
   												<input type="text" name="orderCode" id="orderCode" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">客户姓名:</span>
   												<input type="text" name="userName" id="userNameOrder" class="form-control"/>                    		
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">客户电话:</span>
                                    			<input type="text" name="userMobile" id="userMobileOrder" class="form-control"/>
                                    		</label>
                                    	</div>
                                    </div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">订单来源:</span>
                                    		<select name="orderSourceId" id="orderSourceId" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">订单类型:</span>
                                    			<select name="serverType" id="serverType" onchange="javascript:checkedChannelOrderStatusSelect(this)" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">订单状态:</span>
                                    			<select name="orderStatus" id="orderStatus" class="form-control">
                                    				<option value="">...请选择...</option>
                                    			</select>
                                    		</label>
                                    	</div>
                                   	</div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:</span>
                                    			<select name="orderCheckedProvinces" id="orderCheckedProvinces" onclick="setSelCity('orderCheckedProvinces','orderCheckedCitys')" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
                                    		<label><span style="font-weight:bold;">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</span>
                                    			<select name="orderCheckedCitys" id="orderCheckedCitys" class="form-control">
                                    				<option value="">...请选择...</option>
                                    			</select>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-4">
	                                    	<label><span style="font-weight:bold;">支付状态:</span>
	                                    			<select name="orderPay" id="orderPay" class="form-control">
	                                    				<option value="">...请选择...</option>
	                                    				<option value="20110001">未支付</option>
	                                    				<option value="20110002">部分支付</option>
	                                    				<option value="20110003">支付完成</option>
	                                    				<option value="20130003">确认有效</option>
	                                    				<option value="20130002">确认无效</option>
	                                    				<option value="20130008">退款成功</option>
	                                    			</select>
	                                    		</label>
                                    	</div>
                                    </div>
                                    
                                 <div class="row">
                        			<div class="form-group col-xs-4">
										<label><span style="font-weight:bold;">订单渠道:</span>
                                    			<select name="orderChannel" id="orderChannel" class="form-control"     style="width:220px;"/>
                                    		</label>
                        			</div>
                                    <div class="form-group col-xs-4">
										<label><span style="font-weight:bold;">负责部门:</span>
										<select  id="mendianSelect" class="form-control"  style="width:220px;">
										<option value="-1">...请选择...</option>
										</select>
	                        			</label>
                        			</div>
                        			 <div class="form-group col-xs-4">
										<label><span style="font-weight:bold;">负责人&nbsp;&nbsp;&nbsp;&nbsp;:</span>
										<select id="guanjiaSelect" class="form-control"  >
										<option value="">...请选择...</option>
						  				</select>
	                        			</label>
                        			</div>
										
								</div>
								 <div class="row" style="display:none" id="birthTimeDiv">
                                    	<div class="form-group  col-xs-8">
                                    		<label><p>宝宝出生日期:</p>
                                    		<input id="birthTimeOrder" class="Wdate form-control" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
		                                    </label>
		                                    <label><span style="font-weight:bold;">至</span>
		                                    <input id="birthTimeOrderEnd" class="Wdate form-control" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                                    		</label>
                                    	</div>
                                    </div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-8">
                                    		<label><p>创建时间:</p>
                                    			<input id="createTimeOrder" class="Wdate form-control" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                    		</label>
                                    		<label><span style="font-weight:bold;">至</span>
		                                    	<input id="createTimeEnd" class="Wdate form-control" type="text" onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                    		</label>
                                    	</div>
                                    	<div class="form-group col-xs-4">
                                       		<button type="button" class="btn btn-sm btn-default" onclick="queryChannelOrderByLike(0,10);">
                                       		<em class="glyphicon glyphicon-search"></em>查询</button>&nbsp;&nbsp;
                                        	<button type="reset" class="btn btn-sm btn-default">
                                        	<em class="glyphicon glyphicon-refresh"></em>重置</button>
                                        </div>
                                    </div>
                                   
                                    </form>
                                </div>
                            </div>
                            <div class="panel-heading panel-new">
                            <auth:menu menuId="1012">
                            	<button class="xinzeng btn btn-sm btn-default" type="button" onclick="openModule('/order/jsp/orderUser.jsp',{},{},{},'orderUser')">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>新增
                                </button> 
                            </auth:menu>
                            </div>
                                
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" id="tablecode">
                                    <thead>
                                    <tr>
                                    	<th>序号</th>
                                    	<th>订单编号</th>
										<th>客户姓名</th>
										<th>订单类型</th>
										<th>订单状态</th>
										<th>支付状态</th>
										<th>创建时间</th>
										<th>负责人</th>
										<th>负责部门</th>
										<th>录入人</th>
										<th>录入部门</th>
										<th>订单渠道</th>
										<th>订单来源</th>
                                    </tr>
                                    </thead>
                                    <tbody id="listBodyOrder" style="width:100%">
                                    </tbody>
                                </table>
                            </div>
                            <ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
                            </tbody>
                        </div>
                    </div>
                    </div>
              <!-- /.col-lg-4 -->
            <div class="col-lg-4">
	        	<div class="aside-tab" id="rightTags" >
		           	<input type="hidden" id="checkedOrderId" name="checkedOrderId" >
		           	<input type="hidden" id="checkedUserMobile" name="checkedUserMobile">
		           	<input type="hidden" id="checkedOrderType" name="checkedOrderType">
		           	<input type="hidden" id="checkedCateType" name="checkedCateType">
		           	<input type="hidden" id="checkedUserId" name="checkedUserId">
		           	<input type="hidden" id="checkedCity" name="checkedCity">
		           	<input type="hidden" id="checkedCityName" name="checkedCityName">
		           	<input type="hidden" id="checkedTotalPay" name="checkedTotalPay">
		           	<input type="hidden" id="checkedPayStatus" name="checkedPayStatus">
		           	<input type="hidden" id="checkedOrderStatus" name="checkedOrderStatus">
		           	<input type="hidden" id="checkedLoginByOrNot" name="checkedLoginByOrNot">
		           	<input type="hidden" id="checkedLoginRechargeOrNot" name="checkedLoginRechargeOrNot">
		           	<input type="hidden" id="checkedRechargeBy" name="checkedRechargeBy">
		           	<input type="hidden" id="checkedLoginBy" name="checkedLoginBy">
		           	<input type="hidden" id="checkedOrderChannel" name="checkedOrderChannel" >
		           	<input type="hidden" id="channelManagerType" name="channelManagerType" >
			           	<div id="orderBasicServerCont" style="display:none;">
			           		<%@ include file="../orderBasicServer.jsp" %>
			           	</div>
			           	<div  id="orderBasicServerOne" style="display:none;">
			           		<%@ include file="../orderBasicServerOne.jsp" %>
			           	</div>
			           	<div  id="orderBasicItem" style="display:none;">
			           		<%@ include file="../orderBasicItem.jsp" %>
			           	</div> 
			           	<div  id="orderBasicServerThree" style="display:none;">
			           		<%@ include file="../orderBasicServerThree.jsp" %>
			           	</div> 
			           	<div  id="orderBasic" style="display:none;">
			           		<%@ include file="../orderBasic.jsp" %>
			           	</div> 
	        	</div>
	        	
           	</div>
    </div>
	<script type="text/javascript">
	$(function() {
		//查询当前渠道经理类型
		queryChannelManagerType();
		//查询订单类型
		queryServerType("1002",12,"serverType");
		//查询订单来源
		queryBaseDictionary("2018",8,"orderSourceId");
		//查询渠道
		queryOrderChannel("orderChannel");
		//查询省
		setSelProvinceCitys("101",6,"orderCheckedProvinces");
		//查询负责部门
		selectOrderDeptName("mendianSelect");
		//查询渠道经理订单列表
		queryOrders(0, 10);
		//订单基本信息
		checkChannelOrderBasic($("#checkedOrderId").val());
		 /*loading*/
	    $('#shclDefault').shCircleLoader();
	    tabx(".tab-item",".main");
	    /*表单合并*/
	    drop(".drop-on",".drop-down",".info-select");
	 	 //删除或隐藏右边栏所有按钮
	    $("#rightTags li").on('click',function(){
	    	attrDisabledInput();
	    	//延续性订单页面特殊处理
	    	var channelManagerType = $("#channelManagerType").val();
			if(channelManagerType == 1){
		    	if($(this).prop("id") == 'jiaofei'){
		    		$("#fees").css("display","block");
		    		$("#basicCont").css("display","none");
		    	}
		    	if($(this).prop("id") == 'basicInformation'){
		    		$("#fees").css("display","none");
		    		$("#basicCont").css("display","block");
		    	}
			}
	    });
	})
	
	/**设置右边栏显示基本信息和缴费标签*/
	function attrDisabledTab(){
			var mytabs = $("#rightTags ul.mytabs");
			var tabContent = $("#rightTags .tab-content");
			//只显示基本信息和缴费标签
			mytabs.each(function(i){
				$(this).find("li:not(:first,#jiaofei,#serverBasicOne3,#itemBasic2,#serverBasicThree2)").css("display","none");
			})
			tabContent.each(function(i){
				$(this).children("div:not(:first,#fees,#feesServerOne,#feesItem,#feesServerThree)").css("display","none");
			})
	}
	
	/**设置右边栏表单元素*/
	function attrDisabledInput(){
		$("#rightTags :button").remove();
		$("#rightTags [type=checkbox],[type=radio]").prop("disabled",true);
	}
	
	/**设置右边栏默认显示标签*/
	function attrCheckedTab(){
		var mytabs = $("#rightTags ul.mytabs");
		var tabContent = $("#rightTags .tab-content");
		//只显示基本信息和缴费标签
		mytabs.each(function(i){
			$(this).find("li").removeClass("tab-active");
			$(this).find("li:first").addClass("tab-active");
		})
		tabContent.each(function(i){
			$(this).children("div").removeClass("tab-selected").css("display","none");
			$(this).children("div:first").addClass("tab-selected").css("display","block");
		})
	}
	
	/**查询当前渠道经理类型*/
	function queryChannelManagerType(){
		var ctx = $("#ctx").val();
		$.ajax({
			url : ctx + "/order/queryChannelManagerType",
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == "00") {
					var channelManagerType = data.channelManagerType; 
					if(channelManagerType == 1 || channelManagerType == null || channelManagerType == "" ){
						//合作方渠道经理
						attrDisabledTab();
					}
					$("#channelManagerType").val(data.channelManagerType?data.channelManagerType:"");
				}else{
					attrDisabledTab();
				}
			}
		})
		attrDisabledInput();
	}
	
	/**查询订单列表*/
	function queryOrders(num, pageCount) {
		var ctx = $("#ctx").val();
		var data = {};
		data.curPage = num;
		data.pageCount = pageCount;
		data.cateTypeNot = "5,6,7,8";
		$.ajax({
			url : ctx + "/order/queryChannelOrder",
			type : "post",
			data:data,
			dataType : "json",
			async : false,
			success : function(data) {
				showMsg(data.msg);
				$("#pageInfoDiv").empty().pagination(data.page,listBodyChannelOrder);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				var html = "";
				if (data.msg == "00") {
					var num = $.each(data.list,function(i, v) {
					if (i == 0) {
						$("#checkedOrderId").val(v.id);
						$("#checkedLoginBy").val(v.loginBy);
						}
							num = i + 1;
							html +="<tr onclick=\"checkChannelOrderBasic(" +v.id +")\">" ;
							html +="<td>"+(total + num - pageCount)+"</td>";
							html +="<td>"+v.orderCode+"</td>";
							html +="<td>"+v.userName+"</td>";
							html +="<td>"+v.typeText+"</td>";
							html +="<td>"+v.statusText+"</td>";
							html +="<td>"+v.payText+"</td>";
							html +="<td>"+numberToDatestr(v.createTime,12)+"</td>";
							html +="<td>"+v.rechargeByText+"</td>";
							html +="<td>"+v.rechargeDeptText+"</td>";
							html +="<td>"+v.createByText+"</td>";
							html +="<td>"+v.createDeptText+"</td>";
							html +="<td>"+v.channelText+"</td>";
							html +="<td>"+v.sourceText+"</td>";
							html +="</tr>";
					})
			}else{
				html="<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
				checkChannelOrderBasic("");
		   }
			$("#listBodyOrder").html(html);
			if (data.msg =="00") { 
				trColorOrder("#listBodyOrder tr");
				var orderId = $("#checkedOrderId").val();
				checkChannelOrderBasic(orderId);
				$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
			}
		}
	});
}
	
	 //分页跳转使用
	 function listBodyChannelOrder(num,pageCount) {
		$("#listBodyOrder").empty();
		queryChannelOrderByLike(num,pageCount);
	}
	
	//模糊查询
	function queryChannelOrderByLike(num,pageCount){
		var ctx = $("#ctx").val();
		var orderCode=$("#orderCode").val();
		var userName=$("#userNameOrder").val();
		var createTime = $("#createTimeOrder").val();
		var createTimeEnd = $("#createTimeEnd").val();
		var userMobile=$("#userMobileOrder").val();
		var serverType=$("#serverType").val();
		var orderType = serverType.split("-")[0];
		var cateType = serverType.split("-")[1];
		var orderStatus=$("#orderStatus").val();
		var orderSourceId=$("#orderSourceId").val();
		var orderChannel=$("#orderChannel").val();
		var critical=$("#critical").val();
		var orderGroupid=$("#orderGroupid").val();
		var managerId=$("#managerId").val();
		var city = $("#orderCheckedCitys").val();
		var mendian=$("#mendianSelect").find("option:selected").val();
		var rechargeByGuan=$("#guanjiaSelect").find("option:selected").val();
		var orderPay=$("#orderPay").val();
		var birthTimeOrder=$("#birthTimeOrder").val();
		var birthTimeOrderEnd=$("#birthTimeOrderEnd").val();
		var rechargeDept = $("#mendianSelect").val();
		if(mendian==null||mendian==""){
			rechargeByGuan=null;
		}
		if(city==null||city=="" || city==-1){
			city = $("#orderCheckedProvinces").val();
		}
		$.ajax({
			url: ctx +"/order/queryChannelOrder?curPage="+num+"&pageCount="+pageCount,
			data:{
				orderCode:orderCode,
				userName:userName,
				createTime:createTime,
				createTimeEnd:createTimeEnd,
				userMobile:userMobile,
				cateType:cateType,
				orderType:orderType,
				orderStatus:orderStatus,
				orderSourceId:orderSourceId,
				orderChannel:orderChannel,
				critical:critical,
				orderGroupid:orderGroupid,
				managerId:managerId,
				city:city,
				cateTypeNot:"5,6,7,8",
				threeOrderItemId:rechargeByGuan,
				payText :orderPay,
				birthTimeOrder : birthTimeOrder ,
				birthTimeOrderEnd : birthTimeOrderEnd,
				rechargeDept:rechargeDept
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				showMsg(data.msg);
				var html="";
				$("#pageInfoDiv").empty().pagination(data.page,listBodyChannelOrder);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				if (data.msg =="00") {
					var num = 
						$.each(data.list,function(i,v){
							if(i==0){
								$("#checkedOrderId").val(v.id);
								$("#checkedLoginBy").val(v.loginBy);
							}
							num = i + 1;
							html +="<tr onclick='checkChannelOrderBasic(" +v.id +")'>" ;
							html +="<td>"+(total + num - pageCount)+"</td>";
							html +="<td>"+v.orderCode+"</td>";
							html +="<td>"+v.userName+"</td>";
							html +="<td>"+v.typeText+"</td>";
							html +="<td>"+v.statusText+"</td>";
							html +="<td>"+v.payText+"</td>";
							html +="<td>"+numberToDatestr(v.createTime,12)+"</td>";
							html +="<td>"+v.rechargeByText+"</td>";
							html +="<td>"+v.rechargeDeptText+"</td>";
							html +="<td>"+v.createByText+"</td>";
							html +="<td>"+v.createDeptText+"</td>";
							html +="<td>"+v.channelText+"</td>";
							html +="<td>"+v.sourceText+"</td>";
							html +="</tr>";
						})
				}else{
					html="<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
					checkChannelOrderBasic("");
			   }
				$("#listBodyOrder").html(html);
				if (data.msg =="00") { 
					trColorOrder("#listBodyOrder tr");
					var orderId = $("#checkedOrderId").val();
					checkChannelOrderBasic(orderId);
					$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
				}
			}
		});
	}
	
	//查询渠道
	function queryOrderChannel(tagId){
	    var ctx=$("#ctx").val();
	    var html = "<option style='color:blue;' value='' >...请选择...</option>";
		$.ajax({
			url:ctx+"/orderbase/queryOrderChannel",
			data:null,
			type:'post',
			async:false,
			dataType : "json",
			success:function(data){
				$.each(data.list,function(i,v){
					html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
				})
			}
		})
		$("#"+tagId).html(html).checkSelect();
	}
	
	/**
	 * 查询渠道
	 * 条件：是否启用、管家可见、业务类型为订单和通用
	 * @param tagId 下拉框标签id 必选
	 * @param data 参数 可选
	 */
	function queryChannels(tagId,data){
	    var ctx=$("#ctx").val();
	    if(tagId){
	    	$.ajax({
	    		url:ctx+"/orderbase/queryChannels",
	    		data:data,
	    		type:'post',
	    		async:false,
	    		dataType : "JSON",
	    		success:function(data){
	    			var html = "<option style='color:blue;' value='' >---请选择---</option>";
	    			if(data.msg == "00"){
	    				$.each(data.list,function(i,v){
	    					html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
	    				})
	    			}
	    			$("#"+tagId).html(html).checkSelect();
	    		}
	    	})
	    }
	}
	
		//选择订单类型
		function checkedChannelOrderStatusSelect(status) {
			var values = status.options[status.options.selectedIndex].value.split("-");
			//当订单类型为月嫂或者育婴师,显示宝宝出生日期条件
			if (values[0] == 100200010001 || values[0] == 100200010002) {
				$("#birthTimeDiv").css("display", "block")
			} else {
				$("#birthTimeDiv").css("display", "none")
			}
			var html = "<option style='color:blue;' value='' >...请选择...</option>";
			//1 新建,2已受理,3匹配中, 4已匹配, 5待面试, 6面试成功, 7已签约, 8已上户, 9已完成, 10已取消, 11已下户,12已终止,13捡货中, 14配送中 16 三方订单匹配失败
			if (values[1] == 1) {
				html += "<option value='1' >新建</option>";
				html += "<option value='2' >已受理</option>";
				html += "<option value='3' >匹配中</option>";
				html += "<option value='4' >已匹配</option>";
				html += "<option value='8' >已上户</option>";
				html += "<option value='11' >已下户</option>";
				html += "<option value='9' >已完成</option>";
				html += "<option value='10' >已取消</option>";
			} else if (values[1] == 2) {
				html += "<option value='1' >新建</option>";
				html += "<option value='2' >已受理</option>";
				html += "<option value='3' >匹配中</option>";
				html += "<option value='4' >已匹配</option>";
				html += "<option value='5' >待面试</option>";
				html += "<option value='6' >面试成功</option>";
				html += "<option value='7' >已签约</option>";
				html += "<option value='8' >已上户</option>";
				html += "<option value='9' >已完成</option>";
				html += "<option value='10' >已取消</option>";
				html += "<option value='11' >已下户</option>";
				html += "<option value='12' >已终止</option>";
			} else if (values[1] == 3) {
				html += "<option value='1'>新建</option>";
				html += "<option value='18'>待受理</option>";
				html += "<option value='2'>已受理</option>";
				html += "<option value='13'>捡货中</option>";
				html += "<option value='14'>配送中 </option>";
				html += "<option value='9'>已完成</option>";
				html += "<option value='10'>已取消</option>";
			} else {
				html += "<option value='18' >待受理</option>";
				html += "<option value='2' >已受理</option>";
				html += "<option value='9' >已完成</option>";
			}
			$("#orderStatus").html(html);
		}
	
	 /**选择订单*/	
	 function checkChannelOrderBasic(orderId){
		var ctx = $("#ctx").val();
		if(orderId){
			$.ajax({
				url: ctx +"/order/loadOrderByOrder",
				data:{"id":orderId},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg =="00") {
						$("#checkedOrderId").val(data.order.id);
						$("#checkedOrderType").val(data.order.orderType);
						$("#checkedCateType").val(data.order.cateType);
						$("#checkedUserMobile").val(data.order.userMobile);
						$("#checkedUserId").val(data.order.userId);
						$("#checkedCity").val(data.order.city);
						$("#checkedCityName").val(data.order.cityName);
						$("#checkedTotalPay").val(data.order.totalPay);
						$("#checkedPayStatus").val(data.order.payStatus);
						$("#checkedOrderStatus").val(data.order.orderStatus);
						$("#checkedLoginByOrNot").val(data.order.loginByOrNot);
						$("#checkedLoginRechargeOrNot").val(data.order.loginRechargeOrNot);
						$("#checkedRechargeBy").val(data.order.rechargeBy);
						$("#checkedLoginBy").val(data.order.loginBy);
						$("#checkedOrderChannel").val(data.order.orderChannel);
						var orderId = $("#checkedOrderId").val();
						var orderType = $("#checkedOrderType").val();
						var cateType = $("#checkedCateType").val();
						var userMobile = $("#checkedUserMobile").val();
						var userId = $("#checkedUserId").val();
						var city = $("#checkedCity").val();
						var cityName = $("#checkedCityName").val();
						var totalPay = $("#checkedTotalPay").val();
						var payStatus = $("#checkedPayStatus").val();
						var orderStatus = $("#checkedOrderStatus").val();
						var loginByOrNot = $("#checkedLoginByOrNot").val();
						var loginRechargeOrNot = $("#checkedLoginRechargeOrNot").val();
						var rechargeBy = $("#checkedRechargeBy").val();
						var loginBy = $("#checkedLoginBy").val();
						if(cateType==1){
								$("#orderBasicServerOne").css("display","block");
								$("#orderBasicServerCont").css("display","none");
								$("#orderBasicItem").css("display","none");
								$("#orderBasicServerThree").css("display","none");
								$("#orderBasic").css("display","none");
								setBasicServerOne(orderId,cateType,totalPay,orderStatus,rechargeBy);
						}else if(cateType==2){
								$("#orderBasicServerOne").css("display","none");
								$("#orderBasicServerCont").css("display","block");
								$("#orderBasicItem").css("display","none");
								$("#orderBasicServerThree").css("display","none");
								$("#orderBasic").css("display","none");
								setBasicServer(orderId,cateType,totalPay,orderStatus,rechargeBy);
						}else if(cateType==3){
								$("#orderBasicServerOne").css("display","none");
								$("#orderBasicServerCont").css("display","none");
								$("#orderBasicItem").css("display","block");
								$("#orderBasicServerThree").css("display","none");
								$("#orderBasic").css("display","none");
								setBasicItem(orderId,userMobile,cateType,totalPay,rechargeBy);
						}else{
								$("#orderBasicServerOne").css("display","none");
								$("#orderBasicServerCont").css("display","none");
								$("#orderBasicItem").css("display","none");
								$("#orderBasicServerThree").css("display","block");
								$("#orderBasic").css("display","none");
								if(orderId!=null&&orderId!="" && cateType!=null&&cateType!=""){
									setBasicServerThree(orderId,cateType,totalPay,orderStatus,rechargeBy);
							}
						}
					}
				}
			})
		}else{
			$("#orderBasicServerOne").css("display","none");
			$("#orderBasicServerCont").css("display","none");
			$("#orderBasicItem").css("display","none");
			$("#orderBasicServerThree").css("display","none");
			$("#orderBasic").css("display","block");
		}
		//当前登录人是负责人显示客户相关电话
		if($("#checkedRechargeBy").val() ==$("#checkedLoginBy").val()){
			$("#rightTags span[id^=mobileBasic],span[id^=receiverMobile]").css("display","block");
		}else{
			$("#rightTags span[id^=mobileBasic],span[id^=receiverMobile]").css("display","none");
		}
		var channelManagerType = $("#channelManagerType").val();
		if(channelManagerType == 1){
			attrDisabledTab();
			attrCheckedTab();
		}
			attrDisabledInput();
	} 
	 
	
	/*表格点击行高亮*/
	function trColorOrder(trli){
	    $(trli).on("click",function(){
	        $(this).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
	    })
	
	}
	//加载管家
	function selguanjia(){
		var ctx=$("#ctx").val();
		var  deptId = $("#mendianSelect option:selected").val();
		var guanjiaSelectval=$("#guanjiaSelect").find("option:selected").val();
		if(deptId==""||deptId==null){
			$("#guanjiaSelect").empty();
	    	$("#guanjiaSelect").html("<option>...请选择...</option>");
		}else{
			$.ajax({
				url:ctx+"/orderbase/queryguanjia",
				data : {
					deptId : deptId
				},
				type : 'post',
				async : false,
				dataType : "json",
				success : function(data) {
					var html = "";
					html +="<option style='color:blue;' value='' >...请选择...</option>";
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							html += "<option   value='" + v.id + "'>"+v.realName+"</option>";
						});
						
					} else if(data.msg== "02"){
						$.alert({millis:2000,text:"门店无管家!"});
					}else{
						$.alert({millis:2000,text:"查询出错，请稍后再试!"});
					}
					$("#guanjiaSelect").html(html);
				}
			});
		}
		
	}
	
	
	/*查询回访记录  */
	function queryReturn(orderId,returnInfoOne){
		var ctx = $("#ctx").val();
			$.ajax({
				url: ctx +"/recorder/queryRecorder",
				data:{
					orderId : orderId
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg=="00") {
						var html = "" ;
						var num = $.each(data.list,function(i,v){
							var checked = "" ;
							html += "<div class='row'>";
							html += "<div class='col-xs-6'>";
							html += "<input type='hidden' value='"+v.recorder+"' id='recorder'>";
							html += "<input data-recorder='"+v.recorder+"' type='radio' " +checked +" name='returnrecord_id' value='" +v.id +"' ${(state=='" +v.id +"')?'checked' : ''}>" ;
							html += "回访记录人：<span id='accountId" +v.id +"'>" +v.realname +"</span>";
							html += "</div><div class='col-xs-6'>";
							html += "<span>记录时间：</span><span>" +numberToDatestr(v.createTime,14) +"</span>";
							html += "</div></div>";
							html += "<div class='row'>";
							html += "<div class='col-xs-6'>";
							html += "<span>服务满意度：</span><span>" +v.satis +"</span>";
							html += "</div>";
							html += "<div class='col-xs-6'>";
							html += "<span>销售意向：</span><span>" +v.threeclass+"-"+v.mean +"</span>";	
							html += "</div></div>" ;
							html += "<div class='row'>";
							html += "<div class='col-xs-12'>";
							html += "<span>下次回访时间：</span><span>" +v.nextTime+"</span>";
							html += "</div></div>" ;
							html += "<div class='row'>";
							html += "<div class='col-xs-12'>";
							html += "<span>备注：</span><span>" +v.remark +"</span>";
							html += "</div></div></hr>";
							
							$("#"+returnInfoOne).html(html);
						})
						$("#"+returnInfoOne).find("input[type=radio]").eq(0).prop("checked",true);	
					}else{
						$("#"+returnInfoOne).html("");
					}
				}
			});
			
	}
	

	function createOrderTest(){
		var ctx = $("#ctx").val();
		$.ajax({
			url:ctx +"/order/createOrderTest",
			sync:false,
			success:function(data){
				if(data.msg ="00"){
					var ary = data.num.split(",");
					alert("本次本生成订单编号："+ary[0] +"条，耗时：" +ary[1] +"秒.");
				}
			}
		});
	}

	// 订单转单
	function openOrderFollow(){
		var orderId = $("#checkedOrderId").val();
		if(orderId==null || orderId==""){
			$.alert({text:"请选择需要转单的订单！"});
			return ;
		}
		openModule('/order/jsp/orderFollow.jsp',{'id':orderId},{},{},'orderFollow');
	}

	//查看服务人员详细信息
	function checkPersons(id){
		openModule("${ctx}/jsp/server/orderNeedsPerson.jsp",{"id":id},'','','checkPersonsModle') ;
	}
	
	//较验当前是否可以匹配人员和推送需求
	function queryNeedPersons(type){
		var ctx = $("#ctx").val();
		var orderId = $("#checkedOrderId").val();
		var n = 0;
		$.ajax({
			url: ctx +"/itemInterview/queryNeedPersons",
			data:{
				orderId:orderId,
				type:type
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					n = data.list.length;
				}
			}
		})
		return n;
	}


	function editUserHref(){
		var cust_phone=$("#checkedUserMobile").val();
		var cust_id=$("#checkedUserId").val();
		var ctx = $("#ctx").val();
		var url = "http://erp.95081.com/catchUsersData?cust_id=" +cust_id ;
		window.open(url);
		
	}

	/*表格点击行高亮*/
	function trColorOrder(trli){
	    $(trli).on("click",function(){
	        $(this).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
	    })

	}
	
	/**
	 * 查询转单日志列表
	 */
	function queryOrderTurnLog(orderId,tagId){
		if(!tagId || !orderId){
			return;
		}
		var $tid = $("#"+tagId);
		$tid.empty();
		$.ajax({
			url:ctx+"/orderTurnLog/queryOrderTurnLog",
			data:{"orderId":orderId},
			type:"post",
			async:false,
			dataType:"json",
			success:function(data){
				if(data.msg == "00"){
					var html = "";
					$.each(data.list,function(i,v){
						html += "<tr>";
						html += "<td>"+(i+1)+"</td>";
						html += "<td>"+v.createTime+"</td>";
						html += "<td>"+v.createByText+"</td>";
						html += "<td>"+v.createDeptText+"</td>";
						html += "<td>"+v.turnByText+"</td>";
						html += "<td>"+v.turnDeptText+"</td>";
						html += "<td>"+v.rechargeByText+"</td>";
						html += "<td>"+v.rechargeDeptText+"</td>";
						html += "<td></td>";
						html += "</tr>";
					})
						$tid.append(html);
				}else if(data.msg == "02"){
					$tid.append("<tr><td colspan='9'>暂无数据</td></tr>")
				}else{
					$tid.append("<tr><td colspan='9'>查询异常</td></tr>")
				}
				trColorOrder("#"+tagId+" tr");
			}
		})
	}
	
	
	function exportOrders(){
		var ctx = $("#ctx").val();
		var orderCode=$("#orderCode").val();
		var userName=$("#userNameOrder").val();
		var createTime = $("#createTimeOrder").val();
		var createTimeEnd = $("#createTimeEnd").val();
		var userMobile=$("#userMobileOrder").val();
		var serverType=$("#serverType").val();
		var orderType = serverType.split("-")[0];
		var cateType = serverType.split("-")[1];
		var orderStatus=$("#orderStatus").val();
		var orderSourceId=$("#orderSourceId").val();
		var orderChannel=$("#orderChannel").val();
		var critical=$("#critical").val();
		var orderGroupid=$("#orderGroupid").val();
		var managerId=$("#managerId").val();
		var city = $("#orderCheckedCitys").val();
		var mendian=$("#mendianSelect").find("option:selected").val();
		var rechargeByGuan=$("#guanjiaSelect").find("option:selected").val();
		var orderPay=$("#orderPay").val();
		var birthTimeOrder=$("#birthTimeOrder").val();
		var birthTimeOrderEnd=$("#birthTimeOrderEnd").val();
		if(orderType==100200010001||orderType==100200010002){
		$.ajax({
			type:"post",
			url:ctx+"/order/exportExcel",
			data : {
				 orderCode:orderCode,
				userName:userName,
				createTime:createTime,
				createTimeEnd:createTimeEnd,
				userMobile:userMobile,
				cateType:cateType, 
				orderStatus:orderStatus,
				orderSourceId:orderSourceId,
				orderChannel:orderChannel,
				critical:critical,
				orderGroupid:orderGroupid,
				managerId:managerId,
				city:city,
				cateTypeNot:"5,6,7,8",
				threeOrderItemId:rechargeByGuan,
				payText :orderPay,
				orderType:orderType,
				birthTimeOrder : birthTimeOrder ,
				birthTimeOrderEnd : birthTimeOrderEnd
			},
			dataType:"json",
			type:"POST",
			success:function(data){
				window.open(ctx + "/order/downExcel?filename="+data.a, "_self");
			},
				error:function(){
					$.alert({text:"失败！"});
			}		
		});
			
		}else{
			$.alert({text:"请选择订单类型为月嫂/育婴师的订单！！！！"});
			return;
		}
		
	}
	
	//修改回访记录 
	function updatereturnrecord(){
		var ctx = $("#ctx").val();
		orderId=parent.$("#checkedOrderId").val()
		var record  = $("input[name='returnrecord_id']:checked").attr("data-recorder");
		RechargeBy=parent.$("#checkedRechargeBy").val()
		var recorder = $("input[name='returnrecord_id']:checked").val();
		var customId = parent.$("#checkedUserId").val();//客户id
		if(record==RechargeBy){
			openModule(ctx + "/jsp/returnrecord/editReturn.jsp",{orderId:orderId,record:recorder,customId:customId},{},{},"orderReturn");
		}else{
			$.alert({
				text : "请修改自己的回访记录"
			});
			return;
		}
	}
	
	</script>
</body>
</html>

