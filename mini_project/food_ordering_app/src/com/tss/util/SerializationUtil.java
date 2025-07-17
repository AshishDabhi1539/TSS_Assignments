package com.tss.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SerializationUtil {
    /**
     * Saves an object to a file.
     * @param object The object to serialize.
     * @param filename The file to save to.
     */
    public static void saveObject(Object object, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(object);
        } catch (IOException e) {
            System.out.println("Serialization failed: " + e.getMessage());
        }
    }

    /**
     * Reads an object from a file.
     * @param filename The file to read from.
     * @param <T> The type of the object.
     * @return The deserialized object or null if not found.
     */
    @SuppressWarnings("unchecked")
    public static <T> T readObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Saves a list of objects to a file.
     * @param list The list to serialize.
     * @param filename The file to save to.
     * @param <T> The type of the objects in the list.
     */
    public static <T> void saveList(List<T> list, String filename) {
        saveObject(list, filename);
    }

    /**
     * Reads a list of objects from a file.
     * @param filename The file to read from.
     * @param <T> The type of the objects in the list.
     * @return The deserialized list or null if not found.
     */
    public static <T> List<T> readList(String filename) {
        return readObject(filename);
    }
}