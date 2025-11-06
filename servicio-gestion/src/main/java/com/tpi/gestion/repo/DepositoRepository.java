package com.tpi.gestion.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.gestion.models.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long>{

    Page<Deposito> findAll(Specification<Deposito> spec, Pageable pageable);

    boolean existsByNombreIgnoreCase(String nombre);
     
}
