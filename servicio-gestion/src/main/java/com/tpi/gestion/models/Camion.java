package com.tpi.gestion.models;

import com.tpi.gestion.enums.EstadoCamion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "CAMION")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CAMION_ID")
    private long camionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHOFER_ID", unique = true)
    private Chofer chofer;

    @Column(name = "CAPACIDAD_PESO")
    private Double capacidad_peso;

    @Column(name = "CAPACIDAD_VOLUMEN")
    private Double capacidad_volumen;

    @Column(name = "DISPONIBILIDAD")
    private EstadoCamion disponibilidad;

    @Column(name = "COSTO_X_KM")
    private Double costo_x_km;

    @Column(name = "CONSUMO")
    private Double consumo;



}
