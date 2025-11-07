package com.tpi.gestion.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tpi.gestion.DTO.DepositoCreationDTO;
import com.tpi.gestion.DTO.DepositoResponseDTO;
import com.tpi.gestion.models.Deposito;
import com.tpi.gestion.repo.DepositoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DepositoService {
    
    private final DepositoRepository depositoRepository;

    private DepositoResponseDTO mapToResponseDTO(Deposito deposito){
        return new DepositoResponseDTO(
            deposito.getDepositoId(),
            deposito.getNombre(),
            deposito.getDireccion(),
            deposito.getLatitud(),
            deposito.getLongitud(),
            deposito.getCosto_x_dia()
        );
    }

    @Transactional
    public DepositoResponseDTO crearNuevoDeposito(DepositoCreationDTO dto){
        if(depositoRepository.existsByNombreIgnoreCase(dto.nombre())){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, 
                "El depósito con nombre '" + dto.nombre() + "' ya existe. Por favor, elija otro."
            );
        }

        Deposito nuevoDeposito = Deposito.builder()
        .costo_x_dia(dto.costo_x_dia())
        .direccion(dto.direccion())
        .latitud(dto.latitud())
        .longitud(dto.longitud())
        .nombre(dto.nombre())
        .build();
        
        Deposito deposito_db = depositoRepository.save(nuevoDeposito);

        return this.mapToResponseDTO(deposito_db);
    }

    @Transactional
    public void eliminarDeposito(long id){
        if(depositoRepository.findById(id).isPresent()){
            depositoRepository.deleteById(id);
            return; 
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "El deposito con id '" + id + "' no existe."
        );
    }

    @Transactional
    public DepositoResponseDTO actualizarDeposito(Long id, DepositoCreationDTO dto){
        Deposito deposito_db = depositoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND
        ));
        deposito_db.setNombre(dto.nombre());
        deposito_db.setCosto_x_dia(dto.costo_x_dia());
        deposito_db.setDireccion(dto.direccion());
        deposito_db.setLatitud(dto.latitud());
        deposito_db.setLongitud(dto.longitud());

        Deposito depo = depositoRepository.save(deposito_db);

        return this.mapToResponseDTO(depo);
    }

    public Page<DepositoResponseDTO> buscarDeposito(
        String nombre, Double longitud, Double latitud, int page, int size
    ){
            Pageable pageable = PageRequest.of(page, size);

            if(nombre != null && latitud != null && longitud != null){
                return depositoRepository
                .findByNombreContainingIgnoreCaseAndLatitudAndLongitud(nombre, latitud, longitud, pageable)
                .map(this::mapToResponseDTO);
            }else if (nombre != null){
                return depositoRepository
                .findByNombreContainingIgnoreCase(nombre, pageable)
                .map(this::mapToResponseDTO);
            } else if (latitud != null && longitud != null){
                return depositoRepository
                .findByLatitudAndLongitud(latitud,longitud, pageable)
                .map(this::mapToResponseDTO);
            } else{
                return depositoRepository
                .findAll(pageable)
                .map(this::mapToResponseDTO);
            }
    }

    public Page<DepositoResponseDTO> listarTodos(
        int page, int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return depositoRepository
        .findAll(pageable)
        .map(this::mapToResponseDTO);
    }

}
