package com.vidaplus.service;

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

        if (dto.getNome() != null) paciente.setNome(dto.getNome());
        if (dto.getDataNascimento() != null) paciente.setDataNascimento(dto.getDataNascimento());
        if (dto.getCpf() != null) paciente.setCpf(dto.getCpf());
        if (dto.getEmail() != null) paciente.setEmail(dto.getEmail());
        if (dto.getTelefone() != null) paciente.setTelefone(dto.getTelefone());

        return paciente;
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}