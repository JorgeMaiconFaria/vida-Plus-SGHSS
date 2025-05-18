package com.vidaplus.dto;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Getter
@Setter
public class UsuarioUpdateDTO {
    @Schema(description = "Email do Usuário.", example = "teste@email.com")
    private String email;

    @Schema(description = "Senha do Usuário.")
    private String senha;

    @Schema(description = "Role(Função) do Usuário.", example = "['ADMIN', 'MEDICO']")
    private List<String> roles;
}
