package com.tpi.gestion.DTO;

public record ChoferResponseDTO(
    long choferId,
    
    String nombre,
    
    String apellido,
    
    String email,
    
    long telefono,
    
    long dni
) {
    
}
