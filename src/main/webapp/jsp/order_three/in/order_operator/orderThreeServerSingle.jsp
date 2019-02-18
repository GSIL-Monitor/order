<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<body>
	<!-- 字段的命名规则 T--three，O--order,I--in,S--Server,S--Single,A--Add -->
	<div class="row">
		<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
		<div class="col-xs-12">
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p>
							<span style="color: red">*</span>开始时间：
						</p> <input class="Wdate form-control" type="text"
						id="TOI_S_S_A_startTime" name="TOI_S_S_A_startTime"
						onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p>
							<span style="color: red">*</span>结束时间：
						</p> <input class="Wdate form-control" type="text"
						id="TOI_S_S_A_endTime" name="TOI_S_S_A_endTime"
						onfocus="WdatePicker({minDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd HH:mm'})" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-6">
					<label class="has-feedback">
						<p>
							<span style="color: red">*</span>订单渠道：
						</p> <select id="TOI_S_S_A_originchannel"
						name="TOI_S_S_A_originchannel" class="form-control">
					</select>
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p>居家面积：</p> <input id="TOI_S_S_A_homeForests"
						class="form-control" maxlength="8"
						onkeyup="javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\d]/g,'');}"
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
						onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" style="ime-mode:Disabled" />
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-3">
					<label class="has-feedback">
						<p class="text-r">
							数量(<span id="smallestUnit"></span>)：
						</p> <input type="text" class="form-control" id="number"
						onblur="totalAmount()" onkeydown="inputOnlyNum(event);"
						onkeypress="checkIfNum(event);"
						style="ime-mode:Disabled">
					</label>
				</div>
				<div class="form-group col-xs-3">
					<label>
						<p class="text-r">价格(元)：</p> <input type="text"
						class="form-control" disabled="true " id="price"> <input
						type="hidden" class="form-control" disabled="true "
						id="hiddenPrice"> <input type="hidden"
						class="form-control" disabled="true " id="productUnit"> <input
						type="hidden" class="form-control" disabled="true "
						id="productPriceType">
					</label>
				</div>
				<div class="form-group col-xs-3">
					<label>
						<p class="text-r">价格体系：</p> <input type="text"
						class="form-control" disabled="true " id="priceSystem">
					</label>
				</div>
				<div class="form-group col-xs-3">
					<label>
						<p class="text-r">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格：</p>
						<input type="text" class="form-control" disabled="true " id="spec">
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-12">
					<label class="form-label">
						<p>备注：</p> <textarea class="form-control form-textarea" rows="3"
							id="TOI_S_S_A_remark" name="TOI_S_S_A_remark"></textarea>
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-1">
					<label>
						<p>
							<span style="color: red">*</span>服务地址：
						</p> <input type="hidden" id="unicodes">
					</label>
				</div>
				<div id="TOI_S_S_A_userAddrList" class="form-group col-xs-8"
					style="overflow: auto; display: block; height: 100px;"></div>
				<div class="form-group col-xs-2">
					<button type="button" class="btn btn-sm btn-primary"
						onclick="openModule('/order/jsp/order_three/in/addr_operator/orderThreeFA_AddrAdd.jsp',{lx:1},'','','orderThreeFA_AddrAdd');">
						新增地址</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var keyIsNum = true;
	function inputOnlyNum(myEvent) {
		var keys = myEvent.keyCode;
		if (!((keys >= 48 && keys <= 57) || (keys >= 96 && keys <= 105)
				|| (keys == 8) || (keys == 46) || (keys == 37) || (keys == 39)
				|| (keys == 13) || (keys == 229))) {
			keyIsNum = false;
		} else {
			keyIsNum = true;
		}

	}
	function checkIfNum(myEvent) {
		if (!keyIsNum) {
			$.alert({
				text : "请输入数字！"
			});
			if (document.all)
				myEvent.returnValue = false;//ie
			else
				myEvent.preventDefault();//ff
		}

	}
	$(function() {
		//清除之前操作选择的地址
		$("#TOI_A_userAddrId").val("");
		$("#TOI_A_userAdde_proVersion").val("");
		//调用
		queryBaseChannel(null,"TOI_S_S_A_originchannel");
		getAddrListByUser("TOI_S_S_A_userAddrList");
	});
	// 保存
	function saveServerSingle(cateType) {
		var ctx = $("#ctx").val();
		//地址
		var userId = $("#TOI_A_userId").val();
		var startTime = $("#TOI_S_S_A_startTime").val();
		var endTime = $("#TOI_S_S_A_endTime").val();
		var receiverId = $("#TOI_A_userAddrId").val()
		//var address =$("#TOI_S_S_A_addr").val();
		var channel = $("#TOI_S_S_A_originchannel").val();
		var remark = $("#TOI_S_S_A_remark").val();
		var serverType = $("#TOI_A_threeClassify").val();
		var productCode = $("#TOI_A_serverType").val();
		
		var product_name = $("#TOI_A_serverType option:selected").text();
		var homeForests_code = $("#" + productCode).val();
		var homeForests = $("#TOI_S_S_A_homeForests").val();
		serverType = serverType.split(",")[0];
		var number = $("#number").val();//数量
		var price = $("#price").val();//总价格
		var hiddenPrice = $("#hiddenPrice").val();//单价
		var spec = $("#spec").val();//规格 
		var productUnit = $("#productUnit").val();//最小单位
		var productPriceType = $("#productPriceType").val();//价格体系
		var cateCode = $("#TOI_A_threeClassify option:selected").val().split(
				",")[0];//分类code
		var city = $("#TOI_A_city option:selected").val();//城市code
		if (productCode==0 || productCode == '0'||product_name=="...请选择...") {
			$.alert({
				text : "请选择产品！"
			});
			return;
		}
		// 按平方计算
		if ($.trim(homeForests_code) == "20070011") {
			if (!homeForests || $.trim(homeForests) == '') {
				$.alert({
					text : "此产品是按照平方计算，请输入居家面积。"
				});
				return;
			}
		} else {
			//
			if (!startTime || $.trim(startTime) == '' || !endTime
					|| $.trim(endTime) == '') {
				$.alert({
					text : "请选择开始、结束时间。"
				});
				return;
			}
			try {
				var st = new Date(startTime.replace(/\-/g, "\/"));
				var et = new Date(endTime.replace(/\-/g, "\/"));
				if (et < st) {
					$.alert({
						text : "开始时间大于结束时间。"
					});
					return;
				}
			} catch (e) {
				$.alert({
					text : "时间格式不正确。"
				});
				return;
			}
		}

		if (!userId || $.trim(userId) == '') {
			$.alert({
				text : "请选择用户。"
			});
			return;
		}
		if (!productCode || $.trim(productCode) == '') {
			$.alert({
				text : "请选择产品。"
			});
			return;
		}

		if ($.trim(receiverId) == '' || !receiverId) {
			$.alert({
				text : "请选择地址。"
			});
			return;
		}

		if (channel==null||channel=="") {
			$.alert({
				text : "请选择订单渠道。"
			});
			return;
		}
		if (!number || $.trim(number) == '') {
			$.alert({
				text : "请输入数量。"
			});
			return;
		}
		var result = 1;
		$.ajax({
			url : ctx + "/threeOrderIn/saveThreeOrder",
			data : {
				userId : userId,
				receiverId : receiverId,
				cateType : cateType,
				startTime : startTime,
				endTime : endTime,
				homeForests : homeForests,
				receiverId : receiverId,
				remark : remark,
				serverType : serverType,
				orderChannel : channel,
				productCode : productCode,
				productName : product_name,
				priceType : productPriceType,
				totalPay : price,
				pickQuantity : number,
				nowPrice : hiddenPrice,
				productSpec : spec,
				productUnit : productUnit,
				cateCode : cateCode,
				city : city
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == '00') {
					result = 0;
				} else {
					$.alert({
						text : "保存失败。"
					});
				}
			}
		});
		return result;
	}
</script>

