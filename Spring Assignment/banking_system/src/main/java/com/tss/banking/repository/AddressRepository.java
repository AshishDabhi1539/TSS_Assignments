package com.tss.banking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.Address;
import com.tss.banking.entity.enums.AddressType;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    // Find all addresses for a specific entity
    List<Address> findByEntityTypeAndEntityIdAndIsActiveTrue(String entityType, Long entityId);
    
    // Find addresses by entity and address type
    List<Address> findByEntityTypeAndEntityIdAndAddressTypeAndIsActiveTrue(
        String entityType, Long entityId, AddressType addressType);
    
    // Find primary address for an entity
    Optional<Address> findByEntityTypeAndEntityIdAndIsPrimaryTrueAndIsActiveTrue(
        String entityType, Long entityId);
    
    // Find primary address by entity and type
    Optional<Address> findByEntityTypeAndEntityIdAndAddressTypeAndIsPrimaryTrueAndIsActiveTrue(
        String entityType, Long entityId, AddressType addressType);
    
    // Check if primary address exists for entity and type
    boolean existsByEntityTypeAndEntityIdAndAddressTypeAndIsPrimaryTrueAndIsActiveTrue(
        String entityType, Long entityId, AddressType addressType);
    
    // Check if primary address exists for entity (any type)
    boolean existsByEntityTypeAndEntityIdAndIsPrimaryTrueAndIsActiveTrue(
        String entityType, Long entityId);
    
    // Count active addresses for an entity
    long countByEntityTypeAndEntityIdAndIsActiveTrue(String entityType, Long entityId);
    
    // Find all addresses for multiple entities
    @Query("SELECT a FROM Address a WHERE a.entityType = :entityType AND a.entityId IN :entityIds AND a.isActive = true")
    List<Address> findByEntityTypeAndEntityIdIn(@Param("entityType") String entityType, @Param("entityIds") List<Long> entityIds);
    
    // Find addresses by city for analytics
    List<Address> findByCityAndIsActiveTrue(String city);
    
    // Find addresses by state for analytics
    List<Address> findByStateAndIsActiveTrue(String state);
    
    // Custom query to find addresses with formatted search
    @Query("SELECT a FROM Address a WHERE a.isActive = true AND " +
           "(LOWER(a.addressLine1) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.city) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.state) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(a.postalCode) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<Address> searchAddresses(@Param("searchTerm") String searchTerm);
}
