package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.xinpeng.model.User;
import com.xinpeng.util.JDBCUtil;

public class UserDao {
	// 操作数据库数据
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;
	static User user;

	/**
	 * 用户登录
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
			while (rs.next()) { // 获取用户相关信息
				user = new User();// 使user不是null，
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
	 * 用户注册
	 * 
	 * @param user
	 * @return
	 */
	public static boolean reg(User user) {
		// 定义SQL语句
		String sql = "insert into tb_users(USER_LOGNAME,USER_PWD) "
				+ "values('" + user.getUsername() + "','" + user.getPassword()
				+ "')";
		// 连接数据库
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
	//检查用户名是否注册
	public static boolean checkUserName(String userName) {
		boolean flag = false;
		// 定义SQL语句
		String sql = "select * from tb_users where USER_LOGNAME = '" + userName
				+ "'";
		// 连接数据库
		conn = JDBCUtil.getConnection();
		try {
			// 编译SQL
			stmt = conn.prepareStatement(sql);
			// 设置参数 [可选-Statement]

			// 执行SQL语句 并且获取结果集
			rs = stmt.executeQuery(sql);
			// 判断是否已存在
			flag = rs.next();// 存在即f为true
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭数据库
			JDBCUtil.close(conn, stmt, null, rs);
		}
		return flag;
	}
	
	//判断是否已经有简历
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
