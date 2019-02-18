<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- 命名规则TOI-ThreeOrderIn,D--delivery A--Address -->
<html>
<head>
	<title>Insert title here</title>
	<%
		String lx = request.getParameter("lx");
	    String delivery_addr_contactId = request.getParameter("delivery_addr_contactId");
	%>
	<c:set var="lx" value="<%=lx%>"></c:set>
	<style type="text/css">
		#baiduMapUserAddress {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		#r-result-userAddress{width:100%;}
		.tangram-suggestion-main {z-index: 1060;}
		.tangram-suggestion table tr td {text-align: left;}
		.tangram-suggestion table tr td i{display: inline;}
		.address-map-area{width: 500px;height: 250px;margin-left: 103px;border: 1px solid #CCCCCC;}
	</style>
</head>
<body>
	<div class="modal fade" >  
		<div class="modal-dialog">  
	    	<div class="modal-content" >
				<div class="modal-header">  
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
					<h4 class="modal-title">
						<c:if test="${lx == 1 }">
						新增收货地址>>接收人信息
						</c:if>
						<c:if test="${lx == 2 }">
						编辑收货地址>>接收人信息
						</c:if>
					</h4>
				</div> 
				<input type="hidden" name="TOI_D_A_version" id="TOI_D_A_version" />
				<input type="hidden" name="TOI_D_A_valid" id="TOI_D_A_valid" />
				<input type="hidden" name="TOI_D_A_isDefault" id="TOI_D_A_isDefault" />
				<input type="hidden" name="TOI_D_A_version" id="TOI_D_A_version" />
				<input type="hidden" id="TOI_D_A_addr_lng" />
	      		<input type="hidden" id="TOI_D_A_addr_lat"  />
	      		<form class="form-inline" id="TOI_D_A_formAdd">
				<div class="modal-body">
	      			<div class="row">
	      				<div class="form-group col-xs-12">
	      					 <label><p><span style="color: red">*</span>联系人：</p>
								 <input id="TOI_D_A_contactName" class="form-control" type="text" name="TOI_D_A_contactName">
								 </label>
	      				</div>
	      			</div>
	      			<div class="row">
	      				<div class="form-group col-xs-12">
	      					 <label><p><span style="color: red">*</span>手机号码：</p>
								 <input id="TOI_D_A_contactPhone" class="form-control" type="text" name="TOI_D_A_contactPhone">
								 </label>
	      				</div>
	      			</div>
	      			<div class="row">
	      				<div class="form-group col-xs-12">
	      					<label><span style="color: red">*</span>省份：
								 <select name="TOI_D_A_province" class="form-control" style="width:120px;" id="TOI_D_A_province"  onchange="set_TOI_D_A_City()"></select>
							</label>
	      				</div>
	      			</div>
	      			<div class="row">
	      				<div class="form-group col-xs-12">
	      					 <label><span style="color: red">*</span>城市：
								 <select name="TOI_D_A_city" class="form-control" style="width:120px;" id="TOI_D_A_city" onchange="setTOI_D_A_district()"></select>
							 </label>
	      				</div>
	      			</div>
	      			<div class="row">
	      				<div class="form-group col-xs-12">
	      					<label>区县：
								 <select name="TOI_D_A_district" class="form-control" style="width:120px;" id="TOI_D_A_district" ></select>
							</label>
	      				</div>
	      			</div>
	      			<div class="row">
							   <div class="form-group col-xs-12" id="r-result-userAddress">
								 <label><p><span style="color: red">*</span>详细地址：</p>
								 <input id="addressDetail" class="form-control" style="width:400px;" type="text" name="addressDetail" placeholder="请输入具体地址：如1号楼3单元502">
								 </label>
								</div>
					</div>
	      			 <div class="row">
							   <div class="form-group col-xs-12">
								 <label><p><span style="color: red">*</span> 选择地址：</p>
								 <input id="addressChooseAddress" class="form-control" style="width:400px;" type="text" name="addressChooseAddress">
								
								 </label>
								</div>
							</div>
							<div class="row">
								<div style="margin-left:10px; width:550px; height:250px; border:1px solid #CCC;">
						      		<div id="baiduMapUserAddress"></div>
								</div>
							</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="closeModule('orderThreeFA_AddrAdd');">取消</button>  
					<!--  <button type="submit" class="btn btn-sm btn-primary">保存</button>  -->
				<!-- 	<button type="button" class="btn btn-sm btn-primary" onclick="saveOrderUser()">保存</button>  -->
				 	<button type="button" class="btn btn-sm btn-primary" onclick="saveOrderUser1()">保存</button>  
				</div>  
				</form>
			</div>
		</div>
	</div>     
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=initUserAddress" ></script>
<script type="text/javascript">
var lx=${lx};
//加载默认省份
$(function() {
	selProvinceCitys("101",6,"TOI_D_A_province");
	$("#TOI_D_A_city").prepend("<option>...请选择...</option>");
	$("#TOI_D_A_district").prepend("<option>...请选择...</option>");
	if(lx==2||lx=="2"){
		editer();
	}
});

function initUserAddress() {
	//加载百度地图
	var mapTool = MapTool();
	mapTool.init("baiduMapUserAddress", function(data) {
		//通过输入地址
		var auc = mapTool.autoComplete("addressChooseAddress", function(obj, addr,short_addr) {
			auc.val(short_addr);
			mapTool.searchPoint(addr, function(point) {
				mapTool.clearOverlays();
				mapTool.addMarker(point);
				mapTool.panTo(point);
				$("#TOI_D_A_addr_lng").val(point.lng);
				$("#TOI_D_A_addr_lat").val(point.lat);
			})
		});
		//通过地图打点
		mapTool.click(function(event) {
			mapTool.clearOverlays();
			mapTool.addMarker(event.point);
			$("#TOI_D_A_addr_lng").val(event.point.lng);
			$("#TOI_D_A_addr_lat").val(event.point.lat);
			mapTool.getAddrByPoint(event.point, function(addr,short_addr) {
				auc.val(short_addr);
			});
		});
		var longt = $("#TOI_D_A_addr_lng").val();
		var latit = $("#TOI_D_A_addr_lat").val();
		// 如果没有经纬度，默认定位到北京
		if(longt==null||longt==""||latit==null||latit==""){
			longt = 116.404;
			latit = 39.951;
		}
		//坐标回显
		mapTool.addMarker(longt,latit);
		mapTool.panTo(new BMap.Point(longt,latit));
	});
	
}

//回显信息
function editer(){
	
		var userAddrId = parent.document.getElementById("TOI_A_userAddrId").value;
		var ctx=$("#ctx").val();
		 $.ajax({
			 url:ctx + "/threeOrder/getCustomerAddrByAddrId",
			 data:{
				 id:userAddrId,
				 valid:1
			 },
			 type:'post',
			 dataType : "json",
			 async:false,
			 success:function(data){
				if (data.msg == "00") {
					var obj = data.list;
					$("#TOI_D_A_version").val(obj.version);
					$("#TOI_D_A_contactName").val(obj.contactName);
					$("#TOI_D_A_contactPhone").val(obj.contactPhone);
				
					$("#TOI_D_A_province").val(obj.cityCode.substr(0,6));
					set_TOI_D_A_City();
					$("#TOI_D_A_city").val(obj.cityCode.substr(0,9));
					setTOI_D_A_district();
					$("#TOI_D_A_district").val(obj.cityCode);
					
					$("#TOI_D_A_addr_lng").val(obj.longitude); 
					$("#TOI_D_A_addr_lat").val(obj.latitude);
					$("#addressDetail").val(obj.addressDetail);
					$("#addressChooseAddress").val(obj.addressChooseAddress);
					
				}
			 }
		});
	
}; 
//省份change,重新生产city，同时跟新城市的validate
function set_TOI_D_A_City(){
	var ctx=$("#ctx").val();
    var code=$("#TOI_D_A_province option:selected").val();
    if(code && $.trim(code)!=null){
    	selProvinceCitys(code,9,"TOI_D_A_city");
    }else{
    	$("#TOI_D_A_city").empty();
    	$("#TOI_D_A_city").prepend("<option>...请选择...</option>");
    }
    $("#TOI_D_A_district").empty();
	$("#TOI_D_A_district").prepend("<option>...请选择...</option>");
	
}
function setTOI_D_A_district(){
	var ctx=$("#ctx").val();
  	var code=$("#TOI_D_A_city option:selected").val();
  	if(code && $.trim(code)!=""){
  		selProvinceCitys(code,12,"TOI_D_A_district");
  	}else{
  		$("#TOI_D_A_district").empty();
  		$("#TOI_D_A_district").prepend("<option value='no'>请选择...</option>");
  	}
}
		
//保存新地址
function saveOrderUser1(){
	var ctx=$("#ctx").val();
	var delivery_addr_name = $.trim($("#TOI_D_A_contactName").val());
	var delivery_addr_valid = $.trim($("#TOI_D_A_valid").val());
	var delivery_addr_isDefault = $.trim($("#TOI_D_A_isDefault").val());
	var delivery_addr_Phone = $.trim($("#TOI_D_A_contactPhone").val());
	var addressDetail=$.trim($("#addressDetail").val());
	var delivery_addr_province = $.trim($("#TOI_D_A_province option:selected").text());
	var delivery_addr_province1 = $.trim($("#TOI_D_A_province option:selected").val());
	var delivery_addr_city =$.trim($("#TOI_D_A_city option:selected").text());
	var delivery_addr_district =  $.trim($("#TOI_D_A_district option:selected").text());
	var delivery_addr_CityCode = $("#TOI_D_A_district option:selected").val();
	var addressChooseAddress =  $.trim($("#addressChooseAddress").val());
	
	var province =  $.trim($("#TOI_D_A_province option:selected").text());
	var city =  $.trim($("#TOI_D_A_city option:selected").text());
	var district =  $.trim($("#TOI_D_A_district option:selected").text());
	var lng = $("#TOI_D_A_addr_lng").val();
	var lat = $("#TOI_D_A_addr_lat").val();
	
	var delivery_addr_userId = parent.document.getElementById("TOI_A_userId").value ;
	
	
	if(delivery_addr_name==null||delivery_addr_name==""){
		$.alert({text:"请输入联系人！"}); 
		return ;
	}
	if(!(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/.test(delivery_addr_Phone))){
		$.alert({text:"手机号码为空或者格式不正确！"});
		return ;
	}
	if(delivery_addr_CityCode=="...请选择..."){
		$.alert({text:"请选择城市、区域！！"});
		return ;
	}
	if(addressDetail==null||addressDetail==""){
		$.alert({text:"请输入详细地址！"});
		return ;
	}
	if(addressChooseAddress==null||addressChooseAddress==""){
		$.alert({text:"请选择地址！"});
		return ;
	}
	var userAddrId ;
	if(lx==1||lx=="1"){
		userAddrId=null;
	}else{
		userAddrId = parent.document.getElementById("TOI_A_userAddrId").value;
	}
	var	delivery_addr_version = null;
	var delivery_addr_orderId = null;
	$.ajax({
		url: ctx +"/threeOrder/saveOrEidtCustomerAddr",
		data:{
			id:userAddrId,
			userId:delivery_addr_userId,
			orderId:delivery_addr_orderId,
			contactName:delivery_addr_name,
			contactPhone:delivery_addr_Phone,
			addressDetail:addressDetail,
			version :delivery_addr_version,
			province : delivery_addr_province,
			city : delivery_addr_city,
			country : delivery_addr_district,
			valid: delivery_addr_valid, 
			isDefault: delivery_addr_isDefault,
			cityCode: delivery_addr_CityCode,
			longitude :lng,
			latitude : lat,
			addressChooseAddress:addressChooseAddress
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg =="00") {
				
				$("#versionAddress").val('');
				$("#TOI_D_A_contactName").val('');
				$("#TOI_D_A_contactPhone").val('');
				$("#TOI_D_A_province").val('');
				$("#TOI_D_A_city").val('');
				$("#TOI_D_A_district").val('');
				$("#addressDetail").val('');
				$("#addressChooseAddress").val(''); 
				
				if($("#TOI_FA_userAddrList") && $("#TOI_FA_userAddrList").length>0 ){
					getAddrListByUser("TOI_FA_userAddrList");
				}
				
				if($("#TOI_S_S_A_userAddrList") && $("#TOI_S_S_A_userAddrList").length>0 ){
					getAddrListByUser("TOI_S_S_A_userAddrList");
				}
				
				if($("#TOI_S_E_A_userAddrList") && $("#TOI_S_E_A_userAddrList").length>0 ){
					getAddrListByUser("TOI_S_E_A_userAddrList");
				}
				closeModule('orderThreeFA_AddrAdd');
			}
		}
	});
}
</script>
</html>