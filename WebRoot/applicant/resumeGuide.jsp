<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历填写向导 - 锐聘网</title>
<link href="../css/base.css" type="text/css" rel="stylesheet" />
<link href="../css/resumeGuide.css" type="text/css" rel="stylesheet" />
<meta
	content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
	name="keywords">
<meta
	content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
	name="description">
</head>
<body>

	<div class="success_content">
		<div class="success_left">
			<div class=it-pageimg></div>
			<h3 align="center">操作成功！</h3>
		</div>
		<div class="success_right">
			<p class="green16">需要先填写简历，才能申请职位哟！</p>
			<p>快快选择以下任意一种方式完善简历，去申请心仪职位吧！</p>
			<p>
				<a href="resume.html"><span class="tn-button">填写简历</span></a><a
					href="#"><span class="tn-button">站点首页</span></a>
			</p>
		</div>
		<div class="clear"></div>
	</div>

</body>
</html>