package com.proyectoBasesDatos.ProyectoBD.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.PermisosMenu;
import com.proyectoBasesDatos.ProyectoBD.Repository.PermisosMenuRepository;

import java.util.List;


@Service
public class MenuService {

    @Autowired
    private PermisosMenuRepository permisosRepository;

    // Trae los permisos (y los menús anidados) filtrados por el ID del rol
    public List<PermisosMenu> getMenuByRol(Long rolId) {
        // Usamos el método mágico que creamos en el repositorio
        return permisosRepository.findByRol_RolAppId(rolId);
    }
}