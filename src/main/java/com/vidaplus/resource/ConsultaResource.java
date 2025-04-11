package com.vidaplus.resource;

import com.vidaplus.model.Consulta;
import com.vidaplus.service.ConsultaService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService consultaService;

    @GET
    public List<Consulta> listarTodas() {
        return consultaService.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta != null) {
            return Response.ok(consulta).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(Consulta consulta) {
        consultaService.salvar(consulta);
        return Response.status(Response.Status.CREATED).entity(consulta).build();
    }

    @PUT
    @Path("/{id}/status")
    @Transactional
    public Response atualizarStatus(@PathParam("id") Long id, String novoStatus) {
        try {
            Consulta.StatusConsulta status = Consulta.StatusConsulta.valueOf(novoStatus.toUpperCase());
            consultaService.atualizarStatus(id, status);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Status inválido: " + novoStatus)
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        consultaService.deletar(id);
        return Response.noContent().build();
    }
}
