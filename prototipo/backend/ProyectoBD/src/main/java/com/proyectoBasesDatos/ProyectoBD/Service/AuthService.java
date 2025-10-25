package com.proyectoBasesDatos.ProyectoBD.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.Usuario;
import com.proyectoBasesDatos.ProyectoBD.Repository.UsuarioRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // SIMULACIÓN DE LOGIN
    // En un proyecto real, verificarías el hash o un token.
    // Para la entrega, solo buscaremos el usuario.
    public Optional<Usuario> login(String username, String password) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username.toUpperCase());
        
        if (usuarioOpt.isEmpty()) {
            return Optional.empty(); // Usuario no existe
        }
        
        // --- SIMULACIÓN ---
        // Como no podemos validar el hash, haremos una "simulación"
        // Asumimos que si el usuario es APP_ADMIN, la clave es 'claveadmin123'
        // ¡ESTO ES SOLO PARA LA DEMO!
        if (username.equalsIgnoreCase("APP_ADMIN") && password.equals("claveadmin123")) {
             return usuarioOpt;
        }
        
        // Si no es el admin, devolvemos vacío (login falla)
        return Optional.empty(); 
    }
}