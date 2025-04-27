package com.vidaplus.mapper;

import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.email = dto.email;
        usuario.senha = dto.senha;
        usuario.roles = dto.roles;
        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.email = usuario.email;
        dto.senha = usuario.senha;
        dto.roles = usuario.roles;
        return dto;
    }
}
