package com.example.model;

import java.awt.*;

/**
 * an ASCII image representation used for patterns. Contains colors for image rendering.
 */
public class PatternTextImage extends TextImage {
	private static final Color UNKNOWN_COLOR = Color.GRAY;
	private Color filterColor = Color.RED;
	private Color enhanceColor = Color.WHITE;
	private String filepath;

	public PatternTextImage(String filepath) throws Exception {
		super(filepath);
		this.filepath = filepath;
	}

	public void setFilterColor(Color filterColor) {
		this.filterColor = filterColor;
	}

	public void setEnhanceColor(Color enhanceColor) {
		this.enhanceColor = enhanceColor;
	}

	public String getFilepath() {
		return filepath;
	}

	public Color getColor(ConfidenceValue valueEnum) {
		switch (valueEnum) {
			case CLEAN_NOISE:
				return filterColor;
			case BEST_MATCH:
				return enhanceColor;
			case UNKNOWN:
			case BELOW_SUBSECTION_MIN:
				return UNKNOWN_COLOR;
		}
		return null;
	}
}
