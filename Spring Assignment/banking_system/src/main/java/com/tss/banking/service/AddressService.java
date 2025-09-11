package com.tss.banking.service;

import java.util.List;

import com.tss.banking.dto.request.AddressRequestDto;
import com.tss.banking.dto.response.AddressResponseDto;

public interface AddressService {
    
    AddressResponseDto createAddress(AddressRequestDto dto);
    
    AddressResponseDto getAddressById(Long id);
    
    List<AddressResponseDto> getAllAddresses();
    
    AddressResponseDto updateAddress(Long id, AddressRequestDto dto);
    
    void deleteAddress(Long id);
}
