package com.tpi.gestion.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.gestion.models.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Long>{

    boolean existsByNombreIgnoreCase(String nombre);

    Page<Deposito> findByNombreContainingIgnoreCaseAndLatitudAndLongitud(
    String nombre, 
    Double latitud, 
    Double longitud, 
    Pageable pageable);
     
    Page<Deposito> findByLatitudAndLongitud(Double latitud, Double longitud, Pageable pageable);

    Page<Deposito> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    Page<Deposito> findAll(Pageable pageable);

}
