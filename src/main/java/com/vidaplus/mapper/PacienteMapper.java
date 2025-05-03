package com.vidaplus.mapper;

import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {

    public static PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.id);
        dto.setNome(paciente.getNome());
        dto.setDataNascimento(paciente.getDataNascimento());
        dto.setCpf(paciente.getCpf());
        dto.setEmail(paciente.getEmail());
        dto.setTelefone(paciente.getTelefone());
        dto.setCriadoEm(paciente.getCriadoEm());
        return dto;
    }

    public static Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.id = dto.getId();
        paciente.setNome(dto.getNome());
        paciente.setDataNascimento(dto.getDataNascimento());
        paciente.setCpf(dto.getCpf());
        paciente.setEmail(dto.getEmail());
        paciente.setTelefone(dto.getTelefone());
        paciente.setCriadoEm(dto.getCriadoEm() != null ? dto.getCriadoEm() : LocalDateTime.now());
        return paciente;
    }

    public static List<PacienteDTO> toDTOList(List<Paciente> pacientes) {
        return pacientes.stream().map(PacienteMapper::toDTO).collect(Collectors.toList());
    }
}
