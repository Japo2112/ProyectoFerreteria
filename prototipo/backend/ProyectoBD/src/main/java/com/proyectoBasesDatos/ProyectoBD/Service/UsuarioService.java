package com.proyectoBasesDatos.ProyectoBD.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.Usuario;
import com.proyectoBasesDatos.ProyectoBD.Repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Servicio para actualizar (ej: cambiar el rol)
    public Usuario updateUsuario(Long id, Usuario usuarioDetalles) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        
        // Solo dejamos que el admin cambie el nombre o el rol
        usuario.setNombre(usuarioDetalles.getNombre());
        usuario.setRol(usuarioDetalles.getRol());
        usuario.setActivo(usuarioDetalles.getActivo());
        
        return usuarioRepository.save(usuario);
    }
}