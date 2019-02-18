/**
 * 售后单管理js
 * @author wn
 * @date 7-16
 */
 var ctx=$("#ctx").val();
 var afterSalesKindArr={1:"FA订单取消",2:"FA订单退货/退款",3:"FA订单换货重发",4:"单次服务订单取消",5:"延续性服务订单取消",6:"解决方案订单取消",7:"延续性服务订单换人",8:"延续性服务订单终止",9:"解决方案订单退费",10:"延续性服务订单退费",11:"唯品会白条分期退费",12:"迁单",13:"唯品会白条分期服务费退费",14:"海金保理白条分期服务费退费",15:"招行分期分期服务费退费",17:"海金保理白条分期退费",18:"招行分期退费",19:"分期回购海金保理",20:"分期回购唯品会 ",16:"卡退费 ",21:"汇嘉分期退费"};
 var auditFlagArr = {20130013:"新建",20260001:"已取消",20130001:"待确认",20130002:"确认无效",20130003:"确认有效 ",20130004:"审核中",20130005:"审核失败",20130006:"审核成功",20130007:"退款中",20130008:"退款成功",20130009:"退款失败",20130010:"已删除",20130011:"结算中心审核中",20130012:"结算中心驳回",20130014:"回访中",20130015:"回访失败"};
 var orderStatus = {1:"新单",2:"已受理",3:"匹配中",4:"已匹配",5:"待面试",6:"面试成功",7:"已签约",8:"已上户",9:"已完成",10:"已取消",11:"已下户",12:"已终止",13:"捡货中",14:"配送中",16:"三方订单匹配失败",17:"工作完成",18:"待受理"};
 var vphCancleArr = {20130001:"待确认",20130002:"确认无效",20130003:"确认有效 ",20130004:"审核中",20130005:"审核失败",20130006:"审核成功",20310001:"取消中",20310002:"取消成功",20310003:"取消失败","-":"-",20130011:"结算中心审核中",20130012:"结算中心驳回",20130009:"退款失败",20130008:"退款成功"};
 var vphBackArr = {20130001:"待确认",20130002:"确认无效",20130003:"确认有效 ",20130004:"审核中",20130005:"审核失败",20130006:"审核成功",20130007:"退款中",20130008:"退款成功",20130009:"退款失败","-":"-",20130011:"结算中心审核中",20130012:"结算中心驳回"};
 var cardStatusArr = {101:"未销售",102:"已登记",103:"已激活",104:"已绑定",105:"已过期",201:"未销售",202:"已激活",203:"已绑定",204:"已过期",206:"已失效"};

 var solution_status = {"":"", 0:"", 1:"新单",2:"已受理",3:"执行中",4:"暂停中",5:"已完成",6:"已取消"};
 var solution_frequency = {"":"", 0:"", 1 : "一周一次", 2 : "两周一次", 3 : "一月一次", 4 : "仅一次", 5 : "自定义" };
 var flag = true;
 var solutionBigOrderflag = true;
	//显示卡售后详情
	function showCardDetail(cardId){
		//显示table
		$("#tableBuyBackDetail").hide();//隐藏回购详情
		$("#tableDetail").hide();  //隐藏订单详情table
		$("#tableSolutionDetail").hide();//隐藏方案详情
		$("#tableNoData").hide();
		$("#tableCardDetail").show()//展示卡片详情

		//清除历史数据
		resetSolutionDetail();
		resetAllOrderDetail();

		//页面赋值
		$.ajax({
			url: ctx+"/afterSalesNew/findCardAfterSalesDetail/"+cardId,
			type:"get",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00"){
					var afterSalesNew = data.afterSalesNew;
					$("#card_number").html(afterSalesNew.cardNumber);//卡编号
					$("#card_status").html(cardStatusArr[afterSalesNew.cardStatus]);//卡状态
					$("#card_amount").html(afterSalesNew.totalMoney);//卡面值
					$("#card_balance").html(afterSalesNew.cardbalance);//卡余额
					$("#card_source").html(afterSalesNew.channelName);//购卡来源
					$("#card_recommendName").html(afterSalesNew.recommendName);//推卡人
					$("#card_give").html(afterSalesNew.gjMoney);//卡提成
					$("#card_accountBalance").html(afterSalesNew.gjBalance);//管家账户余额(退卡后)
					$("#card_custName").html(afterSalesNew.custName);//客户姓名
					$("#card_custMobile").html(afterSalesNew.custMobile);//联系方式
					$("#card_auditFlag").html(auditFlagArr[afterSalesNew.auditFlag]);//售后状态
					$("#card_salesKind").html(afterSalesKindArr[afterSalesNew.afterSalesKind]);//售后类型
					$("#card_total").html(afterSalesNew.refundTotal);//退费总额
					$("#card_reason").html(afterSalesNew.reason);//原因
					if(afterSalesNew.gjBalance < 0){
						$("#card_accountBalance").css('color','red');
					}else{
						$("#card_accountBalance").css('color','black');
					}
				}
			}
		});
	}
 //售后单查询
	function queryAfterSalesByLike(num,pageCount){
		var custName = $("#userNameOrder").val();
		var custMobile = $("#userMobileOrder").val();
		var auditFlag = $("#orderStatus").val();
		var afterSalesKind = $("#serverType").val();
		var creStart = $("#creStart").val();
		var creEnd = $("#creEnd").val();
		var afterSaleIdSearch = $("#afterSaleIdSearch").val();
		var fkCode = $("#afterSales_fkcode").val();//关联编号
		//var orderCodeSearch = $("#orderCodeSearch").val();
		//var managerType = $("#managerTypeQualityList").val();
		$.ajax({
			url: ctx+"/afterSales/queryAfterSales?curPage="+num+"&pageCount="+pageCount,
			data:{
				custName:custName,
				custMobile:custMobile,
				auditFlag:auditFlag,
				afterSalesKind:afterSalesKind,
				creStart:creStart,
				creEnd:creEnd,
				id:afterSaleIdSearch,
				orderCode:fkCode,
				fkCode:fkCode
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html="";
				$("#pageQualityDiv").pagination(data.page,listBodyQuality);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				
				if (data.msg == "00") {
					$("#userLoginLevel").val(data.loginLevel);
					var num = $.each(data.list,function(i,v){
						    var auditStr =  auditFlagStr(v.auditFlag,v.remark,v.accountRemark);
							if(i == 0){
								if(v.orderType == 1){
									showQualitySolutionDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
									showCallBackList(v.id,v.orderId);
								}else if(v.orderType == 2){
									//showQualityDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
									showQualityDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag,v.bathno);
									showCallBackList(v.id,v.orderId);
								}else{
									showCardDetail(v.orderId);
									showCallBackList(v.id,v.orderId);
								}
							}
							num=i+1;
							if(v.orderType == 1){
								html +="<tr onclick='showQualitySolutionDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+");showCallBackList("+ v.id+","+v.orderId+");'>";
							}else if(v.orderType == 2){
								html +="<tr onclick='showQualityDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+","+v.bathno+");showCallBackList("+ v.id+","+v.orderId+");'>";
							}else{
								html +="<tr onclick='showCardDetail("+v.orderId+");showCallBackList("+ v.id+","+v.orderId+");'>" ;
							}
							html +="<td><input data-orderTypeCode='"+(v.order?v.order.orderType:"")+"' name='quaRadio' type='radio' value='" +v.id +"-"+v.orderId +"-"+v.auditFlag +"-"+v.order.cateType+"-"+v.afterSalesKind+"-"+(v.vphBackStatus=="-"?" ":v.vphBackStatus)+"-"+v.accountPayId+"-"+v.vphAccountId+"-"+v.payFeeId+"'/></td>";
							html +="<td>"+(total + num - pageCount);
							html +="</td><td>"+v.id;
							if(v.auditFlag == "20130009" || v.auditFlag == "20130005"|| v.auditFlag == "20130002" || v.auditFlag == "20130012" || v.auditFlag == "20130015"){//退款失败，状态显示红色
								html +="</td><td align='left' style='color:red;'>"+auditStr;
							}else{
								html +="</td><td align='left' style='color:green;'>"+auditStr;
							}
							/*html +="</td><td>"+vphBackArr[v.vphBackStatus];
							html +="</td><td>"+vphCancleArr[v.vphCancleStatus];*/
							html +="</td><td>"+(v.fkCode||"");
							html +="</td><td>"+v.custName;
							/*if(managerType != null && managerType==2){
								html += "";
							}else{
								html +="</td><td>"+v.custMobile;
							}*/
							html +="</td><td>"+afterSalesKindArr[v.afterSalesKind];
							html +="</td><td>"+(v.isStop == 2?"否":"是");
							html +="</td><td>"+numberToDatestr(v.createTime,8);
							html +="</td><td>"+v.order.log;
							html +="</td></tr><tr></tr>";
					})
				}else if(data.msg == "02"){
						html +="<tr><td colspan='13'><p class='table-empty'>暂无数据</p></td></tr>";
						$("#pageQualityDiv").hide();
						$("#tableDetail").hide();
						$("#tableSolutionDetail").hide();
						$("#tableCardDetail").hide();
						$("#tableBuyBackDetail").hide();
						$("#tableNoData").show();
						$("#tableNoData").html("<table class='table table-condensed'><tr><td colspan='2'><p class='table-empty'>暂无数据</p></td></tr></table>");
						resetSolutionDetail();
						resetAllOrderDetail();
				}else{
					$.alert({millis:5000,top:'30%',text:data.msg});
				}
				$("#listBodyQuality").html(html);
				//设置默认行背景色
				$("#listBodyQuality tr").each(function(n) {
					if(n==0){
						$(this).find("input[name='quaRadio']").attr("checked", "checked");
						$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer'); return;
					}
				});
			}
		});
		/*初始化所有弹出提示框 */
		$('[data-toggle="popover"]').popover();
		/*表格单选*/
		radioColor("#listBodyQuality > tr");
		/*表格点击行高亮*/
		trColor("#native_tbody > tr");
	}
	
	  //售后信息详情
	 function showQualityDetail(saleId,orderId,saleKind,auditFlag,bathno){
		 if("19" == saleKind || "20" == saleKind){//分期回购
			 //隐藏table
			 $("#tableSolutionDetail").hide();
			 $("#tableDetail").hide();
			 $("#tableNoData").hide();
			 $("#tableCardDetail").hide();
			 resetAllOrderDetail();
			 //cleanTableCardDetail();

			 //显示分期回购table
			 $("#tableBuyBackDetail").show();
			 $("#saleId").val(saleId);
			 $("#orderId").val(orderId);
			 $("#saleKind").val(saleKind);
			 $("#auditFlag").val(auditFlag);

			 //参数赋值
			 showBuyBackDetail(orderId,bathno);
		 }else{
			 $("#tableBuyBackDetail").hide();
			 $("#tableSolutionDetail").hide();//隐藏解决方案详情table
			 $("#tableCardDetail").hide();
			 //cleanTableCardDetail();
			 $("#tableDetail").show();
			 $("#tableNoData").hide();
			 resetAllOrderDetail();
			 /*$("#tableDetail").find("lable").html("");
			  $("#tableDetail").find("textarea").html("");
			  $("#tableDetail").find("div").html("");*/
			 $("#saleId").val(saleId);
			 $("#orderId").val(orderId);
			 $("#saleKind").val(saleKind);
			 $("#auditFlag").val(auditFlag);
			 if(saleKind == 1 || saleKind == 2 || saleKind == 3){
				 $("#ServerTr1").hide();
				 $("#ServerTr2").hide();
				 $("#ServerTr3").hide();
				 $("#FACreTr").show();
				 $("#TKFA").show();
				 $("#TKTitle").show();
				 showFADetail(orderId);
			 }else{
				 $("#ServerTr1").show();
				 $("#ServerTr2").show();
				 $("#ServerTr3").show();
				 $("#FACreTr").hide();
				 $("#TKFA").hide();
				 $("#TKTitle").hide();
				 showServerDetail(orderId);
			 }
		 }
		 //showTotal(orderId,saleKind);
		 showAfterSalesDetail(saleId);
	 }
	 
	 //解决方案售后信息详情
	 function showQualitySolutionDetail(saleId,solutionId,saleKind,auditFlag){
		 $("#tableBuyBackDetail").hide();
		 $("#tableDetail").hide();  //隐藏订单详情table
		 $("#tableSolutionDetail").show();
		 $("#tableNoData").hide();
		 $("#tableCardDetail").hide();
		 //cleanTableCardDetail();
		 $("#saleId").val(saleId);
		 $("#orderId").val(solutionId);
		 $("#saleKind").val(saleKind);
		 $("#auditFlag").val(auditFlag);
		 resetSolutionDetail();
		 loadSolutionDetail(solutionId);//方案信息
		 querySolutionItemDetail(solutionId);//方案套餐信息
		 showAfterSalesDetail(saleId);//售后单信息
	 }
	 /**查询解决方案详情*/
	 function loadSolutionDetail(solutionId){
	 	$.ajax({
	 		url : ctx + '/solution/loadSolution',
	 		data : { id : solutionId ,delFlag : 2},
	 		type : 'post',
	 		async : false,
	 		success : function(data) {
	 			if (data.msg == "00") {
	 				var m = data.solution;// 获取方案订单实体
	 				$("#sd_createTime").html(numberToDatestr(m.createTime,8));//创建时间
	 				//$("#solutionOrderStatus").val(m.solutionStatus);//解决方案订单状态
	 				//$("#solutionPayStatus").val(m.payStatus);//解决方案订单支付状态
	 				if(m.manager!=null){
	 					$("#sd_managerName").html(m.follRealName);// 分包管家名称
	 					$("#sd_managerPhone").html(m.managerPhone);// 分包管家电话
	 				}
	 				$("#sd_orderStatus").html(solution_status[m.solutionStatus]);// 订单状态
	 				$("#sd_order_source_id").html(m.orderSourceId);//订单来源
	 				$("#sd_mcode").html(m.mcode);//订单渠道
	 				$("#sd_withholdingCard").html(m.feeCardNum);//m.withholdingCard//代扣卡
	 				$("#sd_orderPrice").html(intToDecimal(m.totalPay));//订单金额
	 				/** 填写订单包含项目 */
	 				$("#sd_custName").html(m.payerName);// 客户名称
	 				$("#sd_custMobile").html(m.payerMobile);// 客户电话
	 				
	 			}
	 		}
	 	});
	 }

	 /**查询解决方案套餐列表*/
	 function querySolutionItemDetail(solutionId){
	 	$.ajax({
	 		url : ctx + '/solution/querySolutionItem',
	 		data : { solutionCust_solutionId : solutionId }, // 方案订单ID
	 		type : 'post',
	 		async : false,
	 		dataType : "json",
	 		success : function(data) {
	 			if (data != null) {
	 				if (data.msg == "00" && data.list.length > 0) {
	 					var html = "";
	 					var num = $.each(data.list,function(i, v) {
	 							num = i + 1;
	 							html += "<tr align='center'>" ;
	 							html += "<td><input type='hidden' name='solutionItemChk' value ='"+v.id+"_"+v.proudctName+"_"+v.surplusNum+"' />"+num;
	 							html += "</td><td>" + v.proudctName;// 项目
	 							html += "</td><td>" + v.qty;// 数量
	 							html += "</td><td>" + v.surplusNum;//剩余数量
	 							html += "</td><td>" + v.qtyOnce;// 每次配送数量
	 							html += "</td><td>"+ solution_frequency[v.frequency];// 频次
	 							html += "</td><td>" + v.startServiceTime.substr(0,19);// 首次服务/配送时间
	 							html += "</td></tr>";
	 						});
	 					$("#sd_solutionCombos").html(html);
	 				}
	 			}
	 		}

	 	});
	 }
 	//FA订单客户信息
 	function showFADetail(orderId){
 		$.ajax({
			url: ctx+"/order/queryOrderBasicItem",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					var html = "";
					var num = $.each(data.list,function(i,v){
						 $("#order_code").html(v.orderCode);
						 $("#order_type").html("商品订单");
						 $("#order_status").html(orderStatus[v.orderStatus]);
						 $("#order_creTime").html(numberToDatestr(v.createTime,8));
						 $("#order_channel").html(v.channelText);
						 $("#order_receiverName").html(v.receiverName);
						 $("#order_receiverMobile").html(v.receiverMobile);
						 $("#order_receiverAddress").html(v.receiverAddress);
						 $("#order_source").html(v.sourceText);
						 
						 num=i+1;
					 	html +="<tr><td>"+ num;
						html +="</td><td>"+v.item.productName;
						html +="</td><td>"+v.item.nowPrice;
						html +="</td><td>"+v.item.quantity;
						html +="</td></tr><tr></tr>";
					});
					$("#refundFAInfo").html(html);
				}
			}
		});
 	}
 	//服务类订单客户信息
 	function showServerDetail(orderId){
		$.ajax({
			url: ctx+"/orderbase/queryOrderBasicServer",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					 $.each(data.list,function(i,v){
						 //$("#qd_custName").html(v.order.userName);
						 //$("#qd_custMobile").html(v.order.userMobile);
						 $("#order_receiverName").html(v.order.receiverName);
						 $("#order_receiverMobile").html(v.order.receiverMobile);
						 $("#order_receiverAddress").html(v.order.receiverAddress);
						 $("#order_code").html(v.orderCode);
						 $("#order_type").html(v.typeText);
						 $("#order_startTime").html(numberToDatestr(v.startTime,8));
						 $("#order_endTime").html(numberToDatestr(v.endTime,8));
						 $("#order_serverAddress").html(v.address);
						 $("#order_status").html(orderStatus[v.order.orderStatus]);
						 $("#order_channel").html(v.channelText);
						 $("#order_source").html(v.sourceText);
						 //$("#totalPay").html(v.order.totalPay);
						 //$("#serverRemark").html(v.order.remark);
					});
				}
			}
		});
 	}
 	
 	//售后信息
 	function showAfterSalesDetail(saleId){
		$.ajax({
			url: ctx+"/afterSales/queryAfterSales",
			data:{
				id:saleId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg ="00") {
					$.each(data.list,function(i,v){
						if(v.orderType != 1){
							if(v.afterSalesKind == "19" || v.afterSalesKind == "20"){
								$("#sc_custName").html(v.custName);
								$("#sc_custMobile").html(v.custMobile);
								$("#sc_total").html(intToDecimal(v.refundTotal));//退费总额
								$("#sc_salesKind").html(afterSalesKindArr[v.afterSalesKind]);//售后类型
								$("#sc_auditFlag").html(auditFlagArr[v.auditFlag]);//售后状态
								$("#sc_reason").html(v.reason);//原因
								$("#sc_buyBack_amount").html(v.refundTotal);//回购金额
								$("#sc_agreement_code").html(v.agreementCode);//合同编号
							}else{
								$("#qd_custName").html(v.custName);
								$("#qd_custMobile").html(v.custMobile);
								$("#qd_total").html(intToDecimal(v.refundTotal));
								$("#qd_salesKind").html(afterSalesKindArr[v.afterSalesKind]);
								$("#qd_auditFlag").html(auditFlagArr[v.auditFlag]);
								$("#qd_reason").html(v.reason);
								$("#qd_remark").val(v.remark);
								if(v.vphFee != null && v.vphFee != ""){
									$("#vipshopDetailTr").show();
									$("#qd_vipshop_total").html(intToDecimal(v.vphFee));
								}else{
									$("#vipshopDetailTr").hide();
								}
							}
						}else{
							//$("#sd_custName").html(v.custName);
							//$("#sd_custPhone").html(v.custMobile);
							$("#sd_total").html(intToDecimal(v.refundTotal));
							$("#sd_salesKind").html(afterSalesKindArr[v.afterSalesKind]);
							$("#sd_auditFlag").html(auditFlagArr[v.auditFlag]);
							$("#sd_reason").html(v.reason);
							$("#sd_remark").val(v.remark);
						}
					});
				}
			}
		});
 	}
 	
 	//重置
	function resetSales(){
		$("#userNameOrder").val("");
		$("#userMobileOrder").val("");
		$("#orderStatus").val("");
		$("#serverType").val("");
		$("#creStart").val("");
		$("#creEnd").val("");
		$("#afterSaleIdSearch").val("");
		$("#orderCodeSearch").val("");
		$("#afterSales_fkcode").val("");
		queryAfterSalesByLike(0,10);
		$("#pageQualityDiv").show();
	}

	// 分页跳转使用
	function listBodyQuality(num,pageCount) {
		$("#listBodyQuality").empty();
		queryAfterSalesByLike(num,pageCount);
	}
	// 分页跳转使用
	function listBodyQualityApprove(num,pageCount) {
		$("#listBodyQualityApprove").empty();
		queryAfterSalesApprove(num,pageCount);
	}
 	
 	//审核成功
 	/*function qualityPass(){
 		var saleId = $("#saleId").val();
 		var orderId = $("#orderId").val();
 		var saleKind = $("#saleKind").val();
 		var auditFlag = $("#auditFlag").val();
 		auditFlag = 20130006; //审核成功
 		//延续性服务类型为换人或终止或退费
		if(saleKind == 7){
			updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag);
		}else if(saleKind == 8){
			updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag);
		}else if(saleKind == 10){
			updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag);
		//解决方案退费
		}else if(saleKind == 9){
			updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag);
		}else{
			$.alert({millis:3000,top:'30%',text:"请选择可以审批的售后单！"});
		}
 	}*/
 	
 	//审核失败
 	/*function qualityNotPass(){
 		var saleId = $("#saleId").val();
 		var orderId = $("#orderId").val();
 		var saleKind = $("#saleKind").val();
 		var auditFlag = $("#auditFlag").val();
 		var remark = "";
 		if(saleKind == 6 || saleKind == 9){
 			 remark = $("#sd_remark").val();
 		}else{
 			 remark = $("#qd_remark").val();
 		}
 		if(remark == "" ){
 			$.alert({millis:3000,top:'30%',text:"请在备注里填写审核不通过原因！"});
 			return;
 		}
 		
 		auditFlag = 20130005; //审核失败
 		$.ajax({
			url: ctx+"/afterSales/updateAfterSales",
			data:{
				id:saleId,
				orderId:orderId,
				auditFlag:auditFlag,
				remark:remark,
				afterSalesKind:saleKind
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$.alert({millis:3000,top:'30%',text:"修改成功！"});
					//showQualityDetail(saleId,orderId,saleKind,auditFlag);
					queryAfterSalesByLike(0,10);
				}else{
					 $.alert({millis:3000,top:'30%',text:"修改失败！"});
				}
			}
		});
 	}*/
 	//重新提交按钮操作
 	/*function qualityReSubmit(){
 		var saleId = $("#saleId").val();
 		var orderId = $("#orderId").val();
 		var saleKind = $("#saleKind").val();
 		var auditFlag = $("#auditFlag").val();
 		
 		//alert("saleId:"+saleId+" orderId:"+orderId+" saleKind:"+saleKind+" auditFlag:"+auditFlag);
 		//auditFlag = 20130006; //审核成功
 		updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag);
 	}*/
 	//更新售后单操作
 	function updateAfterSaleStatus(saleId,orderId,saleKind,auditFlag){
 		$.ajax({
			url: ctx+"/afterSales/updateAfterSales",
			data:{
				id:saleId,
				auditFlag:auditFlag,
				orderId:orderId,
				afterSalesKind:saleKind
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$.alert({millis:3000,top:'30%',text:"审核成功！"});
					//刷新详情与列表
					showQualityDetail(saleId,orderId,saleKind,auditFlag);
					queryAfterSalesByLike(0,10);
				}else{
					 $.alert({millis:3000,top:'30%',text:"审核失败！"});
				}
			}
		});
 	}
 	//重置解决方案详情
 	function resetSolutionDetail(){
 			$("#sd_createTime").html("");//创建时间
			$("#sd_managerName").html("");// 分包管家名称
			$("#sd_managerPhone").html("");// 分包管家电话
			$("#sd_orderStatus").html("");// 订单状态
			$("#sd_order_source_id").html("");//订单来源
			$("#sd_mcode").html("");//订单渠道
			$("#sd_withholdingCard").html("");//代扣卡
			$("#sd_orderPrice").html("");//订单金额
			$("#sd_custName").html("");// 客户名称
			$("#sd_custMobile").html("");// 客户电话
			$("#sd_solutionCombos").html("");
			$("#sd_total").html("");
			$("#sd_salesKind").html("");
			$("#sd_auditFlag").html("");
			$("#sd_reason").html("");
 	}
 	//重置订单详情
 	function resetAllOrderDetail(){
	 		 $("#order_code").html("");
			 $("#order_type").html("");
			 $("#order_status").html("");
			 $("#order_creTime").html("");
			 $("#order_channel").html("");
			 $("#order_receiverName").html("");
			 $("#order_receiverMobile").html("");
			 $("#order_receiverAddress").html("");
			 $("#order_source").html("");
			 $("#order_startTime").html("");
			 $("#order_endTime").html("");
			 $("#order_serverAddress").html("");
			 $("#qd_custName").html("");
			 $("#qd_custMobile").html("");
			 $("#qd_total").html("");
			 $("#qd_salesKind").html("");
			 $("#qd_auditFlag").html("");
			 $("#qd_reason").html("");
			 $("#refundFAInfo").html("");
 	}

	//转换价格类型
	function intToDecimal(str){
		return new Number(str).toFixed(2);
	}
	
	//查询解决方案大订单是否有售后单，或者售后单状态为未通过
	function querySolutionBigOrderAfterSale(orderId){
		$.ajax({
			url:ctx+"/afterSales/queryAfterSales",
			data:{
				orderId:orderId,
				pageCount:-1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00" && data.list.length > 0) {
					$.each(data.list,function(j,w){ 
						if(w.auditFlag != "20130005"){//售后单不是审核失败状态
							solutionBigOrderflag = false;
						}
					});
				}else{
					solutionBigOrderflag = true;
				}
			}
		}); 
	}
	
	//展示售后单状态，退款原因和审核失败原因悬浮
	function auditFlagStr(auditFlag,remark,accountRemark){
		//TODO:accountRemark结算单备注填写原因待用
		var auditStr = "";
		switch(auditFlag){
			case "20260001" :
				auditStr = "已取消";
				break;
			case "20130001" :
				auditStr = "待确认";
				break;
			case "20130002" :
				auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='确认无效原因：' data-toggle='popover' data-trigger='hover' data-content='"+(remark?remark:"无")+"'>确认无效</a>";
				break;
			case "20130003" :
				auditStr = "确认有效";
				break;
			case "20130004" :
				auditStr = "审核中";
				break;
			case "20130005" :
				if(accountRemark != null && accountRemark != ""){
					auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='驳回原因：' data-toggle='popover' data-trigger='hover' data-content='"+(accountRemark?accountRemark:"无")+"'>审核失败</a>";
				}else{
					auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='驳回原因：' data-toggle='popover' data-trigger='hover' data-content='"+(remark?remark:"无")+"'>审核失败</a>";
				}
				break;
			case "20130006" :
				auditStr = "审核成功";
				break;
			case "20130007" :
				auditStr = "退款中";
				break;
			case "20130008" :
				auditStr = "退款成功";
				break;
			case "20130009" :
				auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='退款失败原因：' data-toggle='popover' data-trigger='hover' data-content='"+(accountRemark?accountRemark:"无")+"'>退款失败</a>";
				break;
			case "20130010" :
				auditStr = "已删除";
				break;
			case "20130011" :
				auditStr = "结算中心审核中";
				break;
			case "20130012" :
				if(accountRemark != null && accountRemark != ""){
					auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='驳回原因：' data-toggle='popover' data-trigger='hover' data-content='"+(accountRemark?accountRemark:"无")+"'>结算中心驳回</a>";
				}else{
					auditStr = "<a style='color:red;display:inline-block;' href='javascript:void(0)' data-placement='bottom' bindex='0' title='驳回原因：' data-toggle='popover' data-trigger='hover' data-content='"+(remark?remark:"无")+"'>结算中心驳回</a>";
				}
				break;
			case "20130013" :
				auditStr = "新建";
				break;
			case "20130014" :
				auditStr = "回访中";
				break;
			case "20130015" :
				auditStr = "回访失败";
				break;
			default:
				break;
		}
		return auditStr;
	}
	/**
	 * 查询部门
	 * @param typeId
	 */
	function queryApproveDeptName(typeId){
	    var ctx=$("#ctx").val();
	    $.ajax({
	        url:ctx+"/orderbase/querydeptnameWithDept",
	        data:{
	        },
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	            var html="";
	            html +="<option style='color:blue;'  value='' >...请选择...</option>";
	            var selected = false ;
	            $.each(data.list,function(i,v){
	                html +="<option value='" +v.dkey +"' >"+v.dvalue+"</option>"; //keyValue='"+v.dvalue +"'
	            })
	            $("#"+typeId).html(html); 
	            $("#"+typeId).checkSelect(function(a,b,c){queryGuanjiaName(); });
	        }
	    });
	}
	//上一步按钮
	function lastStep(moduleId){
		var loginLevel = parent.$("#userLoginLevel").val();
		closeModule(moduleId,function(){openModule('/order/jsp/quality/qualityOrderList.jsp',{loginLevel:loginLevel},{},{width:'70%'},'qulityOrderList')});
	}
	//查询售后审核信息
	function queryAfterSalesApprove(num,pageCount){
		var custName = $("#userNameOrder_approve").val();
		var custMobile = $("#userMobileOrder_approve").val();
		var auditFlag = $("#orderStatus_approve").val();
		var afterSalesKind = $("#serverType_approve").val();
		var creStart = $("#creStart_approve").val();
		var creEnd = $("#creEnd_approve").val();
		var afterSaleIdSearch = $("#afterSaleIdSearch_approve").val();
		var fkCode = $("#afterSales_fkcode").val();//关联编号
		//var orderCodeSearch = $("#orderCodeSearch_approve").val();
		//var managerType = $("#managerTypeQualityList").val();
		$.ajax({
			url: ctx+"/afterSales/queryAfterSalesApprove?curPage="+num+"&pageCount="+pageCount,
			data:{
				custName:custName,
				custMobile:custMobile,
				auditFlag:auditFlag,
				afterSalesKind:afterSalesKind,
				creStart:creStart,
				creEnd:creEnd,
				id:afterSaleIdSearch,
				orderCode:fkCode,
				fkCode:fkCode
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html="";
				$("#pageQualityDiv_approve").pagination(data.page,listBodyQualityApprove);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				
				if (data.msg == "00" && data.flag == 0) {
					$("#userLoginLevel_approve").val(data.loginLevel);
					var num = $.each(data.list,function(i,v){
						console.log(v.auditFlag);
						    var auditStr =  auditFlagStr(v.auditFlag,v.remark,v.accountRemark);
							if(i == 0){
								if(v.orderType == 1){
									showQualitySolutionDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
								}else if(v.orderType == 2){
									showQualityDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
								}else{
									showCardDetail(v.orderId);
								}
							}
							num=i+1;
							if(v.orderType == 1){
								html +="<tr onclick='showQualitySolutionDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+")'>" ;
							}else if(v.orderType == 2){
								html +="<tr onclick='showQualityDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+")'>" ;
							}else{
								html +="<tr onclick='showCardDetail("+v.orderId+")'>" ;
							}
							html +="<td><input name='approveRadio' type='radio' data-approveBy='"+(v.approveBy||"")+"' value='" +v.id +"-"+v.orderId +"-"+v.auditFlag +"-"+v.order.cateType+"-"+v.afterSalesKind+"-"+v.accountPayId+"-"+v.vphAccountId+"'/></td>";
							html +="<td>"+(total + num - pageCount);
							html +="</td><td>"+v.id;
							if(v.auditFlag == "20130009" || v.auditFlag == "20130005"|| v.auditFlag == "20130002" || v.auditFlag == "20130012" || v.auditFlag == "20130015"){//退款失败，状态显示红色
								html +="</td><td align='left' style='color:red;'>"+auditStr;
							}else{
								html +="</td><td align='left' style='color:green;'>"+auditStr;
							}
							html +="</td><td>"+vphBackArr[v.vphBackStatus];
							html +="</td><td>"+vphCancleArr[v.vphCancleStatus];
							html +="</td><td>"+(v.fkCode||"");
							html +="</td><td>"+v.custName;
							/*if(managerType != null && managerType==2){
								html += "";
							}else{
								html +="</td><td>"+v.custMobile;
							}*/
							html +="</td><td>"+afterSalesKindArr[v.afterSalesKind];
							html +="</td><td>"+(v.approveByText||"");
							html +="</td><td>"+(v.approveDeptText||"");
							html +="</td><td>"+v.createByText;
							html +="</td><td>"+v.createDeptText;
							html +="</td><td>"+numberToDatestr(v.createTime,8);
							html +="</td></tr>";
					})
					$("#accountMakeSureBtn").hide();
					$("#makeSureBtn").show();
				}else if(data.msg == "00" && data.flag == 1){
					var num = $.each(data.list,function(i,v){
						console.log(v.bathno);
					    var auditStr =  auditFlagStr(v.auditFlag,v.remark,v.accountRemark);
						if(i == 0){
							if(v.orderType == 1){
								showQualitySolutionDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag);
							}else if(v.orderType == 2){
								showQualityDetail(v.id,v.orderId,v.afterSalesKind,v.auditFlag,v.bathno);
							}else{
								showCardDetail(v.orderId);
							}
						}
						num=i+1;
						if(v.orderType == 1){
							html +="<tr onclick='showQualitySolutionDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+")'>" ;
						}else if(v.orderType == 2){
							html +="<tr onclick='showQualityDetail(" +v.id +","+v.orderId+","+v.afterSalesKind+","+v.auditFlag+","+ v.bathno+")'>" ;
						}else{
							html +="<tr onclick='showCardDetail(" + v.orderId + ")'>" ;
						}
						html +="<td><input name='approveRadio' type='radio' value='" +v.id +"-"+v.orderId +"-"+v.auditFlag +"-"+v.order.cateType+"-"+v.afterSalesKind+"-"+v.accountPayId+"-"+v.vphAccountId+"'/></td>";
						html +="<td>"+(total + num - pageCount);
						html +="</td><td>"+v.id;
						if(v.auditFlag == "20130009" || v.auditFlag == "20130005"|| v.auditFlag == "20130002" ||v.auditFlag == "20130012"){//退款失败，状态显示红色
							html +="</td><td align='left' style='color:red;'>"+auditStr;
						}else{
							html +="</td><td align='left' style='color:green;'>"+auditStr;
						}
						html +="</td><td>"+vphBackArr[v.vphBackStatus];
						html +="</td><td>"+vphCancleArr[v.vphCancleStatus];
						html +="</td><td>"+v.orderCode;
						html +="</td><td>"+v.custName;
						/*if(managerType != null && managerType==2){
							html += "";
						}else{
							html +="</td><td>"+v.custMobile;
						}*/
						html +="</td><td>"+afterSalesKindArr[v.afterSalesKind];
						html +="</td><td>"+(v.approveByText||"");
						html +="</td><td>"+(v.approveDeptText||"");
						html +="</td><td>"+v.createByText;
						html +="</td><td>"+v.createDeptText;
						html +="</td><td>"+numberToDatestr(v.createTime,8);
						html +="</td></tr><tr></tr>";
				})
				$("#makeSureBtn").hide();
			    $("#accountMakeSureBtn").show();
				}else if(data.msg == "02"){
						html +="<tr><td colspan='13'><p class='table-empty'>暂无数据</p></td></tr>";
						$("#pageQualityDiv_approve").hide();
						$("#tableDetail").hide();
						$("#tableSolutionDetail").hide();
						$("#tableCardDetail").hide();
						$("#tableBuyBackDetail").hide();
						$("#tableNoData").show();
						$("#tableNoData").html("<table class='table table-condensed'><tr><td colspan='2'><p class='table-empty'>暂无数据</p></td></tr></table>");
						resetSolutionDetail();
						resetAllOrderDetail();
						//cleanTableCardDetail();
						$("#accountMakeSureBtn").hide();
						$("#makeSureBtn").show();
				}
				$("#listBodyQualityApprove").html(html);
				//设置默认行背景色
				$("#listBodyQualityApprove tr").each(function(n) {
					if(n==0){
						$(this).find("input[name='approveRadio']").attr("checked", "checked");
						$(this).toggleClass('info').siblings().removeClass('info').css('cursor', 'pointer'); return;
					}
				});
			}
		});
		/*初始化所有弹出提示框 */
		$('[data-toggle="popover"]').popover();
		/*表格单选*/
		radioColor("#listBodyQualityApprove > tr");
		/*表格点击行高亮*/
		trColor("#native_tbody > tr");
	}
	var messageConfig = {1:"有效",2:"无效",3:"无效"};
	//确认有效、无效按钮
	function makeSureBtn(flag){
		var message = "确认"+messageConfig[flag]+"此售后单？";
		var flagTips = "该售后单已确认"+messageConfig[flag]+"！";
		var approveRadio = $("input[name='approveRadio']:checked");
		var approveBy = approveRadio.attr("data-approveBy");//当前售后单审核人id
		var manager = JSON.parse($.cookie('manager'));//当前登录人id
		if(manager.id != approveBy){
			$.alert({top:'30%',text:"您非当前售后单审核人,不能操作审核"});
			return;
		}
		var approveInfo = approveRadio.val();
		var approveArr = approveInfo.split("-");
		var afterSalesId = approveArr[0];  //售后单id
		var orderId = approveArr[1];	   //订单id
		var auditFlag = approveArr[2];	 //审核状态
		var afterSalesKind = approveArr[4];  //售后类型
		var accountPayId = approveArr[5];//银行卡结算单id
		var vphAccountId = approveArr[6];//唯品会结算单id
		if(auditFlag == "20130001"){//待确认状态可以改
			if(flag == 1){//确认有效
				$.confirm({text:message,callback:function(y){
					if(y){
						$.ajax({
							url: ctx+"/afterSales/updateAfterSales",
							data:{
								id:afterSalesId,
								auditFlag:"20130003",   //审核中
								orderId:orderId,
								afterSalesKind:afterSalesKind,
								accountPayId:accountPayId,
								vphAccountId:vphAccountId
							},
							type:"post",
							dataType:"json",
							async:false,
							success:function(data){
								if (data.msg == "00") {
									$.alert({millis:2000,top:'30%',text:"确认成功！"});
									queryAfterSalesApprove(0,10);
								}else{
									$.alert({millis:2000,top:'30%',text:"确认失败！"});
								}
							}
						});
					}
				}
				})
			}else{//确认无效
				openModule('/order/jsp/quality/qualityApproveReason.jsp',{afterSalesId:afterSalesId,orderId:orderId,accountPayId:accountPayId,vphAccountId:vphAccountId},{},{width:'40%'},'qualityApproveReason');
			}
		}else{
			$.alert({millis:3000,top:'30%',text:"请选择待确认的售后单！"});
		}
	}

	//重置售后审核查询信息选项
	function resetSalesApprove(){
		$("#userNameOrder_approve").val("");
		$("#userMobileOrder_approve").val("");
		$("#orderStatus_approve").val("");
		$("#serverType_approve").val("");
		$("#creStart_approve").val("");
		$("#creEnd_approve").val("");
		$("#afterSaleIdSearch_approve").val("");
		$("#orderCodeSearch_approve").val("");
		$("#afterSales_fkcode").val("");
		queryAfterSalesApprove(0,10);
		$("#pageQualityDiv_approve").show();
	}
	
	//迁单按钮
	function changeOrderSales(){
		openModule('/order/jsp/quality/changeOrderSale.jsp',{},{},{width:'50%'},'changeOrderSale');
	}
	
	/**
	 * 查询订单是不是延续和非月嫂
	 * @param orderId
	 * @returns
	 */
	function queryOrder(orderId){
		var flag=0;
		$.ajax({
			url: ctx+"/afterSales/queryOrder",
			data:{
				orderId:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					flag=data.flag;
					$.alert({millis:2000,top:'30%',text:"确认成功！"});
				}else{
					$.alert({millis:2000,top:'30%',text:"确认失败！"});
				}
			}
		});
		return flag;
	}
	
	
	var messageConfig1 = {1:"有效",2:"无效"};
	//确认有效、无效按钮
	function accountMakeSureBtn(flag){
		var message = "确认"+messageConfig1[flag]+"此售后单？";
		var flagTips = "该售后单已确认"+messageConfig1[flag]+"！";
		var approveInfo = $("input[name='approveRadio']:checked").val();
		var approveArr = approveInfo.split("-");
		var afterSalesId = approveArr[0];  //售后单id
		var orderId = approveArr[1];	   //订单id
		var auditFlag = approveArr[2];	 //审核状态
		var afterSalesKind = approveArr[4];  //售后类型
		var accountPayId = approveArr[5];//银行卡结算单id
		var vphAccountId = approveArr[6];//唯品会结算单id
		if(auditFlag == "20130011"){//结算中心审核中状态可以改
			if(flag == 1){//确认有效
				$.confirm({text:message,callback:function(y){
					if(y){
						$.ajax({
							url: ctx+"/afterSales/updateAfterSales",
							data:{
								id:afterSalesId,
								auditFlag:"20130011", 
								orderId:orderId,
								afterSalesKind:afterSalesKind,
								accountPayId:accountPayId,
								vphAccountId:vphAccountId
							},
							type:"post",
							dataType:"json",
							async:false,
							success:function(data){
								if (data.msg == "00") {
									$.alert({millis:2000,top:'30%',text:"确认成功！"});
									queryAfterSalesApprove(0,10);
								}else{
									$.alert({millis:2000,top:'30%',text:"确认失败！"});
								}
							}
						});
					}
				}
				})
			}else{//确认无效
				openModule('/order/jsp/quality/qualityApproveReason.jsp',{afterSalesId:afterSalesId,orderId:orderId,accountPayId:accountPayId,vphAccountId:vphAccountId,afterSalesKind:afterSalesKind,flag:3},{},{width:'40%'},'qualityApproveReason');
			}
		}else{
			$.alert({millis:3000,top:'30%',text:"请选择待确认的售后单！"});
		}
	}

//显示分期回购详情信息
function showBuyBackDetail(orderId,bathno) {
	$.ajax({
		url: ctx+"/afterSalesNew/findBuyBackAfterSales/"+orderId+"/"+bathno,
		type:"get",
		dataType:"json",
		async:false,
		success:function(data){
			if (data.msg == "00"){
				var orderAfterSales = data.orderAfterSales;
				$("#sc_order_code").html(orderAfterSales.orderCode);
				//$("#sc_salary_amount").html(orderAfterSales.serviceLabourFee);
				//$("#sc_salary_number").html(orderAfterSales.bathno);
				//$("#sc_custName").html(orderAfterSales.realName);
				//$("#sc_custMobile").html(orderAfterSales.mobile);
			}
		}
	});

	//清除卡售后详情信息
	function cleanTableCardDetail(){
		$("#card_number").html("");//卡编号
		$("#card_status").html("");//卡状态
		$("#card_amount").html("");//卡面值
		$("#card_balance").html("");//卡余额
		$("#card_source").html("");//购卡来源
		$("#card_recommendName").html("");//推卡人
		$("#card_give").html("");//卡提成
		$("#card_accountBalance").html("");//管家账户余额(退卡后)
		$("#card_custName").html("");//客户姓名
		$("#card_custMobile").html("");//联系方式
		$("#card_auditFlag").html("");//售后状态
		$("#card_salesKind").html("");//售后类型
		$("#card_total").html("");//退费总额
		$("#card_reason").html("");//原因
	}
}

//订单回访信息详情
function showCallBackList(afterSalesId,orderId){
	$.ajax({
		url:ctx + "/callBack/findCallBackList",
		data:{
			afterSalesId:afterSalesId,
			orderId:orderId
		},
		type:"post",
		async:false,
		success:function(data){
			var orderAfterSales = data.orderAfterSales;
			if(orderAfterSales != null){
				var bankUserName = orderAfterSales.bankUserName;
				var bankName = orderAfterSales.bankName;
				var bankCard = orderAfterSales.bankCard;
				if(bankUserName != null && bankUserName.length > 0){
					$("#callBack_bankUserName").html(orderAfterSales.bankUserName);
				}
				if(bankName != null && bankName.length > 0){
					$("#callBack_bankName").html(orderAfterSales.bankName);
				}
				if(bankCard != null && bankCard.length > 0){
					$("#callBack_bankCard").html(orderAfterSales.bankCard);
				}
			}else{
				$("#callBack_bankUserName").html("暂无信息");
				$("#callBack_bankName").html("暂无信息");
				$("#callBack_bankCard").html("暂无信息");
			}
			var html = "";
			var htmlHistory = "";
			if(data.msg == "00"){
				$.each(data.afterCallBacks,function(i,n){
					if(n.isOld == 1){//判断是否历史回访
						htmlHistory+="<tr><td colspan='4'><strong>回访时间:</strong><lable><strong>"+ n.createTime+"</strong></lable></td></tr>";
						htmlHistory+="<tr><td colspan='2'>回访人员:<lable>"+ n.userName+"</lable></td></tr>";
						var status = n.status;
						if(status == 1){//1:回访成功,2:回访失败,3:需再次回访
							status = "回访成功";
						}else if(status == 2){
							status = "回访失败";
						}else{
							status = "需再次回访";
						}
						htmlHistory+="<tr><td colspan='2'>回访结果:<lable>"+ status+"</lable></td>";
						if(n.status == 2){
							var reason = n.reason;
							if(reason == 1){
								reason = "银行信息错误";
							}else if(reason == 2){
								reason = "电话号错误";
							}else if(reason == 3){
								reason = "回访超过三次无人接听";
							}else{
								reason = "";
							}
							htmlHistory+="<td colspan='2'>失败原因:<lable>"+ reason+"</lable></td></tr>";
						}else{
							htmlHistory+="<td colspan='2'>下次回访时间:<lable>"+ n.nextTime+"</lable></td></tr>";
						}
						htmlHistory+="<tr><td colspan='4'>备注:<lable>"+ n.remark+"</lable></td></tr>";
					}else{
						html+="<tr><td colspan='4'><input type='radio' name='ckeckedCallBack' value='"+ n.id+"'/><strong>回访时间:</strong><lable><strong>"+ n.createTime+"</strong></lable></td></tr>";
						html+="<tr><td colspan='2'>回访人员:<lable>"+ n.userName+"</lable></td></tr>";
						var status = n.status;
						if(status == 1){//1:回访成功,2:回访失败,3:需再次回访
							status = "回访成功";
						}else if(status == 2){
							status = "回访失败";
						}else{
							status = "需再次回访";
						}
						html+="<tr><td colspan='2'>回访结果:<lable>"+ status+"</lable></td>";
						if(n.status == 2){
							var reason = n.reason;
							if(reason == 1){
								reason = "银行信息错误";
							}else if(reason == 2){
								reason = "电话号错误";
							}else if(reason == 3){
								reason = "回访超过三次无人接听";
							}else{
								reason = "";
							}
							html+="<td colspan='2'>失败原因:<lable>"+ reason+"</lable></td></tr>";
						}else{
							html+="<td colspan='2'>下次回访时间:<lable>"+ n.nextTime+"</lable></td></tr>";
						}
						html+="<tr><td colspan='4'>备注:<lable>"+ n.remark+"</lable></td></tr>";
					}
				});
			}
			if(html == ""){
				html += "<tr><td colspan='4'><lable>暂无信息</lable></td></tr>";
			}
			if(htmlHistory == ""){
				htmlHistory += "<tr><td colspan='4'><lable>暂无信息</lable></td></tr>";
			}
			$("#callBackListTable").html(html);
			$("#callBackListTableHistory").html(htmlHistory);
		}
	});
};
