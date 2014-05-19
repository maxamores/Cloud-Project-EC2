package com.eamores.cloud.aws.recognition;

import java.util.ArrayList;
import java.util.Date;

import com.eamores.cloud.aws.dao.Blobs;
import com.eamores.cloud.aws.dao.LatestUpdate;
import com.eamores.cloud.aws.dao.Tags;
import com.eamores.cloud.aws.tables.Links;

public class RunRecognition {

	public static void main(String[] args) {
		while (true) {
			try {
				runTask();
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void runTask(){
		
		Date lastupadte = LatestUpdate.getDate();
		Date maxDate = lastupadte;
		
		ArrayList<Links> links;
		
		if(lastupadte == null){
			links = Blobs.getLinks();
		}
		else{
			links = Blobs.getLinks(lastupadte);
		}
		
		if(links.size() == 0){
			return;
		}
				
		for(int i = 0; i < links.size(); i++){
			String user_id = links.get(i).getUser_id();
			String picUrl = links.get(i).getGoogle_url() + "=s800"; 
			String filename = links.get(i).getBlob_key();
			
			FindKeyPoint find = new FindKeyPoint(picUrl, filename);
			find.findKeyPoints();
			
			
			S3Image s3i = new S3Image();
			String amz_url = s3i.storeFile(find.getOutputfile(), filename);
			find.eraseOutputfile();
			
			if(amz_url != null){
				Blobs.updateAmzLink(user_id, filename, amz_url);
			}
				
			Tags.add(user_id, filename, find.getTags());
			
			Date ts = links.get(i).getTs();
			if(maxDate == null || ts.compareTo(maxDate) > 0){
				maxDate = ts;
			}
		}
		
		if(lastupadte == null){
			LatestUpdate.add(maxDate);
		}
		else{
			LatestUpdate.updateWith(maxDate, lastupadte);
		}
	}
}
