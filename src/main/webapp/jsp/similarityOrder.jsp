<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

<script src="${ctx}/js/orderBase.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/main.js"></script>
</head>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="modal fade" id="modelFrameOrderAdd">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×
					</button>
					<h4 class="modal-title">选择相似订单</h4>
				</div>
				<div class="modal-body">
					<form action="" method="post" class="form-inline">
						<div>
							<label><span style="font-weight: bold;">订单类型:</span> <select
								name="serverType" id="similarityId" class="form-control"></select>
							</label>
							<button onclick="querySimilarityOrdersByLike(0,10);"
								type="button" class="btn btn-sm btn-default"
								style="margin-left: 120px;">查询</button>
						</div>
					</form>
					<hr>
					<div align="center" style="font-size: 22px;">
						<table border="1" class="table table-hover table-striped"
							style="width: 85%;">
							<thead>
								<tr>
									<th width="3%"></th>
									<th width="7%">序号</th>
									<th width="15%">订单类型</th>
									<th width="12%">客户姓名</th>
									<th width="18%">创建时间</th>
									<th width="13%">录入人</th>
									<th width="20%">录入部门</th>
									<th width="12%">订单状态</th>
								</tr>
							</thead>
							<tbody id="listSimilarityId" style="width: 100%"></tbody>
						</table>
					</div>
					<div class="panel-heading panel-new">
						<ul class="pagination pagination-sm navbar-right" id="similaryPage"></ul>
					</div>
				</div>
				<div class="modal-footer text-center">
					<button type="button" class="btn btn-sm btn-primary"
						data-dismiss="modal">取消
					</button>
					<button type="button" class="btn btn-sm btn-primary"
						id="nextSimilaryModule" onclick="nextSimilaryModule();">下一步</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			queryOrderServerTypeNew("1002", 12, "similarityId");
			querySimilarityOrdersByLike(0,10);
		});
		function querySimilarityOrdersByLike(num, pageCount) {
			var orderSimilarityAddUserId = "${param.orderAddUserId}";
			var serverType = $("#similarityId").val();
			var orderType = serverType.split("-")[0];
			var cateType = serverType.split("-")[1];
			$.ajax({
						url : ctx + "/order/queryOrder?curPage=" + num
								+ "&pageCount=" + pageCount,
						data : {
							userId : orderSimilarityAddUserId,
							cateType : cateType,
							orderType : orderType,
							cateTypeNot:"3,7,8"
						},
						type : "post",
						dataType : "json",
						async : false,
						success : function(data) {
							var html = "";
							var pageCount = data.page.pageCount;
							var curPage = data.page.curPage;
							var total = curPage * pageCount;
							var num = 0;
							$.each(
									data.list,
									function(i, v) {
										num = num + 1;
										html += "<tr>";
										html += "<td align='left'><input type='radio' name='smId' cateType="+v.cateType+" value="+v.id+">";
										html += "</td><td>"
												+ (total + num - pageCount);
										html += "</td><td align='left'>"
												+ v.typeText;
										html += "</td><td align='left'>"
												+ v.userName;
										html += "</td><td align='left'>"
												+ numberToDatestr(
														v.createTime, 8);
										html += "</td><td align='left'>"
												+ v.createByText;
										html += "</td><td align='left'>"
												+ v.createDeptText;
										html += "</td><td align='left'>"
												+ v.statusText;

										html += "</td></tr>";
									});
							$("#listSimilarityId").html(html);
							trColor("#native_tbody > tr");
						    radioColor("#listSimilarityId > tr");
							$("#similaryPage").pagination1(data.page,
									listBodyOrder);
						}
					});
		}
		/**
		 * 分页跳转使用
		 */
		function listBodyOrder(num, pageCount) {
			$("#listSimilarityId").empty();
			querySimilarityOrdersByLike(num, pageCount);
		}
		function nextSimilaryModule() {
			 var radioSmId=$('input:radio[name="smId"]:checked').val();
	            if(radioSmId==null){
	            	$.alert({text: "请选择一条历史订单"});
	                return false;
	            }
			//closeModule('similaryOrder','','defaultManagerAll');
			closeModule("similaryOrder");
			$("#defaultManagerAll").removeClass("defaultManagerAll").addClass("defaultManagerAllHidden");
			var orderId = $("input[name='smId']:checked").val();
			var cateType = $("input[name='smId']:checked").attr("cateType");
			if (cateType == 1 || cateType == 2||cateType == 4) {
				$.ajax({
						url : ctx + "/order/queryOrderEcho",
						data : {
							orderId : orderId
						},
						type : "post",
						dataType : "json",
						async : false,
						success : function(data) {
							if (data.msg == "00") {
								var isInsure = data.map.isInsure;
								var insureAmount = data.map.insureAmount;
								var forestsserver = data.map.forestsserver;
								var homeForests = data.map.homeForests;
								var familys = data.map.familys;
								var education = data.map.education;
								var personLevel = data.map.personLevel;
								var origin = data.map.origin;
								var interviewAddress = data.map.interviewAddress;
								var personNumber = data.map.personNumber;
								var minAge = data.map.minAge;
								var maxAge = data.map.maxAge;
								var productCode = data.map.productCode;
								var productName = data.map.productName;
								var categoryCode = data.map.categoryCode;
								var sex = data.map.sex;
								var quantity = data.map.quantity;
								var rechargeType = data.map.rechargeType;
								var rechargeBy = data.map.rechargeBy;
								var rechargeDept = data.map.rechargeDept;
								var orderChannel = data.map.orderChannel;
								var city = data.map.city;
								var city1 = city.substring(0,6);
								var serviceObject=data.map.serviceObject
								
								$("#orderAddChannel").val(orderChannel);
								
								$("#orderAddProvince").val(city1);
								$("#orderAddProvince").trigger("onclick");
								
								$("#orderAddCity").val(city);
								$("#orderAddCity").trigger("onchange");
								
								
								
								categoryCode1 = categoryCode.substring(0, 4);
								categoryCode2 = categoryCode.substring(0, 8);
								$("#oneClassify").val(categoryCode1);
								$("#oneClassify").trigger("onchange");
								$("#twoClassify").val(categoryCode2);
								$("#twoClassify").trigger("onchange");
								$("#threeClassify").val(categoryCode);
								$("#threeClassify").trigger("onchange");
								if (personNumber != undefined) {
									$("#personNumberServer").val(personNumber);
								}
								if (interviewAddress != undefined) {
									$("#interviewAddressServer").val(
											interviewAddress);
								}
								if (education != undefined) {
									$("#educationServer").val(education);
								}
								if (personLevel != undefined) {
									$("#levelServer").val(personLevel);
								}
								if (origin != undefined) {
									$("#originServer").val(origin);
								}
								if (isInsure != undefined) {
									$("#insure").val(isInsure);
									$("#insure").trigger("onchange");
								}
								if (minAge != undefined && minAge != 0) {
									$("#minAgeServer").val(minAge);
								}
								if (maxAge != undefined && maxAge != 0) {
									$("#maxAgeServer").val(maxAge);
								}
								if (forestsserver != undefined
										&& forestsserver != 0) {
									$("#ForestsServerOne").val(forestsserver);
								}
								if (homeForests != undefined
										&& homeForests != 0) {
									$("#homeForestsServer").val(homeForests);
								}
								if (familys != undefined && familys != 0) {
									$("#familysServer").val(familys);
								}
								if (sex != undefined && sex != 0) {
									$("#sexAddServer").val(sex);
								}
								if (quantity != undefined && quantity != 0) {
									$("#pickQuantityServer").val(quantity);
								}
								if (serviceObject != undefined) {
									$("#serviceObject").val(serviceObject);
								}
								if (productName != undefined) {
									var count = $("#baseProduct option").length;
									for (var i = 0; i < count; i++) {
										if ($("#baseProduct").get(0).options[i].text == productName) {
											$("#baseProduct").get(0).options[i].selected = true;
											break;
										}
									}
									selectStock();
									$("#serverSelectFourth option:eq(1)").attr('selected', 'selected');
									$("#serverSelectFourth").trigger("onchange");
									if (rechargeType != undefined) {
										if (rechargeType == 1) {
											$("#orderFenfa").val(-2);
											$("#orderFenfa").trigger("onchange");
											if (rechargeDept != undefined) {
												$("#mendianjidi").val(rechargeDept);
											}
											selguanjia();
											if (rechargeBy != undefined) {
												$("#guanjiaxingming").val(rechargeBy);
											}
											if (insureAmount != undefined) {
												$("#insureAmount").val(insureAmount);
											} 
										}
										if (rechargeType == 2) {
											$("#orderFenfa").val(-3);
											$("#orderFenfa").trigger("onchange");
										}
									}
								}
							}
						}
					});
			   }
		}
	</script>
</body>
</html>

