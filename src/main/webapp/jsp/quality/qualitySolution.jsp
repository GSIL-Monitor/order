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
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('qualitySolution')">×</button>  
			        <h4 class="modal-title">新增售后>>新增解决方案订单售后单</h4>
		        </div>
	      <form class="form-inline" id="qualitySolutionForm">
	      		<input type="hidden" id="solutionOrderStatus"/>
	      		<input type="hidden" id="solutionPayStatus"/>
	      		<input type="hidden" id="solutionPlanMoney"/>
	      		<input type="hidden" id="solutionActiveMoney"/>
	      		<input type="hidden" id="solutionBaitiaoMoney"/>
			      <div class="modal-body" >
			      	<div class="modal-content-basic">
			      	 <header><h4>方案信息</h4></header>
		      	<div class="info-select clearfix" >
		      			<div class="row">
							<div class="form-group col-xs-12">
								<span>解决方案编号：</span>
								<lable id="solution_solutionCode"></lable>
							</div>
						</div>
		      		    <div class="row">
							<div class="form-group col-xs-6">
								<span>创建时间：</span>
								<lable id="solution_createTime"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>订单金额：</span>
								<lable id="solution_orderPrice"></lable>元
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>客户：</span>
								<lable id="solution_payerName"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>客户电话：</span>
								<lable id="solution_payerMobile"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>管家：</span>
								<lable id="solution_managerName"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>管家电话：</span>
								<lable id="solution_managerPhone"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>状态：</span>
								<lable id="solution_orderStatus"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>代扣卡号：</span>
								<lable id="solution_withholdingCard"></lable>
							</div>
						</div>
						<div class="row">
							<div class="form-group col-xs-6">
								<span>订单来源：</span>
								<lable id="solution_order_source_id"></lable>
							</div>
							<div class="form-group col-xs-6">
								<span>渠道：</span>
								<lable id="solution_mcode"></lable>
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
									<tbody id="order_solutionCombos">

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
	                            <select class="form-control" name="afterSalesKind" id="solution_afterSalesKind" >
	                                     <option value="">...请选择...</option>
	                                     <option value="6">解决方案订单取消</option>
	                                     <!-- <option value="9">解决方案订单退费</option> -->
	                             </select>
	                        </label>
	                    </div>
	               </div>
	               <div class="row" id="solutionApproveDiv">
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="solution_ApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="solution_ApproveBySelect" name="approveBy" class="form-control" >
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
	                            <span id="solutionReturnTotal" name="refundTotal"></span>&nbsp;&nbsp;<span id="solutionMoneyDetail"></span>
	                        </label>
	                        
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label><p>退费明细（元）：</p>
	                            <span id="solutionReturnTotalDetail"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12" id="solution_bank">
	                        <label>
	                            <p>银行：</p>
	                            <select class="form-control" name="bankSupportId" id="solution_bankSupportId" >
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
	                            <p>开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="solution_bankProvinceCode"  onclick="setSelCity('solution_bankProvinceCode','solution_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="solution_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="solution_bankCN">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="solution_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="solution_bankName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row" id="solution_bankUn">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="solution_bankUserName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>原因：</p>
	                            <textarea rows="3"  name="reason" style="height:50px;" id="solution_reason" class= "form-control form-textarea"></textarea>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                       	    <p style="color:red;">&nbsp;&nbsp;售后规则：</p><br/>
	                            <span style="font-size: 12px;color:red;padding-left:100px;">（1）FA订单：新建、待受理、已受理 以上状态可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（2）延续性订单：已受理、匹配中、待面试、面试成功、已签约 以上状态可以取消,已上户和已下户状态可以终止;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（3）自营单次订单：已受理、匹配中、已匹配 以上状态可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（4）他营单次订单：服务开始时间之前4个小时可以取消;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（5）解决方案大订单：新建状态可以取消。</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（6）机器人订单需要退费时，审批部门请选择—产品事业部，审批人选择-黄强。</span><br/>
                   		 		</span>
	                        </label>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	                    </div>
	                </div>
	                </div>
				</div>
			  </div>
		      <div class="modal-footer"> 
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="lastStep('qualitySolution')" >上一步</button>   
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('qualitySolution')" >取消</button>  
		      	<button type="submit" class="btn btn-sm btn-primary" id="qualitySolutionBtn">保存</button>
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
	
	//加载省份
	setSelProvinceCitys('101',6,'solution_bankProvinceCode');
	
	loadSolution(orderId);
    //查询是否有白条缴费
    showSolultionTotal(orderId);
    //缴费
    //solutionqueryPayFeeById(orderId); 
  	//查询审核部门
	queryApproveDeptName("solution_ApproveDeptSelect");
});

/**查询解决方案详情*/
function loadSolution(solutionId){
	//$("#tableSolutionDetail").empty();
	$.ajax({
		url : ctx + '/solution/loadSolution',
		data : { id : solutionId ,delFlag : 2},
		type : 'post',
		async : false,
		success : function(data) {
			if (data.msg == "00") {
				var m = data.solution;// 获取方案订单实体
				$("#solution_createTime").html(m.createTime.substr(0,19));//创建时间
				$("#solutionOrderStatus").val(m.solutionStatus);//解决方案订单状态
				$("#solution_solutionCode").html(m.solutionCode);//解决方案订单编号
				$("#solutionPayStatus").val(m.payStatus);//解决方案订单支付状态
				if(m.manager!=null){
					$("#solution_managerName").html(m.follRealName);// 分包管家名称
					$("#solution_managerPhone").html(m.managerPhone);// 分包管家电话
				}
				$("#solution_orderStatus").html(solution_status[m.solutionStatus]);// 订单状态
				$("#solution_order_source_id").html(m.orderSourceId);//订单来源
				$("#solution_mcode").html(m.mcode);//订单渠道
				$("#solution_withholdingCard").html(m.feeCardNum);//m.withholdingCard//代扣卡
				$("#solution_orderPrice").html(intToDecimal(m.totalPay));//订单金额
				/** 填写订单包含项目 */
				//$("#solution_blessing").html(m.blessing);//祝福语
				//$("#solution_remark").html(m.remark);//备注
				$("#solution_payerName").html(m.payerName);// 客户名称
				$("#solution_payerMobile").html(m.payerMobile);// 客户电话
				$("#solutionPlanMoney").val(data.planFeeSum);// 排期余额
				$("#solutionActiveMoney").val(m.activeMoney);// 可用金额
				//var solutionReturn = intToDecimal(data.planFeeSum)*1 + intToDecimal(m.activeMoney)*1;
				//退款总额
				$("#solutionReturnTotal").text("0.00");
				//退款明细
				$("#solutionReturnTotalDetail").html("&nbsp;&nbsp;无");
				//$("#solutionMoneyDetail").html("<label>&nbsp;&nbsp;=排期余额： <span>"+intToDecimal(m.activeMoney)+"</span>&nbsp;&nbsp;</label> +"+
				//							"<label>&nbsp;&nbsp;可用余额： <span>"+intToDecimal(data.planFeeSum)+"</span>&nbsp;&nbsp;</label>");
				if(m.payStatus == "20110001"){
					$("#solution_bank").hide();
					$("#solution_bankCity").hide();
					$("#solution_bankCN").hide();
					$("#solution_bankUn").hide();
					$("#solutionApproveDiv").hide();
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
							html += "<td><input type='hidden' name='solutionItemChk' value ='"+v.id+"_"+v.proudctName+"_"+v.surplusNum+"' />"+num;
							html += "</td><td>" + v.proudctName;// 项目
							html += "</td><td>" + v.qty;// 数量
							html += "</td><td>" + v.surplusNum;//剩余数量
							html += "</td><td>" + v.qtyOnce;// 每次配送数量
							html += "</td><td>"+ solution_frequency[v.frequency];// 频次
							html += "</td><td>" + v.startServiceTime.substr(0,19);// 首次服务/配送时间
							html += "</td></tr>";
						});
					$("#order_solutionCombos").html(html);
				}
			}
		}

	});
}
 	//退款总额信息
 	function showSolultionTotal(orderId){
		var baitiaoSearchRefundTotal = 0;
 		$.ajax({
			url: ctx+"/payfee/queryAccount",
			data:{
				orderId:orderId,
				isBackType:2,
				valid:1
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$.each(data.list,function(j,w){
							$.ajax({
								url: "${ctx}/payfee/queryPayfee",
								data:{
									orderId:w.id,
									isBackType:1,
									valid:1
								},
								type:"post",
								dataType:"json",
								async:false,
								success:function(data){
									if(data.msg=='00'){
										$.each(data.list,function(l,x){
											if(x.feePost == "20250016"){//白条支付
												baitiaoSearchRefundTotal += intToDecimal(x.feeSum) * 1;
											}
										});
									}
								}
							});
					});
					//将白条缴费放入隐藏域
					$("#solutionBaitiaoMoney").val(intToDecimal(baitiaoSearchRefundTotal));
				}
			}
		});
 	}
 	
 	
 	$("#solution_afterSalesKind").change(function(){
		//var soluOrderStatus = $("#solutionOrderStatus").val();
		var orderStatus = '${param.orderStatus}';
		var activeMoney = $("#solutionActiveMoney").val();
	    var planMoney = $("#solutionPlanMoney").val();
		var solutionReturn = intToDecimal(activeMoney)*1 + intToDecimal(planMoney)*1;
		var baitiaoMoney = $("#solutionBaitiaoMoney").val();
		if($(this).find("option:selected").val() == 6){//取消
			if(orderStatus != 1  ){//&& soluOrderStatus != 18
				$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
				$(this).val("");
			}else{
				//退款总额
				$("#solutionReturnTotal").text("0.00");
				$("#solutionReturnTotalDetail").html("&nbsp;&nbsp;无");
				$("#solutionMoneyDetail").hide();
				//银行银行
				$("#solution_bank").hide();
				$("#solution_bankCity").hide();
				$("#solution_bankCN").hide();
				$("#solution_bankUn").hide();
				$("#solutionApproveDiv").hide();
				
			}
		}else if($(this).find("option:selected").val() == 9){//退费
			if(orderStatus == 1  ){//&& soluOrderStatus != 18
				$.alert({millis:3000,top:'30%',text:"当前订单状态不能进行所选售后操作，请重新选择！"});
				$(this).val("");
			}else{
				//退款总额
				$("#solutionReturnTotal").text(intToDecimal(solutionReturn));
				if(solutionReturn == "0" || solutionReturn == "0.00"){
					$("#solution_bank").hide();
					$("#solution_bankCity").hide();
					$("#solution_bankCN").hide();
					$("#solution_bankUn").hide();
					$("#solutionApproveDiv").hide();
				}else{
					$("#solution_bank").show();
					$("#solution_bankCity").show();
					$("#solution_bankCN").show();
					$("#solution_bankUn").show();
					$("#solutionApproveDiv").show();
				}
				if(baitiaoMoney == "0" || baitiaoMoney == "0.00"){
					$("#solutionReturnTotalDetail").html("<label>&nbsp;&nbsp;银行卡打卡退费： <span>"+intToDecimal(solutionReturn)+"</span>&nbsp;&nbsp;</label>");
				}else{
					$("#solutionReturnTotalDetail").html("<label>&nbsp;&nbsp;白条支付退费： <span>"+intToDecimal(solutionReturn)+"</span>&nbsp;&nbsp;</label>");
					$("#solution_bank").hide();
					$("#solution_bankCity").hide();
					$("#solution_bankCN").hide();
					$("#solution_bankUn").hide();
				}
				$("#solutionMoneyDetail").html("&nbsp;&nbsp;=&nbsp;&nbsp;排期余额： <span>"+intToDecimal(activeMoney)+"</span>&nbsp;&nbsp;+  可用余额： <span>"+intToDecimal(planMoney)+"</span>&nbsp;&nbsp;");
			}
		}else{
			$("#solutionMoneyDetail").hide();
			$("#solutionReturnTotal").html("0.00");
		}
	});
	//保存操作
	function saveQualitySolution(){
		 var orderId = '${param.orderId}';
		 var custName = $("#solution_payerName").text();
		 var custMobile = $("#solution_payerMobile").text();
		 var solutionReturnTotal = $("#solutionReturnTotal").text();
		 var solutionStatus = $("#solutionOrderStatus").val();
		 var solutionPayStatus = $("#solutionPayStatus").val();
		 var baitiaoMoney = $("#solutionBaitiaoMoney").val();
		 if(solutionReturnTotal != undefined && solutionReturnTotal != "" && solutionReturnTotal != "0" && solutionReturnTotal != "0.00"){
			 var solutionApproveDept  = $("#solution_ApproveDeptSelect").find("option:selected").val();
			 if(solutionApproveDept == "" || solutionApproveDept == null){
				 $.alert({millis:2000,top:'30%',text:"请选择审批部门！"});
				 $("#qualitySolutionBtn").removeAttr("disabled");
				  return ;
			 }
			//验证审批人
			 var solutionApproveBy = $("#solution_ApproveBySelect").find("option:selected").val();
			 if(solutionApproveBy == "" || solutionApproveBy == null){
				 $.alert({millis:2000,top:'30%',text:"请选择审批人！"});
				 $("#qualitySolutionBtn").removeAttr("disabled");
				  return ;
			 }
		 }
		 if(solutionPayStatus != "20110001" && (baitiaoMoney == "0" || baitiaoMoney == "0.00")){
				var solutionSupportId = $("#solution_bankSupportId").val();
				var solutionCityCode = $("#solution_bankCityCode").val();
				var solutionCard = $("#solution_bankCard").val();
				var solutionName = $("#solution_bankName").val();
				var solutionUserName = $("#solution_bankUserName").val();
				if(solutionSupportId == null || solutionSupportId == ""){
					$.alert({millis:3000,top:'30%',text:"请填写银行！"});
					flag = true;
				}else if(solutionCityCode == null || solutionCityCode == ""){
					$.alert({millis:3000,top:'30%',text:"请填写城市！"});
					flag = true;
				}else if(solutionCard == null || solutionCard == ""){
					$.alert({millis:3000,top:'30%',text:"请填写银行卡号！"});
					flag = true;
				}else if(solutionName == null || solutionName == ""){
					$.alert({millis:3000,top:'30%',text:"请填写开户行！"});
					flag = true;
				}else if(solutionUserName == null || solutionUserName == ""){
					$.alert({millis:3000,top:'30%',text:"请填写持卡人！"});
					flag = true;
				}else{
					flag = true;
					if(flag){
						insertQualitySolution(orderId,custName,custMobile,solutionReturnTotal);
					}
				}
			}else{
				flag = true;
				if(flag){
					insertQualitySolution(orderId,custName,custMobile,solutionReturnTotal);
				}
			}
		 $("#qualitySolutionBtn").removeAttr("disabled");
	}
	//保存解决方案售后单
	function insertQualitySolution(orderId,custName,custMobile,solutionReturnTotal){
		var data = $("#qualitySolutionForm").serialize();
		var solutionBankMainName = $("#solution_bankSupportId").find("option:selected").text();
		if(solutionBankMainName == "...请选择..."){
			solutionBankMainName = "";
		}
		$.ajax({
			url: "${ctx}/afterSales/insertAfterSales?orderId="+orderId+"&custName="+encodeURI(encodeURI(custName))+"&custMobile="+custMobile+"&refundTotal="+solutionReturnTotal+"&bankMainName="+encodeURI(encodeURI(solutionBankMainName)),//"&afterSalesImgs="+imgUrls+
			data:data,
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg =="00") {
					 $.alert({millis:3000,top:'30%',text:"添加成功！"});
					queryAfterSalesByLike(0,10);
					/* var saleId = data.saleId;
					var auditFlag = data.auditFlag;
					showQualitySolutionDetail(saleId,orderId,auditFlag); */
					closeModule('qualitySolution');
				}else{
					 $.alert({millis:3000,top:'30%',text:"添加失败！"});
					closeModule('qualitySolution');
				}
			}
		}); 
	}
	//查询审批人
	function queryGuanjiaName(){
		var ctx=$("#ctx").val();
		var deptId = $("#solution_ApproveDeptSelect option:selected").val();;
		if(deptId==""||deptId==null){
			$("#solution_ApproveBySelect").empty();
	    	$("#solution_ApproveBySelect").html("<option value=''>...请选择...</option>");
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
					$("#solution_ApproveBySelect").html(html);
				}
			});
		}
	}
 	//进行验证的方法
	$('#qualitySolutionForm').bootstrapValidator({
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
	        /* bankSupportId: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        }, */
	        afterSalesKind: {
	            validators: {
	                notEmpty: {
	                    message: '必选！'
	                },
	            }
	        },
	        /* validateProvince : {
                validators: {
                    notEmpty: {
                        message: '必选！'
                    },
                }
            }, */
            bankCard : {
            	validators: {
            		regexp: {
	                    regexp: /^(\d{16}|\d{18}|\d{19}|\d{20})$/,
	                    message: '银行卡号错误！'
	                }
            	}
            },
             /* bankCityCode : {
            	validators: {
            		notEmpty: {
            			message: '必选！'
            		},
            	}
            },  */
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
	    //保存售后单
	    saveQualitySolution();
	}); 
</script>
</html>

