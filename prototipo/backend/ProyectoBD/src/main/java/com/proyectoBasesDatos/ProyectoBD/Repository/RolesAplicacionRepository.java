package com.proyectoBasesDatos.ProyectoBD.Repository;

import com.proyectoBasesDatos.ProyectoBD.Entity.RolesAplicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesAplicacionRepository extends JpaRepository<RolesAplicacion, Long> {

}
