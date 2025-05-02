package com.vidaplus.resource;

import com.vidaplus.dto.ProfissionalSaudeDTO;
import com.vidaplus.dto.ProfissionalSaudeUpdateDTO;
import com.vidaplus.mapper.ProfissionalSaudeMapper;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.service.ProfissionalSaudeService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/profissionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfissionalSaudeResource {

    @Inject
    ProfissionalSaudeService profissionalSaudeService;

    @GET
    public List<ProfissionalSaudeDTO> listar() {
        List<ProfissionalSaude> profissionais = profissionalSaudeService.listar();
        Log.info("Listagem de profissionais da saúde realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return ProfissionalSaudeMapper.toDTOList(profissionais);
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        ProfissionalSaude profissional = profissionalSaudeService.buscarPorId(id);

        if (profissional != null) {
            Log.info("Profissional da saúde ID %d buscado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(ProfissionalSaudeMapper.toDTO(profissional)).build();
        }

        Log.error("Profissional da saúde ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response salvar(ProfissionalSaudeDTO dto) {
        try {
            ProfissionalSaude profissional = ProfissionalSaudeMapper.toEntity(dto);
            profissionalSaudeService.salvar(profissional);
            Log.info("Profissional da saúde ID %d criado com sucesso às %s.".formatted(profissional.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(ProfissionalSaudeMapper.toDTO(profissional)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar profissional da saúde: %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, ProfissionalSaudeUpdateDTO dto) {
        try {
            ProfissionalSaude profissionalAtualizado = profissionalSaudeService.atualizar(id, dto);

            if (profissionalAtualizado != null) {
                Log.info("Profissional da saúde ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
                return Response.ok(ProfissionalSaudeMapper.toDTO(profissionalAtualizado)).build();
            }

            Log.error("Profissional da saúde ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar profissional da saúde ID %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        profissionalSaudeService.deletar(id);
        Log.warn("Profissional da saúde ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}

