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
<title>重签合同</title>

</head>
<body>
	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">重签合同</h4>
				</div>
					<form id="sendMessage_agreementForm" action="" method="post" class="form-inline">
					<!-- <input type="hidden" id="ucpecurrentTime"></input> -->
					<div class="modal-body">
						<div class="modal-content-basic">
						<div class="info-select clearfix">
							<div class="row">
							   <div class="form-group col-xs-5">
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
							 	<div class="form-group col-xs-2">
							 	   <label id="customer">
							 	    <p>是否重签:</p>
								 	<input type="checkbox" id ='checkBox_a'/>
								 </label>	
								</div>
							   <div class="form-group col-xs-3">
								 <label class="has-feedback"><p> 甲方(客户):</p>
								 	<input type="text" readonly id="party_a"  class="form-control" style="width:100px!important;" value=""/>
								 </label>
								</div>
							</div>
							
							<div class="row">
							<div class="form-group col-xs-2">
							   <label id="serve">
							    <p>是否重签:</p>
								<input type="checkbox" id ='checkBox_c'/>
								</div>
								</label>
							   <div class="form-group col-xs-3">
								 <label class="has-feedback"><p>丙方(服务人员):</p>
								 	<input type="text" id="party_c" style="width:100px!important;" class="form-control" style="width:200px" readOnly/>
								 </label>
								</div>
							</div>
							
					  </div>
                     </div> 
                   </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-sm btn-primary" id="contractSign">确定</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" >
	var orderId = '${param.orderId}';
	var contractId = '${param.contractId}';
	var isCollection ;
	var advancePeriod2 ;
	var elecOtherState = $("#elecOther_State").val();
	var datas="";
	var dataw="";
	$(function(){
		selectContract(contractId,orderId)
	})
	function selectContract(contractId,orderId){
		$.ajax({
			url : ctx+"/agreement/queryAgreement",
			data : {id:contractId,orderId:orderId},
			type : "POST",
			async : true,
			traditional: true,
			success : function(data) {
				 console.log(data); 
				if(data.msg=="00"){
					initPage(data);
				}
			}
		});
	}
	
	$("#contractSign").click(function(){
		if(!$("#checkBox_a").is(":checked") && !$("#checkBox_c").is(":checked")){
			$.alert({millis:3000,text:"至少选 一个!!"});
			return;
		}else{
			//判断是已签约还是签约中
			if($("#elecOther_State").val()==3){
				if($("#checkBox_a").is(":checked") && $("#checkBox_c").is(":checked")){
					datas="?elecOtherState=2&agreementState=1&checkStatus=1";
				}else{
					datas="?elecOtherState=2&checkStatus=1";
				}
			}else{
				datas="?elecOtherState=2&agreementState=1&checkStatus=1";
			}
			contractSign();
		}
	  })
		
	function contractSign(){
		if($("#checkBox_a").is(":checked")){
			 var signatories ='';
				if(advancePeriod2==5 && isCollection == 7){
					signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
                 "'signImg':'',"+
                 "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.35','offsetY':'0.465'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.82'}]}]}]";
				}else{
					signatorieY='0.495';
                 signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
                     "'signImg':'',"+
                     "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.35','offsetY':"+signatorieY+"}]}]}]";
				}
			    dataw={signatories:signatories,
	                    id:contractId,
	                    role:1,
	                    elecClientState:1}
			    sendAjax(dataw,datas);
		}
		
		if($("#checkBox_c").is(":checked")){
			var signatories ='';
			if(advancePeriod2==5 && isCollection == 7){
				//signatorieY='0.68';
				signatorieY='0.71';
                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
                    "'signImg':'',"+
                    "'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
			}else{
				//signatorieY='0.77';
				signatorieY='0.80';
                signatories+="[{'identityType':'11','fullName':'北京易盟天地信息技术股份有限公司','email':'623161512@qq.com','identityCard':'91110107764338538E',"+
                    "'signImg':'',"+
                    "'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.27','offsetY':"+signatorieY+"}]}]}]";
			}
			dataw={signatories:signatories,
                    id:contractId,
                    role:2,
                    elecServeState:'1'}
			sendAjax(dataw,datas);
		}
	}
	
	function sendAjax(dataw,datas){
		$.ajax({
             url: ctx + "/orderAgreementGs/contractRemark"+datas,
             /* url:"http://10.10.7.85:8080/order/orderAgreementGs/contractRemark"+datas, */ 
            type: "POST",
            data:dataw,
            dataType: "json",
            async: false,
            success: function (res) {
                console.log(res)
                if(res.msg=="00"){
                  closeModule("agreementContractRemark"); 
               	 $.alert({millis:2000,top:'30%',text:"成功！可以重签合同！"});  
                }else{
               	 $.alert({millis:2000,top:'30%',text:"重签失败！"}); 
                }
            }
        })
	}
	
	function initPage (data){
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
		 /*丙方电话 */
		if(data.list[0].mobileC){
			$("#mobile_c").val(data.list[0].mobileC)
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
		 if(data.list[0].elecClientState==1){
			$("#customer").css("display","none");
			$("#elecClient_State").val(data.list[0].elecClientState);
		}  
		/*1,合同待签约   2,三方已签约  3, 已电子认证  */
		 if(data.list[0].elecOtherState){
			$("#elecOther_State").val(data.list[0].elecOtherState);
		}  
		/*1,服务人员已签约  2,服务人员已驳回  */
		 if(data.list[0].elecServeState==1){
			 $("#serve").css("display","none");
			 $("#elecServe_State").val(data.list[0].elecServeState);
		}

		 /*合同状态：1新建，2已确认，3已终止，4已完成，5已删除 */
		 if(data.list[0].agreementState){
			$("#agreement_State").val(data.list[0].agreementState);
		}
		 if(data.list[0].advancePeriod){
	    	 advancePeriod2 = data.list[0].advancePeriod;
		}
	     if(data.list[0].isCollection){
	    	 isCollection = data.list[0].isCollection;;
		}
} 
	
	
	
	</script>
</body>
</html>