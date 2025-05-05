package com.vidaplus;

import com.vidaplus.dto.ProfissionalSaudeUpdateDTO;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.repository.ProfissionalSaudeRepository;
import com.vidaplus.service.ProfissionalSaudeService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
public class ProfissionalSaudeServiceTest {

    @InjectMock
    ProfissionalSaudeRepository profissionalRepository;

    @Inject
    ProfissionalSaudeService profissionalService;

    @Test
    void deveSalvarProfissionalComSucesso() {
        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setNome("Dra. Ana");
        profissional.setCrmCoren("CRM1234");
        profissional.setEspecialidade("Cardiologia");

        profissionalService.salvar(profissional);

        verify(profissionalRepository, times(1)).persist(profissional);
    }

    @Test
    void naoDeveSalvarProfissionalComSucessoQuandoLancaExcecao() {
        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setNome("Erro");

        doThrow(new RuntimeException("Erro ao salvar")).when(profissionalRepository).persist(profissional);

        assertThrows(RuntimeException.class, () -> profissionalService.salvar(profissional));
        verify(profissionalRepository, times(1)).persist(profissional);
    }

    @Test
    void deveListarProfissionaisComSucesso() {
        List<ProfissionalSaude> lista = List.of(new ProfissionalSaude(), new ProfissionalSaude());
        when(profissionalRepository.listAll()).thenReturn(lista);

        List<ProfissionalSaude> resultado = profissionalService.listar();

        assertEquals(2, resultado.size());
        verify(profissionalRepository, times(1)).listAll();
    }

    @Test
    void deveBuscarProfissionalPorIdComSucesso() {
        ProfissionalSaude profissional = new ProfissionalSaude();
        when(profissionalRepository.findById(1L)).thenReturn(profissional);

        ProfissionalSaude resultado = profissionalService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(profissionalRepository, times(1)).findById(1L);
    }

    @Test
    void deveAtualizarProfissionalComSucesso() {
        ProfissionalSaude profissional = new ProfissionalSaude();
        profissional.setNome("Antigo");

        ProfissionalSaudeUpdateDTO dto = new ProfissionalSaudeUpdateDTO();
        dto.setNome("Novo");

        when(profissionalRepository.findById(1L)).thenReturn(profissional);

        ProfissionalSaude atualizado = profissionalService.atualizar(1L, dto);

        assertEquals("Novo", atualizado.getNome());
        verify(profissionalRepository, times(1)).findById(1L);
    }

    @Test
    void naoDeveAtualizarProfissionalComSucessoQuandoNaoEncontrado() {
        when(profissionalRepository.findById(1L)).thenReturn(null);

        ProfissionalSaudeUpdateDTO dto = new ProfissionalSaudeUpdateDTO();
        dto.setNome("Nome Inválido");

        ProfissionalSaude resultado = profissionalService.atualizar(1L, dto);

        assertNull(resultado);
        verify(profissionalRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarProfissionalComSucesso() {
        profissionalService.deletar(1L);

        verify(profissionalRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveChamarDeleteMesmoQuandoIdNaoExiste() {
        profissionalService.deletar(999L);
        verify(profissionalRepository, times(1)).deleteById(999L);
    }
}
