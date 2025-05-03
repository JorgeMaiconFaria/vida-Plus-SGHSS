package com.vidaplus.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Getter
@Setter
public class ProfissionalSaude extends PanacheEntity {
    private String nome;

    @Column(unique = true)
    private String crmCoren;

    private String especialidade;
    private String email;
    private String telefone;
    private LocalDateTime criadoEm = LocalDateTime.now();
}