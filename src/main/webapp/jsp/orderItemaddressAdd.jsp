<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
	<title>Insert title here</title>
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
	    <div class="modal-content">
	    	<div class="modal-header">  
	        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeOrderModalOrderItemaddressAdd();">×</button>  
		        <h4 class="modal-title" >用户常用地址信息</h4>
		    </div> 
	      
	<!-- 新增用户地址块 -->
			<div class="modal-body">
				<div class="modal-content-basic">
					<div class="info-select clearfix">
						<form class="form-inline" action="" method="post">
							<input type="hidden" name="version" id="versionAddress" >
							<input type="hidden" name="valid" id="validAddress" >
							<input type="hidden" name="isDefault" id="isDefaultAddress" >
							<input type="hidden" name="addressLongitude" id="addressLongitude" >
							<input type="hidden" name="addressLatitude" id="addressLatitude" >
<!-- 				                <header class="mb10"><h4>接收人信息</h4> </header> -->
				            
				            <div class="row">
							   <div class="form-group col-xs-12">
							     <label><p><span style="color: red">*</span>联系人：</p>
								 <input id="contactNameAddress" class="form-control" type="text" name="contactName">
								 </label>
								 <label><p><span style="color: red">*</span>手机号码：</p>
								 <input id="contactPhoneAddress" class="form-control" type="text" name="contactPhone">
								 </label>
								</div>
							</div>
				            <div class="row">
							   <div class="form-group col-xs-12">
								 <label><span style="color: red">*</span>省份：
								 <select name="selProvince" class="form-control" style="width:120px;" id="selProvinceAddressadd" onchange="set_TOI_D_A_City()"></select>
								 </label>
								 <label><span style="color: red">*</span>城市：
								 <select name="selCity" class="form-control" style="width:120px;" id="selCityAddressadd" onchange="setTOI_D_A_district()"></select>
								 </label>
								 <label><span style="color: red">*</span>区县：
								 <select name="selCountry" class="form-control" style="width:120px;" id="selCountryAddressadd"></select>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12" id="r-result-userAddress">
								 <label><p><span style="color: red">*</span>选择地址：</p>
								 <input id="addressChooseAddress" class="form-control" style="width:400px;" type="text" name="addressChooseAddress">
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12" id="r-result-userAddress">
								 <label><p><span style="color: red">*</span>详细地址：</p>
								 <input id="addressDetailAddress" class="form-control" style="width:400px;" type="text" 
								 name="addressDetailAddress" placeholder="请输入具体地址：如1号楼3单元502">
								 </label>
								</div>
							</div>
							<div class="row">
								<div style="margin-left:10px; width:550px; height:250px; border:1px solid #CCC;">
						      		<div id="baiduMapUserAddress"></div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" onclick="closeOrderModalOrderItemaddressAdd();">取消</button> 
	        <button type="button" class="btn btn-sm btn-primary"  id="buttonNext" onclick="saveOrderUserAddress();">提交</button> 
	      </div>  
	    </div>
	  </div>
	</div>     
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=initUserAddress" ></script>
<script type="text/javascript">
$(function() { 
	var lx = ${param.lx};
	var userId = ${param.userId} ;
	var userAddressId = ${param.userAddressId};
	setSelProvinceCitys("101",6,"selProvinceAddressadd");
	$("#selCityAddressadd").prepend("<option>...请选择...</option>");
	$("#selCountryAddressadd").prepend("<option>...请选择...</option>");
	if(lx==2 ||lx==4 || lx==6 || lx==8){
		var ctx=$("#ctx").val();
		 $.ajax({
			 url:ctx + "/orderbase/queryUserAddressMapper",
			 data:{
				 id:userAddressId,
				 userId:userId,
				 valid:1
			 },
			 type:'post',
			 dataType : "json",
			 async:false,
			 success:function(data){
				if (data.msg = "00") {
					var num = $.each(data.list,function(i, v) {
						if(i<=1){
							$("#versionAddress").val(v.version);
							$("#contactNameAddress").val(v.contactName);
							$("#contactPhoneAddress").val(v.contactPhone);
							$("#selProvinceAddressadd").val(v.cityCode.substr(0,6));
							setSelCity('selProvinceAddressadd','selCityAddressadd','selCountryAddressadd');
							$("#selCityAddressadd").val(v.cityCode.substr(0,9));
							setSelCountry('selCityAddressadd','selCountryAddressadd');
							$("#selCountryAddressadd").val(v.cityCode);
							$("#addressDetailAddress").val(v.addressDetail);
							$("#addressLongitude").val(v.longitude);
							$("#addressLatitude").val(v.latitude);
							$("#addressChooseAddress").val(v.addressChoose);
						}
					});
				}
			 }
		 });
	}
})

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
				$("#addressLongitude").val(point.lng);
				$("#addressLatitude").val(point.lat);
			})
		});
		//通过地图打点
		mapTool.click(function(event) {
			mapTool.clearOverlays();
			mapTool.addMarker(event.point);
			$("#addressLongitude").val(event.point.lng);
			$("#addressLatitude").val(event.point.lat);
			mapTool.getAddrByPoint(event.point, function(addr,short_addr) {
				auc.val(short_addr);
			});
		});
		var longt = $("#addressLongitude").val();
		var latit = $("#addressLatitude").val();
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

//省份change,重新生产city，同时跟新城市的validate
function set_TOI_D_A_City(){
	var ctx=$("#ctx").val();
    var code=$("#selProvinceAddressadd option:selected").val();
    if(code && $.trim(code)!=null){
    	setSelProvinceCitys(code,9,"selCityAddressadd");
    } else{
    	$("#selCityAddressadd").empty();
    	$("#selCityAddressadd").prepend("<option>...请选择...</option>");
    } 
    $("#selCountryAddressadd").empty();
	$("#selCountryAddressadd").prepend("<option>...请选择...</option>");
	
}
function setTOI_D_A_district(){
	var ctx=$("#ctx").val();
  	var code=$("#selCityAddressadd option:selected").val();
  	if(code && $.trim(code)!=""){
  		setSelProvinceCitys(code,12,"selCountryAddressadd");
  	}else{
  		$("#selCountryAddressadd").empty();
    	$("#selCountryAddressadd").prepend("<option value='no'>请选择...</option>");
  	}
}
//保存新地址
function saveOrderUserAddress(){
	var ctx=$("#ctx").val();
	var lx = ${param.lx};
	var userId = ${param.userId} ;
	var userAddressId = ${param.userAddressId};
	
	var contactName = document.getElementById("contactNameAddress").value;
	var valid = document.getElementById("validAddress").value;
	var isDefault = document.getElementById("isDefaultAddress").value;
	var contactPhone = $("#contactPhoneAddress").val();
	var addressDetail = $("#addressDetailAddress").val();
	var addressChoose = $("#addressChooseAddress").val();
	var province = $("#selProvinceAddressadd option:selected").text();
	var city =$("#selCityAddressadd option:selected").text();
	var country =  $("#selCountryAddressadd option:selected").text();
	var cityCode = $("#selCountryAddressadd option:selected").val();
	var longitude = $("#addressLongitude").val();
	var latitude = $("#addressLatitude").val();
	var version = $("#versionAddress").val();
	var addressChooseAddress = $("#addressChooseAddress").val();
	// alert(longitude +" and " +latitude);
	if(contactName==null||contactName==""){
		$.alert({text:"请输入联系人！"});
		return ;
	}
	if(!(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/.test(contactPhone))){
		$.alert({text:"手机号码为空或者格式不正确！"});
		return ;
	}
	if(cityCode==null||cityCode==""){
		$.alert({text:"请选择城市、区域！"});
		return ;
	}
	if(addressDetail==null||addressDetail==""){
		$.alert({text:"请输入详细地址！"});
		return ;
	}
	if(addressChooseAddress==null||addressChooseAddress==""){
		$.alert({text:"请输入选择地址！"});
		return ;
	}
	
	$.ajax({
		url: ctx +"/orderbase/insertUserAddress",
		data:{
			id:userAddressId,
			userId:userId,
			contactName:contactName,
			contactPhone:contactPhone,
			addressDetail:addressDetail,
			addressChoose:addressChoose,
			version :version,
			province : province,
			city : city,
			country : country,
			valid:valid,
			isDefault:isDefault,
			cityCode:cityCode,
			longitude:longitude,
			latitude:latitude
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg ="00") {
				$("#versionAddress").val('');
				$("#contactNameAddress").val('');
				$("#contactPhoneAddress").val('');
				$("#addressDetailAddress").val('');
				$("#addressChooseAddress").val('');
				$("#addressLongitude").val('');
				$("#addressLatitude").val('');
				if(lx==1 || lx==2){ //FA订单新增，收货地址
					checkOrderAddUserAddress("listBodyAddressItem");
					setOrderModalOverflowAuto("modelFrameOrderAdd");
				}else if(lx==3 || lx==4){ //服务订单新增，服务地址
					checkOrderAddUserAddress("listBodyAddressServer");
					setOrderModalOverflowAuto("modelFrameOrderAdd");
				}else if(lx==5||lx==6){ //FA订单修改，修改收货地址
					orderItemEditCheckAddress();
					setOrderModalOverflowAuto("modelFrameOrderItemEdit");
				}else if(lx==7||lx==8){ //服务订单修改，修改服务地址
					checkOrderAddUserAddressAddressEditcOne();
					setOrderModalOverflowAuto("modelFrameOrderServerEdit");
				}
				closeModule('orderItemaddressAdd');
			}
		}
	});
}

function closeOrderModalOrderItemaddressAdd(){
	var lx = ${param.lx};
	if(lx==1 || lx==2 ||lx==3 || lx==4){
		setOrderModalOverflowAuto("modelFrameOrderAdd");
	}else if(lx==5||lx==6){
		setOrderModalOverflowAuto("modelFrameOrderItemEdit");
	}else if(lx==7||lx==8){
		setOrderModalOverflowAuto("modelFrameOrderServerEdit");
	}
	closeModule('orderItemaddressAdd');
}
$("#addressChooseAddress").blur(function(){
	var address = $(this).val();
	var longitude = $("#addressLongitude").val();
	var latitude = $("#addressLatitude").val();
	if(address != null && address != ""){
		if(longitude == null || longitude =="" || latitude == null || latitude ==""){
			$(this).val("");
		}
	}
});
$("#addressChooseAddress").change(function(){
	$("#addressLongitude").val("");
	$("#addressLatitude").val("");
});

</script>
</html>