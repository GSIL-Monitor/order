<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<body>
  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	      <div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
	        <h4 class="modal-title">上传物流</h4>
	      </div>  
	      <div class="modal-body">
 			 <form id="excelForm" name="excelForm" method="post" action="" enctype="multipart/form-data">
 			 	<p>浏览：</p>
 				<input name="excelfile" type="file" id="excel">         
	      		<div class="modal-footer">  
	        		<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">取消</button>  
	        		<button type="button" class="btn btn-primary btn-sm" onclick="importExcel()">提交</button>
	      		</div>
	        </form>
		  </div> 
	    </div>
	  </div>
	</div> 
	
	<script type="text/javascript">
		function importExcel(){
			var ctx = $("#ctx").val();
			var data = new FormData($("#excelForm")[0]);  
		    $.ajax({  
		         url:  ctx+'/parcel/importExcel' ,  
		         type: 'POST',  
		         data: data,  
		         async: false,  
		         cache: false,  
		         contentType: false,  
		         processData: false,  
		         success: function (data) {  
		        	 closeModule();
		 			if(data.msg == "error"){
		 			    $.alert({millis:10000,text:"格式错误，只能导入后缀为xls的文件"});
		 			}else if(data.msg == "isnull"){
		 			    $.alert({millis:10000,text:"导入失败，文件为空"});
		 			}else if(data.msg == "00"){
		 			    $.alert({millis:10000,text:"导入成功！"});
		 			    selectAll(1,10);
						buttonNewOrderCount();//查询新订单条数
						buttonTobeshippedOrderCount();//查询待发货条数
						buttonAlreadyshippedOrderCount();//查询已发货条数
		 			}else if(data.msg == "01"){
		 			    $.alert({millis:10000,text:"导入失败，请稍后重试"});
		 			}
		         },  
		    });
		};
	</script> 
	
	        
</body>
</html>