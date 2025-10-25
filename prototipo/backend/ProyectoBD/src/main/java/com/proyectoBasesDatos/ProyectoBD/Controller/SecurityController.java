package com.proyectoBasesDatos.ProyectoBD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoBasesDatos.ProyectoBD.Entity.PermisosMenu;
import com.proyectoBasesDatos.ProyectoBD.Entity.Usuario;
import com.proyectoBasesDatos.ProyectoBD.Service.AuthService;
import com.proyectoBasesDatos.ProyectoBD.Service.MenuService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "http://localhost:4200")
public class SecurityController {

    @Autowired
    private AuthService authService;

    @Autowired
    private MenuService menuService;

    // --- Endpoint de Login ---
    // POST http://localhost:8080/api/security/login
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        Optional<Usuario> usuario = authService.login(username, password);

        // Si el usuario existe y la clave es (simuladamente) correcta, devuelve OK
        return usuario.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.status(401).build()); // 401 Unauthorized
    }

    // --- Endpoint de Menú ---
    // GET http://localhost:8080/api/security/menu/1
    @GetMapping("/menu/{rolId}")
    public List<PermisosMenu> getMenuForRole(@PathVariable Long rolId) {
        return menuService.getMenuByRol(rolId);
    }
}