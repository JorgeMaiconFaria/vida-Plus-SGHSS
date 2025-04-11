package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Prescricao extends PanacheEntity {
    @ManyToOne
    public Consulta consulta;

    public String medicamento;
    public String dosagem;
    @Lob
    public String instrucoes;
    public LocalDate dataPrescricao;

    public LocalDateTime createdAt = LocalDateTime.now();
}
