package com.devsu.controller;

import com.devsu.domain.Cliente;
import com.devsu.domain.Persona;
import com.devsu.dto.ClienteDto;
import com.devsu.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // GET: Obtener todos los clientes
    @GetMapping
    public List<ClienteDto> obtenerTodosLosClientes() {
        return clienteService.obtenerTodosLosClientes().stream().
                map(this::convertToDto).collect(Collectors.toList());

    }

    // GET: Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id) {
        return clienteService.obtenerClientePorId(id)
                .map(cliente -> ResponseEntity.ok(convertToDto(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Crear un nuevo cliente
    @PostMapping
    public ClienteDto crearCliente(@RequestBody ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        return convertToDto(clienteService.crearCliente(cliente));
    }

    // PUT: Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        return clienteService.obtenerClientePorId(id)
                .map(cliente -> {
                    cliente.setContrasena(clienteDto.getContrasena());
                    cliente.setEstado(clienteDto.isEstado());
                    Persona persona = cliente.getPersona();
                    persona.setNombre(clienteDto.getNombre());
                    persona.setGenero(clienteDto.getGenero());
                    persona.setEdad(clienteDto.getEdad());
                    persona.setIdentificacion(clienteDto.getIdentificacion());
                    persona.setDireccion(clienteDto.getDireccion());
                    persona.setTelefono(clienteDto.getTelefono());
                    return ResponseEntity.ok(convertToDto(clienteService.crearCliente(cliente)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH: Actualizar parcialmente un cliente
    @PatchMapping("/{id}")
    public ResponseEntity<ClienteDto> updatePartialCliente(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Optional<Cliente> clienteOptional = clienteService.obtenerClientePorId(id);

        if (!clienteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cliente cliente = clienteOptional.get();
        Persona persona = cliente.getPersona();

        updates.forEach((key, value) -> {
            switch (key) {
                case "contrasena":
                    cliente.setContrasena((String) value);
                    break;
                case "estado":
                    cliente.setEstado((Boolean) value);
                    break;
                case "nombre":
                    persona.setNombre((String) value);
                    break;
                case "genero":
                    persona.setGenero((String) value);
                    break;
                case "edad":
                    persona.setEdad((Integer) value);
                    break;
                case "identificacion":
                    persona.setIdentificacion((String) value);
                    break;
                case "direccion":
                    persona.setDireccion((String) value);
                    break;
                case "telefono":
                    persona.setTelefono((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        cliente.setPersona(persona);
        Cliente updatedCliente = clienteService.crearCliente(cliente);

        return ResponseEntity.ok(convertToDto(updatedCliente));
    }

    // DELETE: Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setClienteId(cliente.getClienteId());
        clienteDto.setContrasena(cliente.getContrasena());
        clienteDto.setEstado(cliente.isEstado());
        Persona persona = cliente.getPersona();
        clienteDto.setNombre(persona.getNombre());
        clienteDto.setGenero(persona.getGenero());
        clienteDto.setEdad(persona.getEdad());
        clienteDto.setIdentificacion(persona.getIdentificacion());
        clienteDto.setDireccion(persona.getDireccion());
        clienteDto.setTelefono(persona.getTelefono());
        return clienteDto;
    }

    private Cliente convertToEntity(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        Persona persona = new Persona();
        persona.setNombre(clienteDto.getNombre());
        persona.setGenero(clienteDto.getGenero());
        persona.setEdad(clienteDto.getEdad());
        persona.setIdentificacion(clienteDto.getIdentificacion());
        persona.setDireccion(clienteDto.getDireccion());
        persona.setTelefono(clienteDto.getTelefono());
        cliente.setPersona(persona);
        cliente.setContrasena(clienteDto.getContrasena());
        cliente.setEstado(clienteDto.isEstado());
        return cliente;
    }
}

