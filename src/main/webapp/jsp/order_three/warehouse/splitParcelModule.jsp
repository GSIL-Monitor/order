<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
	<head>
		<link href="${ctx}/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
		<style>
			.wrap_list{
				width:100%;
				margin:0 auto;
				display:inline-block
			}
			.wrap_list p{
				width:33%;
				line-height:26px;
				float:left;
				text-align:center;
			}
		</style>
	</head>
	<body>
	<input type="hidden" id="ctx" value="${ctx}" />
	<input type="hidden" id="parcelId" value="" />
	<input type="hidden" id="orderId" value="" />
	<input type="hidden" id="warehouseId" value="" />
	  <div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">  
		  <div class="modal-dialog" >  
		    <div class="modal-content">  
		      <div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
		        <h4 class="modal-title">拆分包裹</h4>
		      </div>  
		      <div class="modal-body">  
				<form  role="form" id="my-form">
					<div class="row container-fluid" style="margin-bottom: 10px;">
						<div class="form-group col-xs-12 form-inline">
							<h4>包裹<span id="parcelNum" name="num"></span></h4>
							<div class="table table-responsive">
				                <table class="table focus-table" style="width:680px;">
				                    <thead>
					                    <tr>
					                        <th></th>
					                        <th>商品名称(单位)</th>
					                        <th>数量 </th>
					                        <th>规格</th>
					                    </tr>
				                    </thead>
				                    <tbody id="spiltList">
				                    </tbody>
				                </table>
		                    </div>
		                    <button type="button" class="btn btn-xs btn-primary" onclick="createPackages()">拣出</button>  
		                    <hr>
		                    <div id="addparcel">
		                    	
		                    
		                    </div>
	                    </div>
					</div>
					<div class="modal-footer">  
				        <button type="button" class="btn btn-primary btn-sm" data-dismiss="modal">取消</button>  
				        <button type="button" class="btn btn-primary btn-sm" onclick="splitInsert()">提交</button>  
				    </div> 
		      </form>
		    </div>
		  </div>
		</div> 
		</div>
	</body>
</html>

