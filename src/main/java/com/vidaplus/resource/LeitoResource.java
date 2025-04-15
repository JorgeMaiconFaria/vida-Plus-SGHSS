package com.vidaplus.resource;

import com.vidaplus.model.Leito;
import com.vidaplus.model.StatusLeito;
import com.vidaplus.service.LeitoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/leitos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeitoResource {

    @Inject
    LeitoService leitoService;

    @GET
    public List<Leito> listarTodos() {
        return leitoService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Leito leito = leitoService.buscarPorId(id);
        if (leito != null) {
            return Response.ok(leito).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(Leito leito) {
        leitoService.salvar(leito);
        return Response.status(Response.Status.CREATED).entity(leito).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Leito atualizado) {
        Leito existente = leitoService.buscarPorId(id);
        if (existente != null) {
            existente.numero = atualizado.numero;
            existente.status = atualizado.status;
            return Response.ok(existente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}/status")
    @Transactional
    public Response atualizarStatus(@PathParam("id") Long id,  Map<String, String> body) {
        Leito leito = Leito.findById(id);
        if (leito == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Leito não encontrado").build();
        }

        String status = body.get("status");
        if (status == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Campo 'status' é obrigatório.").build();
        }

        try {
            leito.status = StatusLeito.valueOf(status.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Status inválido. Use DISPONIVEL, OCUPADO ou MANUTENCAO.").build();
        }

        return Response.ok(leito).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        leitoService.deletar(id);
        return Response.noContent().build();
    }
}