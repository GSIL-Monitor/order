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
	var orderId = '${param.orderId}';
	var contractId = '${param.contractId}';
	var orderType = '${param.orderType}';
	var orgType = '${param.orgType}';
	var checkstatus = '${param.checkstatus}';
	/**查询城市
	 *@param id
	 *@param levels
	 *@param code 
	 * */
	function queryCitys(baseCity,tagId){
		var html = "<option value='' >--请选择--</option>";
		var settings = {"levels":1}
		jQuery.extend(settings, baseCity);
		if(settings.levels == 1 || (settings.levels != 1 && settings.code)){
			$.ajax({
				url : ctx+"/agreement/queryCitys",
				data : settings,
				type : "POST",
				async : false,
				success : function(data) {
					if(data.msg == "00"){
						$.each(data.list,function(i,v){
							html += "<option value='"+v.code+"' data-name='"+v.name+"'>"+v.name+"</option>";
						});
					}
				}
			});
		}
		$("#"+tagId).empty().html(html).trigger("change");
	} 
	/**甲方服务场所-回显字段-给表单赋值**/
	function setUpdateServiceAddressEcho(serviceAddressEcho){
		if(serviceAddressEcho && serviceAddressEcho.indexOf("{") != -1 && serviceAddressEcho.indexOf("}") != -1){
			var sae = JSON.parse(serviceAddressEcho);
			$("#province_a").children("option[data-name='"+(sae.province_a||"")+"']").prop("selected",true).trigger("change"); //省份
			$("#city_a").children("option[data-name='"+(sae.city_a||"")+"']").prop("selected",true).trigger("change");//城市
			$("#county_a").children("option[data-name='"+(sae.county_a||"")+"']").prop("selected",true);//省份
			$("#street_a").val(sae.street_a||""); //街道
			$("#residence_community_a").val(sae.residence_community_a||""); //小区
			$("#building_No_a").val(sae.building_No_a||""); //号楼
			$("#unit_a").val(sae.unit_a||""); //单元
			$("#room_a").val(sae.room_a||""); //室
		}
	}
	/**甲方地址-回显字段-给表单赋值**/
	function setUpdateCustomerAddressEcho(customerAddressEcho){
		if(customerAddressEcho && customerAddressEcho.indexOf("{") != -1 && customerAddressEcho.indexOf("}") != -1){
			var sae = JSON.parse(customerAddressEcho);
			$("#province_ca").children("option[data-name='"+(sae.province_ca||"")+"']").prop("selected",true).trigger("change");//省份
			$("#city_ca").children("option[data-name='"+(sae.city_ca||"")+"']").prop("selected",true).trigger("change"); //城市
			$("#county_ca").children("option[data-name='"+(sae.county_ca||"")+"']").prop("selected",true); //县区
			$("#street_ca").val(sae.street_ca||""); //街道
			$("#residence_community_ca").val(sae.residence_community_ca||""); //小区
			$("#building_No_ca").val(sae.building_No_ca||""); //号楼
			$("#unit_ca").val(sae.unit_ca||""); //单元
			$("#room_ca").val(sae.room_ca||""); //室
		}
	}
	
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
	
	$(function(){
		initAgreementList(getAgreementModels());
		$("input[name='advancePeriod2']").change(function(){
			var val = $(this).val();
			if(val != null && val == 4){
				$("#u_otherWayDiv").show();
				$("#u_allPayDiv").hide();
			}else{
				$("#u_otherWayDiv").hide();
				$("#u_allPayDiv").show();
			}
			//海金保理白条
			if(val == 5){
				$("#hjbl_new_div1_hr").show();//分隔线
				$("#hjbl_new_div2_hr").show();//分隔线
				$("#hjbl_new_div1").show();
				$("#hjbl_new_div2").show();
				var bv = $("#update_agreementForm").data("bootstrapValidator");
				if(bv){
					$("#hjbl_new_div1,#hjbl_new_div2").find("input").each(function(i,v){
						var inputName = v.name;
						bv.updateStatus(inputName, "NOT_VALIDATED");
					});
				}
			}else{
				$("#hjbl_new_div1_hr").hide();//分隔线
				$("#hjbl_new_div2_hr").hide();//分隔线
				$("#hjbl_new_div1").hide();
				$("#hjbl_new_div2").hide();
				$("#hjbl_new_div1").find("input").val("");
				$("#hjbl_new_div2").find("input").val("");
			}
		});
		$("input[name='isCollection']").change(function(){
			var val = $(this).val();
			if(val != null && val == 7){
				$("#u_oldDiv").hide();
				$("#u_oldDateDiv").hide();
				
				$("#u_newDiv").show();
				$("#u_newDateDiv").show();
				//$("#u_otherWayDiv").hide();
			}else{
				$("#u_oldDiv").show();
				$("#u_oldDateDiv").show();
				
				$("#u_newDiv").hide();
				$("#u_newDateDiv").hide();
				//$("#u_otherWayDiv").hide();
				$("input[name='advancePeriod2']").prop("checked",false);
				$("#hjbl_new_div1_hr").hide();//分隔线
				$("#hjbl_new_div2_hr").hide();//分隔线
				$("#hjbl_new_div1").hide();
				$("#hjbl_new_div2").hide();
				$("#hjbl_new_div1").find("input").val("");
				$("#hjbl_new_div2").find("input").val("");
			}
		});
			//钟点工备注展示
			if(orderType == "100200040002" ){//钟点工
				$("#u_remarkZdgDiv").show();
			}else{
				$("#u_remarkZdgDiv").hide();
			}
			//加盟商不显示代收非代收 
			/* if(orderType != "" && orderType == 3){//加盟商
				$("#u_isCollectionDiv").show();
			}else{
				$("#u_isCollectionDiv").hide();
			} */
			//加载甲方地址
			queryCitys(null,"province_ca");
			//加载甲方服务场所
			queryCitys(null,"province_a");
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
							
                           var agrbusstype_select=$("#s_agreementBusinessType > option");
							
							for(var i=0;i<agrbusstype_select.length;i++){
	                            var svalue=agrbusstype_select[i].value;
	                            if(agr.agreementBusinessType==svalue){ 
	                             $("#s_agreementBusinessType option[value='"+svalue+"']").attr("selected","selected");
	                            }                        
	                        }
							
							//加载甲方服务场所、地址
							setUpdateServiceAddressEcho(agr.serviceAddressEcho);
							setUpdateCustomerAddressEcho(agr.customerAddressEcho);
							$("#u_agreementCode").val(agr.agreementCode);
						
							$("#s_agreement_type").val(agr.agreementModel);
							$("input[name='isCollection']").each(function() {
					        	if (agr.isCollection != null && $(this).val() == agr.isCollection) {
						        	$(this).attr("checked", "checked");
						        	}
						    	});
							if(agr.isCollection != null && agr.isCollection == 6){
				        		$("#u_oldDiv").show();
				    			$("#u_oldDateDiv").show();
				    			
				    			$("#u_newDiv").hide();
				    			$("#u_newDateDiv").hide();
				    			$("#u_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod1']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked");
							        	}
							    	});
								$("#u_agreement_agreementPayDate").val(agr.agreementPayDate);
								if(agr.advancePeriod == 1){
									$("#u_agreement_remindDay").val(agr.remindDay);
									$("#uzhifuRemarkDiv").hide();
								}else{
									$("#u_agreement_zhifuRemark").val(agr.zhifuRemark);
									$("#upayDateDiv").hide();
								}
				        	}else{
				        		$("#u_oldDiv").hide();
				    			$("#u_oldDateDiv").hide();
				    			
				    			$("#u_newDiv").show();
				    			$("#u_newDateDiv").show();
				    			$("#u_otherWayDiv").hide();
				        		
				        		$("input[name='advancePeriod2']").each(function() {
						        	if ($(this).val() == agr.advancePeriod) {
							        	$(this).attr("checked", "checked").trigger("change");
							        	}
							    	});
								$("#u_agreement_agreementPayDate_new").val(agr.agreementPayDate);
								if(agr.advancePeriod == 4){
									$("#u_agreement_otherWay").val(agr.otherWay);
									$("#u_otherWayDiv").show();
									$("#u_allPayDiv").hide();
								}else{
									$("#u_agreement_allPay").val(agr.allPay);
									$("#u_otherWayDiv").hide();
									$("#u_allPayDiv").show();
								}
								if(agr.advancePeriod == 5){
									$("#u_prepaidMonths").val(agr.prepaidMonths);
									$("#u_prepaidMoney").val(agr.prepaidMoney);
									$("#u_instPrepaidMonths").val(agr.instPrepaidMonths||"");
									$("#u_instPrepaidMoney").val(agr.instPrepaidMoney||"");
									$("#u_limitDays").val(agr.limitDays);
									$("#u_accountName").val(agr.accountName||"");
									$("#u_accountBank").val(agr.accountBank||"");
									$("#u_accountNum").val(agr.accountNum||"");
									$("#u_accountMobile").val(agr.accountMobile||"");
									$("#u_authCode_flag").val("1");
									$("#u_agreement_partyA").attr("readonly",true);
									$("#uorder_agree_add_id_cardNum").attr("readonly",true);
									$("#u_agreement_cardType").attr("disabled",true);
									$("#u_accountName").attr("readonly",true);
									$("#u_accountBank").attr("disabled",true);
									$("#u_accountNum").attr("readonly",true);
									$("#u_accountMobile").attr("readonly",true);
									$("#submitAuth").attr("disabled",true);
								}
				        	}
							$("#u_agreement_partyA").val(agr.partyA);
							$("#checkstatus").val(checkstatus);
							$("#u_agreement_cardType").val(agr.cardType);
							if(agr.cardType == 1){
								$("#uorder_agree_add_id_cardNum").val(agr.cardNum);
							}else if(agr.cardType == 2){
								$("#uorder_agree_add_passport_cardNum").val(agr.cardNum); 
							}else if(agr.cardType == 3){
								$("#uorder_agree_add_license_cardNum").val(agr.cardNum);
							}else if(agr.cardType == 4){
								$("#uorder_agree_add_businessLicense_cardNum").val(agr.cardNum);
							}
							//$("#u_agreement_ID").val(agr.cardNum);
							$("#u_agreement_mobile").val(agr.mobile);
							$("#u_agreement_customerAddress").val(agr.customerAddress);
							//$("#u_agreement_partyB").val(agr.partyB);
							$("#u_contractHeader").text(agr.partyB);
							//乙方联系电话
							$("#u_mobileB").val(agr.mobileB);
							//$("#u_agreement_serviceGarage").val(agr.serviceGarage).prop("title",agr.serviceGarage);
							$("#u_agreement_platformAddress").val(agr.platformAddress);
							$("#u_agreement_partyC").val(agr.partyC);
							$("#u_agreement_partyCId").val(agr.personId);
							$("#u_agreement_mobileC").val(agr.mobileC);
							$("#u_agreement_cardTypeC").val(agr.cardTypeC);
							if(agr.cardTypeC == 1){
								$("#uorder_agree_add_id_cardNumC").val(agr.cardNumC);
							}else if(agr.cardTypeC == 2){
								$("#uorder_agree_add_passport_cardNumC").val(agr.cardNumC); 
							}else if(agr.cardTypeC == 3){
								$("#uorder_agree_add_license_cardNumC").val(agr.cardNumC);
							}
							//$("#u_agreement_IDC").val(agr.cardNumC);
							$("#u_agreement_waiterAddress").val(agr.waiterAddress);
							$("#u_agreement_serviceAddress").val(agr.serviceAddress);
							$("#u_agreement_createTime").val(agr.effectDate);
							$("#u_agreement_endTime").val(agr.finishDate);
							$("#u_agreement_serviceMoney").val(agr.serviceMoney);
							$("#u_agreement_chargeTimes").val(agr.chargeTimes);
							$("#u_agreement_personManageMoney").val(agr.personManageMoney);
							$("#u_agreement_customerManageMoney").val(agr.customerManageMoney);
							$("#u_agreement_payment").val(agr.payment);
							
							$("#u_agreement_otherMethods").val(agr.otherMethods);
							$("#u_agreement_otherMatters").val(agr.otherMatters);
							$("#u_agreement_remarkZdg").val(agr.remarkZdg);
							$("#u_agreement_linkManName").val(agr.linkManName);
							$("#u_agreement_linkManMobile").val(agr.linkManMobile);
							$("#u_agreement_contractDate").val(agr.contractDate);
							//$("#serviceGarageId").data("serviceGarageId",agr.serviceGarageId);
							
							var agreementBusinessTypeVal =$("#s_agreementBusinessType").val();
							if(typeof agreementBusinessTypeVal !='undefined' && agreementBusinessTypeVal !='' && agreementBusinessTypeVal !=null && agreementBusinessTypeVal == '2'){
								
								
								$("input[name='isCollection']").each(function() {
						        	if ($(this).val() == agr.isCollection) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								
								//支付方式只能选 [平台代收] – [一次性预付服务费]
								$("input:radio[name='isCollection']:not([checked])").attr("disabled","disabled");
								$("input:radio[name='advancePeriod2']").eq(0).attr("checked",true);
								$("input:radio[name='advancePeriod2']:not([checked])").attr("disabled","disabled");
								
								$("#s_customerserviceAddress").val(agr.customerserviceAddress);
								$("#s_hospitalizationNum").val(agr.hospitalizationNum);
								$("#s_departments").val(agr.departments);
								$("#s_roomNumber").val(agr.roomNumber);
								$("#s_bedNumber_a").val(agr.bedNumber_a);
								$("#s_bedNumber_b").val(agr.bedNumber_b);
								$("#s_consumerstate").val(agr.consumerstate);
								$("#s_consumersName").val(agr.consumersName);
								$("#s_consumersCard").val(agr.consumersCard);
								//服务对象与甲方关系  .....
								$("input[name='custconsumerRelation']").each(function() {
						        	if ($(this).val() == agr.custconsumerRelation) {
							        	$(this).attr("checked", "checked");
							        	}
							    });
								var custconsumerrlVal = $("input[name='custconsumerRelation']:checked").val();
								if(custconsumerrlVal != null && custconsumerrlVal == 2){
									$("#heal_new_div6").show();
									$("#heal_new_div6x").hide();
									$("#s_relation_relatives").val(agr.relation_relatives);
								}else if(custconsumerrlVal != null && custconsumerrlVal ==3){
									$("#heal_new_div6x").show();
									$("#heal_new_div6").hide();
									$("#s_relation_entrust").val(agr.relation_entrust);
								}else{
									$("#heal_new_div6").hide();
									$("#heal_new_div6x").hide();
								}
								$("#s_birthPeriod").val(agr.birthPeriod);
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
								$("#s_serviceStarttime").val(agr.serviceStarttime);
								//临床陪护暂定天数
								$("#s_hostsitDay").val(agr.hostsitDay);
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
								$("#s_replaceNumber").val(agr.replaceNumber);
								//服务标准
								$("#s_serviceprojectStandard").val(agr.serviceprojectStandard);
								//开户行信息
								$("#s_partyBaccountName").val(agr.partyBaccountName);
								$("#s_partyBaccountNum").val(agr.partyBaccountNum);
								$("#s_partyBaccountBank").val(agr.partyBaccountBank);
								
								$("#phfw_new_div1_hr").show();
				            	$("#phfw_new_div2_hr").show();
								$("#heal_new_div1").show();
								$("#heal_new_div2").show();
								$("#heal_new_div3").show();
								$("#heal_new_div4").show();
								$("#heal_new_div5").show();
								$("#heal_new_div7").show();
								$("#heal_new_div8").show();
								$("#heal_new_div9").show();
								$("#heal_new_div10").show();
								$("#heal_new_div11").show();
								$("#heal_new_div12").show();
								$("#heal_new_div13").show();
								$("#phfw_msg_div1").show();
								
								
								
								
							}
							
							
						});
						
					}
				}
			});
			
	});
	function haijinChange(thiz){
		var serviceMoney=$("#u_agreement_serviceMoney").val()||0; //服务人员服务费（实发）
		var prepaidMonths =$("#u_prepaidMonths").val()||0; //预付几个月劳务费(月)
		var instPrepaidMonths=$("#u_instPrepaidMonths").val()||0; //分期支付几个月劳务费(月)
		$("#u_prepaidMoney").val(prepaidMonths*serviceMoney);
		var allMonths=parseInt(prepaidMonths)+parseInt(instPrepaidMonths);
		if(allMonths<13){
			$("#u_instPrepaidMoney").val(serviceMoney*instPrepaidMonths);
		}else{
			$(thiz).val("");
			$.alert({millis:5000,text:"[预付几个月劳务费(月)] + [分期支付几个月劳务费(月)]不得大于12个月, 如需录入一年以上的合同请联系结算中心!"});
		}
		var bv = $("#update_agreementForm").data("bootstrapValidator");
		bv.updateStatus("prepaidMoney", "NOT_VALIDATED").validateField("prepaidMoney");
		bv.updateStatus("instPrepaidMoney", "NOT_VALIDATED").validateField("instPrepaidMoney");
	}
	//预付方式联动
	$("input[name='advancePeriod1']").change(function(){
		if($(this).val() == 1){
			$("#upayDateDiv").show();
			$("#uzhifuRemarkDiv").hide();
			//计算本次支付合计
			var serviceMoney = parseFloat($("#u_agreement_serviceMoney").val());//服务人员服务费(实发)
			var customerManageMoney = parseFloat($("#u_agreement_customerManageMoney").val());//客户信息费
			var personManageMoney = parseFloat($("#u_agreement_personManageMoney").val());//服务人员信息费
			var total = parseFloat(serviceMoney + customerManageMoney + personManageMoney).toFixed(2);
			//若为整数，处理.00情况
			var money = "0";
			var s = total.charAt(total.length-1);
			if(s == "0"){
				money = total.split(".")[0];
			}else{
				money = total;
			}
			//赋值
			if(isNaN(total)){
				$("#u_agreement_payment").val(0);//赋值
			}else{
				$("#u_agreement_payment").val(money).trigger("keyup");//赋值
			}
		}else{
			$("#upayDateDiv").hide();
			$("#uzhifuRemarkDiv").show();
		}
	});
	
	//服务对象与甲方关系
	$("input[name='custconsumerRelation']").change(function(){
		var val = $(this).val();
		if(val != null && val == 2){
			$("#heal_new_div6").show();
			$("#heal_new_div6x").hide();
		}else if(val != null && val ==3){
			$("#heal_new_div6x").show();
			$("#heal_new_div6").hide();
		}else{
			$("#heal_new_div6").hide();

			$("#heal_new_div6x").hide();
		}
	});
	
	
	$('#update_agreementForm').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled,:hidden',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
	    	 partyA: {
	            message: '名称无效！',
	            validators: {
	                notEmpty: {
	                    message: '名称不能为空！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4e00-\u9fa5a-zA-Z0-9()（）]+$/,
	                    message: '名称可为中文、英文或数字！'
	                },
	                callback: {
	                      message: '【甲方(客户)账户姓名】 字段须与 【甲方（客户）】 字段一致!',
	                      callback: function(value, validator) {
	                    	  var selHjbl = $("input[name=advancePeriod2][value=5]").prop("checked");
	                    	  if(selHjbl){
	                    	 	  $("#u_accountName").val(value);
	                    	 	  validator.updateStatus("accountName", "NOT_VALIDATED").validateField("accountName");
	                    	 	  return value == $("#u_accountName").val();
	                    	  }else{
	                    		  $("#u_accountName").val("");
	                    		  validator.updateStatus("accountName", "NOT_VALIDATED");
	                    	  }
	                    	  return true;
	                    	}
	                }
	            }
	        },
	        contractDate: {
	            validators: {
	                notEmpty: {
	                    message: '必填'
	                }
	            }
	        }, 
	         cardType: {
	            validators: {
	                notEmpty: {
	                    message: '必填！'
	                }
	            }
	        }, 
	        
	        agreementModel: {
	            validators:{ 
	                notEmpty: {
	                    message: '必填！'
	                }
	            }
	        }, 
	        
	        mobile: {
               validators: {
               	notEmpty: {
	                    message: '必填！'
	                },
                   regexp: {
                	   regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/,
                       message: '请输入正确的电话号码。'
                   }
               }
           },
			mobileB: {
				validators: {
					notEmpty: {
						message: '必填！'
					},
					regexp: {
						regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/,
						message: '请输入正确的电话号码。'
					}
				}
			},
			allPay:{
				validators: {
					notEmpty: {
						message: '必填！'
					},
					regexp: {
						regexp: /^(([1-9]\d*(\.\d{0,1}[1-9])?)|(0\.\d{0,1}[1-9]))?$/  ,
						message: '请填写不为0的整数或2位小数！'
					},
				}
			},
			payment:{
				validators: {
					notEmpty: {
						message: '必填！'
					},
					regexp: {
						regexp: /^(([1-9]\d*(\.\d{0,1}[1-9])?)|(0\.\d{0,1}[1-9]))?$/  ,
						message: '请填写不为0的整数或2位小数！'
					},
//	        		regexp: {
//	        			regexp: /^\d+(\.\d{1,2})?$/ ,
//	        			message: '请填写正确费用！'
//	        		},
				}
			},
           advancePeriod2: {
               validators: {
              	 notEmpty: {
	                    message: '必填！'
	                }
              }
          },
          advancePeriod1: {
              validators: {
             	 notEmpty: {
	                    message: '必填！'
	                }
             }
         },
            /* cardNum: {
	            validators: {
	                notEmpty: {
	                    message: '证件号不能为空！'
	                },
	                stringLength: {
	                    min: 15,
	                    max: 18,
	                    message: '证件号为15-18位'
	                },
	                 regexp: {
                       regexp: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/,
                       message: '请输入正确的证件号！'
                   }
	            }
	        },  */
	        customerAddress: {
	        	validators: {
	        		notEmpty: {
	        			message: '地址不能为空！'
	        		},
	        		stringLength: {
	                    min: 1,
	                    max: 50,
	                    message: '地址不超过50字！'
	                },
	        	}
	        },
	    	/* partyB: {
	            message: '名称无效！',
	            validators: {
	                notEmpty: {
	                    message: '名称不能为空！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$/,
	                    message: '名称可为中文或英文字母！'
	                }
	            }
	        }, */
	        serviceGarage: {
	            validators: {
	                notEmpty: {
	                    message: '服务站不能为空！'
	                }
	            }
	        },
	        platformAddress: {
	        	validators: {
	        		notEmpty: {
	        			message: '地址不能为空！'
	        		},
	        		stringLength: {
	                    min: 1,
	                    max: 50,
	                    message: '地址不超过50字！'
	                },
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
	        	}
	        },
	    	partyC: {
	            message: '名称无效！',
	            validators: {
	                notEmpty: {
	                    message: '名称不能为空！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4e00-\u9fa5a-zA-Z0-9]+$/,
	                    message: '名称可为中文、英文或数字！'
	                }
	            }
	        },
	         cardType_C: {
	            validators: {
	                notEmpty: {
	                    message: '必填！'
	                },
	            }
	        }, 
	        mobileC: {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                },
                   regexp: {
                	   regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/,
                       message: '请输入正确的电话号码。'
                   }
               }
           },
            /* cardNumC: {
	            validators: {
	                notEmpty: {
	                    message: '证件号不能为空！'
	                },
	                stringLength: {
	                    min: 15,
	                    max: 18,
	                    message: '证件号为15-18位'
	                },
	                 regexp: {
                       regexp: /(^\d{15}$)|(^\d{17}([0-9]|X)$)/,
                       message: '请输入正确的证件号！'
                   }
	            }
	        },  */
	        cardone : {
            	validators: {
            		notEmpty: {
            			message: '不能为空！'
            		},
            		regexp: {
 	        			regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/ ,
 	        			message: '请输入正确的身份证号码！'
 	        		},
            	}
            },
            cardtwo : {
            	validators: {
            		notEmpty: {
            			message: '不能为空！'
            		},
            		regexp: {
 	        			regexp: /^[^\u4e00-\u9fa5]{0,}$/ ,
 	        			message: '证件号码不能为中文！'
 	        		},
 	        		stringLength: {
		                min: 0,
		                max: 20,
		                message: '证件号码不能超过20位！'
		            }
            	}
            },
            cardthree : {
            	validators: {
            		notEmpty: {
            			message: '不能为空！'
            		},
            		regexp: {
 	        			regexp: /^[^\u4e00-\u9fa5]{0,}$/ ,
 	        			message: '证件号码不能为中文！'
 	        		},
 	        		stringLength: {
		                min: 0,
		                max: 20,
		                message: '证件号码不能超过20位！'
		            }
            	}
            },
            cardfour : {
            	validators: {
            		notEmpty: {
            			message: '不能为空！'
            		},
            	}
            },
	        waiterAddress: {
	        	validators: {
	        		notEmpty: {
	        			message: '地址不能为空！'
	        		},
	        		stringLength: {
	                    min: 1,
	                    max: 50,
	                    message: '地址不超过50字！'
	                },
	        	}
	        },
	        serviceAddress: {
	        	validators: {
	        		notEmpty: {
	        			message: '地址不能为空！'
	        		},
	        		stringLength: {
	                    min: 1,
	                    max: 50,
	                    message: '地址不超过50字！'
	                },
	        	}
	        },

           serviceMoney:{
	        	validators: {
	        		notEmpty: {
	        			message: '必填！'
	        		},
	        		regexp: {
	        			regexp: /^\d{3,5}?$/  ,
	        			message: '请填写3~5位正整数！'
	        		},
	        	}
	        },
	        chargeTimes:{
	        	validators: {
	        		notEmpty: {
	        			message: '必填！'
	        		},
	        		stringLength: {
	                    min: 1,
	                    max: 12,
	                    message: '请填写正确倍数！'
	                },
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/,
	        			message: '请填写正确倍数！'
	        		},
	        	}
	        },
	        personManageMoney:{
	        	validators: {
	        		notEmpty: {
	        			message: '必填！'
	        		},
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/  ,
	        			message: '请填写正确费用！'
	        		},
	        	}
	        },
	        customerManageMoney:{
	        	validators: {
	        		notEmpty: {
	        			message: '必填！'
	        		},
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/  ,
	        			message: '请填写正确费用！'
	        		},
	        	}
	        },
	        otherMethods: {
	        	validators: {
	        		stringLength: {
	                    min: 0,
	                    max: 30,
	                    message: '其他服务不超过30字！'
	                },
	        	}
	        },
	        otherMatters: {
	        	validators: {
	        		stringLength: {
	                    min: 0,
	                    max: 150,
	                    message: '其他约定不超过150字！'
	                },
	        	}
	        },
	        remarkZdg: {
	        	validators: {
	        		stringLength: {
	                    min: 0,
	                    max: 20,
	                    message: '钟点工工作时间不超过20字！'
	                },
	        	}
	        },
	        zhifuRemark: {
	        	validators: {
	        		stringLength: {
	                    min: 0,
	                    max: 20,
	                    message: '支付时间说明不超过20字！'
	                },
	        	}
	        },
	        remindDay : {
               validators: {
                   stringLength: {
	                    min: 1,
	                    max: 2,
	                    message: '请填写正确日期！'
	                },
                   regexp: {
	        			regexp: /^[1-9]\d*$/ ,
	        			message: '只能为数字！'
	        		},
               }
           }, 
           linkManName: {
	            message: '名称无效！',
	            validators: {
	                notEmpty: {
	                    message: '名称不能为空！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4e00-\u9fa5a-zA-Z0-9]+$/,
	                    message: '名称可为中文、英文或数字！'
	                },
	                different: {//不能和甲方客户名称相同
                       field: 'partyA',//需要进行比较的input name值
                       message: '不能和甲方客户名称相同'
                   },
	            }
	        },
	        linkManMobile : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                },
                   regexp: {
                	   regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/,
                       message: '请输入正确的电话号码。'
                   },
                   different: {//不能和甲方客户电话相同
                       field: 'mobile',//需要进行比较的input name值
                       message: '不能和甲方客户电话相同'
                   },
               }
           },
           cardfive : {
           	validators: {
           		notEmpty: {
           			message: '不能为空！'
           		},
           		regexp: {
	        			regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/ ,
	        			message: '请输入正确的身份证号码！'
	        		},
           	}
           },
           province_a : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           city_a : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           county_a : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           street_a : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                },
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           province_ca : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           city_ca : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           county_ca : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                }
               }
           },
           street_ca : {
               validators: {
               	 notEmpty: {
	                    message: '必填！'
	                },
 	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           residence_community_ca : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           building_No_ca : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           unit_ca : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           room_ca : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           residence_community_a : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           building_No_a : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           unit_a : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           room_a : {
               validators: {
	                regexp: {
	        			regexp: /^[^{}<>#$&;]*$/ ,
	        			message: '不能包含{}<>#$&;字符'
	        		}
               }
           },
           prepaidMonths : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	                regexp: {
	        			regexp: /^[0-9]\d*$/ ,
	        			message: '只能为整数数字'
	        		}
               }
           },
           prepaidMoney : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/  ,
	        			message: '请填写正确费用'
	        		}
               }
           },
           instPrepaidMonths : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	                regexp: {
	        			regexp: /^[0-9]\d*$/ ,
	        			message: '只能为整数数字'
	        		}
               }
           },
           instPrepaidMoney : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/  ,
	        			message: '请填写正确费用'
	        		}
               }
           },
           limitDays : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	                regexp: {
	        			regexp: /^[0-9]\d*$/ ,
	        			message: '只能为整数数字'
	        		}
               }
           },
           accountName : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
           	  identical: {//与指定控件内容比较是否相同，比如两次密码不一致
            	field: 'partyA',//指定控件name
               message: '[甲方(客户)账户姓名] 字段须与 [甲方（客户）] 字段一致!'

             }
               }
           },
           accountBank : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                }
               }
           },
           accountNum : {
               validators: {
               	 notEmpty: {
	                    message: '必填'
	                },
	                regexp: {
	        			regexp: /^[0-9]\d*$/ ,
	        			message: '只能为整数数字'
	        		}
               }
           }
	    }
	}).on('success.form.bv', function(e) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    
	    $("#s_agreementBusinessType").attr("disabled",false);
	    //保存
	    agreement.update(orderId,orderType);
	}); 
	inputreplacethreeAgree(".uorder-card-one",".uorder-card-way-one",".uorder-card-way-two",'cardone','cardtwo',".uorder-card-way-three",'cardthree',".uorder-card-way-four",'cardfour');
	inputreplacethreeAgree(".uorder-cardC-one",".uorder-cardC-way-one",".uorder-cardC-way-two",'cardfive','cardtwo',".uorder-cardC-way-three",'cardthree',".uorder-cardC-way-four",'cardfour');
/* 	 $("#u_agreement_cardType").change(function(){
			if($(this).find("option:selected").val() == 1 || $(this).find("option:selected").val() == 3){
				$("#uIDCardValid").show();
				$("#upassportValid").hide();
			}else{
				$("#uIDCardValid").hide();
				$("#upassportValid").show();
			}
		});
		$("#u_agreement_cardTypeC").change(function(){
			if($(this).find("option:selected").val() == 1 || $(this).find("option:selected").val() == 3){
				$("#uIDCardValidC").show();
				$("#upassportValidC").hide();
			}else{
				$("#uIDCardValidC").hide();
				$("#upassportValidC").show();
			}
		});
	//验证身份证号
	function uIDvalid(eleId){
		setTimeout(function(){
		var reg = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
		var idCardNum = $("#"+eleId).val();
		if(!reg.test(idCardNum)){
				$.alert({millis:2000,top:'30%',text:"请填写正确证件号！"});
		}
		},2000);
	}
	//验证护照号
	function upassportvalid(eleId){
		setTimeout(function(){
		var reg = /^[a-zA-Z0-9]{3,21}$/;
		var passportNum = $("#"+eleId).val();
		if(!reg.test(passportNum)){
				$.alert({millis:2000,top:'30%',text:"请填写正确证件号！"});
		}
		},2000);
	}
		$("#u_agreement_ID").on("keyup",function(){
			uIDvalid("u_agreement_ID");
		})
		$("#u_agreement_IDC").on("keyup",function(){
			uIDvalid("u_agreement_IDC");
		})
		$("#u_agreement_passport").on("keyup",function(){
			upassportvalid("u_agreement_passport");
		})
		$("#u_agreement_passportC").on("keyup",function(){
			upassportvalid("u_agreement_passportC");
		}) */

	//预付方式为月时，修改费用同步本次支付合计
	function countPayAmount(){
		//服务人员服务费
		var serviceMoney = parseFloat($("#u_agreement_serviceMoney").val());
		//服务人员信息费
		var personManageMoney = parseFloat($("#u_agreement_personManageMoney").val());
		//客户信息费
		var customerManageMoney = parseFloat($("#u_agreement_customerManageMoney").val());
		var total = parseFloat(serviceMoney+personManageMoney+customerManageMoney).toFixed(2);
		//若为整数，处理.00情况
		var money = "0";
		var s = total.charAt(total.length-1);
		if(s == "0"){
			money = total.split(".")[0];
		}else{
			money = total;
		}
		//赋值
		//判断月选项是否被选中
		var radioValue = $("input:radio[name='advancePeriod1']:checked").val();
		//赋值
		if(radioValue == "1"){
			if(isNaN(total)){
				$("#s_agreement_payment").val(0);
			}else{
				$("#s_agreement_payment").val(money);
			}
		}
	}
</script>
<title>修改合同</title>

</head>
<body>

	<div class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content"> 
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title">修改合同</h4>
				</div>
					<form id="update_agreementForm" action="" method="post" class="form-inline">
					<!-- <input type="hidden" id="ucpecurrentTime"></input> -->
					<input type="hidden" id="checkstatus"></input>
					<div class="modal-body">
						<div class="modal-content-basic">
						<div class="info-select clearfix">
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label><span style="color:red">*</span><p> 合同编号：</p>
								 	<input type="text" id="u_agreementCode" name="agreementCode" readonly="readonly" class="form-control" />
								 </label>
								</div>
								 <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>合同类型：</p>
									 <select name="agreementModel" id="s_agreement_type" class="form-control ">
										<!-- <option value="" selected="selected">请选择...</option> 
										<option value="2" >电子合同</option>
										<option value="3" >普通合同</option> -->
									</select>
								 </label>
							   </div>
								<!-- <div class="form-group col-xs-6" id="u_isCollectionDiv">
									<label><span style="color:red">*</span><p> 是否代收：</p></label>	
		                       	    <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="1"/>是
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="2" />否
		                       	    	</label>
		                       	    </div>
								</div> -->
								<div class="form-group col-xs-6">
									<label class="has-feedback"><span style="color:red!important">*</span><p>合同业务类型：</p>
									 <select name="agreementBusinessType" id="s_agreementBusinessType" class="form-control" disabled="true">
										<!-- <option value="">请选择...</option>  -->
										<option value="1">普通三方协议</option>
										<!-- <option value="2">医院陪护</option> -->
									</select>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p> 甲方（客户）：</p>
								 	<input type="text" id="u_agreement_partyA" name="partyA"  class="form-control"/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>联系电话：</p>
									 <input  type="text" name="mobile" id="u_agreement_mobile"  class="form-control"/>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p> 紧急联系人姓名：</p>
								 	<input type="text" id="u_agreement_linkManName" name="linkManName"  maxLength="100" class="form-control" style="behavior:url(maxLength.htc)"/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>紧急联系人电话：</p>
									 <input  type="text" name="linkManMobile" id="u_agreement_linkManMobile" class="form-control"/>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件类型：</p>
									 <select name="cardType" id="u_agreement_cardType" class="form-control uorder-card-one">
										<!-- <option value="" selected="selected">...请选择...</option> -->
										<option value="1" >身份证</option>
										<option value="2" >护照</option>
										<option value="3" >驾照</option>
										<option value="4" >营业执照</option>
									</select>
								 </label>
							   </div>
							   <div>
									<div class="uorder-card-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>身份证：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_id_cardNum" name="cardone" />
			                                    <button  class="btn" type="button" id="chack_cardNum_a" name="chack_cardNum_a">验证</button>
	                                		</label>
	                                		<strong id="chack_cardNum_a_message" name="chack_cardNum_message"></strong>
	                            		</div>
									</div>
									<div class="uorder-card-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>护照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_passport_cardNum" name="cardtwo" />
	                                		</label>
	                            		</div>
									</div>
									<div class="uorder-card-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>驾照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_license_cardNum" name="cardthree" />
	                                		</label>
	                            		</div>
									</div>
									<div class="uorder-card-way-four">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>营业执照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_businessLicense_cardNum" name="cardfour" />
	                                		</label>
	                            		</div>
									</div>
								</div>
							   <!--  <div class="form-group col-xs-6" id="uIDCardValid">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNum" id="u_agreement_ID"  class="form-control"/>
								 </label>
							   </div> -->
							   <!-- <div class="form-group col-xs-6" id="upassportValid">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNum" id="u_agreement_passport"  class="form-control"/>
								 </label>
							   </div> -->
							</div>
							<div class="row" id="u_agreement_customerAddress">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址：</p>
								 	<select id="province_ca" name="province_ca" class="form-control" onchange="queryCitys({'levels':2,'code':this.value},'city_ca');">
								 		<option value="" >--请选择--</option>
								 	</select>
								 	<select id="city_ca" name="city_ca" class="form-control" onchange="queryCitys({'levels':3,'code':this.value},'county_ca');">
								 		<option value="" >--请选择--</option>
								 	</select>
								 	<select id="county_ca" name="county_ca" class="form-control">
								 		<option value="" >--请选择--</option>
								 	</select>
									 <input type="text" id="street_ca" name="street_ca"  class="form-control"  style="width:130px"/>街道
								 </label>
								</div>
								<div class="form-group col-xs-12">
								 <label class="has-feedback"><span>&nbsp;&nbsp;</span><p></p>
									 <input type="text" id="residence_community_ca" name="residence_community_ca"  class="form-control"  style="width:130px"/>小区
									 <input type="text" id="building_No_ca" name="building_No_ca"  class="form-control"  style="width:130px"/>号楼
									 <input type="text" id="unit_ca" name="unit_ca"  class="form-control"  style="width:130px"/>单元
									 <input type="text" id="room_ca" name="room_ca"  class="form-control"  style="width:130px"/>室
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6 mt10">
								 <label class="has-feedback"><span style="color:red">*</span><p>乙方（平台）： </p>
								 	<!-- <input type="text" id="u_agreement_partyB" name="partyB" class="form-control" style="width:200px" /> -->
									<span id="u_contractHeader"></span>
								 </label>
								</div>
								<!--  <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务站： </p>
								 	<input  type="hidden" name="serviceGarageId" id="serviceGarageId" class="form-control" readOnly>
								 	<input  type="text" name="serviceGarage" id="u_agreement_serviceGarage" class="form-control" readOnly   style="width: 220px;"/>
								 </label>
								</div> -->
								<div class="form-group col-xs-6">
								 <label class="has-feedback "><span style="color:red!important">*</span><p>联系电话： </p>
								 	<input type="text" id="u_mobileB" name="mobileB" class="form-control"  /> 
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址： </p>
								 <input type="text" id="u_agreement_platformAddress" name="platformAddress" style="width:550px" class="form-control" />
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>丙方（服务员）：</p>
								 	<input type="text" id="u_agreement_partyC" name="partyC"  class="form-control" style="width:200px" readOnly/>
								 	<input type="hidden" id="u_agreement_partyCId" name="partyCId"  class="form-control" style="width:200px"/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>联系电话：</p>
									 <input  type="text" name="mobileC" id="u_agreement_mobileC" class="form-control" readOnly/>
								 </label>
							   </div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件类型：</p>
									 <select name="cardType_C" id="u_agreement_cardTypeC" class="form-control uorder-cardC-one"  disabled>
										<!-- <option value="" selected="selected">...请选择...</option> -->
										<option value="1" >身份证</option>
										<option value="2" >护照</option>
										<option value="3" >驾照</option>
									</select>
								 </label>
							   </div>
							   <div>
									<div class="uorder-cardC-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>身份证：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_id_cardNumC" name="cardfive" readOnly/>
			                                    <button  class="btn" type="button" id="chack_cardNum_c" name="chack_cardNum_c">验证</button>
	                                		</label>
	                                		 <strong id="chack_cardNum_c_message" name="chack_cardNum_message"></strong>
	                            		</div>
									</div>
									<div class="uorder-cardC-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>护照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_passport_cardNumC" name="cardtwo" readOnly/>
	                                		</label>
	                            		</div>
									</div>
									<div class="uorder-cardC-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>驾照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_license_cardNumC" name="cardthree" readOnly/>
	                                		</label>
	                            		</div>
									</div>
									<div class="uorder-cardC-way-four">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red">*</span><p>营业执照：</p>
			                                    <input class="form-control" type="text" id="uorder_agree_add_businessLicense_cardNumC" name="cardfour"  readOnly/>
	                                		</label>
	                            		</div>
									</div>
								</div>
							   
							    <!-- <div class="form-group col-xs-6" id="uIDCardValidC">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNumC" id="u_agreement_IDC"  class="form-control"/>
								 </label>
							   </div> -->
							   <!-- <div class="form-group col-xs-6" id="upassportValidC">
								 <label class="has-feedback"><span style="color:red">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNumC" id="u_agreement_passportC"  class="form-control"/>
								 </label>
							   </div> -->
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>地址： </p>
								 <input type="text" id="u_agreement_waiterAddress" name="waiterAddress" style="width:550px" class="form-control" readOnly/>
								 </label>
								</div>
							</div>
							 <hr>
                           	<div class="row">
							   <div class="form-group col-xs-12">
								 <label>&nbsp;&nbsp;<p>服务内容：</p>
									 略，详见订单明细
								 </label>
								</div>
							</div>
							<div class="row" id="u_agreement_serviceAddress">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务场所： </p>
								    <select id="province_a" name="province_a" class="form-control" onchange="queryCitys({'levels':2,'code':this.value},'city_a');">
								 		<option value="" >--请选择--</option>
								 	</select>
								 	<select id="city_a" name="city_a" class="form-control" onchange="queryCitys({'levels':3,'code':this.value},'county_a');">
								 		<option value="" >--请选择--</option>
								 	</select>
								 	<select id="county_a" name="county_a" class="form-control">
								 		<option value="" >--请选择--</option>
								 	</select>
									 <input type="text" id="street_a" name="street_a"  class="form-control"  style="width:130px"/>街道
								 </label>
								</div>
								<div class="form-group col-xs-12">
								 <label class="has-feedback"><span>&nbsp;&nbsp;</span><p></p>
									 <input type="text" id="residence_community_a" name="residence_community_a"  class="form-control"  style="width:130px"/>小区
									 <input type="text" id="building_No_a" name="building_No_a"  class="form-control"  style="width:130px"/>号楼
									 <input type="text" id="unit_a" name="unit_a"  class="form-control"  style="width:130px"/>单元
									 <input type="text" id="room_a" name="room_a"  class="form-control"  style="width:130px"/>室
								 </label>
								</div>
							</div>
                             <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p> 合同期限：</p>
								 	<input type="text" class="Wdate form-control" id="u_agreement_createTime"  name="effectDate"
											data-type="date"  data-minDate="2000-01-01" readonly="readonly">
									&nbsp;至&nbsp;
									<input type="text" class="Wdate form-control" id="u_agreement_endTime" name="finishDate"
								 	 data-type="date"  data-minDate="2000-01-01" readonly="readonly">
								 </label>
								</div>
							</div>
							
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>签约日期：</p>
								 	<input type="text" class="Wdate form-control" id="u_agreement_contractDate"  name="contractDate" data-type="date"  data-minDate="2000-01-01" readonly="readonly">
								 </label>
								</div>
							</div>
							
							
							 <hr>
	                        
	                        
	                        <!-- 陪护服务开始 -->
		                        <div class="row" id="heal_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-12">
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务地址：</p>
									 	<input type="text" class="form-control" id="s_customerserviceAddress"  name="customerserviceAddress" style="width:600px">
									 </label>
									</div>
								</div>
								<!-- <hr id="phfw_new_div0_hr" style="display: none;"> -->
								 <div class="row" id="heal_new_div2" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">住院号：</p>
									 	<input type="text" class="form-control" id="s_hospitalizationNum"  name="hospitalizationNum">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">科室：</p>
									 	<input type="text" class="form-control" id="s_departments"  name="departments">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">病房号：</p>
									 	<input type="text" class="form-control" id="s_roomNumber"  name="roomNumber">
									 </label>
									</div>
									<div class="form-group col-xs-6">
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">床号：</p>
									 	<input type="text" class="form-control" id="s_bedNumber_a"  name="bedNumber_a" style="width:50px;">—<input type="text" class="form-control" id="s_bedNumber_b" name="bedNumber_b" style="width:50px;">
									 </label>
									</div>
								</div>
								
								<div class="row" id="heal_new_div3" style="display: none;"> 
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象现状：</p>
									 	<input type="text" class="form-control" id="s_consumerstate"  name="consumerstate" style="width:600px">
									 </label>
									</div>
								</div>
								
								
								 <div class="row" id="heal_new_div4" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象姓名：</p>
									 	<input type="text" class="form-control" id="s_consumersName"  name="consumersName">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象身份证：</p>
									 	<input type="text" class="form-control" id="s_consumersCard"  name="consumersCard">
									 	<button  class="btn" type="button" id="chack_service_cardNum" name="chack_service_cardNum">验证</button>
									 </label>
									    <strong id="chack_service_cardNum_message" name="chack_service_cardNum_message"></strong>
									</div>
									
									
								</div>
								
								
								 <div class="row" id="heal_new_div5" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务对象与甲方关系：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="1"/>&nbsp;甲方本人
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="2"/>&nbsp;与甲方为亲属关系
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="custconsumerRelation" value="3"/>&nbsp;甲方非亲属关系的委托人
		                       	      </label>
									 </div>
									</div>
								 
								 
								 </div>
								
								
								 <div class="row" id="heal_new_div6" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:170px;">甲方与服务对象具体亲属关系：</p>
									 	<input type="text" class="form-control" id="s_relation_relatives"  name="relation_relatives">
									 </label>
									</div>
								</div>
								<div class="row" id="heal_new_div6x" style="display: none;">	
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width:170px;">甲方与服务对象具体委托关系：</p>
									 	<input type="text" class="form-control" id="s_relation_entrust"  name="relation_entrust">
									 </label>
									</div>
								</div>
								
								<div class="row" id="heal_new_div7" style="display: none;">	
								
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">产妇预产期：</p>
									 	<input type="text" class="Wdate form-control" id="s_birthPeriod"  name="birthPeriod" data-type="date"  data-minDate="2000-01-01">
									 </label>
									</div>
									
								</div>
								
	                         	<div class="row" id="heal_new_div8" style="display: none;"> 
									<div class="form-group col-xs-6" >
									  <label class="has-feedback"><span style="color:red!important">*</span><p>特殊注意事项：</p></label>
								           <div class="radio">
								
		                       	    	   <label class="radio-inline">
		                       	    		<input  type="radio" name="specialConsiderations" value="1" />&nbsp;有
		                       	    	   </label>
		                       	    	   <label class="radio-inline">
		                       	    		<input  type="radio" name="specialConsiderations" value="2"/>&nbsp;无
		                       	    	   </label>
		                       	    	
		                       	          </div>
									</div>
								</div>
								
								<hr id="phfw_new_div1_hr" style="display: none;">
								
								<div class="row" id="heal_new_div9" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方选择服务项：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="1"/>&nbsp;产妇护理
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="2"/>&nbsp;新生儿护理
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="3"/>&nbsp;临床陪护
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceItems" value="4"/>&nbsp;其他
		                       	      </label>
									 </div>
									</div>
								 
								 </div>
								
								
								  <div class="row" id="heal_new_div10" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务起始日期：</p>
									 	<input type="text" class="Wdate form-control" id="s_serviceStarttime"  name="serviceStarttime" data-type="date"  data-minDate="2000-01-01">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">临床陪护暂定天数：</p>
									 	<input type="text" class="form-control" id="s_hostsitDay"  name="hostsitDay">
									 </label>
									</div>
								 
								 </div>
								 
								 <div class="row" id="heal_new_div11" style="display: none;">
								       
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">服务形式：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceFormat" value="1"/>&nbsp;白天
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="serviceFormat" value="2"/>&nbsp;24小时
		                       	      </label>
									 </div>
									</div>
									
								     <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分娩方式：</p></label>
									  <div class="radio">
									  <label class="radio-inline">
		                       	    		<input  type="radio" name="deliveryMode" value="1"/>&nbsp;顺产
		                       	      </label>
		                       	      <label class="radio-inline">
		                       	    		<input  type="radio" name="deliveryMode" value="2"/>&nbsp;剖宫产
		                       	      </label>
									 </div>
									</div>
									
									
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">更换次数：</p>
									 	<input type="text" class="form-control" id="s_replaceNumber"  name="replaceNumber">
									 </label>
									</div>
									
								 </div>
								 
		                        <!-- 陪护服务结束 -->
	                        
	                        <hr>
							
	                        <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p> 服务人员服务费（实发）：</p>
									 <input  type="text" name="serviceMoney" id="u_agreement_serviceMoney"  class="form-control" oninput="haijinChange(this)" onpaste="haijinChange(this)" onkeyup="countPayAmount()"/>&nbsp;元/月&nbsp;&nbsp;&nbsp;
	                        		服务人员法定假日加班费，是日劳务费：<input  type="text" name="chargeTimes"  class="form-control" id="u_agreement_chargeTimes" style="width:10%"/>&nbsp;倍
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>服务人员信息费：</p>
								 <input  type="text" name="personManageMoney" id="u_agreement_personManageMoney" class="form-control" onkeyup="countPayAmount()"/>&nbsp;元/月&nbsp;&nbsp;&nbsp;
	                        	客户信息费（一次）：<input  type="text" name="customerManageMoney" id="u_agreement_customerManageMoney" class="form-control" style="width:20%" onkeyup="countPayAmount()"/>&nbsp;元
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p>本次支付合计：</p>
									 <input  type="text" name="payment" id="u_agreement_payment" class="form-control" onkeyup="changeAllpay()" onchange="changeAllpay()"/>&nbsp;元&nbsp;&nbsp;&nbsp;
								 </label>
								</div>
							</div>
	                        <hr>
	                        <div class="row">
								<div class="form-group col-xs-12">
									<label class="has-feedback"><span style="color:red">*</span><p> 劳务费支付方式：</p></label>	
		                       	    <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="7" checked="checked"/>平台代付
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="6" />客户直付
		                       	    	</label>
		                       	    </div>
								</div>
	                        </div>
	                        <div class="row" id="u_oldDiv">
							   <div class="col-xs-12">
								 <label class="has-feedback"><span style="color:red">*</span><p>预付方式：</p></label>
								 <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="1"/>&nbsp;月
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="2"/>&nbsp;季
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="3"/>&nbsp;半年
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="4"/>&nbsp;年
		                       	    	</label>
		                       	  </div>
								</div>
							</div>
							<div class="row" id="u_newDiv">
							   <div class="col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付方式：</p></label>
								 <div class="radio">
								 <!-- 1 一次性预付劳务费 ，2 白条支付，3银行白条支付，4其他支付方式 -->
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="1" />&nbsp;一次性预付劳务费
		                       	    	</label>
		                       	    	 <label class="radio-inline">
		                       	    		<!--<input  type="radio" name="advancePeriod2" value="2"/>&nbsp;白条支付  -->
		                       	    		<input  type="radio" name="advancePeriod2" value="2"/>&nbsp;唯品会白条
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<!--<input  type="radio" name="advancePeriod2" value="3"/>&nbsp;银行白条支付 -->
		                       	    		<input  type="radio" name="advancePeriod2" value="3"/>&nbsp;招行分期
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="4"/>&nbsp;其他支付方式
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="5"/>&nbsp;海金保理白条
		                       	    	</label>
		                       	  </div>
								</div>
								
							</div>
							<div class="row" id="u_oldDateDiv">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red">*</span><p>甲方预支付时间：</p>
								 <input type="text" class="Wdate form-control" id="u_agreement_agreementPayDate"  name="agreementPayDate1"
											onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
								 </label>
								</div>
								<div class="form-group col-xs-6" id="upayDateDiv">
									<label class="has-feedback"><span style="color:red">*</span><p>预支付日期：</p>
										<input type="text"  id="u_agreement_remindDay" name="remindDay" class="form-control">&nbsp;日前支付
									 </label>
								</div>
								<div class="form-group col-xs-6" id="uzhifuRemarkDiv">
									<label class="has-feedback"><span style="color:red">*</span><p>支付时间说明：</p>
										<input type="text"  id="u_agreement_zhifuRemark" name="zhifuRemark" class="form-control">
									 </label>
								</div>
							</div>
							
							<hr>
							<!-- 服务协议必填项-->
							 <div class="row" id="heal_new_div12" style="display: none;">
								  
								   <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方选择服务项目收费标准(/天)：</p>
									 	<input type="text" class="form-control" id="s_serviceprojectStandard"  name="serviceprojectStandard">
									 </label>
									</div>
									
									
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">乙方指定收款账户开户名称：</p>
									 	<input type="text" class="form-control" id="s_partyBaccountName"  name="partyBaccountName">
									 </label>
									</div>
								
								   
								 </div>
								 
								 <div class="row" id="heal_new_div13" style="display: none;">
								  
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">乙方指定收款账户银行账号：</p>
									 	<input type="text" class="form-control" id="s_partyBaccountNum"  name="partyBaccountNum">
									 </label>
									</div>
								          
								    <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">乙方指定收款账户开户银行：</p>
									 	<input type="text" class="form-control" id="s_partyBaccountBank"  name="partyBaccountBank">
									 </label>
									</div>
								   
								 </div>
							<!-- 服务协议必填项 -->
							<hr>
						
							<div class="row" id="u_newDateDiv"> 
							   <div class="form-group col-xs-6" >
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付截止日期：</p>
								 	<input type="text" class="Wdate form-control" id="u_agreement_agreementPayDate_new"  name="agreementPayDate2"
											onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
								 </label>
								</div>
								<div class="form-group col-xs-6" id="u_allPayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>费用总计：</p>
										 <input  type="text" name="allPay" id="u_agreement_allPay" readonly="readonly" class="form-control" onkeyup="changePayment()" onchange="changePayment()"/>&nbsp;元
									 </label>
								</div>
								<div class="form-group col-xs-6" id="u_otherWayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>其他支付方式：</p>
										 <%--<input  type="text" name="otherWay" id="u_agreement_otherWay" class="form-control"/>--%>
										<select name="otherWay" id="u_agreement_otherWay" class="form-control">
											<option value="">--请选择--</option>
											<option value="按1个月支付">按1个月支付</option>
											<option value="按2个月支付">按2个月支付</option>
										</select>
									 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p> 其他服务：</p>
								 <input  type="text" name="otherMethods" class="form-control" id="u_agreement_otherMethods"  style="width:300px"/>
								 </label>
								</div>
							</div>
							
							<div class="row" id="phfw_msg_div1" style="display: none;"> 
								<div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>医院陪护类合同, 甲方选择的服务项目收费标准, 乙方指定收款账户开户名称, 乙方指定收款账户银行账号, 乙方指定收款账户开户地址4项为必填. </h5>
	                           </div>
							</div>
							
							<div class="row" id='u_remarkZdgDiv'>
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p> 钟点工工作时间：</p>
								 <input  type="text" name="remarkZdg" class="form-control" id="u_agreement_remarkZdg" style="width:300px"/>
								 </label>
								</div>
							</div>
	                        <hr>
	                         <!-- 海金保理白条开始 -->
		                        <div class="row" id="hjbl_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付几个月劳务费(月)：</p>
									 	<input type="text" class="form-control" id="u_prepaidMonths"  name="prepaidMonths" oninput="haijinChange(this)" onpaste="haijinChange(this)">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付劳务费总金额(元)：</p>
									 	<input type="text" class="form-control" id="u_prepaidMoney"  name="prepaidMoney" readonly="readonly">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付几个月劳务费(月)：</p>
									 	<input type="text" class="form-control" id="u_instPrepaidMonths"  name="instPrepaidMonths" oninput="haijinChange(this)" onpaste="haijinChange(this)">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付劳务费总金额(元)：</p>
									 	<input type="text" class="form-control" id="u_instPrepaidMoney"  name="instPrepaidMoney" readonly="readonly">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">几日内支付分期手续费(日)：</p>
									 	<input type="text" class="form-control" id="u_limitDays"  name="limitDays">
									 </label>
									</div>
								</div>
								<hr id="hjbl_new_div1_hr" style="display: none;">
								<div class="row" id="hjbl_new_div2" style="display: none;"> 
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户姓名：</p>
									 	<input type="text" class="form-control" id="u_accountName"  name="accountName">
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户开户行：</p>
									 	<select name="accountBank" id="u_accountBank"  class="form-control order-card-one">
										<option value="1" selected="selected">请选择</option>
										<option value="中国银行" >中国银行</option>
										<option value="农业银行" >农业银行</option>
										<option value="工商银行" >工商银行</option>
										<option value="建设银行" >建设银行</option>
										<option value="交通银行" >交通银行</option>
										<option value="中信银行" >中信银行</option>
										<option value="浦发银行" >浦发银行</option>
										<option value="上海银行" >上海银行</option>
										<option value="平安银行" >平安银行</option>
										<option value="浙商银行" >浙商银行</option>
										<option value="招商银行" >招商银行</option>
										<option value="民生银行" >民生银行</option>
										<option value="光大银行" >光大银行</option>
									</select>
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户号码：</p>
									 	<input type="text" class="form-control" id="u_accountNum"  name="accountNum">
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">银行预留手机号：</p>
									 	<input type="text" class="form-control" id="u_accountMobile" name="accountMobile">
									 	<input type="hidden" class="form-control" id="u_authCode_flag"  name="u_authCode_flag">
									 	<button class="btn btn-sm btn-primary" type="button" id="submitAuth">提交验证</button> 
									 </label>
									</div>
								</div>
	                         	<hr id="hjbl_new_div2_hr" style="display: none;">
		                        <!-- 海金保理白条结束 -->
	                        <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback form-label">&nbsp;&nbsp;<p> 其他约定：</p>
								 <textarea rows="3" name="otherMatters" id="u_agreement_otherMatters" class= "form-control form-textarea" ></textarea>
								 </label>
								</div>
							</div>
					  </div>
                     </div> 
                   </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-sm btn-primary" id="ucpeSubmit">保存</button>
				</div>
			</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx }/js/agreement/order_cpe_update.js"></script>
</body>
</html>