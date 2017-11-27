<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/base.css" rel="stylesheet" type="text/css">
</head>

<body><div class="head">
	<div class="head_area">
		<div class="head_nav">
			<ul>
				<li><img src="images/nav_inc1.png" /><a href="index.html" target="_parent">首页</a></li>
				<li><img src="images/nav_inc2.png" /><a href="#">成功案例</a></li>
				<li><img src="images/nav_inc3.png" /><a href="#">关于锐聘</a></li>
			</ul>
		</div>
		<div class="head_logo"><img src="images/head_logo.png" /></div>
		<div class="head_user">
		
		<a href="login.html" target="_parent"><span class="type1">登录</span></a>
		<a href="register.html" target="_parent"><span class="type2">注册</span></a>
		<a href="applicant/resume.html" target="_parent">青软实训</a>
		<a href="index.html" target="_parent">退出</a>
		</div>
		<div class="clear"></div>
	</div>
</div>

<div class="top_main">
	<div class="top_logo"><img src="images/main_logo.png" /></div>
	<div class="top_instr">提供岗前培训的IT职位</div>
	<div class="top_tel"><img src="images/it-phone.png" /></div>
	<div class="clear"></div>
</div>

<div class="clear"></div>

</body>
</html>
