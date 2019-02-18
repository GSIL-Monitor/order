<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="mytabs-wrap">
		<ul class="mytabs">
			<li class="tab-item tab-active" id="threeOrderOne1" onclick="threeOrderOne(1);">基本信息</li>
			<li class="tab-item" id="threeOrderOne2" onclick="threeOrderOne(2);">缴费</li>
		</ul>
	</div>
	<div id="arr" class="arr">
		<span id="left">&lt;</span><span id="right">&gt;</span>
	</div>
	<div class="tab-content" id="detailOne">
		<div class="main tab-selected">
			<div class="widget-content">
				<input type="hidden" id="threeOrderOutOneUserId" name="threeOrderOutOneUserId"/>
				<input type="hidden" id="threeOrderOutOneOrderId" name="threeOrderOutOneOrderId"/>
				<input type="hidden" id="threeOrderOutOneOrderServerType" name="threeOrderOutOneOrderServerType" />
				<table class="table table-condensed">
					<tr>
						<td colspan="2"><h4>详细信息</h4></td>
					</tr>
					<tr>
						<td colspan="2">订单ID：<span id="threeOrderOutOneOrderCode"></span></td>
					</tr>
					<tr>
						<td>订单分类：<span id="threeOrderOutOneServserText"></span></td>
						<td>商品名称：<span id="threeOrderOutOneProductName"></span></td>
					</tr>
					<tr>
						<td>订单状态：<span id="threeOrderOutOneStatusText"></span></td>
						<td>订单来源：<span id="threeOrderOutOneSourceText"></span></td>
					</tr>
					<tr>
						<td colspan="2">开始时间：<span id="threeOrderOutOneStartTime"></span></td>
					</tr>
					<tr>
						<td colspan="2">结束时间：<span id="threeOrderOutOneEndTime"></span></td>
					</tr>
					<tr>
						<td colspan="2">服务地址：<span id="threeOrderOutOneAddress"></span></td>
					</tr>
					<tr>
						<td colspan="2">订单备注：<span id="threeOrderOutOneRemark"></span></td>
					</tr>
					<tr>
						<td colspan="2"><h4>客户信息</h4></td>
					</tr>
					<tr>
						<td>客户姓名：<span id="threeOrderOutOneUserName"></span></td>
						<td>联系方式：<span id="threeOrderOutOneUserMobile"></span></td>
					</tr>
					<tr>
						<td width="50%">性别：<span id="threeOrderOutOneUserSex"></span></td>
						<td width="50%">出生年月：<span id="threeOrderOutOneUserBirth"></span></td>
					</tr>
					<tr>
						<td colspan="2">家庭地址：<span id="threeOrderOutOneUserAddress"></span></td>
					</tr>
					<tr>
						<td colspan="2"><h4>管家信息</h4></td>
					</tr>
					<tr>
						<td width="50%">所属管家：<span id="threeOrderOutOneAuthManagerRealName"></span></td>
						<td width="50%">联系方式：<span id="threeOrderOutOneAuthManagerPhone"></span></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div id="accountsOne" style="display: none;">
		<div class="main tab-selected">
			<div class="widget-content">
				<div class="row" style="margin-top: 10px;">
					<div class="form-group  col-xs-12">
						<button class="btn btn-primary btn-sm" type="button" onclick="addAccounts()">
							<em class="add glyphicon glyphicon-plus-sign"></em>新增结算单
						</button>
					</div>
				</div>
				<!-- 循环结算单信息开始 -->
				<div id="threeOrderOneAccountsList">
					
				</div>
				<!-- 循环结算单信息结束 -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function setThreeOrderOutOne(orderId) {
			$("#threeOrderOutOneOrderId").val(orderId);
			queryThreeOrderOneDetail(orderId);
			queryThreeOrderOneAccounts(orderId);
		}

		//取到订单详细信息
		function queryThreeOrderOneDetail(orderId) {
			var ctx = $("#ctx").val();
			$.ajax({
				url : ctx + "/threeOrderOut/queryOrderOneDetail",
				data : {
					id : orderId
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					if (data.msg == "00") {
						var v = data.order;
						if (v) {
							$("#threeOrderOutOneOrderCode").text(v.orderCode);
							$("#threeOrderOutOneOrderServerType").val(v.orderType);
							$("#threeOrderOutOneServserText").text(v.typeText);
							$("#threeOrderOutOneProductName").text(v.productName);
							$("#threeOrderOutOneStartTime").text(numberToDatestr(v.startTime, 12));
							$("#threeOrderOutOneEndTime").text(numberToDatestr(v.endTime, 12));
							if('1'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("新单");
							}else if('2'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已受理");
							}else if('3'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("匹配中");
							}else if('4'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已匹配");
							}else if('5'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("待面试");
							}else if('6'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("面试成功");
							}else if('7'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已签约");
							}else if('8'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已上户");
							}else if('9'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已完成");
							}else if('10'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已取消");
							}else if('11'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已下户");
							}else if('12'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("已终止");
							}else if('13'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("捡货中");
							}else if('14'==v.orderStatus){
								$("#threeOrderOutOneStatusText").text("配送中");
							}
							$("#threeOrderOutOneSourceText").text(v.sourceText);
							$("#threeOrderOutOneAddress").text(v.receiverAddress);
							$("#threeOrderOutOneRemark").text(v.remark);
							// 客户信息
							$("#threeOrderOutOneUserId").val(v.userId);
							$("#threeOrderOutOneUserName").text(v.userName);
							$("#threeOrderOutOneUserMobile").text(v.userMobile);
							$("#threeOrderOutOneUserSex").text(v.sex == 1 ? "男" : "女");
							$("#threeOrderOutOneUserBirth").text(v.birth == "" ? "" : numberToDatestr(v.birth, 8));
							$("#threeOrderOutOneUserAddress").text(v.userAddress);
							$("#threeOrderOutOneAuthManagerRealName").text(v.authManagerRealName);
							$("#threeOrderOutOneAuthManagerPhone").text(v.authManagerPhone);
						}
					}
				}
			});
		}
		
		function queryThreeOrderOneAccounts(orderId){
			var ctx = $("#ctx").val();
			$.ajax({
				url : ctx + "/threeOrderOut/queryOrderOneAccounts",
				data : {
					orderId : orderId
				},
				type : "post",
				dataType : "json",
				async : false,
				success : function(data) {
					var html="";
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							html += "<div class='row'>";
							html += "<div class='form-group  col-xs-7'><label><p>结算单号："+v.accountsId+"</p></label></div>";
							html += "<div class='form-group  col-xs-5'><label><p>时间："+v.createTime+"</p></label></div>";
							html += "</div>";
							html += "<div class='row'>";
							html += "<div class='form-group  col-xs-7'><label><p>应收："+v.accountAmount+"</p></label></div>";
							html += "<div class='form-group  col-xs-5'>";
							if(v.children.length==0){
								html += "<button type='button' class='btn btn-primary btn-sm' onclick='addPayfee("+v.accountsId+","+v.accountAmount+")'>新增缴费</button>";
							}else{
								html += "<button type='button' class='btn btn-primary btn-sm' disabled='disabled'>新增缴费</button>";
							}
							html += "</div>";
							html += "</div>";
							html += "<table class='table table-hover table-responsive'>";
							html += "<thead>";
							html += "<tr><td width='25%'>序号</td><td width='25%'>缴费人</td><td width='25%'>缴费方式</td><td width='25%'>缴费金额</td></tr>";
							html += "</thead>";
							html += "<tbody style='width: 100%'>";
							if(v.children.length!=0){
								$.each(v.children,function(j,m){
									html += "<tr>";
									html += "<td>"+(j+1)+"</td>";
									html += "<td>"+m.postUser+"</td>";
									if(m.feePost == "1002"){
										html += "<td>银行转账</td>";
									}else if(m.feePost == "1005"){
										html += "<td>POS机</td>";
									}else if(m.feePost == "1008"){
										html += "<td>融汇天下POS机</td>";
									}else if(m.feePost == "1010"){
										html += "<td>礼品卡</td>";
									}else if(m.feePost == "1101"){
										html += "<td>他人代收</td>";
									}
									html += "<td>"+m.feeSum+"</td>";
									html += "</tr>";
								})
								
							}else{
								html += "<tr><td colspan='4'>暂无记录</td></tr>";
							}
							html += "";
							html += "</tbody>";
							html += "</table>";
						})
						$("#threeOrderOneAccountsList").html(html);
					}
				}
			});
		}

		function threeOrderOne(num) {
			if (num == 1) {
				$("#threeOrderOne1").addClass("tab-active");
				$("#threeOrderOne2").removeClass("tab-active");
				$("#detailOne").css("display", "block");
				$("#accountsOne").css("display", "none");
			} else if (num == 2) {
				$("#threeOrderOne1").removeClass("tab-active");
				$("#threeOrderOne2").addClass("tab-active");
				$("#detailOne").css("display", "none");
				$("#accountsOne").css("display", "block");
			}
		}
		
		function addAccounts(){
			var ctx = $("#ctx").val();
			var orderId = $("#threeOrderOutOneOrderId").val();
			if(orderId!=null&&orderId!=""){
				var data = {
					orderId : orderId
				}
				openModule(ctx +'/jsp/order_three/threeOrderAccounts.jsp',data,'','','threeOrderAccounts');
			}else{
				$.alert({text:"请先选择要结算的订单！"});
			}
		}
		function addPayfee(accountsId,accountAmount) {
			var orderId = $("#threeOrderOutOneOrderId").val();
			var userId = $("#threeOrderOutOneUserId").val();
			var ctx = $("#ctx").val();
			var data = {
				orderId : orderId,
				accountsId : accountsId,
				accountAmount : accountAmount,
				userId : userId
			}
			openModule(ctx + '/jsp/order_three/threeOrderPayfee.jsp', data, '', {width : '70%'}, 'threeOrderPayfee');
		}
	</script>
</body>
</html>