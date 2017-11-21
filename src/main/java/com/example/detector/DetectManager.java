package com.example.detector;

import java.io.IOException;
import java.util.List;

import com.example.model.PatternTextImage;
import com.example.model.TextImage;

/**
 * Responsible for image recognition and image output
 */
public interface DetectManager {
	/**
	 * Detects patterns in an image and creates a heatmap image output representing the hot spots
	 *
	 * @param patterns  List of PatternTextImage
	 * @param image     TextImage to search through
	 * @param writePath String representing file path to write to
	 * @throws IOException
	 */
	void process(List<PatternTextImage> patterns, TextImage image, String writePath) throws IOException;
}
