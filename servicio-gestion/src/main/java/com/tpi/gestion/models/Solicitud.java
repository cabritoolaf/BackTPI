package com.tpi.gestion.models;

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
import java.time.Duration;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "SOLICITUD")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SOLICITUD_ID")
    private long solicitudId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    @Column(name = "ORIGEN_DIRECCION")
    private String origen_direccion;

    @Column(name = "ORIGEN_LATITUD")
    private Double origen_latitud;

    @Column(name = "ORIGEN_LONGITUD")
    private Double origen_longitud;

    @Column(name = "DESTINO_DIRECCION")
    private String destino_direccion;

    @Column(name = "DESTINO_LATITUD")
    private Double destino_latitud;

    @Column(name = "DESTINO_LONGITUD")
    private Double destino_longitud;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTADO_SOLICITUD_ID")
    private EstadoSolicitud estadoSolicitud;

    @Column(name = "COSTO_ESTIMADO")
    private Double costoEstimado;

    @Column(name = "TIEMPO_ESTIMADO")
    private Duration tiempoEstimado;

    @Column(name = "COSTO_FINAL")
    private Double costoFinal;

    @Column(name = "TIEMPO_REAL")
    private Duration tiempoReal;
}
