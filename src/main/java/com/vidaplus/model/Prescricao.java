package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Prescricao extends PanacheEntity {
    private String medicamento;
    private String posologia;
    private LocalDateTime dataHora;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private ProfissionalSaude profissional;
}
