package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PrescricaoDTO {
    @Schema(readOnly = true)
    private Long id;

    @Schema(description = "Nome do medicamento.", example = "Paracetamol", required = true)
    private String medicamento;

    @Schema(description = "Posologia do medicamento.", example = "1 comp. de 8/8 horas.", required = true)
    private String posologia;

    @Schema(description = "Data da validade da receita.", example = "1999-12-31")
    private LocalDateTime dataHora;

    @Schema(description = "ID do paciente receptor.", example = "1", required = true)
    private Long pacienteId;

    @Schema(description = "ID do Médico responsável.", example = "1", required = true)
    private Long profissionalId;
}
