package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Song;

public class SongTest {
	static int choice;
	static Scanner scanner = new Scanner(System.in);
	static List<Song> songs = new ArrayList<>();

	public static void main(String[] args) {

		do {
			System.out.println("----Song-Menu----");
			System.out.println("1. Add a Song");
			System.out.println("2. Remove a Song");
			System.out.println("3. Search by title");
			System.out.println("4. Display all songs");
			System.out.println("5. Shuffle playlist");
			System.out.println("6. Exit");
			System.out.print("Enter your choice : ");

			choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addSong();
				break;

			case 2:
				removeSong();
				break;

			case 3:
				searchByTitle();
				break;

			case 4:
				displaySongs();
				break;

			case 5:
				shuffleSongs();
				break;

			case 6:
				System.out.println("Exiting...");
				System.exit(0);

			}

		} while (choice != 6);
	}

	private static void shuffleSongs() {
		if (!checkList("No songs to shuffle."))
			return;

		Collections.shuffle(songs);
		System.out.println("Shuffled Playlist");
		displaySongs();
	}

	private static void displaySongs() {
		if (!checkList("No song in the playlist."))
			return;

		System.out.println("Songs in Playlist:");
		System.out.println("Title\t\tArtist\t\tDuratrion");
		System.out.println("-----\t\t------\t\t---------");
		for (Song song : songs) {
			System.out.println(song);
		}
	}

	private static void searchByTitle() {
		if (!checkList("No songs to search."))
			return;

		System.out.print("Enter Song Title : ");
		String title = scanner.nextLine();

		boolean found = false;

		for (Song song : songs) {
			if (song.getTitle().equalsIgnoreCase(title)) {
				System.out.println("Found : " + song);
				found = true;
			}
		}

		if (!found) {
			System.out.println("No song found!");
		}

	}

	private static void removeSong() {
		if (!checkList("No songs to remove."))
			return;

		System.out.print("Enter Song Title : ");
		String title = scanner.nextLine();

		System.out.print("Enter Artist Name : ");
		String artist = scanner.nextLine();

		boolean removed = false;

		for (int i = 0; i < songs.size(); i++) {
			Song song = songs.get(i);
			if (song.getTitle().equalsIgnoreCase(title) && song.getArtist().equalsIgnoreCase(artist)) {
				songs.remove(i);
				removed = true;
				System.out.println("Song removed Successfully.");
				break;
			}
		}

		if (!removed) {
			System.out.println("Song not found!");
		}
	}

	private static void addSong() {
		System.out.print("Enter Song Title : ");
		String title = scanner.nextLine();

		System.out.print("Enter Artist Name : ");
		String artist = scanner.nextLine();

		System.out.print("Enter Song Duration : ");
		String duration = scanner.nextLine();

		songs.add(new Song(title, artist, duration));
		System.out.println("Song added Successfully.");
	}

	private static boolean checkList(String message) {
		if (songs.isEmpty()) {
			System.out.println(message);
			return false;
		}
		return true;
	}
}
