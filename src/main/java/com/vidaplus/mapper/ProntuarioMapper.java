package com.vidaplus.mapper;

import com.vidaplus.dto.ProntuarioDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.Prontuario;
import com.vidaplus.model.ProfissionalSaude;

import java.util.List;
import java.util.stream.Collectors;

public class ProntuarioMapper {

    public static ProntuarioDTO toDTO(Prontuario prontuario) {
        ProntuarioDTO dto = new ProntuarioDTO();
        dto.id = prontuario.id;
        dto.descricao = prontuario.descricao;
        dto.data = prontuario.data;
        dto.pacienteId = prontuario.paciente != null ? prontuario.paciente.id : null;
        dto.profissionalId = prontuario.profissional != null ? prontuario.profissional.id : null;
        return dto;
    }

    public static Prontuario toEntity(ProntuarioDTO dto) {
        Prontuario prontuario = new Prontuario();
        prontuario.descricao = dto.descricao;
        prontuario.data = dto.data;

        prontuario.paciente = dto.pacienteId != null ? Paciente.findById(dto.pacienteId) : null;
        prontuario.profissional = dto.profissionalId != null ? ProfissionalSaude.findById(dto.profissionalId) : null;

        return prontuario;
    }

    public static List<ProntuarioDTO> toDTOList(List<Prontuario> lista) {
        return lista.stream().map(ProntuarioMapper::toDTO).collect(Collectors.toList());
    }
}
