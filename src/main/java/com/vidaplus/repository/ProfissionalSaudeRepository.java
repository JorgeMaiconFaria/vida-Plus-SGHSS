package com.vidaplus.repository;

import com.vidaplus.model.ProfissionalSaude;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfissionalSaudeRepository implements PanacheRepository<ProfissionalSaude> {
    // Métodos customizados podem ser adicionados aqui, se necessário
}
