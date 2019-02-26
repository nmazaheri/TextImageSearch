package com.example;

import java.awt.*;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.detector.DetectManager;
import com.example.detector.DetectManagerImpl;
import com.example.model.PatternTextImage;
import com.example.model.TextImage;

/**
 * Main application
 */
public class Application {
	private static final String IMAGE_NAME = "image";
	private static final String PATTERN1_NAME = "pattern1";
	private static final String PATTERN2_NAME = "pattern2";
	private static final String DESTINATION_PATH = "doc/";
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		LOGGER.info("Starting");
		TextImage image = new TextImage(IMAGE_NAME);
		PatternTextImage pattern1 = new PatternTextImage(PATTERN1_NAME);
		PatternTextImage pattern2 = new PatternTextImage(PATTERN2_NAME);
		pattern2.setFilterColor(Color.PINK);
		DetectManager detectManager = new DetectManagerImpl();
		detectManager.process(Arrays.asList(pattern1, pattern2), image, DESTINATION_PATH);
		LOGGER.info("Finished");
	}

}
