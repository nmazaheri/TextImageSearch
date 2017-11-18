package com.example.confidence;

import com.example.model.ConfidenceResult;
import com.example.model.ConfidenceValue;

/**
 *
 */
public class ConfidenceFilter {
	private Integer threshold;

	public ConfidenceFilter(int threshold) {
		this.threshold = threshold;
	}

	public void filter(ConfidenceResult confidenceResult, int numOfPass, int activeNeighbors) {
		if (numOfPass < 1 || threshold == null) {
			return;
		}
		for (int i = 0; i < numOfPass; i++) {
			removeLonePoints(confidenceResult, activeNeighbors);
		}
	}

	private void removeLonePoints(ConfidenceResult confidenceResult, int minActiveNeighbors) {
		final int[][] result = confidenceResult.getArr();
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

		final int[][] result = confidenceResult.getArr();
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
