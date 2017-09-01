package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
	public static void main(String[] args) {
		App app = new App();
	}

	public App() {
		URL profileURL = getClass().getResource("/profile.txt");
		URI uri = URI.create(profileURL.toExternalForm());
		try {
			Path path = Paths.get(uri);
			Files.lines(path)
				.map(l -> l.split(","))
				.map(CurryEater::new)
//				.filter(CurryEater::isMinor)
				.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
