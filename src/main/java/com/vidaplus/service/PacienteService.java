package com.vidaplus.service;

import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.dto.PacienteUpdateDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.repository.PacienteRepository;
import jakarta.enterprise.context.*;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    public List<Paciente> listar() {
        return pacienteRepository.listAll();
    }

    public void salvar(Paciente paciente) {
        pacienteRepository.persist(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente atualizar(Long id, PacienteUpdateDTO dto) {
        Paciente paciente = buscarPorId(id);
        if (paciente == null) {
            return null;
        }

        if (dto.nome != null) paciente.nome = dto.nome;
        if (dto.dataNascimento != null) paciente.dataNascimento = dto.dataNascimento;
        if (dto.cpf != null) paciente.cpf = dto.cpf;
        if (dto.email != null) paciente.email = dto.email;
        if (dto.telefone != null) paciente.telefone = dto.telefone;

        return paciente;
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}