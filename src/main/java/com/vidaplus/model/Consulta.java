package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Consulta extends PanacheEntity {
    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private ProfissionalSaude profissional;
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoConsulta tipo;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    private LocalDateTime createdAt = LocalDateTime.now();
}
