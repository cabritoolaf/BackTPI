package com.tpi.gestion.models;

import com.tpi.gestion.enums.EstadoContenedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "CONTENEDOR")
public class Contenedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTENEDOR_ID")
    private long contenedorId;

    @Column(name = "PESO")
    private Double peso;

    @Column(name = "VOLUMEN")
    private Double volumen;

    @Column(name = "ESTADO_ACTUAL")
    private EstadoContenedor estado_actual;



}
