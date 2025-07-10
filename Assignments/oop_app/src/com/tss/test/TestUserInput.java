package com.tss.test;

import com.tss.model.UserInput;
import java.util.Scanner;

public class TestUserInput {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username, password, email;

        while (true) {
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            if (new UserInput(username, "", "").isValidUsername()) {
                break;
            }
            System.out.println("Enter 3–20 characters.");
        }

        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (new UserInput("", password, "").isValidPassword()) {
                break;
            }
            System.out.println("Enter 8–30 characters.");
        }

        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();

            if (new UserInput("", "", email).isValidEmail()) {
                break;
            }
            System.out.println("Enter 5–50 characters, '@' also '.'.");
        }

        UserInput userInput = new UserInput(username, password, email);

        System.out.println("\nAll valid inputs are:");
        System.out.println("Username: " + userInput.getUsername());
        System.out.println("Password: " + userInput.getPassword());
        System.out.println("Email: " + userInput.getEmail());
    }
}
