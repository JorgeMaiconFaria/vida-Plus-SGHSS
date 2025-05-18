package com.vidaplus.service;

import com.vidaplus.dto.UsuarioUpdateDTO;
import com.vidaplus.model.Usuario;
import com.vidaplus.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        return usuarioRepository.listAll();
    }

    public void salvar(Usuario usuario) {
        usuarioRepository.persist(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario atualizar(Long id, UsuarioUpdateDTO dto) {
        Usuario usuarioAtualizado = usuarioRepository.findById(id);

        if(usuarioAtualizado != null) {
            if(dto.getEmail() != null) usuarioAtualizado.setEmail(dto.getEmail());
            if(dto.getSenha() != null) usuarioAtualizado.setSenha(dto.getSenha());
            if(dto.getRoles() != null) usuarioAtualizado.setRoles(dto.getRoles());
        }

        return usuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
