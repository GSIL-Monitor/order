/**
 * @author zhangshun
 */
$(function () {
    var ctx = $("#ctx").val();
    $("[data-toggle='popover']").popover();
    //加载甲方地址
    queryCitys(null, "province_ca");
    //加载甲方服务场所
    queryCitys(null, "province_a");
    //加载服务站
    //queryServiceStation_add("serviceGarageId");
    //查询丙方(面试成功服务人员)信息
    queryPersonnel($("#checkedOrderId").val());
    //验证甲方身份证(客户)
    $("#chack_cardNum_a").on("click", function () {
        checkIdCard_partyA();
    })
    //修改甲方证件信息
    $("#s_agreement_partyA,#order_agree_add_id_cardNum").on("input", function () {
        $("#chack_cardNum_a_message").text("").data("value", "").hide();
        $("#chack_cardNum_a").show();
        $("#order_agree_add_id_cardNum").parent().nextAll("small").css("left", "353px");
    })
    //验证甲方身份证(客户)
    $("#chack_cardNum_c").on("click", function () {
        checkIdCard_partyC();
    })
    //修改丙方证件信息
    $("#s_agreement_partyC_id,#order_agree_add_id_cardNumC").on("input", function () {
        $("#chack_cardNum_c_message").text("").data("value", "").hide();
        $("#chack_cardNum_c").show();
        $("#order_agree_add_id_cardNumC").parent().nextAll("small").css("left", "353px");
    })

    //验证陪护服务对象身份证信息
    $("#chack_service_cardNum").on("click", function () {
        checkIdCard_party_forservice();
    })


    //隐藏
    $("#oldDiv").hide();
    $("#oldDateDiv").hide();
    $("#otherWayDiv").hide();

    echoSelect($("#checkedOrderId").val());
    /**新增合同页查询服务站**/
    /*function queryServiceStation_add(tagId){
     var orderId = $("#checkedOrderId").val();
     var html = "<option value='' >--请选择--</option>";
     $.ajax({
     url:ctx+"/agreement/queryServiceStation",
     type:"POST",
     datetype:"json",
     async : false,
     data:{"orderId":orderId},
     success : function(data){
     if (data.msg == "00") {
     $.each(data.list,function(i,v){
     if(v.RECHARGEDEPTSELECTED == "true"){
     html += "<option value='"+(v.DEPTID||"")+"' data-addr='"+v.DEPTADDR+"' selected>"+(v.DEPTNAME||"")+"</option>";
     }else{
     html += "<option value='"+(v.DEPTID||"")+"' data-addr='"+v.DEPTADDR+"' >"+(v.DEPTNAME||"")+"</option>";
     }
     })
     }
     }
     })
     $("#"+tagId).nextAll("div").remove();
     $("#"+tagId).html(html).checkSelect(function(){
     selectServiceStation();
     });
     $("#"+tagId).nextAll("div").css("z-index",2000);
     selectServiceStation();
     }*/

    /**选择服务站**/
    /*function selectServiceStation(){
     //获取服务站地址
     var thiz = $("#serviceGarageId").find("option:selected");
     var deptName = thiz.text();
     var deptId = thiz.val();
     var deptAddr = thiz.data("addr");
     $("#s_agreement_serviceGarage").val(deptId?deptName:"");
     $("#s_agreement_platformAddress").val(deptId?deptAddr:"");
     //手动验证
     var bv = $("#add_agreementForm").data("bootstrapValidator");
     bv.updateStatus("serviceGarageId", "NOT_VALIDATED").validateField("serviceGarageId");
     bv.updateStatus("platformAddress", "NOT_VALIDATED").validateField("platformAddress");
     //调整验证提示显示位置
     $("#serviceGarageId").parent().nextAll("small").css("left","353px");
     }*/

    /**按订单id查询面试成功的服务人员信息**/
    function queryPersonnel(orderId) {
        $.ajax({
            url: ctx + "/agreement/queryPersonnel",
            type: "POST",
            datetype: "json",
            async: false,
            data: {"orderId": orderId},
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        var option = '<option data-endTime="' + (v.END_TIME || "") + '" data-starTime="' + (v.STAR_TIME || "") + '" data-address="' + (v.CURRENT_ADDRESS || "") + '" data-cardNum="' + (v.ID_CARD_NUM || "") + '" data-cardType="' + (v.ID_CARD_TYPE || 1) + '" data-mobile="' + (v.MOBILE || "") + '" data-id="' + (v.ID || "") + '" value="' + (v.NAME || "") + '">' + (v.NAME || "") + '</option>';
                        $("#s_agreement_partyC").append(option);
                    })
                    $("#s_agreement_partyC").find("option:eq(1)").prop("selected",true);
                    $("#s_agreement_partyC").trigger("change");
                } else {
                    $.alert({millis: 5000, text: "获取丙方信息失败！"});
                }
            }
        })
    }

    /**验证甲方身份证**/
    function checkIdCard_partyA() {
        //手动验证
        var bv = $("#add_agreementForm").data("bootstrapValidator");
        bv.updateStatus("partyA", "NOT_VALIDATED").validateField("partyA");
        bv.updateStatus("cardone", "NOT_VALIDATED").validateField("cardone");
        var partyA_flag = bv.isValidField("partyA");
        var cardone_flag = bv.isValidField("cardone");
        $("#order_agree_add_id_cardNum").parent().nextAll("small").css("left", "353px");
        //调整验证提示显示位置
        if (partyA_flag && cardone_flag) {
            //验证通过
            var name = $("#s_agreement_partyA").val();
            var cardNum = $("#order_agree_add_id_cardNum").val();
            var result = checkIdCard(name, cardNum);
            if (result) {
                var code = result.code;
                var message = "", color = "red";
                if (code == 0) {
                    message = "不存在"
                } else if (code == 1) {
                    color = "green";
                    message = "匹配"
                } else if (code == 2) {
                    message = "不匹配"
                } else if (code == -1) {
                    message = "错误"
                } else {
                    message = "查询异常"
                }
                $("#chack_cardNum_a_message").text(message).data("value", code).css("color", color).show();
                $("#chack_cardNum_a").hide();
            } else {
                $.alert({millis: 5000, text: "系统忙,请重试!"});
                return;
            }
        } else {
            $.alert({millis: 5000, text: "姓名或身份证号不正确！"});
            return;
        }
    }


    /**验证陪护服务合同身份证信息**/
    function checkIdCard_party_forservice() {
        //手动验证
        var bv = $("#add_agreementForm").data("bootstrapValidator");
        bv.updateStatus("consumersName", "NOT_VALIDATED").validateField("consumersName");
        bv.updateStatus("consumersCard", "NOT_VALIDATED").validateField("consumersCard");
        var consumersName_flag = bv.isValidField("consumersName");
        var consumersCard_flag = bv.isValidField("consumersCard");
        $("#s_consumersCard").parent().nextAll("small").css("left", "353px");
        //调整验证提示显示位置
        if (consumersName_flag && consumersCard_flag) {
            //验证通过
            var name = $("#s_consumersName").val();
            var cardNum = $("#s_consumersCard").val();
            var result = checkIdCard(name, cardNum);
            if (result) {
                var code = result.code;
                var message = "", color = "red";
                if (code == 0) {
                    message = "不存在"
                } else if (code == 1) {
                    color = "green";
                    message = "匹配"
                } else if (code == 2) {
                    message = "不匹配"
                } else if (code == -1) {
                    message = "错误"
                } else {
                    message = "查询异常"
                }
                $("#chack_service_cardNum_message").text(message).data("value", code).css("color", color).show();
                $("#chack_service_cardNum").hide();
            } else {
                $.alert({millis: 5000, text: "系统忙,请重试!"});
                return;
            }
        } else {
            $.alert({millis: 5000, text: "姓名或身份证号不正确！"});
            return;
        }
    }


    /**验证丙方身份证**/
    function checkIdCard_partyC() {
        //手动验证
        var bv = $("#add_agreementForm").data("bootstrapValidator");
        bv.updateStatus("partyC", "NOT_VALIDATED").validateField("partyC");
        bv.updateStatus("cardfive", "NOT_VALIDATED").validateField("cardfive");
        var partyC_flag = bv.isValidField("partyC");
        var cardfive_flag = bv.isValidField("cardfive");
        $("#order_agree_add_id_cardNumC").parent().nextAll("small").css("left", "353px");
        if (partyC_flag && cardfive_flag) {
            //验证通过
            var name = $("#s_agreement_partyC").val();
            var cardNum = $("#order_agree_add_id_cardNumC").val();
            var result = checkIdCard(name, cardNum);
            if (result) {
                var code = result.code;
                var message = "", color = "red";
                if (code == 0) {
                    message = "不存在"
                } else if (code == 1) {
                    color = "green";
                    message = "匹配"
                } else if (code == 2) {
                    message = "不匹配"
                } else if (code == -1) {
                    message = "错误"
                } else {
                    message = "查询异常"
                }
                $("#chack_cardNum_c_message").text(message).data("value", code).css("color", color).show();
                $("#chack_cardNum_c").hide();
            } else {
                $.alert({millis: 5000, text: "系统忙,请重试!"});
                return;
            }
        } else {
            $.alert({millis: 5000, text: "姓名或身份证号不正确！"});
            return;
        }
    }

    /**验证身份证**/
    function checkIdCard(name, cardNum) {
        var result = "";
        $.ajax({
            url: ctx + "/agreement/checkIdCard",
            type: "POST",
            datetype: "json",
            async: false,
            data: {"name": name, "cardNum": cardNum},
            success: function (data) {
                result = data.result || "";
            }
        })
        return result;
    }


    $("#submitAuth").on("click", function () {
        submitAuth();
    })

})
/**合并甲方服务场所-使用字段**/
function getAddServiceAddress() {
    var addr = "";
    var province_a = $("#province_a").children("option:selected").text(); //省份
    var city_a = $("#city_a").children("option:selected").text(); //城市
    var county_a = $("#county_a").children("option:selected").text(); //县区
    var street_a = $("#street_a").val(); //街道
    var residence_community_a = $("#residence_community_a").val(); //小区
    var building_No_a = $("#building_No_a").val(); //号楼
    var unit_a = $("#unit_a").val(); //单元
    var room_a = $("#room_a").val(); //室
    addr = province_a + city_a + county_a + street_a + "街道";
    addr += (residence_community_a ? residence_community_a + "小区" : "");
    addr += (building_No_a ? building_No_a + "号楼" : "");
    addr += (unit_a ? unit_a + "单元" : "");
    addr += (room_a ? room_a + "室" : "");
    return addr.replace(/\s+/g, "");
}
/**合并甲方服务场所-回显字段**/
function getAddServiceAddressEcho() {
    var str = "", serviceAddressEcho = {};
    serviceAddressEcho.province_a = $("#province_a").children("option:selected").text() || ""; //省份
    serviceAddressEcho.city_a = $("#city_a").children("option:selected").text() || ""; //城市
    serviceAddressEcho.county_a = $("#county_a").children("option:selected").text() || ""; //县区
    serviceAddressEcho.street_a = $("#street_a").val() || ""; //街道
    serviceAddressEcho.residence_community_a = $("#residence_community_a").val() || ""; //小区
    serviceAddressEcho.building_No_a = $("#building_No_a").val() || ""; //号楼
    serviceAddressEcho.unit_a = $("#unit_a").val() || ""; //单元
    serviceAddressEcho.room_a = $("#room_a").val() || ""; //室
    str = JSON.stringify(serviceAddressEcho);
    return str.replace(/\s+/g, "");
}
/**合并甲方地址-使用字段**/
function getAddCustomerAddress() {
    var addr = "";
    var province_ca = $("#province_ca").children("option:selected").text(); //省份
    var city_ca = $("#city_ca").children("option:selected").text(); //城市
    var county_ca = $("#county_ca").children("option:selected").text(); //县区
    var street_ca = $("#street_ca").val(); //街道
    var residence_community_ca = $("#residence_community_ca").val(); //小区
    var building_No_ca = $("#building_No_ca").val(); //号楼
    var unit_ca = $("#unit_ca").val(); //单元
    var room_ca = $("#room_ca").val(); //室
    addr = province_ca + city_ca + county_ca + street_ca + "街道";
    addr += (residence_community_ca ? residence_community_ca + "小区" : "");
    addr += (building_No_ca ? building_No_ca + "号楼" : "");
    addr += (unit_ca ? unit_ca + "单元" : "");
    addr += (room_ca ? room_ca + "室" : "");
    return addr.replace(/\s+/g, "");
}
/**合并甲方地址-回显字段**/
function getAddCustomerAddressEcho() {
    var str = "", customerAddressEcho = {};
    customerAddressEcho.province_ca = $("#province_ca").children("option:selected").text() || ""; //省份
    customerAddressEcho.city_ca = $("#city_ca").children("option:selected").text() || ""; //城市
    customerAddressEcho.county_ca = $("#county_ca").children("option:selected").text() || ""; //县区
    customerAddressEcho.street_ca = $("#street_ca").val() || ""; //街道
    customerAddressEcho.residence_community_ca = $("#residence_community_ca").val() || ""; //小区
    customerAddressEcho.building_No_ca = $("#building_No_ca").val() || ""; //号楼
    customerAddressEcho.unit_ca = $("#unit_ca").val() || ""; //单元
    customerAddressEcho.room_ca = $("#room_ca").val() || ""; //室
    str = JSON.stringify(customerAddressEcho);
    return str.replace(/\s+/g, "");
}
/**
 * 新增判断是否有已确认、己终止的合同
 * 1、己确认状态优先于己终止状态;
 * 2、同种状态优先取更新时间最近的一条
 */
function echoSelect() {
    $.ajax({
        url: ctx + "/agreement/queryCanCopyAgreement",
        data: {"orderId": orderId},
        type: "POST",
        async: false,
        traditional: true,
        success: function (data) {
            if (data.msg == "00") {
                $.each(data.list, function (i, agr) {
                    //加载甲方服务场所、地址
                    echoServiceAddr(agr.serviceAddressEcho);
                    echoCustomerAddr(agr.customerAddressEcho)
                    $("input[name='isCollection']").each(function () {
                        if (agr.isCollection != null && $(this).val() == agr.isCollection) {
                            $(this).attr("checked", "checked");
                        }
                    });
                    if (agr.isCollection != null && agr.isCollection == 6) {
                        $("#oldDiv").show();
                        $("#oldDateDiv").show();

                        $("#newDiv").hide();
                        $("#newDateDiv").hide();
                        $("#otherWayDiv").hide();

                        $("input[name='advancePeriod1']").each(function () {
                            if ($(this).val() == agr.advancePeriod) {
                                $(this).attr("checked", "checked");
                            }
                        });
                        $("#s_agreement_agreementPayDate").val(agr.agreementPayDate);
                        if (agr.advancePeriod == 1) {
                            $("#s_agreement_remindDay").val(agr.remindDay);
                            $("#zhifuRemarkDiv").hide();
                        } else {
                            $("#s_agreement_zhifuRemark").val(agr.zhifuRemark);
                            $("#payDateDiv").hide();
                        }
                    } else {
                        $("#oldDiv").hide();
                        $("#oldDateDiv").hide();

                        $("#newDiv").show();
                        $("#newDateDiv").show();
                        $("#otherWayDiv").hide();

                        $("input[name='advancePeriod2']").each(function () {
                            if ($(this).val() == agr.advancePeriod) {
                                $(this).attr("checked", "checked").trigger("change");
                            }
                        });
                        $("#s_agreement_agreementPayDate_new").val(agr.agreementPayDate);
                        if (agr.advancePeriod == 4) {
                            $("#s_agreement_otherWay").val(agr.otherWay);
                            $("#s_otherWayDiv").show();
                            $("#s_allPayDiv").hide();
                        } else {
                            $("#s_agreement_allPay").val(agr.allPay);
                            $("#s_otherWayDiv").hide();
                            $("#s_allPayDiv").show();
                        }
                        if (agr.advancePeriod == 5) {
                            $("#s_prepaidMonths").val(agr.prepaidMonths || "");
                            $("#s_prepaidMoney").val(agr.prepaidMoney || "");
                            $("#s_instPrepaidMonths").val(agr.instPrepaidMonths || "");
                            $("#s_instPrepaidMoney").val(agr.instPrepaidMoney || "");
                            $("#s_limitDays").val(agr.limitDays || "");
                            $("#s_accountName").val(agr.accountName || "");
                            $("#s_accountBank").val(agr.accountBank || "");
                            $("#s_accountNum").val(agr.accountNum || "");
                        }
                    }
                    $("#s_agreement_partyA").val(agr.partyA);
                    $("#s_agreement_cardType").val(agr.cardType);
                    if (agr.cardType == 1) {
                        $("#order_agree_add_id_cardNum").val(agr.cardNum);
                    } else if (agr.cardType == 2) {
                        $("#order_agree_add_passport_cardNum").val(agr.cardNum);
                    } else if (agr.cardType == 3) {
                        $("#order_agree_add_license_cardNum").val(agr.cardNum);
                    } else if (agr.cardType == 4) {
                        $("#order_agree_add_businessLicense_cardNum").val(agr.cardNum);
                    }
                    $("#s_agreement_mobile").val(agr.mobile);
                    $("#s_agreement_serviceAddress").val(agr.serviceAddress);
                    $("#s_agreement_createTime").val(agr.effectDate);
                    $("#s_agreement_endTime").val(agr.finishDate);
                    $("#s_agreement_serviceMoney").val(agr.serviceMoney);
                    $("#s_agreement_chargeTimes").val(agr.chargeTimes);
                    $("#s_agreement_personManageMoney").val(agr.personManageMoney);
                    $("#s_agreement_customerManageMoney").val(agr.customerManageMoney);
                    $("#s_agreement_payment").val(agr.payment);
                    $("#s_agreement_otherMethods").val(agr.otherMethods);
                    $("#s_agreement_otherMatters").val(agr.otherMatters);
                    $("#s_agreement_remarkZdg").val(agr.remarkZdg);
                    $("#s_agreement_linkManName").val(agr.linkManName);
                    $("#s_agreement_linkManMobile").val(agr.linkManMobile);
                });
            } else if (data.msg == "02") {
                showCustomerManager();
            }
        }
    });
}
/**甲方服务场所-回显字段-给表单赋值**/
function echoServiceAddr(serviceAddressEcho) {
    if (serviceAddressEcho && serviceAddressEcho.indexOf("{") != -1 && serviceAddressEcho.indexOf("}") != -1) {
        var sae = JSON.parse(serviceAddressEcho);
        $("#province_a").children("option[data-name='" + (sae.province_a || "") + "']").prop("selected", true).trigger("change"); //省份
        $("#city_a").children("option[data-name='" + (sae.city_a || "") + "']").prop("selected", true).trigger("change");//城市
        $("#county_a").children("option[data-name='" + (sae.county_a || "") + "']").prop("selected", true);//省份
        $("#street_a").val(sae.street_a || ""); //街道
        $("#residence_community_a").val(sae.residence_community_a || ""); //小区
        $("#building_No_a").val(sae.building_No_a || ""); //号楼
        $("#unit_a").val(sae.unit_a || ""); //单元
        $("#room_a").val(sae.room_a || ""); //室
    }
}
/**甲方地址-回显字段-给表单赋值**/
function echoCustomerAddr(customerAddressEcho) {
    if (customerAddressEcho && customerAddressEcho.indexOf("{") != -1 && customerAddressEcho.indexOf("}") != -1) {
        var sae = JSON.parse(customerAddressEcho);
        $("#province_ca").children("option[data-name='" + (sae.province_ca || "") + "']").prop("selected", true).trigger("change");//省份
        $("#city_ca").children("option[data-name='" + (sae.city_ca || "") + "']").prop("selected", true).trigger("change"); //城市
        $("#county_ca").children("option[data-name='" + (sae.county_ca || "") + "']").prop("selected", true); //县区
        $("#street_ca").val(sae.street_ca || ""); //街道
        $("#residence_community_ca").val(sae.residence_community_ca || ""); //小区
        $("#building_No_ca").val(sae.building_No_ca || ""); //号楼
        $("#unit_ca").val(sae.unit_ca || ""); //单元
        $("#room_ca").val(sae.room_ca || ""); //室
    }
}

/**查询城市
 *@param id
 *@param levels
 *@param code
 * */
function queryCitys(baseCity, tagId) {
    var html = "<option value='' >--请选择--</option>";
    var settings = {"levels": 1}
    jQuery.extend(settings, baseCity);
    if (settings.levels == 1 || (settings.levels != 1 && settings.code)) {
        $.ajax({
            url: ctx + "/agreement/queryCitys",
            data: settings,
            type: "POST",
            async: false,
            success: function (data) {
                if (data.msg == "00") {
                    $.each(data.list, function (i, v) {
                        html += "<option value='" + v.code + "' data-name='" + v.name + "' data-id='" + v.id + "' data-pid='" + v.pid + "'>" + v.name + "</option>";
                    });
                }
            }
        });
    }
    $("#" + tagId).empty().html(html).trigger("change");
}


//回显客户信息
function showCustomerManager() {
    var userId = $("#checkedUserId").val();
    $.ajax({
        url: ctx + "/agreement/showCustomerManager",
        data: {
            userId: userId
        },
        type: "POST",
        async: false,
        success: function (data) {
            if (data.msg == "00") {
                $.each(data.list, function (key, values) {
                    var relatinformation = values.RELATINFORMATION || "";
                    var cardtype = values.CARDTYPE || "1";
                    var cardnum = values.CARDNUM || "";
                    if (relatinformation) {
                        var xinxi = relatinformation.split(",");
                        $("#s_agreement_linkManName").val(xinxi[0] || "");
                        $("#s_agreement_linkManMobile").val(xinxi[1] || "");
                    }
                    $("#s_agreement_mobile").val(values.MOBILE || "");
                    $("#s_agreement_partyA").val(values.REALNAME || "");
                    $("#s_agreement_cardType").val(cardtype).trigger("change");
                    if (cardtype == 1) {
                        $("#order_agree_add_id_cardNum").val(cardnum);
                    } else if (cardtype == 2) {
                        $("#order_agree_add_passport_cardNum").val(cardnum);
                    } else if (cardtype == 3) {
                        $("#order_agree_add_license_cardNum").val(cardnum);
                    }
                })
            }
        }
    });
}

//20180806《配合海金的合同填报规则调整》需求单实现：[费用总计] 填写并回填 [本次费用合计]
function changePayment() {
    var allpay = $("#s_agreement_allPay").val();
    $("#s_agreement_payment").val(allpay);
    var bv = $("#add_agreementForm").data("bootstrapValidator");
    bv.updateStatus("allPay", "NOT_VALIDATED").validateField("allPay");
    bv.updateStatus("payment", "NOT_VALIDATED").validateField("payment");
}
function changeAllpay() {
    var payment = $("#s_agreement_payment").val();
    $("#s_agreement_allPay").val(payment);
    var bv = $("#add_agreementForm").data("bootstrapValidator");
    bv.updateStatus("allPay", "NOT_VALIDATED").validateField("allPay");
    bv.updateStatus("payment", "NOT_VALIDATED").validateField("payment");
}

//发送验证码
function getAuthCode() {
    var userMoble = $("#s_agreement_mobile").val();
    if (userMoble == null || userMoble == "") {
        $.alert({millis: 5000, text: "请输入电话号码!"});
        return;
    }

}
//四要素验证
function submitAuth() {
    var userMoble = $("#s_accountMobile").val();
    if (userMoble == null || userMoble == "") {
        $.alert({millis: 5000, text: "请输入预留电话号码!"});
        return;
    }
    var customName = $("#s_accountName").val();
    if (customName == null || customName == "") {
        $.alert({millis: 5000, text: "请输入甲方(客户)账户姓名!"});
        return;
    }
    var bankName = $("#s_accountBank").val();
    if (bankName == 1) {
        $.alert({millis: 5000, text: "请选择甲方(客户)账户开户行"});
        return;
    }
    var bankAccountNum = $("#s_accountNum").val();
    if (bankAccountNum == null || bankAccountNum == "") {
        $.alert({millis: 5000, text: "请输入甲方(客户)账户号码!"});
        return;
    }
    var customIdCard = $("#order_agree_add_id_cardNum").val();
    if (customIdCard == null || customIdCard == "") {
        $.alert({millis: 5000, text: "请输入身份证!"});
        return;
    }

    $.ajax({
        url: ctx + "/agreement/submitAuth",
        data: {
            userMoble: userMoble,
            customName: customName,
            bankName: bankName,
            bankAccountNum: bankAccountNum,
            customIdCard: customIdCard,
            applyType: 1
        },
        type: "POST",
        beforeSend: function () {
            $("#cpeSubmit").attr("disabled", true);
            $("#submitAuth").attr("disabled", true);
            $.alert({millis: 5000, text: "信息正在校验，请耐心等待!"});
        },
        success: function (data) {
            if (data.code == 00) {
                $("#s_authCode_flag").val("1");
                $("#cpeSubmit").removeAttr("disabled");
                $("#submitAuth").attr("disabled", true);
                $.alert({millis: 5000, text: data.msg || "验证失败!"});
            } else if (data.code == 02) {
                $("#submitAuth").removeAttr("disabled");
                $.alert({millis: 5000, text: "请再次点击验证!"});
                return;
            } else {
                $("#submitAuth").removeAttr("disabled");
                $.alert({millis: 5000, text: data.msg || "验证失败!"});
                return;
            }
        }
    });
}