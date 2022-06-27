package com.busra.etsturproject.repository;

import com.busra.etsturproject.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {

    Optional<Storage> findByName(String name);
}
