package com.tss.test;

import java.io.File;

public class FileClassTest {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\ashishkumar.dabhi\\Desktop\\Ashish\\Java\\Assignments\\basics_app\\src\\com\\tss");

        if (file.exists()) {
            listOfFiles(file, "");
        } else {
            System.out.println("File or directory does not exist.");
        }
    }

    public static void listOfFiles(File file, String string) {
        if (file.isDirectory()) {
            System.out.println(string + "\nDirectory: " + file.getName());
            File[] files = file.listFiles();

            if (files != null) {
                for (File f : files) {
                    listOfFiles(f, string + "  ");
                }
            }
        } else if (file.isFile()) {
            System.out.println(string + "File: " + file.getName() + " -> Size: " + file.length() + " bytes");
        }
    }
}
