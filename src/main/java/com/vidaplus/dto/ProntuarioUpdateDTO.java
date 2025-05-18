package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProntuarioUpdateDTO {
    @Schema(description = "Conteúdo do prontuário.", example = "Cirurgia para remoção da apêndice.")
    private String descricao;

    @Schema(description = "Data da abertura do prontuário.", example = "2022-03-10T12:15:50")
    private LocalDateTime data;

    @Schema(description = "ID do Paciente.", example = "1")
    private Long pacienteId;

    @Schema(description = "ID do Profissinal da saúde.", example = "1")
    private Long profissionalId;
}
