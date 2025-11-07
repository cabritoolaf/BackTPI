package com.tpi.gestion.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChoferCreationDTO(

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "El apellido es obligatorio")
    String apellido,

    String email,

    long telefono,
    
    @NotNull(message = "El DNI es obligatorio")
    long dni
) {
    
}
