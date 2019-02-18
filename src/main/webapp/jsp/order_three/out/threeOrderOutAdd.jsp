<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		<%
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String mobile = request.getParameter("mobile");
		String createTime = request.getParameter("createTime");
		String cardType = request.getParameter("cardType");
		String cardNum = request.getParameter("cardNum");
		String city = request.getParameter("city");
		String userCity = request.getParameter("userCity");
		String cityCode = request.getParameter("cityCode");
		String isBigCust = request.getParameter("isBigCust");
		String isVip = request.getParameter("isVip");
		%>
	</script>
	<style type="text/css">
		.table-address{width: 70%; border: 1px solid #CCCCCC; padding: 10px; margin-left: 103px; margin-bottom: 10px;height: 100px;overflow-y:scroll;}
		.table-address td{text-align: left;}
		.order-add{height:650px; width: 900px;margin: 20px auto; }
		.otable-tr-top{height:25px; border-top:1px solid #CCC}
		.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
		.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
		.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
	</style>
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade"   id="modelFrameThreeOrderAdd">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setOrderCloseModule('threeOrderOutAdd')">×</button>
					<h4 class="modal-title">新增订单>>录入订单信息</h4>
				</div>
				<div class="modal-body">
					<form action="" method="post" class="form-inline">
						<input type="hidden" id="threeOrderOutAddUserId" name="threeOrderOutAddUserId" value="<%=id%>">
						<input type="hidden" id="threeOrderOutAddCityCode" name="threeOrderOutAddCityCode" value="<%=cityCode%>">
						<input type="hidden" id="threeOrderOutAddisBigCust" name="" value="<%=isBigCust%>">
						<input type="hidden" id="threeOrderOutAddisVip" name="" value="<%=isVip%>">
						<header class="mb10">
							<h4>客户信息</h4>
						</header>
						<div class="info-select clearfix">
							<div class="row">
								<div class="form-group col-xs-4">
									<label>客户姓名：<%=name %></label>
								</div>
								<div class="form-group col-xs-4">
									<label>性别：<%=sex %></label>
								</div>
								<div class="form-group col-xs-4">
									<label>联系方式：<%=mobile %></label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-4">
									<label>证件类型：<%=cardType %></label>
								</div>
								<div class="form-group col-xs-4">
									<label>证件号码：<%=cardNum %></label>
								</div>
								<div class="form-group col-xs-4">
									<label>所在城市：<%=city %></label>
								</div>
							</div>
						</div>
						<header class="mb10">
							<h4>订单信息</h4>
						</header>
						<div class="info clearfix">
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
										<span style="color: red">*</span>省份：
										<!-- <p><span style="color: red">*</span> 
										<p class="text-r">省份：</p> -->
										<select id="threeOrderOutAddProvince" class="form-control"   onclick="set_TOI_D_A_City('threeOrderOutAddProvince','threeOrderOutAddCity')"  >
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
									<label>
									<span style="color: red">*</span>城市：
								    	<!-- <p><span style="color: red">*</span> 
										<p class="text-r">城市：</p> -->
										<select id="threeOrderOutAddCity" class="form-control" onchange="javascript:selThreeOrderOutAddDistrict(this)">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
									    <span style="color: red">*</span> 
										一级分类：
										<select id="threeOrderOutAddCategoryOne" class="form-control" onchange="javascript:selThreeOrderOutCategoryOne(this)">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
									<label>
									    <span style="color: red">*</span> 
										二级分类：
										<select id="threeOrderOutAddCategoryTwo" class="form-control" onchange="javascript:selThreeOrderOutCategoryTwo(this)">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
									<label>
									     <span style="color: red">*</span> 
										三级分类：
										<select id="threeOrderOutAddCategoryThree" class="form-control" onchange="javascript:selThreeOrderOutProduct(this)">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
									  <span style="color: red">*</span> 
										选择产品：
										<select class="form-control" id="threeOrderOutAddProductCode"  onchange="selectPrice()">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
									<label>
									    <span style="color: red">*</span> 
										订单渠道： 
										<select class="form-control" id="threeOrderOutAddChannel">
											<option style='color:blue;' value=''>...请选择...</option>
										</select>
									</label>
									<!-- <label>
									     <span style="color: red">*</span> 
										订单来源：
										<select class="form-control" id="threeOrderOutAddSourceId">
											<option style='color:blue;' value=''>第三方订单</option>
										</select>
									</label> -->
								</div>
							</div>
							<div class="row">
								<div class="form-group col-xs-12">
									<label>
									    <span style="color: red">*</span> 
										数量(<span id="smallestUnit"></span>)：
										<input type="text" class="form-control"  id="number" 
										onblur="totalAmount()" onkeydown="inputOnlyNum(event);"
										onkeypress="checkIfNum(event);"   style="ime-mode:Disabled"">
									</label>
									<label>
										<p class="text-r">价格(元)：</p> 
										<input type="text" class="form-control" disabled="true " id="price" >
										<input type="hidden" class="form-control" disabled="true " id="hiddenPrice" >
										<input type="hidden" class="form-control" disabled="true " id="productUnit" >
										<input type="hidden" class="form-control" disabled="true " id="productPriceType" >
									</label>
									<label>
										<p class="text-r">价格体系：</p> 
										<input type="text" class="form-control" disabled="true " id="priceSystem" >
									</label>
									<label>
										<p class="text-r">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</p>
										<input type="text" class="form-control" disabled="true " id="spec" > 
									</label>
								</div>
							</div>
						</div>
						<div class="info-select mt20" style="border: 1px solid #CCC;">
							<div class="row">
								<div class="form-group col-xs-12">
									<div class="row">
										<div class="form-group col-xs-12">
											<label>
											    <span style="color: red">*</span> 
												服务时间:
												<input type="text" class="Wdate form-control" name="threeOrderOutAddStartTime" id="threeOrderOutAddStartTime"
													onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
												至
												<input type="text" class="Wdate form-control" name="threeOrderOutAddEndTime" id="threeOrderOutAddEndTime"
													onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
											</label>
										</div>
										<div class="form-group col-xs-12">
											<label>
											   <span style="color: red">*</span> 
												服务地址:
												<button class="btn btn-sm btn-default" type="button" onclick="threeOrderOutAddUserAddress()">
													<em class="add glyphicon glyphicon-plus-sign"></em>新增地址
												</button>
											</label>
										</div>
										<div class="form-group col-xs-12">
											<div class="table-address">
												<table id="threeOrderOutAddAddressTable"></table>
											</div>
										</div>
										<div class="form-group col-xs-12">
											<label class="form-label">
												<p>备注:</p>
												<textarea class="form-control" name="threeOrderOutAddRemark" id="threeOrderOutAddRemark" style="width: 70%;height: 40px"></textarea>
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderCloseModule('threeOrderOutAdd')">取消</button>
					<button type="button" id="submitButton" class="btn btn-sm btn-primary" onclick="saveOrder();">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var keyIsNum = true;
function inputOnlyNum(myEvent) {
	var keys = myEvent.keyCode;
	if (!((keys >= 48 && keys <= 57) || (keys >= 96 && keys <= 105)
			|| (keys == 8) || (keys == 46) || (keys == 37) || (keys == 39)
			|| (keys == 13) || (keys == 229))) {
		keyIsNum = false;
	} else {
		keyIsNum = true;
	}

}
function checkIfNum(myEvent) {
	if (!keyIsNum) {
		$.alert({
			text : "请输入数字！"
		});
		if (document.all)
			myEvent.returnValue = false;//ie
		else
			myEvent.preventDefault();//ff
	}

}
	$(function() {
		// 查询省市
		setSelProvinceCitys(101,6,"threeOrderOutAddProvince");
	//	selThreeOrderProvinceCitys("101", 6, "threeOrderOutAddProvince");
		// 查询出渠道列表
		queryBaseChannel(null,"threeOrderOutAddChannel");
	   // 	queryThreeOrderDictionary("threeOrderOutAddChannel","2019",null);
		// 查询出来源列表
	     //	queryThreeOrderDictionary("threeOrderOutAddSourceId","2018",null);
	});
	
	/* 查询城市 */
	function set_TOI_D_A_City(provinceId,cityId,countryId){
		var ctx=$("#ctx").val();
		var code=$("#" +provinceId +" option:selected").val();
		 if(code==null||code==""){
		    	code=-2;
		    }
		    setSelProvinceCitys(code,9,cityId);
		    if(countryId){
		    	$("#" +countryId).html("<option value=''>...请选择...</option>");
		    }
	    selThreeOrderOutAddReset("threeOrderOutAddCategoryOne");
		selThreeOrderOutAddReset("threeOrderOutAddCategoryTwo");
		selThreeOrderOutAddReset("threeOrderOutAddCategoryThree");
		selThreeOrderOutAddReset("threeOrderOutAddProductCode");
	}
	
	/* 查询区域 */
	function selThreeOrderOutAddDistrict(_this){
		var code = $("#threeOrderOutAddCity").val();
		if(code!=null&&code!=""){
			// 查询出一级分类
			selThreeOrderCategory("threeOrderOutAddCategoryOne", "",1 ,"");
		}else{
			selThreeOrderOutAddReset("threeOrderOutAddCategoryOne");
		}
		selThreeOrderOutAddReset("threeOrderOutAddCategoryTwo");
		selThreeOrderOutAddReset("threeOrderOutAddCategoryThree");
		selThreeOrderOutAddReset("threeOrderOutAddProductCode");
		queryThreeOrderAddress();
	}
	
	/* 查询商品的二级分类 */	
	function selThreeOrderOutCategoryOne(oc){
	    var code = oc.options[oc.options.selectedIndex].value;
	    if(code!=null&&code!=""){
	    	selThreeOrderCategory("threeOrderOutAddCategoryTwo",code,2,"");
	    }else{
	    	selThreeOrderOutAddReset("threeOrderOutAddCategoryTwo");
	    }
	    selThreeOrderOutAddReset("threeOrderOutAddCategoryThree");
	    selThreeOrderOutAddReset("threeOrderOutAddProductCode");
	}
	
	/* 查询商品的三级分类 */	
	function selThreeOrderOutCategoryTwo(oc){
	    var code = oc.options[oc.options.selectedIndex].value;
	    if(code!=null&&code!=""){
	    	selThreeOrderCategory("threeOrderOutAddCategoryThree",code,3,4);
	    }else{
	    	selThreeOrderOutAddReset("threeOrderOutAddCategoryThree");
	    }
	    selThreeOrderOutAddReset("threeOrderOutAddProductCode");
	}
	
	// 查询商品分类
	function selThreeOrderCategory(typeId,code,level,cateType){
		var ctx = $("#ctx").val();
		var cityCode = $("#threeOrderOutAddCity").val();
		$.ajax({
			url:ctx+"/threeOrderOut/queryCategory",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				code:code,
				cityCode:cityCode,
				level:level,
				cateType:cateType
			},
			success : function(data){
				if (data.msg == "00") {
					var html="";
					var result = data.list;
					var html = "<option style='color:blue;' value=''>...请选择...</option>";
					$.each(result,function(i, p) {
						html += "<option value='"+p.code+"'>"+p.cname+"</option>";  
					});
					$("#"+typeId).html(html);
				}else{
					$("#"+typeId).html("<option style='color:blue;' value=''>...无可选项...</option>");
				}
			}
		})
	}
	/**
	* 通过三级分类和城市code查询城市商品
	*/
	function selThreeOrderOutProduct(_this){
		var ctx = $("#ctx").val();
		var categoryCode = $(_this).val();
		var cityCode = $("#threeOrderOutAddCity option:selected").val();
		$.ajax({
			url:ctx+"/threeOrderOut/queryProduct",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				categoryCode:categoryCode,
				cityCode:cityCode
			},
			success : function(data){
				if (data.msg == "00") {
					var html="";
					var result = data.list;
					var html = "<option style='color:blue;' value='' >...请选择...</option>";
					$.each(result,function(i, p) {
						html += "<option value='"+p.code+"' >"+p.name+"</option>";  
					});
					$("#threeOrderOutAddProductCode").html(html);
				}else{
					$("#threeOrderOutAddProductCode").html("<option style='color:blue;' value=''>...无可选项...</option>");
				}
			}
		})
	}
	/**
	*通过商品查询市场价，最小单位
	*/
	function selectPrice(){
		var productCode = $("#threeOrderOutAddProductCode option:selected").val();
		var isVip = $("#threeOrderOutAddisVip").val();
		var isBigCust = $("#threeOrderOutAddisBigCust").val();
		if(isBigCust==1){
			dictCode="20000003";
		}else if(isBigCust==2){
			if(isVip==1){
				dictCode="20000006";
			}else if(isVip==2){
				dictCode="20000002";
			}
		}
		$.ajax({
			url:"/order/item/queryCityServerProduct",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				productCode : productCode,
				dictCode : dictCode,
				status : 1
			},
			success : function(data){
				if (data.msg == "00") {
					$.each(data.list,function(i,v){
						$("#smallestUnit").html(v.productUnitText);
						$("#priceSystem").val(v.priceText);
						$("#number").val(1);
						var price = v.priceText.split(":");
						$("#price").val(price[1]);
						$("#hiddenPrice").val(price[1]);
						$("#spec").val(v.productSpec);
						$("#productPriceType").val(v.productPriceType);
						$("#productUnit").val(v.productUnit);
					})
				}else{
					
				}
			}
		})
	}
	/*
	*算总金额
	*/
	function totalAmount(){
		var price = $("#hiddenPrice").val();
		var number = $("#number").val();
		$("#price").val(price*number)
	}
	function selThreeOrderOutAddReset(htmlId){
		var html = "<option style='color:blue;' value='' >...请选择...</option>";
		$("#"+htmlId).html(html);
	}
	
	function queryThreeOrderAddress(){
		var ctx = $("#ctx").val();
		var userId = $("#threeOrderOutAddUserId").val();
		var code = $("#threeOrderOutAddCity").val();
		if(code!=null&&code!=""){
			$.ajax({
				url:ctx+"/threeOrderOut/queryAddress",
				type:"post",
				datetype:"json",
				async : false,
				data:{
					userId:userId,
					cityCode:code
				},
				success : function(data){
					if (data.msg == "00") {
						var html="";
						var result = data.list;
						$.each(result,function(i, a) {
							html += "<tr>";
						/* 	if(a.isDisabled=="1"){ */
								html += "<td><input type='radio' name='threeOrderOutAddAddress' value='"+a.id+"'/></td>";
							/* }else{
								html += "<td><input type='radio' name='threeOrderOutAddAddress' disabled='disabled' value='"+a.id+"'/></td>";
							} */
							html += "<td>";
							html += a.contactName+"，"+a.contactPhone+"，";
							html += a.province+"，"+a.city+"，"+a.country+"，"+a.chooseAddress+","+a.addressDetail;
							html += "</td>";
							/* if(a.isFront=="2"){ */
								html += "<td style='padding-left:20px'><a href='javascript:threeOrderOutUpdateUserAddress("+a.id+")'>修改</a></td>";
							/* }else{
								html += "<td></td>";
							} */
							html += "</tr>";  
						});
						$("#threeOrderOutAddAddressTable").html(html);
					}
				}
			})
		}
		
	}
	
	function threeOrderOutAddUserAddress(){
		var ctx = $("#ctx").val();
		var userId = $("#threeOrderOutAddUserId").val();
		var data = {id : "", userId : userId};
		openModule(ctx+'/jsp/order_three/out/threeOrderOutUserAddress.jsp', data, {}, {width:'700px'}, 'threeOrderOutUserAddress');
	}
	
	function threeOrderOutUpdateUserAddress(id){
		var ctx = $("#ctx").val();
		var userId = $("#threeOrderOutAddUserId").val();
		var data = {id : id, userId : userId};
		openModule(ctx+'/jsp/order_three/out/threeOrderOutUserAddress.jsp', data, {}, {width:'700px'}, 'threeOrderOutUserAddress');
	}

	//确定,保存按钮方法
	function saveOrder() {
		$("#submitButton").attr("disabled", true);
		var ctx = $("#ctx").val();
		var userId = $("#threeOrderOutAddUserId").val();
		var cityCode = $("#threeOrderOutAddCity option:selected").val();
		var orderType = $("#threeOrderOutAddCategoryThree").val();
		var productCode = $("#threeOrderOutAddProductCode").val();
		var orderChannel = $("#threeOrderOutAddChannel").val();
	//	var orderSourceId = $("#threeOrderOutAddSourceId").val();
		var startTime = $("#threeOrderOutAddStartTime").val();
		var endTime = $("#threeOrderOutAddEndTime").val();
		var userAddressId = $("input[name=threeOrderOutAddAddress]:checked").val();
		var remark = $("#threeOrderOutAddRemark").val();
		var quantity = $("#number").val();//数量
		var price = $("#price").val();//总价格
		var hiddenPrice = $("#hiddenPrice").val();//单价
		var spec = $("#spec").val();//规格 
		var productUnit = $("#productUnit").val();//最小单位
		var productPriceType = $("#productPriceType").val();//价格体系
		var params = {
			userId : $.trim(userId),
			cityCode : cityCode,
			orderType : $.trim(orderType),
			productCode : $.trim(productCode),
			orderChannel : $.trim(orderChannel),
			orderSourceId : "20180007",
			startTime : $.trim(startTime),
			endTime : $.trim(endTime),
			userAddressId : $.trim(userAddressId),
			remark : $.trim(remark),
			totalPay : price,//order总价格
			productUnit : productUnit,//ietm商品最小单位
			quantity : quantity,//ietm数量
			price : hiddenPrice,//ietm单价
			productSpec : spec,//item规格
			priceType : productPriceType//ietm和order价格体系
		};
		if (checkThreeOrderData(params)) {
			$.ajax({
				url : ctx + "/threeOrderOut/saveOrder",
				type : "post",
				datetype : "json",
				async : false,
				data : params,
				success : function(data) {
					if (data.msg == "00") {
						queryOrdersByLike(0, 10);
						closeModule("threeOrderOutAdd");
					}
				}
			})
			$("#submitButton").attr("disabled", false);
		}
	}

	function checkThreeOrderData(params) {
		var provinceCode = $("#threeOrderOutAddProvince").val();
		if (provinceCode == null || provinceCode == "") {
			$.alert({
				text : "请选择省份！"
			});
			return false;
		}
		if (params.cityCode == null || params.cityCode == "") {
			$.alert({
				text : "请选择城市！"
			});
			return false;
		}
		var oneType = $("#threeOrderOutAddCategoryOne").val();
		if (oneType == null || oneType == "") {
			$.alert({
				text : "请选择一级分类！"
			});
			return false;
		}
		var twoType = $("#threeOrderOutAddCategoryTwo").val();
		if (twoType == null || twoType == "") {
			$.alert({
				text : "请选择二级分类！"
			});
			return false;
		}
		if (params.orderType == null || params.orderType == "") {
			$.alert({
				text : "请选择三级分类！"
			});
			return false;
		}
		if (params.productCode == null || params.productCode == "") {
			$.alert({
				text : "请选择产品！"
			});
			return false;
		}
		if (params.orderChannel == null || params.orderChannel == "") {
			$.alert({
				text : "请选择订单渠道！"
			});
			return false;
		}
		/* if (params.orderSourceId == null || params.orderSourceId == "") {
			$.alert({
				text : "请选择订单来源！"
			});
			return false;
		} */
		var number = $("#number").val();
		if (number == null || number == "") {
			$.alert({
				text : "请输入数量！"
			});
			return false;
		}
		var startTime = $("#threeOrderOutAddStartTime").val();
		var endTime = $("#threeOrderOutAddEndTime").val();
		if (params.startTime == null || params.startTime == "") {
			$.alert({
				text : "请输入服务开始时间！"
			});
			return false;
		}
		if (params.endTime == null || params.endTime == "") {
			$.alert({
				text : "请输入服务结束时间！"
			});
			return false;
		}
		try {
			var st = new Date(params.startTime.replace(/\-/g, "\/"));
			var et = new Date(params.endTime.replace(/\-/g, "\/"));
			if (et < st) {
				$.alert({
					text : "开始时间大于结束时间。"
				});
				return false;
			}
		} catch (e) {
			$.alert({
				text : "时间格式不正确。"
			});
			return  false;
	}
		if (params.userAddressId == null || params.userAddressId == "") {
			$.alert({
				text : "请选择服务地址！"
			});
			return false;
		}
		
		return true;
	}
</script>
</html>