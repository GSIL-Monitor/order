/**
 * 三方合同
 */
var ctx = $("#ctx").val();
var agreement = {};
var flag = true;
var serviceNo = "";
var PeriodAndDateUrl = "";
//保存新增合同
agreement.save = function (orderId, orderType) {
    //甲方和丙方是否通过身份证验证匹配
    var s_agreement_cardType = $("#s_agreement_cardType").val();
    var s_agreement_cardTypeC = $("#s_agreement_cardTypeC").val();
    var chack_cardNum_a_message = $("#chack_cardNum_a_message").data("value");
    var chack_cardNum_c_message = $("#chack_cardNum_c_message").data("value");
    var agrbusstype = $('#s_agreementBusinessType option:selected').val();
    if (s_agreement_cardType == 1 && chack_cardNum_a_message != 1) {
        $.alert({millis: 3000, text: "请先对甲方实名认证！"});
        return;
    }
    if (s_agreement_cardTypeC == 1 && chack_cardNum_c_message != 1) {
        $.alert({millis: 3000, text: "请先对丙方实名认证！"});
        return;
    }
    if (agrbusstype == '2') {
        var chack_cardNum_healservices_message = $("#chack_service_cardNum_message").data("value");
        if (chack_cardNum_healservices_message != 1) {
            $.alert({millis: 3000, text: "请先对陪护合同服务对象实名认证！"});
            return;
        }
    }
    //甲方地址
    var customerAddress = getAddCustomerAddress();
    var customerAddressEcho = getAddCustomerAddressEcho();
    if (!customerAddress) {
        $.alert({millis: 3000, text: "请输入甲方地址！"});
        return;
    }
    //甲方服务场所
    var serviceAddress = getAddServiceAddress();
    var serviceAddressEcho = getAddServiceAddressEcho();
    if (!serviceAddress) {
        $.alert({millis: 3000, text: "请输入服务场所！"});
        return;
    }
    var data = $("#add_agreementForm").serialize();
    var creTime = $("#s_agreement_createTime").val();
    var endTime = $("#s_agreement_endTime").val();
    var payDate = $("#s_agreement_agreementPayDate").val();
    var remindDay = $("#s_agreement_remindDay").val();
    var payDateNew = $("#s_agreement_agreementPayDate_new").val();
    var allPay = $("#s_agreement_allPay").val();
    var otherWay = $("#s_agreement_otherWay").val();
    var zhifuRemark = $("#s_agreement_zhifuRemark").val();
    var start = new Date(creTime.replace(/-/g, "/"));
    var end = new Date(endTime.replace(/-/g, "/"));
    //校验签约日期
    var contractDate = new Date($("#s_agreement_contractDate").val().replace(/-/g, "/"));//签约日期
    if (contractDate > start) {
        $.alert({millis: 3000, text: "【签约日期】不能晚于【合同起始日期】！"});
        return;
    }
    var cardType = $("#s_agreement_cardType").val();
    var personId = $("#s_agreement_partyC").find("option:selected").attr("data-id");
    var cardNum = "";
    var partyB = $("#s_contractHeader").text();
    if (partyB == "" && partyB == null) {
        $.alert({millis: 3000, text: "乙方信息不能为空, 请联系相关部门解决!"});
        return;
    }
    var bobileB = $("#s_mobileB").val();
    if (bobileB == "" && bobileB == null) {
        $.alert({millis: 3000, text: "乙方信息不能为空, 请联系相关部门解决!"});
        return;
    }
    var BAddress = $("#s_agreement_platformAddress").val();
    if (BAddress == "" && BAddress == null) {
        $.alert({millis: 3000, text: "乙方信息不能为空, 请联系相关部门解决!"});
        return;
    }
    //计算合同期限 时间差
    var d1 = new Date(creTime);
    var d2 = new Date(endTime);
    var date3 = d2.getTime() - d1.getTime();  //时间差的毫秒数
    //时间差值
    var daydifferenceVal = Math.floor(date3 / (24 * 3600 * 1000));

    if (cardType == 1) {//身份证
        cardNum = $("#order_agree_add_id_cardNum").val();
    } else if (cardType == 2) {//护照
        cardNum = $("#order_agree_add_passport_cardNum").val();
    } else if (cardType == 3) {//驾照
        cardNum = $("#order_agree_add_license_cardNum").val();
    } else if (cardType == 4) {//营业执照
        cardNum = $("#order_agree_add_businessLicense_cardNum").val();
    }
    var cardTypeC = $("#s_agreement_cardTypeC").val();
    if (!cardTypeC) {
        $.alert({millis: 3000, text: "丙方证件类型不能为空！"});
        return;
    }
    var cardNumC = "";
    if (cardTypeC == 1) {//身份证
        cardNumC = $("#order_agree_add_id_cardNumC").val();
    } else if (cardTypeC == 2) {//护照
        cardNumC = $("#order_agree_add_passport_cardNumC").val();
    } else if (cardTypeC == 3) {//驾照
        cardNumC = $("#order_agree_add_license_cardNumC").val();
    }
    if (orderType == "100200040001" || orderType == "100200120005") {//一般家务
        serviceNo = "1";
    } else if (orderType == "100200010001" || orderType == "100200120003") {//月嫂
        serviceNo = "2";
    } else if (orderType == "100200010002" || orderType == "100200120004") {//育婴师
        serviceNo = "3";
    } else if (orderType == "100200050001" || orderType == "100200050002") {//老人陪护,医院陪护
        serviceNo = "4";
    } else if (orderType == "100200040002") {//钟点工
        serviceNo = "7";
    } else {
        serviceNo = "";
    }
    //判断平台代付和客户直付
    if ($("input[name='isCollection']:checked").val() == 7) {
        if (payDateNew == null || payDateNew == "") {
            $.alert({millis: 2000, top: '30%', text: "请填写预付截止日期！"});
            $("#cpeSubmit").removeAttr("disabled");
            return;
        } else if ($("input[name='advancePeriod2']:checked").val() != 4 && (allPay == null || allPay == "")) {
            $.alert({millis: 2000, top: '30%', text: "请填写费用总计！"});
            $("#cpeSubmit").removeAttr("disabled");
            return;
        } else if ($("input[name='advancePeriod2']:checked").val() == 4 && (otherWay == null || otherWay == "")) {
            $.alert({millis: 2000, top: '30%', text: "请填写其他支付方式！"});
            $("#cpeSubmit").removeAttr("disabled");
            return;
        } else {
            PeriodAndDateUrl = "&advancePeriod=" + $("input[name='advancePeriod2']:checked").val();
            PeriodAndDateUrl += "&agreementPayDate=" + payDateNew;
        }
    } else {
        if (payDate == null || payDate == "") {
            $.alert({millis: 2000, top: '30%', text: "请填写甲方预支付时间！"});
            $("#ucpeSubmit").removeAttr("disabled");
            return;
        } else if ($("input[name='advancePeriod1']:checked").val() == 1 && (remindDay == null || remindDay == "")) {
            $.alert({millis: 2000, top: '30%', text: "请填写预支付日期！"});
            $("#cpeSubmit").removeAttr("disabled");
            return;
        } else if ($("input[name='advancePeriod1']:checked").val() != 1 && (zhifuRemark == null || zhifuRemark == "")) {
            $.alert({millis: 2000, top: '30%', text: "请填写支付时间说明！"});
            $("#cpeSubmit").removeAttr("disabled");
            return;
        } else {
            PeriodAndDateUrl = "&advancePeriod=" + $("input[name='advancePeriod1']:checked").val();
            PeriodAndDateUrl += "&agreementPayDate=" + payDate;
        }
    }
    var authCodeflag = $("#s_authCode_flag").val();
    var advancePeriodType = $('input[name=advancePeriod2]:checked').val();
    //校验合同起始，终止时间 add 20181115
    debugger;
    var flag = checkAgreementDate(orderId,start,end,advancePeriodType);
    if(flag == false){
        $("#cpeSubmit").removeAttr("disabled");
        return;
    }
    if (end < start) {
        $.alert({millis: 2000, top: '30%', text: "开始时间必须小于结束时间！"});
        $("#cpeSubmit").removeAttr("disabled");
        return;
    } else if (creTime == null || creTime == "") {
        $.alert({millis: 2000, top: '30%', text: "请填写合同期限！"});
        flag = false;
        $("#cpeSubmit").removeAttr("disabled");
        return;
    } else if (endTime == null || endTime == "") {
        $.alert({millis: 2000, top: '30%', text: "请填写合同期限！"});
        flag = false;
        $("#cpeSubmit").removeAttr("disabled");
        return;
    } else if (daydifferenceVal > 366) {
        $("#s_agreement_time_Msg").html("应业务管理部要求,录入合同的起止时间不得超过1年, 请核实后再选择合同结束时间. 超过1年的合同, 请联系结算中心进行处理。");
        $("#cpeSubmit").removeAttr("disabled");
        return;
    } else if (advancePeriodType == 5 && authCodeflag != 1) {
        $.alert({millis: 5000, top: '30%', text: "请验证甲方(客户)账户信息！"});
        return
    } else {
        $("#s_agreement_time_Msg").html("");
        var signatories = null;
        var isCollection = $('input[name=isCollection]:checked').val();
        var advancePeriod2 = $('input[name=advancePeriod2]:checked').val();
        if (advancePeriod2 == 5 && isCollection == 7) {
            signatories =
                "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.25','offsetY':'0.59'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.92'}]}]}]";
        } else if (agrbusstype == '2') {
            signatories =
                "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.50','offsetY':'0.40'}]}]}]";
        } else {
            signatories =
                "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.67'}]}]}]";
        }
        $.ajax({
            url: ctx + "/agreement/insertAgreement?orderId=" + orderId + "&personId=" + personId + "&cardNum=" + cardNum + "&cardTypeC=" + cardTypeC + "&cardNumC=" + cardNumC + "&serviceCode=" + serviceNo + "&partyB=" + encodeURI(encodeURI(partyB))
            + "&serviceAddress=" + encodeURI(encodeURI(serviceAddress)) + "&serviceAddressEcho=" + encodeURI(encodeURI(serviceAddressEcho))
            + "&customerAddress=" + encodeURI(encodeURI(customerAddress)) + "&customerAddressEcho=" + encodeURI(encodeURI(customerAddressEcho))
            + "&orderType=" + orderType + PeriodAndDateUrl + "&editContract=true&fileType=0&returnType=2&signApplyHashFlag=0&signatories=" + encodeURI(encodeURI(signatories)),
            data: data,
            type: "POST",
            async: false,
            traditional: true,
            success: function (data) {
                var msg = data.msg;
                if (msg == "00") {
                    $.alert({millis: 3000, top: '30%', text: "添加成功!!"});
                    closeModule('agreementAdd');
                    queryAgreement();
                } else {
                    $.alert({millis: 5000, top: '30%', text: data.msgText || "添加失败!!"});
                    closeModule('agreementAdd');
                }
            }
        });
    }
}
//新增合同确认页面
//agreement.add = function (orderId, orderStatus, orderType, orgType) {
//    if (orderStatus != 6 && orderStatus != 7 && orderStatus != 8 && orderStatus != 21 && orderStatus != 22) {
//        $.alert({text: "未确定面试成功的服务人员，无法新增合同！"});
//        return;
//    }
//    $.alert({
//        text: "<h5>预存赠送解决方案满足的条件：<br/><br/>1、录入准确合同信息；<br/><br/>2、合同规定的服务开始日期前已完成一年服务人员服务费的缴费对账；<br/><br/>3，预存一年服务人员服务费对应有效合同相应档次，一档(48000元至72000元-包含)、二档(72000元以上)</h5>",
//        callback: function (r) {
//            if (r) {
//                //var contractId = $("input[name='selectCheckBox']").val();
//                //var message = "确定要新增合同吗？新增合同将终止原合同！";
//                //if(contractId==null||contractId==""){
//                var message = "确定要新增合同吗？";
//                //}
//                $.confirm({
//                    text: message, callback: function (y) {
//                        if (y) {
//                            //删除以前的合同
//                            /*if(contractId != null && contractId != ""){
//                             deleteAGR(contractId,orderId);
//                             }*/
//                            openModule(ctx + "/jsp/agreement/order_cpe_add.jsp", {
//                                orderId: orderId,
//                                orderType: orderType,
//                                orgType: orgType
//                            }, {}, {width: '60%'}, 'agreementAdd');
//                        }
//                    }
//                });
//            }
//        }
//    });
//}

//新增合同确认页面 add 20181116 zhanghao (暂时不能上线)
agreement.add = function (orderId, orderStatus, orderType, orgType) {
    if (orderStatus != 6 && orderStatus != 7 && orderStatus != 8 && orderStatus != 21 && orderStatus != 22) {
        $.alert({text: "未确定面试成功的服务人员，无法新增合同！"});
        return;
    }
    $.ajax({
        url:ctx + "/agreement/findAgreementByOrderId",
        data:{orderId:orderId},
        type:"post",
        async:false,
        success:function(data){
            debugger;
            if(data.msg == "00"){
                $.alert({
                    text: "<h5>预存赠送解决方案满足的条件：<br/><br/>1、录入准确合同信息；<br/><br/>2、合同规定的服务开始日期前已完成一年服务人员服务费的缴费对账；<br/><br/>3，预存一年服务人员服务费对应有效合同相应档次，一档(48000元至72000元-包含)、二档(72000元以上)</h5>",
                    callback: function (r) {
                        if (r) {
                            var message = "确定要新增合同吗？";
                            $.confirm({
                                text: message, callback: function (y) {
                                    if (y) {
                                        openModule(ctx + "/jsp/agreement/order_cpe_add.jsp", {
                                            orderId: orderId,
                                            orderType: orderType,
                                            orgType: orgType
                                        }, {}, {width: '60%'}, 'agreementAdd');
                                    }
                                }
                            });
                        }
                    }
                });
            }else if(data.msg == "02"){
                $.alert({text: "已有合同必须为已删除或已终止,才能新增合同!"});
            }else{
                $.alert({text: "系统错误!"});
            }
        }
    });
}

//查询合同详情
agreement.detail = function (orderId, orderType, orgType) {
    var contractId = $("input[name='selectCheckBox']:checked").val();
    if (contractId) {
        openModule(ctx + "/jsp/agreement/order_cpe_detail.jsp", {
            contractId: contractId,
            orderId: orderId,
            orderType: orderType,
            orgType: orgType
        }, {}, {width: '60%'}, 'agreementDetail');
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}
//预览三方合同PDF
agreement.lookPDF = function () {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementModel');
        if (astate == 3) {
            $.alert({millis: 2000, top: '30%', text: "该合同已经终止！"});
        } else {

            $.ajax({
                url: ctx + "/agreement/queryContract",
                data: {contractId: contract_id, previewFlag: 1, valid: 1, agreementModel: agreementModel},
                type: "POST",
                async: false,
                traditional: true,
                success: function (data) {
                    var msg = data.msg;
                    if (msg == "00") {
                        var path = data.fileName;
                        //转义反斜杠
                        var strs = path.split("\\");
                        for (j = 0; j < strs.length; j++) {
                            path = path.replace("\\", "/");
                        }
                        path = ctx + "/" + path;
                        //在新页面中打开预览PDF
                        window.open(path, 'newWindow_' + (+new Date));
                    } else {
                        $.alert({millis: 2000, top: '30%', text: "预览合同失败！"});
                    }
                }
            });
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}

//导出合同PDF
agreement.exportPDF = function (orderId) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementModel');
        if (astate == -1) {
            showMsg('您还没有选择合同，请选择一个合同！');
            return;
        } else {
            location.href = ctx + "/agreement/queryContract?previewFlag=0&contractId=" + contract_id + "&orderId=" + orderId + "&agreementModel=" + agreementModel//+"&fileType=2&returnType=2&signApplyHashFlag=0&signatories="+encodeURI(encodeURI(signatories))
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}
//导出合同PDF
agreement.exportHomePage = function (orderId) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        if (astate == -1) {
            showMsg('您还没有选择合同，请选择一个合同！');
            return;
        } else {
            location.href = ctx + "/agreement/queryContract?previewFlag=0&homePage=1&contractId=" + contract_id + "&orderId=" + orderId
        }
        /*else if (astate == 1){//新增的合同
         showMsg('您还没有选择合同，请选择一个合同！');
         return;
         }*/
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}
//跳转修改合同页面
agreement.updateForward = function (orderId, orderType, orderStatus, orgType) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var checkstatus = $("input[name='selectCheckBox']:checked").attr("checkstatus");
        var astate = $('#agreementState_' + contract_id).val();
        var elecOtherState = $("input[name='selectCheckBox']:checked").attr('elecOtherState');
        if (astate == 2) {
            $.alert({millis: 2000, top: '30%', text: "合同已确认!不能修改！"});
        } else if (astate == 3) {
            $.alert({millis: 2000, top: '30%', text: "合同已终止!不能修改！"});
        } else if (astate == 4) {
            $.alert({millis: 2000, top: '30%', text: "合同已完成!不能修改！"});
        } else if (orderStatus == 10) {
            $.alert({millis: 2000, top: '30%', text: "订单已取消!不能修改！"});
        } else if (orderStatus == 12) {
            $.alert({millis: 2000, top: '30%', text: "订单已终止!不能修改！"});
        } else if (elecOtherState == 2 || elecOtherState == 3 || elecOtherState == 4) {
            $.alert({millis: 2000, top: '30%', text: "电子合同已经推送!不能修改！"});
        } else {
            $.alert({
                text: "<h5>预存赠送解决方案满足的条件：<br/><br/>1、录入准确合同信息；<br/><br/>2、合同规定的服务开始日期前已完成一年服务人员服务费的缴费对账；<br/><br/>3，预存一年服务人员服务费对应有效合同相应档次，一档(48000元至72000元-包含)、二档(72000元以上)</h5>",
                callback: function (r) {
                    if (r) {
                        openModule(ctx + "/jsp/agreement/order_cpe_update.jsp", {
                            contractId: contract_id,
                            orderId: orderId,
                            orderType: orderType,
                            orgType: orgType,
                            checkstatus: checkstatus
                        }, {}, {width: '60%'}, 'agreementUpdate');
                    }
                }
            });
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }

}
//修改合同
agreement.update = function (orderId, orderType) {
    //甲方和丙方是否通过身份证验证匹配
    var u_agreement_cardType = $("#u_agreement_cardType").val();
    var u_agreement_cardTypeC = $("#u_agreement_cardTypeC").val();
    var chack_cardNum_a_message = $("#chack_cardNum_a_message").data("value");
    var chack_cardNum_c_message = $("#chack_cardNum_c_message").data("value");
    var upagrbusstype = $('#s_agreementBusinessType option:selected').val();
    if (u_agreement_cardType == 1 && chack_cardNum_a_message != 1) {
        $.alert({millis: 3000, text: "请先对甲方实名认证！"});
        return;
    }
    if (u_agreement_cardTypeC == 1 && chack_cardNum_c_message != 1) {
        $.alert({millis: 3000, text: "请先对丙方实名认证！"});
        return;
    }

    if (upagrbusstype == '2') {
        var chack_cardNum_healservices_message = $("#chack_service_cardNum_message").data("value");
        if (chack_cardNum_healservices_message != 1) {
            $.alert({millis: 3000, text: "请先对陪护合同服务对象实名认证！"});
            return;
        }
    }

    //时间校验
    var creTime = $("#u_agreement_createTime").val();
    var endTime = $("#u_agreement_endTime").val();
    var start = new Date(creTime.replace(/-/g, "/"));
    var end = new Date(endTime.replace(/-/g, "/"));
    //校验签约日期
    var contractDate = new Date($("#u_agreement_contractDate").val().replace(/-/g, "/"));//签约日期
    if (contractDate > start) {
        $.alert({millis: 3000, text: "【签约日期】不能晚于【合同起始日期】！"});
        return;
    }
    //校验合同起始，终止时间 add 20181115
    debugger;
    var advancePeriodType = $('input[name=advancePeriod2]:checked').val();//预付方式
    var flag = checkAgreementDate(orderId,start,end,advancePeriodType);
    if(flag == false){
        $("#ucpeSubmit").removeAttr("disabled");
        return;
    }
    //甲方地址
    var customerAddress = getUpdateCustomerAddress();
    var customerAddressEcho = getUpdateCustomerAddressEcho();
    if (!customerAddress) {
        $.alert({millis: 3000, text: "请输入甲方地址！"});
        return;
    }
    //甲方服务场所
    var serviceAddress = getUpdateServiceAddress();
    var serviceAddressEcho = getUpdateServiceAddressEcho();
    if (!serviceAddress) {
        $.alert({millis: 3000, text: "请输入服务场所！"});
        return;
    }
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    var ucardType = $("#u_agreement_cardType").val();
    var ucardNum = "";
    if (ucardType == 1) {//身份证
        ucardNum = $("#uorder_agree_add_id_cardNum").val();
    } else if (ucardType == 2) {//护照
        ucardNum = $("#uorder_agree_add_passport_cardNum").val();
    } else if (ucardType == 3) {//驾照
        ucardNum = $("#uorder_agree_add_license_cardNum").val();
    } else if (ucardType == 4) {//营业执照
        ucardNum = $("#uorder_agree_add_businessLicense_cardNum").val();
    }
    var ucardTypeC = $("#u_agreement_cardTypeC").val();
    if (!ucardTypeC) {
        $.alert({millis: 3000, text: "丙方证件类型不能为空！"});
        return;
    }
    var ucardNumC = "";
    if (ucardTypeC == 1) {//身份证
        ucardNumC = $("#uorder_agree_add_id_cardNumC").val();
    } else if (ucardTypeC == 2) {//护照
        ucardNumC = $("#uorder_agree_add_passport_cardNumC").val();
    } else if (ucardTypeC == 3) {//驾照
        ucardNumC = $("#uorder_agree_add_license_cardNumC").val();
    }
    if (contract_id) {
        var partyB = $("#u_contractHeader").text();
        var data = $("#update_agreementForm").serialize();
        var ucreTime = $("#u_agreement_createTime").val();
        var uendTime = $("#u_agreement_endTime").val();
        var upayDate = $("#u_agreement_agreementPayDate").val();
        var uremindDay = $("#u_agreement_remindDay").val();
        var upayDateNew = $("#u_agreement_agreementPayDate_new").val();
        var uallPay = $("#u_agreement_allPay").val();
        var uotherWay = $("#u_agreement_otherWay").val();
        var uzhifuRemark = $("#u_agreement_zhifuRemark").val();
        var ustart = new Date(ucreTime.replace(/-/g, "/"));
        var uend = new Date(uendTime.replace(/-/g, "/"));

        //陪护服务验证
        var custserviceAdd = $("#s_customerserviceAddress").val();
        var hospitaliNum = $("#s_hospitalizationNum").val();
        var departmentInfo = $("#s_departments").val();
        var roomNumber = $("#s_roomNumber").val();
        var bedNumber_a = $("#s_bedNumber_a").val();
        var bedNumber_b = $("#s_bedNumber_b").val();
        var consumerstate = $("#s_consumerstate").val();
        var consumersName = $("#s_consumersName").val();
        var consumersCard = $("#s_consumersCard").val();
        var custconsumerrlVal = $("input[name='custconsumerRelation']:checked").val();
        var relation_relativesVal = $("#s_relation_relatives").val();
        var relation_entrustVal = $("#s_relation_entrust").val();
        var birthPeriodVal = $("#s_birthPeriod").val();
        var specialConsiderationsVal = $("input[name='specialConsiderations']:checked").val();
        var serviceItemsVal = $("input[name='serviceItems']:checked").val();

        var serviceStarttimeVal = $("#s_serviceStarttime").val();
        var hostsitDayVal = $("#s_hostsitDay").val();

        var serviceFormatVal = $("input[name='serviceFormat']:checked").val();
        var deliveryModeVal = $("input[name='deliveryMode']:checked").val();

        var replaceNumberVal = $("#s_replaceNumber").val();

        var serviceprojectVal = $("#s_serviceprojectStandard").val();
        var partyBaccountNameVal = $("#s_partyBaccountName").val();
        var partyBaccountNumVal = $("#s_partyBaccountNum").val();
        var partyBaccountBankVal = $("#s_partyBaccountBank").val();
        var personId = $("#u_agreement_partyCId").val();
        //陪护服务验证end

        //判断平台代付和客户直付
        if ($("input[name='isCollection']:checked").val() == 7) {
            if (upayDateNew == null || upayDateNew == "") {
                $.alert({millis: 2000, top: '30%', text: "请填写预付截止日期！"});
                $("#cpeSubmit").removeAttr("disabled");
                return;
            } else if ($("input[name='advancePeriod2']:checked").val() != 4 && (uallPay == null || uallPay == "")) {
                $.alert({millis: 2000, top: '30%', text: "请填写费用总计！"});
                $("#cpeSubmit").removeAttr("disabled");
                return;
            } else if ($("input[name='advancePeriod2']:checked").val() == 4 && (uotherWay == null || uotherWay == "")) {
                $.alert({millis: 2000, top: '30%', text: "请填写其他支付方式！"});
                $("#cpeSubmit").removeAttr("disabled");
                return;
            } else {
                PeriodAndDateUrl = "&advancePeriod=" + $("input[name='advancePeriod2']:checked").val();
                PeriodAndDateUrl += "&agreementPayDate=" + upayDateNew;
            }
        } else {
            if (upayDate == null || upayDate == "") {
                $.alert({millis: 2000, top: '30%', text: "请填写甲方预支付时间！"});
                $("#ucpeSubmit").removeAttr("disabled");
                return;
            } else if ($("input[name='advancePeriod1']:checked").val() == 1 && (uremindDay == null || uremindDay == "")) {
                $.alert({millis: 2000, top: '30%', text: "请填写预支付日期！"});
                $("#cpeSubmit").removeAttr("disabled");
                return;
            } else if ($("input[name='advancePeriod1']:checked").val() != 1 && (uzhifuRemark == null || uzhifuRemark == "")) {
                $.alert({millis: 2000, top: '30%', text: "请填写支付时间说明！"});
                $("#cpeSubmit").removeAttr("disabled");
                return;
            } else {
                PeriodAndDateUrl = "&advancePeriod=" + $("input[name='advancePeriod1']:checked").val();
                PeriodAndDateUrl += "&agreementPayDate=" + upayDate;
            }
        }
        if (uend < ustart) {
            $.alert({millis: 2000, top: '30%', text: "开始时间必须小于结束时间！"});
            $("#ucpeSubmit").removeAttr("disabled");
        } else if (ucreTime == null || ucreTime == "") {
            $.alert({millis: 2000, top: '30%', text: "请填写合同期限！"});
            //flag = false;
            $("#ucpeSubmit").removeAttr("disabled");
        } else if (uendTime == null || uendTime == "") {
            $.alert({millis: 2000, top: '30%', text: "请填写合同期限！"});
            //flag = false;
            $("#ucpeSubmit").removeAttr("disabled");
        }/*else if(agrbusstype=='2'||agrbusstype==2){
         else if(upayDate == null || upayDate == ""){
         $.alert({millis:2000,top:'30%',text:"请填写甲方预支付时间！"});
         //flag = false;
         $("#ucpeSubmit").removeAttr("disabled");
         }else if($("input[name='advancePeriod']:checked").val() == 1 && (uremindDay == null || uremindDay == "")){
         $.alert({millis:2000,top:'30%',text:"请填写预支付日期！"});
         //flag = false;
         $("#ucpeSubmit").removeAttr("disabled");
         }else if($("input[name='advancePeriod']:checked").val() != 1 && (uzhifuRemark == null || uzhifuRemark == "")){
         $.alert({millis:2000,top:'30%',text:"请填写支付时间说明！"});
         //flag = false;
         $("#ucpeSubmit").removeAttr("disabled");
         }*/

        /*if(custserviceAdd== null || custserviceAdd == ""){
         $.alert({millis:2000,top:'30%',text:"请填写服务地址！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(hospitaliNum== null || hospitaliNum == ""){
         $.alert({millis:2000,top:'30%',text:"请填写住院号！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(departmentInfo== null || departmentInfo == ""){
         $.alert({millis:2000,top:'30%',text:"请填写科室！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(roomNumber== null || roomNumber == ""){
         $.alert({millis:2000,top:'30%',text:"请填写病房号！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if((bedNumber_a== null || bedNumber_a =="")){
         $.alert({millis:2000,top:'30%',text:"请填写床号！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if((bedNumber_b==null || bedNumber_b =="")){
         $.alert({millis:2000,top:'30%',text:"请填写床号！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(consumerstate== null || consumerstate == ""){
         $.alert({millis:2000,top:'30%',text:"请填写服务对象现状！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(consumersName== null || consumersName == ""){
         $.alert({millis:2000,top:'30%',text:"请填写服务对象姓名！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(consumersCard== null || consumersCard == ""){
         $.alert({millis:2000,top:'30%',text:"请填写服务对象身份证！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(custconsumerrlVal== null || custconsumerrlVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择服务对象与甲方关系！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(custconsumerrlVal== 2 || custconsumerrlVal == "2"){
         if(relation_relativesVal==""||relation_relativesVal==null){
         $.alert({millis:2000,top:'30%',text:"请填写具体亲属关系！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         }
         if(custconsumerrlVal==3|| custconsumerrlVal == "3"){
         if(relation_entrustVal==""||relation_entrustVal==null){
         $.alert({millis:2000,top:'30%',text:"请填写具体委托关系！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         }
         if(birthPeriodVal== null || birthPeriodVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择产妇预产期！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(specialConsiderationsVal== null || specialConsiderationsVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写特殊注意事项！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(serviceItemsVal== null || serviceItemsVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择-甲方选择服务项！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(serviceStarttimeVal== null || serviceStarttimeVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择服务起始日期！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(hostsitDayVal== null || hostsitDayVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写临床陪护暂定天数！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(serviceFormatVal== null || serviceFormatVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择服务形式！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(deliveryModeVal== null || deliveryModeVal == ""){
         $.alert({millis:2000,top:'30%',text:"请选择分娩方式！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(replaceNumberVal== null || replaceNumberVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写更换次数！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(serviceprojectVal== null || serviceprojectVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写服务项目收费标准！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(partyBaccountNameVal== null || partyBaccountNameVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写乙方指定收款账户开户名称！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(partyBaccountNumVal== null || partyBaccountNumVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写乙方指定收款账户银行账号！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }
         if(partyBaccountBankVal== null || partyBaccountBankVal == ""){
         $.alert({millis:2000,top:'30%',text:"请填写乙方指定收款账户开户银行！"});
         $("#ucpeSubmit").removeAttr("disabled");
         return;
         }*/

        else {
            //flag = true;
            //判断是否是电子合同
            var agreementModel = $('#s_agreement_type option:selected').val();
            var suffix;
            var judement = false;
            if (flag) {
                if (agreementModel == '20520001') {
                    suffix = '&elecClientState=1&elecServeState=1&elecOtherState=1'
                    judement = true;
                } else {
                    suffix = '';
                    judement = true;
                }
            }

            if (judement) {
                var signatories = null;
                var isCollection = $('input[name=isCollection]:checked').val();
                var advancePeriod2 = $('input[name=advancePeriod2]:checked').val();
                var checkstatus = $("#checkstatus").val();
                //验证四要素标识
                var authCodeflag = $("#u_authCode_flag").val();
                if (authCodeflag != 1 && advancePeriod2 == 5) {
                    $.alert({millis: 5000, top: '30%', text: "请验证甲方(客户)账户信息！"});
                    return
                }
                if (advancePeriod2 == 5 && isCollection == 7) {
                    signatories =
                        "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                        "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.25','offsetY':'0.59'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.92'}]}]}]";
                } else if (upagrbusstype == '2') {
                    signatories =
                        "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                        "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.50','offsetY':'0.40'}]}]}]";
                } else {
                    signatories =
                        "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806'," +
                        "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.67'}]}]}]";

                    if (advancePeriod2 != 4) {
                        $('#u_agreement_otherWay').val('');
                    }
                }

                if (orderType == "100200040001" || orderType == "100200120005") {//一般家务
                    serviceNo = "1";
                } else if (orderType == "100200010001" || orderType == "100200120003") {//月嫂
                    serviceNo = "2";
                } else if (orderType == "100200010002" || orderType == "100200120004") {//育婴师
                    serviceNo = "3";
                } else if (orderType == "100200050001" || orderType == "100200050002") {//老人陪护,医院陪护
                    serviceNo = "4";
                } else if (orderType == "100200040002") {//钟点工
                    serviceNo = "7";
                } else {
                    serviceNo = "";
                }
                var flagTrue = $("#u_agreement_cardType").prop("disabled");
                var a = "";
                if (flagTrue) {
                    a = "&cardType=" + ucardType;
                }
                $.ajax({
                    url: ctx + "/agreement/updateAgreement?orderId=" + orderId + "&id=" + contract_id + "&cardNum=" + ucardNum + "&cardNumC=" + ucardNumC + "&cardTypeC=" + ucardTypeC + "&personId=" + personId + "&checkStatus=" + checkstatus + a
                    + "&serviceAddress=" + encodeURI(encodeURI(serviceAddress)) + "&serviceAddressEcho=" + encodeURI(encodeURI(serviceAddressEcho))
                    + "&customerAddress=" + encodeURI(encodeURI(customerAddress)) + "&customerAddressEcho=" + encodeURI(encodeURI(customerAddressEcho))
                    + "&editContract=true&fileType=0&returnType=2&signApplyHashFlag=0&signatories=" + encodeURI(encodeURI(signatories)) + "&partyB=" + encodeURI(encodeURI(partyB))
                    + "&serviceCode=" + serviceNo + "&orderType=" + orderType
                    + PeriodAndDateUrl + suffix,
                    data: data,
                    type: "POST",
                    async: false,
                    traditional: true,
                    success: function (data) {
                        var msg = data.msg;
                        var msgText = data.msgText;
                        if (msg == "00") {
                            $.alert({millis: 3000, top: '30%', text: "修改成功！"});
                            closeModule('agreementUpdate');
                            queryAgreement();
                        } else if (msgText == null) {
                            $.alert({millis: 3000, top: '30%', text: "修改失败！"});
                            closeModule('agreementUpdate');
                        } else {
                            $.alert({millis: 5000, top: '30%', text: data.msgText});
                            closeModule('agreementUpdate');
                        }
                    }
                });
            }
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}


//确认合同
agreement.sure = function (orderId, orderStatus) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    var checkedOrderStatus = $("#checkedOrderStatus").val();
    var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementmodel');
    //agreement.updateType(orderId);
    //agreement.updateType(orderId,contract_id);
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        var payStatus = $('#payStatus_' + contract_id).val();
        var valid = $("#valid_" + contract_id).val();
        if (astate == 2) {
            $.alert({millis: 2000, top: '30%', text: "合同已确认！"});
        } else if (astate == 3) {
            $.alert({millis: 2000, top: '30%', text: "合同已终止！"});
        } else if (astate == 4) {
            $.alert({millis: 2000, top: '30%', text: "合同已完成！"});
        } else if (payStatus == 20110003) {
            $.alert({millis: 3000, top: '30%', text: "订单支付完成！不能确认合同！"});
        } else if (orderStatus == 10) {
            $.alert({millis: 2000, top: '30%', text: "订单已取消!不能确认！"});
        } else if (orderStatus == 12) {
            $.alert({millis: 2000, top: '30%', text: "订单已终止!不能确认！"});
        } else if (agreementModel == '20520001') {
            $.alert({millis: 2000, top: '30%', text: "电子合同不能确认！"});
        } else {
            if (valid != 2) {
                var message = "是否确认该合同的有效性？（注意:一经确认后，该合同将无法修改、删除！）";
                $.confirm({
                    text: message, callback: function (a) {
                        if (a) {
                            $.ajax({//查询这个订单有没有已确认合同
                                url: ctx + "/agreement/queryAgreement",
                                data: {
                                    agreementState: 2,
                                    orderId: orderId
                                },
                                type: "POST",
                                async: false,
                                traditional: true,
                                success: function (data) {
                                    if (data.msg == "02") {
                                        $.ajax({
                                            url: ctx + "/agreement/updateAgreement",
                                            data: {
                                                orderId: orderId,
                                                id: contract_id,
                                                agreementState: 2,
                                                orderStatus: 6,
                                                checkedOrderStatus: checkedOrderStatus
                                            },
                                            type: "POST",
                                            async: false,
                                            traditional: true,
                                            success: function (data) {
                                                if (data.msg == "00") {
                                                    $.alert({millis: 2000, top: '30%', text: "确认成功！"});
                                                    $("#checkedOrderStatus").val(7);
                                                    closeModule();
                                                    queryAgreement();
                                                    agreement.updateType(orderId, contract_id);
                                                } else {
                                                    $.alert({millis: 2000, top: '30%', text: "确认失败！"});
                                                    closeModule();
                                                }
                                            }
                                        });
                                    } else if (data.msg == "00") {
                                        agreement.sureTwo(data.list[0].orderId, data.list[0].orderStatus, data.list[0].id);
                                        //$.alert({millis:2000,top:'30%',text:"请先把已确认合同终止在确认新的合同！"});
                                    }
                                }
                            });
                        }
                    }
                });
            }
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }

}

//确认合同
agreement.sureTwo = function (orderId, orderStatusTwo, idTwo) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    var checkedOrderStatus = $("#checkedOrderStatus").val();
    var orderStatus = $("#checkedOrderStatus").val();
    $.ajax({
        url: ctx + "/agreement/updateAgreementTwo",
        data: {
            orderId: orderId,
            agreementState: 2, //已确认
            orderStatus: 6,
            checkedOrderStatus: checkedOrderStatus,
            id: contract_id,
            reason: "合同终止,新增合同.",
            agreementStateTwo: 3, //已终止
            sureId: idTwo //已确认id
        },
        type: "POST",
        async: false,
        success: function (data) {
            if (data.msg == "00") {
                $.alert({millis: 2000, top: '30%', text: "确认成功！"});
                $("#checkedOrderStatus").val(7);
                closeModule();
                queryAgreement();
                agreement.updateType(orderId, contract_id);
            } else {
                $.alert({millis: 2000, top: '30%', text: "确认失败！"});
                closeModule();
            }
        }
    });
}
//终止合同
agreement.end = function (orderStatus) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        if (astate == 3) {
            $.alert({millis: 2000, top: '30%', text: "合同已终止！"});
        } else if (astate == 1 || astate == 4 || astate == 5) {
            $.alert({millis: 2000, top: '30%', text: "此合同状态不能终止！"});
        } else if (orderStatus == 10) {
            $.alert({millis: 2000, top: '30%', text: "订单已取消!不能终止！"});
        } else if (orderStatus == 12) {
            $.alert({millis: 2000, top: '30%', text: "订单已终止!不能终止！"});
        } else {
            openModule(ctx + '/jsp/agreement/termination_reason.jsp', {
                "astate": astate,
                "id": contract_id
            }, {}, {"width": "30%"}, 'termination_reason')
            /*		var message = "确认终止合同？";
             $.confirm({text:message,callback:function(a){
             if(a){
             $.ajax({
             url : ctx+"/agreement/updateAgreement",
             data : {
             id:contract_id,
             agreementState:3
             },
             type : "POST",
             async : false,
             traditional: true,
             success : function(data) {
             if(data.msg == "00"){
             $.alert({millis:2000,top:'30%',text:"已终止合同！"});
             closeModule();
             queryAgreement();
             }else{
             $.alert({millis:2000,top:'30%',text:"终止合同失败！"});
             closeModule();
             }
             }
             });
             }
             }
             });*/
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}
//删除合同
agreement.deleteCpe = function (orderId, orderStatus) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (contract_id) {
        var astate = $('#agreementState_' + contract_id).val();
        var valid = $("#valid_" + contract_id).val();
        if (astate == 2) {
            $.alert({millis: 2000, top: '30%', text: "合同已确认!不能删除！"});
        } else if (astate == 3) {
            $.alert({millis: 2000, top: '30%', text: "合同已终止!不能删除！"});
        } else if (astate == 4) {
            $.alert({millis: 2000, top: '30%', text: "合同已完成!不能删除！"});
        } else if (astate == 6) {
            $.alert({millis: 2000, top: '30%', text: "合同签约中!不能删除！"});
        } else if (orderStatus == 10) {
            $.alert({millis: 2000, top: '30%', text: "订单已取消!不能删除！"});
        } else if (orderStatus == 12) {
            $.alert({millis: 2000, top: '30%', text: "订单已终止!不能删除！"});
        } else {
            if (valid == 1) {
                openModule(ctx + '/jsp/agreement/delete_reason.jsp', {
                    "astate": astate,
                    "orderId": orderId,
                    "id": contract_id
                }, {}, {"width": "30%"}, 'delete_reason')
                /*	var message = "确认删除该合同？";
                 $.confirm({text:message,callback:function(a){
                 if(a){
                 $.ajax({
                 url : ctx+"/agreement/updateAgreement",
                 data : {orderId:orderId,
                 id:contract_id,
                 agreementState:5
                 },
                 type : "POST",
                 async : false,
                 traditional: true,
                 success : function(data) {
                 var msg = data.msg;
                 if(msg == "00"){
                 $.alert({millis:2000,top:'30%',text:"删除成功！"});
                 closeModule();
                 queryAgreement();
                 }else{
                 $.alert({millis:2000,top:'30%',text:"删除失败！"});
                 closeModule();
                 }
                 }
                 });
                 }
                 }
                 });*/

            }
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}


//更新海金合同签约日期
agreement.updateContractDate = function (orderId, orderType, orgType) {
    var contractId = $("input[name='selectCheckBox']:checked").val();
    if (contractId) {
        openModule(ctx + "/jsp/agreement/update_contractdate.jsp", {
            contractId: contractId,
            orderId: orderId,
            orderType: orderType,
            orgType: orgType
        }, {}, {width: '60%'}, 'updateContractDate');
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}


/*
 * 根据id修改createTime
 * xyp 20180629
 */
agreement.updateTime = function (id, createtime, createtimeNew, updateflag) {

    $.ajax({
        url: ctx + "/agreement/updateAgreementcreatedate",
        data: {
            id: id,
            createtime: createtime, //已确认
            createtimeinfo: createtimeNew, //已确认
            updateflag: updateflag //已确认
        },
        type: "POST",
        async: false,
        success: function (data) {
            if (data.msg == "00") {
                $.alert({millis: 2000, top: '30%', text: "更新成功！"});

            } else {
                $.alert({millis: 2000, top: '30%', text: "更新失败！"});
            }
            closeModule('updateContractDate');
        }
    });
}


//为新增合同，直接删除合同
function deleteAGR(contractId, orderId) {
    var agreementIds = [];
    $("input[name='selectCheckBox']").each(function () {
        agreementIds.push($(this).val());
    });
    $.ajax({
        url: ctx + "/agreement/updateAgreement",
        data: {
            agreementIds: agreementIds.toString(),
            orderId: orderId
            //agreementState:3          //已终止
        },
        type: "POST",
        async: false,
        traditional: true,
        success: function (data) {
            var msg = data.msg;
            if (msg == "00") {
                queryAgreement();
            }
        }
    });
}

//证件号码切换方法
function inputreplacethreeAgree(sele, paywayone, paywaytwo, wayone, waytwo, paywaythree, waythree, paywayfour, wayfour) {
    var formpay = $(sele).parents("form").attr("id");
    var flag = $(sele).val();
    if (flag == 1) {
        $(paywaytwo).hide();
        $(paywaythree).hide();
        $(paywayfour).hide();
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, true);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
    } else if (flag == 2) {
        $(paywayone).hide();
        $(paywaythree).hide();
        $(paywayfour).hide();
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, true);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
    } else if (flag == 3) {
        $(paywayone).hide();
        $(paywaytwo).hide();
        $(paywayfour).hide();
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, true);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
    } else if (flag == 4) {
        $(paywayone).hide();
        $(paywaytwo).hide();
        $(paywaythree).hide();
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
        $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, true);
    }
    $(sele).on("change", function () {
        switch ($(sele).val()) {
            case $(sele + ">option:eq(0)").val():
                $(paywayone).show();
                $(paywaytwo).hide();
                $(paywaythree).hide();
                $(paywayfour).hide();
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, true);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
                break;
            case $(sele + ">option:eq(1)").val():
                $(paywayone).hide();
                $(paywaythree).hide();
                $(paywaytwo).show();
                $(paywayfour).hide();
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, true);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
                break;
            case $(sele + ">option:eq(2)").val():
                $(paywayone).hide();
                $(paywaytwo).hide();
                $(paywaythree).show();
                $(paywayfour).hide();
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, true);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, false);
                break;
            case $(sele + ">option:eq(3)").val():
                $(paywayone).hide();
                $(paywaytwo).hide();
                $(paywaythree).hide();
                $(paywayfour).show();
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayone, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waytwo, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(waythree, false);
                $("#" + formpay).data('bootstrapValidator').enableFieldValidators(wayfour, true);
                break;
        }
    });
}
//转换价格类型
function intToDecimal(str) {
    return new Number(str).toFixed(2);
}

//上传合同扫描件
agreement.uploadScanFile = function () {
    var agreementId = $("input[name='selectCheckBox']:checked").val();
    if (agreementId) {
        openModule(ctx + "/jsp/agreement/uploadScanFile.jsp", {"agreementId": agreementId}, {});
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}

//查看合同扫描件
agreement.viewScanFile = function (orderId) {
    var agreementId = $("input[name='selectCheckBox']:checked").val();
    if (agreementId) {
        var ctx = $("#ctx").val();
        var flag = true;
        $.ajax({
            url: ctx + "/agreementFile/queryAgreementFile",
            data: {"agreementId": agreementId},
            type: 'post',
            async: false,
            dataType: "json",
            success: function (data) {
                var list = data.list;
                if (data.msg != "00" || !list || list.length == 0) {
                    flag = false;
                }
            }
        })
        if (!flag) {
            $.alert({millis: 2000, top: '30%', text: "未上传合同扫描件"});
            return;
        }
        openModule(ctx + "/jsp/agreement/previewScanFile.jsp", {"agreementId": agreementId}, {}, {width: "65%"});
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选择合同！"});
    }
}

/*
 查询合同类型下拉列表   dyq
 */
function getAgreementModels() {
    var retData;
    var param = {"dictCode": "2052"}
    $.ajax({
        url: ctx + "/agreement/getDictionaryInfo",
        data: param,
        type: "POST",
        async: false,
        success: function (data) {
//				console.log(data);
            if (data.msg == '00') {
                retData = data.data;
            }
        }
    });
    return retData;
}


/*
 * 发送合同的短信链接  dyq
 */
agreement.sendMessage = function (orderId, orderType, orderStatus, orgType) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (typeof contract_id != 'undefined' && contract_id != null && contract_id != '') {
        var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementmodel');

        if (agreementModel != '20520001') {
            $.alert({millis: 3000, text: "非电子合同,不能发。。"});
        } else {
            openModule(ctx + "/jsp/agreement/order_cpe_sendMessage.jsp", {
                contractId: contract_id,
                orderId: orderId,
                orderType: orderType,
                orgType: orgType
            }, {}, {width: '60%'}, 'agreementSendMessage');
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选中合同!!"});
    }
}

/**
 * 调用第三方接口的 哈希保全 接口
 */
agreement.hashBaoquan = function (orderId) {
    var signatories = '[{"identityType":"11","fullName":"北京易盟天地信息技术股份有限公司","email":"623161512@qq.com","identityCard":"91110107764338538E"}]';
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementmodel');
    var elecOtherState = $("input[name='selectCheckBox']:checked").attr('elecOtherState');
    var checkStatus = $("input[name='selectCheckBox']:checked").attr('checkStatus');
    if (contract_id != null && typeof contract_id != 'undefined' && contract_id != '') {
        if (agreementModel != '20520001') {
            $.alert({millis: 3000, text: "非电子合同,不能保全"});
        } else {
            if (elecOtherState == 3) {
                if (checkStatus == 2) {
                    //20180720日根据【201807变更-1电子合同确认规则调整】修改电子合同确认有效期为2周14d
                    $.ajax({
                        url: ctx + "/updateContractStatus/checkContractDate",
                        data: {contractId: contract_id},
                        type: "POST",
                        async: true,
                        success: function (data) {
                            if (data.msg == '00') {
                                var param = {
                                    signatories: signatories,
                                    contractName: contract_id,
                                    hashType: "2",
                                };
                                var paramStr = JSON.stringify(param);
                                $.ajax({
                                    url: ctx + "/signature/signatureHash",
                                    data: param,
                                    type: "POST",
                                    async: true,
                                    success: function (data) {
                                        if (data.msg == '00') {
                                            $.ajax({
                                                url: ctx + "/updateContractStatus/updateContractStatus",
                                                data: {
                                                    id: contract_id,
                                                    elecOtherState: 4,
                                                    orderId: orderId,
                                                    hashApplyNo: data.applyNo
                                                },
                                                type: "POST",
                                                async: false,
                                                success: function (result) {
                                                    if (result.msg == '00') {
                                                        $.alert({millis: 2000, top: '30%', text: "电子签章认证成功!!"});
                                                        queryAgreement();
                                                        agreement.updateType(orderId, contract_id);
                                                    } else {
                                                        $.alert({millis: 2000, top: '30%', text: "电子签章认证失败!!"});
                                                    }
                                                }
                                            })
                                        } else {
                                            $.alert({millis: 2000, top: '30%', text: "哈希保全失败！！"});
                                        }
                                    }
                                });
                            } else {
                                $.alert({millis: 3000, text: "合同三方签约完成后2周内不进行填报即不能确认合同,请联系结算中心!"});
                            }
                        }
                    });
                } else {
                    $.alert({millis: 4000, text: "该电子合同未经结算中心审核,审核完毕后才能确认!"});
                }
            } else if (elecOtherState == '4') {
                $.alert({millis: 3000, text: "合同已签章认证"});
            } else {
                $.alert({millis: 3000, text: "合同尚未三方签约"});
            }
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选中合同!!"});
    }

}


//合同重签
agreement.contractRemark = function (orderId) {  //$("input[name='isCollection']:checked").val() == 7
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (typeof contract_id != 'undefined' && contract_id != null && contract_id != '') {
        var agreementModel = $("input[name='selectCheckBox']:checked").attr('agreementmodel');
        var astate = $('#agreementState_' + contract_id).val();
        if (agreementModel == '20520001' && astate == 6) {
            openModule(ctx + "/jsp/agreement/order_cpe_contractRemark.jsp", {
                astate: astate,
                orderId: orderId,
                contractId: contract_id
            }, {}, {width: '60%'}, 'agreementContractRemark');
        } else {
            $.alert({millis: 3000, text: "合同必须是签约中的电子合同！"});
        }
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选中合同!!"});
    }
}


//分期回购功能
agreement.buyBack = function (orderId) {
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (typeof contract_id != 'undefined' && contract_id != null && contract_id != '') {
        $.confirm({
            millis: -1,
            title: "确认框",
            text: "如果客户海金分期逾期不还,管家应终止三方协议,并采用此功能通知海金终止海金与客户的贷款协议,确认后不可撤回,请确认!",
            width: "300px",
            callback: function (flag) {
                if (flag) {
                    $.ajax({
                        url: ctx + "/afterSalesNew/buyBackOrder",
                        type: "post",
                        datetype: "json",
                        async: false,
                        data: {
                            orderId: orderId
                        },
                        success: function (result) {
                            debugger;
                            if (result.msg == "00") {
                                $.alert({top: '30%', text: "海金分期协议终止成功!"});
                            }else if(result.msg == "01"){
                                $.alert({top: '30%', text: "未找到相关的海金协议!"});
                            }else if(result.msg = "02"){
                                $.alert({top: '30%', text: "程序执行失败!"});
                            }else {
                                $.alert({top: '30%', text: "系统错误!"});
                            }
                        }
                    });
                } else {
                    return;
                }
            }
        });
    }else{
        $.alert({millis: 2000, top: '30%', text: "请选中合同!!"});
    }
};

//自动校验生成卡券
agreement.updateType = function (orderId, contractId) {
    $.ajax({
        url: ctx + "/agreement/updateAgreementType",
        data: {
            orderId: orderId,
            contractId: contractId,
        },
        type: "POST",
        async: false,
        traditional: true,
        success: function (data) {
            //alert("成功");
        }
    });
};
agreement.sendWithholdAgreement = function (orderId) {
    var orderMobile = $("#checkedUserMobile").val();
    var userName = $("#nameBasics").text();
    var orderCodeBasics = $("#orderCodeBasics").text();
    var contract_id = $("input[name='selectCheckBox']:checked").val();
    if (typeof contract_id != 'undefined' && contract_id != null && contract_id != '') {
        $.confirm({
            title: "温馨提示",
            text: "是否确认给用户发送代扣协议短息？<br/><br/>客户姓名:" + userName + "<br/><br/>客户手机号:" + orderMobile,
            width: "500px",
            buttons: {
                ok: {
                    name: '确认',
                    btnClass: 'btn-primary',
                    handler: function () {
                        $.ajax({
                            url: ctx + "/agreement/reissueAgreementByOrderId",
                            data: {
                                orderId: orderCodeBasics,
                            },
                            type: "POST",
                            traditional: true,
                            success: function (data) {
                                var context;
                                if (data.code == '00') {
                                    context = data.result.msg;
                                } else {
                                    context = "信息发送失败";
                                }
                                $.confirm({
                                    title: "温馨提示",
                                    text: context,
                                    width: "500px",
                                    buttons: {
                                        ok: {
                                            name: '确认',
                                            handler: function () {
                                                return true;
                                            }
                                        },
                                        cancel: {
                                            name: '取消',
                                            handler: function () {
                                                return true;
                                            }
                                        }
                                    }
                                });
                            }
                        });
                        return true;
                    }
                },
                cancel: {
                    name: '取消',
                    handler: function () {
                        return true;
                    }
                }
            }
        });
    } else {
        $.alert({millis: 2000, top: '30%', text: "请选中合同!"});
    }
};

/**
 * 校验合同起始，终止时间 add 20181115 zhanghao
 *
 * 新增合同校验规则：
 * 1、不存在有效合同，不存在有效海金合同：
 * 		合同类型无校验：
 * 	        海金：合同期限间隔不得小于3个自然月
 * 2、存在有效合同，不存在有效海金合同：
 * 		允许新增合同类型：
 * 			非海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
 * 			海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间前3个月）；结束时间为:第一份有效合同的结束时间
 * 3、存在有效合同，存在有效海金合同：
 * 		允许新增合同类型：
 * 			非海金：新增合同起始时间为:（当前日期-7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
 * 4、海金合同服务时间不得小于三个月
 */
function checkAgreementDate(orderId,start,end,advancePeriodType){
    var flag = true;
    var agreementCode = $("#u_agreementCode").val();
    if(orderId != null && orderId != ""){
        $.ajax({
            url:ctx + "/agreement/checkAgreementHas",
            data:{
                orderId:orderId,
                agreementCode:agreementCode
            },
            type:"post",
            async:false,
            success:function(data){
                debugger;
                //格式化时间格式,获取毫秒值
                var startTime = new Date(getNowFormatDate(start)).getTime();//毫秒值
                var endTime = new Date(getNowFormatDate(end)).getTime();//毫秒值
                //获取合同期限最早时间(当前时间-7d)
                var minCreateTime = new Date() - (7*60*60*1000*24);
                var minCreateDate = new Date(minCreateTime);
                var minDate = getNowFormatDate(minCreateDate);//yyyy-MM-dd
                var minTime = new Date(minDate).getTime();//毫秒值
                //获取支付方式
                var isCollection = $('input[name=isCollection]:checked').val();

                if(data.msg == "01"){//存在有效合同，不存在有效海金合同
                    //获取前一份合同结束时间
                    var maxDate = data.agreement.effectDate.split(" ")[0];//(yyyy-MM-dd)
                    var effectDate = new Date(maxDate);
                    var maxTime = effectDate.getTime();//毫秒值

                    if(advancePeriodType == 5 && isCollection == 7){//新增海金保理合同
                        //之前合同结束日期-3月
                        var effectDateAfterThreeMonth = new Date(data.agreement.effectDate.split(" ")[0]).setMonth(effectDate.getMonth()-3);
                        var maxTimeAfterThreeMonth = new Date(effectDateAfterThreeMonth);
                        var maxDateAfterThreeMonth = getNowFormatDate(maxTimeAfterThreeMonth);//yyyy-MM-dd

                        //校验起始时间
                        if(startTime < minTime){//最早时间校验
                            $.alert({text: "海金合同期限开始时间不能早于:" + minDate + "/(当前日期前7日)"});
                            flag = false;
                            return;
                        }else if(startTime > maxTimeAfterThreeMonth.getTime()){//最晚时间校验
                            $.alert({text: "海金合同期限开始时间不能晚于:" + maxDateAfterThreeMonth + "/(上份合同结束时间前3个月)"});
                            flag = false;
                            return;
                        }

                        //校验结束时间
                        if(endTime != maxTime){
                            $.alert({text: "海金合同期限结束时间必须为:" + maxDate + "/(上份合同结束时间)"});
                            flag = false;
                            return;
                        }
                    }else{//新增非海金合同
                        //校验起始时间
                        if(startTime < minTime){
                            $.alert({text: "合同期限开始时间不能早于:" + minDate + "/(当前日期前7日)"});
                            flag = false;
                            return;
                        }else if(startTime > maxTime){
                            $.alert({text: "合同期限开始时间不能晚于:" + maxDate + "/(上份合同结束时间)"});
                            flag = false;
                            return;
                        }

                        //校验结束时间
                        if(endTime != maxTime){
                            $.alert({text: "合同期限结束时间必须为:"+maxDate+"/(上份合同结束时间)"});
                            flag = false;
                            return;
                        }
                    }
                }else if(data.msg == "02"){//存在有效合同，存在有效海金合同
                    //获取前一份合同结束时间
                    var maxDate = data.agreement.effectDate.split(" ")[0];//(yyyy-MM-dd)
                    var effectDate = new Date(maxDate);
                    var maxTime = effectDate.getTime();//毫秒值

                    if(advancePeriodType == 5 && isCollection == 7){//新增海金保理合同
                        $.alert({text: "订单已存在有效的海金合同,只能新增非海金合同!"});
                        flag = false;
                        return;
                    }else{//新增非海金合同
                        //校验起始时间
                        if(startTime < minTime){
                            $.alert({text: "合同期限开始时间不能早于:" + minDate + "/(当前日期前7日)"});
                            flag = false;
                            return;
                        }else if(startTime > maxTime){
                            $.alert({text: "合同期限开始时间不能晚于:" + maxDate + "/(上份合同结束时间)"});
                            flag = false;
                            return;
                        }

                        //校验结束时间
                        if(endTime != maxTime){
                            $.alert({text: "合同期限结束时间必须为:" + maxDate + "/(上份合同结束时间)"});
                            flag = false;
                            return;
                        }
                    }
                }else if(data.msg = "00"){//新增合同
                    if(advancePeriodType == 5 && isCollection == 7) {//新增海金保理合同
                        var endTimeAfterThreeMonth = end.setMonth(end.getMonth() - 3);
                        var endDate = new Date(endTimeAfterThreeMonth);
                        var nowFormatDate = getNowFormatDate(endDate);
                        //合同期限时长校验不得小于3个月
                        if(startTime > endTimeAfterThreeMonth){
                            $.alert({text: "海金合同期限间隔不得小于3个月!"});
                            flag = false;
                            return;
                        }
                    }
                }
            }
        });
    }else{//orderId为空
        $.alert({text: "未选择订单!"});
        flag = false;
    }
    return flag;
}

//时间格式化方法
function getNowFormatDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + "-" + month + "-" + strDate;
    return currentdate;
}