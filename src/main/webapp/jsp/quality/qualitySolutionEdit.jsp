<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<link type="text/css" href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" />
 	<script src="${ctx}/js/fileinput.js" type="text/javascript" charset="utf-8"></script>
 	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<div class="modal fade">  
	    <div class="modal-dialog">
	    	<div class="modal-content">  
	    		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('qualitySolutionEdit')">×</button>  
			        <h4 class="modal-title">修改售后>>修改解决方案订单退费</h4>
		        </div>
	      <form class="form-inline" id="uqualitySolutionForm">
	      		<input type="hidden" id="usolutionOrderStatus"/>
	      		<input type="hidden" id="usolutionPayStatus"/>
	      		<input type="hidden" id="usolution_afterSaleId"/>
	      		<input type="hidden" id="usolution_baitiaoMoney"/>
			      <div class="modal-body" >
			      	<div class="modal-content-basic">
			      	 <header><h4>方案信息</h4></header>
		      	<div class="info-select clearfix" >
		      	<div class="row">
						<div class="form-group col-xs-12">
								<span>解决方案编号：</span>
								<lable id="usolution_solutionCode"></lable>
							</div>
						</div>
		      		     <div class="row">
							<div class="form-group col-xs-6">
								<span>创建时间：</span>
								<lable id="usolution_createTime"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>订单金额：</span>
								<lable id="usolution_orderPrice"></lable>元
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>客户：</span>
								<lable id="usolution_payerName"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>客户电话：</span>
								<lable id="usolution_payerMobile"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>管家：</span>
								<lable id="usolution_managerName"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>管家电话：</span>
								<lable id="usolution_managerPhone"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>状态：</span>
								<lable id="usolution_orderStatus"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>代扣卡号：</span>
								<lable id="usolution_withholdingCard"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>订单来源：</span>
								<lable id="usolution_order_source_id"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>渠道：</span>
								<lable id="usolution_mcode"></lable>
							</div>
						</div>
	                </div>
						
					  <div class="row">
						<div class="col-xs-12">
							<div class="panel-content table-responsive">
								<table class="table table-hover table-striped">
									<thead>
										<tr align="center">
											<th width="5%">序号</th>
											<th width="15%" align="center">项目</th>
											<th width="10%" align="center">数量</th>
											<th width="10%" align="center">剩余数量</th>
											<th width="10%" align="center">每次配送数量</th>
											<th width="25%" align="center">服务/配送频次</th>
											<th width="25%" align="center">首次/配送时间</th>
										</tr>
									</thead>
									<tbody id="uorder_solutionCombos">

									</tbody>
								</table>
							</div>
						</div>
					</div>
	                <header><h4>售后信息</h4></header>
	               <div class="info-select clearfix" >
	               <div class="row">
	               		<div class="form-group col-xs-12">
	                        <label>
	                            <p><span style="color:red">*</span>售后类型：</p>
	                            <input class="form-control"  id="usolution_afterSalesKind" readonly="readonly" />
	                        </label>
	                    </div>
	               </div>
	               <div class="row">
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="usolution_ApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="usolution_ApproveBySelect" name="approveBy" class="form-control" >
									<option value="">...请选择...</option>
				  				</select>
	                     	</label>
                    	</div>
					</div>
	               <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>退费总额（元）：</p>
	                            <span id="usolutionReturnTotal" name="refundTotal"></span>&nbsp;&nbsp;<span id="usolutionMoneyDetail"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label><p>退费明细（元）：</p>
	                            <span id="usolutionReturnTotalDetail"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12" id="usolution_bank">
	                        <label>
	                            <p>&nbsp;&nbsp;银行：</p>
	                            <select class="form-control" name="bankSupportId" id="usolution_bankSupportId" >
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
	                </div>
	                <div class="row" id="solution_bankCity">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>&nbsp;&nbsp;开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="usolution_bankProvinceCode"  onclick="setSelCity('usolution_bankProvinceCode','usolution_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="usolution_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="solution_bankCN">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>&nbsp;&nbsp;银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="usolution_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="usolution_bankName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row" id="solution_bankUn">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>&nbsp;&nbsp;持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="usolution_bankUserName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>&nbsp;&nbsp;原因：</p>
	                            <textarea rows="3"  name="reason" style="height:50px;" id="usolution_reason" class= "form-control form-textarea"></textarea>
	                        </label>
	                    </div>
	                </div>
	                
	                </div>
				</div>
			  </div>
		      <div class="modal-footer">  
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('qualitySolutionEdit')" >取消</button>  
		      	<button type="submit" class="btn btn-sm btn-primary" >保存</button>
		      </div>  
		      
	      	</form>
	      	
		  </div> 
	    </div>
	</div>     
</body>
<script type="text/javascript">
$(function() {
	var orderId = '${param.orderId}';
	var cateType = '${param.cateType}';
	var afterSalesId = '${param.afterSalesId}';
	//加载省份
	setSelProvinceCitys('101',6,'usolution_bankProvinceCode');
  	//查询审核部门
	queryApproveDeptName("usolution_ApproveDeptSelect");
	
	loadSolution(orderId);
    //退款总额显示
    //ushowSolultionTotal(orderId);
    //缴费
    //solutionqueryPayFeeById(orderId); 
    //售后信息展示
    showSolutionAfterSales(afterSalesId);
});

/**查询解决方案详情*/
function loadSolution(solutionId){
	$("#tableSolutionDetail").empty();
	$.ajax({
		url : ctx + '/solution/loadSolution',
		data : { id : solutionId ,delFlag : 3},
		type : 'post',
		async : false,
		success : function(data) {
			if (data.msg == "00") {
				var m = data.solution;// 获取方案订单实体
				$("#usolution_createTime").html(m.createTime.substr(0,19));//创建时间
				$("#usolutionOrderStatus").val(m.solutionStatus);//解决方案订单状态
				$("#usolution_solutionCode").html(m.solutionCode);//解决方案订单编号
				$("#usolutionPayStatus").val(m.payStatus);//解决方案订单支付状态
				if(m.manager!=null){
					$("#usolution_managerName").html(m.follRealName);// 分包管家名称
					$("#usolution_managerPhone").html(m.managerPhone);// 分包管家电话
				}
				$("#usolution_orderStatus").html(solution_status[m.solutionStatus]);// 订单状态
				$("#usolution_order_source_id").html(m.orderSourceId);//订单来源
				$("#usolution_mcode").html(m.mcode);//订单渠道
				$("#usolution_withholdingCard").html(m.feeCardNum);//m.withholdingCard//代扣卡
				$("#usolution_orderPrice").html(m.totalPay);//订单金额
				/** 填写订单包含项目 */
				//$("#solution_blessing").html(m.blessing);//祝福语
				//$("#solution_remark").html(m.remark);//备注
				$("#usolution_payerName").html(m.payerName);// 客户名称
				$("#usolution_payerMobile").html(m.payerMobile);// 客户电话
				//审核部门回显
				$("#usolution_ApproveDeptSelect option").each(function(i){
					var udept = $(this).val();
					if(udept != undefined && udept != ""){
						if(udept == m.approveDept){
							$(this).attr("selected",true);
						}
					}
				})
 				//审核人回显
 				$("#usolution_ApproveBySelect").html("<option value='"+m.approveBy+"' >"+m.approveByText+"</option>");
				if(auditFlag == '20130002'||auditFlag == '20130005' ||auditFlag == '20130009'){
 					$("#usolution_ApproveDeptSelect").attr("disabled","disabled");
 					$("#usolution_ApproveBySelect").attr("disabled","disabled");
 				}else{
 					$("#usolution_ApproveDeptSelect").removeAttr("disabled");
 					$("#usolution_ApproveBySelect").removeAttr("disabled");
 				}
				var uactiveMoney = m.activeMoney;
				var uplanFeeSum = data.planFeeSum; 
				$("#usolutionMoneyDetail").html("&nbsp;&nbsp;=&nbsp;&nbsp;排期余额： <span>"+intToDecimal(uactiveMoney)+"</span>&nbsp;&nbsp;+  可用余额： <span>"+intToDecimal(uplanFeeSum)+"</span>&nbsp;&nbsp;");
				if(m.payStatus == "20110001"){
					$("#usolution_bank").hide();
					$("#usolution_bankCity").hide();
					$("#usolution_bankCN").hide();
					$("#usolution_bankUn").hide();
				}
				
				querySolutionItem(solutionId);
			}
		}
	});
}

/**查询解决方案套餐列表*/
function querySolutionItem(solutionId){
	$.ajax({
		url : ctx + '/solution/querySolutionItem',
		data : { solutionCust_solutionId : solutionId }, // 方案订单ID
		type : 'post',
		async : false,
		dataType : "json",
		success : function(data) {
			if (data != null) {
				if (data.msg == "00" && data.list.length > 0) {
					var html = "";
					var num = $.each(data.list,function(i, v) {
							num = i + 1;
							html += "<tr align='center'>" ;
							html += "<td><input type='hidden' name='usolutionItemChk' value ='"+v.id+"_"+v.proudctName+"_"+v.surplusNum+"' />"+num;
							html += "</td><td>" + v.proudctName;// 项目
							html += "</td><td>" + v.qty;// 数量
							html += "</td><td>" + v.surplusNum;//剩余数量
							html += "</td><td>" + v.qtyOnce;// 每次配送数量
							html += "</td><td>"+ solution_frequency[v.frequency];// 频次
							html += "</td><td>" + v.startServiceTime.substr(0,19);// 首次服务/配送时间
							html += "</td></tr>";
						});
					$("#uorder_solutionCombos").html(html);
				}
			}
		}

	});
}

 	//售后单信息展示
	function showSolutionAfterSales(afterSalesId){
	    $.ajax({
	      	 url:ctx+'/afterSales/loadAfterSales',
	      	 data:{
	      		id:afterSalesId,
	      	 },
	      	 type:'post',
	      	 async:false,
	      	 success:function(data){
	      		 if(data.msg == "00"){
	 				var uasObj = data.afterSales;
 					$("#usolution_afterSalesKind").val("解决方案订单退费");
 					//银行
	 				if(uasObj.bankSupportId != "" && uasObj.bankSupportId != null){
		 				$("#usolution_bankSupportId").val(uasObj.bankSupportId);
	 				}else{
	 					$("#usolution_bankSupportId").val("");
	 				}
	 				//省、市
	 				if(uasObj.bankCityCode != "" && uasObj.bankCityCode != null){
		 				var ucode = uasObj.bankCityCode.substr(0,6);
		 				$("#usolution_bankProvinceCode option[value='" +ucode+"']").attr("selected","selected");
		 				setSelProvinceCitys(uasObj.bankCityCode,9,"usolution_bankCityCode");
		 				$("#usolution_bankCityCode option[value='" +uasObj.bankCityCode+"']").attr("selected","selected");
	 				}else{
	 					$("#usolution_bankCityCode").val("");
	 				}
	 				$("#usolution_bankCard").val(uasObj.bankCard);
	 				$("#usolution_bankName").val(uasObj.bankName);
	 				$("#usolution_bankUserName").val(uasObj.bankUserName);
 					$("#usolution_reason").val(uasObj.reason);
 					//退款总额
					$("#usolutionReturnTotal").html(intToDecimal(uasObj.refundTotal));       
					//退款明细
					var baitiaoStatus = $("#usolution_baitiaoMoney").val();
					if(baitiaoStatus == "1"){
						$("#usolutionReturnTotalDetail").html("<label>&nbsp;&nbsp;白条支付退费： <span>"+intToDecimal(uasObj.refundTotal)+"</span>&nbsp;&nbsp;</label>");
					}else{
						$("#usolutionReturnTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(uasObj.refundTotal)+"</span>&nbsp;&nbsp;</label>");
					}
					$("#usolution_afterSaleId").val(uasObj.id);
	      		 }
	      		 
	      	 }})
	}
	//更新操作
	function updateQualitySolution(){
		 var data = $("#uqualitySolutionForm").serialize();
		 var orderId = '${param.orderId}';
		 var afterSaleId = $("#usolution_afterSaleId").val();
		 var usolutionBankMainName = $("#usolution_afterSalesKind").find("option:selected").text();
		 if(usolutionBankMainName == "...请选择..."){
			 usolutionBankMainName = "";
		 }
		 var url = "";
		 var auditFlag = '${param.auditFlag}';
		//验证审批部门
		var usolutionApproveDept  = $("#usolution_ApproveDeptSelect").find("option:selected").val();
		 if(usolutionApproveDept == "" || usolutionApproveDept == null){
			 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
			 $("#qualitySolutionBtn").removeAttr("disabled");
			  return ;
		 }
		//验证审批人
		 var usolutionApproveBy = $("#usolution_ApproveBySelect").find("option:selected").val();
		 if(usolutionApproveBy == "" || usolutionApproveBy == null){
			 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
			 $("#qualitySolutionBtn").removeAttr("disabled");
			  return ;
		 }
		//url售后单状态修改
		 url = "${ctx}/afterSales/updateAfterSales?id="+afterSaleId+"&orderId="+orderId+"&bankMainName="+encodeURI(encodeURI(usolutionBankMainName))+"&auditFlag="+auditFlag;
		 $.ajax({
			url: url ,//"&afterSalesImgs="+imgUrls+
			data:data,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					 $.alert({millis:3000,top:'30%',text:"添加成功！"});
					queryAfterSalesByLike(0,10);
					//var uauditFlag = data.auditFlag;
					//showQualitySolutionDetail(afterSaleId,orderId,uauditFlag);
					closeModule('qualitySolutionEdit');
				}else{
					 $.alert({millis:3000,top:'30%',text:"添加失败！"});
					closeModule('qualitySolutionEdit');
				}
			}
		}); 
	}
	//查询审批人
	function queryGuanjiaName(){
		var ctx=$("#ctx").val();
		var deptId = $("#usolution_ApproveDeptSelect option:selected").val();;
		if(deptId==""||deptId==null){
			$("#usolution_ApproveBySelect").empty();
	    	$("#usolution_ApproveBySelect").html("<option value=''>...请选择...</option>");
		}else{
			$.ajax({
				url:ctx+"/orderbase/queryguanjia",
				data : {
					deptId : deptId
				},
				type : 'post',
				async : false,
				dataType : "json",
				success : function(data) {
					var html = "";
					html +="<option style='color:blue;' value='' >...请选择...</option>";
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							html += "<option   value='" + v.id + "'>"+v.realName+"（"+v.userName+"）</option>";
						});
					} else if(data.msg== "02"){
						$.alert({millis:2000,text:"无记录!"});
					}else{
						$.alert({millis:2000,text:"查询出错，请稍后再试!"});
					}
					$("#usolution_ApproveBySelect").html(html);
				}
			});
		}
	}
 	//进行验证的方法
	$('#uqualitySolutionForm').bootstrapValidator({
	    message: 'This value is not valid',
	    excluded: ':disabled',
	    feedbackIcons: {
	        valid: 'glyphicon glyphicon-ok',
	        invalid: 'glyphicon glyphicon-remove',
	        validating: 'glyphicon glyphicon-refresh'
	    },
	    fields: {         //fields下的是表单name属性
	    	bankUserName: {
	            message: '持卡人无效！',
	            validators: {
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
	                stringLength: {
	                    min: 1,
	                    max: 30,
	                    message: '开户行名称必须为1-30个字！'
	                },
	                regexp: {
	                    regexp: /^[\u4E00-\u9FA5]{1,6}$/,
	                    message: '开户行名称为中文！'
	                }
	            }
	        },
	         bankSupportId: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        }, 
	        /* afterSalesKind: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        }, */
	         validateProvince : {
                validators: {
                    notEmpty: {
                        message: '必选！'
                    },
                }
            }, 
            bankCard : {
            	validators: {
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
            reason : {
            	validators: {
            		stringLength: {
	                    min: 0,
	                    max: 200,
	                    message: '描述不超过200字！'
	                },
            	}
            },
            
	    }
	}).on('success.form.bv', function(e) {
	    // 阻止表单提交【submit】【必填】
	    e.preventDefault();
	    //更新售后单
	    updateQualitySolution();
	}); 
</script>
</html>

