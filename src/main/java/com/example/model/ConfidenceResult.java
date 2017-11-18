package com.example.model;

/**
 *
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
