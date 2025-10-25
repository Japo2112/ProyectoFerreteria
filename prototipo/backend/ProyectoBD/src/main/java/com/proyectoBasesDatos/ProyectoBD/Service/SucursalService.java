package com.proyectoBasesDatos.ProyectoBD.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.Sucursal;
import com.proyectoBasesDatos.ProyectoBD.Repository.SucursalRepository;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    // Solo necesitamos un método para traer todas
    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }
}