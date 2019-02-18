<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!-- TOI- ThreeOrderIn , FA-- 自营商品  -->
<!DOCTYPE html>
<body>
	<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
	<div class="row mb10">
		<div class="form-group col-xs-6" style="max-height:240px; overflow: auto;">
			<h6>在售商品</h6>
			<div class="panel-content table-responsive" >
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th style="width:20%">商品</th>
							<th style="width:60%">价格</th>
							<th style="width:15%">操作</th>
						</tr>
					</thead>
					<tbody id="TOI_FA_tbody">
					</tbody>
				</table>
			</div>
		</div>
		<div class="form-group col-xs-6" style="max-height:240px; overflow: auto;">
			<h6>已选商品</h6>
			<div class="panel-content table-responsive">
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th style="width:20%">商品</th>
							<th style="width:60%">价格</th>
							<th style="width:15%">操作</th>
						</tr>
					</thead>
					<tbody id="TOI_FA_Selected_tbody">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="row pt10" >
		<div class="form-group col-xs-12">
			<div class="row">
				<div class="form-group col-xs-6">
					<label>
						<p>应付总金额：</p>
						<span id="TOI_FA_amount_id"></span>
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label>
						<p>运费：</p>
						<span>15元</span>
						<input type="hidden" id="price_system">
					</label>
				</div>
			</div>
		</div>
		<div class="form-group col-xs-12">
			<div class="row">
				<div class="form-group col-xs-6">
					<label class="has-feedback">
						<p>收货时间：</p>
						<input id="TOI_FA_receiptTime" class="Wdate form-control" type="text" style="width:185px;"
							onfocus="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					</label>
				</div>
				<div class="form-group col-xs-6">
					<label class="has-feedback">
						<p><span style="color:red">*</span>订单渠道：</p>
						<select id="TOI_FA_originchannel" name="TOI_FA_originchannel" class="form-control">
						</select>
					</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-xs-1">
					<label>
						<p><span style="color:red">*</span>收货地址：</p>
						<input type="hidden" id="unicodes">
					</label>
				</div>
				<div id="TOI_FA_userAddrList" class="form-group col-xs-8" style="overflow: auto;  display: block; height:100px;">
				</div>
				<div class="form-group col-xs-2">
					<button type="button" class="btn btn-sm btn-primary" 
						onclick="openModule('/order/jsp/order_three/in/addr_operator/orderThreeFA_AddrAdd.jsp',{lx:1},'','','orderThreeFA_AddrAdd');">
						新增地址
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//防止连续操作，数据不正确
	var operate_flag = true;
	$(function(){
		//清除之前操作选择的地址
		$("#TOI_A_userAddrId").val("");
		$("#TOI_A_userAdde_proVersion").val("");
		queryBaseChannel(null,"TOI_FA_originchannel");
		getAddrListByUser("TOI_FA_userAddrList");
	});
	/*商品列表 */
	function TOI_FA_queryProduct(dataList){
		var htm ="";
		 $.each(dataList,function(i, p) {
			 htm +="<tr>";
			 htm +="<input type='hidden' name='product_code' id='TOI_FA_"+p.product_code+"' value='"+p.product_code+"' />"
			 htm +="<td>"+p.product_name+"</td>";
			 var price = "";
			 if(p.priceDiscList){
				 for(var i = 0;i<p.priceDiscList.length;++i){
					 var disc = p.priceDiscList[i];
					 if(disc.dict_code =='20000006'){
						 price += "会员价:"+disc.price+"元";
					 }
				 }
				 for(var i = 0;i<p.priceDiscList.length;++i){
					 var disc = p.priceDiscList[i];
					 if(disc.dict_code =='20000002'){
						 price += "，市场价:"+disc.price+"元";
					 }
				 }
			 }
			 htm +="<td>"+price+"</td>";
			 htm +="<td><input type='hidden' id='TOI_FA_Min_"+p.product_code+"' value='"+p.minUnitCount+"'>";
			 htm +="<input type='button' value='购买' onclick=\"javascript:TOI_FA_chooseProduct('"+p.product_code+"');\" /></td>"
			 htm +="</tr>"
		});
		$("#TOI_FA_tbody").html(htm);
	}
	//已经选中的商品列表
	function TOI_FA_SelectedProduct(dataList){
		var htm="";
		var count_keyup ="onkeyup=\"javascrpt:if(!(event.ctrlKey||event.shiftKey)){this.value=this.value.replace(/[^\\d]/g,'');}\" ";
		var count_past=	"onbeforepaste=\"clipboardData.setData('text',clipboardData.getData('text').replace(/[^\\d]/g,''))\" ";
		
		
		var c_id_prefix = "TOI_FA_SD_Count_";
		 $.each(dataList,function(i, p) {
			 htm +="<tr>";
			 htm +="<input type='hidden' name='product_selected_code' id='TOI_FA_SD_"+p.product_code+"' value='"+p.product_code+"' />"
			 htm +="<td>"+p.product_name+"</td>";
			 var price = "";
			 switch (p.dict_code)
			 {
				 case "20000006":
					 $("#price_system").val("20000006");
					price = "会员价:"+p.price+"元";
				   	break;
				 case "20000005":
					 $("#price_system").val("20000005");
					price = "解决方案价:"+p.price+"元";
					break;
				 case "20000004":
					 $("#price_system").val("20000004");
				 	price = "团购价:"+p.price+"元";
					break;
				 case "20000007":
					 $("#price_system").val("20000007");
				 	price = "活动价:"+p.price+"元";
					break;
				 case "20000008":
					 $("#price_system").val("20000008");
				 	price = "原价:"+p.price+"元";
			   		break;
				 case "20000002":
					$("#price_system").val("20000002");
				 	price = "市场价:"+p.price+"元";
			   		break;
				 case "20000003":
					 $("#price_system").val("20000003");
				 	price = "大客户价:"+p.price+"元";
			   		break;
				 default :
					price = "市场价:"+p.price+"元";
			   		break;
			 }
			 var c_id = c_id_prefix + p.product_code;
			 var count_change = "onchange=\"javascript:TOI_FA_ChangeQyt('"+p.product_code+"',this.value,'edit');\" ";
			 var toggle_event = count_keyup + " " + count_past +" " +count_change;
			 htm +="<td>"+price+"</td>";
			 htm +="<td><a style='width:30px;' href=\"javascript:TOI_FA_ChangeQyt('"+p.product_code+"',1,'minus');\">-</a>";
			 htm +="<input id='TOI_FA_SD_min_"+p.product_code+"' value='"+p.minUnitCount+"' type='hidden' />";
			 htm +="<input type='text' style ='width:50px;' id='"+c_id+"' value='"+p.product_count+"' preValue='"+p.product_count+"' "+ toggle_event + "/>";
			 htm += "<a style='width:30px;' href=\"javascript:TOI_FA_ChangeQyt('"+p.product_code+"',1,'add');\">+</a></td>"
			 htm +="</tr>"
		});
		$("#TOI_FA_Selected_tbody").html(htm);
	}
	
	/*点击购买 */
	function TOI_FA_chooseProduct(product_code){
		product_code = $.trim(product_code);
		//点击购买时，购买数量为最小单位起订量
		var product_count = $.trim($("#TOI_FA_Min_"+product_code).val());
		
		if($("#TOI_FA_SD_"+product_code).length>0){
			$.alert({text:"此商品已经选中。"});
			return;
		}else{
			TOI_FA_reCalaPrice(product_code,product_count);
		}
	}
	//价格重算
	function TOI_FA_reCalaPrice(product_code,product_count) {
		var ctx = $("#ctx").val();
		if(operate_flag){
			operate_flag = false;
			var user_id = $.trim($("#TOI_A_userId").val());
			var city_code = $.trim($("#TOI_A_city").val());
			var category_code = $.trim($("#TOI_A_threeClassify").val());
			if(!category_code){
				$.alert({text:"请选择三级类别。"});
				operate_flag = true;
				return ;
			}
			
			category_code = category_code.split(",")[0];
			
			if(!category_code){
				$.alert({text:"请选择三级类别。"});
				operate_flag = true;
				return ;
			}
			if(!user_id || $.trim(user_id) == ""){
				$.alert({text:"请选择用户。"});
				operate_flag = true;
				return ;
			}
			if(!city_code || $.trim(city_code)==""){
				$.alert({text:"您选择用户的用户没有城市信息。"});
				operate_flag = true;
				return ;
			}
			if(!product_code || $.trim(product_code)==""){
				$.alert({text:"请选择您需要购买的商品."});
				operate_flag = true;
				return ;
			}
			var reg = /^(0|[1-9][0-9]*)$/;
			if(!reg.test(product_count)){
				$.alert({text:"输入的购买数量."});
				operate_flag = true;
				return ;
			}
		
			var product_selected = "";
			//获得已经选中的商品，不包含当前操作的商品
			$("input[name='product_selected_code'][id!='TOI_FA_SD_"+product_code+"']").each(function(){
				var product_code_ = $(this).val();
				var product_count_ = $("#TOI_FA_SD_Count_"+product_code_).val();
				product_selected += "{\"product_code\":\""+product_code_+"\",\"product_count\":\""+product_count_+"\"}"
			});
			//当前操作的商品
			var product_buy = "{\"product_code\":\""+product_code+"\",\"product_count\":\""+product_count+"\"}";
			if(product_selected==""){
				product_selected += product_buy;
			}else{
				product_selected += ","+product_buy;
			}
			if(product_selected != null){
				product_selected = "["+product_selected+"]";
			}
			$.ajax({
				url:ctx+"/threeOrder/reCalculateTOIProduct",
				type:"post",
				datetype:"json",
				async : false,
				data:{
					user_id : user_id,
					city_code : city_code,
					category_code : category_code,
					product_code_count_json : product_selected
				},
				success:function(data){
					if(data.msg == "00"){
						var dataList = data.list;
						TOI_FA_SelectedProduct(dataList);
						$("#TOI_FA_amount_id").text(data.amount+"元");
					}
					operate_flag = true;
				}
			});
		}else{
			$.alert({text:"上一次操作未完成，请稍后再试"});
			return;
		}
	}
	//数量修改
	function TOI_FA_ChangeQyt(product_code,qty,operator_type){
		var product_count ;
		if(operate_flag){
			//商品数量
			var count = $("#TOI_FA_SD_Count_"+product_code).val();
			//商品改变前的数量
			var precount = $("#TOI_FA_SD_Count_"+product_code).attr("prevalue");
			//最小起订量
			var min = $("#TOI_FA_SD_min_"+product_code).val();
			if(!count || !qty || !min || !precount){
				$.alert({text:"数量不正确"});
				return;
			}
			if(isNaN(parseInt(count)) || isNaN(parseInt(qty)) 
					|| isNaN(min) || isNaN(parseInt(precount))){
				$.alert({text:"数量不正确"});
				return;
			}
			if(operator_type=="add"){
				product_count = parseInt(count) + parseInt(qty);
				TOI_FA_reCalaPrice(product_code,product_count);
				$("#TOI_FA_SD_Count_"+product_code).attr("prevalue",product_count);
			}else if("minus"==operator_type){
				//达到最小起订量执行删除方法
				if(parseInt(count) <= parseInt(min)){
					//确认窗口
					// true -- 表示同意删除，false -- 不同意
					var ret_falgg = true;
					$.confirm({text:"此操作小于此商品的最小起订量,将删除此商品。",
						callback:function(ret_falg){
							if(ret_falg){
								//删除
								product_count = parseInt(count) - parseInt(qty);
								TOI_FA_reCalaPrice(product_code,product_count);
							}else{
								
								$("#TOI_FA_SD_Count_"+product_code).val(precount);
								ret_falgg = false;
							}
						}
					});
					if(!ret_falgg){
						return;
					}
				}else{
					product_count = parseInt(count) - parseInt(qty);
					TOI_FA_reCalaPrice(product_code,product_count);
					$("#TOI_FA_SD_Count_"+product_code).attr("prevalue",product_count);
				}
			}else if("edit"==operator_type){
				//小于最小起订量执行删除方法
				if(parseInt(qty) < parseInt(min)){
					//确认窗口
					var ret_falgg = true;
					$.confirm({text:"此操作小于此商品的最小起订量,将删除此商品。",
						callback:function(ret_falg){
							if(ret_falg){
								product_count = qty;
								TOI_FA_reCalaPrice(product_code,product_count);
							}else{
								$("#TOI_FA_SD_Count_"+product_code).val(precount);
								ret_falgg = false;
							}
						}
					});
					if(!ret_falgg){
						return;
					}
				}else{
					product_count = qty;
					TOI_FA_reCalaPrice(product_code,product_count);
					$("#TOI_FA_SD_Count_"+product_code).attr("prevalue",product_count);
				}
			}else{
				$.alert({text:"操作类型不正确"});
				//openAlert("操作类型不正确","提示",5000);
				return;
			} 
		}
	}
	
	// 添加商品订单
	function saveFa(){
		var userId = $("#TOI_A_userId").val();
		var receiverId = $("#TOI_A_userAddrId").val();
		var ctx = $("#ctx").val();
		var orderChannel = $("#TOI_FA_originchannel").val();
		//接收时间
		var receiptTime = $("#TOI_FA_receiptTime").val();
		var priceSystem = $("#price_system").val();
		var listItem = "";
		//获得已经选中的商品，不包含当前操作的商品
		$("input[name='product_selected_code']").each(function(){
			var product_code_ = $(this).val();
			var product_count_ = $("#TOI_FA_SD_Count_"+product_code_).val();
			listItem += "{\"product_code\":\""+product_code_+"\",\"product_count\":\""+product_count_+"\"}"
		});
		var result = 1;
		var userCity = $("#TOI_A_userCity").val();
		
		if(!userId){
			$.alert({text:"请选择用户。"});
			return ;
		}
		if(!receiverId){
			$.alert({text:"请填写收货地址。"});
			return ;
		}
		if(!userCity){
			$.alert({text:"您选择用户的用户没有城市信息。"});
			return ;
		}
		
		if(!listItem){
			$.alert({text:"请选择商品！再提交订单。"});
			return;
		}
		if(!orderChannel){
			$.alert({text:"请选择订单渠道。"});
			return;
		}
		 $.ajax({
			url : ctx + "/threeOrderIn/saveThreeOrderFa",
			data : {
				listItem : listItem,
				userId : userId,
				receiverId : receiverId,
				cateType : 3,
				orderChannel: orderChannel,
				userCity : userCity,
				receiptTime : receiptTime,
				priceSystem : priceSystem
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == '00') {
					result = 0;
				} else {
					$.alert({text:"保存失败。"});
				}
			}
		}); 
		return result;
	}
	
</script>
