<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信扫码支付二维码生成页</title>
</head>
<body>
		<div class="modal fade" >  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="setWeiXinAccount();">×</button>  
		      	<h4 class="modal-title">支付二维码</h4>
		     </div> 
	      <div class="modal-body">
	      		<div id="qrCodeDiv" style="height:550px">
	      			<iframe runat='server' src='http://erp.95081.com/emPay/wxpay/order/scanpay/?orderId=${param.accountId}&source=1' width='100%' height='100%' frameborder='no' border='0' marginwidth='0' marginheight='0'  allowtransparency='yes'>
	      			</iframe>
	      		</div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setWeiXinAccount();" >取消</button>  
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript">

function setWeiXinAccount(){
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