package com.eamores.cloud.aws.recognition;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;

public class S3Image {
	private String secretKey;
	private String accessKey;
	
	public S3Image() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config/AwsCredentials.properties"));
		} catch (Exception e) {
			System.out.println("no config: config/AwsCredentials.properties");
		}

		secretKey = prop.getProperty("secretKey").trim();
		accessKey = prop.getProperty("accessKey").trim();
	}
	
	public String storeFile(String file, String fname){
		
		File sfile = new File(file);
				
		if(!sfile.exists()){
			return null;
		}
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		AmazonS3 s3 = new AmazonS3Client(credentials);
        Region usEast = Region.getRegion(Regions.US_EAST_1);
        s3.setRegion(usEast);
      
        Bucket bucket = new Bucket("MaxDev");
        s3.putObject(bucket.getName(), fname, sfile);
        
        s3.setObjectAcl(bucket.getName(), fname, CannedAccessControlList.PublicRead);
        
        
        
        return "https://s3.amazonaws.com/" + bucket.getName() + "/" + fname;
	}

	

}
