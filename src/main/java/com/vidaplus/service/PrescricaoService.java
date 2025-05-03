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
            if (dto.getMedicamento() != null) prescricao.setMedicamento(dto.getMedicamento());
            if (dto.getPosologia() != null) prescricao.setPosologia(dto.getPosologia());
            if (dto.getDataHora() != null) prescricao.setDataHora(dto.getDataHora());
            if (dto.getPacienteId() != null) prescricao.setPaciente(Paciente.findById(dto.getPacienteId()));
            if (dto.getProfissionalId() != null) prescricao.setProfissional(ProfissionalSaude.findById(dto.getProfissionalId()));
        }

        return prescricao;
    }

    public void deletar(Long id) {
        prescricaoRepository.deleteById(id);
    }
}
