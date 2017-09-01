package com.example.ans;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandsOn8 {

	private List<String> words = Arrays.asList("Hello", "Hi", "Bye", "Good Night");


	@Test
	public void Streamとこれまでの違い() {
		List<Integer> nums = Arrays.asList(22, 10, 19, 38);

		for (Integer i : nums) {
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}

		nums.stream()
			.filter(i -> i % 2 == 0)
			.forEach(i -> System.out.println(i));
	}

	@Test
	public void 単語の長さが5以上のものだけをListに集める() {
		List<String> filterdList = words.stream()
			.filter(word -> word.length() >= 5)
			.collect(Collectors.toList());
		System.out.println(filterdList);
	}

	@Test
	public void 単語の長さのリストに変えてListに集める() {
		List<Integer> lengthList = words.stream()
			.map(word -> word.length())
			.collect(Collectors.toList());
		System.out.println(lengthList);
	}

	@Test
	public void 単語の長さのリストに変えて5文字以上のものだけをListに集める() {
		List<Integer> lengthList = words.stream()
			.map(word -> word.length())
			.collect(Collectors.toList());
		System.out.println(lengthList);
	}

	@Test
	public void 文字のリストに変えてListに集める() {
		List<String> chars = words.stream()
			.map(word -> word.split(""))
			.flatMap(word -> Arrays.stream(word))
			.collect(Collectors.toList());
		System.out.println(chars);
	}

	@Test
	public void 文字のリストに変えてListに集める_別解() {
		List<Character> chars = words.stream()
			.flatMapToInt(String::chars)
			.mapToObj(c -> ((char) c))
			.collect(Collectors.toList());
		System.out.println(chars);
	}

	@Test
	public void 単語と文字数のMapに集める() {
		Map<String, Integer> map = words.stream()
			.collect(Collectors.toMap(s -> s, s -> s.length()));
		System.out.println(map);
	}

	@Test
	public void 一番短い単語を取得する() {
		String defaultStr = "none.";
		String ans = words.stream()
			.sorted(Comparator.comparing(String::length))
			.findFirst() //文字数の小さい順に並び替えたものから最初の一つ
			.orElse(defaultStr); //もしnullだったら初期値を使う
		System.out.println(ans);
	}

	@Test
	public void 一番長い単語を取得する() {
		String defaultStr = "none.";
		String ans = words.stream()
			.sorted(Comparator.comparing(String::length).reversed())
			.findFirst() //文字数の大きい順に並び替えたものから最初の一つ
			.orElse(defaultStr); //もしnullだったら初期値を使う
		System.out.println(ans);
	}

	@Test
	public void 単語の長さを合計する() {
		int defaultInt = 0;
		int ans = words.stream()
			.map(word -> word.length())
			.reduce(defaultInt, (x, y) -> x + y); // 0+5, 5+2, 7+3...
		System.out.println(ans);
	}


	@Test
	public void 異なる処理をするStreamを合成して実行する() {
		Stream<String> s1 = words.stream()
			.filter(word -> word.length() >= 5)
			.map(String::toUpperCase);

		Stream<String> s2 = words.stream()
			.filter(word -> word.length() < 5)
			.map(String::toLowerCase);

		// ↑ どんなStreamを用意すればいいのか宣言のみ行われる

		List<String> ans = Stream.concat(s1, s2)
			.collect(Collectors.toList());

		// ↑　終端処理(collect)でs1, s2の内容がすべて実行される

		System.out.println(ans);
	}

}
