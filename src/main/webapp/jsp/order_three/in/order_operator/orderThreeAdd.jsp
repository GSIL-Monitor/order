<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!-- 所有的ID的前缀:T--Three,O--Order,I--In,A--Add -->
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
			String addr = request.getParameter("addr");
			String isBigCust = request.getParameter("isBigCust");
			String isVip = request.getParameter("isVip");
			%>
		</script>
		<style type="text/css">
			.threeOrderAdd{height:650px; width: 900px;margin: 20px auto; }
			.otable-tr-top{height:25px; border-top:1px solid #CCC}
			.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
			.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
			.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
		</style>
		<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
	</head>
	<body>
		<div class="modal fade">  
			<div class="modal-dialog" >  
				<div class="modal-content">
					<div class="modal-header">  
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" 
							onclick="closeModule('threeOrderAdd')">×</button>
						<h4 class="modal-title" >新增订单>>录入订单信息</h4>
		      		</div> 
					<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
					<form class="form-inline">
						<div class="modal-body" id="TOI_A_FormId">
							<!-- 添加订单的user_id -->
							<input type="hidden" id="TOI_A_userId" value="<%=id%>" />
							<input type="hidden" id="TOI_A_cateType" value="0" >
							<input type="hidden" id="TOI_A_mobiles" value="<%=mobile%>" />
							<input type="hidden" id="TOI_A_userCity" value="<%=userCity%>" />
							<input type="hidden" id="TOI_A_addr" value="<%=addr%>" />
							<input type="hidden" id="threeOrderOutAddisBigCust" name="" value="<%=isBigCust%>">
							<input type="hidden" id="threeOrderOutAddisVip" name="" value="<%=isVip%>">
							<input type="hidden" id="TOI_A_userAddrId" name="TOI_A_userAddrId" />
							<header>
								<h4>客户信息</h4>
							</header>
							<div class="info-select clear-fix">
								<div class="row">
									<div class="form-group col-xs-4">
										<label class="has-feedback">
											<p>客户姓名：</p>
											<span><%=name %></span> 
										</label>
									</div>
									<div class="form-group col-xs-4">
										<label class="has-feedback">
											<p>性&nbsp;&nbsp;&nbsp;&nbsp;别：</p>
											<span><%=sex %></span>
										</label>
									</div>
									<div class="form-group col-xs-4">
										<label>
											<p>联系方式：</p>
											<span><%=mobile %></span>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-4">
										<label>
											<p>证件类型：<p>
											<span><%=cardType %></span> 
										</label>
									</div>
									<div class="form-group col-xs-4">
										<label>
											<p>证件号码：</p>
											<span><%=cardNum %></span>
										</label>
									</div>
									<div class="form-group col-xs-4">
										<label>
											<p>所在城市：</p>
											<span><%=city %></span>
										</label>
									</div>
								</div>
							</div>
							<header class="mb10">
								<h4>订单信息</h4>
							</header>
							<div class="info-select clear-fix">
								<div class="row">
									<div class="form-group col-xs-12">
									<label>
										省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:
										<select  id="TOI_A_province" class="form-control" onchange="javascript:tOI_A_changeProvince();">
										</select>
									</label>
								
									<label>
										城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:
										<select  id="TOI_A_city" class="form-control" onchange="javascript:toI_A_changeCity();">
										</select>
									</label>
									</div>
								</div>
								<div class="row" id="TOI_A_classify_id" style="display:none;">
									<div class="form-group col-xs-12">
										<label>
											一级分类:
											<select  id="TOI_A_oneClassify" class="form-control" onchange="javascript:firstChange(this);">
												<option value="-1">---请选择---</option>
											</select>
										</label>
									
										<label>
											二级分类:
											<select  id="TOI_A_twoClassify" class="form-control" onchange="javascript:secondChange(this);">
												<option value="-1">---请选择---</option>
											</select>
										</label>
									
										<label>
											三级分类:
											<select  id="TOI_A_threeClassify" class="form-control" onchange="javascript:serverProductPages(this);">
												<option value="-1">---请选择---</option>
											</select>
										</label>
										<label id="TOI_A_fourClassify" style="display:none;">
											产品:
											<select class="form-control" id="TOI_A_serverType">
									  		</select>
										</label>
										<!-- 商品的最小单位 -->
										<div style="display:none" id="TOI_A_LeastUnit_id">
										</div>
									</div>
								</div>
							</div>
							<div class="modal-content-basic">
							<!--  style="height:410px;" -->
								<div id="TOI_A_conversionPage" class="info-select clear-fix table-scroll-y" style="height:270px;">
								</div>
							</div>
				  		</div>
						<div class="modal-footer" style="text-align: center;">  
							<!-- <button type="button" class="btn btn-sm btn-primary" onclick="saveThreeOrderIn();" >保存</button> -->
							<button type="button" class="btn btn-sm btn-primary" onclick="saveThreeOrderIn();" >保存</button> 
	        				<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('threeOrderAdd')" >取消</button>  
						</div>  
					</form>
				</div>
			</div>
		</div>   
	</body>
	<script type="text/javascript">
		// 初始化分类，产品
		function initClassifyAndProduct(typeId,isInitProduct){
			if("TOI_A_oneClassify"==typeId){
				$("#TOI_A_twoClassify").empty();
				$("#TOI_A_twoClassify").html( "<option style='color:blue;' value='0' >...请选择...</option>");
				$("#TOI_A_threeClassify").empty();
				$("#TOI_A_threeClassify").html( "<option style='color:blue;' value='0' >...请选择...</option>");
				$("#TOI_A_fourClassify").hide();
			}else if("TOI_A_twoClassify"==typeId){
				$("#TOI_A_threeClassify").empty();
				$("#TOI_A_threeClassify").html( "<option style='color:blue;' value='0' >...请选择...</option>");
				$("#TOI_A_fourClassify").hide();
			}if("TOI_A_threeClassify"==typeId){
				$("#TOI_A_fourClassify").hide();
			}
			if(isInitProduct){
				//隐藏分类
				$("#TOI_A_classify_id").hide();
				//清除产品
				$("#TOI_A_conversionPage").empty();
				//初始化产品服务类型
				$("#TOI_A_cateType").val(0);
				
				$("#TOI_A_userAddrId").val("");
				$("#TOI_A_userAdde_proVersion").val("");
			}
		}
		
		//加载默认省份
		$(function() {
			selProvinceCitys("101",6,"TOI_A_province");
			$("#TOI_A_city").prepend("<option>...请选择...</option>");
			$("#TOI_A_district").prepend("<option>...请选择...</option>");
		});
		//切换省份，获得城市信息
		function tOI_A_changeProvince(){
			var code=$("#TOI_A_province").val();
		    if(code && $.trim(code)!=null){
		    	selProvinceCitys(code,9,"TOI_A_city");
		    }else{
		    	$("#TOI_A_city").empty();
		    	$("#TOI_A_city").prepend("<option>...请选择...</option>");
		    }
		    $("#TOI_A_district").empty();
			$("#TOI_A_district").prepend("<option>...请选择...</option>");
			
			//省份切换，重新获取产品分类，以及产品信息
			initClassifyAndProduct("TOI_A_oneClassify",true);
		}
		//切换城市，获得区域信息
		function toI_A_changeCity(){
			var code=$("#TOI_A_city").val();
			//省份切换，重新获取产品分类，以及产品信息
			initClassifyAndProduct("TOI_A_oneClassify",true);
			tOI_A_getClassifyByCity('TOI_A_oneClassify',1,'1001');
		}
		
		//根据cityCode获得类别
		function tOI_A_getClassifyByCity(classifyTypeId,level,categoryCode){
			
			var cityCode=$("#TOI_A_city").val();
		  	if(cityCode && $.trim(cityCode)!=""){
		  		//根据城市获得分类
		  		toi_a_queryCategory(classifyTypeId,categoryCode,level,cityCode);
		  		$("#TOI_A_classify_id").show();
		  	}else{
		  		$("#TOI_A_district").empty();
		  		$("#TOI_A_district").prepend("<option value='no'>请选择...</option>");
		  	}
		}
		
		 //一级分类发生改变获得二级分类信息
		function firstChange (oc){
		    var code = oc.options[oc.options.selectedIndex].value;
		    tOI_A_getClassifyByCity("TOI_A_twoClassify",2,code);
		}
		
		
		//二级分类发生改变触发三级分类 
		function secondChange(oc){
		    var code = oc.options[oc.options.selectedIndex].value;
		    tOI_A_getClassifyByCity("TOI_A_threeClassify",3,code);
		}
		/*
		*算总金额
		*/
		function totalAmount(){
			var price = $("#hiddenPrice").val();
			var number = $("#number").val();
			$("#price").val(price*number)
		}
		// 查询商品分类
		function toi_a_queryCategory(typeId,code,level,currCityCode){
			var ctx = $("#ctx").val();
			$.ajax({
				url:ctx+"/threeOrder/queryProductCategory",
				type:"post",
				datetype:"json",
				async : false,
				data:{
					level:level,
					code:code,
					cityCode :currCityCode
				},
				success : function(data){
					//初始化分类
					initClassifyAndProduct(typeId,false);
					if (data.msg == "00") {
						var html="";
						var result = data.list;
						var html = "<option style='color:blue;' value='0' >...请选择...</option>";
						if("TOI_A_threeClassify"==typeId){
							$.each(result,function(i, p) {
								html += "<option value='"+p.code +"," +p.cateType +"' >"+p.cname+"</option>";  
							});
						}else {
							$.each(result,function(i, p) {
								html += "<option value='"+p.code+"' >"+p.cname+"</option>";  
							});
						}
						$("#"+typeId).html(html);
					}else{
						$("#"+typeId).html("<option style='color:blue;' value='0' >...无可选项...</option>");
					}
				}
			});
		}
		
		// 根据三级分类来切换产品类型
		function serverProductPages(oc){
			var ctx = $("#ctx").val();
			//改变前的服务类型
			var pre_cate_type = $.trim($("#TOI_A_cateType").val());
			
			var values = oc.options[oc.options.selectedIndex].value
			var val = values.split(",");
			//选择后的服务类型
			var type = val[1];
			
			var is_switch_server_div = false; 
			if(pre_cate_type === type && $("#TOI_FA_tbody").length>0){
				is_switch_server_div =  true;
			}
			$("#TOI_A_cateType").val(type);
			//三级分类
			var category_code= val[0];
			//用户Id
			var user_id = $.trim($("#TOI_A_userId").val());
			//用户city
			var city_code = $.trim($("#TOI_A_city").val());
			
			if(f_TOI_isEmpty(category_code)){
				$.alert({text:"请选择三级分类"});
				return;
			}
			if(f_TOI_isEmpty(user_id)){
				$.alert({text:"请选择用户."});
				return;
			}
			if(f_TOI_isEmpty(city_code)){
				$.alert({text:"您选择用户的用户没有城市信息."});
				return;
			}
			//获得商品
			$.ajax({
				url:ctx+"/threeOrder/getThreeOrderProductByCategroy",
				type:"post",
				datetype:"json",
				async : false,
				data:{
					user_id : user_id,
					city_code : city_code,
					category_code : category_code
				},
				success : function(data){
					if(data.msg == "00"){
						var result = data.list;
						var htm = "";  
						var htm = "<option style='color:blue;' value='0' >...请选择...</option>";
						var htm_leastUnit = "";
						if(type == 3){
							 //Fa商品
							 if(is_switch_server_div){
								 TOI_FA_queryProduct(result);
							 }else{
								 getOrderViewByServerType("/jsp/order_three/in/order_operator/orderThreeFA.jsp",result);
							 }
						}else{
							$.each(result,function(i, p) {
								var product_name = p.product_name;
								if(p.typeSpecValue !=null && p.typeSpecValue !='' && p.typeSpecValue) {
									product_name +="("+p.typeSpecValue+")";
								}
								htm += "<option value='"+p.product_code +"' onclick='selectPrice()'>"+p.product_name+"</option>";  
								//最小单位
								htm_leastUnit +="<input id='"+p.product_code+"' value='"+p.leastUnit+"'/>"
							});
							$("#TOI_A_serverType").html(htm);
							$("#TOI_A_LeastUnit_id").html(htm_leastUnit);
							$("#TOI_A_fourClassify").css("display","inline");
						 }
						
						if(type == 1 || type==4){
							//自营单次，他营单次
							getOrderViewByServerType("/jsp/order_three/in/order_operator/orderThreeServerSingle.jsp");
						}else if(type == 2){  
							//自营延续
							//打开第四级目录
							getOrderViewByServerType("/jsp/order_three/in/order_operator/orderThreeServerExtended.jsp");
						}
					}else if(data.msg == "01"){
						$.alert({text:data.errInfo});
						$("#TOI_A_serverType").html("<option value='err' >没有相应的产品</option>");
						//$("#TOI_A_conversionPage").empty();
					}
				}
			});
		}
		/**
		*通过商品查询市场价，最小单位
		*/
		function selectPrice(){
			var productCode = $("#TOI_A_serverType option:selected").val();
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
		//确定--保存按钮方法
		function saveThreeOrderIn(){
			var cityCode=$("#TOI_A_city").val();
			if(!cityCode || cityCode ==""){
				$.alert({text:"请选择城市"});
				return;
			}
			var cateType = $("#TOI_A_cateType").val();
			var result=1;
			if(cateType == 1 || cateType == 4 ){
				result=saveServerSingle(cateType);
			}else if(cateType == 2){
				result=saveServerExtended();
			}else if(cateType == 3){
				result=saveFa();
			}else{
				$.alert({text:"请选择类别"});
				return;
			}
			// 如果保存成功，刷新父页面并关掉窗口
			if(result==0){
				parent.queryOrders(0,10);
				closeModule("threeOrderAdd");
			}
		}
		//根据订单的类型，切换页面
		function getOrderViewByServerType(url,result){
			var ctx = $("#ctx").val();
			$.ajax({
				url:ctx+url,
				data:{
					param : result
				},
				type:"post",
				datetype:"json",
				async : false,
				success:function(data){
					$("#TOI_A_conversionPage").html(data);
					if(result){
						TOI_FA_queryProduct(result);
					}
				}
			});
		}
		
		//获得用户地址管理
		function getAddrListByUser(domId) {
			var ctx = $("#ctx").val();
			var userId = $("#TOI_A_userId").val();
			var code=$("#TOI_A_city").val();
			$.ajax({
				url : ctx + "/threeOrder/getCustomerAddrList",
				data : {
					userId:userId,
					valid : 1,
					cityCode:code
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					var html = "";
					if (data.msg == "00") {
						var num = $.each(data.list,function(i, v) {
							num = i + 1;
							var isChecked ="";
							if(i == 0 && v.isDisabled == 1 ){
								isChecked = "checked";
								$("#TOI_A_userAddrId").val(v.id);
							}
							html += "<table style=\"height:44px; width:100%; border:1px solid #CCC; margin-top:5px;\" >";
							html += "<tr style=\"height:22px;\">";
							html += "<td rowspan='2' style='text-align:center; width:58px;'>";
							
							
							html += "<input name='addressId' type='radio' "+isChecked+" id='TOI_A_address"+ v.id
								 + "' onclick=TOI_FA_getaddressValue("+ v.id + ");></td>";
							
							
							html += "<td style='text-align:center;width:105px;'>"
									+ v.contactName
									+ "</td>";
							html += "<td style='text-align:center;width:275px;'>"
									+ v.contactPhone
									+ "</td>";
						
							html += "<td rowspan='2' style='text-align:center;width:67px;'><div><a "
									+ " href='javascript:TOI_FA_orderFaEditAddr("
									+ v.id
									+ ")' >修改</a></div></td>";
							
							
							html += "</tr>";
							html += "<tr style='height:22px;'>";
							html += "<td colspan='2' style='text-align:center;width:385px;'>"
									+ v.province+v.city +v.addressChooseAddress+ v.addressDetail
									+ "</td>";
							html += "</tr>";
							html += "</table>";
						});
					}else{
						$.alert({text:data.errInfo})
					}
					$("#"+domId).html(html);
				}
			});
		}
		
		//复选框设置
		function TOI_FA_getaddressValue(addressId) {
			var tf = document.getElementById("TOI_A_address" + addressId + "").checked;
			$("input[name='addressId']:enabled").prop("checked", false);
			// 每次选择，把需要取到的id设置为0
			if (tf) {
				$("#TOI_A_userAddrId").val(addressId);
			} else {
				$("#TOI_A_userAddrId").val('');
			}
			$("input[id='TOI_A_address" + addressId + "']:enabled").prop("checked", tf);
		}
		
		// 编辑地址
		function TOI_FA_orderFaEditAddr(id){
			$("#TOI_A_userAddrId").val(id);
			if($("#TOI_A_userAddrId").val()==""){
				$.alert({text:"请选择一条数据修改！"});
				return false;
			}else{  
				openModule('/order/jsp/order_three/in/addr_operator/orderThreeFA_AddrAdd.jsp',{lx:2},'','','orderThreeFA_AddrAdd');
			}
		}
	</script>
</html>

