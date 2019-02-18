<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <%--<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>--%>
    <title></title>
</head>
<body>
<div class="modal fade" id="onece_agreement_edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">修改合同</h4>
            </div>
            <form class="form-inline" role="form" id="onece_agreement_edit_form">
                <div class="modal-body">
                    <input type="hidden" name="id" id="agreementId" value="${param.agreementId}"/>
                    <input type="hidden" name="orderId" id="once_agreement_edit_orderId" value=""/>
                    <div id="partyADiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>公司名称:</p>
                                <input name="partyA" id="once_agreement_edit_partyA" type="text" class="form-control"/>
                            </label>
                        </div>
                    </div>
                    <div id="cardNumDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>营业执照号:</p>
                                <input name="cardNum" id="once_agreement_edit_cardNum" type="text" class="form-control"/>
                            </label>
                        </div>
                    </div>
                    <div id="agreementCodeDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>合同号:</p>
                                <input name="agreementCode" id="once_agreement_edit_agreementCode" type="text" class="form-control"/>
                            </label>
                        </div>
                    </div>
                    <div id="dingTalkAuditCodeDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>钉钉审批号:</p>
                                <input name="dingTalkAuditCode" id="once_agreement_edit_dingTalkAuditCode" type="text"
                                       class="form-control"/>
                            </label>
                        </div>
                    </div>
                    <div id="allPayDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>合同金额:</p>
                                <input name="allPay" id="once_agreement_edit_allPay" type="number" step="0.01"
                                       class="form-control" onkeydown="inputOnlyNum(event);"
                                       onkeypress="checkIfNum(event);"/>
                            </label>
                        </div>
                    </div>
                    <div id="timeDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>合同期限:</p>

                                <input name="effectDate" id="once_agreement_edit_effectDate" type="text"
                                       class="Wdate form-control"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'once_agreement_edit_finishDate\'||\'%y-%M-{%d}\')}',readOnly:true})">
                                &nbsp;<strong>至</strong>&nbsp;
                                <input name="finishDate" id="once_agreement_edit_finishDate" type="text"
                                       class="Wdate form-control"
                                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'once_agreement_edit_effectDate\')}',readOnly:true})">
                            </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="editOneceAgreement">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function (){
        var agreementId = $("#agreementId").val();
        $.ajax({
            url:ctx + "/agreement/findAgreementById",
            data:{id:agreementId},
            type:"post",
            success:function(data){
                if(data.msg == "00"){
                    debugger;
                    var agreement = data.agreement;
                    $("#once_agreement_edit_partyA").val(agreement.partyA);
                    $("#once_agreement_edit_cardNum").val(agreement.cardNum);
                    $("#once_agreement_edit_agreementCode").val(agreement.agreementCode);
                    $("#once_agreement_edit_dingTalkAuditCode").val(agreement.dingTalkAuditCode);
                    $("#once_agreement_edit_allPay").val(agreement.allPay);
                    $("#once_agreement_edit_effectDate").val(agreement.effectDate);
                    $("#once_agreement_edit_finishDate").val(agreement.finishDate);
                    $("#once_agreement_edit_orderId").val(agreement.orderId);
                }
            }
        });
    });

    function inputOnlyNum(myEvent) {
        var keys = myEvent.keyCode;
        if (!((keys >= 48 && keys <= 57) || (keys >= 96 && keys <= 105) || ( keys == 8 ) || (keys == 46) || (keys == 37) || (keys == 39) || (keys == 13) || (keys == 229) || (keys == 190) || (keys == 110))) {
            keyIsNum = false;
        } else {
            keyIsNum = true;
        }
    }
    function checkIfNum(myEvent) {
        if (!keyIsNum) {
            $.alert({millis: 2000, text: "请输入数字！"});
            if (document.all)
                myEvent.returnValue = false;//ie
            else
                myEvent.preventDefault();//ff
        }
    }

    $("#onece_agreement_edit_form").bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled,:hidden',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            partyA: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        max: 25,
                        message: '公司名称长度为25个字符'
                    }
                }
            },
            cardNum: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-Z]+$/,
                        message: '只能输入数字和字母!'
                    }
                }
            },
            agreementCode: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-Z]+$/,
                        message: '只能输入数字和字母!'
                    }
                }
            },
            dingTalkAuditCode: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-Z]+$/,
                        message: '只能输入数字和字母!'
                    }
                }
            },
            allPay: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^\d+(\.\d{0,2})?$/,
                        message: '只能输入数字或两位小数!'
                    },
                    stringLength: {
                        max: 12,
                        message: '公司名称长度为12个字符'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        editOneceAgreement();
    });

    function editOneceAgreement() {
        $("#editOneceAgreement").css("disable", true);
        var data = $("#onece_agreement_edit_form").serialize();
        var orderId = $("#once_agreement_edit_orderId").val();
        $.ajax({
            url:ctx+"/agreement/updateOneceAgreement?"+data,
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    closeModule("onece_agreement_edit")
                    $.alert({text:"修改成功!",millis:3000});
                    queryAgreementServerOneDetail(orderId);
                }else{
                    $.alert({text:"修改失败!",millis:3000});
                    $("#editOneceAgreement").removeAttr("disable");
                }
            }
        });
    }
</script>
</html>