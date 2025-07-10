package com.tss.model;

public class UserInput {
    private String username;
    private String password;
    private String email;

    public UserInput(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean isValidUsername() {
        return isValidLength(username, 3, 20);
    }

    public boolean isValidPassword() {
        return isValidLength(password, 8, 30);
    }

    public boolean isValidEmail() {
        return isValidLength(email, 5, 50)
            && email.contains("@") && email.contains(".");
    }

    private boolean isValidLength(String value, int min, int max) {
        return value != null && !value.isEmpty() && value.length() >= min && value.length() <= max;
    }
}