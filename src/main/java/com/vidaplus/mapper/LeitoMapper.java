package com.vidaplus.mapper;

import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.model.Leito;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.StatusLeito;

import java.util.List;
import java.util.stream.Collectors;

public class LeitoMapper {

    public static LeitoDTO toDTO(Leito leito) {
        LeitoDTO dto = new LeitoDTO();
        dto.setId(leito.id);
        dto.setNumero(leito.getNumero());
        dto.setStatus(leito.getStatus().name());
        dto.setPacienteId(leito.getPaciente() != null ? leito.getPaciente().id : null);
        return dto;
    }

    public static Leito toEntity(LeitoDTO dto) {
        Leito leito = new Leito();
        leito.setNumero(dto.getNumero());
        leito.setStatus(StatusLeito.valueOf(dto.getStatus().toUpperCase()));

        if (dto.getPacienteId() != null) {
            leito.setPaciente(Paciente.findById(dto.getPacienteId()));
        }

        return leito;
    }

    public static void updateEntity(Leito leito, LeitoDTO dto) {
        leito.setNumero(dto.getNumero());
        leito.setStatus(StatusLeito.valueOf(dto.getStatus().toUpperCase()));

        if (dto.getPacienteId() != null) {
            leito.setPaciente(Paciente.findById(dto.getPacienteId()));
        } else {
            leito.setPaciente(null);
        }
    }

    public static List<LeitoDTO> toDTOList(List<Leito> leitos) {
        return leitos.stream().map(LeitoMapper::toDTO).collect(Collectors.toList());
    }
}
