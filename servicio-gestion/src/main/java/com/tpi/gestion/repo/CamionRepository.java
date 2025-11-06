package com.tpi.gestion.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.gestion.models.Tramo;

public interface CamionRepository extends JpaRepository<Tramo, Long>{
    
}
