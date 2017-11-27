package com.xinpeng.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinpeng.dao.ResumeBasicinfoDao;
import com.xinpeng.model.ResumeBasicinfo;

public class ResumeBasicinfoServlet extends HttpServlet {

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

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		if ("add".equals(type)) {
			String realName = request.getParameter("realName");
			String gender = request.getParameter("gender");
			String birthday = request.getParameter("birthday");
			String currentLoc = request.getParameter("currentLoc");
			String residentLoc = request.getParameter("residentLoc");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String jobIntension = request.getParameter("jobIntension");
			String jobExperience = request.getParameter("jobExperience");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdayDate = null;
			try {
				birthdayDate = sdf.parse(birthday);
			} catch (ParseException e) {
				birthdayDate = null;
			}
			
			ResumeBasicinfo resumeBasicinfo = new ResumeBasicinfo(realName, gender, birthdayDate,
					currentLoc, residentLoc, telephone, email, jobIntension,
					jobExperience);
			
			HttpSession session = request.getSession();
			
			
			int isResume = ResumeBasicinfoDao.add(resumeBasicinfo,ResumeBasicinfoDao.getID((String)session.getAttribute("username")));
			if (isResume != 0) {
				response.sendRedirect("applicant/resume.html");
			}else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('ÃÌº”ºÚ¿˙ ß∞‹£°');history.go(-1);</script>");
			}
			
		}
		
		

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
