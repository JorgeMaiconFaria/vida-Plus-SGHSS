package com.vidaplus.service;

import com.vidaplus.dto.ProntuarioUpdateDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.model.Prontuario;
import com.vidaplus.repository.ProntuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProntuarioService {

    @Inject
    ProntuarioRepository prontuarioRepository;

    public List<Prontuario> listar() {
        return prontuarioRepository.listAll();
    }

    public void salvar(Prontuario prontuario) {
        prontuarioRepository.persist(prontuario);
    }

    public Prontuario buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario atualizar(Long id, ProntuarioUpdateDTO dto) {
        Prontuario prontuarioAtualizado = prontuarioRepository.findById(id);

        if(prontuarioAtualizado != null) {
            if(dto.descricao != null) prontuarioAtualizado.descricao = dto.descricao;
            if(dto.data != null) prontuarioAtualizado.data = dto.data;
            if(dto.pacienteId != null) prontuarioAtualizado.paciente = Paciente.findById(dto.pacienteId);
            if(dto.profissionalId != null) prontuarioAtualizado.profissional = ProfissionalSaude.findById(dto.profissionalId);
        }

        return prontuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        prontuarioRepository.deleteById(id);
    }
}
