package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "SUCURSAL")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    private Long idSucursal;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "region")
    private String region;

    // --- Relación Inversa ---
    // Una Sucursal puede tener muchos Clientes
    // "mappedBy" debe coincidir con el nombre de la variable en Cliente.java
    @OneToMany(mappedBy = "sucursal") 
    private Set<Cliente> clientes;
}
