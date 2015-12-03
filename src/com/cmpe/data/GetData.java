package com.cmpe.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//packages
import com.cmpe.loginDatabaseConnectivity.*;

public class GetData {
	
	PreparedStatement pst = null;
	Connection conn;
	ResultSet rs = null;
	LoginDatabase db = new LoginDatabase();
	
	public ResultSet getTopDays() {		
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAl1, VAL2 from DATAEXTRACT where ID = ?");
			pst.setString(1, "QRY_DAY");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getMonthlyIncidents() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL2 from DATAEXTRACT where ID = ? ORDER BY VAL1;");
			pst.setString(1, "QRY_MONTH");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getHourlyIncidents() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL2 from DATAEXTRACT where ID = ? ORDER BY VAL1;");
			pst.setString(1, "QRY_HOUR");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getTopCategory() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL1, VAL2 from DATAEXTRACT where ID = ?");
			pst.setString(1, "QRY_CAT");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getTotalIncidents() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL2 from DATAEXTRACT where ID = ?");
			pst.setString(1, "QRY_TOTAL");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getTotalTrafficIncidents() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL2 from DATAEXTRACT where ID = ?");
			pst.setString(1, "QRY_TRAFFI");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getDrivingInfluence() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL1, VAL2 from DATAEXTRACT where ID = ? ORDER BY RAND() LIMIT 1");
			pst.setString(1, "QRY_CAT");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getYearlyData(int year) {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL2 from DATAEXTRACT where ID = ? AND VAL1 = ?");
			pst.setString(1, "QRY_YEAR");
			pst.setInt(2, year);
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	
	public ResultSet getYearlyData() {
		conn = db.getConn();
		try {
			pst = conn.prepareStatement("select VAL1, VAL2 from DATAEXTRACT where ID = ?");
			pst.setString(1, "QRY_YEAR");
			rs = pst.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return rs;
	}
	

}
