<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- TOI -- ThreeOrderIn -->
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			.modal-order-iframe{height:600px; width:800px;margin: 30px auto; }
			.otable-tr-top{height:25px; border-top:1px solid #CCC}
			.otable-tr-bottom{height:25px; border-bottom:1px solid #CCC;}
			.otable-td-left{border-bottom:1px solid #CCC; border-left:1px solid #CCC; } 
			.otable-td-right{border-bottom:1px solid #CCC; border-right:1px solid #CCC; }
		</style>
	</head>
	<body>
	  <div class="modal fade">  
		  <div class="modal-order-iframe" >  
		    <div class="modal-content">
		    	<div class="modal-header" >  
		        	<button type="button" class="close" data-dismiss="modal">×</button>  
		        	<h4 class="modal-title">选择客户</h4>
		      	</div> 
				<div class="modal-body">
					<div class="modal-content-basic">
						<div class="info-select clearfix">
							<form class="form-inline">
			                    <div class="row">
			                        <div class="form-group col-xs-5 col-md-offset-1">
			                            <label class="parentCls">
					                        <p>客户姓名：</p>
					                        <input type="text" class="form-control inputElem" name="TOI_realName" id="TOI_realName"/>
					                    </label>
			                        </div>
			                        <div class="form-group col-xs-6">
			                           <label class="parentCls">
					                        <p>联系方式：</p>
					                        <input type="text" class="form-control inputElem" name="TOI_userMobile" id="TOI_userMobile">
					                    </label>
			                        </div>
			                    </div>
	                		</form>
	                		<div class="panel-heading">
	                			<button class="btn btn-sm btn-default fr mr20" 
			                     	type="button" onclick="serUserId();">
		                     		<em class="glyphicon glyphicon-search"></em>查询
			                     </button>
			                     <button class="btn btn-sm btn-default fr mr20" type="button" 
			                     	onclick="openModule('/order/jsp/order_three/in/customer_operator/orderThreeUserEdit.jsp','','','','orderThreeUserEdit');">
			                     	<em class="glyphicon glyphicon-menu-hamburger"></em>新增
			                     </button>
	                		</div>
	                		<div class="panel-content table-responsive">
								<input type="hidden" id="TOI_orderUserId" name="TOI_userId" />
								<input type="hidden" id="TOI_checkValue" name="TOI_checkValue" />
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
									<tbody id="TOI_listBodyUser" style="width:100%;">
					                </tbody>
				            	</table>
				            </div>
				            <div class="pagination fr">
				            	<ul id="TOI_pageUserDiv"></ul>
				            </div>
						</div>
	                </div>
			  </div>
		      <div class="modal-footer" style="text-align: center;">  
		      	<button type="button" class="btn btn-sm btn-primary" onclick="tOI_nextModule();">下一步</button>  
		        <button type="button" data-dismiss="modal" class="btn btn-sm btn-primary" onclick="closeModule('orderThreeCustomerList')">取消</button>  
		      </div>  
		    </div>
		  </div>
		</div>         
	</body>
	<script type="text/javascript">
	
	/**
	 * 初始化数据，获得用户列表
	 */
	$(function(){
		queryUser(0,5,1);
		//默认选择第一行
		updateColorUser(0);
	});
	
	/**
	 * 手动加载,查选
	 */
	function serUserId(){
		queryUser(0,5,1) ;
		updateColorUser(0);
	}
	
	/**
	 * 分页跳转使用
	 */
	function fun_TOI_listBodyUser(num,pageCount) {
		$("#TOI_listBodyUser").empty();
		queryUser(num,pageCount,1);
		updateColorUser(0);
	}
	
	//默认选择一行,记录选择行的用户
	function updateColorUser(value){
		$("#TOI_checkValue").val(value);
		$("#TOI_listBodyUser tr:eq("+value+")").addClass("info").css("cursor","pointer");
	}
	//点击选择后，设置选中的信息
	function checkUser(id,index){
		$("#TOI_checkValue").val(index);
		$("#TOI_orderUserId").val(id);
	}
	
	/**
	 * 分页跳转使用
	 
	function toPage(num) {
		if (null == num || "" == num || isNaN(parseInt(num))) {
			$.alert({text:"请输入数字。"});
			return;
		}
		var pageCount = $("#pageCount").val();
		if (pageCount == "" || pageCount == undefined) {
			pageCount = 3;
		}
		$("#TOI_listBodyUser").empty();
		//queryUser(num, pageCount);
	}*/
	
	// 用户列表
	function queryUser(num,pageCount,valid){
		
		var ctx = $("#ctx").val();
		var realName = $("#TOI_realName").val();
		var userMobile = $("#TOI_userMobile").val();
		$.ajax({
			url: ctx +"/threeOrder/queryThreePartUserMapper?curPage="+num+"&pageCount="+pageCount,
			data:{
				realName:realName,
				userMobile:userMobile,
				valid:valid
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				$("#TOI_pageUserDiv").show();
	 			$("#TOI_pageUserDiv").pagination(data.page,fun_TOI_listBodyUser);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				var html="";
				if (data.msg == "00") {
					
					var num = 
						$.each(data.list,function(i,v){
							if(i==0){
								$("#TOI_orderUserId").val(v.id);
							}
							var card_type = "暂无";
							if(v.cardType == '1' || v.cardType == 1){
								card_type = "身份证";
							}
							
							if(v.cardType == '2' || v.cardType == 2){
								card_type = "护照";
							}
							
							if(v.cardType == '3' || v.cardType == 3){
								card_type = "驾照";
							}
							num=i+1;
							html +="<tr width='98%' onclick='checkUser(" +v.id +"," +i +")'>" ;
							html +="<td>"+(total + num - pageCount);
							html +="</td><td>"+v.realName;
							html +="</td><td>"+(v.userSex==1?"男":"女");
							html +="</td><td>"+v.userMobile;
							html +="</td><td>"+numberToDatestr(v.createTime,8); 
							html +="</td><td>"+card_type;
							html +="</td><td>"+($.trim(v.cardNum)==""?"暂无":v.cardNum) ;
							html +="</td><td title='"+v.userCity+"'>"+v.userCity;
							html +="<input type='hidden' value='" +v.userCityCode +"' />" ;
							html +="<input type='hidden' value='"+v.userProvince+v.userCity+v.userDistrict+v.userAddress+"' />";
							html +="<input type='hidden' value='" +v.is_vip +"' />" ;
							html +="<input type='hidden' value='" +v.is_big_cust +"' />" ;
							html +="</td></tr>";
						});
				}else if(data.msg =="02"){
					$("#TOI_orderUserId").val("");
					$("#TOI_checkValue").val("");
					$("#TOI_pageUserDiv").hide();
					//$.alert({text:"没有符合条件的数据。"});
					html +="<tr><td colspan='8'>暂无记录</td></tr>";
				}
				
				$("#TOI_listBodyUser").html(html);
				trColor("#TOI_listBodyUser > tr");
			}
		});
	}
	
	//下一步
	function tOI_nextModule(){
		var userId = $("#TOI_orderUserId").val();
		if(userId && userId!=""){
			var value = $("#TOI_checkValue").val();
			var trd = $("#TOI_listBodyUser tr:eq("+value+")").children("td");
			var name = trd.eq(1).text() ;
			var sex = trd.eq(2).text() ;
			var mobile = trd.eq(3).text() ;
			var createTime = trd.eq(4).text() ;
			var cardType = trd.eq(5).text() ;
			var cardNum = trd.eq(6).text() ;
			var city = trd.eq(7).text() ;
			var userCity = trd.eq(7).find("input:eq(0)").val() ;
			var addr = trd.eq(7).find("input:eq(1)").val() ;
			var isVip = trd.eq(7).find("input:eq(2)").val() ;
			var isBigCust = trd.eq(7).find("input:eq(3)").val() ;
			closeModule("orderThreeCustomerList",function(){openModule('/order/jsp/order_three/in/order_operator/orderThreeAdd.jsp',
					{"id":userId,"name":name,"sex":sex,"mobile":mobile,
						"createTime":createTime,"cardType":cardType,
						"cardNum":cardNum,"city":city,"userCity":userCity,"addr":addr,"isVip":isVip,"isBigCust":isBigCust},
					{},{width:'70%'},'threeOrderAdd')});
		}else{
			$.alert({text:"请先选择客户信息"});
			return ;
		}
	}
	</script>
</html>

