package com.example.model;

/**
 *
 */
public enum ConfidenceValue {
	UNKNOWN(-1),
	CLEAN_NOISE(-2),
	BEST_MATCH(-3);

	private final int value;

	ConfidenceValue(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ConfidenceValue convert(int val) {
		for (ConfidenceValue e : ConfidenceValue.values()) {
			if (val == e.getValue()) {
				return e;
			}
		}
		return null;
	}
}
