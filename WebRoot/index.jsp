<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.xinpeng.dao.CompanyDAO,com.xinpeng.model.Company,com.xinpeng.model.Job" %>
<%@ page import="java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RTO服务_锐聘官网-大学生求职,大学生就业,IT行业招聘，IT企业快速入职 - 锐聘网</title>
<meta name="renderer" content="ie-stand">
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<meta content="ItOffer" name="generator">
<meta content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职" name="keywords">
<meta content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。" name="description">
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/index.css" type="text/css" rel="stylesheet" />
<script src="js/a.js" type="text/javascript"></script>


</head>

<body>
	<jsp:include page="top.jsp"></jsp:include>

<div id="tn-content" >
     <!-- 招聘企业展示 -->
     <%
     	CompanyDAO dao = new CompanyDAO();
		List<Company> list = dao.getCompanyList();
		if(list!=null)
			for(Company c : list){
     %>
      <div class="tn-grid">
        <div class="tn-box tn-widget tn-widget-content tn-corner-all it-home-box">
          <div class="tn-box-content tn-widget-content tn-corner-all">
          	<!-- 企业图片展示 -->
            <div class="it-company-keyimg tn-border-bottom tn-border-gray"> 
            <a href="CompanyServlet?type=select&id=<%=c.getCompanyId() %>"> 
            <img src="recruit/images/<%=c.getCompanyPic() %>" width="990"> </a> </div>
            <!-- 招聘职位展示 -->
            <%
	            Set<Job> jobset = c.getJobs();
            	if(jobset!=null)
					for(Job job : jobset){
            %>
            <div class="it-home-present">
             <div class="it-present-btn"> <a class=" tn-button tn-button-home-apply" href="#"> 
             <span class="tn-button-text">我要申请</span> </a> </div>
              <div class="it-present-text" style="padding-left:185px;">
                <div class="it-line01 it-text-bom">
                  <p class="it-text-tit">职位</p>
                  <p class="it-line01 it-text-explain"> <span class="tn-icon it-home-arrow"></span> 
                  <span class="tn-helper-right tn-action"> 
                  <a href="CompanyServlet?type=select&id=<%=c.getCompanyId() %>" class="tn-button tn-corner-all tn-button-text-only tn-button-semidlong"> 
                  <span class="tn-button-text">更多职位</span> </a> </span> 
                  <b><%=job.getJobName() %></b> </p>
                </div>
                <div class="it-line01 it-text-top">
                  <p class="it-text-tit">薪资</p>
                  <p class="it-line01 it-text-explain"> <span class="tn-icon it-home-arrow"></span> 
                  <b><%=job.getJobSalary() %></b> </p>
                </div>
              </div>
              <div class="it-present-text">
                <div class="it-line01 it-text-bom">
                  <p class="it-text-tit">到期时间</p>
                  <p class="it-line01 it-text-explain"> <span class="tn-icon it-home-arrow"></span> 
                   <b><%=job.getJobEnddate() %></b> </p>
                </div>
                <div class="it-line01 it-text-top">
                  <p class="it-text-tit">工作地区</p>
                  <p class="it-line01 it-text-explain"> <span class="tn-icon it-home-arrow"></span> 
                  <b><%=job.getJobArea() %></b> </p>
                </div>
              </div>
            </div>
            </div>
            <%} %>
          </div>
        </div>
      <%} %>
    <!---------------- 企业列表 结束 -------------------->
  </div>
<p align="center">欢迎光临，您是本站的第【 ${count }】位访客！</p>
    <!---------------- 企业列表 结束 -------------------->

	<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
