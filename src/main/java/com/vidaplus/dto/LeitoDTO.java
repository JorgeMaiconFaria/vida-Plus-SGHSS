package com.vidaplus.dto;

import java.io.Serializable;

public class LeitoDTO implements Serializable {
    public Long id;
    public String numero;
    public String status; // DISPONIVEL, OCUPADO, MANUTENCAO
    public Long pacienteId; // apenas o ID do paciente
}
