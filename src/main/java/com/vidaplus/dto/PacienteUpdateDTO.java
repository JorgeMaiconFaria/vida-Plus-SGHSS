package com.vidaplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

@Getter
@Setter
public class PacienteUpdateDTO {

    @Schema(description = "Nome do Paciente.", example = "João da Silva")
    private String nome;

    @Schema(description = "Data de nascimento do Paciente.", example = "1999-12-31")
    private LocalDate dataNascimento;

    @Schema(description = "CPF do Paciente.", example = "00000000191")
    private String cpf;

    @Email(message = "Email inválido. Formato esperado: 'teste@email.com'.")
    @Schema(description = "Email do Paciente.", example = "teste@email.com")
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato '(99) 99999-9999'.")
    @Schema(description = "Telefone do Paciente.", example = "(99) 99999-9999")
    private String telefone;
}
