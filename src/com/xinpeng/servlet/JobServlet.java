package com.xinpeng.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinpeng.dao.JobDAO;
import com.xinpeng.model.Company;
import com.xinpeng.model.Job;

public class JobServlet extends HttpServlet {

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
		request.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");
		String jobName = request.getParameter("jobName");
		String Hiringnum = request.getParameter("jobHiringnum");
		String jobSalary = request.getParameter("jobSalary");
		String jobArea = request.getParameter("jobArea");
		String jobDesc = request.getParameter("jobDesc");
		String Enddate = request.getParameter("jobEnddate");
		String state = request.getParameter("jobState");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Company company = new Company();
		if ("add".equals(type)) {
			Date jobEnddate = null;
			try {
				jobEnddate = sdf.parse(Enddate);
				
			} catch (ParseException e) {
				jobEnddate = null;
			}
			int jobState = Integer.valueOf(state);
			int jobHiringnum = Integer.valueOf(Hiringnum);
			Job job = new Job(company, jobName, jobHiringnum, jobSalary, jobArea, jobDesc, jobEnddate, jobState);

			int isAdd = JobDAO.addJob(job, jobName);

			if (isAdd != 0) {
				response.sendRedirect("JobListServlet");
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('Ìí¼Ó¹«Ë¾Ê§°Ü£¡');history.go(-1);</script>");
			}
		}else if("select".equals(type)){
			String jobid = request.getParameter("jobid");
			JobDAO dao = new JobDAO();
			Job job = dao.getJobByID(jobid);
			request.setAttribute("job", job);
			request.setAttribute("company", job.getCompany());
			request.getRequestDispatcher("recruit/job.jsp").forward(request, response);
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
