package com.vidaplus.service;

import com.vidaplus.dto.PrescricaoUpdateDTO;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.Prescricao;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.repository.PrescricaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class PrescricaoService {

    @Inject
    PrescricaoRepository prescricaoRepository;

    public List<Prescricao> listar() {
        return prescricaoRepository.listAll();
    }

    public void salvar(Prescricao prescricao) {
        prescricaoRepository.persist(prescricao);
    }

    public Prescricao buscarPorId(Long id) {
        return prescricaoRepository.findById(id);
    }

    public Prescricao atualizar(Long id, PrescricaoUpdateDTO dto) {
        Prescricao prescricao = prescricaoRepository.findById(id);
        if(prescricao != null) {
            if (dto.medicamento != null) prescricao.medicamento = dto.medicamento;
            if (dto.posologia != null) prescricao.posologia = dto.posologia;
            if (dto.dataHora != null) prescricao.dataHora = dto.dataHora;
            if (dto.pacienteId != null) prescricao.paciente = Paciente.findById(dto.pacienteId);
            if (dto.profissionalId != null) prescricao.profissional = ProfissionalSaude.findById(dto.profissionalId);
        }

        return prescricao;
    }

    public void deletar(Long id) {
        prescricaoRepository.deleteById(id);
    }
}
