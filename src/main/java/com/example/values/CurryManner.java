package com.example.values;

import java.util.Objects;

public class CurryManner {
	private final String prefecture;

	public CurryManner(String prefecture) {
		if (Objects.isNull(prefecture)) {
			throw new IllegalArgumentException("食べ方がnull");
		}
		if (prefecture.length() > 32) {
			throw new IllegalArgumentException("食べ方は32文字以下");
		}
		this.prefecture = prefecture;
	}

	@Override
	public String toString() {
		return prefecture;
	}
}
