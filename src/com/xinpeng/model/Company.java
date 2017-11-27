package com.xinpeng.model;

import java.util.HashSet;
import java.util.Set;


public class Company {
	
	// ��ҵ��ʶ
	private int companyId;
	// ��ҵ����
	private String companyName;
	// ��ҵ���ڵ���
	private String companyArea;
	// ��ҵ��ģ
	private String companySize;
	// ��ҵ����
	private String companyType;
	// ��ҵ���
	private String companyBrief;
	// ��Ƹ״̬:1��Ƹ�� 2����ͣ 3�ѽ���
	private int companyState;
	// �������
	private int companySort;
	// �����
	private int companyViewnum;
	// ����ͼƬ
	private String companyPic;
	// ְλ
	private Set<Job> jobs = new HashSet<Job>();

	public Company() {
		super();
	}
	public Company(int companyId, String companyName, String companyArea,
			String companySize, String companyType, String companyBrief,
			int companyState, int companySort, int companyViewnum,
			String companyPic) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companySort = companySort;
		this.companyViewnum = companyViewnum;
		this.companyPic = companyPic;
	}

	
	public Company(String companyName, String companyArea,
			String companySize, String companyType, String companyBrief,
			int companyState, int companySort,String companyPic) {
		super();
		this.companyName = companyName;
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companySort = companySort;
		this.companyPic = companyPic;
	}

	public Company(String companyArea, String companySize,
			String companyType, String companyBrief, int companyState,
			int companySort, String companyPic) {
		
		super();
		this.companyArea = companyArea;
		this.companySize = companySize;
		this.companyType = companyType;
		this.companyBrief = companyBrief;
		this.companyState = companyState;
		this.companySort = companySort;
		this.companyPic = companyPic;
	}

	public Company(String companyName) {
		// TODO Auto-generated constructor stub
		super();
		this.companyName = companyName;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyArea() {
		return companyArea;
	}

	public void setCompanyArea(String companyArea) {
		this.companyArea = companyArea;
	}

	public String getCompanySize() {
		return companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public int getCompanyState() {
		return companyState;
	}

	public void setCompanyState(int companyState) {
		this.companyState = companyState;
	}

	public int getCompanySort() {
		return companySort;
	}

	public void setCompanySort(int companySort) {
		this.companySort = companySort;
	}

	public int getCompanyViewnum() {
		return companyViewnum;
	}

	public void setCompanyViewnum(int companyViewnum) {
		this.companyViewnum = companyViewnum;
	}

	public String getCompanyPic() {
		return companyPic;
	}

	public void setCompanyPic(String companyPic) {
		this.companyPic = companyPic;
	}

	public Set<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Set<Job> jobs) {
		this.jobs = jobs;
	}

}
