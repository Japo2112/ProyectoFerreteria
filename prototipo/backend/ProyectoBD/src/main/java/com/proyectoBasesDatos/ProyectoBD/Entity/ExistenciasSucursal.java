package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "EXISTENCIAS_SUCURSAL")
public class ExistenciasSucursal {

    @EmbeddedId
    private ExistenciasSucursalId id; // La llave compuesta

    @Column(name = "cantidad_stock")
    private Double cantidadStock;

    @Column(name = "stock_minimo")
    private Double stockMinimo;

    // --- Mapeo de las llaves compuestas como relaciones ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productoId") // Mapea la parte "productoId" de la llave compuesta
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("sucursalId") // Mapea la parte "sucursalId" de la llave compuesta
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}