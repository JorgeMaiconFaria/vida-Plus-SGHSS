package com.vidaplus;

import com.vidaplus.dto.LeitoUpdateDTO;
import com.vidaplus.model.Leito;
import com.vidaplus.model.StatusLeito;
import com.vidaplus.repository.LeitoRepository;
import com.vidaplus.repository.PacienteRepository;
import com.vidaplus.service.LeitoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LeitoServiceTest {

    @InjectMocks
    LeitoService leitoService;

    @Mock
    LeitoRepository leitoRepository;

    @Mock
    PacienteRepository pacienteRepository;

    @Test
    void deveSalvarLeitoComSucesso() {
        Leito leito = new Leito();
        leito.setStatus(StatusLeito.DISPONIVEL);

        leitoService.salvar(leito);

        verify(leitoRepository, times(1)).persist(leito);
    }

    @Test
    void deveListarLeitosComSucesso() {
        List<Leito> leitos = List.of(new Leito(), new Leito());
        when(leitoRepository.listAll()).thenReturn(leitos);

        List<Leito> resultado = leitoService.listar();

        assertEquals(2, resultado.size());
        verify(leitoRepository, times(1)).listAll();
    }

    @Test
    void deveBuscarLeitoPorIdComSucesso() {
        Leito leito = new Leito();
        when(leitoRepository.findById(1L)).thenReturn(leito);

        Leito resultado = leitoService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(leitoRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarLeitoComSucesso() {
        leitoService.deletar(1L);

        verify(leitoRepository, times(1)).deleteById(1L);
    }

    @Test
    void naoDeveAtualizarLeito_QuandoStatusInvalido() {
        Long leitoId = 1L;
        Leito leito = new Leito();
        leito.id =leitoId;

        LeitoUpdateDTO dto = new LeitoUpdateDTO();
        dto.setStatus("INVALIDO");

        when(leitoRepository.findById(leitoId)).thenReturn(leito);

        assertThrows(IllegalArgumentException.class, () -> {
            leitoService.atualizar(leitoId, dto);
        });

        verify(leitoRepository).findById(leitoId);
    }

    @Test
    void naoDeveAtualizarLeito_QuandoLeitoNaoEncontrado() {
        Long leitoId = 1L;
        LeitoUpdateDTO dto = new LeitoUpdateDTO();
        dto.setStatus("OCUPADO");
        dto.setPacienteId(2L);

        when(leitoRepository.findById(leitoId)).thenReturn(null);

        Leito resultado = leitoService.atualizar(leitoId, dto);

        assertNull(resultado);
        verify(leitoRepository, times(1)).findById(leitoId);
        verifyNoInteractions(pacienteRepository);
    }
}

