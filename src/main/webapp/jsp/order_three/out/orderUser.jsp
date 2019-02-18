<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
		.modal-order-iframe{height:450px; width: 800px;margin: 30px auto; }
		.otable-tr-top{height:25px; border-top:1px solid #CCC}
		.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
		.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
		.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
	</style>
</head>
<body>
	<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" id="modelFrame">
		<div class="modal-order-iframe" >  
		    <div class="modal-content" id="modalCPM">
		    	<div class="modal-header" style="height:45px;" >  
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeModule('orderUser')">×</button>  
			        <h4 class="modal-title" style="height:30px;" >选择客户</h4>
				</div>
				<div class="modal-body">
					<form class="form-inline" action="" method="post">
						<div class="row">
							<div class="form-group col-xs-5 col-md-offset-1">
								<label>
									<p>客户姓名:</p>
									<input type="text" class="form-control" name="threeOrderUserRealName" id="threeOrderUserRealName"/>
								</label>
							</div>
							<div class="form-group col-xs-6">
								<label>
									<p>联系方式:</p>
									<input type="text" class="form-control" name="threeOrderUserUserMobile" id="threeOrderUserUserMobile"/>
								</label>
							</div>
						</div>
					</form>
					<div class="panel-heading">
						<button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryUser(0,5,1,2)"><em class="glyphicon glyphicon-search"></em>查询</button>
						<button type="button" class="btn btn-sm btn-default fr mr20" onclick="openModule('${ctx}/jsp/order_three/out/threeOrderOutUserAdd.jsp','','','','threeOrderOutUserAdd')"><em class="glyphicon glyphicon-menu-hamburger"></em>新增</button>
					</div>
					<div class="panel-content table-responsive">
						<input type="hidden" id="threeOrderUserId" name="threeOrderUserId">
						<input type="hidden" id="threeOrderUserRowNo" name="threeOrderUserRowNo">
						<table class="table table-hover" style="width:100%;">
			                <thead>
			                	<tr>
									<th width="10%">序号</th>
									<th width="10%">客户姓名</th>
									<th width="10%">性别</th>
									<th width="15%">联系方式</th>
									<th width="10%">注册时间</th>
									<th width="10%">证件类型</th>
									<th width="20%">证件号码</th>
									<th width="15%">所在城市</th>
			                   	</tr>
							</thead>
							<tbody id="threeOrderOutListBodyUser" style="width:100%;"></tbody>
			            </table>
					</div>
					<div class="row">
						<div class="panel-heading panel-new">
							<ul class="pagination pagination-sm navbar-right" id="threeOrderOutUserListPage"></ul>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('threeOrderOutUser')">取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="nextModule()" >下一步</button>
				</div>
			</div>
		</div>
	</div>         
</body>
<script type="text/javascript">
/**
 * 初始化数据，获得客户列表，并将第一行置灰
 */
$(function(){
	queryUser(0,5,1,2);
});

/**
 * 手动加载
 */
function serUserId(){
	queryUser(0,5,1,2) ;
}

/**
 * 分页跳转使用
 */
function listBodyUser(num,pageCount) {
	$("#threeOrderOutListBodyUser").empty();
	queryUser(num,pageCount,1,2);
}
/**
 * 分页跳转使用
 */
function toPage(num) {
	if (null == num || "" == num || isNaN(parseInt(num))) {
		$.alert({text:"请输入数字！"});
		return;
	}
	var pageCount = $("#pageCount").val();
	if (pageCount == "" || pageCount == undefined) {
		pageCount = 3;
	}
	$("#threeOrderOutListBodyUser").empty();
	queryOrders(num, pageCount);
}
//点击选中一行
function updateColorUser(value){
	$("#threeOrderUserRowNo").val(value);
}

// 客户列表
function queryUser(num,pageCount,valid,isCommit){
	var ctx = $("#ctx").val();
	var realName = $("#threeOrderUserRealName").val();
	var userMobile = $("#threeOrderUserUserMobile").val();
	if(isCommit==2 && userMobile!="" && userMobile.length>=5){
		isCommit = 1;
	}
	$.ajax({
		url: ctx +"/threeOrderOut/queryOrderUser?curPage="+num+"&pageCount="+pageCount,
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
 			$("#threeOrderOutUserListPage").pagination(data.page,listBodyUser);
			var pageCount = data.page.pageCount;
			var curPage = data.page.curPage;
			var total = curPage * pageCount;
			var html="";
			if (data.msg =="00") {
				var num;
				$.each(data.list,function(i,v){
					num=i+1;
					html +="<tr width='98%' onclick='checkUser(" +v.id +"," +i +")'>" ;
					html +="<input type='hidden'  value='"+v.cityCode+"'/><input type='hidden'  value='"+v.isBigCust+"'/><input type='hidden'  value='"+v.isVip+"'/>";
					html +="<td>"+(total + num - pageCount);
					html +="</td><td>"+v.realName;
					html +="</td><td>"+(v.userSex==1?"男":"女");
					html +="</td><td>"+v.userMobile;
					html +="</td><td>"+numberToDatestr(v.createTime,8); 
					html +="</td><td>"
					if(1==v.cardType){
						html +="身份证";
					}else if(2==v.cardType){
						html +="护照";
					}else if(3==v.cardType){
						html +="驾照";
					}
					html +="</td><td>"+v.cardNum;
					html +="</td><td>"+v.userCity;
					html +="</td></tr>";
				})
			}else if(data.msg=="01"){
				html = "<tr width='98%'><td colspan='8' align='center'>查询失败</td></tr>"
			}else if(data.msg=="02"){
				html = "<tr width='98%'><td colspan='8' align='center'>没有查到数据</td></tr>"
			}
			$("#threeOrderOutListBodyUser").html(html);
		}
	});
	/*表格点击行高亮*/
    trColor("#threeOrderOutListBodyUser > tr");
}

//取到订单详细信息
function checkUser(id,index){
	updateColorUser(index);
	$("#threeOrderUserId").val(id);
}
//下一步
function nextModule(){
	var userId = $("#threeOrderUserId").val();
	if(userId!=""){
		var ctx = $("#ctx").val();
		var value = $("#threeOrderUserRowNo").val();
		var trd = $("#threeOrderOutListBodyUser tr:eq("+value+")").children("td");
		//var cityCode = trd.parent().find("input").val();
		var cityCode = trd.parent().find("input:eq(0)").val() ;
		var isBigCust = trd.parent().find("input:eq(1)").val() ;
		var isVip = trd.parent().find("input:eq(2)").val() ;
		var data = {
			"id" : userId,
			"name" : trd.eq(1).text(),
			"sex" : trd.eq(2).text(),
			"mobile" : trd.eq(3).text(),
			"createTime" : trd.eq(4).text(),
			"cardType" : trd.eq(5).text(),
			"cardNum" : trd.eq(6).text(),
			"city" : trd.eq(7).text(),
			"cityCode" : cityCode,
			"isBigCust" : isBigCust,
			"isVip" : isVip
		}
		closeModule("threeOrderOutUser", function() {
			openModule(ctx+'/jsp/order_three/out/threeOrderOutAdd.jsp', data, {}, {width:'70%'}, 'threeOrderOutAdd')
		});
	} else {
		$.alert({text:"请先选择客户信息！"});
		return;
	}
}
</script>
</html>