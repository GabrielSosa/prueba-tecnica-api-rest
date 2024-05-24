package com.devsu.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long clienteId;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private boolean estado; // true: activo, false: inactivo

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personaId", referencedColumnName = "id", unique = true)
    private Persona persona;

}