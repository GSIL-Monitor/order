<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	 #baiduMapUserAddressadd {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
/* 	table tr td {display: inline;} */
/* 	table tr td {text-align: left;} */
	.tangram-suggestion-main{z-index: 1500}
	.address-map-area{width: 500px;height: 250px;margin-left: 103px;border: 1px solid #CCCCCC;}
</style>
<script type="text/javascript">
	<%String userId = request.getParameter("userId");%>
	<%String id = request.getParameter("id");%>
</script>
</head>
<body>
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"   onclick="closeOrderModalOrderItemaddressAdd();">×</button>
					<h4 class="modal-title">新增地址</h4>
				</div>
				<div class="modal-body">
					<div class="modal-content-basic">
						<div class="info-select clearfix">
							<form class="form-inline" action="" method="post">
								<input type="hidden" id="threeOrderOutUserAddressId" name="threeOrderOutUserAddressId" value="<%=id%>"/>
								<input type="hidden" id="threeOrderOutUserAddressUserId" name="threeOrderOutUserAddressUserId" value="<%=userId%>"/>
								<input  type="hidden" id="TOI_D_A_addr_lngaa" />
	      						<input type="hidden" id="TOI_D_A_addr_lataa"  />
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p><span style="color: red">*</span>客户姓名：</p>
											<input type="text" class="form-control" name="threeOrderOutUserAddressContactName" id="threeOrderOutUserAddressContactName" />
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>
											<p><span style="color: red">*</span>联系方式：</p>
											<input type="text" class="form-control" name="threeOrderOutUserAddressContactPhone" id="threeOrderOutUserAddressContactPhone" 
											maxlength="11"/>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>
											<p><span style="color: red">*</span>省份：</p>
											<select name="threeOrderOutUserAddresProvinceqwer" id="threeOrderOutUserAddresProvinceqwer" class="form-control" onchange="set_TOI_D_A_City()"></select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>
											<p><span style="color: red">*</span>城市：</p>
											<select name="threeOrderOutUserAddressCity" id="threeOrderOutUserAddressCity" class="form-control" onchange="setTOI_D_A_district()">
												<option style='color:blue;' value='' >...请选择...</option>
											</select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>
											<p>区县：</p>
											<select name="threeOrderOutUserAddressCountry" id="threeOrderOutUserAddressCountry" class="form-control">
												<option style='color:blue;' value='' >...请选择...</option>
											</select>
										</label>
									</div>
								</div>
					            <div class="row">
							   <div class="form-group col-xs-12">
								 <label><p><span style="color: red">*</span> 选择地址：</p>
								 <input id="addressChooseAddressqw" class="form-control" style="width:400px;" type="text" name="addressChooseAddressqw">
								
								 </label>
								</div>
							    </div>
							    <div class="row">
							   <div class="form-group col-xs-12">
								 <label><p><span style="color: red">*</span> 详细地址：</p>
								 <input id="threeOrderOutUserAddressDetail" class="form-control" style="width:400px;" type="text" name="threeOrderOutUserAddressDetail"
								     placeholder="请输入具体地址：如1号楼3单元502">
								
								 </label>
								</div>
							</div>
								<div class="row">
								<div style="margin-left:10px; width:550px; height:250px; border:1px solid #CCC;">
						      		<div id="baiduMapUserAddressadd"></div>
								</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeOrderModalOrderItemaddressAdd();">取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="saveThreeOrderOutUserAddress();">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=initUserAddressabianjia" ></script><script type="text/javascript">
function initUserAddressabianjia() {
	//加载百度地图
	var mapTool = MapTool();
	mapTool.init("baiduMapUserAddressadd", function(data) {
		//通过输入地址
		var auc = mapTool.autoComplete("addressChooseAddressqw", function(obj, addr,short_addr) {
			auc.val(short_addr);
			mapTool.searchPoint(addr, function(point) {
				mapTool.clearOverlays();
				mapTool.addMarker(point);
				mapTool.panTo(point);
				$("#TOI_D_A_addr_lngaa").val(point.lng);
				$("#TOI_D_A_addr_lataa").val(point.lat);
			})
		});
		//通过地图打点
		mapTool.click(function(event) {
			mapTool.clearOverlays();
			mapTool.addMarker(event.point);
			$("#TOI_D_A_addr_lngaa").val(event.point.lng);
			$("#TOI_D_A_addr_lataa").val(event.point.lat);
			mapTool.getAddrByPoint(event.point, function(addr,short_addr) {
				auc.val(short_addr);
			});
		});
		var longt = $("#TOI_D_A_addr_lngaa").val();
		var latit = $("#TOI_D_A_addr_lataa").val();
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
$(function(){
	setSelProvinceCitys(101,6,"threeOrderOutUserAddresProvinceqwer");
	queryUserAddressDetail();
	
});
	
//省份change,重新生产city，同时跟新城市的validate
function set_TOI_D_A_City(){
	var ctx=$("#ctx").val();
    var code=$("#threeOrderOutUserAddresProvinceqwer option:selected").val();
    if(code && $.trim(code)!=null){
    	setSelProvinceCitys(code,9,"threeOrderOutUserAddressCity");
    } else{
    	$("#threeOrderOutUserAddressCity").empty();
    	$("#threeOrderOutUserAddressCity").prepend("<option>...请选择...</option>");
    } 
    $("#threeOrderOutUserAddressCountry").empty();
	$("#threeOrderOutUserAddressCountry").prepend("<option>...请选择...</option>");
	
}
function setTOI_D_A_district(){
	var ctx=$("#ctx").val();
  	var code=$("#threeOrderOutUserAddressCity option:selected").val();
  	if(code && $.trim(code)!=""){
  		setSelProvinceCitys(code,12,"threeOrderOutUserAddressCountry");
  	}else{
  		$("#threeOrderOutUserAddressCountry").empty();
    	$("#threeOrderOutUserAddressCountry").prepend("<option value='no'>请选择...</option>");
  	}
}
	
	function queryUserAddressDetail(){
		var ctx = $("#ctx").val();
		var id = $("#threeOrderOutUserAddressId").val();
		if(id!=null&&id!=""){
			$.ajax({
				url:ctx+"/threeOrderOut/queryAddressDetail",
				type:"post",
				datetype:"json",
				async : false,
				data:{userAddressId : id},
				success : function(data){
					if (data.msg == "00") {
						var obj = data.userAddress;
						selThreeOrderProvinceCitys(obj.provinceCode, 9, "threeOrderOutUserAddressCity");
						selThreeOrderProvinceCitys(obj.cityCode, 12, "threeOrderOutUserAddressCountry");
						$("#threeOrderOutUserAddresProvinceqwer").val(obj.provinceCode);
						$("#threeOrderOutUserAddressCity").val(obj.cityCode);
						$("#threeOrderOutUserAddressCountry").val(obj.countryCode);
						$("#threeOrderOutUserAddressContactName").val(obj.contactName);
						$("#threeOrderOutUserAddressContactPhone").val(obj.contactPhone);
					
						$("#addressChooseAddressqw").val(obj.chooseAddress);
						$("#threeOrderOutUserAddressDetail").val(obj.addressDetail);
						$("#TOI_D_A_addr_lngaa").val(obj.longitude);
						$("#TOI_D_A_addr_lataa").val(obj.latitude);
					}
				}
			})
		}
	}
	
	function saveThreeOrderOutUserAddress(){
		var ctx = $("#ctx").val();
		var id = $("#threeOrderOutUserAddressId").val();
		var userId = $("#threeOrderOutUserAddressUserId").val();
		var contactName = $("#threeOrderOutUserAddressContactName").val();
		var contactPhone = $("#threeOrderOutUserAddressContactPhone").val();
		var province = $("#threeOrderOutUserAddresProvinceqwer option:selected").text();
		var provinceCode = $("#threeOrderOutUserAddresProvinceqwer").val();
		var city = $("#threeOrderOutUserAddressCity option:selected").text();
		var cityCode = $("#threeOrderOutUserAddressCity").val();
		var country = $("#threeOrderOutUserAddressCountry option:selected").text();
		var countryCode = $("#threeOrderOutUserAddressCountry").val();
		var addressDetail = $("#threeOrderOutUserAddressDetail").val();
		var chooseAddress= $("#addressChooseAddressqw").val();
		var longitude = $("#TOI_D_A_addr_lngaa").val();
		var latitude = $("#TOI_D_A_addr_lataa").val();
		var params = {
			id:id,
			custId:$.trim(userId),
			contactName:$.trim(contactName),
			contactPhone:$.trim(contactPhone),
			province:$.trim(province),
			provinceCode:$.trim(provinceCode),
			city:$.trim(city),
			cityCode:$.trim(cityCode),
			country:$.trim(country),
			countryCode:$.trim(countryCode),
			addressDetail:$.trim(addressDetail),
			longitude:$.trim(longitude),
			latitude:$.trim(latitude),
			chooseAddress:$.trim(chooseAddress)
		};
			if(checkThreeOrderUserAddress(params)){
			$.ajax({
				url:ctx+"/threeOrderOut/saveAddress",
				type:"post",
				datetype:"json",
				async : false,
				data:params,
				success : function(data){
					if (data.msg == "00") {
						queryThreeOrderAddress();
						closeModule("threeOrderOutUserAddress");
					}
				}
			})
			}
	}
	
	function checkThreeOrderUserAddress(params){
		if(params.contactName==null||params.contactName==""){
			$.alert({text:"请输入客户姓名！"});
			return false;
		}
		if(params.contactPhone==null||params.contactPhone==""){
			$.alert({text:"请输入联系方式！"});
			return false;
		}
		if(params.provinceCode==null||params.provinceCode==""){
			$.alert({text:"请输入省份！"});
			return false;
		}
		if(params.cityCode==null||params.cityCode==""){
			$.alert({text:"请输入城市！"});
			return false;
		}
		if(params.countryCode==null||params.countryCode==""){
			$.alert({text:"请输入区县！"});
			return false;
		}
		if(params.addressDetail==null||params.addressDetail==""){
			$.alert({text:"请输入详细地址！"});
			return false;
		}
		if(params.chooseAddress==null||params.chooseAddress==""){
			$.alert({text:"请输入选择地址！"});
			return false;
		}
		return true;
	}
	function closeOrderModalOrderItemaddressAdd(){
		
		setOrderModalOverflowAuto("modelFrameThreeOrderAdd");
		
		closeModule('threeOrderOutUserAddress');
	}
	$("#addressChooseAddressqw").blur(function(){
		var address = $(this).val();
		var longitude = $("#TOI_D_A_addr_lngaa").val();
		var latitude = $("#TOI_D_A_addr_lataa").val();
		if(address != null && address != ""){
			if(longitude == null || longitude =="" || latitude == null || latitude ==""){
				$(this).val("");
			}
		}
	});
	$("#addressChooseAddressqw").change(function(){
		$("#TOI_D_A_addr_lngaa").val("");
		$("#TOI_D_A_addr_lataa").val("");
	});
</script>
</html>

