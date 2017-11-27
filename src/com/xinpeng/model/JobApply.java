package com.xinpeng.model;

import java.util.Date;

public class JobApply {
	private int applyId;

	private int jobId;

	private Applicant applicant;

	private Date applyDate;

	private int applyState;

	private Job job;

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public int getApplyState() {
		return applyState;
	}

	public void setApplyState(int applyState) {
		this.applyState = applyState;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
