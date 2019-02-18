<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.modal-order-iframe{height:400px; width: 800px;margin: 30px auto; }
		.otable-tr-top{height:25px; border-top:1px solid #CCC}
		.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
		.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
		.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
		.table{
			font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
			color:#333;
		}
	</style>
</head>
<body>
  <div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" id="modelFrameOrderUser">  
	  <div class="modal-order-iframe" >  
	    <div class="modal-content" id="modalCPM">
	    	<div class="modal-header">  
	        	<button type="button" class="close" data-dismiss="modal" onclick="setOrderCloseModule('orderUser');">×</button>  
	        	<h4 class="modal-title">选择客户</h4>
			</div> 
			<div class="modal-body" >
				<input type="hidden" id="userIsCommit">
		      	<form class="form-inline" action="" method="post">
					<div class="row">
					   <div class="form-group col-xs-6">
						 <label><p>客户姓名:</p>
						 	<input type="text" class="form-control" name="realName" id="realName"/>
						 </label>
						</div>
					   <div class="form-group col-xs-6">
						 <label><p>联系方式:</p>
						 	<input type="text" class="form-control" name="userMobile" id="userMobile"/>
						 </label>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-xs-10">
	                        <button type="button" class="btn btn-sm btn-default fr mr20" 
	                      	        onclick="editOrderUser();">修改</button>
	                        <button type="button" class="btn btn-sm btn-default fr mr20" 
	                      	        onclick="openModule('${ctx}/jsp/orderUserEdit.jsp?edit=0','','',{},'orderUserEdit');">新增</button>
							
						</div>
					   <div class="form-group col-xs-1">
							<button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryUser(0,10,1,2);">查询</button>
						</div>
					</div>
	            </form>
				<div class="panel-content table-responsive" >
					<input type="hidden" id="orderUserId" name="orderUserId">
					<input type="hidden" id="checkValue" name="checkValue">
					<table class="table table-hover " >
		                <thead>
		                	<tr>
								<th width="10%">序号</th>
								<th width="10%">客户姓名</th>
								<th width="10%">性别</th>
								<th width="15%">联系方式</th>
								<th width="13%">注册时间</th>
								<th width="10%">证件类型</th>
								<th width="20%">证件号码</th>
								<th width="12%">所在城市</th>
		                   </tr>
		                  </thead>
		                  <tbody id="listBodyUser" >
		                </tbody>
		            </table>
				</div>
				<div class="row">
					<div class="panel-heading panel-new">
						<ul class="pagination pagination-sm navbar-right" id="pageUserDiv"></ul>
					</div>
				</div>
			</div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderCloseModule('orderUser');">取消</button>  
	      	<button type="button" class="btn btn-sm btn-primary" onclick="nextModule()">下一步</button>
	      </div>  
	    </div>
	    </div>
	</div>         
</body>
<script type="text/javascript">
/**
 * 分页跳转使用
 */
function listBodyUser(num,pageCount) {
	$("#listBodyUser").empty();
	var ism = $("#userIsCommit").val();
	queryUser(num,pageCount,1,ism);
}

$(function(){
	queryUser(0,10,1,2);
	$("#userIsCommit").val(2);
	$("#checkValue").val(0);
})

// 选择用户列表
function queryUser(num,pageCount,valid,isCommit){
	var ctx = $("#ctx").val();
	var realName = $("#realName").val();
	var userMobile = $("#userMobile").val();
	if(isCommit==2 && userMobile!="" && userMobile.length>=5){
		isCommit = 1;
	}
	$("#userIsCommit").val(isCommit);
	$.ajax({
		url: ctx +"/orderbase/queryUserMapper?curPage="+num+"&pageCount="+pageCount,
		data:{
			realName:realName,
			userMobile:userMobile,
			adValid:isCommit,
			valid:valid
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			//showMsg(data.msg);
			//$("#pageUserDiv").html("");
 			//$("#pageUserDiv").append(createPageDiv(data.page));
 			$("#pageUserDiv").pagination(data.page,listBodyUser);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			var html="";
			if (data.msg =="00") {
				var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						html +="<tr width='98%' onclick='checkUser(" +v.id +"," +i +")'>" ;
						html +="<td>"+(total + num - pageCount);
						html +="</td><td>"+v.realName;
						html +="</td><td>"+(v.userSex==1?"男":(v.userSex==2?"女":"保密"));
						html +="</td><td>"+v.userMobile;
						html +="</td><td>"+numberToDatestr(v.createTime,8); 
						html +="</td><td>"+selectedcardType(v.cardType);
						html +="</td><td>"+v.cardNum ;
						//html += "123456789123456789";
						html +="</td><td title='" +v.userCityText +"'>"+v.userCityText;
						html +="<input type='hidden' value='" +v.userCity +"' />" ;
						html +="<input type='hidden' value='" +v.userAddress +"' />" ;
						html +="<input type='hidden' value='" +v.longitude +"' />" ;
						html +="<input type='hidden' value='" +v.latitude +"' />" ;
						html +="<input type='hidden' value='" +v.cityCode +"' />" ;
						html +="<input type='hidden' value='" +v.version +"' />" ;
						html +="<input type='hidden' value='" +v.priceType +"' />" ;
						html +="<input type='hidden' value='" +v.cardType +"' />" ;
						html +="<input type='hidden' value='" +v.channelId +"' />" ;
						html +="<input type='hidden' value='" +v.channel +"' />" ;
						html +="</td></tr>";
					})
			}
			$("#listBodyUser").html(html);
		}
	});
	/*表格点击行高亮*/
    trColor("#listBodyUser > tr");
}

function selectedcardType(cardType){
	var card = "" ;
	if(cardType==1){
		card = "身份证" ;
	}else if(cardType==2){
		card = "护照" ;
	}else if(cardType==3){
		card = "驾照" ;
	}
	return card;
}

//取到订单详细信息
function checkUser(id,index){
	if($("#listBodyUser > tr").eq(index).attr("class")=="info"){
		$("#checkValue").val("");
		$("#orderUserId").val("");
	}else{
		$("#checkValue").val(index);
		$("#orderUserId").val(id);
	}
}

function serUserId(){
	queryUser(0,10,1,2) ;
	$("#checkValue").val(0);
}

// 修改用户
function editOrderUser(){
	var userId = $("#orderUserId").val();
	if(userId!=""){
		openModule('${ctx}/jsp/orderUserEdit.jsp?edit=1','','',{},'orderUserEdit');
	}else{
		$.alert({text:"请先选择用户信息！"});
		return ;
	}
	
}

// 下一步信息
function nextModule(){
	var userId = $("#orderUserId").val();
	var data = getUserValues();
	if(userId!=""){
		closeModule("orderUser",function(){openModule('/order/jsp/orderAdd.jsp',data,{},{width:'70%'},'orderAdd')});
	}else{
		$.alert({text:"请先选择用户信息！"});
		return ;
	}
}

function getUserValues(){
	var userId = $("#orderUserId").val();
	if(userId!=""){
		var value = $("#checkValue").val();
		var trd = $("#listBodyUser tr:eq("+value+")").children("td");
		var name = trd.eq(1).text() ;
		var sex = trd.eq(2).text() ;
		var mobile = trd.eq(3).text() ;
		var createTime = trd.eq(4).text() ;
		var cardTypeText = trd.eq(5).text() ;
		var cardNum = trd.eq(6).text() ;
		var city = trd.eq(7).text() ;
		var userCity = trd.eq(7).find("input:eq(0)").val() ;
		var userAddress = trd.eq(7).find("input:eq(1)").val() ;
		var longitude = trd.eq(7).find("input:eq(2)").val() ;
		var latitude = trd.eq(7).find("input:eq(3)").val() ;
		var cityCode = trd.eq(7).find("input:eq(4)").val() ;
		var version = trd.eq(7).find("input:eq(5)").val() ;
		var priceType = trd.eq(7).find("input:eq(6)").val() ;
		var cardType = trd.eq(7).find("input:eq(7)").val() ;
		var channelId = trd.eq(7).find("input:eq(8)").val() ;
		var channel = trd.eq(7).find("input:eq(9)").val() ;
		return {"id":userId,"name":name,"sex":sex,"mobile":mobile,"createTime":createTime,"cardType":cardType,"cardTypeText":cardTypeText,
				"cardNum":cardNum,"city":city,"userCity":userCity,"userAddress":userAddress,
				"longitude":longitude,"latitude":latitude,"cityCode":cityCode,"version":version,"priceType":priceType,
				"channelId":channelId,"channel":channel};
	}
}

</script>
</html>

