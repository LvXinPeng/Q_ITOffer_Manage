package com.xinpeng.model;

import java.util.Date;


public class Job {
	// 职位编号
	private int jobId;
	// 所属企业
	private Company company;
	// 职位名称
	private String jobName;
	// 招聘人数
	private int jobHiringnum;
	// 职位薪资
	private String jobSalary;
	// 工作地区
	private String jobArea;
	// 职位描述
	private String jobDesc;
	// 结束日期
	private Date jobEnddate;
	// 招聘状态:1招聘中 2已暂停 3已结束
	private int jobState;

	public Job() {
		super();
	}

	public Job(Company company, String jobName, int jobHiringnum,
			String jobSalary, String jobArea, String jobDesc, Date jobEnddate,
			int jobState) {
		super();
		this.company = company;
		this.jobName = jobName;
		this.jobHiringnum = jobHiringnum;
		this.jobSalary = jobSalary;
		this.jobArea = jobArea;
		this.jobDesc = jobDesc;
		this.jobEnddate = jobEnddate;
		this.jobState = jobState;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getJobHiringnum() {
		return jobHiringnum;
	}

	public void setJobHiringnum(int jobHiringnum) {
		this.jobHiringnum = jobHiringnum;
	}

	public String getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(String jobSalary) {
		this.jobSalary = jobSalary;
	}

	public String getJobArea() {
		return jobArea;
	}

	public void setJobArea(String jobArea) {
		this.jobArea = jobArea;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public Date getJobEnddate() {
		return jobEnddate;
	}

	public void setJobEnddate(Date jobEnddate) {
		this.jobEnddate = jobEnddate;
	}

	public int getJobState() {
		return jobState;
	}

	public void setJobState(int jobState) {
		this.jobState = jobState;
	}

}
