package com.example.ans;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class HandsOn7 {

	// クラスパスのprofile.txt
	private URL url = getClass().getResource("/profile.txt");

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

	@Test
	public void NIO2を使う() {
		System.out.println("NIO2を使う ------------");
		List<String> lines = new ArrayList<>();
		URI uri = URI.create(url.toString());

		Path path = Paths.get(uri);
		// ↓ 第2引数で文字コードも指定できる
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

}
