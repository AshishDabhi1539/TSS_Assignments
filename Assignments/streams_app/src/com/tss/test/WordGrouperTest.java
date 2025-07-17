package com.tss.test;

import java.util.List;
import java.util.Map;

import com.tss.model.WordGrouper;

public class WordGrouperTest {
    public static void main(String[] args) {
        List<String> input = List.of("apple", "banana", "apricot", "blueberry", "cherry");

        System.out.println("Original Word List: " + input);

        Map<Character, List<String>> grouped = WordGrouper.groupByFirstCharacter(input);
        System.out.println("\nGrouped by First Character:");
        grouped.forEach((ch, list) -> System.out.println(ch + " = " + list));

        Map<Character, Long> wordCountMap = WordGrouper.countWordsByFirstLetter(input);
        System.out.println("\nWord Count by First Character:");
        wordCountMap.forEach((ch, count) -> System.out.println(ch + " = " + count));
    }
}
