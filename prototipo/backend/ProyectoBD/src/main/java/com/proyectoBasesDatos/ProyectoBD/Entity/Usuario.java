package com.proyectoBasesDatos.ProyectoBD.Entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password_hash") // Es NULLABLE, ¡correcto!
    private String passwordHash;

    @Column(name = "activo")
    private String activo;

    @Column(name = "last_login")
    private LocalDateTime lastLogin; // TIMESTAMP se mapea a LocalDateTime

    // --- Relaciones ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_app_id") // La FK en la tabla USUARIO
    private RolesAplicacion rol;
}