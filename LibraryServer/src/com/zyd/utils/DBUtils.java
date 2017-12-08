package com.zyd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	
	public static String Table_Student = "STUDENT";
	public static String Table_Admin="Adminstrator";
	public static String Table_Seat = "SEAT";
	public static String Table_Sit = "SIT";
	private static Connection connect;
	private static Connection getConnection(){
		if(connect == null){
			String url = "jdbc:mysql://localhost:3306/LibrarySeat";
			try{
				Class.forName("com.mysql.jdbc.Driver");
				connect = (Connection)DriverManager.getConnection(url,"root","zyd2288778.");
				System.out.println("数据库连接成功");
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}
		return connect;
	}
	
	public static ResultSet query(String querySql)throws SQLException{
		Statement statement=(Statement) getConnection().createStatement();
		return statement.executeQuery(querySql);
	}
	
	public static int update(String insertSql)throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		System.out.println(insertSql);
		return stateMent.executeUpdate(insertSql);
	}
	
	public static void closeConnection() {
		if (connect != null) {
			try {
				connect.close();
				connect = null;
			} catch (SQLException e) {
				System.out.println("关闭错误：[" + e.getErrorCode() + "]" + e.getMessage());
			}
		}
	}
}
