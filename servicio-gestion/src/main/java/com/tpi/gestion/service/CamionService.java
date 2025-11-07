package com.tpi.gestion.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tpi.gestion.DTO.CamionCreationDTO;
import com.tpi.gestion.DTO.CamionResponseDTO;
import com.tpi.gestion.enums.EstadoCamion;
import com.tpi.gestion.models.Camion;
import com.tpi.gestion.models.Chofer;
import com.tpi.gestion.repo.CamionRepository;

import jakarta.el.ELException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CamionService {
    
    private final CamionRepository camionRepository;
    private final ChoferService choferService;

    private CamionResponseDTO mapToReponseDTO(Camion camion){
            return new CamionResponseDTO(
                camion.getCamionId(),
                camion.getPatente(),
                camion.getChofer().getChoferId(),
                camion.getCapacidadVolumen(),
                camion.getCapacidadPeso(),
                camion.getDisponibilidad(),
                camion.getCosto_x_km(),
                camion.getConsumo()
            );
    }

    @Transactional
    public CamionResponseDTO crearNuevoCamion(CamionCreationDTO camionDTO){
        if(camionRepository.existsByPatenteIgnoreCase(camionDTO.patente())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Optional<Chofer> chofer = choferService.devolverChoferPorId(camionDTO.choferId());
        if (chofer.isPresent()){

            Camion nuevoCamion = Camion.builder()
                            .capacidadPeso(camionDTO.capacidadPeso())
                            .capacidadVolumen(camionDTO.capacidadVolumen())
                            .chofer(chofer.get())
                            .consumo(camionDTO.consumo())
                            .costo_x_km(camionDTO.costo_x_km())
                            .disponibilidad(camionDTO.disponibilidad())
                            .patente(camionDTO.patente())
                            .build();
            Camion camiondb = camionRepository.save(nuevoCamion);
            return this.mapToReponseDTO(camiondb);}
            
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }


    @Transactional
    public CamionResponseDTO actualizarCamion(long id, CamionCreationDTO dto){
            Camion camionDb = camionRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND
        ));
        Optional<Chofer> chofer = choferService.devolverChoferPorId(dto.choferId());
        if(chofer.isPresent()){
        camionDb.setCapacidadPeso(dto.capacidadPeso());
        camionDb.setCapacidadVolumen(dto.capacidadVolumen());
        camionDb.setChofer(chofer.get());
        camionDb.setConsumo(dto.consumo());
        camionDb.setCosto_x_km(dto.costo_x_km());
        camionDb.setDisponibilidad(dto.disponibilidad());
        camionDb.setPatente(dto.patente());

        Camion camion = camionRepository.save(camionDb);

        return this.mapToReponseDTO(camion);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    

    @Transactional
    public void eliminarCamion(long id){
        if(camionRepository.findById(id).isPresent()){
            camionRepository.deleteById(id);
            return; 
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "El camion con id '" + id + "' no existe."
        );
    }

   public Page<CamionResponseDTO> filtrarCamiones(
        String disponibilidad, Double pesoMin, Double volumenMin, int page, int size
    ){  
        // 1. Declara la variable enum aquí
        EstadoCamion disponibilidadEnum = null;
        
        try{
            if (disponibilidad != null){
                // 2. Asígnale el valor (añado .toUpperCase() por seguridad)
                disponibilidadEnum = EstadoCamion.valueOf(disponibilidad.toUpperCase());
            }
        }catch (IllegalArgumentException e){
            // 3. Es mejor lanzar una ResponseStatusException, que Spring entiende
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El valor de 'disponibilidad' no es válido: " + disponibilidad, e);
        }

        Pageable pageable = PageRequest.of(page, size);

        // 4. Ahora, usa la variable 'disponibilidad' (String) para los IFs,
        //    pero usa 'disponibilidadEnum' (Enum) para el REPOSITORIO.

        if(disponibilidad != null && pesoMin != null && volumenMin != null){
            return camionRepository
            .findByDisponibilidadAndCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(disponibilidadEnum, pesoMin, volumenMin,pageable)
            .map(this::mapToReponseDTO);
        }else if(pesoMin != null && volumenMin != null){
            return camionRepository
            .findByCapacidadPesoGreaterThanEqualAndCapacidadVolumenGreaterThanEqual(pesoMin, volumenMin, pageable)
            .map(this::mapToReponseDTO);
        }else if(pesoMin != null && disponibilidad == null && volumenMin == null){
            return camionRepository
            .findByCapacidadPesoGreaterThanEqual(pesoMin, pageable)
            .map(this::mapToReponseDTO);
        }else if(volumenMin != null && disponibilidad == null && pesoMin == null){
            return camionRepository
            .findByCapacidadVolumenGreaterThanEqual(volumenMin, pageable)
            .map(this::mapToReponseDTO);
        }else if(disponibilidad != null && volumenMin == null && pesoMin == null){
            return camionRepository
            .findByDisponibilidad(disponibilidadEnum, pageable) // <-- USA EL ENUM
            .map(this::mapToReponseDTO);
        }else if(disponibilidad != null && volumenMin != null){
            return camionRepository
            .findByDisponibilidadAndCapacidadVolumenGreaterThanEqual(disponibilidadEnum, volumenMin, pageable) // <-- USA EL ENUM
            .map(this::mapToReponseDTO);
        }else if (disponibilidad != null && pesoMin != null){
            return camionRepository
            .findByDisponibilidadAndCapacidadPesoGreaterThanEqual(disponibilidadEnum, pesoMin, pageable) // <-- USA EL ENUM
            .map(this::mapToReponseDTO);
        }else{
            return camionRepository.findAll(pageable)
            .map(this::mapToReponseDTO);
        }
    }
}





