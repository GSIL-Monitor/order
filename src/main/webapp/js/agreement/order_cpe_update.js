/**
 * @author zhangshun
 */
$(function(){ 
		var ctx=$("#ctx").val();
		//加载服务站
		//queryServiceStation_update();
		//验证甲方身份证(客户)
		$("#chack_cardNum_a").on("click",function(){
			checkIdCard_partyA();
		})
		//修改甲方证件信息
		$("#u_agreement_partyA,#uorder_agree_add_id_cardNum").on("input",function(){
			$("#chack_cardNum_a_message").text("").data("value","").hide();
			$("#chack_cardNum_a").show();
			$("#uorder_agree_add_id_cardNum").parent().nextAll("small").css("left","353px");
		})
		//验证甲方身份证(客户)
		$("#chack_cardNum_c").on("click",function(){
			checkIdCard_partyC();
		})
		//修改丙方证件信息
		$("#u_agreement_partyC,#uorder_agree_add_id_cardNumC").on("input",function(){
			$("#chack_cardNum_c_message").text("").data("value","").hide();
			$("#chack_cardNum_c").show();
			$("#uorder_agree_add_id_cardNumC").parent().nextAll("small").css("left","353px");
		})
		
		
		$("#chack_service_cardNum").on("click",function(){
		   checkIdCard_party_forservice();
	    }) 
		
		
		/**新增合同页查询服务站**/
		/*function queryServiceStation_update(){
			var orderId = $("#checkedOrderId").val();
			$.ajax({
				url:ctx+"/agreement/queryServiceStation",
				type:"POST",
				datetype:"json",
				async : false,
				data:{"orderId":orderId},
				success : function(data){
					if (data.msg == "00") {
						var deptList = data.list||[];
						deptList.unshift({"DEPTID":"","DEPTNAME":"--请选择--","DEPTADDR":""})
						$("#serviceGarageId").nextAll("div").remove();
						checkInput($("#u_agreement_serviceGarage"),function(key,o,thiz){
							selectServiceStation(key,o,thiz);
						},deptList);
						$("#serviceGarageId").val($("#serviceGarageId").data("serviceGarageId")||"");
					}
				}
			})
		}*/
		/**选择服务站下拉框**/
		/*function selectServiceStation(key,o,thiz){
			var deptId = o.DEPTID;
			var deptName = o.DEPTNAME;
			var deptAddr = o.DEPTADDR;
			$("#serviceGarageId").val(deptId||"");
			$("#u_agreement_serviceGarage").val(deptId?deptName:"").prop("title",(deptId?deptName:""));
			$("#u_agreement_platformAddress").val(deptId?deptAddr:"");
			//手动验证
			var bv = $("#update_agreementForm").data("bootstrapValidator");
			bv.updateStatus("serviceGarage", "NOT_VALIDATED").validateField("serviceGarage");
			bv.updateStatus("platformAddress", "NOT_VALIDATED").validateField("platformAddress");
			//调整验证提示显示位置
			$("#u_agreement_serviceGarage").parent().nextAll("small").css("left","353px");
		} */
		
		/**验证甲方身份证**/
		function checkIdCard_partyA(){
			//手动验证
			var bv = $('#update_agreementForm').data("bootstrapValidator");
			bv.updateStatus("partyA", "NOT_VALIDATED").validateField("partyA");
			bv.updateStatus("cardone", "NOT_VALIDATED").validateField("cardone");
			var partyA_flag = bv.isValidField("partyA");
			var cardone_flag = bv.isValidField("cardone");
			$("#uorder_agree_add_id_cardNum").parent().nextAll("small").css("left","353px");
			//调整验证提示显示位置
			if(partyA_flag && cardone_flag){
				//验证通过
				var name = $("#u_agreement_partyA").val();
				var cardNum = $("#uorder_agree_add_id_cardNum").val();
				var result = checkIdCard(name,cardNum);
				if(result){
					var code = result.code;
					var message = "",color="red";
					if(code == 0){
						message = "不存在"
					}else if(code == 1){
						color = "green";
						message = "匹配"
					}else if(code == 2){
						message = "不匹配"
					}else if(code == -1){
						message = "错误"
					}else{
						message = "查询异常"							
					}
					$("#chack_cardNum_a_message").text(message).data("value",code).css("color",color).show();
					$("#chack_cardNum_a").hide();
				}else{
					$.alert({millis:5000,text:"系统忙,请重试!"});return;
				}
			}else{
				$.alert({millis:5000,text:"姓名或身份证号不正确！"});return;
			}
		}
		
		/**验证丙方身份证**/
		function checkIdCard_partyC(){
			//手动验证
			var bv = $('#update_agreementForm').data("bootstrapValidator");
			bv.updateStatus("partyC", "NOT_VALIDATED").validateField("partyC");
			bv.updateStatus("cardfive", "NOT_VALIDATED").validateField("cardfive");
			var partyC_flag = bv.isValidField("partyC");
			var cardfive_flag = bv.isValidField("cardfive");
			$("#uorder_agree_add_id_cardNumC").parent().nextAll("small").css("left","353px");
			if(partyC_flag && cardfive_flag){
				//验证通过
				var name = $("#u_agreement_partyC").val();
				var cardNum = $("#uorder_agree_add_id_cardNumC").val();
				var result = checkIdCard(name,cardNum);
				if(result){
					var code = result.code;
					var message = "",color="red";
					if(code == 0){
						message = "不存在"
					}else if(code == 1){
						color = "green";
						message = "匹配"
					}else if(code == 2){
						message = "不匹配"
					}else if(code == -1){
						message = "错误"
					}else{
						message = "查询异常"							
					}
					$("#chack_cardNum_c_message").text(message).data("value",code).css("color",color).show();
					$("#chack_cardNum_c").hide();
				}else{
					$.alert({millis:5000,text:"系统忙,请重试!"});return;
				}
			}else{
				$.alert({millis:5000,text:"姓名或身份证号不正确！"});return;
			}
		}
		
		
		
		
		/**验证陪护服务合同身份证信息**/
		function checkIdCard_party_forservice(){
			//手动验证
			var bv = $("#update_agreementForm").data("bootstrapValidator");
			bv.updateStatus("consumersName", "NOT_VALIDATED").validateField("consumersName");
			bv.updateStatus("consumersCard", "NOT_VALIDATED").validateField("consumersCard");
			var consumersName_flag = bv.isValidField("consumersName");
			var consumersCard_flag = bv.isValidField("consumersCard");
			$("#s_consumersCard").parent().nextAll("small").css("left","353px");
			//调整验证提示显示位置
			if(consumersName_flag && consumersCard_flag){
				//验证通过
				var name = $("#s_consumersName").val();
				var cardNum = $("#s_consumersCard").val();
				var result = checkIdCard(name,cardNum);
				if(result){
					var code = result.code;
					var message = "",color="red";
					if(code == 0){
						message = "不存在"
					}else if(code == 1){
						color = "green";
						message = "匹配"
					}else if(code == 2){
						message = "不匹配"
					}else if(code == -1){
						message = "错误"
					}else{
						message = "查询异常"							
					}
					$("#chack_service_cardNum_message").text(message).data("value",code).css("color",color).show();
					$("#chack_service_cardNum").hide();
				}else{
					$.alert({millis:5000,text:"系统忙,请重试!"});return;
				}
			}else{
				$.alert({millis:5000,text:"姓名或身份证号不正确！"});return;
			}
		}
		
		
		
		/**验证身份证**/
		function checkIdCard(name,cardNum){
			var result = "";
			$.ajax({
				url:ctx+"/agreement/checkIdCard",
				type:"POST",
				datetype:"json",
				async : false,
				data:{"name":name,"cardNum":cardNum},
				success : function(data){
					result = data.result||"";
				}
			})
			return result;
		}
		
		/**下拉框**/
		 function checkInput(thiz,callback,data){
		    	if($.browser.msie) return;
				var $div = $("<div style='position:absolute;z-index:2000'></div>").width(thiz.outerWidth());
				var $ul = $("<ul style='max-height:300px;overflow:auto;border:#ccc 1px solid;padding:0px;';></ul>");
				var $ops = data;
					$.each($ops,function(i,o){
						$("<li></li>").html(o.DEPTNAME)
										.appendTo($ul)
										.height(thiz.outerHeight()-5)
										.css({"lineHeight": (thiz.outerHeight()-5) + "px","margin":"0px","paddingLeft":"5px","background":"#fefefe","borderBottom":" #eee 1px solid"})
										.hover(function(){
											$(this).css("background","#eaeaea");
										},function(){
											if(o.DEPTNAME != thiz.val()){
												$(this).css("background","#fefefe");
											}
										}).mousedown(function(){
											if(o.DEPTNAME != thiz.val()){
												thiz.val(o.DEPTNAME);
											}
											$div.slideUp(10);
											callback?callback(o.DEPTID,o,thiz[0]):null;
										}).data("data-val",o.DEPTNAME);
					});
				var $lis = $ul.find("li");
				var $input = $("<input type='text' style='width:100%;'>")
							.height(thiz.outerHeight())
							.bind('input propertychange', function() {
										var val = $(this).val();
										$lis.each(function(i,o){
											var $o = $(o);
											if($o.html().indexOf(val) != -1){
												$o.show();
											}else{
												$o.hide();
											} 
										});
							});
				$div.append($input).append($ul);
				$div.insertAfter(thiz).offset(thiz.offset()).hide();
				thiz.mousedown(function(){
					$div.show().offset(thiz.offset());
					$lis.each(function(i,o){
						if(thiz.val() == $(o).data("data-val")){
							$(o).css("background","#eaeaea");
						}else{
							$(o).css("background","#fefefe");
						}
						$(o).show();
					});
					$div.parents("body").mousedown(function(){
						$div.slideUp(10);
						$input.val("");
					});
					$div.mousedown(function(){
						return false;
					});
					$input[0].focus();
					return false;
				});
			} 
		 
		 $("#submitAuth").on("click",function(){
				submitAuth();
			});
	})
		/**合并甲方服务场所-使用字段**/
		function getUpdateServiceAddress(){
			 var addr = "";
			 var province_a = $("#province_a").children("option:selected").text(); //省份
			 var city_a = $("#city_a").children("option:selected").text(); //城市
			 var county_a = $("#county_a").children("option:selected").text(); //县区
			 var street_a = $("#street_a").val(); //街道
			 var residence_community_a = $("#residence_community_a").val(); //小区
			 var building_No_a = $("#building_No_a").val(); //号楼
			 var unit_a = $("#unit_a").val(); //单元
			 var room_a = $("#room_a").val(); //室
			 addr = province_a+city_a+county_a+street_a+"街道";
			 addr += (residence_community_a?residence_community_a+"小区":"");
			 addr += (building_No_a?building_No_a+"号楼":"");
			 addr += (unit_a?unit_a+"单元":"");
			 addr += (room_a?room_a+"室":"");
			 return addr.replace(/\s+/g,"");
		}
		/**合并甲方服务场所-回显字段**/
		function getUpdateServiceAddressEcho(){
			 var str = "",serviceAddressEcho = {};
			 serviceAddressEcho.province_a = $("#province_a").children("option:selected").text()||""; //省份
			 serviceAddressEcho.city_a = $("#city_a").children("option:selected").text()||""; //城市
			 serviceAddressEcho.county_a = $("#county_a").children("option:selected").text()||""; //县区
			 serviceAddressEcho.street_a = $("#street_a").val()||""; //街道
			 serviceAddressEcho.residence_community_a = $("#residence_community_a").val()||""; //小区
			 serviceAddressEcho.building_No_a = $("#building_No_a").val()||""; //号楼
			 serviceAddressEcho.unit_a = $("#unit_a").val()||""; //单元
			 serviceAddressEcho.room_a = $("#room_a").val()||""; //室
			 str = JSON.stringify(serviceAddressEcho);
			 return str.replace(/\s+/g,"");
		}
		/**合并甲方地址-使用字段**/
		function getUpdateCustomerAddress(){
			 var addr = "";
			 var province_ca = $("#province_ca").children("option:selected").text(); //省份
			 var city_ca = $("#city_ca").children("option:selected").text(); //城市
			 var county_ca = $("#county_ca").children("option:selected").text(); //县区
			 var street_ca = $("#street_ca").val(); //街道
			 var residence_community_ca = $("#residence_community_ca").val(); //小区
			 var building_No_ca = $("#building_No_ca").val(); //号楼
			 var unit_ca = $("#unit_ca").val(); //单元
			 var room_ca = $("#room_ca").val(); //室
			 addr = province_ca+city_ca+county_ca+street_ca+"街道";
			 addr += (residence_community_ca?residence_community_ca+"小区":"");
			 addr += (building_No_ca?building_No_ca+"号楼":"");
			 addr += (unit_ca?unit_ca+"单元":"");
			 addr += (room_ca?room_ca+"室":"");
			 return addr.replace(/\s+/g,"");
		}
		/**合并甲方地址-回显字段**/
		function getUpdateCustomerAddressEcho(){
			 var str = "",serviceUpdateressEcho = {};
			 serviceUpdateressEcho.province_ca = $("#province_ca").children("option:selected").text()||""; //省份
			 serviceUpdateressEcho.city_ca = $("#city_ca").children("option:selected").text()||""; //城市
			 serviceUpdateressEcho.county_ca = $("#county_ca").children("option:selected").text()||""; //县区
			 serviceUpdateressEcho.street_ca = $("#street_ca").val()||""; //街道
			 serviceUpdateressEcho.residence_community_ca = $("#residence_community_ca").val()||""; //小区
			 serviceUpdateressEcho.building_No_ca = $("#building_No_ca").val()||""; //号楼
			 serviceUpdateressEcho.unit_ca = $("#unit_ca").val()||""; //单元
			 serviceUpdateressEcho.room_ca = $("#room_ca").val()||""; //室
			 str = JSON.stringify(serviceUpdateressEcho);
			 return str.replace(/\s+/g,"");
		}

//20180806《配合海金的合同填报规则调整》需求单实现：[费用总计] 填写并回填 [本次费用合计]
function changePayment(){
	var allpay = $("#u_agreement_allPay").val();
	$("#u_agreement_payment").val(allpay);
	var bv = $("#update_agreementForm").data("bootstrapValidator");
	bv.updateStatus("allPay", "NOT_VALIDATED").validateField("allPay");
	bv.updateStatus("payment", "NOT_VALIDATED").validateField("payment");
}
function changeAllpay(){
	var payment = $("#u_agreement_payment").val();
	$("#u_agreement_allPay").val(payment);
	var bv = $("#update_agreementForm").data("bootstrapValidator");
	bv.updateStatus("allPay", "NOT_VALIDATED").validateField("allPay");
	bv.updateStatus("payment", "NOT_VALIDATED").validateField("payment");
}

//修改四要素验证
function submitAuth(){
	var userMoble=$("#u_accountMobile").val();
	if(userMoble==null || userMoble ==""){
		$.alert({millis:5000,text:"请输入预留电话号码!"});return;
	}
	var customName=$("#u_accountName").val();
	if(customName==null || customName ==""){
		$.alert({millis:5000,text:"请输入甲方(客户)账户姓名!"});return;
	}
	var bankName=$("#u_accountBank").val();
	if(bankName == 1){
		$.alert({millis:5000,text:"请选择甲方(客户)账户开户行"});return;
	}
	var bankAccountNum=$("#u_accountNum").val();
	if(bankAccountNum==null || bankAccountNum ==""){
		$.alert({millis:5000,text:"请输入甲方(客户)账户号码!"});return;
	}
	var customIdCard=$("#uorder_agree_add_id_cardNum").val();
	if(customIdCard==null || customIdCard ==""){
		$.alert({millis:5000,text:"请输入身份证!"});return;
	}
	
	$.ajax({
		url : ctx+"/agreement/submitAuth",
		data :{
			userMoble:userMoble,
			customName:customName,
			bankName:bankName,
			bankAccountNum:bankAccountNum,
			customIdCard:customIdCard,
			applyType:1
		},
		type : "POST",
		beforeSend:function (){  
			$("#ucpeSubmit").attr("disabled",true);
			$("#submitAuth").attr("disabled",true);
			$.alert({millis:5000,text:"信息正在校验，请耐心等待!"});
        }, 
		success : function(data) {
			if(data.code == 00){
				$("#u_authCode_flag").val("1");
				$("#ucpeSubmit").removeAttr("disabled");
				$("#submitAuth").attr("disabled",true);
				$.alert({millis:5000,text:data.msg || "验证失败!"});
			}else if(data.code == 02){
				$("#submitAuth").removeAttr("disabled");
				$.alert({millis:5000,text:"请再次点击验证!"});return;
			}else{
				$("#submitAuth").removeAttr("disabled");
				$.alert({millis:5000,text:data.msg || "验证失败!"});return;
			}
		}
	});
}