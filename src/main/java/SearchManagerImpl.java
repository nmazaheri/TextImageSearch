import java.awt.*;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.MatchResult;
import model.ProcessedImage;
import model.TextImage;

/**
 *
 */
public class SearchManagerImpl {

	protected static final Logger LOGGER = LoggerFactory.getLogger(SearchManagerImpl.class);
	private TextImage image;

	public SearchManagerImpl(TextImage image) {
		if (image == null) {
			throw new IllegalArgumentException("image cannot be null");
		}
		this.image = image;
	}

	public List<ProcessedImage> search(List<TextImage> patterns, int totalResultsToReturn) {

	}

	protected TextImage extractImage(Point position, TextImage pattern) {

	}

	protected MatchResult match(TextImage actual, TextImage expected) {

	}
}
