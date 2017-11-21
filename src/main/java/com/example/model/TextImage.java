package com.example.model;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * an ASCII image representation used for searching through
 */
public class TextImage {

	public static final String EXTENSION = ".txt";
	private static final Logger LOGGER = LoggerFactory.getLogger(TextImage.class);
	private int width;
	private int height;
	private char[] image;

	public TextImage(String filepath) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			image = IOUtils.toCharArray(classLoader.getResourceAsStream(filepath + EXTENSION));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setWidth();
		setHeight();
	}

	public TextImage(int width, int height, char[] image) {
		this.width = width;
		this.height = height;
		this.image = image;
	}

	private void setWidth() {
		for (char c : image) {
			if (c == '\n') {
				return;
			}
			width++;
		}
	}

	private void setHeight() {
		for (char c : image) {
			if (c == '\n') {
				height++;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public TextImage getSubsection(int x1, int y1, int x2, int y2) {
		if (!isValidPoint(x1, y1) || !isValidPoint(x2, y2) || x1 > x2 || y1 > y2) {
			LOGGER.warn("invalid subsection request; [{}, {}], [{}, {}]", x1, y1, x2, y2);
			return null;
		}

		int width = Math.abs(x1 - x2);
		int height = Math.abs(y1 - y2);

		int c = 0;
		char[] subSection = new char[(1 + width) * height];
		for (int j = y1; j < y1 + height; j++) {
			for (int i = x1; i < x1 + width; i++) {
				subSection[c++] = getChar(i, j);
			}
			subSection[c++] = '\n';
		}
		return new TextImage(width, height, subSection);
	}

	public Character getChar(int x, int y) {
		if (isValidPoint(x, y)) {
			int pos = x + y * (width + 1);
			return image[pos];
		}
		throw new IllegalArgumentException("Cannot retrieve point (" + x + "," + y + ") because it is out of range");
	}

	private boolean isValidPoint(int x, int y) {
		return x >= 0 && y >= 0 && x <= width && y <= height;
	}

	public char[] getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "TextImage{" +
				"width=" + width +
				", height=" + height +
				'}';
	}
}
