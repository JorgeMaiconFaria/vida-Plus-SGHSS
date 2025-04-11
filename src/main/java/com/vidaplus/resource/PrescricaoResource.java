package com.vidaplus.resource;

import com.vidaplus.model.Prescricao;
import com.vidaplus.service.PrescricaoService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/prescricoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrescricaoResource {

    @Inject
    PrescricaoService prescricaoService;

    @GET
    public List<Prescricao> listarTodas() {
        return prescricaoService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Prescricao prescricao = prescricaoService.buscarPorId(id);
        if (prescricao != null) {
            return Response.ok(prescricao).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(Prescricao prescricao) {
        prescricaoService.salvar(prescricao);
        return Response.status(Response.Status.CREATED).entity(prescricao).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Prescricao nova) {
        Prescricao existente = prescricaoService.buscarPorId(id);
        if (existente != null) {
            existente.medicamento = nova.medicamento;
            existente.dosagem = nova.dosagem;
            existente.instrucoes = nova.instrucoes;
            existente.dataPrescricao = nova.dataPrescricao;
            return Response.ok(existente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        prescricaoService.deletar(id);
        return Response.noContent().build();
    }
}
