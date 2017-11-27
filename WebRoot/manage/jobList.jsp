<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.xinpeng.model.Job"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>职位列表</title>
<link href="/Q_ITOffer_Manage/css/manageadmin.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="index.html">首页</a></li>
			<li>职位列表</li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><span><img
						src="/Q_ITOffer_Manage/images/t01.png" /></span> <a
					href="manage/jobAdd.html">添加</a></li>
			</ul>
			<iframe src="manage/jobSearch.html" scrolling="no" frameborder="0"
				width="630" height="42"></iframe>
		</div>
		<c:if test="${ empty sessionScope.jobNation.getPageData() }">
  		没有任何职位信息
  	</c:if>
		<c:if test="${!empty sessionScope.jobNation.getPageData() }">
			<table class="imgtable">
				<thead>
					<tr>
						<th>职位名称</th>
						<th>所属企业</th>
						<th>招聘数</th>
						<th>申请数</th>
						<th>结束日期</th>
						<th>招聘状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${jobNation.getPageData() }" var="job">
						<tr height="50px">
							<td>${job.jobName }</td>
							<td>青软实训</td>
							<td>${job.jobHiringnum }</td>
							<td>40</td>
							<td>${job.jobEnddate }</td>
							<td>${job.jobState }</td>
							<td><a href="#" class="tablelink">修改</a> &nbsp;&nbsp; <a
								href="#" class="tablelink"> 删除</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</c:if>
		<div class="pagin">
			<div class="message">
				共<i class="blue">${requestScope.count }</i>条记录，当前显示第&nbsp;<i
					class="blue">${pageNo }&nbsp;</i>页
			</div>
			<ul class="paginList">

				<li class="paginItem"><a href="JobListServlet?pageNo=a">上一页
				</a></li>

				<li class="paginItem"><a href="JobListServlet?pageNo=1">首页</a></li>

				<li class="paginItem"><a href="JobListServlet?pageNo=b">下一页</a></li>

			</ul>
		</div>
	</div>
</body>
</html>
