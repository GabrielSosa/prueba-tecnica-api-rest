package com.devsu.controller;

import com.devsu.domain.Cliente;
import com.devsu.domain.Persona;
import com.devsu.dto.ClienteDto;
import com.devsu.repository.ClienteRepository;
import com.devsu.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCliente() throws Exception {

        when(clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente());
        when(clienteRepository.findAll()).thenReturn(Collections.singletonList(new Cliente()));

        // Configurar el objeto ClienteDto
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setContrasena("password123");
        clienteDto.setClienteId(4L);
        clienteDto.setNombre("Gabriel");
        clienteDto.setEstado(true);
        clienteDto.setDireccion("Los llanos");
        clienteDto.setIdentificacion("0801-000-109291");
        clienteDto.setEdad(30);
        clienteDto.setGenero("M");
        clienteDto.setTelefono("XXXX-XXXX");

        Cliente cliente = new Cliente();
        cliente.setClienteId(1L);
        cliente.setPersona(new Persona());

        when(clienteService.crearCliente(any(Cliente.class))).thenReturn(cliente);

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").exists());

        List<Cliente> clientes = clienteRepository.findAll();
        assertFalse(clientes.isEmpty());
    }
}

