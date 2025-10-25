package com.proyectoBasesDatos.ProyectoBD.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.RolesAplicacion;
import com.proyectoBasesDatos.ProyectoBD.Repository.RolesAplicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolesAplicacionService {

    @Autowired
    private RolesAplicacionRepository rolesRepository;

    public List<RolesAplicacion> getAllRoles() {
        return rolesRepository.findAll();
    }
}