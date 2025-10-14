package com.tss.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.KYCDocument;

@Repository
public interface KYCDocumentRepository extends JpaRepository<KYCDocument, Long> {
    
    @Query("SELECT k FROM KYCDocument k WHERE k.customer.id = :customerId")
    List<KYCDocument> findByCustomerId(@Param("customerId") Long customerId);
    
    @Query("SELECT k FROM KYCDocument k WHERE k.customer.id = :customerId AND k.status = :status")
    List<KYCDocument> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status);
    
    @Query("SELECT k FROM KYCDocument k WHERE k.status = :status")
    List<KYCDocument> findByStatus(@Param("status") String status);
}
