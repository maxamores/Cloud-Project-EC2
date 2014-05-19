package com.eamores.cloud.aws.dao;

import java.sql.Statement;
import java.util.ArrayList;

public class Tags {
	private static ConnectionManager conn = null;
	private static Statement stmt = null;

	public static int add(String user_id, String blob_key,  ArrayList<String> tags) {
		int updates = 0;
		
		if (tags.size() == 0 || tags == null){
			return 0;
		}
		
		String query = "INSERT INTO Tags (user_id, blob_key, tag) VALUES ";
		
		for(int i = 0; i < tags.size(); i++){
			query += "('" + user_id + "', '" + blob_key + "', '" + tags.get(i) + "')";
			
			if(i + 1 < tags.size()){
				query += ", ";
			}
		}		
		
		try {
			conn = new ConnectionManager();
			stmt = conn.getCon().createStatement();
			updates = stmt.executeUpdate(query);
			

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
