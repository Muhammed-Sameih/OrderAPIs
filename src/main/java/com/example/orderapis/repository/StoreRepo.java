package com.example.orderapis.repository;

import com.example.orderapis.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Long> {
    Optional<Store> findByCode(String code);
}
