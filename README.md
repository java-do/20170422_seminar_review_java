# Java DOでしょう \#10

Java 9直前！最近のJava (7,8) 復習ハンズオン

## はじめに

Java 7,8で登場したAPIや記法のうち、代表的なものをコーディングしてみましょう。

## Try-With-Resource

`com.example.handson` パッケージの中の、 `HandsOn7#TryWithResourceを使う` を書き換えてみましょう

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

`com.example.handson` パッケージの中の、 `HandsOn7#NIO2を使う` を書き換えてみましょう

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

`com.example.handson` パッケージの中の、 `HandsOn7#NIO2を使う_小さなファイル` を書き換えてみましょう

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


## Stream API


## そのほか
