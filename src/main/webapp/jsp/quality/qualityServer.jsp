<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		var orderId = '${param.orderId}';
		var cateType = '${param.cateType}';
		var orderType = '${param.orderType}';
	</script>
</head>
<body>
	<div class="modal fade">  
	    <div class="modal-dialog">
	    	<div class="modal-content">  
	    		<div class="modal-header" id="yanxvTitle">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('qualityServer')">×</button>  
			        <h4 class="modal-title">新增售后>>新增延续服务类售后单</h4>
		        </div>
	    		<div class="modal-header" id="danciTitle">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('qualityServer')">×</button>  
			        <h4 class="modal-title">新增售后>>新增单次服务类售后单</h4>
		        </div>

	      		<input type="hidden" id="serverOrderStatus"></input>
	      		<input type="hidden" id="serverPayStatus"></input>
	      		<form class="form-inline" id="qualityYXServerForm" action="">
		      <div class="modal-body" >
		      	<div class="modal-content-basic">
	      		<input type="hidden" id="serverNowPrice"></input>
	      		<input type="hidden" id="serverOrderTotalPay" placeholder="订单余额"></input>
	      		<input type="hidden" id="yxcustomerManageMoney" placeholder="客户信息费"></input>
	      		<input type="hidden" id="yxPrePareMoney"></input>
	      		<input type="hidden" id="yxSaleMoney"></input>
	      		<input type="hidden" id="yxHasVipshopFee"></input>
	      		<input type="hidden" id="yxFqBtSaleMoney" placeholder="分期白条预收使用金额"></input>
	      		<input type="hidden" id="currentTime" placeholder="当前时间"></input>
	      		<input type="hidden" id="yxServerParentId" placeholder="当前订单父解决方案订单id"></input>
			      	 <header><h4>客户信息</h4></header>
		      	<div class="info-select clearfix" >
		      		<div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p>
	                            <span id="yxServerUserName"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>联系方式：</p>
	                            <span id="yxServerMobile"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>性别：</p>
	                            <span id="yxServerSex"></span>
	                        </label>
	                    </div>
	                </div>
	                </div>
	                <header><h4>订单信息</h4></header>
	               <div class="info-select clearfix" >
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单编号：</p>
	                            <span id="yxServerOrderCode"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单类型：</p>
	                            <span id="yxServerOrderType"></span>
	                        </label>
	                    </div>
	                 </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>开始时间：</p>
	                            <span id="yxServerStartTime"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>结束时间：</p>
	                            <span id="yxServerEndTime"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>服务地址：</p>
	                            <span id="yxServerReceiverAddress"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单状态：</p>
	                            <span id="yxServerOrderStatus"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单渠道：</p>
	                            <span id="yxServerOrderChannel"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单来源：</p>
	                            <span id="yxServerOrderSource"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>订单备注：</p>
	                            <textarea rows="2" id="yxServerRemark" style="height:50px;" class= "form-control form-textarea" readonly="readonly"></textarea>
	                        </label>
	                    </div>
	                 </div>
	                 </div>
	                 
	                <!-- <header id="arterFeeHeader" ><h4>售后手续费</h4></header>
	                <div class="info-select clearfix" id="arterFeeDiv">
	               		<div class="form-group col-xs-12">
							<label>
								<p style="width: 15em;">应缴售后手续费(元)：</p>
								<span name="payfeeMoney" id="payfeeMoney" ></span>
								</label>
						</div>
						<div class="form-group col-xs-6">
							<label>
								<p style="width: 15em;">信息费可用(元)：</p>
								<span name="MembershipFee" id="MembershipFee" ></span>
								</label>
						</div>
						<div class="form-group col-xs-12">
							<label>
								<p style="width: 15em;">已缴售后手续费(元)：</p>
								<span name="accountAmount" id="accountAmount" ></span>
								</label>
						</div>
						<div class="form-group col-xs-6">
							<label>
								<p style="width: 15em;">非白条预存服务费可用(元)：</p>
								<span name="notIousSalaryMoney" id="notIousSalaryMoney" ></span>
								</label>
						</div>
						<div class="form-group col-xs-12">
							<label>
								<p style="width: 15em;">未缴售后手续费(元)：</p>
								<span name="payfeeMoneyC" id="payfeeMoneyC" ></span>
								</label>
						</div>
						<div class="form-group col-xs-6">
							<label>
								<p style="width: 15em;">分期手续费可用(元)：</p>
								<span name="installmentFee" id="installmentFee" ></span>
							</label>	
						</div>
						<div class="row" id="after-sale-service-charge-hidden" style="display:none">
							<div id="MembershipFee_hidden"></div> 分期手续费可用缴费
							<div id="installmentFee_hidden"></div> 信息费可用缴费
							<div id="notIousSalaryMoney_hidden"></div> 非白条预存服务费可用缴费
						</div>
	               </div> --> 
	                 
	               <header><h4>售后信息</h4></header>
	               <div class="info-select clearfix" >
	               <div class="row">
	                	<div class="form-group col-xs-12" >
	                        <label>
	                            <p><span style="color:red">*</span>售后类型：</p>
	                            <select class="form-control" name="afterSalesKind" id="yxServer_afterSalesKind" >
	                            		 <option value="">...请选择...</option>
	                                     <option value="5">延续服务订单取消</option>
	                                     <!-- <option value="7">延续服务订单换人</option> -->
	                                     <option value="8">延续服务订单终止</option>
	                                    <!--  <option value="10">延续服务订单退费</option> -->
	                             </select>
	                        </label>
	                    </div>
	               </div>
	               <input type="hidden" id="historyAfterCharge" value="0">
	               <div class="row" id="yxApproveSelectDiv">
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="yxApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="yxApproveBySelect" name="approveBy" class="form-control" style="width: 180px;" >
									<option value="">...请选择...</option>
				  				</select>
	                     	</label>
                    	</div>
					</div>
					<!-- <div class="row" id="changeOrderDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>&nbsp;&nbsp;转单订单单号：</p>
	                           <select id="yxChangeOrderSelect" class="form-control" >
									<option value="">...请选择...</option>
				  				</select>
				  				<span style="color:red;">&nbsp;&nbsp;注意：迁单请选择此项，只能迁单到该客户已签约、已上户的延续订单上，如果为正常售后，请勿选择！</span>
	                        </label>
	                    </div>
	                </div> -->
	                
	               	<hr>
	                <div class="row" id="refundMembershipFeeDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p style="width: 150px;"><span style="color:red">*</span>应退信息费(元)：</p>
	                            <input type="text" name="refundMembershipFee" id="yxMembershipFee"  class="form-control" placeholder="请填写应退信息费"/>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="refundSalaryFeeDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p style="width: 150px;"><span style="color:red">*</span>应退服务人员服务费(元)：</p>
	                            <input type="text" name="refundSalaryFee" id="yxSalaryFee"  class="form-control" placeholder="请填写应退服务人员服务费"/>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="refundDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>退费总额(元)：</p><span id="yxServerRefund" name="refundTotal"></span>&nbsp;&nbsp;<span id="yxServerMoneyDetail" style="display: none;"></span>
	                            <span style="color:red;" id="yxWarn">退款总额不能超过最大退款金额，请重新填写！</span> 
	                            <input type="hidden"  id="yxServerMoneyHidden"  class="form-control"/>
	                        </label>
	                    </div>
	                </div>
	                <!-- <div class="row" id="refundVipShopFeeDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><span style="color:red">*</span>应退分期金额(元)：</p>
	                            <input type="text" name="refundVipShopFee" id="yxVipShopFee"  class="form-control"></input>
	                            &nbsp;&nbsp;<span id="yxVipShopMoneyDetail"></span><span style="color:red;" id="yxVipShopWarn">分期退款总额不能超过最大退款金额，请重新填写！</span> 
	                            <input type="hidden"  id="yxVipShopMoneyHidden"  class="form-control"></input>
	                        </label>
	                    </div>
	                </div> -->
	                <div class="row" id="refundDetailDiv">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>退费明细(元)：</p>
	                            <span id="yxRefundDetail">
	                        </label>
	                    </div>
	                </div>
	                <div class="row text-center" id="afterReturnableDiv">
	               		<div class="form-group col-xs-12">
	                        <label>
						          <header>
							          <h4>
								       	   信息费最大可退：<span id="afterReturnableMessageFee" value="0"></span>元&nbsp;&nbsp;&nbsp;&nbsp;
								                       服务费最大可退：<span id="afterReturnableSalary" value="0"></span>元
							          </h4>
						          </header>
	                        </label>
	                    </div>
	                </div>
	                <hr id="refundDetailLine">
	                <div class="row" id="yxServer_bank">
		                <div class="form-group col-xs-6">
		                        <label>
		                            <p>银行：</p>
		                            <select class="form-control" name="bankSupportId" id="yxServer_bankSupportId" >
		                           			 <option value=""  data-name="">...请选择...</option>
		                                     <option value="1" data-name="工商银行">工商银行</option>
		                                     <option value="2" data-name="建设银行">建设银行</option>
		                                     <option value="3" data-name="农业银行">农业银行</option>
		                                     <option value="4" data-name="中国银行">中国银行</option>
		                                     <option value="5" data-name="兴业银行">兴业银行</option>
		                                     <option value="6" data-name="民生银行">民生银行</option>
		                                     <option value="7" data-name="招商银行">招商银行</option>
		                                     <option value="8" data-name="交通银行">交通银行</option>
		                             </select>
		                        </label>
		                    </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="yxServer_bankUserName"/>
                               </label>
                           </div>
                    </div>
                      <div class="row" id="yxServer_bankCity">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="yxServer_bankProvinceCode"  onclick="setSelCity('yxServer_bankProvinceCode','yxServer_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="yxServer_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="yxServer_bankCN">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="yxServer_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="yxServer_bankName"/>
                               </label>
                           </div>
                    </div>
                    <!-- <div class="row">
		                <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>取消原因：</p>
		                            <select class="form-control" id="yxReasonSelect" >
		                           			 <option value="">...请选择...</option>
		                                     <option value="1" data-quality="客户咨询">客户咨询</option>
		                                     <option value="2" data-quality="不需要服务">不需要服务</option>
		                                     <option value="3" data-quality="已经找到服务">已经找到服务</option>
		                                     <option value="4" data-quality="距离远服务不到">距离远服务不到</option>
		                                     <option value="5" data-quality="提供不了合适人员">提供不了合适人员</option>
		                                     <option value="6" data-quality="觉得信息费太贵">觉得信息费太贵</option>
		                                     <option value="7" data-quality="人员服务费太高">人员服务费太高</option>
		                                     <option value="8" data-quality="对管家不认同">对管家不认同</option>
		                                     <option value="9" data-quality="对管家帮不认同">对管家帮不认同</option>
		                                     <option value="10"data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		                    </div>
                    </div> -->
	                <div class="row" id="yxReasonDiv">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>原因：</p>
	                            <textarea rows="3"  name="reason" id="yxServerReason" style="height:50px;" class= "form-control form-textarea" ></textarea>
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
	                    <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="lastStep('qualityServer')" >上一步</button>  
				        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('qualityServer')" >取消</button>  
				      	<button type="submit" class="btn btn-sm btn-primary"  id="qualityServerBtn">保存</button>
				     </div> 
	      		</form>
	      		<form class="form-inline" id="qualityDCServerForm" action="">
	      		<input type="hidden" id="dcServerCateType"></input>
	      		<input type="hidden" id="dcServerStartTimeHide"></input>
	      		<input type="hidden" id="dcServerParentId"></input>
	      		<input type="hidden" id="dcServerBankCardMoney"></input>
	      		<div class="modal-body" >
		      	 <div class="modal-content-basic">
			      	 <header><h4>客户信息</h4></header>
		      	<div class="info-select clearfix" >
		      		<div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p>
	                            <span id="dcServerUserName"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>联系方式：</p>
	                            <span id="dcServerMobile"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>性别：</p>
	                            <span id="dcServerSex"></span>
	                        </label>
	                    </div>
	                </div>
	                </div>
	                <header><h4>订单信息</h4></header>
	               <div class="info-select clearfix" >
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单编号：</p>
	                            <span id="dcServerOrderCode"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单类型：</p>
	                            <span id="dcServerOrderType"></span>
	                        </label>
	                    </div>
	                 </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>开始时间：</p>
	                            <span id="dcServerStartTime"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>结束时间：</p>
	                            <span id="dcServerEndTime"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>服务地址：</p>
	                            <span id="dcServerReceiverAddress"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单状态：</p>
	                            <span id="dcServerOrderStatus"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单渠道：</p>
	                            <span id="dcServerOrderChannel"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单来源：</p>
	                            <span id="dcServerOrderSource"></span>
	                        </label>
	                    </div>
	                 </div>
	                 <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>订单备注：</p>
	                            <textarea rows="2" id="dcServerRemark" style="height:50px;" class= "form-control form-textarea" readonly="readonly"></textarea>
	                        </label>
	                    </div>
	                 </div>
	                 </div>
	                <header><h4>售后信息</h4></header>
	               <div class="info-select clearfix" >
	               <div class="row">
	               		<div class="form-group col-xs-12" >
	                        <label>
	                            <p><span style="color:red">*</span>售后类型：</p>
	                            <select class="form-control" name="afterSalesKind" id="dcServer_afterSalesKind" >
	                            		 <option value="">...请选择...</option>
	                                     <option value="4">单次服务订单取消</option>
	                             </select>
	                        </label>
	                    </div>
	               </div>
	               <div class="row" id="dcqualityApproveDiv">
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="dcApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="dcApproveBySelect" name="approveBy" class="form-control" >
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
	                            <span id="dcServerReturnTotal"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-9">
	                        <label><p>退费明细（元）：</p>
	                            <span  id="dcReturnTotalDetail"></span>
	                        </label>
	                    </div>
	                </div>
					   <div class="row">
						   <div class="form-group col-xs-12">
							   <label>
								   是否填写银行信息：
								   <span>
									   <label>
										   <input type="radio" id="trueBank" name="selectBank" value="1" checked>是
									   </label>
									   &nbsp;&nbsp;&nbsp;&nbsp;
									   <label>
										   <input type="radio" id="falseBank" name="selectBank" value="2">否
									   </label>
								   </span>
							   </label>
						   </div>
					   </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12" id="dcServer_bank">
	                        <label>
	                            <p><span style="color:red">*</span>银行：</p>
	                            <select class="form-control" name="bankSupportId" id="dcServer_bankSupportId" >
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
	                <div class="row" id="dcServer_bankCity">
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p><span style="color:red">*</span>开户省份：</p>
	                            <select class="form-control" name="validateProvince" id="dcServer_bankProvinceCode"  onclick="setSelCity('dcServer_bankProvinceCode','dcServer_bankCityCode')">
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-6">
	                        <label>
	                            <p><span style="color:red">*</span>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="dcServer_bankCityCode" >
	                                     <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" id="dcServer_bankCN">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>银行卡号：</p>
                                   <input type="text" name="bankCard" style="width:200px" class="form-control" id="dcServer_bankCard"/>
                               </label>
                           </div>
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>开户行：</p>
                                   <input type="text" name="bankName" class="form-control" id="dcServer_bankName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row" id="dcServer_bankUn">
                           <div class="form-group  col-xs-6">
                               <label>
                                   <p><span style="color:red">*</span>持卡人：</p>
                                   <input type="text" name="bankUserName" class="form-control" id="dcServer_bankUserName"/>
                               </label>
                           </div>
                    </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p>原因：</p>
	                            <textarea rows="3"  name="reason" style="height:50px;" id="dcServerReason" class= "form-control form-textarea" ></textarea>
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
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="lastStep('qualityServer')" >上一步</button>  
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('qualityServer')" >取消</button>  
		      	<button type="submit" class="btn btn-sm btn-primary" id="qualityServerOneBtn">保存</button>
		      </div>  
	      	</form>
		  </div> 
	    </div>
	</div> 
  
</body>
<script type="text/javascript" src="${ctx}/js/afterSale/qualityServerAdd.js"></script>
<script type="text/javascript">
	$(function() {
		var smoney = queryAfterReturnableSalary({"orderId":orderId});
		var mmoney = queryAfterReturnableMessageFee({"orderId":orderId});
		$("#afterReturnableSalary").attr("value",smoney).html(smoney*1);
		$("#afterReturnableMessageFee").attr("value",mmoney).html(mmoney*1);
		$("#yxMembershipFee").on("input",function(){
			inputMembershipFeeOrSalaryFee();
		});
		$("#yxSalaryFee").on("input",function(){
			inputMembershipFeeOrSalaryFee();
		});
		getQualityServer(orderId,cateType);
		/** 周鑫 2019-01-07  查询历史服务费退费  **/
		var gha=getHistoryAfterCharge(orderId);
		$("#historyAfterCharge").val(gha);
		/** 周鑫 2019-01-07 查询历史服务费退费 **/
		$("#dcServer_bank").show();
		$("#dcServer_bankCity").show();
		$("#dcServer_bankCN").show();
		$("#dcServer_bankUn").show();
		//判断执行订单，大订单是否有售后单
		if(cateType == 1 || cateType == 4){
			var dcParentId =  $("#dcServerParentId").val();
			if(dcParentId != null && dcParentId != ""){
				querySolutionBigOrderAfterSale(dcParentId);
				if(solutionBigOrderflag){
					 $("#dcServer_bank").hide();
					 $("#dcServer_bankCity").hide();
					 $("#dcServer_bankCN").hide();
					 $("#dcServer_bankUn").hide();
				}
			}
		}
		//隐藏售后原因输入框
		//$("#yxReasonDiv").hide();

		/*售后手续费2-开始*/
		/* if(cateType == 2 && orderType != 100200120003 && orderType != 100200010001){
			$("#arterFeeHeader").show();
			$("#arterFeeDiv").show();
		 	var num1=queryPayMoney(orderId);//应缴售后手续费
			var num2=queryAccountPay(orderId);//已缴售后手续费
			var num3=queryMembershipFee(orderId);//信息费可用
			var num4=queryNotIousSalaryMoney(orderId);//非白条预存服务费可用
			var num5=queryInstallmentFee(orderId);//分期手续费可用
			var num6 = num1-num2;
			$("#payfeeMoneyC").html(num6 > 0 ? (Math.round(num6 * 100) / 100):0);//未缴售后手续费
		}else{
			$("#arterFeeHeader").hide();
			$("#arterFeeDiv").hide();
		} */
		/*售后手续费2-结束*/

		//20180612他人代收类型可不填写银行账户信息，提交表单js绕过校验
		$(":radio").click(function(){
			var check = $(this).val();
			if(check == "2"){//不填写银行信息
				$("#dcServer_bank").hide();
				$("#dcServer_bankCity").hide();
				$("#dcServer_bankCN").hide();
				$("#dcServer_bankUn").hide();
				$.confirm({text:"您选择了不填写银行信息!只有在对所需退费金额为0的订单取消时才操作此项,否则会因财务无法退费驳回!",callback:function(r){
					if(r==false){
						$("#trueBank").click();
					}else{
						$("#dcServer_bankSupportId").val("");
						$("#dcServer_bankProvinceCode").val("");
						$("#dcServer_bankCityCode").val("");
						$("#dcServer_bankCard").val("");
						$("#dcServer_bankName").val("");
						$("#dcServer_bankUserName").val("");
					}
				}})
			}else{
				$("#dcServer_bank").show();
				$("#dcServer_bankCity").show();
				$("#dcServer_bankCN").show();
				$("#dcServer_bankUn").show();
			}
		});
	});

	//查询审批人
	function queryGuanjiaName(){
		var deptId = "";
		var cateType = '${param.cateType}';
		if(cateType == 2){
	        deptId = $("#yxApproveDeptSelect option:selected").val();
		}else{
			deptId = $("#dcApproveDeptSelect option:selected").val();
		}
		if(deptId==""||deptId==null){
			if(cateType == 2){
				$("#yxApproveBySelect").empty();
		    	$("#yxApproveBySelect").html("<option value=''>...请选择...</option>");
			}else{
				$("#dcApproveBySelect").empty();
		    	$("#dcApproveBySelect").html("<option value=''>...请选择...</option>");
			}
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
					if(cateType == 2){
						$("#yxApproveBySelect").html(html);
						$("#yxApproveBySelect").siblings("div").remove();
						$("#yxApproveBySelect").checkSelect();
					}else{
						$("#dcApproveBySelect").html(html);
						$("#dcApproveBySelect").siblings("div").remove();
						$("#dcApproveBySelect").checkSelect();
						
					}
				}
			});
		}
	}
	
	
	
	//非白条预存服务费可用金额
	function queryNotIousSalaryMoney(orderId) {
		var total = 0;
		$.ajax({
			url : ctx+"/afterFees/queryNotIousSalaryMoney",
			data : {"orderId" : orderId},
			type : "POST",
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.msg == "00") {
					$.each(data.list,function(i,v){
						total += v.feeSumC;
					})
					$("#notIousSalaryMoney_hidden").html(JSON.stringify(data.list));
					$("#notIousSalaryMoney").html(total);
				}else{
					$("#notIousSalaryMoney").html(total);
				}
			}
		})
		return total;
	}
		//查询总手续费
		function queryPayMoney(orderId) {
			var money=0;
			$.ajax({
				url : ctx+"/afterFees/queryPayMoney",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					"orderId" : orderId
				},
				success : function(data) {
					if (data.msg == "00" && data.payfeeMoney != null) {
						money=data.payfeeMoney;
						$("#payfeeMoney").html(money);
					} else {
						$("#payfeeMoney").html(money);
					}
				}
			})
			return money;
		}
		
		//查询扣减已缴手续费
		function queryAccountPay(orderId) {
			var money=0;
			$.ajax({
				url : ctx+"/afterFees/queryAccountPay",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					"orderId" : orderId
				},
				success : function(data) {
					if (data.msg == "00") {
						money=data.accountAmount;
						$("#accountAmount").html(money);
					} else {
						$("#accountAmount").html(money);
					}
				}
			})
			return money;
		}
		
		//查询信息费可退金额
		function queryMembershipFee(orderId) {
			var total=0;
			$.ajax({
				url : ctx+"/afterFees/queryMembershipFee",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					"orderId" : orderId
				},
				success : function(data) {
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							total+=v.feeSumC;
						})
						$("#MembershipFee").html(total);
						$("#MembershipFee_hidden").html(JSON.stringify(data.list))
					} else {
						$("#MembershipFee").html(total);
					}
				}
			})
			return total;
		}
		
		
		//查询分期手续费
		function queryInstallmentFee(orderId) {
			var total=0;
			$.ajax({
				url : "/order/afterFees/queryInstallmentFee",
				type : "POST",
				dataType : "json",
				async : false,
				data : {
					"orderId" : orderId
				},
				success : function(data) {
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							total+=v.feeSumC;
						})
						$("#installmentFee").html(total);
						$("#installmentFee_hidden").html(JSON.stringify(data.list))
					} else {
						$("#installmentFee").html(total);
					}
				}
			})
			return total;
		}
		
</script>
</html>

