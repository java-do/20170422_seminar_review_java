# Java DOでしょう \#10

Java 9直前！最近のJava (7,8) 復習ハンズオン

## はじめに

Java 7,8で登場したAPIや記法のうち、代表的なものをコーディングしてみましょう。

変更するファイルと模範解答は、 `main/test` フォルダの中にあります。

## Try-With-Resource

① `com.example.handson` パッケージの中の、 `HandsOn7#TryWithResourceを使う` を書き換えてみましょう。

```java
@Test
public void TryWithResourceを使う() {
  System.out.println("TryWithResourceを使う ------------");
  List<String> lines = new ArrayList<>();

  File file = new File(url.getPath());
    try (FileReader fr = new FileReader(file);
      BufferedReader bf = new BufferedReader(fr)) {
    while (true) {
      String line = bf.readLine();
      if (line == null) {
        break;
      }
      lines.add(line);
    }
  } catch (IOException e) {
    e.printStackTrace();
  }
  for (String line : lines) {
    System.out.println(line);
  }
}
```

メソッドを実行して、動作を確認しましょう。

## NIO2（Path, Paths, Files）

① `com.example.handson` パッケージの中の、 `HandsOn7#NIO2を使う` を書き換えてみましょう。

```java
@Test
public void NIO2を使う() {
  System.out.println("NIO2を使う ------------");
  List<String> lines = new ArrayList<>();
  URI uri = URI.create(url.toString());

  Path path = Paths.get(uri);
  try (BufferedReader bf = Files.newBufferedReader(path)) {
    while (true) {
      String line = bf.readLine();
      if (line == null) {
        break;
      }
      lines.add(line);
    }
  } catch (IOException e) {
    e.printStackTrace();
  }

  for (String line : lines) {
    System.out.println(line);
  }
}
```

メソッドを実行して、動作を確認しましょう。

② `com.example.handson` パッケージの中の、 `HandsOn7#NIO2を使う_小さなファイル` を書き換えてみましょう。

```java
@Test
public void NIO2を使う_小さなファイル() {
  System.out.println("NIO2を使う_ファイルサイズが小さいとき ------------");
  List<String> lines = null;
  URI uri = URI.create(url.toString());

  Path path = Paths.get(uri);
  try {
    lines = Files.readAllLines(path);
  } catch (IOException e) {
    e.printStackTrace();
  }

  for (String line : lines) {
    System.out.println(line);
  }
}
```

メソッドを実行して、動作を確認しましょう。

## DateTime API

① `com.example.handson` パッケージの中の、 `HandsOn8#今日の日付と時刻を作る` を書き換えてみましょう。

```java
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
```

メソッドを実行して、動作を確認しましょう。

② `com.example.handson` パッケージの中の、 `HandsOn8#日時の表示方法を加工する` を書き換えてみましょう。

```java
@Test
public void 日時の表示方法を加工する() {
  LocalDateTime ldt = LocalDateTime.now();
  System.out.println(ldt);

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日h時m分s秒");
  System.out.println(dtf.format(ldt));
}
```

メソッドを実行して、動作を確認しましょう。

③ `com.example.handson` パッケージの中の、 `HandsOn8#日時を操作する` を書き換えてみましょう。

```java
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
```

メソッドを実行して、動作を確認しましょう。

④ `com.example.handson` パッケージの中の、 `HandsOn8#日時の差を計算する` を書き換えてみましょう。

```java
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
```

メソッドを実行して、動作を確認しましょう。

## Stream API / ラムダ式

① `com.example.handson` パッケージの中の、 `HandsOn8#Streamとこれまでの違い` を書き換えてみましょう。

```java
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
```

メソッドを実行して、動作を確認しましょう。

② `com.example.handson` パッケージの中の、 `HandsOn8#長さが5以上の単語だけをListに集める` を書き換えてみましょう。

```java
@Test
public void 長さが5以上の単語だけをListに集める() {
  List<String> filterdList = words.stream()
    .filter(word -> word.length() >= 5)
    .collect(Collectors.toList());
  
  System.out.println(filterdList);
}
```

メソッドを実行して、動作を確認しましょう。

③ `com.example.handson` パッケージの中の、 `HandsOn8#要素を単語の長さに変えてListに集める` を書き換えてみましょう。

```java
@Test
public void 要素を単語の長さに変えてListに集める() {
  List<Integer> lengthList = words.stream()
    .map(word -> word.length())
    .collect(Collectors.toList());
  
  System.out.println(lengthList);
}
```

メソッドを実行して、動作を確認しましょう。

④ `com.example.handson` パッケージの中の、 `HandsOn8#要素を単語の長さに変えて5以上の数字だけをListに集める` を書き換えてみましょう。

```java
@Test
public void 要素を単語の長さに変えて5以上の数字だけをListに集める() {
  List<Integer> lengthList = words.stream()
    .map(word -> word.length())
    .filter(length -> length >= 5)
    .collect(Collectors.toList());
  
  System.out.println(lengthList);
}
```

メソッドを実行して、動作を確認しましょう。

⑤ `com.example.handson` パッケージの中の、 `HandsOn8#要素を文字のリストに変えてListに集める` を書き換えてみましょう。

```java
@Test
public void 要素を文字のリストに変えてListに集める() {
  List<String> chars = words.stream()
    .map(word -> word.split(""))
    .flatMap(word -> Arrays.stream(word))
    .collect(Collectors.toList());
  
  System.out.println(chars);
}
```

メソッドを実行して、動作を確認しましょう。

⑥ `com.example.handson` パッケージの中の、 `HandsOn8#単語と文字数のMapに集める` を書き換えてみましょう。

```java
@Test
public void 単語と文字数のMapに集める() {
  Map<String, Integer> map = words.stream()
    .collect(Collectors.toMap(s -> s, s -> s.length()));

  System.out.println(map);
}
```

メソッドを実行して、動作を確認しましょう。

⑦ `com.example.handson` パッケージの中の、 `HandsOn8#一番短い単語を取得する` を書き換えてみましょう。

```java
@Test
public void 一番短い単語を取得する() {
  String defaultStr = "none.";
  String ans = words.stream()
    .sorted(Comparator.comparing(String::length))
    .findFirst()
    .orElse(defaultStr);

  System.out.println(ans);
}
```

メソッドを実行して、動作を確認しましょう。

⑧ `com.example.handson` パッケージの中の、 `HandsOn8#一番長い単語を取得する` を書き換えてみましょう。

```java
@Test
public void 一番長い単語を取得する() {
  String defaultStr = "none.";
  String ans = words.stream()
    .sorted(Comparator.comparing(String::length).reversed())
    .findFirst()
    .orElse(defaultStr);

  System.out.println(ans);
}
```

メソッドを実行して、動作を確認しましょう。

⑨ `com.example.handson` パッケージの中の、 `HandsOn8#単語の長さを合計する` を書き換えてみましょう。

```java
@Test
public void 単語の長さを合計する() {
  int defaultInt = 0;
  int ans = words.stream()
    .map(word -> word.length())
    .reduce(defaultInt, (x, y) -> x + y);

  System.out.println(ans);
}
```

メソッドを実行して、動作を確認しましょう。

⑩  `com.example.handson` パッケージの中の、 `HandsOn8#異なる処理をするStreamを合成して実行する` を書き換えてみましょう。

```java
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
```

メソッドを実行して、動作を確認しましょう。

## そのほか

`com.example.ans` パッケージの中の、 `Others` に、コード例があります。

そのほか、今日の内容を使って、ファイルから読み取った内容をインスタンスにマッピングする機能の例が、`main/java` フォルダの中の `com.example.App` クラスです。