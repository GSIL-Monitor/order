/**
 * 售后单服务类更新js
 * @author wn
 */
var ctx = $("#ctx").val();

function getQualityServer(orderId, cateType) {
    var serverTotalPay = "";
    //单次服务展示
    if (cateType == 1 || cateType == 4) {
        $("#udanciTitle").show();
        $("#uqualityDCServerForm").show();
        $("#uyanxvTitle").hide();
        $("#uqualityYXServerForm").hide();
        //加载省份
        setSelProvinceCitys('101', 6, 'udcServer_bankProvinceCode');
        //查询订单信息与客户信息（单次）
        $.ajax({
            url: ctx + "/orderbase/queryOrderBasicServer",
            data: {
                id: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        $("#udcServerUserName").html(v.order.userName);
                        $("#udcServerMobile").html(v.order.userMobile);
                        //$("#dcServerReceiptName").html(v.order.receiverName);
                        $("#udcServerReceiptMobile").html(v.order.receiverMobile);
                        $("#udcServerReceiptAddress").html(v.order.receiverAddress);
                        $("#udcServerOrderCode").html(v.orderCode);
                        $("#udcServerOrderType").html(v.typeText);
                        $("#udcServerStartTime").html(numberToDatestr(v.startTime, 8));
                        $("#udcServerEndTime").html(numberToDatestr(v.endTime, 8));
                        $("#udcServerReceiverAddress").html(v.address);
                        $("#udcServerOrderStatus").html(orderStatus[v.order.orderStatus]);
                        $("#udcServerOrderChannel").html(v.channelText);
                        $("#udcServerOrderSource").html(v.sourceText);
                        $("#udcServerRemark").html(v.order.remark);
                        //$("#dcServerNowPrice").val(v.nowPrice);
                        //$("#serverOrderStatus").val(v.order.orderStatus);
                    });
                }
            }
        });
    } else if (cateType == 2) {//延续性服务展示
        $("#udanciTitle").hide();
        $("#uqualityDCServerForm").hide();
        $("#uyanxvTitle").show();
        $("#uqualityYXServerForm").show();
        //将延续服务的终止月费用生成结算单
        //计算此订单号的所有结算单费用，并展示到文本框中
        //退款总额框，以管家填写的金额为准
        //insertEndMonthAccount(orderId);
        //获取当前时间
        var date = new Date();
        $("#ucurrentTime").val((new Date()).format("yyyy-MM-dd hh:mm:ss"));
        //加载省份
        setSelProvinceCitys('101', 6, 'uyxServer_bankProvinceCode');
        //查询订单信息与客户信息（延续）
        $.ajax({
            url: ctx + "/orderbase/queryOrderBasicServer",
            data: {
                id: orderId
            },
            type: "post",
            dataType: "json",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        $("#uyxServerUserName").html(v.order.userName);
                        $("#uyxServerMobile").html(v.order.userMobile);
                        //$("#yxServerReceiptName").html(v.order.receiverName);
                        $("#uyxServerReceiptMobile").html(v.order.receiverMobile);
                        $("#uyxServerReceiptAddress").html(v.order.receiverAddress);
                        $("#uyxServerOrderCode").html(v.orderCode);
                        $("#uyxServerOrderType").html(v.typeText);
                        $("#uyxServerStartTime").html(numberToDatestr(v.startTime, 8));
                        $("#uyxServerEndTime").html(numberToDatestr(v.endTime, 8));
                        $("#uyxServerReceiverAddress").html(v.address);
                        $("#uyxServerOrderStatus").html(orderStatus[v.order.orderStatus]);
                        $("#uyxServerOrderChannel").html(v.channelText);
                        $("#uyxServerOrderSource").html(v.sourceText);
                        $("#uyxServerRemark").html(v.order.remark);
                        $("#userverNowPrice").val(v.nowPrice);
                        $("#userverPayStatus").val(v.order.payStatus);
                        serverTotalPay = intToDecimal(v.order.totalPay);
                        $("#userverOrderTotalPay").val(serverTotalPay);  //订单总额
                        //最大金额提示隐藏
                        $("#uyxWarn").hide();
                        $("#uyxVipShopWarn").hide();
                    });
                }
            }
        });
    }

    //退款总额及缴费信息
    uqueryAccountTotal(orderId, cateType, serverTotalPay);
    //缴费信息
    //uqueryPayFeeById(orderId,cateType);
    //售后单信息
    showServerAfterSales(afterSalesId, cateType);
    //查询合同
    uqueryAgreementMoney(orderId);
}

//查询合同信息费
function uqueryAgreementMoney(orderId) {
    $.ajax({
        url: ctx + "/agreement/queryAgreement",
        data: {
            orderId: orderId,
            valid: 1
        },
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            $.each(data.list, function (j, w) {
                $("#uyxcustomerManageMoney").val(w.customerManageMoney);
            });
        }
    });
}
//售后单信息展示
function showServerAfterSales(afterSalesId, cateType) {
//		var auditFlag = '${param.auditFlag}';
    $.ajax({
        url: ctx + '/afterSales/loadAfterSales',
        data: {
            id: afterSalesId,
        },
        type: 'post',
        async: false,
        success: function (data) {
            if (data.msg == "00") {
                var uasObj = data.afterSales;
                if (cateType == 1 || cateType == 4) {
                	$("#udcAccountPayId").val(uasObj.accountPayId);
                    //查询审核部门
                    queryApproveDeptName("udcApproveDeptSelect");
                    $("#udcServer_afterSalesKind").val("单次服务订单取消");
                    //售后单类型放入隐藏域
                    $("#udcServer_afterSalesKindHidden").val(uasObj.afterSalesKind);
                    //银行
                    if (uasObj.bankSupportId != "" && uasObj.bankSupportId != null) {
                        $("#udcServer_bankSupportId").val(uasObj.bankSupportId);
                    } else {
                        $("#udcServer_bankSupportId").val("");
                    }
                    //省、市
                    if (uasObj.bankCityCode != "" && uasObj.bankCityCode != null) {
                        var ucode = uasObj.bankCityCode.substr(0, 6);
                        $("#udcServer_bankProvinceCode option[value='" + ucode + "']").attr("selected", "selected");
                        setSelProvinceCitys(uasObj.bankCityCode, 9, "udcServer_bankCityCode");
                        $("#udcServer_bankCityCode option[value='" + uasObj.bankCityCode + "']").attr("selected", "selected");
                    } else {
                        $("#udcServer_bankCityCode").val("");
                    }
                    $("#udcServer_bankCard").val(uasObj.bankCard);
                    $("#udcServer_bankName").val(uasObj.bankName);
                    $("#udcServer_bankUserName").val(uasObj.bankUserName);
                    $("#udcServerReason").val(uasObj.reason);
                    //审核部门回显
                    $("#udcApproveDeptSelect option").each(function (i) {
                        var udept = $(this).val();
                        if (udept != undefined && udept != "") {
                            if (udept == uasObj.approveDept) {
                                $(this).attr("selected", true);
                            }
                        }
                    })
                    //审核人回显
                    $("#udcApproveBySelect").html("<option value='" + uasObj.approveBy + "' >" + uasObj.approveByText + "</option>");
                    //$("#udcServerBankReturnTotal").val(intToDecimal(uasObj.refundTotal)); //单次服务退款总额
                    if (auditFlag == '20130002' || auditFlag == '20130005' || auditFlag == '20130009') {
                        $("#udcApproveDeptSelect").attr("disabled", "disabled");
                        $("#udcApproveBySelect").attr("disabled", "disabled");
                    } else {
                        $("#udcApproveDeptSelect").removeAttr("disabled");
                        $("#udcApproveBySelect").removeAttr("disabled");
                    }
                    $("#udcServerReturnTotal").text(intToDecimal(uasObj.refundTotal));
                } else if (cateType == 2) {
                	$("#uyxAccountPayId").val(uasObj.accountPayId);
                    //查询审核部门
                    queryApproveDeptName("uyxApproveDeptSelect");
                    /** 2019-01-07 售后历史费用 **/
        			var historyAfterCharge=getHistoryAfterCharge(orderId);
        			/** 2019-01-07 **/
                    //预存缴费放入隐藏域
                    var uyxPrepareMoney = $("#uyxPrePareMoney").val();
                    //已消费金额放入隐藏域
                    var uyxFeeTotal = $("#uyxSaleMoney").val();
                    //客户信息费
                    var uyxcustomerManageMoney = $("#uyxcustomerManageMoney").val();
                    //展示的已消费金额
                    var ushowSaleMoney = uyxFeeTotal * 1 - uyxcustomerManageMoney * 1;
                    //延续订单终止退款总额
                    var uendServerRefundMoney = uyxPrepareMoney * 1 - uyxFeeTotal * 1-historyAfterCharge;
                    //管家帮分期最大退款总额
                    var uyxVipShopMoney = $("#uyxVipShopMoneyHidden").val();
                    //管家帮分期最大退款总额,去掉服务人员服务费
                    var uyxVipShopMoneyMax = uyxVipShopMoney * 1 - uyxFeeTotal * 1-historyAfterCharge;
                    //是否有管家帮分期缴费标记
                    var uhasVipShop = $("#uyxHasVipshopFee").val();
                    
                  
                    
                    //银行卡和管家帮分期混合支付，服务人员服务费>分期缴费，最大退款金额(银行卡最大退款金额  = 客户缴费 + 分期缴费 - 服务人员服务费)
                    var umixBankRefund = uyxPrepareMoney * 1 + uyxVipShopMoneyMax * 1 - uyxFeeTotal * 1-historyAfterCharge;
                    //如果分期退款状态为退款失败，银行卡信息不允许修改
                    if (vphBackStatus != null && vphBackStatus != "" && vphBackStatus == "20130009") {
                        $("#uyxMembershipFee").attr("readonly", "readonly");
                        $("#uyxSalaryFee").attr("readonly", "readonly");
                        $("#uyxServer_bank").hide();
                        $("#uyxServer_bankCity").hide();
                        $("#uyxServer_bankCN").hide();
                        $("#uyxServer_bankUn").hide();
                    }
                    if (uasObj.auditFlag == "20130013") {
                        $("#uyxServer_bankSupportId").attr("readonly", "readonly");
                        $("#uyxServer_bankUserName").attr("readonly", "readonly");
                        $("#uyxServer_bankProvinceCode").attr("readonly", "readonly");
                        $("#uyxServer_bankCityCode").attr("readonly", "readonly");
                        $("#uyxServer_bankCard").attr("readonly", "readonly");
                        $("#uyxServer_bankName").attr("readonly", "readonly");
                        $("#uyxServerReason").attr("readonly", "readonly");
                    }
                    //隐藏域迁单默认银行卡号
                    var ubankCard = uasObj.bankCard;//$("#uyxBankCard").val();
                    $("#uyxBankCard").val(uasObj.bankCard);  //订单退款卡号
                    if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                        $("#uyxServer_bank").hide();
                        $("#uyxServer_bankCity").hide();
                        $("#uyxServer_bankCN").hide();
                        $("#uyxServer_bankUn").hide();
                    }
                    $("#uyxServerTime").val(uasObj.serverTime.substr(0, 19));
                    if (uasObj.vphFee != null && uasObj.vphFee != "" && uasObj.refundTotal != null && uasObj.refundTotal != "") {
                        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(uasObj.refundTotal) + "</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(uasObj.vphFee) + "</span>&nbsp;&nbsp;</label>");
                    } else if (uasObj.refundTotal != null && uasObj.refundTotal != "" && (uasObj.vphFee == null || uasObj.vphFee == "")) {
                        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(uasObj.refundTotal) + "</span>&nbsp;&nbsp;</label>");
                    } else {
                        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(uasObj.vphFee) + "</span>&nbsp;&nbsp;</label>");
                    }
                    //售后单类型放入隐藏域
                    $("#uyxServer_afterSalesKindHidden").val(uasObj.afterSalesKind);
                    //排除换人和终止显示
                    if (uasObj.afterSalesKind == 8) {
                        $("#uyxServer_afterSalesKind").val("延续服务订单终止");
                        //银行
                        if (uasObj.bankSupportId != "" && uasObj.bankSupportId != null) {
                            $("#uyxServer_bankSupportId").val(uasObj.bankSupportId);
                        } else {
                            $("#uyxServer_bankSupportId").val("");
                        }
                        //省、市
                        if (uasObj.bankCityCode != "" && uasObj.bankCityCode != null) {
                            var ucode = uasObj.bankCityCode.substr(0, 6);
                            $("#uyxServer_bankProvinceCode option[value='" + ucode + "']").attr("selected", "selected");
                            setSelProvinceCitys(uasObj.bankCityCode, 9, "uyxServer_bankCityCode");
                            $("#uyxServer_bankCityCode option[value='" + uasObj.bankCityCode + "']").attr("selected", "selected");
                        } else {
                            $("#uyxServer_bankCityCode").val("");
                        }
                        $("#uyxServer_bankCard").val(uasObj.bankCard);
                        $("#uyxServer_bankName").val(uasObj.bankName);
                        $("#uyxServer_bankUserName").val(uasObj.bankUserName);
                        //审核部门回显
                        $("#uyxApproveDeptSelect option").each(function (i) {
                            var udept = $(this).val();
                            if (udept != undefined && udept != "") {
                                if (udept == uasObj.approveDept) {
                                    $(this).attr("selected", true);
                                }
                            }
                        })
                        //审核人回显
                        $("#uyxApproveBySelect").html("<option value='" + uasObj.approveBy + "' >" + uasObj.approveByText + "</option>");
                        $("#uyxMembershipFee").val(uasObj.refundMembershipFee);//信息费
                        $("#uyxSalaryFee").val(uasObj.refundSalaryFee);//工资
                        $("#oldRefundMembershipFee").val(uasObj.refundMembershipFee);//信息费
                        $("#oldRefundSalaryFee").val(uasObj.refundSalaryFee);//工资
                        //$("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uendServerRefundMoney)+"</span>&nbsp;");
                        //$("#uyxServerMoneyHidden").val(intToDecimal(uendServerRefundMoney));//最大退费金额放入隐藏域

                        //管家帮分期回显
//			 				$("#uyxVipShopFee").val(uasObj.vphFee);
                        //$("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxVipShopMoney)+"</span>&nbsp;");
                        //$("#uyxVipShopMoneyHidden").val(intToDecimal(uyxVipShopMoney));//最大退费金额放入隐藏域
                        //最大退款金额展示
                        //只有管家帮分期
                        if (uasObj.vphFee != "0" && uasObj.refundTotal <= "0") {
                            $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='uMaxReturnPremium'>0.00</span>&nbsp;");
                            $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='uMaxReturnPremium'>" + intToDecimal(uyxVipShopMoneyMax) + "</span>&nbsp;");
                            $("#uyxServerRefund").html(intToDecimal(0));
                            $("#uyxServerMoneyHidden").val(0);//银行卡最大退费金额放入隐藏域
                            $("#uyxVipShopMoneyHidden").val(uyxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
                            //$("#uyxVipShopFee").val("");//管家帮分期退费金额清空
                            $("#uyxMembershipFee").val(0);
                            $("#oldRefundMembershipFee").val(0);//信息费
                            $("#uyxMembershipFee").attr("readonly", "readonly");
                            //银行信息项隐藏
                            $("#uyxServer_bank").hide();
                            $("#uyxServer_bankCity").hide();
                            $("#uyxServer_bankCN").hide();
                            $("#uyxServer_bankUn").hide();
                            //只有银行卡
                        } else if (uasObj.refundTotal != "0") {
                            /*售后手续费2-开始*/
                            /*if(cateType == 2 && orderTypeCode != 100200120003 && orderTypeCode != 100200010001){
                             var mniTotal = $("#payfeeMoneyC").text();
                             if(uendServerRefundMoney && uendServerRefundMoney != 0){
                             if(uendServerRefundMoney >= mniTotal){
                             uendServerRefundMoney = uendServerRefundMoney-mniTotal;
                             }else{
                             uendServerRefundMoney = 0;
                             }
                             }
                             }*/
                            /*售后手续费2-结束*/
                            $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='uMaxReturnPremium'>" + intToDecimal(uendServerRefundMoney) + "</span>&nbsp;");
                            $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='uMaxReturnPremium'>0.00</span>&nbsp;");
                            //$("#uyxServerRefund").html(intToDecimal(endServerRefundMoney));
                            $("#uyxServerMoneyHidden").val(uendServerRefundMoney);//银行卡最大退费金额放入隐藏域
                            $("#uyxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
                            //$("#uyxVipShopFee").val("");//管家帮分期退费金额清空
                            //银行卡和管家帮分期混合支付
                        }
                        /*else if(uasObj.vphFee != "0" && uasObj.refundTotal != "0"){
                         if(uyxPrepareMoney*1 > uyxVipShopFee){ //总服务人员服务费>管家帮分期金额
                         $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(umixBankRefund)+"</span>&nbsp;");
                         $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
                         $("#uyxServerRefund").html(intToDecimal(umixBankRefund));
                         $("#uyxServerMoneyHidden").val(umixBankRefund);//银行卡最大退费金额放入隐藏域
                         $("#uyxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
                         //$("#uyxVipShopFee").val(0);//管家帮分期退费金额
                         //									$("#uyxVipShopFee").attr("readonly","readonly");//管家帮分期不可填写
                         }else{//总服务人员服务费<管家帮分期金额（）
                         $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxPrepareMoney*1)+"</span>&nbsp;");
                         $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxVipShopMoneyMax)+"</span>&nbsp;");
                         $("#uyxServerRefund").html(intToDecimal(uyxPrepareMoney*1));
                         $("#uyxServerMoneyHidden").val(uyxPrepareMoney*1);//银行卡最大退费金额放入隐藏域
                         $("#uyxVipShopMoneyHidden").val(uyxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
                         //$("#uyxVipShopFee").val(0);//管家帮分期退费金额
                         }
                         }*/
                        if ((uasObj.refundTotal == "0" || uasObj.refundTotal == "0.00")) { // && (uasObj.vphFee == "0" || uasObj.vphFee == "0.00")
                            $("#uyxServer_bank").hide();
                            $("#uyxServer_bankCity").hide();
                            $("#uyxServer_bankCN").hide();
                            $("#uyxServer_bankUn").hide();
                        }
                        if (uasObj.refundTotal == "0" || uasObj.refundTotal == "0.00") {
                            if(uasObj.isApp != "1" && uasObj.auditFlag != "20130013"){
                                $("#uyxSalaryFee").val(0);//工资
                                $("#uyxMembershipFee").val(0);//信息费
                                $("#oldRefundMembershipFee").val(0);//信息费
                                $("#oldRefundSalaryFee").val(0);//工资
                                $("#uyxSalaryFee").attr("readonly", "readonly");
                                $("#uyxMembershipFee").attr("readonly", "readonly");
                            }
                        }
                        if(uasObj.isApp == "1" && uasObj.auditFlag == "20130013"){
                            $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>" + intToDecimal(uendServerRefundMoney) + "</span>&nbsp;");
                            $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
                            //$("#uyxServerRefund").html(intToDecimal(endServerRefundMoney));
                            $("#uyxServerMoneyHidden").val(uendServerRefundMoney);//银行卡最大退费金额放入隐藏域
                            $("#uyxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
                        }
                    } else if (uasObj.afterSalesKind == 5) {
                        $("#uyxServer_afterSalesKind").val("延续服务订单取消");
                        //银行
                        if (uasObj.bankSupportId != "" && uasObj.bankSupportId != null) {
                            $("#uyxServer_bankSupportId").val(uasObj.bankSupportId);
                        } else {
                            $("#uyxServer_bankSupportId").val("");
                        }
                        //省、市
                        if (uasObj.bankCityCode != "" && uasObj.bankCityCode != null) {
                            var ucode = uasObj.bankCityCode.substr(0, 6);
                            $("#uyxServer_bankProvinceCode option[value='" + ucode + "']").attr("selected", "selected");
                            setSelProvinceCitys(uasObj.bankCityCode, 9, "uyxServer_bankCityCode");
                            $("#uyxServer_bankCityCode option[value='" + uasObj.bankCityCode + "']").attr("selected", "selected");
                        } else {
                            $("#uyxServer_bankCityCode").val("");
                        }
                        $("#uyxServer_bankCard").val(uasObj.bankCard);
                        $("#uyxServer_bankName").val(uasObj.bankName);
                        $("#uyxServer_bankUserName").val(uasObj.bankUserName);
                        //审核部门回显
                        $("#uyxApproveDeptSelect option").each(function (i) {
                            var udept = $(this).val();
                            if (udept != undefined && udept != "") {
                                if (udept == uasObj.approveDept) {
                                    $(this).attr("selected", true);
                                }
                            }
                        })
                        //审核人回显
                        $("#uyxApproveBySelect").html("<option value='" + uasObj.approveBy + "' >" + uasObj.approveByText + "</option>");
                        $("#uyxMembershipFee").val(uasObj.refundMembershipFee);
                        $("#uyxSalaryFee").val(uasObj.refundSalaryFee);
                        $("#oldRefundMembershipFee").val(uasObj.refundMembershipFee);//信息费
                        $("#oldRefundSalaryFee").val(uasObj.refundSalaryFee);//工资
                        /*售后手续费2-开始*/
                        /*if(cateType == 2 && orderTypeCode != 100200120003 && orderTypeCode != 100200010001){
                         var mniTotal = $("#payfeeMoneyC").text();
                         if(uendServerRefundMoney && uendServerRefundMoney != 0){
                         if(uendServerRefundMoney >= mniTotal){
                         uendServerRefundMoney = uendServerRefundMoney-mniTotal;
                         }else{
                         uendServerRefundMoney = 0;
                         }
                         }
                         }*/
                        /*售后手续费2-结束*/
                        $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>" + intToDecimal(uendServerRefundMoney) + "</span>&nbsp;");
                        $("#uyxServerMoneyHidden").val(intToDecimal(uendServerRefundMoney));//最大退费金额放入隐藏域
                        /* $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;预存金额： <span>"+intToDecimal(uyxPrepareMoney)+
                         "</span>&nbsp;&nbsp;-  客户信息费： <span>"+intToDecimal(uyxcustomerManageMoney)+"</span>&nbsp;&nbsp;"); */
                        if ((uasObj.refundTotal == "0" || uasObj.refundTotal == "0.00")) {// && (uasObj.vphFee == "0" || uasObj.vphFee == "0.00")
                            $("#uyxServer_bank").hide();
                            $("#uyxServer_bankCity").hide();
                            $("#uyxServer_bankCN").hide();
                            $("#uyxServer_bankUn").hide();
                        }
                        //管家帮分期回显
//			 				$("#uyxVipShopFee").val(uasObj.vphFee);
                        //$("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxVipShopMoney)+"</span>&nbsp;");
                        //$("#uyxVipShopMoneyHidden").val(intToDecimal(uyxVipShopMoney));//最大退费金额放入隐藏域
                        //最大退款金额展示
                        //只有管家帮分期
                        if (uasObj.vphFee != "0" && uasObj.refundTotal <= "0") {
                            $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
                            $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>" + intToDecimal(uyxVipShopMoneyMax) + "</span>&nbsp;");
                            $("#uyxServerRefund").html(intToDecimal(0));
                            $("#uyxServerMoneyHidden").val(0);//银行卡最大退费金额放入隐藏域
                            $("#uyxVipShopMoneyHidden").val(uyxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
                            //$("#uyxVipShopFee").val("");//管家帮分期退费金额清空
                            $("#uyxMembershipFee").val(0);
                            $("#oldRefundMembershipFee").val(0);//信息费
                            $("#uyxMembershipFee").attr("readonly", "readonly");
                            //银行信息项隐藏
                            $("#uyxServer_bank").hide();
                            $("#uyxServer_bankCity").hide();
                            $("#uyxServer_bankCN").hide();
                            $("#uyxServer_bankUn").hide();
                            //只有银行卡
                        } else if (uasObj.refundTotal != "0") { //uasObj.vphFee <= "0" &&
                            $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span id='uMaxReturnPremium'>" + intToDecimal(uendServerRefundMoney) + "</span>&nbsp;");
                            $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
                            //$("#uyxServerRefund").html(intToDecimal(endServerRefundMoney));
                            $("#uyxServerMoneyHidden").val(uendServerRefundMoney);//银行卡最大退费金额放入隐藏域
                            $("#uyxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
                            //$("#uyxVipShopFee").val("");//管家帮分期退费金额清空
                            //银行卡和管家帮分期混合支付
                        }
                        /*else if(uasObj.vphFee != "0" && uasObj.refundTotal != "0"){
                         if(uyxPrepareMoney*1 > uyxVipShopFee){ //总服务人员服务费>管家帮分期金额
                         $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(umixBankRefund)+"</span>&nbsp;");
                         $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
                         $("#uyxServerRefund").html(intToDecimal(umixBankRefund));
                         $("#uyxServerMoneyHidden").val(umixBankRefund);//银行卡最大退费金额放入隐藏域
                         $("#uyxVipShopMoneyHidden").val(0);//管家帮分期最大退费金额放入隐藏域
                         //									$("#uyxVipShopFee").val(0);//管家帮分期退费金额
                         //									$("#uyxVipShopFee").attr("readonly","readonly");//管家帮分期不可填写
                         }else{//总服务人员服务费<管家帮分期金额（）
                         $("#uyxServerMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxPrepareMoney*1)+"</span>&nbsp;");
                         $("#uyxVipShopMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(uyxVipShopMoneyMax)+"</span>&nbsp;");
                         $("#uyxServerRefund").html(intToDecimal(uyxPrepareMoney*1));
                         $("#uyxServerMoneyHidden").val(uyxPrepareMoney*1);//银行卡最大退费金额放入隐藏域
                         $("#uyxVipShopMoneyHidden").val(uyxVipShopMoneyMax);//管家帮分期最大退费金额放入隐藏域
                         //$("#uyxVipShopFee").val(0);//管家帮分期退费金额
                         }
                         }*/
                        if ((uasObj.refundTotal == "0" || uasObj.refundTotal == "0.00") && uasObj.isApp != "1" && uasObj.auditFlag != "20130013" ) {
                            $("#uyxSalaryFee").val(0);
                            $("#uyxMembershipFee").val(0);
                            $("#oldRefundMembershipFee").val(0);//信息费
                            $("#oldRefundSalaryFee").val(0);//工资
                            $("#uyxSalaryFee").attr("readonly", "readonly");
                            $("#uyxMembershipFee").attr("readonly", "readonly");
                        }
                    }
                    $("#uyxServerReason").val(uasObj.reason);
                    if (auditFlag == '20130009') {
                        $("#uyxApproveDeptSelect").attr("disabled", "disabled");
                        $("#uyxApproveBySelect").attr("disabled", "disabled");
                        $("#uyxMembershipFee").attr("readonly", "readonly");
                        $("#uyxSalaryFee").attr("readonly", "readonly");
                    } else if (auditFlag == '20130002' || auditFlag == '20130005') {
                        $("#uyxApproveDeptSelect").attr("disabled", "disabled");
                        $("#uyxApproveBySelect").attr("disabled", "disabled");
                    } else {
                        $("#uyxApproveDeptSelect").removeAttr("disabled");
                        $("#uyxApproveBySelect").removeAttr("disabled");
                        if (uasObj.refundTotal != "0" && uasObj.refundTotal != "0.00"
                            && (uasObj.vphBackStatus != null && uasObj.vphBackStatus != '20130009')) {
                            $("#uyxMembershipFee").removeAttr("readonly");
                            $("#uyxSalaryFee").removeAttr("readonly");
                        }
                    }
                    $("#uyxServerRefund").html(intToDecimal(uasObj.refundTotal));       //延续服务，退款总额
                    //移动端售后添加隐藏域
                    $("#uyxServerIsApp").val(uasObj.isApp);//是否移动端售后
                    $("#uyxServerAfterSalesKind").val(uasObj.afterSalesKind);//售后类型
                    $("#uyxServerPersonnelStatus").val(uasObj.personnelStatus);//移动端服务状态
                    $("#uyxServerImgUrl").val(uasObj.afterSalesImgs);//售后图片地址
                }
            }
        }
    })
}
//单次和延续服务的退款总额展示
function uqueryAccountTotal(orderId, cateType, serverTotalPay) {
    var uyxserverPayStatus = $("#userverPayStatus").val();
    $.ajax({
        url: ctx + "/payfee/queryAccount",
        data: {
            orderId: orderId,
            isBackType: 2,
            valid: 1
        },
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            var refundTotal = 0;
            var userveraliPayRefundTotal = 0;
            var userverbankRefundTotal = 0;
            var userverdhRefundTotal = 0;
            var userverticketRefundTotal = 0;
            var userverposRefundTotal = 0;
            var uservertaobaofundTotal = 0;
            var userverweixinRefundTotal = 0;
            var userverrhPosRefundTotal = 0;
            var userverccbcRefundTotal = 0;
            var uservercardRefundTotal = 0;
            var uservertyqRefundTotal = 0;
            var userverremainRefundTotal = 0;
            var userverotherRefundTotal = 0;
            var userverweixinSearchRefundTotal = 0;
            var userverbaitiaoRefundTotal = 0;
            var userverjialianPosweixinRefundTotal = 0;
            var userverjialianPosalipayRefundTotal = 0;
            var userverjialianPoscardRefundTotal = 0;
            var userverjialianweixinRefundTotal = 0;
            var userverjialianalipayRefundTotal = 0;
            var userverjialiancardRefundTotal = 0;
            var userveryiwangtongRefundTotal = 0;
            var userverdaoweiRefundTotal = 0;
            var userverweizhishuRefundTotal = 0;
            var userverwangcaiRefundTotal = 0;
            var uservervipshopRefundTotal = 0;
            var html = "";
            var uprepareMoney = 0;
            if (data.msg == '00') {
                if (cateType == 1 || cateType == 4) {
                    var dcAccountPayId = [];//结算单id,防止重复累加缴费
                    $.each(data.list, function (j, w) {
                        if (dcAccountPayId.contains(w.id)) {
                            //防止重复累加缴费
                            return true;
                        }
                        dcAccountPayId.push(w.id);
                        //缴费展示
                        $.ajax({
                            url: ctx + "/payfee/queryPayfee",
                            data: {
                                orderId: w.id,
                                isBackType: 1,
                                valid: 1
                            },
                            type: "post",
                            dataType: "json",
                            async: false,
                            success: function (data) {
                                if (data.msg == '00') {
                                    $.each(data.list, function (l, y) {
                                        //单次服务，直接展示
                                        if (y.feePost == "20250001") {//支付宝
                                            userveraliPayRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250002") {//银行卡
                                            userverbankRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250003") {//电汇
                                            userverdhRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250004") {//支票
                                            userverticketRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250005") {//pos机
                                            userverposRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250006") {//淘宝支付
                                            uservertaobaofundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250007") {//微信退费
                                            userverweixinRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250008") {//融汇天下POS
                                            userverrhPosRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250009") {//建行
                                            userverccbcRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250010") {//卡支付
                                            uservercardRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250011") {//体验券
                                            uservertyqRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250013") {//账户
                                            userverremainRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250014") {//他人代收（三方订单）
                                            userverotherRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250015") {//微信扫码支付
                                            userverweixinSearchRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250016") {//白条支付
                                            userverbaitiaoRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250017") {//嘉联pos微信支付
                                            userverjialianPosweixinRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250018") {//嘉联pos支付宝支付
                                            userverjialianPosalipayRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250019") {//嘉联pos刷卡支付
                                            userverjialianPoscardRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250020") {//嘉联微信支付
                                            userverjialianweixinRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250021") {//嘉联支付宝支付
                                            userverjialianalipayRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250022") {//嘉联快捷支付
                                            userverjialiancardRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250023") {//招商银行一网通支付
                                            userveryiwangtongRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250024") {//支付宝到位
                                            userverdaoweiRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250025") {//微指数余额
                                            userverweizhishuRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                        if (y.feePost == "20250026") {//旺财支付
                                            userverwangcaiRefundTotal += intToDecimal(y.feeSum) * 1;
                                        }
                                    });

                                }
                            }
                        });
                    });
                    if (userveraliPayRefundTotal != "0") {
                        html += "<label>支付宝退费：<span>" + intToDecimal(userveraliPayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverbankRefundTotal != "0") {
                        html += "<label>银行打卡退费合计：<span>" + intToDecimal(userverbankRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverdhRefundTotal != "0") {
                        html += "<label>电汇退费：<span>" + intToDecimal(userverdhRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverticketRefundTotal != "0") {
                        html += "<label>支票退费：<span>" + intToDecimal(userverticketRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverposRefundTotal != "0") {
                        html += "<label>POS机退费：<span>" + intToDecimal(userverposRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (uservertaobaofundTotal != "0") {
                        html += "<label>淘宝退费：<span>" + intToDecimal(uservertaobaofundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverweixinRefundTotal != "0") {
                        html += "<label>微信退费：<span>" + intToDecimal(userverweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverrhPosRefundTotal != "0") {
                        html += "<label>融汇天下POS退费：<span>" + intToDecimal(userverrhPosRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverccbcRefundTotal != "0") {
                        html += "<label>建行退费：<span>" + intToDecimal(userverccbcRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (uservercardRefundTotal != "0") {
                        html += "<label>卡退费：<span>" + intToDecimal(uservercardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (uservertyqRefundTotal != "0") {
                        html += "<label>体验券(不退)：<span>" + intToDecimal(uservertyqRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverremainRefundTotal != "0") {
                        html += "<label>账户退费：<span>" + intToDecimal(userverremainRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverotherRefundTotal != "0") {
                        html += "<label>他人代收退费：<span>" + intToDecimal(userverotherRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverweixinSearchRefundTotal != "0") {
                        html += "<label>微信扫码支付退费：<span>" + intToDecimal(userverweixinSearchRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverbaitiaoRefundTotal != "0") {
                        html += "<label>白条退费：<span>" + intToDecimal(userverbaitiaoRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialianPosweixinRefundTotal != "0") {
                        html += "<label>嘉联pos微信支付退费：<span>" + intToDecimal(userverjialianPosweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialianPosalipayRefundTotal != "0") {
                        html += "<label>嘉联pos支付宝支付退费：<span>" + intToDecimal(userverjialianPosalipayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialianPoscardRefundTotal != "0") {
                        html += "<label>嘉联pos刷卡支付退费：<span>" + intToDecimal(userverjialianPoscardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialianweixinRefundTotal != "0") {
                        html += "<label>嘉联微信支付退费：<span>" + intToDecimal(userverjialianweixinRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialianalipayRefundTotal != "0") {
                        html += "<label>嘉联支付宝支付退费：<span>" + intToDecimal(userverjialianalipayRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverjialiancardRefundTotal != "0") {
                        html += "<label>嘉联快捷支付退费：<span>" + intToDecimal(userverjialiancardRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userveryiwangtongRefundTotal != "0") {
                        html += "<label>招商银行一网通支付退费：<span>" + intToDecimal(userveryiwangtongRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverdaoweiRefundTotal != "0") {
                        html += "<label>支付宝到位退费：<span>" + intToDecimal(userverdaoweiRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverweizhishuRefundTotal != "0") {
                        html += "<label>微指数余额退费：<span>" + intToDecimal(userverweizhishuRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userverwangcaiRefundTotal != "0") {
                        html += "<label>旺财支付退费：<span>" + intToDecimal(userverwangcaiRefundTotal) + "</span>&nbsp;&nbsp;</label>";
                    }
                    if (userveraliPayRefundTotal == "0" && userverbankRefundTotal == "0" && userverdhRefundTotal == "0" && userverticketRefundTotal == "0"
                        && userverposRefundTotal == "0" && uservertaobaofundTotal == "0" && userverweixinRefundTotal == "0" && userverrhPosRefundTotal == "0"
                        && userverccbcRefundTotal == "0" && uservercardRefundTotal == "0" && uservertyqRefundTotal == "0" && userverremainRefundTotal == "0"
                        && userverotherRefundTotal == "0" && userverweixinSearchRefundTotal == "0" && userverbaitiaoRefundTotal == "0" && userverjialianPosweixinRefundTotal == "0"
                        && userverjialianPosalipayRefundTotal == "0" && userverjialianPoscardRefundTotal == "0" && userverjialianweixinRefundTotal == "0" && userverjialianalipayRefundTotal == "0"
                        && userverjialiancardRefundTotal == "0" && userveryiwangtongRefundTotal == "0" && userverdaoweiRefundTotal == "0" && userverweizhishuRefundTotal == "0"
                        && userverwangcaiRefundTotal == "0") {
                        html += "&nbsp;&nbsp;无";
                    }
                    //退款明细
                    $("#udcReturnTotalDetail").html(html);
                    $("#udcServerCardMoney").val(uservercardRefundTotal);
                    $("#udcServerRemainMoney").val(userverremainRefundTotal);
                    if (uservercardRefundTotal != "0") {
                        $("#udcServer_bank").hide();
                        $("#udcServer_bankCity").hide();
                        $("#udcServer_bankCN").hide();
                        $("#udcServer_bankUN").hide();
                    } else if (uservertyqRefundTotal != "0") {
                        $("#udcServer_bank").hide();
                        $("#udcServer_bankCity").hide();
                        $("#udcServer_bankCN").hide();
                        $("#udcServer_bankUN").hide();
                    } else if (userverremainRefundTotal != "0") {
                        $("#udcServer_bank").hide();
                        $("#udcServer_bankCity").hide();
                        $("#udcServer_bankCN").hide();
                        $("#udcServer_bankUN").hide();
                    }
                } else {
                    var ufeeTotal = 0;
                    var yxFqBtSaleMoney = 0;//分期白条类缴费已发服务人员服务费合计
                    var yxAccountPayId = [];//结算单id,防止重复累加缴费
                    $.each(data.list, function (j, w) {
                        if (yxAccountPayId.contains(w.id)) {
                            //防止重复累加缴费
                            return true;
                        }
                        yxAccountPayId.push(w.id);
                        if (w.payStatus == "20110002" || w.payStatus == "20110003") {//已支付
                            if (w.feeType == 4) {
                                ufeeTotal += w.feeSum * 1;
                            }
                            //缴费展示
                            $.ajax({
                                url: ctx + "/payfee/queryPayfee",
                                data: {
                                    orderId: w.id,
                                    isBackType: 1,
                                    valid: 1
                                },
                                type: "post",
                                dataType: "json",
                                async: false,
                                success: function (data) {
                                    if (data.msg == '00') {
                                        $.each(data.list, function (k, x) {
                                            if (x.payStatus == "20110002") {//已支付
                                                //分期白条类型(20250016,20250027,20250031,20250033)
                                                //结算单类型(3:预收、5:信息费预收、7:押金转收入)
                                                if (w.feeType <= 3 || w.feeType == 5 || w.feeType == 7) {
                                                    var fqbtFeePost = [20250016, 20250027, 20250031, 20250033];
                                                    if (!fqbtFeePost.contains(x.feePost)) {
                                                        uprepareMoney += x.feeSum * 1;
                                                    }
                                                    if (fqbtFeePost.contains(x.feePost)) {
                                                        var salaryFee = queryPayfeeDetail(x.id);
                                                        yxFqBtSaleMoney += salaryFee * 1;//分期白条预收使用金额
                                                        /*uservervipshopRefundTotal += intToDecimal(x.feeSum) * 1;//分期白条预收
                                                         $("#uyxHasVipshopFee").val(1);*/
                                                    }
                                                }
                                            }
                                        });

                                    }
                                }
                            });
                        }
                    });
                    ufeeTotal = ufeeTotal - yxFqBtSaleMoney;//已使用金额去除分期白条类缴费使用部份
                    //预存缴费放入隐藏域
                    $("#uyxPrePareMoney").val(uprepareMoney);
                    //已消费金额放入隐藏域
                    $("#uyxSaleMoney").val(ufeeTotal);
                    //管家帮分期缴费放入隐藏域
                    //$("#uyxVipShopMoneyHidden").val(uservervipshopRefundTotal);

                }
            } else {
                if (cateType == 1 || cateType == 4) {
                    //单次服务，直接展示
                    $("#udcServerReturnTotal").text("0.00");
                    $("#udcReturnTotalDetail").html("&nbsp;&nbsp;无");
                } else {
                    $("#uyxServerRefund").text("0.00");
                    $("#uyxRefundTotalDetail").html("&nbsp;&nbsp;无");
                }
            }
        }
    });
}
//延续订单应退信息费更改，退款总额和银行卡退款明细联动
/*$("#uyxMembershipFee").on("input", function () {
    var salaryFee = $("#uyxSalaryFee").val();
    var maxRefund = $("#uyxServerMoneyHidden").val();//最大退费金额
    var yxHasVipshopFee = $("#uyxHasVipshopFee").val();//分期退款标记隐藏域取值
    var yxVipShopMoney = $("#uyxVipShopMoneyHidden").val();//管家帮分期最大退费金额
    var yxRefundVipShop = 0;//$("#uyxVipShopFee").val();//管家帮分期填写金额
    var yxRefundAll = 0;
    //判断应退信息费和应退服务人员服务费总额
    if (!isNaN($(this).val()) && $(this).val() != "0") {
        yxRefundAll = $(this).val() * 1 + salaryFee * 1;
    } else if (!isNaN(salaryFee) && salaryFee != "0") {
        yxRefundAll = salaryFee * 1;
    } else {
        yxRefundAll = "0";
    }
    //获取隐藏域迁单默认银行卡号
    var ubankCard = $("#uyxBankCard").val();
    //判断管家帮分期
    if (!isNaN(yxHasVipshopFee) && yxHasVipshopFee != "" && yxHasVipshopFee != null && yxHasVipshopFee == 1) {
        if ((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxRefundVipShop == "0.00" || yxRefundVipShop == "0")) {
            $("#uyxServer_bank").hide();
            $("#uyxServer_bankCity").hide();
            $("#uyxServer_bankCN").hide();
            $("#uyxServer_bankUn").hide();
            $("#uyxApproveSelectDiv").hide();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
            //退费总额大于最大退费总额，提示错误，并不允许提交
        } else if ((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll * 1 > maxRefund * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").show();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else if ((yxHasVipshopFee != "0.00" || yxHasVipshopFee != "0") && yxRefundVipShop * 1 > yxVipShopMoney * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        }
    } else {
        if (yxRefundAll == "0.00" || yxRefundAll == "0") {
            $("#uyxServer_bank").hide();
            $("#uyxServer_bankCity").hide();
            $("#uyxServer_bankCN").hide();
            $("#uyxServer_bankUn").show();
            $("#uyxApproveSelectDiv").hide();
            $("#uyxWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
            //退费总额大于最大退费总额，提示错误，并不允许提交
        } else if ((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll * 1 > maxRefund * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard != null && ubankCard != "" && ubankCard == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        }
    }
    $("#uyxServerRefund").html(intToDecimal(yxRefundAll));
    if (yxRefundAll != "0" && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)) {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(yxRefundAll) + "</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(yxVipShopMoney) + "</span>&nbsp;&nbsp;</label>");
    } else if ((yxRefundAll == "0.00" || yxRefundAll == "0") && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)) {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(yxRefundVipShop) + "</span>&nbsp;&nbsp;</label>");
    } else {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(yxRefundAll) + "</span>&nbsp;&nbsp;</label>");
    }
});*/

//延续订单应退服务人员服务费更改，退款总额和银行卡退款明细联动
/*$("#uyxSalaryFee").on("input", function () {
    var membershipFee = $("#uyxMembershipFee").val();
    var maxRefund = $("#uyxServerMoneyHidden").val();//最大退费金额
    var yxHasVipshopFee = $("#uyxHasVipshopFee").val();//分期退款标记隐藏域取值
    var yxVipShopMoney = $("#uyxVipShopMoneyHidden").val();//管家帮分期最大退费金额
    var yxRefundVipShop = 0;//$("#uyxVipShopFee").val();//管家帮分期填写金额
    var yxRefundAll = 0;
    //判断应退信息费和应退服务人员服务费总额
    if (!isNaN($(this).val()) && $(this).val() != "0") {
        yxRefundAll = $(this).val() * 1 + membershipFee * 1;
    } else if (!isNaN(membershipFee) && membershipFee != "0") {
        yxRefundAll = membershipFee * 1;
    } else {
        yxRefundAll = "0";
    }
    //获取隐藏域迁单默认银行卡号
    var ubankCard1 = $("#uyxBankCard").val();
    //判断管家帮分期
    if (!isNaN(yxHasVipshopFee) && yxHasVipshopFee != "" && yxHasVipshopFee != null && yxHasVipshopFee == 1) {
        if ((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxRefundVipShop == "0.00" || yxRefundVipShop == "0")) {
            $("#uyxServer_bank").hide();
            $("#uyxServer_bankCity").hide();
            $("#uyxServer_bankCN").hide();
            $("#uyxServer_bankUn").hide();
            $("#uyxApproveSelectDiv").hide();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        } else if ((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll * 1 > maxRefund * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard1 != null && ubankCard1 != "" && ubankCard1 == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").show();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else if ((yxHasVipshopFee != "0.00" || yxHasVipshopFee != "0") && yxRefundVipShop * 1 > yxVipShopMoney * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard1 != null && ubankCard1 != "" && ubankCard1 == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard1 != null && ubankCard1 != "" && ubankCard1 == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uyxVipShopWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        }
    } else {
        if (yxRefundAll == "0.00" || yxRefundAll == "0") {
            $("#uyxServer_bank").hide();
            $("#uyxServer_bankCity").hide();
            $("#uyxServer_bankCN").hide();
            $("#uyxServer_bankUn").hide();
            $("#uyxApproveSelectDiv").hide();
            $("#uyxWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        } else if ((maxRefund != "0.00" || maxRefund != "0") && yxRefundAll * 1 > maxRefund * 1) {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard1 != null && ubankCard1 != "" && ubankCard1 == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
        } else {
            //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
            if (ubankCard1 != null && ubankCard1 != "" && ubankCard1 == "QD-0000") {
                $("#uyxServer_bank").hide();
                $("#uyxServer_bankCity").hide();
                $("#uyxServer_bankCN").hide();
                $("#uyxServer_bankUn").hide();
            } else {
                $("#uyxServer_bank").show();
                $("#uyxServer_bankCity").show();
                $("#uyxServer_bankCN").show();
                $("#uyxServer_bankUn").show();
            }
            $("#uyxApproveSelectDiv").show();
            $("#uyxWarn").hide();
            $("#uqualityServerBtn").removeAttr("disabled");
        }
    }
    $("#uyxServerRefund").html(intToDecimal(yxRefundAll));
    if (yxRefundAll != "0" && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)) {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(yxRefundAll) + "</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(yxRefundVipShop) + "</span>&nbsp;&nbsp;</label>");
    } else if ((yxRefundAll == "0.00" || yxRefundAll == "0") && yxRefundVipShop != null && yxRefundVipShop != "" && !isNaN(yxRefundVipShop)) {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>" + intToDecimal(yxRefundVipShop) + "</span>&nbsp;&nbsp;</label>");
    } else {
        $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>" + intToDecimal(yxRefundAll) + "</span>&nbsp;&nbsp;</label>");
    }
});*/


//管家帮分期退款
/*$("#uyxVipShopFee").on("keyup",function(){
 var yxVipShopMoney = $(this).val();  //应退分期费用
 var membershipFee = $("#uyxMembershipFee").val();//应退信息费
 var salaryFee = $("#uyxSalaryFee").val();//应退服务人员服务费
 var maxVipShopFee = $("#uyxVipShopMoneyHidden").val();//管家帮分期最大退费金额
 var yxRefundAll = 0;
 if(!isNaN(membershipFee)  && !isNaN(salaryFee)){
 yxRefundAll = membershipFee*1 + salaryFee*1;
 }
 //获取隐藏域迁单默认银行卡号
 var ubankCard2 = $("#uyxBankCard").val();

 if((yxRefundAll == "0.00" || yxRefundAll == "0") && (yxVipShopMoney == "0.00" || yxVipShopMoney == "0")){
 $("#uyxServer_bank").hide();
 $("#uyxServer_bankCity").hide();
 $("#uyxServer_bankCN").hide();
 $("#uyxApproveSelectDiv").hide();
 $("#uyxWarn").hide();
 $("#uyxVipShopWarn").hide();
 $("#uqualityServerBtn").removeAttr("disabled");
 }else if( yxVipShopMoney*1 > maxVipShopFee*1 ){
 //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
 if(ubankCard2 != null && ubankCard2 != "" && ubankCard2 == "QD-0000"){
 $("#uyxServer_bank").hide();
 $("#uyxServer_bankCity").hide();
 $("#uyxServer_bankCN").hide();
 $("#uyxServer_bankUn").hide();
 }else{
 $("#uyxServer_bank").show();
 $("#uyxServer_bankCity").show();
 $("#uyxServer_bankCN").show();
 $("#uyxServer_bankUn").show();
 }
 $("#uyxApproveSelectDiv").show();
 $("#uyxWarn").hide();
 $("#uyxVipShopWarn").show();
 $("#uqualityServerBtn").attr("disabled","disabled");
 }else{
 //判断售后是否有默认银行卡号，有则为迁单订单，银行卡信息不显示
 if(ubankCard2 != null && ubankCard2 != "" && ubankCard2 == "QD-0000"){
 $("#uyxServer_bank").hide();
 $("#uyxServer_bankCity").hide();
 $("#uyxServer_bankCN").hide();
 $("#uyxServer_bankUn").hide();
 }else{
 $("#uyxServer_bank").show();
 $("#uyxServer_bankCity").show();
 $("#uyxServer_bankCN").show();
 $("#uyxServer_bankUn").show();
 }
 $("#uyxApproveSelectDiv").show();
 $("#uyxWarn").hide();
 $("#uyxVipShopWarn").hide();
 $("#uqualityServerBtn").removeAttr("disabled");
 }
 if(vphBackStatus != null && vphBackStatus != "" && vphBackStatus == "20130009"){
 $("#uyxServer_bank").hide();
 $("#uyxServer_bankCity").hide();
 $("#uyxServer_bankCN").hide();
 $("#uyxServer_bankUn").hide();
 }
 if((yxRefundAll == "0.00" || yxRefundAll == "0") && yxVipShopMoney!= null && yxVipShopMoney!= ""){
 $("#uyxServer_bank").hide();
 $("#uyxServer_bankCity").hide();
 $("#uyxServer_bankCN").hide();
 $("#uyxServer_bankUn").hide();
 }
 if(yxRefundAll != "0" && yxVipShopMoney != null && yxVipShopMoney != "" && !isNaN(yxVipShopMoney)){
 $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>&nbsp;&nbsp;<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxVipShopMoney)+"</span>&nbsp;&nbsp;</label>");
 }else if((yxRefundAll == "0.00" || yxRefundAll == "0") && yxVipShopMoney != null && yxVipShopMoney != "" && !isNaN(yxVipShopMoney) ){//
 $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;管家帮分期退费： <span>"+intToDecimal(yxVipShopMoney)+"</span>&nbsp;&nbsp;</label>");
 }else{
 $("#uyxRefundTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(yxRefundAll)+"</span>&nbsp;&nbsp;</label>");
 }
 });*/
//修改服务类售后单
function updateQualityServer() {
    var userverPayStatus = $("#userverPayStatus").val(); //订单状态
    var uyxAsk = $("#uyxServer_afterSalesKind").val();
    var uyxAfterSalesKind = $("#uyxServer_afterSalesKindHidden").val();//延续服务类售后单状态
    var udcAfterSalesKind = $("#udcServer_afterSalesKindHidden").val();//单次服务类售后单状态
    var uyxRefundTotal = $("#uyxServerRefund").text();
    var data = "";
    var custName = "";
    var custMobile = "";
    var udcServerCardMoney = $("#udcServerCardMoney").val();
    var udcServerRemainMoney = $("#udcServerRemainMoney").val();
    var url = "";
    //var auditFlag = '${param.auditFlag}';
    if (cateType == 2) {
        data = $("#uqualityYXServerForm").serialize();
        var umembershipFee = $("#uyxMembershipFee").val();//应退信息费
        var usalaryFee = $("#uyxSalaryFee").val();//应退服务人员服务费
        
        
        /*var yxMembershipFeeA =$("#uMaxReturnPremium").text();
		 if((umembershipFee*1+usalaryFee*1)>yxMembershipFeeA*1){
			 $.alert({millis:2000,top:'30%',text:"退款总额不能超过最大退款金额，请重新填写！"});
			 return;
		 }*/
		 
		/**new-start*/
		var uafterReturnableMessageFee = $("#uafterReturnableMessageFee").attr("value");//信息费最大可退金额
		var uafterReturnableSalary = $("#uafterReturnableSalary").attr("value");//服务费最大可退金额
		if(umembershipFee*1 > uafterReturnableMessageFee*1){
			$.alert({"text":"应退信息费已超过信息费最大可退金额,请重新输入"});
			$("#qualityServerBtn").removeAttr("disabled");
			return;	
		}
		if(usalaryFee*1 > uafterReturnableSalary*1){
			$.alert({"text":"应退服务费已超过服务费最大可退金额,请重新输入"});
			$("#qualityServerBtn").removeAttr("disabled");
			return;	
		}
		/**new-end*/
		 
        var uyxServerRefund = Number(umembershipFee)+Number(usalaryFee);//退款合计金额
        uyxServerRefund = uyxServerRefund.toFixed(2);//保留两位小数
        var uyxApproveDept = $("#uyxApproveDeptSelect").find("option:selected").val();
        var uServerBankMainName = $("#uyxServer_bankSupportId").find("option:selected").text();
        var bankCityCode = $("#uyxServer_bankCityCode").val();
        var ubankCard3 = $("#uyxBankCard").val();
        //var uyxServerRefund = $("#uyxServerRefund").html();
        //custName = $("#uyxServerUserName").text();
        //custMobile = $("#uyxServerMobile").text();
        //获取隐藏域迁单默认银行卡号
        if (uServerBankMainName == "...请选择...") {
            uServerBankMainName = "";
        }
        //验证信息费
        if (umembershipFee == "" || umembershipFee == null) {
            $.alert({millis: 2000, top: '30%', text: "请填写应退信息费！"});
            $("#uqualityServerBtn").removeAttr("disabled");
            return;
        }
        //验证服务人员服务费
        if (usalaryFee == "" || usalaryFee == null) {
            $.alert({millis: 2000, top: '30%', text: "请填写应退服务人员服务费！"});
            $("#uqualityServerBtn").removeAttr("disabled");
            return;
        }
        //验证管家帮分期
        var umaxVipShopFee = 0;//$("#uyxVipShopMoneyHidden").val();//管家帮分期最大退费金额
        var uyxRefundVipShop = 0; // $("#uyxVipShopFee").val();//管家帮分期填写金额
        if (umaxVipShopFee != "" && uyxRefundVipShop != "" && uyxRefundVipShop * 1 > umaxVipShopFee * 1) {
            $("#uyxVipShopWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
            return;
        }
        //验证审批部门
        if (uyxApproveDept == "" || uyxApproveDept == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批部门！"});
            $("#uqualityServerBtn").removeAttr("disabled");
            return;
        }
        //验证审批人
        var uyxApproveBy = $("#uyxApproveBySelect").find("option:selected").val();
        if (uyxApproveBy == "" || uyxApproveBy == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批人！"});
            $("#uqualityServerBtn").removeAttr("disabled");
            return;
        }
        //验证服务人员服务费和信息费总和与退款金额是否相等，不相等不允许提交
        var uyxRefundCheck = umembershipFee * 1 + usalaryFee * 1;
        if (uyxServerRefund != "" && uyxServerRefund != "" && uyxServerRefund * 1 < uyxRefundCheck * 1) {
            $.alert({millis: 2000, top: '30%', text: "银行卡退款金额总和错误！"});
            $("#uqualityServerBtn").removeAttr("disabled");
            return;
        }
        var uyxServerMoneyHidden = $("#uyxServerMoneyHidden").html();//最大退款总额
        if (uyxServerRefund != "" && uyxServerMoneyHidden != "" && uyxServerRefund > uyxServerMoneyHidden) {
            $("#uyxWarn").show();
            $("#uqualityServerBtn").attr("disabled", "disabled");
            return;
        }
        //移动端售后修改时跳转新增方法
        if(auditFlag == "20130013" && $("#uyxServerIsApp").val() == "1"){
            var isApp = $("#uyxServerIsApp").val();
            var personnelStatus = $("#uyxServerPersonnelStatus").val();
            custName = $("#uyxServerUserName").text();
            custMobile = $("#uyxServerMobile").text();
            var afterSalesImgs = $("#uyxServerImgUrl").val();
            url = ctx + "/afterSales/saveAfterSales?id=" + afterSalesId
                + "&orderId=" + orderId + "&bankMainName=" + encodeURI(encodeURI(uServerBankMainName))
                + "&auditFlag=" + auditFlag + "&afterSalesKind=" + uyxAfterSalesKind + "&refundTotal=" + uyxServerRefund
                + "&isApp=" + isApp + "&personnelStatus=" + personnelStatus + "&custName=" + custName + "&custMobile=" + custMobile
                + "#afterSalesImgs=" + afterSalesImgs + "&bankCityCode=" + bankCityCode;

            $.ajax({
                url: url,
                data: data,
                type: "post",
                dataType: "json",
                async: false,
                success: function (data) {
                    if (data.msg == "00") {
                        $.alert({millis: 3000, top: '30%', text: "修改成功！"});
                        queryAfterSalesByLike(0, 10);
                        closeModule('qualityServerEdit');
                    } else {
                        $.alert({millis: 3000, top: '30%', text: "修改失败！"});
                        closeModule('qualityServerEdit');
                    }
                }
            });
        }else{
            //url售后单状态修改
            url = ctx + "/afterSales/updateAfterSales?id=" + afterSalesId + "&orderId=" + orderId + "&bankMainName=" + encodeURI(encodeURI(uServerBankMainName)) + "&auditFlag=" + auditFlag + "&afterSalesKind=" + uyxAfterSalesKind;
            url += "&refundTotal=" + uyxServerRefund;
            //url += "&vphFee="+uyxRefundVipShop;
            //url += "&vphBackStatus="+vphBackStatus;
            if (((uyxAsk != "延续服务订单换人" && uyxRefundTotal != "0" && uyxRefundTotal != "0.00")
                || (uyxRefundTotal != "0.00" && auditFlag != "20130009" && auditFlag != "20130002" && vphBackStatus != null && vphBackStatus != "" && vphBackStatus != "20130009" ))
                && (ubankCard3 == null || ubankCard3 == "")) {
                var uyxbanksupportId = $("#uyxServer_bankSupportId").val();
                var uyxbankCityCode = $("#uyxServer_bankCityCode").val();
                var uyxbankCard = $("#uyxServer_bankCard").val();
                var uyxbankName = $("#uyxServer_bankName").val();
                var uyxbankUserName = $("#uyxServer_bankUserName").val();
                if (uyxbanksupportId == null || uyxbanksupportId == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写银行！"});
                    flag = true;
                } else if (uyxbankCityCode == null || uyxbankCityCode == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写城市！"});
                    flag = true;
                } else if (uyxbankCard == null || uyxbankCard == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写银行卡号！"});
                    flag = true;
                } else if (uyxbankName == null || uyxbankName == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写开户行！"});
                    flag = true;
                } else if (uyxbankUserName == null || uyxbankUserName == "") {
                    $.alert({millis: 3000, top: '30%', text: "请填写持卡人！"});
                    flag = true;
                } else {
                    flag = true;
                    if (flag) {
                        updateServer(url, data);
                    }
                }
            } else {
                flag = true;
                if (flag) {
                    updateServer(url, data);
                }
            }
        }
    } else {
        data = $("#uqualityDCServerForm").serialize();
        var udcApproveDept = $("#udcApproveDeptSelect").find("option:selected").val();
        if (udcApproveDept == "" || udcApproveDept == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批部门！"});
            $("#uqualityServerOneBtn").removeAttr("disabled");
            return;
        }
        var udcApproveBy = $("#udcApproveBySelect").find("option:selected").val();
        if (udcApproveBy == "" || udcApproveBy == null) {
            $.alert({millis: 2000, top: '30%', text: "请选择审批人！"});
            $("#qualityServerOneBtn").removeAttr("disabled");
            return;
        }
        var uServerOneBankMainName = $("#udcServer_bankSupportId").find("option:selected").text();
        if (uServerOneBankMainName == "...请选择...") {
            uServerOneBankMainName = "";
        }
        //url售后单状态修改
        url = ctx + "/afterSales/updateAfterSales?id=" + afterSalesId + "&orderId=" + orderId + "&bankMainName=" + encodeURI(encodeURI(uServerOneBankMainName)) + "&auditFlag=" + auditFlag + "&afterSalesKind=" + udcAfterSalesKind;
        if (userverPayStatus != "20110001" && ((udcServerCardMoney == "0.00" && udcServerCardMoney != null) || (udcServerRemainMoney == "0.00" && udcServerRemainMoney != null) )) {
            var dcbanksupportId = $("#udcServer_bankSupportId").val();
            var dcbankCityCode = $("#udcServer_bankCityCode").val();
            var dcbankCard = $("#udcServer_bankCard").val();
            var dcbankName = $("#udcServer_bankName").val();
            var dcbankUserName = $("#udcServer_bankUserName").val();
            if (dcbanksupportId == null || dcbanksupportId == "") {
                $.alert({millis: 3000, top: '30%', text: "请填写银行！"});
                flag = true;
            } else if (dcbankCityCode == null || dcbankCityCode == "") {
                $.alert({millis: 3000, top: '30%', text: "请填写城市！"});
                flag = true;
            } else if (dcbankCard == null || dcbankCard == "") {
                $.alert({millis: 3000, top: '30%', text: "请填写银行卡号！"});
                flag = true;
            } else if (dcbankName == null || dcbankName == "") {
                $.alert({millis: 3000, top: '30%', text: "请填写开户行！"});
                flag = true;
            } else if (dcbankUserName == null || dcbankUserName == "") {
                $.alert({millis: 3000, top: '30%', text: "请填写持卡人！"});
                flag = true;
            } else {
                flag = true;
                if (flag) {
                    updateServer(url, data);
                }
            }
        } else {
            flag = true;
            if (flag) {
                updateServer(url, data);
            }
        }
        $("#udcqualityServerBtn").removeAttr("disabled");
    }
}
//更新售后服务
function updateServer(url, data) {
    $.ajax({
        url: url,
        data: data,
        type: "post",
        dataType: "json",
        async: false,
        success: function (data) {
            if (data.msg == "00") {
                $.alert({millis: 3000, top: '30%', text: "修改成功！"});
                queryAfterSalesByLike(0, 10);
                //var usaleKind = data.saleKind;
                //var uauditFlag = data.auditFlag;
                //showQualityDetail(afterSalesId,orderId,usaleKind,uauditFlag);
                closeModule('qualityServerEdit');
            } else {
                $.alert({millis: 3000, top: '30%', text: "修改失败！"});
                closeModule('qualityServerEdit');
            }
        }
    });
}

//单次服务验证
$('#uqualityDCServerForm').bootstrapValidator({
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
        reason: {
            validators: {
                stringLength: {
                    min: 0,
                    max: 200,
                    message: '描述不超过200字！'
                },
            }
        },
        bankCard: {
            validators: {
                regexp: {
                    regexp: /^(\d{12}|\d{16}|\d{18}|\d{19}|\d{20})$/,
                    message: '银行卡号错误！'
                }
            }
        },
    }
}).on('success.form.bv', function (e) {
    // 阻止表单提交【submit】【必填】
    e.preventDefault();
    //保存售后单
    updateQualityServer();
});
//延续性服务验证
$('#uqualityYXServerForm').bootstrapValidator({
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
                    max: 30,
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
        serverTime: {
            validators: {
                notEmpty: {
                    message: '必选！'
                },
            }
        },
        refundMembershipFee: {
            validators: {
                regexp: {
                    regexp: /^\d+\.?\d{0,2}$/,
                    message: '只能为数字！'
                },
            }
        },
        refundSalaryFee: {
            validators: {
                regexp: {
                    regexp: /^\d+\.?\d{0,2}$/,
                    message: '只能为数字！'
                },
            }
        },
        yxServerWage: {
            validators: {
                regexp: {
                    regexp: /^\d+\.?\d{0,2}$/,
                    message: '只能为数字！'
                },
            }
        },
        reason: {
            validators: {
                stringLength: {
                    min: 0,
                    max: 200,
                    message: '描述不超过200字！'
                },
            }
        },
        bankCard: {
            validators: {
                regexp: {
                    regexp: /^(\d{12}|\d{16}|\d{18}|\d{19}|\d{20})$/,
                    message: '银行卡号错误！'
                }
            }
        },
    }
}).on('success.form.bv', function (e) {
    // 阻止表单提交【submit】【必填】
    e.preventDefault();
    //保存售后单
    updateQualityServer();
});
$("#uyxServer_bankUserName,#uyxServer_bankSupportId,#uyxServer_bankProvinceCode,#uyxServer_bankCityCode,#uyxServer_bankCard,#uyxServer_bankName,#uyxServerReason").each(function () {
    $(this).change(function () {
        if ($("#uyxWarn").is(':hidden')) {
            $("#uqualityServerBtn").removeAttr("disabled");
        } else {
            $("#uqualityServerBtn").attr("disabled", "disabled");
        }
    })
});


/**查询订单可退工资总金额(不含:券,他人代收,白条支付,管家帮分期/海金保理分期,培训分期,唯品会白条,招行分期,唯品会白条支付,消费金额分期还款,汇嘉分期)*/
function queryUAfterReturnableSalary(paramObj){
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
function queryUAfterReturnableMessageFee(paramObj){
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
function inputUMembershipFeeOrSalaryFee(){
	var membershipFee = $("#uyxMembershipFee").val();//应退信息费金额
	var salaryFee = $("#uyxSalaryFee").val();//应退服务人员服务费金额
	var yxRefundAll = 0;//应退信息费金额+应退服务人员服务费金额
	var afterReturnableMessageFee = $("#uafterReturnableMessageFee").attr("value");//信息费最大可退金额
	var afterReturnableSalary = $("#uafterReturnableSalary").attr("value");//服务费最大可退金额
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
			