<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
  body{overflow:auto;}  

#ceshimodel {overflow: auto !important;}  
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript" src="${ctx}/js/agreement.js"></script>
<script type="text/javascript">
	var orderId = '${param.orderId}';
	var contractId = '${param.contractId}';
	var orderType = '${param.orderType}';
	var orgType = '${param.orgType}';
	
	var agreementModel='${param.agreementModel}';
	var normalContactfile='${param.normalContactfile}';
	//证件类型: 1 身份证 2 护照  3驾照
	var cardType = {1:"身份证", 2:"护照", 3:"驾照", 4:"营业执照"};
	$(function(){
		
		initAgreementList(getAgreementModels());
			//钟点工备注展示
			if(orderType == "100200040002" ){//钟点工
				$("#d_remarkZdgDiv").show();
			}else{
				$("#d_remarkZdgDiv").hide();
			}
			//加盟商不显示代收非代收 
			/* if(orderType != "" && orderType == 3 ){//加盟商
				$("#detail_isCollectionDiv").show();
			}else{
				$("#detail_isCollectionDiv").hide();
			} */
			$.ajax({
				url : ctx+"/agreement/queryAgreement",
				data : {id:contractId,orderId:orderId},
				type : "POST",
				async : false,
				traditional: true,
				success : function(data) {
					console.log(data);
					if(data.msg == "00"){
						$.each(data.list,function(i,agr){
							$("#d_agreementCode").text(agr.agreementCode);
							$("#d_agreementModel").text(agr.agreementModelName);
							
							if(agr.agreementBusinessType=='2'){
								$("#d_agreementBusinessType").text("医院陪护");
							}else if(agr.agreementBusinessType=='1'){
								$("#d_agreementBusinessType").text("三方协议");
							}
							
							$("input[name='isCollection']").each(function() {
					        	if (agr.isCollection != null && $(this).val() == agr.isCollection) {
						        	$(this).attr("checked", "checked");
						        	}
						    	});
							
							if(agr.isCollection != null && agr.isCollection == 7){
				        		/* $("#d_oldDiv").show();
				    			$("#d_oldDateDiv").show();
				    			
				    			$("#d_newDiv").hide();
				    			$("#d_newDateDiv").hide();
				    			$("#d_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod1']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked");
							        	}
							    	});
								$("#d_agreement_agreementPayDate").text(agr.agreementPayDate);
								if(agr.advancePeriod == 1){
									$("#d_agreement_remindDay").text(agr.remindDay);
									$("#dzhifuRemarkDiv").hide();
								}else{
									$("#d_agreement_zhifuRemark").text(agr.zhifuRemark);
									$("#dpayDateDiv").hide();
								} */
								 $("#d_oldDiv").hide();
				    			$("#d_oldDateDiv").hide();
				    			
				    			$("#d_newDiv").show();
				    			$("#d_newDateDiv").show();
				    			$("#d_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod2']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked");
							        	}
							    	});
								$("#d_agreement_agreementPayDate_new").text(agr.agreementPayDate);
								if(agr.advancePeriod == 4){
									$("#d_agreement_otherWay").text(agr.otherWay);
									$("#d_otherWayDiv").show();
									$("#d_allPayDiv").hide();
								}else{
									$("#d_agreement_allPay").text(agr.allPay);
									$("#d_otherWayDiv").hide();
									$("#d_allPayDiv").show();
								} 
								if(agr.advancePeriod == 5){
									$("#hjbl_new_div1_hr").show();//分隔线
									$("#hjbl_new_div2_hr").show();//分隔线
									$("#hjbl_new_div1").show();
									$("#hjbl_new_div2").show();
									$("#d_prepaidMonths").text(agr.prepaidMonths||"");
									$("#d_prepaidMoney").text(agr.prepaidMoney||"");
									$("#d_instPrepaidMonths").text(agr.instPrepaidMonths||"");
									$("#d_instPrepaidMoney").text(agr.instPrepaidMoney||"");
									$("#d_limitDays").text(agr.limitDays||"");
									$("#d_accountName").text(agr.accountName||"");
									$("#d_accountBank").text(agr.accountBank||"");
									$("#d_accountNum").text(agr.accountNum||"");
								}
				        	}else{
				        		/* $("#d_oldDiv").hide();
				    			$("#d_oldDateDiv").hide();
				    			
				    			$("#d_newDiv").show();
				    			$("#d_newDateDiv").show();
				    			$("#d_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod2']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked");
							        	}
							    	});
								$("#d_agreement_agreementPayDate_new").text(agr.agreementPayDate);
								if(agr.advancePeriod == 4){
									$("#d_agreement_otherWay").text(agr.otherWay);
									$("#d_otherWayDiv").show();
									$("#d_allPayDiv").hide();
								}else{
									$("#d_agreement_allPay").text(agr.allPay);
									$("#d_otherWayDiv").hide();
									$("#d_allPayDiv").show();
								} */
								
								$("#d_oldDiv").show();
				    			$("#d_oldDateDiv").show();
				    			
				    			$("#d_newDiv").hide();
				    			$("#d_newDateDiv").hide();
				    			$("#d_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod1']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked");
							        	}
							    	});
								$("#d_agreement_agreementPayDate").text(agr.agreementPayDate);
								if(agr.advancePeriod == 1){
									$("#d_agreement_remindDay").text(agr.remindDay);
									$("#dzhifuRemarkDiv").hide();
								}else{
									$("#d_agreement_zhifuRemark").text(agr.zhifuRemark);
									$("#dpayDateDiv").hide();
								}
				        	}
							
							
							$("#d_agreement_partyA").text(agr.partyA);
							$("#d_agreement_cardType").text(cardType[agr.cardType]);
							if(agr.cardType == 1){
								$("#d_order_agree_add_id_cardNum").text(agr.cardNum);
								$(".d_order-card-way-two").hide();
								$(".d_order-card-way-three").hide();
								$(".d_order-card-way-four").hide();
							}else if(agr.cardType == 2){
								$("#d_order_agree_add_passport_cardNum").text(agr.cardNum);
								$(".d_order-card-way-one").hide();
								$(".d_order-card-way-three").hide();
								$(".d_order-card-way-four").hide();
							}else if(agr.cardType == 3){
								$("#d_order_agree_add_license_cardNum").text(agr.cardNum);
								$(".d_order-card-way-one").hide();
								$(".d_order-card-way-two").hide();
								$(".d_order-card-way-four").hide();
							}else if(agr.cardType == 4){
								$("#d_order_agree_add_businessLicense_cardNum").text(agr.cardNum);
								$(".d_order-card-way-one").hide();
								$(".d_order-card-way-two").hide();
								$(".d_order-card-way-three").hide();
							}
							$("#d_agreement_mobile").text(agr.mobile);
							$("#d_agreement_customerAddress").text(agr.serviceAddress); //合同详情展示服务地址，原展示甲方地址去掉customerAddress
							$("#d_contractHeader").text(agr.partyB);
							//$("#d_agreement_serviceGarage").text(agr.serviceGarage);
							//乙方联系电话
							$("#d_mobileB").text(agr.mobileB);
							$("#d_agreement_platformAddress").text(agr.platformAddress);
							$("#d_agreement_partyC").text(agr.partyC);
							$("#d_agreement_mobileC").text(agr.mobileC);
							$("#d_agreement_cardTypeC").text(cardType[agr.cardTypeC]);
							if(agr.cardTypeC == 1){
								$("#d_order_agree_add_id_cardNumC").text(agr.cardNumC);
								$(".d_order-cardC-way-two").hide();
								$(".d_order-cardC-way-three").hide();
							}else if(agr.cardTypeC == 2){
								$("#d_order_agree_add_passport_cardNumC").text(agr.cardNumC); 
								$(".d_order-cardC-way-one").hide();
								$(".d_order-cardC-way-three").hide();
							}else if(agr.cardTypeC == 3){
								$("#d_order_agree_add_license_cardNumC").text(agr.cardNumC);
								$(".d_order-cardC-way-one").hide();
								$(".d_order-cardC-way-two").hide();
							}
							$("#d_agreement_waiterAddress").text(agr.waiterAddress);
							$("#d_agreement_serviceAddress").text(agr.serviceAddress);
							$("#d_agreement_createTime").text(agr.effectDate);
							$("#d_agreement_endTime").text(agr.finishDate);
							$("#d_agreement_serviceMoney").text(intToDecimal(agr.serviceMoney));
							$("#d_agreement_chargeTimes").text(agr.chargeTimes);
							$("#d_agreement_personManageMoney").text(intToDecimal(agr.personManageMoney));
							$("#d_agreement_customerManageMoney").text(intToDecimal(agr.customerManageMoney));
							$("#d_agreement_payment").text(intToDecimal(agr.payment));
							/* $("input[name='advancePeriod']").each(function() {
					        	if ($(this).val() == agr.advancePeriod) {
						        	$(this).attr("checked", "checked");
						        	}
						    	});
							$("#d_agreement_agreementPayDate").text(agr.agreementPayDate);
							if(agr.advancePeriod == 1){
								$("#d_agreement_remindDay").text(agr.remindDay);
								$("#dzhifuRemarkDiv").hide();
							}else{
								$("#d_agreement_zhifuRemark").text(agr.zhifuRemark);
								$("#dpayDateDiv").hide();
							} */
							$("#d_agreement_otherMethods").text(agr.otherMethods);
							$("#d_agreement_otherMatters").text(agr.otherMatters);
							$("#d_agreement_remarkZdg").text(agr.remarkZdg);
							$("#d_agreement_linkManName").text(agr.linkManName);
							$("#d_agreement_linkManMobile").text(agr.linkManMobile);
							
							
                             //陪护服务处理
							
							if($("#d_agreementBusinessType").text()=='医院陪护'){
							   
								$("#d_customerserviceAddress").text(agr.customerserviceAddress);
								$("#d_hospitalizationNum").text(agr.hospitalizationNum);
								$("#d_departments").text(agr.departments);
								$("#d_roomNumber").text(agr.roomNumber);
								$("#d_bedNumber_a").text(agr.bedNumber_a);
								$("#d_bedNumber_b").text(agr.bedNumber_b);
								$("#d_consumerstate").text(agr.consumerstate);
								$("#d_consumersName").text(agr.consumersName);
								$("#d_consumersCard").text(agr.consumersCard);
								//服务对象与甲方关系  .....
								$("input[name='custconsumerRelation']").each(function() {
						        	if ($(this).val() == agr.custconsumerRelation) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								
								//产妇预产期
								$("#s_birthPeriod").text(agr.birthPeriod);
								//特殊注意事项
								$("input[name='specialConsiderations']").each(function() {
						        	if ($(this).val() == agr.specialConsiderations) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								//甲方选择服务项
								$("input[name='serviceItems']").each(function() {
						        	if ($(this).val() == agr.serviceItems) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								
								//服务起始日期
								$("#s_serviceStarttime").text(agr.serviceStarttime);
								//临床陪护暂定天数
								$("#d_hostsitDay").text(agr.hostsitDay);
								//服务形式
								//serviceFormat
								$("input[name='serviceFormat']").each(function() {
						        	if ($(this).val() == agr.serviceFormat) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								//分娩形式
								$("input[name='deliveryMode']").each(function() {
						        	if ($(this).val() == agr.deliveryMode) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								//更换次数
								$("#d_replaceNumber").text(agr.replaceNumber);
								//服务标准
								$("#d_serviceprojectStandard").text(agr.serviceprojectStandard);
								//开户行信息
								$("#d_partyBaccountName").text(agr.partyBaccountName);
								$("#d_partyBaccountNum").text(agr.partyBaccountNum);
								$("#d_partyBaccountBank").text(agr.partyBaccountBank);
								
								
								$("#phfw_new_div1_hr").show();
				            	$("#phfw_new_div2_hr").show();
								$("#heal_new_div1").show();
								$("#heal_new_div2").show();
								$("#heal_new_div3").show();
								$("#heal_new_div4").show();
								$("#heal_new_div5").show();
								if($("input[name='custconsumerRelation']:checked").val()==2){
									//甲方与服务对象具体亲属关系   
									$("#d_relation_relatives").text(agr.relation_relatives);
									$("#heal_new_div6").show();
								}else if($("input[name='custconsumerRelation']:checked").val()==3){
									//甲方与服务对象具体委托关系
									$("#d_relation_entrust").text(agr.relation_entrust);
									$("#heal_new_div6x").show();
								}
								$("#heal_new_div7").show();
								$("#heal_new_div8").show();
								$("#heal_new_div9").show();
								$("#heal_new_div10").show();
								$("#heal_new_div11").show();
								$("#heal_new_div12").show();
								$("#heal_new_div13").show();
								
							}
							
							
							
						});
					}
				}
			});
			
			 /** 如果是电子合同的, 查看PDF xyp add 20180716**/
			  if(agreementModel=='20520001'){
					if(normalContactfile!=null||normalContactfile!=""){
						    var basePath = "http://erp.95081.com/order/";
							var viewPDFpath = basePath+'signature/viewPDF?filePath=';
							var url = basePath+'pdf/web/viewer.html?file='+ encodeURIComponent(viewPDFpath+normalContactfile);
							window.open(url,'newWindow_'+(+new Date));
					}else{
						 $.alert({millis:2000,top:'30%',text:"电子合同PDF预览失败!"});
					}
				   

				}   
			
			
			
	});
	function initAgreementList(agreementList) {
		$("#s_agreement_type").html("");
		var optionHtml ="<option value='' selected='selected'>请选择...</option> ";
		if(agreementList !=null && typeof agreementList!='undefined'  && agreementList.length>0){
			for(var i =0 ;i<agreementList.length; i++ ){
				var obj = agreementList[i];
				optionHtml +="<option value='"+obj.dictCode+"'>"+obj.dictName+"</option> ";
			}
		}
		$("#s_agreement_type").html(optionHtml);
	}	

</script>

<title>合同详情</title>
</head>
<body>
	<div class="modal fade" id="ceshimodel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeModule('agreementDetail')">×</button>
					<h4 class="modal-title">合同详情</h4>
				</div>
					<form id="detail_agreementForm" action="" method="post" class="form-inline">
					<div class="modal-body">
						<div class="modal-content-basic">
						<div class="info-select clearfix">
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label><span style="color:red">*</span><p>合同编号：</p>
								 	<span id="d_agreementCode"></span>
								 </label>
								</div>
								
								 <div class="form-group col-xs-6">
								 <label><span style="color:red">*</span><p>合同类型：</p>
								 	<span id="d_agreementModel"></span>
								 </label>
								</div>
								<!-- <div class="form-group col-xs-6" id="detail_isCollectionDiv">
									<label><span style="color:red">*</span><p> 是否代收：</p></label>	
		                       	    <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="1" disabled="disabled"/>是
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="2" disabled="disabled" />否
		                       	    	</label>
		                       	    </div>
								</div> -->
							</div>
							
							<div class="row">
							   <div class="form-group col-xs-12">
								<label>
								<span style="color:red">*</span>
								 <p>合同业务类型：</p>
								 <span id="d_agreementBusinessType"></span>
								 </label>
								</div>
							</div>
							
							
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p> 甲方（客户）：</p>
								 	<span id="d_agreement_partyA"></span>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>联系电话：</p>
								 	<span id="d_agreement_mobile"></span>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p> 紧急联系人姓名：</p>
								 	<span id="d_agreement_linkManName"></span>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>紧急联系人电话：</p>
								 	<span id="d_agreement_linkManMobile"></span>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件类型：</p>
								 	<span id="d_agreement_cardType"></span>
								 </label>
							   </div>
							   <div>
									<div class="d_order-card-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>身份证：</p>
			                                    <span id="d_order_agree_add_id_cardNum"></span>
	                                		</label>
	                            		</div>
									</div>
									<div class="d_order-card-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>护照：</p>
			                                    <span id="d_order_agree_add_passport_cardNum"></span>
	                                		</label>
	                            		</div>
									</div>
									<div class="d_order-card-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>驾照：</p>
			                                    <span id="d_order_agree_add_license_cardNum"></span>
	                                		</label>
	                            		</div>
									</div>
									<div class="d_order-card-way-four">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>营业执照：</p>
			                                    <span id="d_order_agree_add_businessLicense_cardNum"></span>
	                                		</label>
	                            		</div>
									</div>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址：</p>
								 	<span id="d_agreement_customerAddress"></span>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>乙方（平台）： </p>
									 <span id="d_contractHeader"></span>
								 </label>
								</div>
							   <!-- <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务站： </p>
									 <span id="d_agreement_serviceGarage"></span>
								 </label>
								</div> -->
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>联系电话： </p>
									 <span id="d_mobileB"></span>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址： </p>
								  	<span id="d_agreement_platformAddress"></span>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>丙方（服务员）：</p>
								 	<span id="d_agreement_partyC"></span>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>联系电话：</p>
								 	<span id="d_agreement_mobileC"></span>
								 </label>
							   </div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件类型：</p>
								 	<span id="d_agreement_cardTypeC"></span>
								 </label>
							   </div>
							   <div>
									<div class="d_order-cardC-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>身份证：</p>
			                                    <span id="d_order_agree_add_id_cardNumC"></span>
	                                		</label>
	                            		</div>
									</div>
									<div class="d_order-cardC-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>护照：</p>
			                                    <span id="d_order_agree_add_passport_cardNumC"></span>
	                                		</label>
	                            		</div>
									</div>
									<div class="d_order-cardC-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>驾照：</p>
			                                    <span id="d_order_agree_add_license_cardNumC"></span>
	                                		</label>
	                            		</div>
									</div>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址： </p>
								 	<span id="d_agreement_waiterAddress"></span>
								 </label>
								</div>
							</div>
							 <hr>
                           	<div class="row">
							   <div class="form-group col-xs-12">
								 <label>&nbsp;&nbsp;<p>服务内容：</p>
									<span>略，详见订单明细</span> 
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务场所： </p>
									 <span id="d_agreement_serviceAddress"></span>
								 </label>
								</div>
							</div>
                             <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p> 合同期限：</p>
								    <span id="d_agreement_createTime"></span>
									&nbsp;至&nbsp;
									<span id="d_agreement_endTime"></span>
								 </label>
								</div>
							</div>  
	                        <hr>
	                        
	                        <!-- 陪护服务开始 -->
		                    <div class="row" id="heal_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-12">
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务地址：</p>
									 	<span id="d_customerserviceAddress"></span>
									 </label>
									</div>
								</div>
								<!-- <hr id="phfw_new_div0_hr" style="display: none;"> -->
								 <div class="row" id="heal_new_div2" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">住院号：</p>
									     <span id="d_hospitalizationNum"></span>
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">科室：</p>

									 	 <span id="d_departments"></span>
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">病房号：</p>
									 	
									 	 <span id="d_roomNumber"></span>
									 </label>
									</div>
									<div class="form-group col-xs-6">
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">床号：</p>
									 	 <span id="d_bedNumber_a"></span>—<span id="d_bedNumber_b"></span>
									 </label>
									</div>
								</div>
								
								<div class="row" id="heal_new_div3" style="display: none;"> 
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象现状：</p>
									 	 <span id="d_consumerstate"></span>
									 </label>
									</div>
								</div>
								
								
								 <div class="row" id="heal_new_div4" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象姓名：</p>
									 	 <span id="d_consumersName"></span>
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象身份证：</p>
									 	 <span id="d_consumersCard"></span>
									 </label>
									</div>
									
								</div>
								
								
								 <div class="row" id="heal_new_div5" style="display: none;">
								       
								     <div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象与甲方关系：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="1" disabled="disabled"/>&nbsp;甲方本人
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="2" disabled="disabled"/>&nbsp;与甲方为亲属关系
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="3" disabled="disabled"/>&nbsp;甲方非亲属关系的委托人
		                       	      </label>
									 </div>
									</div>
								 
								 
								 </div>
								
								
								 <div class="row" id="heal_new_div6" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:170px;">甲方与服务对象具体亲属关系：</p>
									 
									    <span id="d_relation_relatives"></span>
									 </label>
									</div>
								</div>
								<div class="row" id="heal_new_div6x" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:170px;">甲方与服务对象具体委托关系：</p>
									 	
									 	<span id="d_relation_entrust"></span>
									 </label>
									</div>
								</div>
								
								<div class="row" id="heal_new_div7" style="display: none;">	
								
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">产妇预产期：</p>
									 	<span id="s_birthPeriod"></span>
									 </label>
									</div>
									
								</div>
								
	                         	<div class="row" id="heal_new_div8" style="display: none;"> 
									<div class="form-group col-xs-6" >
									  <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">特殊注意事项：</p></label>
								           <div class="radio">
								
		                       	    	   <label class="radio-inline">
		                       	    		<input  type="radio" name="specialConsiderations" value="1" disabled="disabled"/>&nbsp;有
		                       	    	   </label>
		                       	    	   <label class="radio-inline">
		                       	    		<input  type="radio" name="specialConsiderations" value="2" disabled="disabled"/>&nbsp;无
		                       	    	   </label>
		                       	    	
		                       	          </div>
									</div>
								</div>
							
								<div class="row" id="heal_new_div9" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方选择服务项：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="1" disabled="disabled"/>&nbsp;产妇护理
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="2" disabled="disabled"/>&nbsp;新生儿护理
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="3" disabled="disabled"/>&nbsp;临床陪护
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="4" disabled="disabled"/>&nbsp;其他
		                       	      </label>
									 </div>
									</div>
								 
								 </div>
								
								
								  <div class="row" id="heal_new_div10" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务起始日期：</p>
									 	
									 	<span id="s_serviceStarttime"></span>
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">临床陪护暂定天数：</p>
									 	<span id="d_hostsitDay"></span>
									 </label>
									</div>
								 
								 </div>
								 
								 <div class="row" id="heal_new_div11" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务形式：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceFormat" value="1" disabled="disabled"/>&nbsp;白天
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceFormat" value="2" disabled="disabled"/>&nbsp;24小时
		                       	      </label>
									 </div>
									</div>
									
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分娩方式：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="deliveryMode" value="1" disabled="disabled"/>&nbsp;顺产
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="deliveryMode" value="2" disabled="disabled"/>&nbsp;剖宫产
		                       	      </label>
									 </div>
									</div>
									
									
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">更换次数：</p>
									 	<span id="d_replaceNumber"></span>
									 </label>
									</div>
									
							 </div>
								
		                     <!-- 陪护服务结束 -->
	                        
	                         <hr id="phfw_new_div1_hr" style="display: none;">
	                        
	                        
	                        <div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p> 服务人员服务费（实发）：</p>
								 	<span id="d_agreement_serviceMoney"></span>&nbsp;元/月&nbsp;&nbsp;&nbsp;
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span>服务人员法定假日加班费，是日劳务费：
	                        	  	<span id="d_agreement_chargeTimes"></span>&nbsp;倍
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务人员信息费：</p>
								 <span id="d_agreement_personManageMoney"></span>&nbsp;元/月
								 </label>
								</div>
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span>客户信息费（一次）：
	                        	  	<span id="d_agreement_customerManageMoney"></span>&nbsp;元
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p>本次支付合计：</p>
									 <span id="d_agreement_payment"></span>&nbsp;元&nbsp;&nbsp;&nbsp;
								 </label>
								</div>
							</div>
	                        <hr>
	                        <div class="row" id="d_oldDiv">
							   <div class="col-xs-6">
								 <label><span style="color:red">*</span><p>预付方式：</p></label>
								 <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="1" disabled="disabled"/>&nbsp;月
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="2" disabled="disabled"/>&nbsp;季
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="3" disabled="disabled"/>&nbsp;半年
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="4" disabled="disabled"/>&nbsp;年
		                       	    	</label>
		                       	  </div>
								</div>
							</div>
							<div class="row" id="d_newDiv">
							   <div class="col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付方式：</p></label>
								 <div class="radio">
								 <!-- 1 一次性预付劳务费 ，2 白条支付，3银行白条支付，4其他支付方式 -->
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="1" disabled="disabled"/>&nbsp;一次性预付劳务费
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<!--<input  type="radio" name="advancePeriod2" value="2"/>&nbsp;白条支付  -->
		                       	    		<input  type="radio" name="advancePeriod2" value="2" disabled="disabled"/>&nbsp;唯品会白条
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<!--<input  type="radio" name="advancePeriod2" value="3"/>&nbsp;银行白条支付 -->
		                       	    		<input  type="radio" name="advancePeriod2" value="3" disabled="disabled"/>&nbsp;招行分期
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="4" disabled="disabled"/>&nbsp;其他支付方式
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="5" disabled="disabled"/>&nbsp;海金保理白条
		                       	    	</label>
		                       	  </div>
								</div>
								
							</div>
							<div class="row" id="d_oldDateDiv">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>甲方预支付时间：</p>
									 <span id="d_agreement_agreementPayDate"></span>
								 </label>
								</div>
								<div class="form-group col-xs-6" id="dpayDateDiv">
									<label class="has-feedback"><span style="color:red">*</span><p>预支付日期：</p>
										 每月&nbsp;<span id="d_agreement_remindDay"></span>&nbsp;日前支付
									 </label>
								</div>
								<div class="form-group col-xs-6" id="dzhifuRemarkDiv">
									<label class="has-feedback"><span style="color:red">*</span><p>支付时间说明：</p>
										<span id="d_agreement_zhifuRemark"></span>
									 </label>
								</div>
							</div>
							
							<hr>
							<!-- 服务协议必填项-->
							 <div class="row" id="heal_new_div12" style="display: none;">
								  
								   <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 220px;">甲方选择服务项目收费标准(/天)：</p>
									 	
									 	<span id="d_serviceprojectStandard"></span>
									 </label>
									</div>
									
									
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 200px;">乙方指定收款账户开户名称：</p>
									 	<span id="d_partyBaccountName"></span>
									 </label>
									</div>
								
								   
								 </div>
								 
								 <div class="row" id="heal_new_div13" style="display: none;">
								  
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:200px;">乙方指定收款账户银行账号：</p>

									 	<span id="d_partyBaccountNum"></span>
									 </label>
									</div>
								          
								    <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:200px;">乙方指定收款账户开户银行：</p>
									 	<span id="d_partyBaccountBank"></span>
									 </label>
									</div>
								   
								 </div>
							<!-- 服务协议必填项 -->
							
							<hr>
							
							
							<div class="row" id="d_newDateDiv"> 
							   <div class="form-group col-xs-6" >
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付截止日期：</p>
								 	<span type="text" id="d_agreement_agreementPayDate_new" ></span>
								 </label>
								</div>
								<div class="form-group col-xs-6" id="d_allPayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>费用总计：</p>
										 <span   id="d_agreement_allPay" ></span>&nbsp;元
									 </label>
								</div>
								<div class="form-group col-xs-6" id="d_otherWayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>其他支付方式：</p>
										 <span  id="d_agreement_otherWay"></span>
									 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p> 其他服务：</p>
								 	<span id="d_agreement_otherMethods"></span>
								 </label>
								</div>
							</div>
							<div class="row" id="d_remarkZdgDiv">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p> 钟点工工作时间：</p>
								 	<span id="d_agreement_remarkZdg"></span>
								 </label>
								</div>
							</div>
	                        <hr>
	                        <!-- 海金保理白条开始 -->
		                        <div class="row" id="hjbl_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付几个月劳务费(月)：</p>
									 	<span   id="d_prepaidMonths"  name="prepaidMonths">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付劳务费总金额(元)：</p>
									 	<span   id="d_prepaidMoney"  name="prepaidMoney">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付几个月劳务费(月)：</p>
									 	<span   id="d_instPrepaidMonths"  name="instPrepaidMonths">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付劳务费总金额(元)：</p>
									 	<span   id="d_instPrepaidMoney"  name="instPrepaidMoney">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">几日内支付分期手续费(日)：</p>
									 	<span   id="d_limitDays"  name="limitDays">
									 </label>
									</div>
								</div>
								<hr id="hjbl_new_div1_hr" style="display: none;">
								<div class="row" id="hjbl_new_div2" style="display: none;"> 
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户姓名：</p>
									 	<span   id="d_accountName"  name="accountName">
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户开户行：</p>
									 	<span   id="d_accountBank"  name="accountBank">
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户号码：</p>
									 	<span   id="d_accountNum"  name="accountNum">
									 </label>
									</div>
								</div>
	                         	<hr id="hjbl_new_div2_hr" style="display: none;">
		                        <!-- 海金保理白条结束 -->
	                        <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback form-label">&nbsp;&nbsp;<p> 其他约定：</p>
								 <textarea rows="3"  id="d_agreement_otherMatters" class= "form-control form-textarea" style="height: 80px"  readonly="readonly"></textarea>
								 </label>
								</div>
							</div>
					  </div>
                     </div> 
                   </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" onclick="closeModule('agreementDetail')">关闭</button>
				</div>
			</form>
			</div>
		</div>

	</div>

</body>
</html>