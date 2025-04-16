package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Prescricao extends PanacheEntity {
    public String medicamento;
    public String posologia;
    public LocalDateTime dataHora;

    @ManyToOne
    public Paciente paciente;

    @ManyToOne
    public ProfissionalSaude profissional;
}
