package com.vidaplus.dto;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
public class ConsultaDTO {
    @Schema(readOnly = true)
    private Long id;

    @Schema(description = "ID do Paciente.", example = "1", required = true)
    private Long pacienteId;

    @Schema(description = "ID do Médico.", example = "1", required = true)
    private Long profissionalId;

    @FutureOrPresent(message = "Deve ser uma data passada ou presente.")
    @Schema(description = "Data e hora da consulta.", example = "2022-03-10T12:15:50", required = true)
    private LocalDateTime dataHora;

    @Schema(description = "Tipo da consulta.", example = "PRESENCIAL, TELEMEDICINA", required = true)
    private String tipo;

    @Schema(description = "Status da consulta.", example = "AGENDADA, CANCELADA, REALIZADA", required = true)
    private String status;

    @Schema(readOnly = true)
    private LocalDateTime createdAt;
}
