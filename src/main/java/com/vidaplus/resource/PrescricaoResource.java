package com.vidaplus.resource;

import com.vidaplus.dto.PrescricaoDTO;
import com.vidaplus.mapper.PrescricaoMapper;
import com.vidaplus.model.Prescricao;
import com.vidaplus.service.PrescricaoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/prescricoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescricaoResource {

    @GET
    public List<PrescricaoDTO> listarTodas() {
        return Prescricao.<Prescricao>listAll().stream()
                .map(PrescricaoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Prescricao prescricao = Prescricao.findById(id);
        if (prescricao == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(PrescricaoMapper.toDTO(prescricao)).build();
    }

    @POST
    @Transactional
    public Response criar(PrescricaoDTO dto) {
        Prescricao p = PrescricaoMapper.toEntity(dto);
        p.persist();
        return Response.status(Response.Status.CREATED).entity(PrescricaoMapper.toDTO(p)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, PrescricaoDTO dto) {
        Prescricao existente = Prescricao.findById(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existente.medicamento = dto.medicamento;
        existente.posologia = dto.posologia;
        existente.dataHora = dto.dataHora;

        if (dto.pacienteId != null) {
            existente.paciente = com.vidaplus.model.Paciente.findById(dto.pacienteId);
        }

        if (dto.profissionalId != null) {
            existente.profissional = com.vidaplus.model.ProfissionalSaude.findById(dto.profissionalId);
        }

        return Response.ok(PrescricaoMapper.toDTO(existente)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Prescricao.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
