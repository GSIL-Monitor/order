<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <%--<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>--%>
    <title>驳回</title>
</head>
<body>
<div class="modal fade" id="onece_agreement_turnDown" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">合同驳回</h4>
            </div>
            <form class="form-inline" role="form" id="onece_agreement_turnDown_form">
                <div class="modal-body">
                    <input type="hidden" name="id" id="agreementId" value="${param.agreementId}"/>
                    <input type="hidden" name="orderId" id="orderId" value="${param.orderId}"/>
                    <div id="reasonDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>驳回原因:</p>
                            </label>
                            <textarea name="reason" id="reason" class="form-control form-textarea" rows="3" cols="10" style="width: 40%;height: 60px;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" id="turnDownOneceAgreement">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        var id = $("#agreementId").val();
        $.ajax({
            url:ctx+"/agreement/findAgreementById",
            data:{id:id},
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    $("#reason").val(data.agreement.reason);
                }else{
                    $("#reason").val("");
                }
            }
        });
    });

    $("#onece_agreement_turnDown_form").bootstrapValidator({
        message: 'This value is not valid',
        excluded: ':disabled,:hidden',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            reason: {
                validators: {
                    notEmpty: {
                        message: '驳回原因不能为空'
                    },
                    stringLength: {
                        max: 200,
                        message: '驳回原因长度为200个字符'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        turnDownOneceAgreement();
    });

    //驳回单次合同
    function turnDownOneceAgreement() {
        $("#turnDownOneceAgreement").css("disable", true);
        var data = $("#onece_agreement_turnDown_form").serialize();
        var orderId = $("#orderId").val();
        $.ajax({
            url:ctx+"/agreement/turnDownOneceAgreement?"+data,
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    closeModule("onece_agreement_turnDown")
                    $.alert({text:"合同已驳回!",millis:3000});
                    queryAgreementServerOneDetail(orderId);
                }else{
                    $.alert({text:"驳回失败!",millis:3000});
                    $("#turnDownOneceAgreement").removeAttr("disable");
                }
            }
        });
    }
</script>
</html>