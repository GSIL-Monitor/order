<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>确认页面</title>
<script type="text/javascript">
	$(function(){
		var message = '${param.message}';
		$("#message").html(message);
	});
</script>
</head>
<body>
	  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">  
	      <div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        <h5 class="modal-title">确认页面</h5>
	      </div>  
	      <div class="modal-body text-center">  
			<h4 id="message"></h4>
		</div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" id="sure">确定</button>  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>  
	      </div>  
	    </div>
	  </div>
	</div>         
</body>
</html>