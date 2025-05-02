package com.vidaplus.mapper;

import com.vidaplus.dto.ConsultaDTO;
import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.model.Consulta;
import com.vidaplus.model.Leito;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.StatusLeito;

import java.util.List;
import java.util.stream.Collectors;

public class LeitoMapper {

    public static LeitoDTO toDTO(Leito leito) {
        LeitoDTO dto = new LeitoDTO();
        dto.id = leito.id;
        dto.numero = leito.numero;
        dto.status = leito.status.name();
        dto.pacienteId = leito.paciente != null ? leito.paciente.id : null;
        return dto;
    }

    public static Leito toEntity(LeitoDTO dto) {
        Leito leito = new Leito();
        leito.numero = dto.numero;
        leito.status = StatusLeito.valueOf(dto.status.toUpperCase());

        if (dto.pacienteId != null) {
            leito.paciente = Paciente.findById(dto.pacienteId);
        }

        return leito;
    }

    public static void updateEntity(Leito leito, LeitoDTO dto) {
        leito.numero = dto.numero;
        leito.status = StatusLeito.valueOf(dto.status.toUpperCase());

        if (dto.pacienteId != null) {
            leito.paciente = Paciente.findById(dto.pacienteId);
        } else {
            leito.paciente = null;
        }
    }

    public static List<LeitoDTO> toDTOList(List<Leito> leitos) {
        return leitos.stream().map(LeitoMapper::toDTO).collect(Collectors.toList());
    }
}
