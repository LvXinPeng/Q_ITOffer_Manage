package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xinpeng.model.Applicant;
import com.xinpeng.model.Job;
import com.xinpeng.model.JobApply;
import com.xinpeng.model.ResumeBasicinfo;
import com.xinpeng.util.JDBCUtil;
/** 
 * 职位申请信息数据库操作类 
 * @公司 青软实训
 * @作者 fengjj 
 */
public class JobApplyDAO {

	public List<JobApply> query(String companyId, String jobId,
			String startDate, String endDate) {
		List<JobApply> list = new ArrayList<JobApply>();
		Connection conn = JDBCUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer("SELECT a.apply_id,a.apply_state,a.apply_date,"
				+ "j.job_id,j.job_name,c.applicant_id,d.basicinfo_id,d.realname "
				+ "FROM tb_jobapply a , tb_job j ,tb_applicant c ,tb_resume_basicinfo d "
				+ "WHERE a.job_id=j.job_id and a.applicant_id=c.applicant_id and c.applicant_id=d.applicant_id ");
		try {
			stmt = conn.createStatement();
			int cid = Integer.parseInt(companyId==null ? "0" :companyId);
			int jid = Integer.parseInt(jobId==null ? "0" :jobId);
			if(cid != 0)
				sql.append(" and j.company_id = " + cid);
			if(jid != 0)
				sql.append(" and a.job_id = "+jid);
			if(!"".equals(startDate))
				sql.append(" and a.apply_date >= to_timestamp('"+startDate+"','yyyy-MM-dd HH24:mi:ss')");
			if(!"".equals(endDate))
				sql.equals(" and a.apply_date <= to_timestamp('"+endDate+"','yyyy-MM-dd HH24:mi:ss')");
			rs = stmt.executeQuery(sql.toString());
			System.out.println(sql.toString());
			while (rs.next()) {
				//姓名、申请职位、申请状态、申请日期
				JobApply ja = new JobApply();
				ja.setApplyId(rs.getInt(1));
				ja.setApplyState(rs.getInt(2));
				ja.setApplyDate(rs.getTimestamp(3));
				Job job = new Job();
				job.setJobId(rs.getInt(4));
				job.setJobName(rs.getString(5));
				Applicant applicant = new Applicant();
				applicant.setApplicantId(rs.getInt(6));
				ResumeBasicinfo resume = new ResumeBasicinfo();
				resume.setBasicinfoId(rs.getInt(7));
				resume.setRealName(rs.getString(8));
				applicant.setResume(resume);
				ja.setJob(job);
				ja.setApplicant(applicant);
				list.add(ja);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt,null, rs);
		}
		return list;
	}

}
