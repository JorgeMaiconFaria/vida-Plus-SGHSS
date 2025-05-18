package com.vidaplus;

import com.vidaplus.dto.PacienteUpdateDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.repository.PacienteRepository;
import com.vidaplus.service.PacienteService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class PacienteServiceTest {

    @InjectMock
    PacienteRepository pacienteRepository;

    @Inject
    PacienteService pacienteService;

    @Test
    void deveSalvarPacienteComSucesso() {
        Paciente paciente = new Paciente();
        paciente.setNome("Maria");
        paciente.setCpf("12345678900");
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));

        assertDoesNotThrow(() -> pacienteService.salvar(paciente));

        verify(pacienteRepository, times(1)).persist(paciente);
    }

    @Test
    void naoDeveSalvarPacienteComSucesso() {
        Paciente paciente = new Paciente();
        paciente.setNome("João");
        paciente.setCpf("cpf-invalido");
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));

        doThrow(new RuntimeException("CPF inválido")).when(pacienteRepository).persist((Paciente) any());

        assertThrows(RuntimeException.class, () -> pacienteService.salvar(paciente));
    }

    @Test
    void deveAtualizarPacienteComSucesso() {
        Paciente paciente = new Paciente();
        paciente.setNome("Maria");
        paciente.setCpf("12345678900");
        paciente.setDataNascimento(LocalDate.of(1990, 1, 1));

        pacienteService.salvar(paciente);
        PacienteUpdateDTO dto = new PacienteUpdateDTO();

        assertDoesNotThrow(() -> pacienteService.atualizar(1L, dto));

        verify(pacienteRepository, times(1)).persist(paciente);
    }

    @Test
    void deveDeletarPacienteComSucesso() {
        Long pacienteId = 1L;
        Paciente paciente = new Paciente();
        paciente.id = pacienteId;

        when(pacienteRepository.findById(pacienteId)).thenReturn(paciente);

        pacienteService.deletar(pacienteId);

        verify(pacienteRepository, times(1)).deleteById(pacienteId);
    }

    @Test
    void naoDeveDeletarPacienteQuandoNaoEncontrado() {
        Long pacienteId = 1L;
        when(pacienteRepository.findById(pacienteId)).thenReturn(null);

        pacienteService.deletar(pacienteId);

        verify(pacienteRepository, never()).delete(any());
    }

    @Test
    void deveBuscarPacientePorIdComSucesso() {
        Long pacienteId = 1L;
        Paciente paciente = new Paciente();
        paciente.id = pacienteId;
        when(pacienteRepository.findById(pacienteId)).thenReturn(paciente);

        Paciente resultado = pacienteService.buscarPorId(pacienteId);

        assertNotNull(resultado);
        assertEquals(pacienteId, resultado.id);
        verify(pacienteRepository, times(1)).findById(pacienteId);
    }

    @Test
    void deveListarTodosOsPacientes() {
        List<Paciente> pacientes = List.of(new Paciente(), new Paciente());
        when(pacienteRepository.listAll()).thenReturn(pacientes);

        List<Paciente> resultado = pacienteService.listar();

        assertEquals(2, resultado.size());
        verify(pacienteRepository, times(1)).listAll();
    }
}
