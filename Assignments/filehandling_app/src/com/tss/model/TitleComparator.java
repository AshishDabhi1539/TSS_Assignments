package com.tss.model;

import java.util.Comparator;

public class TitleComparator implements Comparator<Book>{

	@Override
	public int compare(Book b1, Book b2) {
		return b2.getTitle().compareTo(b1.getTitle());
	}

}
