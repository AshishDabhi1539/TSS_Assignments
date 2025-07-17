package com.tss.test;

import java.util.List;

import com.tss.model.UniqueSortedWords;

public class UniqueSortedWordsTest {
	public static void main(String[] args) {
        List<String> sentences = List.of("Hello world", "hello Java", "Stream API");

        List<String> result = UniqueSortedWords.getUniqueSortedWords(sentences);

        System.out.println("Unique Sorted Words: " + result);
    }
}
