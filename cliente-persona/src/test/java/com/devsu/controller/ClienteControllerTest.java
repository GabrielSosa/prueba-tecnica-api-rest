package com.devsu.controller;

import com.devsu.domain.Cliente;
import com.devsu.domain.Persona;
import com.devsu.dto.ClienteDto;
import com.devsu.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    public void testCrearClienteConvierteCorrectamente() {

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setClienteId(1L);
        clienteDto.setContrasena("password123");
        clienteDto.setEstado(true);
        clienteDto.setNombre("Juan Pérez");
        clienteDto.setGenero("Masculino");
        clienteDto.setEdad(30);
        clienteDto.setIdentificacion("1234567890");
        clienteDto.setDireccion("Calle Principal #123");
        clienteDto.setTelefono("555-555-5555");

        Cliente clienteEsperado = new Cliente();
        clienteEsperado.setClienteId(1L);
        clienteEsperado.setContrasena("password123");
        clienteEsperado.setEstado(true);

        Persona persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan Pérez");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1234567890");
        persona.setDireccion("Calle Principal #123");
        persona.setTelefono("555-555-5555");

        clienteEsperado.setPersona(persona);
        when(clienteService.crearCliente(any(Cliente.class))).thenReturn(clienteEsperado);

        ClienteDto resultadoDto = clienteController.crearCliente(clienteDto);

        // Verificaciones
        assertEquals(clienteEsperado.getClienteId(), resultadoDto.getClienteId());
    }

}
