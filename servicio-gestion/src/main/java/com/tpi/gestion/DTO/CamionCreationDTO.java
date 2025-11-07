package com.tpi.gestion.DTO;

import com.tpi.gestion.enums.EstadoCamion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CamionCreationDTO(
    @NotBlank(message = "La patente es obligatoria")
    String patente,

    @NotNull(message = "El chofer es obligatorio")
    Long choferId,

    @NotNull(message = "El peso maximo es obligatorio")
    Double capacidadPeso,

    @NotNull(message = "El volumen maximo es obligatorio")
    Double capacidadVolumen,

    @NotNull(message = "La disponibilidad es obligatoria")
    EstadoCamion disponibilidad,

    @NotNull(message = "El costo es obligatorio")
    Double costo_x_km,

    @NotNull(message = "El consumo es obligatorio")
    Double consumo

){
}
