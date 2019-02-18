<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
	<%
		String id = request.getParameter("id");
	%>
	</script>
	<style>
		.modal-order-iframe{height:550px; width: 350px; margin: 30px auto; }
		.table-condensed_order_person tr{line-height:20px;}
		.table-condensed_order_person tbody tr td{text-align: left; height:16px; line-height:15px;}
	</style>
</head>
<body>
	<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" >  
		<div class="modal-order-iframe modal-content" > 
			<div class="modal-header" style="height:40px;">  
	        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        	<h4 class="modal-title">服务人员信息</h4>
			</div>
			<input type="hidden" id="idPerson" value="<%=id %>">
			<input type="hidden" id="etc" value="${etc }" />
			<div style="width:330px; heith:500px;">
					<div>
						<table class="table-condensed table-condensed_order_person">
		                    <tr>
		                        <td width="35%">
		                        	姓名：<span id="nameNeeds"></span>
		                        </td>
		                        <td width="35%">
		                        	性别：<span id="sexNeeds"></span>
		                        </td>
		                        <td width="30%" rowspan="4">
	                               <img src="${ctx}/images/touxiang.png" style="width:90px; height:90px;" id="orderNeedsPersonImg"/>
	                               &nbsp;&nbsp;
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>
		                        	年龄：<span id="ageNeeds"></span>
		                        </td>
		                        <td>
		                        	属相：<span id="zodiacNeeds"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>
		                        	民族：<span id="nationNeeds"></span>
		                        </td>
		                        <td>
		                        	学历：<span id="educationNeeds"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td>
		                        	籍贯：<span id="originNeeds"></span>
		                        </td>
		                        <td>
		                        	婚姻状况：<span id="isMarryNeeds"></span>
		                        </td>
		                    </tr>
		                   <!--  <tr>
		                        <td colspan="2">
		                        	联系方式：<span id="mobileNeeds"></span>
		                        </td>
		                    </tr> -->
		                    <tr>
		                        <td colspan="2">
		                        	身份证号：<span id="idCardNumNeeds"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td colspan="3">
		                        	现住址：<span id="userAddressNeeds"></span>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td colspan="3">
		                        	原籍地址：<span id="addressNeeds"></span>
		                        </td>
		                    </tr>
	            		</table>
					</div>
					<div style="border-top:1px solid #CCC">
						<table class="table-condensed table-condensed_order_person">
							<tr>
								<td width="50%">工种：<span id="workTypeTextNeeds"></span></td>
								<td width="50%">级别：<span id="workLevelTextNeeds"></span></td>
							</tr>
							<tr>
								<td>从事家政年限:<span id="workingLifeNeeds"></span></td>
								<td>是否怕宠物：<span id="fearPetNeeds"></span></td>
							</tr>
							<tr>
								<td colspan="3">有何特长：<textarea rows="2" cols="25" id="specialtyNeeds"></textarea></td>
							</tr>
							<tr>
								<td>所属管家：<span id="realNameNeeds"></span></td>
								<td>管家电话：<span id="phoneNeeds"></span></td>
							</tr>
						</table>
					</div>
					<div style="height:10px; border-top:1px solid #CCC"></div>
					<div style="width:300px;text-align:center;margin-bottom:0px;">
						<input type="button" width="150px;" value="关闭" onclick="closeModule('checkPersonsModle')">
					</div>
				</div>  
	</div>         
</body>
<script type="text/javascript">

$(function(){
	var idPerson = $("#idPerson").val();
	checkPersions(idPerson) ;
})

//取到订单详细信息
function checkPersions(idPerson){
	var ctx = $("#ctx").val();
	$.ajax({
		url: ctx +"/itemDetailServer/queryOrderNeedServer",
		data:{
			id:idPerson,
			valid:1,
			type:3
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg ="00") {
				var fearPetText = "";
				var num =  $.each(data.list,function(i,v){
					$("#nameNeeds").text(v.name);
					$("#sexNeeds").text(v.sex==1?"女":"男");
					$("#ageNeeds").text(v.age);
					$("#zodiacNeeds").text(v.zodiac);
					$("#nationNeeds").text(v.nation);
					$("#educationNeeds").text(v.educationText);
					$("#originNeeds").text(v.origin);
					$("#isMarryNeeds").text(v.isMarry);
					$("#idCardNumNeeds").text(v.idCardNum);
					$("#addressNeeds").text(v.address);
					$("#userAddressNeeds").text(v.userAddress);
					$("#workingLifeNeeds").text(v.workingLife);
					if(v.fearPet==1){
						fearPetText = "是";
					}else if(v.fearPet==2){
						fearPetText = "否";
					}else if(v.fearPet==3){
						fearPetText = "不喜欢";
					}
					$("#fearPetNeeds").text(fearPetText);
					$("#specialtyNeeds").text(v.specialty);
					$("#realNameNeeds").text(v.realName);
					$("#mobileNeeds").text(v.mobile);
					$("#phoneNeeds").text(v.phone);
					$("#workTypeTextNeeds").text(v.workTypeText);
					$("#workLevelTextNeeds").text(v.workLevelText);
					$("#orderNeedsPersonImg").attr("src",v.originalAddress);
				})
			}
		}
	});
}

</script>
</html>

