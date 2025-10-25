package com.proyectoBasesDatos.ProyectoBD.Service;

import com.proyectoBasesDatos.ProyectoBD.Entity.Cliente;
import com.proyectoBasesDatos.ProyectoBD.Repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // ¡Importante! Marca esta clase como un Servicio de Spring
public class ClienteService {

    // 1. Inyecta el "refrigerador" (Repositorio)
    @Autowired
    private ClienteRepository clienteRepository;

    // --- Lógica de Negocio ---
    // (Para un CRUD simple, la lógica es solo "llamar al repositorio")

    // Lógica para (R)EAD: Obtener todos
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    // Lógica para (R)EAD: Obtener uno por ID
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    // Lógica para (C)REATE y (U)PDATE
    public Cliente saveCliente(Cliente cliente) {
        // Aquí podrías añadir lógica extra, como:
        // if (cliente.getNit() == null) { throw new Exception("NIT es obligatorio"); }
        return clienteRepository.save(cliente);
    }

    // Lógica para (D)ELETE
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Método 'helper' para saber si existe
    public boolean existsCliente(Long id) {
        return clienteRepository.existsById(id);
    }
}