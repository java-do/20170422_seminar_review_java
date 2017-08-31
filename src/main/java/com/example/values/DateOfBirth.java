package com.example.values;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateOfBirth {

	private static final Clock DEFAULT_CLOCK = Clock.systemDefaultZone();

	private static final int MIN_YEAR = 1900;

	private LocalDate localDate;

	public DateOfBirth(int year, int month, int day) {
		LocalDate now = LocalDate.now(getClock());
		if (year < MIN_YEAR || year > now.getYear()) {
			throw new IllegalArgumentException("生年月日の年は" + MIN_YEAR + "〜今年まで");
		}
		// 他のチェックはデフォルトのバリデーションに任せる
		this.localDate = LocalDate.of(year, month, day);
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
		return dtf.format(localDate);
	}

	public int howOld() {
		// 生まれた日からのトータル月数を12で割れば年齢が出る
		Period diff = localDate.until(LocalDate.now(getClock()));
		long age = diff.toTotalMonths() / 12;
		return (int) age;
	}

	public boolean isMinor() {
		return howOld() < 20;
	}

	protected Clock getClock() {
		// テスト用に、日付ソースを外部から固定できるようにする
		return DEFAULT_CLOCK;
	}

}
