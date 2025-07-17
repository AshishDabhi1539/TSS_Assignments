package com.tss.test;

import java.util.List;
import java.util.Set;

import com.tss.model.EmailDomainExtractor;

public class EmailDomainExtractorTest {

	public static void main(String[] args) {
        List<String> emails = List.of(
            "ashish@gmail.com",
            "deep@yahoo.com",
            "tushar@gmail.com",
            "dishant@outlook.com",
            "ashish@ashish.com"
        );

        System.out.println("Original Email List: " + emails);

        Set<String> domainList = EmailDomainExtractor.extractDomains(emails);

        System.out.println("\nExtracted Unique Domains:");
        domainList.forEach(System.out::println);
    }

}
