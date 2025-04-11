package com.vidaplus.resource;

import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.service.ProfissionalSaudeService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/profissionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfissionalSaudeResource {

    @Inject
    ProfissionalSaudeService profissionalService;

    @GET
    public List<ProfissionalSaude> listarTodos() {
        return profissionalService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        ProfissionalSaude profissional = profissionalService.buscarPorId(id);
        if (profissional != null) {
            return Response.ok(profissional).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(ProfissionalSaude profissional) {
        profissionalService.salvar(profissional);
        return Response.status(Response.Status.CREATED).entity(profissional).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, ProfissionalSaude atualizado) {
        ProfissionalSaude existente = profissionalService.buscarPorId(id);
        if (existente != null) {
            existente.nome = atualizado.nome;
            existente.especialidade = atualizado.especialidade;
            existente.crmCoren = atualizado.crmCoren;
            existente.email = atualizado.email;
            existente.telefone = atualizado.telefone;
            return Response.ok(existente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        profissionalService.deletar(id);
        return Response.noContent().build();
    }
}

