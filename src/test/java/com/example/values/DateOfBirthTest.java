package com.example.values;

import org.junit.Test;

import java.time.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DateOfBirthTest {

	@Test(expected = IllegalArgumentException.class)
	public void 年が小さすぎる() {
		new DateOfBirth(1898, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 年が大きすぎる() {
		int overYear = LocalDate.now().getYear() + 1;
		new DateOfBirth(overYear, 1, 1);
	}

	@Test(expected = DateTimeException.class)
	public void 月が小さすぎる() {
		new DateOfBirth(2000, 0, 1);
	}

	@Test(expected = DateTimeException.class)
	public void 月が大きすぎる() {
		new DateOfBirth(2000, 13, 1);
	}

	@Test(expected = DateTimeException.class)
	public void 日が小さすぎる() {
		new DateOfBirth(2000, 1, 0);
	}

	@Test(expected = DateTimeException.class)
	public void 日が大きすぎる() {
		new DateOfBirth(2000, 1, 32);
	}

	@Test(expected = DateTimeException.class)
	public void うるう年じゃないのに2月29日() {
		new DateOfBirth(2001, 2, 29);
	}

	@Test
	public void うるう年に2月29日() {
		new DateOfBirth(2000, 2, 29);
	}

	@Test
	public void AC2017年8月20日のときAC1980年8月20日生まれは37歳() {
		DateOfBirth sut = new DateOfBirth(1980, 8, 20) {
			@Override
			protected Clock getClock() {
				// テストのためにシステム時刻を固定
				return Clock.fixed(Instant.parse("2017-08-20T00:00:00Z"), ZoneId.of("Asia/Tokyo"));
			}
		};
		int actual = sut.howOld();
		int expected = 37;
		assertThat(actual, is(expected));
	}

	@Test
	public void AC2017年8月19日のときAC1980年8月20日生まれは36歳() {
		DateOfBirth sut = new DateOfBirth(1980, 8, 20) {
			@Override
			protected Clock getClock() {
				// テストのためにシステム時刻を固定
				return Clock.fixed(Instant.parse("2017-08-19T00:00:00Z"), ZoneId.of("Asia/Tokyo"));
			}
		};
		int actual = sut.howOld();
		int expected = 36;
		assertThat(actual, is(expected));
	}


}
