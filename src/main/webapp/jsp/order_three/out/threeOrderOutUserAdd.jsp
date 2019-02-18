<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.modal-user-add {height: 220px;width: 400px;margin: 30px auto;}
 #baiduMapUserAddressadd {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}

</style>
</head>
<body>
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">新增客户</h4>
				</div>
				<div class="modal-body">
					<div class="modal-content-basic">
						<input type="hidden" name="threeOrderOutUserAddId" id="threeOrderOutUserAddId">
						<input type="hidden" name="threeOrderOutUserAddVersion" id="threeOrderOutUserAddVersion">
						<input type="hidden" id="TOI_D_A_addr_lnguser" />
	      				<input type="hidden" id="TOI_D_A_addr_latuser"  />
						<div class="info-select clearfix">
							<form class="form-inline" action="" method="post">
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<span style="color: red">*</span>客户姓名：
											<input type="text" class="form-control" name="threeOrderOutUserAddRealName" id="threeOrderOutUserAddRealName" />
										</label>
									</div>
									<div class="form-group col-xs-6">
										 <label class="col-sm-2 control-label"><p>性别：</p></label>
										 	<div class="col-sm-10 pl40 mb10">
					                   	    	<label class="radio-inline">
					                   	    			<input type="radio" checked="checked" name="threeOrderOutUserAddUserSex" value="1"> 男
					                   	    	</label>
					                   	    	<label class="radio-inline">
					                   	    		<input type="radio" name="threeOrderOutUserAddUserSex" value="2"> 女
					                   	    	</label>
					                   	    </div>
										</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label>
										<span style="color: red">*</span>联系方式：
											<input type="text" class="form-control" name="threeOrderOutUserAddUserMobile" id="threeOrderOutUserAddUserMobile" 
											   maxlength="11"/>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											证件类型：
											<select id="threeOrderOutUserAddCardType" class="form-control">
												<option style='color:blue;' value=''>...请选择...</option>
												<option value="1">身份证</option>
												<option value="2">护照</option>
												<option value="3">驾照</option>
											</select>
										</label>
									</div>
									<div class="form-group col-xs-6">
										<label>
											证件号码：
											<input type="text" name="threeOrderOutUserAddCardNum" id="threeOrderOutUserAddCardNum" class="form-control" />
										</label>
								</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-4">
										<label>
											<span style="color: red">*</span>省  份：
											<select name="threeOrderOutUserAddProvince" id="threeOrderOutUserAddProvince" class="form-control" onchange="setSelCity()"></select>
										</label>
									</div>
									<div class="form-group col-xs-4">
										<label>
											<span style="color: red">*</span>城  市：
											<select name="threeOrderOutUserAddCity" id="threeOrderOutUserAddCity" class="form-control" onchange="setSelCountry()">
												<option style='color:blue;' value='' >...请选择...</option>
											</select>
										</label>
								</div>
									<div class="form-group col-xs-4">
										<label>
											<span style="color: red">*</span>区  县：
											<select name="threeOrderOutUserAddDistrict" id="threeOrderOutUserAddDistrict" class="form-control">
												<option style='color:blue;' value='' >...请选择...</option>
											</select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12" id="r-result-userAdd">
									 <label><p><span style="color: red">*</span>选择地址：</p>
									 <input type="text" style="width:400px" class="form-control" name="threeOrderOutUserAddAddress" id="threeOrderOutUserAddAddress"/>
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
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('threeOrderOutUserEdit');">取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="saveOrderUser();">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc&callback=initUserAdd" ></script>
<script type="text/javascript">
function initUserAdd() {
	//加载百度地图
	var mapTool = MapTool();
	mapTool.init("baiduMapUserAddressadd", function(data) {
		//通过输入地址
		var auc = mapTool.autoComplete("threeOrderOutUserAddAddress", function(obj, addr, short_addr) {
			auc.val(short_addr);
			mapTool.searchPoint(addr, function(point) {
				mapTool.clearOverlays();
				mapTool.addMarker(point);
				mapTool.panTo(point);
				$("#TOI_D_A_addr_lnguser").val(point.lng);
				$("#TOI_D_A_addr_latuser").val(point.lat);
			})
		});
		//通过地图打点
		mapTool.click(function(event) {
			//event{type, target, point, pixel, overlay}
			mapTool.clearOverlays();
			mapTool.addMarker(event.point);
			$("#TOI_D_A_addr_lnguser").val(event.point.lng);
			$("#TOI_D_A_addr_latuser").val(event.point.lat);
			mapTool.getAddrByPoint(event.point, function(addr,short_addr) {
				auc.val(short_addr);
			});
		});
	});
	//坐标回显
	mapTool.addMarker($("#TOI_D_A_addr_lnguser").val(),$("#TOI_D_A_addr_latuser").val());
	mapTool.panTo(new BMap.Point($("#TOI_D_A_addr_lnguser").val(),$("#TOI_D_A_addr_latuser").val()));
}
	// 设置省、市、区
	$(function() {
		setSelProvinceCitys(101,6,"threeOrderOutUserAddProvince");
	})
	function setSelCity() {
		var ctx=$("#ctx").val();
	    var code=$("#threeOrderOutUserAddProvince option:selected").val();
	    if(code && $.trim(code)!=null){
	    	setSelProvinceCitys(code,9,"threeOrderOutUserAddCity");
	    } else{
	    	$("#threeOrderOutUserAddCity").empty();
	    	$("#threeOrderOutUserAddCity").prepend("<option>...请选择...</option>");
	    } 
	    $("#threeOrderOutUserAddDistrict").empty();
		$("#threeOrderOutUserAddDistrict").prepend("<option>...请选择...</option>");
	}
	function setSelCountry() {
		var ctx=$("#ctx").val();
	  	var code=$("#threeOrderOutUserAddCity option:selected").val();
	  	if(code && $.trim(code)!=""){
	  		setSelProvinceCitys(code,12,"threeOrderOutUserAddDistrict");
	  	}else{
	  		$("#threeOrderOutUserAddDistrict").empty();
	    	$("#threeOrderOutUserAddDistrict").prepend("<option value='no'>请选择...</option>");
	  	}
	}
	//保存新地址
	function saveOrderUser() {
		var ctx = $("#ctx").val();
		var realName = $("#threeOrderOutUserAddRealName").val();
		var userSex = $('input[name=threeOrderOutUserAddUserSex]:checked').val();
		var provinceCode = $("#threeOrderOutUserAddProvince").val();
		var cityCode = $("#threeOrderOutUserAddCity").val();
		var districtCode = $("#threeOrderOutUserAddDistrict").val();
		var province = "";
		var city = "";
		var district = "";
		if(provinceCode!=null&&provinceCode!=""){
			province = $("#threeOrderOutUserAddProvince option:selected").text();
		}
		if(cityCode!=null&&cityCode!=""){
			city = $("#threeOrderOutUserAddCity option:selected").text();
		}
		if(districtCode!=null&&districtCode!=""){
			district = $("#threeOrderOutUserAddDistrict option:selected").text();
		}
		var userMobile = $("#threeOrderOutUserAddUserMobile").val();
		var cardType = $("#threeOrderOutUserAddCardType").val();
		var cardNum = $("#threeOrderOutUserAddCardNum").val();
		var address = $("#threeOrderOutUserAddAddress").val();
		
		var params = {
			realName : $.trim(realName),
			userSex : $.trim(userSex),
			userMobile : $.trim(userMobile),
			cardType : $.trim(cardType),
			cardNum : $.trim(cardNum),
			userProvince : $.trim(province),
			provinceCode : $.trim(provinceCode),
			userCity : $.trim(city),
			cityCode : $.trim(cityCode),
			userDistrict : $.trim(district),
			districtCode : $.trim(districtCode),
			userAddress : $.trim(address)
		};
		if(checkThreeOrderOutUserData(params)){
			if(address==null||address==""){
				$.alert({text:"请输入详细地址！"});
				return ;
			}
			$.ajax({
				url : ctx + "/threeOrderOut/saveOrderUser",
				data : params,
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.msg == "00") {
						queryUser(0, 5, 1,2);
						closeModule('threeOrderOutUserAdd',function(){
							//添加完成之后，默认选中第一行数据
							$("#threeOrderOutListBodyUser tr:eq(0)").trigger("click");
						});
					}else if(data.msg == "06"){
						$.alert({text:"手机号码已占用！"});
					}
				}
			});
		}
	}
	
	function checkThreeOrderOutUserData(params){
		if(params.realName==null||params.realName==""){
			$.alert({text:"请输入客户姓名！"});
			return false;
		}
		 if(!(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/.test(params.userMobile))){
				$.alert({text:"手机号码格式输入错误！"});
				return  false;
			}
		//证件类型的验证
         if(params.cardType == "no"){
       	  //信息照常录入！
         } 
   	  if(params.cardType == "1"){
   		  if((!(/^([0-9]{15}|[0-9]{18})$/.test(params.cardNum)))||(!(/\d{15}/.test(params.cardNum)))){
   			  $.alert({text:"身份证号码格式输入错误！"});
   			  return    false;
   		  }
         }
   	  if(params.cardType == "2"){ /^[a-zA-Z]{5,17}$/
   		  if(!((/^[a-zA-Z]{5,17}$/.test(params.cardNum)))||!((/^[a-zA-Z0-9]{5,17}$/.test(params.cardNum)))){
   			  $.alert({text:"护照格式输入错误！"});
   			  return  false;
   		  }
         }
   	  //默认匹配为身份证号码
   	  if(params.cardType == "3"){
   		  if(!(/^([0-9]{15}|[0-9]{18})$/.test(params.cardNum))){
   			  $.alert({text:"驾照格式输入错误！"});
   			  return  false;
   		  }
         }  
		if(params.provinceCode==null||params.provinceCode==""){
			$.alert({text:"请选择省份！"});
			return false;
		}
		if(params.cityCode==null||params.cityCode==""){
			$.alert({text:"请选择城市！"});
			return false;
		}
		if(params.districtCode==null||params.districtCode==""){
			$.alert({text:"请选择区县！"});
			return false;
		}
		
		return true;
	}
	
	function selAddressReset(htmlId){
		var html = "<option style='color:blue;' value='' >...请选择...</option>";
		$("#"+htmlId).html(html);
	}

	function clearUserAdd() {
		$("#idAdd").val("");
		$("#versionAdd").val("");
		$("#realNameAdd").val("");
		$("#userMobileAdd").val("");
		$("#cardTypeAdd").val("");
		$("#cardNumAdd").val("");
		$("#remarkAdd").val("");
	}
	$("#threeOrderOutUserAddAddress").blur(function(){
		var address = $(this).val();
		var longitude = $("#TOI_D_A_addr_lnguser").val();
		var latitude = $("#TOI_D_A_addr_latuser").val();
		if(address != null && address != ""){
			if(longitude == null || longitude =="" || latitude == null || latitude ==""){
				$(this).val("");
			}
		}
	});
	$("#threeOrderOutUserAddAddress").change(function(){
		$("#TOI_D_A_addr_lnguser").val("");
		$("#TOI_D_A_addr_latuser").val("");
	});
</script>
</html>

