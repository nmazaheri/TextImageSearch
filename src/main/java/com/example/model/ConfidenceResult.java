package com.example.model;

/**
 * Contains confidence values where each coordinate represents a pixel location and the value represents how good of a
 * match it is
 */
public class ConfidenceResult {
	private int[][] arr;
	private int width;
	private int height;

	public ConfidenceResult(int width, int height) {
		this.arr = new int[width][height];
		this.width = width;
		this.height = height;
	}

	public int[][] getArr() {
		return arr;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
