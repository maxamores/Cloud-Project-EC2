package com.eamores.cloud.aws.recognition;

import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

public class FindKeyPoint {
	private String picUrl;
	private String filename;
	private String outputfile;
	private ArrayList<String> tags;

	public FindKeyPoint(String picUrl, String filename) {
		this.picUrl = picUrl;
		this.filename = filename;
	}
	
	public void findKeyPoints(){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		getImgfromURL();
		
		Mat image = Highgui.imread(outputfile);

		FacialPoints facialpoints = new FacialPoints();
		facialpoints.setKeyPoints(outputfile);
		tags = facialpoints.getTags();

		for (Rectangle rect : facialpoints.getKeyPoints()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		Highgui.imwrite(outputfile + ".png", image);
		replaceFile(outputfile + ".png", outputfile);
		
	}
	
	private void getImgfromURL(){
		outputfile = "images" + File.separator + this.filename;
		
		try {
			URL url = new URL(picUrl);
			InputStream in = new BufferedInputStream(url.openStream());
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1!=(n=in.read(buf)))
			{
			   out.write(buf, 0, n);
			}
			out.close();
			in.close();
			
			byte[] response = out.toByteArray();
			
			FileOutputStream fos = new FileOutputStream(outputfile);
			fos.write(response);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private void replaceFile(String f1s, String f2s){
		File f1 = new File(f1s);
		File f2 = new File(f2s);
		
		String f1name = f2.getAbsolutePath();
		
		
		if(f1.isFile() && f1.isFile()){
			f2.delete();
			f1.renameTo(new File(f1name));
		}
	}
	
	public void eraseOutputfile(){
		File file = new File(outputfile);
		file.delete();
		
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public String getOutputfile() {
		return outputfile;
	}

	public void setOutputfile(String outputfile) {
		this.outputfile = outputfile;
	}
}
