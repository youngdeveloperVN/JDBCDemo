package com.vn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainJDBC_Statement {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			//2.1 Tạo driver cho DriverManager
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.2 Khai báo URL - connection string
			String url = "jdbc:mysql://localhost:3306/10_jdbc";
			
			//2.3 Khai báo username, password
			String username = "root";
			String password = "";
			
			//2.4 DriverManager.getConnection() <=   2.2 và 2.3
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected.....");
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM users");
		  //ResultSet rs = statement.getResultSet();
			
			while (rs.next()) {
				String uname = rs.getString(1);
				String upassword = rs.getString(2);
				String name = rs.getString(3);
				String phone = rs.getString("phone");
				
				System.out.println(uname + '-' + "password: " + upassword);
				System.out.println(name + '-' + "phone: " + phone);
				
			}
			System.out.println("Disconnect.....");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
