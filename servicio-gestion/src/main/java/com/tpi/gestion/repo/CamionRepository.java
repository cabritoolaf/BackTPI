package com.tpi.gestion.repo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.gestion.enums.EstadoCamion;
import com.tpi.gestion.models.Camion;

public interface CamionRepository extends JpaRepository<Camion, Long>{
    boolean existsByPatenteIgnoreCase(String Patente);

    Page<Camion> findByDisponibilidad(EstadoCamion disponibilidad, Pageable pageable);

    Page<Camion> findByCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
        Double minPeso, Double minVolumen, Pageable pageable
    );

    Page<Camion> findByDisponibilidadAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(
        EstadoCamion disponibilidad, 
        Double minPeso, 
        Double minVolumen, 
        Pageable pageable
    );
    
    Page<Camion> findByCapacidadPesoGreaterThanEqual(
        Double minPeso, 
        Pageable pageable
    );

    Page<Camion> findByCapacidadVolumenGreaterThanEqual(
        Double minVolumen,
        Pageable pageable
    );

    Page<Camion> findByDisponibilidadAndCapacidadPesoGreaterThanEqual(
        EstadoCamion disponibilidad,
        Double minPeso, 
        Pageable pageable
    );

    Page<Camion> findByDisponibilidadAndCapacidadVolumenGreaterThanEqual(
        EstadoCamion disponibilidad,
        Double minVolumen, 
        Pageable pageable
    );

    Page<Camion> findAll(Pageable pageable);


}
