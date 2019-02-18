<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.lang.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>订单</title>

    <!-- Bootstrap Core CSS -->
    <link href="${ctx}/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="${ctx}/css/metisMenu.min.css" rel="stylesheet">
    <!--Tree CSS-->
    <link rel="stylesheet" href="${ctx}/css/jquery.treeview.css"/>
    <!--编辑器插件 CSS-->
    <link rel="stylesheet" href="${ctx}/css/jquery.cleditor.css"/>
    <!-- Custom CSS -->
    <link id="css" href="${ctx}/css/sb-admin-2.css" rel="stylesheet" type="text/css" title="gray">
    <!-- Custom Fonts -->
    <link href="${ctx}/css/awesome/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<input type="hidden" id="ctx" name="ctx" value="${ctx}" />
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="logo">
                <a href="#"><img src="${ctx}/images/logo.png" alt=""/></a>
            </div>
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="navbar-h">
                    <div class="dropdown navbar-brand active">
                        <a id="dLabel" data-target="#" href="#" data-toggle="dropdown" role="button">
                            系统模块1
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu child-menu" aria-labelledby="dLabel">
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                        </ul>
                    </div>
                    <div class="dropdown navbar-brand">
                        <a id="dLabe2" data-target="#" href="#" data-toggle="dropdown" role="button">
                            系统模块2
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu child-menu" aria-labelledby="dLabe2">
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                        </ul>
                    </div>
                    <div class="dropdown navbar-brand">
                        <a id="dLabe3" data-target="#" href="#" data-toggle="dropdown" role="button">
                            系统模块1
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu child-menu" aria-labelledby="dLabe3">
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                        </ul>
                    </div>
                    <div class="dropdown navbar-brand">
                        <a id="dLabe4" data-target="#" href="#" data-toggle="dropdown" role="button">
                            系统模块2
                            <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu child-menu" aria-labelledby="dLabe4">
                            <li><a href="#">二级菜单1</a></li>
                            <li><a href="#">二级菜单1</a></li>
                        </ul>
                    </div>
                </div>
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
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend${ctx}.</div>
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
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend${ctx}.</div>
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
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend${ctx}.</div>
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
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i>订单管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/orderList.jsp');">订单管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="$('#page-wrapper').load('${ctx}/jsp/quality/qualityList.jsp');">售后管理</a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);" onclick="openModule('module.jsp')">模态框</a>
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
    <!-- Metis Menu Plugin JavaScript -->
    <script src="${ctx}/js/metisMenu.min.js"></script>
    <!--Tree JavaScript-->
    <script src="${ctx}/js/jquery.cookie.js"></script>
    <script src="${ctx}/js/jquery.treeview.js"></script>
    <!--编辑器插件 JS-->
    <script src="${ctx}/js/jquery.cleditor.min.js"></script>
    <!--表单验证框架-->
    <script src="${ctx}/js/jquery-validate.js"></script>
    <!-- Custom Theme JavaScript --> 
    <script src="${ctx}/js/sb-admin-2.js"></script>
    <script src="${ctx}/js/main.js"></script>
    <script src="${ctx}/js/orderBase.js"></script>
    <script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</body>

</html>
