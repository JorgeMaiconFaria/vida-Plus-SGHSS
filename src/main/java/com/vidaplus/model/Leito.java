package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Leito extends PanacheEntity {
    @Column(unique = true)
    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusLeito status;

    @ManyToOne
    private Paciente paciente;
}
