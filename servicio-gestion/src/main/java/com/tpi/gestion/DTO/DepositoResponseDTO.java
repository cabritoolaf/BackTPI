package com.tpi.gestion.DTO;

public record DepositoResponseDTO(

    long depositoId,

    String nombre,

    String direccion,

    Double latitud,
    Double longitud,

    Double costo_x_dia

) {
    
}
