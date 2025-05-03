package com.vidaplus.mapper;

import com.vidaplus.dto.PrescricaoDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.model.Prescricao;

import java.util.List;
import java.util.stream.Collectors;

public class PrescricaoMapper {

    public static Prescricao toEntity(PrescricaoDTO dto) {
        Prescricao prescricao = new Prescricao();
        prescricao.id = dto.getId();
        prescricao.setMedicamento(dto.getMedicamento());
        prescricao.setPosologia(dto.getPosologia());
        prescricao.setDataHora(dto.getDataHora());

        if (dto.getPacienteId() != null) {
            prescricao.setPaciente(Paciente.findById(dto.getPacienteId()));
        }

        if (dto.getProfissionalId() != null) {
            prescricao.setProfissional(ProfissionalSaude.findById(dto.getProfissionalId()));
        }

        return prescricao;
    }

    public static PrescricaoDTO toDTO(Prescricao p) {
        PrescricaoDTO dto = new PrescricaoDTO();
        dto.setId(p.id);
        dto.setMedicamento(p.getMedicamento());
        dto.setPosologia(p.getPosologia());
        dto.setDataHora(p.getDataHora());
        dto.setPacienteId(p.getPaciente() != null ? p.getPaciente().id : null);
        dto.setProfissionalId(p.getProfissional() != null ? p.getProfissional().id : null);
        return dto;
    }

    public static List<PrescricaoDTO> toDTOList(List<Prescricao> prescricoes) {
        return prescricoes.stream().map(PrescricaoMapper::toDTO).collect(Collectors.toList());
    }
}
