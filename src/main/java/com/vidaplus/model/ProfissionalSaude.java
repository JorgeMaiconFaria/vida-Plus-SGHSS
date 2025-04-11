package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProfissionalSaude extends PanacheEntity {
    public String nome;
    @Column(unique = true)
    public String crmCoren;
    public String especialidade;
    public String email;
    public String telefone;
    public LocalDateTime criadoEm = LocalDateTime.now();
}