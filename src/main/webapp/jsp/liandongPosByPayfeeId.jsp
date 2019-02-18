<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联动POS二维码</title>
</head>
<body>
	<div class="modal fade" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header" id="showdiv">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	    		<h4 class="modal-title">联动POS二维码</h4>  
		     </div> 
	      <div class="modal-body text-center" id="liandongPosByPayfeeId">
	     				 <div class="row">
	     				 	<div class="form-group col-xs-12">
	     				 		<div class="img-thumbnail" id="liandongPosByPayfeeIdDiv"></div>
	     				 	</div>
					      	<div class="form-group col-xs-12">
						      	<ol>
								  <li><strong>支付金额:</strong><label id="liandongPosPostFeeSum"></label></li>
								  <li>&nbsp;</li>
								  <li><strong>支付方式:</strong><label id="liandongPosPostText"></label></li>
								  <li>&nbsp;</li>
								  <li><strong>缴费单号:</strong><label id="liandongPosPayfeeId"></label></li>
								  <li>&nbsp;</li>
								</ol>
					      	</div>
						</div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">关闭</button>
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript" src="${ctx}/js/qrcode/jquery.qrcode.min.js"></script>
<script type="text/javascript">
$(function(){
	var payfeeId = '${param.payfeeId}';//缴费id
	var postText = '${param.postText}';//支付方式名称
	var feeSum = '${param.feeSum}';//缴费金额
	$("#liandongPosPayfeeId").text(payfeeId);
	$("#liandongPosPostText").text(postText);
	$("#liandongPosPostFeeSum").text(feeSum);
	$("#liandongPosByPayfeeIdDiv").html("").qrcode({"text":payfeeId});
});
</script>
</html>