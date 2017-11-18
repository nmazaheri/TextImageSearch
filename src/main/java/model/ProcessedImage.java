package model;

import model.MatchResult;
import model.TextImage;

/**
 *
 */
public class ProcessedImage {
	private TextImage image;
	private MatchResult matchResult;

	public ProcessedImage(TextImage image, MatchResult matchResult) {
		this.image = image;
		this.matchResult = matchResult;
	}

	public TextImage getImage() {
		return image;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}
}
