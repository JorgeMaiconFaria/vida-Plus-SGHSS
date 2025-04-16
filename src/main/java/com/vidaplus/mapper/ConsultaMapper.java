package com.vidaplus.mapper;

import com.vidaplus.dto.ConsultaDTO;
import com.vidaplus.model.Consulta;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultaMapper {

    public static ConsultaDTO toDTO(Consulta consulta) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.id = consulta.id;
        dto.pacienteId = consulta.paciente != null ? consulta.paciente.id : null;
        dto.profissionalId = consulta.profissional != null ? consulta.profissional.id : null;
        dto.dataHora = consulta.dataHora;
        dto.tipo = consulta.tipo != null ? consulta.tipo.name() : null;
        dto.status = consulta.status != null ? consulta.status.name() : null;
        dto.createdAt = consulta.createdAt;
        return dto;
    }

    public static Consulta toEntity(ConsultaDTO dto) {
        Consulta consulta = new Consulta();
        consulta.paciente = dto.pacienteId != null ? Paciente.findById(dto.pacienteId) : null;
        consulta.profissional = dto.profissionalId != null ? ProfissionalSaude.findById(dto.profissionalId) : null;
        consulta.dataHora = dto.dataHora;
        consulta.tipo = dto.tipo != null ? Consulta.TipoConsulta.valueOf(dto.tipo.toUpperCase()) : null;
        consulta.status = dto.status != null ? Consulta.StatusConsulta.valueOf(dto.status.toUpperCase()) : null;
        consulta.createdAt = dto.createdAt;
        return consulta;
    }

    public static List<ConsultaDTO> toDTOList(List<Consulta> consultas) {
        return consultas.stream().map(ConsultaMapper::toDTO).collect(Collectors.toList());
    }
}
