package com.vidaplus.repository;

import com.vidaplus.model.Prescricao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PrescricaoRepository implements PanacheRepository<Prescricao> {
}
