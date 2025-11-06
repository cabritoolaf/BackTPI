package com.tpi.gestion.models;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TARIFA")
public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TARIFA_ID")
    private long tarifaId;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "VALOR")
    private Double valor;

    @Column(name = "RANGO_MIN")
    private Double rangoMin;

    @Column(name = "RANGO_MAX")
    private Double rangoMax;
}
