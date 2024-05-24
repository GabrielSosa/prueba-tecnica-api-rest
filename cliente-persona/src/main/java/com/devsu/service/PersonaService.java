package com.devsu.service;

import com.devsu.domain.Persona;
import com.devsu.repository.PersonaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> obtenerTodasLasPersonas() {
        return personaRepository.findAll();
    }

    public Optional<Persona> obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id);
    }

    public Persona crearPersona(Persona persona) {
        // Validaciones adicionales aquí (por ejemplo, verificar si la identificación ya existe)
        return personaRepository.save(persona);
    }

    public Persona actualizarPersona(Persona persona) {
        if (personaRepository.existsById(persona.getId())) {
            return personaRepository.save(persona);
        } else {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + persona.getId());
        }
    }

    public void eliminarPersona(Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Persona no encontrada con ID: " + id);
        }
    }
}
