package com.tpi.gestion.DTO;
import jakarta.validation.constraints.*;

public record DepositoCreationDTO(
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    String direccion,

    @NotNull(message = "La latitud es obligatoria.")
    @DecimalMin(value = "-90.0", message = "La latitud no es valida")
    @DecimalMax(value = "90.0", message = "La latitud no es valida")
    Double latitud,

    @NotNull(message = "La longitud es obligatoria.")
    @DecimalMin(value = "-180.0", message = "La longitud no es válida")
    @DecimalMax(value = "180.0", message = "La longitud no es válida")
    Double longitud,

    @NotNull(message = "El costo por dia de estadia es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El costo de estadia no puede ser negativo.")
    Double costo_x_dia

){

}
