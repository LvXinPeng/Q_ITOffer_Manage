<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>“锐聘之星”软件设计大赛 - 锐聘网</title>
<link href="css/base.css" type="text/css" rel="stylesheet">
<link href="css/job.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../top.jsp"></jsp:include>

	<div class="tn-grid" align="center">
		<div class="bottomban">
			<div class="bottombanbox">
				<a
					href="CompanyServlet?type=select&id=${requestScope.company.companyId }">
					<img src="recruit/images/${requestScope.company.companyPic }">
				</a>
			</div>
		</div>
	</div>
	<div class="tn-grid">
		<div class="tn-box-content">
			<div class="it-main">
				<div class="it-ctn-heading">
					<div class="it-title-line">
						<h3>${requestScope.job.jobName }</h3>
					</div>
				</div>
				<div class="job">
					<table class="it-table" style="width:700px">
						<tbody>
							<tr>
								<td class="it-table-title">招聘人数：</td>
								<td class="tn-border-rb">${requestScope.job.jobHiringnum }人
								</td>
								<td class="it-table-title">薪资：</td>
								<td class="tn-border-rb">${requestScope.job.jobSalary }</td>
							</tr>
							<tr>
								<td class="it-table-title">工作地区：</td>
								<td class="tn-border-rb">${requestScope.job.jobArea }</td>
								<td class="it-table-title">结束日期：</td>
								<td class="tn-border-rb">${requestScope.job.jobEnddate }</td>
							</tr>
						</tbody>
					</table>
					<div class="it-post-count">
						<div class="it-com-apply">
							<a href="" title="申请职位"
								class="tn-button2 it-smallbutton-apply-hover"></a>
						</div>
						<ul class="tn-text-note it-text-part">
							<li class="jobli"><span class="tn-explain-icon"> <span
									class="tn-icon it-icon-people"></span> <span
									class="tn-icon-text">招聘人数 <span class="it-font-cor">${requestScope.job.jobHiringnum }</span>
										人
								</span></span></li>
						</ul>
					</div>
					<div class="clear"></div>
					<div class="it-post-text">
						<p>${requestScope.job.jobDesc }</p>
					</div>
					<div class="btn_bot">
						<a class="tn-button-secondary"
							href="CompanyServlet?type=select&id=${requestScope.company.companyId }">
							<span style="color:#1faebc" class="tn-button-text">查看公司信息</span>
						</a> <a class="tn-button-secondary" href=""> <span
							class="tn-button-text">申请职位</span>
						</a>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>