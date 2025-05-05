package com.vidaplus;

import com.vidaplus.dto.ConsutaUpdateDTO;
import com.vidaplus.model.Consulta;
import com.vidaplus.model.StatusConsulta;
import com.vidaplus.repository.ConsultaRepository;
import com.vidaplus.repository.PacienteRepository;
import com.vidaplus.service.ConsultaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @InjectMocks
    ConsultaService consultaService;

    @Mock
    ConsultaRepository consultaRepository;

    @Test
    void deveSalvarConsultaComSucesso() {
        Consulta consulta = new Consulta();
        consultaService.salvar(consulta);
        verify(consultaRepository, times(1)).persist(consulta);
    }

    @Test
    void deveListarConsultasComSucesso() {
        List<Consulta> consultas = Arrays.asList(new Consulta(), new Consulta());
        when(consultaRepository.listAll()).thenReturn(consultas);

        List<Consulta> resultado = consultaService.listar();

        assertEquals(2, resultado.size());
        verify(consultaRepository, times(1)).listAll();
    }

    @Test
    void deveBuscarConsultaPorIdComSucesso() {
        Consulta consulta = new Consulta();
        when(consultaRepository.findById(1L)).thenReturn(consulta);

        Consulta resultado = consultaService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void deveDeletarConsultaComSucesso() {
        consultaService.deletar(1L);
        verify(consultaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveAtualizarStatusComSucesso() {
        Consulta consulta = new Consulta();
        consulta.setStatus(StatusConsulta.AGENDADA);
        ConsutaUpdateDTO dto = new ConsutaUpdateDTO();
        dto.setStatus("REALIZADA");

        when(consultaRepository.findById(1L)).thenReturn(consulta);

        Consulta resultado = consultaService.atualizarStatus(1L, dto);

        assertEquals(StatusConsulta.REALIZADA, resultado.getStatus());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void naoDeveAtualizarStatusQuandoConsultaNaoEncontrada() {
        when(consultaRepository.findById(1L)).thenReturn(null);

        ConsutaUpdateDTO dto = new ConsutaUpdateDTO();
        dto.setStatus("CANCELADA");

        Consulta resultado = consultaService.atualizarStatus(1L, dto);

        assertNull(resultado);
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void naoDeveAtualizarStatusQuandoStatusEhNulo() {
        Consulta consulta = new Consulta();
        consulta.setStatus(StatusConsulta.AGENDADA);
        when(consultaRepository.findById(1L)).thenReturn(consulta);

        ConsutaUpdateDTO dto = new ConsutaUpdateDTO();
        dto.setStatus(null);

        Consulta resultado = consultaService.atualizarStatus(1L, dto);

        assertEquals(StatusConsulta.AGENDADA, resultado.getStatus());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void deveAtualizarDataHoraComSucesso() {
        Consulta consulta = new Consulta();
        LocalDateTime novaDataHora = LocalDateTime.now().plusDays(1);
        ConsutaUpdateDTO dto = new ConsutaUpdateDTO();
        dto.setDataHora(novaDataHora);

        when(consultaRepository.findById(1L)).thenReturn(consulta);

        Consulta resultado = consultaService.atualizarDataHora(1L, dto);

        assertEquals(novaDataHora, resultado.getDataHora());
        verify(consultaRepository, times(1)).findById(1L);
    }

    @Test
    void naoDeveAtualizarDataHoraQuandoConsultaNaoEncontrada() {
        when(consultaRepository.findById(1L)).thenReturn(null);

        ConsutaUpdateDTO dto = new ConsutaUpdateDTO();
        dto.setDataHora(LocalDateTime.now().plusDays(2));

        Consulta resultado = consultaService.atualizarDataHora(1L, dto);

        assertNull(resultado);
        verify(consultaRepository, times(1)).findById(1L);
    }
}
