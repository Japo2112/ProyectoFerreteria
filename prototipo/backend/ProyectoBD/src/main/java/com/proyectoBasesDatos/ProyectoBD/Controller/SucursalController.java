package com.proyectoBasesDatos.ProyectoBD.Controller;

import com.proyectoBasesDatos.ProyectoBD.Entity.Sucursal;
import com.proyectoBasesDatos.ProyectoBD.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
@CrossOrigin(origins = "http://localhost:4200") 
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> getAllSucursales() {
        return sucursalService.getAllSucursales();
    }

}
