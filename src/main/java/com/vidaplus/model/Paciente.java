package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Paciente extends PanacheEntity {
    public String nome;
    public LocalDate dataNascimento;
    @Column(unique = true)
    public String cpf;
    public String email;
    public String telefone;
    @Lob
    public String historicoMedico;
    public LocalDateTime criadoEm = LocalDateTime.now();
}
