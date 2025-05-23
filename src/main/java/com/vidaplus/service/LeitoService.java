package com.vidaplus.service;

import com.vidaplus.dto.LeitoUpdateDTO;
import com.vidaplus.model.Leito;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.StatusLeito;
import com.vidaplus.repository.LeitoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class LeitoService {

    @Inject
    LeitoRepository leitoRepository;

    public List<Leito> listar() {
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

    public Leito atualizar(Long id, LeitoUpdateDTO dto) {
        Leito leito = leitoRepository.findById(id);

        if (leito != null) {
            if (dto.getStatus() != null) leito.setStatus(StatusLeito.valueOf(dto.getStatus()));
            if (dto.getPacienteId() != null) leito.setPaciente(Paciente.findById(dto.getPacienteId()));
        }

        return leito;
    }
}