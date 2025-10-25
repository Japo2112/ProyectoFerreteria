package com.proyectoBasesDatos.ProyectoBD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyectoBasesDatos.ProyectoBD.Entity.RolesAplicacion;
import com.proyectoBasesDatos.ProyectoBD.Service.RolesAplicacionService;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolesAplicacionController {

    @Autowired
    private RolesAplicacionService rolesService;

    // GET http://localhost:8080/api/roles
    @GetMapping
    public List<RolesAplicacion> getAllRoles() {
        return rolesService.getAllRoles();
    }
}