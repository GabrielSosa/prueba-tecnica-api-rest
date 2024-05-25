package com.devsu.controller;

import com.devsu.domain.Cliente;
import com.devsu.dto.ClienteDto;
import com.devsu.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearCliente() throws Exception {

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setContrasena("password123");
        clienteDto.setClienteId(3L);
        clienteDto.setNombre("Gabriel");
        clienteDto.setEstado(true);
        clienteDto.setDireccion("Los llanos");
        clienteDto.setIdentificacion("0801-XXXX-109291");
        clienteDto.setEdad(30);
        clienteDto.setGenero("M");
        clienteDto.setTelefono("XXXX-XXXX");

        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(clienteDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clienteId").exists());

        List<Cliente> clientes = clienteRepository.findAll();
        assertFalse(clientes.isEmpty());

    }
}

