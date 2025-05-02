package com.vidaplus.service;

import com.vidaplus.dto.ProntuarioUpdateDTO;
import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.dto.UsuarioUpdateDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.model.Prontuario;
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
            if(dto.email != null) usuarioAtualizado.email = dto.email;
            if(dto.senha != null) usuarioAtualizado.senha = dto.senha;
            if(dto.roles != null) usuarioAtualizado.roles = dto.roles;
        }

        return usuarioRepository.findById(id);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
