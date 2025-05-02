package com.vidaplus.dto;

import java.time.LocalDateTime;

public class PrescricaoUpdateDTO {
    public String medicamento;
    public String posologia;
    public LocalDateTime dataHora;
    public Long pacienteId;
    public Long profissionalId;
}
