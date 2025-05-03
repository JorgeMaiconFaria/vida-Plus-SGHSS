package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

@Getter
@Setter
public class LeitoDTO implements Serializable {
    @Schema(readOnly = true)
    private Long id;

    @Schema(description = "Número do leito.", example = "10", required = true)
    private String numero;

    @Schema(description = "Status do leito.", example = "DISPONIVEL, OCUPADO, MANUTENCAO", required = true)
    private String status;

    @Schema(description = "ID do Paciente ocupante do leito.", example = "1", required = true)
    private Long pacienteId;
}
