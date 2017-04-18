<%--
  Created by IntelliJ IDEA.
  User: luojian
  Date: 2016/11/30
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>美丽秘籍后台管理 - 主页</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <spring:url value="/resources/" var="res_dir"/>

    <link rel="shortcut icon" href="favicon.ico">
    <link href="${res_dir}css/bootstrap.min.css" rel="stylesheet">
    <link href="${res_dir}css/font-awesome.min.css" rel="stylesheet">
    <link href="${res_dir}css/animate.min.css" rel="stylesheet">
    <link href="${res_dir}css/style.min.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span>
                            <c:if test="${not empty manager.avatar}">
                                <img alt="image" class="img-circle" src="manager.avatar"/>
                            </c:if>
                            <c:if test="${empty manager.avatar}">
                                <img alt="image" class="img-circle" src="${res_dir}img/profile_small.jpg"/>
                            </c:if>
                        </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${manager.username}</strong></span>
                                <span class="text-muted text-xs block">${manager.rollName}<b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                            </li>
                            <li><a class="J_menuItem" href="profile.html">个人资料</a>
                            </li>
                            <!--<li><a class="J_menuItem" href="contacts.html">联系我们</a>
                            </li> -->
                            <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="logout">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">H+
                    </div>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-home"></i>
                        <span class="nav-label">主页</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-columns"></i>
                        <span class="nav-label">美丽秘籍</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="#">发布文章</a></li>
                        <li><a class="J_menuItem" href="#">文章列表</a></li>
                        <li><a class="J_menuItem" href="#">审核发布</a></li>
                        <li><a class="J_menuItem" href="#">抓取配置</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-table"></i>
                        <span class="nav-label">美丽购&nbsp;</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="#">商品列表</a></li>
                        <li><a class="J_menuItem" href="#">发布商品</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-edit"></i>
                        <span class="nav-label">评论管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="#">文章评论</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-cutlery"></i>
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="#">表单构建器</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post" action="search">
                        <div class="form-group">
                            <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="text"
                                   id="top-search">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-envelope"></i>
                            <c:if test="${mail_count > 0}">
                                <span class="label label-warning">${mail_count}</span>
                            </c:if>
                        </a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                            <i class="fa fa-bell"></i>
                            <c:if test="${notice_count > 0}">
                                <span class="label label-primary">${notice_count}</span>
                            </c:if>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
                </ul>
            </div>
            <a href="logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="index_v148b2.html?v=4.0"
                    frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 2016-2017 <a href="#" target="_blank">Beauty Message</a>
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<script src="${res_dir}js/jquery.min.js"></script>
<script src="${res_dir}js/bootstrap.min.js"></script>
<script src="${res_dir}js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${res_dir}js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${res_dir}js/plugins/layer/layer.min.js"></script>
<script src="${res_dir}js/hplus.js"></script>
<script type="text/javascript" src="${res_dir}js/contabs.js"></script>
<script src="${res_dir}js/plugins/pace/pace.min.js"></script>
</body>
</html>
