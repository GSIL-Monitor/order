<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>联动扫码支付二维码生成页</title>
<style>
.modal-body{
	position: relative;
}
.modal-body p.name{
	width:100%;
	text-align:center;
	display:block;
	font-size:14px;
	position: relative;
	bottom:98px;
}
</style>
</head>
<body>
		<div class="modal fade" >  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header" id="showdiv">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setFreshAccount();">×</button>  
		     </div> 
	      <div class="modal-body">
	      		<div id="LianDongQrCodeDiv" style="height:394px;position: relative; margin-left: 233px;"></div>
	            <p class="name" id="money"></p>
	      		<p class="name" id="accountIdMsg"></p>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderCloseModule(orderLianDongQRCode)">取消</button>
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript" src="${ctx}/js/qrcode/jquery.qrcode.min.js"></script>
<script type="text/javascript">
$(function(){
	var orderPayFeeId = '${param.orderPayFeeId}';
	var feePostPayfee = '${param.feePostPayfee}';
	var totalPay = '${param.totalPay}';
	var payMode=''
	if(feePostPayfee == '20250042'){
		var html='<h4 class="modal-title">联动微信扫码支付</h4>';
		payMode="WX";
	}else{
		var html='<h4 class="modal-title">联动支付宝扫码支付</h4>';
		payMode='AL';
	}
	$("#showdiv").append(html);
	$("#accountIdMsg").text("缴费单号：  "+orderPayFeeId);
	$("#money").text("金额：  "+totalPay);
	 if(orderPayFeeId!=null){
		 $.ajax({
	    		url:ctx+"/order/ld",
	    		data:{
	    			orderPayFeeId:orderPayFeeId,
	    			payMode:payMode
	    		},
	    		type:'post',
	    		async:false,
	    		dataType : "JSON",
	    		success:function(data){
	    			console.log(data)
	    			if(data.code == 0 && data.url!=null){
						$("#LianDongQrCodeDiv").qrcode(data.url);
	    			}else{
	    				var html = "<div  border='0' marginwidth='0' marginheight='0'  allowtransparency='yes' style='height:270px;width:270px;	position:absolute;left:40%;top:68%;margin-left:-135px;margin-top:-135px;'><h1>请重试!!!</h1></div>"
	    				$("#LianDongQrCodeDiv").html(html);
	    			}
	    			
	    		}
	    	})
	}
}
);
	//刷新页面
	function setFreshAccount(){
		var accountId = '${param.accountId}';
		var orderId = '${param.orderId}';
		var cateType = '${param.cateType}';
		var totalPay = '${param.totalPay}';
		//刷新结算单和缴费
		if(cateType==1){
			queryAccount(orderId,"accountListBodyOne",cateType,totalPay);
			showBtn(cateType,orderId);
		}else if(cateType==2){
			queryAccount(orderId,"accountListBodyCont",cateType,totalPay);
			showBtn(cateType,orderId);
		}else if(cateType==3){
			queryAccount(orderId,"accountListBodyItem",cateType,totalPay);
			showBtn(cateType,orderId);
		}else if(cateType==4){
			queryAccount(orderId,"accountListBodyThree",cateType,totalPay);
			showBtn(cateType,orderId);
		}
	}
	
	function setOrderCloseModule(modelId){
		closeModule(modelId);
	}

</script>
</html>