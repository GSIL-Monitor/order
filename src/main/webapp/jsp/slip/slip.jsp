<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="${ctx}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转单redis存值情况查询</title>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="row">
            <div class="col-lg-8">
	            	<div class="panel panel-default">
					<!-- /.panel-heading -->
					<div class="panel-body">
						<tbody class="dataTable_wrapper">
							<div class="dataTable-info">
								<div class="info-select clearfix">
									<form id="listForm">
										<div class="row">
											<div class="form-group col-xs-6  form-inline">
												<label class="parentCls">
													<p>客户电话:</p> 
													<input type="text" class="form-control inputElem" id="mobile" name="mobile">
												</label>
											</div>
											<div class="col-xs-6">
													<button type='button' class='btn btn-default btn-sm fr mr20' onclick="javascript:query(0,5)">
														<em class="glyphicon glyphicon-search"></em>查询
													</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<table class="table table-striped table-hover table-responsive">
								<thead>
								<tr>
									<th>序号</th>
									<th>键</th>
									<th>值</th>
								</tr>
						</thead>
						<tbody id="manager_list_body"></tbody>
						</table>
						<ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
						</tbody>
					</div>
				</div>
            </div>
            <!-- <div class="col-lg-4" >
	            	<div class="panel panel-default">
					/.panel-heading
					<div class="panel-body">
						<tbody class="dataTable_wrapper">
							<div class="dataTable-info">
								<div class="info-select clearfix">
									<form id="listForm">
										<div class="row">
											<div class="form-group col-xs-6  form-inline">
												<label class="parentCls">
													<p>账号姓名:</p> 
													<input type="text" class="form-control inputElem" id="userName" name="userName">
												</label>
											</div>
											<div class="col-xs-6">
													<button type='button' class='btn btn-default btn-sm fr mr20' onclick="javascript:alternateQueryManager(0,15)">
														<em class="glyphicon glyphicon-search"></em>查询
													</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<table class="table table-striped table-hover table-responsive">
								<thead>
								<tr>
									<th>序号</th>
									<th>键</th>
									<th>值</th>
								</tr>
						</thead>
						<tbody id="manager_list_body1"></tbody>
						</table>
						<ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
						</tbody>
					</div>
				</div>
            </div> -->
          <!--   <div class="col-lg-4" >
            	<div class="panel panel-default">
					/.panel-heading
					<div class="panel-body">
						<tbody class="dataTable_wrapper">
							<div class="dataTable-info">
								<div class="info-select clearfix">
									<form id="listForm">
										<div class="row">
											<div class="form-group col-xs-6  form-inline">
												<label class="parentCls">
													<p>账号姓名:</p> 
													<input type="text" class="form-control inputElem" id="userName" name="userName">
												</label>
											</div>
											<div class="col-xs-6">
													<button type='button' class='btn btn-default btn-sm fr mr20' onclick="javascript:alternateQueryManager(0,15)">
														<em class="glyphicon glyphicon-search"></em>查询
													</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<table class="table table-striped table-hover table-responsive">
								<thead>
								<tr>
									<th>序号</th>
									<th>用户姓名</th>
								</tr>
						</thead>
						<tbody id="manager_list_body2"></tbody>
						</table>
						<ul class="pagination pagination-sm navbar-right" id="pageInfoDiv"></ul>
						</tbody>
					</div>
				</div>
            </div> -->
	</div>
</body>
<script src="${ctx}/js/jquery/jquery.min.js" type="text/javascript"></script>
<script>
$(function(){  
    query(0,5);
});  
function query(min,max){
	$("#manager_list_body").html("");
	$("#manager_list_body1").html("");
	$("#manager_list_body2").html("");
	var ctx = $("#ctx").val();
	var mobile = $("#mobile").val();
	/* var json = {'data':[]};
	json.data.push("123");
	json.data.push("456");
	json.data.push("789");
	var html ="";
	 $.each(json.data,function(i,v){
		html+="<tr>"
		html+="<td>"+(i+1)+"</td>";
		html+="<td>"+v+"</td>";
		html+="</tr>";
		$("#manager_list_body").html(html);
	});  */
	var html_id ="";
	var html_log ="";
	var html_err ="";
	var num =0
	$.ajax({
		url: ctx +'/partyOrder/queryRedis?min'+min+'&max'+max,
		data:{mobile:mobile},
		type:'post',
		dataType:"json",
		async:false,
		success:function(data){
			if(data.msg=="00"){
				$.each(data.list,function(i,v){
					html_id+="<tr>"
					html_id+="<td>"+(i+1)+"</td>";
					html_id+="<td>"+v.key+"</td>";
					html_id+="<td>"+v.value+"</td>";
					html_id+="</tr>";
					$("#manager_list_body").html(html_id);
				})
			}else if(data.msg=="02"){
				$("#manager_list_body").html("未输入客户电话,请输入电话查询");
			}else if(data.msg=="01"){
				$("#manager_list_body").html("未查询到redis里面有值");
			}
		}
	});
}
</script>
</html>