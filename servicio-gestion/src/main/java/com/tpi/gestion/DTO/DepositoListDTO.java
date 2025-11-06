package com.tpi.gestion.DTO;

public record DepositoListDTO(

    long depositoId,
    String nombre,
    String direccion,
    Double latitud,
    Double longitud

) {
    
}
