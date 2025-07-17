package com.tss.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailDomainExtractor {
	public static Set<String> extractDomains(List<String> emails) {
        return emails.stream()
                .filter(email -> email.contains("@"))
                .map(email -> email.substring(email.indexOf('@') + 1))
                .collect(Collectors.toSet());
    }
}
