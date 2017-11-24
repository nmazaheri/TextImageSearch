package com.example.confidence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.model.ConfidenceResult;
import com.example.model.ConfidenceValue;
import com.example.model.PatternTextImage;
import com.example.model.TextImage;

/**
 * Used to compare a patterns against an image and outputs a ConfidenceResult
 */
public class ConfidenceFinder {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfidenceFinder.class);
	public static final double MIN_PATTERN_SUBSECTION_PERCENTAGE = 0.4;

	private TextImage image;
	private ConfidenceStats confidenceStats;

	public ConfidenceFinder(TextImage image, ConfidenceStats confidenceStats) {
		this.image = image;
		this.confidenceStats = confidenceStats;
	}

	public ConfidenceResult createConfidenceArray(PatternTextImage pattern) {
		int height = image.getHeight() + pattern.getHeight();
		int width = image.getWidth() + pattern.getWidth();

		ConfidenceResult confidenceResult = new ConfidenceResult(width, height);
		double[][] result = confidenceResult.getArr();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = getConfidenceValue(pattern, i, j);
			}
		}
		confidenceStats.print();
		return confidenceResult;
	}

	protected double getConfidenceValue(PatternTextImage pattern, int i, int j) {
		int imageX1 = Math.max(i - pattern.getWidth(), 0);
		int imageY1 = Math.max(j - pattern.getHeight(), 0);

		int patternX1, patternY1, patternX2, patternY2, imageX2, imageY2;
		if (i < pattern.getWidth()) {
			// outside left quadrant
			patternX1 = pattern.getWidth() - i;
			patternX2 = pattern.getWidth();
			imageX2 = patternX2 - patternX1;
		} else if (i > image.getWidth()) {
			// outside right quadrant
			imageX2 = image.getWidth();
			patternX1 = 0;
			patternX2 = imageX2 - imageX1;
		} else {
			// inside
			imageX2 = imageX1 + pattern.getWidth();
			patternX1 = 0;
			patternX2 = pattern.getWidth();
		}

		if (j < pattern.getHeight()) {
			// top quadrant
			patternY1 = pattern.getHeight() - j;
			patternY2 = pattern.getHeight();
			imageY2 = patternY2 - patternY1;
		} else if (j > image.getHeight()) {
			// bottom quadrant
			imageY2 = image.getHeight();
			patternY1 = 0;
			patternY2 = imageY2 - imageY1;
		} else {
			// inside
			imageY2 = imageY1 + pattern.getHeight();
			patternY1 = 0;
			patternY2 = pattern.getHeight();
		}

		TextImage imageSubsection = image.getSubsection(imageX1, imageY1, imageX2, imageY2);
		TextImage patternSubsection = pattern.getSubsection(patternX1, patternY1, patternX2, patternY2);
		return calculateMatchCount(patternSubsection, imageSubsection, pattern);
	}

	protected double calculateMatchCount(TextImage a, TextImage b, PatternTextImage p) {
		int confidenceValue = 0;

		if (a.getImage().length != b.getImage().length) {
			LOGGER.warn("all comparisons must be equal size to compare");
			return confidenceValue;
		}
		if (a.getImage().length < p.getImage().length * MIN_PATTERN_SUBSECTION_PERCENTAGE) {
			return ConfidenceValue.BELOW_SUBSECTION_MIN.getValue();
		}
		for (int i = 0; i < a.getImage().length; i++) {
			if (a.getImage()[i] == b.getImage()[i]) {
				confidenceValue++;
			}
		}

		double matchPercentage = (double) confidenceValue / a.getImage().length;
		confidenceStats.update(matchPercentage);
		return matchPercentage;
	}
}
