package com.devsu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsu.domain.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}