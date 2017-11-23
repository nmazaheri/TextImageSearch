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

	public void update(double i) {
		stats.addValue(i);
	}

	public double getThreshold() {
		return stats.getMean() + stats.getStandardDeviation();
	}

	public void print() {
		LOGGER.debug("threshold is {}; std dev={} with {} values", stats.getMean(), stats.getStandardDeviation(), stats.getN());
	}
}
