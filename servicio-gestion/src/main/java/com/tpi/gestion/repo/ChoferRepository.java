package com.tpi.gestion.repo;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.gestion.models.Chofer;


public interface ChoferRepository extends JpaRepository<Chofer, Long> {

    boolean existsByDni(long dni);

    Optional<Chofer> findByDni(long dni);

    Page<Chofer> findAll(Pageable page);     
}
