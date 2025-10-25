package com.proyectoBasesDatos.ProyectoBD.Repository;

import com.proyectoBasesDatos.ProyectoBD.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
