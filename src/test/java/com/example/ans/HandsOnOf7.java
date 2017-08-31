package com.example.ans;

import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandsOnOf7 {

	Path makePath() throws URISyntaxException {
		// クラスパス内の profile.txt のパスを作成する
		return Paths.get(getClass().getResource("/profile.txt").toURI());
	}

	@Test
	public void test01() {
		List<String> lines = new ArrayList<>();
		// Scanner.closeを省略している
		try (Scanner in = new Scanner(makePath())) {
			while (in.hasNext()) {
				lines.add(in.next());
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		for (String line : lines) {
			System.out.println(line);
		}
	}

	@Test
	public void test02() {


	}

}
