package com.example.ans;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
