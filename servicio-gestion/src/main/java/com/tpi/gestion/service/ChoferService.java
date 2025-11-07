package com.tpi.gestion.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tpi.gestion.DTO.ChoferCreationDTO;
import com.tpi.gestion.DTO.ChoferResponseDTO;
import com.tpi.gestion.models.Chofer;
import com.tpi.gestion.repo.ChoferRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChoferService {
    
    private final ChoferRepository choferRepository;

    private ChoferResponseDTO mapToResponseDTO(Chofer chofer){
        return new ChoferResponseDTO(
            chofer.getChoferId(),
            chofer.getNombre(),
            chofer.getApellido(),
            chofer.getEmail(),
            chofer.getTelefono(),
            chofer.getDni()
        );
    }




    public ChoferResponseDTO crearNuevoChofer(ChoferCreationDTO choferDTO){
        if (choferRepository.existsByDni(choferDTO.dni())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Chofer nuevoChofer = Chofer.builder()
                                .apellido(choferDTO.apellido())
                                .dni(choferDTO.dni())
                                .email(choferDTO.email())
                                .telefono(choferDTO.telefono())
                                .nombre(choferDTO.nombre())
                                .build();


        Chofer choferDb = choferRepository.save(nuevoChofer);

        return this.mapToResponseDTO(choferDb);
    }






    public ChoferResponseDTO buscarChoferPorId(long id){
        var choferDb = choferRepository.findById(id);
        if(choferDb.isPresent()){
            return this.mapToResponseDTO(choferDb.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }







    public ChoferResponseDTO buscarChoferPorDni(long dni){
        var choferDb = choferRepository.findByDni(dni);
        if(choferDb.isPresent()){
            return this.mapToResponseDTO(choferDb.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }






     @Transactional
    public void eliminarChofer(long id){
        if(choferRepository.findById(id).isPresent()){
            choferRepository.deleteById(id);
            return; 
        }
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "El deposito con id '" + id + "' no existe."
        );
    }







    @Transactional
    public ChoferResponseDTO actualizarChofer(Long id, ChoferCreationDTO dto){
        Chofer chofer_db = choferRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND
        ));
        chofer_db.setNombre(dto.nombre());
        chofer_db.setApellido(dto.apellido());
        chofer_db.setDni(dto.dni());
        chofer_db.setEmail(dto.email());
        chofer_db.setTelefono(dto.telefono());

        choferRepository.save(chofer_db);

        return this.mapToResponseDTO(chofer_db);
    }


    public Optional<Chofer> devolverChoferPorId(long id){
        var choferDb = choferRepository.findById(id);
            return choferDb;
    }

    public Page<ChoferResponseDTO> listarTodos(int page, int size){
            Pageable pagebale = PageRequest.of(page, size);

            return choferRepository
            .findAll(pagebale)
            .map(this::mapToResponseDTO);
    }
 
}
