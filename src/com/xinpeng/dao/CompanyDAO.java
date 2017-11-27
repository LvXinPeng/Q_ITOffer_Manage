package com.xinpeng.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.xinpeng.model.Company;
import com.xinpeng.model.Job;
import com.xinpeng.util.JDBCUtil;

public class CompanyDAO {

	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static PreparedStatement pstmt;

	public List<Company> getCompanyList() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = JDBCUtil.getConnection();
		try {
			String sql = "SELECT tb_company.company_id,company_pic,job_id,job_name,job_salary,job_area,job_endtime FROM tb_company LEFT OUTER JOIN tb_job ON tb_job.company_id = tb_company.company_id WHERE company_state = 1 AND job_id IN ( SELECT MAX(job_id) FROM tb_job WHERE job_state = 1 GROUP BY company_id )";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				Job job = new Job();
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyPic(rs.getString("company_pic"));
				job.setJobId(rs.getInt("job_id"));
				job.setJobName(rs.getString("job_name"));
				job.setJobSalary(rs.getString("job_salary"));
				job.setJobArea(rs.getString("job_area"));
				job.setJobEnddate(rs.getTimestamp("job_endtime"));
				company.getJobs().add(job);
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	public static List<Company> getCompany() {
		List<Company> list = new ArrayList<Company>();
		conn = JDBCUtil.getConnection();
		try {
			String sql = "select COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,"
					+ "COMPANY_BRIEF,COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM,COMPANY_PIC "
					+ "from tb_company";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setCompanyName(rs.getString("COMPANY_NAME"));
				company.setCompanyArea(rs.getString("COMPANY_AREA"));
				company.setCompanySize(rs.getString("COMPANY_SIZE"));
				company.setCompanyType(rs.getString("COMPANY_TYPE"));
				company.setCompanyBrief(rs.getString("COMPANY_BRIEF"));
				company.setCompanyState(rs.getInt("COMPANY_STATE"));
				company.setCompanySort(rs.getInt("COMPANY_SORT"));
				company.setCompanyViewnum(rs.getInt("COMPANY_VIEWNUM"));
				company.setCompanyPic(rs.getString("company_pic"));
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}

	public static int addCompany(Company company, String companyName) {
		int companyId = 0;

		String sql = "insert into tb_company("
				+ "COMPANY_ID,COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,"
				+ "COMPANY_BRIEF,COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM,COMPANY_PIC)"
				+ "Values(LAST_INSERT_ID(),?,?,?,?,?,?,?,1,?)";

		conn = JDBCUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getCompanyName());
			pstmt.setString(2, company.getCompanyArea());
			pstmt.setString(3, company.getCompanySize());
			pstmt.setString(4, company.getCompanyType());
			pstmt.setString(5, company.getCompanyBrief());
			pstmt.setInt(6, company.getCompanyState());
			pstmt.setInt(7, company.getCompanySort());
			pstmt.setString(8, company.getCompanyPic());
			pstmt.executeUpdate();
			// 获取当前生成的简历标识
			String sql2 = "SELECT LAST_INSERT_ID()";
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				companyId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(null, pstmt, conn);
		}

		return companyId;

	}

	public static boolean upadteCompany(Company company, String companyName) {
		conn = JDBCUtil.getConnection();
		int num = 0;
		String sql = "update tb_company set COMPANY_AREA ='"
				+ company.getCompanyArea() + "'," + "COMPANY_SIZE='"
				+ company.getCompanySize() + "'," + "COMPANY_TYPE='"
				+ company.getCompanyType() + "'," + "COMPANY_STATE='"
				+ company.getCompanyState() + "'," + "COMPANY_PIC='"
				+ company.getCompanyPic() + "'" + "where COMPANY_NAME ='"
				+ companyName + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			num = pstmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return num > 0;
	}

	public static boolean deleteCompany(String companyName) {
		conn = JDBCUtil.getConnection();
		int num = 0;
		String sql = "delete from tb_company where COMPANY_NAME = '"
				+ companyName + "'";
		try {
			stmt = conn.prepareStatement(sql);
			num = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return num > 0;
	}

	public int getRecordCount() {
		int recordCount = 0;
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM tb_company ";
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

	public List<Company> getCompanyPageList(int pageNo, int pageSize) {
		int index = pageSize * (pageNo - 1) + 1;
		List<Company> list = new ArrayList<Company>();
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select COMPANY_NAME,COMPANY_AREA,COMPANY_SIZE,COMPANY_TYPE,"
					+ "COMPANY_STATE,COMPANY_SORT,COMPANY_VIEWNUM "
					+ "from tb_company limit ?,?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, index);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Company company = new Company();
				company.setCompanyName(rs.getString("COMPANY_NAME"));
				company.setCompanyArea(rs.getString("COMPANY_AREA"));
				company.setCompanySize(rs.getString("COMPANY_SIZE"));
				company.setCompanyType(rs.getString("COMPANY_TYPE"));
				// company.setCompanyBrief(rs.getString("COMPANY_BRIEF"));
				company.setCompanyState(rs.getInt("COMPANY_STATE"));
				company.setCompanySort(rs.getInt("COMPANY_SORT"));
				company.setCompanyViewnum(rs.getInt("COMPANY_VIEWNUM"));
				// company.setCompanyPic(rs.getString("company_pic"));
				// System.out.println(company.toString());
				list.add(company);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, connection);
		}
		return list;
	}
	
	public Company getCompanyByID(String companyID) {
		Company company = new Company();
		Connection conn = JDBCUtil.getConnection();
		try {
			String sql = "SELECT * FROM tb_company WHERE company_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(companyID));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				company.setCompanyId(rs.getInt("company_id"));
				company.setCompanyArea(rs.getString("company_area"));
				company.setCompanyBrief(rs.getString("company_brief"));
				company.setCompanyPic(rs.getString("company_pic"));
				company.setCompanySize(rs.getString("company_size"));
				company.setCompanyType(rs.getString("company_type"));
				company.setCompanyViewnum(rs.getInt("company_viewnum"));
				company.setCompanyName(rs.getString("company_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return company;
	}
	
	/**
	 * 查询所有企业的名称和标识
	 * @return
	 */
	public List<Company> selectAllCompanyName() {
		List<Company> list = new ArrayList<Company>();
		Connection conn = JDBCUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT company_id,company_name FROM tb_company ORDER BY company_id DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Company company = new Company();
				company.setCompanyId(rs.getInt(1));
				company.setCompanyName(rs.getString(2));
				list.add(company);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeJDBC(rs, pstmt, conn);
		}
		return list;
	}


}