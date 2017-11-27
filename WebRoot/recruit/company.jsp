<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.xinpeng.model.Company,com.xinpeng.model.Job,java.util.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>“锐聘之星”软件设计大赛 - 锐聘网</title>
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/company.css" type="text/css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="../top.jsp"></jsp:include>

<div class="tn-grid">
   <div class="bottomban">
    <div class="bottombanbox"> <img src="recruit/images/${company.getCompanyPic() }" /> </div>
  </div>
</div>

<div class="clear"></div>
<div class="tn-grid">
  <div class="tn-widget-content">
    <div class="tn-box-content">
      <div class="tn-helper-clearfix">
        <div class="it-main2">
          <div class="it-ctn-heading">
            <div class="it-title-line"> <span> <em> ${company.getCompanyViewnum() } </em> 浏览 </span>
              <h3> 企业简介 </h3>
            </div>
          </div>
          <div class="it-com-textnote"> 
          <span class="kuai"> 所在地：${company.getCompanyArea() } </span> 
          <span class="kuai"> 规模：${company.getCompanySize() } </span> 
          <span class="kuai"> 性质：${company.getCompanyType() } </span> </div>
          <div class="it-company-text">
            <p class="p1" style="padding-left: 30px;">
            <span style="line-height: 1.5; font-size: 14px;"> 
           	${company.getCompanyBrief() }
            </span> </p>
            <br />
          </div>
        </div>
       
      </div>
    </div>
  </div>
  
  <div class="it-content-seqbox">
    <div id="morejob"   >
      <div class="it-ctn-heading">
        <div class="it-title-line">
          <h3> 职位 </h3>
        </div>
      </div>
      <!--职位列表-->
      <c:forEach items="${joblist }" var="jl">
      <div class="it-post-box tn-border-dashed">
        <div class="it-post-name">
          <div class="tn-helper-right it-post-btn"> 
          <a class="it-font-underline" href="JobServlet?type=select&jobid=${jl.jobId }">
          <span class="tn-icon-view"></span><span class=tn-action-text>查看详细</span> </a>
          <a class="tn-button-small" href="                                    ">
          <span class="tn-button-text">申请</span> </a></div>
          <h3><a href="JobServlet?type=select&jobid=${jl.jobId }">${jl.jobName }</a></h3>
        </div>
        <ul class="it-post">
          <li style="width:300px;">薪资： 
          <span class="it-font-size">${jl.jobSalary }</span></li>
          <li style="width:250px;"><span class=tn-text-note>工作地区：</span>
          <label for="">${jl.jobArea }</label>
          </li>
          <li><span class="tn-text-note">招聘人数：</span> ${jl.jobHiringnum }</li>
          <li><span class="tn-text-note">结束日期：</span> ${jl.jobEnddate } </li>
        </ul>
      </div>
      </c:forEach>
      <!--职位列表--> 

    </div>
  </div>
</div>
<p class="it-back-to-top" style=" position:fixed" id="backTop" title="返回顶部"> <a href="#top"> <span> </span> 返回顶部 </a> </p>


	<!-- InstanceEndEditable -->
	<jsp:include page="../foot.jsp"></jsp:include>

	</div>
</body>
</html>