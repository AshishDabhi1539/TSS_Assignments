package com.tss.banking.entity;

import java.time.LocalDateTime;

import com.tss.banking.entity.enums.AssignmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
<<<<<<< HEAD
=======
import org.hibernate.annotations.CreationTimestamp;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "branch_assignments", indexes = {
        @Index(name = "uq_assignment_unique", columnList = "customer_id,branch_id,assignmentType", unique = true)
})
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class BranchAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private AssignmentType assignmentType;

<<<<<<< HEAD
=======
    @CreationTimestamp
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    private LocalDateTime createdAt;

    @PrePersist
    void onCreate() {
<<<<<<< HEAD
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
=======
        // handled by @CreationTimestamp
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
    }
}