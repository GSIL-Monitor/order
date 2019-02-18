<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link href="${ctx}/css/fileinput.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<input type="hidden" id="ctx" value="${ctx}"/>
<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title">上传文件</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="upload_form">
                    <div class="row container-fluid" style="margin-bottom: 10px;">
                        <div class="form-group col-xs-12">
                            <div id="addFiles">
                                <input id="file1" type="file" multiple>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/fileinput.js"></script>
<script type="text/javascript">
    $(function () {
        //初始化参数
        var returnInputId = "${param.returnInputId}";//接收返回url表单id
        var agreementId = "${param.agreementId}";//接收合同id
        var orderId = "${param.orderId}";//接收订单id
        var attachmentSpan = "${param.attachmentSpan}";//接收上传状态id
        var buttonId = "${param.buttonId}";//上传按钮id
        var initData = {
            uploadUrl: ctx + '/scanFile/uploadFile',//上传地址
            allowedFileExtensions: ['pdf'],//支持图片类型
            allowedPreviewTypes: ['image'],
            showClose: false,//不显示关闭
            maxFileSize: 10 * 1024,//文件大小不超过5M
            maxFileCount: 1,//上传文件数量
            initialCaption: "支持文件类型:pdf",
            msgFilesTooMany: "只能上传{m}个文件！",//上传文件数量验证提示
            slugCallback: function (filename) {
                return filename.replace('(', '_').replace(']', '_');
            }
        };

        //初始化加载
        $("#file1").fileinput(initData);

        //图片上传完成之后，将返回的url放到相应的span
        $("#file1").on("fileuploaded", function (event, data, previewId, index) {
            $(".kv-file-remove[type='button']").hide();
            if (data.response.msg == 0) {
                var newImgString = data.response.imgInfo.reUrl + "";
                if (newImgString && newImgString != "undefined") {
                    //返回存入成功,状态为己上传
                    $("#" + returnInputId).val(newImgString);
                    //会话缓存(storageId=_订单id_表单id)
                    isShowSaveButton();
                    var storageId = "_" + $("#orderId_once").val() + "_" + returnInputId;
                    sessionStorage.removeItem(storageId);
                    sessionStorage.setItem(storageId, newImgString);
                    var flag = saveOnceServerAgreementPDF(agreementId,newImgString);
                    debugger;
                    if(flag){
                        changeAgreementAuditStatus(orderId);
                        $.alert({millis: 3000, text: "合同扫描件保存成功!"});
                    }else{
                        queryAgreementServerOneDetail(orderId);
                        $.alert({millis: 3000, text: "合同扫描件保存失败!"});
                    }
                }
            } else {
                $.alert({millis: 3000, text: "合同扫描件上传失败!"});
            }
            closeModule();
        })

        //上传之前
        $("#file1").on("filepreupload", function () {
            $(".kv-file-remove[type='button']").hide();
            var count = $(".file-preview-success .file-preview-image").size();
            if (count == 1) {
                return {message: "只能上传1个文件,请移除后重新操作!"};
            }
        });

        //选择时
        $("#file1").on('fileselect', function (event, numFiles, label) {
            $(".kv-file-remove[type='button']").hide();
        });
    });

    /**判断是否显示保存按钮*/
    function isShowSaveButton(flag){
        if(flag==1){
            $("#submitSalarysButton").show();
        }else{
            var urls = $("#person [name='returnURL'] ");
            var count = 0;//已上传数量
            urls.each(function(i,v){
                var url = $(v).val();
                url?(count++):"";
            })
            if(urls.size() == count){
                $("#submitSalarysButton").show();
            }else{
                $("#submitSalarysButton").hide();
            }
        }
    }

    function saveOnceServerAgreementPDF(agreementId,newImgString){
        var flag = false;
        $.ajax({
            url:ctx+"/agreementFile/saveOnceServerAgreementCopy",
            data:{agreementId:agreementId,url:newImgString},
            type:"post",
            async:false,
            success:function(data){
                if(data.msg == "00"){
                    flag = true;
                }
            }
        });
        return flag;
    }

    //上传扫描件后更新审核状态 add 20190212 zhanghao
    function changeAgreementAuditStatus(orderId){
        debugger;
        $.ajax({
            url:ctx+"/agreement/changeOnceAgreementAuditStatus",
            data:{orderId:orderId},
            type:"post",
            async:false,
            success:function(data){
                debugger;
                if(data.msg == "00"){
                    queryAgreementServerOneDetail(orderId);
                }else{
                    $.alert({millis : 3000,text : "更新合同审核状态失败!"});
                }
            }
        });
    }
</script>
</html>

