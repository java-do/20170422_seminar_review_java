package com.example.ans;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.Assert.assertTrue;

public class Others {

	// Java7 引数のNullチェック用メソッド
	@Test(expected = NullPointerException.class)
	public void nullチェック() {
		String str = null;
		Objects.requireNonNull(str, "strがnull");
	}

	// Java7 compare, equals の実装の容易化
	@Test
	public void オブジェクトの一致() {
		A a1 = new A("hello", "world");
		A a2 = new A("hello", "world");
		assertTrue(a1.equals(a2));
	}

	// Java7 Comparator の実装の容易化
	@Test
	public void 並べ替え() {
		List<B> bList = Arrays.asList(new B(8), new B(12), new B(10), new B(1));
		Collections.sort(bList);
		bList.stream()
			.map(b -> b.getX())
			.forEach(System.out::println);
	}

	// Java8 Stringの結合
	@Test
	public void 文字列結合() {
		List<String> strs = Arrays.asList("Hello", "Duke");
		System.out.println(String.join("_", strs));
	}

	// Java8 Optional
	@Test
	public void nullかもしれない値の取り扱い() {
		String str1 = "a";
		Optional<String> o1 = Optional.ofNullable(str1);

		String str2 = null;
		Optional<String> o2 = Optional.ofNullable(str2);

		System.out.println("------------");
		// nullじゃないときだけラムダ式が実行される
		o1.ifPresent(s -> System.out.println(s));
		o2.ifPresent(s -> System.out.println(s));

		System.out.println("------------");
		// nullの時は "nullです" を返す
		System.out.println(o1.orElse("nullです"));
		System.out.println(o2.orElse("nullです"));
	}

	@Test
	public void DateTimeからsqlパッケージのクラスに変換() {

		// LocalDateTime から java.sql.Timestamp
		LocalDateTime ldt1 = LocalDateTime.now();
		java.sql.Timestamp sqlTime = java.sql.Timestamp.valueOf(ldt1);

		// java.sql.Timestamp から LocalDateTime
		LocalDateTime ldt2 = sqlTime.toLocalDateTime();

		// LocalDate から java.sql.Date
		LocalDate date1 = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(date1);

		// java.sql.Date から LocalDate
		LocalDate date2 = sqlDate.toLocalDate();

		// LocalDate, LocalTime, LocalDateTime
		LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		LocalDate date = ldt.toLocalDate();
		LocalTime time = ldt.toLocalTime();
	}


	class A {
		private String x;
		private String y;

		public A(String x, String y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof A) {
				A other = (A) object;
				return Objects.equals(x, other.getX())
					&& Objects.equals(y, other.getY());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		public String getX() {
			return x;
		}

		public String getY() {
			return y;
		}
	}

	class B implements Comparable<B> {
		private int x;

		public B(int x) {
			this.x = x;
		}

		@Override
		public int compareTo(B other) {
			// 小さい順に並び替える
			return Integer.compare(x, other.getX());
		}

		public int getX() {
			return x;
		}
	}
}
