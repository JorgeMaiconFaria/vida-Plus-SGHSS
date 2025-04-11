package com.vidaplus.service;

import com.vidaplus.model.Paciente;
import com.vidaplus.repository.PacienteRepository;
import jakarta.enterprise.context.*;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PacienteService {

    @Inject
    PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.listAll();
    }

    public void salvar(Paciente paciente) {
        pacienteRepository.persist(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}