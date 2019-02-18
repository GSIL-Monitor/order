<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<body>
	<div>
		<div class="row mb10">
            <div class="col-md-6">
                <h4>在售商品</h4>
                <div class="table-scroll table-responsive">
                    <table class="table focus-table" style="width:680px;">
                        <thead>
                        <tr>
                            <th style="width:50px;">序号</th>
                            <th style="width:25%">商品名称(单位)</th>
                            <th style="width:25%">规格</th>
                            <th style="width:30%">价格（元）</th>
                            <th style="">操作</th>
                        </tr>
                        </thead>
                        <tbody id="itemsAll">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-md-6">
                <h4>已选商品</h4>
                <div class="table-scroll table-responsive">
                    <table class="table focus-table" style="width:680px;">
                        <thead>
	                        <tr>
	                            <th style="width:50px;">序号</th>
	                            <th style="width:25%">商品名称(单位)</th>
	                            <th style="width:25%">规格</th>
	                            <th style="width:30%">价格（元）</th>
	                            <th style="">操作</th>
	                        </tr>
                        </thead>
                        <tbody id="itemsChecked">
	                        <tr></tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row">
			<div class="form-group col-xs-12">
				 <label><p>商品总价：</p>
				 	<span id="payfeesum" class="form-control" ></span>
				 </label>
				 <label><p>订单运费：</p>
				 	<input id="deliverPay" class="form-control" value="15"/>
				 </label>
			</div>
		</div>
        <div class="row">
			<div class="form-group col-xs-12">
				 <label><p>收货时间：</p>
				 	<input id="receiptTime" name="receiptTime" class="form-control Wdate" type="text" 
	                	onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" >
				 </label>
			</div>
		</div>
		<div class="row">
				    <div class="form-group col-xs-12">
					  <label><p>备注：</p>
						 <textarea id="remarkItem" class="form-control form-textarea" style="width:320px; height:40px;"rows="3"></textarea>
					  </label>
					</div>
			     </div>
		<div class="row">
            <div class="form-group col-xs-2">
                <label>
                    <p>收货地址：</p>
                </label>
            </div>
			<div class="form-group col-xs-8" >
                <div class="panel-content">
                    <table id="listBodyAddressItem" class="table order-table" style="margin-bottom:0">
					</table>
				</div>
			</div>
			<div class="form-group col-xs-2">
                <button type="button" class="btn btn-primary btn-sm"
					onclick="openModule('/order/jsp/orderItemaddressAdd.jsp',{lx:1,userId:$('#orderAddUserId').val(),userAddressId:0},'','','orderItemaddressAdd')">新增地址</button>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/*	 查询商品  */
	function queryProduct(productId,cityCode) {
		var ctx = $("#ctx").val();
		var priceType = $("#priceType").val();
		// 查询是否享受解决方案价格
		var solution = $("#orderAddSolutionOrNot").val();
		if(solution==1){
			priceType = 20000005;
		}
		$.ajax({
			url : ctx + "/item/queryProduct",
			type : "post",
			data : {
				productId : productId,
				cityCode : cityCode,
				dictCode : priceType,
				status : 1 
			},
			datetype : "json",
			async : false,
			success : function(data) {
				var html = "";
				if (data.msg == "00") {
					var result = data.list;
					$.each(result,function(i, p) {
						html += "<tr><td><input type='hidden' value='" +p.productCode +"'/>" ;
						html += "<input type='hidden' value='" +p.categoryCode +"' />" ;
						html += "<input type='hidden' value='" +p.productInventoryId +"' />";
						html += "<input type='hidden' value='" +p.marketPrice +"' />";
						html += "<input type='hidden' value='" +p.productPriceType +"' />";
						html += "<input type='hidden' value='" +p.productSpec +"' />";
						html += "<input type='hidden' value='" +p.productUnit +"' />";
						html += "<input type='hidden' value='" +p.productName +"' />";
						html += "" +(i+1) +"</td>" ;
                        html += "<td>" +p.productName +"(" +p.productUnitText +")</td>" ;
                        html += "<td>" +p.productSpec +"</td>" ;
                        html += "<td>" +p.priceText +"</td>" ;
                        html += "<td><button class='btn btn-xs btn-primary' type='button' onclick='addItems(this);'>购买</button></td>" ;
                        html += "</tr>" ;
					});
				} 
				html += "<tr></tr>" ;
				$("#itemsAll").html(html);
			}
		})
	}
	
	
	//查询客户城市和订单配送城市是否匹配
	function selectOrderClash(){
		var map=null;
		var receiverId = $("#orderAddReceiverId").val();
		if(receiverId==null || receiverId=="" || receiverId==0){
			$.alert({text:"请选择收货人地址！"});
			return ;
		}
		var payfeesum = $("#payfeesum").text();
		if(payfeesum==null || payfeesum=="" || payfeesum==0){
			$.alert({text:"请先选择商品！"});
			return ;
		}
		var productCode="";
		var trs = $("#itemsChecked").children("tr");
		for(var i=0; i<trs.length-1; i++){
			if(parseInt(trs.eq(i).children("td").eq(4).find("input:eq(0)").val())>0){
				productCode+=trs.eq(i).children("td").eq(1).find("input:eq(0)").val()+",";
			}else{
				countinue ;
			}
		}
		var data={
				"receiverId":receiverId,
				"productCode":productCode
		};
		$.ajax({
			url : ctx + "/item/selectOrderClash",
			data : {
				"data":JSON.stringify(data)
				},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == '00') {
					map= data.map;
				} 
			}
		});
		return map;
	}
	
	// 保存商品订单的方法
	function dosaveItems(cateType){
 	 var data =selectOrderClash();
 	 if(data!=null){
 		 if(data.IS_ENABLE==2){
 			 $.alert({text:"本商品["+data.NAME+"]目前不支持配送到本城市！"});
 			 return;
 		 } 
 	 }
		var ctx = $("#ctx").val();
		var userId = $("#orderAddUserId").val();
		var receiverId = $("#orderAddReceiverId").val();
		var orderChannel = $("#orderAddChannel").val();
		var  remarkItem = $("#remarkItem").val();
		var threeOrderCode = $("#threeOrderCode_add").val();
		var  personId=0;
		var channel=$("#orderAddChannel").val()
		if(channel==212028286803173||channel=='212028286803173'){
			personId =$("input[name='person_id']:checked").val();
		}
		var payfeesum = $("#payfeesum").text();
		if(payfeesum==null || payfeesum=="" || payfeesum==0){
			$.alert({text:"请先选择商品！"});
			return ;
		}
		if(receiverId==null || receiverId=="" || receiverId==0){
			$.alert({text:"请选择收货人地址！"});
			return ;
		}
		
		var deliverPay = $("#deliverPay").val();
		var priceType = $("#priceType").val();
		// 查询是否享受解决方案价格
		var solution = $("#orderAddSolutionOrNot").val();
		if(solution==1){
			priceType = 20000005;
		}
		var city = $("#orderAddCity").val();
		
		//接收时间
		var receiptTime = $("#receiptTime").val();
		if(receiptTime==null || receiptTime=="" ){
			$.alert({text:"请选择收货时间 ！"});
			return ;
		}
		var html = "{'remark':'" +remarkItem +"','userId':'" +userId +"','receiverId':'" +receiverId +"','receiptTime':'" +receiptTime +"','threeOrderCode':'" +threeOrderCode +"'";
			html += ",'payfeesum':'" +payfeesum +"','deliverPay':'" +deliverPay +"','priceType':'" +priceType +"'" ;
			html += ",'city':'" +city +"'" +",'critical':'1'" +",'orderChannel':'" +orderChannel +"','cateType':'" +cateType +"','emp_id':'" +personId +"'" ;
		var item = "[" ;
		var trs = $("#itemsChecked").children("tr");
		for(var i=0; i<trs.length-1; i++){
			if(parseInt(trs.eq(i).children("td").eq(4).find("input:eq(0)").val())>0){
				if(item!='[') item += "," ;
				item += "{'productCode':'" +trs.eq(i).children("td").eq(1).find("input:eq(0)").val();
				item += "','categoryCode':'" +trs.eq(i).children("td").eq(1).find("input:eq(1)").val();
				item += "','productInventoryId':'" +trs.eq(i).children("td").eq(1).find("input:eq(2)").val();
				item += "','nowPrice':'" +trs.eq(i).children("td").eq(1).find("input:eq(3)").val();
				item += "','productPriceType':'" +trs.eq(i).children("td").eq(1).find("input:eq(4)").val();
				item += "','productSpec':'" +trs.eq(i).children("td").eq(1).find("input:eq(5)").val();
				item += "','productUnit':'" +trs.eq(i).children("td").eq(1).find("input:eq(6)").val();
				item += "','productName':'" +trs.eq(i).children("td").eq(1).find("input:eq(7)").val();
				item += "','quantity':'" +trs.eq(i).children("td").eq(4).find("input:eq(0)").val();
				item += "','saleType':'1'}" ;
			}else{
				countinue ;
			}
		}
		item += "]" ;
		html += ",'item':" +item +"}";
		var result = 1;
		//alert(ctx +" and " +receiverId); return ;
		$.ajax({
			url : ctx + "/item/insertItem",
			data : {
				data : html
			},
			type : "post",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == '00') {
					result = 0;
				} else {
					$.alert({text:"保存失败！"});
				}
			}
		});
		return result;
	}
		
	
	// 添加商品
	function addItems(items){
		var name = $(items).parent("td").parent("tr").children("td").eq(1).text();
		var priceText = $(items).parent("td").parent("tr").children("td").eq(3).text();
		var productCode = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(0)").val();
		var categoryCode = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(1)").val();
		var productInventoryId = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(2)").val();
		var price = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(3)").val();
		var productPriceType = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(4)").val();
		var productSpec = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(5)").val();
		var productUnit = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(6)").val();
		var productName = $(items).parent("td").parent("tr").children("td").eq(0).find("input:eq(7)").val();
		var itemstr = $("#itemsChecked tr") ;
		html = "<tr><td></td>";
		html += "<td><input type='hidden' value='" +productCode +"' />" ;
		html += "<input type='hidden' value='" +categoryCode +"' />";
		html += "<input type='hidden' value='" +productInventoryId +"' />";
		html += "<input type='hidden' value='" +price +"' />";
		html += "<input type='hidden' value='" +productPriceType +"' />";
		html += "<input type='hidden' value='" +productSpec +"' />";
		html += "<input type='hidden' value='" +productUnit +"' />";
		html += "<input type='hidden' value='" +productName +"' />";
		html += "" +name +"</td>";
        html += "<td>" +productSpec +"</td>" ;
		html += "<td>" +priceText +"</td><td style='white-space:nowrap;'>";
		html += "<button style='width:15px;' type='button' onclick='subtractQuantity(this)'>-</button>";
		html += "<input style='width:25px;' type='text' value='1' onchange='setItemPayfeesum(this)'/>";
		html += "<button style='width:15px;' type='button' onclick='addQuantity(this)'>+</button>";
		html += "</td></tr><tr></tr>" ;
		var k = 0;
		// 需要判断当前选择的商品是否已经存在
		if(itemstr.length>1){
			for(var i=0; i<itemstr.length-1; i++){
				if($("#itemsChecked tr:eq(" +i +")").children("td:eq(1)").find("input:eq(0)").val()==productCode){
					k = i+1;
					break;
				}
			}
		}
		if(k>0){
			var num = $("#itemsChecked tr:eq(" +(k-1) +")").children("td:eq(4)").find("input:eq(0)").val();
			$("#itemsChecked tr:eq(" +(k-1) +")").children("td:eq(4)").find("input:eq(0)").val(parseInt(num)+1);
			setItemsSerials(1);
		}else{
			$("#itemsChecked tr").eq(itemstr.length-1).remove();
			$("#itemsChecked").append(html);
			//$("#itemsChecked").html(html+$("#itemsChecked").html());
			setItemsSerials(2);
		}
	}
	//商品数量加
	function addQuantity(add){
		var quantity = $(add).parent("td").find("input:eq(0)").val();
		$(add).parent("td").find("input:eq(0)").val(parseInt(quantity)+1);
		setItemsSerials(1);
	}
	//商品数量减
	function subtractQuantity(subtract){
		var quantity = $(subtract).parent("td").find("input:eq(0)").val();
		if(parseInt(quantity)-1>=1){
			$(subtract).parent("td").find("input:eq(0)").val(parseInt(quantity)-1);
			setItemsSerials(1);
		}else{
			$(subtract).parent("td").parent("tr").remove();
			setItemsSerials(2);
		}
	}
	// input金额输入框
	function setItemPayfeesum(input){
		if(input.value<=0){
			$(input).parent("td").find("input:eq(0)").val(1);
		}
		setItemsSerials(1);
	}
	// 排序
	function setItemsSerials(lx){
		var trs = $("#itemsChecked").children("tr");
		var feesum = 0;
		for(var i=0; i<trs.length-1; i++){
			if(lx==2){
				trs.eq(i).children("td").eq(0).text(i+1);	
			}
			var price = parseFloat(trs.eq(i).children("td").eq(1).find("input:eq(3)").val());
			var quantity = parseFloat(trs.eq(i).children("td").eq(4).find("input:eq(0)").val());
			feesum += price*quantity;
		}
		$("#payfeesum").text(feesum);
		
	}
</script>
