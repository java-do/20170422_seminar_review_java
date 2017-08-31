package com.example.values;

public class Gender {

	private int gender;

	public Gender(int gender) {
		if (gender < 0 || gender > 3) {
			throw new IllegalArgumentException("性別は0:男性, 1:女性, 2:不明のいずれか");
		}
		this.gender = gender;
	}

	@Override
	public String toString() {
		switch (gender) {
			case 0:
				return "男性";
			case 1:
				return "女性";
			default:
				return "不明";
		}
	}
}
