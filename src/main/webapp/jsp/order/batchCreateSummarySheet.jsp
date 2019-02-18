<%@ page language="java" import="java.util.*,com.emotte.server.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>批量下单</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="modal fade in" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" style="display:block;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">批量下单</h4>
            </div>
            <div class="modal-header">
                <h5 class="modal-title">不支持FA订单下单!</h5>
                <br>
                <h5 class="modal-title">录入人	
                      	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=CookieUtils.getJSONKey(request, "userName") %>
                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${param.data}
                    	</h5>
                <div class="form-group col-xs-2 pull-right">
				  <button type="button" class="btn" onclick="openHelp();" >
				  <span class="glyphicon glyphicon-question-sign"></span>怎样填报？</button>
				</div>
            </div>
            <div class="modal-body">
				<div class="info-select clearfix">
					<form class="form-inline" id="otherForm" >
                		<div class="row">
                            <div class="form-group col-xs-4">
                            	<label>
                                	选择文件:
                                	<input type="text" id="fileName" name="fileName" value="">
                                	<!-- <input type="file" id="file" name="file"> -->
                                </label>
                            </div>
			                <input type="file" id="file" name="file" class="button-file hide"/>
                            <button class="btn btn-sm btn-default" type="button" onclick="uploadfiles()">
			                	<em class="glyphicon glyphicon-plus"></em>选择文件
			                </button>
			                
                        </div>
					</form>
                </div>
            </div>
       		<!-- </form> -->
            <div class="modal-footer">
            	<input id="excelFileNew" name="excelFileNew" onclick="commit();" type="button" class="btn btn-primary" data-dismiss="modal" value="下单">
                <input type="button" class="btn btn-primary" data-dismiss="modal" value="关闭">
            </div>
        </div>
    </div>
</div>
    
    <script type="text/javascript">
    
    	function uploadfiles() {
    		$("#file").click();
    		$("#file").change(function(){
				var $this = $(this);
				var file = $this[0].files[0];
				var fileType = file.type;
				var fileName = file.name;
	    		$("#fileName").val(fileName);
				var fileExt = fileName.replace(/.*\.(.*)/, "$1");
				if (!fileExt || (fileExt != "xls" && fileExt != "xlsx")) {
					$.ShowDialog("您选择的文件为："+fileName+"</br></br>格式不正确，请确认是否选择正确的文档");
					return;
				}
				$.ShowConfirm("温馨提示", "您选择的文件是：" + fileName + "</br></br>是否继续？", function(flag) {
					if (flag) {
						$(".modal-backdrop").eq(0).hide();
						upload(file, channelName,channelCode);
					}
					// 清空file组件
					var fileInput = $this[0];
					fileInput.outerHTML = fileInput.outerHTML;
				});
			});
    		
    	}
    	function openHelp(){
	    	window.open(ctx+"/jsp/order/help.jsp",'newWindow_'+(+new Date));
	    }
	    function commit(){
	    	var fileName =$('#file').val();
	    	var ext = fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	    	var name = fileName.substring(fileName.lastIndexOf('/')+1).toLowerCase();
	    	$('#fileName').val = fileName;
	    	document.getElementById("fileName").value=fileName;
	    	if(ext !='xlsx' && ext != 'xls'){
	    		$.alert({text:"请选择正确的excel文件"});
	    		return;
	    	}else{
	    		$.ajaxFileUpload
	            (
	                {
	                    url: ctx+'/order/excelToListNew', //用于文件上传的服务器端请求地址
	                    secureuri: false, //是否需要安全协议，一般设置为false
	                    fileElementId: 'file', //文件上传域的ID
	                    dataType: 'json', //返回值类型 一般设置为json
	                    success: function (data, status)  //服务器成功响应处理函数
	                    {
	                    	if(data.success){
	                    		$.alert({text:"导入完成,请点击导入记录查看结果!"});
	    	                	queryOrders(0, 10);
	                    	}else{
	                    		$.alert({text:"导入失败，请检查excel表中必填项或表中有无数据！详情请查看导入记录信息"});
	                    	}
	                    },
	                }
	            )
	            return false;
	    	}
	    }
	    
    </script>
</body>
</html>


