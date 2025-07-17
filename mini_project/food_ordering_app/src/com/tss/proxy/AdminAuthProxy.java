package com.tss.proxy;

import com.tss.exception.AppException;
import com.tss.model.Admin;
import com.tss.util.ValidationUtil;

public class AdminAuthProxy implements AuthProxy<Admin> {

    private final Admin admin;

    public AdminAuthProxy(Admin admin) {
        this.admin = admin;
    }

    @Override
    public Admin authenticate(String username, String password) throws AppException {
        System.out.println("Warning: Attempting admin authentication for username: " + username);
        ValidationUtil.validateUsername(username);
        ValidationUtil.validatePassword(password);
        String sanitizedUsername = ValidationUtil.sanitizeInput(username);
        String sanitizedPassword = ValidationUtil.sanitizeInput(password);
        if (sanitizedUsername == null || sanitizedPassword == null) {
            System.out.println("Warning: Invalid admin credentials: empty input");
            throw new AppException("Username or password cannot be empty.");
        }
        if (!admin.getPassword().equals(sanitizedPassword)) {
            System.out.println("Warning: Admin authentication failed for username: " + sanitizedUsername);
            throw new AppException("Invalid admin credentials.");
        }
        System.out.println("Warning: Admin authentication successful for username: " + sanitizedUsername);
        return admin;
    }
}