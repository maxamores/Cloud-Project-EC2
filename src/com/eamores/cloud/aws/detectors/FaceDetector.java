package com.eamores.cloud.aws.detectors;

import java.awt.Rectangle;
import java.util.ArrayList;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetector {
	private CascadeClassifier detector;
	private String filename;
	private int total = 0;


	public FaceDetector(String filename) {
		detector = new CascadeClassifier("data/haarcascade_frontalface_default.xml");
		this.filename = filename;
	}

	public ArrayList<Rectangle> getKeyPoints() {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Mat image = Highgui.imread(filename);

		MatOfRect detections = new MatOfRect();
		detector.detectMultiScale(image, detections);

		ArrayList<Rectangle> keyPoints = new ArrayList<Rectangle>();

		for (Rect rect : detections.toArray()) {
			keyPoints.add(new Rectangle(rect.x, rect.y, rect.width, rect.height));
			total++;
		}
		
		return keyPoints;
	}
	
	public int getTotal() {
		return total;
	}
}
