package com.tss.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.MovieClass;
import com.tss.model.MovieEmptyException;
import com.tss.model.MovieFullException;

public class MovieTestClass {

	private static List<MovieClass> movies = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		File file = new File("movies.ser");

		if (file.exists()) {
			try {
				FileInputStream fileInput = new FileInputStream(file);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				movies = (List<MovieClass>) objectInput.readObject();
				objectInput.close();
				fileInput.close();
				System.out.println("Movies loaded from file.");
			} catch (IOException | ClassNotFoundException e) {
				System.out.println("Error in loading: " + e.getMessage());
			}
		} else {
			System.out.println("No movie found.");
		}

		int choice;

		do {
			System.out.println("\n--- Movie Menu ---");
			System.out.println("1. Display Movies");
			System.out.println("2. Add Movie");
			System.out.println("3. Delete Movie by ID");
			System.out.println("4. Clear All Movies");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();
			scanner.nextLine();

			try {
				switch (choice) {
				case 1:
					if (movies.isEmpty())
						throw new MovieEmptyException("Movie store is empty (no movies to display).");
					System.out.println("\nMovies in Store:");
					for (MovieClass movie : movies) {
						System.out.println(movie);
					}
					break;

				case 2:
					if (movies.size() >= 5)
						throw new MovieFullException("Movie store is full (5 movies only)");

					System.out.print("Enter Movie Name: ");
					String name = scanner.nextLine();
					System.out.print("Enter Genre: ");
					String genre = scanner.nextLine();
					System.out.print("Enter Year: ");
					int year = scanner.nextInt();
					scanner.nextLine();

					String id = generateId(name, genre, year);
					movies.add(new MovieClass(id, name, genre, year));
					System.out.println("Movie added successfully with ID: " + id);
					break;

				case 3:
					if (movies.isEmpty())
						throw new MovieEmptyException("Movie store is empty.");

					System.out.print("Enter Movie ID to delete: ");
					String deleteId = scanner.nextLine();

					boolean removed = false;
					for (int i = 0; i < movies.size(); i++) {
					    if (movies.get(i).getId().equalsIgnoreCase(deleteId)) {
					        movies.remove(i);
					        removed = true;
					        break;
					    }
					}

					if (removed) {
						System.out.println("Movie id : " + deleteId + " deleted successfully.");
					} else {
						System.out.println("Movie id : " + deleteId + " not found.");
					}
					break;

				case 4:
					movies.clear();
					System.out.println("All movies cleared successfully.");
					break;

				case 5:
					try {
						FileOutputStream fileOutput = new FileOutputStream("movies.ser");
						ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
						objectOutput.writeObject(movies);
						objectOutput.close();
						fileOutput.close();
						System.out.println("Movies saved to file. Exiting...");
					} catch (IOException e) {
						System.out.println("Error saving movies: " + e.getMessage());
					}
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
				}

			} catch (MovieFullException | MovieEmptyException e) {
				System.out.println("Error: " + e.getMessage());
			}

		} while (choice != 5);
	}

	private static String generateId(String name, String genre, int year) {
		String part1 = name.length() >= 2 ? name.substring(0, 2) : name;
		String part2 = genre.length() >= 2 ? genre.substring(0, 2) : genre;
		String part3 = String.valueOf(year).substring(2);
		return (part1 + part2 + part3).replaceAll("\\s+", "").trim();
	}

}
