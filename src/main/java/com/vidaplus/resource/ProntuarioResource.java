package com.vidaplus.resource;

import com.vidaplus.dto.ProntuarioDTO;
import com.vidaplus.mapper.ProntuarioMapper;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.model.Prontuario;
import com.vidaplus.service.ProntuarioService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/prontuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProntuarioResource {

    @Inject
    ProntuarioService prontuarioService;

    @GET
    @RolesAllowed({"ADMIN", "PROFISSIONAL"})
    public List<ProntuarioDTO> listar() {
        List<Prontuario> prontuarios = prontuarioService.listar();
        Log.info("Listagem de prontuários realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return ProntuarioMapper.toDTOList(prontuarios);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "PROFISSIONAL"})
    public Response buscar(@PathParam("id") Long id) {
        Prontuario prontuario = prontuarioService.buscarPorId(id);
        if (prontuario != null) {
            Log.info("Prontuário ID %d buscado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(ProntuarioMapper.toDTO(prontuario)).build();
        }

        Log.error("Prontuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    @RolesAllowed({"ADMIN", "PROFISSIONAL"})
    public Response salvar(ProntuarioDTO dto) {
        try{
            Prontuario prontuario = ProntuarioMapper.toEntity(dto);
            prontuarioService.salvar(prontuario);
            Log.info("Prontuário ID %d criado com sucesso às %s.".formatted(prontuario.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(ProntuarioMapper.toDTO(prontuario)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar prontuário: %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN", "PROFISSIONAL"})
    public Response atualizar(@PathParam("id") Long id, ProntuarioDTO dto) {
        try {
            Prontuario prontuario = Prontuario.findById(id);
            if (prontuario != null) {
                Log.info("Prontuário ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
                return Response.ok(ProntuarioMapper.toDTO(prontuario)).build();

            }

            Log.error("Prontuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).entity("Prontuário não encontrado").build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar prontuário ID %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response deletar(@PathParam("id") Long id) {
        Prontuario prontuario = prontuarioService.buscarPorId(id);

        if (prontuario == null) {
            Log.error("Prontuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).entity("Prontuário ID %d não encontrado.".formatted(id)).build();
        }

        prontuarioService.deletar(id);
        Log.warn("Paciente ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
