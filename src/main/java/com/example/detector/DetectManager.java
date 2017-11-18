package com.example.detector;

import java.io.IOException;
import java.util.List;

import com.example.model.PatternTextImage;
import com.example.model.TextImage;

/**
 *
 */
public interface DetectManager {
	void process(List<PatternTextImage> patterns, String writePath, TextImage image) throws IOException;
}
