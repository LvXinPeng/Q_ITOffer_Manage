package com.xinpeng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinpeng.dao.CompanyDAO;
import com.xinpeng.dao.JobDAO;
import com.xinpeng.model.Company;
import com.xinpeng.model.CompanyPage;
import com.xinpeng.model.Job;

public class CompanyServlet extends HttpServlet {

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
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String type = request.getParameter("type");
		String companyName = request.getParameter("companyName");
		String companyArea = request.getParameter("companyArea");
		String companySize = request.getParameter("companySize");
		String companyType = request.getParameter("companyType");
		String companyBrief = request.getParameter("companyBrief");
		String state = request.getParameter("companyState");
		String sort = request.getParameter("companySort");
		String companyPic = request.getParameter("companyPic");

		if ("add".equals(type)) {
			int companyState = Integer.valueOf(state);
			int companySort = Integer.valueOf(sort);
			Company company = new Company(companyName, companyArea,
					companySize, companyType, companyBrief, companyState,
					companySort, companyPic);

			int isAdd = CompanyDAO.addCompany(company, companyName);

			if (isAdd != 0) {
				// response.sendRedirect("manage/companyList.jsp");
				response.sendRedirect("CompanyListServlet");
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('添加公司失败！');history.go(-1);</script>");
			}
		} else if ("update".equals(type)) {
			int companyState = Integer.valueOf(state);
			int companySort = Integer.valueOf(sort);
			Company company = new Company(companyName, companyArea,
					companySize, companyType, companyBrief, companyState,
					companySort, companyPic);
			boolean isUpdate = CompanyDAO.upadteCompany(company, companyName);
			if (isUpdate) {
				// response.sendRedirect("manage/companyList.jsp");
				response.sendRedirect("CompanyListServlet");
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('修改公司失败！');history.go(-1);</script>");
			}
		} else if ("delete".equals(type)) {
			boolean isDelete = CompanyDAO.deleteCompany(companyName);
			if (isDelete) {
				response.sendRedirect("CompanyListServlet");
			} else {
				response.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().print(
						"<script>alert('删除公司失败！');history.go(-1);</script>");
			}
		} else if ("select".equals(type)) {
			String companyID = request.getParameter("id");
			CompanyDAO dao = new CompanyDAO();
			Company company = dao.getCompanyByID(companyID);
			request.setAttribute("company", company);
			JobDAO jobdao = new JobDAO();
			List<Job> jobList = jobdao.getJobListByCompanyID(companyID);
			request.setAttribute("joblist", jobList);
			request.getRequestDispatcher("recruit/company.jsp").forward(request,
					response);
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
