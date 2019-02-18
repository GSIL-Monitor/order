<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/order_three/ajaxfileupload.js"></script>
<script type="text/javascript" src="${ctx}/js/order_three/common.js"></script>
<script src="${ctx}/js/order_three/orderBaseThree.js"></script>
<script src="${ctx}/js/orderBase.js"></script>
<style>
		.orderlisttable tr td{text-align: left}
		.feesOnediv{margin:20px auto auto 15px}
		
		.excelFileDiv{ position:relative;width:340px} 
		.excelFilebtn{ height:30px; width:90px;} 
		.excelFile{ position:absolute; top:0; left:110px; height:28px; filter:alpha(opacity:0);opacity: 0;width:105px; cursor: pointer;} 
	</style>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="row">
		<div class="col-lg-12">
			<ol class="page-header breadcrumb">
				<li><a href="#">Home</a></li>
				<li><a href="#">订单管理系统</a></li>
				<li><a href="#">三方订单管理</a></li>
				<li class="active">商家订单管理</li>
			</ol>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-8">
			<div class="panel panel-default">
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
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p>订单编号:</p>
											<input type="text" name="threeOrderOrderCode" id="threeOrderOrderCode" class="form-control"/>
										</label>
									</div>
									<div class="form-group col-xs-6">
										<label>
											<p>客户姓名:</p>
											<input type="text" name="threeOrderUserName" id="threeOrderUserName" class="form-control"/>
										</label>
									</div>
									
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p>客户电话:</p>
											<input type="text" name="threeOrderUserMobile" id="threeOrderUserMobile" class="form-control"/>
										</label>
									</div>
									<div class="form-group  col-xs-6">
										<label>
											<p>订单来源:</p>
											<select name="threeOrderOrderSourceId" id="threeOrderOrderSourceId" class="form-control"></select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p>订单类型:</p>
											<select name="threeOrderOrderType" id="threeOrderOrderType" class="form-control">
											</select> 
										</label>
									</div>
									<div class="form-group  col-xs-6">
										<label>
											<p>订单状态:</p>
											<select name="threeOrderOrderStatus" id="threeOrderOrderStatus" class="form-control">
												<option style='color:blue;' value=''>...请选择...</option>
											<!-- 	<option value='1'>新单</option>
												<option value='2'>已受理</option>
												<option value='3'>匹配中</option> -->
												<option value='4'>已匹配</option>
												<option value='9'>已完成</option>
											<!-- 	<option value='10'>已取消</option> -->
											</select>
										</label>
									</div>
									
									
								</div>
								<div class="row">
									<div class="form-group  col-xs-6">
										<label>
											<p>省份:</p>
											<select class="form-control"  name="threeOrderProvinces" id="threeOrderProvinces" onchange="serThreeOrderCitys()">
                                		</select>
										</label>
									</div>
									<div class="form-group  col-xs-6">
										<label>
											<p>城市:</p>
											 	<select class="form-control" id="threeOrderCitys" name="threeOrderCitys"  >
											 		<option style='color:blue;' value=''>...请选择...</option>
												</select>
										</label>
									</div>
								</div>
									
									<div class="row">
									<div class="form-group  col-xs-12">
										<label>
											<p>创建时间:</p>
											<input id="threeOrderStartTime" class="Wdate form-control" type="text" 
												onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'threeOrderEndTime\')||\'%y-%M-{%d}\'}',dateFmt:'yyyy-MM-dd'})" />
											 &nbsp;至&nbsp;<input id="threeOrderEndTime" class="Wdate form-control" type="text" 
												onfocus="WdatePicker({minDate:'#F{$dp.$D(\'threeOrderStartTime\')}',maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
										</label>
									</div>
									
								</div>
								<div class="row">
                                    	<div class="form-group  col-xs-8">
                                    		<label><p>订单渠道</p>
                                    			<select name="threeOrderOrderChannel" id="threeOrderOrderChannel" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group col-xs-4">
                                       		<button type="button" class="btn btn-sm btn-default" 
                                       			onclick="queryOrdersByLike(0,10);">查询</button>&nbsp;&nbsp;
                                        		<button type="button" class="btn btn-sm btn-default" onclick="resetOrders();">重置</button>
                                        </div>
                                    </div>
							</form>
						</div>
					</div>
			<%-- 		<div class="panel-heading panel-new">
						<div class="excelFileDiv">
							<input type="file" name="excelFile" class="excelFile" id="excelFile" onchange="uploadFile()" />
						</div>
						<button class="btn btn-sm btn-default excelFilebtn" type="button" onclick="exportExcel()">
							<em class="add glyphicon glyphicon-plus-sign "></em>导入订单
						</button>
						<button class="btn btn-sm btn-default" type="button" onclick="exportThreeOrders()">
							<em class="add glyphicon glyphicon-plus-sign"></em>导出订单
						</button>
						<button class="btn btn-sm btn-default" type="button" onclick="openModule('${ctx}/jsp/order_three/out/orderUser.jsp',{},{},{},'threeOrderOutUser')">
							<em class="add glyphicon glyphicon-plus-sign"></em>新增
						</button>
						<!-- <button class="btn btn-sm btn-default" type="button" onclick="doMatch()">
							<em class="add glyphicon glyphicon-plus-sign"></em>去匹配
						</button> -->
						<button class="btn btn-sm btn-default" type="button" onclick="doBilling()">
							<em class="add glyphicon glyphicon-plus-sign"></em>结单
						</button>
						<button class="btn btn-sm btn-default" type="button" onclick="downloadTemplate()">
							<em class="add glyphicon glyphicon-plus-sign"></em>模板下载
						</button>
						<button class="btn btn-sm btn-default" type="button" onclick="downloadRecord()">
							<em class="add glyphicon glyphicon-plus-sign"></em>导入记录信息
						</button>
					</div> --%>
					
					 <div class="panel-heading panel-new">
                                 <button class="xinzeng btn btn-sm btn-default" type="button" onclick="openModule('${ctx}/jsp/order_three/out/orderUser.jsp',{},{},{},'threeOrderOutUser')">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>新增</button>   
                           		 	<button class="xinzeng btn btn-sm btn-default" type="button" onclick="doBilling()">
                                 	<em class="add glyphicon glyphicon-plus-sign"></em>结单</button>  
                           		 	<div class="excelFileDiv">
                           		 	<button class="xinzeng btn btn-sm btn-default excelFilebtn" type="button">
                           		 	<em class="add glyphicon glyphicon-plus-sign "></em>导入订单</button>  
                           		 	<input type="file" name="excelFile" class="excelFile" id="excelFile" onchange="uploadFile()" />
                           		 	</div>
                           		 	<!-- <button class="btn btn-sm btn-default" type="button" onclick="exportThreeOrders()">
							       <em class="add glyphicon glyphicon-plus-sign"></em>导出订单
									</button> -->
									<button class="btn btn-sm btn-default" type="button" onclick="exportOrdersThreeOut()">
							       <em class="add glyphicon glyphicon-plus-sign"></em>导出订单
									</button>
                                 	<button class="btn btn-sm btn-default" type="button" onclick="downloadRecord()">
										<em class="add glyphicon glyphicon-plus-sign"></em>导入记录信息
									</button>   
                            </div>
					<form action="${ctx }/threeOrderOut/exportOrderExcel" method="post" id="theForm">
						<input type="hidden" name="orderCode" />
						<input type="hidden" name="userName" />
						<input type="hidden" name="startTime" />
						<input type="hidden" name="endTime" />
						<input type="hidden" name="userMobile" />
						<input type="hidden" name="orderType" />
						<input type="hidden" name="orderStatus" />
						<input type="hidden" name="orderSourceId" />
						<input type="hidden" name="orderChannel" />
						<input type="hidden" name="provinceCode" />
						<input type="hidden" name="cityCode" />
					</form>
					<div class="panel-content table-responsive">
						<table class="table table-hover">
							<thead>
								<tr>
									<th width="6%">
										<input name="odIds" id="odIds" onclick="checkboxAll(this)" type="checkbox">
									</th>
									<th width="6%">序号</th>
									<th width="5%">订单编号</th>
									<th width="5%">品牌</th>
									<th width="5%">客户姓名</th>
									<%-- <% if( !CookieUtils.getJSONKey(request, "managerType").equals("2")){ %>
										<th width="16%">联系方式</th>
										<%} %> --%>
									<th width="16%">分类</th>
									<th width="14%">创建时间</th>
									<th width="12%">订单状态</th>
									<th width="5%">订单来源</th>
									<th width="5%">订单渠道</th>
									<!-- <th width="6%">分包人</th> -->
									<!-- <th width="5%">分包部门</th> -->
									<th width="5%">负责人</th>
									<th width="5%">负责部门</th>
									<th width="5%">录入人</th>
									<th width="5%">录入人部门</th>
									<!-- <th width="5%">分包时间</th> -->
								<!-- 	<th width="5%">操作</th> -->
								</tr>
							</thead>
							<tbody id="threeOrderOutList" style="width: 100%"></tbody>
						</table>
					</div>
					<nav>
						<ul class="pagination pagination-sm navbar-right" id="threeOrderOutListPage"></ul>
					</nav>
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
		           	<div  id="orderBasicServerThree"  style="display:none;" >
		           		<%@ include file="orderBasicServerThree.jsp" %>
		           	</div> 
		           	
		           	<div  id="orderbasicThree"    style="display:none;">
		           		<%@ include file="ThreeOrderBasic.jsp" %>
		           	</div> 
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
		queryThreehreeOrderInitCss();
		queryThreeOrderDictionary("threeOrderOrderSourceId","2018",null);
		queryBaseChannel(null,"threeOrderOrderChannel");
		selThreeOrderProvinceCitys("101",6,"threeOrderProvinces");
		selThreeOrderOrderType("threeOrderOrderType");
		queryThreeOrders(0, 10);
	})
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
	 * 全选
	 */
	function checkboxAll(_this){
		var checked = document.getElementById("odIds").checked==true;
		$("input[name='odId']:enabled").prop("checked", checked);
	}

	/**
	 * 查数据字典
	 */
	function queryThreeOrderDictionary(htmlId, code, value) {
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
	 * 重置选择框
	 */
	function resetThreeOrderDictionary(htmlId) {
		$("#"+htmlId).html("<option style='color:blue;' value=''>...请选择...</option>");
	}

	function serThreeOrderCitys(){
		var code=$("#threeOrderProvinces").val();
		if(code!=null&&code!=""){
			selThreeOrderProvinceCitys(code,9,"threeOrderCitys");
		}else{
			resetThreeOrderDictionary("threeOrderCitys");
		}
	}

	// 设置选择城市
	function selThreeOrderProvinceCitys(code, length, htmlId) {
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
				var html = "<option style='color:blue;' value=''>... 请选择...</option>";
				var obj = JSON.parse(data);
				$.each(obj.list, function(i, v) {
					html += "<option value='" + v.code + "' keyValue='" + v.name + "'>" + v.name + "</option>";
				});
				$("#" + htmlId + "").html(html);
			}
		});
	}
	
	function selThreeOrderOrderType(htmlId){
		var ctx = $("#ctx").val();
		$.ajax({
			url : ctx + "/threeOrderOut/queryOrderType",
			type : 'post',
			async : false,
			success : function(data) {
				var html = "<option style='color:blue;' value=''>...请选择...</option>";
				var obj = JSON.parse(data);
				$.each(obj.list, function(i, v) {
					html += "<option value='" + v.code + "' keyValue='" + v.cname + "'>" + v.cname + "</option>";
				});
				$("#" + htmlId + "").html(html);
			}
		});
	}

	/**
	 * 初始化查询
	 * @param num
	 * @param pageCount
	 */
	function queryThreeOrders(num,pageCount){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/threeOrderOut/queryOrder?curPage="+num+"&pageCount="+pageCount,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				showMsg(data.msg);
				$("#threeOrderOutListPage").html("");
	 			$("#threeOrderOutListPage").pagination(data.page,threeOrderOutList);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				var html="";
				if (data.msg =="00") {
					var num = 
						$.each(data.list,function(i,v){
							num=i+1;
							html +="<tr data-orderStatus='"+v.orderStatus+"' onclick=\"checkOrderBasic(" +v.id +"," +v.cateType +"," +("'"+v.userMobile+"'") +","  +v.userId +",'" +v.city +"','" +(v.receiverProvince +v.receiverCity) +"'," +v.totalPay +"," +i +","+v.payStatus+","+v.orderStatus +")\"><td>" ;
							html +="<input  name='odId' type='checkbox' value='" +v.id +"'>";
							html +="</td><td>"+(total + num - pageCount);
							html +="</td><td align='left'>"+v.orderCode;
							html +="</td><td align='left'>"+v.brandName;
							html +="</td><td align='left'>"+v.userName;
							html +="</td><td>"+v.typeText;
							html +="</td><td>"+numberToDatestr(v.createTime,8);
							html +="</td><td align='left'>";
							if('1'==v.orderStatus){
								html += "新单";
							}else if('2'==v.orderStatus){
								html += "已受理";
							}else if('3'==v.orderStatus){
								html += "匹配中";
							}else if('4'==v.orderStatus){
								html += "已匹配";
							}else if('5'==v.orderStatus){
								html += "待面试";
							}else if('6'==v.orderStatus){
								html += "面试成功";
							}else if('7'==v.orderStatus){
								html += "已签约";
							}else if('8'==v.orderStatus){
								html += "已上户";
							}else if('9'==v.orderStatus){
								html += "已完成";
							}else if('10'==v.orderStatus){
								html += "已取消";
							}else if('11'==v.orderStatus){
								html += "已下户";
							}else if('12'==v.orderStatus){
								html += "已终止";
							}else if('13'==v.orderStatus){
								html += "捡货中";
							}else if('14'==v.orderStatus){
								html += "配送中";
							}
							html +="</td><td align='left'>"+v.sourceText;
							html +="</td><td align='left'>"+v.channelText;
							/* html +="</td><td align='left'>"+ v.followByName ; */
							/* html +="</td><td align='left'>"+v.followDeptName; */
							html +="</td><td align='left'>"+v.rechargeByName;
							html +="</td><td align='left'>"+v.rechargeDeptName;
							html +="</td><td align='left'>"+v.realName;
							html +="</td><td align='left'>"+v.realNameDept;
							/* html +="</td><td align='left'>"+v.followTime; */
							/* html +="</td><td align='left'>";
							if('2'==v.orderStatus){
								html +="<input type='button' value='匹配成功' onclick='matchSuccess("+v.id+")'/>";
								html +="<input type='button' value='匹配失败' onclick='matchFail("+v.id+")'/>";
							} */
							html +="</td></tr>";
						})
				}else if(data.msg=="01"){
					html = "<tr class='paper-remark' ><td colspan='18' class='blue font20'>查询失败</td></tr>"
						document.getElementById('orderbasicThree').style.display="block";
					document.getElementById('orderBasicServerThree').style.display="none";
				}else if(data.msg=="02"){
					html = "<tr class='paper-remark' ><td colspan='18' class='blue font20'>暂无记录</td></tr>"
						document.getElementById('orderBasicServerThree').style.display="none";
						document.getElementById('orderbasicThree').style.display="block";
					
				}
				$("#threeOrderOutList").html(html);
				/*表格点击行高亮*/
		        trColor("#threeOrderOutList > tr");
			}
		});
		$("#threeOrderOutList > tr").eq(0).trigger("click");
	}

	// 基本信息显示
	function checkOrderBasic(orderId,cateType,userMobile,userId,city,cityName,totalPay,index,payStatus,orderStatus){
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
			document.getElementById('orderBasicServerThree').style.display="block";
			if(orderId!=null&&orderId!=""&&cateType!=null&&cateType!=""){
				setBasicServerThree(orderId,cateType,totalPay,orderStatus);
		}
	}

	/**
	 * 分页跳转使用
	 */
	function threeOrderOutList(num,pageCount) {
		$("#threeOrderOutList").empty();
		queryOrdersByLike(num,pageCount);
	}

	/**
	 * 模糊查询
	 */
	function queryOrdersByLike(num,pageCount){
		//setExportThreeOrders();		//设置excel导出参数
		document.getElementById('orderbasicThree').style.display="none";
		var ctx = $("#ctx").val();
		var orderCode=$("#threeOrderOrderCode").val();
		var userName=$("#threeOrderUserName").val();
		var createTimeStart = $("#threeOrderStartTime").val();
		var createTimeEnd = $("#threeOrderEndTime").val();
		var userMobile=$("#threeOrderUserMobile").val();
		var orderType=$("#threeOrderOrderType").val();
		var orderStatus=$("#threeOrderOrderStatus").val();
		var orderChannel=$("#threeOrderOrderChannel").val();
		var orderSourceId=$("#threeOrderOrderSourceId").val();
		var provinceCode= $("#threeOrderProvinces").val();
		var cityCode=$("#threeOrderCitys").val();
		$.ajax({
			url: ctx +"/threeOrderOut/queryOrder?curPage="+num+"&pageCount="+pageCount,
			data:{
				orderCode:orderCode,
				userName:userName,
				createTimeStart:createTimeStart,
				createTimeEnd:createTimeEnd,
				userMobile:userMobile,
				orderType:orderType,
				orderStatus:orderStatus,
				orderSourceId:orderSourceId,
				orderChannel:orderChannel,
				provinceCode:provinceCode,
				cityCode:cityCode
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				showMsg(data.msg);
				var html="";
				$("#threeOrderOutListPage").pagination(data.page,threeOrderOutList);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				if (data.msg =="00") {
					var num = 
						$.each(data.list,function(i,v){
							num=i+1;
							html +="<tr data-orderStatus='"+v.orderStatus+"' onclick=\"checkOrderBasic(" +v.id +"," +v.cateType +"," +("'"+v.userMobile+"'") +","  +v.userId +",'" +v.city +"','" +(v.receiverProvince +v.receiverCity) +"'," +v.totalPay +"," +i +","+v.payStatus+","+v.orderStatus +")\"><td>" ;
							html +="<input name='odId' type='checkbox' value='" +v.id +"'>";
							html +="</td><td>"+(total + num - pageCount);
							html +="</td><td align='left'>"+v.orderCode;
							html +="</td><td align='left'>"+v.brandName;
							html +="</td><td align='left'>"+v.userName;
							html +="</td><td>"+v.typeText;
							html +="</td><td>"+numberToDatestr(v.createTime,8); 
							html +="</td><td align='left'>";
							if('1'==v.orderStatus){
								html += "新单";
							}else if('2'==v.orderStatus){
								html += "已受理";
							}else if('3'==v.orderStatus){
								html += "匹配中";
							}else if('4'==v.orderStatus){
								html += "已匹配";
							}else if('5'==v.orderStatus){
								html += "待面试";
							}else if('6'==v.orderStatus){
								html += "面试成功";
							}else if('7'==v.orderStatus){
								html += "已签约";
							}else if('8'==v.orderStatus){
								html += "已上户";
							}else if('9'==v.orderStatus){
								html += "已完成";
							}else if('10'==v.orderStatus){
								html += "已取消";
							}else if('11'==v.orderStatus){
								html += "已下户";
							}else if('12'==v.orderStatus){
								html += "已终止";
							}else if('13'==v.orderStatus){
								html += "捡货中";
							}else if('14'==v.orderStatus){
								html += "配送中";
							}
							html +="</td><td align='left'>"+v.sourceText;
							html +="</td><td align='left'>"+v.channelText;
							/* html +="</td><td align='left'>"+ v.followByName ; */
							/* html +="</td><td align='left'>"+v.followDeptName; */
							html +="</td><td align='left'>"+v.rechargeByName;
							html +="</td><td align='left'>"+v.rechargeDeptName;
							html +="</td><td align='left'>"+v.realName;
							html +="</td><td align='left'>"+v.realNameDept;
							/* html +="</td><td align='left'>"+v.followTime; */
							html +="</td><td align='left'>";
							if('2'==v.orderStatus){
								html +="<input type='button' value='匹配成功' onclick='matchSuccess("+v.id+")'/>";
								html +="<input type='button' value='匹配失败' onclick='matchFail("+v.id+")'/>";
							}
							html +="</td></tr>";
						})
				}else if(data.msg=="01"){
					html = "<tr class='paper-remark' ><td colspan='18' class='blue font20'>查询失败</td></tr>"
						document.getElementById('orderbasicThree').style.display="block";
						document.getElementById('orderBasicServerThree').style.display="none";
				}else if(data.msg=="02"){
					html = "<tr class='paper-remark' ><td colspan='18' class='blue font20'>暂无记录</td></tr>"
						document.getElementById('orderbasicThree').style.display="block";
						document.getElementById('orderBasicServerThree').style.display="none";
				}
				$("#threeOrderOutList").html(html);
				/*表格点击行高亮*/
		        trColor("#threeOrderOutList > tr");
			}
		});
		$("#threeOrderOutList > tr").eq(0).trigger("click");
	}
	//重置查询订单条件
	function resetOrders(){
		$("#threeOrderOrderCode").val('');
		$("#threeOrderUserName").val('');
		$("#threeOrderUserMobile").val('');
		$("#threeOrderOrderType").val('');
		$("#threeOrderOrderStatus").val('');
	 	$("#threeOrderOrderSourceId").val("");
		$("#threeOrderOrderChannel").val('');
		$("#threeOrderProvinces").val("");
		$("#threeOrderCitys").val('');
		$("#threeOrderStartTime").val('');
		$("#threeOrderEndTime").val('');
	}

	
	/**
	 * 基本信息显示
	 */
	function checkThreeOrderOne(orderId){
		setThreeOrderOutOne(orderId);
	}
	
	/**
	*导出订单
	*/
	function setExportThreeOrders(){
		var orderCode=$("#threeOrderOrderCode").val();
		var userName=$("#threeOrderUserName").val();
		var startTime = $("#threeOrderStartTime").val();
		var endTime = $("#threeOrderEndTime").val();
		var userMobile=$("#threeOrderUserMobile").val();
		var orderType=$("#threeOrderOrderType").val();
		var orderStatus=$("#threeOrderOrderStatus").val();
		var orderSourceId=$("#threeOrderOrderSourceId").val();
		var orderChannel=$("#threeOrderOrderChannel").val();
		var provinceCode= $("#threeOrderProvinces").val();
		var cityCode=$("#threeOrderCitys").val();
		
		$('#theForm input[name="orderCode"]').val(orderCode);
		$('#theForm input[name="userName"]').val(userName);
		$('#theForm input[name="startTime"]').val(startTime);
		$('#theForm input[name="endTime"]').val(endTime);
		$('#theForm input[name="userMobile"]').val(userMobile);
		$('#theForm input[name="orderType"]').val(orderType);
		$('#theForm input[name="orderStatus"]').val(orderStatus);
		$('#theForm input[name="orderSourceId"]').val(orderSourceId);
		$('#theForm input[name="orderChannel"]').val(orderChannel);
		$('#theForm input[name="provinceCode"]').val(provinceCode);
		$('#theForm input[name="cityCode"]').val(cityCode);
	}
	
	/**
		*导出订单
		*/
	function exportThreeOrders(){
		var count = 0;
		$("#threeOrderOutList tr").each(function(i){
			if($(this).attr("data-orderStatus") == 4){
				count++;
			};
		})
		if(count == 0){
			$.alert({millis:3000,text:"当前页没有可导出记录!(己匹配状态)"})
			return;	
		} 
		$("#theForm").submit();
	}
	
	/**
	*导入订单
	*/
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
	                url: ctx+'/threeOrderOut/excelToList', //用于文件上传的服务器端请求地址
	                secureuri: false, //是否需要安全协议，一般设置为false
	                fileElementId: 'excelFile', //文件上传域的ID
	                dataType: 'json', //返回值类型 一般设置为json
	                success: function (data, status)  //服务器成功响应处理函数
	                {
	                	if(data.success){
	                		$.alert({text:"导入完成,请点击导入记录查看结果!"});
		                	queryOrders(0, 10);
	                	}else{
	                		$.alert({text:"导入失败，请检查excel表格式或表中有无数据！"});
	                	}
	                },
	            }
	        )
	        return false;
		}
	}
	
	/**
	*去匹配
	*/
	/* function doMatch(){
		var ctx = $("#ctx").val();
		var orderArray = $("input[name=odId]:checked");
		if(0==orderArray.length){
			$.alert({text:"请选择要匹配的订单！"});
			return false;
		}
		$.confirm({
			text : "确认受理这些订单？",
			callback : function(flag) {
				if(flag){
					var orderIds = "";
					orderArray.each(function(){
						orderIds = orderIds + $(this).val() + ",";
					})
					$.ajax({
						url: ctx +"/threeOrderOut/doMatch",
						data:{
							orderIds:orderIds
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.msg =="00") {
								$.alert({text:"操作成功！"});
								queryThreeOrders(0,10);
							}else if(data.msg =="01"){
								$.alert({text:"操作 失败！"});
								queryThreeOrders(0,10);
							}else if(data.msg =="02"){
								$.alert({text:"包含非“已受理”订单，非“已受理”订单不可进行匹配操作！"});
							}
						}
					});
				}
			}
		});
	} */
	
	/**
	*结单
	*/
	function doBilling(){
		var ctx = $("#ctx").val();
		var orderArray = $("input[name=odId]:checked");
		if(0==orderArray.length){
			$.alert({text:"请选择要结算的订单！"});
			return false;
		}
		
		$.confirm({
			text : "确认结算这些订单？",
			callback : function(flag) {
				if(flag){
					var orderIds = "";
					orderArray.each(function(){
						orderIds = orderIds + $(this).val() + ",";
					})
					$.ajax({
						url: ctx +"/threeOrderOut/doBilling",
						data:{
							orderIds:orderIds
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.msg =="00") {
								$.alert({text:"操作成功！"});
								queryThreeOrders(0,10);
							}else if(data.msg =="01"){
								$.alert({text:"操作 失败！"});
								queryThreeOrders(0,10);
							}else if(data.msg =="02"){
								$.alert({text:"包含非已匹配订单，非已匹配订单不可进行结单操作！"});
							}
						}
					});
				}
			}
		})
	}
	
	/**
	 *	匹配成功
	 */
	function matchSuccess(orderId){
		$.confirm({
			text : "已经为客户成功匹配服务，确认后订单状态变成已匹配！",
			callback : function(flag) {
				if(flag){
					var ctx = $("#ctx").val();
					$.ajax({
						url: ctx +"/threeOrderOut/matchSuccess",
						data:{
							orderId:orderId
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.msg =="00") {
								$.alert({text:"操作成功！"});
								queryThreeOrders(0,10);
							}
						}
					});
				}
			}
		})
	}
	
	/**
	 *	匹配失败
	 */
	function matchFail(orderId){
		var ctx = $("#ctx").val();
		var data = {orderId:orderId};
		openModule(ctx+'/jsp/order_three/out/threeOrderOutMatchFail.jsp', data, {}, {}, 'threeOrderOutMatchFail');
	}
	
	/**
	 *	模板下载
	 */
	function downloadTemplate(){
		var ctx = $("#ctx").val();
		window.location.href = ctx + "/upload/三方订单(给出)导出模板.xls"
	}
	
	/* 
	*导入记录信息 
	*/
	function downloadRecord(){
		var ctx = $("#ctx").val();
		openModule(ctx + '/jsp/order_three/threeOrderOutRecord.jsp','','',{width:'50%'},'threeOrderOutRecord');
	}
	
	function loadMap(){
		var ctx = $("#ctx").val();
		openModule(ctx + '/jsp/order_three/mapTest.jsp','','',{width:'80%'},'mapTest');
	}
	/* 导出三方订单订单 */
	function exportOrdersThreeOut(){
		var ctx = $("#ctx").val();
		var orderCode=$("#threeOrderOrderCode").val();
		var userName=$("#threeOrderUserName").val();
		var createTimeStart = $("#threeOrderStartTime").val();
		var createTimeEnd = $("#threeOrderEndTime").val();
		var userMobile=$("#threeOrderUserMobile").val();
		var orderType=$("#threeOrderOrderType").val();
		var orderStatus=$("#threeOrderOrderStatus").val();
		var orderChannel=$("#threeOrderOrderChannel").val();
		var orderSourceId=$("#threeOrderOrderSourceId").val();
		var provinceCode= $("#threeOrderProvinces").val();
		var cityCode=$("#threeOrderCitys").val();
	$.ajax({
		type:"post",
		url: ctx +"/threeOrderOut/exportExcelOut",
		data:{
			orderCode:orderCode,
			userName:userName,
			createTimeStart:createTimeStart,
			createTimeEnd:createTimeEnd,
			userMobile:userMobile,
			orderType:orderType,
			orderStatus:orderStatus,
			orderSourceId:orderSourceId,
			orderChannel:orderChannel,
			provinceCode:provinceCode,
			cityCode:cityCode
		},
		dataType:"json",
		type:"POST",
		success:function(data){
			window.open(ctx + "/threeOrderOut/downExcel?filename="+data.a, "_self");
			//window.location.href =data;
		},
			error:function(){
				$.alert({text:"失败！"});
		}		
	});
	}
	
	
	</script>
	</html>

