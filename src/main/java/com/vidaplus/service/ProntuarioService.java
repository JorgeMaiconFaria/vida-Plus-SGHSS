package com.vidaplus.service;

import com.vidaplus.model.Prontuario;
import com.vidaplus.repository.ProntuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProntuarioService {

    @Inject
    ProntuarioRepository prontuarioRepository;

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.listAll();
    }

    public void salvar(Prontuario prontuario) {
        prontuarioRepository.persist(prontuario);
    }

    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        prontuarioRepository.deleteById(id);
    }
}
