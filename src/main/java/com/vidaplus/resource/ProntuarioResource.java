package com.vidaplus.resource;

import com.vidaplus.dto.ProntuarioDTO;
import com.vidaplus.mapper.ProntuarioMapper;
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
    public List<ProntuarioDTO> listar() {
        return ProntuarioMapper.toDTOList(Prontuario.listAll());
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Prontuario prontuario = Prontuario.findById(id);
        if (prontuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ProntuarioMapper.toDTO(prontuario)).build();
    }

    @POST
    @Transactional
    public Response criar(ProntuarioDTO dto) {
        if (dto.pacienteId == null || dto.profissionalId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Paciente ou Profissional não informado.").build();
        }

        Prontuario prontuario = ProntuarioMapper.toEntity(dto);
        prontuario.persist();

        return Response.status(Response.Status.CREATED)
                .entity(ProntuarioMapper.toDTO(prontuario)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, ProntuarioDTO dto) {
        Prontuario prontuario = Prontuario.findById(id);
        if (prontuario == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Prontuário não encontrado").build();
        }

        prontuario.descricao = dto.descricao;
        prontuario.data = dto.data;

        return Response.ok(ProntuarioMapper.toDTO(prontuario)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Prontuario.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
