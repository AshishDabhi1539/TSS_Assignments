package com.tss.banking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.NotificationRequestDto;
import com.tss.banking.dto.response.NotificationResponseDto;
import com.tss.banking.entity.Notification;
import com.tss.banking.entity.User;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.NotificationRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new BankApiException("User not found with id: " + dto.getUserId()));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationType(dto.getNotificationType());
        notification.setTitle(dto.getTitle());
        notification.setMessage(dto.getMessage());
        notification.setChannel(dto.getChannel());
        notification.setStatus("SENT");
        notification.setMetadata(dto.getMetadata());
        notification.setSentAt(LocalDateTime.now());

        Notification savedNotification = notificationRepository.save(notification);
        return mapToResponseDto(savedNotification);
    }

    @Override
    public NotificationResponseDto getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Notification not found with id: " + id));
        return mapToResponseDto(notification);
    }

    @Override
    public List<NotificationResponseDto> getAllNotifications() {
        return notificationRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> getNotificationsByUserId(Long userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> getUnreadNotificationsByUserId(Long userId) {
        return notificationRepository.findUnreadByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDto> getNotificationsByStatus(String status) {
        return notificationRepository.findByStatusOrderByCreatedAtDesc(status).stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDto markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Notification not found with id: " + id));

        notification.setReadAt(LocalDateTime.now());
        Notification updatedNotification = notificationRepository.save(notification);
        return mapToResponseDto(updatedNotification);
    }

    @Override
    public NotificationResponseDto updateNotification(Long id, NotificationRequestDto dto) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Notification not found with id: " + id));

        if (dto.getNotificationType() != null) {
            notification.setNotificationType(dto.getNotificationType());
        }
        if (dto.getTitle() != null) {
            notification.setTitle(dto.getTitle());
        }
        if (dto.getMessage() != null) {
            notification.setMessage(dto.getMessage());
        }
        if (dto.getChannel() != null) {
            notification.setChannel(dto.getChannel());
        }
        if (dto.getMetadata() != null) {
            notification.setMetadata(dto.getMetadata());
        }

        Notification updatedNotification = notificationRepository.save(notification);
        return mapToResponseDto(updatedNotification);
    }

    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new BankApiException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
    }

    private NotificationResponseDto mapToResponseDto(Notification notification) {
        NotificationResponseDto dto = new NotificationResponseDto();
        dto.setId(notification.getId());
        dto.setUserId(notification.getUser().getId());
        dto.setNotificationType(notification.getNotificationType());
        dto.setTitle(notification.getTitle());
        dto.setMessage(notification.getMessage());
        dto.setChannel(notification.getChannel());
        dto.setStatus(notification.getStatus());
        dto.setMetadata(notification.getMetadata());
        dto.setSentAt(notification.getSentAt());
        dto.setReadAt(notification.getReadAt());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setUpdatedAt(notification.getUpdatedAt());
        return dto;
    }
}
