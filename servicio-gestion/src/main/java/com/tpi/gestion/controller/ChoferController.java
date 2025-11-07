package com.tpi.gestion.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.gestion.DTO.ChoferCreationDTO;
import com.tpi.gestion.DTO.ChoferResponseDTO;
import com.tpi.gestion.service.ChoferService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chofer")
@RequiredArgsConstructor
public class ChoferController {
    
    private final ChoferService choferService;

    @PostMapping
    public ResponseEntity<ChoferResponseDTO> crearChofer(
        @Valid @RequestBody ChoferCreationDTO choferDTO 
    ){
        ChoferResponseDTO choferCreado = choferService.crearNuevoChofer(choferDTO);
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(choferCreado);
    }



    @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarChofer(@PathVariable("id") Long id){
    choferService.eliminarChofer(id);

    return ResponseEntity.noContent().build();
   }

    @PutMapping("/{id}")
   public ResponseEntity<ChoferResponseDTO> actualizarChofer(
    @PathVariable("id") Long id,
    @Valid @RequestBody ChoferCreationDTO dto) {
        ChoferResponseDTO choferAct = choferService.actualizarChofer(id, dto);

        return ResponseEntity.ok(choferAct);
   }


   @GetMapping("/{id}")
   public ResponseEntity<ChoferResponseDTO> buscarChoferPorId(
    @PathVariable("id") long id){
        ChoferResponseDTO choferDb = choferService.buscarChoferPorId(id);
        return ResponseEntity.ok(choferDb);
    }

   @GetMapping("/dni/{dni}")
   public ResponseEntity<ChoferResponseDTO> buscarChoferPorDni(
    @PathVariable("dni") long dni){
        ChoferResponseDTO choferDb = choferService.buscarChoferPorDni(dni);
        return ResponseEntity.ok(choferDb);
    }


    @GetMapping
    public ResponseEntity<Page<ChoferResponseDTO>> listarTodos(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
    ){
        Page<ChoferResponseDTO> response = choferService.listarTodos(page, size);

        return ResponseEntity.ok(response);
    }
 








}
