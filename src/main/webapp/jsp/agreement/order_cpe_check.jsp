<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
/* 提示框长单词或数字自动换行 */
.popover-content {
	word-wrap: break-word
}
</style>
</head>
<body >
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12 -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="javascript:void(0)">生产管理</a></li>
                <li><a href="javascript:void(0)">订单管理系统</a></li>
                <li><a href="javascript:void(0)">订单模块管理</a></li>
                <li class="active">合同审核</li>
            </ol>
        </div>
    </div>
    <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <tbody class="dataTable_wrapper">
                            <div class="dataTable-info">
                                <header>
                                    <h4>查询</h4>
                                    <!--表单显示及隐藏-->
                                    <div class="drop-on">
                                        <span><i class="glyphicon glyphicon-chevron-up"></i></span>
                                    </div>
                                    <div class="drop-down">
                                        <span><i class="glyphicon glyphicon-chevron-down"></i></span>
                                    </div>
                                </header>
                                <div class="info-select clearfix">
                                    <form class="form-inline" id="agreement_query_form">
                                    <div class="row">
                                    	<div class="form-group  col-xs-3">
                                    		<label><span style="font-weight:bold;">订单编号:</span>
   												<input type="text" name="orderCode" id="orderCode" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-3">
                                    		<label><span style="font-weight:bold;">客户姓名:</span>
   												<input type="text" name="userName" id="userNameOrder" class="form-control"/>                    		
                                    		</label>
                                    	</div>
                                    	<div class="form-group  col-xs-3">
                                    		<label><span style="font-weight:bold;">客户电话:</span>
                                    			<input type="text" name="userMobile" id="userMobileOrder" class="form-control"/>
                                    		</label>
                                    	</div>
                                    	<div class="form-group col-xs-3">
										<label><span style="font-weight:bold;">合同状态:</span>
										<select class="form-control" name="agreementState" >
													<option value="">...请选择...</option>
													<option value="1">新建</option>
													<option value="2">已确认</option>
													<option value="3">已终止</option>
													<option value="4">已完成</option>
													<option value="5">已删除</option>
													<option value="6">签约中</option>
													
										</select>
	                        			</label>
                        			</div>
                                    </div>
                                 <div class="row">
                                    <div class="form-group col-xs-3">
										<label><span style="font-weight:bold;">负责部门:</span>
										<select name="rechargeDept"  id="mendianSelect" class="form-control"  style="width:220px;">
										<option value="">...请选择...</option>
										</select>
	                        			</label>
                        			</div>
                        			 <div class="form-group col-xs-3">
										<label><span style="font-weight:bold;">负责人&nbsp;&nbsp;&nbsp;&nbsp;:</span>
										<select name="rechargeBy" id="guanjiaSelect" class="form-control"  >
										<option value="">...请选择...</option>
						  				</select>
	                        			</label>
                        			</div>
                        			 <div class="form-group col-xs-3">
										<label><span style="font-weight:bold;">审核状态:</span>
										<select  name="checkStatus" id="checkStatus" class="form-control">
										  <option value="">...请选择...</option>
										  <option value="1">未处理</option>
										  <option value="2">审核通过</option>
										  <option value="3">驳回</option>
										</select>
	                        			</label>
                        			</div>
                        			
                        			 <div class="form-group col-xs-3">
										<label><span style="font-weight:bold;">签约状态:</span>
										<select  name="elecOtherState" id="elecOtherState" class="form-control">
										  <option value="">...请选择...</option>
										  <option value="1">合同待签约</option>
										  <option value="2">已推送</option>
										  <option value="3" selected = "selected">三方已签约</option>
										  <option value="4">已电子签章认证</option>
										  <option value="5">签章已驳回</option>
										</select>
	                        			</label>
                        			</div>
								</div>
								<div class="row">
                                       	<div class="form-group  col-xs-9">
                                       <label><span style="font-weight:bold;">签订日期：</span>
                                       		<input id="creStart" class="Wdate form-control" type="text" name="creStart"
									        		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									        &nbsp;至&nbsp;
									        <input id="creEnd" class="Wdate form-control" type="text"  name="creEnd"
								        		onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                                       	</label>
                                       	</div>
                                       	<div class="form-group col-xs-3 col-xs-offset-9">
											<button class="btn btn-sm btn-default" type="reset">
											<span  class="glyphicon glyphicon-refresh"></span>重置</button>
											<button class="btn btn-sm btn-default" type="button" id="agreement_query_button">
										<span  class="glyphicon glyphicon-search"></span>查询</button>
                        			</div>
                                  </div>
								  <!-- <div class="row">
                        			 <div class="form-group col-xs-3 col-xs-offset-9">
										<button class="btn btn-sm btn-default" type="reset">
										<span  class="glyphicon glyphicon-refresh"></span>重置</button>
										<button class="btn btn-sm btn-default" type="button" id="agreement_query_button">
										<span  class="glyphicon glyphicon-search"></span>查询</button>
                        			</div>
								</div> -->
                              </form>
                             </div>
                            </div>
                            
                            <div class="panel-heading panel-new">
                            	<button class="btn btn-sm btn-default" type="button" id="agreement_accept_button">
                                 	<span class="glyphicon glyphicon-ok"></span>审核通过
                                </button> 
                                <button class="btn btn-sm btn-default" type="button" id="agreement_deny_button">
                                 	<span class="glyphicon glyphicon-remove"></span>驳回
                                </button> 
                                <button class="btn btn-sm btn-default" type="button" onclick="exportContract();">
				                	<em class="glyphicon glyphicon-export"></em>导出数据
				                </button>
                            </div>
                                
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
									<table class="table table-hover table-striped"  id="agreement_table">
										<thead>
											<tr>
												<th>序号</th>
												<th>订单编号</th>
												<th>合同编号</th>
												<th>查看合同</th>
												<th>合同状态</th>
												<!-- 20180713 add -->
												<th>签约状态</th>
												<!-- end-->
												<th>审核状态</th>
												<th>甲方</th>
												<th class="text-left">乙方</th>
												<th class="text-left">负责部门</th>
												<th>负责人</th>
												<th>更新时间</th>
												<%--<th>签订日期</th>--%>
												<%--<th>开始日期</th>--%>
												<%--<th>结束日期</th>--%>
											</tr>
										</thead>
										<tbody id="agreement_tbody">
										</tbody>
									</table>
						</div>
                            <div class="pagination pagination-sm navbar-right" id="agreement_page_info"></div>
                            </tbody>
                        </div>
                    </div>
                    </div>
    </div>
    
    <script type="text/javascript" src="${ctx}/js/jquery-extends-base.js"></script>
    
	<script type="text/javascript">
	$(function() {
		//查询负责部门
		queryDept("mendianSelect");
		queryCheckAgreement_listPage(1,10);
	    //查询
		$("#agreement_query_button").on("click",function(){
			queryCheckAgreement_listPage(1,10); 
		})
		
		//审核通过
		$("#agreement_accept_button").on("click",function(){
			var data = [];
			var agreements = $("#agreement_tbody input[type='checkbox']:checked");
			agreements.each(function(i,v){
				var id = $(v).data("agreement");
				if(id){
					data.push(id)
				}
			})
			agreementAccept(data);
		})
		
		//驳回
		$("#agreement_deny_button").on("click",function(){
			var data = {};
			var agreement = $("#agreement_tbody input[type='checkbox']:checked");
			if(agreement.length == 0){
				$.alert({millis:3000,text:"请选择合同！"});	
				return;
			}else if(agreement.length > 1){
				$.alert({millis:3000,text:"只能选择<b>1</b>条记录！"});	
				return;
			}else{
				var url = "${ctx}/jsp/agreement/order_cpe_deny2.jsp";
				var agre = agreement.data("agreement");
				var checkStatus = agre.checkStatus;//审核状态
				var agreementState = agre.agreementState;//合同状态
				/* if(agreementState != 2 && agreementState != 3 && agreementState != 4){
					$.alert({millis:5000,text:"仅可审核<strong>已确认、已终止、己完成</strong>合同状态的合同"});
					return;
				}
				if(checkStatus == 2){
					$.alert({millis:4000,text:"当前合同己<strong>审核通过</strong>！"});
					return;
				} */
				openModule(url,{"id":agre.id,"version":agre.version,"agreemenetModel":agre.agreemenetModel||""},null,{"width":"60%"},"deny");
			}
		})
		tabx(".tab-item",".main");
	    drop(".drop-on",".drop-down",".info-select");
	})
	
	
	/**查询订单列表*/
	function queryCheckAgreement_listPage(curPage, pageCount) {
		var data = $("#agreement_query_form").serialize()+"&curPage="+curPage+"&pageCount="+pageCount;
		$.ajax({
			url : "${ctx}/agreement/queryCheckAgreement_listPage", 
			type : "post",
			data:data,
			dataType : "json",
			async : false,
			success : function(data) {
				showMsg(data.msg);
				$("#agreement_page_info").empty().pagination(data.page,queryCheckAgreement_toPage);
				var pageCount = data.page.pageCount;
				var curPage = data.page.curPage;
				var total = curPage * pageCount;
				var html = "";
				if (data.msg == "00") {
					$.each(data.list,function(i,v){
						var agreement = {"id":v.AGREEMENT_ID,"version":v.VERSION,"agreementState":v.AGREEMENT_STATE,"checkStatus":v.CHECK_STATUS,"elecOtherState":v.ELEC_OTHER_STATE,"normalContactFile":v.NORMALCONTACTFILE,"agreemenetModel": v.AGREEMENT_MODEL};
						html += "<tr><td><input  type='checkbox' name='agreement'  data-agreement='"+(JSON.stringify(agreement)||"")+"' value='"+v.AGREEMENT_ID+"'>";
						html += "&nbsp;"+(total + (i + 1) - pageCount)+"</td>";
						html += "<td>"+(v.ORDER_CODE||"")+"</td>";
						html += "<td>"+(v.AGREEMENT_CODE||"")+"</td>";
						/* html += "<td><a href='javascript:void(0)' onclick='agreementDetail("+v+");'>查看</a></td>"; */
						html += "<td><a href='javascript:void(0)' onclick='agreementDetail("+v.AGREEMENT_ID+","+v.ORDER_ID+","+v.ORDER_TYPE+","+v.AGREEMENTMODEL+",\""+v.NORMALCONTACTFILE+"\");'>查看</a></td>";
						//html += "<td><a href='javascript:void(0)' onclick='agreementDetail("+this+");'>查看</a></td>";
						html += "<td>"+(v.AGREEMENT_STATE_TEXT||"")+"</td>";
						html += "<td>"+(v.ELEC_OTHER_STATE_TEXT||"-")+"</td>";
						console.log(html);
						if(v.CHECK_STATUS == 2){
							html += "<td name='CHECK_STATUS_TEXT' style='color:green' >"+(v.CHECK_STATUS_TEXT||"")+"</td>";
						}else if(v.CHECK_STATUS == 3){
							var popoverStr = " title='驳回原因' data-container='body' data-trigger='hover' data-toggle='popover' data-placement='right' data-content='"+(v.CHECK_INSTRUCTIONS||"无")+"' ";
							html += "<td name='CHECK_STATUS_TEXT' style='color:red;'  "+popoverStr+">"+(v.CHECK_STATUS_TEXT||"")+"</td>";
						}else{
							html += "<td name='CHECK_STATUS_TEXT'>"+(v.CHECK_STATUS_TEXT||"")+"</td>";
						}
						html += "<td>"+(v.PARTY_A||"")+"</td>";
						html += "<td class='text-left'>"+(v.PARTY_B||"")+"</td>";
						html += "<td class='text-left'>"+(v.RECHARGE_DEPT_NAME||"")+"</td>";
						html += "<td>"+(v.RECHARGE_BY_NAME||"")+"</td>";
						html += "<td>"+(v.CREATETIME||"")+"</td></tr>";
//						html += "<td>"+(v.EFFECT_DATE||"")+"</td>";
//						html += "<td>"+(v.EFFECT_DATE||"")+"</td>";
//						html += "<td>"+(v.FINISH_DATE||"")+"</td></tr>";
					})
				}else{
					html += "<tr><td colspan='13'>暂无数据</td></tr>";
				}
				$("#agreement_tbody").html(html);
				//表格点击行高亮
				trColor("#native_tbody > tr");
				//表格单选
				radioColor("#agreement_tbody > tr");
				$("[data-toggle='popover']").popover();
		}
	})
}
	
	 //分页跳转使用
	 function queryCheckAgreement_toPage(curPage,pageCount) {
		queryCheckAgreement_listPage(curPage,pageCount);
	}
	 
	 /***审核通过***/
		function agreementAccept(){
			var data = {};
			var agreement = $("#agreement_tbody input[type='checkbox']:checked");
			if(agreement.length == 0){
				$.alert({millis:3000,text:"请选择合同！"});	
				return;
			}else if(agreement.length > 1){
				$.alert({millis:3000,text:"只能选择<b>1</b>条记录！"});	
				return;
			}else{
				var agre = agreement.data("agreement");
				var checkStatus = agre.checkStatus;//审核状态
				var agreementState = agre.agreementState;//合同状态
				/* if(agreementState != 2 && agreementState != 3 && agreementState != 4){
					$.alert({millis:5000,text:"仅可审核<strong>已确认、已终止、己完成</strong>合同状态的合同"});
					return;
				}
				if(checkStatus == 2){
					$.alert({millis:5000,text:"当前合同己<strong>审核通过</strong>！"});
					return;
				} */
				data.id = agre.id;
				data.version = agre.version;
				data.checkStatus = 2;
			$.confirm({text:"审核无误,确认审核通过?",callback:function(r){
				if(r){
				$.ajax({
			        url:"${ctx}/agreement/checkAgreement",
			        data:data,
			        type:'post',
			        async:false,
			        dataType:"json",
			        success:function(data){
			        	if(data.msg == "00"){
			        		closeModule();
			        		$.alert({millis:3000,text:"审核成功!"});
			        		queryCheckAgreement_listPage(1, 10);
			        	}else{
			        		$.alert({millis:3000,text:"审核失败!"});
			        	}
			        }
			    })
				}
				}
			});
				
			}
		}
	
	/**查询管家*/
	function queryManager(){
		var deptId = $("#mendianSelect option:selected").val();
		var html = "<option style='color:blue;' value=''>...请选择...</option>";
		if(deptId){
			$.ajax({
				url:"${ctx}/orderbase/queryguanjia",
				data : {"deptId" : deptId},
				type : 'post',
				async : false,
				dataType : "json",
				success : function(data) {
					if (data.msg == "00") {
						$.each(data.list,function(i,v){
							html += "<option value='" + v.id + "'>"+v.realName+"</option>";
						})
					} else if(data.msg== "02"){
						$.alert({millis:2000,text:"门店无管家!"});
					}else{
						$.alert({millis:2000,text:"查询出错,请稍后再试!"});
					}
				}
			})
		}
		$("#guanjiaSelect").empty().html(html);
		$("#guanjiaSelect").nextAll("div").remove();
		$("#guanjiaSelect").checkSelect();
	}
	
	/**查询部门*/
	function queryDept(typeId){
	    $.ajax({
	        url:"${ctx}/orderbase/querydeptname",
	        data:{},
	        type:'post',
	        async:false,
	        dataType:"json",
	        success:function(data){
	            var html = "<option style='color:blue;' name='4' value='' >...请选择...</option>";
	            $.each(data.list,function(i,v){
	                html +="<option value='" +v.dkey +"' keyValue='"+v.dvalue +"'>"+v.dvalue+"</option>";
	            })
	            $("#"+typeId).html(html);
	            $("#"+typeId).nextAll("div").remove();
	            $("#"+typeId).checkSelect(function(a,b,c){
	            		queryManager();
	              });
	        }
	    })
	    
	}
	
	/**查询合同详情**/
	function agreementDetail(agreementId,orderId,orderType,agreementModel,normalContactfile){
		var url = "${ctx}/jsp/agreement/order_cpe_detail.jsp";
		var param = {"contractId":agreementId,"orderId":orderId,"orderType":orderType,"orgType":1,"agreementModel":agreementModel,"normalContactfile":normalContactfile};
		openModule(url,param,{},{width:'60%'},"agreementDetail"); 
		
	}
	
	/**
	 * 导出符合条件审核成功的合同
	 */
	function exportContract(){
		var ctx=$("#ctx").val();
		var data = $("#agreement_query_form").serialize();
		var ids = $("#agreement_table").GetCheckboxCheckedValue();
		if (ids.length > 0) data += "&ids=" + ids;
		$.ajax({
			url:ctx+'/agreement/exportContract/',
			data:data,
			dataType: "json",
			async:false,
			type:'post',
			success:function(data){
				if(data.msg=='00'){//如果已经搞定
					window.location.href=ctx+"/"+data.url;
				} else if (data.msg == '01'){
					$.alert({millis:2000,text:"导出失败!"});
				} else {
					$.alert({millis:2000,text:data.msg});
				}
			}
		}); 
	}
	</script>
	
</body>
 
</html>

