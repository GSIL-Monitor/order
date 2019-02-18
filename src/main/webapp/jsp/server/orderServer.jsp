<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageEditcext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<style>
		.modal-order-edit{height:500px; width:850px; margin: 30px auto; }
		.order-body-edit{height:380px; width: 780px; margin: 10px auto; }
		.table-condensed tr td{text-align: left;}
	</style>
</head>
<body>
	<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" id="modelFrameOrderServerEdit">  
		<div class="modal-dialog modal-order-edit" > 
			<div class="modal-content">
				<div class="modal-header">  
		        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setOrderCloseModule('orderServerEdit_type',1);">×</button>  
		        	<h4 class="modal-title">延续性订单修改</h4>
				</div>  
				<div class="modal-body">
					<div class="modal-content-basic">
						<div class="info-select clearfix">
							<input type=hidden name="id" id="orderIdEditc" >
							<input type=hidden name="orderState" id="orderState">
							<input type=hidden id="cityCode">
							<form id="serverEditCont" class="form-inline" method="post"  style="display:block;">
								<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p>客户姓名：</p> 
											<input id="orderUserAddServer_edit_cont_name" type="text" class="form-control" name="totalPayAddServer" disabled="disabled">
										</label>
									</div>
									<div class="form-group col-xs-6">
										<label>
											<p>二级分类：</p>
											<select id="twoCategory_edit_cont_type" class="form-control" onchange="javascript:twoChange(this)" >
											</select>
										</label>
									</div>
								</div>
							<div class="row">
									<div class="form-group col-xs-6">
										<label>
											<p>三级分类：</p>
											<select id="threeCategory_edit_cont" class="form-control"  onchange="javascript:queryCityServerProduct1(this)">
											</select>
										</label>
									</div>
									<div class="form-group col-xs-6">
										 <label>
											<p>商品：</p> 
											<select id="serverProduct_edit_cont" class="form-control"  onchange="javascript:setCityServerProduct(this,'_edit_cont')"></select>
										</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group  col-xs-6">
										<input type="hidden" id="productUnitAddServer_edit_cont"> 
										 <input type="hidden" id="productPriceTypeAddServer_edit_cont"> 
										<label><p>总数量(<span id="productUnitTextAddServer_edit_cont"></span>):</p>
										 <input id="pickQuantityServer_edit_cont" type="text" class="form-control"
											style="ime-mode:Disabled;"
											name="pickQuantityServer" 
											onkeydown="inputOnlyNum(event);" onkeypress="checkIfNum(event);" 
											>
										</label> 
									</div>
									<div class="form-group  col-xs-6">
											<label><p>价格(元)：</p>
											 <input id="totalPayAddServer_edit_cont" type="text" class="form-control" name="totalPayAddServer" readOnly>
											</label>
									</div>
								</div>
								<div class="row">
									<div class="form-group  col-xs-6">
										<label><p>价格体系：</p> <input id="priceTextAddServer_edit_cont"
											type="text" class="form-control" name="totalPayAddServer"
											readonly="true" >
										</label> 
									</div>
									<div class="form-group  col-xs-6">
										<label><p>规格：</p> <input id="productSpecAddServer_edit_cont"
											type="text" class="form-control" readonlrey="true"
											>
										</label>
									</div>
								</div>
								
							</form>
							
						</div>
					</div>
				</div>
				<div class="modal-footer">  
					<button type="button" class="btn btn-sm btn-primary" onclick="setOrderCloseModule('orderServerEdit_type',2);" >取消</button>
					<button type="button" class="btn btn-sm btn-primary" onclick="saveServerEditc()">提交</button>
				</div>  
			</div>
	    </div>
	</div>         
</body>
<script type="text/javascript">
	$(function(){
		orderserver_type1();
	})
	//取到订单详细信息
	function orderserver_type1(){
		var orderId = parent.document.getElementById("checkedOrderId").value;
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/orderbase/queryOrderBasicServerType",
			data:{
				id:orderId,
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					var num =  $.each(data.list,function(i,v){
						$("#orderUserAddServer_edit_cont_name").val(v.name);//
						//所属城市
						$("#cityCode").val(v.city);
						oneChange();//查询二级分类
						//二级分类substr(0,6)
						$("#twoCategory_edit_cont_type").find("option[value='"+v.productCategoryCode.substr(0,8) +"']").attr("selected",true); 
						twoChangeq(v.productCategoryCode.substr(0,8));//查询三级分类
						$("#threeCategory_edit_cont").find("option[value='"+v.productCategoryCode +"']").attr("selected",true); 
						 //查询三级分类下的商品
						queryCityServerProduct(v.productCategoryCode,v.city,"_edit_cont");
						 //选中商品
						$("#serverProduct_edit_cont option[value^='"+v.productCode+"']").prop("selected",true).trigger("change");
						//数量
						$("#pickQuantityServer_edit_cont").val(v.quantity).trigger("change");
						
						$("#orderIdEditc").val(v.id); // 订单表id
						$("#orderState").val(v.orderStatus);//订单状态
						
					})
				}
			}
		});
	}
	//取到订单详细信息
	function orderServer_type(){
		var cateType = parent.$("#checkedCateType").val();
		var orderId = parent.document.getElementById("checkedOrderId").value;
		var orderType = parent.document.getElementById("checkedOrderType").value;
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/orderbase/queryOrderBasicServer",
			data:{
				id:orderId,
				orderType:orderType
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					var num =  $.each(data.list,function(i,v){
						$("#orderIdEditc").val(v.id); // 订单表id
						$("#codeEditc").val(v.code); // server表id
						$("#versionEditc").val(v.version);
							$("#serverAddressEditCont").val(v.address);
							$("#startTimeEditc").val(numberToDatestr(v.startTime,12));
							$("#endTimeEditc").val(numberToDatestr(v.endTime,12));
							$("#interviewTimeEditc").val(numberToDatestr(v.interviewTime,12));
							$("#interviewAddressEditc").val(v.interviewAddress);
							//$("#educationEditc option[value='" +v.education +"']").attr("selected","selected"); 
							$("#educationEditc").val(v.education);//
							//$("#levelEditc").val(v.personLevel);//
							queryWorkTypeLevel("levelEditc",v.personLevel);
							$("#minAgeEditc").val(v.minAge);
							$("#maxAgeEditc").val(v.maxAge);
							$("#homeForestsEditc").val(v.homeForests);//
							$("#familysEditc").val(v.familys);//
							$("#remarkEditc").val(v.remark);//
							$("#orderUserAddServer_edit_cont").val(v.order.userName);//
// 							setSelProvinceCitys("101",6,"provinceEditCont");
// 							$("#provinceEditCont option[value='" +v.order.receiverCityCode.substr(0,6) +"']").attr("selected","selected");
// 							setSelCity("provinceEditCont","cityEditCont","countryEditCont");
// 							$("#cityEditCont option[value='" +v.order.receiverCityCode.substr(0,9) +"']").attr("selected","selected");
// 							setSelCountry("cityEditCont","countryEditCont");
// 							$("#countryEditCont option[value='" +v.order.receiverCityCode +"']").attr("selected","selected");
							queryOriginDictionary("","originEditc",v.origin);
							
							// 是否享受解决方案价
							var priceType = v.order?v.order.priceType:"";
							if(priceType != "" && priceType != null){
								if(priceType == 20000005){
									$("#orderAddSolutionOrNot_edit_cont option[value='20000005']").prop("selected",true);
								}else{
									$("#orderAddSolutionOrNot_edit_cont option[value='20000002']").prop("selected",true);
								}
							}
							 //基础商品三级分类
							$("#threeCategory_edit_cont").val(v.productCategoryName).attr("data-productCategoryCode",v.productCategoryCode);	
							 //查询三级分类下的商品
							//queryCityServerProduct(v.productCategoryCode,v.city,"_edit_cont");
							 //选中商品
							$("#serverProduct_edit_cont option[value^='"+v.productCode+"']").prop("selected",true).trigger("change");
							//数量
							$("#pickQuantityServer_edit_cont").val(v.quantity).trigger("change");
							 //所属城市
							 $("#cityCode").val(v.city);
					})
				}
			}
		});
	}
	// 查询商品分类
	function queryCategory(typeId,code,length){
		var cateType = 0;
		if(length == 12){
			cateType = 2
		}
		var ctx = $("#ctx").val();
		var cityCode = $("#cityCode").val();
		$.ajax({
			url:ctx+"/item/queryCategory",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				code:code,
				length:length,
				cityCode:cityCode,
				cateType:cateType
			},
			success : function(data){
				if (data.msg == "00") {
					var html="";
					var result = data.list;
					var html = "<option style='color:blue;' value='0' >...请选择...</option>";
					$.each(result,function(i, p) {
						html += "<option value='"+p.code +"' >"+p.cname+"</option>";    
					});
					$("#"+typeId).html(html);
				}else{
					$("#"+typeId).html("<option style='color:blue;' value='0' >...无可选项...</option>");
				}
			}
		})
	}
	
	/* 查询商品的二级分类 */	
	function oneChange(){
	    queryCategory("twoCategory_edit_cont_type","1002",8);
	    $("#threeCategory_edit_cont").html("<option style='color:blue;' value='0' >...请选择...</option>");
	}

	/* 查询商品的三级分类 */	
	function twoChange(oc){
	    var code = oc.options[oc.options.selectedIndex].value;
	    queryCategory("threeCategory_edit_cont",code,12);
	}
	
	/* 查询商品的三级分类 */	
	function twoChangeq(code){
	    queryCategory("threeCategory_edit_cont",code,12);
	} 
	/* 查询服务商品   queryCityServerProduct(val[0],cityCode); */
	function queryCityServerProduct1(oc){
	var values = oc.options[oc.options.selectedIndex].value
	var val = values.split(",");
	var ctx = $("#ctx").val();
	var cityCode = $("#cityCode").val();
	var priceType = $("#priceType").val();
	$.ajax({
		url:ctx+"/item/queryCityServerProduct",
		type:"post",
		datetype:"json",
		async : false,
		data:{
			categoryCode:val[0],
			cityCode:cityCode,
			status:1,
			dictCode:priceType
		},
		success : function(data){
			if (data.msg == "00") {
				var result = data.list;
				var html = "<option style='color:blue;' value='0'>...请选择...</option>";
				$.each(result,function(i, p) {
					html += "<option value='"+p.productCode +"," +p.marketPrice +"," +p.productUnit +"," +p.productUnitText;
					html += "," +p.productPriceType +"," +p.priceText +"," +p.productSpec ;
					html += "'>"+p.productName +"</option>";  
				});
				$("#serverProduct_edit_cont").html(html);
			}else{
				//html = "<option value='101001001628653740620980,12800,20070008,月,20000002,市场价:12800,月嫂:无特殊'>中级月嫂无特殊</option>";
				$("#serverProduct_edit_cont").html("<option style='color:blue;' value='0' >...无可选项...</option>");
			}
		}
	});
}
	
	//查询服务商品(tagIdSuffix:标签id后缀)
	function queryCityServerProduct(categoryCode,cityCode,tagIdSuffix){
		var ctx = $("#ctx").val();
		var priceType = $("#orderAddSolutionOrNot"+tagIdSuffix).prop("value");
		// 查询是否享受解决方案价格
		$.ajax({
			url:ctx+"/item/queryCityServerProduct",
			type:"post",
			datetype:"json",
			async : false,
			data:{
				categoryCode:categoryCode,
				cityCode:cityCode,
				status:1,
				dictCode:priceType,
				typeSpecId:242340276249476
			},
			success : function(data){
				if (data.msg == "00") {
					var result = data.list;
					var html = "<option style='color:blue;' value='0'>...请选择...</option>";
					$.each(result,function(i, p) {
						html += "<option value='"+p.productCode +"," +p.marketPrice +"," +p.productUnit +"," +p.productUnitText;
						html += "," +p.productPriceType +"," +p.priceText +"," +p.productSpec ;
						html += "'>"+p.productName +"</option>";  
					});
					$("#serverProduct"+tagIdSuffix).html(html);
				}else{
					$("#serverProduct"+tagIdSuffix).html("<option style='color:blue;' value='0' >...无可选项...</option>");
				}
			}
		});
	}
	
	//选择商品(tagIdSuffix:标签id后缀)
	function setCityServerProduct(item,tagIdSuffix){
		var items = item.options[item.options.selectedIndex].value;
		$("#pickQuantityServer"+tagIdSuffix).val(items == 0?"":1);
		$("#totalPayAddServer"+tagIdSuffix).val(items.split(",")[1]);
		$("#productUnitAddServer"+tagIdSuffix).val(items.split(",")[2]);
		$("#productUnitTextAddServer"+tagIdSuffix).text(items == 0?"":items.split(",")[3]);
		$("#productPriceTypeAddServer"+tagIdSuffix).val(items.split(",")[4]);
		$("#priceTextAddServer"+tagIdSuffix).val(items.split(",")[5]);
		$("#productSpecAddServer"+tagIdSuffix).val(items.split(",")[6]);
	}
	
	//提交修改
	function saveServerEditc(){
		// var twoClassfy = $("#twoCategory_edit_cont_type option:selected").text();
		var threeClassfy = $("#threeCategory_edit_cont option:selected").val();
		var productName = $("#serverProduct_edit_cont option:selected").text();//商品名称
		var itemC = $("#serverProduct_edit_cont")[0];
		var itemsC = itemC.options[itemC.options.selectedIndex]?itemC.options[itemC.options.selectedIndex].value:"";
		var productCode = itemsC.split(",")[0];//城市商品code
		var nowPrice =  itemsC.split(",")[1];//价格
		var quantuty=$("#pickQuantityServer_edit_cont").val();
		var priceText=$("#priceTextAddServer_edit_cont").val();
		var orderId = parent.document.getElementById("checkedOrderId").value;
		var productspec=$("#productSpecAddServer_edit_cont").val();
		var orderState=$("#orderState").val();
		if(threeClassfy==null || threeClassfy ==""||threeClassfy==0 || threeClassfy =="0"){
			$.alert({ text : "分类不为空!" });
			return ;
		}
		if(productCode==null || productCode ==""||productCode==0 || productCode =="0"){
			$.alert({ text : "商品不为空!" });
			return ;
		}
		if(orderState==1||orderState==2){
			$.ajax({
				url: ctx +"/itemDetailServer/updateItemDetailServerType",
				data:{
					orderId:orderId,
					productName:productName,
					productUnit:threeClassfy,
					productCode : productCode,
					nowPrice : nowPrice,
					pickQuantity : quantuty,
					priceType : priceText,
					productSpec:productspec
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if(data.msg=='00'){
						setOrderCloseModule('orderServerEdit_type');
					}
				}
			})
		}else{
			$.alert({ text : "订单类型为新建或者已受理才可以改变类型！" });
		}
			
		
	}
	
	function setOrderEditcReceiverId(checkbox){
		$("#orderEditcReceiverId").val($(checkbox).val());
	}
	function orderEditcUpdateAddress(userAddressId){
		var userId = parent.$("#checkedUserId").val();
		openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:8,userId:userId,userAddressId:userAddressId},'','','orderItemaddressAdd');
	}
	

</script>
</html>

