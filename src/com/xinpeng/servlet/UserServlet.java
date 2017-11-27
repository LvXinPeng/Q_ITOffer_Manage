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
		if ("login".equals(flag)) { // ��¼
			String userName = request.getParameter("username");
			String userPassword = request.getParameter("password");
			String checkbox = request.getParameter("checkbox");
			// System.out.println(checkbox);
			
			// ���cookie����Ϣ
			Cookie cookie = null;
			User user = UserDao.login(userName, userPassword);
			int applicantID = user.getApplicantID();
			if (user != null && user.getApplicantID() != 0) {
				
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getUsername());
				
				
				if ("on".equals(checkbox)) {
					cookie = new Cookie("userName", userName);
					cookie.setMaxAge(7 * 24 * 60 * 60);// ���һ�ܵ�ʱ��
					response.addCookie(cookie);
					cookie = new Cookie("userPassword", userPassword);
					cookie.setMaxAge(7 * 24 * 60 * 60);// ���һ�ܵ�ʱ��
					response.addCookie(cookie);
				}
				int resumeID = UserDao.isExistResume(applicantID);
				System.out.println(resumeID+":"+applicantID);
				if (resumeID != 0){
					session.setAttribute("sessionResumeID", resumeID);
					// �������Ѵ��ڣ�������ҳ
					response.sendRedirect("manage/main.html");
				}else{
					// �����������ڣ�����������д��ҳ��
					response.sendRedirect("applicant/resumeGuide.jsp");
				}
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('�û��������벻��ȷ��');history.go(-1);</script>");
				// history�ǵ�ǰҳ�����go(-1)�ǵ��˵�ǰһ����ҳ
			}
		} else if ("reg".equals(flag)) { // ע��
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String verifyCode = request.getParameter("verifyCode");
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("code");
			
			if (verifyCode.equals(code)) {
				// �ж��û��Ƿ��ѱ�ע��
				boolean isExist = UserDao.checkUserName(username);
				if (isExist) {
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().print(
							"<script>alert('���û���ע�ᣡ');history.go(-1);</script>");
					// history�ǵ�ǰҳ�����go(-1)�ǵ��˵�ǰһ����ҳ
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
						"<script>alert('��֤�����');history.go(-1);</script>");
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
