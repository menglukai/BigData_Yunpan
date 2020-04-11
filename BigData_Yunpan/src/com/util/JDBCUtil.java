package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	public static String Driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static String url="jdbc:sqlserver://localhost:1433;database=db_yun";
	public static String name="sa";
	public static String pwd="123456";
	public static Connection conn;
	public static ResultSet rs;
	public static PreparedStatement ps;
	private int i;
	public Connection getConn() {
		
		try {
			//��������
			Class.forName(Driver);
			//��������
		    conn = DriverManager.getConnection(url, name, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		
	}
	public ResultSet checkUser(String sql,String name,String pwd) {
		//��������
		conn=getConn();
		try {
			//����Ԥ�������
			ps = conn.prepareStatement(sql);
			//���ʺŸ�ֵ
			ps.setString(1, name);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	public int update(String sql,String name,String pwd) {
		//��������
		conn=getConn();
		try {
			//����Ԥ�������
			ps = conn.prepareStatement(sql);
			//���ʺŸ�ֵ
			ps.setString(1, name);
			ps.setString(2, pwd);
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
	}

}
