package com.vidaplus.resource;

import com.vidaplus.dto.ProfissionalSaudeDTO;
import com.vidaplus.dto.ProfissionalSaudeUpdateDTO;
import com.vidaplus.mapper.ProfissionalSaudeMapper;
import com.vidaplus.model.Prescricao;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.service.ProfissionalSaudeService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    @RolesAllowed({"ADMIN"})
    public List<ProfissionalSaudeDTO> listar() {
        List<ProfissionalSaude> profissionais = profissionalSaudeService.listar();
        Log.info("Listagem de profissionais da saúde realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return ProfissionalSaudeMapper.toDTOList(profissionais);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
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
    @RolesAllowed({"ADMIN"})
    public Response salvar(@Valid ProfissionalSaudeDTO dto) {
            ProfissionalSaude profissional = ProfissionalSaudeMapper.toEntity(dto);
            profissionalSaudeService.salvar(profissional);
            Log.info("Profissional da saúde ID %d criado com sucesso às %s.".formatted(profissional.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(ProfissionalSaudeMapper.toDTO(profissional)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response atualizar(@PathParam("id") Long id, @Valid ProfissionalSaudeUpdateDTO dto) {
        ProfissionalSaude profissionalAtualizado = profissionalSaudeService.atualizar(id, dto);

        if (profissionalAtualizado != null) {
            Log.info("Profissional da saúde ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(ProfissionalSaudeMapper.toDTO(profissionalAtualizado)).build();
        }

        Log.error("Profissional da saúde ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response deletar(@PathParam("id") Long id) {
        ProfissionalSaude profissionalSaude = profissionalSaudeService.buscarPorId(id);

        if (profissionalSaude == null) {
            Log.error("Profissional da saúde ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).entity("Profissional da saúde ID %d não encontrado.".formatted(id)).build();
        }

        profissionalSaudeService.deletar(id);
        Log.warn("Profissional da saúde ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}

