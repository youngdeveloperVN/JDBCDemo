package com.vn.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainJDBC_PreparedStatement {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			//2.1 Tạo driver cho DriverManager
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.2 Khai báo URL - connection string
			String url = "jdbc:mysql://localhost:3306/10_jdbc12";
			
			//2.3 Khai báo username, password
			String username = "root";
			String password = "";
			
			//2.4 DriverManager.getConnection() <=   2.2 và 2.3
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected.....");
			
			String unameSearch = "Ngoc";
			String phoneSearch = "09";
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE name LIKE ? OR phone LIKE ?");
			statement.setString(1, "%" + unameSearch + "%");
			statement.setString(2, "%" + phoneSearch + "%");
			ResultSet rs = statement.executeQuery();
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
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			System.out.println(e.getSQLState());
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
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
