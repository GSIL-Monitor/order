<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>完善客户信息</title>
</head>
<body>
	<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
	  <div class="modal-dialog" style="width: 60%; height: 100%;">  
	    <div class="modal-content">  
	      <div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        <h4 class="modal-title">完善客户信息</h4>
	      </div>  
	       <div class="modal-body">  
				<form  role="form" id="my-form">
					<div class="row container-fluid" style="margin-bottom: 10px;">
						<iframe width="750px" height="800px" src="" id="src"></iframe>
					</div>
		      </form>
		    </div>
		    <div class="modal-footer">  
		        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">取消</button>  
		    </div>
	    </div>
	  </div>
	</div>
		
</body>
<!-- 本页对应的js -->
<script type="text/javascript">

</script>
</html>














