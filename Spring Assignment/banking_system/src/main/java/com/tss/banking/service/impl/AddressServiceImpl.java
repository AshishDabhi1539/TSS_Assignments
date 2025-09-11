package com.tss.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tss.banking.dto.request.AddressRequestDto;
import com.tss.banking.dto.response.AddressResponseDto;
import com.tss.banking.entity.Address;
import com.tss.banking.exception.BankApiException;
import com.tss.banking.repository.AddressRepository;
import com.tss.banking.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressResponseDto createAddress(AddressRequestDto dto) {
        Address address = modelMapper.map(dto, Address.class);
        Address savedAddress = addressRepository.save(address);
        return modelMapper.map(savedAddress, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto getAddressById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Address not found with id: " + id));
        return modelMapper.map(address, AddressResponseDto.class);
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(address -> modelMapper.map(address, AddressResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponseDto updateAddress(Long id, AddressRequestDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new BankApiException("Address not found with id: " + id));
        
        modelMapper.map(dto, address);
        Address updatedAddress = addressRepository.save(address);
        return modelMapper.map(updatedAddress, AddressResponseDto.class);
    }

    @Override
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new BankApiException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}
