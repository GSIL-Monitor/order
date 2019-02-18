<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade">  
	    <div class="modal-dialog">
	    	<div class="modal-content">  
	    		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"  onclick="closeModule('changeOrderSale')">×</button>  
			        <h4 class="modal-title">迁单操作>>新建迁单</h4>
		        </div>

	      		<form class="form-inline" id="c_orderForm" action="">
		      <div class="modal-body" >
		      	<div class="modal-content-basic">
		      	<input type="hidden" id="h_memberShipFee"/>
		      	<input type="hidden" id="h_salery_novph"/>
		      	<input type="hidden" id="h_salery_vph"/>
		      	<input type="hidden" id="h_userName"/>
		      	<input type="hidden" id="h_userMobile"/>
		      	<input type="hidden" id="h_orderId"/>
			     <header><h4>客户订单信息</h4></header>
		      	<div class="info-select clearfix" >
			      	<div class="row">
					   <div class="form-group col-xs-8">
						 <label><p>要迁订单号:</p>
						 	<input type="text" class="form-control" id="c_orderCode"/>
						 </label>
						</div>
		            	<div class="form-group col-xs-1">
		                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryOrderA();">查询</button>
						</div>
		            	<div class="form-group col-xs-1">
		                      <button type="button" class="btn btn-sm btn-default fr mr20" onclick="resetChangeInfo();">重置</button>
						</div>
					</div>
		      		<div class="panel-content " >
						<table class="table table-hover" style="width:100%;">
			                <thead>
			                	<tr>
									<th>订单编号</th>
									<th>客户姓名</th>
									<th>客户电话</th>
									<th>订单类型</th>
									<th>创建时间</th>
									<th>订单状态</th>
									<th>订单来源</th>
			                   </tr>
			                  </thead>
			                  <tbody id="c_order_info" style="width:100%;">
			                </tbody>
			            </table>
					</div>
	                </div>
	               <header><h4>迁单信息</h4></header>
	               <div class="info-select clearfix" >
	               <div class="row" id="changeOrderDiv">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><span style="color:red">*</span>迁至订单号：</p>
	                           <select id="c_orderSelect" class="form-control" >
									<option value="">...请选择...</option>
				  				</select>
	                        </label>
	                    </div>
	                </div>
	               <div class="row" >
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批部门:</p>
								<select  id="c_ApproveDeptSelect" name="approveDept" class="form-control" style="width:260px">
									<option value="-1">...请选择...</option>
								</select>
	                     	</label>
                    	</div>
                        <div class="form-group col-xs-6">
							<label>
								<p><span style="color:red">*</span>审批人:</p>
								<select id="c_ApproveBySelect" name="approveBy" class="form-control" >
									<option value="">...请选择...</option>
				  				</select>
	                     	</label>
                    	</div>
					</div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>可迁信息费：</p>
	                            <span id="c_memberShipFee"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row" >
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>可迁服务费(非白条)：</p>
	                            <span id="c_salery_novph"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>可迁服务费(白条)：</p>
	                            <span id="c_salery_vph"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                            <p><span style="color:red">*</span>原因：</p>
	                            <textarea rows="3"  name="reason" id="c_reason" style="height:50px;" class= "form-control form-textarea" ></textarea>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
	                       	    <p style="color:red;">&nbsp;注意：</p><br/>
	                            <span style="font-size: 12px;color:red;padding-left:100px;">（1）只能迁至【已签约】或【已上户】的【延续性】订单;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（2）迁单前请确认原订单应发服务人员服务费全部处于【已发放】状态，即已发放完毕;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（3）迁单成功后，原订单终止，余额全部迁至新订单;</span><br/>
								<span style="font-size: 12px;color:red;padding-left:100px;">（4）如原订单服务人员服务费状态未更新至【已发放】状态，导致原订单服务人员应发服务人员服务费无法发放，请找结算中心处理;</span><br/>
	                        </label>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	                    </div>
	                </div>
	                 <div class="row">&nbsp;</div>
	                <div class="row text-center">
	                    <div class="form-group col-xs-12">
	                        <label class="form-label">
								<span style="font-size: 20px;color:red;">订单只能迁单1次,请谨慎操作</span>
	                        </label>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	                    </div>
	                </div>
	                </div>
	                </div>
	                </div>
	                <div class="modal-footer">  
				        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="closeModule('changeOrderSale')" >取消</button>  
				      	<button type="submit" class="btn btn-sm btn-primary"  id="changeOrderSaleBtn">保存</button>
				     </div> 
	      		</form>
		  </div> 
	    </div>
	</div> 
  
</body>
<script type="text/javascript" src="${ctx}/js/afterSale/changeOrderSale.js"></script>
<script type="text/javascript">
	$(function() {
		queryOrderA();
		//查询审核部门
		queryApproveDeptName("c_ApproveDeptSelect");
	});
	
	

</script>
</html>

