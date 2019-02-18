<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/js/order_three/ajaxfileupload.js"></script>
	<script src="${ctx}/js/order_three/orderBaseThree.js"></script>
	<script src="${ctx}/js/orderBase.js"></script>
	<style>
		.orderlisttable tr td{text-align: left}
		.feesOnediv{margin:20px auto auto 15px}
		
		.excelFileDiv{ position:relative;width:340px} 
		.excelFilebtn{ height:30px; width:90px;} 
		.excelFile{ position:absolute; top:0; left:151px; height:28px; filter:alpha(opacity:0);opacity: 0;width:105px; cursor: pointer;} 
	</style>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12 -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">订单管理系统</a></li>
                <li><a href="#">三方订单管理</a></li>
                <li class="active">渠道订单管理</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
            <div class="col-lg-8">
				<div class="panel panel-default">
					<!-- /.panel-heading -->
					<div class="panel-body">
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
                                        
                                        	<input type="hidden" name="orderSourceId" id="orderSourceId" value="20180007"/>
									<div class="row">
			                        <div class="form-group col-xs-6">
			                            <label>
			                                <p>订单ID</p>
			                               <input type="text" class="form-control inputElem"  name="orderCode" id="orderCode"/>
			                            </label>
			                        </div>
			                          <div class="form-group col-xs-6">
			                            <label>
			                                <p>客户姓名</p>
			                               <input type="text"  class="form-control inputElem"  name="userName" id="userNameOrder"/>
			                            </label>
			                        </div>
			                        </div>
			                        <div class="row ">
			                         <div class="form-group col-xs-6">
			                            <label >
			                                <p>订单分类</p>
			                                <select class="form-control" name="serverType" id="serverType">
			                                	
			                                </select>
			                            </label>
			                        </div>
			                        <div class="form-group col-xs-6">
			                            <label >
			                                <p>订单状态</p>
			                                <select class="form-control" name="orderStatus" id="orderStatus">
			                              	  <option style='color:blue;' value=''>...请选择...</option>
												<option value='1'>新单</option>
												<option value='2'>已受理</option>
												<option value='3'>匹配中</option>
												<option value='4'>已匹配</option>
												<option value='5'>待面试</option>
												<option value='6'>面试成功</option>
												<option value='7'>已签约</option>
												<option value='8'>已上户</option>
												<option value='9'>已完成</option>
												<option value='10'>已取消</option>
												<option value='11'>已下户</option>
												<option value='12'>已终止</option>
												<option value='13'>捡货中</option>
												<option value='14'>配送中</option>
			                                </select>
			                            </label>
			                        </div>
			                        </div>
			                         <div class="row ">
			                          <div class="form-group col-xs-6">
			                            <label >
			                                <p>订单渠道</p>
			                                <select class="form-control" name="orderChannel" id="orderChannel">
			                                
			                                </select>
			                            </label>
			                        </div>
			                         <div class="form-group col-xs-6">
			                            <label >
			                                <p>所属管家</p>
			                               <input type="text" class="form-control inputElem"  name="managerId" id="managerId"/>
			                            </label>
			                        </div>
			                         </div> 
			                          <div class="row ">
			                          <div class="form-group col-xs-6">
			                            <label >
			                                <p>省</p>
			                                <select class="form-control" name="orderCheckedProvinces" id="orderCheckedProvinces" onchange="serThreeOrderCitys()">
			                                
			                                </select>
			                            </label>
			                        </div>
			                          <div class="form-group col-xs-6">
			                            <label >
			                                <p>城市</p>
			                                <select class="form-control" name="orderCheckedCitys" id="orderCheckedCitys">
			                               		<option value="">...请选择...</option>
			                                </select>
			                            </label>
			                        </div>
			                         </div>
			                           <div class="row">
			                         <div class="form-group col-xs-12">
			                            <label >
			                                <p>客户电话</p>
			                               <input type="text" class="form-control inputElem"  name="userMobile" id="userMobileOrder"/>
			                            </label>
			                        </div>
			                        </div>
			                         <div class="row">
			                         <div class="form-group col-xs-11">
			                            <label>
			                                <p>创建时间</p>
			                             <input id="createTimeOrder" class="Wdate  form-control" type="text"
                                    onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'createTimeEnd\')||\'%y-%M-{%d}\'}' ,dateFmt:'yyyy-MM-dd'})" />
                                            &nbsp;至&nbsp;
                                    		<input id="createTimeEnd" class="Wdate form-control" type="text"
                                  			  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'createTimeOrder\')}',maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
			                            </label>
			                        </div>
			                         <div class="form-group col-xs-1">
			                         	<button class="btn btn-sm btn-default fr mr20"  onclick="queryOrdersByLike(0,10);" type="button">
										<em class="glyphicon glyphicon-search"></em>
									查询
									</button>
			                         </div>
			                         </div>
                                    </form>
                                </div>
                            </div>
                            <div class="panel-heading panel-new">
                                 <button class="xinzeng btn btn-sm btn-default" type="button" onclick="openModule('/order/jsp/order_three/in/customer_operator/orderThreeUser.jsp',{},{},{},'orderThreeCustomerList')">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>新增</button>   
<!--                                     <button class="xinzeng btn" type="button" onclick="createOrderTest()"> -->
<!--                                     	<em class="add glyphicon glyphicon-plus-sign"></em>手动订单号生成</button> -->
                           		 	<button class="xinzeng btn btn-sm btn-default" type="button" onclick="exportExcel()">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>导出订单</button>  
                           		 	<div class="excelFileDiv">
                           		 	<button class="xinzeng btn btn-sm btn-default excelFilebtn" type="button">
                           		 	<em class="add glyphicon glyphicon-plus-sign "></em>导入订单</button>  
                           		 	<input type="file" name="excelFile" class="excelFile" id="excelFile" onchange="uploadFile()" />
                           		 	</div>
                           		 	<button class="xinzeng btn btn-sm btn-default" type="button" onclick="downloadTemplate()">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>下载模板</button> 
                                 	<button class="xinzeng btn btn-sm btn-default" type="button" onclick="downloadRecord()">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>导入记录</button>   
                            </div>
                               <form action="${ctx }/threeOrderIn/exportToExcel" method="post" id="theForm">
                               	<input type="hidden"  name="orderCode"/>
                                <input type="hidden"  name="userName"/>
                                <input type="hidden"  name="createTime"/>
                                <input type="hidden"  name="createTimeEnd"/>
                                <input type="hidden"  name="userMobileOrder"/>
                                <input type="hidden"  name="serverType"/>
                                <input type="hidden"  name="orderStatus"/>
                                <input type="hidden"  name="orderSourceId"/>
                                <input type="hidden"  name="orderChannel"/>
                               </form>
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th width="6%"><input name="odIds" id="odIds" onclick="checkboxAll(this)" type="checkbox"></th>
										<th width="6%">序号</th>
										<th width="10%">订单ID</th>
										<th width="10%">客户姓名</th>
										<th width="16%">联系方式</th>
										<th width="10%">订单分类</th>
										<th width="16%">创建时间</th>
										<th width="16%">订单状态</th>
										<th width="10%">订单渠道</th>
										<th width="6%">分包人</th>
										<th width="5%">分包部门</th>
										<th width="5%">负责人</th>
										<th width="5%">负责人部门</th>
										<th width="5%">开发人</th>
										<th width="5%">开发人部门</th>
										<th width="5%">分包时间</th>
                                    </tr>
                                    </thead>
                                    <tbody id="listBodyOrder" style="width:100%">
                                    </tbody>
                                </table>
                                <ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
                            </div>
                        </div>
                    </div>
                    </div>
                <!-- /.col-lg-4 -->
              <div class="col-lg-4">
					<div class="aside-tab">
		           	<input type="hidden" id="checkedOrderId" name="checkedOrderId">
		           	<input type="hidden" id="checkedUserMobile" name="checkedUserMobile">
		           	<input type="hidden" id="checkedOrderType" name="checkedOrderType">
		           	<input type="hidden" id="checkedCateType" name="checkedCateType">
		           	<input type="hidden" id="checkedUserId" name="checkedUserId">
		           	<input type="hidden" id="checkedCity" name="checkedCity">
		           	<input type="hidden" id="checkedCityName" name="checkedCityName">
		           	<input type="hidden" id="checkedTotalPay" name="checkedTotalPay">
		           	<input type="hidden" id="checkedPayStatus" name="checkedPayStatus">
		           	<input type="hidden" id="checkedOrderStatus" name="checkedOrderStatus">
		           	<div id="orderBasicServerCont" style="display:none;">
		           		<%@ include file="orderBasicServers.jsp" %>
		           	</div>
		           	<div  id="orderBasicServerOne" style="display:none;">
		           		<%@ include file="orderBasicServerOnes.jsp" %>
		           	</div>
		           	<div  id="orderBasicItem" style="display:none;">
		           		<%@ include file="orderBasicItems.jsp" %>
		           	</div> 
		           	<div  id="orderBasicServerThree" style="display:none;">
		           		<%@ include file="orderBasicServerThrees.jsp" %>
	        	</div>
				</div>
		</div>
</body>
<script type="text/javascript">


//全选
function checkboxAll(e){
	$("input[name='odId']:enabled").prop("checked", document.getElementById("odIds").checked==true);
}
// 复选框取值
function checkboxVale(){
	  var ids=''; 
	  $('input[name="odId"]:checked').each(function(){ 
		  ids += $(this).val() + ','; 
	  });
	  ids = ids.substring(0, ids.length-1);
	  return ids;
}

// 测试保存订单
function serOrderBasic(pid,types){
    var ctx=$("#ctx").val();
	 $.ajax({
		 url:ctx+"/order/insertOrder",
		 data:{
			 urserId:1
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			 alert(data.msg);
		 }
	 });
}

//ready方法
$(function() {
	queryBaseChannel(null,"orderChannel");
	queryThreehreeOrderInitCss();
	queryTOI_Dictionary()	
	setThreeOrderOrderType("serverType");
	selProvinceCitys("101",6,"orderCheckedProvinces");
	queryOrders(0, 10);
	checkOrderBasic($("#checkedOrderId").val(),$("#checkedCateType").val(),0,$("#checkedTotalPay").val());
	//loadBaiduMapJs();
})
/**
 function loadBaiduMapJs(){
	var script = document.createElement("script");
	script.src = "http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=''";
	document.body.appendChild(script);
} */
function setThreeOrderOrderType(htmlId){
	var ctx = $("#ctx").val();
	$.ajax({
		url : ctx + "/threeOrderOut/queryOrderType",
		type : 'post',
		async : false,
		success : function(data) {
			var html = "<option style='color:blue;' value=''>...请选择...</option>";
			html += "<option value='1'>商品</option>";
			var obj = JSON.parse(data);
			$.each(obj.list, function(i, v) {
				html += "<option value='" + v.code + "' keyValue='" + v.cname + "'>" + v.cname + "</option>";
			});
			$("#" + htmlId + "").html(html);
		}
	});
}

function queryThreehreeOrderInitCss(){
	/*tab栏选择*/
    tabx(".tab-item",".main")
    /*表单合并*/
    drop(".drop-on",".drop-down",".info-select");
    /*Tab栏滑动*/
    tabs(".mytabs-wrap");
    /*复选框全选*/
    $('#checkall').change(function(){
        trColorall("#checkall","#native_tbody");
    });
    /*判断是否全部被选中，如果选中全选复选框被选中，反之未被选中*/
    $("#native_tbody > tr").change(function(){
        selall("#native_tbody > tr","#checkall");
    })
    /*阻止冒泡*/
    $("#native_tbody > tr input[type=checkbox]").click(function(event){
        event.stopPropagation();
    });
}


/**
 * 重置选择框
 */
function resetThreeOrderDictionary(htmlId) {
	$("#"+htmlId).html("<option style='color:blue;' value=''>...请选择...</option>");
}

function serThreeOrderCitys(){
	var code=$("#orderCheckedProvinces").val();
	if(code!=null&&code!=""){
		selProvinceCitys(code,9,"orderCheckedCitys");
	}else{
		resetThreeOrderDictionary("orderCheckedCitys");
	}
}


function uploadFile(){
	var ctx = $("#ctx").val();
	var fileName =$('#excelFile').val();
	var ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	if(ext !='xlsx' && ext != 'xls'){
		$.alert({text:"请选择正确的excel文件"});
		return;
	}else{
		$.ajaxFileUpload
        (
            {
                url: ctx+'/threeOrderIn/excelToList', //用于文件上传的服务器端请求地址
                secureuri: false, //是否需要安全协议，一般设置为false
                fileElementId: 'excelFile', //文件上传域的ID
                dataType: 'json', //返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                	if(data.success){
                	$.alert({text:"导入完成,请点击导入记录查看结果"});
                	queryOrders(0, 10);
                	}else{
                		$.alert({text:data.msg});
                	}
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
        return false;
	}
}
function exportExcel(){
	var ctx = $("#ctx").val();
	var orderCode=$("#orderCode").val();
	var userName=$("#userNameOrder").val();
	var createTime = $("#createTimeOrder").val();
	var createTimeEnd = $("#createTimeEnd").val();
	var userMobile=$("#userMobileOrder").val();
	var serverType=$("#serverType").val();
	var orderStatus=$("#orderStatus").val();
	var orderSourceId=$("#orderSourceId").val();
	var orderChannel=$("#orderChannel").val();
	
	$('#theForm input[name="orderCode"]').val(orderCode);
	$('#theForm input[name="userName"]').val(userName);
	$('#theForm input[name="createTime"]').val(createTime);
	$('#theForm input[name="createTimeEnd"]').val(createTimeEnd);
	$('#theForm input[name="userMobile"]').val(userMobile);
	$('#theForm input[name="serverType"]').val(serverType);
	$('#theForm input[name="orderStatus"]').val(orderStatus);
	$('#theForm input[name="orderSourceId"]').val(orderSourceId);
	$('#theForm input[name="orderChannel"]').val(orderChannel);
	$('#theForm').submit();
	
	
}
function queryOrders(num,pageCount){
	var ctx = $("#ctx").val();
	var orderSourceId = $('#orderSourceId').val();
	$.ajax({
		url: ctx +"/threeOrderIn/queryOrder?curPage="+num+"&pageCount="+pageCount,
		type:"post",
		data:{
			orderSourceId:orderSourceId			
		},
		dataType:"json",
		async:false,
		success:function(data){
			showMsg(data.msg);
			$("#pageInfoDiv").html("");
 			$("#pageInfoDiv").pagination(data.page,listBodyOrder);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			
			var html="";
			if (data.msg ="00") {
				var num = 
					$.each(data.list,function(i,v){
						if(i==0){
							$("#checkedOrderId").val(v.id);
							$("#checkedOrderType").val(v.orderType);
							$("#checkedCateType").val(v.cateType);
							$("#checkedTotalPay").val(v.totalPay);
						}
						num=i+1;
						//var parentNo="";
						//parentNo=v.parentNo==0?"父菜单":"子菜单";
						//html +="<tr onclick='updateColor("+i+");'><td width='6%'>" ;
						html +="<tr onclick=\"checkOrderBasic(" +v.id +"," +v.cateType +"," +v.userMobile +"," +v.totalPay+"," +v.userId +",'" +v.city +"','" +(v.receiverProvince +v.receiverCity)  +"'," +i +","+v.payStatus+","+v.orderStatus+")\"><td>" ;
						html +="<input name='odId' type='checkbox' value='" +v.id +"'>";
						html +="</td><td>"+(total + num - pageCount);
						//html +="</td><td>"+num;
						html +="</td><td align='left'>"+v.orderCode;
						html +="</td><td align='left'>"+v.userName;
						html +="</td><td>"+v.userMobile;
						html +="</td><td>"+(v.orderType == 1?"商品":v.serverText);
						html +="</td><td>"+numberToDatestr(v.createTime,8); 
						html +="</td><td align='left'>"+v.statusText;
						html +="</td><td align='left'>"+v.sourceText;
						html +="</td><td align='left'>"+ v.followByName ;
						html +="</td><td align='left'>"+v.followDeptName;
						html +="</td><td align='left'>"+v.rechargeByName;
						html +="</td><td align='left'>"+v.rechargeDeptName;
						html +="</td><td align='left'>"+v.realName;
						html +="</td><td align='left'>"+v.realNameDept;
						html +="</td><td align='left'>"+v.followTime;
						html +="</td></tr>";
					})
			}
			$("#listBodyOrder").html(html);
		}
	});
	  trColor("#listBodyOrder > tr");
	  $("#listBodyOrder > tr").eq(0).trigger("click");
}

/**
 * 分页跳转使用
 */
function listBodyOrder(num,pageCount) {
	$("#listBodyOrder").empty();
	queryOrdersByLike(num,pageCount);
}


//根据菜单地址，链接，介绍进行模糊查询
function queryOrdersByLike(num,pageCount){
	//var options=$("#serverType option:selected");  //获取选中的项
	var ctx = $("#ctx").val();
	var orderCode=$("#orderCode").val();
	var userName=$("#userNameOrder").val();
	var createTime = $("#createTimeOrder").val();
	var createTimeEnd = $("#createTimeEnd").val();
	var userMobile=$("#userMobileOrder").val();
	var serverType=$("#serverType").val();
	var orderStatus=$("#orderStatus").val();
	var orderSourceId=$("#orderSourceId").val();
	var orderChannel=$("#orderChannel").val();
	var userProvinceCode = $("#orderCheckedProvinces").val();
	var userCityCode = $("#orderCheckedCitys").val();
	$.ajax({
		url: ctx +"/threeOrderIn/queryOrder?curPage="+num+"&pageCount="+pageCount,
		data:{
			orderCode:orderCode,
			userName:userName,
			createTime:createTime,
			createTimeEnd:createTimeEnd,
			userMobile:userMobile,
			orderType:serverType,
			orderStatus:orderStatus,
			orderSourceId:orderSourceId,
			orderChannel:orderChannel,
			userProvinceCode:userProvinceCode,
			userCityCode:userCityCode
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			showMsg(data.msg);
// 			$("#pageInfoDiv").html("").html(createPageDiv(data.page));
			var html="";
			$("#pageInfoDiv").pagination(data.page,listBodyOrder);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			
			if (data.msg ="00") {
				var num = 
					$.each(data.list,function(i,v){
						if(i==0){
							$("#checkedOrderId").val(v.id);
							$("#checkedOrderType").val(v.orderType);
							$("#checkedCateType").val(v.cateType);
							$("#checkedTotalPay").val(v.totalPay);
						}
						num=i+1;
						html +="<tr onclick=\"checkOrderBasic(" +v.id +"," +v.cateType +"," +v.userMobile +"," +v.totalPay+","  +v.userId +",'" +v.city +"','" +(v.receiverProvince +v.receiverCity)+"'," +i +","+v.payStatus+","+v.orderStatus+")\"><td>" ;
						html +="<input name='odId' type='checkbox' value='" +v.id +"'>";
						html +="</td><td>"+(total + num - pageCount);
						html +="</td><td align='left'>"+v.orderCode;
						html +="</td><td align='left'>"+v.userName;
						html +="</td><td>"+v.userMobile;
						html +="</td><td>"+(v.orderType == 1?"商品":v.serverText);
						html +="</td><td>"+numberToDatestr(v.createTime,8);
						html +="</td><td align='left'>"+v.statusText;
						html +="</td><td align='left'>"+v.sourceText;
						html +="</td><td align='left'>"+ v.followByName ;
						html +="</td><td align='left'>"+v.followDeptName;
						html +="</td><td align='left'>"+v.rechargeByName;
						html +="</td><td align='left'>"+v.rechargeDeptName;
						html +="</td><td align='left'>"+v.realName;
						html +="</td><td align='left'>"+v.realNameDept;
						html +="</td><td align='left'>"+v.followTime;
						html +="</td></tr>";
					})
			}
			$("#listBodyOrder").html(html);
		}
	});
	  trColor("#listBodyOrder > tr");
	  $("#listBodyOrder > tr").eq(0).trigger("click");
}

//基本信息显示
/* function checkOrderBasic(orderId,cateType,userMobile,userId,city,cityName,totalPay,index,payStatus,orderStatus){
	// updateColorOrder(index);
	$("#checkedOrderId").val(orderId);
	$("#checkedCateType").val(cateType);
	$("#checkedUserMobile").val(userMobile);
	$("#checkedUserId").val(userId);
	$("#checkedCity").val(city);
	$("#checkedCityName").val(cityName);
	$("#checkedTotalPay").val(totalPay);
	$("#checkedPayStatus").val(payStatus);
	$("#checkedOrderStatus").val(orderStatus);
	//alert(orderId +" and " +cateType +" and " +userMobile +" and " +userId +" and " +city +" and " +cityName +" and " +totalPay +" and " +index) ;
	if(cateType==1){
		document.getElementById('orderBasicServerOne').style.display="block";
		document.getElementById('orderBasicServerCont').style.display="none";
		document.getElementById('orderBasicItem').style.display="none";
		document.getElementById('orderBasicServerThree').style.display="none";
		setBasicServerOne(orderId,cateType,totalPay,orderStatus);
	}else if(cateType==2){
		document.getElementById('orderBasicServerOne').style.display="none";
		document.getElementById('orderBasicServerCont').style.display="block";
		document.getElementById('orderBasicItem').style.display="none";
		document.getElementById('orderBasicServerThree').style.display="none";
		setBasicServer(orderId,cateType,totalPay,orderStatus);
	}else if(cateType==3){
		document.getElementById('orderBasicServerOne').style.display="none";
		document.getElementById('orderBasicServerCont').style.display="none";
		document.getElementById('orderBasicItem').style.display="block";
		document.getElementById('orderBasicServerThree').style.display="none";
		setBasicItem(orderId,userMobile,cateType,totalPay);
	}else{
		document.getElementById('orderBasicServerOne').style.display="none";
		document.getElementById('orderBasicServerCont').style.display="none";
		document.getElementById('orderBasicItem').style.display="none";
		document.getElementById('orderBasicServerThree').style.display="block";
		if(orderId!=null&&orderId!=""&&cateType!=null&&cateType!=""){
			setBasicServerThree(orderId,cateType,totalPay,orderStatus);
		}
	}
} */

// 基本信息显示
function checkOrderBasic(orderId,cateType,index,totalPay){
	$("#checkedOrderId").val(orderId);
	$("#checkedCateType").val(cateType);
	$("#checkedTotalPay").val(totalPay);
	if(cateType==1 || cateType==2 || cateType == 4){
		document.getElementById('orderBasicServerOne').style.display="block";
		document.getElementById('orderBasicItem').style.display="none";
		setBasicServerOne(orderId,cateType);
	}else if(cateType==3){
		document.getElementById('orderBasicServerOne').style.display="none";
		document.getElementById('orderBasicItem').style.display="block";
		setBasicItem(orderId);
	}
}

function createOrderTest(){
	var ctx = $("#ctx").val();
	$.ajax({
		url:ctx +"/threeOrderIn/createOrderTest",
		sync:false,
		success:function(data){
			if(data.msg ="00"){
				var ary = data.num.split(",");
				alert("本次本生成订单编号："+ary[0] +"条，耗时：" +ary[1] +"秒.");
			}
		}
	});
}
function downloadTemplate(){
	var ctx = $("#ctx").val();
	window.location.href = ctx + "/upload/三方订单(给入)导入模板.xls"
}
function downloadRecord(){
	var ctx = $("#ctx").val();
	openModule(ctx + '/jsp/order_three/in/threeOrderRecord.jsp', '', '',
			{
				width : '50%'
			}, 'threeOrderRecord');
}

function queryTOI_Dictionary(htmlId, code, value) {
	var ctx = $("#ctx").val();
	$.ajax({
		url : ctx + "/threeOrderOut/queryBaseDictionary",
		data : {
			code : code
		},
		type : 'post',
		async : false,
		success : function(data) {
			var html = "";
			var obj = JSON.parse(data);
			html += "<option style='color:blue;' value=''>...请选择...</option>";
			var selected = false;
			$.each(obj.list, function(i, v) {
				html += "<option value='" + v.code + "' keyValue='" + v.name + "'>" + v.name + "</option>";
			})
			$("#" + htmlId).html(html);
			// 设置默认选中
			if (value != null && value != '') {
				$("#" + htmlId + " option[value='" + value + "']").attr("selected", "selected");
			}
		}
	})
}


/**
 * 日期转格式
 * @param datenum
 * @param num   日期需要的数字位数
 * @returns {String}
 */
function numberToDatestr(datenum,num){
	var ndate = "" ;
	if(datenum!=null && datenum!="" && datenum!="undefined"){
		if(num==8 && datenum.length>=10){
			ndate = datenum.substr(0,10);
		}else if(num==10 && datenum.length>=13){
			ndate = datenum.substr(0,13);
		}else if(num==12 && datenum.length>=16){
			ndate = datenum.substr(0,16);
		}else{
			ndate = datenum;
		}
	}
	return ndate;
	
}
 
//设置选择城市
 function selProvinceCitys(code, length, htmlId) {
 	var ctx = $("#ctx").val();
 	$.ajax({
 		url : ctx + "/threeOrderOut/queryBaseCity",
 		data : {
 			code : code,
 			length : length
 		},
 		type : 'post',
 		async : false,
 		success : function(data) {
 			var html = "<option style='color:blue;' value=''>...请选择...</option>";
 			var obj = JSON.parse(data);
 			$.each(obj.list, function(i, v) {
 				html += "<option value='" + v.code + "' keyValue='" + v.name + "'>" + v.name + "</option>";
 			});
 			$("#" + htmlId + "").html(html);
 		}
 	});
 }
 
 //是否为空验证
 function f_TOI_isEmpty(value){
	 if(!value || value == "" || value =="null" ){
		 return true;
	 }else{
		 return false;
	 }
 }
</script>
</html>

