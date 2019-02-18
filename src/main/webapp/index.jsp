<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>订单管理系统</title>
  <!-- Bootstrap Core CSS -->
    <link href="${ctx}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!--日历插件-->
    <link rel="stylesheet" href="${ctx}/css/calendar.css"/>
    <!-- MetisMenu CSS -->
    <link href="${ctx}/css/metisMenu.min.css" rel="stylesheet">
    <!--表单验证 CSS-->
    <link href="${ctx}/css/form-test/jquery.idealforms.min.css" rel="stylesheet" media="screen"/>
    <!--表单下拉选择-->

    <!-- Custom CSS -->
    <link id="css" href="${ctx}/css/sb-admin-2.css" rel="stylesheet" type="text/css" title="gray">
    <!--表单日期插件-->

    <!-- Custom Fonts -->
    <link href="${ctx}/css/awesome/font-awesome.min.css" rel="stylesheet" type="text/css">
	<!-- 表单验证样式 -->
    <link href="${ctx}/css/bootstrapValidator.css" rel="stylesheet" type="text/css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
		.defaultManagerAll{overflow-y:auto;}
	</style>
</head>

<body id="defaultManagerAll">
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation">
            <div class="logo">
                <a href="#"><img src="${ctx}/images/logo.png" alt=""/></a>
            </div>
            <div class="navbar-header navbar-h">
                <a class="navbar-brand manage active" href="index.jsp">订单管理系统</a>
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#"><i class="icon-key"></i>换肤</a>
                            <ul class="inline" id="inline">
                                <li onclick="changeCss('black')" class="color-black" data-style="gray"></li>
                                <li onclick="changeCss('gray')" class="color-gray current color-default" data-style="default"></li>
                                <li onclick="changeCss('yellow')" class="color-yellow" data-style="purple"></li>
                                <li onclick="changeCss('green')" class="color-green" data-style="green"></li>
                                <li onclick="changeCss('blue')" class="color-blue" data-style="blue"></li>
                            </ul>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="sidebar navbar-sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="javascript:void(0);" onclick=""><i class="fa fa-dashboard fa-fw"></i>菜单</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>订单管理系统<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/orderList.jsp');">订单管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/quality/qualityList.jsp');">售后管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/quality/qualityApprove.jsp');">售后审核</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/vipshop/vipshopRefund.jsp');">唯品会退费</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/Ledgeraccount/edit.jsp');">分账管理</a>
                                </li>
                                 <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/tongjitable/tongjiTable.jsp');">营养统计</a>
                                </li>
                                 <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/tongjitable/exercise.jsp');">练习任务</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>三方订单管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/order_three/out/partyOrder.jsp');">商家订单（对内）</a>
                                </li> 
                                 
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/order_three/out/orderList.jsp');">商家订单（对外）</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
            <i class="glyphicon glyphicon-align-justify" id="open-button"></i>
        </nav>

        <div id="page-wrapper" class="page-wrapper">
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${ctx}/js/jquery/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${ctx}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${ctx}/js/bootstrap/bootstrap.js"></script>
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/js/metisMenu.min.js"></script>
    <!--Tree JavaScript-->
    <script src="${ctx}/js/jquery.cookie.js"></script>
    <script src="${ctx}/js/jquery.treeview.js"></script>
    <!--fullcalendar JavaScript-->
    <!-- Custom Theme JavaScript -->
    <script src="${ctx}/js/sb-admin-2.js"></script>
    <script src="${ctx}/js/main.js"></script>
    <script  src="${ctx}/js/creat_page.js"></script>
    <!-- 时间插件js -->
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
    <!--邮箱提示-->
	<script src="${ctx}/js/emailAutoComplete.js"></script>
    <script type="text/javascript" src="${ctx}/js/orderInputSearch.js"></script>
     <!-- 验证插件 -->
    <script type="text/javascript" src="${ctx}/js/jquery-validate.js"></script>
    <!-- 验证 -->
	<script type="text/javascript" src="${ctx}/js/bootstrapValidator.js"></script>
	<!-- 时间控件 -->
    <script type="text/javascript" src="${ctx}/js/bootbox.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/time_format.js"></script>
	<script type="text/javascript" src="${ctx}/js/framework.js"></script>
	<!-- 评价控件 -->
	<!--<script type="text/javascript" src="${ctx}/js/star-rating.js"></script>
	<script type="text/javascript" src="${ctx}/js/swiper.min.js"></script>-->
	<!-- 订单查询 -->
    <script type="text/javascript" src="${ctx}/js/orderBase.js"></script>  
</body>

</html>
