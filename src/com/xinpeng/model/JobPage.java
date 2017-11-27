package com.xinpeng.model;

import java.util.ArrayList;
import java.util.List;



import com.xinpeng.dao.JobDAO;

public class JobPage {
	private int pageSize = 5;
	// 当前页码
	private int pageNo = 1;
	// 每页数据记录集合
	@SuppressWarnings("unused")
	private List<Job> pageData = new ArrayList<Job>();

	
	public JobPage() {
	}
	
	public JobPage(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}



	public List<Job> getPageData() {
		// 查询当页记录
		com.xinpeng.dao.JobDAO dao = new JobDAO();
		List<Job> list = dao.getJobPageList(pageNo, pageSize);
		return list;
	}

	public void setPageData(List<Job> pageData) {
		this.pageData = pageData;
	}

}