package com.xinpeng.model;

import java.util.ArrayList;
import java.util.List;

import com.xinpeng.dao.CompanyDAO;

public class CompanyPage {
	
	private int pageSize = 5;
	// ��ǰҳ��
	private int pageNo = 1;
	// ��ҳ��
	private int totalPages;
	// ÿҳ���ݼ�¼����
	private List<Company> pageData = new ArrayList<Company>();
	// �Ƿ�����һҳ
	private boolean hasNextPage;
	// �Ƿ�����һҳ
	private boolean hasPreviousPage;
	
	public CompanyPage() {
	}
	
	public CompanyPage(int pageSize, int pageNo) {
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

	public int getTotalPages() {
		// ��ȡ�ܼ�¼��
		CompanyDAO dao = new CompanyDAO();
		int recordCount = dao.getRecordCount();
		// ��ȡ��������ҳ��
		return (recordCount + pageSize - 1) / pageSize;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<Company> getPageData() {
		// ��ѯ��ҳ��¼
		com.xinpeng.dao.CompanyDAO dao = new CompanyDAO();
		List<Company> list = dao.getCompanyPageList(pageNo, pageSize);
		return list;
	}

	public void setPageData(List<Company> pageData) {
		this.pageData = pageData;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	
	
}
