/**
 * 售后单服务类添加js
 * @author wn
 */
 var ctx=$("#ctx").val();
 
 function getQualityServer(orderId,cateType){
		//单次服务与延续性服务展示
		if(cateType == 1 || cateType == 4){
			$("#danciTitle").show();
			$("#qualityDCServerForm").show();
			$("#yanxvTitle").hide();
			$("#qualityYXServerForm").hide();
			//加载省份
			setSelProvinceCitys('101',6,'dcServer_bankProvinceCode');
			//查询审核部门
			queryApproveDeptName("dcApproveDeptSelect");
			//查询订单信息与客户信息（单次）
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
							 $("#dcServerUserName").html(v.order.userName);
							 $("#dcServerMobile").html(v.order.userMobile);
							 //$("#dcServerReceiptName").html(v.order.receiverName);
							 $("#dcServerReceiptMobile").html(v.order.receiverMobile);
							 $("#dcServerReceiptAddress").html(v.order.receiverAddress);
							 $("#dcServerOrderCode").html(v.orderCode);
							 $("#dcServerOrderType").html(v.typeText);
							 $("#dcServerStartTime").html(numberToDatestr(v.startTime,8));
							 $("#dcServerEndTime").html(numberToDatestr(v.endTime,8));
							 $("#dcServerReceiverAddress").html(v.address);
							 $("#dcServerOrderStatus").html(orderStatus[v.order.orderStatus]);
							 $("#dcServerOrderChannel").html(v.channelText);
							 $("#dcServerOrderSource").html(v.sourceText);
							 $("#dcServerRemark").html(v.order.remark);
							 //$("#dcServerNowPrice").val(v.nowPrice);
							 $("#serverOrderStatus").val(v.order.orderStatus);
							 $("#serverPayStatus").val(v.order.payStatus);
							 $("#dcServerParentId").val(v.order.parentId);
							 $("#dcServerCateType").val(cateType);
							 $("#dcServerStartTimeHide").val(v.startTime);
							 if(v.order.payStatus == "20110001"){
								 $("#dcServer_bank").hide();
								 $("#dcServer_bankCity").hide();
								 $("#dcServer_bankCN").hide();
								 $("#dcServer_bankUn").hide();
							 }
						});
					}
				}
			});
		}else if(cateType == 2){
			$("#danciTitle").hide();//隐藏单次标题
			$("#qualityDCServerForm").hide();//隐藏单次表单
			$("#yanxvTitle").show();//隐藏延续标题
			$("#qualityYXServerForm").show();//隐藏延续表单
			//查询合同中的客户信息费
			queryAgreementMoney(orderId);
			//查询审核部门
			queryApproveDeptName("yxApproveDeptSelect");
			//将延续服务的终止月费用生成结算单
			//计算此订单号的所有结算单费用，并展示到文本框中
			//退款总额框，以管家填写的金额为准
			//获取当前时间
			$("#currentTime").val((new Date()).format("yyyy-MM-dd hh:mm:ss"));
			//加载省份
			setSelProvinceCitys('101',6,'yxServer_bankProvinceCode');
			//查询订单信息与客户信息（延续）
			var orderPay = 0;//订单余额
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
							 $("#yxServerUserName").html(v.order.userName);
							 $("#yxServerMobile").html(v.order.userMobile);
							 //$("#yxServerReceiptName").html(v.order.receiverName);
							 $("#yxServerReceiptMobile").html(v.order.receiverMobile);
							 $("#yxServerReceiptAddress").html(v.order.receiverAddress);
							 $("#yxServerOrderCode").html(v.orderCode);
							 $("#yxServerOrderType").html(v.typeText);
							 $("#yxServerStartTime").html(numberToDatestr(v.startTime,8));
							 $("#yxServerEndTime").html(numberToDatestr(v.endTime,8));
							 $("#yxServerReceiverAddress").html(v.address);
							 $("#yxServerOrderStatus").html(orderStatus[v.order.orderStatus]);
							 $("#yxServerOrderChannel").html(v.channelText);
							 $("#yxServerOrderSource").html(v.sourceText);
							 $("#yxServerRemark").html(v.order.remark);
							 $("#serverNowPrice").val(v.nowPrice);
							 $("#serverOrderStatus").val(v.order.orderStatus);
							 $("#serverPayStatus").val(v.order.payStatus);
							 orderPay = intToDecimal(v.order.totalPay)*1;
							 $("#serverOrderTotalPay").val(orderPay);  //订单总额
							 $("#yxServerParentId").val(v.order.parentId);//当前订单父解决方案订单id
							 $("#refundDiv").hide();
							 $("#refundMembershipFeeDiv").hide();
							 $("#refundSalaryFeeDiv").hide();
							 $("#refundDetailDiv").hide();
							 $("#refundDetailLine").hide();
							 //$("#yxServerMoneyDetail").hide();  //最大退款金额
							 $("#yxWarn").hide();  //退款金额错误提示
							 //if(v.order.payStatus == "20110001"){
								 $("#yxServer_bank").hide();
								 $("#yxServer_bankCity").hide();
								 $("#yxServer_bankCN").hide();
							 //}
							 //管家帮分期隐藏
//							 $("#refundVipShopFeeDiv").hide();
						});
					}
				}
			});
		}
		
		//退款总额
		queryAccountTotal(orderId,cateType,orderPay);
		afterSalesKindChange();//加载选择售后类型,计算退费相关金额
	}
	//单次和延续服务的退款总额展示
	function queryAccountTotal(orderId,cateType,orderPay){
		var serverPayStatus = $("#serverPayStatus").val();//当前支付状态
		$.ajax({
			url: ctx+"/payfee/queryAccount",
			data:{
				orderId:orderId,
				isBackType:2,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var serveraliPayRefundTotal = 0;
				var serverbankRefundTotal = 0;
				var serverdhRefundTotal = 0;
				var serverticketRefundTotal = 0;
				var serverposRefundTotal = 0;
				var servertaobaofundTotal = 0;
				var serverweixinRefundTotal = 0;
				var serverrhPosRefundTotal = 0;
				var serverccbcRefundTotal = 0;
				var servercardRefundTotal = 0;
				var servertyqRefundTotal = 0;
				var serverremainRefundTotal = 0;
				var serverotherRefundTotal = 0;
				var serverweixinSearchRefundTotal = 0;
				var serverbaitiaoRefundTotal = 0;
				var serverjialianPosweixinRefundTotal = 0;
				var serverjialianPosalipayRefundTotal = 0;
				var serverjialianPoscardRefundTotal = 0;
				var serverjialianweixinRefundTotal = 0;
				var serverjialianalipayRefundTotal = 0;
				var serverjialiancardRefundTotal = 0;
				var serveryiwangtongRefundTotal = 0;
				var serverdaoweiRefundTotal = 0;
				var serverweizhishuRefundTotal = 0;
				var serverwangcaiRefundTotal = 0;
				var servervipshopRefundTotal = 0;
				var html = "";
				var fSum = 0;
				var prepareMoney = 0;
				if(data.msg=='00'){
					if(cateType == 1 || cateType == 4){
						//单次服务，直接展示
						var dcAccountPayId = [];//结算单id,防止重复累加缴费
						$.each(data.list,function(j,w){
							if(dcAccountPayId.contains(w.id)){
								//防止重复累加缴费
								return true;
							}
							dcAccountPayId.push(w.id);
							if(w.payStatus == "20110002" || w.payStatus == "20110003"){
								//缴费展示
								$.ajax({
									url: ctx+"/payfee/queryPayfee",
									data:{
										orderId:w.id,
										isBackType:1,
										valid:1
									},
									type:"post",
									dataType:"json",
									async:false,
									success:function(data){
										if(data.msg=='00'){
									    	$.each(data.list,function(l,y){
									    		if(y.payStatus == "20110002" ){
												    fSum += y.feeSum * 1;
												 }
									    		if(y.feePost == "20250001"){//支付宝
									    			serveraliPayRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250002"){//银行卡
													serverbankRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250003"){//电汇
													serverdhRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250004"){//支票
													serverticketRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250005"){//pos机
													serverposRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250006"){//淘宝支付
													servertaobaofundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250007"){//微信退费
													serverweixinRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250008"){//融汇天下POS
													serverrhPosRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250009"){//建行
													serverccbcRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250010"){//卡支付
													servercardRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250011"){//体验券
													servertyqRefundTotal += intToDecimal(y.feeSum)*1;
												}
												if(y.feePost == "20250013"){//账户
													serverremainRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250014"){//他人代收（三方订单）
													serverotherRefundTotal += intToDecimal(y.feeSum) * 1;
													//serverotherRefundTotal += intToDecimal(0) * 1;
												}
												if(y.feePost == "20250015"){//微信扫码支付
													serverweixinSearchRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250016"){//白条支付
													serverbaitiaoRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250017"){//嘉联pos微信支付
													serverjialianPosweixinRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250018"){//嘉联pos支付宝支付
													serverjialianPosalipayRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250019"){//嘉联pos刷卡支付
													serverjialianPoscardRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250020"){//嘉联微信支付
													serverjialianweixinRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250021"){//嘉联支付宝支付
													serverjialianalipayRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250022"){//嘉联快捷支付
													serverjialiancardRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250023"){//招商银行一网通支付
													serveryiwangtongRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250024"){//支付宝到位
													serverdaoweiRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250025"){//微指数余额
													serverweizhishuRefundTotal += intToDecimal(y.feeSum) * 1;
												}
												if(y.feePost == "20250026"){//旺财支付
													serverwangcaiRefundTotal += intToDecimal(y.feeSum) * 1;
												}
										  });
									  }
									}
								});
							}
						});
								if(serveraliPayRefundTotal != "0" ){
									html +="<label>支付宝退费：<span>"+intToDecimal(serveraliPayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverbankRefundTotal != "0"){
									html +="<label>银行打卡退费合计：<span>"+intToDecimal(serverbankRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverdhRefundTotal != "0"){
									html +="<label>电汇退费：<span>"+intToDecimal(serverdhRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverticketRefundTotal != "0"){
									html +="<label>支票退费：<span>"+intToDecimal(serverticketRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverposRefundTotal != "0"){
									html +="<label>POS机退费：<span>"+intToDecimal(serverposRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(servertaobaofundTotal != "0"){
									html +="<label>淘宝退费：<span>"+intToDecimal(servertaobaofundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverweixinRefundTotal != "0"){
									html +="<label>微信退费：<span>"+intToDecimal(serverweixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverrhPosRefundTotal != "0"){
									html +="<label>融汇天下POS退费：<span>"+intToDecimal(serverrhPosRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverccbcRefundTotal != "0"){
									html +="<label>建行退费：<span>"+intToDecimal(serverccbcRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(servercardRefundTotal != "0"){
									html +="<label>卡退费：<span>"+intToDecimal(servercardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(servertyqRefundTotal != "0"){
									html +="<label>体验券(不退)：<span>"+intToDecimal(servertyqRefundTotal)+"</span>&nbsp;&nbsp;</label>";
									//如果退款有券缴费，退款总额-券缴费（不退）
									fSum = fSum - servertyqRefundTotal;
								}
								if(serverremainRefundTotal != "0"){
									html +="<label>账户退费：<span>"+intToDecimal(serverremainRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverotherRefundTotal != "0"){
									html +="<label>他人代收退费(不退)：<span>"+intToDecimal(serverotherRefundTotal)+"</span>&nbsp;&nbsp;</label>";
									fSum = fSum - serverotherRefundTotal;
								}
								if(serverweixinSearchRefundTotal != "0"){
									html +="<label>微信扫码支付退费：<span>"+intToDecimal(serverweixinSearchRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverbaitiaoRefundTotal != "0"){
									html +="<label>白条退费：<span>"+intToDecimal(serverbaitiaoRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialianPosweixinRefundTotal != "0"){
									html +="<label>嘉联pos微信支付退费：<span>"+intToDecimal(serverjialianPosweixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialianPosalipayRefundTotal != "0"){
									html +="<label>嘉联pos支付宝支付退费：<span>"+intToDecimal(serverjialianPosalipayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialianPoscardRefundTotal != "0"){
									html +="<label>嘉联pos刷卡支付退费：<span>"+intToDecimal(serverjialianPoscardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialianweixinRefundTotal != "0"){
									html +="<label>嘉联微信支付退费：<span>"+intToDecimal(serverjialianweixinRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialianalipayRefundTotal != "0"){
									html +="<label>嘉联支付宝支付退费：<span>"+intToDecimal(serverjialianalipayRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverjialiancardRefundTotal != "0"){
									html +="<label>嘉联快捷支付退费：<span>"+intToDecimal(serverjialiancardRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serveryiwangtongRefundTotal != "0"){
									html +="<label>招商银行一网通支付退费：<span>"+intToDecimal(serveryiwangtongRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverdaoweiRefundTotal != "0"){
									html +="<label>支付宝到位退费：<span>"+intToDecimal(serverdaoweiRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverweizhishuRefundTotal != "0"){
									html +="<label>微指数余额退费：<span>"+intToDecimal(serverweizhishuRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serverwangcaiRefundTotal != "0"){
									html +="<label>旺财支付退费：<span>"+intToDecimal(serverwangcaiRefundTotal)+"</span>&nbsp;&nbsp;</label>";
								}
								if(serveraliPayRefundTotal == "0" && serverbankRefundTotal == "0" && serverdhRefundTotal == "0" && serverticketRefundTotal == "0"
									  &&  serverposRefundTotal == "0" && servertaobaofundTotal == "0" && serverweixinRefundTotal == "0" && serverrhPosRefundTotal == "0"
									  &&  serverccbcRefundTotal == "0" && servercardRefundTotal == "0" && servertyqRefundTotal == "0" && serverremainRefundTotal == "0"
									  &&  serverotherRefundTotal == "0" && serverweixinSearchRefundTotal == "0" && serverbaitiaoRefundTotal == "0"&& serverjialianPosweixinRefundTotal == "0"
									  &&  serverjialianPosalipayRefundTotal == "0"&& serverjialianPoscardRefundTotal == "0"&& serverjialianweixinRefundTotal == "0"&& serverjialianalipayRefundTotal == "0"
									  &&  serverjialiancardRefundTotal == "0"&& serveryiwangtongRefundTotal == "0"&& serverdaoweiRefundTotal == "0"&& serverweizhishuRefundTotal == "0"
									  &&  serverwangcaiRefundTotal == "0"){
										html += "&nbsp;&nbsp;无";
									}
								  //退款明细
								  var serverTotal = serverbankRefundTotal + serveraliPayRefundTotal+serverposRefundTotal + servertaobaofundTotal
								  						+ serverweixinRefundTotal + serverrhPosRefundTotal + serverccbcRefundTotal + serverotherRefundTotal
								  						+ serverweixinSearchRefundTotal +serverjialianPosweixinRefundTotal
														+  serverjialianPosalipayRefundTotal + serverjialianPoscardRefundTotal + serverjialianweixinRefundTotal + serverjialianalipayRefundTotal 
														+ serverjialiancardRefundTotal + serveryiwangtongRefundTotal + serverdaoweiRefundTotal ;
								  var parentId = $("#dcServerParentId").val();//订单父订单id
								  if(parentId != null && parentId != "" && parentId != 0){
										var type = queryTypeByParentId(parentId);
										if(type == 20120008){
											//是赠送解决方案,可退金额为0
											fSum = 0;
											serverTotal = 0;
											html = "无";
										}
								   }
								  $("#dcServerReturnTotal").text(intToDecimal(fSum));
								  $("#dcServerBankCardMoney").val(intToDecimal(serverTotal));//银行卡费用放入隐藏域
								  $("#dcReturnTotalDetail").html(html);
									if(fSum <= 0){
										 $("#dcServer_bank").hide();
										 $("#dcServer_bankCity").hide();
										 $("#dcServer_bankCN").hide();
										 $("#dcServer_bankUn").hide();
										 $("#dcqualityApproveDiv").hide();
									}else if(servercardRefundTotal != "" && serverbankRefundTotal == "0"){
										 $("#dcServer_bank").hide();
										 $("#dcServer_bankCity").hide();
										 $("#dcServer_bankCN").hide();
										 $("#dcServer_bankUn").hide();
										 $("#dcqualityApproveDiv").hide();
									}else if(servertyqRefundTotal != "" && serverbankRefundTotal == "0"){
										 $("#dcServer_bank").hide();
										 $("#dcServer_bankCity").hide();
										 $("#dcServer_bankCN").hide();
										 $("#dcServer_bankUn").hide();
										 $("#dcqualityApproveDiv").hide();
									}else if(serverremainRefundTotal != "" && serverbankRefundTotal == "0"){
										 $("#dcServer_bank").hide();
										 $("#dcServer_bankCity").hide();
										 $("#dcServer_bankCN").hide();
										 $("#dcServer_bankUn").hide();
										 $("#dcqualityApproveDiv").hide();
									}
					}else if(cateType == 2){
						//已支付结算单
						var feeTotal = 0;
						var yxFqBtSaleMoney = 0;//分期白条类缴费已发服务人员服务费合计
						var yxAccountPayId = [];//结算单id,防止重复累加缴费
						$.each(data.list,function(l,y){ 
							if(yxAccountPayId.contains(y.id)){
								//防止重复累加缴费
								return true;
							}
							yxAccountPayId.push(y.id);
							if(y.payStatus == "20110002" || y.payStatus == "20110003" ){//已支付
								if(y.feeType == 4){
									feeTotal += y.feeSum * 1;
								}
								$.ajax({
									url: ctx+"/payfee/queryPayfee",
									data:{
										orderId:y.id,
										isBackType:1,
										valid:1
									},
									type:"post",
									dataType:"json",
									async:false,
									success:function(data){
										if(data.msg=='00'){
											$("#yxServer_afterSalesKind").find("option[value='10']").show() ;
											//分期白条类型(20250016,20250027,20250031,20250033)
											//结算单类型(3:预收、5:信息费预收、7:押金转收入)
											$.each(data.list,function(k,x){
												if(x.payStatus == "20110002" ){//已支付 总预收收入（非管家帮分期）
													if(y.feeType <= 3 || y.feeType == 5 || y.feeType == 7){
														var fqbtFeePost = [20250016,20250027,20250031,20250033];
														if(!fqbtFeePost.contains(x.feePost)){
															prepareMoney += x.feeSum * 1;
														}
														//分期白条预收使用金额
														if(fqbtFeePost.contains(x.feePost)){
															var salaryFee = queryPayfeeDetail(x.id);
															yxFqBtSaleMoney += salaryFee * 1;
															/*servervipshopRefundTotal += intToDecimal(x.feeSum) * 1;
															$("#yxHasVipshopFee").val(1);*/
														}
													}
												}
											});
									  }else{
										  $("#yxServer_afterSalesKind").find("option[value='10']").hide() ;
									  }
									}
								});
							}else{
								$("#yxServerRefund").html("0.00");
								  //退款明细
								$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>");
								$("#yxApproveSelectDiv").hide();
							}
						});
						    feeTotal = feeTotal - yxFqBtSaleMoney;//已使用金额去除分期白条类缴费使用部份
							$("#yxPrePareMoney").val(prepareMoney);//预存缴费,放入隐藏域
							$("#yxSaleMoney").val(feeTotal);//已消费金额,放入隐藏域
							//管家帮分期缴费放入隐藏域
							//$("#yxVipShopMoneyHidden").val(servervipshopRefundTotal);
							//退款总额
							var yxRefundMoney = intToDecimal(prepareMoney) - intToDecimal(feeTotal);
							if(yxRefundMoney <= "0.00" || yxRefundMoney <= "0" ){
								$("#yxServerRefund").html("0.00");
								$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>");
								$("#yxApproveSelectDiv").hide();
								$("#yxServer_bank").hide();
								$("#yxServer_bankCity").hide();
								$("#yxServer_bankCN").hide();
							}
							if(feeTotal <= 0 ){
								 $("#yxServer_bank").hide();
								 $("#yxServer_bankCity").hide();
								 $("#yxServer_bankCN").hide();
							}
					}
				}else{
					if(cateType == 1 || cateType == 4){
						$("#dcServerReturnTotal").text("0.00");
						$("#dcReturnTotalDetail").html("&nbsp;&nbsp;无");
					}else{
						$("#yxServerRefund").html("0.00");
						$("#yxServer_afterSalesKind").find("option[value='10']").hide() ;
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>");
					}
				}
			}
		});
	}
	//查询合同信息费
	function queryAgreementMoney(orderId){
		$.ajax({
			url: ctx+"/agreement/queryAgreement",
			data:{
				orderId:orderId,
				agreementStateEffect:1,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg == "00"){
					$.each(data.list,function(j,w){ 
						$("#yxcustomerManageMoney").val(w.customerManageMoney);//客户信息费隐藏框
						if(w.customerManageMoney != null && (w.customerManageMoney == 0 || w.customerManageMoney == "0.00")){
							$("#yxMembershipFee").val(w.customerManageMoney);//应退信息费框默认值
						}
					});
				}else{
					$("#yxcustomerManageMoney").val(0);
					$("#yxMembershipFee").val(0);
//					$("#yxMembershipFee").attr("readonly","readonly");
				}
			}
		});
	}

	function afterSalesKindChange(){
		//延续服务售后类型，判断是否可以新增售后
		$("#yxServer_afterSalesKind").change(function(){
			var yxserverOrderStatus = $("#serverOrderStatus").val();
			//预存缴费放入隐藏域---------------(银行卡)
			var changePrepareMoney = $("#yxPrePareMoney").val();
			//预存缴费金额
			var cancleServerRefundMoney = changePrepareMoney * 1  ;//- changeAgreementMoney * 1;
			//已消费金额放入隐藏域----------------(服务人员服务费)
			var changeSaleMoney = $("#yxSaleMoney").val();
			//客户信息费
			var changeAgreementMoney = $("#yxcustomerManageMoney").val();
			/** 2019-01-07 售后历史费用 **/
			var historyAfterCharge=$("#historyAfterCharge").val();
			/** 2019-01-07 **/
			//银行卡最大退款总额 
			var endServerRefundMoney = changePrepareMoney * 1 -  changeSaleMoney * 1 - historyAfterCharge;//- changeAgreementMoney * 1;
			//展示的已消费金额
			var showSaleMoney = changeSaleMoney * 1 - changeAgreementMoney * 1;
			//管家帮分期最大退款总额----------------(分期金额)
			var yxVipShopMoney = 0;//$("#yxVipShopMoneyHidden").val() ;
			//管家帮分期缴费金额
			var yxVipShopFee = 0 ;// yxVipShopMoney * 1 ;
			//管家帮分期最大退款总额,去掉服务人员服务费
			var yxVipShopMoneyMax = 0 ;//yxVipShopFee * 1 - changeSaleMoney * 1 ;
			//是否有管家帮分期缴费标记
			var hasVipShop = $("#yxHasVipshopFee").val();
			//信息费与应退服务人员服务费总和
			var yxBankTotal = changeSaleMoney * 1 + changeAgreementMoney * 1;
			
			
			
			//银行卡和管家帮分期混合支付，服务人员服务费>分期缴费， 2019-1-7  最大退款金额(银行卡最大退款金额  = 客户缴费 + 分期缴费 - 服务人员服务费-历史售后退费)   
			var mixBankRefund = cancleServerRefundMoney + yxVipShopMoneyMax-historyAfterCharge ;
			
			if(showSaleMoney < "0.00" || showSaleMoney < "0"){
				showSaleMoney = 0 ;
			} 
			if(endServerRefundMoney < "0.00" || endServerRefundMoney < "0"){
				endServerRefundMoney = 0 ;
			}
			if(cancleServerRefundMoney < "0.00" || cancleServerRefundMoney < "0"){
				cancleServerRefundMoney = 0 ;
			} 
			if(changePrepareMoney == null || changePrepareMoney == "" || (changePrepareMoney < "0.00" || changePrepareMoney < "0")){
				$("#yxSalaryFee").val(0);
				$("#yxSalaryFee").attr("readonly","readonly");
			} 
			//退款总额输入框的数字
			var lastServerRefund = "";
			if($(this).find("option:selected").val() == 5){//延续服务订单取消
				if(yxserverOrderStatus != 2 && yxserverOrderStatus != 3 && yxserverOrderStatus != 5 
						&& yxserverOrderStatus != 6 && yxserverOrderStatus != 7){
					$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
					$(this).val("");
				}else{
					//获取客户电话，并查找对应订单，加入下拉框
					//queryChangeOrder('yxServerMobile','yxChangeOrderSelect');
					//$("#yxServerMoneyDetail").show(); 
					$("#yxWarn").hide(); 
					$("#refundDiv").show();
					$("#refundMembershipFeeDiv").show();
					$("#refundSalaryFeeDiv").show();
					$("#refundDetailDiv").show();
					$("#refundDetailLine").show();
					var customerFee2 = $("#yxMembershipFee").val();
					//最大退款金额展示
					//只有管家帮分期
					if((yxVipShopFee != null && yxVipShopFee != "" && yxVipShopFee != "0") 
							&& (changePrepareMoney == null || changePrepareMoney == "" || (changePrepareMoney <= "0.00" || changePrepareMoney <= "0"))){
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='maxReturnPremium'>0.00</span>&nbsp;");
//					$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='maxReturnPremium'>"+intToDecimal(yxVipShopMoneyMax)+"</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyHidden").val(0);//银行卡最大退费金额放入隐藏域
//					$("#yxVipShopMoneyHidden").val(yxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
						$("#yxVipShopFee").val("");//管家帮分期退费金额清空
						//将应退服务人员服务费和应退信息费文本框内容置为0
						$("#yxSalaryFee").val(0);
						$("#yxMembershipFee").val(0);
//					$("#yxMembershipFee").attr("readonly","readonly");
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
						//只有银行卡
					}else if((changePrepareMoney != null && changePrepareMoney != "" && changePrepareMoney != "0" )){
						var showFee2 = customerFee2*1 ;
						if(endServerRefundMoney != null && endServerRefundMoney <= 0){
							endServerRefundMoney = changeAgreementMoney;
						}
						/*售后手续费2-开始*/
						/*if(cateType == 2 && orderType != 100200120003 && orderType != 100200010001){
						var mniTotal = $("#payfeeMoneyC").text();
						if(endServerRefundMoney && endServerRefundMoney != 0){
							if(endServerRefundMoney >= mniTotal){
								endServerRefundMoney = endServerRefundMoney-mniTotal;
							}else{
								endServerRefundMoney = 0;
							}
						}
					   }*/
						/*售后手续费2-结束*/
						var parentId = $("#yxServerParentId").val();//订单父订单id
						if(parentId != null && parentId != "" && parentId != 0){
							var type = queryTypeByParentId(parentId);
							if(type == 20120008){
								//是赠送解决方案,可退金额为0
								endServerRefundMoney = 0;
							}
						}
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='maxReturnPremium'>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
//					$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(showFee2));
						$("#yxSalaryFee").val(0);
//					$("#yxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
						$("#yxVipShopFee").val("");//管家帮分期退费金额清空
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(showFee2)+"</span>&nbsp;&nbsp;</label>");
						//银行卡和管家帮分期混合支付
					}
					
					/*else if((changePrepareMoney != null && changePrepareMoney != "" && changePrepareMoney != "0" ) 
						&& (yxVipShopFee != null && yxVipShopFee != "" && yxVipShopFee != "0")){
					if(endServerRefundMoney != null && endServerRefundMoney <= 0){
						endServerRefundMoney = changeAgreementMoney;
					}
					if(changeSaleMoney > yxVipShopFee){ //总服务人员服务费>管家帮分期金额
//						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(mixBankRefund)+"</span>&nbsp;");
//						$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
//						$("#yxServerMoneyHidden").val(mixBankRefund);//银行卡最大退费金额放入隐藏域
//						$("#yxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
						$("#yxSalaryFee").val(0);//应退服务人员服务费框默认为0
						$("#yxVipShopFee").val(0);//管家帮分期退费金额
						$("#yxVipShopFee").attr("readonly","readonly");//管家帮分期不可填写
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
					}else{//总服务人员服务费<管家帮分期金额（）
//						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(changePrepareMoney)+"</span>&nbsp;");
//						$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(yxVipShopMoneyMax)+"</span>&nbsp;");
//						$("#yxServerMoneyHidden").val(changePrepareMoney);//银行卡最大退费金额放入隐藏域
//						$("#yxVipShopMoneyHidden").val(yxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
						$("#yxSalaryFee").val(0);//银行卡最大退费金额放入应退服务人员服务费框
						$("#yxVipShopFee").val(0);//管家帮分期退费金额
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
					}
				}*/
					
					lastServerRefund = $("#yxServerRefund").text();
					//将应退服务人员服务费和分期退款文本框内容置为0
					if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoneyMax == "0"|| yxVipShopMoneyMax == "0.00" )){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
						$("#yxApproveSelectDiv").hide();
					}else if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoneyMax != "0"&& yxVipShopMoneyMax != "0.00" )){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
					}else if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoney  == null || yxVipShopMoney  == "")){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
					}else{
						$("#yxServer_bank").show();
						$("#yxServer_bankCity").show();
						$("#yxServer_bankCN").show();
						$("#yxApproveSelectDiv").show();
					}
//				if(hasVipShop != "" && hasVipShop != null){
//					$("#refundVipShopFeeDiv").show();//展示管家帮分期
//					$("#yxVipShopWarn").hide();
//				}
				}
			}
			else if($(this).find("option:selected").val() == 8){//延续服务订单终止
				if(yxserverOrderStatus != 8 && yxserverOrderStatus != 11){
					$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
					$(this).val("");
				}else{
					//获取客户电话，并查找对应订单，加入下拉框
					//queryChangeOrder('yxServerMobile','yxChangeOrderSelect');
					//$("#yxServerMoneyDetail").show(); 
					$("#yxWarn").hide(); 
					$("#refundDiv").show();
					$("#refundMembershipFeeDiv").show();
					$("#refundSalaryFeeDiv").show();
					$("#refundDetailDiv").show();
					$("#refundDetailLine").show();
					var customerFee = $("#yxMembershipFee").val();
					//将应退服务人员服务费和分期退款文本框内容置为0
					//最大退款金额展示
					//只有管家帮分期
					if((yxVipShopFee != null && yxVipShopFee != "" && yxVipShopFee != "0") 
							&& (changePrepareMoney == null || changePrepareMoney == "" || (changePrepareMoney <= "0.00" || changePrepareMoney <= "0"))){
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='maxReturnPremium'>0.00</span>&nbsp;");
//					$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(yxVipShopMoneyMax)+"</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyHidden").val(0);//银行卡最大退费金额放入隐藏域
//					$("#yxVipShopMoneyHidden").val(yxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
						$("#yxVipShopFee").val("");//管家帮分期退费金额清空
						$("#yxSalaryFee").val("0");//应退服务人员服务费为0
						$("#yxMembershipFee").val(0);
//					$("#yxMembershipFee").attr("readonly","readonly");
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
						//只有银行卡
					}else if(changePrepareMoney != null && changePrepareMoney != "" && changePrepareMoney != "0" ){
						if(endServerRefundMoney != null && endServerRefundMoney <= 0){
							endServerRefundMoney =  changeAgreementMoney;
						}
						/*售后手续费2-开始*/
						/*if(cateType == 2 && orderType != 100200120003 && orderType != 100200010001){
						var mniTotal = $("#payfeeMoneyC").text();
						if(endServerRefundMoney && endServerRefundMoney != 0){
							if(endServerRefundMoney >= mniTotal){
								endServerRefundMoney = endServerRefundMoney-mniTotal;
							}else{
								endServerRefundMoney = 0;
							}
						}
					}*/
						/*售后手续费2-结束*/
						var parentId = $("#yxServerParentId").val();//订单父订单id
						if(parentId != null && parentId != "" && parentId != 0){
							var type = queryTypeByParentId(parentId);
							if(type == 20120008){
								//是赠送解决方案,可退金额为0
								endServerRefundMoney = 0;
							}
						}
						var showFee = customerFee*1;
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='maxReturnPremium'>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
//					$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(showFee));
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
						$("#yxSalaryFee").val(0);
//					$("#yxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
						$("#yxVipShopFee").val("");//管家帮分期退费金额清空
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(showFee)+"</span>&nbsp;&nbsp;</label>");
						//银行卡和管家帮分期混合支付
					}
					/*else if((changePrepareMoney != null && changePrepareMoney != "" && changePrepareMoney != "0" ) 
						&& (yxVipShopFee != null && yxVipShopFee != "" && yxVipShopFee != "0")){
					if(endServerRefundMoney != null && endServerRefundMoney <= 0){
						endServerRefundMoney =  changeAgreementMoney;
					}
					if(changeSaleMoney > yxVipShopFee){ //总服务人员服务费>管家帮分期金额
//						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(mixBankRefund)+"</span>&nbsp;");
//						$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
//						$("#yxServerMoneyHidden").val(mixBankRefund);//银行卡最大退费金额放入隐藏域
//						$("#yxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
						$("#yxSalaryFee").val(0);//银行卡最大退费金额放入应退服务人员服务费框
						$("#yxVipShopFee").val(0);//管家帮分期退费金额
						$("#yxVipShopFee").attr("readonly","readonly");//管家帮分期不可填写
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
					}else{//总服务人员服务费<管家帮分期金额（）
//						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(changePrepareMoney)+"</span>&nbsp;");
//						$("#yxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(yxVipShopMoneyMax)+"</span>&nbsp;");
//						$("#yxServerMoneyHidden").val(changePrepareMoney);//银行卡最大退费金额放入隐藏域
//						$("#yxVipShopMoneyHidden").val(yxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
						$("#yxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(endServerRefundMoney)+"</span>&nbsp;");
						$("#yxServerRefund").html(intToDecimal(0));
						$("#yxServerMoneyHidden").val(endServerRefundMoney);//银行卡最大退费金额放入隐藏域
						$("#yxSalaryFee").val(0);//银行卡最大退费金额放入应退服务人员服务费框
						$("#yxVipShopFee").val(0);//管家帮分期退费金额
						$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>0.00</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>0.00</span>&nbsp;&nbsp;</label>");
					}
				}*/
					lastServerRefund = $("#yxServerRefund").text();
					if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoney == "0"|| yxVipShopMoney == "0.00" )){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
						$("#yxApproveSelectDiv").hide();
					}else if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoneyMax != "0"&& yxVipShopMoneyMax != "0.00" )){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
					}else if((lastServerRefund == "0" || lastServerRefund == "0.00") && (yxVipShopMoney  == null || yxVipShopMoney  == "")){
						$("#yxServer_bank").hide();
						$("#yxServer_bankCity").hide();
						$("#yxServer_bankCN").hide();
					}else{
						$("#yxServer_bank").show();
						$("#yxServer_bankCity").show();
						$("#yxServer_bankCN").show();
						$("#yxApproveSelectDiv").show();
					}
//				if(hasVipShop != "" && hasVipShop != null){
//					$("#refundVipShopFeeDiv").show();//展示管家帮分期
//					$("#yxVipShopWarn").hide();
//				}
				}
			}
		});
	}
	//延续订单应退信息费更改，退款总额和银行卡退款明细联动
	/*$("#yxMembershipFee").on("input",function(){
		var salaryFee = $("#yxSalaryFee").val();
		var maxRefund = $("#yxServerMoneyHidden").val();//最大退费金额
		var yxHasVipshopFee = $("#yxHasVipshopFee").val();//分期退款标记隐藏域取值
		var yxVipShopMoney = 0 ;//$("#yxVipShopMoneyHidden").val();//管家帮分期最大退费金额
		var yxRefundVipShop = 0 ;//$("#yxVipShopFee").val();//管家帮分期填写金额
		var yxRefundAll = 0;
		//判断应退信息费和应退服务人员服务费总额
		if(!isNaN($(this).val()) && $(this).val() != "0"){
			 yxRefundAll = $(this).val()*1 + salaryFee*1 ;
		}else if(!isNaN(salaryFee) && salaryFee != "0"){
			yxRefundAll = salaryFee*1 ;
		}else{
			yxRefundAll = "0" ;
		}
		//售后手续费1-开始
		var MembershipFee = $("#MembershipFee").text();//信息费可用
		var notIousSalaryMoney = $("#notIousSalaryMoney").text();//非白条预存服务人员服务费可用
		var installmentFee = $("#installmentFee").text();//分期手续费可用
		var mniTotal = ((MembershipFee||0)*1+(notIousSalaryMoney||0)*1+(installmentFee||0)*1);//售后手续费可用合计
		if(yxRefundAll && yxRefundAll != 0){
			if(yxRefundAll >= mniTotal){
				yxRefundAll = yxRefundAll-mniTotal;
			}else{
				yxRefundAll = 0;
			}
		}
		//售后手续费1-结束
		//判断是否选中转单下拉框的订单编号
		//var p1 = $("#yxChangeOrderSelect").find("option:selected").val();
		//判断管家帮分期
		if(!isNaN(yxHasVipshopFee) && yxHasVipshopFee != "" && yxHasVipshopFee != null && yxHasVipshopFee == 1){
			if((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxRefundVipShop == "0.00" || yxRefundVipShop == "0")){
				 $("#yxServer_bank").hide();
				 $("#yxServer_bankCity").hide();
				 $("#yxServer_bankCN").hide();
				 $("#yxApproveSelectDiv").hide();
				 $("#yxWarn").hide();
				 //$("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			//退费总额大于最大退费总额，提示错误，并不允许提交
			}else if((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll*1 > maxRefund*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
//				if(p1 != null && p1 != ""){
//					$("#yxServer_bank").hide();
//					$("#yxServer_bankCity").hide();
//					$("#yxServer_bankCN").hide();
//				}else{
//				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").show();
//				 $("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else if((yxHasVipshopFee != "0.00" || yxHasVipshopFee != "0") && yxRefundVipShop*1 > yxVipShopMoney*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
//				//迁单展示银行卡信息
//				if(p1 != null && p1 != ""){
//					$("#yxServer_bank").hide();
//					$("#yxServer_bankCity").hide();
//					$("#yxServer_bankCN").hide();
//				}else{
//				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
//				 $("#yxVipShopWarn").show();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
//				if(p1 != null && p1 != ""){
//					$("#yxServer_bank").hide();
//					$("#yxServer_bankCity").hide();
//					$("#yxServer_bankCN").hide();
//				}else{
//				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
//				 $("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			} 
		}else{
		    if(yxRefundAll == "0.00" || yxRefundAll == "0"){
				 $("#yxServer_bank").hide();
				 $("#yxServer_bankCity").hide();
				 $("#yxServer_bankCN").hide();
				 $("#yxApproveSelectDiv").hide();
				 $("#yxWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			//退费总额大于最大退费总额，提示错误，并不允许提交
			}else if((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll*1 > maxRefund*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
//				if(p1 != null && p1 != ""){
//					$("#yxServer_bank").hide();
//					$("#yxServer_bankCity").hide();
//					$("#yxServer_bankCN").hide();
//				}else{
//				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").show();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
//				if(p1 != null && p1 != ""){
//					$("#yxServer_bank").hide();
//					$("#yxServer_bankCity").hide();
//					$("#yxServer_bankCN").hide();
//				}else{
//				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			} 
		}
		$("#yxServerRefund").html(intToDecimal(yxRefundAll));
		if(yxRefundAll != "0" && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)){
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxVipShopMoney)+"</span>&nbsp;&nbsp;</label>");
		}else if((yxRefundAll == "0.00" || yxRefundAll == "0") &&yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)){
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxRefundVipShop)+"</span>&nbsp;&nbsp;</label>");
		}else{
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>");
		}
	});*/
	
	
	//延续订单应退服务人员服务费更改，退款总额和银行卡退款明细联动
	/*$("#yxSalaryFee").on("input",function(){
		var membershipFee = $("#yxMembershipFee").val();
		var maxRefund = $("#yxServerMoneyHidden").val();//最大退费金额
		var yxHasVipshopFee = $("#yxHasVipshopFee").val();//分期退款标记隐藏域取值
		var yxVipShopMoney = 0;//$("#yxVipShopMoneyHidden").val();//管家帮分期最大退费金额
		var yxRefundVipShop = 0 ;//$("#yxVipShopFee").val();//管家帮分期填写金额
		var yxRefundAll = 0;
		//判断应退信息费和应退服务人员服务费总额
		if(!isNaN($(this).val()) && $(this).val() != "0"){
			 yxRefundAll = $(this).val()*1 + membershipFee*1 ;
		}else if(!isNaN(membershipFee) && membershipFee != "0"){
			yxRefundAll = membershipFee*1 ;
		}else{
			yxRefundAll = "0" ;
		}
		
		
		//判断是否选中转单下拉框的订单编号
        //var p2 = $("#yxChangeOrderSelect").find("option:selected").val();
		//判断管家帮分期
		if(!isNaN(yxHasVipshopFee) && yxHasVipshopFee != "" && yxHasVipshopFee != null && yxHasVipshopFee == 1){ 
			if((yxRefundAll == "0.00" || yxRefundAll == "0")&& (yxRefundVipShop == "0.00" || yxRefundVipShop == "0") ){
				 $("#yxServer_bank").hide();
				 $("#yxServer_bankCity").hide();
				 $("#yxServer_bankCN").hide();
				 $("#yxApproveSelectDiv").hide();
				 $("#yxWarn").hide();
				 //$("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			}else if((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll*1 > maxRefund*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
				//if(p2 != null && p2 != ""){
				//$("#yxServer_bank").hide();
				//$("#yxServer_bankCity").hide();
				//$("#yxServer_bankCN").hide();
				//}else{
				//}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").show();
				 //$("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else if((yxHasVipshopFee != "0.00" || yxHasVipshopFee != "0") && yxRefundVipShop*1 > yxVipShopMoney*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
				if(p2 != null && p2 != ""){
					$("#yxServer_bank").hide();
					$("#yxServer_bankCity").hide();
					$("#yxServer_bankCN").hide();
				}else{
				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
//				 $("#yxVipShopWarn").show();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
				if(p2 != null && p2 != ""){
					$("#yxServer_bank").hide();
					$("#yxServer_bankCity").hide();
					$("#yxServer_bankCN").hide();
				}else{
				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
//				 $("#yxVipShopWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			}
		}else{
			 if(yxRefundAll == "0.00" || yxRefundAll == "0"){
				 $("#yxServer_bank").hide();
				 $("#yxServer_bankCity").hide();
				 $("#yxServer_bankCN").hide();
				 $("#yxApproveSelectDiv").hide();
				 $("#yxWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			}else if((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll*1 > maxRefund*1 ){
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
				if(p2 != null && p2 != ""){
					$("#yxServer_bank").hide();
					$("#yxServer_bankCity").hide();
					$("#yxServer_bankCN").hide();
				}else{
				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").show();
				 $("#qualityServerBtn").attr("disabled","disabled");
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
				//迁单展示银行卡信息
				if(p2 != null && p2 != ""){
					$("#yxServer_bank").hide();
					$("#yxServer_bankCity").hide();
					$("#yxServer_bankCN").hide();
				}else{
				}
				 $("#yxApproveSelectDiv").show();
				 $("#yxWarn").hide();
				 $("#qualityServerBtn").removeAttr("disabled");
			}
		}
		$("#yxServerRefund").html(intToDecimal(yxRefundAll));
		if(yxRefundAll != "0" && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)){
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxRefundVipShop)+"</span>&nbsp;&nbsp;</label>");
		}else if((yxRefundAll == "0.00" || yxRefundAll == "0") && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)){
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxRefundVipShop)+"</span>&nbsp;&nbsp;</label>");
		}else{
			$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>");
		}
	});*/
	
	
	//管家帮分期退款
	/*$("#yxVipShopFee").on("keyup",function(){ 
		var yxVipShopMoney = $(this).val();  //应退分期费用
		var membershipFee = $("#yxMembershipFee").val();//应退信息费
		var salaryFee = $("#yxSalaryFee").val();//应退服务人员服务费
		var maxVipShopFee = $("#yxVipShopMoneyHidden").val();//管家帮分期最大退费金额
		var yxRefundAll = 0;
		if(!isNaN(membershipFee) && !isNaN(salaryFee) ){
			yxRefundAll = membershipFee*1 + salaryFee*1;
		}
		//判断是否选中转单下拉框的订单编号
		var p3 = $("#yxChangeOrderSelect").find("option:selected").val();
		 if((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxVipShopMoney == "0.00" || yxVipShopMoney == "0")){
			 $("#yxServer_bank").hide();
			 $("#yxServer_bankCity").hide();
			 $("#yxServer_bankCN").hide();
			 $("#yxApproveSelectDiv").hide();
			 $("#yxWarn").hide();
//			 $("#yxVipShopWarn").hide();
			 $("#qualityServerBtn").removeAttr("disabled");
		}else if( yxVipShopMoney*1 > maxVipShopFee*1 ){
			//迁单展示银行卡信息
			if(p3 != null && p3 != ""){
				$("#yxServer_bank").hide();
				$("#yxServer_bankCity").hide();
				$("#yxServer_bankCN").hide();
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
			}
			 $("#yxApproveSelectDiv").show();
			 $("#yxWarn").hide();
//			 $("#yxVipShopWarn").show();
			 $("#qualityServerBtn").attr("disabled","disabled");
		}else{
			//迁单展示银行卡信息
			if(p3 != null && p3 != ""){
				$("#yxServer_bank").hide();
				$("#yxServer_bankCity").hide();
				$("#yxServer_bankCN").hide();
			}else{
				$("#yxServer_bank").show();
				$("#yxServer_bankCity").show();
				$("#yxServer_bankCN").show();
			}
			 $("#yxApproveSelectDiv").show();
			 $("#yxWarn").hide();
//			 $("#yxVipShopWarn").hide();
			 $("#qualityServerBtn").removeAttr("disabled");
		}
		 if((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxVipShopMoney != "0.00" && yxVipShopMoney != "0")){
			 $("#yxServer_bank").hide();
			 $("#yxServer_bankCity").hide();
			 $("#yxServer_bankCN").hide();
		}
		 if(yxRefundAll != "0" && yxVipShopMoney != null && yxVipShopMoney != "" && !isNaN(yxVipShopMoney)){
				$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxVipShopMoney)+"</span>&nbsp;&nbsp;</label>");
			}else if((yxRefundAll == "0.00" || yxRefundAll == "0") &&yxVipShopMoney != null && yxVipShopMoney != "" && !isNaN(yxVipShopMoney)){
				$("#yxRefundDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxVipShopMoney)+"</span>&nbsp;&nbsp;</label>");
			}else{
				$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>");
			}
	});*/
	
	//单次服务售后类型，判断是否可以新增售后
	$("#dcServer_afterSalesKind").change(function(){
		var dcserverOrderStatus = $("#serverOrderStatus").val();
		var orderCateType = $("#dcServerCateType").val();
		var fSum = $("#dcServerReturnTotal").text();
		var dcMoney =  $("#dcServerBankCardMoney").val();
		var serverPayStatus = $("#serverPayStatus").val(); //订单支付状态
		if(serverPayStatus != "20110001" && dcMoney != "0.00" && dcMoney != null){
			 $("#dcServer_bank").show();
			 $("#dcServer_bankCity").show();
			 $("#dcServer_bankCN").show();
			 $("#dcServer_bankUn").show();
		}else{
			 $("#dcServer_bank").hide();
			 $("#dcServer_bankCity").hide();
			 $("#dcServer_bankCN").hide();
			 $("#dcServer_bankUn").hide();
		}
		if(fSum == "0.00" || fSum == "0"){ 
			//隐藏审批部门和审批人
			$("#dcqualityApproveDiv").hide(); 
		}else{
			//填写审批部门和审批人
			$("#dcqualityApproveDiv").show(); 
		}
		if($(this).find("option:selected").val() == 4){//单次服务订单取消
			//自营单次订单：已受理、匹配中、已匹配 以上状态可以取消
			if((dcserverOrderStatus == 1 || dcserverOrderStatus > 4 ) && orderCateType == 1){
				$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
				$(this).val("");
			}
			if(orderCateType == 4){//他营单次订单：服务开始时间之前4个小时可以取消
				//服务开始时间
				var orderStartTime = $("#dcServerStartTimeHide").val();
				//查询系统时间
				var newSystemTime = "";
				$.ajax({
					url: ctx +"/order/querySysdate",
					data:{},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if(data.msg=='00'){
							 newSystemTime = data.systemDate;
						}
					}
				});
				//当前时间  + 4小时 = 可以取消的订单时间
				var orderCancleTime =new Date(newSystemTime.setHours(newSystemTime.getHours()+4)).format("yyyy-MM-dd hh:mm:ss");
	 		   	//格式化时间
				var regEx = new RegExp("\\-","gi");
				orderStartTime=orderStartTime.replace(regEx,"/");
				orderCancleTime=orderCancleTime.replace(regEx,"/");
	 			var orderStartTimeVal=Date.parse(orderStartTime);
	 			var orderCancleTimeVal=Date.parse(orderCancleTime);
	 			if(orderStartTimeVal < orderCancleTimeVal){
	 				$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
					$(this).val("");
	 			} 
			}
		}
	});
	
	
			//保存服务类售后单
			function saveQualityServer(){
				var serverPayStatus = $("#serverPayStatus").val(); //订单支付状态
				var data = "";
				var custName = "";
				var custMobile = "";
				var dcRefundTotal = "";
				var url = "";
				if(cateType == 1 || cateType == 4){
					 data = $("#qualityDCServerForm").serialize();
					 var parentId = $("#dcServerParentId").val();//订单父订单id
					 if(parentId != null && parentId != "" && parentId != 0){
							var type = queryTypeByParentId(parentId);
							data+=("&solutionType="+type);//订单父订单解决方案类型
					  }
					 dcRefundTotal = $("#dcServerReturnTotal").text();
					 custName = $("#dcServerUserName").text();
					 custMobile = $("#dcServerMobile").text(); 
					 if(dcRefundTotal != undefined && dcRefundTotal != "" && dcRefundTotal != "0" && dcRefundTotal != "0.00"){
						 var dcApproveDept = $("#dcApproveDeptSelect").find("option:selected").val();
						 if(dcApproveDept == "" || dcApproveDept == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
							 $("#qualityServerOneBtn").removeAttr("disabled");
							  return ;
						 }
						 var dcApproveBy = $("#dcApproveBySelect").find("option:selected").val();
						 if(dcApproveBy == "" || dcApproveBy == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
							 $("#qualityServerOneBtn").removeAttr("disabled");
							  return ;
						 }
					 }
					 var dcServerBankCardMoney =  $("#dcServerBankCardMoney").val();
					 var ServerOneBankMainName = $("#dcServer_bankSupportId").find("option:selected").text();
					 if(ServerOneBankMainName == "...请选择..."){
						 ServerOneBankMainName = "";
					  }
					 url = ctx+"/afterSales/insertAfterSales?orderId="+orderId+"&feePost=20250014";
					 url += "&custName="+encodeURI(encodeURI(custName))+"&custMobile="+custMobile;
					 url += "&refundTotal="+dcRefundTotal+"&bankMainName="+encodeURI(encodeURI(ServerOneBankMainName));
					 if(serverPayStatus != "20110001" && dcServerBankCardMoney != "0.00" && dcServerBankCardMoney != null && !solutionBigOrderflag){
							var dcbanksupportId = $("#dcServer_bankSupportId").val();
							var dcbankCityCode = $("#dcServer_bankCityCode").val();
							var dcbankCard = $("#dcServer_bankCard").val();
							var dcbankName = $("#dcServer_bankName").val();
							var dcbankUserName = $("#dcServer_bankUserName").val();
						 	var selectBank = $("input[name=selectBank]:checked").val();
							if(selectBank == "2"){//不填写银行
								insertQualityServer(url,data);
							}else{//填写银行
								if(dcbanksupportId == null || dcbanksupportId == ""){
									$.alert({millis:3000,top:'30%',text:"请填写银行！"});
									flag = true;
								}else if(dcbankCityCode == null || dcbankCityCode == ""){
									$.alert({millis:3000,top:'30%',text:"请填写城市！"});
									flag = true;
								}else if(dcbankCard == null || dcbankCard == ""){
									$.alert({millis:3000,top:'30%',text:"请填写银行卡号！"});
									flag = true;
								}else if(dcbankName == null || dcbankName == ""){
									$.alert({millis:3000,top:'30%',text:"请填写开户行！"});
									flag = true;
								}else if(dcbankUserName == null || dcbankUserName == ""){
									$.alert({millis:3000,top:'30%',text:"请填写持卡人！"});
									flag = true;
								}else{
									flag = true;
									if(flag){
										insertQualityServer(url,data);
									}
								}
							}
						}else{
							flag = true;
							if(flag){
								insertQualityServer(url,data);
							 }
						}
					 $("#qualityServerOneBtn").removeAttr("disabled");
				}else{
					 data = $("#qualityYXServerForm").serialize();
					 var membershipFee = $("#yxMembershipFee").val();//应退信息费
					 var salaryFee = $("#yxSalaryFee").val();//应退服务人员服务费
					 
					 
					 
					 /*var yxMembershipFeeA =$("#maxReturnPremium").text();
					 if((membershipFee*1+salaryFee*1)>yxMembershipFeeA*1){
						 $.alert({millis:2000,top:'30%',text:"退款总额不能超过最大退款金额，请重新填写！"});
						 return;
					 }*/
					 
					 
					 /**new-start*/
					var afterReturnableMessageFee = $("#afterReturnableMessageFee").attr("value");//信息费最大可退金额
					var afterReturnableSalary = $("#afterReturnableSalary").attr("value");//服务费最大可退金额
					if(membershipFee*1 > afterReturnableMessageFee*1){
						$.alert({"text":"应退信息费已超过信息费最大可退金额,请重新输入"});
						$("#qualityServerBtn").removeAttr("disabled");
						return;	
					}
					if(salaryFee*1 > afterReturnableSalary*1){
						$.alert({"text":"应退服务费已超过服务费最大可退金额,请重新输入"});
						$("#qualityServerBtn").removeAttr("disabled");
						return;	
					}
					/**new-end*/
					
					 
					 var yxServerRefund = Number(membershipFee)+Number(salaryFee);//退款合计金额
					 yxServerRefund = yxServerRefund.toFixed(2);//保留两位小数
					 //var yxServerRefund = $("#yxServerRefund").html();//退款合计金额
					 var parentId = $("#yxServerParentId").val();//订单父订单id
					 if(parentId != null && parentId != "" && parentId != 0){
							var type = queryTypeByParentId(parentId);
							data+=("&solutionType="+type);//订单父订单解决方案类型
					  }
					 custName = $("#yxServerUserName").text();
					 custMobile = $("#yxServerMobile").text();
					 //var payfeeMoneyC = $("#payfeeMoneyC").html();
					 var yxRefundVipShop = "0";// $("#yxVipShopFee").val();//管家帮分期填写金额
					 var yxApproveDept = $("#yxApproveDeptSelect").find("option:selected").val();//审核部门
					 var yxApproveBy = $("#yxApproveBySelect").find("option:selected").val();//审核人
					 //var yxChangeOrder = $("#yxChangeOrderSelect").find("option:selected").val();//转单订单ID
					 if(yxServerRefund != undefined && yxServerRefund != "" && yxServerRefund != "0"&& yxServerRefund != "0.00"){
						 if(yxApproveDept == "" || yxApproveDept == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
							 $("#qualityServerBtn").removeAttr("disabled");
							  return ;
						 }
						 if(yxApproveBy == "" || yxApproveBy == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
							 $("#qualityServerBtn").removeAttr("disabled");
							  return ;
						 }
					 }
					 var yxServerMoneyHidden =  $("#yxServerMoneyHidden").html();//最大退款总额
					 if(yxServerRefund !="" && yxServerMoneyHidden != ""  && yxServerRefund*1 > yxServerMoneyHidden*1 ){
						 $("#yxWarn").show();
						 $("#qualityServerBtn").attr("disabled","disabled");
						 return ;
					}
					 var maxVipShopFee = 0 //$("#yxVipShopMoneyHidden").val();//管家帮分期最大退费金额
					 var yxRefundVipShop = "";//$("#yxVipShopFee").val();//管家帮分期填写金额
					 //var yxHasVipshopFee = $("#yxHasVipshopFee").val();//分期退款标记隐藏域取值
					 if( (yxServerRefund == "0"|| yxServerRefund == "0.00") && yxRefundVipShop != undefined && yxRefundVipShop != ""){
						 if(yxApproveDept == "" || yxApproveDept == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
							 $("#qualityServerBtn").removeAttr("disabled");
							  return ;
						 }
						 if(yxApproveBy == "" || yxApproveBy == null){
							 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
							 $("#qualityServerBtn").removeAttr("disabled");
							  return ;
						 }
					 }
					 //白条退款判断，退款大于最大退款金额，不可提交
					 if(maxVipShopFee !="" && yxRefundVipShop != ""  && yxRefundVipShop*1 > maxVipShopFee*1 ){//&& yxHasVipshopFee != null && yxHasVipshopFee == 1
						 //$("#yxVipShopWarn").show();
						 $("#qualityServerBtn").attr("disabled","disabled");
						 return ;
					}
					 //开户银行名称
					 var serverBankMainNameSel = $("#yxServer_bankSupportId").find("option:selected");
					 var serverBankMainName = serverBankMainNameSel.attr("data-name")||"";
					 //判断取消原因是否必填，其他原因
					 /*var ServerReasonSelect = $("#yxReasonSelect").find("option:selected").val();
					 if(ServerReasonSelect != null && ServerReasonSelect != "" && ServerReasonSelect != 10 ){
						 var reasonSelect = $("#yxReasonSelect").find("option:selected"); 
						 var qualityReason = reasonSelect.data("quality");
						 url += "&reason="+qualityReason;
					 }*/
					 url = ctx+"/afterSales/insertAfterSales?orderId="+orderId;
					 url +=	"&custName="+encodeURI(encodeURI(custName))+"&custMobile="+custMobile;
					 url += "&bankMainName="+encodeURI(encodeURI(serverBankMainName));
					 url += "&refundTotal="+yxServerRefund;
					 //url += "&payfeeMoneyC="+payfeeMoneyC;
					 //url += "&vphFee="+yxRefundVipShop;
					 //url += "&reasonFlag="+ServerReasonSelect;
					 //url += "&moveToOrderId="+yxChangeOrder;//转单订单ID
					 var yxAsk = $("#yxServer_afterSalesKind").find("option:selected").val();//选择的售后类型
					 var lastYxServerRefund = $("#yxServerRefund").html();
					 if(membershipFee == "" || membershipFee == null){
						 $.alert({millis:2000,top:'30%',text:"请填写应退信息费！"});
						 $("#qualityServerBtn").removeAttr("disabled");
						 return ;
					 }
					 if(salaryFee == "" || salaryFee == null){
						 $.alert({millis:2000,top:'30%',text:"请填写应退服务人员服务费！"});
						 $("#qualityServerBtn").removeAttr("disabled");
						 return ;
					 }
					 /*if(yxAsk != "" && yxAsk != 7 ){//售后类型不为延续订单换人
					 }*/
					 //if(serverPayStatus != "20110001" && yxAsk != 7 && yxAsk != 8 && lastYxServerRefund != "0" && lastYxServerRefund != "0.00"){
					 if(serverPayStatus != "20110001" && yxAsk != 7 && lastYxServerRefund != "0" && lastYxServerRefund != "0.00"){
							var yxbanksupportId = $("#yxServer_bankSupportId").val();
							var yxbankCityCode = $("#yxServer_bankCityCode").val();
							var yxbankCard = $("#yxServer_bankCard").val();
							var yxbankName = $("#yxServer_bankName").val();
							var yxbankUserName = $("#yxServer_bankUserName").val();
							if(yxbanksupportId == null || yxbanksupportId == ""){
								$.alert({millis:3000,top:'30%',text:"请填写银行！"});
								flag = true;
							}else if(yxbankCityCode == null || yxbankCityCode == ""){
								$.alert({millis:3000,top:'30%',text:"请填写城市！"});
								flag = true;
							}else if(yxbankCard == null || yxbankCard == ""){
								$.alert({millis:3000,top:'30%',text:"请填写银行卡号！"});
								flag = true;
							}else if(yxbankName == null || yxbankName == ""){
								$.alert({millis:3000,top:'30%',text:"请填写开户行！"});
								flag = true;
							}else if(yxbankUserName == null || yxbankUserName == ""){
								$.alert({millis:3000,top:'30%',text:"请填写持卡人！"});
								flag = true;
							}else{
								flag = true;
								if(flag){
									insertQualityServer(url,data);
								 }
							}
						}else{
							flag = true;
							if(flag){
								insertQualityServer(url,data);
							 }
						}
					 $("#qualityServerBtn").removeAttr("disabled");
				}
			}
			//保存售后
			function insertQualityServer(url,data){
				$.ajax({
					url: url,
					data:data,
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if (data.msg =="00") {
							$.alert({millis:3000,top:'30%',text:"添加成功！"});
							queryAfterSalesByLike(0,10);
							closeModule('qualityServer');
						}else if (data.msg =="10"){
							$.alert({millis:3000,top:'30%',text:"售后单已存在！"});
							closeModule('qualityServer');
						}else{
							$.alert({millis:3000,top:'30%',text:"添加失败！"});
							closeModule('qualityServer');
						}
					}
				}); 
			}
			
	
			//单次服务验证
			$('#qualityDCServerForm').bootstrapValidator({
			    message: 'This value is not valid',
			    excluded: ':disabled,:hidden',
			    feedbackIcons: {
			        valid: 'glyphicon glyphicon-ok',
			        invalid: 'glyphicon glyphicon-remove',
			        validating: 'glyphicon glyphicon-refresh'
			    },
			    fields: {         //fields下的是表单name属性
			    	bankUserName: {
			            message: '持卡人无效！',
			            validators: {
			                stringLength: {
			                    min: 1,
			                    max: 30,
			                    message: '持卡人名称必须为1-30个字！'
			                },
			                regexp: {
			                    regexp: /^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$/,
			                    message: '持卡人名称为中文和英文！'
			                }
			            }
			        },
			        bankName: {
			            message: '开户行无效！',
			            validators: {
			                stringLength: {
			                    min: 1,
			                    max: 100,
			                    message: '开户行名称必须为1-100个字！'
			                },
			                regexp: {
			                    regexp: /^[\u4E00-\u9FA5]{1,100}$/,
			                    message: '开户行名称为中文！'
			                }
			            }
			        },
			        afterSalesKind: {
			            validators: {
			                notEmpty: {
			                    message: '必选！'
			                },
			            }
			        },
		            reason : {
		            	validators: {
		            		stringLength: {
			                    min: 0,
			                    max: 200,
			                    message: '描述不超过200字！'
			                },
		            	}
		            },
		            bankCard : {
		            	validators: {
		            		regexp: {
			                    regexp: /^(\d{12}|\d{16}|\d{18}|\d{19}|\d{20})$/,
			                    message: '银行卡号错误！'
			                }
		            	}
		            },
			    }
			}).on('success.form.bv', function(e) {
			    // 阻止表单提交【submit】【必填】
			    e.preventDefault();
			     //保存售后单
			     $.confirm({text:"请确认退款中不包含服务人员应发服务人员服务费，否则一旦退款成功，将由责任人自行追回服务人员服务费款项",callback:function(r){
					if(r){
				   		saveQualityServer();
					}else{
						$('#qualityDCServerForm').bootstrapValidator('disableSubmitButtons', false);
					}
				}})
			});
			
			//延续性服务验证
			$('#qualityYXServerForm').bootstrapValidator({
			    message: 'This value is not valid',
			    excluded: ':disabled,:hidden',
			    feedbackIcons: {
			        valid: 'glyphicon glyphicon-ok',
			        invalid: 'glyphicon glyphicon-remove',
			        validating: 'glyphicon glyphicon-refresh'
			    },
			    fields: {  
			    	bankUserName: {
			            message: '持卡人无效！',
			            validators: {
			                stringLength: {
			                    min: 1,
			                    max: 30,
			                    message: '持卡人名称必须为1-30个字！'
			                },
			                regexp: {
			                    regexp: /^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$/,
			                    message: '持卡人名称为中文和英文！'
			                }
			            }
			        },
			        bankName: {
			            message: '开户行无效！',
			            validators: {
			                stringLength: {
			                    min: 1,
			                    max: 100,
			                    message: '开户行名称必须为1-100个字！'
			                },
			                regexp: {
			                    regexp: /^[\u4E00-\u9FA5]{1,100}$/,
			                    message: '开户行名称为中文！'
			                }
			            }
			        },
			        afterSalesKind: {
			            validators: {
			                notEmpty: {
			                    message: '必选！'
			                },
			            }
			        },
			        refundMembershipFee: {
			        	validators: {
			        		regexp: {
			        			regexp: /^\d+\.?\d{0,2}$/ ,
			        			message: '只能为数字！'
			        		},
			            }
			        },
			        refundSalaryFee: {
			        	validators: {
			        		regexp: {
			        			regexp: /^\d+\.?\d{0,2}$/ ,
			        			message: '只能为数字！'
			        		},
			            }
			        },
			        yxServerWage: {
			            validators: {
			                regexp: {
			        			regexp: /^\d+\.?\d{0,2}$/ ,
			        			message: '只能为数字！'
			        		},
			            }
			        },
		            reason : {
		            	validators: {
		            		stringLength: {
			                    min: 0,
			                    max: 200,
			                    message: '描述不超过200字！'
			                },
		            	}
		            },
		            bankCard : {
		            	validators: {
		            		regexp: {
			                    regexp: /^(\d{12}|\d{16}|\d{18}|\d{19}|\d{20})$/,
			                    message: '银行卡号错误！'
			                }
		            	}
		            },
			    }
			}).on('success.form.bv', function(e,data) {
			    // 阻止表单提交【submit】【必填】
			    e.preventDefault();
			    //保存售后单
			     $.confirm({text:"请确认退款中不包含服务人员应发服务人员服务费，否则一旦退款成功，将由责任人自行追回服务人员服务费款项",callback:function(r){
					if(r){
						saveQualityServer();
					}else{
						$('#qualityYXServerForm').bootstrapValidator('disableSubmitButtons', false);
					}
				}})
			});
			
			$("#yxServer_bankUserName,#yxServer_bankSupportId,#yxServer_bankProvinceCode,#yxServer_bankCityCode,#yxServer_bankCard,#yxServer_bankName,#yxServerReason").each(function(){
				$(this).change(function(){
					if($("#yxWarn").is(':hidden')){  
						$("#qualityServerBtn").removeAttr("disabled");
					} else {
						$("#qualityServerBtn").attr("disabled","disabled");
					}
				})
			});
			
			//根据客户电话，查询可以转单的订单
			function queryChangeOrder(eleId,selectId){
				var mobile = $("#"+eleId).html();
				var orderCode = $("#yxServerOrderCode").html();
				$.ajax({
					url: ctx+"/order/queryChangeOrder",
					data:{
						userMobile:mobile,
						orderCodeNotIn:orderCode
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						var html = "";
						html +="<option style='color:blue;'  value='' >...请选择...</option>";
						if (data.msg == "00") {
							 $.each(data.list,function(i,v){
								 html +="<option value='" +v.id +"' >"+v.orderCode+"</option>";
							});
						}
						 $("#"+selectId).html(html); 
					}
				});
			}
			
			//选择转单的下拉框，隐藏银行卡信息提交
			/*$("#yxChangeOrderSelect").change(function(){ 
				var p1=$(this).children('option:selected').val();//获取selected的值 
				if(p1 != null && p1 != ""){
					 $("#yxServer_bank").hide();
					 $("#yxServer_bankCity").hide();
					 $("#yxServer_bankCN").hide();
					 $("#yxApproveSelectDiv").hide();
				}
			});*/
			//其他原因显示输入框
			/*$("#yxReasonSelect").change(function(){
				var s1=$(this).children('option:selected').val();
				if(s1 != null && s1 != "" && s1 == 10){
					//显示售后原因输入框
					$("#yxReasonDiv").show();
				}else{
					//隐藏售后原因输入框
					$("#yxReasonDiv").hide();
				}
			});*/
			
			
			/**查询订单可退工资总金额(不含:券,他人代收,白条支付,管家帮分期/海金保理分期,培训分期,唯品会白条,招行分期,唯品会白条支付,消费金额分期还款,汇嘉分期)*/
			function queryAfterReturnableSalary(paramObj){
				var money = 0;
				$.ajax({
					url: ctx+"/afterSalesNew/queryAfterReturnableSalary",
					data:paramObj,
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if (data.msg =="00") {
							money = data.result?data.result.REFUND_TOTAL:0;
						}
					}
				}); 
				return money;
			}
			
			/**查询订单可退客户信息费总金额*/
			function queryAfterReturnableMessageFee(paramObj){
				var money = 0;
				$.ajax({
					url: ctx+"/afterSalesNew/queryAfterReturnableMessageFee",
					data:paramObj,
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						if (data.msg =="00") {
							money = data.result?data.result.REFUND_TOTAL:0;
						}
					}
				});
				return money;
			}
			
			/**输入信息费或工资*/
			function inputMembershipFeeOrSalaryFee(){
				var membershipFee = $("#yxMembershipFee").val();//应退信息费金额
				var salaryFee = $("#yxSalaryFee").val();//应退服务人员服务费金额
				var yxRefundAll = 0;//应退信息费金额+应退服务人员服务费金额
				var afterReturnableMessageFee = $("#afterReturnableMessageFee").attr("value");//信息费最大可退金额
				var afterReturnableSalary = $("#afterReturnableSalary").attr("value");//服务费最大可退金额
				if(membershipFee && !isNaN(membershipFee)){
					if(membershipFee*1 > afterReturnableMessageFee*1){
						$.alert({"text":"应退信息费已超过信息费最大可退金额,请重新输入"});
						return;	
					}
					yxRefundAll += membershipFee*1;
				}
				if(salaryFee && !isNaN(salaryFee)){
					if(salaryFee*1 > afterReturnableSalary*1){
						$.alert({"text":"应退服务费已超过服务费最大可退金额,请重新输入"});
						return;	
					}
					yxRefundAll += salaryFee*1;
				}
				if(yxRefundAll > 0){
					$("#yxServer_bank").show();//银行、持卡人
					$("#yxServer_bankCity").show();//开户省份、开户城市
					$("#yxServer_bankCN").show();//银行卡号、开户行
				    $("#yxApproveSelectDiv").show();//审批部门、审批人
				}else{
					$("#yxServer_bank").hide();//银行、持卡人
					$("#yxServer_bankCity").hide();//开户省份、开户城市
					$("#yxServer_bankCN").hide();//银行卡号、开户行
					$("#yxApproveSelectDiv").hide();//审批部门、审批人
				}
				$("#yxServerRefund").html(intToDecimal(yxRefundAll));
				$("#yxRefundDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费:<span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>");
			}
	

