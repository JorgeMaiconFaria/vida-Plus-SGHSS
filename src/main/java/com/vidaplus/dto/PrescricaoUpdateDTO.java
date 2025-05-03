package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
public class PrescricaoUpdateDTO {
    @Schema(description = "Nome do medicamento.", example = "Paracetamol")
    private String medicamento;

    @Schema(description = "Posologia do medicamento.", example = "1 comp. de 8/8 horas.")
    private String posologia;

    @Schema(description = "Data da validade da receita.", example = "1999-12-31")
    private LocalDateTime dataHora;

    @Schema(description = "ID do paciente receptor.", example = "1")
    private Long pacienteId;

    @Schema(description = "ID do Médico responsável.", example = "1")
    private Long profissionalId;
}
