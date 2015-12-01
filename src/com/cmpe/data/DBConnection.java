package com.cmpe.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public Connection getConnection() {

		Connection conn = null;

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "cmpe272_fall2015_finalproj";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "admin";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;

	}

}
