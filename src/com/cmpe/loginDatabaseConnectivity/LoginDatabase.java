package com.cmpe.loginDatabaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cmpe.login.PasswordEncrypt;

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
			
			//hash pass and then compare to the entry in DB
			String hashedPass = PasswordEncrypt.sha256(password);
			System.out.println(password);
			System.out.println(hashedPass);
			pst.setString(2, hashedPass);

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
	
	public ArrayList<String> getDistinctCategories() {

	    Statement stmt = null;
	    String query = "SELECT DISTINCT(CATEGORY) FROM customizedsearchcrimedata";
        ArrayList<String> list = new ArrayList<String>();
        
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String category = rs.getString("CATEGORY");
	            list.add(category);
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	        //JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    
	    return list;
	}
	
	
	public ArrayList<String> getDistinctYears() {

	    Statement stmt = null;
	    String query = "SELECT DISTINCT(YEAR) FROM customizedsearchcrimedata";
        ArrayList<String> list = new ArrayList<String>();
        
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String category = rs.getString("YEAR");
	            list.add(category);
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	        //JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    
	    return list;
	}
	
	
	public ArrayList<String> getDistinctMonths() {
	    Statement stmt = null;
	    String query = "SELECT DISTINCT(MONTH) FROM customizedsearchcrimedata";
        ArrayList<String> list = new ArrayList<String>();
        
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);
	        while (rs.next()) {
	            String category = rs.getString("MONTH");
	            list.add(category);
	        }
	    } catch (SQLException e ) {
	    	e.printStackTrace();
	        //JDBCTutorialUtilities.printSQLException(e);
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	    
	    return list;
	}

	public String getLongLatOfLocations(String year, String month, String category) {
		String toRet = "";
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = conn.prepareStatement("select X,Y from cmpe272_fall2015_finalproj.customizedsearchcrimedata WHERE CATEGORY = ? AND MONTH = ? AND YEAR = ?");
			pst.setString(1, category);
			pst.setString(2, month);
			pst.setString(3, year);
			
			System.out.println(pst.toString());
			JSONArray jarray = new JSONArray();
			rs = pst.executeQuery();
			while(rs.next())
			{
				JSONObject row = new JSONObject();
				row.put("longitude", rs.getString(1));
				row.put("latitude", rs.getString(2));
				jarray.put(row);
			}
			toRet = jarray.toString();
			System.out.println("DATA IN DATABASE");
			System.out.println(toRet);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
	        if (pst != null) { try {
	        	pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
		}
		
		return toRet ;
	}
}
