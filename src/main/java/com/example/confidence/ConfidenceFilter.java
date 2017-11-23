package com.example.confidence;

import com.example.model.ConfidenceResult;
import com.example.model.ConfidenceValue;

/**
 * Identifies lone points and points with lots of neighbors. Capable of doing multiple passes
 */
public class ConfidenceFilter {
	private Double threshold;

	public ConfidenceFilter(double threshold) {
		this.threshold = threshold;
	}

	public void filter(ConfidenceResult confidenceResult, int numOfPass, int minActiveNeighbors) {
		if (numOfPass < 1 || threshold == null || minActiveNeighbors < 1 || minActiveNeighbors > 8) {
			return;
		}
		for (int i = 0; i < numOfPass; i++) {
			removeLonePoints(confidenceResult, minActiveNeighbors);
		}
	}

	private void removeLonePoints(ConfidenceResult confidenceResult, int minActiveNeighbors) {
		final double[][] result = confidenceResult.getArr();
		for (int i = 1; i < result.length - 1; i++) {
			for (int j = 1; j < result[i].length - 1; j++) {

				if (result[i][j] < threshold) {
					continue;
				}
				int currentNeighbors = 0;
				if (result[i - 1][j] < threshold) {
					currentNeighbors++;
				}
				if (result[i - 1][j + 1] < threshold) {
					currentNeighbors++;
				}
				if (result[i - 1][j - 1] < threshold) {
					currentNeighbors++;
				}
				if (result[i][j + 1] < threshold) {
					currentNeighbors++;
				}
				if (result[i][j - 1] < threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j + 1] < threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j] < threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j - 1] < threshold) {
					currentNeighbors++;
				}
				if (currentNeighbors > minActiveNeighbors) {
					result[i][j] = ConfidenceValue.CLEAN_NOISE.getValue();
				}
			}
		}
	}

	public void enhance(ConfidenceResult confidenceResult, int minActiveNeighbors) {
		if (threshold == null) {
			return;
		}

		final double[][] result = confidenceResult.getArr();
		for (int i = 1; i < result.length - 1; i++) {
			for (int j = 1; j < result[i].length - 1; j++) {

				if (result[i][j] < threshold) {
					continue;
				}
				int currentNeighbors = 0;
				if (result[i - 1][j] > threshold) {
					currentNeighbors++;
				}
				if (result[i - 1][j + 1] > threshold) {
					currentNeighbors++;
				}
				if (result[i - 1][j - 1] > threshold) {
					currentNeighbors++;
				}
				if (result[i][j + 1] > threshold) {
					currentNeighbors++;
				}
				if (result[i][j - 1] > threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j + 1] > threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j] > threshold) {
					currentNeighbors++;
				}
				if (result[i + 1][j - 1] > threshold) {
					currentNeighbors++;
				}

				if (currentNeighbors > minActiveNeighbors) {
					result[i][j] = ConfidenceValue.BEST_MATCH.getValue();
				}
			}
		}
	}

}
