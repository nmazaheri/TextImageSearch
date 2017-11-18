package model;

import model.Match;

/**
 *
 */
public class MatchResult {
	private int matchCount;
	private int unknownCount;
	private int noMatchCount;
	private int total;

	/**
	 * Increments enum by 1
	 * @param match
	 */
	public void increment(Match match) {
		increment(match, 1);
	}

	public void increment(Match match, int amount) {
		total += amount;
		switch (match) {
			case YES:
				matchCount += amount;
				return;
			case NO:
				noMatchCount += amount;
				return;
			case UNKNOWN:
				unknownCount += amount;
				return;
		}
	}

	public double getMatchPercentage() {
		return (double) matchCount/total;
	}

	public double getNoMatchPercentage() {
		return (double) noMatchCount/total;
	}

	public double getUnknownPercentage() {
		return (double) unknownCount/total;
	}
}
