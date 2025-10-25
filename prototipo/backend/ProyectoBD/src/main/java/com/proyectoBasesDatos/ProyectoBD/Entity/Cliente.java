package com.proyectoBasesDatos.ProyectoBD.Entity;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nit", unique = true)
    private String nit;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "tipo_cliente")
    private String tipoCliente;

    @Column(name = "limite_credito")
    private BigDecimal limiteCredito;

   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id") 
    private Sucursal sucursal;
}