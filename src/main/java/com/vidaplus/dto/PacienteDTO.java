package com.vidaplus.dto;

import com.vidaplus.util.CPF;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class PacienteDTO {
    @Schema(readOnly = true)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Schema(description = "Nome do Paciente.", example = "João da Silva", required = true)
    private String nome;

    @NotNull(message = "Data de nascimento é obrigatória.")
    @PastOrPresent(message = "Deve ser uma data passada ou presente.")
    @Schema(description = "Data de nascimento do Paciente.", example = "1999-12-31", required = true)
    private LocalDate dataNascimento;

    @CPF
    @Schema(description = "CPF do Paciente.", example = "00000000191", required = true)
    private String cpf;

    @Email(message = "Email inválido. Formato esperado: 'teste@email.com'.")
    @Schema(description = "Email do Paciente.", example = "teste@email.com")
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato '(99) 99999-9999'.")
    @Schema(description = "Telefone do Paciente.", example = "(99) 99999-9999")
    private String telefone;

    @Schema(readOnly = true)
    private LocalDateTime criadoEm;
}
