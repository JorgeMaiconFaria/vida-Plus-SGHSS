package com.vidaplus.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PacienteDTO {
    public Long id;
    public String nome;
    public LocalDate dataNascimento;
    public String cpf;
    public String email;
    public String telefone;
    public LocalDateTime criadoEm;
}
