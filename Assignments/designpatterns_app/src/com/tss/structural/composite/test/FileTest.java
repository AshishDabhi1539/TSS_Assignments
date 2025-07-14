package com.tss.structural.composite.test;

import com.tss.structural.composite.model.Directory;
import com.tss.structural.composite.model.File;
import com.tss.structural.composite.model.IFileSystem;

public class FileTest {
	public static void main(String[] args) {
		Directory movieDirectory = new Directory("Movie");

        IFileSystem border = new File("Border");
        movieDirectory.add(border);

       Directory comedyMovieDirectory = new Directory("ComedyMovie");
       File hulchul = new File("Hulchul");
        comedyMovieDirectory.add(hulchul);
        movieDirectory.add(comedyMovieDirectory);

        movieDirectory.ls();

	}
}
