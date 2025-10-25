package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data // Lombok crea equals() y hashCode()
@Embeddable
public class PermisosMenuId implements Serializable {

    @Column(name = "rol_app_id")
    private Long rolAppId;

    @Column(name = "menu_id")
    private Long menuId;
}