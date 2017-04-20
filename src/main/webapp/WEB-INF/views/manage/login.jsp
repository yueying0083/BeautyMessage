<%--
  Created by IntelliJ IDEA.
  User: luojian
  Date: 2016/11/30
  Time: 14:39
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>美丽秘籍后台 - 登录</title>
    <spring:url value="/resources/css" var="css_dir"/>
    <link href="${css_dir}/bootstrap.min.css" rel="stylesheet">
    <link href="${css_dir}/font-awesome.css" rel="stylesheet">
    <link href="${css_dir}/animate.css" rel="stylesheet">
    <link href="${css_dir}/style.css" rel="stylesheet">
    <link href="${css_dir}/login.min.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->
    <script>
        if (window.top !== window.self) {
            window.top.location = window.location
        }
        ;
    </script>

</head>

<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="logopanel m-b">
                    <h1>美丽秘籍</h1>
                </div>
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>美丽秘籍后台管理系统</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>每天20篇最前沿，最fashion的美丽资讯，让你的审美永不脱节；</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>每天10款时尚精品推荐，让你足不出户，静享美丽；</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>每天星座运势指导心情更加美丽；</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i>时尚丽人的首选，省去海量的，让你心烦的轰炸，尽在要美丽呢。</li>
                </ul>
            </div>
        </div>
        <div class="col-sm-5">
            <form method="post" action="login">
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">登录美丽秘籍后台</p>
                <c:if test="${not empty error}">
                    <p style="color: red"> ${error} </p>
                </c:if>
                <input type="text" class="form-control uname" placeholder="用户名" name="username"/>
                <input type="password" class="form-control pword m-b" placeholder="密码" name="password"/>
                <button class="btn btn-success btn-block">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2016 All Rights Reserved. BeautyMessage
        </div>
    </div>
</div>
</body>
</html>