package com.vidaplus.resource;

import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.mapper.PacienteMapper;
import com.vidaplus.model.Paciente;
import com.vidaplus.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @GET
    public List<PacienteDTO> listarTodos() {
        return Paciente.listAll()
                .stream()
                .map(p -> PacienteMapper.toDTO((Paciente) p))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Paciente paciente = Paciente.findById(id);
        if (paciente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(PacienteMapper.toDTO(paciente)).build();
    }

    @POST
    @Transactional
    public Response criar(PacienteDTO dto) {
        Paciente paciente = PacienteMapper.toEntity(dto);
        paciente.persist();
        return Response.status(Response.Status.CREATED).entity(PacienteMapper.toDTO(paciente)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, PacienteDTO dto) {
        Paciente paciente = Paciente.findById(id);
        if (paciente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        paciente.nome = dto.nome;
        paciente.dataNascimento = dto.dataNascimento;
        paciente.cpf = dto.cpf;
        paciente.email = dto.email;
        paciente.telefone = dto.telefone;
        return Response.ok(PacienteMapper.toDTO(paciente)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Paciente.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
