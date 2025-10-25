package com.proyectoBasesDatos.ProyectoBD.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PERMISOS_MENU")
public class PermisosMenu {

    @EmbeddedId
    private PermisosMenuId id; // La llave compuesta

    @Column(name = "permiso_leer")
    private Integer permisoLeer;

    // --- Mapeo de las llaves compuestas como relaciones ---
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rolAppId") // Mapea la parte "rolAppId" de la llave
    @JoinColumn(name = "rol_app_id")
    private RolesAplicacion rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId") // Mapea la parte "menuId" de la llave
    @JoinColumn(name = "menu_id")
    private MenuSistema menu;
}