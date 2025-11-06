package com.tpi.gestion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpi.gestion.DTO.DepositoCreationDTO;
import com.tpi.gestion.DTO.DepositoResponseDTO;
import com.tpi.gestion.service.DepositoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/depositos")
@RequiredArgsConstructor
public class DepositoController {
    private final DepositoService depositoService;

    @PostMapping
    public ResponseEntity<DepositoResponseDTO> crearDeposito(
        @Valid @RequestBody DepositoCreationDTO depositodto
    ){
        DepositoResponseDTO depositoCreado = depositoService.crearNuevoDeposito(depositodto);

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(depositoCreado);
    }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> eliminarDeposito(@PathVariable("id") Long id){
    depositoService.eliminarDeposito(id);

    return ResponseEntity.noContent().build();
   }
}
