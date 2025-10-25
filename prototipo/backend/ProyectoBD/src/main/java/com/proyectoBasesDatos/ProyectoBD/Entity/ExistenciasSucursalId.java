package com.proyectoBasesDatos.ProyectoBD.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data // Lombok crea equals() y hashCode() (vital para llaves compuestas)
@Embeddable
public class ExistenciasSucursalId implements Serializable {

    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "sucursal_id")
    private Long sucursalId;
}