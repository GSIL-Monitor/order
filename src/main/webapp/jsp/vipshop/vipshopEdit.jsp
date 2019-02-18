<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" />
 	<script src="${ctx}/js/fileinput.js" type="text/javascript" charset="utf-8"></script>
 	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
 	<script type="text/javascript" src="${ctx}/js/vipshop.js"></script>
 	<style>
 	.form-inline .input-group > .file-caption {
    width: 100%;
    height: 33px; 
	}
	.form-inline .input-group .input-group-btn {
	    width: 1%;
	    height:30px;
	} 
	</style>
</head>
<body>
	<div class="modal fade">  
	    <div class="modal-dialog">
	    	<div class="modal-content">  
	    		<div class="modal-header" id="insertTitle">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('vipshopEdit')">×</button>  
			        <h4 class="modal-title">唯品会退费>>新增退费</h4>
		        </div>
		        <div class="modal-header" id="editTitle">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('vipshopEdit')">×</button>  
			        <h4 class="modal-title">唯品会退费>>修改退费</h4>
		        </div>
	      <form class="form-inline" id="vphUpdateForm">
			      <div class="modal-body" >
			      	<div class="modal-content-basic">
	                <header><h4>订单信息</h4></header>
	               <div class="info-select clearfix" >
	                 <div class="row">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>订单编号：</p>
	                            <span id="vph_OrderCode"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>订单类型：</p>
	                            <span id="vph_OrderType"></span>
	                        </label>
	                    </div>
	                    
	                 </div>
	                <div class="row">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>订单开始时间：</p>
	                            <span id="vph_startTime"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>订单结束时间：</p>
	                            <span id="vph_endTime"></span>
	                        </label>
	                    </div>
	                 </div>
	                 
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>白条签约时间：</p>
	                            <span id="vph_payTime"></span>
	                        </label>
	                    </div>
	                 </div>
	                 </div>
	                <header><h4>退费信息</h4></header>
	               <div class="info-select clearfix" >
	                <input type="hidden"  id="vph_vphId"  class="form-control"/>
	                <input type="hidden"  id="vph_accountId"  class="form-control"/>
	                <input type="hidden"  id="vph_maxMoney_hidden"  class="form-control"/>
	                <div class="row">
	                	<div class="form-group col-xs-12" >
	                        <label>
	                            <p><span style="color:red">*</span>退费对象：</p>
	                            <select class="form-control" name="refundObject" id="vph_refuncdObject" >
	                            		 <option value="">...请选择...</option>
	                                     <option value="1">唯品会</option>
	                                      <!-- 结算中心要求打开客户下拉选，权限只给他们 -->
	                                      <option id="custOpt" value="2">客户</option> 
	                             </select>
	                             <auth:menu menuId="1206">
	                             	<button type="button" id="authBtn"  ></button>  
                                 </auth:menu>
	                             </select>
	                        </label>
	                    </div>
	               </div>
	               <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><span style="color:red">*</span>退费金额（元）：</p>
	                            <input id="vph_vphFee" name="vphFee" class= "form-control" style="ime-mode:Disabled"
                                    maxlength="9" onkeyup="value=value.replace(/[^\d{1,}\.\d{1,}|\d{1,}]/g,'')"></input>
	                            &nbsp;&nbsp;<span id="vph_maxMoneyDetail"></span> <span style="color:red;" id="vph_Warn">退款总额不能超过白条最大退款金额，请重新填写！</span> 
	                        </label>
	                    </div>
	                </div>
	                <div id="vph_bank_info">
	                <hr>
	                 <div class="row">
		                <div class="form-group col-xs-6">
		                        <label>
		                            <p><span style="color:red">*</span>银行：</p>
		                            <select class="form-control" name="bankSupportId" id="vph_bankSupportId" >
		                           			 <option value="">...请选择...</option>
		                                     <option value="1">工商银行</option>
		                                     <option value="2">建设银行</option>
		                                     <option value="3">农业银行</option>
		                                     <option value="4">中国银行</option>
		                                     <option value="5">兴业银行</option>
		                                     <option value="6">民生银行</option>
		                                     <option value="7">招商银行</option>
		                                     <option value="8">交通银行</option>
		                             </select>
		                        </label>
		                    </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="vph_bankUserName"/>
                               </label>
                           </div>
                    </div>
                      <div class="row">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p><span style="color:red">*</span>开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="vph_bankProvinceCode"  onclick="setSelCity('vph_bankProvinceCode','vph_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p><span style="color:red">*</span>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="vph_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="vph_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="vph_bankName"/>
                               </label>
                           </div>
                    </div>
	               </div>
	                <div class="row">
		                <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>取消原因：</p>
		                            <select class="form-control" name="reasonFlag" id="vph_ReasonSelect" >
		                           			 <option value="">...请选择...</option>
		                                     <option value="1" data-quality="客户咨询">客户咨询</option>
		                                     <option value="2" data-quality="不需要服务">不需要服务</option>
		                                     <option value="3" data-quality="已经找到服务">已经找到服务</option>
		                                     <option value="4" data-quality="距离远服务不到">距离远服务不到</option>
		                                     <option value="5" data-quality="提供不了合适人员">提供不了合适人员</option>
		                                     <option value="6" data-quality="觉得信息费太贵">觉得信息费太贵</option>
		                                     <option value="7" data-quality="人员服务人员服务费太高">人员服务人员服务费太高</option>
		                                     <option value="8" data-quality="对管家不认同">对管家不认同</option>
		                                     <option value="9" data-quality="对管家帮不认同">对管家帮不认同</option>
		                                     <option value="10"data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		                    </div>
                    </div>
	                <div class="row" id="vph_reasonDiv">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>&nbsp;&nbsp;原因：</p>
	                            <textarea rows="2"   id="vph_reason" style="height:50px;" class= "form-control form-textarea"></textarea>
	                        </label>
	                    </div>
	                </div>
	                
	                </div>
				</div>
			  </div>
		      <div class="modal-footer">  
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('vipshopEdit')" >取消</button>  
		      	<button type="submit" class="btn btn-sm btn-primary" id="vphUpdateBtn">保存</button>
		      </div>  
		      
	      	</form>
	      	
		  </div> 
	    </div>
	</div>     
</body>
<script type="text/javascript">
	var afterSaleId = '${param.afterSalesId}';
	var orderId = '${param.orderId}';
	var userName = '${param.userName}';
	var userMobile = '${param.userMobile}';
	var flag = '${param.flag}';
	$(function() {
		//加载省份
		setSelProvinceCitys('101',6,'vph_bankProvinceCode');
		//查询唯品会订单信息
		vphOrder();
		//隐藏客户银行信息
		$("#vph_bank_info").hide();
		//查询唯品会售后单信息
		vphAfterSales();
		//查询唯品会白条最大退款金额
		vphMaxMoney();
		//标题展示信息
		if(flag != null && flag == 1){
			$("#insertTitle").show();
			$("#editTitle").hide();
		}else{
			$("#insertTitle").hide();
			$("#editTitle").show();
		}
		//白条最大退款金额提示隐藏
		 $("#vph_Warn").hide();
		//权限按钮，控制下拉选
		 if($("#authBtn").length > 0){  
			$("#custOpt").show();
		 } else {
			$("#custOpt").hide();
		 }
	});
	
	//查询唯品会订单信息
	function vphOrder(){
		$.ajax({
			url: "${ctx}/afterSales/queryVPHOrder?curPage=1&pageCount=1",
			data:{
				id:orderId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				var html = "";
				if (data.msg == "00") {
					 $.each(data.list,function(i,v){
						 $("#vph_OrderCode").html(v.orderCode);
						 $("#vph_OrderType").html(v.typeText);
						 $("#vph_vphId").val(v.vphOrderId);
						 $("#vph_accountId").val(v.accountId);
						 $("#vph_startTime").html(v.startTime.substr(0,10));
						 $("#vph_endTime").html(v.endTime.substr(0,10));
						 $("#vph_payTime").html(v.payTime == undefined ?"-": v.payTime.substr(0,10));//TODO:白条缴费日期 feeToDate
					});					
				}
			}
		});
	}
	//查询唯品会售后单信息和订单信息
	function vphAfterSales(){
		if(afterSaleId != null && afterSaleId != ""){
			$.ajax({
				url: "${ctx}/afterSales/queryVPHSales",
				data:{
					id:afterSaleId
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					var html = "";
					if (data.msg == "00") {
						 $.each(data.list,function(i,v){
							 $("#vph_vphFee").val(v.vphFee);
							 //$("#vph_vphId").val(v.vphId);
							 //$("#vph_accountId").val(v.accountId);
							 if(v.reasonFlag == 10 ){
								 $("#vph_reason").val(v.reason);	
								 $("#vph_reasonDiv").show();
							 }else{
								 $("#vph_reasonDiv").hide();
							 }
							 $("#vph_ReasonSelect").val(v.reasonFlag);
							 $("#vph_refuncdObject").val(v.refundObject);
							 if(v.refundObject == 2 ){
								 $("#vph_bank_info").show();
							 }else{
								 $("#vph_bank_info").hide();
							 }
							 //银行卡信息
							 if(v.bankSupportId !=null && v.bankSupportId != ""){
							 	$("#vph_bankSupportId").val(v.bankSupportId);	
							 }else{
								 $("#vph_bankSupportId").val("");	
							 }
							 $("#vph_bankUserName").val(v.bankUserName);
				 			 $("#vph_bankCard").val(v.bankCard);	
							 $("#vph_bankName").val(v.bankName);	
							 //省、市
							 if(v.bankCityCode != null && v.bankCityCode != ""){
								 var ucode = v.bankCityCode.substr(0,6);
					 			 $("#vph_bankProvinceCode option[value='" +ucode+"']").attr("selected","selected");
					 			 setSelProvinceCitys(v.bankCityCode,9,"vph_bankCityCode");
					 			 $("#vph_bankCityCode option[value='" +v.bankCityCode+"']").attr("selected","selected");
							 }else{
								 $("#vph_bankCityCode").val("");
							 }
							 
						});					
					}
				}
			});
		}
	}
 	
	//保存操作
	function updateVph(){
		 var reason = $.trim($("#vph_reason").val());
		 var reasonSelected  = $("#vph_ReasonSelect").find("option:selected").html();
		 var bankMainName = $("#vph_bankSupportId").find("option:selected").html();
		 if(reasonSelected != "其它原因"){
			  if(reasonSelected != "...请选择..."){
				  reason = reasonSelected ;
			  }else{
				  reason ="";
			  }
			}
		 var data = $("#vphUpdateForm").serialize();
		 data += "&orderId="+orderId+"&custName="+userName+"&custMobile="+userMobile+"&reason="+reason+"&bankMainName="+bankMainName;
		 var url = "";
		 if(flag != null && flag == 1){ //新增
			 url = "${ctx}/afterSales/insertVphAfterSales";
			 updateVphSale(url,data);
		 }else{//修改
			 url = "${ctx}/afterSales/updateVphAfterSales";
			 data += "&id="+afterSaleId;
			 updateVphSale(url,data);
		 }
		$("#vphUpdateBtn").removeAttr("disabled");
	}
	//保存售后单
	function updateVphSale(url,data){
		 $.ajax({
			url: url,
			data:data,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					 $.alert({millis:3000,top:'30%',text:"操作成功！"});
					 queryVPHSalesByLike(0,10);
					 closeModule('vipshopList');
					 closeModule('vipshopEdit');
				}else if(data.msg =="01"){
					 $.alert({millis:3000,top:'30%',text:"操作失败！"});
					 closeModule('vipshopEdit');
					 queryVPHSalesByLike(0,10);
				}else{
					$.alert({millis:2000,text:data.msg});
					closeModule('vipshopEdit');
				}
			}
		}); 
	}
	
 	//进行验证的方法
	$('#vphUpdateForm').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled,:hidden',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
	    	vphFee : {
            	validators: {
            		notEmpty: {
	                    message: '必填！'
	                },
            		regexp: {
            			regexp: /^(([1-9]\d*)|([0-9]\d*\.\d?[1-9]{1}))$/,
	        			message: '请填写正确费用！小数点后两位有效！'
	                }
            	}
            }, 
            bankUserName: {
	            message: '持卡人无效！',
	            validators: {
	            	notEmpty: {
	                    message: '必填！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '持卡人名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^(?!_)(?!.*?_$)[a-zA-Z_\u4e00-\u9fa5]+$/,
	                    message: '持卡人名称为中文和英文！'
	                }
	            }
	        },
	        bankName: {
	            message: '开户行无效！',
	            validators: {
	            	notEmpty: {
	                    message: '必填！'
	                },
	                stringLength: {
	                    min: 1,
	                    max: 100,
	                    message: '开户行名称必须为1-100个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4E00-\u9FA5]{1,100}$/,
	                    message: '开户行名称为中文！'
	                }
	            }
	        },
	        bankCard : {
            	validators: {
            		notEmpty: {
	                    message: '必填！'
	                },
            		regexp: {
	                    regexp: /^(\d{16}|\d{18}|\d{19}|\d{20})$/,
	                    message: '银行卡号错误！'
	                }
            	}
            },
            bankCityCode : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            	}
            },
            bankSupportId : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            	}
            },
            refundObject : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            	}
            },
            reasonFlag : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            	}
            },
            reason : {
            	validators: {
            		notEmpty: {
	                    message: '必选！'
	                },
            		stringLength: {
	                    min: 0,
	                    max: 200,
	                    message: '原因不超过200字！'
	                },
            	}
            },
            
	    }
	}).on('success.form.bv', function(e) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    //保存售后单
	    updateVph();
	}); 
 	
 	//判断选择唯品会还是客户
 	$("#vph_refuncdObject").change(function(){ 
 		var bankInfo = $(this).find("option:selected").val();
 		if(bankInfo != null && bankInfo == 1){
 			$("#vph_bank_info").hide();
 		}else{
 			$("#vph_bank_info").show();
 		}
 	});
 	//查询唯品会白条最大退款金额
	function vphMaxMoney(){
 		var vphId = $("#vph_vphId").val();
 		var accountId = $("#vph_accountId").val();
			$.ajax({
				url: "${ctx}/afterSales/queryVPHMaxMoney",
				data:{
					vphId:vphId,
					accountPayId : accountId
				},
				type:"post",
				dataType:"json",
				async:false,
				success:function(data){
					if (data.msg == "00") {
						if(data.money != null && data.money != ""){
						 	$("#vph_maxMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>"+intToDecimal(data.money)+"</span>&nbsp;");
					    	$("#vph_maxMoney_hidden").val(intToDecimal(data.money));
						}else{
							$("#vph_maxMoneyDetail").html("&nbsp;&nbsp;&nbsp;&nbsp;最大退费金额： <span>0.00</span>&nbsp;");
							$("#vph_maxMoney_hidden").val("0");						
						}
					}
				}
			});
		}
 	//白条退款金额输入判断
	$("#vph_vphFee").on("keyup",function(){ 
		var maxRefund = $("#vph_maxMoney_hidden").val();//最大白条退费金额
		var maxMoney = $(this).val();//输入的最大白条退款金额
		if(!isNaN(maxMoney) && maxMoney != ""){
			if(maxRefund != null && maxRefund != ""  && maxMoney*1 > maxRefund*1 ){
				 $("#vph_Warn").show();
				 $("#vphUpdateBtn").attr("disabled","disabled");
				 return ;
			}else{
				 $("#vph_Warn").hide();
				 $("#vphUpdateBtn").removeAttr("disabled");
				 return ;
			}
		}else{
			maxMoney = "0" ;
		}
		
	});
	//其他原因显示输入框
	$("#vph_ReasonSelect").change(function(){
		var s1=$(this).children('option:selected').val();
		if(s1 != null && s1 != "" && s1 == 10){
			//显示售后原因输入框
			$("#vph_reasonDiv").show();
		}else{
			//隐藏售后原因输入框
			$("#vph_reasonDiv").hide();
		}
	});
	
</script>
</html>

