package com.tss.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileReadTest {

	public static void main(String[] args) {

		try (FileReader reader = new FileReader("input.txt"); FileWriter writter = new FileWriter("output.txt");) {
			
			
			int ch;
			
			
			/*while((ch = reader.read()) != -1) {
				System.out.print((char)ch);
			}*/
			
			while((ch = reader.read()) != -1) {
				
				writter.write(ch);
			}
			System.out.println("File copied successfully...");
		}catch(FileNotFoundException exception) {
			exception.getStackTrace();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

}
