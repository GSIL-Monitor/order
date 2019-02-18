<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<link href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
	</head>
	<body>
	<input type="hidden" id="ctx" value="${ctx}" />
	  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
		  <div class="modal-dialog" >  
		    <div class="modal-content">  
		      <div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
		        <h4 class="modal-title">商品发货</h4>
		      </div>  
		      <div class="modal-body">  
				<form  role="form" id="my-form">
					<div class="row container-fluid" style="margin-bottom: 10px;">
					<input type="hidden" class="form-control" id="id" >
					<input type="hidden" class="form-control" id="version" >
					<input type="hidden" class="form-control" id="orderid" >
						<h4>&nbsp;商&nbsp;品&nbsp;信&nbsp;息</h4>
						<div class="form-group col-xs-12 form-inline">
							<div class="table table-responsive">
				                <table class="table focus-table" style="width:680px;">
				                    <thead>
				                    <tr>
				                        <th width="30%">商品名称(单位)</th>
				                        <th width="20%">价格体系</th>
				                        <th width="10%">商品价格</th>
				                        <th width="10%">商品数量 </th>
				                        <th width="20%">规格</th>
				                    </tr>
				                    </thead>
				                    <tbody id="ItemproductName">
				                    </tbody>
				                </table>
		                    </div>
	                    </div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>收&nbsp;&nbsp;&nbsp;&nbsp;货&nbsp;&nbsp;&nbsp;&nbsp;人：</p>
								<span ID="OrderuserName"></span>
							</label>
						</div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>收货人电话：</p>
								<span ID="OrderuserMobile"></span>
							</label>
						</div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>收&nbsp;货&nbsp;地&nbsp;址&nbsp;：</p>
								<span ID="OrderreceiverAddress"></span>
							</label>
						</div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>送&nbsp;货&nbsp;时&nbsp;间&nbsp;：</p>
								<span ID="OrderreceiptTime"></span>
							</label>
						</div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>快&nbsp;递&nbsp;公&nbsp;司&nbsp;：</p>
								<select class="form-control" id="express" name="express">
								</select>
							</label>
						</div>
						<div class="form-group col-xs-12 form-inline">
							<label for="custom_name" class="has-feedback">
								<p>物&nbsp;流&nbsp;单&nbsp;号&nbsp;：</p>
								<input type="text" class="form-control" name="logisticsnumber" id="logisticsnumber">
								<span id="logisticsnumberspan"></span>
							</label>
						</div>
					</div>
					<div class="modal-footer">  
				        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">取消</button>  
				        <button type="submit" class="btn btn-primary btn-sm" >提交</button>  
				    </div> 
		      </form>
		    </div>
		  </div>
		</div> 
	</body>
	<script type="text/javascript">
		$(function (){
			/**
			*验证
			*/
		        $('#my-form').bootstrapValidator({
		            message: 'This value is not valid',
		            excluded: ':disabled',
		            feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            fields: {
		                logisticsnumber : {
		                    validators: {
		                        notEmpty: {
		                            message: '不能为空'
		                        },
		                        regexp: {
		                            regexp: /^[0-9]+$/,
		                            message: '物流单号只能为数字。'
		                        }
		                    }
		                },
		                express : {
		                    validators: {
		                        notEmpty: {
		                            message: '不能为空'
		                        },
		                    }
		                },
		            }
		        }).on('success.form.bv', function(e) {
		        	e.preventDefault();
		        	insertdelivergoods();
		        });
		})
	</script>
</html>

