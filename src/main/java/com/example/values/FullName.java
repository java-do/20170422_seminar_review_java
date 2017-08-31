package com.example.values;

import java.util.Objects;

public class FullName {
	private final String fullName;

	public FullName(String name) {
		if (Objects.isNull(name)) {
			throw new IllegalArgumentException("氏名がnull");
		}
		if (name.length() > 32) {
			throw new IllegalArgumentException("氏名は32文字以下");
		}
		this.fullName = name;
	}

	@Override
	public String toString() {
		return fullName + "さん";
	}
}
