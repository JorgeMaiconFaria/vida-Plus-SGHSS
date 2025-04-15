package com.vidaplus.service;

import com.vidaplus.model.Leito;
import com.vidaplus.model.StatusLeito;
import com.vidaplus.repository.LeitoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class LeitoService {

    @Inject
    LeitoRepository leitoRepository;

    public List<Leito> listarTodos() {
        return leitoRepository.listAll();
    }

    public void salvar(Leito leito) {
        leitoRepository.persist(leito);
    }

    public Leito buscarPorId(Long id) {
        return leitoRepository.findById(id);
    }

    public void deletar(Long id) {
        leitoRepository.deleteById(id);
    }

    public void atualizarStatus(Long id, StatusLeito novoStatus) {
        Leito leito = leitoRepository.findById(id);
        if (leito != null) {
            leito.status = novoStatus;
        }
    }
}