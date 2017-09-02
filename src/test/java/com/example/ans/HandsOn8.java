package com.example.ans;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandsOn8 {

	private List<String> words = Arrays.asList("Hello", "Hi", "Bye", "Good Night");

	@Test
	public void 今日の日付と時刻を作る() {
		// 日付の作成
		LocalDate date = LocalDate.now();
		System.out.println(date);

		// 時刻の作成
		LocalTime time = LocalTime.now();
		System.out.println(time);

		// 今年は何年？
		int year = date.getYear();
		System.out.println("今年は" + year + "年");

		// 今は何時？
		int hour = time.getHour();
		System.out.println("今は" + hour + "時");

		// 今日は何曜日？
		DayOfWeek dow = date.getDayOfWeek();
		System.out.println("今日は" + dow);
	}

	@Test
	public void 日時の表示方法を加工する() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日h時m分s秒");
		System.out.println(dtf.format(ldt));
	}

	@Test
	public void 日時を操作する() {
		// 2016年3月1日12時30分
		LocalDateTime ldt1 = LocalDateTime.of(2016, 3, 1, 12, 30);
		System.out.println(ldt1);

		// ldt1の一日前のインスタンスを取得
		LocalDateTime ldt2 = ldt1.minusDays(1);
		System.out.println("一日前は" + ldt2);

		// ldt1を2017年に上書きしたインスタンスを取得
		LocalDateTime ldt3 = ldt1.withYear(2017);
		System.out.println(ldt3);

		// ldt3の一日前のインスタンスを取得
		LocalDateTime ldt4 = ldt3.minusDays(1);
		System.out.println("一日前は" + ldt4);
	}

	@Test
	public void 日時の差を計算する() {
		LocalTime time1 = LocalTime.of(2, 48);
		LocalTime time2 = LocalTime.now();

		// time1とtime2の時間差をとる
		Duration duration = Duration.between(time1, time2);
		System.out.println(time1.toString() + "から" + duration.toMinutes() + "分経過");
		System.out.println(time1.toString() + "から" + duration.toHours() + "時間" + duration.toMinutes() % 60 + "分経過");

		// date1には、皆さんの誕生日を入れて下さい
		LocalDate date1 = LocalDate.of(1980, 8, 20);
		LocalDate date2 = LocalDate.now();

		// date1とdate2の年月差をとる
		Period period = Period.between(date1, date2);
		System.out.println(date1.toString() + "から" + period.toTotalMonths() + "ヶ月経過");
		System.out.println("あなたの年齢は" + period.toTotalMonths() / 12 + "歳");
	}


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
	public void 長さが5以上の単語だけをListに集める() {
		List<String> filterdList = words.stream()
			.filter(word -> word.length() >= 5)  // Hello, Good Night
			.collect(Collectors.toList());

		System.out.println(filterdList);
	}

	@Test
	public void 要素を単語の長さに変えてListに集める() {
		List<Integer> lengthList = words.stream()
			.map(word -> word.length()) // 5, 2, 3, 10
			.collect(Collectors.toList());

		System.out.println(lengthList);
	}

	@Test
	public void 要素を単語の長さに変えて5以上の数字だけをListに集める() {
		List<Integer> lengthList = words.stream()
			.map(word -> word.length())  // 5, 2, 3, 10
			.filter(length -> length >= 5)  // 5, 10
			.collect(Collectors.toList());

		System.out.println(lengthList);
	}

	@Test
	public void 要素を文字のリストに変えてListに集める() {
		List<String> chars = words.stream()
			.map(word -> word.split(""))  // {H, e, l, l, o}, {H, i}, ...
			.flatMap(word -> Arrays.stream(word)) // H, e, l, l, o, H, i, ...
			.collect(Collectors.toList());

		System.out.println(chars);
	}

	@Test
	public void 要素を文字のリストに変えてListに集める_別解() {
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
			.orElse(defaultStr); //もしnullだったら初期値を使う（Optional）

		System.out.println(ans);
	}

	@Test
	public void 一番長い単語を取得する() {
		String defaultStr = "none.";
		String ans = words.stream()
			.sorted(Comparator.comparing(String::length).reversed())
			.findFirst() //文字数の大きい順に並び替えたものから最初の一つ
			.orElse(defaultStr); //もしnullだったら初期値を使う（Optional）

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
