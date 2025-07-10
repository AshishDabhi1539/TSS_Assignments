package com.tss.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.tss.model.Student;

public class SerializationPOC {

	public static void main(String[] args) {
		Student student = new Student("Ashish", 123);

		try (FileOutputStream fileOutput = new FileOutputStream("studentData.ser");
				ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);) {
			objectOutput.writeObject(student);

			System.out.println("Serializtion of object is done...\n");
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		try (FileInputStream fileInput = new FileInputStream("studentData.ser");
				ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {

			Student savedStudent = (Student) objectInput.readObject();
			System.out.println("Deserialization of object is Done...");
			System.out.println("Restored the detailes of student...");
			savedStudent.display();
		} catch (IOException | ClassNotFoundException exception) {
			exception.printStackTrace();
		}

	}

}
