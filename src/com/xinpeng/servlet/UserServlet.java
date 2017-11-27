package com.xinpeng.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinpeng.dao.UserDao;
import com.xinpeng.model.User;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flag = request.getParameter("flag");
		if ("login".equals(flag)) { // 登录
			String userName = request.getParameter("username");
			String userPassword = request.getParameter("password");
			String checkbox = request.getParameter("checkbox");
			// System.out.println(checkbox);
			
			// 添加cookie的信息
			Cookie cookie = null;
			User user = UserDao.login(userName, userPassword);
			int applicantID = user.getApplicantID();
			if (user != null && user.getApplicantID() != 0) {
				
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				
				
				if ("on".equals(checkbox)) {
					cookie = new Cookie("userName", userName);
					cookie.setMaxAge(7 * 24 * 60 * 60);// 存放一周的时间
					response.addCookie(cookie);
					cookie = new Cookie("userPassword", userPassword);
					cookie.setMaxAge(7 * 24 * 60 * 60);// 存放一周的时间
					response.addCookie(cookie);
				}
				int resumeID = UserDao.isExistResume(applicantID);
				System.out.println(resumeID+":"+applicantID);
				if (resumeID != 0){
					session.setAttribute("sessionResumeID", resumeID);
					// 若简历已存在，跳到首页
					response.sendRedirect("manage/main.html");
				}else{
					// 若简历不存在，跳到简历填写向导页面
					response.sendRedirect("applicant/resumeGuide.jsp");
				}
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('用户名或密码不正确！');history.go(-1);</script>");
				// history是当前页面对象，go(-1)是倒退到前一个网页
			}
		} else if ("reg".equals(flag)) { // 注册
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String verifyCode = request.getParameter("verifyCode");
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");
			
			if (verifyCode.equals(code)) {
				// 判断用户是否已被注册
				boolean isExist = UserDao.checkUserName(username);
				if (isExist) {
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(
							"<script>alert('该用户已注册！');history.go(-1);</script>");
					// history是当前页面对象，go(-1)是倒退到前一个网页
				} else {
					boolean isReg = UserDao.reg(user);
					if (isReg) {
						response.sendRedirect("login.html");
					} else {
						response.sendRedirect("manage/regester.jsp");
					}
				}
			}else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('验证码错误！');history.go(-1);</script>");
			}
			
			
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
