package com.vidaplus;

import com.vidaplus.dto.PrescricaoUpdateDTO;
import com.vidaplus.model.Prescricao;
import com.vidaplus.repository.PrescricaoRepository;
import com.vidaplus.service.PrescricaoService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class PrescricaoServiceTest {

    @InjectMock
    PrescricaoRepository prescricaoRepository;

    @Inject
    PrescricaoService prescricaoService;

    @Test
    void deveSalvarPrescricaoComSucesso() {
        Prescricao prescricao = new Prescricao();
        prescricao.setMedicamento("Dipirona");
        prescricao.setPosologia("1 comprimido a cada 6 horas");

        prescricaoService.salvar(prescricao);

        verify(prescricaoRepository, times(1)).persist(prescricao);
    }

    @Test
    void deveListarPrescricoesComSucesso() {
        List<Prescricao> lista = Arrays.asList(new Prescricao(), new Prescricao());
        when(prescricaoRepository.listAll()).thenReturn(lista);

        List<Prescricao> resultado = prescricaoService.listar();

        assertEquals(2, resultado.size());
    }

    @Test
    void deveBuscarPrescricaoPorIdComSucesso() {
        Prescricao prescricao = new Prescricao();
        when(prescricaoRepository.findById(1L)).thenReturn(prescricao);

        Prescricao resultado = prescricaoService.buscarPorId(1L);

        assertEquals(prescricao, resultado);
    }

    @Test
    void naoDeveBuscarPrescricaoPorId() {
        when(prescricaoRepository.findById(999L)).thenReturn(null);

        Prescricao resultado = prescricaoService.buscarPorId(999L);
        assertNull(resultado);
    }

    @Test
    void deveAtualizarPrescricaoComSucesso() {
        Prescricao prescricao = new Prescricao();
        PrescricaoUpdateDTO dto = new PrescricaoUpdateDTO();
        dto.setMedicamento("Ibuprofeno");
        dto.setPosologia("2x ao dia");

        when(prescricaoRepository.findById(1L)).thenReturn(prescricao);

        Prescricao atualizado = prescricaoService.atualizar(1L, dto);

        assertEquals("Ibuprofeno", atualizado.getMedicamento());
        assertEquals("2x ao dia", atualizado.getPosologia());
    }

    @Test
    void naoDeveAtualizarPrescricaoQuandoNaoEncontrar() {
        PrescricaoUpdateDTO dto = new PrescricaoUpdateDTO();
        dto.setMedicamento("Novo Medicamento");

        when(prescricaoRepository.findById(999L)).thenReturn(null);

        Prescricao resultado = prescricaoService.atualizar(999L, dto);
        assertNull(resultado);
    }

    @Test
    void deveDeletarPrescricaoComSucesso() {
        prescricaoService.deletar(1L);
        verify(prescricaoRepository, times(1)).deleteById(1L);
    }
}
