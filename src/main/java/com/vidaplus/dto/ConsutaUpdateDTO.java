package com.vidaplus.dto;

import java.time.LocalDateTime;

public class ConsutaUpdateDTO {
    public Long pacienteId;
    public Long profissionalId;
    public LocalDateTime dataHora;
    public String tipo;   // PRESENCIAL, ONLINE
    public String status; // AGENDADA, REALIZADA, CANCELADA
}
