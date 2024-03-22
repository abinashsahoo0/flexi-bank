package com.jtbank.backend.repository;

import com.jtbank.backend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, String> {
}
