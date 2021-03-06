package com.il.sod.db.model.repositories;

import com.il.sod.db.model.entities.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
}