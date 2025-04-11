package com.vidaplus.service;

import com.vidaplus.model.Prescricao;
import com.vidaplus.repository.PrescricaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PrescricaoService {

    @Inject
    PrescricaoRepository prescricaoRepository;

    public List<Prescricao> listarTodas() {
        return prescricaoRepository.listAll();
    }

    public void salvar(Prescricao prescricao) {
        prescricaoRepository.persist(prescricao);
    }

    public Prescricao buscarPorId(Long id) {
        return prescricaoRepository.findById(id);
    }

    public void deletar(Long id) {
        prescricaoRepository.deleteById(id);
    }
}
