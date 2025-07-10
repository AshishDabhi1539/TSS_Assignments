package com.tss.test;

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadCharWordLine {

    public static void main(String[] args) {
        int charCount = 0;
        int wordCount = 0;
        int lineCount = 0;

        try(FileReader file = new FileReader("input.txt"); Scanner scanner = new Scanner(file);) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                charCount += line.length();
                String[] words = line.split(" ");
                wordCount += words.length;
            }

            System.out.println("Lines: " + lineCount);
            System.out.println("Words: " + wordCount);
            System.out.println("Characters: " + charCount);

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }catch (IOException e) {
        	e.getStackTrace();
        }
    }
}
