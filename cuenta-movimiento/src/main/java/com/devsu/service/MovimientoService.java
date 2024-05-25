package com.devsu.service;

import com.devsu.domain.Movimientos;
import com.devsu.repository.MovimientosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientosRepository movimientosRepository;

    public List<Movimientos> findAll() {
        return movimientosRepository.findAll();
    }

    public Optional<Movimientos> findById(Long id) {
        return movimientosRepository.findById(id);
    }

    public Movimientos save(Movimientos movimientos) {
        return movimientosRepository.save(movimientos);
    }

    public void deleteById(Long id) {
        movimientosRepository.deleteById(id);
    }
}
