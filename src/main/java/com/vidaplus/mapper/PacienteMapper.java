package com.vidaplus.mapper;

import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.model.Leito;
import com.vidaplus.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PacienteMapper {

    public static PacienteDTO toDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.id = paciente.id;
        dto.nome = paciente.nome;
        dto.dataNascimento = paciente.dataNascimento;
        dto.cpf = paciente.cpf;
        dto.email = paciente.email;
        dto.telefone = paciente.telefone;
        dto.criadoEm = paciente.criadoEm;
        return dto;
    }

    public static Paciente toEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.id = dto.id;
        paciente.nome = dto.nome;
        paciente.dataNascimento = dto.dataNascimento;
        paciente.cpf = dto.cpf;
        paciente.email = dto.email;
        paciente.telefone = dto.telefone;
        paciente.criadoEm = dto.criadoEm != null ? dto.criadoEm : LocalDateTime.now();
        return paciente;
    }

    public static List<PacienteDTO> toDTOList(List<Paciente> pacientes) {
        return pacientes.stream().map(PacienteMapper::toDTO).collect(Collectors.toList());
    }
}
