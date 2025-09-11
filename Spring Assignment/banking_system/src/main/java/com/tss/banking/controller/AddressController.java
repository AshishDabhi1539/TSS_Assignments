package com.tss.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tss.banking.dto.request.AddressRequestDto;
import com.tss.banking.dto.response.AddressResponseDto;
import com.tss.banking.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/addresses")
@Tag(name = "Address", description = "Address Management APIs")
@PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    @Operation(summary = "Create address", description = "Create a new address")
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto dto) {
        return ResponseEntity.ok(addressService.createAddress(dto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Retrieve address details by ID")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping
    @Operation(summary = "Get all addresses", description = "Retrieve all addresses")
    public ResponseEntity<List<AddressResponseDto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update address", description = "Update an existing address")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressRequestDto dto) {
        return ResponseEntity.ok(addressService.updateAddress(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete address", description = "Delete an address by ID")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
