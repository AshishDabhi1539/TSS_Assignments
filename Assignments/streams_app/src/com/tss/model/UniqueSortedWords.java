package com.tss.model;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UniqueSortedWords {
	public static List<String> getUniqueSortedWords(List<String> sentences) {
        return sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split("\\W+")))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.toCollection(TreeSet::new))
                .stream().toList();
    }
}
