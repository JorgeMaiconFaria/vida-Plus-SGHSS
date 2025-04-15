package com.vidaplus.mapper;

import com.vidaplus.dto.ProfissionalSaudeDTO;
import com.vidaplus.model.ProfissionalSaude;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ProfissionalSaudeMapper {

    public static ProfissionalSaudeDTO toDTO(ProfissionalSaude entity) {
        if (entity == null) return null;

        ProfissionalSaudeDTO dto = new ProfissionalSaudeDTO();
        dto.id = entity.id;
        dto.nome = entity.nome;
        dto.crmCoren = entity.crmCoren;
        dto.especialidade = entity.especialidade;
        dto.email = entity.email;
        dto.telefone = entity.telefone;
        dto.criadoEm = entity.criadoEm;
        return dto;
    }

    public static ProfissionalSaude toEntity(ProfissionalSaudeDTO dto) {
        if (dto == null) return null;

        ProfissionalSaude entity = new ProfissionalSaude();
        entity.id = dto.id;
        entity.nome = dto.nome;
        entity.crmCoren = dto.crmCoren;
        entity.especialidade = dto.especialidade;
        entity.email = dto.email;
        entity.telefone = dto.telefone;
        entity.criadoEm = dto.criadoEm != null ? dto.criadoEm : LocalDateTime.now();
        return entity;
    }

    public static List<ProfissionalSaudeDTO> toDTOList(List<ProfissionalSaude> entities) {
        return entities.stream().map(ProfissionalSaudeMapper::toDTO).collect(Collectors.toList());
    }
}
