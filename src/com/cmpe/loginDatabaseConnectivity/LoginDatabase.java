package com.cmpe.loginDatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginDatabase {
	private Connection conn;
	
	public LoginDatabase() {
		
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "cmpe272_fall2015_finalproj";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";

		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
			
//				PreparedStatement pst = null;
//				ResultSet rs = null;
//				pst = conn.prepareStatement("select * from cmpe272_fall2015_finalproj.credentials where username=? and password=?");
//				pst.setString(1, "john");
//				pst.setString(2, "smith");
//
//				rs = pst.executeQuery();
//				
//				System.out.println(rs);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		return conn;
	}

	public boolean validate(String username, String password) {

		boolean status = false;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			System.out.println(conn);
			pst = conn.prepareStatement("select * from cmpe272_fall2015_finalproj.credentials where username=? and password=?");
			pst.setString(1, username);
			pst.setString(2, password);

			rs = pst.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return status;
	}
}
