package com.vidaplus.mapper;

import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.dto.PrescricaoDTO;
import com.vidaplus.model.Leito;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.model.Prescricao;

import java.util.List;
import java.util.stream.Collectors;

public class PrescricaoMapper {

    public static Prescricao toEntity(PrescricaoDTO dto) {
        Prescricao p = new Prescricao();
        p.id = dto.id;
        p.medicamento = dto.medicamento;
        p.posologia = dto.posologia;
        p.dataHora = dto.dataHora;

        if (dto.pacienteId != null) {
            p.paciente = Paciente.findById(dto.pacienteId);
        }

        if (dto.profissionalId != null) {
            p.profissional = ProfissionalSaude.findById(dto.profissionalId);
        }

        return p;
    }

    public static PrescricaoDTO toDTO(Prescricao p) {
        PrescricaoDTO dto = new PrescricaoDTO();
        dto.id = p.id;
        dto.medicamento = p.medicamento;
        dto.posologia = p.posologia;
        dto.dataHora = p.dataHora;
        dto.pacienteId = p.paciente != null ? p.paciente.id : null;
        dto.profissionalId = p.profissional != null ? p.profissional.id : null;
        return dto;
    }

    public static List<PrescricaoDTO> toDTOList(List<Prescricao> prescricoes) {
        return prescricoes.stream().map(PrescricaoMapper::toDTO).collect(Collectors.toList());
    }
}
