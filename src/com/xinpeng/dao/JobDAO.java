package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.xinpeng.model.Company;
import com.xinpeng.model.Job;
import com.xinpeng.util.JDBCUtil;

public class JobDAO {

	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;

	public static int addJob(Job job, String jobName) {
		int jobId = 0;
		conn = JDBCUtil.getConnection();

		String sql = "insert into tb_job(JOB_ID,COMPANY_ID,JOB_NAME,JOB_HIRINGNUM,JOB_SALARY,JOB_AREA,JOB_DESC,JOB_ENDTIME,JOB_STATE)"
				+ "Values(LAST_INSERT_ID(),?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			// pstmt.setInt(1, job.getCompany().getCompanyId());
			// System.out.println(job.getCompany().getCompanyId());
			pstmt.setString(2, job.getJobName());
			pstmt.setInt(3, job.getJobHiringnum());
			pstmt.setString(4, job.getJobSalary());
			pstmt.setString(5, job.getJobArea());
			pstmt.setString(6, job.getJobDesc());
			pstmt.setTimestamp(7, job.getJobEnddate() == null ? null
					: new Timestamp(job.getJobEnddate().getTime()));
			pstmt.setInt(8, job.getJobState());
			pstmt.executeUpdate();
			String sql2 = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				jobId = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, pstmt, conn);
		}

		return jobId;
	}

	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_job ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				recordCount = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return recordCount;
	}

	public List<Job> getJobPageList(int pageNo, int pageSize) {
		int index = pageSize * (pageNo - 1) + 1;
		List<Job> list = new ArrayList<Job>();
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from tb_job limit ?,?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobName((rs.getString("JOB_NAME")));
				job.setJobHiringnum(rs.getInt("JOB_HIRINGNUM"));
				job.setJobEnddate(rs.getDate("JOB_ENDTIME"));
				// job.setJobState(rs.getInt("JOB_STATE"));
				job.setJobSalary(rs.getString("JOB_SALARY"));
				job.setJobArea(rs.getString("JOB_AREA"));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}

	public List<Job> getJobListByCompanyID(String companyID) {
		List<Job> list = new ArrayList<Job>();
		Connection conn = JDBCUtil.getConnection();
		try {
			String sql = "SELECT * FROM tb_job WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	public Job getJobByID(String jobid) {
		Job job = new Job();
		Connection conn = JDBCUtil.getConnection();
		try {
			String sql = "SELECT tb_job.*,company_pic "
					+ "FROM tb_job "
					+ "INNER JOIN tb_company on tb_job.company_id =  tb_company.company_id "
					+ "WHERE job_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(jobid));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobHiringnum(rs.getInt("job_hiringnum"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobDesc(rs.getString("job_desc"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				job.setJobState(rs.getInt("job_state"));
				Company company = new Company();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setCompany(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return job;
	}
	
	/**
	 * 根据企业编号查询此企业的所有招聘职位
	 * 
	 * @param companyID
	 * @return
	 */
	public List<Job> selectJobNameByCompany(int companyID) {
		List<Job> list = new ArrayList<Job>();
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT job_id,job_name FROM tb_job WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,companyID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

}
