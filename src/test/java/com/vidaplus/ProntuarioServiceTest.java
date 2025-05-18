package com.vidaplus;

import com.vidaplus.dto.ProntuarioUpdateDTO;
import com.vidaplus.model.Prontuario;
import com.vidaplus.repository.ProntuarioRepository;
import com.vidaplus.service.ProntuarioService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class ProntuarioServiceTest {

    @Inject
    ProntuarioService prontuarioService;

    @InjectMock
    ProntuarioRepository prontuarioRepository;

    @Test
    void deveListarProntuarios() {
        Prontuario p1 = new Prontuario();
        Prontuario p2 = new Prontuario();
        when(prontuarioRepository.listAll()).thenReturn(Arrays.asList(p1, p2));

        List<Prontuario> lista = prontuarioService.listar();

        assertEquals(2, lista.size());
    }

    @Test
    void deveSalvarProntuario() {
        Prontuario prontuario = new Prontuario();

        prontuarioService.salvar(prontuario);

        verify(prontuarioRepository).persist(prontuario);
    }

    @Test
    void deveBuscarProntuarioPorId() {
        Prontuario prontuario = new Prontuario();
        when(prontuarioRepository.findById(1L)).thenReturn(prontuario);

        Prontuario resultado = prontuarioService.buscarPorId(1L);

        assertNotNull(resultado);
    }

    @Test
    void naoDeveBuscarProntuarioPorId() {
        when(prontuarioRepository.findById(123L)).thenReturn(null);

        Prontuario resultado = prontuarioService.buscarPorId(123L);

        assertNull(resultado);
    }

    @Test
    void deveAtualizarProntuarioComSucesso() {
        Prontuario prontuario = new Prontuario();
        prontuario.setDescricao("Antiga");

        ProntuarioUpdateDTO dto = new ProntuarioUpdateDTO();
        dto.setDescricao("Nova");
        dto.setData(LocalDateTime.now());

        when(prontuarioRepository.findById(1L)).thenReturn(prontuario);

        Prontuario atualizado = prontuarioService.atualizar(1L, dto);

        assertEquals("Nova", atualizado.getDescricao());
        assertEquals(dto.getData(), atualizado.getData());
    }

    @Test
    void naoDeveAtualizarProntuarioInexistente() {
        when(prontuarioRepository.findById(999L)).thenReturn(null);

        ProntuarioUpdateDTO dto = new ProntuarioUpdateDTO();
        dto.setDescricao("Nova descrição");

        Prontuario resultado = prontuarioService.atualizar(999L, dto);

        assertNull(resultado);
    }

    @Test
    void deveDeletarProntuario() {
        prontuarioService.deletar(1L);

        verify(prontuarioRepository).deleteById(1L);
    }

    @Test
    void deveTentarDeletarMesmoSeNaoExiste() {
        prontuarioService.deletar(404L);

        verify(prontuarioRepository).deleteById(404L);
    }
}
