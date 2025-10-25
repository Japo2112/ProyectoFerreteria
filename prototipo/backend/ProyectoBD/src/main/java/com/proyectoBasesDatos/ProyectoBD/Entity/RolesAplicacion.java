package com.proyectoBasesDatos.ProyectoBD.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Data
@Entity
@Table(name = "ROLES_APLICACION")
public class RolesAplicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_app_id")
    private Long rolAppId;

    @Column(name = "nombre_rol", nullable = false)
    private String nombreRol;

    @Column(name = "descripcion")
    private String descripcion;

    // --- Relación Inversa ---
    @OneToMany(mappedBy = "rol")
    private Set<Usuario> usuarios;
}