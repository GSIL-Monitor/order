	var ctx=$("#ctx").val();
	$(function(){
		selectAll(1,10);
		buttonNewOrderCount();//查询新订单条数
		buttonTobeshippedOrderCount();//查询待发货条数
		buttonAlreadyshippedOrderCount();//查询已发货条数
	})
	/**
	 * 全部查询
	 * @param start
	 * @param limit
	 * @param val
	 * @param thiz
	 */
	function selectAll(start,limit,val,thiz){
		$("#selectallparcel").html("");
		var submitStartTime = $("#ordertime").val();
		var submitEndTime = $("#ordertimes").val();
		var orderCode = $("#order_number").val();
		var receiptTime = $("#deliverydate").val();
		var receiptTimes = $("#deliverydates").val();
		var packageNumber = $("#packageNumber").val();
		var receiver_mobile = $("#receiver_mobile").val();
		var state = $("#state").val();
		var orderStatus = $("#orderState").val();
		if(val == 1){
			var createTime = $(thiz).val();
			var state = 1;
		}else if(val == 2){
			var updateTime = $(thiz).val();
			var state = 2;
		}else{
			var state = $("#state").val();
		}
		var html = "";
		$.ajax({
			url : ctx + "/parcel/queryParcel",
			data : {
				"curPage":start,
				"pageCount":limit,
				submitStartTime : submitStartTime,
				submitEndTime : submitEndTime,
				"order.orderCode" : orderCode,
				packageNumber : packageNumber,
				state : state,
				"order.receiverMobile" : receiver_mobile,
				"order.receiptTime" : receiptTime,
				"order.receiptTimes" : receiptTimes,
				"order.orderStatus" : orderStatus,
				createTime : createTime,
				updateTime : updateTime
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivallparcel").pagination(data.page,parcel_topage);
					$("#selectallparcel").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						var $button = null;
						if(v.state == 2){
							$button = $("<button type='button' class='btn btn-primary btn-xs'><em class='glyphicon glyphicon-share-alt'></em>发货</button>").click(function(){
								openalldelivergoods(v)
							});
						}
						num=i+1;
						var html = $("<tr onclick='selectTrDivide(this);queryParcelDetails("+v.packageNumber+");queryParcelLogistics("+v.packageNumber+","+v.state+")'></tr>").append("<td>"
								+"<input value="+v.id+" data-packageNumber="+v.packageNumber+" data-orderId="+v.order.id+" data-state="+v.state+" name='parcelCheckbox' type='checkbox' onclick='event.stopPropagation();validateCheckAll()'/></td>"
								+"<td>" + (total + num - pageCount) + "</td><td>" + (v.order?v.order.orderCode:"") + "</td>"
								+"<td>" + v.packageNumber + "</td><td>" + (v.order?v.order.createTime:"") + "</td>"
								+"<td>" + (v.order?v.order.userMobile:"") + "</td>"
								+"<td>" + v.createByDept + "</td><td>" + v.orderRechargeDept + "</td>"
								).append($("<td class='text-l'>"+ (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':"")+"</td>").append($button));
						$("#selectallparcel").append(html);
					})
					$("#pageCount").val(data.page.pageCount);//第页显示记录数
					$("#curPage").val(data.page.curPage);//当前页数
					$("#totalRecord").val(data.page.totalRecord);//当前总页数
					$("#maxPage").val(data.page.maxPage);//当前总页数
				}else if(data.msg=="02"){
					$("#pageInfoDivallparcel").pagination(data.page,parcel_topage);
					showMsg("无记录");
				}
			}
		});
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#selectallparcel > tr");
	    $("#selectallparcel").find("tr:first").trigger("click");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function parcel_topage(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selectAll(num,(count?count:15));
	 }
	 /**
	  * 行点击选中记录
	  * @param thiz
	  */
	 function selectTrDivide(thiz){
	 	var $check = $(thiz).find("input[type=checkbox]");
	 	$check.prop("checked",!$check.prop("checked"));
	 	validateCheckAll();//验证全选
	 }
	 /**
	  * 根据所有行是否被选中
	  * 来切换全选框选中
	  */
	 function validateCheckAll(){
	 	var $checkedDivide = $("#selectallparcel input[type='checkbox']:checked");
	 	var $divide = $("#selectallparcel input[type='checkbox']");
	 	if($checkedDivide.size() == $divide.size()){
	 		$("#parcelall").prop("checked",true);
	 	}else{
	 		$("#parcelall").prop("checked",false);
	 	}
	 }
	 /**
	  * 全选
	  */
	 function selectAllCheckbox(){
	 	var $inputs = $("input[type='checkbox'][name='parcelCheckbox']");
	 	$inputs.prop("checked",$("#parcelall").prop("checked"));
	 }
	 /**
	  * 合并包裹
	  */
	 function mergeParcel(){
		var index = $("#activeId").find(".client-add-active").index();
		if(index == 0){
			var checkValues = $("input[name='parcelCheckbox']:checked");
			var orderId = $("input[name='parcelCheckbox']").eq(0).attr("data-orderId");
		}else if(index == 1){
			var checkValues = $("input[name='newparcelCheckbox']:checked");
			var orderId = $("input[name='newparcelCheckbox']").eq(0).attr("data-orderId");
		}else{
			$.alert({millis:2000,text:"请到全部或者新订单标签下选择包裹合并！"});
			return;
		}
		if(checkValues.length <= 1){
			$.alert({millis:2000,text:"最少选择两个包裹！"});
			return;
		}
		var str = "";
		for (var i = 0; i < checkValues.length; i++) {
			if(orderId != $(checkValues[i]).attr("data-orderId")){
				$.alert({millis:2000,text:"请选择相同订单在合并！"});
				return;
			}
		}
		for (var i = 0; i < checkValues.length; i++) {
			if(1 != $(checkValues[i]).attr("data-state")){
				$.alert({millis:2000,text:"请选择包裹状态为新订单！"});
				return;
			}
		}
		var str = "";
		for (var i = 0; i < checkValues.length; i++) {
			if(i == checkValues.length-1){
				str += checkValues[i].value;
			}else{
				str += checkValues[i].value+",";
			}
		}
		$.confirm({text:"是否合并包裹",callback:function(a){
			if(a == true){
				$.ajax({
					url :ctx + "/parcel/updateParcel",
					data : {
						idStr : str
					},
					type : 'post',
					async : false,
					success : function(data) {
						if(data.msg == "00"){
							var pageCount = $("#pageCount").val();//第页显示记录数
							var curPage = $("#curPage").val();//当前页数
							if(index == 0){
								selectAll(curPage,pageCount);
							}else if(index == 1){
								selectnewparcel(curPage,pageCount)
							}
							buttonNewOrderCount();//查询新订单条数
						}else if(data.msg=="01"){
							$.alert({millis:2000,text:"合并失败！"});
						}
					}
				});
			}
		}})
	 }
	 /**
	 * 发货弹出框
	 */
	function openalldelivergoods(v){
		var packageNumber = v.packageNumber;
		$.ajax({
			url :ctx + "/parcel/queryDeliveryReturn",
			data : {
				packageNumber : packageNumber
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					openModule(ctx + "/jsp/order_three/warehouse/deliverGoodsModule.jsp",{},function(){
						queryBaseDictionary(2003,8,"express");
						$.each(data.list,function(i,v){
							$("#express").find("option[value="+ v.dictaryCode +"]").attr("selected",true);
							$("#OrderuserName").html(v.order.userName);
							$("#OrderreceiptTime").html(v.order.receiptTime);
							$("#OrderreceiverAddress").html(v.order.receiverAddress);
							$("#OrderuserMobile").html(v.order.userMobile);
							$("#id").val(v.id);
							$("#version").val(v.version);
							$("#orderid").val(v.order.id);
							var html = $("<tr class='text-center'></tr>").append("<td>"+v.item.productName +"(" +v.item.productUnitText+")</td>"
									+"<td>"+v.item.productPriceTypeText+"</td><td>"+v.item.nowPrice+"</td><td>"+v.item.quantity+"</td><td>"+v.item.productSpec+"</td>"
							);
							$("#ItemproductName").append(html);
						})
					});
				}
			}
		});
	}
	/**
	 * 发货
	 */
	function insertdelivergoods(){
		var dictaryCode = $("#express").val();
		var logisticsNumber = $("#logisticsnumber").val();
		var id = $("#id").val();
		var version = $("#version").val();
		var orderid = $("#orderid").val();
		$.ajax({  
			url :ctx + "/parcel/updateDelivergoods",
			data : {
				dictaryCode : dictaryCode,
				logisticsNumber :logisticsNumber,
				id : id,
				version : version,
				state : "3",
				"order.id" : orderid
			},
			type : 'post',
			async : false,
			success:function(data){
				if(data.msg == "00"){
					showMsg("发货成功！")
				}else{
					showMsg("发货失败！");
				}
			}
		});
		var pageCount = $("#pageCount").val();//第页显示记录数
		var curPage = $("#curPage").val();//当前页数
		selectAll(curPage,pageCount);//查询全部
		selecttobeshipped(curPage,pageCount);//查询待发货
		buttonNewOrderCount();//查询新订单条数
		buttonTobeshippedOrderCount();//查询待发货条数
		buttonAlreadyshippedOrderCount();//查询已发货条数
		closeModule();
	}
	/**
	 * 查询新订单条数
	 */
	function buttonNewOrderCount(){
		$.ajax({
			url : ctx + "/parcel/queryNewOrderCount",
			data : {
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$.each(data.list,function(i,v){
						if(v.dateNumber == 1){
							$("#today").html(v.createTime);
							$("#todayneworder").html(v.ordercount);
							$("#buttontodayneworder").val(v.createTime);
						}else if(v.dateNumber == 2){
							$("#yesterday").html(v.createTime);
							$("#yesterdayorder").html(v.ordercount);
							$("#buttonyesterdayorder").val(v.createTime);
						}else if(v.dateNumber == 3){
							$("#beforeyesterday").html(v.createTime);
							$("#beforeyesterdayorder").html(v.ordercount);
							$("#buttonbeforeyesterdayorder").val(v.createTime);
						}
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
	}
	/**
	 * 查询待发货条数
	 */
	function buttonTobeshippedOrderCount(){
		$.ajax({
			url : ctx + "/parcel/queryTobeshippedOrderCount",
			data : {
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$.each(data.list,function(i,v){
						if(v.dateNumber == 1){
							$("#todayTobeshipped").html(v.ordercount);
							$("#buttontodayTobeshipped").val(v.createTime);
						}else if(v.dateNumber == 2){
							$("#yesterdayTobeshipped").html(v.ordercount);
							$("#buttonyesterdayTobeshipped").val(v.createTime);
						}else if(v.dateNumber == 3){
							$("#beforeyesterdayTobeshipped").html(v.ordercount);
							$("#buttonbeforeyesterdayTobeshipped").val(v.createTime);
						}
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
	}
	/**
	 * 查询已发货条数
	 */
	function buttonAlreadyshippedOrderCount(){
		$.ajax({
			url : ctx + "/parcel/queryAlreadyshippedOrderCount",
			data : {
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$.each(data.list,function(i,v){
						if(v.dateNumber == 1){
							$("#todayAlreadyshipped").html(v.ordercount);
							$("#buttontodayneworder").val(v.createTime);
						}else if(v.dateNumber == 2){
							$("#yesterdayAlreadyshipped").html(v.ordercount);
							$("#buttonyesterdayorder").val(v.createTime);
						}else if(v.dateNumber == 3){
							$("#beforeyesterdayAlreadyshipped").html(v.ordercount);
						}
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
	}
	/**
	 * 新订单查询
	 * @param start
	 * @param limit
	 */
	function selectnewparcel(start,limit){
		$("#selectallparcelnew").html("");
		var submitStartTime = $("#ordertime").val();
		var submitEndTime = $("#ordertimes").val();
		var orderCode = $("#order_number").val();
		var receiptTime = $("#deliverydate").val();
		var receiptTimes = $("#deliverydates").val();
		var packageNumber = $("#packageNumber").val();
		var receiver_mobile = $("#receiver_mobile").val();
		var state = $("#state").val();
		var orderStatus = $("#orderState").val();
		var html = "";
		$.ajax({
			url : ctx + "/parcel/queryParcel",
			data : {
				"curPage":start,
				"pageCount":limit,
				submitStartTime : submitStartTime,
				submitEndTime : submitEndTime,
				"order.orderCode" : orderCode,
				packageNumber : packageNumber,
				state : "1",
				"order.receiverMobile" : receiver_mobile,
				"order.receiptTime" : receiptTime,
				"order.receiptTimes" : receiptTimes,
				"order.orderStatus" : orderStatus
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivwareNewOrder").pagination(data.page,newOrder);
					$("#selectallparcelnew").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						var html = $("<tr onclick='newselectTrDivide(this);queryParcelDetails("+v.packageNumber+");queryParcelLogistics("+v.packageNumber+","+v.state+")'></tr>").append("<td>"
								+"<input value="+v.id+" data-orderId="+v.order.id+" data-state="+v.state+" name='newparcelCheckbox' type='checkbox' onclick='event.stopPropagation();validateCheckAll()'/></td>"
								+"<td>" + (total + num - pageCount) + "</td><td>" + (v.order?v.order.orderCode:"") + "</td>"
								+"<td>" + v.packageNumber + "</td><td>" + (v.order?v.order.createTime:"") + "</td>"
								+"<td>" + (v.order?v.order.userMobile:"") + "</td>"
								+"<td>" + v.createByDept + "</td><td>" + v.orderRechargeDept + "</td>"
								+"<td class='text-l'>"+ (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':"")+"</td>");
						$("#selectallparcelnew").append(html);
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
		/*初始化所有弹出提示框 */
		$('[data-toggle="popover"]').popover();
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#selectallparcelnew > tr");
	    $("#selectallparcelnew").find("tr:first").trigger("click");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function newOrder(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selectnewparcel(num,(count?count:15));
	  }
	 /**
	  * 新订单行点击选中记录
	  * @param thiz
	  */
	 function newselectTrDivide(thiz){
	 	var $check = $(thiz).find("input[type=checkbox]");
	 	$check.prop("checked",!$check.prop("checked"));
	 	newvalidateCheckAll();//验证全选
	 }
	 /**
	  * 新订单根据所有行是否被选中
	  * 来切换全选框选中
	  */
	 function newvalidateCheckAll(){
	 	var $checkedDivide = $("#selectallparcelnew input[type='checkbox']:checked");
	 	var $divide = $("#selectallparcelnew input[type='checkbox']");
	 	if($checkedDivide.size() == $divide.size()){
	 		$("#parcelnew").prop("checked",true);
	 	}else{
	 		$("#parcelnew").prop("checked",false);
	 	}
	 }
	 /**
	  * 新订单全选
	  */
	 function newselectAllCheckbox(){
	 	var $inputs = $("input[type='checkbox'][name='newparcelCheckbox']");
	 	$inputs.prop("checked",$("#parcelnew").prop("checked"));
	 }
	/**
	 * 待发货查询
	 * @param start
	 * @param limit
	 */
	function selecttobeshipped(start,limit){
		$("#selecttobeshippedparcel").html("");
		var submitStartTime = $("#ordertime").val();
		var submitEndTime = $("#ordertimes").val();
		var orderCode = $("#order_number").val();
		var receiptTime = $("#deliverydate").val();
		var receiptTimes = $("#deliverydates").val();
		var packageNumber = $("#packageNumber").val();
		var receiver_mobile = $("#receiver_mobile").val();
		var orderStatus = $("#orderState").val();
		var html = "";
		$.ajax({
			url : ctx + "/parcel/queryParcel",
			data : {
				"curPage":start,
				"pageCount":limit,
				submitStartTime : submitStartTime,
				submitEndTime : submitEndTime,
				"order.orderCode" : orderCode,
				packageNumber : packageNumber,
				state : "2",
				"order.receiverMobile" : receiver_mobile,
				"order.receiptTime" : receiptTime,
				"order.receiptTimes" : receiptTimes,
				"order.orderStatus" : orderStatus
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivwarebeshipped").pagination(data.page,beshipped);
					$("#selecttobeshippedparcel").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						if(v.state == 2){
							$button = $("<button type='button' class='btn btn-primary btn-xs'><em class='glyphicon glyphicon-share-alt'></em>发货</button>").click(function(){
								openalldelivergoods(v)
							});
						}
						num=i+1;
						var html = $("<tr onclick='queryParcelDetails("+v.packageNumber+");queryParcelLogistics("+v.packageNumber+","+v.state+")'></tr>").append(
								"<td>" + (total + num - pageCount) + "</td><td>" + (v.order?v.order.orderCode:"") + "</td>"
								+"<td>" + v.packageNumber + "</td><td>" + (v.order?v.order.createTime:"") + "</td>"
								+"<td>" + (v.order?v.order.userMobile:"") + "</td>"
								+"<td>" + v.createByDept + "</td><td>" + v.orderRechargeDept + "</td>"
						).append($("<td class='text-l'>"+ (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':"")+"</td>").append($button));
						$("#selecttobeshippedparcel").append(html);
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#selecttobeshippedparcel > tr");
	    $("#selecttobeshippedparcel").find("tr:first").trigger("click");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function beshipped(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selecttobeshipped(num,(count?count:15));
	  }
	/**
	 * 已发货查询
	 * @param start
	 * @param limit
	 */
	function selectdelivergoods(start,limit){
		$("#selectdelivergoodsparcel").html("");
		var submitStartTime = $("#ordertime").val();
		var submitEndTime = $("#ordertimes").val();
		var orderCode = $("#order_number").val();
		var receiptTime = $("#deliverydate").val();
		var receiptTimes = $("#deliverydates").val();
		var packageNumber = $("#packageNumber").val();
		var receiver_mobile = $("#receiver_mobile").val();
		var orderStatus = $("#orderState").val();
		var html = "";
		$.ajax({
			url : ctx + "/parcel/queryParcel",
			data : {
				"curPage":start,
				"pageCount":limit,
				submitStartTime : submitStartTime,
				submitEndTime : submitEndTime,
				"order.orderCode" : orderCode,
				packageNumber : packageNumber,
				state : "3",
				"order.receiverMobile" : receiver_mobile,
				"order.receiptTime" : receiptTime,
				"order.receiptTimes" : receiptTimes,
				"order.orderStatus" : orderStatus
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivwaredelivergoods").pagination(data.page,delivergoods);
					$("#selectdelivergoodsparcel").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						var html = $("<tr onclick='queryParcelDetails("+v.packageNumber+");queryParcelLogistics("+v.packageNumber+","+v.state+")'></tr>").append(
								"<td>" + (total + num - pageCount) + "</td><td>" + (v.order?v.order.orderCode:"") + "</td>"
								+"<td>" + v.packageNumber + "</td><td>" + (v.order?v.order.createTime:"") + "</td>"
								+"<td>" + (v.order?v.order.userMobile:"") + "</td>"
								+"<td>" + v.createByDept + "</td><td>" + v.orderRechargeDept + "</td>"
								+"<td>" + (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':"") + "</td>");
						$("#selectdelivergoodsparcel").append(html);
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#selectdelivergoodsparcel > tr");
	    $("#selectdelivergoodsparcel").find("tr:first").trigger("click");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function delivergoods(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selectdelivergoods(num,(count?count:15));
	  }
	/**
	 * 已完成查询
	 * @param start
	 * @param limit
	 */
	function selectcomplete(start,limit){
		$("#selectcompleteparcel").html("");
		var submitStartTime = $("#ordertime").val();
		var submitEndTime = $("#ordertimes").val();
		var orderCode = $("#order_number").val();
		var receiptTime = $("#deliverydate").val();
		var receiptTimes = $("#deliverydates").val();
		var packageNumber = $("#packageNumber").val();
		var receiver_mobile = $("#receiver_mobile").val();
		var orderStatus = $("#orderState").val();
		var html = "";
		$.ajax({
			url : ctx + "/parcel/queryParcel",
			data : {
				"curPage":start,
				"pageCount":limit,
				submitStartTime : submitStartTime,
				submitEndTime : submitEndTime,
				"order.orderCode" : orderCode,
				packageNumber : packageNumber,
				state : "4",
				"order.receiverMobile" : receiver_mobile,
				"order.receiptTime" : receiptTime,
				"order.receiptTimes" : receiptTimes,
				"order.orderStatus" : orderStatus
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivwarecomplete").pagination(data.page,complete);
					$("#selectcompleteparcel").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						num=i+1;
						var html = $("<tr onclick='queryParcelDetails("+v.packageNumber+");queryParcelLogistics("+v.packageNumber+","+v.state+")'></tr>").append(
								"<td>" + (total + num - pageCount) + "</td><td>" + (v.order?v.order.orderCode:"") + "</td>"
								+"<td>" + v.packageNumber + "</td><td>" + (v.order?v.order.createTime:"") + "</td>"
								+"<td>" + (v.order?v.order.userMobile:"") + "</td>"
								+"<td>" + v.createByDept + "</td><td>" + v.orderRechargeDept + "</td>"
								+"<td>" + (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':"") + "</td>");
						$("#selectcompleteparcel").append(html);
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#selectcompleteparcel > tr");
	    $("#selectcompleteparcel").find("tr:first").trigger("click");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function complete(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selectcomplete(num,(count?count:15));
	  }
	/**
	 * 批量发货记录
	 * @param start
	 * @param limit
	 */
	function selectUpload(start,limit){
		$("#seleceUploadLog").html("");
		var html = "";
		$.ajax({
			url : ctx + "/uploadLog/queryUploadLog",
			data : {
				"curPage":start,
				"pageCount":limit,
				"parcel.dictaryCode" :"",
			    "parcel.logisticsNumber" :"",
			    "dictionary.dict_Name" :"",
			    "parcel.packageNumber" :""
			},
			type : 'post',
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					$("#pageInfoDivwareUploadLog").pagination(data.page,UploadLog);
					$("#seleceUploadLog").html("");
					var pageCount = data.page.pageCount;
					var curPage = data.page.curPage;
					var total = curPage * pageCount;
					var num = 
					$.each(data.list,function(i,v){
						var $button = null;
						num=i+1;
						var html = $("<tr class='text-center'></tr>").append("<td>" + (total + num - pageCount) + "</td>"
								+"<td>" + (v.parcel?v.parcel.packageNumber:"") + "</td><td>" + (v.dictionary?v.dictionary.dictName:"") + "</td>"
								+"<td class='text-l'>" + (v.parcel?v.parcel.logisticsNumber:"") + "</td><td>" + v.createTime + "</td>"
								+"<td>" + (v.state == 1?'新订单':v.state == 2?'待发货':v.state == 3?'已发货':v.state == 4?'已完成':v.state == 5?'失败':"") + "</td>"	
								+"<td class='text-l'>" + v.reason + "</td>");
						$("#seleceUploadLog").append(html);
					})
				}else if(data.msg=="02"){
					showMsg("无记录");
				}
			}
		});
		/*表格点击行高亮*/
	    trColor("#native_tbody > tr");
	    /*表格单选*/
	    radioColor("#seleceUploadLog > tr");
	}
	/**
	 * 分页跳转使用
	 * @param num
	 * @param count
	 */
	 function UploadLog(num,count) {
	 	if (null == num || "" == num || isNaN(parseInt(num))) {
	 		alert("请输入数字");
	  		return;
	 	}
	 	selectUpload(num,(count?count:15));
	  }
	 /**
		 * 详情
		 * @param packageNumber
		 */
		function queryParcelDetails(packageNumber){
			$("#logisticsDetails").html("");
			$.ajax({
				url :ctx + "/parcel/queryParcelDetails",
				data : {
					packageNumber : packageNumber
				},
				type : 'post',
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						$.each(data.list,function(i,v){
							$("#orderCode").html(v.order.orderCode);//订单号
							$("#orderCreateTime").html(v.order.createTime);//创建时间
							//订单状态
							if(v.order.orderStatus == 2){
								$("#orderStatus").html("已受理");
							}else if(v.order.orderStatus == 13){
								$("#orderStatus").html("拣货中");
							}else if(v.order.orderStatus == 14){
								$("#orderStatus").html("配送中");
							}else if(v.order.orderStatus == 9){
								$("#orderStatus").html("已完成");
							}else if(v.order.orderStatus == 10){
								$("#orderStatus").html("已取消");
							}
							$("#orderPayStatus").html(v.order.payStatusText);//支付状态
							$("#userName").html(v.order.userName);//客户电话
							$("#userMobile").html(v.order.userMobile);//下单电话
							$("#receiverName").html(v.order.receiverName);//收货人
							$("#receiverMobile").html(v.order.receiverMobile);//收货人电话
							$("#orderAddr").html(v.order.receiverProvince+v.order.receiverCity+v.order.receiverArea+v.order.receiverAddress);//收货地址
							$("#receiptTime").html(v.order.receiptTime);//配送时间
							$("#orderRemark").html(v.order.remark);//用户备注
							var html = $("<tr class='text-center'></tr>").append("<td>"+v.item.productName +"(" +v.item.productUnitText+")</td>"
								+"<td>"+v.item.brandName+"</td><td>"+v.item.productPriceTypeText+"</td>"
								+"<td>"+v.item.nowPrice+"</td><td>"+v.item.quantity+"</td><td>"+v.item.productSpec+"</td>"
							);
							$("#logisticsDetails").append(html);
						})
					}
				}
			});
		}
		/**
		 * 查询物流信息
		 * @param packageNumber
		 * @param state
		 */
		function queryParcelLogistics(packageNumber,state){
			$("#logisticsInformation").html("");
			$("#logisticsCompany").html("");//快递公司
			$("#logisticsNumber").html("");//快递号
			$("#commodity").html("");//商品
			if(state == 1 || state == 2){
				return
			}
			$.ajax({
				url :ctx + "/parcel/queryParcelLogistics",
				data : {
					packageNumber : packageNumber
				},
				type : 'post',
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						$.each(data.list,function(i,v){
							$("#logisticsCompany").html(v.dictName);//快递公司
							$("#logisticsNumber").html(v.packageNumber);//快递号
							$("#commodity").html(v.item.productName);//商品
							if(v.json != ""){
								$.each(v.json,function(i,s){
									var data = s.AcceptTime.split(" ")
									var html = "<li><div class='trip clearfix'><div class='time'>"
											+"<a>"+ data[1] +"</a>"
											+"<a class='date'>"+ data[0] +"</a></div>"
											+"<p>"+ s.AcceptStation +"</p>";
										if(i != v.json.length-1){
											html += "</div><div class='pic_enp'></div></li>";
										}else{
											html += "</div></li>";
										}
									$("#logisticsInformation").append(html);
								})
							}
						})
					}
				}
			});
		}
		/**
		 * 导出
		 */
		function exportExcel(){
			var submitStartTime = $("#ordertime").val();
			var submitEndTime = $("#ordertimes").val();
			var orderCode = $("#order_number").val();
			var receiptTime = $("#deliverydate").val();
			var receiptTimes = $("#deliverydates").val();
			var packageNumber = $("#packageNumber").val();
			var receiverMobile = $("#receiver_mobile").val();
			var state = $("#state").val();
			var orderStatus = $("#orderState").val();
			 $.ajax({
					url : ctx + "/parcel/queryExcel",
					data : {
						submitStartTime : submitStartTime,
						submitEndTime : submitEndTime,
						"order.orderCode" : orderCode,
						packageNumber : packageNumber,
						state : state,
						"order.receiverMobile" : receiverMobile,
						"order.receiptTime" : receiptTime,
						"order.receiptTimes" : receiptTimes,
						"order.orderStatus" : orderStatus
					},
					type : 'post',
					async : false,
					success : function(data) {
						if(data.msg == "00"){
							window.open(ctx + "/parcel/downExcel?filename="+data.a, "_self");
							$("#warereset").trigger("click"); 
							selectAll(1,10);
							buttonNewOrderCount();//查询新订单条数
							buttonTobeshippedOrderCount();//查询待发货条数
							buttonAlreadyshippedOrderCount();//查询已发货条数
						}else if(data.msg=="02"){
							showMsg("失败");
						}
					}
				});
		    }
		/**
		 * 上传物流弹出框
		 */
		function openimportExcel(){
			openModule(ctx + "/jsp/order_three/warehouse/importExcel.jsp",{},function(){
			})
		}
		/**
		 * 拆分包裹弹出框
		 */
		function splitParcel(){
			var index = $("#activeId").find(".client-add-active").index();
			var parcelId = "";
			if(index == 0){
				var checkValues = $("input[name='parcelCheckbox']:checked");
				packageNumber = $("input[name='parcelCheckbox']:checked").attr("data-packageNumber");
			}else if(index == 1){
				var checkValues = $("input[name='newparcelCheckbox']:checked");
				packageNumber = $("input[name='newparcelCheckbox']:checked").attr("data-packageNumber");
			}else{
				$.alert({millis:2000,text:"请到全部或者新订单标签下选择包裹拆分！"});
				return;
			}
			if(checkValues.length != 1){
				$.alert({millis:2000,text:"请选择选择一个包裹！"});
				return;
			}
			for (var i = 0; i < checkValues.length; i++) {
				if(1 != $(checkValues[i]).attr("data-state")){
					$.alert({millis:2000,text:"请选择包裹状态为新订单！"});
					return;
				}
			}
			$.ajax({
				url : ctx + "/parcel/queryDeliveryReturn",
				data : {
					packageNumber : packageNumber
				},
				type : 'post',
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						openModule(ctx + "/jsp/order_three/warehouse/splitParcelModule.jsp",{},function(){
							$("#parcelNum").html(1);
							$.each(data.list,function(i,v){
								$("#orderId").val(v.orderId);
								$("#warehouseId").val(v.warehouseId);
								var html = $("<tr class='text-center'></tr>").append("<td>"
										+"<input data-productUnitText='"+v.item.productUnitText+"' data-prodName='"+v.item.productName+"' value='"+v.item.id+"' name='spiltCheckbox' type='checkbox'/></td>"
										+"<td>"+v.item.productName +"(" +v.item.productUnitText+")</td><td>"+v.item.quantity+"</td><td>"+v.item.productSpec+"</td>"
								);
								$("#spiltList").append(html);
							})
						})
					}else if(data.msg=="02"){
						
					}
				}
			});
		}
		/**
		 * 创建新包裹
		 */
		function createPackages(){
			var $checkedDivide = $("#spiltList input[type='checkbox']:checked");
		 	var $divideAll = $("#spiltList input[type='checkbox']");
		 	var $divide = $("#spiltList input[type='checkbox']").not(":hidden");
		 	var checkValues = $("input[name='spiltCheckbox']:checked");
		 	if(checkValues.length == 0){
				$.alert({millis:2000,text:"请选择商品！"});
				return;
			}
		 	if($checkedDivide.size() == $divideAll.size()){
		 		$.alert({millis:2000,text:"不能全部拆分！"});
				return;
		 	}
		 	if($divide.size() == 1){
		 		$.alert({millis:2000,text:"不能全部拆分！"});
				return;
		 	}
		 	var html = "";
		 	var itemId = "";
			for (var i = 0; i < checkValues.length; i++) {
				$(checkValues[i]).prop("checked",false)
				$(checkValues[i]).parent().parent().hide();
				itemId += checkValues[i].value+","
				html += "<input type='hidden' name='create' value='"+checkValues[i].value+"'/><p>"+$(checkValues[i]).attr("data-prodName")+"(" +$(checkValues[i]).attr("data-productUnitText")+")</p>"
			}
			var htmls = "<div><input type='hidden' name='itemId' value='"+itemId.substring(0,itemId.length-1)+"'/><h4 style='float:left;'>"
					  + "包裹<span name='num'></span></h4>"
					  + "<button style='float:right;' type='button' class='btn btn-xs btn-primary' onclick='recovery(this)'>删除</button></div>"
			$("#addparcel").append($(htmls).append("<div class='wrap_list clearfix'>"+html+"</div><hr>"))
			changeNum()
		}
		/**
		 * 删除新包裹，同时原来的显示出来
		 * @param thiz
		 */
		function recovery(thiz){
			var createCheck = $(thiz).siblings("div").find("input[name='create']");
			var $divide = $("#spiltList input[type='checkbox']");
			for (var i = 0; i < createCheck.length; i++) {
				for (var j = 0; j < $divide.length; j++) {
					if(createCheck[i].value == $divide[j].value){
						$($divide[j]).parent().parent().show();
					}
				}
			}
			$(thiz).parent().remove();
			changeNum()
		}
		/**
		 * 生成包裹号
		 */
		function changeNum(){
			 $("[name=num]").empty();
			 $("[name=num]:visible").each(function(i,v){
				 $(v).html(i+1);
			})   
		}
		/**
		 * 拆分包裹提交
		 */
		function splitInsert(){
			var itemId = $("[name=itemId]");
			var arr = "";
			itemId.each(function(i,v){
			   arr += v.value+"-";
			})
			var orderId = $("#orderId").val();
			var warehouseId = $("#warehouseId").val();
			if(arr == ""){
				return;
			}
			$.ajax({
				url : ctx + "/parcel/splitParcel",
				data : {
					arrItemId : arr.substring(0,arr.length-1),
					orderId : orderId,
					warehouseId : warehouseId
				},
				type : 'post',
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						
					}else if(data.msg=="02"){
						
					}
				}
			});
			closeModule();
			selectAll(1,10);//查询全部
			selectnewparcel(1,10)//查询新订单
			buttonNewOrderCount();//查询新订单条数
			buttonTobeshippedOrderCount();//查询待发货条数
			buttonAlreadyshippedOrderCount();//查询已发货条数
		}
		
		
		
		
		
		