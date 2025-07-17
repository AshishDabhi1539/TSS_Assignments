package com.tss.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WordGrouper {
	public static Map<Character, List<String>> groupByFirstCharacter(List<String> words) {
		return words.stream().collect(Collectors.groupingBy(word -> word.toLowerCase().charAt(0)));
	}

	public static Map<Character, Long> countWordsByFirstLetter(List<String> words) {
		return words.stream()
				.collect(Collectors.groupingBy(word -> word.toLowerCase().charAt(0), Collectors.counting()));
	}
}
