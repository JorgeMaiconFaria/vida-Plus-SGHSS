package com.vidaplus.dto;

import java.time.LocalDateTime;

public class PrescricaoDTO {
    public Long id;
    public String medicamento;
    public String posologia;
    public LocalDateTime dataHora;
    public Long pacienteId;
    public Long profissionalId;
}
