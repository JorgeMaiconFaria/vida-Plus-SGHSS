package com.vidaplus.resource;

import com.vidaplus.model.Prontuario;
import com.vidaplus.model.Paciente;
import com.vidaplus.model.ProfissionalSaude;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/prontuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProntuarioResource {

    @GET
    public List<Prontuario> listar() {
        return Prontuario.listAll();
    }

    @GET
    @Path("/{id}")
    public Prontuario buscar(@PathParam("id") Long id) {
        return Prontuario.findById(id);
    }

    @POST
    @Transactional
    public Response criar(Prontuario prontuario) {
        Paciente paciente = Paciente.findById(prontuario.paciente.id);
        ProfissionalSaude profissional = ProfissionalSaude.findById(prontuario.profissional.id);

        if (paciente == null || profissional == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Paciente ou Profissional não encontrado.")
                    .build();
        }

        prontuario.paciente = paciente;
        prontuario.profissional = profissional;
        prontuario.persist();
        return Response.status(Response.Status.CREATED).entity(prontuario).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Prontuario atualizado) {
        Prontuario prontuarioExistente = Prontuario.findById(id);
        if (prontuarioExistente == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Prontuário não encontrado").build();
        }

        // Atualiza apenas os campos permitidos
        prontuarioExistente.descricao = atualizado.descricao;
        prontuarioExistente.data = atualizado.data;

        return Response.ok(prontuarioExistente).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Prontuario.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
