package com.tss.banking.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tss.banking.exception.BankApiException;

@Component
public class SecurityUtils {

    public static String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BankApiException("User not authenticated");
        }
        return authentication.getName();
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        }
        return authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_" + role));
    }

    public static boolean isAdmin() {
        return hasRole("ADMIN") || hasRole("SUPERADMIN");
    }

    public static boolean isSuperAdmin() {
        return hasRole("SUPERADMIN");
    }

    public static void validateUserAccess(String userEmail) {
        String currentUserEmail = getCurrentUserEmail();
        if (!isAdmin() && !currentUserEmail.equals(userEmail)) {
            throw new BankApiException("Access denied: You can only access your own data");
        }
    }

    public static void validateUserAccess(Long userId, Long currentUserId) {
        if (!isAdmin() && !userId.equals(currentUserId)) {
            throw new BankApiException("Access denied: You can only access your own data");
        }
    }
}
