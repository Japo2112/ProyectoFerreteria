package com.proyectoBasesDatos.ProyectoBD.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;
    
    // --- Relación Inversa ---
    @OneToMany(mappedBy = "categoria")
    private Set<Producto> productos;
}