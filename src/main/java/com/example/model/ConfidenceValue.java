package com.example.model;

/**
 * Unique confidence values used for creating the final image
 */
public enum ConfidenceValue {
	UNKNOWN(-1),
	CLEAN_NOISE(-2),
	BEST_MATCH(-3),
	BELOW_SUBSECTION_MIN(-4);

	private final int value;

	ConfidenceValue(int value) {
		this.value = value;
	}

	public static ConfidenceValue convert(int val) {
		for (ConfidenceValue e : ConfidenceValue.values()) {
			if (val == e.getValue()) {
				return e;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}
}
