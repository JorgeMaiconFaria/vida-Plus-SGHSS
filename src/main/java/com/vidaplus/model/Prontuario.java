package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Prontuario extends PanacheEntity {
    @ManyToOne
    public Paciente paciente;

    @ManyToOne
    public ProfissionalSaude profissional;

    @Lob
    public String descricao;

    public LocalDateTime data = LocalDateTime.now();
}
