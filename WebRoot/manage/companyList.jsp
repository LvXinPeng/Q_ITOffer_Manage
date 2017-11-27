<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.xinpeng.model.Company"%>
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
<title>企业列表</title>
<link href="/Q_ITOffer_Manage/css/manageadmin.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">企业列表</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click"><span><img
						src="/Q_ITOffer_Manage/images/t01.png" /></span><a
					href="manage/companyAdd.html">添加</a></li>
				<li><span><img src="/Q_ITOffer_Manage/images/t03.png" /></span><a
					href="#">删除</a></li>
			</ul>
		</div>
		<c:if test="${ empty requestScope.pagenation.getPageData() }">
  		没有任何公司信息
  	</c:if>
		<c:if test="${!empty requestScope.pagenation.getPageData() }">
			<table class="imgtable">
				<thead>
					<tr>
						<th><input name="" type="checkbox" value="" checked="checked" /></th>
						<th>企业名称</th>
						<th>企业所在地</th>
						<th>企业规模</th>
						<th>企业性质</th>
						<th>招聘状态</th>
						<th>显示排序</th>
						<th>浏览数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pagenation.getPageData() }" var="comp">

						<tr height="50px">
							<td><input name="" type="checkbox" value="" /></td>
							<td>${comp.companyName }</td>
							<td>${comp.companyArea }</td>
							<td>${comp.companySize }</td>
							<td>${comp.companyType }</td>
							<td>${comp.companyState }</td>
							<td>${comp.companySort }</td>
							<td>1234</td>
							<td><a href="manage/companyUpdate.jsp" class="tablelink">修改</a>
								&nbsp;&nbsp;<a
								href="CompanyServlet?type=delete&companyName=${comp.companyName }"
								class="tablelink"> 删除</a></td>
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

				<li class="paginItem"><a href="CompanyListServlet?pageNo=a">上一页
				</a></li>

				<li class="paginItem"><a href="CompanyListServlet?pageNo=1">首页</a></li>

				<li class="paginItem"><a href="CompanyListServlet?pageNo=b">下一页</a></li>

			</ul>
		</div>
	</div>
</body>
</html>
