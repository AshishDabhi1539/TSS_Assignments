package com.tss.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tss.banking.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
