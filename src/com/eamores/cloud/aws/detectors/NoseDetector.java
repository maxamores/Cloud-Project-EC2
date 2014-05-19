package com.eamores.cloud.aws.detectors;

import java.awt.Rectangle;
import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

public class NoseDetector {
	private CascadeClassifier detector;
	private CascadeClassifier noseDetector;
	private String filename;
	private int total = 0;


	public NoseDetector(String filename) {
		detector = new CascadeClassifier("data/haarcascade_frontalface_default.xml");
		noseDetector = new CascadeClassifier("data/haarcascade_mcs_nose.xml");
		this.filename = filename;
	}

	public ArrayList<Rectangle> getKeyPoints() {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		Mat image = Highgui.imread(filename);

		MatOfRect detections = new MatOfRect();
		detector.detectMultiScale(image, detections);
		
		MatOfRect noseDetections = new MatOfRect();
		noseDetector.detectMultiScale(image, noseDetections);

		ArrayList<Rectangle> keyPoints = new ArrayList<Rectangle>();

		for (Rect rect : detections.toArray()) {
			for (Rect rect2 : noseDetections.toArray()) {
				if (rect.contains(new Point(rect2.x, rect2.y)) && rect.contains(new Point(rect2.x + rect2.width, rect2.y + rect2.height))) {
					keyPoints.add(new Rectangle(rect2.x, rect2.y, rect2.width, rect2.height));
					total++;
				}
			}
		}
		return keyPoints;
	}
	
	public int getTotal() {
		return total;
	}
}
