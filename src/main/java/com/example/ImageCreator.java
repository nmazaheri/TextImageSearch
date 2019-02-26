package com.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.example.confidence.ConfidenceStats;
import com.example.model.ConfidenceResult;
import com.example.model.ConfidenceValue;
import com.example.model.PatternTextImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Renders a .png image using confidence values. Can handle unique colors using patterns and enumerated {@link ConfidenceValue}
 */
public class ImageCreator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageCreator.class);
	private static final String IMG_EXTENSION = "png";
	private ConfidenceStats stats;
	private String writePath;

	public ImageCreator(ConfidenceStats stats, String writePath) {
		this.stats = stats;
		this.writePath = writePath;
	}

	public void writeImage(PatternTextImage pattern, ConfidenceResult confidenceResult, String uniqueFileId) throws IOException {
		final BufferedImage image = createBufferedImage(confidenceResult, pattern);
		String filePath = generateImagePath(pattern.getFilepath(), uniqueFileId);
		LOGGER.info("writing image to " + filePath);
		ImageIO.write(image, IMG_EXTENSION, new File(filePath));
	}

	private String generateImagePath(String filePath, String name) {
		return writePath + filePath + "_" + name + "." + IMG_EXTENSION;
	}

	private BufferedImage createBufferedImage(ConfidenceResult confidenceResult, PatternTextImage pattern) {
		final BufferedImage image = new BufferedImage(confidenceResult.getWidth(), confidenceResult.getHeight(), BufferedImage.TYPE_INT_RGB);

		double[][] result = confidenceResult.getArr();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				final int colorVal = getColorVal(result[i][j], pattern);
				image.setRGB(i, j, colorVal);
			}
		}
		return image;
	}

	private int getColorVal(double val, PatternTextImage pattern) {
		Color color = getColor(val, pattern, stats.getThreshold());
		return color.getRGB();
	}

	private Color getColor(double val, PatternTextImage pattern, double threshold) {
		final ConfidenceValue e = ConfidenceValue.convert((int) val);
		if (e != null) {
			return pattern.getColor(e);
		}

		if (val < threshold) {
			return Color.BLACK;
		}

		int colorVal = (int) Math.round(val * 255);
		return new Color(colorVal, colorVal, 0);
	}

}
