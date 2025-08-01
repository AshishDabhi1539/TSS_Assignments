package com.tss.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationUtil {

	public static void saveObject(Object object, String filename) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
			oos.writeObject(object);
		} catch (IOException e) {
			System.out.println("Serialization failed: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T readObject(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			return (T) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}

	public static <T> void saveList(List<T> list, String filename) {
		saveObject(list, filename);
	}

	public static <T> List<T> readList(String filename) {
		return readObject(filename);
	}
}