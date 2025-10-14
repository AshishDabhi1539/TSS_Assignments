package com.tss.banking.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "scheduled_job_runs", indexes = {
        @Index(name = "idx_job_name", columnList = "jobName,startedAt")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ScheduledJobRun {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 64)
    private String jobName;

    private LocalDateTime startedAt;

    private LocalDateTime finishedAt;

    @Column(length = 32)
    private String status; // SUCCESS, FAILED

    private Long processedCount;

    @Column(length = 512)
    private String error;
}


