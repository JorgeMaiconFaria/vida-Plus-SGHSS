package com.vidaplus.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vidaplus.model.Consulta;
import com.vidaplus.model.StatusConsulta;
import com.vidaplus.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
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

    @JsonProperty("statusConsulta")
    public void atualizarStatus(Long id, StatusConsulta novoStatus) {
        Consulta consulta = consultaRepository.findById(id);
        if (consulta != null) {
            consulta.status = novoStatus;
        }
    }

    public void atualizarDataHora(Long id, LocalDateTime novaDataHora) {
        Consulta consulta = Consulta.findById(id);
        if (consulta != null) {
            consulta.dataHora = novaDataHora;
        }
    }
}

