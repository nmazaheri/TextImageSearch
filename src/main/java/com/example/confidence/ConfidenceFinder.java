package com.example.confidence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.ConfidenceResult;
import com.example.model.ConfidenceValue;
import com.example.model.PatternTextImage;
import com.example.model.TextImage;

/**
 *
 */
public class ConfidenceFinder {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfidenceFinder.class);

	private TextImage image;
	private ConfidenceStats confidenceStats;

	public ConfidenceFinder(TextImage image, ConfidenceStats confidenceStats) {
		this.image = image;
		this.confidenceStats = confidenceStats;
	}

	public ConfidenceResult createConfidenceArray(PatternTextImage p) {
		int height = image.getHeight() + 2 * p.getHeight();
		int width = image.getWidth() + 2 * p.getWidth();

		ConfidenceResult confidenceResult = new ConfidenceResult(width, height);
		int[][] result = confidenceResult.getArr();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				updateConfidencePoint(p, result, i, j);
			}
		}

		confidenceStats.print();
		return confidenceResult;
	}

	protected void updateConfidencePoint(PatternTextImage pattern, int[][] result, int i, int j) {
//		TextImage patternSubsection = pattern.getSubsection(i - pattern.getHalfWidth(), j - pattern.getHalfWidth(), pattern.getWidth() + i, pattern.getHeight() + j);
//		TextImage imageSubsection = image.getSubsection(i - pattern.getHalfHeight(), j - pattern.getHalfHeight(), i + pattern.getHalfWidth(), j + pattern.getHalfHeight());
//		TextImage patternSubsection = pattern.getSubsection(0, 0, pattern.getWidth(), pattern.getHeight());
//		TextImage imageSubsection = image.getSubsection(i, j, i + pattern.getWidth(), j + pattern.getHeight());
		int x1 = i - pattern.getWidth();
		int y1 = j - pattern.getHeight();
		TextImage imageSubsection = image.getSubsection(x1, y1, x1 + pattern.getWidth(), y1 + pattern.getHeight());
		TextImage patternSubsection = pattern.getSubsection(0, 0, pattern.getWidth(), pattern.getHeight());

		if (patternSubsection == null || imageSubsection == null) {
			result[i][j] = ConfidenceValue.UNKNOWN.getValue();
		} else {
			result[i][j] = calculateMatchCount(patternSubsection, imageSubsection);
		}
	}
	
	protected int calculateMatchCount(TextImage a, TextImage b) {
		int confidenceValue = 0;

		if (a.getImage().length != b.getImage().length) {
			LOGGER.warn("all comparisons must be equal size to compare");
			return confidenceValue;
		}
		for (int i = 0; i < a.getImage().length; i++) {
			if (a.getImage()[i] == b.getImage()[i]) {
				confidenceValue++;
			}
		}

		confidenceStats.update(confidenceValue);
		return confidenceValue;
	}
}
