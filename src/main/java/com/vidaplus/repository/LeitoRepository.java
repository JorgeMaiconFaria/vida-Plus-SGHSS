package com.vidaplus.repository;

import com.vidaplus.model.Leito;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LeitoRepository implements PanacheRepository<Leito> {
}
