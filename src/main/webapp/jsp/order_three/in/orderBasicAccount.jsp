<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增结算</title>
<script type="text/javascript">
	<%
		String orderId = request.getParameter("orderId");
		String cateType = request.getParameter("cateType");
		String totalPay = request.getParameter("totalPay");
	%>
</script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false">
    <div class="modal-dialog" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">新增结算</h4>
            </div>
            <div class="modal-body new-sort" id="new_sort">
                <div class="info clearfix">
                    <form id="balanceAddForm" action="" class="form-inline" method="post"
                        		data-validator-option="{theme:'yellow_left_effect',stopOnError:true}">
                       
                         <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p>结算单时间：</p>
                                    <input id="accountStartTimead" name="startTime" class="form-control Wdate" type="text" 
	                                   	 				onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" >
                               		至<input id="accountEndTimead" name="endTime" class="form-control Wdate" type="text" 
	                                   	 				onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd'})" >
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p>结算单金额：</p>
                                    <input class="form-control" id="accountAmountad" name="accountAmountad" value="<%=totalPay%>" readOnly/>元
                                </label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-12">
                                <label>
                                    <p>结算单类型：</p>
                                    <select id="accountTypead" name="payType" class="form-control" >
										<option value="1">定金</option>
										<option value="2">押金</option>
										<option value="3" selected="selected">预收</option> 
										<option value="4">实收</option>
										<option value="5">补缴</option>
										<!-- <option value="6">退款</option> -->                                   
                                    </select>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="insertAccount();">保存</button>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
	function insertAccount(){
		var ctx = $("#ctx").val();
		var accountStartTimead=$("#accountStartTimead").val();
		var accountEndTimead=$("#accountEndTimead").val();
		var accountAmountad=$("#accountAmountad").val();
		var payType=$("#accountTypead option:selected").val();
		var orderId="<%=orderId%>";
		var params = {
				orderId : orderId,
				startTime : accountStartTimead,
				endTime : accountEndTimead,
				accountAmount : accountAmountad,
				payType : payType
			};
			if(checkThreeOrderInAccountsData(params)){
				$.ajax({
					url : ctx + "/threeOrderOut/saveOrderAccounts",
					data : params,
					type : "post",
					dataType : "json",
					async : false,
					success : function(data) {
						if (data.msg == "00") {
							parent.queryThreeOrderAccounts(orderId);
							closeModule('orderBasicAccount');
						}
					}
				});
			}
	}
	
	function checkThreeOrderInAccountsData(params){
		if(params.startTime==null||params.startTime==""){
			$.alert({text:"请输入结算开始时间！"});
			return false;
		}
		if(params.endTime==null||params.endTime==""){
			$.alert({text:"请输入结算结束时间！"});
			return false;
		}
		if(params.accountAmount==null||params.accountAmount==""){
			$.alert({text:"请输入结算金额！"});
			return false;
		}
		if(params.payType==null||params.payType==""){
			$.alert({text:"请选择结算类型！"});
			return false;
		}
		return true;
	}
</script>
</html>