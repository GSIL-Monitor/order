<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <title>回访信息</title>
</head>
<body>
<div class="modal fade" id="addCallBack" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">新增售后回访</h4>
            </div>
            <form class="form-inline" role="form" id="addCallBackForm">
                <div class="modal-body">
                    <input type="hidden" name="afterSalesId" id="afterSalesId" value="${param.afterSalesId}"/>
                    <input type="hidden" name="orderId" id="orderId" value="${param.orderId}"/>
                    <div id="statusDiv" class="row">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>回访结果:</p>
                                <select name="status" id="status" onchange="checkStatusIsTrue()" class="form-control">
                                    <option value="1">回访成功</option>
                                    <option value="2">回访失败</option>
                                    <option value="3" selected="selected">需再次回访</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div id="reasonDiv" class="row" style="display: none">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>失败原因:</p>
                                <select name="reason" id="reason" onchange="" class="form-control">
                                    <option value="1">银行信息错误</option>
                                    <option value="2">电话号错误</option>
                                    <option value="3">回访超过三次无人接听</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div id="nextTimeDiv" class="row" style="display: none">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>下次回访时间:</p>
                            </label>
                            <input name="nextTime" id="nextTime" type="text" class="form-control Wdate"
                                   onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        </div>
                    </div>
                    <div id="remarkDiv" class="row" style="display: none">
                        <div class="form-group col-xs-12">
                            <label>
                                <p>回访备注:</p>
                            </label>
                            <textarea name="remark" id="remark" class="form-control form-textarea" rows="3" cols="10" style="width: 40%;height: 60px;"></textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="saveCallBack" onclick="addCaleBack();">保存</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {
        $("#nextTimeDiv").css("display", "block");
        $("#remarkDiv").css("display", "block");
    });

    /**
     * 回访结果状态改变页面样式
     *      1、回访成功：
     *              只能添加备注
     *      2、回访失败
     *              失败原因
     *              备注
     *      3、需再次回访
     *              下次回访时间
     *              备注
     */
    function checkStatusIsTrue() {
        var status = $("#status").val();
        if (status == 1) {//回访成功
            //div布局
            $("#nextTimeDiv").css("display", "none");
            $("#reasonDiv").css("display", "none");
            $("#remarkDiv").css("display", "block");
            $("#statusDiv").css("display", "block");
            //参数重置
            $("#reason").val("");
            $("#nextTimeDiv").val("");
        } else if (status == 2) {//回访失败
            //div布局
            $("#nextTimeDiv").css("display", "none");
            $("#statusDiv").css("display", "block");
            $("#remarkDiv").css("display", "block");
            $("#reasonDiv").css("display", "block");
            //参数重置
            $("#nextTimeDiv").val("");
        } else {//需再次回访
            //div布局
            $("#reasonDiv").css("display", "none");
            $("#nextTimeDiv").css("display", "block");
            $("#remarkDiv").css("display", "block");
            //参数重置
            $("#reason").val("");
        }
    };

    /**
     * 保存回访信息
     */
    function addCaleBack(){
        $("#saveCallBack").attr("disabled", "disabled");
        //参数校验
        var status = $("#status").val();
        if (status == 2) {//回访失败
            var reason = $("#reason").val();
            if(reason == ""){
                $.alert({text:"请选择失败原因!"});
                $("#saveCallBack").removeAttr("disabled");
                return;
            }
        } else if(status == 3) {//需再次回访
            var nextTime = $("#nextTime").val();
            if(nextTime == ""){
                $.alert({text:"请输入下次回访时间!"});
                $("#saveCallBack").removeAttr("disabled");
                return;
            }
        }
        //获取参数信息
        var afterSalesId = $("#afterSalesId").val();
        var orderId = $("#orderId").val();
        var status = $("#status").val();
        var nextTime = $("#nextTime").val();
        var reason = $("#reason").val();
        var remark = $("#remark").val();
        $.ajax({
            url:ctx+"/callBack/addCallBack",
            data:{
                afterSalesId:afterSalesId,
                orderId:orderId,
                status:status,
                nextTime:nextTime,
                reason:reason,
                remark:remark
            },
            type:"post",
            async:false,
            success: function (data) {
                if(data.msg == "00"){
                    $.alert({text:"保存成功!"});
                    closeModule("qualityCallBackAdd");
                    showCallBackList(afterSalesId,orderId);
                    queryAfterSalesByLike(0, 10);
                }else{
                    $.alert({text:"保存失败!"});
                    $("#saveCallBack").removeAttr("disabled");
                }
            }
        });
    };
</script>
</html>