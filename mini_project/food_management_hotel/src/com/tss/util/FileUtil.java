package com.tss.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void writeToFile(String filename, String content) {
		try(FileWriter writer = new FileWriter(filename,true)){
			writer.write(content + "\n");
		}catch (IOException exception) {
			System.out.println("Error writing to file : " + exception.getMessage());
		}
	}
}
