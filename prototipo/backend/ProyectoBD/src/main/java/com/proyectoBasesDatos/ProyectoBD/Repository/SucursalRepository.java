package com.proyectoBasesDatos.ProyectoBD.Repository;

import com.proyectoBasesDatos.ProyectoBD.Entity.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

}
