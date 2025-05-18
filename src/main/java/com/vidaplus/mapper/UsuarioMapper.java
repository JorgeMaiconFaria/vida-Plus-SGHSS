package com.vidaplus.mapper;

import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.id = dto.getId();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setRoles(dto.getRoles());

        return usuario;
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.id);
        dto.setEmail(usuario.getEmail());
        dto.setSenha(usuario.getSenha());
        dto.setRoles(usuario.getRoles());

        return dto;
    }

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }
}
