package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
