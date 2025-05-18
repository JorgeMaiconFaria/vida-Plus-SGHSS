package com.vidaplus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
public class ProfissionalSaudeUpdateDTO {
    @NotBlank(message = "Nome é obrigatório.")
    @Schema(description = "Nome do Profissional.", example = "João da Silva.")
    private String nome;

    @NotBlank(message = "CRM-COREN é obrigatório.")
    @Schema(description = "Número do CRM ou COREN.", example = "'CRM/SP 123456' ou 'COREN-SC 000.000-TE'")
    private String crmCoren;

    @Schema(description = "Especialidade do Profissinal.", example = "Otorrinolaringologista")
    private String especialidade;

    @Email(message = "Email inválido. Formato esperado: 'teste@email.com'.")
    @Schema(description = "Email do Profissinal.", example = "teste@email.com")
    private String email;

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Telefone deve estar no formato '(99) 99999-9999'.")
    @Schema(description = "Telefone do Profissinal.", example = "(99) 99999-9999")
    private String telefone;
}
