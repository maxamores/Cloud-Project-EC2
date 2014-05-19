package com.eamores.cloud.aws.recognition;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.eamores.cloud.aws.detectors.EyesDetector;
import com.eamores.cloud.aws.detectors.FaceDetector;
import com.eamores.cloud.aws.detectors.LeftEarDetector;
import com.eamores.cloud.aws.detectors.MouthDetector;
import com.eamores.cloud.aws.detectors.NoseDetector;
import com.eamores.cloud.aws.detectors.ProfileDetector;
import com.eamores.cloud.aws.detectors.RightEarDetector;
import com.eamores.cloud.aws.detectors.SmileDetector;

public class FacialPoints {
	private ArrayList<Rectangle> keyPoints;
	private ArrayList<String> tags;

	public FacialPoints() {
		this.keyPoints = new ArrayList<Rectangle>();
		this.tags = new ArrayList<String>();
	}

	public ArrayList<Rectangle> getKeyPoints() {
		return keyPoints;
	}

	public ArrayList<String> getTags() {
		return tags;
	}

	public void setKeyPoints(String filename) {
		FaceDetector face = new FaceDetector(filename);
		this.keyPoints.addAll(face.getKeyPoints());
		if(face.getTotal() > 0){tags.add(FaceTags.FACE);}
		if(face.getTotal() > 1){tags.add(FaceTags.GROUP);}

		ProfileDetector profile = new ProfileDetector(filename);
		this.keyPoints.addAll(profile.getKeyPoints());
		if(profile.getTotal() > 0){tags.add(FaceTags.PROFILE);}
		
		EyesDetector eye = new EyesDetector(filename);
		this.keyPoints.addAll(eye.getKeyPoints());
		if(eye.getTotal() > 0){tags.add(FaceTags.EYES);}

		
		LeftEarDetector lear = new LeftEarDetector(filename);
		this.keyPoints.addAll(lear.getKeyPoints());
		if(lear.getTotal() > 0){tags.add(FaceTags.LEFTEAR);}

		RightEarDetector rear = new RightEarDetector(filename);
		this.keyPoints.addAll(rear.getKeyPoints());
		if(rear.getTotal() > 0){tags.add(FaceTags.RIGHTEAR);}

		MouthDetector mouth = new MouthDetector(filename);
		this.keyPoints.addAll(mouth.getKeyPoints());
		if(mouth.getTotal() > 0){tags.add(FaceTags.MOUTH);}

		NoseDetector nose = new NoseDetector(filename);
		this.keyPoints.addAll(nose.getKeyPoints());
		if(nose.getTotal() > 0){tags.add(FaceTags.NOSE);}
		
		SmileDetector smile = new SmileDetector(filename);
		this.keyPoints.addAll(smile.getKeyPoints());
		if(smile.getTotal() > 0){tags.add(FaceTags.SMILE);}
	}
}
