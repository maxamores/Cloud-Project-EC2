package com.eamores.cloud.aws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LatestUpdate {
	private static ConnectionManager conn = null;
	private static Statement stmt = null;
	private static ResultSet rset = null;


	public static Date getDate() {
		Date ts = null;
		
		String query = "SELECT MAX(ts) AS ts FROM Last_Update";

		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				ts = rset.getTimestamp("ts");
			}
			
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	
		return ts;		
	}
	
	public static int updateWith(Date newd, Date oldd) {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		int updates = 0;
		
		String query = "UPDATE Last_Update SET ts ='" + ft.format(newd) + "' ";
		query += "WHERE ts='" + ft.format(oldd) + "'";
		

		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			updates = stmt.executeUpdate(query);

			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (Exception ex) {
			System.out.println("Sql exception: " + ex);
		}
		
		return updates;
	}
	
	public static int add(Date date) {
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		int updates = 0;

		String query = "INSERT INTO Last_Update (ts) ";
				query += "VALUES('" + ft.format(date) + "')" ;
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			updates = stmt.executeUpdate(query);

			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		} catch (Exception ex) {
			System.out.println("Sql exception: " + ex);
		}
		return updates;
	}


}
