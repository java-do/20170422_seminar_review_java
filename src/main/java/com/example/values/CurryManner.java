package com.example.values;

import java.util.Objects;

public class CurryManner {
	private final String manner;

	public CurryManner(String manner) {
		Objects.requireNonNull(manner, "manner がnull.");
		if (manner.length() > 32) {
			throw new IllegalArgumentException("manner は32文字以下.");
		}
		this.manner = manner;
	}

	@Override
	public String toString() {
		return manner;
	}
}
