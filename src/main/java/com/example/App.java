package com.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
	public static void main(String[] args) {
		App app = new App();
	}

	public App() {
		try {
			Path path = Paths.get(getClass().getResource("/profile.txt").toURI());
			Files.lines(path)
				.map(l -> l.split(","))
				.map(a -> new CurryEater(a))
//				.filter(CurryEater::isMinor)
				.forEach(System.out::println);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	}
}
