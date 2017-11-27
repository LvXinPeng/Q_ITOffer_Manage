<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 - 锐聘网</title>
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/register.css" type="text/css" rel="stylesheet" />
<meta
	content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
	name="keywords">
<meta
	content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
	name="description">
<script type="text/javascript">
	function validate() {
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		var agree = document.getElementById("agree");
		var verifyCode = document.getElementById("verifyCode");
		

		if (username.value == "") {
			alert("用户名不能为空！");
			username.focus();
			return false;
		} 
		if (password.value == "") {
			alert("密码不能为空！");
			password.focus();
			return false;
		} else if (password.length<6 || password.length>12) {
			alert("密码长度不符合要求，请输入6-12位密码!");
			password.focus();
			return false;
		}
		if (!agree.checked) {
			alert("请先同意本站服务条款！");
			return false;
		}
		if (verifyCode.value == "") {
			alert("验证码不能为空！");
			verifyCode.focus();
			return false;
		} 
		return true;
	}
	//服务条款的显示跟隐藏
	function showdiv() {
		document.getElementById("bg").style.display = "block";
		document.getElementById("show").style.display = "block";
	}
	function hidediv() {
		document.getElementById("bg").style.display = "none";
		document.getElementById("show").style.display = "none";
	}
	//验证码的更换
	function changeValidateCode() {
		document.getElementById("validateCode").src = "ValidateCodeServlet?rand="
				+ Math.random();
	}
</script>
</head>
<body>
	<!-- 网站公共头部 -->
	<iframe src="manage/top.html" width="100%" height="100" scrolling="no"
		frameborder="0"></iframe>

	<!-- 注册部分开始 -->
	<div class="content">
		<div class="page_name">注册</div>
		<div class="login_content">
			<form action="UserServlet?flag=reg" method="post"
				onsubmit="return validate();" accept-charset="UTF-8">
				<div class="login_l">
					<div class="span1">
						<label class="tn-form-label">用户名：</label> <input class="tn-textbox"
							type="text" name="username" id="username">
					</div>
					<div class="span1">
						<label class="tn-form-label">密码：</label> <input class="tn-textbox"
							type="password" name="password" id="password">
					</div>
					<div class="span1">
						<label class="tn-form-label">验证码：</label> <input
							class="tn-textbox-long" type="text" name="verifyCode" id="verifyCode"> <span>
							<img src="ValidateCodeServlet"
							id="validateCode" title="点击换一换" onclick="changeValidateCode()">
							<a href="javascript:changeValidateCode();">看不清？</a>
						</span>
					</div>
					<div class="tn-form-row-button">
						<div class="span1">
							<input name="submit" type="submit" class="tn-button-text"
								value="立即注册">
							<p class="it-register-text">
								<input name="agree" id="agree" class="tn-checkbox"
									checked="checked" type="checkbox"> <label>同意本站服务条款</label>
								<a href="javascript:showdiv();">查看</a>
							</p>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</form>
			<div class="register_r">
				<p align="center">
					<br><br> <b>已有帐号？</b><a href="login.html">登录</a>
				</p>
				<div>
					<img height="230" src="images/reg_pic.jpg">
				</div>
			</div>
		</div>
	</div>
	<!-- 注册部分结束 -->

	<!-- 服务条款部分开始 -->
	<div id="bg"></div>
	<div id="show">
		<center>
			服务条款<br />欢迎加入锐聘网。<br />所有用户，只要进入锐聘网注册，即被视为已经阅读、理解并同意本协议的以下各项条款。
		</center>
		免责条款：<br />第一条
		用户在本站登记的简历信息，必须完整、正确。出于遵守国家相关法规的前提，我们有权在不经用户准许的情况下删除其在本站所登记的信息。<br />第二条
		用户必须同意使用本站仅用于合法的目的。<br />第三条
		本站如因系统维护或升级而需暂停服务时，将事先公告。若因线路及非本公司控制范围外的硬件故障或其它不可抗力而导致暂停服务，于暂停服务期间造成的一切不便与损失，本站不负任何责任。<br />第四条
		本站使用者因为违反本声明的规定而触犯中华人民共和国法律的，一切后果自己负责，本站不承担任何责任。<br />第五条
		凡以任何方式登录本站或直接、间接使用本站的资料者，视为自愿接受本站声明的约束。本声明未涉及的问题参见国家有关法律法规，当本声明与国家法律法规冲突时，以国家法律法规为准。
		<center>
			<input type="button" onclick="javascript:hidediv()"
				class="tn-button-text" value="我明白了">
		</center>
	</div>
	<!-- 服务条款部分结束 -->

	
</body>
</html>