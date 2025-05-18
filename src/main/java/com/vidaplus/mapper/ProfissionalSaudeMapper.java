package com.vidaplus.mapper;

import com.vidaplus.dto.ProfissionalSaudeDTO;
import com.vidaplus.model.ProfissionalSaude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalSaudeMapper {

    public static ProfissionalSaudeDTO toDTO(ProfissionalSaude profissional) {
        if (profissional == null) return null;

        ProfissionalSaudeDTO dto = new ProfissionalSaudeDTO();
        dto.setId(profissional.id);
        dto.setNome(profissional.getNome());
        dto.setCrmCoren(profissional.getCrmCoren());
        dto.setEspecialidade(profissional.getEspecialidade());
        dto.setEmail(profissional.getEmail());
        dto.setTelefone(profissional.getTelefone());
        dto.setCriadoEm(profissional.getCriadoEm());
        return dto;
    }

    public static ProfissionalSaude toEntity(ProfissionalSaudeDTO dto) {
        if (dto == null) return null;

        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.id = dto.getId();
        profissional.setNome(dto.getNome());
        profissional.setCrmCoren(dto.getCrmCoren());
        profissional.setEspecialidade(dto.getEspecialidade());
        profissional.setEmail(dto.getEmail());
        profissional.setTelefone(dto.getTelefone());
        profissional.setCriadoEm(dto.getCriadoEm() != null ? dto.getCriadoEm() : LocalDateTime.now());
        return profissional;
    }

    public static List<ProfissionalSaudeDTO> toDTOList(List<ProfissionalSaude> entities) {
        return entities.stream().map(ProfissionalSaudeMapper::toDTO).collect(Collectors.toList());
    }
}
