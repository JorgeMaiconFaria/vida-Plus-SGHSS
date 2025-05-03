package com.vidaplus.resource;

import com.vidaplus.dto.PacienteDTO;
import com.vidaplus.dto.PacienteUpdateDTO;
import com.vidaplus.mapper.PacienteMapper;
import com.vidaplus.model.Paciente;

import com.vidaplus.service.PacienteService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    @Inject
    PacienteService pacienteService;

    @GET
    //@RolesAllowed("ADMIN")
    public List<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteService.listar();
        Log.info("Listagem de pacientes realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return PacienteMapper.toDTOList(pacientes);
    }

    @GET
    @Path("/{id}")
    //@RolesAllowed({"ADMIN", "MEDICO"})
    public Response buscarPorId(@PathParam("id") Long id) {
        Paciente paciente = pacienteService.buscarPorId(id);
        if (paciente != null) {
            Log.info("Paciente ID %d buscado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(PacienteMapper.toDTO(paciente)).build();
        }

        Log.error("Paciente ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    //@RolesAllowed("ADMIN")
    public Response salvar(@Valid PacienteDTO dto) {
        Paciente paciente = PacienteMapper.toEntity(dto);
        pacienteService.salvar(paciente);
        Log.info("Paciente ID %d criada com sucesso às %s.".formatted(paciente.id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.CREATED).entity(PacienteMapper.toDTO(paciente)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    //@RolesAllowed("ADMIN")
    public Response atualizar(@PathParam("id") Long id,@Valid PacienteUpdateDTO dto) {
        Paciente pacienteAtualizado = pacienteService.atualizar(id, dto);
        if (pacienteAtualizado != null) {
            Log.info("Paciente ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(PacienteMapper.toDTO(pacienteAtualizado)).build();
        }

        Log.error("Paciente ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        pacienteService.deletar(id);
        Log.warn("Paciente ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
