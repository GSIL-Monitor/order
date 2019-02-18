<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style>
		/*解决第二个modal关闭后，第一个modal不能上下滑动*/
       .modal {overflow: auto !important;}
</style> 
</head>
<body>
	<div class="modal fade">
	    <div class="modal-dialog">
	    	<div class="modal-content">
	    		<div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			        <h4 class="modal-title" id="operationType1" style="display: none;">新增售后</h4>
			        <h4 class="modal-title" id="operationType2" style="display: none;">修改售后</h4>
		        </div>
 				<div class="modal-body" >
				<ul id="afterSales_myTab" class="nav nav-tabs">
					<li class="active">
						<a href="#addFqAfter" data-toggle="tab">分期退费</a>
					</li>
					 <li>
						<a href="#addFqsxfAfter" data-toggle="tab">分期服务费退费</a>
					</li>
					<!-- <li>
						<a href="#addCardAfter" data-toggle="tab">卡退费</a>
					</li> -->
					<li>
						<a href="#addActivitiesAfter" data-toggle="tab">解决方案退费</a>
					</li> 
				</ul>
				<div id="myTabContent_insertAfterSalesNew" class="tab-content">
				<!-- 分期退费-->
				<div class="tab-pane fade in active" id="addFqAfter" >
				<div class="info-select clearfix" >
					<form class="form-inline" id="addFqAfterForm">
					<div class="row">&nbsp;</div>
					<div class="row" id="resetAfterSalesOrder_i">
	                     <div class="form-group col-xs-5">
	                        <label>
	                            <p>订单编号：</p>
	                            <input type="text" name="" id="orderCode_i"  class="form-control" >
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>客户手机：</p>
	                            <input type="text" name="" id="userMobile_i"  class="form-control" >
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-2">
	                     <button type="button" class="btn btn-sm btn-primary"  onclick="loadAfterSalesOrder(1);"  id="queryAfterBu_i">搜索</button>
	                     <button type="button" class="btn btn-sm btn-primary"  onclick="resetAfterSalesOrder('resetAfterSalesOrder_i');">重置</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>选择订单：</p>
	                           	<select class="form-control" name="" id="orderList_i"  onchange="selAfterSalesOrder(this,1);">
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单编号：</p><span id="order_orderCode_i" name="order_detail_i"></span>
	                            <input type="hidden" id="order_orderId_i" name="order_detail_i" placeholder="订单id">
	                            <input type="hidden" id="order_custMobile_i" name="order_detail_i" placeholder="客户电话">
	                            <input type="hidden" id="order_custName_i" name="order_detail_i" placeholder="客户姓名">
	                            <input type="hidden" id="order_custId_i" name="order_detail_i" placeholder="客户id">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单渠道：</p><span id="order_channel_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单来源：</p><span id="order_source_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>开始时间：</p><span id="order_start_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>结束时间：</p><span id="order_end_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单状态：</p><span id="order_status_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单类型：</p><span id="order_orderType_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p><span id="order_userName_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户手机：</p><span id="order_userMobile_i" name="order_detail_i"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                <div class="row">
	                    <div class="form-group col-xs-12">
	                    <div class="panel-content " >
							<table class="table table-hover" style="width:100%;">
				                <thead>
				                	<tr>
				                		<th></th>
										<th>序号</th>
										<th>分期类型</th>
										<th>缴费单号</th>
										<th>可退金额</th>
										<th>支持退款方式</th>
										<th>退款金额</th>
				                   </tr>
				                  </thead>
				                 <tbody id="listBodyQualityUser_i"> </tbody>
				            </table>
						</div>
	                    </div>
	                    <div class="row">&nbsp;</div>
	                    <div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>(1)如果订单下存在多笔白条/分期缴费, 一次售后只能申请一笔白条/分期的退费. </h5>
								<h5>(2)现阶段白条/分期缴费的退费仅支持退至贷方(金融公司). </h5>
								<h5>(3)退费成功后, 订单状态不更改, 即不关闭订单.</h5>
	                    </div>
	                </div>
	                <hr>
 				<div class="row" id="qualityCustomerBankCard_fq"  style="display: none;">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户人姓名：</p><input type="text" name="cardName" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>银行账号：</p><input type="text" name="bankNumber" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-2">
	                         <button type="button" class="btn btn-sm btn-primary" onclick="openQualityCustomerBankCard('order_custId_i')" id="customerBankCardSel_i">客户账号选择</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户银行：</p><input type="text" name="bankName" id=""  class="form-control"  readonly="readonly">
	                            <input type="hidden" name="bankSupportId" id="">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户支行名称：</p><input type="text" name="bankBranch" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><b style="color: red;">*</b>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="bankCityCode_fq"  onchange="" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                            </select>
	                        </label>
	                    </div>
	                </div>
	                <br/>
	                
		                 <div class="row">
		                   <div class="form-group col-xs-12">
		                        <label>
		                            <p><b style="color: red;">*</b>售后类型:</p><input class="form-control" type="text" id="order_feePostText_i" name="" readonly="readonly"></span>
		                            <input type="hidden" id="order_feePost_i" placeholder="支付类型">
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-7">
		                        <label>
		                            <p><b style="color: red;">*</b>审核部门:</p>
		                            <select class="form-control"  id="deptnameWithDept_i" style="width: 280px;">
		                            		 <option value="">...请选择...</option>
		                             </select>
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-5">
		                        <label>
		                            <p><b style="color: red;">*</b>审核人:</p>
		                             <select class="form-control"  id="manager_i" >
		                            		 <option value="">...请选择...</option>
		                             </select>
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>售后原因：</p>
		                            <select class="form-control" name="" id="order_reasonFlag_i" >
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
		                                     <option value="10" data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		                    </div>
		                     <div class="form-group col-xs-5">
		                        <label>
		                            <p>原因:</p>
		                           <textarea rows="7" cols="" id="order_reason_i" name=""  style="width: 300px;"></textarea>
		                        </label>
		                    </div>
		               	 </div>
	                </form>
					</div>
					</div>
					
				<!-- 分期服务费退费 -->
				<div class="tab-pane fade" id="addFqsxfAfter" >
				<div class="info-select clearfix" >
					<form class="form-inline" id="addFqsxfAfterForm">
					<div class="row">&nbsp;</div>
					<div class="row" id="resetAfterSalesOrder_s">
	                     <div class="form-group col-xs-5">
	                        <label>
	                            <p>订单编号：</p>
	                            <input type="text" name="" id="orderCode_s"  class="form-control" >
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>客户手机：</p>
	                            <input type="text" name="" id="userMobile_s"  class="form-control" >
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-2">
	                     <button type="button" class="btn btn-sm btn-primary"  onclick="loadAfterSalesOrder(2);"  id="queryAfterBu_s">搜索</button>
	                     <button type="button" class="btn btn-sm btn-primary"  onclick="resetAfterSalesOrder('resetAfterSalesOrder_s');">重置</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>选择订单：</p>
	                           	<select class="form-control" name="" id="orderList_s"  onchange="selAfterSalesOrder(this,2);">
	                            		 <option value="">...请选择...</option>
	                            </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单编号：</p><span id="order_orderCode_s" name="order_detail_s"></span>
	                            <input type="hidden" id="order_orderId_s" name="order_detail_s" placeholder="订单id">
	                            <input type="hidden" id="order_custMobile_s" name="order_detail_s" placeholder="客户电话">
	                            <input type="hidden" id="order_custName_s" name="order_detail_s" placeholder="客户姓名">
	                            <input type="hidden" id="order_custId_s" name="order_detail_s" placeholder="客户id">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单渠道：</p><span id="order_channel_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单来源：</p><span id="order_source_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>开始时间：</p><span id="order_start_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>结束时间：</p><span id="order_end_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单状态：</p><span id="order_status_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>订单类型：</p><span id="order_orderType_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p><span id="order_userName_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户手机：</p><span id="order_userMobile_s" name="order_detail_s"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                <div class="row">
	                    <div class="form-group col-xs-12">
	                    <div class="panel-content " >
							<table class="table table-hover" style="width:100%">
				                <thead>
				                	<tr>
				                		<th></th>
										<th>序号</th>
										<th>服务费类型</th>
										<th>缴费单号</th>
										<th>可退金额</th>
										<th>支持退款方式</th>
										<th>退款金额</th>
				                   </tr>
				                  </thead>
				                 <tbody id="listBodyQualityUser_s"> </tbody>
				            </table>
						</div>
	                    </div>
	                    <div class="row">&nbsp;</div>
	                    <div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>(1)退费成功后,订单状态不更改,即不关闭订单</h5>
								<h5>(2)如果服务费金额不符,请核对对应的所有该分期类型的服务费结算单下所有缴费是否对账、分账完成</h5>
	                    </div>
	                </div>
	                <hr>
	                <div class="row" id="qualityCustomerBankCard_s">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户人姓名：</p><input type="text" name="cardName" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>银行账号：</p><input type="text" name="bankNumber" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-2">
	                         <button type="button" class="btn btn-sm btn-primary" onclick="openQualityCustomerBankCard('order_custId_s')" id="customerBankCardSel_s">客户账号选择</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户银行：</p><input type="text" name="bankName" id=""  class="form-control"  readonly="readonly">
	                            <input type="hidden" name="bankSupportId" id="">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户支行名称：</p><input type="text" name="bankBranch" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><b style="color: red;">*</b>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="bankCityCode_s"  onchange="" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                            </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>
		                 <div class="row">
		                   <div class="form-group col-xs-12">
		                        <label>
		                            <p><b style="color: red;">*</b>售后类型:</p><input class="form-control" type="text" id="order_payTypeText_s" name="" readonly="readonly" style="width: 176px;">
		                            <input type="hidden" id="order_payType_s" placeholder="结算类型">
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-7" >
		                        <label>
		                            <p><b style="color: red;">*</b>审核部门:</p>
		                            <select class="form-control"  id="deptnameWithDept_s" style="width: 280px;">
		                            		 <option value="">...请选择...</option>
		                             </select>
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-5">
		                        <label>
		                            <p><b style="color: red;">*</b>审核人:</p>
		                             <select class="form-control"  id="manager_s" >
		                            		 <option value="">...请选择...</option>
		                             </select>
		                        </label>
		                    </div>
		                    <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>售后原因：</p>
		                            <select class="form-control" name="" id="order_reasonFlag_s" >
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
		                                     <option value="10" data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		                    </div>
		                     <div class="form-group col-xs-5">
		                        <label>
		                            <p>原因:</p>
		                           <textarea rows="7" cols="" id="order_reason_s" name=""  style="width: 300px;"></textarea>
		                        </label>
		                    </div>
		               	 </div>
	                </form>
					</div>
					</div> 

				
				<!-- 卡退费 -->
				<!-- <div class="tab-pane fade" id="addCardAfter">
				<div class="info-select clearfix" >
					<form class="form-inline" id="addCardAfterForm">
					<div class="row">&nbsp;</div>
					<div class="row" id="resetAfterSalesCard_c">
	                     <div class="form-group col-xs-5">
	                        <label>
	                            <p>卡号：</p>
	                            <input type="text" name="" id="cardNumber_c"  class="form-control" >
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>客户手机：</p>
	                            <input type="text" name="" id="custMobile_c"  class="form-control" >
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-2">
	                      <button type="button" class="btn btn-sm btn-primary"  onclick="loadAfterSalesOrder(3);"  id="queryAfterBu_c">搜索</button>
	                      <button type="button" class="btn btn-sm btn-primary"  onclick="resetAfterSalesOrder('resetAfterSalesCard_c');">重置</button>
	                    </div>
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>选择卡号：</p>
	                           	<select class="form-control" name="" id="cardList_c"  onchange="selAfterSalesOrder(this,3);">
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>卡号：</p><span id="cardNumberText_c" name="card_detail_c"></span>
	                            <input type="hidden" id="card_id_c" name="card_detail_c" placeholder="卡id">
	                            <input type="hidden" id="card_custMobile_c" name="card_detail_c" placeholder="客户电话">
	                            <input type="hidden" id="card_custName_c" name="card_detail_c" placeholder="客户姓名">
	                            <input type="hidden" id="card_custId_c" name="card_detail_c" placeholder="客户id">
	                            <input type="hidden" id="card_cardNumber_c" name="card_detail_s" placeholder="卡号">
	                            <input type="hidden" id="card_salesType_c" name="card_detail_c" placeholder="是否赠卡">
	                            <input type="hidden" id="card_isGive_c" name="card_detail_c" placeholder="是否标记赠卡">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户手机：</p><span id="custMobileText_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                      <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p><span id="userName_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>推卡人电话：</p><span id="recommendInst_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>推卡人：</p><span id="recommendName_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>卡状态：</p><span id="cardStatus_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>办卡日期：</p><span id="openCardDate_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>有效期限：</p><span id="validDate_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-4">
	                        <label>
	                            <p>购卡来源：</p><span id="isGJTK_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>卡面值(元)：</p><span id="totalMoney_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>是否赠卡：</p><span id="salesTypeText_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>标记赠卡：</p><span id="isGiveText_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4"></div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>推卡：<br>(家政家园卡)</p><span id="isPushMoney_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>卡余额(元)：</p><span id="cardbalance_c" name="card_detail_c"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                    <div class="panel-content " >
							<table class="table table-hover" style="width:100%;">
				                <thead>
				                	<tr>
										<th>序号</th>
										<th>管家姓名</th>
										<th>提成金额</th>
										<th>扣除提成后管家账户余额</th>
				                   </tr>
				                  </thead>
				                 <tbody id="listBodyQualityUser_c">
				                  
				                 </tbody>
				            </table>
						</div>
	                    </div>
	                    <div class="row">&nbsp;</div>
	                    <div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>(1)赠卡/标记赠卡不可退卡</h5>
								<h5>(2)如果扣除提成后管家账号余额为负，代表管家已提成、提现，此种情况必须与结算中心或财务进行确认并在售后原因中说明，否则驳回处理</h5>
	                    </div>
	                </div>
	                <div class="row">&nbsp;</div>
	                <hr>

	                <div class="row" id="qualityCustomerBankCard_c">
	                	<div class="form-group col-xs-12">
	                        <label>
	                            <p><b style="color: red;">*</b>退款金额(元)：</p><input type="text" name="card_detail_c" id="card_money"  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户人姓名：</p><input type="text" name="cardName" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>银行账号：</p><input type="text" name="bankNumber" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-2">
	                         <button type="button" class="btn btn-sm btn-primary" onclick="openQualityCustomerBankCard('card_custId_c')" id="customerBankCardSel_c">客户账号选择</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户银行：</p><input type="text" name="bankName" id=""  class="form-control"  readonly="readonly">
	                            <input type="hidden" name="bankSupportId" id="">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户支行名称：</p><input type="text" name="bankBranch" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><b style="color: red;">*</b>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="bankCityCode_c"  onchange="" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                            </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                 <div class="row">
	                    <div class="form-group col-xs-7">
	                        <label>
	                            <p><b style="color: red;">*</b>审核部门</p>
	                            <select class="form-control" name="deptnameWithDept" id="deptnameWithDept_c" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>审核人：</p>
	                            <select class="form-control" name="" id="manager_c" >
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>售后原因：</p>
		                            <select class="form-control" name="" id="card_reasonFlag_c" >
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
		                                     <option value="10" data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		               </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>原因：</p>
	                          <textarea rows="7" cols="" id="card_reason_c" name=""  style="width: 300px;"></textarea>
	                        </label>
	                    </div>
	                </div>
	                </form>
					</div>
					</div> -->

					 <!-- 解决方案退费 -->
					<div class="tab-pane fade" id="addActivitiesAfter">
					<div class="info-select clearfix" >
					<form class="form-inline" id="addActivitiesAfterForm">
					<div class="row">&nbsp;</div>
					<div class="row" id="resetAfterSalesOrder_a">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>解决方案：</p>
	                            <input type="text" name="" id="solutionId"  class="form-control" >
	                            <input type="hidden" id="solution_id" name="solution_detail" placeholder="解决方案id">
	                            <input type="hidden" id="solution_custMobile" name="solution_detail" placeholder="客户电话">
	                            <input type="hidden" id="solution_custName" name="solution_detail" placeholder="客户姓名">
	                            <input type="hidden" id="solution_custId" name="solution_detail" placeholder="客户id">
	                            <input type="hidden" id="solution_give" name="solution_detail" placeholder="是否赠送">
	                            <input type="hidden" id="solution_allMoney" name="solution_detail" placeholder="总金额">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>客户手机：</p>
	                            <input type="text" name="" id="solutionMobile"  class="form-control" >
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-2">
	                      <button type="button" class="btn btn-sm btn-primary"  onclick="loadAfterSalesOrder(4);"  id="queryAfterBu_a">搜索</button>
	                      <button type="button" class="btn btn-sm btn-primary"  onclick="resetAfterSalesOrder('resetAfterSalesOrder_a');">重置</button>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p>选择解决方案：</p>
	                           	<select class="form-control" name="" id="solutionSelect" onchange="selAfterSalesOrder(this,4)" >
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                </div>
	                <hr>

	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>方案单号：</p><span id="solutionIdt" name="solution_detail"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>下单时间：</p><span id="solutionTime" name="solution_detail"></span>
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-4">
	                        <label>
	                            <p>当前状态：</p><span id="solutionStatus" name="solution_detail"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户手机：</p><span id="solutionMobilet" name="solution_detail"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>客户姓名：</p><span id="solutionUserName" name="solution_detail"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>下单金额：</p><span id="solutionTotalPay" name="solution_detail"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>录入部门：</p><span id="solutionRechargeDept" name="solution_detail"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>录入人：</p><span id="solutionRechargeBy" name="solution_detail"></span>
	                        </label>
	                    </div>
	                </div>
	                <hr>
	                <div class="row">
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>关联订单号：</p><span id="solutionCode" name="solution_detail"></span>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-4">
	                        <label>
	                            <p>是否赠送：</p><span id="giveSolution" name="solution_detail"></span>
	                        </label>
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-8">
	                    <div class="panel-content " >
							<table class="table table-hover">
				                <thead>
				                	<tr>
										<th>序号</th>
										<th>释放排期单号</th>
										<th>锁定金额</th>
				                   </tr>
				                  </thead>
				                 <tbody id="solutionList">
				                 
				                </tbody>
				            </table>
						</div>
	                    </div>
	                    <div class="form-group col-xs-4">
	                   		<ul>
			                   	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方案余额&nbsp;&nbsp;&nbsp;<span id='solutionCardbalance' name="solution_detail"></span></li>
			                    <li>+&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排期释放&nbsp;&nbsp;&nbsp;<span id='allOncePrirce' name="solution_detail"></span></li>
			                    <li>-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;他人代收&nbsp;&nbsp;&nbsp;<span id='otherMoney' name="solution_detail"></span></li>
			                    -------------------------------------
			                    <li>=&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可退总额&nbsp;&nbsp;&nbsp;<span id='allMoney' name="solution_detail"></span></li>
	                   		</ul>
	                    </div>
	                </div>
	                <hr>
	                 <div class="row">&nbsp;</div>
	                    <div class="form-group col-xs-12" style="color: red">
	                    		<h5>提示：</h5>
						        <h5>(1)售后单创建成功后, 系统会自动取消所有尚未创建订单的排期, 已生成执行订单的订单不做处理</h5>
								<h5>(2)执行订单的售后请走普通售后流程</h5>
								<h5>(3)退费总额 = 解决方案余额 + 所有释放的排期锁定金额 - 所含他人代收缴费部分</h5>
								<h5>(4)提交售后单后, 解决方案取消, 排期不能恢复, 请谨慎操作!</h5>
	                 </div>
	                
						<div class="row">&nbsp;</div>
						<hr>
	                <div class="row" id="qualityCustomerBankCard_so">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户人姓名：</p><input type="text" name="cardName" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>银行账号：</p><input type="text" name="bankNumber" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                   <div class="form-group col-xs-2">
	                         <button type="button" class="btn btn-sm btn-primary" onclick="openQualityCustomerBankCard('solution_custId')" id="customerBankCardSel_a">客户账号选择</button>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户银行：</p><input type="text" name="bankName" id=""  class="form-control"  readonly="readonly">
	                            <input type="hidden" name="bankSupportId" id="">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>开户支行名称：</p><input type="text" name="bankBranch" id=""  class="form-control"  readonly="readonly">
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
	                        <label>
	                            <p><b style="color: red;">*</b>开户城市：</p>
	                            <select class="form-control" name="bankCityCode" id="bankCityCode_so"  onchange="" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                            </select>
	                        </label>
	                    </div>
	                     <div class="form-group col-xs-12">
	                 		<label>
	                            <p>可退金额：</p><span id="releaseMoney" name="solution_detail"></span>元
	                        </label>
	                </div>
	                </div>
	                <hr>

	                 <div class="row">
	                    <div class="form-group col-xs-7">
	                        <label>
	                            <p><b style="color: red;">*</b>审核部门</p>
	                            <select class="form-control" name="" id="deptnameWithDept_so" style="width: 280px;">
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p><b style="color: red;">*</b>审核人：</p>
	                            <select class="form-control" name="" id="manager_so" >
	                            		 <option value="">...请选择...</option>
	                             </select>
	                        </label>
	                    </div>
	                    <div class="form-group col-xs-12">
		                        <label>
		                            <p><span style="color:red">*</span>售后原因：</p>
		                            <select class="form-control" name="" id="solution_reasonFlag" >
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
		                                     <option value="10" data-quality="其它原因">其它原因</option>
		                             </select>
		                        </label>
		                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-group col-xs-5">
	                        <label>
	                            <p>原因：</p>
	                           <textarea  rows="7" cols="" id="solution_reason" style="width: 300px;"></textarea>
	                        </label>
	                    </div>
	                </div>
	                </form>
					</div>
					</div>
				</div>
				</div>
				<div class="modal-footer">
		        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">取消</button>
		      	<button type="submit" class="btn btn-sm btn-primary" id="qualityServerOneBtn" onclick="insertAfterSales();" data-loading-text="保存中...">保存</button>
		      </div>
		  </div>
	    </div>
	</div>

<script type="text/javascript" src="${ctx}/js/afterSale/insertAfterSalesNew.js"></script>
<script>
    	var afterid = '${param.afterSalesId}';//售后id(注:修改使用)
    	var payFeeId = '${param.payFeeId}';//缴费id(注:分期退费、分期服务费退费修改使用)
    	var afterSalesKind  = '${param.afterSalesKind}';//售后类型(注:修改使用)
    	var operationType  = '${param.operationType}';//1:新增操作 2:修改操作
    	$("#operationType"+operationType).show();
    $(function(){
    	var optionDeptHtml = querydeptnameWithDept();
    	var optionCityHtml = queryUserBankCity();
      	loadAfterSalesDept("deptnameWithDept_i","manager_i",optionDeptHtml);
      	loadAfterSalesDept("deptnameWithDept_s","manager_s",optionDeptHtml);
      	loadAfterSalesDept("deptnameWithDept_c","manager_c",optionDeptHtml);
      	loadAfterSalesDept("deptnameWithDept_a","manager_a",optionDeptHtml);
      	loadAfterSalesDept("deptnameWithDept_so","manager_so",optionDeptHtml);
      	loadAfterSalesCity("bankCityCode_s",optionCityHtml);
      	loadAfterSalesCity("bankCityCode_c",optionCityHtml);
      	loadAfterSalesCity("bankCityCode_a",optionCityHtml);
      	loadAfterSalesCity("bankCityCode_so",optionCityHtml);
		loadAfterSalesCity("bankCityCode_fq",optionCityHtml);
    	if(operationType == '2'){
    		//注:修改加载
	    	if(afterSalesKind  == '11' || afterSalesKind  == '17' || afterSalesKind  == '18'||afterSalesKind  == '21'){
		    	var tab = $("#afterSales_myTab").find('a[data-toggle="tab"]');
	    		tab.eq(0).tab('show');//激活标签页
	    		tab.filter(":not(:eq(0))").prop("disabled",true);//禁用其他标签页
	    		loadAfterSales(afterSalesKind);
	    		disabledInput(afterSalesKind);
	    	}else if(afterSalesKind  == '13' || afterSalesKind  == '14' || afterSalesKind  == '15'){
	    		var tab = $("#afterSales_myTab").find('a[data-toggle="tab"]');
	    		tab.eq(1).tab('show');//激活标签页
	    		tab.filter(":not(:eq(1))").prop("disabled",true);//禁用其他标签页
	    		loadAfterSales(afterSalesKind);
	    		disabledInput(afterSalesKind);
	    	}
    	}
    	
    });
    //显示隐藏是否退至客户
    function selectType(thiz){
    	var confirmText='[请注意, 未与结算中心确认的情况下, 选择退至客户必然会被驳回!]';
    	if(thiz.value == 2){
    		$.confirm({text:confirmText,callback:function(r){
    			if(r){
    				$("#qualityCustomerBankCard_fq").show();
    			}else{
    				$(thiz).val("1");
    			}
    		}})
    	}else{
    		$("#qualityCustomerBankCard_fq").hide();
    	}
    }
    

    /**选择客户账户*/
    function openQualityCustomerBankCard(cusTagId){
    	//var cusId = 543118762529927;//测试数据
    	var cusId = $("#"+cusTagId).val();
    	if(cusId){
	    	openModule('${ctx}/jsp/quality/qualityCustomerBankCard.jsp',{"cusId":cusId},null,{'width':'50%'},'qualityCustomerBankCard')
    	}else{
    		$.alert({millis:5000,text:"未获取到客户信息!"});
    	}
    }

    
	/**
	* 加载查询审核部门
	*/
	function loadAfterSalesDept(deptTagId,manangerTagId,optionHtml){
    	if(deptTagId && manangerTagId){
    		$("#"+deptTagId).html(optionHtml);
            $("#"+deptTagId).checkSelect(function(){
            	queryguanjia(deptTagId,manangerTagId);
            });
    	}
	}
    
	/**
	* 查询审核部门
	* @param
	* @returns
	*/
	function querydeptnameWithDept(){
		var html = "<option style='color:blue;' value=''>...请选择...</option>";
	    var ctx=$("#ctx").val();
			    $.ajax({
			        url:ctx+"/orderbase/querydeptnameWithDept",
			        data:{},
			        type:'post',
			        async:false,
			        dataType:"json",
			        success:function(data){
			        	if(data.msg == "00"){
				            $.each(data.list,function(i,v){
				                html +="<option value='" +v.dkey +"' >"+v.dvalue+"</option>";
				            })
			        	}
			        }
			    })
		return html;		    
	}

	/**查询审批人*/
	function queryguanjia(deptTagId,manangerTagId){
			var deptId =  $("#"+deptTagId).val();
			if(deptId){
				$.ajax({
					url:ctx+"/orderbase/queryguanjia",
					data : {
						deptId : deptId
					},
					type : 'post',
					async : false,
					dataType : "json",
					success : function(data) {
						var html =  "<option style='color:blue;' value='' >...请选择...</option>";
						if (data.msg == "00") {
							$.each(data.list,function(i,v){
								html += "<option value='" + v.id + "'>"+v.realName+"（"+v.userName+"）</option>";
							});
						}else if(data.msg == "02"){
							$.alert({millis:5000,text:"当前部门无管家"});
						}
						$("#"+manangerTagId).html(html);
						$("#manager_i").siblings("div").remove();
						$("#manager_s").siblings("div").remove();
						$("#manager_so").siblings("div").remove();
						$("#"+manangerTagId).checkSelect();
					}
				});
			}
	}

	/**加载售后列表
	* @param type 自定义类型
	*/
	function loadAfterSalesOrder(type){
	    var data = {};
	    //自定义类型type(1:分期退费、2:分期服务费退费、3:卡退费、4:解决方案退费)
	    data.loginLevel=99;
		if(!type){
			return;
		}else if(type == 1){
				var userMobile = $("#userMobile_i").val();
				var orderCode = $("#orderCode_i").val();
				if(!userMobile && !orderCode){
					$.alert({millis:5000,text:"请输入查询条件"});
					return;
				}
				data.userMobile = userMobile;
				data.orderCode = orderCode;
				data.type = type;
				//data.loginLevel=99;
				var html = queryAfterSalesOrder(data)
				$("#orderList_i").html(html);
				if($("#orderList_i").find("option").size() > 1){
					$("#orderList_i").find("option:eq(1)").prop("selected",true).trigger("change");
				}else{
					$("#orderList_i").find("option:eq(0)").prop("selected",true).trigger("change");
				}
		}else if(type == 2){
				var userMobile = $("#userMobile_s").val();
				var orderCode = $("#orderCode_s").val();
				if(!userMobile && !orderCode){
					$.alert({millis:5000,text:"请输入查询条件"});
					return;
				}
				data.userMobile = userMobile;
				data.orderCode = orderCode;
				data.type = type;
				//data.loginLevel=99;
				var html = queryAfterSalesOrder(data)
				$("#orderList_s").html(html);
				if($("#orderList_s").find("option").size() > 1){
					$("#orderList_s").find("option:eq(1)").prop("selected",true).trigger("change");
				}else{
					$("#orderList_s").find("option:eq(0)").prop("selected",true).trigger("change");
				}
		}else if(type == 3){
			var custMobile = $("#custMobile_c").val();
			var cardNumber = $("#cardNumber_c").val();
			if(!custMobile && !cardNumber){
				$.alert({millis:5000,text:"请输入查询条件"});
				return;
			}
			data.custMobile = custMobile;
			data.cardNumber = cardNumber;
			data.type = type;
			var html = queryAfterSalesCard(data)
			$("#cardList_c").html(html);
			if($("#cardList_c").find("option").size() > 1){
				$("#cardList_c").find("option:eq(1)").prop("selected",true).trigger("change");
			}else{
				$("#cardList_c").find("option:eq(0)").prop("selected",true).trigger("change");
			}
		}else if(type == 4){
			var custMobile = $("#solutionMobile").val();
			var solutionId = $("#solutionId").val();
			if(!custMobile && !solutionId){
				$.alert({millis:5000,text:"请输入查询条件"});
				return;
			}
			data.payerMobile = custMobile;
			data.solutionCode = solutionId;
			data.type = type;
			var html = querySolutionOrder(data)
			$("#solutionSelect").html(html);
			if($("#solutionSelect").find("option").size() > 1){
				$("#solutionSelect").find("option:eq(1)").prop("selected",true).trigger("change");
			}else{
				$("#solutionSelect").find("option:eq(0)").prop("selected",true).trigger("change");
			}
		}
		$("#qualityCustomerBankCard_fq").hide();
			
	}
	
	/**查询订单*/
	function queryAfterSalesOrder(data){
		var html = "<option value=''>...请选择...</option>";
	    $.ajax({
	        url:ctx+"/afterSalesNew/queryOrder",
	        data:data,
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	           if(data.msg == "00"){
	        	   $.each(data.list,function(i,v){
		        	   html += " <option value='"+v.id+"' data-order='"+JSON.stringify(v)+"'>"+v.orderCode+"</option>";
	        	   })
	           }else{
	        	   $.alert({millis:5000,text:"无符合条件订单"});
	           }
	        }
	    })
		return html;	
	}

	/**分期售后-清空查询条件*/
	function resetAfterSalesOrder(tagId,selTagId){
		if(tagId){
			$("#"+tagId).find("input[type=text],select,textarea").val("");
			$("#"+tagId).find("select").html("<option value=''>...请选择...</option>").trigger("change");
		}
	}
    /**分期售后-查询缴费列表*/
    function queryFqAfterSalesPayFee(){
        var orderId = $("#orderList_i").val();//售后id
        if(orderId){
            var html = "";
            $.ajax({
                url:ctx+"/afterSalesNew/findPayFeeDetail",
                data:{
                    "orderId":orderId,
                    "payFeeId":payFeeId||""
                },
                type:'post',
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.msg == "00"){
                        var feePostText = {"20250027":"唯品会白条","20250033":"海金保理","20250031": "招行分期","20250054":"汇嘉分期"};
                        var feePostTypeText = {"20250027":"退至唯品会","20250033":"退至海金保理","20250031": "退至招行","20250054":"退至汇嘉"};
                        $.each(data.list,function(i,v){
                            var trId = "vph_fee"+i;
                            var seId = "selType" + i;
                            var feePost = "";
                            if(v.feePost == '20250029'){
                            	feePost = v.sourceFeePost;//原类型
                            }else{
                            	feePost = v.feePost;
                            }
                            html += "<tr data-payfee='"+JSON.stringify(v)+"' data-trId='"+trId+"' onclick='selAfterSalesPayFee(this,1)'>";
                            html += "<td><input type='radio' id='' data-trId='"+trId+"' data-seId='"+seId+"' data-payFeeId='"+v.payFeeId+"' name='fqAfterSalesPayFee_i'></td>";
                            html += "<td>"+(i+1)+"</td>";
                            html += "<td>"+feePostText[feePost]||""+"</td>";
                            html += "<td>"+v.payFeeId||""+"</td>";
                            html += "<td>"+v.returnAmount||0+"</td>";
                            /* html += "<td>"+feePostTypeText[feePost]||""+"</td>"; */
                            html += "<td><select style='width:100px' id='"+seId+"'  onchange='selectType(this)'><option value='1'>"+feePostTypeText[feePost]+"</option><option value='2'>客户</option><select/></td>";
                            html += "<td><input type='text' id='"+trId+"' data-returnAmount='"+(v.returnAmount||0)+"' name='vph_fee_i' value=''  onkeyup='checkNumber(this)' onafterpaste='checkNumber(this)' >元</td></tr>";
                        })
                    }else{
                        html = "<tr onclick='selAfterSalesPayFee(this,1)'><td colspan='7' class='table-empty' >暂无数据</td></tr>";
                    }
                    $("#listBodyQualityUser_i").html(html);
                }
            })
        }else{
            $("#listBodyQualityUser_i").html("<tr onclick='selAfterSalesPayFee(this,1)'><td colspan='7' class='table-empty' >暂无数据</td></tr>");
        }
        radioColor("#listBodyQualityUser_i > tr");/*表格单选*/
        trColor("#native_tbody > tr");/*表格点击行高亮*/
        $("#listBodyQualityUser_i").find("tr:first").trigger("click");
    }
    
    /**分期服务费售后-查询缴费列表*/
    function queryFqsxfAfterSalesPayFee(){
        var orderId = $("#orderList_s").val();//售后id
        var bType=null;
        if(afterSalesKind==13){
        	bType=112;
        }else if(afterSalesKind==14){
        	bType=118;
        }else if(afterSalesKind==15){
        	bType=117;
        }
        if(orderId){
            var html = "";
            $.ajax({
                url:ctx+"/afterSalesNew/queryServiceCharge",
                data:{
                    "orderId":orderId,
                    "payFeeId":payFeeId||"",
                    "bType":bType
                },
                type:'post',
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.msg == "00"){
                        var accText = {"8":"唯品会白条分期服务费","11":"海金保理白条分期服务费","12": "招行分期分期服务费"};
                        var accTypeText = {"8":"退至客户指定账户","11":"退至客户指定账户","12": "退至客户指定账户"};
                        $.each(data.afterSalesNews,function(i,v){
                            var trId = "vph_fee"+i;
                            html += "<tr data-payfee='"+JSON.stringify(v)+"' data-trId='"+trId+"'  onclick='selAfterSalesPayFee(this,2)'>";
                            html += "<td><input type='radio' id='' data-trId='"+trId+"' data-payFeeId='"+v.payFeeId+"' name='fqAfterSalesPayFee_s'></td>";
                            html += "<td>"+(i+1)+"</td>";
                            html += "<td>"+accText[v.payType||""]||""+"</td>";
                            html += "<td>"+v.payFeeId||""+"</td>";
                            html += "<td>"+v.returnAmount||0+"</td>";
                            html += "<td>"+accTypeText[v.payType]||""+"</td>";
                            html += "<td><input type='text' id='"+trId+"' data-returnAmount='"+(v.returnAmount||0)+"' name='vph_fee_s' value=''  onkeyup='checkNumber(this)' onafterpaste='checkNumber(this)' >元</td></tr>";
                        })
                    }else{
                        html = "<tr onclick='selAfterSalesPayFee(this,2)'><td colspan='7' class='table-empty' >暂无数据</td></tr>";
                    }
                    $("#listBodyQualityUser_s").html(html);
                }
            })
        }else{
            $("#listBodyQualityUser_s").html("<tr onclick='selAfterSalesPayFee(this,2)'><td colspan='7' class='table-empty' >暂无数据</td></tr>");
        }
        radioColor("#listBodyQualityUser_s > tr");/*表格单选*/
        trColor("#native_tbody > tr");/*表格点击行高亮*/
        $("#listBodyQualityUser_s").find("tr:first").trigger("click");
    }

	/**售后-选择*/
	function selAfterSalesOrder(thiz,type){
		if(!type){
			return;
		}else if(type == 1){
			var t = $(thiz);
			if(t.val() != null && t.val() != ''){
				var order = t.find("option:selected").data("order");
				if(order){
					$("#order_orderCode_i").html(order.orderCode||"");
					$("#order_channel_i").html(order.channelText||"");
					$("#order_source_i").html(order.sourceText||"");
					$("#order_start_i").html(order.startTime||"");
					$("#order_end_i").html(order.endTime||"");
					$("#order_status_i").html(order.statusText||"");
					$("#order_orderType_i").html(order.typeText||"");
					$("#order_userName_i").html(order.userName||"");
					$("#order_userMobile_i").html(order.userMobile||"");
					$("#order_orderId_i").val(order.id||"");
					$("#order_custMobile_i").val(order.userMobile||"");
					$("#order_custName_i").val(order.userName||"");
					$("#order_custId_i").val(order.userId||"");
				}else{
					$("span[name='order_detail_i']").html("");
					$("input[name='order_detail_i']").val("");
				}
			}else{
				$("span[name='order_detail_i']").html("");
				$("input[name='order_detail_i']").val("");
			}
			queryFqAfterSalesPayFee();
		}else if(type == 2){
			var t = $(thiz);
			if(t.val() != null && t.val() != ''){
				var order = t.find("option:selected").data("order");
				if(order){
					$("#order_orderCode_s").html(order.orderCode||"");
					$("#order_channel_s").html(order.channelText||"");
					$("#order_source_s").html(order.sourceText||"");
					$("#order_start_s").html(order.startTime||"");
					$("#order_end_s").html(order.endTime||"");
					$("#order_status_s").html(order.statusText||"");
					$("#order_orderType_s").html(order.typeText||"");
					$("#order_userName_s").html(order.userName||"");
					$("#order_userMobile_s").html(order.userMobile||"");
					$("#order_orderId_s").val(order.id||"");
					$("#order_custMobile_s").val(order.userMobile||"");
					$("#order_custName_s").val(order.userName||"");
					$("#order_custId_s").val(order.userId||"");
				}else{
					$("span[name='order_detail_s']").html("");
					$("input[name='order_detail_s']").val("");
				}
			}else{
				$("span[name='order_detail_s']").html("");
				$("input[name='order_detail_s']").val("");
			}
			queryFqsxfAfterSalesPayFee();
		}else if(type == 3){
				var t = $(thiz);
				if(t.val() != null && t.val() != ''){
					var card = t.find("option:selected").data("card");
					if(card){
						var cardStatusText = {'101':'未销售','102':'已登记','103':'已激活','104':'已绑定','105':'已过期','201':'未销售','202':'已激活','203':'已绑定','204':'已过期','206':'已失效'};
						var isGJTKText = {'2':'客户购买','1':'家政家园'};
						var salesTypeText = {'1':'否','2':'是'};////是否赠卡-销售方式(1:销售、 2:赠送)
						var isGiveText = {'1':'是','0':'否'};//是否标记赠卡
						var isPushMoneyText = {'1':'是','2':'否'};//是否家政家园推卡
						$("#cardNumberText_c").html(card.cardNumber||"");//卡号
						$("#custMobileText_c").html(card.custMobile||"");//客户手机
						$("#userName_c").html(card.userName||"");//客户姓名
						$("#recommendName_c").html(card.recommendName||"");//推卡人
						$("#recommendInst_c").html(card.recommendInst||"");//推卡人电话
						$("#cardStatus_c").html(cardStatusText[card.cardStatus]||"");//卡状态
						$("#openCardDate_c").html(card.openCardDate||"");//办卡日期
						$("#validDate_c").html(card.validDate||"");//有效期限
						$("#totalMoney_c").html(card.totalMoney||0);//卡面值
						$("#cardbalance_c").html(card.cardbalance||0);//卡余额
						$("#isGJTK_c").html(card.channelName);//购卡来源
						$("#salesTypeText_c").html(salesTypeText[card.salesType]||"");//是否赠卡
						$("#isGiveText_c").html(isGiveText[card.isGive]||"");//是否标记赠卡
						$("#isPushMoney_c").html(isPushMoneyText[card.isGJTK]||"");//是否家政家园推卡
						$("#card_id_c").val(card.cardId||"");//卡id
						$("#card_custMobile_c").val(card.custMobile||"");//用户手机号
						$("#card_custName_c").val(card.userName||"");//用户姓名
						$("#card_custId_c").val(card.custId||"");//用户id 
						$("#card_money").val(card.cardbalance||0);//退款金额
						$("#card_salesType_c").val(card.salesType||"");//是否赠卡
						$("#card_isGive_c").val(card.isGive);//是否标记赠卡
						$("#card_cardNumber_c").val(card.cardNumber||"");//卡号
						/**提成列表*/
						if(card.payMentId){
						var html = "<tr><td>1</td>";
						    html += "<td>"+(card.personnelName||"")+"</td>";
						    html += "<td>"+(card.gjMoney||"")+"</td>";
						    html += "<td>"+(card.gjBalance||"")+"</td></tr>";
							$("#listBodyQualityUser_c").html(html||"");
						}else{
							$("#listBodyQualityUser_c").html("<tr><td colspan='10'>暂无数据</td></tr>");
						}
						radioColor("#listBodyQualityUser_c > tr");/*表格单选*/
				        trColor("#native_tbody > tr");/*表格点击行高亮*/
					}else{
						$("span[name='card_detail_c']").html("");
						$("input[name='card_detail_c']").val("");
						$("#listBodyQualityUser_c").html("");
					}
			}else{
				$("span[name='card_detail_c']").html("");
				$("input[name='card_detail_c']").val("");
				$("#listBodyQualityUser_c").html("");
			}
		}else if(type == 4){ //解决方案 
			var t = $(thiz);
			if(t.val() != null && t.val() != ''){
				var solution = t.find("option:selected").data("solution");
				if(solution){
					var solutionStatusText = {'1':'新单','2':'已受理','3':'执行中','4':'暂停中','5':'已完成','6':'已取消','7':'待受理'};
					var solutionType = {'20120006':'推荐方案','20120005':'定制解决方案','20120007':'智能方案','20120008':'预存赠送方案'};
					$("#solutionIdt").html(solution.solutionCode||"");//解决方案单号
					$("#solutionTime").html(solution.createTime||"");//创建时间
					$("#solutionStatus").html(solutionStatusText[solution.solutionStatus]||"");//解决方案状态
					$("#solutionMobilet").html(solution.payerMobile||"");//电话
					$("#solutionUserName").html(solution.payerName||"");//姓名
					$("#solutionTotalPay").html(solution.totalPay||"");//总金额
					$("#solutionRechargeDept").html(solution.rechargeDeptText||"");//录入部门
					$("#solutionRechargeBy").html(solution.rechargeByText||"");//录入人
					$("#solutionCode").html(solution.orderCode||"");//关联订单号
					$("#giveSolution").html(solution.solutionType == 20120008 ? '是':'否'||"");//是否赠送卡
					$("#solution_id").val(solution.id||""); //解决方案id
					$("#solution_custMobile").val(solution.payerMobile||"");
					$("#solution_custName").val(solution.payerName||"");
					$("#solution_custId").val(solution.custId||"");
					$("#solution_give").val(solution.solutionType||"");
					
				}else{
					 $("span[name='solution_detail']").html("");
					$("input[name='solution_detail']").val("");
					$("#solutionList").html(""); 
				} 
		}else{
			 $("span[name='solution_detail']").html("");
			$("input[name='solution_detail']").val("");
			$("#solutionList").html("");
		}
			querySolutionSchedule();
			querySolutionMoney();//查询可退金额
		}
	}

	/**分期售后-选择缴费*/
	function selAfterSalesPayFee(thiz,type){
		if(!type){
			return;
		}else if(type == 1){
			var payfee = $(thiz).data("payfee");
			if(payfee){
				var feePostText = {"20250027":"唯品会白条售后","20250033":"海金保理售后","20250031": "招行分期售后","20250054":"汇嘉分期售后"};
				var feePost = "";
                if(payfee.feePost == '20250029'){
                	feePost = payfee.sourceFeePost||"";
                }else{
                	feePost = payfee.feePost||"";
                }
				$("#order_feePostText_i").val(feePostText[feePost]||"");
				$("#order_feePost_i").val(feePost||"");
			}else{
				$("#order_feePostText_i").val("");
				$("#order_feePost_i").val("");
			}
		}else if(type == 2){
			var payfee = $(thiz).data("payfee");
			if(payfee){
				var accText = {"8":"唯品会白条分期服务费售后","11":"海金保理分期服务费售后","12": "招行分期分期服务费售后"};
				var payType = payfee.payType;
				$("#order_payTypeText_s").val(accText[payType]||"");
				$("#order_payType_s").val(payType||"");
			}else{
				$("#order_payTypeText_s").val("");
				$("#order_payType_s").val("");
			}
		}
	}

	/**分期售后-保存*/
	var post_flag = false;
	function insertAfterSales(){
		debugger
		if(post_flag){
			return;
		}
		post_flag = true;
		/*1确定售后类型、退费对象*/
		var afterSalesKind = '',refundObject = '';//售后类型、退费对象
		var tabPane =  $("#myTabContent_insertAfterSalesNew").find("div.tab-pane.active");//当前选择标签
		if(tabPane.size() == 1){
			var tabPaneId = tabPane.prop("id");
			if(tabPaneId == 'addFqAfter'){
				var orderList = $("#orderList_i").val();
				var feePost = $("#order_feePost_i").val();//支付类型
				if(!orderList){
					$.alert({millis:5000,text:"请选择订单"});
					post_flag = false;
					return;
				}
				if(!feePost){
					$.alert({millis:5000,text:"请选择要退分期缴费"});
					post_flag = false;
					return;
				}else if(feePost == '20250027'){
					afterSalesKind = '11';//唯品会
					refundObject = '1';
				}else if(feePost == '20250033'){
					afterSalesKind = '17';//海金保理
					refundObject = '3';
					/** 2019-01-18 周鑫 判断有海金缴费可以  **/
					var orderCodeA = $("#order_orderCode_i").text();
					var checkinputA = $("#listBodyQualityUser_i").find("input[name='fqAfterSalesPayFee_i']:checked");
					var payfeeidA = checkinputA.attr("data-payFeeId");
					var result=agreementSeaGlodPayFree(payfeeidA,orderCodeA);
					if(result==1){
						$.alert({text:"该订单的海金放款金额正在用作发放服务费，且尚未生成客户还款账单，不可发起售后！"});
						return;
					}
					/** 2019-01-18 周鑫  **/
				}else if(feePost == '20250031'){
					afterSalesKind = '18';//招行分期
					refundObject = '4';
				}else if(feePost == '20250054'){
					afterSalesKind = '21';//汇嘉分期
					refundObject = '5';
				}
			}else if(tabPaneId == 'addFqsxfAfter'){
				var orderList = $("#orderList_s").val();
				var payType = $("#order_payType_s").val();//支付类型
				if(!orderList){
					$.alert({millis:5000,text:"请选择订单"});
					post_flag = false;
					return;
				}
				if(!payType){
					$.alert({millis:5000,text:"请选择要退分期缴费"});
					post_flag = false;
					return;
				}else if(payType == '8'){
					afterSalesKind = '13';//唯品会
					refundObject = '2';
				}else if(payType == '11'){
					afterSalesKind = '14';//海金保理
					refundObject = '2';
				}else if(payType == '12'){
					afterSalesKind = '15';//招行分期
					refundObject = '2';
				}
			}else if(tabPaneId == 'addCardAfter'){
				afterSalesKind = '16';//卡售后
				refundObject = '2';
			}else if(tabPaneId == 'addActivitiesAfter'){
				afterSalesKind = '9';//解决方案售后
				refundObject = '2';
			}
		}
		/*2根据售后类型,获取需提交的数据*/
		var data = {};
		var confirmText = "确认提交？";//保存提示语
		if(!afterSalesKind){
			$.alert({millis:5000,text:"未获取售后类型"});
			post_flag = false;
			return;
		}else if(afterSalesKind == '11' || afterSalesKind == '17' || afterSalesKind == '18'||afterSalesKind == '21'){
			confirmText = "请确认退款中不包含服务人员应发服务人员服务费,否则一旦退款成功,将由责任人自行追回服务人员服务费款项";
			var orderId = $("#order_orderId_i").val();
			var checkinput = $("#listBodyQualityUser_i").find("input[name='fqAfterSalesPayFee_i']:checked");
			var feeId = checkinput.attr("data-trId");
			var seId = checkinput.attr("data-seId");
			var payfeeid = checkinput.attr("data-payFeeId");
			var reason = $("#order_reason_i").val();
			var reasonFlag = $("#order_reasonFlag_i").val();
			var custMobile = $("#order_custMobile_i").val();
			var custName = $("#order_custName_i").val();
			var vphFee = $("#"+feeId).val();//要退金额
			var returnAmount = $("#"+feeId).attr("data-returnAmount");//可退金额
			var oldMoney = $("#"+feeId).attr("data-money");
			var approveDept = $("#deptnameWithDept_i").val();//审核部门
			var approveBy = $("#manager_i").val();//审核人
			var flag=$("#"+seId).val(); //是否是客户 客户2
			if(flag==2){
				var bank = $("#qualityCustomerBankCard_fq");
				var bankUserName = bank.find("input[name=cardName]").val();
				var bankMainName = bank.find("input[name=bankBranch]").val();
				var bankCard = bank.find("input[name=bankNumber]").val();
				var bankName = bank.find("input[name=bankName]").val();
				var bankSupportId = bank.find("input[name=bankSupportId]").val();
				var bankCityCode = bank.find("select[name=bankCityCode]").val();
				
				if(!bankUserName){
					$.alert({millis:5000,text:"开户人姓名不能为空"});
					post_flag = false;
					return;
				}
				if(!bankMainName){
					$.alert({millis:5000,text:"开户支行名称不能为空"});
					post_flag = false;
					return;
				}
				if(!bankCard){
					$.alert({millis:5000,text:"银行账号不能为空"});
					post_flag = false;
					return;
				}
				if(!bankUserName){
					$.alert({millis:5000,text:"开户人姓名不能为空"});
					post_flag = false;
					return;
				}
				if(!bankSupportId || !bankName){
					$.alert({millis:5000,text:"开户银行不能为空"});
					post_flag = false;
					return;
				}
				if(!bankCityCode){
					$.alert({millis:5000,text:"开户城市不能为空"});
					post_flag = false;
					return;
				}
				refundObject=2;
			}
			if(checkinput.size() == 0){
				$.alert({millis:5000,text:"请选择要退缴费"});
				post_flag = false;
				return;
			}
			if(!vphFee){
				$.alert({millis:5000,text:"请输入退费金额"});
				post_flag = false;
				return;
			}
			if(afterid){
				var allMoney =Number(returnAmount)+Number(oldMoney);
				if(Number(vphFee) > Number(allMoney)){
					$.alert({millis:5000,text:"退费金额已超过可退金额,请重新输入"});
					post_flag = false;
					return;
				}
			}else{
				if(Number(vphFee) > Number(returnAmount)){
					$.alert({millis:5000,text:"退费金额已超过可退金额,请重新输入"});
					post_flag = false;
					return;
				}
			}
			if(Number(vphFee) == 0){
				$.alert({millis:5000,text:"退费金额不能为0,请重新输入"});
				post_flag = false;
				return;
			}
			if(!approveDept){
				$.alert({millis:5000,text:"审核部门不能为空"});
				post_flag = false;
				return;
			}
			if(!approveBy){
				$.alert({millis:5000,text:"审核人不能为空"});
				post_flag = false;
				return;
			}
			if(!reasonFlag){
				$.alert({millis:5000,text:"售后原因不能为空"});
				post_flag = false;
				return;
			}
			if(operationType == 2){
				if(!afterid){
					$.alert({millis:5000,text:"未获取到修改售后记录信息"});
					post_flag = false;
					return;
				}
			}
			if(operationType == 2){
				data["id"] = afterid;
			}
			data["afterSalesKind"] = afterSalesKind;
			data["refundObject"] = refundObject;
			data["orderId"] = orderId;
			data["vphFee"] = vphFee;
			data["reason"] = reason;
			data["custMobile"] = custMobile;
			data["custName"] = custName;
			data["approveDept"] = approveDept;
			data["approveBy"] = approveBy;
			data["payFeeId"] = payfeeid;
			data["reasonFlag"] = reasonFlag;
			data["bankUserName"] = bankUserName;
			data["bankMainName"] = bankMainName;
			data["bankCard"] = bankCard;
			data["bankName"] = bankName;
			data["bankSupportId"] = bankSupportId;
			data["bankCityCode"] = bankCityCode;
		}else if(afterSalesKind == '13' || afterSalesKind == '14' || afterSalesKind == '15'){
			//分期服务费售后
			confirmText = "请确认退款中不包含服务人员应发服务人员服务费,否则一旦退款成功,将由责任人自行追回服务人员服务费款项";
			var orderId = $("#order_orderId_s").val();
			var checkinput = $("#listBodyQualityUser_s").find("input[name='fqAfterSalesPayFee_s']:checked");
			var feeId = checkinput.attr("data-trId");
			var payfeeid = checkinput.attr("data-payFeeId");
			var reason = $("#order_reason_s").val();
			var reasonFlag = $("#order_reasonFlag_s").val();
			var custMobile = $("#order_custMobile_s").val();
			var custName = $("#order_custName_s").val();
			var vphFee = $("#"+feeId).val();//要退金额
			var returnAmount = $("#"+feeId).attr("data-returnAmount");//可退金额
			var oldMoney = $("#"+feeId).attr("data-money");
			var approveDept = $("#deptnameWithDept_s").val();//审核部门
			var approveBy = $("#manager_s").val();//审核人
			var bank = $("#qualityCustomerBankCard_s");
			var bankUserName = bank.find("input[name=cardName]").val();
			var bankMainName = bank.find("input[name=bankBranch]").val();
			var bankCard = bank.find("input[name=bankNumber]").val();
			var bankName = bank.find("input[name=bankName]").val();
			var bankSupportId = bank.find("input[name=bankSupportId]").val();
			var bankCityCode = bank.find("select[name=bankCityCode]").val();
			if(checkinput.size() == 0){
				$.alert({millis:5000,text:"请选择要退缴费"});
				post_flag = false;
				return;
			}
			if(!vphFee){
				$.alert({millis:5000,text:"请输入退费金额"});
				post_flag = false;
				return;
			}
			if(afterid){
				var allMoney =Number(returnAmount)+Number(oldMoney);
				if(Number(vphFee) > Number(allMoney)){
					$.alert({millis:5000,text:"退费金额已超过可退金额,请重新输入"});
					post_flag = false;
					return;
				}
			}else{
				if(Number(vphFee) > Number(returnAmount)){
					$.alert({millis:5000,text:"退费金额已超过可退金额,请重新输入"});
					post_flag = false;
					return;
				}
			}
			if(Number(vphFee) == 0){
				$.alert({millis:5000,text:"退费金额不能为0,请重新输入"});
				post_flag = false;
				return;
			}
			if(!approveDept){
				$.alert({millis:5000,text:"审核部门不能为空"});
				post_flag = false;
				return;
			}
			if(!approveBy){
				$.alert({millis:5000,text:"审核人不能为空"});
				post_flag = false;
				return;
			}
			if(!reasonFlag){
				$.alert({millis:5000,text:"售后原因不能为空"});
				post_flag = false;
				return;
			}
			if(operationType == 2){
				if(!afterid){
					$.alert({millis:5000,text:"未获取到修改售后记录信息"});
					post_flag = false;
					return;
				}
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankMainName){
				$.alert({millis:5000,text:"开户支行名称不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCard){
				$.alert({millis:5000,text:"银行账号不能为空"});
				post_flag = false;
				return;
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankSupportId || !bankName){
				$.alert({millis:5000,text:"开户银行不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCityCode){
				$.alert({millis:5000,text:"开户城市不能为空"});
				post_flag = false;
				return;
			}
			if(operationType == 2){
				data["id"] = afterid;
			}
			data["afterSalesKind"] = afterSalesKind;
			data["refundObject"] = refundObject;
			data["orderId"] = orderId;
			data["vphFee"] = vphFee;
			data["reason"] = reason;
			data["custMobile"] = custMobile;
			data["custName"] = custName;
			data["approveDept"] = approveDept;
			data["approveBy"] = approveBy;
			data["payFeeId"] = payfeeid;
			data["reasonFlag"] = reasonFlag;
			data["bankUserName"] = bankUserName;
			data["bankMainName"] = bankMainName;
			data["bankCard"] = bankCard;
			data["bankName"] = bankName;
			data["bankSupportId"] = bankSupportId;
			data["bankCityCode"] = bankCityCode;
		}else if(afterSalesKind == '16'){
			//卡售后
			var cardId = $("#card_id_c").val();//卡id
			var custMobile = $("#card_custMobile_c").val();
			var custName = $("#card_custName_c").val();
			var reason = $("#card_reason_c").val();
			var reasonFlag = $("#card_reasonFlag_c").val();
			var approveDept = $("#deptnameWithDept_c").val();//审核部门
			var approveBy = $("#manager_c").val();//审核人
			var bank = $("#qualityCustomerBankCard_c");
			var bankUserName = bank.find("input[name=cardName]").val();
			var bankMainName = bank.find("input[name=bankBranch]").val();
			var bankCard = bank.find("input[name=bankNumber]").val();
			var bankName = bank.find("input[name=bankName]").val();
			var bankSupportId = bank.find("input[name=bankSupportId]").val();
			var bankCityCode = bank.find("select[name=bankCityCode]").val();
			var vphFee = $("#card_money").val();//卡退款金额
			var cardNumber = $("#card_cardNumber_c").val();//卡号
			var salesType = $("#card_salesType_c").val();//是否赠卡
			var isGive = $("#card_isGive_c").val();//是否标记赠卡
			if(salesType == '2'){
				$.alert({millis:5000,text:"赠卡不可申请退费"});
				post_flag = false;
				return;
			}
			if(isGive == '1'){
				$.alert({millis:5000,text:"标记赠卡不可申请退费"});
				post_flag = false;
				return;
			}
			if(!cardNumber){
				$.alert({millis:5000,text:"未获取到卡号"});
				post_flag = false;
				return;
			}
			if(!vphFee){
				$.alert({millis:5000,text:"请输入退费金额"});
				post_flag = false;
				return;
			}
			if(Number(vphFee) == 0){
				$.alert({millis:5000,text:"退费金额不能为0,请重新输入"});
				post_flag = false;
				return;
			}
			if(!approveDept){
				$.alert({millis:5000,text:"审核部门不能为空"});
				post_flag = false;
				return;
			}
			if(!approveBy){
				$.alert({millis:5000,text:"审核人不能为空"});
				post_flag = false;
				return;
			}
			if(!reasonFlag){
				$.alert({millis:5000,text:"售后原因不能为空"});
				post_flag = false;
				return;
			}
			if(operationType == 2){
				if(!afterid){
					$.alert({millis:5000,text:"未获取到修改售后记录信息"});
					post_flag = false;
					return;
				}
				data["id"] = afterid;
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankMainName){
				$.alert({millis:5000,text:"开户支行名称不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCard){
				$.alert({millis:5000,text:"银行账号不能为空"});
				post_flag = false;
				return;
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankSupportId || !bankName){
				$.alert({millis:5000,text:"开户银行不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCityCode){
				$.alert({millis:5000,text:"开户城市不能为空"});
				post_flag = false;
				return;
			}
			data["cardNumber"] = cardNumber;
			data["afterSalesKind"] = afterSalesKind;
			data["refundObject"] = refundObject;
			data["orderId"] = cardId;
			data["vphFee"] = vphFee;//退款金额
			data["reason"] = reason;
			data["custMobile"] = custMobile;
			data["custName"] = custName;
			data["approveDept"] = approveDept;
			data["approveBy"] = approveBy;
			data["reasonFlag"] = reasonFlag;
			data["bankUserName"] = bankUserName;
			data["bankMainName"] = bankMainName;
			data["bankCard"] = bankCard;
			data["bankName"] = bankName;
			data["bankSupportId"] = bankSupportId;
			data["bankCityCode"] = bankCityCode;
		}else if(afterSalesKind == '9'){
			//解决方案售后
			var cardId = $("#solution_id").val();//解决方案id
			var custMobile = $("#solution_custMobile").val();
			var custName = $("#solution_custName").val();
			var reason = $("#solution_reason").val();
			var reasonFlag = $("#solution_reasonFlag").val();
			var approveDept = $("#deptnameWithDept_so").val();//审核部门
			var approveBy = $("#manager_so").val();//审核人
			var bank = $("#qualityCustomerBankCard_so");
			var bankUserName = bank.find("input[name=cardName]").val();
			var bankMainName = bank.find("input[name=bankBranch]").val();
			var bankCard = bank.find("input[name=bankNumber]").val();
			var bankName = bank.find("input[name=bankName]").val();
			var bankSupportId = bank.find("input[name=bankSupportId]").val();
			var bankCityCode = bank.find("select[name=bankCityCode]").val();
			var allMoney = $("#solution_allMoney").val();//退款总金额
			var salesType = $("#solution_give").val();//是否赠解决方案
			if(salesType == 20120008){
				$.alert({millis:5000,text:"赠的解决方案不可申请退费"});
				post_flag = false;
				return;
			} 
			if(!approveDept){
				$.alert({millis:5000,text:"审核部门不能为空"});
				post_flag = false;
				return;
			}
			if(!approveBy){
				$.alert({millis:5000,text:"审核人不能为空"});
				post_flag = false;
				return;
			}
			if(!reasonFlag){
				$.alert({millis:5000,text:"售后原因不能为空"});
				post_flag = false;
				return;
			}
			if(operationType == 2){
				if(!afterid){
					$.alert({millis:5000,text:"未获取到修改售后记录信息"});
					post_flag = false;
					return;
				}
				data["id"] = afterid;
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankMainName){
				$.alert({millis:5000,text:"开户支行名称不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCard){
				$.alert({millis:5000,text:"银行账号不能为空"});
				post_flag = false;
				return;
			}
			if(!bankUserName){
				$.alert({millis:5000,text:"开户人姓名不能为空"});
				post_flag = false;
				return;
			}
			if(!bankSupportId || !bankName){
				$.alert({millis:5000,text:"开户银行不能为空"});
				post_flag = false;
				return;
			}
			if(!bankCityCode){
				$.alert({millis:5000,text:"开户城市不能为空"});
				post_flag = false;
				return;
			}
			data["afterSalesKind"] = afterSalesKind;
			data["refundObject"] = refundObject;
			data["solutionId"] = cardId;
			data["orderId"] = cardId;
			data["vphFee"] = allMoney;//退款金额
			data["reason"] = reason;
			data["custMobile"] = custMobile;
			data["custName"] = custName;
			data["approveDept"] = approveDept;
			data["approveBy"] = approveBy;
			data["reasonFlag"] = reasonFlag;
			data["bankUserName"] = bankUserName;
			data["bankMainName"] = bankMainName;
			data["bankCard"] = bankCard;
			data["bankName"] = bankName;
			data["bankSupportId"] = bankSupportId;
			data["bankCityCode"] = bankCityCode;
		}
		
		/**   周鑫 2018-12-13  用来校验是否相同类型未退款成功记录     扩展：直接继续添加 afterSalesKind 允许的值就可以            **/
		var updateOrAdd  = '${param.operationType}';//1:新增操作 2:修改操作
		if(updateOrAdd==1){
			if(afterSalesKind==11||afterSalesKind==13||afterSalesKind==14||afterSalesKind==15||afterSalesKind==17||afterSalesKind==18||afterSalesKind==21){
				var id;
				//不同，类型获取id号不同
				if(afterSalesKind==11||afterSalesKind==17||afterSalesKind==18||afterSalesKind==21){
					//分期退费
					id = $("#order_orderId_i").val();
				}else{
					//分期服务费退费
					id = $("#order_orderId_s").val();
				}
				//调用方法
				debugger
				var resultCount=returnPremiumCheck(id,afterSalesKind);
				if(resultCount>0){
					if(afterSalesKind==11||afterSalesKind==17||afterSalesKind==18||afterSalesKind==21){
						 $.alert({text:"该订单已经申请了分期退费，请先完成上一笔退费申请！"});
						 	post_flag = false;
						 	return;
					}else{
						$.alert({text:"该订单已经申请了分期服务费退费，请先完成上一笔分期服务费退费！"});
							post_flag = false;
						    return;
					}
				}
				//调用接口失败
				if(resultCount==-1){
					 $.alert({millis:5000,text:"操作失败"});
						 post_flag = false;
						 return;
				}
			}
		}
	    /**   周鑫 2018-12-13  用来校验是否相同类型未退款成功记录       **/
		/*3提交数据*/
		 $.confirm({text:confirmText,callback:function(r){
					if(r){
						$.ajax({
					        url:ctx+"/afterSalesNew/insertAfterSales",
					        data:data,
					        type:'post',
					        async:false,
					        dataType:"json",
					        success:function(data){
					           if(data.msg == "00"){
					        	   $.alert({millis:5000,text:"操作成功"});
					        	   closeModule();
					        	   queryAfterSalesByLike(0,10);
					           }else{
					        	   $.alert({millis:5000,text:"操作失败"});
								   post_flag = false;
					           }
					        }
					    })
					}else{
						post_flag = false;
					}
				}})
	}

	/**验证数字*/
	function checkNumber(thiz){
		thiz.value=thiz.value.replace(/[^((\d{1,}\.\d+)|(\d{1,}))]/g,'');
	}

	/**修改回显加载*/
	function loadAfterSales(afterSalesKind){
		if(!afterSalesKind){
			return;
		}else if(afterSalesKind  == '11' || afterSalesKind  == '17' || afterSalesKind  == '18'||afterSalesKind  == '21'){
			var data = queryAfterSales();
			if(data){
				 $("#orderCode_i").val(data.orderCode);
	             $("#deptnameWithDept_i").val(data.approveDept);
                 queryguanjia("deptnameWithDept_i","manager_i");
                 $("#manager_i").val(data.approveBy);
                 $("#order_reason_i").val(data.reason);
                 $("#order_reasonFlag_i").val(data.reasonFlag);
                 $("#queryAfterBu_i").trigger("click");
                 var checkinput = $("#listBodyQualityUser_i").find("input[name='fqAfterSalesPayFee_i']:checked");
                 var feeId = checkinput.attr("data-trId");
                 var seId = checkinput.attr("data-seid");
                 if(afterSalesKind == '11'){
                 	 $("#"+feeId).val(data.vphFee);//要退金额
                 	 $("#"+feeId).attr("data-money",data.vphFee);//要退金额
                  }else{
               	 	$("#"+feeId).val(data.refundTotal);//要退金额
               	 	$("#"+feeId).attr("data-money",data.refundTotal);//要退金额
                 }
                 var refundObject=data.refundObject;
                 if(refundObject == 2){
                	 $("#qualityCustomerBankCard_fq").show();
                	 $("#"+seId).val(refundObject);
                	 var tabPane = $("#addFqAfter");
                     tabPane.find("input[name=bankNumber]").val(data.bankCard);
                     tabPane.find("select[name=bankCityCode]").val(data.bankCityCode);
                     tabPane.find("input[name=bankName]").val(data.bankMainName);
                     tabPane.find("input[name=bankBranch]").val(data.bankName); 
                     tabPane.find("input[name=bankSupportId]").val(data.bankSupportId);
                     tabPane.find("input[name=cardName]").val(data.bankUserName);
                 }

			}
    	}else if(afterSalesKind  == '13' || afterSalesKind  == '14' || afterSalesKind  == '15'){
    		var data = queryAfterSales();
			if(data){
			  $("#orderCode_s").val(data.orderCode);
	          $("#deptnameWithDept_s").val(data.approveDept);
              queryguanjia("deptnameWithDept_s","manager_s");
              $("#manager_s").val(data.approveBy);
              $("#order_reason_s").val(data.reason);
              $("#order_reasonFlag_s").val(data.reasonFlag);
              $("#queryAfterBu_s").trigger("click");
              var checkinput = $("#listBodyQualityUser_s").find("input[name='fqAfterSalesPayFee_s']:checked");
              var feeId = checkinput.attr("data-trId");
              $("#"+feeId).val(data.refundTotal);//要退金额
              $("#"+feeId).attr("data-money",data.refundTotal);//要退金额
              var tabPane = $("#addFqsxfAfter");
              tabPane.find("input[name=bankNumber]").val(data.bankCard);
              tabPane.find("select[name=bankCityCode]").val(data.bankCityCode);
              tabPane.find("input[name=bankName]").val(data.bankMainName);
              tabPane.find("input[name=bankBranch]").val(data.bankName);
              tabPane.find("input[name=bankSupportId]").val(data.bankSupportId);
              tabPane.find("input[name=cardName]").val(data.bankUserName);
			}
    	}
	}
	
	/**修改回显查询*/
	function queryAfterSales(){
		var result = null;
		$.ajax({
	        url:ctx+"/afterSalesNew/queryFqafterSales",
	        data:{
	            "id":afterid
			},
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	           if(data.msg == "00"){
	        	   result = data.afterSalesNew;
	           }
	        }
	    })
	    return result;
	}
	
	
	/**修改禁用相关表单*/
	function disabledInput(afterSalesKind){
		if(afterSalesKind  == '11' || afterSalesKind  == '17' || afterSalesKind  == '18'){
			$("#addFqAfterForm").find(":input").not("#order_feePostText_i,#deptnameWithDept_i,#order_reasonFlag_i,#manager_i,#order_reason_i,[name='vph_fee_i']").prop("disabled",true);
		}else if(afterSalesKind  == '13' || afterSalesKind  == '14' || afterSalesKind  == '15'){
			$("#addFqsxfAfterForm").find(":input").not("#bankCityCode_s,#customerBankCardSel_s,#order_feePostText_s,#deptnameWithDept_s,#order_reasonFlag_s,#manager_s,#order_reason_s,[name='vph_fee_s'],#bankCityCode_s+div input").prop("disabled",true);
		}
	}
	
	/**查询客户开户城市**/
	function queryUserBankCity(){
		var html = "<option style='color:blue;' value=''>...请选择...</option>";
	    var ctx=$("#ctx").val();
			    $.ajax({
			        url:ctx+"/afterSalesNew/queryCity",
			        data:{},
			        type:'post',
			        async:false,
			        dataType:"json",
			        success:function(data){
			        	if(data.msg == "00"){
				            $.each(data.list,function(i,v){
				                html +="<option value='" +v.code +"' >"+v.fullName+"</option>";
				            })
			        	}
			        }
			    })
		return html;	
	}
	
	/**加载客户开户城市*/
	function loadAfterSalesCity(cityTagId,optionCityHtml){
		if(cityTagId){
    		$("#"+cityTagId).html(optionCityHtml||"").checkSelect();
    	}
	}
	
	
	
	/**查询卡列表*/
	function queryAfterSalesCard(data){
		var html = "<option value=''>...请选择...</option>";
	    $.ajax({
	        url:ctx+"/afterSalesNew/findCardDetailByCardNumOrMoblie",
	        data:data,
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	           if(data.msg == "00"){
	        	   $.each(data.list,function(i,v){
		        	   html += " <option value='"+v.cardNumber+"' data-card='"+JSON.stringify(v)+"'>"+v.cardNumber+"</option>";
	        	   })
	           }else{
	        	   $.alert({millis:5000,text:"无符合条件卡"});
	           }
	        }
	    })
		return html;	
		}
	
	/**查询解决方案订单*/
	function querySolutionOrder(data){
		var html = "<option value=''>...请选择...</option>";
	    $.ajax({
	        url:ctx+"/afterSalesNew/querySolution",
	        data:data,
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	           if(data.msg == "00"){
	        	   $.each(data.list,function(i,v){
		        	   html += " <option value='"+v.solutionCode+"' data-solution='"+JSON.stringify(v)+"'>"+v.solutionCode+"</option>";
	        	   })
	           }else{
	        	   $.alert({millis:5000,text:"无符合条件订单"});
	           }
	        }
	    })
		return html;	
	}
	
	 /**查询解决方案列表*/
    function querySolutionSchedule(){
        var solutionId = $("#solutionSelect").val();//售后id
        if(solutionId){
            var html = "";
            $.ajax({
                url:ctx+"/afterSalesNew/querySolutionSchedule",
                data:{
                    "id":solutionId
                },
                type:'post',
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.msg == "00"){
                        $.each(data.list,function(i,v){
                            var trId = "solution"+i;
                            html += "<tr data-payfee='"+JSON.stringify(v)+"' data-trId='"+trId+"'>";
                            html += "<td>"+(i+1)+"</td>";
                            html += "<td>"+v.id||""+"</td>";
                            html += "<td>"+v.oncePrirce||""+"</td>";
                        })
                    }else{
                        html = "<tr onclick='selAfterSalesPayFee(this,2)'><td colspan='7' class='table-empty' >暂无数据</td></tr>";
                    }
                    $("#solutionList").html(html);
                }
            })
        }else{
            $("#solutionList").html("<tr onclick='selAfterSalesPayFee(this,2)'><td colspan='7' class='table-empty' >暂无数据</td></tr>");
        }
        radioColor("#solutionList > tr");/*表格单选*/
        trColor("#native_tbody > tr");/*表格点击行高亮*/
        $("#solutionList").find("tr:first").trigger("click");
    }

	 
    /**查询解决方案列表*/
    function querySolutionMoney(){
        var solutionId = $("#solutionSelect").val();//售后id
        var solutionGive = $("#solution_give").val();//判断是否是赠送方案
        if(solutionId){
            var html = "";
            $.ajax({
                url:ctx+"/afterSalesNew/querySolutionMoney",
                data:{
                    "id":solutionId
                },
                type:'post',
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.msg == "00"){
                    	if(solutionGive == '20120008'){
                    		$("#solutionCardbalance").html(0);//卡余额
                        	$("#allOncePrirce").html(0);//单价总和
                        	$("#otherMoney").html(0);//他人代收
                        	$("#allMoney").html((0));//可退总额
                        	$("#releaseMoney").html(0);//可退总额
                        	 $("#solution_allMoney").val(0);//可退总额
                    	}else{
                    		$("#solutionCardbalance").html(data.afterSalesNew.cardbalance);//卡余额
                        	$("#allOncePrirce").html(data.afterSalesNew.allOncePrirce);//单价总和
                        	$("#otherMoney").html(data.afterSalesNew.otherMoney - data.afterSalesNew.solutionExpend);//他人代收
                        	$("#allMoney").html((data.afterSalesNew.cardbalance+data.afterSalesNew.allOncePrirce)-(data.afterSalesNew.otherMoney - data.afterSalesNew.solutionExpend));//可退总额
                        	$("#releaseMoney").html((data.afterSalesNew.cardbalance+data.afterSalesNew.allOncePrirce)-(data.afterSalesNew.otherMoney - data.afterSalesNew.solutionExpend));//可退总额
                        	 $("#solution_allMoney").val((data.afterSalesNew.cardbalance+data.afterSalesNew.allOncePrirce)-(data.afterSalesNew.otherMoney - data.afterSalesNew.solutionExpend));//可退总额
                    	}
                    	
                    }
                }
            })
        }else{
        }
    }
    /** 周鑫 2018-12-13  用来校验是否相同类型未退款成功记录     **/
    function returnPremiumCheck(orderId,afterSalesKind){
    	
    	var result;
    	 $.ajax({
    		 url: ctx+"/afterSalesNew/premiumCheck",
    	        data: {
    	            orderId: orderId,
    	            afterSalesKind:afterSalesKind,
    	        },
    	        type: "POST",
    	        async: false,
    	        success: function (data) {
    	        	if(data.msg=='00'){
    	        		 if(data.count==0){
    	        			 result=0;
    	    	            }else{
    	    	             result=1;
    	    	            }
    	        	}else{
    	        		result=-1;
    	        	}
    	        }
    	    });
    	 return result;
    }
    /**  周鑫 2018-12-13   **/
    /** A、如果该合同对应的工资有海金缴费且提交到了结算中心但未审核通过（打款状态为“审核中”、“待通过”、“驳回”） 
		B、如果该合同对应的工资有海金缴费且结算中心审核通过生成了账单但没有给海金公司推送过去
		周鑫 2019-01-18
		**/
    function agreementSeaGlodPayFree(payfeeidA,orderCodeA){
    	debugger
    	var result=1;
	   	$.ajax({
	   		 url: ctx+"/afterSalesNew/agreementSeaGlodPayFree",
	   	        data: {
	   	        	payfeeId: payfeeidA,
	   	        	orderCode: orderCodeA,
	   	        },
	   	        type: "POST",
	   	        async: false,
	   	        success: function (data) {
	   	        	debugger
	   	        	if(data.msg=='00'){
	   	        		 if(data.count==0){
	   	        			 result=0;
	   	    	            }
	   	        	}
	   	        }
	   	    });
   	 	return result;
    }
   /** 周鑫 2019-01-18 **/
</script>
</body>
</html>

