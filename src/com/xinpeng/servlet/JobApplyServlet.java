package com.xinpeng.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinpeng.dao.CompanyDAO;
import com.xinpeng.dao.JobApplyDAO;
import com.xinpeng.dao.JobDAO;
import com.xinpeng.model.Company;
import com.xinpeng.model.Job;
import com.xinpeng.model.JobApply;

@WebServlet("/JobApplyServlet")
public class JobApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JobApplyServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置请求数据编码
		request.setCharacterEncoding("UTF-8");
		// 设置响应文档类型和编码
		response.setContentType("text/plain;charset=UTF-8");
		// 获取请求操作类型
		String type = request.getParameter("type");
		if("companyNameList".equals(type)){
			// 获取企业标识和名称
			CompanyDAO dao = new CompanyDAO();
			List<Company> companylist = dao.selectAllCompanyName();
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),companylist);
		}else if("jobNameList".equals(type)){
			// 获取职位标识和名称
			int companyId = Integer.parseInt(request.getParameter("companyId")==null?"0":request.getParameter("companyId"));
			JobDAO dao = new JobDAO();
			List<Job> joblist = dao.selectJobNameByCompany(companyId);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),joblist);
		}else if("query".equals(type)){
			// 根据查询条件进行职位申请信息查询
			String companyId = request.getParameter("companyId");
			String jobId = request.getParameter("jobId");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			JobApplyDAO dao = new JobApplyDAO();
			List<JobApply> applylist = dao.query(companyId,jobId,startDate,endDate);
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getWriter(),applylist);
		}
	}

}
