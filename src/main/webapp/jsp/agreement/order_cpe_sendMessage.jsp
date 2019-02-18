<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx}/js/agreement.js"></script>
<style>
		/*解决第二个modal关闭后，第一个modal不能上下滑动*/
		body{
			color:#000!important;
			font-size:12px!important;
			font-family: "Helvetica Neue",Helvetica,Arial,sans-serif!important;
		}	
</style>
<script type="text/javascript">
	
</script>
<title>发送短信</title>

</head>
<body>
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">发送短信</h4>
				</div>
					<form id="sendMessage_agreementForm" action="" method="post" class="form-inline">
					<!-- <input type="hidden" id="ucpecurrentTime"></input> -->
					<div class="modal-body">
						<div class="modal-content-basic">
						<div class="info-select clearfix">
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label><p> 合同编号：</p>
								 	<input type="text" id="u_agreementCode" name="agreementCode" readonly="readonly" class="form-control" value="" />
								 </label>
								</div>
								
								<input type="hidden" id="agreement_File_Url" value=""> 
								<input type="hidden" id="agreement_Id" value=""> 
								
								<input type="hidden" id="elecClient_State" value=""> 
								<input type="hidden" id="elecOther_State" value=""> 
								<input type="hidden" id="elecServe_State" value=""> 
								<input type="hidden" id="agreement_State" value=""> 
								
							</div>
							<div class="row">
							 	<div class="form-group col-xs-1">
								 	<input type="checkbox" id ='checkBox_a'/>
								</div>
							   <div class="form-group col-xs-5">
								 <label class="has-feedback"><p> 甲方(客户):</p>
								 	<input type="text" readonly id="party_a"  class="form-control" style="width:100px!important;" value=""/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><p>联系电话:</p>
									<input type="text" id="mobile" name="mobile" readonly class="form-control" value=""/>
								 </label>
							   </div>
							</div>
							
							<div class="row">
								<div class="form-group col-xs-1">
								 	<input type="checkbox" id ='checkBox_b'/>
								</div>
							   <div class="form-group col-xs-5">
								 <label class="has-feedback"><p> 乙方(管家):</p>
								 	<input type="text" readonly id="party_b"  class="form-control" style="width:100px!important;" value=""/>
								 </label>
								</div>
								
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><p>联系电话:</p>
									<input type="text" id="mobile_b" name="mobile_b" class="form-control" value=""/>
								 </label>
							   </div>
							</div>
							
							<div class="row">
							<div class="form-group col-xs-1">
								 	<input type="checkbox" id ='checkBox_c'/>
								</div>
							   <div class="form-group col-xs-5">
								 <label class="has-feedback"><p>丙方(服务人员):</p>
								 	<input type="text" id="party_c" style="width:100px!important;" class="form-control" style="width:200px" readOnly/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><p>联系电话：</p>
									 <input  type="text" id="mobile_c" readonly class="form-control" readOnly/>
								 </label>
							   </div>
							</div>
							
						 <div class="row">
							<div class="form-group col-xs-1">
								 	<input type="checkbox" id ='checkBox_d'/>
								</div>
							   <div class="form-group col-xs-5">
								 <label class="has-feedback"><p>补发丙方保险委托书:</p>
								 	<input type="text" id="party_d" style="width:100px!important;" class="form-control" style="width:200px" readOnly/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><p>联系电话：</p>
									 <input  type="text" id="mobile_d" readonly class="form-control" readOnly/>
								 </label>
							   </div>
							</div>
							 
					  </div> 
                     </div> 
                   </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-sm btn-primary" id="ucpeSubmit">发送</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" >
	var orderId = '${param.orderId}';
	var contractId = '${param.contractId}';
	var orderType = '${param.orderType}';
	var orgType = '${param.orgType}';
	
	
	/* debugger; */
	$(function(){
		selectAgreement(contractId,orderId);
		getAgreementFile(contractId);
		
		$("#ucpeSubmit").click(function(){
			sendMessage(); 
		})
	})
	
	function selectAgreement (contractId,orderId){
		$.ajax({
			url : ctx+"/agreement/queryAgreement",
			data : {id:contractId,orderId:orderId},
			type : "POST",
			async : true,
			traditional: true,
			success : function(data) {
				
				/* console.log(data); */
				getAgreementFile(contractId);
				if(data.msg=="00"){
					initPage(data);
					
				}
			}
		});
		
	}
	/**  周鑫  2019-01-02  **/
	var checkStatusType=0;//标记是否 甲方 和丙方
	var statusCheck=0;//用来 判断是否已经修改过状态
	/**  周鑫  2019-01-02  **/
	function sendMessage(){
		/*  debugger; */
			if(!$("#checkBox_a").is(":checked") && !$("#checkBox_c").is(":checked") && !$("#checkBox_b").is(":checked")&& !$("#checkBox_d").is(":checked")){
				$.alert({millis:3000,text:"至少选 一个!!"});
				return;
			}
			var list= new Array();
			if($("#checkBox_a").is(":checked")){
				var mobileA = $("#mobile").val();
				var identity = 'client';
				checkStatusType=1;
				list.push({mobile:mobileA,identity : identity}); 
			}
			
			if($("#checkBox_b").is(":checked")){
				var mobileB = $("#mobile_b").val().trim();
				var identity = 'manager';
				/* debugger; */;
				if(typeof mobileB !='undefined' && mobileB !=null && mobileB!='' ){
					var myreg=/^[1][3,4,5,7,8,6,9][0-9]{9}$/;  
					var flag = myreg.test(mobileB);
					if(flag){
						list.push({mobile:mobileB,identity : identity}); 
					}else{
						$.alert({millis:3000,text:"请填写正确的手机号!!"});
						return;
					}
				}else{
					$.alert({millis:3000,text:"管家请填写手机号!!"});
					return;
				}
			}
			
			if($("#checkBox_c").is(":checked")){
				var mobileC = $("#mobile_c").val();
				var identity = 'service';
				checkStatusType=1;
				list.push({mobile:mobileC,identity : identity});
			}
			var argumentId=$('input[name="selectCheckBox"]:checked').val();
			if($("#checkBox_d").is(":checked")){
				var mobileC = $("#mobile_d").val();
				var identity = 'reissueService';
				list.push({mobile:mobileC,identity : identity,argumentId:argumentId});
			}
			var param = {
				
			    compactId:$("#agreement_Id").val(),
		    	/* elecClientState:$("#elecClient_State").val(),
		    	elecOtherState:$("#elecOther_State").val(),
		    	elecServeState:$("#elecServe_State").val(),
		    	agreementState:$("#agreement_State").val(),
			    agreementFileUrl:$("#agreement_File_Url").val(), */
			    baseList:list
			}
			var paramStr=JSON.stringify(param);
			
			  $.ajax({
				url : ctx+"/sendMessage/getAgreementUrl",
				data :{jsonStr:paramStr},
				type : "POST",
				async : false,
				success : function(data) {
				 	console.log(data);
					if(data.msg == '00' ){
						getShortUrl(data);
						 sendPushMessage(); 
						  
					}
				}
			}); 
	};
	
	function sendPushMessage (){
		/*  debugger; */
		 var paramObj ={
			compactId:$("#agreement_Id").val(),
			baseList:messageShortUrls
		 }
		var paramStr = JSON.stringify(paramObj);
		$.ajax({
			url : ctx+"/sendMessage/sendPushMessage",
			data :{jsonStr:paramStr},
			type : "POST",
			async : true,
			success : function(data) {
				console.log(data); 
				if(data.msg == '00' ){
					queryAgreement();
					  closeModule("agreementSendMessage");
					  debugger
					  //修改审核状态
					  var  checkStatusText= $("#checkStatusText").val()
					  var  checkAgreementId= $("#agreement_Id").val()
					  if(checkStatusType==1&&statusCheck!=1&&checkStatusText=='驳回'){
						updateCheckType(checkAgreementId);  
						statusCheck=1;//用来 判断是否已经修改过状态 
					  }
					 $.alert({millis:3000,text:data.info}); 
				}else{
					 $.alert({millis:3000,text:data.info}); 
				}
			}
		});
	}
	var  messageShortUrls = [];
	function getShortUrl(data){
		 messageShortUrls =[];
		 var messageInfos =data.data;
		 var  emotteUrl= data.emotteUrl;
		for (var i=0; i<messageInfos.length; i++){
			var messageUrl = messageInfos[i].messageUrl;
			var  mobile = messageInfos[i].mobile;
				  $.ajax({
						url : emotteUrl+"/sms/su/ts",
						data :{url:messageUrl},
						type : "POST",
						async : false,
						success : function(data) {
							if(data.msg == '00' ){
								if(messageUrl.indexOf("reissueService")!=-1){
									var temObj ={
											
											mobile :mobile,
											shortUrl :data.shortUrl,
											templet:"order_1682",
											system:"order"
									}
								} else{
									var temObj ={
											mobile :mobile,
											shortUrl :data.shortUrl,
											templet:"other_744",
											system:"other"
									}
								}
								messageShortUrls.push(temObj);
							}
						}
					});
		}
		console.log(messageShortUrls);
	}
	
	function initPage (data){
		if(data.list[0].agreementModel!='20520001'){
			/* closeModule("agreementSendMessage"); */
			$.alert({millis:3000,text:"非电子合同,不能发。。"});
			
		}else{
				 /*甲方姓名*/ 
				if(data.list[0].partyA){
					$("#party_a").val(data.list[0].partyA);
				}
				 /*甲方电话 */
				if(data.list[0].mobile){
					$("#mobile").val(data.list[0].mobile);
				}
				 /*丙方姓名 */
				if(data.list[0].partyC){
					$("#party_c").val(data.list[0].partyC);
				}
				 /*丙方姓名 */
				if(data.list[0].partyC){
					$("#party_d").val(data.list[0].partyC);
				}
				 /*丙方电话 */
				if(data.list[0].mobileC){
					$("#mobile_c").val(data.list[0].mobileC)
				}
				 /*丙方电话 */
				if(data.list[0].mobileC){
					$("#mobile_d").val(data.list[0].mobileC)
				}
				 /*乙方*/
				 if(data.list[0].agreementState){
					$("#party_b").val(data.list[0].partyB);
				}
				 
				/*合同编号*/
				if(data.list[0].agreementCode){
					$("#u_agreementCode").val(data.list[0].agreementCode);
				}
				/*合同ID*/
				if(data.list[0].id){
					$("#agreement_Id").val(data.list[0].id);
				}
				 
				/*1,客户已签约   2,客户已驳回  */
				 if(data.list[0].elecClientState){
					$("#elecClient_State").val(data.list[0].elecClientState);
				}  
				/*1,合同待签约   2,三方已签约  3, 已电子认证  */
				 if(data.list[0].elecOtherState){
					$("#elecOther_State").val(data.list[0].elecOtherState);
				}  
				/*1,服务人员已签约  2,服务人员已驳回  */
				 if(data.list[0].elecServeState){
					$("#elecServe_State").val(data.list[0].elecServeState);
				}

				 /*合同状态：1新建，2已确认，3已终止，4已完成，5已删除 */
				 if(data.list[0].agreementState){
					$("#agreement_State").val(data.list[0].agreementState);
				}
		} 
	}
	
	
	function getAgreementFile (contractId){
			$.ajax({
				url : ctx+"/agreementFile/queryAgreementFile",
				data : {agreementId:contractId},
				type : "POST",
				async : true,
				traditional: true,
				success : function(data) {
				/* 	console.log(data); */
					if(data.msg=="00"){
						if(data.list[0].url){
							$("#agreement_File_Url").val(data.list[0].url)
						}
						
					}
				}
			});
		}	
	//修改合同状态
	function updateCheckType(checkAgreementId){
		debugger
		$.ajax({
			url : ctx+"/agreement/updateAgreementCheckStatus",
			data : {checkAgreementId:checkAgreementId},
			type : "POST",
			async : true,
			traditional: true,
			success : function(data) {
				debugger
				if(data.code=="00"){
					queryAgreement();
				}
			}
		});
	}
	
	
	
	
	</script>
</body>
</html>