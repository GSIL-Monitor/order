<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认无效原因新增页</title>
</head>
<body>
		<div class="modal fade" >  
	  <div class="modal-dialog" >  
	    <div class="modal-content">
	    	<div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true" >×</button>  
		      	<h4 class="modal-title"></h4>
		     </div> 
	      <div class="modal-body">
	      		<div class="info-select clearfix">
                 <input type="hidden" id="userLoginLevel">
                     <form class="form-inline">
                     <div class="row">
                          <div class="form-group  col-xs-6">
                              <label>
                                  <p>售后单ID：</p>
                                  <span  id="afterSaleId_reason"></span>
                              </label>
                          </div>
                          <div class="form-group  col-xs-6">
                              <label>
                                  <p>售后单类型：</p>
                                  <span id="orderType_reason"></span>
                              </label>
                          </div> 
                      </div>
                      <div class="row">
                          <div class="form-group  col-xs-12">
                              <label>
                                  <p>创建原因：</p>
                                  <span id="create_reason"></span>
                              </label>
                          </div>
                       </div>
                      <div class="row">
                          <div class="form-group  col-xs-12">
                              <label class="form-label">
                                  <p>无效原因：</p>
                                  <textarea rows="3"  name="remark" style="height:50px;" id="unuse_reason" class= "form-control form-textarea" ></textarea>
                              </label>
                          </div>
                       </div>
                      </form>
                  </div>
		  </div>
	      <div class="modal-footer">  
	        <button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" onclick="setOrderCloseModule('qualityApproveReason');" >取消</button>  
	        <button type="button" class="btn btn-sm btn-primary" onclick="updateSaleReason();" >保存</button>
	      </div>  
	    </div>
	  </div>
	</div>    
</body>
<script type="text/javascript">
	var afterSalesId = '${param.afterSalesId}';
	var orderId = '${param.orderId}';
	var accountPayId = '${param.accountPayId}';
	var vphAccountId = '${param.vphAccountId}';
	var afterSalesKind = '${param.afterSalesKind}';
	var flag = '${param.flag}';
	$(function(){
		querySaleReason();
	});
	//查询售后信息
	function querySaleReason(){
		$.ajax({
			url: ctx+"/afterSales/loadAfterSales",
			data:{
				id:afterSalesId
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if (data.msg == "00") {
					 var asObj = data.afterSales;
					 $("#afterSaleId_reason").html(asObj.id);
					 $("#orderType_reason").html(afterSalesKindArr[asObj.afterSalesKind]);
					 $("#create_reason").html(asObj.reason);
				}
			}
		});
	}
	//更新无效原因
	function updateSaleReason(){
		var auditFlag="20130002";
		var remark = $("#unuse_reason").val();
		if(flag == 3){
			auditFlag="20130012";
		}
		$.ajax({
			url: ctx+"/afterSales/updateAfterSales",
			data:{
				id:afterSalesId,
				orderId:orderId,
				remark:remark,
				auditFlag:auditFlag,
				makesureFlag:2,
				accountPayId:accountPayId,
				vphAccountId:vphAccountId,
				afterSalesKind:afterSalesKind
			},
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				if(data.msg=='00'){
					$.alert({millis:3000,top:'30%',text:"提交成功！"});
					closeModule('qualityApproveReason');
					queryAfterSalesApprove(0,10);
				}else{
					 $.alert({millis:3000,top:'30%',text:"提交失败！"});
				}
			}
		});
	}
</script>
</html>