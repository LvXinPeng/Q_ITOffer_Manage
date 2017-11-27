package com.xinpeng.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xinpeng.dao.JobDAO;
import com.xinpeng.model.JobPage;

public class JobListServlet extends HttpServlet {

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

		String page = request.getParameter("pageNo");
		JobDAO jd = new JobDAO();
		int count = jd.getRecordCount(); // 信息总数
		int pageSize = 5; // 每页显示信息数
		int pageCount = (int) Math.ceil(count * 1.0 / pageSize); // 总共的页数
		int pageNo = 1; // 页号
		if (page == null || "".equals(page) || page.equals("1")) {
			pageNo = 1;
		} else if (page.equals("a")) {
			pageNo = (Integer) request.getSession().getAttribute("pageNow");
			pageNo--;
		} else if (page.equals("b")) {
			pageNo = (Integer) request.getSession().getAttribute("pageNow");
			pageNo++;
		} else {
			pageNo = count;
		}
		if (pageNo > count) {
			pageNo = count;
		}
		if (pageNo < 1) {
			pageNo = 1;
		}
		JobPage jobNation = new JobPage(pageSize, pageNo);
		jobNation.setPageData(jd.getJobPageList(pageNo, pageSize));
		// request.setAttribute("jobNation", jobNation);
		HttpSession session = request.getSession();
		session.setAttribute("jobNation", jobNation);
		request.getSession().setAttribute("pageNow", pageNo);
		request.getSession().setAttribute("pageCount", pageCount);
		request.setAttribute("count", count);
		request.setAttribute("pageNo", pageNo);
		request.getRequestDispatcher("manage/jobList.jsp").forward(request,
				response);
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
