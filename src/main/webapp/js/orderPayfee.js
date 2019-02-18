/**
 * 缴费js
 */
//根据结算单ID查询缴费
function queryPayfeeByAccountId(){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/payfee/queryPayfee",
			data:{
				orderId:accountId,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				console.log(data);
				if(data.msg=='00'){
					var num = $.each(data.list,function(i,v){
						if(i==0){
							$("#payment_feeToDate").val(v.feeToDate);
						}
						if(v.accountStatus==1){
							$("input[name=feeSumAll]").prop('disabled','disabled');
							$("input[type=checkbox]").prop('disabled','disabled');
						}
						if(v.feePost==20250002){
							$("#checkPayfeeBanktrans").attr("checked","checked");
							$("#payfeeBanktrans").css("display","block");
							$("#payfeeIdBanktrans").val(v.id);
							$("#feeSumBanktrans").val(v.feeSum);
							$("#postBankBanktrans").val(v.postBank);
							$("#bankFlowNumBanktrans").val(v.bankFlowNum);
							$("#postUserBanktrans").val(v.postUser);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeBanktrans").css("display","block");
							}
							if (v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumBanktrans").attr("readonly","readonly");
								$("#postBankBanktrans").attr("readonly","readonly");
								$("#bankFlowNumBanktrans").attr("readonly","readonly");
								$("#postUserBanktrans").attr("readonly","readonly");
							}
						}else if(v.feePost==20250005){
							$("#checkPayfeePOS").attr("checked","checked");
							$("#payfeePos").css("display","block");
							$("#payfeeIdPos").val(v.id);
							$("#feeSumPos").val(v.feeSum);
							$("#bankFlowNumPos").val(v.bankFlowNum);
							$("#postNumPos").val(v.postNum);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_pos").prop("src",v.posImgUrl);
								$("#img_url_file_pos").html(v.posImgUrl);
							}
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeePos").css("display","block");
							}
							if (v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumPos").attr("readonly","readonly");
								$("#bankFlowNumPos").attr("readonly","readonly");
								$("#postNumPos").attr("readonly","readonly");
							}
						}else if(v.feePost==20250008){
							$("#checkPayfeeRhtxPOS").attr("checked","checked");
							$("#payfeeRhtxPos").css("display","block");
							$("#payfeeIdRhtxPos").val(v.id);
							$("#feeSumRhtxPos").val(v.feeSum);
							$("#bankFlowNumRhtxPos").val(v.bankFlowNum);
							$("#postTerminalNoRhtxPos").val(v.postTerminalNo);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_rhtxpos").prop("src",v.posImgUrl);
								$("#img_url_file_rhtxpos").html(v.posImgUrl);
							}
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeRhtxPos").css("display","block");
							}
							if (v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumRhtxPos").attr("readonly","readonly");
								$("#bankFlowNumRhtxPos").attr("readonly","readonly");
								$("#postTerminalNoRhtxPos").attr("readonly","readonly");
							}
						}
 						else if(v.feePost==20250010){
 							$("#labelPayfeeCard").show();
 							$("#checkPayfeeCard").attr("checked","checked");
 							$("#payfeeCard").css("display","block");
 							$("#feeSumCard").val(v.feeSum);
 						}
 						else if(v.feePost==20250011){
 							$("#labelPayfeeTicket").show();
 							$("#checkPayfeeTicket").attr("checked","checked");
 							$("#payfeeTicket").css("display","block");
 							$("#feeSumTicket").val(v.feeSum);
 						}
 						else if(v.feePost==20250013){
 							$("#labelPayfeeRemain").show();
 							$("#checkPayfeeRemain").attr("checked","checked");
 							$("#payfeeRemain").css("display","block");
 							$("#feeSumRemain").val(v.feeSum);
 						}
 						else if(v.feePost==20250014){
 							$("#payfeeIdTRDS").val(v.id);
 							$("#checkPayfeeTRDS").attr("checked","checked");
 							$("#payfeeTRDS").css("display","block");
 							$("#feeSumTRDS_DC").val(accountAmount);
 							$("#feeSumTRDS_PT").val(v.platformFee);
 							$("#feeSumTRDS_channel").val(v.collectionChannelText);
 							$("#feeSumTRDS_orderId").val(v.platformOrderId);
 							$("#TRDSChannelId").val(v.collectionChannel);
 							
 							if(v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumTRDS_DC").attr("readonly","readonly");
								$("#feeSumTRDS_PT").attr("readonly","readonly");
								$("#feeSumTRDS_channel").attr("readonly","readonly");
								$("#feeSumTRDS_orderId").attr("readonly","readonly");
								$("#TRDSChannelId").attr("readonly","readonly");
							}
 						}
						else if(v.feePost==20250015){
							$("#checkPayfeeWechat").attr("checked","checked");
							$("#payfeeWechat").css("display","block");
							$("#payfeeIdWechat").val(v.id);
							$("#feeSumWechat").val(v.feeSum);
							$("#bankFlowNumWechat").val(v.bankFlowNum);
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editPayfeeWechat").css("display","block");
							}
							if(v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumWechat").attr("readonly","readonly");
								$("#bankFlowNumWechat").attr("readonly","readonly");
							}
						}else if(v.feePost==20250016){
							$("#checkBaiTiaoPayfee").attr("checked","checked");
							$("#payfeeBaitiao").css("display","block");
							$("#baitiaoIdPayfee").val(v.id);
							$("#feeSumBaitiao").val(v.feeSum);
							if(v.payStatus==20110002 && v.accountStatus==1){
								$("#feeSumBaitiao").attr("readonly","readonly");
							}
						}else if(v.feePost==20250017){
							$("#checkJiaLianPosWechat").attr("checked","checked");
							$("#jiaLianPosWechat").css("display","block");
							$("#jiaLianIdPosWechat").val(v.id);
							$("#jialianPosWechatFee").val(v.feeSum);
							$("#jialianWechatPostNum").val(v.bankFlowNum);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_wechatpos").prop("src",v.posImgUrl);
								$("#img_url_file_wechatpos").html(v.posImgUrl);
							}
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editJiaLianPosWechat").css("display","block");
							}
							if(v.payStatus==20110002 && v.accountStatus==1){ 
								$("#jialianPosWechatFee").attr("readonly","readonly");
								$("#jialianWechatPostNum").attr("readonly","readonly");
							}
						}else if(v.feePost==20250018){
							$("#checkJiaLianPosAliPay").attr("checked","checked");
							$("#jiaLianPosAliPay").css("display","block");
							$("#jiaLianIdPosAliPay").val(v.id);
							$("#jialianPosAliPayFee").val(v.feeSum);
							$("#jialianAliPayPostNum").val(v.bankFlowNum);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_alipaypos").prop("src",v.posImgUrl);
								$("#img_url_file_alipaypos").html(v.posImgUrl);
							}
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editJiaLianPosAliPay").css("display","block");
							}
							if(v.payStatus==20110002 && v.accountStatus==1){ 
								$("#jialianPosAliPayFee").attr("readonly","readonly");
								$("#jialianAliPayPostNum").attr("readonly","readonly");
							}
						}else if(v.feePost==20250019){
							$("#checkJiaLianPosBank").attr("checked","checked");
							$("#jiaLianPosBank").css("display","block");
							$("#jiaLianIdPosBank").val(v.id);
							$("#jialianPosBankFee").val(v.feeSum);
							$("#jialianBankPostCKNum").val(v.bankFlowNum);
							$("#jialianBankPostNum").val(v.postNum);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_cardpos").prop("src",v.posImgUrl);
								$("#img_url_file_cardpos").html(v.posImgUrl);
							}
							if(payStatus==20110002 && v.accountStatus==2){
								$("#editJiaLianPosBank").css("display","block");
							}
							if(v.payStatus==20110002 && v.accountStatus==1){ 
								$("#jialianPosBankFee").attr("readonly","readonly");
								$("#jialianBankPostCKNum").attr("readonly","readonly");
								$("#jialianBankPostNum").attr("readonly","readonly");
							}
						}else if(v.feePost==20250027){
								$("#checkPayfeeInstallment").attr("checked","checked");
								$("#gjbInstallmentDiv").css("display","block");
								$("#gjbInstallmentId").val(v.id);
								$("#gjbInstallmentFee").val(v.feeSum);
								$("#gjbInstallment_Agreement").val(v.bankFlowNum);
								$("#gjbInstallment_vipShopName").val(v.vipShopName);
								if(v.payStatus==20110002 && v.accountStatus==1){
									$("#gjbInstallmentFee").attr("readonly","readonly");
									$("#gjbInstallment_Agreement").attr("readonly","readonly");
									$("#gjbInstallment_vipShopName").attr("readonly","readonly");
								}
						}else if(v.feePost==20250031){//招行分期
							$("#checkPayfeeCMB").attr("checked","checked");
							$("#CMBDiv").css("display","block");
							$("#CMBId").val(v.id);
							$("#CMBFee").val(v.feeSum);
							$("#CMBOrderId").val(v.bankFlowNum);
							if(v.payStatus==20110002 && v.accountStatus==1){
								$("#CMBFee").attr("readonly","readonly");
								$("#CMBOrderId").attr("readonly","readonly");
							}
						}else if(v.feePost==20250033){
							$("input[name=feePosts]").not("#checkHjbl").prop("disabled",true);
							$("#checkHjbl").prop("checked",true);
							$("#hjbl_div").css("display","block");
							$("#hjblFee").val(v.feeSum);
							$("#hjblName").val(v.hjblName);
							if(v.payStatus==20110002 && v.accountStatus==1){
								$("#hjblFee").prop("readonly",true);
							}
						}else if(v.feePost==20250036){//联动微信支付
							$("input[name=feePosts]").not("#checkLianDongWechat").prop("disabled",true);
							$("#checkLianDongWechat").prop("checked",true);
							$("#lianDongWechatDiv").css("display","block");
							$("#lianDongWechatFee").val(v.feeSum);
							$("#lianDongWechatNum").val(v.bankFlowNum);
						}else if(v.feePost==20250037){ //联动POS微信支付
							$("input[name=feePosts]").not("#checkLianDongPosWechat").prop("disabled",true);
							$("#checkLianDongPosWechat").attr("checked","checked");
							$("#lianDongPosWechatDiv").css("display","block");
							$("#lianDongIdPosWechat").val(v.id);
							$("#lianDongPosWechatFee").val(v.feeSum);
							$("#lianDongWechatPostNum").val(v.bankFlowNum);
							
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_Ldwechatpos").prop("src",v.posImgUrl);
								$("#img_url_file_Ldwechatpos").html(v.posImgUrl);
							}
						}else if(v.feePost==20250038){ //联动POS刷卡支付
							$("input[name=feePosts]").not("#checkLianDongPosBank").prop("disabled",true);
							$("#checkLianDongPosBank").prop("checked",true);
							$("#lianDongPosBankDiv").css("display","block");
							$("#lianDongPosBankFee").val(v.feeSum);
							$("#lianDongBankPostCKNum").val(v.bankFlowNum);
							$("#lianDongBankPostNum").val(v.postNum);
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_Ldcardpos").prop("src",v.posImgUrl);
								$("#img_url_file_Ldcardpos").html(v.posImgUrl);
							}
						}else if(v.feePost==20250039){ //掌易收微信支付
							$("input[name=feePosts]").not("#checkZhangYiWechat").prop("disabled",true);
							$("#checkZhangYiWechat").prop("checked",true);
							$("#zhangYiWechatDiv").css("display","block");
							$("#zhangYiWechatFee").val(v.feeSum);
							$("#zhangYiWechatNum").val(v.bankFlowNum);
						}else if(v.feePost==20250040){ //掌易收支付宝支付
							$("input[name=feePosts]").not("#checkZhangYiAliPay").prop("disabled",true);
							$("#checkZhangYiAliPay").prop("checked",true);
							$("#zhangYiAliPayDiv").css("display","block");
							$("#zhangYiAliPayFee").val(v.feeSum);
							$("#zhangYiAliPayNum").val(v.bankFlowNum);
						}else if(v.feePost==20250041){ //联动POS支付宝支付
							$("input[name=feePosts]").not("#checkLianDongPosAliPay").prop("disabled",true);
							$("#checkLianDongPosAliPay").attr("checked","checked");
							$("#lianDongPosAliPay").css("display","block");
							$("#lianDongIdPosAliPay").val(v.id);
							$("#lianDongPosAliPayFee").val(v.feeSum);
							$("#lianDongAliPayPostNum").val(v.bankFlowNum);
							
							if(v.posImgUrl != null && v.posImgUrl != ""){
								$("#img_file_Ldalipaypos").prop("src",v.posImgUrl);
								$("#img_url_file_Ldalipaypos").html(v.posImgUrl);
							}
						}else if(v.feePost==20250042){ //联动微信扫码支付
							$("input[name=feePosts]").not("#checkLianDongweixinPay").prop("disabled",true);
							$("#checkLianDongweixinPay").attr("checked","checked");
							$("#lianDongWeixinPay").css("display","block");
							$("#lianDongWeixinPayFee").val(v.feeSum);
							
						}else if(v.feePost==20250043){ //联动支付宝扫码支付
							$("input[name=feePosts]").not("#checkLianDongAliPay").prop("disabled",true);
							$("#checkLianDongAliPay").attr("checked","checked");
							$("#lianDongAliPay").css("display","block");
							$("#lianDongAliPayFee").val(v.feeSum);
						}else if(v.feePost=='20250046'){
							//联动静态扫码支付
							$("#checkLianDongJingTai").trigger("click");
							$("#feeSumLanDongJingTai").val(v.feeSum);
							$("#bankFlowNumLanDongJingTai").val(v.bankFlowNum);
						}else if(v.feePost=='20250052'){
							//管家帮收款码
							$("#checkGuanJiaBangShouKuanMa").trigger("click");
							$("#feeSumGuanJiaBangShouKuanMa").val(v.feeSum);
							$("#bankFlowNumGuanJiaBangShouKuanMa").val(v.bankFlowNum);
						}
					});
				}
			}
		});
	}
	function checkPayfees(fee){
		var form = $("#payfeeAddForm");
		if(payStatus==20110002){
			return ;
		}
		if(fee.value==20250002){
			if(fee.checked){
				$("#payfeeBanktrans").css("display","block");
			}else{
				$("#payfeeBanktrans").css("display","none");
			}
		}else if(fee.value==20250005){
			if(fee.checked){
				$("#payfeePos").css("display","block");
			}else{
				$("#payfeePos").css("display","none");
			}
		}else if(fee.value==20250008){
			if(fee.checked){
				$("#payfeeRhtxPos").css("display","block");
			}else{
				$("#payfeeRhtxPos").css("display","none");
			}
		}
 		/* else if(fee.value==20250010){
 			if(fee.checked){
 				$("#payfeeCard").css("display","block");
 			}else{
 				$("#payfeeCard").css("display","none");
 			}
 			//queryPayfeeLpk();
 		}
 		else if(fee.value==20250011){
 			if(fee.checked){
 				$("#payfeeTicket").css("display","block");
 			}else{
 				$("#payfeeTicket").css("display","none");
 			}
 		}
 		else if(fee.value==20250013){
 			if(fee.checked){
 				$("#payfeeRemain").css("display","block");
 			}else{
 				$("#payfeeRemain").css("display","none");
 			}
 		} */
		else if(fee.value==20250014){
			var id=queryChannel();
			var checkId=[212028286803190,212028286803254,212028286803228,212028286803244,149365093583765,502049883189605,900172833443030,353530320518358,425419964520278,337624045590614,160704998385543];
			if(fee.checked){
				if(checkId.contains(id)){
					$("#feeSumTRDS_PT").attr("readonly","readonly");
					$("#feeSumTRDS_PT").css('background-color','#c6c6c6');
				}
				$("#payfeeBanktrans").css("display","none");
				$("#payfeePos").css("display","none");
				$("#payfeeRhtxPos").css("display","none");
				$("#payfeeTRDS").css("display","block");
				$("#payfeeWechat").css("display","none");
				$("#jiaLianPosAliPay").css("display","none");
				$("#jiaLianPosWechat").css("display","none");
				$("#jiaLianPosBank").css("display","none");
				$("#gjbInstallmentDiv").css("display","none");
				$("#CMBDiv").css("display","none");
				$("#hjbl_div").css("display","none");
				
				$("#checkPayfeePOS").removeAttr("checked");
				$("#checkPayfeeRhtxPOS").removeAttr("checked");
				$("#checkPayfeeBanktrans").removeAttr("checked");
				$("#checkPayfeeWechat").removeAttr("checked");
				$("#checkJiaLianPosAliPay").removeAttr("checked");
				$("#checkJiaLianPosBank").removeAttr("checked");
				$("#checkJiaLianPosWechat").removeAttr("checked");
				$("#checkPayfeeInstallment").removeAttr("checked");
				$("#checkPayfeeCMB").removeAttr("checked");
				$("#checkHjbl").removeAttr("checked");
				
				$("#payfeeTRDS").css("display","block");
				
				$("#checkPayfeePOS").attr("disabled", "true");
				$("#checkPayfeeRhtxPOS").attr("disabled", "true");
				$("#checkPayfeeBanktrans").attr("disabled", "true");
				$("#checkPayfeeWechat").attr("disabled", "true");
				$("#feeSumTRDS_DC").val(accountAmount);
				$("#checkJiaLianPosAliPay").attr("disabled", "true");
				$("#checkJiaLianPosBank").attr("disabled", "true");
				$("#checkJiaLianPosWechat").attr("disabled", "true");
				$("#checkPayfeeInstallment").attr("disabled", "true");
				$("#checkPayfeeCMB").attr("disabled", "true");
				$("#checkHjbl").attr("disabled", "true");
			}else{
				$("#payfeeTRDS").css("display","none");
				$("#checkPayfeePOS").removeAttr("disabled");
				$("#checkPayfeeRhtxPOS").removeAttr("disabled");
				$("#checkPayfeeBanktrans").removeAttr("disabled");
				$("#checkPayfeeWechat").removeAttr("disabled");
				$("#checkJiaLianPosAliPay").removeAttr("disabled");
				$("#checkJiaLianPosBank").removeAttr("disabled");
				$("#checkJiaLianPosWechat").removeAttr("disabled");
				$("#checkPayfeeInstallment").removeAttr("disabled");
				$("#checkPayfeeCMB").removeAttr("disabled");
				$("#checkHjbl").removeAttr("disabled");
			}
		}else if(fee.value==20250015){
			if(fee.checked){
				$("#payfeeWechat").css("display","block");
			}else{
				$("#payfeeWechat").css("display","none");
			}
		}else if(fee.value==20250016){
			if(fee.checked){
				$("#payfeeBaitiao").css("display","block");
				$("#payfeeBaitiaoNull").css("display","none");
				checkBaiTiaoByUserId(userId);
			}else{
				$("#payfeeBaitiao").css("display","none");
			}
		}else if(fee.value==20250017){
			   //嘉联POS微信支付,单选
			   if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkJiaLianPosWechat").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#jiaLianPosWechat").hide();
			    $("#jiaLianPosWechat").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#jiaLianPosWechat").hide();
			   }
		}else if(fee.value==20250018){
			   //嘉联pos支付宝支付,单选
			   if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkJiaLianPosAliPay").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#jiaLianPosAliPay").hide();
			    $("#jiaLianPosAliPay").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#jiaLianPosAliPay").hide();
			   }
		}else if(fee.value==20250019){
			//嘉联pos刷卡支付
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkJiaLianPosBank").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#jiaLianPosBank").hide();
			    $("#jiaLianPosBank").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#jiaLianPosBank").hide();
			   }
		}else if(fee.value==20250027){
			if(fee.checked){
				$("#payfeeBanktrans").css("display","none");
				$("#payfeePos").css("display","none");
				$("#payfeeRhtxPos").css("display","none");
				$("#payfeeTRDS").css("display","none");
				$("#payfeeWechat").css("display","none");
				$("#jiaLianPosBank").css("display","none");
				$("#jiaLianPosWechat").css("display","none");
				$("#jiaLianPosBank").css("display","none");
				$("#gjbInstallmentDiv").css("display","block");
				$("#CMBDiv").css("display","none");
				$("#hjbl_div").css("display","none");
				
				$("#checkPayfeePOS").removeAttr("checked");
				$("#checkPayfeeRhtxPOS").removeAttr("checked");
				$("#checkPayfeeBanktrans").removeAttr("checked");
				$("#checkPayfeeWechat").removeAttr("checked");
				$("#checkJiaLianPosWechat").removeAttr("checked");
				$("#checkJiaLianPosAliPay").removeAttr("checked");
				$("#checkJiaLianPosBank").removeAttr("checked");
				$("#checkPayfeeCMB").removeAttr("checked");
				$("#checkPayfeeTRDS").removeAttr("checked");
				$("#checkHjbl").removeAttr("checked");
				
				$("#gjbInstallmentDiv").css("display","block");
				
				$("#checkPayfeePOS").attr("disabled", "true");
				$("#checkPayfeeRhtxPOS").attr("disabled", "true");
				$("#checkPayfeeBanktrans").attr("disabled", "true");
				$("#checkPayfeeWechat").attr("disabled", "true");
				$("#checkJiaLianPosWechat").attr("disabled", "true");
				$("#checkJiaLianPosAliPay").attr("disabled", "true");
				$("#checkJiaLianPosBank").attr("disabled", "true");
				$("#checkPayfeeCMB").attr("disabled", "true");
				$("#checkPayfeeTRDS").attr("disabled", "true");
				$("#checkHjbl").attr("disabled", "true");
			}else{
				$("#gjbInstallmentDiv").css("display","none");
				
				/*$("#checkPayfeePOS").removeAttr("disabled");
				$("#checkPayfeeRhtxPOS").removeAttr("disabled");
				$("#checkPayfeeBanktrans").removeAttr("disabled");
				$("#checkPayfeeWechat").removeAttr("disabled");
				$("#checkJiaLianPosWechat").removeAttr("disabled");
				$("#checkJiaLianPosAliPay").removeAttr("disabled");
				$("#checkJiaLianPosBank").removeAttr("disabled");
				$("#checkPayfeeCMB").removeAttr("disabled");
				$("#checkPayfeeTRDS").removeAttr("disabled");
				$("#checkHjbl").removeAttr("disabled");*/
				$("input[name=feePosts]").removeAttr("disabled");
				
			}
		}else if(fee.value==20250031){
			if(fee.checked){
				$("#CMBDiv").css("display","block");
			}else{
				$("#CMBDiv").css("display","none");
			}
		}else if(fee.value==20250033){
			//管家帮分期=海金保理分期,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkHjbl").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#hjbl_div").hide();
			    $("#hjbl_div").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#hjbl_div").hide();
			   }
		}else if(fee.value==20250036){
			//联动微信支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongWechat").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongWechatDiv").hide();
			    $("#lianDongWechatDiv").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongWechatDiv").hide();
			}
		}else if(fee.value==20250037){ 
			//联动POS微信支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongPosWechat").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongPosWechatDiv").hide();
			    $("#lianDongPosWechatDiv").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongPosWechatDiv").hide();
			}
		}else if(fee.value==20250038){ 
			//联动POS刷卡支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongPosBank").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongPosBankDiv").hide();
			    $("#lianDongPosBankDiv").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongPosBankDiv").hide();
			}
		}else if(fee.value==20250039){ 
			//掌易收微信支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkZhangYiWechat").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#zhangYiWechatDiv").hide();
			    $("#zhangYiWechatDiv").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#zhangYiWechatDiv").hide();
			}
		}else if(fee.value==20250040){
			//掌易收支付宝支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkZhangYiAliPay").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#zhangYiAliPayDiv").hide();
			    $("#zhangYiAliPayDiv").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#zhangYiAliPayDiv").hide();
			}
		}else if(fee.value==20250041){
			//联动POS支付宝支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongPosAliPay").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongPosAliPay").hide();
			    $("#lianDongPosAliPay").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongPosAliPay").hide();
			}
		}else if(fee.value==20250042){ 
			//联动微信扫码支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongweixinPay").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongWeixinPay").hide();
			    $("#lianDongWeixinPay").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongWeixinPay").hide();
			}
		}else if(fee.value==20250043){ 
			//联动支付宝扫码支付,单选
			if(fee.checked){ 
			    form.find("input[name=feePosts]").not("#checkLianDongAliPay").removeAttr("checked").prop("disabled",true);
			    form.find("div[name=payFeeDiv]").not("#lianDongAliPay").hide();
			    $("#lianDongAliPay").show();
			   }else{
			    form.find("input[name=feePosts]").removeAttr("disabled");
			    $("#lianDongAliPay").hide();
			}
		}else if(fee.value==20250046){
			//联动静态扫码支付,单选
			var form = $("#payfeeAddForm");
			if(fee.checked){ 
				form.find("input[name=feePosts]").not("#checkLianDongJingTai").removeAttr("checked").prop("disabled",true);
				form.find("div[name=payFeeDiv]").not("#payfeeLanDongJingTai").hide();
				$("#payfeeLanDongJingTai").show();
			}else{
				form.find("input[name=feePosts]").removeAttr("disabled");
				$("#payfeeLanDongJingTai").hide();
			}
		}else if(fee.value==20250052){
			//管家帮收款码,单选
			var form = $("#payfeeAddForm");
			if(fee.checked){ 
				form.find("input[name=feePosts]").not("#checkGuanJiaBangShouKuanMa").removeAttr("checked").prop("disabled",true);
				form.find("div[name=payFeeDiv]").not("#payfeeLanDongJingTai").hide();
				$("#payfeeGuanJiaBangShouKuanMa").show();
			}else{
				form.find("input[name=feePosts]").removeAttr("disabled");
				$("#payfeeGuanJiaBangShouKuanMa").hide();
			}
		}else if(fee.value==20250010){
			//管家卡支付
			var form = $("#payfeeAddForm");
			if(fee.checked){ 
				form.find("input[name=feePosts]").not("#checkGuanJiaKaZhiFu").removeAttr("checked").prop("disabled",true);
				form.find("div[name=payFeeDiv]").not("#payfeeGuanJiaKaZhiFu").hide();
				$("#payfeeGuanJiaKaZhiFu").show();
			}else{
				form.find("input[name=feePosts]").removeAttr("disabled");
				$("#payfeeGuanJiaKaZhiFu").hide();
			}
		}
	}
	
	function queryPayfeeLpk(){
		var ctx = $("#ctx").val();
		$.ajax({
			url: ctx +"/payfee/queryPayfeeLpk",
			data:{
				orderId:""
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html="";
				if (data.msg =="00") {
					var num = $.each(data.list,function(i,v){
						html += "<tr onclick='checkCards(this);'><td width='120px;'>" ;
						html += "<input type='radio' name='radiopayfeeCard' " ;
						html += "value='" +v.id +"," +v.cardNumb +"," +v.cardBalance +"' >" ;
						html += v.cardNumb +"</td>";
						html += "<td width='120px;'>余额：<span>" +v.cardBalance +"</span>元</td>";
						html += "<td width='120px;'>卡名：</td>" ;
						html += "<td width='120px;'>有效期：" +v.validTime +"</td>" ;
					});
				}
				$("#cardListBody").html(html);
			}
		});
		
	}
	function insertPayfee(){
		var  checkPayfeePOS= document.getElementById('checkPayfeePOS');		
		if(checkPayfeePOS.checked === true){
			 //pos机
			var feeSumPos = $.trim($("#feeSumPos").val());
			var postNumPos = $.trim($("#postNumPos").val());
			var bankFlowNumPos = $.trim($("#bankFlowNumPos").val());			
			var posImg = $("#img_url_file_pos").text();
			
			if(feeSumPos==null||feeSumPos==""){
				$.alert({text:"pos机缴费金额不能为空！"});
				return; 
			}
			if(postNumPos==null||postNumPos==""){
				$.alert({text:"卡号后四位不能为空！"});
				return; 
			}
			if(bankFlowNumPos==null || bankFlowNumPos==""){
				$.alert({text:"交易参考号不能为空！"});
				return;
			}
			if(posImg==null||posImg==""){
				$.alert({text:"pos凭条不能为空！"});
				return;
			}
		}
		var  checkPayfeeRhtxPOS= document.getElementById('checkPayfeeRhtxPOS');		
		if(checkPayfeeRhtxPOS.checked === true){
			 //融汇天下pos机
			var posImgRhtx = $("#img_url_file_rhtxpos").text();
			
			if(posImgRhtx==null||posImgRhtx==""){
				$.alert({text:"pos凭条不能为空！"});
				return;
			}
		}
		
		 var  checkPayfeeBanktrans= document.getElementById('checkPayfeeBanktrans');
			if(checkPayfeeBanktrans.checked === true){
				//银行转账
				var feeSumBanktrans = $.trim($("#feeSumBanktrans").val());
				var postBankBanktrans = $.trim($("#postBankBanktrans").val());
				var bankFlowNumBanktrans = $.trim($("#bankFlowNumBanktrans").val());
				var postUserBanktrans = $.trim($("#postUserBanktrans").val());
				if(feeSumBanktrans==null||feeSumBanktrans==""){
					$.alert({text:"银行缴费金额不可以为空！"});
					return; 
				}
// 				if(postBankBanktrans==null||postBankBanktrans==""){
// 					$.alert({text:"银行名称不能为空！"});
// 					return; 
// 				} 
				/* if(bankFlowNumBanktrans==null||bankFlowNumBanktrans==""){
 					$.alert({text:"流水号不能为空！"});
 					return; 
				} */
				if(postUserBanktrans==null||postUserBanktrans==""){
					$.alert({text:"汇款人不能为空！"});
					return; 
				}  
			}
		
		 var  checkPayfeeWechat= document.getElementById('checkPayfeeWechat');
		 if(checkPayfeeWechat.checked === true){
			//微信扫码支付
				var feeSumWechat = $.trim($("#feeSumWechat").val());
				var bankFlowNumWechat = $.trim($("#bankFlowNumWechat").val());
				if(feeSumWechat==null||feeSumWechat==""){
					$.alert({text:"微信缴费金额不能为空！"});
					return; 
				}
				if(bankFlowNumWechat==null||bankFlowNumWechat==""){
					$.alert({text:"商户单号不能为空！"});
					return; 
				}  
			}
		 var  checkPayfeeTRDS= document.getElementById('checkPayfeeTRDS');
		 if(checkPayfeeTRDS.checked === true){
			//他人代收
				//var feeSumTRDS = $.trim($("#feeSumTRDS_PT").val());
				var TRDSOrderId = $.trim($("#feeSumTRDS_orderId").val());
				/*if(feeSumTRDS==null||feeSumTRDS==""){
					$.alert({text:"他人代收平台实付金额不能为空！"});
					return; 
				}*/
				if(TRDSOrderId==null||TRDSOrderId==""){
					$.alert({text:"平台订单编号不能为空！"});
					return; 
				}  
			}
		 var managerTypeBaitiao = $("#managerTypePayfee").val();
		 if(managerTypeBaitiao != null && managerTypeBaitiao == 2){
			 var checkBaiTiaoPayfee = document.getElementById('checkBaiTiaoPayfee');
				if(checkBaiTiaoPayfee.checked === true){
					 //白条支付
					var feeBaitiao = $.trim($("#feeSumBaitiao").val());
					if((feeBaitiao==null||feeBaitiao=="") && $("#payfeeBaitiaoNull").is(":hidden")){
						$.alert({text:"白条支付缴费金额不能为空！"});
						return; 
					}  
				}
		 }
		 var checkJiaLianPosWechat = document.getElementById('checkJiaLianPosWechat');
			if(checkJiaLianPosWechat.checked === true){
				 //嘉联支付pos微信支付
				var feePosWechat = $.trim($("#jialianPosWechatFee").val());
				var postNumPosWechat = $.trim($("#jialianWechatPostNum").val());
				var posImgWechat = $("#img_url_file_wechatpos").text();
				if(feePosWechat==null||feePosWechat==""){
					$.alert({text:"嘉联支付pos微信支付缴费金额不能为空！"});
					return; 
				}
				if(postNumPosWechat==null||postNumPosWechat==""){
					$.alert({text:"嘉联支付pos微信支付交易参考号不能为空！"});
					return; 
				}
				/*if(posImgWechat==null||posImgWechat==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}*/
			}
		 var checkJiaLianPosAliPay = document.getElementById('checkJiaLianPosAliPay');
			if(checkJiaLianPosAliPay.checked === true){
				 //嘉联支付pos支付宝支付
				var feePosAliPay = $.trim($("#jialianPosAliPayFee").val());
				var postNumPosAliPay = $.trim($("#jialianAliPayPostNum").val());
				var posImgAlipay = $("#img_url_file_alipaypos").text();
				if(feePosAliPay==null||feePosAliPay==""){
					$.alert({text:"嘉联支付pos支付宝支付缴费金额不能为空！"});
					return; 
				}
				if(postNumPosAliPay==null||postNumPosAliPay==""){
					$.alert({text:"嘉联支付pos支付宝支付交易参考号不能为空！"});
					return; 
				}
				/*if(posImgAlipay==null||posImgAlipay==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}*/
			}
		 var checkJiaLianPosBank = document.getElementById('checkJiaLianPosBank');
			if(checkJiaLianPosBank.checked === true){
				 //嘉联支付pos刷卡支付
				var feePosBank = $.trim($("#jialianPosBankFee").val());
				var feePosCKBank = $.trim($("#jialianBankPostCKNum").val());
				var postNumPosBank = $.trim($("#jialianBankPostNum").val());
				var posImgBank = $("#img_url_file_cardpos").text();
				if(feePosBank==null||feePosBank==""){
					$.alert({text:"嘉联支付pos刷卡支付缴费金额不能为空！"});
					return; 
				}
				if(feePosCKBank==null||feePosCKBank==""){
					$.alert({text:"嘉联支付pos刷卡支付交易参考号不能为空！"});
					return; 
				}
				if(posImgBank==null||posImgBank==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}
			}
		 var checkPayfeeInstallment = document.getElementById('checkPayfeeInstallment');
			if(checkPayfeeInstallment.checked === true){
				 //管家帮分期支付
				var feeInstallment = $.trim($("#gjbInstallmentFee").val());
				var installmentAgreement = $.trim($("#gjbInstallment_Agreement").val());
				var installmentVipShopName = $.trim($("#gjbInstallment_vipShopName").val());
				if(feeInstallment==null || feeInstallment==""){
					$.alert({text:"管家帮分期支付缴费金额不能为空！"});
					return; 
				}
				if(installmentAgreement==null || installmentAgreement==""){
					$.alert({text:"管家帮分期支付合同编号不能为空！"});
					return; 
				}
				if(installmentVipShopName==null || installmentVipShopName==""){
					$.alert({text:"管家帮分期签约人姓名不能为空！"});
					return; 
				}
			}
		  
			
			var  checkPayfeeCMB= document.getElementById('checkPayfeeCMB');
			if(checkPayfeeCMB.checked === true){
				//招行分期
				var CMBFee = $.trim($("#CMBFee").val());
				var CMBOrderId = $.trim($("#CMBOrderId").val());
				if(CMBFee == null||CMBFee == ""){
					$.alert({text:"招行分期金额不能为空！"});
					return; 
				}
				if(CMBOrderId == null||CMBOrderId == ""){
					$.alert({text:"交易流水码/订单号不能为空！"});
					return; 
				}  
			}
			var checkHjbl= document.getElementById('checkHjbl');
			if(checkHjbl.checked === true){
				if(!$("#hjblFee").val()){
					$.alert({text:"扣款金额不能为空！"});
					return; 
				}
				if(!$("#hjblName").val()){
					$.alert({text:"扣款人姓名不能为空！"});
					return; 
				}
			}
			
			
			var checkLianDongWechat= document.getElementById('checkLianDongWechat');//联动微信支付
			if(checkLianDongWechat.checked === true){
				if(!$("#lianDongWechatFee").val()){
					$.alert({text:"扣款金额不能为空！"});
					return; 
				}
				if(!$("#lianDongWechatNum").val()){
					$.alert({text:"交易参考号不能为空！"});
					return; 
				}
			}
			
			var checkLianDongPosWechat= document.getElementById('checkLianDongPosWechat');//联动POS微信支付
			if(checkLianDongPosWechat.checked === true){
				var feePosWechat = $.trim($("#lianDongPosWechatFee").val());
				var postNumPosWechat = $.trim($("#lianDongWechatPostNum").val());
				var posImgWechat = $("#img_url_file_Ldwechatpos").text();
				if(feePosWechat==null||feePosWechat==""){
					$.alert({text:"联动POS微信支付缴费金额不能为空！"});
					return; 
				}
				/*if(postNumPosWechat==null||postNumPosWechat==""){
					$.alert({text:"联动POS微信支付交易参考号不能为空！"});
					return; 
				}
				if(posImgWechat==null||posImgWechat==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}*/
			}
			
			var checkLianDongPosBank= document.getElementById('checkLianDongPosBank');//联动POS刷卡支付
			if(checkLianDongPosBank.checked === true){
				var feePosBank = $.trim($("#lianDongPosBankFee").val());
				var feePosCKBank = $.trim($("#lianDongBankPostCKNum").val());
				var postNumPosBank = $.trim($("#lianDongBankPostNum").val());
				var posImgBank = $("#img_url_file_Ldcardpos").text();
				
				if(feePosBank==null||feePosBank==""){
					$.alert({text:"联动POS刷卡支付缴费金额不能为空！"});
					return; 
				}
				/*if(feePosCKBank==null||feePosCKBank==""){
					$.alert({text:"联动POS刷卡支付交易参考号不能为空！"});
					return; 
				}
				if(posImgBank==null||posImgBank==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}*/
			}
			
			var checkZhangYiWechat= document.getElementById('checkZhangYiWechat');//掌易收微信支付
			if(checkZhangYiWechat.checked === true){
				if(!$("#zhangYiWechatFee").val()){
					$.alert({text:"扣款金额不能为空！"});
					return; 
				}
				if(!$("#zhangYiWechatNum").val()){
					$.alert({text:"交易参考号不能为空！"});
					return; 
				}
			}
			
			var checkZhangYiAliPay= document.getElementById('checkZhangYiAliPay');//掌易收支付宝支付
			if(checkZhangYiAliPay.checked === true){
				if(!$("#zhangYiAliPayFee").val()){
					$.alert({text:"扣款金额不能为空！"});
					return; 
				}
				if(!$("#zhangYiAliPayNum").val()){
					$.alert({text:"交易参考号不能为空！"});
					return; 
				}
			}
			
			
			var checkLianDongPosAliPay= document.getElementById('checkLianDongPosAliPay');//联动POS支付宝支付
			if(checkLianDongPosAliPay.checked === true){
				var feePosAliPay = $.trim($("#lianDongPosAliPayFee").val());
				var postNumPosAliPay = $.trim($("#lianDongAliPayPostNum").val());
				var posImgAlipay = $("#img_url_file_Ldalipaypos").text();
				
				if(feePosAliPay==null||feePosAliPay==""){
					$.alert({text:"联动POS支付宝支付缴费金额不能为空！"});
					return; 
				}
				/*if(postNumPosAliPay==null||postNumPosAliPay==""){
					$.alert({text:"联动POS支付宝支付交易参考号不能为空！"});
					return; 
				}
				if(posImgAlipay==null||posImgAlipay==""){
					$.alert({text:"pos凭条不能为空！"});
					return;
				}*/
			}
			var checkLianDongweixinPay= document.getElementById('checkLianDongweixinPay');//联动微信扫码支付
			/*if(checkLianDongweixinPay.checked === true){
				var feePosAliPay = $.trim($("#lianDongWeixinPayFee").val());
				
				if(feePosAliPay==null||feePosAliPay==""){
					$.alert({text:"联动微信扫码支付缴费金额不能为空！"});
					return; 
				}
			}
			*/
			var checkLianDongAliPay= document.getElementById('checkLianDongAliPay');//联动支付宝扫码支付
			/*if(checkLianDongAliPay.checked === true){
				var feePosAliPay = $.trim($("#lianDongAliPayFee").val());
				
				if(feePosAliPay==null||feePosAliPay==""){
					$.alert({text:"联动支付宝扫码支付缴费金额不能为空！"});
					return; 
				}
			}*/
			
			
			var checkLianDongJingTai= document.getElementById('checkLianDongJingTai');//联动静态扫码支付
			if(checkLianDongJingTai.checked === true){
				if(!$("#feeSumLanDongJingTai").val()){
					$.alert({text:"缴费金额不能为空！"});
					return; 
				}
				if(!$("#bankFlowNumLanDongJingTai").val()){
					$.alert({text:"商户订单号不能为空！"});
					return; 
				}
			}
			
			var checkGuanJiaBangShouKuanMa= document.getElementById('checkGuanJiaBangShouKuanMa');//管家帮收款码
			if(checkGuanJiaBangShouKuanMa.checked === true){
				if(!$("#feeSumGuanJiaBangShouKuanMa").val()){
					$.alert({text:"缴费金额不能为空！"});
					return; 
				}
				if(!$("#bankFlowNumGuanJiaBangShouKuanMa").val()){
					$.alert({text:"交易参考号不能为空！"});
					return; 
				}
				if($("#bankFlowNumGuanJiaBangShouKuanMa").val().length<6){
					$.alert({text:"交易参考号须为六位！"});
					return; 
				}
			}
			var checkGuanJiaKaZhiFU= document.getElementById('checkGuanJiaKaZhiFu');//管家卡支付
			/*if(checkGuanJiaKaZhiFU.checked === true){
				if(!$("#feeSumGuanJiaKaZhiFu").val()){
					$.alert({text:"缴费金额不能为空！"});
					return; 
				}*/
				/*var Gdate = $("#payment_feeToDate").val();
				if(Gdate==null|| Gdate==""){
					$.alert({text:"缴费时间不能为空！"});
					return;
				}*/
			//}
			
		  
		var ctx = $("#ctx").val();
		//提示信息
		var message = "您本次支付为";
		var feesum = 0;
		var feeToDate = $("#payment_feeToDate").val();
		//|| checkLianDongweixinPay.checked === true || checkLianDongAliPay.checked === true
		if(checkLianDongPosWechat.checked === true || checkLianDongPosBank.checked === true || checkLianDongPosAliPay.checked === true){
			
		}else{
			if(feeToDate==null|| feeToDate==""){
				$.alert({text:"缴费时间不能为空！"});
				return;
			}
		}
		var fee = "[" ;
		// 融汇天下pos作单独校验
		var rhtx = false ;
		$('input[name="feePosts"]:checked').each(function(i){ 
			if(i>0){
				fee += "," ;
			}
			if($(this).val()==20250002 && $("#feeSumBanktrans").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250002 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumBanktrans").val() +"'" ;
				fee += ",'postBank':'" +$.trim($("#postBankBanktrans").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#bankFlowNumBanktrans").val()) +"'" ;
				fee += ",'postUser':'" +$.trim($("#postUserBanktrans").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'" +payStatus +"'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumBanktrans").val());
				
				message +="银行转账:"+intToDecimal($("#feeSumBanktrans").val()) +"元，" ;
			}else if($(this).val()==20250005 && $("#feeSumPos").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250005 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumPos").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#bankFlowNumPos").val() +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_pos").text() +"'" ;//pos凭条
				fee += ",'postNum':'" +$.trim($("#postNumPos").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumPos").val());
				message +="POS机:"+intToDecimal($("#feeSumPos").val()) +"元，" ;
			}else if($(this).val()==20250008 && $("#feeSumRhtxPos").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250008 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumRhtxPos").val() +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#bankFlowNumRhtxPos").val()) +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_rhtxpos").text() +"'" ;//pos凭条
				fee += ",'postTerminalNo':'" +$.trim($("#postTerminalNoRhtxPos").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'2'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				var data = "{'jiaofei':'" +$.trim($("#feeSumRhtxPos").val())+"','terminalNo':'" +$.trim($("#postTerminalNoRhtxPos").val())+"','referenceNo':'" +$.trim($("#bankFlowNumRhtxPos").val())+"'}" ;
				$.ajax({
					url: ctx +"/payfee/posSuccessService",
					data:{
						data:data
					},
					type:"post",
					dataType:"json",
					async:false,
					success:function(data){
						var html="";
						if (data.msg !="00") {
							rhtx = true;
						}
					}
				});
				feesum += parseFloat($("#feeSumRhtxPos").val());
				message +="融汇天下POS:"+intToDecimal($("#feeSumRhtxPos").val()) +"元，" ;
			}else if($(this).val()==20250010 && $("#feeSumCard").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250010 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumCard").val() +"'" ;
				var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
				fee += ",'cardsNum':'" +card.children("td").eq(0).text() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumCard").val());
				
			}else if($(this).val()==20250014 && $("#feeSumTRDS_DC").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250014 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumTRDS_DC").val() +"'" ;
				fee += ",'platformFee':'" +$("#feeSumTRDS_PT").val() +"'" ;
				fee += ",'collectionChannel':'" +$.trim($("#TRDSChannelId").val()) +"'" ;
				fee += ",'platformOrderId':'" +$.trim($("#feeSumTRDS_orderId").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'1'" ;
				fee += ",'payStatus':'20110002'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumTRDS_DC").val());
				message +="他人代收:"+intToDecimal($("#feeSumTRDS_DC").val()) +"元，" ;
			}else if($(this).val()==20250015 && $("#feeSumWechat").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250015 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumWechat").val() +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#bankFlowNumWechat").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumWechat").val());
				message +="微信扫码支付:"+intToDecimal($("#feeSumWechat").val()) +"元，" ;
			}else if($(this).val()==20250016 && $("#feeSumBaitiao").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250016 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#feeSumBaitiao").val() +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'1'" ;
				fee += ",'payStatus':'20110002'" ;
				fee +="}";
				feesum += parseFloat($("#feeSumBaitiao").val());
				message +="白条支付:"+intToDecimal($("#feeSumBaitiao").val()) +"元，" ;
			}else if($(this).val()==20250017 && $("#jialianPosWechatFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250017 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#jialianPosWechatFee").val() +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#jialianWechatPostNum").val()) +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_wechatpos").text() +"'" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'3'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#jialianPosWechatFee").val());
				message +="嘉联POS微信支付:"+intToDecimal($("#jialianPosWechatFee").val()) +"元，" ;
			}else if($(this).val()==20250018 && $("#jialianPosAliPayFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250018 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#jialianPosAliPayFee").val() +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#jialianAliPayPostNum").val()) +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_alipaypos").text() +"'" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'4'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#jialianPosAliPayFee").val());
				message +="嘉联POS支付宝支付:"+intToDecimal($("#jialianPosAliPayFee").val()) +"元，" ;
			}else if($(this).val()==20250019 && $("#jialianPosBankFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250019 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#jialianPosBankFee").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#jialianBankPostCKNum").val() +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_cardpos").text() +"'" ;//pos凭条
				fee += ",'postNum':'" +$.trim($("#jialianBankPostNum").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'5'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#jialianPosBankFee").val());
				message +="嘉联POS刷卡支付:"+intToDecimal($("#jialianPosBankFee").val()) +"元，" ;
			}else if($(this).val()==20250027 && $("#gjbInstallmentFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250027 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#gjbInstallmentFee").val() +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#gjbInstallment_Agreement").val()) +"'" ;
				fee += ",'vipShopName':'" +installmentVipShopName +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#gjbInstallmentFee").val());
				message +="唯品会白条支付:"+intToDecimal($("#gjbInstallmentFee").val()) +"元，" ;
			}else if($(this).val()==20250031 && $("#CMBFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250031 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#CMBFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#CMBOrderId").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110002'" ;
				fee +="}";
				feesum += parseFloat($("#CMBFee").val());
				message +="招行分期支付:"+intToDecimal($("#CMBFee").val()) +"元，" ;
			}else if($(this).val()==20250033 && $("#hjblFee").val()>0.0){
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250033 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#hjblFee").val()) +"'" ;
				fee += ",'hjblName':'" +$.trim($("#hjblName").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110002'" ;
				fee +="}";
				feesum += parseFloat($("#hjblFee").val());
				message +="管家帮分期支付:"+intToDecimal($("#hjblFee").val()) +"元，" ;
			} else if($(this).val()==20250036 && $("#lianDongWechatFee").val()>0.0){//联动微信支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250036 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#lianDongWechatFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#lianDongWechatNum").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongWechatFee").val());
				message +="联动微信支付:"+intToDecimal($("#lianDongWechatFee").val()) +"元，" ;
			}else if($(this).val()==20250037 && $("#lianDongPosWechatFee").val()>0.0){//联动POS微信支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250037 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#lianDongPosWechatFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#lianDongWechatPostNum").val()) +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_Ldwechatpos").text() +"'" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'6'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongPosWechatFee").val());
				message +="联动微信支付:"+intToDecimal($("#lianDongPosWechatFee").val()) +"元，" ;
			}else if($(this).val()==20250038 && $("#lianDongPosBankFee").val()>0.0){//联动POS刷卡支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250038 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$("#lianDongPosBankFee").val() +"'" ;
				fee += ",'bankFlowNum':'" +$("#lianDongBankPostCKNum").val() +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_Ldcardpos").text() +"'" ;//pos凭条
				fee += ",'postNum':'" +$.trim($("#lianDongBankPostNum").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'7'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongPosBankFee").val());
				message +="联动POS刷卡支付:"+intToDecimal($("#lianDongPosBankFee").val()) +"元，" ;
			}else if($(this).val()==20250039 && $("#zhangYiWechatFee").val()>0.0){//掌易收微信支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250039 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#zhangYiWechatFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#zhangYiWechatNum").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#zhangYiWechatFee").val());
				message +="掌易收微信支付:"+intToDecimal($("#zhangYiWechatFee").val()) +"元，" ;
			}else if($(this).val()==20250040 && $("#zhangYiAliPayFee").val()>0.0){//掌易收支付宝支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250040 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#zhangYiAliPayFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#zhangYiAliPayNum").val()) +"'" ;
				fee += ",'feeType':'1'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110002'" ;
				fee +="}";
				feesum += parseFloat($("#zhangYiAliPayFee").val());
				message +="掌易收支付宝支付:"+intToDecimal($("#zhangYiAliPayFee").val()) +"元，" ;
			}else if($(this).val()==20250041 && $("#lianDongPosAliPayFee").val()>0.0){//联动POS支付宝支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250041 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#lianDongPosAliPayFee").val()) +"'" ;
				fee += ",'bankFlowNum':'" +$.trim($("#lianDongAliPayPostNum").val()) +"'" ;
				fee += ",'posImgUrl':'" +$("#img_url_file_Ldalipaypos").text() +"'" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'8'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongPosAliPayFee").val());
				message +="联动微信支付:"+intToDecimal($("#lianDongPosAliPayFee").val()) +"元，" ;
			}else if($(this).val()==20250042 && $("#lianDongWeixinPayFee").val()>0.0){//联动微信扫码支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250042 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#lianDongWeixinPayFee").val()) +"'" ;
				fee += ",'bankFlowNum':''" ;
				fee += ",'posImgUrl':''" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'8'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongWeixinPayFee").val());
				message +="联动微信扫码支付:"+intToDecimal($("#lianDongWeixinPayFee").val()) +"元，" ;
			}else if($(this).val()==20250043 && $("#lianDongAliPayFee").val()>0.0){//联动支付宝扫码支付
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'" +20250043 +"'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +$.trim($("#lianDongAliPayFee").val()) +"'" ;
				fee += ",'bankFlowNum':''" ;
				fee += ",'posImgUrl':''" ;//pos凭条
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':'1'" ;
				fee += ",'posType':'8'" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat($("#lianDongAliPayFee").val());
				message +="联动支付宝扫码支付:"+intToDecimal($("#lianDongAliPayFee").val()) +"元，" ;
			}else if($(this).val()==20250046 && $("#feeSumLanDongJingTai").val()>0.0){
				//联动静态扫码支付
				var feeSum = $.trim($("#feeSumLanDongJingTai").val());
				var bankFlowNum = $.trim($("#bankFlowNumLanDongJingTai").val());
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'20250046'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +feeSum +"'" ;
				fee += ",'bankFlowNum':'"+bankFlowNum+"'" ;
				fee += ",'posImgUrl':''" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':''" ;
				fee += ",'posType':''" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat(feeSum);
				message +="联动静态扫码支付:"+intToDecimal(feeSum) +"元，" ;
			}else if($(this).val()==20250052 && $("#feeSumGuanJiaBangShouKuanMa").val()>0.0){
				//管家帮收款码
				var feeSum = $.trim($("#feeSumGuanJiaBangShouKuanMa").val());
				var bankFlowNum = $.trim($("#bankFlowNumGuanJiaBangShouKuanMa").val());
				fee +="{'orderId':'" +accountId +"'";
				fee += ",'feePost':'20250052'" ;
				fee += ",'feeToDate':'" +feeToDate +"'" ;
				fee += ",'feeSum':'" +feeSum +"'" ;
				fee += ",'bankFlowNum':'"+bankFlowNum+"'" ;
				fee += ",'posImgUrl':''" ;
				fee += ",'feeType':'1'" ;
				fee += ",'posCheckStatus':''" ;
				fee += ",'posType':''" ;
				fee += ",'isBackType':'1'" ;
				fee += ",'accountStatus':'2'" ;
				fee += ",'payStatus':'20110001'" ;
				fee +="}";
				feesum += parseFloat(feeSum);
				message +="管家帮收款码支付:"+intToDecimal(feeSum) +"元，" ;
			}else if($(this).val()==20250010){
				//管家卡支付
				var formController=$("#formController" ).val();
				if(formController!='00'){
					$.alert({
						text : "请先验证验证码！"
					});
					return;
				}
				var feeSum = $.trim($("#feeSumGuanJiaKaZhiFu").val());
				var cardNumb=$("#card").val();
				var selectCode=$("#selectCode").val();
				var checkedOrderId=$("#checkedOrderId").val();
				var mobile =$("#empMobile").val();
				var accountId=$('input[name="radioAccount"]:checked').val();
				feesum += parseFloat(feeSum);
				if(feesum!=accountAmount){
					$.alert({
						text : "当前缴费金额与结算单总金额不等！"
					});
					return ;
				}
				var message="你本次支付为管家卡："+intToDecimal(feeSum)+"元，录入总金额"+intToDecimal(feeSum)+"元";
				$.confirm({text:message,callback:function(y){
					if(y){
						$.ajax({
							url: ctx +"/payfee/cardDeduction",
							data:{
								accountId : accountId,
								cardNumb : cardNumb,
								feeSum :feeSum,
								orderCode:selectCode,
								orderId:checkedOrderId,
							},
							type:"post",
							dataType:"json",
							async:false,
							success:function(data){
								if (data.msg =="00") {
									closeModule("orderBasicPayfee");
									queryAccount(orderId,"accountListBodyItem",3,0);
								}else{
									$.alert({
										text : "卡扣费失败！"
									});
								}
							}
						});
					}
				}
					});
					  }
			i ++;
		});
		/** 位置不能动 **/
		var flag=$('input[name="feePosts"]:checked').val();
		if (flag=='20250010') {
			return;
		}
		if(rhtx){
			$.alert({text:"融汇天下pos信息校验未通过！"});
			return ;
		}
		fee += "]";
		//alert(feesum +" and " +accountAmount);
		if(feesum!=accountAmount){
			$.alert({
				text : "当前缴费金额与结算单总金额不等！"
			});
			return ;
		}
		var messa = message +"合计支付总金额："+accountAmount+"元。"; //'${param.accountAmount}'
		message += "录入总金额"+accountAmount+"元，将以短信形式告知客户，是否确认？"; //'${param.accountAmount}'
		var cityCode = parent.$("#checkedCity").val();
		if(cityCode=='101001001'){
			$.confirm({text:message,callback:function(y){
				if(y){
					$.ajax({
						url: ctx +"/payfee/insertPayfeeList",
						data:{
							data:fee,
							accountId : accountId,
							orderId : orderId,
							cateType : cateType,
							userMobile :userMobile,
							message : messa,
							cityCode : cityCode
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							var html="";
							if (data.msg =="00") {
								if(cateType==1){
									queryAccount(orderId,"accountListBodyOne",1,0);
									getServerBasicOne(orderId);
									//parent.getServerBasicOne(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
									showBtn(cateType,orderId);
								}else if(cateType==2){
									queryAccount(orderId,"accountListBodyCont",2,0);
									getServerBasics(orderId);
									//parent.getServerBasics(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
									showBtn(cateType,orderId);
								}else if(cateType==3){
									queryAccount(orderId,"accountListBodyItem",3,0);
									getItemBasics(orderId);
									//parent.getItemBasics(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
									showBtn(cateType,orderId);
								}else if(cateType==4){
									queryAccount(orderId,"accountListBodyThree",4,0);
									//parent.setBasicServerThree(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
									showBtn(cateType,orderId);
								}
								if (data.oneMsg =="fail") {
									$.alert({text:"自动对账失败，请核对并修改缴费信息！"});
								}
								closeModule("orderBasicPayfee");
							}else{
								$.alert({text:"保存失败！"});
								return ;
							}
						}
					});
				  }
				}
			})
		}else{
			$.ajax({
				url: ctx +"/payfee/insertPayfeeList",
				data:{
					data:fee,
					accountId : accountId,
					orderId : orderId,
					cateType : cateType,
					userMobile :userMobile,
					message : messa,
					cityCode : cityCode
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					var html="";
					if (data.msg =="00") {
						if(cateType==1){
							queryAccount(orderId,"accountListBodyOne",1,0);
							getServerBasicOne(orderId);
							//parent.getServerBasicOne(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
							showBtn(cateType,orderId);
						}else if(cateType==2){
							queryAccount(orderId,"accountListBodyCont",2,0);
							getServerBasics(orderId);
							//parent.getServerBasics(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
							showBtn(cateType,orderId);
						}else if(cateType==3){
							queryAccount(orderId,"accountListBodyItem",3,0);
							getItemBasics(orderId);
							//parent.getItemBasics(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
							showBtn(cateType,orderId);
						}else if(cateType==4){
							queryAccount(orderId,"accountListBodyThree",4,0);
							//parent.setBasicServerThree(orderId,cateType,parent.$("#checkedTotalPay").val(),parent.$("#checkedOrderStatus").val(),parent.$("#checkedLoginByOrNot").val());
							showBtn(cateType,orderId);
						}
						if (data.oneMsg =="fail") {
							$.alert({text:"自动对账失败，请核对并修改缴费信息！"});
						}
						closeModule("orderBasicPayfee");
					}else{
						$.alert({text:"保存失败！"});
						return ;
					}
				}
			});
		}
	}
	// 查询卡片
	function checkCards(card){
		var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
		var je = card.children("td").eq(1).children("span").text();
		$("#feeSumCard").val(je);
	}
	// 保存缴费的修改
	function editPayfeeSave(pn){
		var ctx = $("#ctx").val();
		var feeToDate = $("#payment_feeToDate").val();
		var feePost = "" ;
		var postBank = "" ;
		var bankFlowNum = "" ;
		var postUser = "" ;
		var postTerminalNo = "";
		var postNum = "";
		var id = "" ;
		var platformFee = "";
		var platformId = "";
		//提示信息
		var editmessage = "您本次修改支付为";
		if(pn==20250002 && $("#feeSumBanktrans").val()>0.0){
			feePost=20250002;
			id=$("#payfeeIdBanktrans").val();
			postBank=$.trim($("#postBankBanktrans").val());
			bankFlowNum=$.trim($("#bankFlowNumBanktrans").val());
			postUser=$.trim($("#postUserBanktrans").val());
			editmessage +="银行转账:"+intToDecimal($("#feeSumBanktrans").val()) +"元，" ;
		}else if(pn==20250005 && $("#feeSumPos").val()>0.0){
			feePost=20250005;
			id=$("#payfeeIdPos").val();
			bankFlowNum=$("#bankFlowNumPos").val();
			postNum=$.trim($("#postNumPos").val());
			posImg=$.trim($("#img_file_pos").val());//pos凭条
			editmessage +="POS机:"+intToDecimal($("#feeSumPos").val()) +"元，" ;
		}else if(pn==20250008 && $("#feeSumRhtxPos").val()>0.0){
			feePost=20250008;
			id=$("#payfeeIdRhtxPos").val();
			bankFlowNum=$.trim($("#bankFlowNumRhtxPos").val());
			postTerminalNo=$.trim($("#postTerminalNoRhtxPos").val());
			posImg=$.trim($("#img_file_rhtxpos").val());//pos凭条
			var rhtx = false ;
			$.ajax({
				url: ctx +"/payfee/posSuccessService",
				data:{
					jiaofei : $("#feeSumRhtxPos").val(),
					terminalNo : $("#postTerminalNoRhtxPos").val(),
					referenceNo : $("#bankFlowNumRhtxPos").val()
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					var html="";
					if (data.msg !="00") {
						rhtx = true;
					}
				}
			});
			if(rhtx){
				$.alert({text:"融汇天下pos信息校验未通过！"});
				return ;
			}
			editmessage +="融汇天下POS:"+intToDecimal($("#feeSumRhtxPos").val()) +"元，" ;
		}else if(pn==20250010 && $("#feeSumCard").val()>0.0){
			//feePost=20250010;
			//var card = $("#cardListBody input[name='radiopayfeeCard']:checked").parent("td").parent("tr") ;
			//fee += ",'cardsNum':'" +card.children("td").eq(0).text() +"'" ;
			
		}else if(pn==20250014 && $("#feeSumTRDS").val()>0.0){
			//feePost=20250014;
			//id=$("#payfeeIdTRDS").val();
			//postUser=$("#postUserTRDS").val();
			//editmessage +="他人代收:"+intToDecimal($("#feeSumTRDS_DC").val()) +"元，" ;
		}else if(pn==20250015 && $("#feeSumWechat").val()>0.0){
			feePost=20250015;
			id=$("#payfeeIdWechat").val();
			bankFlowNum=$.trim($("#bankFlowNumWechat").val());
			editmessage +="微信扫码支付:"+intToDecimal($("#feeSumWechat").val()) +"元，" ;
		}else if(pn==20250016 && $("#feeSumBaitiao").val()>0.0){
			feePost=20250016;
			id=$("#baitiaoIdPayfee").val();
			editmessage +="白条支付:"+intToDecimal($("#feeSumBaitiao").val()) +"元，" ;
		}else if(pn==20250017 && $("#jialianPosWechatFee").val()>0.0){
			feePost=20250017;
			id=$("#jiaLianIdPosWechat").val();
			bankFlowNum=$.trim($("#jialianWechatPostNum").val());
			posImg=$.trim($("#img_file_wechatpos").val());//pos凭条
			editmessage +="嘉联POS微信支付:"+intToDecimal($("#jialianPosWechatFee").val()) +"元，" ;
		}else if(pn==20250018 && $("#jialianPosAliPay").val()>0.0){
			feePost=20250018;
			id=$("#jiaLianIdPosAliPay").val();
			bankFlowNum=$.trim($("#jialianAliPayPostNum").val());
			posImg=$.trim($("#img_file_alipaypos").val());//pos凭条
			editmessage +="嘉联POS支付宝支付:"+intToDecimal($("#jialianPosAliPay").val()) +"元，" ;
		}else if(pn==20250019 && $("#jialianPosBank").val()>0.0){
			feePost=20250019;
			id=$("#jiaLianIdPosBank").val();
			bankFlowNum=$.trim($("#jialianBankPostCKNum").val());
			postNum=$.trim($("#jialianBankPostNum").val());
			posImg=$.trim($("#img_file_cardpos").val());//pos凭条
			editmessage +="嘉联POS刷卡支付:"+intToDecimal($("#jialianPosBank").val()) +"元，" ;
		}
		if(id=="" || feePost==""){
			$.alert({text:"数据出错，无法保存！"});
			return ;
		}
		var messa = editmessage +"合计支付总金额"+accountAmount+"元。";
		editmessage += "录入总金额"+accountAmount+"元，将以短信形式告知客户，是否确认？"; 
		var cityCode = parent.$("#checkedCity").val();
		if(cityCode=='101001001'){
			$.confirm({text:editmessage,callback:function(y){
				if(y){ 
					$.ajax({
						url: ctx +"/payfee/updatePayfee",
						data:{
							id : id,
							feePost : feePost,
							feeToDate : feeToDate,
							postBank : postBank,
							bankFlowNum : bankFlowNum,
							postNum : postNum,
							postUser : postUser,
							postTerminalNo : postTerminalNo,
							userMobile : userMobile,
							message : messa,
							cityCode : cityCode
						},
						type:"post",
						dataType:"json",
						async:false,
						success:function(data){
							if (data.msg =="00") {
								$.alert({text:"保存成功！"});
							}else{
								$.alert({text:"保存失败！"});
							}
						}
					});
					}
				}
			})
		}else{
			$.ajax({
				url: ctx +"/payfee/updatePayfee",
				data:{
					id : id,
					feePost : feePost,
					feeToDate : feeToDate,
					postBank : postBank,
					bankFlowNum : bankFlowNum,
					postNum : postNum,
					postUser : postUser,
					postTerminalNo : postTerminalNo,
					userMobile : userMobile,
					message : messa,
					cityCode : cityCode
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg =="00") {
						$.alert({text:"保存成功！"});
					}else{
						$.alert({text:"保存失败！"});
					}
				}
			});
		}
		
	}
	//查询客户的白条信息
	function checkBaiTiaoByUserId(userId){
		var ctx = $("#ctx").val();
		$.ajax({
			url:ctx + "/payfee/loadIOUsByUserId",
			data:{
				userId:userId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=="00"){
					var m = data.iouPayfee;
					$("#payfeeBaitiaoNull").css("display","none");
					$("#payfeeBaitiaoNotNull").css("display","block");
					//$("#baitiaoState").val(m.iouState);
				}else{
					$("#payfeeBaitiaoNull").css("display","block");
					$("#payfeeBaitiaoNotNull").css("display","none");
				}
			}
		});
	}
	
	/**
	 * 上传图片
	 */
	function uploadfile(eleId) {
		$("#"+eleId).click();
		$("#"+eleId).change(function(){
			var $this = $(this);
			var file = $this[0].files[0];
			var fileType = file.type;
			var fileName = file.name;
			var fileExt = $.GetFileExtension(fileName);
			if (!fileExt || (fileExt != "jpg")) {
				alert("格式不正确,请选择jpg格式!");
				// 清空file组件
				var fileInput = $this[0];
				fileInput.outerHTML = fileInput.outerHTML;
				return;
			}
			var msg = "温馨提示您选择的文件是：" + fileName + ",是否继续？";
			if (confirm(msg) == true){
				upload(file,eleId);
			}
			// 清空file组件
			var fileInput = $this[0];
			fileInput.outerHTML = fileInput.outerHTML;
		});
	}

	/**
	 * 上传图片
	 * @param this_
	 */
	 function upload(file,eleId) {
		var formData = new FormData();
		formData.append('excelFile', file);
		$.AjaxFileUpload(ctx+'/payfee/insertImgUrl', formData, { loading:true }, function(data){
			var newImgString = data.imgInfo.reUrl + "";
			$("#img_"+eleId).prop("src",newImgString);
			$("#img_url_"+eleId).html(newImgString);
		});
	} 
	 
	//查询合同信息费
		function queryOrderAgreement(orderId){
			$.ajax({
				url: ctx+"/agreement/queryAgreement",
				data:{
					orderId:orderId,
					agreementStateEffect:2,
					valid:1
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if(data.msg == "00"){
						$.each(data.list,function(i,v){
							if(v.isCollection ==7 && (v.agreementState==1 || v.agreementState==2) && v.advancePeriod==2){
								//不错操作
							}else{
								$("#labelPayfeeInstallment").hide();
							}
						})
						
					}else{
						$("#labelPayfeeInstallment").hide();
					}
				}
			});
		} 
		
		//查询渠道
		function queryChannel(){
			var channelId=null;
			$.ajax({
				url: ctx+"/payfee/queryChannel",
				data:{
					orderId:orderId
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if(data.msg == "00"){
						channelId = data.channelId;
					}
				}
			});
			return channelId;
		} 
		
		Array.prototype.contains = function (needle) {
			  for (i in this) {
			    if (this[i] == needle) return true;
			  }
			  return false;
			}

