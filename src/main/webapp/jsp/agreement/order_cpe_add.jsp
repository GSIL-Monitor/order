<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
		/*解决第二个modal关闭后，第一个modal不能上下滑动*/
       .modal {overflow: auto !important;}
		body{
			color:#000!important;
			font-size:12px!important;
			font-family: "Helvetica Neue",Helvetica,Arial,sans-serif!important;
		}	
</style> 
<script type="text/javascript" src="${ctx}/js/agreement.js"></script>
<script type="text/javascript">
	var orderId = '${param.orderId}';
	var orderType = '${param.orderType}';
	var orgType = '${param.orgType}';
</script>
<title>新增合同</title>
<script type="text/javascript">
	$(function(){
		initAgreementList(getAgreementModels());
		$("#s_accountName").bind("input propertychange",function(event){
		      $("#s_authCode_flag").val("");
		      $("#submitAuth").removeAttr("disabled");
		});
		$("#s_accountBank").bind("input propertychange",function(event){
		      $("#s_authCode_flag").val("");
		      $("#submitAuth").removeAttr("disabled");
		});
		$("#s_accountNum").bind("input propertychange",function(event){
		      $("#s_authCode_flag").val("");
		      $("#submitAuth").removeAttr("disabled");
		});
		$("#s_accountMobile").bind("input propertychange",function(event){
		      $("#s_authCode_flag").val("");
		      $("#submitAuth").removeAttr("disabled");
		});
		$("select[name='agreementBusinessType']").change(function(){
			var changeVal = $(this).val();
			if(typeof changeVal !='undefined' && changeVal !='' && changeVal !=null && changeVal == '2'){
				$("#phfw_new_div1_hr").show();
            	$("#phfw_new_div2_hr").show();
				$("#heal_new_div1").show();
				$("#heal_new_div2").show();
				$("#heal_new_div3").show();
				$("#heal_new_div4").show();
				$("#heal_new_div5").show();
				//$("#heal_new_div6").show();
				$("#heal_new_div7").show();
				$("#heal_new_div8").show();
				$("#heal_new_div9").show();
				$("#heal_new_div10").show();
				$("#heal_new_div11").show();
				$("#heal_new_div12").show();
				$("#heal_new_div13").show();
				$("#phfw_msg_div1").show();
				//支付方式只能选 [平台代收] – [一次性预付服务费]
				$("input:radio[name='isCollection']:not([checked])").attr("disabled","disabled");
				$("input:radio[name='advancePeriod2']").eq(0).attr("checked",true);
				$("input:radio[name='advancePeriod2']:not([checked])").attr("disabled","disabled");
				
				$('#add_agreementForm').bootstrapValidator('addField', 'customerserviceAddress', { 
					validators: { 
					notEmpty: { 
					message: '服务地址不能为空'
					} 
					} 
				}); 
				
				$('#add_agreementForm').bootstrapValidator('addField', 'hospitalizationNum', { 
					validators: { 
					notEmpty: { 
					message: '住院号不能为空'
					} 
					} 
				});
				
				$('#add_agreementForm').bootstrapValidator('addField', 'departments', { 
					validators: { 
					notEmpty: { 
					message: '科室不能为空'
					} 
					} 
				}); 
				
				$('#add_agreementForm').bootstrapValidator('addField', 'roomNumber', { 
					validators: { 
					notEmpty: { 
					message: '病房号不能为空'
					} 
					} 
				});
				
				if($('#s_bedNumber_a').val()=='' || $('#s_bedNumber_a').val()==null){
					$('#add_agreementForm').bootstrapValidator('addField', 'bedNumber_a', { 
						validators: { 
						notEmpty: { 
						message: '床号不能为空'
						} 
						} 
					});
				}
				
				if($('#s_bedNumber_b').val()=='' || $('#s_bedNumber_b').val()==''){
					$('#add_agreementForm').bootstrapValidator('addField', 'bedNumber_b', { 
						validators: { 
						notEmpty: { 
						message: '床号不能为空'
						} 
						} 
					});
				}
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'consumerstate', { 
					validators: { 
					notEmpty: { 
					message: '服务对象现状不能为空'
					} 
					} 
				});
				$('#add_agreementForm').bootstrapValidator('addField', 'consumersName', { 
					validators: { 
					notEmpty: { 
					message: '服务对象姓名不能为空'
					} 
					} 
				});
				$('#add_agreementForm').bootstrapValidator('addField', 'consumersCard', { 
					validators: { 
					notEmpty: { 
					message: '服务对象身份证不能为空'
					} 
					} 
				});
				
				var custconsumerrlVal = $("input[name='custconsumerRelation']:checked").val();
				if(custconsumerrlVal==null||custconsumerrlVal==""){
					$('#add_agreementForm').bootstrapValidator('addField', 'custconsumerRelation', { 
						validators: { 
						notEmpty: { 
						message: '请选择服务对象与甲方关系'
						} 
						} 
					});
				}
				
				
				if(custconsumerrlVal!=null&&custconsumerrlVal==2){
					if($('#s_relation_relatives').val()==null||$('#s_relation_relatives').val()==''){
						$('#add_agreementForm').bootstrapValidator('addField', 'relation_relatives', { 
							validators: { 
							notEmpty: { 
							message: '请填写具体亲属关系'
							} 
							} 
						});
					}
				}else if(custconsumerrlVal!=null&&custconsumerrlVal==3){
					if($('#s_relation_entrust').val()==null||$('#s_relation_entrust').val()==''){
						$('#add_agreementForm').bootstrapValidator('addField', 'relation_entrust', { 
								validators: { 
								notEmpty: { 
								message: '请填写具体委托关系'
								} 
								} 
						});
					  }
				}
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'birthPeriod', { 
					validators: { 
					notEmpty: { 
					message: '请选择产妇预产期'
					} 
					} 
				});
				
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'specialConsiderations', { 
						validators: { 
						notEmpty: { 
						message: '请选择特殊注意事项'
						} 
						} 
				});
				
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'serviceItems', { 
						validators: { 
						notEmpty: { 
						message: '请选择甲方服务项目'
						} 
						} 
				});
			
				$('#add_agreementForm').bootstrapValidator('addField', 'serviceStarttime', { 
					validators: { 
					notEmpty: { 
					message: '请选择服务起始日期'
					} 
					} 
				});
				
				$('#add_agreementForm').bootstrapValidator('addField', 'hostsitDay', { 
					validators: { 
					notEmpty: { 
					message: '请填写临床陪护暂定天数'
					} 
					} 
				});
				
				$('#add_agreementForm').bootstrapValidator('addField', 'serviceFormat', { 
					validators: { 
					notEmpty: { 
					message: '请选择服务形式'
					} 
					} 
				});
				$('#add_agreementForm').bootstrapValidator('addField', 'deliveryMode', { 
					validators: { 
					notEmpty: { 
					message: '请选择分娩方式'
					} 
					} 
				});
				
				$('#add_agreementForm').bootstrapValidator('addField', 'replaceNumber', { 
					validators: { 
					notEmpty: { 
					message: '请填写更换次数'
					} 
					} 
				});
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'serviceprojectStandard', { 
					validators: { 
					notEmpty: { 
					message: '请填写选择服务项目收费标准'
					} 
					} 
				});
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'partyBaccountName', { 
					validators: { 
					notEmpty: { 
					message: '请填写乙方指定收款账户开户名称'
					} 
					} 
				});
				
				
				$('#add_agreementForm').bootstrapValidator('addField', 'partyBaccountNum', { 
					validators: { 
					notEmpty: { 
					message: '请填写乙方指定收款账户银行账号'
					} 
					} 
				});
				
				$('#add_agreementForm').bootstrapValidator('addField', 'partyBaccountBank', { 
					validators: { 
					notEmpty: { 
					message: '请填写乙方指定收款账户开户银行'
					} 
					} 
				});
				
			}else{
				
				$("input:radio").attr("disabled",false);
				
				$("#phfw_new_div1_hr").hide();
            	$("#phfw_new_div2_hr").hide();
				$("#heal_new_div1").hide();
				$("#heal_new_div2").hide();
				$("#heal_new_div3").hide();
				$("#heal_new_div4").hide();
				$("#heal_new_div5").hide();
				//$("#heal_new_div6").hide();
				$("#heal_new_div7").hide();
				$("#heal_new_div8").hide();
				$("#heal_new_div9").hide();
				$("#heal_new_div10").hide();
				$("#heal_new_div11").hide();
				$("#heal_new_div12").hide();
				$("#heal_new_div13").hide();
				$("#phfw_msg_div1").hide();
				
				$("add_agreementForm").bootstrapValidator('removeField','customerserviceAddress');
				$("add_agreementForm").bootstrapValidator('removeField','hospitalizationNum');
				$("add_agreementForm").bootstrapValidator('removeField','departments');
				$("add_agreementForm").bootstrapValidator('removeField','roomNumber');
				$("add_agreementForm").bootstrapValidator('removeField','bedNumber_a');
				$("add_agreementForm").bootstrapValidator('removeField','bedNumber_b');
				$("add_agreementForm").bootstrapValidator('removeField','consumerstate');
				$("add_agreementForm").bootstrapValidator('removeField','consumersName');
				$("add_agreementForm").bootstrapValidator('removeField','consumersCard');
				$("add_agreementForm").bootstrapValidator('removeField','custconsumerRelation');
				$("add_agreementForm").bootstrapValidator('removeField','relation_relatives');
				$("add_agreementForm").bootstrapValidator('removeField','relation_entrust');
				$("add_agreementForm").bootstrapValidator('removeField','birthPeriod');
				
				
				$("add_agreementForm").bootstrapValidator('removeField','partyBaccountBank');
				$("add_agreementForm").bootstrapValidator('removeField','partyBaccountNum');
				$("add_agreementForm").bootstrapValidator('removeField','partyBaccountName');
				$("add_agreementForm").bootstrapValidator('removeField','serviceprojectStandard');
				$("add_agreementForm").bootstrapValidator('removeField','replaceNumber');
				$("add_agreementForm").bootstrapValidator('removeField','deliveryMode');
				$("add_agreementForm").bootstrapValidator('removeField','serviceFormat');
				$("add_agreementForm").bootstrapValidator('removeField','hostsitDay');
				$("add_agreementForm").bootstrapValidator('removeField','serviceStarttime');
				$("add_agreementForm").bootstrapValidator('removeField','serviceItems');
				$("add_agreementForm").bootstrapValidator('removeField','specialConsiderations');
				
			} 
		});
		
		
		//createRandomCode();
		loadContractHeader();
		
		if(orderType == "100200040002" ){//钟点工
			$("#remarkZdgDiv").show();
		}else{
			$("#remarkZdgDiv").hide();
		}
		/* if(orgType != "3" ){//加盟商部门
			$("#collectionDiv").show();
		}else{
			$("#collectionDiv").hide();
		} */
		$("#zhifuRemarkDiv").hide();
		
		$("input[name='isCollection']").change(function(){
			var val = $(this).val();
			if(val != null && val == 7){
				$("#oldDiv").hide();
				$("#oldDateDiv").hide();
				
				$("#newDiv").show();
				$("#newDateDiv").show();
				$("#otherWayDiv").hide();
			}else{
				$("#oldDiv").show();
				$("#oldDateDiv").show();
				
				$("#newDiv").hide();
				$("#newDateDiv").hide();
				$("#otherWayDiv").hide();
				$("input[name='advancePeriod2']").prop("checked",false);
				$("#hjbl_new_div1_hr").hide();//分隔线
				$("#hjbl_new_div2_hr").hide();//分隔线
				$("#hjbl_new_div1").hide();
				$("#hjbl_new_div2").hide();
				$("#hjbl_new_div1").find("input").val("");
				$("#hjbl_new_div2").find("input").val("");
			}
		});
		$("input[name='advancePeriod2']").change(function(){
			var val = $(this).val();
			if(val != null && val == 4){
				$("#otherWayDiv").show();
				$("#allPayDiv").hide();
			}else{
				$("#otherWayDiv").hide();
				$("#allPayDiv").show();
			}
			//海金保理白条
			if(val == 5){
				var type=$("#s_agreement_cardType").val();
				if(type != 1){
					$.alert({millis:5000,text:"四要素验证必须使用身份证"});
					$("input[type='radio'][name='advancePeriod2']").attr("checked",false);
					$("#order_agree_add_id_cardNum").val("");
					$("#s_agreement_cardType").val("1").trigger("change");
					$("#s_agreement_cardType").val("1");
					$("#s_agreement_cardType").attr("disabled",true);
					return
				}
				$("#hjbl_new_div1_hr").show();//分隔线
				$("#hjbl_new_div2_hr").show();//分隔线
				$("#hjbl_new_div1").show();
				$("#hjbl_new_div2").show();
				
			}else{
				$("#hjbl_new_div1_hr").hide();//分隔线
				$("#hjbl_new_div2_hr").hide();//分隔线
				$("#hjbl_new_div1").hide();
				$("#hjbl_new_div2").hide();
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
		
		
		
	});
	
	
	
	
	
	
	/**平台代付-海金保理白条,根据服务人员服务费计算,预付劳务费总金额和分期支付劳务费总金额*/
	function haijinChange(thiz){
		var serviceMoney=$("#s_agreement_serviceMoney").val()||0; //服务人员服务费（实发）
		var prepaidMonths =$("#s_prepaidMonths").val()||0; //预付几个月劳务费(月)
		var instPrepaidMonths=$("#s_instPrepaidMonths").val()||0; //分期支付几个月劳务费(月)
		$("#s_prepaidMoney").val(prepaidMonths*serviceMoney);
		var allMonths=parseInt(prepaidMonths)+parseInt(instPrepaidMonths);
		if(allMonths<13){
			$("#s_instPrepaidMoney").val(serviceMoney*instPrepaidMonths);
		}else{
			$(thiz).val("");
			$.alert({millis:5000,text:"[预付几个月劳务费(月)] + [分期支付几个月劳务费(月)]不得大于12个月, 如需录入一年以上的合同请联系结算中心!"});
		}
		var bv = $("#add_agreementForm").data("bootstrapValidator");
		bv.updateStatus("prepaidMoney", "NOT_VALIDATED").validateField("prepaidMoney");
		bv.updateStatus("instPrepaidMoney", "NOT_VALIDATED").validateField("instPrepaidMoney");
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
		$("#s_agreement_type").val('20520002');
		
		$("select[name='agreementModel']").change(function(){
			var changeVal = $(this).val();
			if(typeof changeVal !='undefined' && changeVal !='' && changeVal !=null && changeVal == '20520001'){
				$.alert({top:'30%',text:"如果您未明确收到可以使用电子合同的通知, 请勿选择此选项!"});
			} 
		});
		
	}
	
	//创建合同编号
	function createRandomCode() {
		 var now = new Date();
	    var year = now.getFullYear().toString();
	    var month =(now.getMonth() + 1).toString();
	    var day = (now.getDate()).toString();
	    var hours = (now.getHours()).toString();
	    var minutes = (now.getMinutes()).toString();
	    var seconds = (now.getSeconds()).toString();
	    
	    if (month.length == 1) { month = "0" + month; }
	    if (day.length == 1) { day = "0" + day; }
	    if(hours.length == 1){ hours = "0" + hours; }
	    if(minutes.length == 1){ minutes = "0" + minutes; }
	    if(seconds.length == 1){ seconds = "0" + seconds; }
	    ye = year.substr(2,4);
	    var random = Math.round(Math.random() * 10 + 89);//10-99之间的随机数
	    var dateTime = ye + month + day + hours + minutes + seconds + random;
	    
		$("#s_agreementCode").val(dateTime);
	}
	
	//新：查询乙方合同头和地址
	function loadContractHeader(){
		$.ajax({
			url : ctx+"/agreement/queryAgreementHeader",
			data :null,
			type : "POST",
			async : false,
			success : function(data) {
				if(data.msg == "00"){
					var v = data.result;
					$("#s_contractHeader").text(v.CONTRACT_HEADER||"");
					$("#s_agreement_platformAddress").val(v.CONTRACTHEADERADDR||"");
				}
			}
		});
	}

	//预付方式联动
	$("input[name='advancePeriod1']").change(function(){
		if($(this).val() == 1){
			$("#payDateDiv").show();
			$("#zhifuRemarkDiv").hide();
			//计算本次支付合计
			var serviceMoney = parseFloat($("#s_agreement_serviceMoney").val());//服务人员服务费(实发)
			var customerManageMoney = parseFloat($("#s_agreement_customerManageMoney").val());//客户信息费
			var personManageMoney = parseFloat($("#s_agreement_personManageMoney").val());//服务人员信息费
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
				$("#s_agreement_payment").val(0);//赋值
			}else{
				$("#s_agreement_payment").val(money).trigger("keyup");//赋值
			}
		}else{
			$("#payDateDiv").hide();
			$("#zhifuRemarkDiv").show();
		}
	});
	/*
	 * 通过服务人员来渲染关于服务人员的信息
	 * 王世博
	 */
	function personnelDetailed(thiz){
		var address = $(thiz).find("option:selected").attr("data-address");
		var cardNum = $(thiz).find("option:selected").attr("data-cardNum");
		var cardType = $(thiz).find("option:selected").attr("data-cardType");
		var mobile = $(thiz).find("option:selected").attr("data-mobile");
		var starTime = $(thiz).find("option:selected").attr("data-starTime");
		var endTime = $(thiz).find("option:selected").attr("data-endTime");
		var id = $(thiz).find("option:selected").attr("data-id");
		$("#s_agreement_partyC_id").val(id);
		$("#s_agreement_mobileC").val(mobile);
		$("#s_agreement_cardTypeC").val(cardType);
		$("#order_agree_add_id_cardNumC").val(cardNum);
		$("#s_agreement_waiterAddress").val(address);
		$("#s_agreement_createTime").val(starTime);
		$("#s_agreement_endTime").val(endTime);
		var bv = $("#add_agreementForm").data("bootstrapValidator");
		bv.updateStatus("cardfive", "NOT_VALIDATED").validateField("cardfive");
		bv.updateStatus("waiterAddress", "NOT_VALIDATED").validateField("waiterAddress");
		bv.updateStatus("mobileC", "NOT_VALIDATED").validateField("mobileC");
		$("#order_agree_add_id_cardNumC").trigger("input");
	}
	
    
	//进行验证的方法
	$('#add_agreementForm').bootstrapValidator({
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
	                    	 	  $("#s_accountName").val(value);
	                    	 	  validator.updateStatus("accountName", "NOT_VALIDATED").validateField("accountName");
	                    	 	 return value == $("#s_accountName").val();
	                    	  }else{
	                    		  $("#s_accountName").val("");
	                    		  validator.updateStatus("accountName", "NOT_VALIDATED");
	                    	  }
	                    	  return true;
	                    	}
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
	               /* callback: {
                      message: '【银行预留手机号】 字段须与 【联系电话】 字段一致!',
                      callback: function(value, validator) {
                    	  var selHjbl = $("input[name=advancePeriod2][value=5]").prop("checked");
                    	  if(selHjbl){
                    	 	  $("#s_accountMobile").val(value);
                    	 	  validator.updateStatus("accountMobile", "NOT_VALIDATED").validateField("accountMobile");
                    	 	 return value == $("#s_accountMobile").val();
                    	  }else{
                    		  $("#s_accountMobile").val("");
                    		  validator.updateStatus("accountMobile", "NOT_VALIDATED");
                    	  }
                    	  return true;
                    	}
                } */
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
	            validators: {
	                notEmpty: {
	                    message: '必填！'
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
	        			regexp:  /^\d+(\.\d{1,2})?$/ ,
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
	        			regexp: /^\d+(\.\d{1,2})?$/ ,
	        			message: '请填写正确费用！'
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
	        	}
	        },
	        customerManageMoney:{
	        	validators: {
	        		notEmpty: {
	        			message: '必填！'
	        		},
	        		regexp: {
	        			regexp: /^\d+(\.\d{1,2})?$/ ,
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
                        message: '【甲方(客户)账户姓名】 字段须与 【甲方（客户）】 字段一致!'

 	                 }
                }
            },
            accountMobile : {
                validators: {
                	 notEmpty: {
 	                    message: '必填'
 	                }
                   /*  identical: {//与指定控件内容比较是否相同，比如两次密码不一致
 	                	field: 'mobile',//指定控件name
                        message: '【银行预留手机号】 字段须与 【联系电话】 字段一致!'

 	                 } */
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
	    //保存
	    agreement.save(orderId,orderType);
	}); 
	inputreplacethreeAgree(".order-card-one",".order-card-way-one",".order-card-way-two",'cardone','cardtwo',".order-card-way-three",'cardthree',".order-card-way-four",'cardfour');
	inputreplacethreeAgree(".order-cardC-one",".order-cardC-way-one",".order-cardC-way-two",'cardfive','cardtwo',".order-cardC-way-three",'cardthree',".order-cardC-way-four",'cardfour');
/* 	$("#s_agreement_cardType").change(function(){
		 if($(this).find("option:selected").val() == 1 || $(this).find("option:selected").val() == 3){
			$("#IDCardValid").show();
			$("#passportValid").hide();
		}else{
			$("#IDCardValid").hide();
			$("#passportValid").show();
		} 
	});
	$("#s_agreement_cardTypeC").change(function(){
		 if($(this).find("option:selected").val() == 1 || $(this).find("option:selected").val() == 3){
			$("#IDCardValidC").show();
			$("#passportValidC").hide();
		}else{
			$("#IDCardValidC").hide();
			$("#passportValidC").show();
		} 
	});
	//验证身份证号
	function IDvalid(eleId){
		setTimeout(function(){
		var reg = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;
		var idCardNum = $("#"+eleId).val();
		if(!reg.test(idCardNum)){
				$.alert({millis:2000,top:'30%',text:"请填写正确证件号！"});
		}
		},2000);
	}
	//验证护照号
	function passportvalid(eleId){
		setTimeout(function(){
		var reg = /^[a-zA-Z0-9]{3,21}$/;
		var passportNum = $("#"+eleId).val();
		if(!reg.test(passportNum)){
				$.alert({millis:2000,top:'30%',text:"请填写正确证件号！"});
		}
		},2000);
	}
		$("#s_agreement_ID").on("keyup",function(){
			IDvalid("s_agreement_ID");
		})
		$("#s_agreement_IDC").on("keyup",function(){
			IDvalid("s_agreement_IDC");
		})
		$("#s_agreement_passport").on("keyup",function(){
			passportvalid("s_agreement_passport");
		})
		$("#s_agreement_passportC").on("keyup",function(){
			passportvalid("s_agreement_passportC");
		}) */

	//预付方式为月时，修改费用同步本次支付合计
	function countPayAmount(){
		//服务人员服务费
		var serviceMoney = parseFloat($("#s_agreement_serviceMoney").val());
		//服务人员信息费
		var personManageMoney = parseFloat($("#s_agreement_personManageMoney").val());
		//客户信息费
		var customerManageMoney = parseFloat($("#s_agreement_customerManageMoney").val());
		//判断月选项是否被选中
		var radioValue = $("input:radio[name='advancePeriod1']:checked").val();
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
		if(radioValue == "1"){
			if(isNaN(total)){
				$("#s_agreement_payment").val(0);
			}else{
				$("#s_agreement_payment").val(money);
			}
		}
	}
		/** 周鑫  2018-11-14 计算时间，  **/
		function sumDate(){
			//获取时间
			var startA=$("#s_agreement_createTime").val();
			var endA=$("#s_agreement_endTime").val();
			var day=0;
			if(startA!=""&&startA!=null&&endA!=""&&endA!=null){
			//截取出年月日
			var startB=startA.substring(0,10);
			var endB=endA.substring(0,10);
			//替换
			var start = startB.split("-");
			var end = endB.split("-");
			//计算相差年份
			var year=end[0]-start[0];
			//计算月份
			var month=year*12+(end[1]-start[1]);
			//计算日的相差值
			var c=(end[2]-start[2])
			//给日期赋值
			if(c>-1){
				day=c+1;
			}
			if(c<-1&&month!=0){
				month=month-1;
				var date1 = new Date(end[0]+'/'+(end[1]-1)+'/'+(start[2]-1)).getTime();
				var date2 = new Date(end[0]+'/'+end[1]+'/'+end[2]).getTime();
				var diff=date2-date1;
				day = diff / 1000 / 60 / 60 / 24;
			}
			var date3 = new Date(start[0]+'/'+start[1]+'/'+start[2]).getTime();
			var date4 = new Date(end[0]+'/'+end[1]+'/'+end[2]).getTime();
			var diff1=(date4-date3)/ 1000 / 60 / 60 / 24;
			$("#date1").text(month+'个月'+day+'天'+"(共"+(diff1+1)+"天)");
			}
		}		
</script>
</head>
<body>

	<div class="modal fade" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title">新增合同</h4>
				</div>
					<form id="add_agreementForm" action="" method="post" class="form-inline">
					<!-- <input type="hidden" id="cpecurrentTime"></input> -->
					<div class="modal-body">
						<div class="modal-content-basic">
							<div class="info-select clearfix">
							<!-- <div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p> 合同编号：</p>
								 	<input type="text" id="s_agreementCode" name="agreementCode"  readonly="readonly" class="form-control" />
								 </label>
								</div>
								<div class="form-group col-xs-6">
									<label class="has-feedback"><span style="color:red!important">*</span><p> 是否代收：</p></label>	
		                       	    <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="1" checked="checked"/>是
		                       	    	</label>
		                       	    	<label class="radio-inline">
		                       	    		<input type="radio" name="isCollection" value="2" />否
		                       	    	</label>
		                       	    </div>
								</div>
							</div> -->
							<div class="row">
								  <div class="form-group col-xs-4">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>合同类型：</p>
									 <select name="agreementModel" id="s_agreement_type" class="form-control ">
										<!-- <option value="" selected="selected">请选择...</option> 
										<option value="2" >电子合同</option>
										<option value="3" >普通合同</option> -->
									</select>
								 </label>
							   </div>
							   
							<div class="form-group col-xs-4">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>合同业务类型：</p>
									 <select name="agreementBusinessType" id="s_agreementBusinessType" class="form-control ">
										<!-- <option value="">请选择...</option>  -->
										<option value="1" selected="selected">普通三方协议</option>
										<!-- <option value="2" >医院陪护</option> -->
									</select>
								 </label>
							   </div> 
							   
							   <div class="form-group col-xs-4"></div>
							    <div class="form-group col-xs-2">
								  <%-- <button type="button" class="btn" onclick="openModule('${ctx }/jsp/agreement/order_alert.jsp',{},null,{width:'65%'})" > --%>
								  <button type="button" class="btn btn-sm" onclick="window.open('${ctx }/jsp/agreement/order_alert.jsp')" >
								  <span class="glyphicon glyphicon-question-sign"></span>帮助</button>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p> 甲方（客户）：</p>
								 	<input type="text" id="s_agreement_partyA" name="partyA"  maxLength="100" class="form-control" style="behavior:url(maxLength.htc)"/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>联系电话：</p>
									 <input  type="text" name="mobile" id="s_agreement_mobile" class="form-control"/>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p> 紧急联系人姓名：</p>
								 	<input type="text" id="s_agreement_linkManName" name="linkManName"  maxLength="100" class="form-control" style="behavior:url(maxLength.htc)"/>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>紧急联系人电话：</p>
									 <input  type="text" name="linkManMobile" id="s_agreement_linkManMobile" class="form-control"/>
								 </label>
							   </div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>证件类型：</p>
									 <select name="cardType" id="s_agreement_cardType" class="form-control order-card-one">
										<!-- <option value="" >...请选择...</option> -->
										<option value="1" selected="selected">身份证</option>
										<option value="2" >护照</option>
										<option value="3" >驾照</option>
										<option value="4" >营业执照</option>
									</select>
								 </label>
							   </div>
							   <div>
									<div class="order-card-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>身份证：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_id_cardNum" name="cardone" />
			                                    <button  class="btn" type="button" id="chack_cardNum_a" name="chack_cardNum_a">验证</button>
	                                		</label>
			                                    <strong id="chack_cardNum_a_message" name="chack_cardNum_message"></strong>
	                            		</div>
									</div>
									<div class="order-card-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>护照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_passport_cardNum" name="cardtwo" />
	                                		</label>
	                            		</div>
									</div>
									<div class="order-card-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>驾照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_license_cardNum" name="cardthree" />
	                                		</label>
	                            		</div>
									</div>
									<div class="order-card-way-four">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>营业执照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_businessLicense_cardNum" name="cardfour" />
	                                		</label>
	                            		</div>
									</div>
								</div>
							   <!-- <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNum" id="s_agreement_ID"  class="form-control"/>
								 </label>
							   </div> -->
							  <!--  <div class="form-group col-xs-6" id="passportValid">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>证件号码：</p>
									 <input  type="text" name="cardNum" id="s_agreement_passport"  class="form-control"/>
								 </label>
							   </div> -->
							</div>
							<div class="row" id="s_agreement_customerAddress">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>地址：</p>
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
								 <label class="has-feedback "><span style="color:red!important">*</span><p>乙方（平台）： </p>
								 	<!-- <input type="text" id="s_agreement_partyB" name="partyB" class="form-control" style="width:200px" maxLength="100" style="behavior:url(maxLength.htc)"/> -->
								 	<span id="s_contractHeader"></span>
								 </label>
								</div>
								
								<div class="form-group col-xs-6">
								 <label class="has-feedback "><span style="color:red!important">*</span><p>联系电话： </p>
								 	<input type="text" id="s_mobileB" name="mobileB" class="form-control"  /> 
								 </label>
								</div>
							   <!-- <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>服务站： </p>
								 	<input  type="hidden" name="serviceGarage" id="s_agreement_serviceGarage" class="form-control" readOnly/>
								 	<select name="serviceGarageId" id="serviceGarageId" class="form-control" onchange="selectServiceStation()" style="width: 220px">
								 		<option value="" >--请选择--</option>
								 	</select>
								 </label>
								</div> -->
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>地址： </p>
								 <input type="text" id="s_agreement_platformAddress" name="platformAddress" style="width:550px" class="form-control" maxLength="100" style="behavior:url(maxLength.htc)"/>
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>丙方（服务员）：</p>
								 	<input type="hidden" id="s_agreement_partyC_id"   class="form-control" readOnly/>
								 	<select name="partyC" id="s_agreement_partyC" onchange="personnelDetailed(this)" class="form-control" style="width: 220px">
								 		<option value="" >--请选择--</option>
								 	</select>
								 </label>
								</div>
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>联系电话：</p>
									 <input  type="text" name="mobileC" id="s_agreement_mobileC" class="form-control" readOnly/>
								 </label>
							   </div>
							</div>
							<div class="row">
								<div class="form-group col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>证件类型：</p>
									 <select name="cardType_C" id="s_agreement_cardTypeC" class="form-control order-cardC-one"  disabled>
										<option value="1" selected="selected">身份证</option>
										<option value="2" >护照</option>
										<option value="3" >驾照</option>
									</select>
								 </label>
							   </div>
							   <div>
									<div class="order-cardC-way-one">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>身份证：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_id_cardNumC" name="cardfive" readOnly/>
			                                    <button  class="btn" type="button" id="chack_cardNum_c" name="chack_cardNum_c">验证</button>
	                                		</label>
	                                		 <strong id="chack_cardNum_c_message" name="chack_cardNum_message"></strong>
	                            		</div>
									</div>
									<div class="order-cardC-way-two">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>护照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_passport_cardNumC" name="cardtwo" readOnly/>
	                                		</label>
	                            		</div>
									</div>
									<div class="order-cardC-way-three">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>驾照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_license_cardNumC" name="cardthree" readOnly/>
	                                		</label>
	                            		</div>
									</div>
									<div class="order-cardC-way-four">
										<div class="form-group col-xs-6">
	                                		<label>
			                                    <span style="color:red!important">*</span><p>营业执照：</p>
			                                    <input class="form-control" type="text" id="order_agree_add_businessLicense_cardNumC" name="cardfour" readOnly/>
	                                		</label>
	                            		</div>
									</div>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>地址： </p>
								 <input type="text" id="s_agreement_waiterAddress" name="waiterAddress" style="width:550px" class="form-control" maxLength="100" style="behavior:url(maxLength.htc)" readOnly/>
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
							<div class="row" id="s_agreement_serviceAddress">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>服务场所： </p>
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
								 <label class="has-feedback"><span style="color:red!important">*</span><p> 合同期限：</p>
								 	<input type="text" class="Wdate form-control" id="s_agreement_createTime"  name="effectDate"
											data-type="date"  data-minDate="2000-01-01" readonly="readonly" onblur="sumDate()"	 />
									&nbsp;至&nbsp;
									<input type="text" class="Wdate form-control" id="s_agreement_endTime" name="finishDate"
								 	 data-type="date"  data-minDate="2000-01-01" readonly="readonly" onblur="sumDate()"  />
								 	 <span id="date1" style="color:red!important"></span>
								 </label>
								</div>
								<div class="form-group col-xs-12"  >
								   <span id="s_agreement_time_Msg" style="color:red!important"></span>
								</div>
							</div> 
							
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>签约日期：</p>
								 	<input type="text" class="Wdate form-control" id="s_agreement_contractDate"  name="contractDate" data-type="date"  data-minDate="2000-01-01" readonly="readonly">
								 </label>
								</div>
							</div>
							 
	                        <hr>
	                        
		                        <!-- 陪护服务开始 -->

		                        <div class="row" id="heal_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-12">
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">陪护服务地址：</p>
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
								 <label class="has-feedback"><span style="color:red!important">*</span><p> 服务人员服务费（实发）：</p>
									 <input  type="text" name="serviceMoney" id="s_agreement_serviceMoney" class="form-control" onkeyup="countPayAmount()" oninput="haijinChange(this)" onpaste="haijinChange(this)"/>&nbsp;元/月&nbsp;&nbsp;&nbsp;
	                        		服务人员法定假日加班费，是日劳务费：<input  type="text" name="chargeTimes" style="width:10%" class="form-control" id="s_agreement_chargeTimes"/>&nbsp;倍
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>服务人员信息费：</p>
								 <input  type="text" name="personManageMoney" id="s_agreement_personManageMoney" onkeyup="countPayAmount()" class="form-control"/>&nbsp;元/月&nbsp;&nbsp;&nbsp;
	                        	客户信息费（一次）：<input  type="text" name="customerManageMoney" id="s_agreement_customerManageMoney" onkeyup="countPayAmount()" class="form-control" style="width:20%"/>&nbsp;元
								 </label>
								</div>
							</div>
							<div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>本次支付合计：</p>
									 <input  type="text" name="payment" id="s_agreement_payment" class="form-control" onkeyup="changeAllpay()" onchange="changeAllpay()"/>&nbsp;元&nbsp;&nbsp;&nbsp;
								 </label>
								</div>
							</div>
	                        <hr>
	                        <div class="row" > 
								<div class="form-group col-xs-12">
										<label class="has-feedback"><span style="color:red!important">*</span><p> 劳务费支付方式：</p></label>	
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
	                        <div class="row" id="oldDiv">
							   <div class="col-xs-6">
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付方式：</p></label>
								 <div class="radio">
		                       	    	<label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod1" value="1" />&nbsp;月
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
	                        <div class="row" id="newDiv">
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
		                       	    	<!-- 2018 0717 add xyp -->
		                       	    	<!-- <label class="radio-inline">
		                       	    		<input  type="radio" name="advancePeriod2" value="6"/>&nbsp;陪护服务
		                       	    	</label> --> 
		                       	  </div>
								</div>
								
							</div>
							
							<div class="row" id="oldDateDiv"> 
							   <div class="form-group col-xs-6" >
								 <label class="has-feedback"><span style="color:red!important">*</span><p>甲方预支付时间：</p>
								 	<input type="text" class="Wdate form-control" id="s_agreement_agreementPayDate"  name="agreementPayDate1"
											onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
								 </label>
								</div>
								<div class="form-group col-xs-6" id="payDateDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>预支付日期：</p>
										<input type="text"  id="s_agreement_remindDay" name="remindDay" class="form-control">&nbsp;日前支付
									 </label>
								</div>
								<div class="form-group col-xs-6" id="zhifuRemarkDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>支付时间说明：</p>
										<input type="text"  id="s_agreement_zhifuRemark" name="zhifuRemark" class="form-control">
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
							
							<div class="row" id="newDateDiv"> 
							   <div class="form-group col-xs-6" >
								 <label class="has-feedback"><span style="color:red!important">*</span><p>预付截止日期：</p>
								 	<input type="text" class="Wdate form-control" id="s_agreement_agreementPayDate_new"  name="agreementPayDate2"
											onFocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly">
								 </label>
								</div>
								<div class="form-group col-xs-6" id="allPayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>费用总计：</p>
										 <input  type="text" name="allPay" readonly="readonly" id="s_agreement_allPay" class="form-control" onkeyup="changePayment()" onchange="changePayment()"/>&nbsp;元
									 </label>
								</div>
								<div class="form-group col-xs-6" id="otherWayDiv">
									<label class="has-feedback"><span style="color:red!important">*</span><p>其他支付方式：</p>
										 <%--<input  type="text" name="otherWay" id="s_agreement_otherWay" class="form-control"/>--%>
										<select name="otherWay" id="s_agreement_otherWay" class="form-control">
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
								 <input  type="text" name="otherMethods" class="form-control" id="s_agreement_otherMethods" style="width:300px"/>
								 </label>
								</div>
							</div>
							
							<div class="row" id="phfw_msg_div1" style="display: none;"> 
								<div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>医院陪护类合同, 甲方选择的服务项目收费标准, 乙方指定收款账户开户名称, 乙方指定收款账户银行账号, 乙方指定收款账户开户地址4项为必填. </h5>
	                           </div>
							</div>
							
							
							<div class="row" id='remarkZdgDiv'>
							   <div class="form-group col-xs-12">
								 <label class="has-feedback">&nbsp;&nbsp;<p> 钟点工工作时间：</p>
								 <input  type="text" name="remarkZdg" class="form-control" id="s_agreement_remarkZdg" style="width:300px"/>
								 </label>
								</div>
							</div>
	                        <hr>
		                        <!-- 海金保理白条开始 -->
		                        <div class="row" id="hjbl_new_div1" style="display: none;"> 
								   <div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付几个月劳务费(月)：</p>
									 	<input type="text" class="form-control" id="s_prepaidMonths"  name="prepaidMonths" oninput="haijinChange(this)" onpaste="haijinChange(this)">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">预付劳务费总金额(元)：</p>
									 	<input type="text" class="form-control" id="s_prepaidMoney"  name="prepaidMoney" readonly="readonly">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付几个月劳务费(月)：</p>
									 	<input type="text" class="form-control" id="s_instPrepaidMonths"  name="instPrepaidMonths" oninput="haijinChange(this)" onpaste="haijinChange(this)">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">分期支付劳务费总金额(元)：</p>
									 	<input type="text" class="form-control" id="s_instPrepaidMoney"  name="instPrepaidMoney" readonly="readonly">
									 </label>
									</div>
									<div class="form-group col-xs-6" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">几日内支付分期手续费(日)：</p>
									 	<input type="text" class="form-control" id="s_limitDays"  name="limitDays">
									 </label>
									</div>
								</div>
								<hr id="hjbl_new_div1_hr" style="display: none;">
								<div class="row" id="hjbl_new_div2" style="display: none;"> 
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户姓名：</p>
									 	<input type="text" class="form-control" id="s_accountName"  name="accountName">
									 </label>
									</div>
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">甲方(客户)账户开户行：</p>
									 	<select name="accountBank" id="s_accountBank"  class="form-control order-card-one">
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
									 	<input type="text" class="form-control" id="s_accountNum"  name="accountNum">
									 </label>
									</div>
									
									<div class="form-group col-xs-12" >
									 <label class="has-feedback"><span style="color:red!important">*</span><p style="width: 160px;">银行预留手机号：</p>
									 	<input type="text" class="form-control" id="s_accountMobile" name="accountMobile">
									 	<input type="hidden" class="form-control" id="s_authCode_flag"  name="s_authCode_flag">
									 	<button class="btn btn-sm btn-primary" type="button" id="submitAuth">提交验证</button> 
									 </label>
									</div>
								</div>
	                         	<hr id="hjbl_new_div2_hr" style="display: none;">
		                        <!-- 海金保理白条结束 -->
		                        
		                       
	                        <div class="row">
							   <div class="form-group col-xs-12">
								 <label class="has-feedback form-label">&nbsp;&nbsp;<p> 其他约定：</p>
								 <textarea rows="3" name="otherMatters" id="s_agreement_otherMatters" class= "form-control form-textarea" ></textarea>
								 </label>
								</div>
							</div>
                      </div> 
                     </div>
                  </div>
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">关闭</button>
					<button type="submit" class="btn btn-sm btn-primary" id="cpeSubmit">保存</button> 
				</div>
			</form>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="${ctx }/js/agreement/order_cpe_add.js"></script>
</html>