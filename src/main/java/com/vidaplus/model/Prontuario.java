package com.vidaplus.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties({"paciente", "profissional"})
@Entity
@Getter
@Setter
public class Prontuario extends PanacheEntity {
    @ManyToOne
    @JsonBackReference
    private Paciente paciente;

    @ManyToOne
    @JsonBackReference
    private ProfissionalSaude profissional;

    @Lob
    private String descricao;
    private LocalDateTime data = LocalDateTime.now();
}
