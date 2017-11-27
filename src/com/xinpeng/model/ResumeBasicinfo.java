package com.xinpeng.model;

import java.util.Date;

public class ResumeBasicinfo {
	
		// 简历id
		private int basicinfoId;
		// 真实姓名 
		private String realName;
		// 性别
		private String gender;
		// 出生日期
		private Date birthday;
		// 当前所在地
		private String currentLoc;
		// 户口所在地
		private String residentLoc;
		// 手机
		private String telephone;
		// 邮件
		private String email;
		// 求职意向
		private String jobIntension;
		// 工作经验
		private String jobExperience;
		// 头像
		private String headshot;
		
		public ResumeBasicinfo(){
			
		}
		
		public ResumeBasicinfo(String realName, String gender,
				Date birthday, String currentLoc, String residentLoc,
				String telephone, String email, String jobIntension,
				String jobExperience) {
			super();
			this.realName = realName;
			this.gender = gender;
			this.birthday = birthday;
			this.currentLoc = currentLoc;
			this.residentLoc = residentLoc;
			this.telephone = telephone;
			this.email = email;
			this.jobIntension = jobIntension;
			this.jobExperience = jobExperience;
			
		}


		public int getBasicinfoId() {
			return basicinfoId;
		}


		public void setBasicinfoId(int basicinfoId) {
			this.basicinfoId = basicinfoId;
		}


		public String getRealName() {
			return realName;
		}


		public void setRealName(String realName) {
			this.realName = realName;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}


		public Date getBirthday() {
			return birthday;
		}


		public void setBirthday(Date birthday) {
			this.birthday = birthday;
		}


		public String getCurrentLoc() {
			return currentLoc;
		}


		public void setCurrentLoc(String currentLoc) {
			this.currentLoc = currentLoc;
		}


		public String getResidentLoc() {
			return residentLoc;
		}


		public void setResidentLoc(String residentLoc) {
			this.residentLoc = residentLoc;
		}


		public String getTelephone() {
			return telephone;
		}


		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getJobIntension() {
			return jobIntension;
		}


		public void setJobIntension(String jobIntension) {
			this.jobIntension = jobIntension;
		}


		public String getJobExperience() {
			return jobExperience;
		}


		public void setJobExperience(String jobExperience) {
			this.jobExperience = jobExperience;
		}


		public String getHeadshot() {
			return headshot;
		}


		public void setHeadshot(String headshot) {
			this.headshot = headshot;
		}
			
		
	
}
