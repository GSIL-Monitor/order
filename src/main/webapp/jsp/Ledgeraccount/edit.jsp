<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<button type="button" class="btn btn-sm btn-primary" onclick="saveOrderUser()">查询</button>  

</body>
<script type="text/javascript">
function saveOrderUser(){
	//alert();
	var ctx=$("#ctx").val();
	alert(ctx);
	$.ajax({
		url: "${ctx}/order/updateOrderSalary",
		data:{
			
		},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			
			
			}
})
}
</script>
</html>