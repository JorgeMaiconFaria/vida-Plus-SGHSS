package com.vidaplus.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Paciente extends PanacheEntity {
    public String nome;
    public LocalDate dataNascimento;

    @Column(unique = true)
    public String cpf;
    public String email;
    public String telefone;

    public LocalDateTime criadoEm = LocalDateTime.now();
}
