package com.vidaplus.resource;

import com.vidaplus.dto.LeitoDTO;
import com.vidaplus.dto.LeitoUpdateDTO;
import com.vidaplus.mapper.LeitoMapper;
import com.vidaplus.model.Leito;
import com.vidaplus.service.LeitoService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/leitos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LeitoResource {

    @Inject
    LeitoService leitoService;

    @GET
    public List<LeitoDTO> listar() {
        List<Leito> leitos = leitoService.listar();
        Log.info("Listagem de leitos realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return LeitoMapper.toDTOList(leitos);
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Leito leito = leitoService.buscarPorId(id);
        if (leito != null){
            Log.info("Leito ID %d buscado às %s..".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(LeitoMapper.toDTO(leito)).build();
        }
        Log.error("Leito ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    public Response salvar(LeitoDTO dto) {
        try {
            Leito leito = LeitoMapper.toEntity(dto);
            leitoService.salvar(leito);
            Log.info("Leito ID %d criado com sucesso às %s.".formatted(leito.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(LeitoMapper.toDTO(leito)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar leito: %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, @Valid LeitoUpdateDTO dto) {
        try {
            Leito leitoAtualizado = leitoService.atualizar(id, dto);

            if (leitoAtualizado == null) {
                Log.error("Leito ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Log.info("Leito ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(LeitoMapper.toDTO(leitoAtualizado)).build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar leito ID %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        leitoService.deletar(id);
        Log.warn("Leito ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}