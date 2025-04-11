package com.vidaplus.service;

import com.vidaplus.model.Consulta;
import com.vidaplus.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.listAll();
    }

    public void salvar(Consulta consulta) {
        consultaRepository.persist(consulta);
    }

    public Consulta buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public void deletar(Long id) {
        consultaRepository.deleteById(id);
    }

    public void atualizarStatus(Long id, Consulta.StatusConsulta novoStatus) {
        Consulta consulta = consultaRepository.findById(id);
        if (consulta != null) {
            consulta.status = novoStatus;
        }
    }
}

