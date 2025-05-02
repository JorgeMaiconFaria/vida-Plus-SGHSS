package com.vidaplus.mapper;

import com.vidaplus.dto.PrescricaoDTO;
import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.model.Prescricao;
import com.vidaplus.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toDTO).collect(Collectors.toList());
    }
}
