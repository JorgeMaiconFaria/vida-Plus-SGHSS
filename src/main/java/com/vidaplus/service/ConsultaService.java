package com.vidaplus.service;

import com.vidaplus.dto.ConsutaUpdateDTO;
import com.vidaplus.model.Consulta;
import com.vidaplus.model.StatusConsulta;
import com.vidaplus.repository.ConsultaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ConsultaService {

    @Inject
    ConsultaRepository consultaRepository;

    public List<Consulta> listar() {
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

    public Consulta atualizarStatus(Long id, ConsutaUpdateDTO dto) {
        Consulta consulta = consultaRepository.findById(id);
        if (consulta != null && dto.getStatus() != null) consulta.setStatus(StatusConsulta.valueOf(dto.getStatus()));

        return consulta;
    }

    public Consulta atualizarDataHora(Long id, ConsutaUpdateDTO dto) {
        Consulta consulta = consultaRepository.findById(id);
        if (consulta != null) consulta.setDataHora(dto.getDataHora());

        return consulta;
    }
}

