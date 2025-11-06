package com.tpi.gestion.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "TRAMO")
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAMO_ID")
    private long tramoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAMION_ID")
    private Camion camion;

    @Column(name = "ORDEN")
    private long orden;

    @Column(name = "ORIGEN_DESCRIPCION")
    private String origenDescripcion;

    @Column(name = "ORIGEN_LATITUD")
    private Double origenLatitud;

    @Column(name = "ORIGEN_LONGITUD")
    private Double origenLongitud;

    @Column(name = "DESTINO_LATITUD")
    private Double destinoLatitud;

    @Column(name = "DESTINO_LONGITUD")
    private Double destinoLongitud;

    @Column(name = "TIPO_TRAMO")
    private String tipoTramo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTADO_TRAMO_ID")
    private EstadoTramo estadoTramo;

    @Column(name = "COSTO_APROXIMADO")
    private Double costoAprox;
    
    @Column(name = "COSTO_REAL")
    private Double costoReal;

    @Column(name = "FECHA_INICIO_ESTIMADA")
    private Date fechaInicioEstimada;

    @Column(name = "FECHA_FIN_ESTIMADA")
    private Date fechaFinEstimada;

    @Column(name = "FECHA_INICIO_REAL")
    private Date fechaInicioReal;

    @Column(name = "FECHA_FIN_REAL")
    private Date fechaFinReal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPOSITO_ORIGEN_ID")
    private Deposito depositoOrigen;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPOSITO_DESTINO_ID")
    private Deposito depositoDestino;
}
