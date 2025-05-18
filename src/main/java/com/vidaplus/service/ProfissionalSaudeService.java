package com.vidaplus.service;

import com.vidaplus.dto.ProfissionalSaudeUpdateDTO;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.repository.ProfissionalSaudeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProfissionalSaudeService {

    @Inject
    ProfissionalSaudeRepository profissionalRepository;

    public List<ProfissionalSaude> listar() {
        return profissionalRepository.listAll();
    }

    public void salvar(ProfissionalSaude profissional) {
        profissionalRepository.persist(profissional);
    }

    public ProfissionalSaude buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public  ProfissionalSaude atualizar(Long id, ProfissionalSaudeUpdateDTO dto) {
        ProfissionalSaude profissionalSaude = profissionalRepository.findById(id);

        if(profissionalSaude != null) {
            if (dto.getNome() != null) profissionalSaude.setNome(dto.getNome());
            if (dto.getCrmCoren() != null) profissionalSaude.setCrmCoren(dto.getCrmCoren());
            if (dto.getEspecialidade() != null) profissionalSaude.setEspecialidade(dto.getEspecialidade());
            if (dto.getTelefone() != null) profissionalSaude.setTelefone(dto.getTelefone());
        }

        return profissionalSaude;
    }

    public void deletar(Long id) {
        profissionalRepository.deleteById(id);
    }
}
