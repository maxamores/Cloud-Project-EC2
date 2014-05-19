package com.eamores.cloud.aws.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.eamores.cloud.aws.tables.Links;

public class Blobs {
	private static ConnectionManager conn = null;
	private static Statement stmt = null;
	private static ResultSet rset = null;

	public static int add(String user_id, String blob_key,  String imageUrl) {
		int updates = 0;
		
		String query = "INSERT INTO Blobs (user_id, blob_key, google_url)";
		query += "VALUES('" + user_id + "', '" + blob_key + "', '" + imageUrl + "')" ;
		

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
	
	public static ArrayList<Links> getLinks(Date since){
		ArrayList<Links> links = new ArrayList<Links>();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		
		String query = "SELECT user_id, blob_key, google_url, amazon_url, ts FROM Blobs WHERE ts > '" + ft.format(since) + "'";
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			while (rset.next()) {
				String user_id = rset.getString("user_id");
				String blob_key = rset.getString("blob_key");
				String google_url = rset.getString("google_url");
				String amazon_url = rset.getString("amazon_url");
				Date ts = rset.getTimestamp("ts");
				
				links.add(new Links(user_id, blob_key, google_url, amazon_url, ts));
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
		
		return links;
	}
	
	public static ArrayList<Links> getLinks(){
		ArrayList<Links> links = new ArrayList<Links>();
		
		String query = "SELECT user_id, blob_key, google_url, amazon_url, ts FROM Blobs";

		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			rset = stmt.executeQuery(query);

			
			while (rset.next()) {
				String user_id = rset.getString("user_id");
				String blob_key = rset.getString("blob_key");
				String google_url = rset.getString("google_url");
				String amazon_url = rset.getString("amazon_url");
				Date ts = rset.getTimestamp("ts");
				
				links.add(new Links(user_id, blob_key, google_url, amazon_url, ts));
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
		
		return links;
	}
	
	
	public static int updateAmzLink(String user_id, String blob_key, String url) {
		int updates = 0;
		
		String query = "UPDATE Blobs SET amazon_url ='" + url + "' ";
		query += "WHERE user_id='" + user_id + "' and blob_key='" + blob_key + "'";
		

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
