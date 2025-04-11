package com.vidaplus.repository;

import com.vidaplus.model.Prontuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProntuarioRepository implements PanacheRepository<Prontuario> {
}

