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
        dto.setId(prontuario.id);
        dto.setDescricao(prontuario.getDescricao());
        dto.setData(prontuario.getData());
        dto.setPacienteId(prontuario.getPaciente() != null ? prontuario.getPaciente().id : null);
        dto.setProfissionalId(prontuario.getProfissional() != null ? prontuario.getProfissional().id : null);

        return dto;
    }

    public static Prontuario toEntity(ProntuarioDTO dto) {
        Prontuario prontuario = new Prontuario();
        prontuario.setDescricao(dto.getDescricao());
        prontuario.setData(dto.getData());
        prontuario.setPaciente(dto.getPacienteId() != null ? Paciente.findById(dto.getPacienteId()) : null);
        prontuario.setProfissional(dto.getProfissionalId() != null ? ProfissionalSaude.findById(dto.getProfissionalId()) : null);

        return prontuario;
    }

    public static List<ProntuarioDTO> toDTOList(List<Prontuario> lista) {
        return lista.stream().map(ProntuarioMapper::toDTO).collect(Collectors.toList());
    }
}
