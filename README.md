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
  // 第2引数で文字コードも指定できる
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

③ `com.example.handson` パッケージの中の、 `HandsOn8#要素を単語の長さに変えて5以上の数字だけをListに集める` を書き換えてみましょう。

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

④ `com.example.handson` パッケージの中の、 `HandsOn8#要素を文字のリストに変えてListに集める` を書き換えてみましょう。

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

⑤ `com.example.handson` パッケージの中の、 `HandsOn8#単語と文字数のMapに集める` を書き換えてみましょう。

```java
@Test
public void 単語と文字数のMapに集める() {
  Map<String, Integer> map = words.stream()
    .collect(Collectors.toMap(s -> s, s -> s.length()));

  System.out.println(map);
}
```

メソッドを実行して、動作を確認しましょう。

⑥ `com.example.handson` パッケージの中の、 `HandsOn8#一番短い単語を取得する` を書き換えてみましょう。

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

⑦ `com.example.handson` パッケージの中の、 `HandsOn8#一番長い単語を取得する` を書き換えてみましょう。

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

⑧ `com.example.handson` パッケージの中の、 `HandsOn8#単語の長さを合計する` を書き換えてみましょう。

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

⑨  `com.example.handson` パッケージの中の、 `HandsOn8#異なる処理をするStreamを合成して実行する` を書き換えてみましょう。

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