<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageEditcext.request.contextPatd}" />
<!DOCTYPE html>
<html>
<head>
<title>完善客户账户信息</title>
</head>
<body>
	<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="模态框" aria-hidden="false" id="">  
		<div class="modal-dialog modal-order-edit" > 
			<div class="modal-content">
				<div class="modal-header">  
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		        	<h4 class="modal-title">完善客户账户信息</h4>
				</div>  
				<div class="modal-body">
						 <div class="panel panel-default">
		                    <div class="panel-body">
					                        <tbody class="dataTable_wrapper">
					                        <div class="panel-content table-responsive">
					                            <table class="table table-hover table-striped">
										           <thead>
										               <tr>
															<th>序号</th>
															<th>账户姓名</th>
															<th>身份证号</th>
															<th>银行</th>
															<th>开户城市</th>
															<th>开户行</th>
															<th>银行账号</th>
															<th>预留手机号</th>
															<th>操作</th>
														</tr>
										           	</thead>
										           	<tbody id="listCustomerBankCard">
														<!-- 列表内容 -->		 		
													</tbody>
										       	</table>
										</div>
					                    </tbody>
					               
					        <tbody class="dataTable_wrapper" >
                            <div class="dataTable-info">
                                <header>
                                    <h4>新增</h4>
                                </header>
					               <div class="info-select clearfix">
                                    <form class="form-inline" id="formCustomerBankCard">
                                    <input type="hidden" value="${param.cusId}" id="id" name="id">
                                    <input type="hidden" value="${param.cusName}" id="cusName">
                                    <input type="hidden" value="20340001" name="platform">
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p style="font-weight:bold;"><b style="color: red">*</b>账户姓名:</p>
   												<input type="text" name="cardName" id="cardName" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p style="font-weight:bold;">身份证号:</p>
   												<input type="text" name="idCardNum" id="idCardNum" class="form-control"/>                    		
                                    		</label>
                                    	</div>
                                    </div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-6">
                                    		<label><p style="font-weight:bold;"><b style="color: red">*</b>银行:</p>
   												<select name="bankSupportId" id="bankSupportId" class="form-control" >
	                                    		</select>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-6">
                                    		<label><p style="font-weight:bold;"><b style="color: red">*</b>银行账号:</p>
   												<input type="text" name="bankCard" id="bankCard" class="form-control"/>                    		
                                    		</label>
                                    	</div>
                                    </div>
                                     <div class="row">
                                    	<div class="form-group  col-xs-12">
                                    		<label><p style="font-weight:bold;"><b style="color: red">*</b>开户城市:</p>
   												<input type="text" name="bankCity" id="bankCity" class="form-control" placeholder="北京"/>
                                    		</label>
                                    	</div>
                                    </div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-12">
                                    		<label><p style="font-weight:bold;"><b style="color: red">*</b>开户行:</p>
   												<input type="text" name="bankBranch" id="bankBranch" class="form-control"/>
                                    		</label>
                                    	</div>
                                    </div>
                                    <div class="row">
                                    	<div class="form-group  col-xs-9">
                                    		<label><p style="font-weight:bold;">预留手机号:</p>
   												<input type="text" name="reservedPhone" id="reservedPhone" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-3">
											<button type="submit" class="btn btn-sm btn-primary" >保存</button>
                                    	</div>
                                    </div>
                                    </form>
                                </div>
                                 </div>
                                </tbody>
                                <div class="form-group col-xs-12 text-center" style="color: red">
									<h4>请确认姓名、账号等相关信息与客户提供的一致</h4>
	           					</div>
		                    </div>
               	 </div>
				</div>
				<div class="modal-footer">  
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal" form="formCustomerBankCard">关闭</button>
				</div>  
			</div>
	    </div>
	</div>         
</body>
<script type="text/javascript">
		queryBankSupport();
		myBankcard();
		function queryBankSupport(){
			var ctx = $("#ctx").val();
			$.ajax({
				url:ctx+"/bankcard/queryBankSupport",
				type:"post",
				datetype:"json",
				async : false,
				data:{},
				success : function(data){
					var html = "<option value='' style='color:blue;'>请选择</option>";
					if (data.msg == "00") {
						var result = data.result;
						if(result && result.code == 1){
							var v = result.data;
							if(v.length > 0){
								$.each(v,function(i,b){
									html+="<option value='"+b.id+"'>"+b.bankName+"</option>";
								})
							}
						}
					}
					$("#bankSupportId").html(html);
				}
			});
		}
		
		function resetFormCustomerBankCard(){
			$("#cardName").val("");
			$("#idCardNum").val("");
			$("#bankSupportId").val("");
			$("#bankCard").val("");
			$("#bankCity").val("");
			$("#bankBranch").val("");
			$("#reservedPhone").val("");
			var bv = $("#formCustomerBankCard").data("bootstrapValidator");
			bv.updateStatus("cardName", "NOT_VALIDATED");
			bv.updateStatus("idCardNum", "NOT_VALIDATED");
			bv.updateStatus("bankSupportId", "NOT_VALIDATED");
			bv.updateStatus("bankCard", "NOT_VALIDATED");
			bv.updateStatus("bankCity", "NOT_VALIDATED");
			bv.updateStatus("bankBranch", "NOT_VALIDATED");
			bv.updateStatus("reservedPhone", "NOT_VALIDATED");
		}
		
		function myBankcard(){
			var cusId =  $("#formCustomerBankCard").find("#id").val();
			var ctx = $("#ctx").val();
			if(cusId){
				$.ajax({
					url:ctx+"/bankcard/myBankcard",
					type:"post",
					datetype:"json",
					async : false,
					data:{
						"custId":cusId
					},
					success : function(data){
						if (data.msg == "00") {
							var result = data.result;
							if(result && result.code == 1){
								var v = result.data;
								if(v.length > 0){
									var html = "";
									$.each(v,function(i,b){
										html+="<tr><td>"+(i+1)+"</td>";
										html+="<td>"+(b.cardName||"")+"</td>";
										html+="<td>"+(b.idCardNum||"")+"</td>";
										html+="<td>"+(b.bankName||"")+"</td>";
										html+="<td>"+(b.bankCity||"")+"</td>";
										html+="<td>"+(b.bankBranch||"")+"</td>";
										html+="<td>"+(b.bankNumber||"")+"</td>";
										html+="<td>"+(b.reservedPhone||"")+"</td>";
										html+="<td><button class='btn btn-sm btn-primary' type='button' onclick='deleteBankcard("+b.id+");'>删除</button></td></tr>";
									})
									$("#listCustomerBankCard").html(html);
									/*表格点击行高亮*/
								    trColor("#native_tbody > tr");
								    /*表格单选*/
								    radioColor("#listCustomerBankCard > tr");
								}else{
									$("#listCustomerBankCard").html("<tr><td colspan='10'>暂无数据</td><tr>");
								}
							}else{
								$("#listCustomerBankCard").html("<tr><td colspan='10'>暂无数据</td><tr>");
							}
						}else{
							$("#listCustomerBankCard").html("<tr><td colspan='10'>暂无数据</td><tr>");
						}
					}
				});
			}
		}
		
		
		function insertBankcard(){
			var cardName = $.trim($("#cardName").val());
			var cusId =  $("#formCustomerBankCard").find("#id").val();
			var data =  $("#formCustomerBankCard").serialize();
			var ctx = $("#ctx").val();
			var options=$("#bankSupportId option:selected"); //获取选中的项
			var bankSupportId=options.text();//银行
			var bankCard=$("#bankCard").val();//银行卡号
			var bankBranch=$("#bankBranch").val();//开户行
			var reservedPhone=$("#reservedPhone").val();//预留手机号
			var idCardNum=$("#idCardNum").val();//身份证号
			var data1={"customName":cardName,"customIdCard":idCardNum,"bankName":bankSupportId,"bankAccountNum":bankCard,"userMoble":reservedPhone,"applyType":2};
			var result=checkFourGeneral(data1);
			 if(result=="00"){
				 //$.alert({text:"验证通过!"});
				 //return;
			 }else{
				 $.alert({text:result});
				 return;
			 }
			if(cusId && data){
				$.ajax({
					url:ctx+"/bankcard/insertBankcard",
					type:"post",
					datetype:"json",
					async : false,
					data:data,
					success : function(data){
						if (data.msg == "00") {
							var result = data.result;
							if(result && result.code == 1){
								$.alert({millis:5000,text:"新增成功"});
								resetFormCustomerBankCard();
								 myBankcard();
							}else{
								$.alert({millis:5000,text:result.msg||"新增失败"});
							}
						}else{
							$.alert({millis:5000,text:result.msg||"新增失败"});
						}
					}
				});
			}
		}
		
		function deleteBankcard(id){
			 $.confirm({text:"确认删除吗？",callback:function(r){
					if(r){
						if(id){
							$.ajax({
								url:ctx+"/bankcard/deleteBankcard",
								type:"post",
								datetype:"json",
								async : false,
								data:{id:id},
								success : function(data){
									if (data.msg == "00") {
										var result = data.result;
										if(result && result.code == 1){
											$.alert({millis:5000,text:"删除成功"});
											 myBankcard();
										}else{
											$.alert({millis:5000,text:"删除失败"});
										}
									}else{
										$.alert({millis:5000,text:"删除失败"});
									}
								}
							});
						}
					}
				}})
		}
		
		function checkCardName(){
			var cardName="";
			var oId = $("#checkedOrderId").val();
			$.ajax({
				url:ctx+"/agreement/checkCardName",
				type:"post",
				datetype:"json",
				async : false,
				data:{
					orderId:oId
				},
				success : function(data){
					if (data.msg == "00") {
						cardName=data.cardName;
					}
				}
			});
			return cardName;
		}
		
		
			$("#formCustomerBankCard").bootstrapValidator({
				message: 'This value is not valid',
				excluded: ':disabled,:hidden',
				feedbackIcons: {
					valid: 'glyphicon glyphicon-ok',
					invalid: 'glyphicon glyphicon-remove',
					validating: 'glyphicon glyphicon-refresh'
				},
				fields: {
					cardName: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 0,
			                    max: 16,
			                    message: '16字以内'
			                },
							regexp: {
								regexp: /^[a-zA-Z\u4e00-\u9fa5]+$/,
								message: '支持中英文'
							}
						} 
					},
					idCardNum: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 18,
			                    max: 18,
			                    message: '长度18位'
			                },
							regexp: {
								regexp: /^[0-9X]+$/,
								message: '格式不正确'
							}
						}
					},
					bankSupportId: {
						validators: {
							notEmpty: {
								message: '必填'
							}
						}
					},
					bankCard: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 0,
			                    max: 29,
			                    message: '30字以内'
			                },
							numeric:{
								message: '只能为数字'
							}
						}
					},
					bankBranch: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 0,
			                    max: 29,
			                    message: '30字以内'
			                },
							regexp: {
								regexp: /^[a-zA-Z\u4e00-\u9fa5]+$/,
								message: '支持中英文'
							}
						}
					},
					bankCity: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 0,
			                    max: 15,
			                    message: '15字以内'
			                },
							regexp: {
								regexp: /^[\u4e00-\u9fa5]+$/,
								message: '支持中文'
							}
						}
					},
					reservedPhone: {
						validators: {
							notEmpty: {
								message: '必填'
							},
							stringLength: {
			                    min: 11,
			                    max: 11,
			                    message: '长度11位'
			                },
							regexp: {
								regexp: /^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0-9])|(18[0-9])|166|198|199|(147))\d{8}$/,
								message: '格式不正确'
							}
						}
					}
				}
			}).on('success.form.bv', function(e) {
				//阻止表单提交
				e.preventDefault();
				//表单提交
				insertBankcard();
			});
</script>
</html>

