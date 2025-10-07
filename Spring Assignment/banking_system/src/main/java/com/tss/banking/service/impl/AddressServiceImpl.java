package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tss.banking.dto.request.AddressRequestDto;
import com.tss.banking.dto.response.AddressResponseDto;
import com.tss.banking.entity.Address;
import com.tss.banking.entity.enums.AddressType;
import com.tss.banking.exception.ResourceNotFoundException;
import com.tss.banking.repository.AddressRepository;
import com.tss.banking.repository.BankRepository;
import com.tss.banking.repository.BranchRepository;
import com.tss.banking.repository.UserRepository;
import com.tss.banking.service.AddressService;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressResponseDto createAddress(String entityType, Long entityId, AddressRequestDto requestDto) {
        // Validate entity exists
        if (!validateEntity(entityType, entityId)) {
            throw new ResourceNotFoundException(entityType + " not found with id: " + entityId);
        }

        Address address = modelMapper.map(requestDto, Address.class);
        address.setEntityType(entityType.toUpperCase());
        address.setEntityId(entityId);

        // If this is set as primary, unset other primary addresses for this entity
        if (Boolean.TRUE.equals(requestDto.getIsPrimary())) {
            unsetPrimaryAddresses(entityType, entityId);
        }

        // If no primary address exists and this is the first address, make it primary
        if (!addressRepository.existsByEntityTypeAndEntityIdAndIsPrimaryTrueAndIsActiveTrue(entityType.toUpperCase(), entityId)) {
            address.setIsPrimary(true);
        }

        Address savedAddress = addressRepository.save(address);
        return mapToResponseDto(savedAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDto> getAddressesByEntity(String entityType, Long entityId) {
        List<Address> addresses = addressRepository.findByEntityTypeAndEntityIdAndIsActiveTrue(entityType.toUpperCase(), entityId);
        return addresses.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponseDto getPrimaryAddress(String entityType, Long entityId) {
        Address address = addressRepository.findByEntityTypeAndEntityIdAndIsPrimaryTrueAndIsActiveTrue(entityType.toUpperCase(), entityId)
                .orElseThrow(() -> new ResourceNotFoundException("Primary address not found for " + entityType + " with id: " + entityId));
        return mapToResponseDto(address);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDto> getAddressesByEntityAndType(String entityType, Long entityId, AddressType addressType) {
        List<Address> addresses = addressRepository.findByEntityTypeAndEntityIdAndAddressTypeAndIsActiveTrue(entityType.toUpperCase(), entityId, addressType);
        return addresses.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponseDto updateAddress(Long addressId, AddressRequestDto requestDto) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        // Update fields
        modelMapper.map(requestDto, address);

        // Handle primary address logic
        if (Boolean.TRUE.equals(requestDto.getIsPrimary())) {
            unsetPrimaryAddresses(address.getEntityType(), address.getEntityId());
            address.setIsPrimary(true);
        }

        Address updatedAddress = addressRepository.save(address);
        return mapToResponseDto(updatedAddress);
    }

    @Override
    public AddressResponseDto setPrimaryAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        // Unset other primary addresses for this entity
        unsetPrimaryAddresses(address.getEntityType(), address.getEntityId());

        // Set this address as primary
        address.setIsPrimary(true);
        Address updatedAddress = addressRepository.save(address);
        return mapToResponseDto(updatedAddress);
    }

    @Override
    public void deleteAddress(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id: " + addressId));

        // Soft delete
        address.setIsActive(false);
        addressRepository.save(address);

        // If this was the primary address, set another address as primary if available
        if (Boolean.TRUE.equals(address.getIsPrimary())) {
            List<Address> remainingAddresses = addressRepository.findByEntityTypeAndEntityIdAndIsActiveTrue(
                    address.getEntityType(), address.getEntityId());
            
            if (!remainingAddresses.isEmpty()) {
                Address newPrimary = remainingAddresses.get(0);
                newPrimary.setIsPrimary(true);
                addressRepository.save(newPrimary);
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public AddressResponseDto getPrimaryAddressByType(String entityType, Long entityId, AddressType addressType) {
        Address address = addressRepository.findByEntityTypeAndEntityIdAndAddressTypeAndIsPrimaryTrueAndIsActiveTrue(
                entityType.toUpperCase(), entityId, addressType)
                .orElseThrow(() -> new ResourceNotFoundException("Primary " + addressType + " address not found for " + entityType + " with id: " + entityId));
        return mapToResponseDto(address);
    }

    @Override
    public void deactivateAddress(Long addressId) {
        deleteAddress(addressId); // Same as soft delete
    }

    @Override
    public Address createAddressForEntity(String entityType, Long entityId, AddressType addressType, 
                                        String addressLine1, String addressLine2, String city, 
                                        String state, String postalCode, String country, String landmark) {
        Address address = new Address();
        address.setEntityType(entityType.toUpperCase());
        address.setEntityId(entityId);
        address.setAddressType(addressType);
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postalCode);
        address.setCountry(country != null ? country : "India");
        address.setLandmark(landmark);
        address.setIsActive(true);
        
        // Set as primary if no other primary address exists for this entity and type
        boolean hasPrimary = addressRepository.existsByEntityTypeAndEntityIdAndAddressTypeAndIsPrimaryTrueAndIsActiveTrue(
                entityType.toUpperCase(), entityId, addressType);
        address.setIsPrimary(!hasPrimary);
        
        return addressRepository.save(address);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateAddressOwnership(Long addressId, String entityType, Long entityId) {
        return addressRepository.findById(addressId)
                .map(address -> address.belongsTo(entityType.toUpperCase(), entityId))
                .orElse(false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressResponseDto> searchAddresses(String searchTerm) {
        List<Address> addresses = addressRepository.searchAddresses(searchTerm);
        return addresses.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean validateEntity(String entityType, Long entityId) {
        switch (entityType.toUpperCase()) {
            case "USER":
                return userRepository.existsById(entityId);
            case "BANK":
                return bankRepository.existsById(entityId);
            case "BRANCH":
                return branchRepository.existsById(entityId);
            default:
                return false;
        }
    }

    private void unsetPrimaryAddresses(String entityType, Long entityId) {
        List<Address> primaryAddresses = addressRepository.findByEntityTypeAndEntityIdAndIsActiveTrue(entityType, entityId)
                .stream()
                .filter(addr -> Boolean.TRUE.equals(addr.getIsPrimary()))
                .collect(Collectors.toList());

        for (Address addr : primaryAddresses) {
            addr.setIsPrimary(false);
            addressRepository.save(addr);
        }
    }

    private AddressResponseDto mapToResponseDto(Address address) {
        AddressResponseDto dto = modelMapper.map(address, AddressResponseDto.class);
        dto.setFormattedAddress(address.getFormattedAddress());
        return dto;
    }
}
