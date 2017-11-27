package com.xinpeng.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC连接数据库封装类
 * 
 * @author Administrator
 * 
 */
public class JDBCUtil {
    // for orcale
   /*  private static final String driver ="oracle.jdbc.driver.OracleDriver";
     private static final String url ="jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
     private static final String username ="citylife";
     private static final String password ="123";*/
    
    // for mysql
	private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/itoffer";
    private static final String username = "root";
    private static final String password = "123";
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }
    
    public static void close(Connection conn, Statement stmt,
            PreparedStatement pstmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public static void closeJDBC(Object object, PreparedStatement pstmt,
			Connection conn) {
		// TODO Auto-generated method stub
		
	}
  
}
