package com.vidaplus.resource;

import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.mapper.PacienteMapper;
import com.vidaplus.model.Paciente;

import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @GET
    //@RolesAllowed("ADMIN")
    public List<PacienteDTO> listarTodos() {
        Log.info("Listagem de pacientes realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return Paciente.listAll()
                .stream()
                .map(p -> PacienteMapper.toDTO((Paciente) p))
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    //@RolesAllowed({"ADMIN", "MEDICO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        Paciente paciente = Paciente.findById(id);
        if (paciente != null) {
            Log.info("Paciente ID %d buscado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(PacienteMapper.toDTO(paciente)).build();
        }
        Log.error("Paciente ID %d não encontrado às %s".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    //@RolesAllowed("ADMIN")
    public Response criar(PacienteDTO dto) {
        try{
            Paciente paciente = PacienteMapper.toEntity(dto);
            paciente.persist();
            Log.info("Paciente ID %d criada com sucesso às %s.".formatted(paciente.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(PacienteMapper.toDTO(paciente)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar paciente: %s às %s".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    //@RolesAllowed("ADMIN")
    public Response atualizar(@PathParam("id") Long id, PacienteDTO dto) {
        Paciente paciente = Paciente.findById(id);
        if (paciente == null) {
            Log.error("Paciente ID %d não encontrado às %s!".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try{
            paciente.nome = dto.nome;
            paciente.dataNascimento = dto.dataNascimento;
            paciente.cpf = dto.cpf;
            paciente.email = dto.email;
            paciente.telefone = dto.telefone;
            Log.info("Paciente ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(PacienteMapper.toDTO(paciente)).build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar paciente ID %s às %s".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        Paciente.deleteById(id);
        Log.warn("Paciente ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
