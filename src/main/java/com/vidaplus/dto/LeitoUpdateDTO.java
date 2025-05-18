package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class LeitoUpdateDTO {
    @Schema(description = "Status do leito.", example = "DISPONIVEL, OCUPADO, MANUTENCAO")
    private String status;

    @Schema(description = "ID do Paciente ocupante do leito.", example = "1")
    private Long pacienteId;
}
