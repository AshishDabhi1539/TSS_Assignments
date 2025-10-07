package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.AddressRequestDto;
import com.tss.banking.dto.response.AddressResponseDto;
import com.tss.banking.entity.Address;
import com.tss.banking.entity.enums.AddressType;

public interface AddressService {
    
    // Create address for any entity (User, Bank, Branch)
    AddressResponseDto createAddress(String entityType, Long entityId, AddressRequestDto requestDto);
    
    // Get all addresses for an entity
    List<AddressResponseDto> getAddressesByEntity(String entityType, Long entityId);
    
    // Get addresses by entity and type
    List<AddressResponseDto> getAddressesByEntityAndType(String entityType, Long entityId, AddressType addressType);
    
    // Get primary address for an entity
    AddressResponseDto getPrimaryAddress(String entityType, Long entityId);
    
    // Get primary address by entity and type
    AddressResponseDto getPrimaryAddressByType(String entityType, Long entityId, AddressType addressType);
    
    // Update address
    AddressResponseDto updateAddress(Long addressId, AddressRequestDto requestDto);
    
    // Set address as primary
    AddressResponseDto setPrimaryAddress(Long addressId);
    
    // Deactivate address (soft delete)
    void deactivateAddress(Long addressId);
    
    // Delete address permanently
    void deleteAddress(Long addressId);
    
    // Helper method to create address during entity registration
    Address createAddressForEntity(String entityType, Long entityId, AddressType addressType, 
                                 String addressLine1, String addressLine2, String city, 
                                 String state, String postalCode, String country, String landmark);
    
    // Validate address ownership
    boolean validateAddressOwnership(Long addressId, String entityType, Long entityId);
    
    // Search addresses
    List<AddressResponseDto> searchAddresses(String searchTerm);
}
