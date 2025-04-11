package com.vidaplus.repository;

import com.vidaplus.model.Paciente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PacienteRepository implements PanacheRepository<Paciente> {
    // Métodos customizados podem ser adicionados aqui, se necessário
}
