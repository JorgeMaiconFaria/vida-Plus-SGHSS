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
            if(dto.getDescricao() != null) prontuarioAtualizado.setDescricao(dto.getDescricao());
            if(dto.getData() != null) prontuarioAtualizado.setData(dto.getData());
            if(dto.getPacienteId() != null) prontuarioAtualizado.setPaciente(Paciente.findById(dto.getPacienteId()));
            if(dto.getProfissionalId() != null) prontuarioAtualizado.setProfissional(ProfissionalSaude.findById(dto.getProfissionalId()));
        }

        return prontuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        prontuarioRepository.deleteById(id);
    }
}
