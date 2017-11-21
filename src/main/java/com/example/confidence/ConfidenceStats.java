package com.example.confidence;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains statistics on all confidence values
 */
public class ConfidenceStats {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfidenceStats.class);
	private SummaryStatistics stats = new SummaryStatistics();

	public static double scale(final double valueIn, final double baseMin, final double baseMax, final double limitMin, final double limitMax) {
		return ((limitMax - limitMin) * (valueIn - baseMin) / (baseMax - baseMin)) + limitMin;
	}

	public void update(int i) {
		stats.addValue(i);
	}

	public int getThreshold() {
		return (int) stats.getMean() + (int) stats.getStandardDeviation() + (int) stats.getStandardDeviation() / 3;
	}

	public int applyLinearScale(int val) {
		final double scale = scale(val, stats.getMin(), stats.getMax(), 100, 255);
		LOGGER.trace("linear scaled {} to {}", val, scale);
		return (int) scale;
	}

	public void print() {
		LOGGER.debug("threshold is {}; std dev={} with {} values", stats.getMean(), stats.getStandardDeviation(), stats.getN());
	}
}
