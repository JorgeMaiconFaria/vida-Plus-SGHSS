package com.vidaplus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"paciente", "profissional"})
@Entity
public class Prontuario extends PanacheEntity {
    @ManyToOne
    @JsonBackReference
    public Paciente paciente;

    @ManyToOne
    @JsonBackReference
    public ProfissionalSaude profissional;

    @Lob
    public String descricao;
    public LocalDateTime data = LocalDateTime.now();
}
