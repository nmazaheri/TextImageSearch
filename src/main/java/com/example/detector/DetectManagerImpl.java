package com.example.detector;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ImageCreator;
import com.example.confidence.ConfidenceFilter;
import com.example.confidence.ConfidenceFinder;
import com.example.confidence.ConfidenceStats;
import com.example.model.ConfidenceResult;
import com.example.model.PatternTextImage;
import com.example.model.TextImage;


/**
 *
 */
public class DetectManagerImpl implements DetectManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(DetectManagerImpl.class);

	@Override
	public void process(List<PatternTextImage> patterns, TextImage image, String writePath) throws IOException {
		for (PatternTextImage p : patterns) {
			handlePattern(image, p, writePath);
		}
	}

	private void handlePattern(TextImage image, PatternTextImage p, String writePath) throws IOException {
		ConfidenceStats confidenceStats = new ConfidenceStats();
		ConfidenceFinder confidenceFinder = new ConfidenceFinder(image, confidenceStats);
		ConfidenceResult confidenceResult = confidenceFinder.createConfidenceArray(p);
		ImageCreator imageCreator = new ImageCreator(confidenceStats, writePath);
		imageCreator.writeImage(p, confidenceResult, "original");
		LOGGER.debug(Arrays.deepToString(confidenceResult.getArr()));
		ConfidenceFilter confidenceFilter = new ConfidenceFilter(confidenceStats.getThreshold());
		confidenceFilter.enhance(confidenceResult, 4);
		confidenceFilter.filter(confidenceResult, 3, 6);
		LOGGER.debug(Arrays.deepToString(confidenceResult.getArr()));
		imageCreator.writeImage(p, confidenceResult, "final");
	}

}
