<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script src="${ctx}/js/jquery/jquery.min.js"></script>
<script src="${ctx}/js/metisMenu.min.js"></script>
	<style type="text/css">
	.table{border-collapse: collapse;}
	</style>
</head>
<body>
<div style="height:380px;font-size:12px">
	  <div style="height:380px; width:60%; float:left; ">
                	<div style="heigth:40px; width:60%; text-align:left; line-height:20px;">客户信息</div>
				  	<div style="height:20px; border-top:1px solid #CCC;"></div>
				  	<div style="heigth:40px;" >
				  		<div style="float:left; ">联系方式
				  			<input data-require id="mobile" type="text" name="mobile" onblur="checkUserByMobile(0)"
				  		 		data-valid="isNonEmpty||between:5-11" data-error="联系方式不能为空||长度为5-11位">
				  		</div>
				  		<div style="margin-left:280px;">客户姓名
				  			<input data-require id="realName" type="text" name="realName" 
				  		 		data-valid="isNonEmpty||onlyZh||between:2-5" data-error="姓名不能为空||姓名只能为中文||长度为2-5位">
				  		</div>
				  	</div>
				  	</br>
				  	<input type="hidden" name="userAddressId" id="userAddressId">
				  	<div style="height:240px; display:block; overflow:auto;" id="listBodyAddress" >
                          <!-- 				  		地址：
					  <table style="width:95%; border:1px solid #CCC; border-collapse:collapse ;" >
                          <tbody id="listBodyAddress" style="display:block; overflow:auto; height:230px; width:520px;" >

                          <tbody  style="width:100%; border-bottom:1px solid #CCC; border-top:1px solid #CCC;" >
                          	<tr style="height:10px; " ></tr>
	                         <tr style="height:22px;">
	                         	<td rowspan="2"  style="text-align:center;" >
	                         	<input id="aaaa" type='checkbox' onclick="getuservalue(this)"></td>
	                         	<td>姓名</td>
	                         	<td>1111111</td>
	                         	<td rowspan="2"  style="text-align:center;" >
	                         		<div>
		                         		<a href='#' onclick='orderUserEdit()'>修改
		                         		</a>
	                         		</div>
								</td>	
	                         </tr>
	                         <tr style="height:22px;">
	                         	<td colspan="2">订单管理系统订单管理系统订单管理系统订单管理系统</td>
	                         </tr>
	                         </tbody>
	                         <tbody  style="border-bottom:1px solid #CCC; border-top:1px solid #CCC;" >
                          	<tr style="height:10px; " ></tr>
	                         <tr style="height:22px;">
	                         	<td rowspan="2"  style="text-align:center;" >
	                         	<input id="aaaa" type='checkbox' onclick="getuservalue(this)"></td>
	                         	<td>姓名</td>
	                         	<td>1111111</td>
	                         	<td rowspan="2"  style="text-align:center;" >
	                         		<div>
		                         		<a href='#' onclick='orderUserEdit()'>修改
		                         		</a>
	                         		</div>
								</td>	
	                         </tr>
	                         <tr style="height:22px;">
	                         	<td colspan="2">订单管理系统订单管理系统订单管理系统订单管理系统</td>
	                         </tr>
	                         </tbody>

                          </tbody>
					  </table>	                          -->
				  </div>
				  <div style="height:40px; width:100%; position:absolute; bottom:0;">
		  		    &nbsp;&nbsp;
		  			订单类型：<select id="orderLx">
		  			<option value="0">...请选择订单类型...</option>
		  			<option value="1">服务订单</option>
		  			<option value="2">商品订单</option>
		  			</select>
		  		  </div>
                </div>
                <!-- 新增用户地址块 -->
                <div style="height:380px; width:35%; float:right;border:1px solid #A3A3A3; " >
                <div style="margin:10px;">
					<div style="height:30px;">接收人信息</div>
					<div style="height:80px;">
					<input type="hidden" name="userId" id="userId" >
					<input type="hidden" name="id" id="contactId" >
					<input type="hidden" name="version" id="version" >
					<input type="hidden" name="valid" id="valid" >
					<input type="hidden" name="isDefault" id="isDefault" >
						<div>
							联系人： <input data-require id="contactName" type="text" name="contactName"
								data-valid="isNonEmpty||onlyZh||between:2-5"
								data-error="姓名不能为空||姓名只能为中文||长度为2-5位">
						</div>
						</br>
						<div>
							手机号码： <input data-require id="contactPhone" type="text" name="contactPhone"
								data-valid="isNonEmpty||between:5-11"
								data-error="联系方式不能为空||长度为5-11位">
						</div>
					</div>
					<div style="height:100px;">
					<table>
						<tr>
						<td>所在地区：</td>
						</tr>
						<tr>
						<td>省：<select style="width:60px;" name="selProvince" id="selProvince" theme="simple" list="" onclick="setSelCity()">
						</select>
						</td>
						<td>市：<select style="width:60px;" name="selCity" id="selCity" theme="simple" list="" onclick="setSelCountry()"></select></td>
						<td>区：<select style="width:60px;" name="selCountry" id="selCountry" theme="simple" list=""></select></td>
						</tr>
					</table>
					</div>
					<div style="height:50px;">
						地址 <textarea data-require id="addressDetail" type="text" name="addressDetail" style="height:40px; width:280px;"
							data-valid="isNonEmpty||onlyZh||between:2-5"
							data-error="姓名不能为空||姓名只能为中文||长度为2-5位"></textarea>
					</div>
					</br>
					<div>
					&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="saveOrderUser()">保存</button>
						&nbsp;&nbsp;
						<button type="button" onclick="setUsersNull()">取消</button>
					</div>
				</div>
                </div>
                
              
</div>  
</body>
<script type="text/javascript">
$(document).keydown(function(e){
    if(!e){
        e=window.event;
        }
        if((e.keyCode||e.which)===13){
        	checkUserByMobile(0);
        
    }
});


//设置选择城市
function setSelProvince(pid){
	var ctx=$("#ctx").val();
    ctx = "/order" ;
	 $.ajax({
		 url:ctx+"/orderbase/queryBaseCity",
		 data:{
			 pid:pid
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			//html +="<option value='0' >...请选择省份...</option>";
			$.each(obj.list,function(i,v){
				//if(i==0) alert("pid:" +v.pid +"  code:" +v.code +"  name:" +v.name);
				html +="<option value='" +v.code +"' keyValue='"+v.name +"'>"+v.name+"</option>";
			});
			$("#selProvince").html(html); 
		 }
	 });
}
function setSelCity(){
	var ctx=$("#ctx").val();
    ctx = "/order" ;
    var pid=$("#selProvince option:selected").val();
	 $.ajax({
		 url:ctx+"/orderbase/queryBaseCity",
		 data:{
			 pid:pid
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			//html +="<option value='0' >...请选择省份...</option>";
			$.each(obj.list,function(i,v){
				//if(i==0) alert("pid:" +v.pid +"  code:" +v.code +"  name:" +v.name);
				html +="<option value='" +v.code +"' keyValue='"+v.name +"'>"+v.name+"</option>";
			});
			$("#selCity").html(html); 
		 }
	 });
}
function setSelCountry(){
	var ctx=$("#ctx").val();
    ctx = "/order" ;
    var pid=$("#selCity option:selected").val();
	 $.ajax({
		 url:ctx+"/orderbase/queryBaseCity",
		 data:{
			 pid:pid
		 },
		 type:'post',
		 async:false,
		 success:function(data){
			var html="";
			var obj = JSON.parse(data); 
			//html +="<option value='0' >...请选择省份...</option>";
			$.each(obj.list,function(i,v){
				//if(i==0) alert("pid:" +v.pid +"  code:" +v.code +"  name:" +v.name);
				html +="<option value='" +v.code +"' keyValue='"+v.name +"'>"+v.name+"</option>";
			});
			$("#selCountry").html(html); 
		 }
	 });
}

//回显显示省份
function selectedProvince(value){
	// 设置默认选中
	if(value!=null && value!=''){
		$("#selProvince option[value='" +value +"']").attr("selected","selected"); 
	}
}
//回显显示城市
function selectedCity(value){
	if(value!=null && value!=''){
		$("#selCity option[value='" +value +"']").attr("selected","selected"); 
	}
}
//回显显示区域
function selectedCountry(value){
	if(value!=null && value!=''){
		$("#selCountry option[value='" +value +"']").attr("selected","selected"); 
	}
}

$(function() { 
	setSelProvince("100000");
})

//复选框设置
function getuservalue(addressId){
	var tf = document.getElementById("address" +addressId +"").checked;
	$("input[name='addressId']:enabled").prop("checked", false);
	// 每次选择，把需要取到的id设置为0
	if(tf){
		$("#userAddressId").val(addressId);
	}else{
		$("#userAddressId").val('');
	}
	$("input[id='address" +addressId +"']:enabled").prop("checked", tf);
}

//通过电话号码取到送贷地址
function checkUserByMobile(contactPhone){
	var ctx = "/order" ;
	var mobile = $("#mobile").val();
	$.ajax({
		url: ctx +"/orderbase/checkUserByUser",
		data:{
			contactPhone:mobile
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			if (data.msg ="00") {
				var num = 
					html +="地址：";
					$.each(data.list,function(i,v){
						if(i==0){
							$("#userId").val(v.userId);
							$("#realName").val(v.realName);
						}
						if(v.id==null || v.id==''){
							return ;
						}
						num=i+1;
						html+="<table style=\"height:50px; width:520px; border:1px solid #CCC; margin-top:20px;\" >";
						//html +="<tbody style='width:100%; border-bottom:1px solid #CCC; border-top:1px solid #CCC;'>";
					/* 						
 						html += "<tr style='height:10px;' ><td style='width:75px;'></td>" +
								"<td style='width:125px;'></td>" +
								"<td style='width:225px;'></td>" +
								"<td style='width:75px;'></td>" +
								"</tr>"; 
					*/
						html += "<tr>" ;
						html += "<td rowspan='2' style='text-align:center; width:58px;'>" ;
						html += "<input name='addressId' type='checkbox' id='address" +v.id +"' onclick=getuservalue(" +v.id +")></td>" ;
						html += "<td style='text-align:left;width:105px;'>" +v.contactName +"</td>" ;
						html += "<td style='text-align:left;width:275px;'>" +v.contactPhone +"</td>" ;
						html += "<td rowspan='2' style='text-align:center;width:67px;'><div><a "
							+ " href='#' onclick='orderUserEdit("+v.id
							+ ")' >修改</a></div>";
						html +="</td></tr>";
						html += "<tr style='height:22px;'>" ;
						html += "<td colspan='2' style='text-align:left;width:385px;'>" +v.addressDetail +"</td>" ;
						html += "</tr>" ;
						//html += "<tr colspan='4'><td style='height:5px; border:1px solid #CCC; width:425px;'></td></tr>" ;
						//html +="</tbody>" ;
						html += "</table>";
					})
			}
			$("#listBodyAddress").html(html);
		}
	});
}

//修改用户地址
function orderUserEdit(id){
	var ctx = "/order" ;
	var mobile = $("#mobile").val();
	$.ajax({
		url: ctx +"/orderbase/checkUserByUser",
		data:{
			id:id
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			if (data.msg ="00") {
				var num = $.each(data.list,function(i,v){
					$("#contactId").val(v.id);
					$("#userId").val(v.userId);
					$("#version").val(v.version);
					$("#contactName").val(v.contactName);
					$("#contactPhone").val(v.contactPhone);
					$("#addressDetail").val(v.addressDetail);
					$("#valid").val(v.valid);
					$("#isDefault").val(v.isDefault);
					setSelProvince("100000")
					selectedProvince(v.province);
					setSelCity();
					selectedCity(v.city);
					setSelCountry();
					selectedCountry(v.country);
				});
			}
		}
	});
}
	
//新增框置空
function setUsersNull(){
	$("#contactId").val('');
	$("#contactName").val('');
	$("#contactPhone").val('');
	$("#addressDetail").val('');
	$("#selProvince").val('');
	$("#selCity").val('');
	$("#selCountry").val('');
	$("#version").val('');
}

//保存新地址
function saveOrderUser(){
	var ctx = "/order" ; 
	var contactId = $("#contactId").val()==''?0:$("#contactId").val();
	var contactName = $("#contactName").val();
	var userId = $("#userId").val();
	var contactPhone = $("#contactPhone").val();
	var addressDetail = $("#addressDetail").val();
	var version = $("#version").val();
	var province = $("#selProvince option:selected").val();
	var city = $("#selCity option:selected").val();
	var country = $("#selCountry option:selected").val();
	var valid = $("#valid").val();
	var isDefault = $("#isDefault").val();
	
	//alert("pro:" +province +" city:" +city +" area:" +country);
	
	if(userId==null || userId=='' || userId=='undefined'){
		alert("用户不存在，无法添加新地址！");
		return ;
	}
	//alert(contactId +"," +contactName +"," +contactPhone +"," +addressDetail+"," +version) ;
	$.ajax({
		url: ctx +"/orderbase/insertUserAddress",
		data:{
			id:contactId,
			userId:userId,
			contactName:contactName,
			contactPhone:contactPhone,
			addressDetail:addressDetail,
			version :version,
			province : province,
			city : city,
			country : country,
			valid:valid,
			isDefault:isDefault
			
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg ="00") {
				checkUserByMobile(contactPhone);
				var num = $.each(data.list,function(i,v){
					$("#contactId").val('');
					$("#version").val('');
					$("#contactName").val('');
					$("#contactPhone").val('');
					$("#addressDetail").val('');
				})
			}
		}
	});
}
function getOrderlx(){
	return $("#orderLx").val();
}
function getUserAddressId(){
	var uid = $("#userAddressId").val();
	if(uid==null||uid==''||uid=='undefined'){
		return 0;
	}else{
		return uid;
	}
}
</script>
</html>

