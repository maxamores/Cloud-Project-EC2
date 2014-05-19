package com.eamores.cloud.aws.detectors;

import java.awt.Rectangle;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

import com.eamores.cloud.aws.recognition.FacialPoints;

public class DrawFacialPoints {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		String input = "pic/picshekhar.JPG";
		String output = "pic/ouput.png";

		Mat image = Highgui.imread(input);

		FacialPoints facialpoints = new FacialPoints();
		facialpoints.setKeyPoints(input);

		for (Rectangle rect : facialpoints.getKeyPoints()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		Highgui.imwrite(output, image);
	}

}
