<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset=utf-8">
    <script type="text/javascript" src="${ctx}/js/quality.js"></script>
    <title>回访信息</title>
</head>
<body>
<div class="widget-content">
    <div id="CallBackDetail">
        <table class="table table-condensed">
            <tr>
                <td colspan="2"><span><h4>客户信息</h4></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">开户人:
                    <lable id="callBack_bankUserName"></lable>
                </td>
            </tr>
            <tr>
                <td>开户银行:
                    <lable id="callBack_bankName"></lable>
                </td>
            </tr>
            <tr>
                <td colspan="2">卡号:
                    <lable id="callBack_bankCard"></lable>
                </td>
            </tr>
        </table>
    </div>
    <div id="callBackList">
        <table class="table table-condensed">
            <tr>
                <td colspan="2">
                    <span><h4>本次回访</h4></span>
                </td>
                <td align="center">
                	<auth:menu menuId="1354">
                    <button type="button" class="btn btn-default btn-sm" title="新增" onclick="openCallBackAdd();">新增回访</button>
                    </auth:menu>
                </td>
                <td align="center">
                	<auth:menu menuId="1355">
                    <button type="button" class="btn btn-default btn-sm" title="修改" onclick="openCallBackUpdate();">修改回访</button>
                    </auth:menu>
                </td>
            </tr>
            <tbody id="callBackListTable">

            </tbody>
            <tr>
                <td colspan="2">
                    <span><h4>历史回访</h4></span>
                </td>
            </tr>
            <tbody id="callBackListTableHistory">

            </tbody>
        </table>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    /**
     * 新增页面
     */
    function openCallBackAdd(){
        var val = $("input[name='quaRadio']:checked").val();
        var valArr = val.split("-");
        var afterSalesId = valArr[0];
        var orderId = valArr[1];
        var auditFlag = valArr[2];
        if(auditFlag != 20130014){//售后单回访中才能新增回访信息
            $.alert({text:"只有【回访中】状态的售后可以新增回访信息!"});
            return;
        }else{
            openModule('${ctx}/jsp/quality/qualityCallBackAdd.jsp', {"afterSalesId":afterSalesId,"orderId":orderId}, null, {width:'25%'},'qualityCallBackAdd')
        }
    }

    /**
     * 修改页面
     */
    function openCallBackUpdate(){
        var val = $("input[name='quaRadio']:checked").val();
        var valArr = val.split("-");
        var auditFlag = valArr[2];
        if(auditFlag != 20130014){//售后单回访中才能修改回访信息
            $.alert({text:"只有【回访中】状态的售后可以修改回访信息!"});
            return;
        }else{
            var callBackId = $("input[name='ckeckedCallBack']:checked").val();
            if(callBackId == "" || callBackId === undefined || callBackId == null){
                $.alert({text:"请选择回访信息!"});
                return;
            }else{
                openModule('${ctx}/jsp/quality/qualityCallBackUpdate.jsp', {"callBackId":callBackId}, null, {width:'25%'},'qualityCallBackUpdate')
            }
        }
    }
</script>