package com.vidaplus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Leito extends PanacheEntity {
    @Column(unique = true)
    public String numero;

    @Enumerated(EnumType.STRING)
    public StatusLeito status;

    @ManyToOne
    public Paciente paciente;
}
