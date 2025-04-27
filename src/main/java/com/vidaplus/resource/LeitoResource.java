package com.vidaplus.resource;

import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.mapper.LeitoMapper;
import com.vidaplus.model.Leito;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Path("/leitos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeitoResource {

    @GET
    public List<LeitoDTO> listar() {
        Log.info("Listagem de leitos realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return Leito.<Leito>listAll()
                .stream()
                .map(LeitoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Leito leito = Leito.findById(id);
        if (leito != null){
            Log.info("Leito ID %d buscado às %s..".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(LeitoMapper.toDTO(leito)).build();
        }
        Log.error("Leito ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response criar(LeitoDTO dto) {
        Leito leito = LeitoMapper.toEntity(dto);
        leito.persist();
        return Response.status(Response.Status.CREATED).entity(LeitoMapper.toDTO(leito)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, LeitoDTO dto) {
        Leito leito = Leito.findById(id);
        if (leito == null) return Response.status(Response.Status.NOT_FOUND).build();

        LeitoMapper.updateEntity(leito, dto);
        return Response.ok(LeitoMapper.toDTO(leito)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Leito.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}