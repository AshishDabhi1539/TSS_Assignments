package com.tss.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.NotificationRequestDto;
import com.tss.banking.dto.response.NotificationResponseDto;
import com.tss.banking.entity.User;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.NotificationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification", description = "Notification Management APIs")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Operation(summary = "Create notification", description = "Create a new notification")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<NotificationResponseDto> createNotification(@Valid @RequestBody NotificationRequestDto dto) {
        return ResponseEntity.ok(notificationService.createNotification(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get notification by ID", description = "Retrieve notification details by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<NotificationResponseDto> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @GetMapping
    @Operation(summary = "Get all notifications", description = "Retrieve all notifications")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/my-notifications")
    @Operation(summary = "Get my notifications", description = "Get notifications for the authenticated user")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<List<NotificationResponseDto>> getMyNotifications(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(user.getId()));
    }

    @GetMapping("/my-notifications/unread")
    @Operation(summary = "Get my unread notifications", description = "Get unread notifications for the authenticated user")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<List<NotificationResponseDto>> getMyUnreadNotifications(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElseThrow();
        return ResponseEntity.ok(notificationService.getUnreadNotificationsByUserId(user.getId()));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get notifications by user", description = "Retrieve notifications for a specific user")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUserId(userId));
    }

    @GetMapping("/status")
    @Operation(summary = "Get notifications by status", description = "Retrieve notifications by status")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsByStatus(@RequestParam String status) {
        return ResponseEntity.ok(notificationService.getNotificationsByStatus(status));
    }

    @PutMapping("/{id}/mark-read")
    @Operation(summary = "Mark notification as read", description = "Mark a notification as read")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN', 'CUSTOMER')")
    public ResponseEntity<NotificationResponseDto> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.markAsRead(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update notification", description = "Update an existing notification")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<NotificationResponseDto> updateNotification(@PathVariable Long id, @Valid @RequestBody NotificationRequestDto dto) {
        return ResponseEntity.ok(notificationService.updateNotification(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete notification", description = "Delete a notification by ID")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
