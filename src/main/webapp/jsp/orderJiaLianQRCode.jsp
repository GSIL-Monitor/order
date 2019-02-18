<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>嘉联扫码支付二维码生成页</title>
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
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setFreshAccount();">×</button>  
		      	<h4 class="modal-title">嘉联支付二维码</h4>
		     </div> 
	      <div class="modal-body">
	      		<div id="JiaLianQrCodeDiv" style="height:550px;position: relative;"></div>
	      		<p class="name" id="accountIdMsg"></p>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setFreshAccount();">取消</button>  
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript">
$(function(){
	var accountId = '${param.accountId}';
	$("#accountIdMsg").text("结算单ID：  "+accountId);
	 if(accountId!=null){
		var url = ctx+"/order/jl/?orderId="+accountId;
		var html = "<iframe runat='server' src='" + url + "' frameborder='no' border='0' marginwidth='0' marginheight='0'  allowtransparency='yes' style='height:270px;width:270px;	position:absolute;left:50%;top:50%;margin-left:-135px;margin-top:-135px;'></iframe>"
		$("#JiaLianQrCodeDiv").html(html);
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

</script>
</html>