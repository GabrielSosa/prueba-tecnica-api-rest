package com.devsu.service;


import com.devsu.domain.Cliente;
import com.devsu.domain.Persona;
import com.devsu.repository.ClienteRepository;
import com.devsu.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;

    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente crearCliente(Cliente cliente) {
        // Encriptar la contraseña antes de guardar
        //cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
        if (cliente.getPersona() != null) {
            personaRepository.save(cliente.getPersona());
        }
        return clienteRepository.save(cliente);
    }

   /* public Cliente actualizarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getClienteId())) {
            // Encriptar la contraseña si ha sido modificada
            if (cliente.getContrasena() != null) {
                // cliente.setContrasena(passwordEncoder.encode(cliente.getContrasena()));
                cliente.setContrasena(cliente.getContrasena());
            }
            return clienteRepository.save(cliente);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + cliente.getClienteId());
        }
    }*/

    public List<Cliente> obtenerTodosLosClientesActivos() {
        return clienteRepository.findByEstadoTrue();
    }
    public Optional<Cliente> obtenerClienteActivoPorId(Long id) {
        return clienteRepository.findClienteByIdAndEstadoTrue(id);
    }
    public void eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }
}

