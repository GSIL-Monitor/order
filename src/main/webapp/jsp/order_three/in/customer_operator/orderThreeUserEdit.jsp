<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<!-- 命名规则 TOI--threeOrderIn C--Customer A-address -->
<html>
	<head>
		<style type="text/css">
			.modal-user-add{height:220px; width: 400px;margin: 30px auto; }
		</style>
	</head>
	<body>
		<div class="modal fade"  tabindex="-1" role="dialog" id="TOI_C_A_modelFrame">  
			<div class="modal-dialog" >  
				<div class="modal-content" id="TOI_C_A_modalCPM">
					<div class="modal-header" >  
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
						<h4 class="modal-title">新增客户</h4>
					</div> 
					<form class="form-inline" id="TOI_C_A_AddrFromId"> 
					<div class="modal-body">
						<div class="modal-content-basic">
							<div class="info-select clearfix">
								<input type="hidden" name="id" id="TOI_C_A_idAdd" />
								<input type="hidden" name="version" id="TOI_C_A_versionAdd" />
								<div class="row">
								   <div class="form-group col-xs-8">
									 <label class="has-feedback"><span style="color:red">*</span><p>客户姓名：</p>
									 	<input type="text" class="form-control" name="TOI_C_A_realNameAdd" id="TOI_C_A_realNameAdd" />
									 </label>
									</div>
								</div>
								<div class="row mb10">
									<div class=" form-group col-xs-12">
										<label class="col-xs-2 control-label"><p>性别：</p></label>
									 	<div class="col-xs-10 ">
				                   	    	<label class="radio-inline">
				                   	    		<input type="radio" checked="checked" name="TOI_C_A_userSexAdd" value="1" /> 男
				                   	    	</label>
				                   	    	<label class="radio-inline">
				                   	    		<input type="radio" name="TOI_C_A_userSexAdd" value="2" /> 女
				                   	    	</label>
				                   	    </div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
									<label class="has-feedback">
										<p><span style="color:red">*</span>联系方式：</p>
										<input type="text" class="form-control" name="TOI_C_A_userMobileAdd" id="TOI_C_A_userMobileAdd" />
									</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="has-feedback"><p>证件类型：</p>
											<select  id="TOI_C_A_cardTypeAdd" name="TOI_C_A_cardTypeAdd" class="form-control" >
												<option value="no">请选择...</option>
												<option value="1">身份证</option>
												<option value="2">护照</option>
												<option value="3">驾照</option>
											</select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="has-feedback">
											<p >证件号码：</p>
									 		<input type="text" style="width:200px;" name="TOI_C_A_cardNumAdd" id="TOI_C_A_cardNumAdd" class="form-control" />
									 	</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="has-feedback">
											<p><span style="color:red">*</span>省份：</p>
									 		<select name="TOI_C_A_selProvince" id="TOI_C_A_selProvince" class="form-control" onchange="tOI_C_A_setSelCity();" onclick="setSelCity('TOI_C_A_selProvince','TOI_C_A_selCity','TOI_C_A_selDistrict')">
									 		</select>
								 		</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="has-feedback">
											<p><span style="color:red">*</span>城市：</p>
									 		<select name="TOI_C_A_selCity" id="TOI_C_A_selCity" class="form-control" onchange="tOI_C_A_setSelCountry();"   onclick="setSelCountry('TOI_C_A_selCity','TOI_C_A_selDistrict')">
									 		</select>
								 		</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="has-feedback">
											<p>地区：</p>
									 		<select name="TOI_C_A_selDistrict" id="TOI_C_A_selDistrict" class="form-control">
									 		</select>
								 		</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-xs-12">
										<label class="form-label">
											<p>详细地址：</p>
											<textarea class="form-control form-textarea" 
												name="TOI_C_A_addrDetail" id="TOI_C_A_addrDetail" ></textarea>
										</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('orderThreeUserEdit');">取消</button>
				      	<button type="button" class="btn btn-sm btn-primary"  id="buttonNext" onclick="tOI_C_A_saveOrderUser();">保存</button>
					</div>  
					</form>
			  </div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	
	// 页面加载完，获取省份信息
	$(function(){
		 
		selProvinceCitys("101",6,"TOI_C_A_selProvince");
		// 加载完省份在省市区前加请选择选项
		$("#TOI_C_A_selCity").prepend("<option>...请选择...</option>");
		$("#TOI_C_A_selDistrict").prepend("<option>...请选择...</option>");
	});
	// 根据省份信息获得城市信息
	function tOI_C_A_setSelCity(){
	    var code=$("#TOI_C_A_selProvince option:selected").val();
	    if(code && $.trim(code)!=null){
	    	selProvinceCitys(code,9,"TOI_C_A_selCity");
	    }else{
	    	$("#TOI_C_A_selCity").empty();
	    	$("#TOI_C_A_selCity").prepend("<option>...请选择...</option>");
	    }
	    $("#TOI_C_A_selDistrict").empty();
		$("#TOI_C_A_selDistrict").prepend("<option>...请选择...</option>");
	}
	// 根据城市信息会的区域信息
	function tOI_C_A_setSelCountry(){
	  	var code=$("#TOI_C_A_selCity option:selected").val();
	  	if(code && $.trim(code)!=""){
	  		selProvinceCitys(code,12,"TOI_C_A_selDistrict");
	  	}else{
	  		$("#TOI_C_A_selDistrict").empty();
	  		$("#TOI_C_A_selDistrict").prepend("<option value='no'>请选择...</option>");
	  	}
	}
		
		//保存新用户
		function tOI_C_A_saveOrderUser(){
			var ctx=$("#ctx").val();
			var realName= $.trim($("#TOI_C_A_realNameAdd").val());
			var userSex = $.trim($('input:radio[name=TOI_C_A_userSexAdd]:checked').val());  
			var userMobile = $.trim($("#TOI_C_A_userMobileAdd").val());
			var cardType = $.trim($("#TOI_C_A_cardTypeAdd").val());
			var cardNum = $.trim($("#TOI_C_A_cardNumAdd").val());
			
			var userProvince1 = $("#TOI_C_A_selProvince option:selected ").text();
			var userCity1 = $("#TOI_C_A_selCity option:selected ").text();
			var userDistrict1 = $("#TOI_C_A_selDistrict option:selected").text();
			
			var userCityCode = $("#TOI_C_A_selDistrict option:selected").val();
			var userAddress = $.trim($("#TOI_C_A_addrDetail").val());
			
			if(realName==null||realName==""){
				$.alert({text:"请输入联系人！"});
				
				return ;
			}
			 if(!(/^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/.test(userMobile))){
				$.alert({text:"手机号码格式输入错误！"});
				return ;
			}
			//证件类型的验证
              if(cardType == "no"){
            	  //信息照常录入！
              } 
        	  if(cardType == "1"){
        		  if((!(/^([0-9]{15}|[0-9]{18})$/.test(cardNum)))||(!(/\d{15}/.test(cardNum)))){
        			  $.alert({text:"身份证号码格式输入错误！"});
        			  return ;
        		  }
        		
              }
        	  if(cardType == "2"){ /^[a-zA-Z]{5,17}$/
        		  if(!((/^[a-zA-Z]{5,17}$/.test(cardNum)))||!((/^[a-zA-Z0-9]{5,17}$/.test(cardNum)))){
        			  $.alert({text:"护照格式输入错误！"});
        			  return ;
        		  }
              }
        	  //默认匹配为身份证号码
        	  if(cardType == "3"){
        		  if(!(/^([0-9]{15}|[0-9]{18})$/.test(cardNum))){
        			  $.alert({text:"驾照格式输入错误！"});
        			  return ;
        		  }
              }  
        	
  			if(userCityCode=="...请选择..."||userCityCode==""){
  				$.alert({text:"请选择城市、区域！！"});
  				return ;
  			}
			if($.trim(cardType)=='no'){
				cardType = null;
				cardNum = null;
			}
			if($.trim(userDistrict1)=='...请选择...'){
				userDistrict1 = "";
			}
			/*
			console.log(realName);
			console.log(userMobile);
			console.log(userSex);
			console.log(cardType);
			console.log(cardNum);
			console.log(userProvince);
			console.log(userCity);
			console.log(userDistrict);
			console.log(userAddress);
			console.log(userCityCode);*/
			$.ajax({
				url: ctx +"/threeOrder/saveOrEditUserAddress",
				data:{
					realName:realName,
					userSex:userSex,
					userMobile:userMobile,
					cardType:cardType,
					cardNum :cardNum,
					userProvince:userProvince1,
					userCity:userCity1,
					userDistrict:userDistrict1,
					userCityCode:userCityCode,
					userAddress : userProvince1+userCity1+userDistrict1+userAddress
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg =="00") {
						parent.serUserId();
						closeModule('orderThreeUserEdit');
					}else{
						$.alert({text:data.errInfo});
					}
				} 
			});
		}
		function tOI_C_A_clearUserAdd(){
			$("#idAdd").val("");
			$("#versionAdd").val("");
			$("#realNameAdd").val("");
			$("#userMobileAdd").val("");
			$("#cardTypeAdd").val("");
			$("#cardNumAdd").val("");
			$("#remarkAdd").val("");
		}
	</script>
</html>

