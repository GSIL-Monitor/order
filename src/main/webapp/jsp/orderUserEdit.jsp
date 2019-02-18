<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		#baiduMapUserAdd {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
		#r-result-userAdd{width:100%;}
		.tangram-suggestion-main {z-index: 1060;}
		.tangram-suggestion table tr td {text-align: left;}
		.tangram-suggestion table tr td i{display: inline;}
		.address-map-area{width: 500px;height: 250px;margin-left: 103px;border: 1px solid #CCCCCC;}
		.modal-user-add{height:220px; width: 650px;margin: 30px auto; }
	</style>
	<script type="text/javascript">
		var edit = ${param.edit};
	</script>
</head>
<body>
  <div class="modal fade">  
	  <div class="modal-dialog">  
	    <div class="modal-content" style="width:650px;">
	    	<div class="modal-header" >  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setOrderModalOverflowAuto('modelFrameOrderUser');">×</button>  
		        <h4 class="modal-title" >用户信息</h4>
		     </div> 
	      <div class="modal-body">
	      	<div class="modal-content-basic">
	      	<input type="hidden" name="userAddId" id="userAddId" >
	      	<input type="hidden" name="userAddVersion" id="userAddVersion" >
	      	<input type="hidden" name="userAddLongitude" id="userAddLongitude" >
	      	<input type="hidden" name="userAddLatitude" id="userAddLatitude" >
	      	<div class="info-select clearfix" >
	      	<form class="form-inline" action="" method="post">
	      		<div class="row">
					<div class="form-group col-xs-6">
						<label><p><span style="color: red">*</span>客户姓名：</p>
					 		<input type="text" class="form-control" name="realName" id="realNameAdd"/>
						</label>
					</div>
					<div class="form-group col-xs-6">
					 <label class="col-sm-2 control-label"><p>性别：</p></label>
					 	<div class="col-sm-10 pl40 mb10">
                   	    	<label class="radio-inline">
                   	    		<input type="radio" checked="checked" name="userSexAdd" value="1" > 男
                   	    	</label>
                   	    	<label class="radio-inline">
                   	    		<input type="radio" name="userSexAdd" value="2" > 女
                   	    	</label>
                   	    	<label class="radio-inline">
                   	    		<input type="radio" name="userSexAdd" value="" > 保密
                   	    	</label>
                   	    </div>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-6">
					 <label><p>证件类型：</p>
					 		<select  id="cardTypeAdd" class="form-control">
					  		  <option value="">...请选择...</option>
					  		  <option value="1">身份证</option>
					          <option value="2">护照</option>
					          <option value="3">驾照</option>
					        </select>
					 </label>
					</div>
					 <div class="form-group col-xs-6">
					 <label><p>证件号码：</p>
					 <input type="text"  name="cardNum" id="cardNumAdd" class="form-control"/>
					 </label>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-xs-6">
					 <label><p><span style="color: red">*</span>联系方式：</p>
						 <input type="text" class="form-control" name="userMobile" id="userMobileAdd"/>
					 </label>
					</div>
					
				</div>
				<div class="row">
					<div class="form-group col-xs-12">
                  		<label><p><span style="color: red">*</span>客户渠道</p>
                  			<select name="userChannel" id="userChannelAdd" class="form-control"   style="width:250px;"/>
                  		</label>
                  	</div>
				</div>
				<div class="row">
				   <div class="form-group col-xs-12">
					 <label><span style="color: red">*</span>省份：
					 <select name="selProvince" id="selProvinceAddress" class="form-control" onchange="set_TOI_D_A_City()" style="width:120px;"></select>
					 </label>
					 <label><span style="color: red">*</span>城市：
					 <select name="selCity" id="selCityAddress" class="form-control" onchange="setTOI_D_A_district()"  style="width:120px;"></select>
					 </label>
					 <label><span style="color: red">*</span>区县：
						 <select name="selCountry" id="selCountryAddress" class="form-control" style="width:120px;"></select>
					 </label>
					</div>
				</div>
				<div class="row">
				   <div class="form-group col-xs-12" id="r-result-userAdd">
					 <label><p><span style="color: red">*</span>选择地址：</p>
					 <input type="text" style="width:400px" class="form-control" name="userAddress" id="userAddress"/>
					 </label>
					</div>
				</div>
				<div class="row">
					<div style="margin-left:10px; width:580px; height:250px; border:1px solid #CCC;">
			      		<div id="baiduMapUserAdd"></div>
					</div>
				</div>
            	</form>
			</div>
			</div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderModalOverflowAuto('modelFrameOrderUser');">取消</button>
	        <button type="button" class="btn btn-sm btn-primary"  onclick="saveOrderUser();">提交</button>  
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=initUserAdd" ></script>
<script type="text/javascript">
// 设置省、市、区
$(function(){
	setSelProvinceCitys("101",6,"selProvinceAddress");
	$("#selCityAddress").prepend("<option>...请选择...</option>");
	$("#selCountryAddress").prepend("<option>...请选择...</option>");
	queryBaseChannel(null,"userChannelAdd");
	if(edit==1){
		var user = parent.getUserValues();
		var sexval = "";
		if(user.sex=="男"){
			sexval = 1;
		}else if(user.sex=="女"){
			sexval = 2;
		}
		$("#userAddId").val(user.id);
		$("#userAddVersion").val(user.version);
		$("#realNameAdd").val(user.name);
		//$("input:radio[name=userSexAdd]:checked").val(sexval);
		$("input:radio[name=userSexAdd][value=" +sexval +"]").prop("checked","ture");
		$("#userMobileAdd").val(user.mobile);
		$("#cardTypeAdd option[value=" +user.cardType +"]").prop("selected","ture");
		$("#cardNumAdd").val(user.cardNum);
		$("#remarkAdd").val();
		$("#userAddLongitude").val(user.longitude);
		$("#userAddLatitude").val(user.latitude);
		$("#userAddress").val(user.userAddress);
		//setSelProvinceCitys("101",6,"selProvinceAddress");
		$("#selProvinceAddress option[value='" +user.cityCode.substr(0,6) +"']").attr("selected","selected");
		setSelCity("selProvinceAddress","selCityAddress","selCountryAddress");
		$("#selCityAddress option[value='" +user.cityCode.substr(0,9) +"']").attr("selected","selected");
		setSelCountry("selCityAddress","selCountryAddress");
		$("#selCountryAddress option[value='" +user.cityCode +"']").attr("selected","selected");
		$("#userChannelAdd option[value=" +user.channelId +"]").attr("selected","selected");
		if(user.channelId!=null&&user.channelId!=""&&user.channelId>1){
			$("#userChannelAdd").attr("disabled","disabled");
		}
		//设置不可改
		$("#userMobileAdd").prop("readonly","false");
		
	}
})


// $('#userAddress').keydown(function(e){
// 	if(e.keyCode==13){
// 		var address = $("#userAddress").val();
// 	}
// });

function initUserAdd() {
	//加载百度地图
	var mapTool = MapTool();
	mapTool.init("baiduMapUserAdd", function(data) {
		//通过输入地址
		var auc = mapTool.autoComplete("userAddress", function(obj, addr, short_addr) {
			auc.val(short_addr);
			mapTool.searchPoint(addr, function(point) {
				mapTool.clearOverlays();
				mapTool.addMarker(point);
				mapTool.panTo(point);
				$("#userAddLongitude").val(point.lng);
				$("#userAddLatitude").val(point.lat);
			})
		});
		//通过地图打点
		mapTool.click(function(event) {
			//event{type, target, point, pixel, overlay}
			mapTool.clearOverlays();
			mapTool.addMarker(event.point);
			$("#userAddLongitude").val(event.point.lng);
			$("#userAddLatitude").val(event.point.lat);
			mapTool.getAddrByPoint(event.point, function(addr,short_addr) {
				auc.val(short_addr);
			});
		});
	});
	//坐标回显
	mapTool.addMarker($("#userAddLongitude").val(),$("#userAddLatitude").val());
	mapTool.panTo(new BMap.Point($("#userAddLongitude").val(),$("#userAddLatitude").val()));
}
//省份change,重新生产city，同时跟新城市的validate
function set_TOI_D_A_City(){
	var ctx=$("#ctx").val();
    var code=$("#selProvinceAddress option:selected").val();
    if(code && $.trim(code)!=null){
    	setSelProvinceCitys(code,9,"selCityAddress");
    } else{
    	$("#selCityAddress").empty();
    	$("#selCityAddress").prepend("<option>...请选择...</option>");
    } 
    $("#selCountryAddress").empty();
	$("#selCountryAddress").prepend("<option>...请选择...</option>");
	
}
function setTOI_D_A_district(){
	var ctx=$("#ctx").val();
  	var code=$("#selCityAddress option:selected").val();
  	if(code && $.trim(code)!=""){
  		setSelProvinceCitys(code,12,"selCountryAddress");
  	}else{
  		$("#selCountryAddress").empty();
    	$("#selCountryAddress").prepend("<option value='no'>请选择...</option>");
  	}
}
//保存新地址
function saveOrderUser(){
	var ctx=$("#ctx").val();
	var realName = $("#realNameAdd").val();
	var userSex = $('input:radio[name=userSexAdd]:checked').val();
	var province = $("#selProvinceAddress option:selected").text();
	var city = $("#selCityAddress option:selected").text();
	var userCity = $("#selCityAddress option:selected").val();
	var country = $("#selCountryAddress option:selected").text();
	var cityCode = $("#selCountryAddress option:selected").val();
	var userMobile = $("#userMobileAdd").val();
	var cardType = $("#cardTypeAdd option:selected").val();
	var cardNum = $("#cardNumAdd").val();
	var remark = $("#remarkAdd").val();
	var userAddress = $("#userAddress").val();
	var longitude = $("#userAddLongitude").val();
	var latitude = $("#userAddLatitude").val();
	var channelId = $("#userChannelAdd option:selected").val();
	var channel = $("#userChannelAdd option:selected").text();
	if(channelId==null || channelId==""){
		$.alert({ text : "请选择渠道！" });
		return ;
	}
	if(userAddress==null||userAddress==""){
		$.alert({text:"请输入选择地址！"});
		return ;
	}
	if($.trim(cardType)==""){
		cardType = null;
		cardNum = null;
	} 
	var params = {
			realName:$.trim(realName),
			cityCode:$.trim(cityCode),
			userMobile:$.trim(userMobile),
			userAddress:$.trim(userAddress),
			cardType:$.trim(cardType),
			cardNum:$.trim(cardNum)
		};
	if(userAddParams(params)){
		if(edit==1){
			$.ajax({
				url: ctx +"/orderbase/insertUser",
				data:{
					id:$("#userAddId").val(),
					version:$("#userAddVersion").val(),
					realName:realName,
					userSex:userSex,
					userMobile:userMobile,
					cardType:cardType,
					cardNum :cardNum,
					remark : remark,
					userProvince:province,
					userCity:city,
					userCountry:country,
					userAddress:userAddress,
					cityCode:cityCode,
					longitude:longitude,
					latitude:latitude,
					valid:1,
					channelId:channelId,
					channel:channel
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg=="00") {
						parent.queryUser(0,10,1,2);
						parent.$("#checkValue").val("");
						parent.$("#orderUserId").val("");
						setOrderModalOverflowAuto("modelFrameOrderUser");
						closeModule('orderUserEdit');
					}
				}
			});
		}else{
			// 如果是新增，先检查当前电话是否已经注册
			$.ajax({
				url: ctx +"/orderbase/checkedUserOrNot?mobile=" +userMobile,
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg=="00" && data.userCount == 0) {
						$.ajax({
							url: ctx +"/orderbase/insertUser",
							data:{
								realName:realName,
								userSex:userSex,
								userMobile:userMobile,
								cardType:cardType,
								cardNum :cardNum,
								remark : remark,
								userProvince:province,
								userCity:city,
								userCountry:country,
								userAddress:userAddress,
								cityCode:cityCode,
								longitude:longitude,
								latitude:latitude,
								version:0,
								valid:1,
								channelId:channelId,
								channel:channel,
								origin:'20100011'
							},
							type:"post",
							dataType:"json",
							async:false,
							success:function(data){
								//新增用户后，直接跳转创建订单页面 add 20181130 zhanghao
								if (data.msg=="00") {
									var user = data.orderUser;
									closeModule('orderUserEdit');
									closeModule('modelFrameOrderUser');
									var userId = user.id;
									var name = user.realName;
									var sex = user.userSex;
									if(sex == "1"){
										sex = "男";
									}else{
										sex = "女";
									}
									var mobile = user.userMobile;
									var createTime = user.createTime;
									var cardTypeText = user.cardType;
									if(cardTypeText=="1"){
										cardTypeText = "身份证" ;
									}else if(cardTypeText=="2"){
										cardTypeText = "护照" ;
									}else if(cardTypeText=="3"){
										cardTypeText = "驾照" ;
									}
									var version = user.version;
									var priceType = user.priceType;
									var channelId = user.channelId;
									var channel = user.channel;
									var params = {"id":userId,"name":name,"sex":sex,"mobile":mobile,"createTime":createTime,"cardType":cardType,
										"cardTypeText":cardTypeText,"cardNum":cardNum,"city":city,"userCity":userCity,"userAddress":userAddress,
										"longitude":longitude,"latitude":latitude,"cityCode":cityCode,"version":version,"priceType":"20000002",
										"channelId":channelId,"channel":channel};
									closeModule("orderUser",
											function(){openModule('/order/jsp/orderAdd.jsp',params,{},{width:'70%'},'orderAdd')});
								}
							}
						});
					}else{
						$.alert({
							text : "当前手机用户已经存在！"
						});
						return ;
					}
				}
			});
		}
	}
}


function userAddParams(data){
	if(data.realName==null||data.realName==""){
		$.alert({text:"请输入客户姓名！"});
		return false;
	}
// 	旧:/^1[34578]\d{9}$/
// 	新:/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\d{8}$/
	if(!(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/.test(data.userMobile))){
		$.alert({text:"手机号码为空或者格式不正确！"});
		return ;
	}
	if(data.cardType == "1"){
		  var cardNumReg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
		  if(!cardNumReg.test(data.cardNum)){
			  $.alert({text:"身份证号码格式输入错误！"});
			  return ;
		  }
    }
	if(data.cityCode==null||data.cityCode==""){
		$.alert({text:"请选择城市、区域！"});
		return false;
	}
	if(data.userAddress==null||data.userAddress==""){
		$.alert({text:"请输入客户详细地址！"});
		return false;
	}
	return true ;
}

function clearUserAdd(){
	$("#idAdd").val("");
	$("#versionAdd").val("");
	$("#realNameAdd").val("");
	$("#userMobileAdd").val("");
	$("#cardTypeAdd").val("");
	$("#cardNumAdd").val("");
	$("#remarkAdd").val("");
}

$("#userAddress").blur(function(){
	var address = $(this).val();
	var longitude = $("#userAddLongitude").val();
	var latitude = $("#userAddLatitude").val();
	if(address != null && address != ""){
		if(longitude == null || longitude =="" || latitude == null || latitude ==""){
			$(this).val("");
		}
	}
});
$("#userAddress").change(function(){
	$("#userAddLongitude").val("");
	$("#userAddLatitude").val("");
});
</script>
</html>

