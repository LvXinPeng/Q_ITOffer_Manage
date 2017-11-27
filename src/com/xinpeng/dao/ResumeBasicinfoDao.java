package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.xinpeng.model.ResumeBasicinfo;
import com.xinpeng.util.JDBCUtil;

public class ResumeBasicinfoDao {
	
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;
	
	
	public static int add(ResumeBasicinfo basicinfo, int applicantID) {
		int basicinfoID = 0;
		String sql = "INSERT INTO tb_resume_basicinfo("
				+ "basicinfo_id, realname, gender, birthday, current_loc, "
				+ "resident_loc, telephone, email, job_intension, job_experience, head_shot,applicant_id) "
				+ "VALUES(LAST_INSERT_ID(),?,?,?,?,?,?,?,?,?,?,?)";
		conn = JDBCUtil.getConnection();
		
		try {
		
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, basicinfo.getRealName());
			pstmt.setString(2, basicinfo.getGender());
			pstmt.setTimestamp(3, basicinfo.getBirthday() == null ? null
					: new Timestamp(basicinfo.getBirthday().getTime()));
			pstmt.setString(4, basicinfo.getCurrentLoc());
			pstmt.setString(5, basicinfo.getResidentLoc());
			pstmt.setString(6, basicinfo.getTelephone());
			pstmt.setString(7, basicinfo.getEmail());
			pstmt.setString(8, basicinfo.getJobIntension());
			pstmt.setString(9, basicinfo.getJobExperience());
			pstmt.setString(10, basicinfo.getHeadshot());
			pstmt.setInt(11, applicantID);
			pstmt.executeUpdate();
			// 获取当前生成的简历标识
			String sql2 = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next())
				basicinfoID = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, pstmt, conn);
		}
		return basicinfoID;
	}
	
	public static int getID(String username){
		int applicantID = 0;
		String sql = "select APPLICANT_ID from tb_users where USER_LOGNAME = '" + username + "'";
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				applicantID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicantID;
	}

}
