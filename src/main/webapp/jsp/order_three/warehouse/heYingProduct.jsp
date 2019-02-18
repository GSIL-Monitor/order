<%@ page language="java" import="java.util.*,com.emotte.server.util.CookieUtils" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/emotte-auth" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
		* { margin: 0; padding: 0; }
		ul, li { list-style: none; }
		.clearfix { zoom: 1; } 
		.clearfix:before, .clearfix:after { display: table; line-height: 0; content: ""; }
		.clearfix:after { clear: both;}
		.wrap_message_en{
			margin: 10px auto 0;
			width: 92%;
		}
		.message{
			margin-top: 10px;
		}
		.message p{
			float: left;
			color: #888888;
			font-size:14px;
		}
		.message span{
			float: left;
			margin-left: 9%;
			font-size:14px;
		}
		.odd_numbers{
			color: red;
		}
		.progresp ul li{
			display: block;
			font-size:14px;
		}
		.progresp{
			margin: 30px 0;
		}
		.time{
			float: left;
			width: 20%;
		}
		.time a{
			display: block;
			text-align: center;
			color: #888888;
			line-height: 24px;
			font-size:14px;
		}
		.date{
			font-size: 12px;
		}
		.trip p{
			width: 66%;
			color: #888888;
			float: left;
			margin: 9px 0 0 8%;
			line-height: 24px;
			font-size:14px;
		}
		.pic_enp{
			width: 1px;
			height: 30px;
			background: #ddd;
			margin: 10px 0 10px 9%;
		}
	</style>
</head>
<body>
	<div class="row">
        <div class="col-lg-12">
            <ol class="page-header breadcrumb">
               <!-- <li><a href="#">Home</a></li> -->
	                <li><a href="#">生产管理</a></li>
	                <li><a href="#">订单管理系统</a></li>
	                <li><a href="#">三方订单管理</a></li>
	                <li class="active">三方物流管理</li>
            </ol>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <input type="hidden" id="ctx" value="${ctx}" />
    <input type="hidden" id="pageCount" value="" />
	<input type="hidden" id="curPage" value="" />
	<input type="hidden" id="totalRecord" value="" />
	<input type="hidden" id="maxPage" value="" />
    <div class="row">
	<div class="col-lg-8">
    <div class="panel panel-default">
        <div class="panel-body">
        	<tbody class="dataTable_wrapper">
            <div class="dataTable-info">
                <header>
                    <h4>查询条件</h4>
                    <div class="drop-on">
                        <span><i class="glyphicon glyphicon-chevron-up"></i></span>
                    </div>
                    <div class="drop-down">
                        <span><i class="glyphicon glyphicon-chevron-down"></i></span>
                    </div>
                </header>
                <div class="info-select clearfix customQueryCon tab-content" >
                	<div class="custom_main paper-add-selected">
                		<form class="form-inline" id="custom_all_form">
	                        <div class="row">
								<div class="form-group col-xs-7 form-inline">
									<label for="custom_tel" class="has-feedback">
										<p>下&nbsp;单&nbsp;时&nbsp;间&nbsp;：</p>
										<input class="Wdate form-control" value="" name="submitStartTime" id="ordertime" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'ordertimes\')||\'2020-10-01\'}',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
										至
										<input class="Wdate form-control" value="" name="submitEndTime" id="ordertimes" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ordertime\')}',maxDate:'2020-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
									</label>
								</div>
								<div class="form-group col-xs-5 form-inline">
									<label for="custom_tel" class="has-feedback">
										<p>订&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;&nbsp;&nbsp;号：</p>
										<input type="text" value="" class="form-control" name="order.id" id="order_number">
									</label>
								</div>
								<div class="form-group col-xs-7 form-inline">
									<label for="custom_tel" class="has-feedback">
									<p>配&nbsp;送&nbsp;时&nbsp;间&nbsp;：</p>
									<input class="Wdate form-control" value="" id="deliverydate" name="order.receiptTime" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									至
									<input class="Wdate form-control" value="" id="deliverydates" name="order.receiptTime" type="text" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
									</label>
								</div>
								<div class="form-group col-xs-5 form-inline">
									<label for="custom_tel" class="has-feedback">
										<p>包&nbsp;&nbsp;&nbsp;&nbsp;裹&nbsp;&nbsp;&nbsp;&nbsp;号：</p>
										<input type="text"  value="" name="packageNumber" class="form-control" id="packageNumber">
									</label>
								</div>
								<div class="form-group col-xs-7 form-inline">
									<label for="custom_tel" class="has-feedback">
									<p>收货人电话：</p>
									<input type="text" value="" class="form-control" name="order.receiverMobile" id="receiver_mobile">
									</label>
								</div>
								<div class="form-group col-xs-5 form-inline">
									<label for="custom_tel" class="has-feedback">
										<p>订&nbsp;单&nbsp;状&nbsp;态&nbsp;：</p>
										<select class="form-control" id="orderState">
											<option value="">请选择</option>	
											<option value="2">已受理</option>	
											<option value="13">拣货中</option>	
											<option value="14">配送中</option>	
											<option value="9">已完成</option>
										</select>
									</label>
								</div>
								<div class="form-group col-xs-7 form-inline">
									<label for="custom_tel" class="has-feedback">
										<p>包&nbsp;裹&nbsp;状&nbsp;态&nbsp;：</p>
										<select class="form-control" id="state">
											<option value="">请选择</option>	
											<option value="1">新订单</option>	
											<option value="2">待发货</option>	
											<option value="3">已发货</option>	
											<option value="4">已完成</option>	
										</select>
									</label>
								</div>
								<div class="row">
		                            <div class="form-group col-xs-12">
		                                <button type="reset" class="btn btn-sm btn-default fr mr20" id="warereset">
										<em class="glyphicon glyphicon-refresh"></em>清空</button>
										<button type="button" class="btn btn-sm btn-default fr mr20" onclick="selectAll(1,10)">
										<em class="glyphicon glyphicon-search"></em>查询</button>
		                            </div>
		                        </div>
							</div>
	                    </form>
	                </div>
	            </div>
	            </div>
	            <div class="panel-heading panel-new">
          			<button type="button" class="btn btn-sm btn-default" onclick="splitParcel()">
	           			<span class="glyphicon glyphicon-user" ></span>
	                	 拆分包裹
               		</button>
          			<button type="button" class="btn btn-sm btn-default" onclick="mergeParcel()">
						<span class="glyphicon glyphicon-arrow-up"></span>
						合并包裹
					</button>
          			<button type="button" class="btn btn-sm btn-default" onclick="exportExcel()">
	               		<span class="glyphicon glyphicon-arrow-down"></span>
	               		导出订单
	               	</button>
          			<button type="button" class="btn btn-sm btn-default" onclick="openimportExcel()">
	               		<span class="add glyphicon glyphicon-plus-sign"></span>
	               		导入物流单
	               	</button>
             </div>
             <div class="client-add-main bggray">
                <form class="form-inline">
                    <div class="paper-add-content">
                        <div class="row mb10">
                            <div class="form-group col-xs-4">
                                <div class="order-news bor-all">
                                    <p class="order-news-data pb10">今日：<span id="today"></span></p>
                                    <div>
                                        <button class="btn btn-sm red" type="button" id="buttontodayneworder" onclick="selectAll(1,10,1,this)">新订单：<span id="todayneworder"></span></button>
                                        <button class="btn btn-sm red" type="button" id="buttontodayTobeshipped" onclick="selectAll(1,10,2,this)">待发货：<span id="todayTobeshipped"></span></button>
                                        <span class="shipments pl10">已发货：<span id="todayAlreadyshipped"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-xs-4">
                                <div class="order-news bor-all">
                                    <p class="order-news-data pb10">昨日：<span id="yesterday"></span></p>
                                    <div>
                                        <button class="btn btn-sm red" type="button" id="buttonyesterdayorder" onclick="selectAll(1,10,1,this)">新订单：<span id="yesterdayorder"></span></button>
                                        <button class="btn btn-sm red" type="button" id="buttonyesterdayTobeshipped" onclick="selectAll(1,10,2,this)">待发货：<span id="yesterdayTobeshipped"></span></button>
                                        <span class="shipments pl10">已发货：<span id="yesterdayAlreadyshipped"></span></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-xs-4">
                                <div class="order-news bor-all">
                                    <p class="order-news-data pb10">前日：<span id="beforeyesterday"></span></p>
                                    <div>
                                        <button class="btn btn-sm red" type="button" id="buttonbeforeyesterdayorder" onclick="selectAll(1,10,1,this)">新订单：<span id="beforeyesterdayorder"></span></button>
                                        <button class="btn btn-sm red" type="button" id="buttonbeforeyesterdayTobeshipped" onclick="selectAll(1,10,2,this)">待发货：<span id="beforeyesterdayTobeshipped"></span></button>
                                        <span class="shipments pl10">已发货：<span id="beforeyesterdayAlreadyshipped"></span></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
               	<div class="dataTable-info">
                    <div class="info-two">
                        <div class="row client-add-head" id="activeId">
                            <div class="client-add-item col-xs-2 text-c client-add-active" id="all" onclick="selectAll(1,10)">全部</div>
                            <div class="client-add-item col-xs-2 text-c" onclick="selectnewparcel(1,10)">新订单</div>
                            <div class="client-add-item col-xs-2 text-c" onclick="selecttobeshipped(1,10)">待发货</div>
                            <div class="client-add-item col-xs-2 text-c" onclick="selectdelivergoods(1,10)">已发货</div>
                            <div class="client-add-item col-xs-2 text-c" onclick="selectcomplete(1,10)">已完成</div>
                            <div class="client-add-item col-xs-2 text-c" onclick="selectUpload(1,10)">批量发货记录</div>
                        </div>
                        <div class="paper-add-content">
                            <div class="paper-add-main paper-add-selected bggray">
                                <form action="" class="form-inline">
                                	<input type="hidden" id="pageCount" value="" />
									<input type="hidden" id="curPage" value="" />
									<input type="hidden" id="totalRecord" value="" />
									<input type="hidden" id="maxPage" value="" />
                                    <div class="row">
                                    	<div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										               <th><input id="parcelall" onclick="selectAllCheckbox()" type="checkbox"></th>
								                       <th>序号</th>
								                       <th>订单号</th>
								                       <th>包裹号</th>
								                       <th>下单时间</th>
								                       <th>下单人电话</th>
								                       <th>录入部门</th>
								                       <th>负责人部门</th>
								                       <th>包裹状态</th>
									               </tr>
									           	</thead>
									           	<tbody id="selectallparcel">
															 		
												</tbody>
									       	</table>
									   	</div>
								       	<div class="pagination" id="pageInfoDivallparcel"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 新订单 -->
                            <div class="paper-add-main bggray">
                                <form action="" class="form-inline">
                                    <div class="row">
                                    	<div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										               <th><input id="parcelnew" onclick="newselectAllCheckbox()" type="checkbox"></th>
								                       <th>序号</th>
								                       <th>订单号</th>
								                       <th>包裹号</th>
								                       <th>下单时间</th>
								                       <th>下单人电话</th>
								                       <th>录入部门</th>
								                       <th>负责人部门</th>
								                       <th>包裹状态</th>
									               </tr>
									           	</thead>
									           	<tbody id="selectallparcelnew">
															 		
												</tbody>
									       	</table>
									    </div>
								       	<div class="pagination" id="pageInfoDivwareNewOrder"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 待发货 -->
                            <div class="paper-add-main bggray">
                            	<form action="" class="form-inline">
                                    <div class="row" id="a">
                                    	<div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										               <th>序号</th>
								                       <th>订单号</th>
								                       <th>包裹号</th>
								                       <th>下单时间</th>
								                       <th>下单人电话</th>
								                       <th>录入部门</th>
								                       <th>负责人部门</th>
								                       <th>包裹状态</th>
									               </tr>
									           	</thead>
									           	<tbody id="selecttobeshippedparcel">
															 		
												</tbody>
									       	</table>
									    </div>
								       	<div class="pagination" id="pageInfoDivwarebeshipped"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 已发货 -->
                            <div class="paper-add-main bggray">
                            	<form action="" class="form-inline">
                                    <div class="row">
	                                    <div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										                <th>序号</th>
								                       <th>订单号</th>
								                       <th>包裹号</th>
								                       <th>下单时间</th>
								                       <th>下单人电话</th>
								                       <th>录入部门</th>
								                       <th>负责人部门</th>
								                       <th>包裹状态</th>
									               </tr>
									           	</thead>
									           	<tbody id="selectdelivergoodsparcel">
															 		
												</tbody>
									       	</table>
									    </div>
								       	<div class="pagination" id="pageInfoDivwaredelivergoods"></div>
                                    </div>
                                </form>
                            </div>
                            <!-- 已完成-->
                            <div class="paper-add-main bggray">
                            	<form action="" class="form-inline">
                                    <div class="row">
	                                    <div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										               <th>序号</th>
								                       <th>订单号</th>
								                       <th>包裹号</th>
								                       <th>下单时间</th>
								                       <th>下单人电话</th>
								                       <th>录入部门</th>
								                       <th>负责人部门</th>
								                       <th>包裹状态</th>
									               </tr>
									           	</thead>
									           	<tbody id="selectcompleteparcel">
															 		
												</tbody>
									       	</table>
									    </div>
								       	<div class="pagination" id="pageInfoDivwarecomplete"></div>
                                    </div>
                                </form>
                            </div>
                            <div class="paper-add-main bggray">
                            	<form action="" class="form-inline">
                                    <div class="row">
	                                    <div class="panel-content table-responsive">
						                   <table class="table table-hover table-striped">
									          <thead>
									               <tr>
										                <th>编号</th>
														<th>包裹号</th>
														<th>物流公司</th>
														<th class='text-l'>物流单号</th>
														<th>发货时间</th>
														<th>操作结果</th>
														<th class='text-l'>失败原因</th>
									               </tr>
									           	</thead>
									           	<tbody id="seleceUploadLog">
															 		
												</tbody>
									       	</table>
									    </div>
								       	<div class="pagination" id="pageInfoDivwareUploadLog"></div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
		     </tbody>
       	 </div>
    	</div>
	</div>
    <div class="col-lg-4">
    	<div class="aside-tab">
    	<div class="mytabs-wrap">
            <ul class="mytabs" id="mytabs">
                <li class="tab-item tab-active">基本信息</li>
                <li class="tab-item">物流信息</li>
            </ul>
        </div>
       <!--  <div id="arr">
            <span id="left"><</span><span id="right">></span>
        </div> -->
        <div class="tab-content" id="tab_content">
        <!-- 基本信息 -->
            <div class="main tab-selected clearfix" id="mainInfo">
            	<div class="widget-content">
                   <table class="table table-condensed">
	                   	<tr>
	                   		<td><h4>订单详情</h4></td>
	                   	</tr>
	                   	<tr>
	                   		<td>
	                   			订单编号：<span id="orderCode"></span>
	                   		</td>
	                   		<td>
	                        	创建时间：<span id="orderCreateTime"></span>
	                        </td>
	                   	</tr>
	                   	<tr>
	                        <td>
	                       		 订单状态：<span id="orderStatus"></span>
	                        </td>
	                        <td>
	                       		 支付状态：<span id="orderPayStatus"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                    	<td>
	                    		客户姓名：<span id="userName"></span>
	                    	</td>
	                    	<td>
	                    		下单电话：<span id="userMobile"></span>
	                    	</td>
	                    </tr>
	                   	<tr>
	               			<td><h4>收货信息</h4></td>
	               		</tr>
	               		<tr>
	                        <td>
	                       		姓名：<span id="receiverName"></span>
	                        </td>
	                   	 	<td>
	                   	 		电话：<span id="receiverMobile"></span>
	                   	 	</td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                         	地址：<span id="orderAddr"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                         	期望送达时间：<span id="receiptTime"></span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <td colspan="2">
	                         	备注：<span id="orderRemark"></span>
	                        </td>
	                    </tr>
	                    <tr>
	               			<td><h4>商品信息</h4></td>
	               		</tr>
	               		<table class="table table-hover table-striped">
				           <thead>
				               <tr>
			                       <th>商品名称(单位)</th>
			                       <th>品牌</th>
			                       <th>价格体系</th>
			                       <th>价格</th>
								   <th>数量</th>
			                       <th>规格</th>
				               </tr>
				           	</thead>
				           	<tbody id="logisticsDetails">
										 		
							</tbody>
			       		</table>
                   </table>
                   <div id="info"></div>
               </div>
            </div>
            <!-- 物流消息 -->
            <div class="main" id="logistics">
                <div class="wrap_message_en clearfix">
					<div class="message clearfix">
						<p>配送公司</p>
						<span id="logisticsCompany"></span>
					</div>
					<div class="message clearfix">
						<p>快递单号</p>
						<span class="odd_numbers" id="logisticsNumber"></span>
					</div>
					<div class="message clearfix">
						<p>商　　品</p>
						<span id="commodity"></span>
					</div>
					<ul class="progresp" id="logisticsInformation">
						
					</ul>
				</div>
            </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/order_three/warehouse/heYingProduct.js"></script>
<script type="text/javascript" src="${ctx}/js/orderBase.js"></script>
<script type="text/javascript">
	/*tab栏选择*/
	tabx(".tab-item","#tab_content>.main");
	//tabx(".custom-tab-item",".custom_main",3);
	drop(".drop-on",".drop-down",".info-select");
	/*Tab栏滑动*/
    tabs(".mytabs-wrap");
    /**
	*联动表格
	*/
	function tab(head,main,active,selected){
        $(head).on('click',function(){
            $(this).addClass(active).siblings().removeClass(active);
            var index = $(this).index();
            $(main)
                    .eq(index)
                    .addClass(selected)
                    .siblings(main)
                    .removeClass(selected);
        })
    };
    tab(".client-add-item",".paper-add-main","client-add-active","paper-add-selected");
</script>
</html>