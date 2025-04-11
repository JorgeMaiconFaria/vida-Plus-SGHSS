package com.vidaplus.service;

import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.repository.ProfissionalSaudeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProfissionalSaudeService {

    @Inject
    ProfissionalSaudeRepository profissionalRepository;

    public List<ProfissionalSaude> listarTodos() {
        return profissionalRepository.listAll();
    }

    public void salvar(ProfissionalSaude profissional) {
        profissionalRepository.persist(profissional);
    }

    public ProfissionalSaude buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
