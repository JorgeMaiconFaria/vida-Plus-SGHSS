package com.vidaplus.resource;

import com.vidaplus.model.Paciente;
import com.vidaplus.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    PacienteService pacienteService;

    @GET
    public List<Paciente> listarTodos() {
        return pacienteService.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            return Response.ok(paciente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(Paciente paciente) {
        pacienteService.salvar(paciente);
        return Response.status(Response.Status.CREATED).entity(paciente).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Paciente pacienteAtualizado) {
        Paciente pacienteExistente = pacienteService.buscarPorId(id);

        if (pacienteExistente != null) {
            // Atualize os campos necessários
            pacienteExistente.nome = pacienteAtualizado.nome;
            pacienteExistente.cpf = pacienteAtualizado.cpf;
            pacienteExistente.dataNascimento = pacienteAtualizado.dataNascimento;
            pacienteExistente.email = pacienteAtualizado.email;
            pacienteExistente.telefone = pacienteAtualizado.telefone;

            return Response.ok(pacienteExistente).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            pacienteService.deletar(id);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
