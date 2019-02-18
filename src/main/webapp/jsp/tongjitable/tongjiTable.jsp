<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<script src="${ctx}/js/orderBase.js"></script>
<%-- 	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script> --%>
	<style>
		.orderlisttable tr td{text-align: left}
		.feesOnediv{margin:20px auto auto 15px}
		
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
                <li><a href="#">订单管理</a></li>
                <li class="active">营养统计</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
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
                                    	<div class="form-group  col-xs-12">
                                    		<label><p>下单日期</p>
                                    		<input id="createTimeTtem" class="Wdate form-control" type="text" 
		                                    onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
		                                            &nbsp;至&nbsp;
		                                    <input id="createTimeTtemend" class="Wdate form-control" type="text" 
		                                    onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-12">
                                    		<label><p>配送日期</p>
                                    		<input id="deliverTimeTtem" class="Wdate form-control" type="text" 
		                                    onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
		                                            &nbsp;至&nbsp;
		                                    <input id="deliverTimeTtemend" class="Wdate form-control" type="text" 
		                                    onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                    		</label>
                                    	</div>
                                    	</div>
                                    	
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>订单编号:</p>
   												<input type="text" name="orderCode" id="orderCode" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>收货人姓名:</p>
   												<input type="text" name="userName" id="userNameOrder" class="form-control"/>                    		
                                    		</label>
                                    	</div>
                                    </div>
                                    
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>收货人电话</p>
                                    			<input type="text"  id="userMobile" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>下单电话</p>
                                    		<input type="text"  id="userMobileOrder" class="form-control"/>
                                    		</label>
                                    	</div>
                                    </div>
                                    
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>下单部门</p>
                                    			<input type="text" name="orderdep" id="orderdep" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>收货地址</p>
                                    			<input type="text" name="address" id="address" class="form-control"/>
                                    		</label>
                                    	</div>
                                   	</div>
                                   	
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>商品名称</p>
                                    			<input type="text" name="productName" id="productName" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>订单来源</p>
                                    			<select name="orderSourceId" id="orderSourceId" class="form-control"/>
                                    		</label>
                                    	</div>
                                    </div>
                                    
                                    <div class="row">
                                    	
                                    	<div class="form-group  col-xs-6">
                                    		<label><p>用户备注</p>
                                    			<input type="text" name="userRemark" id="userRemark" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	</div>
                                    	
                                    	<div class="row">
                                    			<div class="form-group  col-xs-6">
                                    		<label><p>订单渠道</p>
                                    			<select name="orderChannel" id="orderChannel" class="form-control"/>
                                    		</label>
                                    	       </div>
                                    	       <div class="form-group col-xs-4">
                                       		<button type="button" class="btn btn-sm btn-default" 
                                       			onclick="query(0,10);">查询</button>&nbsp;&nbsp;
                                        		<button type="button" class="btn btn-sm btn-default" onclick="resetQuery();">重置</button>
                                        </div>
                                        
                                    	</div>
                                    </div>
                                    </form>
                                </div>
                            </div>
                            <div class="panel-heading panel-new">
                                 <button class="btn btn-sm btn-default" type="button" onclick="exportItemOrders()">
							       <em class="add glyphicon glyphicon-plus-sign"></em>导出订单
									</button>
                            </div>
                                
                        <form action="${ctx }/itemDetailFa/exportOrderExcel" method="post" id="theForm">
						<input type="hidden" name="createTimeTtem" />
						<input type="hidden" name="createTimeTtemend" />
						<input type="hidden" name="deliverTimeTtem" />
						<input type="hidden" name="deliverTimeTtemend" />
						<input type="hidden" name="orderCode" />
						<input type="hidden" name="userMobile" />
						<input type="hidden" name="userMobileOrder" />
						<input type="hidden" name="userNameOrder" />
						<input type="hidden" name="orderdep" />
						<input type="hidden" name="address" />
						<input type="hidden" name="productName" />
						<input type="hidden" name="orderSourceId" />
						<input type="hidden" name="userRemark" />
						<input type="hidden" name="orderChannel" />
					</form>     
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" style="width:1300px;">
                                    <thead>
                                    <tr>
										<th width="5%">序号</th>
										<th width="8%">订单号</th>
										<th width="5%">收货人姓名</th>
										<th width="7%">收货人电话</th>
										<th width="7%">下单电话</th>
										<th width="10%">收货地址</th>
										<th width="8%">下单日期</th>
										<th width="8%">配送日期</th>
										<th width="8%">商品名称</th>
										<th width="8%">规格</th>
										<th width="8%">数量</th>
										<th width="8%">订单渠道</th>
										<th width="8%">下单部门</th>
										<th width="8%">订单来源</th>
										<th width="8%">用户备注</th>
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
    </div>
</body>
<script type="text/javascript">
//默认执行方法
$(function() { 
	queryBaseDictionary("2018",8,"orderSourceId");
	queryBaseChannel(null,"orderChannel");
	queryItemList(0, 10);
	$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
	//loadJScript();
	 /*loading*/
    $('#shclDefault').shCircleLoader();
    /*电话号码显示隐藏*/
    /* show(".phone",".phone-show"); */
    tabx(".tab-item",".main");
    /*表单合并*/
    drop(".drop-on",".drop-down",".info-select");
    /*阻止冒泡*/
    $("#listBodyOrder > tr input[type=checkbox]").click(function(event){
        event.stopPropagation();
    });
})
//初始化查询
function queryItemList(num,pageCount){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/itemDetailFa/queryItemList?curPage="+num+"&pageCount="+pageCount,
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			showMsg(data.msg);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			var html="";
			$("#pageInfoDiv").pagination(data.page,listBodyquery);
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						html +="<tr >" ;
						html +="<td>"+(total + num - pageCount);
						html +="</td><td align='left'>"+v.orderCode;
						html +="</td><td align='left'>"+v.receiverName;
						html +="</td><td>"+v.receiverMobile;
						html +="</td><td>"+v.userMobile;
						html +="</td><td>"+v.userAddress; 
						html +="</td><td>"+v.createTime;
						html +="</td><td align='left'>"+v.recetptTime;
						html +="</td><td align='left'>"+v.productName;
						html +="</td><td align='left'>"+v.productSpec;
						html +="</td><td align='left'>"+v.quantity;
						html +="</td><td align='left'>"+v.orderChannel;
						html +="</td><td align='left'>"+v.name;
						html +="</td><td align='left'>"+v.orderSourceId;
						html +="</td><td align='left'>"+v.remark;
						html +="</td></tr>"; 
					})
					$("#pageInfoDiv").show();
			}else{
				html="<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
				$("#pageInfoDiv").hide();
			}
			$("#listBodyOrder").html(html);
			if (data.msg =="00") { 
				trColorOrder("#listBodyOrder tr");
				$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
			}
		}
	});
}
/**
 * 模糊查询
 */
 function  query(num,pageCount){
	setExportItemOrders();	   //设置excel导出参数
    var ctx = $("#ctx").val();
	var createTimeTtem=$("#createTimeTtem").val();
	var createTimeTtemend=$("#createTimeTtemend").val();
	var deliverTimeTtem=$("#deliverTimeTtem").val();
	var deliverTimeTtemend=$("#deliverTimeTtemend").val();
	var orderCode=$("#orderCode").val();
	var userMobile=$("#userMobile").val();
	var userMobileOrder=$("#userMobileOrder").val();
	var userNameOrder=$("#userNameOrder").val();
	var orderdep=$("#orderdep").val();
	var address=$("#address").val();
	var productName=$("#productName").val();
	var orderSourceId=$("#orderSourceId").val();
	var userRemark=$("#userRemark").val();
	var orderChannel=$("#orderChannel").val();
	
	$.ajax({
		url: ctx +"/itemDetailFa/queryItemList?curPage="+num+"&pageCount="+pageCount,
		type:"post",
		data:{
			orderCode:orderCode,
			receiverName:userNameOrder,
			receiverMobile:userMobileOrder,
			userMobile:userMobile,
			userAddress:address,
			createTime:createTimeTtem,
			createTimeend:createTimeTtemend,
			recetptTime:deliverTimeTtem,
			recetptTimeend:deliverTimeTtemend,
			productName:productName,
			orderChannel:orderChannel,
			name:orderdep,
			orderSourceId:orderSourceId,
			remark:userRemark
		},
		dataType:"json",
		async:false,
		success:function(data){
			showMsg(data.msg);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			var html="";
			$("#pageInfoDiv").pagination(data.page,listBodyquery);
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						html +="<tr >" ;
						html +="<td>"+(total + num - pageCount);
						html +="</td><td align='left'>"+v.orderCode;
						html +="</td><td align='left'>"+v.receiverName;
						html +="</td><td>"+v.receiverMobile;
						html +="</td><td>"+v.userMobile;
						html +="</td><td>"+v.userAddress; 
						html +="</td><td>"+v.createTime;
						html +="</td><td align='left'>"+v.recetptTime;
						html +="</td><td align='left'>"+v.productName;
						html +="</td><td align='left'>"+v.productSpec;
						html +="</td><td align='left'>"+v.quantity;
						html +="</td><td align='left'>"+v.orderChannel;
						html +="</td><td align='left'>"+v.name;
						html +="</td><td align='left'>"+v.orderSourceId;
						html +="</td><td align='left'>"+v.remark;
						html +="</td></tr>"; 
					})
					$("#pageInfoDiv").show();
			}else{
				html="<tr><td colspan='17'><p class='table-empty'>暂无数据</p></td></tr>";
				$("#pageInfoDiv").hide();
			}
			$("#listBodyOrder").html(html);
			if (data.msg =="00") { 
				trColorOrder("#listBodyOrder tr");
				$("#listBodyOrder > tr").eq(0).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
			}
		}
	});
	
}
/**
 * 分页跳转使用
 */
function listBodyquery(num,pageCount) {
	$("#listBodyOrder").empty();
	 query(num,pageCount);
	
}
/*表格点击行高亮*/
function trColorOrder(trli){
    $(trli).on("click",function(){
    	//$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer');
        $(this).addClass('info').siblings().removeClass('info').css('cursor', 'pointer');
    })
}
/**
 * 重置查询条件
 */
function resetQuery(){
	$("#createTimeTtem").val('');
	$("#createTimeTtemend").val('');
	$("#deliverTimeTtem").val('');
	$("#deliverTimeTtemend").val('');
	$("#orderCode").val('');
	$("#userMobile").val('');
	$("#userMobileOrder").val('');
	$("#userNameOrder").val('');
	$("#orderdep").val('');
	$("#address").val('');
	$("#productName").val('');
	$("#orderSourceId").val('');
	$("#userRemark").val('');
	$("#orderChannel").val('');
}
/**
*导出订单
*/
function exportItemOrders(){
$("#theForm").submit();
}
/**
*导出订单
*/
function setExportItemOrders(){
	var createTimeTtem=$("#createTimeTtem").val();
	var createTimeTtemend=$("#createTimeTtemend").val();
	var deliverTimeTtem=$("#deliverTimeTtem").val();
	var deliverTimeTtemend=$("#deliverTimeTtemend").val();
	var orderCode=$("#orderCode").val();
	var userMobile=$("#userMobile").val();
	var userMobileOrder=$("#userMobileOrder").val();
	var userNameOrder=$("#userNameOrder").val();
	var orderdep=$("#orderdep").val();
	var address=$("#address").val();
	var productName=$("#productName").val();
	var orderSourceId=$("#orderSourceId").val();
	var userRemark=$("#userRemark").val();
	var orderChannel=$("#orderChannel").val();
	
	$('#theForm input[name="createTimeTtem"]').val(createTimeTtem);
	$('#theForm input[name="createTimeTtemend"]').val(createTimeTtemend);
	$('#theForm input[name="deliverTimeTtem"]').val(deliverTimeTtem);
	$('#theForm input[name="deliverTimeTtemend"]').val(deliverTimeTtemend);
	$('#theForm input[name="orderCode"]').val(orderCode);
	$('#theForm input[name="userMobile"]').val(userMobile);
	$('#theForm input[name="userMobileOrder"]').val(userMobileOrder);
	$('#theForm input[name="userNameOrder"]').val(userNameOrder);
	$('#theForm input[name="orderdep"]').val(orderdep);
	$('#theForm input[name="address"]').val(address);
	$('#theForm input[name="productName"]').val(productName);
	$('#theForm input[name="orderSourceId"]').val(orderSourceId);
	$('#theForm input[name="userRemark"]').val(userRemark);
	$('#theForm input[name="orderChannel"]').val(orderChannel);
}
</script>
</html>

