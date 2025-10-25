package com.proyectoBasesDatos.ProyectoBD.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectoBasesDatos.ProyectoBD.Entity.Cliente;
import com.proyectoBasesDatos.ProyectoBD.Service.ClienteService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200") // No olvides el CORS
public class ClienteController {

    // ¡Inyecta el "Chef" (Servicio), NO el "refrigerador" (Repositorio)!
    @Autowired
    private ClienteService clienteService;

    // --- (R)EAD: Todos ---
    @GetMapping
    public List<Cliente> getAllClientes() {
        // El controlador solo llama al servicio
        return clienteService.getAllClientes();
    }

    // --- (R)EAD: Uno por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        
        return cliente.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    // --- (C)REATE: Nuevo ---
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        // El ID debe ser nulo para un 'create'
        cliente.setIdCliente(null); 
        return clienteService.saveCliente(cliente);
    }

    // --- (U)PDATE: Actualizar ---
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalles) {
        
        // Primero, usa el servicio para verificar si existe
        if (!clienteService.existsCliente(id)) {
            return ResponseEntity.notFound().build();
        }

        // Si existe, le ponemos el ID correcto y lo guardamos
        clienteDetalles.setIdCliente(id);
        Cliente clienteActualizado = clienteService.saveCliente(clienteDetalles);
        return ResponseEntity.ok(clienteActualizado);
    }

    // --- (D)ELETE: Borrar ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (!clienteService.existsCliente(id)) {
            return ResponseEntity.notFound().build();
        }
        
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}