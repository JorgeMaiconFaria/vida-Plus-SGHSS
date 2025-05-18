package com.vidaplus;

import com.vidaplus.dto.UsuarioUpdateDTO;
import com.vidaplus.model.Usuario;
import com.vidaplus.repository.UsuarioRepository;
import com.vidaplus.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.InjectMock;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class UsuarioServiceTest {

    @InjectMock
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;

    @Test
    void deveListarUsuarios() {
        List<Usuario> usuarios = Arrays.asList(new Usuario(), new Usuario());
        when(usuarioRepository.listAll()).thenReturn(usuarios);

        List<Usuario> resultado = usuarioService.listar();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveSalvarUsuario() {
        Usuario usuario = new Usuario();

        usuarioService.salvar(usuario);

        verify(usuarioRepository).persist(usuario);
    }

    @Test
    void deveBuscarPorId() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(1L)).thenReturn(usuario);

        Usuario resultado = usuarioService.buscarPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        Usuario usuario = new Usuario();
        usuario.setEmail("original@email.com");
        usuario.setSenha("senha123");

        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
        dto.setEmail("novo@email.com");
        dto.setSenha("novaSenha");
        dto.setRoles(List.of("ADMIN"));

        when(usuarioRepository.findById(1L)).thenReturn(usuario);

        Usuario atualizado = usuarioService.atualizar(1L, dto);

        assertEquals("novo@email.com", atualizado.getEmail());
        assertEquals("novaSenha", atualizado.getSenha());
        assertEquals(List.of("ADMIN"), atualizado.getRoles());
    }

    @Test
    void naoDeveAtualizarSeUsuarioNaoExistir() {
        UsuarioUpdateDTO dto = new UsuarioUpdateDTO();
        dto.setEmail("novo@email.com");

        when(usuarioRepository.findById(1L)).thenReturn(null);

        Usuario resultado = usuarioService.atualizar(1L, dto);

        assertNull(resultado);
    }

    @Test
    void deveDeletarUsuario() {
        usuarioService.deletar(1L);
        verify(usuarioRepository).deleteById(1L);
    }
}
