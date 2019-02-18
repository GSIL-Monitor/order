<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
	<script type="text/javascript" src="${ctx}/js/orderBase.js"></script>
	<script type="text/javascript" src="${ctx}/js/quality.js"></script>
	<script type="text/javascript" src="${ctx}/js/vipshop.js"></script>
</head>
<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
<!-- /.col-lg-12  -->
    <div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">订单管理系统</a></li>
                <li><a href="#">唯品会退费</a></li>
                <li class="active">唯品会退费列表</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
            <div class="col-lg-8" >
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
                                 <input type="hidden" id="vph_userLoginLevel">
                                    <form class="form-inline">
                                    <div class="row">
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>售后单ID：</p>
                                                    <input type="text" class="form-control" id="vph_afterSaleIdSearch"/>
                                                </label>
                                            </div>
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>订单编号：</p>
                                                    <input type="text" class="form-control" id="vph_orderCodeSearch"/>
                                                </label>
                                            </div>
                                     </div>
                                    <div class="row">
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>客户姓名：</p>
                                                    <input type="text" class="form-control" id="vph_userNameOrder"/>
                                                </label>
                                            </div>
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>客户电话：</p>
                                                    <input type="text" class="form-control" id="vph_userMobileOrder"/>
                                                </label>
                                            </div>
                                     </div>
                                     <div class="row">
                                        	<div class="form-group  col-xs-6">
                                                <label>
                                                    <p>售后单状态：</p>
                                                    <select id="vph_orderStatus" class="form-control">
															<option value="" selected="selected">...请选择...</option>
															<option value="20130004" >审核中</option>
															<option value="20130005" >审核失败</option>
															<option value="20130006" >审核成功</option>
															<option value="20130007" >退款中</option>
															<option value="20130008" >退款成功</option>
															<option value="20130009" >退款失败</option>
													</select>
                                                   	
                                                </label>
                                            </div> 
                                            <div class="form-group  col-xs-6">
                                                <label>
                                                    <p>唯品会订单编号：</p>
                                                    <input type="text" class="form-control" id="vph_id"/>
                                                </label>
                                            </div>
                                            
                                      </div>
                                      <div class="row">
                                       	<div class="form-group col-xs-12">
						                        <label>
						                           <p>退费对象：</p>
						                            <select class="form-control"  id="vph_refundObject_Search" >
						                            		 <option value="">...请选择...</option>
						                                     <option value="1">唯品会</option>
						                                     <option value="2">客户</option>
						                             </select>
						                        </label>
						                    </div>
                                       </div>
                                     <div class="row">
                                        	<div class="form-group  col-xs-10">
                                        	<label>
                                        		<p>创建时间：</p>
                                        		<input id="vph_creStart" class="Wdate form-control" type="text" 
										        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
										        &nbsp;至&nbsp;
										        <input id="vph_creEnd" class="Wdate form-control" type="text" 
									        		onfocus="WdatePicker({maxDate:'%y-%M-{%d}',dateFmt:'yyyy-MM-dd'})" />
                                        	</label>
                                        	</div>
                                        	<div class="form-group col-xs-1">
                                        		<button type="button" class="btn btn-sm btn-default fr mr20" onclick="queryVPHSalesByLike(0,10);">查询</button>
                                        	</div>
                                        	<div class="form-group col-xs-1">
                                        		<button type="button" class="btn btn-sm btn-default fr mr20" onclick="resetVphSales();">重置</button>
                                        	</div>
                                        </div>
                                     </form>
                                 </div>
                            </div>
                            <div class="panel-heading panel-new">
                            	<button class="xinzeng btn btn-sm btn-default"  onclick="updateVPHsales(1);"><em
								class="widget-new glyphicon glyphicon glyphicon-plus-sign"></em><span>新增退款</span></button>
                            	<button class="xinzeng btn btn-sm btn-default"  onclick="updateVPHsales(2);"><em
								class="widget-new glyphicon glyphicon glyphicon-ok-sign"></em><span>修改</span></button>
                            </div>
                                
                            <!--列表开始-->
                            <div class="panel-content table-responsive">
                                <table class="table table-hover table-striped" >
                                    <thead>
                                    <tr>
										<th></th>
										<th >序号</th>
										<th >售后单ID</th>
										<th >售后状态</th>
										<th >订单编号</th>
										<th >唯品会订单号</th>
										<th >退款金额</th>
										<th >退款对象</th>
										<th >客户姓名</th>
										<th >客户电话</th>
										<th >创建时间</th>
                                    </tr>
                                    </thead>
                                    <tbody id="vphListBody">
                                    </tbody>
                                </table>
                            </div>
                                <ul class="pagination pagination-sm navbar-right" id="pageVphDiv"></ul>
                            </tbody>
                        </div>
                    </div>
               </div>
             <!-- /.col-lg-4 -->
           	<div class="col-lg-4">
				<div class="aside-tab">
                    <jsp:include page="/jsp/quality/qualityRight.jsp"></jsp:include>
                    </div>
             </div>
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function() { 
		queryVPHSalesByLike(0,10);
	    /*loading*/
	    $('#shclDefault').shCircleLoader();
	    /*tab栏选择*/
	    tabx(".tab-item",".main")
	    /*表单合并*/
	    drop(".drop-on",".drop-down",".info-select");
	    /*Tab栏滑动*/
	    tabs(".mytabs-wrap");
	})
	function updateVPHsales(flag){
		if(flag != null && flag == 1){
			var loginLevel = $("#vph_userLoginLevel").val();
			openModule('/order/jsp/vipshop/vphUserList.jsp',{loginLevel:loginLevel,orderId:orderId,flag:flag},{},{width:'70%'},'vipshopList');
		}else{
			var asInfo = $("input[name='vphRadio']:checked").val();
			var asArr = asInfo.split("-");
			var afterSalesId = asArr[0];  //售后单id
			var orderId = asArr[1];		  //订单id
			var auditFlag = asArr[2];	  //审批状态
			//待确认、确认无效、审核失败和退费失败可以修改
			if(auditFlag == "20130001" || auditFlag == "20130002" || auditFlag == "20130005" || auditFlag == "20130009" ){
				openModule('/order/jsp/vipshop/vipshopEdit.jsp',{afterSalesId:afterSalesId,orderId:orderId,flag:flag},{},{width:'70%'},'vipshopList');
			}else{
				$.alert({millis:3000,top:'30%',text:"请选择待确认、审核失败和退费失败的售后单！"});
			}	
		}
	}
	
</script>
</html>

