package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Consulta extends PanacheEntity {
    @ManyToOne
    public Paciente paciente;

    @ManyToOne
    public ProfissionalSaude profissional;
    public LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    public TipoConsulta tipo;

    @Enumerated(EnumType.STRING)
    public StatusConsulta status;

    public LocalDateTime createdAt = LocalDateTime.now();
}
