package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PRODUCTO")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "estado")
    private String estado;

    // --- Relaciones ---
    // Muchos Productos pertenecen a Una Categoria
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id") // La FK en la tabla PRODUCTO
    private Categoria categoria;
}