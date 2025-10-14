package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tss.banking.entity.User;
<<<<<<< HEAD
=======
import com.tss.banking.entity.enums.RoleType;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
import com.tss.banking.entity.enums.UserStatus;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    List<User> findByStatus(UserStatus status);
<<<<<<< HEAD
=======
    List<User> findByRole(RoleType role);
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}