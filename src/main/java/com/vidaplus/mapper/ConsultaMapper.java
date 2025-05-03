package com.vidaplus.mapper;

import com.vidaplus.dto.ConsultaDTO;
import com.vidaplus.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultaMapper {

    public static ConsultaDTO toDTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.setId(consulta.id);
        dto.setPacienteId(consulta.getPaciente() != null ? consulta.getPaciente().id : null);
        dto.setProfissionalId(consulta.getProfissional() != null ? consulta.getProfissional().id : null);
        dto.setDataHora(consulta.getDataHora());
        dto.setTipo(consulta.getTipo() != null ? String.valueOf(consulta.getTipo()) : null);
        dto.setStatus(consulta.getStatus() != null ? String.valueOf(consulta.getStatus()) : null);
        dto.setCreatedAt(consulta.getCreatedAt());
        return dto;
    }

    public static Consulta toEntity(ConsultaDTO dto) {
        Consulta consulta = new Consulta();
        consulta.setPaciente(dto.getPacienteId() != null ? Paciente.findById(dto.getPacienteId()) : null);
        consulta.setProfissional(dto.getProfissionalId() != null ? ProfissionalSaude.findById(dto.getProfissionalId()) : null);
        consulta.setDataHora(dto.getDataHora());
        consulta.setTipo(dto.getTipo() != null ? TipoConsulta.valueOf(dto.getTipo()) : null);
        consulta.setStatus(dto.getStatus() != null ? StatusConsulta.fromString(dto.getStatus()) : null);
        consulta.setCreatedAt(dto.getCreatedAt());
        return consulta;
    }

    public static List<ConsultaDTO> toDTOList(List<Consulta> consultas) {
        return consultas.stream().map(ConsultaMapper::toDTO).collect(Collectors.toList());
    }
}
