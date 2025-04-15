package com.vidaplus.resource;

import com.vidaplus.dto.ProfissionalSaudeDTO;
import com.vidaplus.mapper.ProfissionalSaudeMapper;
import com.vidaplus.model.ProfissionalSaude;
import com.vidaplus.service.ProfissionalSaudeService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/profissionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfissionalSaudeResource {

    @GET
    public List<ProfissionalSaudeDTO> listar() {
        List<ProfissionalSaude> profissionais = ProfissionalSaude.listAll();
        return ProfissionalSaudeMapper.toDTOList(profissionais);
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        ProfissionalSaude profissional = ProfissionalSaude.findById(id);
        if (profissional == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ProfissionalSaudeMapper.toDTO(profissional)).build();
    }

    @POST
    @Transactional
    public Response criar(ProfissionalSaudeDTO dto) {
        ProfissionalSaude profissional = ProfissionalSaudeMapper.toEntity(dto);
        profissional.persist();
        return Response.status(Response.Status.CREATED).entity(ProfissionalSaudeMapper.toDTO(profissional)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, ProfissionalSaudeDTO dto) {
        ProfissionalSaude profissional = ProfissionalSaude.findById(id);
        if (profissional == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        profissional.nome = dto.nome;
        profissional.crmCoren = dto.crmCoren;
        profissional.especialidade = dto.especialidade;
        profissional.email = dto.email;
        profissional.telefone = dto.telefone;
        profissional.criadoEm = dto.criadoEm;

        return Response.ok(ProfissionalSaudeMapper.toDTO(profissional)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = ProfissionalSaude.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}

