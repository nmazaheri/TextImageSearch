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

/**
 *
 */
public class ImageCreator {
	public static final String BMP_EXTENSION = "bmp";
	private ConfidenceStats stats;
	private String writePath;

	public ImageCreator(ConfidenceStats stats, String writePath) {
		this.stats = stats;
		this.writePath = writePath;
	}

	public void writeImage(PatternTextImage pattern, ConfidenceResult confidenceResult, String uniqueFileId) throws IOException {
		final BufferedImage image = createBufferedImage(confidenceResult, pattern);
		String filePath = generateImagePath(pattern.getFilepath(), uniqueFileId);
		ImageIO.write(image, BMP_EXTENSION, new File(filePath));
	}

	private String generateImagePath(String filePath, String name) {
		return writePath + filePath + "_" + name + "." + BMP_EXTENSION;
	}

	private BufferedImage createBufferedImage(ConfidenceResult confidenceResult, PatternTextImage p) {
		final BufferedImage image = new BufferedImage(confidenceResult.getWidth(), confidenceResult.getHeight(), BufferedImage.TYPE_INT_RGB);

		int[][] result = confidenceResult.getArr();
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				final int val = result[i][j];
				image.setRGB(i, j, getColorVal(val, p));
			}
		}

		return image;
	}

	private int getColorVal(int val, PatternTextImage p) {
		Color color = getColor(val, p, stats.getThreshold());
		return color.getRGB();
	}

	private Color getColor(int val, PatternTextImage p, int threshold) {
		final ConfidenceValue e = ConfidenceValue.convert(val);
		if (e != null) {
			return p.getColor(e);
		}

		if (val < threshold) {
			return Color.BLACK;
		}

		int colorVal = stats.applyLinearScale(val);
		return new Color(colorVal, colorVal, 0);

	}

}
