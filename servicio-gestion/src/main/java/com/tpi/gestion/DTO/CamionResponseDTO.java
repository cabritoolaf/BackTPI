package com.tpi.gestion.DTO;

import com.tpi.gestion.enums.EstadoCamion;

public record CamionResponseDTO(
    
    Long camionId,

    String patente,

    Long choferId,

    Double capacidadVolumen,

    Double capacidadPeso,

    EstadoCamion disponibilidad,

    Double costo_x_km,

    Double consumo
) {
}
