package com.vidaplus.resource;

import com.vidaplus.dto.PrescricaoDTO;
import com.vidaplus.dto.PrescricaoUpdateDTO;
import com.vidaplus.mapper.PrescricaoMapper;
import com.vidaplus.model.Prescricao;
import com.vidaplus.service.PrescricaoService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/prescricoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescricaoResource {

    @Inject
    PrescricaoService prescricaoService;

    @GET
    public List<PrescricaoDTO> listar() {
        List<Prescricao> prescricoes = prescricaoService.listar();
        Log.info("Listagem de prescrições realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return PrescricaoMapper.toDTOList(prescricoes);
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Prescricao prescricao = prescricaoService.buscarPorId(id);

        if (prescricao != null) {
            Log.info("Prescrição ID %d buscada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(PrescricaoMapper.toDTO(prescricao)).build();
        }
        Log.error("Prescrição ID %d não encontrada às %s".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response salvar(PrescricaoDTO dto) {
        try {
            Prescricao prescricao = PrescricaoMapper.toEntity(dto);
            prescricaoService.salvar(prescricao);
            Log.info("Prescrição ID %d criada com sucesso às %s.".formatted(prescricao.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(PrescricaoMapper.toDTO(prescricao)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar prescrição: %s às %s".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, PrescricaoUpdateDTO dto) {
        Prescricao prescricao = prescricaoService.atualizar(id, dto);

        if (prescricao == null) {
            Log.error("Prescrição ID %d não encontrada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Log.info("Prescrição ID %d atualizada com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.ok(PrescricaoMapper.toDTO(prescricao)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        prescricaoService.deletar(id);
        Log.warn("Prescrição ID %d deletada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
