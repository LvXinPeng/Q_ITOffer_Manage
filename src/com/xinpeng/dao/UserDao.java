package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xinpeng.model.User;
import com.xinpeng.util.JDBCUtil;

public class UserDao {
	// �������ݿ�����
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static User user;

	/**
	 * �û���¼
	 * 
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public static User login(String userName, String userPassword) {

		String sql = "select * from tb_users where USER_LOGNAME = '" + userName
				+ "' and USER_PWD = '" + userPassword + "'";
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) { // ��ȡ�û������Ϣ
				user = new User();// ʹuser����null��
				user.setUsername(rs.getString("USER_LOGNAME"));
				user.setPassword(rs.getString("USER_PWD"));
				user.setApplicantID(rs.getInt("APPLICANT_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, null, rs);
		}
		return user;
	}

	/**
	 * �û�ע��
	 * 
	 * @param user
	 * @return
	 */
	public static boolean reg(User user) {
		// ����SQL���
		String sql = "insert into tb_users(USER_LOGNAME,USER_PWD) "
				+ "values('" + user.getUsername() + "','" + user.getPassword()
				+ "')";
		// �������ݿ�
		conn = JDBCUtil.getConnection();
		int num = 0;
		try {
			stmt = conn.prepareStatement(sql);
			num = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, null, rs);
		}
		/*
		 * if (num > 0) { return true; }
		 */
		return num > 0;

	}
	//����û����Ƿ�ע��
	public static boolean checkUserName(String userName) {
		boolean flag = false;
		// ����SQL���
		String sql = "select * from tb_users where USER_LOGNAME = '" + userName
				+ "'";
		// �������ݿ�
		conn = JDBCUtil.getConnection();
		try {
			// ����SQL
			stmt = conn.prepareStatement(sql);
			// ���ò��� [��ѡ-Statement]

			// ִ��SQL��� ���һ�ȡ�����
			rs = stmt.executeQuery(sql);
			// �ж��Ƿ��Ѵ���
			flag = rs.next();// ���ڼ�fΪtrue
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// �ر����ݿ�
			JDBCUtil.close(conn, stmt, null, rs);
		}
		return flag;
	}
	
	//�ж��Ƿ��Ѿ��м���
		public static int isExistResume(int applicantID) {
			int resumeID = 0;
			conn = JDBCUtil.getConnection();
			String sql = "SELECT basicinfo_id FROM tb_resume_basicinfo WHERE applicant_id=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, applicantID);
				rs = pstmt.executeQuery();
				if (rs.next())
					resumeID = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.closeJDBC(rs, pstmt, conn);
			}
			return resumeID;
		}
}
