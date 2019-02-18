<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
		<link href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
</head>
<body>
<div id="moudle_one">
    <div class="modal fade" id="commentManageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
            <div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">上传/修改合同扫描件</h4>
				</div>
                <div class="modal-body" style="padding:27px;">
                    <div class="media ">
                        <div class="media-left  col-md-2" style="padding-left:0;width: 100%;">
                            <div class="wrap_modify">
                                <div class="modify_mess">
                                	<p class="clearfix int">
                                	<input type="hidden" id="scanFile_agreementId" value="${param.agreementId}">
		                                <label style="color:red">支持文件类型jpg、png、gif、jpeg、svg</label>
		                            </p>
		                            <p class="clearfix int">
		                                <label>第1页</label>
		                                <input type="file" id="file1" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead1" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="1" onclick="uploadScanFile('file1',this)">
                                        <span></span>
		                            </p>
		                             <p class="clearfix int">
		                                <label>第2页</label>
		                                <input type="file" id="file2" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead2" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="2" onclick="uploadScanFile('file2',this)">
                                         <span></span>
		                            </p>
		                             <p class="clearfix int">
		                                <label>第3页</label>
		                                <input type="file" id="file3" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead3" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="3" onclick="uploadScanFile('file3',this)">
                                         <span></span>
		                            </p>
		                             <p class="clearfix int">
		                                <label>第4页</label>
		                                <input type="file" id="file4" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead4" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="4" onclick="uploadScanFile('file4',this)">
                                         <span></span>
		                            </p>
		                             <p class="clearfix int">
		                                <label>第5页</label>
		                                <input type="file" id="file5" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead5" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="5" onclick="uploadScanFile('file5',this)">
                                         <span></span>
		                            </p>
		                            <p class="clearfix int">
		                                <label>第6页</label>
		                                <input type="file" id="file6" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead6" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="6" onclick="uploadScanFile('file6',this)">
                                         <span></span>
		                            </p>
		                            <p class="clearfix int">
		                                <label>第7页</label>
		                                <input type="file" id="file7" name="file_uploadScanFile" class="button-file hide"/>
                                        <img id="imghead7" name="imghead" style="border:0;width:170px;height:100px;" src="${ctx}/images/big.png" data-url="" data-rankNumber="7" onclick="uploadScanFile('file7',this)">
                                         <span></span>
		                            </p>
                                </div>
                                <div class="modify_mess">
		                            <p class="clearfix int" id="add_img_url">
		                            </p>
                                </div>
                            </div>
                            <div class="modal-footer" >
                                <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>
                                <button type="button" class="btn btn-sm btn-primary" onclick="insertScanFile()">保存</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> 
</body>
<script type="text/javascript" src="${ctx}/js/jquery-extends-base.js"></script>
<script type="text/javascript" src="${ctx}/js/ajax-fileupload.js"></script>
<script type="text/javascript">
		$(function(){
			queryAgreementFile();
		})
		/*上传图片1*/
		function uploadScanFile(tagId,thiz) {
		$("#"+tagId).click();
		$("#"+tagId).change(function(){
			var $this = $(this);
			var file = $this[0].files[0];
			var fileType = file.type;
			var fileName = file.name;
			var fileExt = $.GetFileExtension(fileName);
			if (!fileExt || ((fileExt != "jpg") && (fileExt != "png") && (fileExt != "gif") && (fileExt != "jpeg") && (fileExt != "svg"))) {
				$.alert({millis:5000,text:"不支持"+fileExt+"文件类型上传"});
				//清空file组件
				var fileInput = $this[0];
				fileInput.outerHTML = fileInput.outerHTML;
				return;
			}
			upload_file(file,thiz);
			// 清空file组件
			var fileInput = $this[0];
			fileInput.outerHTML = fileInput.outerHTML;
			});
		} 

		/*上传图片2*/
		function upload_file(file,thiz) {
			var formData = new FormData();
			formData.append('excelFile', file);
			$.AjaxFileUpload(ctx+'/scanFile/uploadFile', formData, {loading:true }, function(data){
				var newImgString = data.imgInfo.reUrl + "";
				if(newImgString){
					$(thiz).prop("src",newImgString).attr("data-url",newImgString);
					$(thiz).next("span").html(file.name);
				}else{
					$.alert({millis:5000,text:"上传图片失败,请刷新后重试"});
				}
			});
		}  
		
		/*保存文件*/
		function insertScanFile(){
			var ctx = $("#ctx").val();
			var imgheads = $("img[name=imghead]");
			var data = {};
			var i = 0;
			imgheads.each(function(j,v){
				var url = $(v).attr("data-url");
				var rankNumber = $(v).attr("data-rankNumber");
				var agreementId = $("#scanFile_agreementId").val();
				if(url && url != "undefined"){
				 data["list["+i+"].agreementId"] = agreementId;	
				 data["list["+i+"].rankNumber"]	= rankNumber;
				 data["list["+i+"].url"] = url;	
				 i++;
				}
			})
			if(i == 0){
				$.alert({millis:5000,text:"请上传图片后保存"});
				return;
			}
			$.ajax({
				url:ctx+"/agreementFile/insertAgreementFile",
		        data:data,
		        type:'post',
		        async:false,
		        dataType:"json",
		        success:function(data){
		        	if(data.msg == "00"){
		        		$.alert({millis:3000,text:"提交成功!"});
		        	}else{
		        		$.alert({millis:3000,text:"提交失败!"});
		        	}
		        	closeModule()
		        }
		    })
		}
		
		/*查询列表*/
		function queryAgreementFile(){
			var ctx = $("#ctx").val();
			var agreementId = $("#scanFile_agreementId").val();
			$.ajax({
				url:ctx+"/agreementFile/queryAgreementFile",
		        data:{"agreementId":agreementId},
		        type:'post',
		        async:false,
		        dataType:"json",
		        success:function(data){
		        	if(data.msg == "00"){
		        		var imgheads = $("img[name=imghead]");
		        		$.each(data.list,function(i,v){
		        			for (var j = 0; j < imgheads.length; j++) {
								if(v.rankNumber == $(imgheads[j]).attr("data-rankNumber")){
									$(imgheads[j]).prop("src",v.url).attr("data-url",v.url);
								}
							}
		        		})
		        	}
		        }
		    })
		}
</script>
</html>
