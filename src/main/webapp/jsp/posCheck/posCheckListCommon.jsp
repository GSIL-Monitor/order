<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<script type="text/javascript" src="${ctx}/js/orderBase.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery-extends-base.js"></script>
	<script type="text/javascript" src="${ctx}/js/posCheck/posCheck.js"></script>
	<script type="text/javascript" src="${ctx}/js/posCheck/posCheckCommon.js"></script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12  -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">订单管理系统</a></li>
                <li><a href="#">POS凭条审核</a></li>
                <li class="active">POS凭条审核列表</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
            <div class="col-lg-12" >
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
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
                                     <form class="form-inline" id="pos_check_common_form">
                                    <div class="row">
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>交易参考号：</p>
                                                    <input type="text" class="form-control" name="bankFlowNum" id="common_pos_bankFlowNum"/>
                                                </label>
                                            </div>
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>户名：</p>
                                                    <input type="text" class="form-control" name="payUser" id="common_pos_payUser"/>
                                                </label>
                                            </div>
                                     </div>
                                     <div class="row">
                                        	 <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>是否对账：</p>
		                                            <select   name="accountStatus" id="common_pos_accountStatus" class="form-control" >
														<option value="">...请选择...</option>
														<option value="1">是</option>
														<option value="2">否</option>
													</select>
                                                </label>
                                            </div>
                                        	 <div class="form-group  col-xs-6">
                                                <label>
                                                	<p>审核状态:</p>
	                                    			<select name="posCheckStatus" id="common_pos_posCheckStatus" class="form-control" >
	                                    				<option value="">...请选择...</option>
	                                    				<option value="1">待审核</option>
	                                    				<option value="2">通过</option>
	                                    				<option value="3">驳回</option>
	                                    			</select>
	                                    		</label>
                                            </div>
                                      </div>
                                     <div class="row">
                                        	 <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>支付类型：</p>
		                                            <select   name="feePost" id="common_pos_feePost" class="form-control" >
														<option value="">...请选择...</option>
														<option value="20250005">POS机刷卡</option>
														<option value="20250008">融汇天下POS</option>
														<!-- <option value="20250017">嘉联POS机微信</option>
														<option value="20250018">嘉联POS机支付宝</option> -->
														<option value="20250019">嘉联POS机刷卡</option>
													</select>
                                                </label>
                                            </div>
                                        	 <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>POS机凭条来源：</p>
		                                            <select   name="feeType" id="common_pos_feeType" class="form-control">
														<option value="">...请选择...</option>
														<option value="1">订单缴费</option>
														<option value="3">服务人员缴费</option>
														<option value="4">卡售卖缴费</option>
														<option value="5">其他缴费</option>
													</select>
                                                </label>
                                            </div>
                                      </div>
                                    <div class="row">
                                   		 <div class="form-group  col-xs-12">
                                        	<label>
                                        		<p>对账时间：</p>
                                        		<input id="common_pos_confirmTime" class="Wdate form-control" type="text" name="confirmTime"
										        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
										        &nbsp;至&nbsp;
										        <input id="common_pos_confirmEndTime" class="Wdate form-control" type="text" name="confirmEndTime"
									        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                        	</label>
                                        </div>
                                     </div>
                                    <div class="row">
                                   		 <div class="form-group  col-xs-12">
                                        	<label>
                                        		<p>提交时间：</p>
                                        		<input id="common_pos_createTime" class="Wdate form-control" type="text" name="createTime"
										        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
										        &nbsp;至&nbsp;
										        <input id="pos_createEndTime" class="Wdate form-control" type="text" name="createEndTime"
									        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                        	</label>
                                        </div>
                                     </div>
                                     <div class="row">
	                                    	<div class="form-group  col-xs-6">
	                                    		<label><p>省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份:</p>
	                                    			<select name="checkProvince" id="common_pos_checkProvince" onclick="setSelCity('common_pos_checkProvince','common_pos_checkCity')" class="form-control"/>
	                                    		</label>
	                                    	</div>
	                                    	<div class="form-group  col-xs-6">
	                                    		<label><p>城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市:</p>
	                                    			<select name="checkCity" id="common_pos_checkCity" class="form-control" onchange="loadCategoryCommon(null,1,'#common_search_category_1');">
	                                    				<option value="">...请选择...</option>
	                                    			</select>
	                                    		</label>
	                                    	</div>
	                                  </div>
                                     <div class="row">
										<div class="form-group col-xs-12">
											<label>
												<p>订单类型：</p>
												 <select class="form-control" id="common_search_category_1" 
													onchange="loadCategoryCommon(this,2,'#common_search_category_2');">
													<option value='' selected="selected">--1级分类--</option>
												</select>
												<select class="form-control"  id="common_search_category_2"
													onchange="loadCategoryCommon(this,3,'#common_search_category_3');">
													<option value='' selected="selected">--2级分类--</option>
												</select>
												<select class="form-control" id="common_search_category_3"
													onchange="loadCategoryCommon(this,'','');">
													<option value='' selected="selected">--3级分类--</option>
												</select>
											</label>
										</div>
									</div>
                                      <div class="row">
	                        			 <div class="form-group col-xs-3 col-xs-offset-9">
											<button class="btn btn-sm btn-default" type="reset">
											<span  class="glyphicon glyphicon-refresh" id="pos_search_reset"></span>重置</button>
											<button class="btn btn-sm btn-default" type="button" id="common_pos_search_button">
											<span  class="glyphicon glyphicon-search"></span>查询</button>
	                        			</div>
									 </div>
                                     </form>
                                 </div>
                                 <table class="table table-condensed" style="border-bottom: 1px solid #CCC;">
									<tr>
										<td colspan="2" >未处理项：<span id="common_pos_check_items" style="color:red;"></span>
										</td>
									</tr>
								</table>
                            </div>
                            <div class="panel-heading panel-new">
                            	<!-- <button class="xinzeng btn btn-sm btn-default"  onclick="common_updatePosCheck(2);" id="common_pass_pos_button">
								<em class="widget-new glyphicon glyphicon glyphicon-ok"></em><span>审批通过</span></button>
                            	<button class="xinzeng btn btn-sm btn-default"  onclick="common_updatePosCheck(3);" id="common_nopass_pos_button">
								<em class="widget-new glyphicon glyphicon glyphicon-remove"></em><span>驳回</span></button> -->
                            	<button class="xinzeng btn btn-sm btn-default"  onclick="common_exportPosCheck();">
								<em class="widget-new glyphicon glyphicon glyphicon-export"></em><span>批量导出图片</span></button>
								<button class="xinzeng btn btn-sm btn-default"  onclick="common_exportPosCheckExcel();">
								<em class="widget-new glyphicon glyphicon glyphicon-open-file"></em><span>导出Excel</span></button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </div>
                                
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" id="common_pos_check_table">
                                    <thead>
                                    <tr>
										<th ></th>
										<th >序号</th>
										<th >户名</th>
										<th >申请号</th>
										<th >参考号</th>
										<th >订单类型</th>
										<th >订单编号</th>
										<th >结算单号</th>
										<th >交易日期</th>
										<th >交易金额</th>
										<th >提交人部门</th>
										<th >提交人</th>
										<th >提交日期</th>
										<th >城市</th>
										<th >支付类型</th>
										<th >是否对账</th>
										<th >对账时间</th>
										<th >凭条影像</th>
										<th >审核状态</th>
										<th >凭条来源</th>
										<th >审核人部门</th>
										<th >审核人</th>
										<th >审核日期</th>
                                    </tr>
                                    </thead>
                                    <tbody id="common_posCheckListBody">
                                    </tbody>
                                </table>
                            </div>
                                <ul class="pagination pagination-sm navbar-right" id="pagePosCheckCommonDiv"></ul>
                            </div>
                        </div>
                    </div>
               </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function() { 
		queryPosCheckCommonByLike(0,10);
		selectOrderDeptName("common_pos_createDeptString"); 
		//加载省份
		setSelProvinceCitys('101',6,'common_pos_checkProvince');
	    /*loading*/
	    $('#shclDefault').shCircleLoader();
	    /*tab栏选择*/
	    tabx(".tab-item",".main");
	    /*表单合并*/
	    drop(".drop-on",".drop-down",".info-select");
	    /*Tab栏滑动*/
	    tabs(".mytabs-wrap");
	    
	    //查询
		$("#common_pos_search_button").on("click",function(){
			queryPosCheckCommonByLike(1,10); 
		})
	    //清空三级分类
		$("#common_pos_checkProvince").change(function(){
			$("#common_search_category_1").html("<option value='' selected = 'selected'>--1级分类--</option>");
			$("#common_search_category_2").html("<option value='' selected = 'selected'>--2级分类--</option>");
			$("#common_search_category_3").html("<option value='' selected = 'selected'>--3级分类--</option>");
		})
		$("#common_pos_checkCity").change(function(){
			$("#common_search_category_2").html("<option value='' selected = 'selected'>--2级分类--</option>");
			$("#common_search_category_3").html("<option value='' selected = 'selected'>--3级分类--</option>");
		})
		 //重置按钮
		$("#common_pos_search_reset").on("click",function(){
			$("#common_pos_bankFlowNum").val('');
			$("#common_pos_posCheckStatus").val('');
			$("#common_pos_createDeptString").val('');
			$("#common_pos_createByString").val('');
			$("#common_pos_createTime").val('');
			$("#common_pos_createEndTime").val('');
		})
		//清空提交人下拉选
		$("#common_pos_createDeptString").on("click",function(){
			if($(this).val() == null && $(this).val() == ""){
				$("#common_pos_createDeptString").html("<option style='color:blue;' value='' >...请选择...</option>");
			}
		}) 
	})
	
	//加载管家
	function selguanjia(){
		var ctx=$("#ctx").val();
		var  deptId = $("#common_pos_createDeptString option:selected").val();
		var guanjiaSelectval=$("#common_pos_createByString").find("option:selected").val();
		if(deptId==""||deptId==null){
			$("#common_pos_createByString").empty();
	    	$("#common_pos_createByString").html("<option value=''>...请选择...</option>");
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
							html += "<option   value='" + v.id + "'>"+v.realName+"</option>";
						});
						
					} else if(data.msg== "02"){
						$.alert({millis:2000,text:"门店无管家!"});
					}else{
						$.alert({millis:2000,text:"查询出错，请稍后再试!"});
					}
					$("#common_pos_createByString").html(html);
				}
			});
		}
	}
</script>
</html>

