package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.NotificationRequestDto;
import com.tss.banking.dto.response.NotificationResponseDto;

public interface NotificationService {
    
    NotificationResponseDto createNotification(NotificationRequestDto dto);
    
    NotificationResponseDto getNotificationById(Long id);
    
    List<NotificationResponseDto> getAllNotifications();
    
    List<NotificationResponseDto> getNotificationsByUserId(Long userId);
    
    List<NotificationResponseDto> getUnreadNotificationsByUserId(Long userId);
    
    List<NotificationResponseDto> getNotificationsByStatus(String status);
    
    NotificationResponseDto markAsRead(Long id);
    
    NotificationResponseDto updateNotification(Long id, NotificationRequestDto dto);
    
    void deleteNotification(Long id);
}
