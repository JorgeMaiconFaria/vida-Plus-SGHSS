package com.vidaplus.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Getter
@Setter
public class UsuarioDTO {
    @Schema(readOnly = true)
    private Long id;

    @NotBlank(message = "Email é obrigatório.")
    @Schema(description = "Email do Usuário.", example = "teste@email.com", required = true)
    private String email;

    @NotBlank(message = "Senha é obrigatório.")
    @Schema(description = "Senha do Usuário.", required = true)
    private String senha;

    @NotNull(message = "Role(Função) é obrigatória.")
    @Schema(description = "Role(Função) do Usuário.", example = "['ADMIN', 'MEDICO']", required = true)
    private List<String> roles;
}
