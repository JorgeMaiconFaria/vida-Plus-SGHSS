package com.vidaplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfissionalSaudeDTO {
    @Schema(readOnly = true)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Schema(description = "Nome do Profissional.", example = "João da Silva.", required = true)
    private String nome;

    @NotBlank(message = "CRM-COREN é obrigatório.")
    @Schema(description = "Número do CRM ou COREN.", example = "'CRM/SP 123456' ou 'COREN-SC 000.000-TE'", required = true)
    private String crmCoren;

    @NotBlank(message = "Especialidade é obrigatória.")
    @Schema(description = "Especialidade do Profissinal.", example = "Otorrinolaringologista", required = true)
    private String especialidade;

    @NotBlank(message = "Email é obrigatório.")
    @Email(message = "Email inválido. Formato esperado: 'teste@email.com'.")
    @Schema(description = "Email do Profissinal.", example = "teste@email.com", required = true)
    private String email;

    @NotBlank(message = "Telefone é obrigatório.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato '(99) 99999-9999'.")
    @Schema(description = "Telefone do Profissinal.", example = "(99) 99999-9999", required = true)
    private String telefone;

    @Schema(readOnly = true)
    private LocalDateTime criadoEm;
}
