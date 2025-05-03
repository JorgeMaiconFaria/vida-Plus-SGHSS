package com.vidaplus.resource;

import com.vidaplus.dto.ConsultaDTO;
import com.vidaplus.dto.ConsutaUpdateDTO;
import com.vidaplus.mapper.ConsultaMapper;
import com.vidaplus.model.Consulta;
import com.vidaplus.service.ConsultaService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaService consultaService;

    @GET
    public List<ConsultaDTO> listar() {
        List<Consulta> consultas = consultaService.listar();
        Log.info("Listagem de consultas realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return ConsultaMapper.toDTOList(consultas);
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Consulta consulta = consultaService.buscarPorId(id);
        if (consulta != null) {
            Log.info("Consulta ID %d buscada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(ConsultaMapper.toDTO(consulta)).build();
        } else {
            Log.error("Consulta ID %d não encontrada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Response salvar(ConsultaDTO dto) {
        try{
            Consulta consulta = ConsultaMapper.toEntity(dto);
            consultaService.salvar(consulta);
            Log.info("Consulta ID %d criada com sucesso às %s.".formatted(consulta.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).entity(ConsultaMapper.toDTO(consulta)).build();
        } catch (Exception e) {
            Log.error("Erro ao criar consulta: %s às %s".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}/status")
    @Transactional
    public Response atualizarStatus(@PathParam("id") Long id, @Valid ConsutaUpdateDTO dto) {
        try {
            Consulta consultaAtualizada = consultaService.atualizarStatus(id, dto);
            Log.info("Status da consulta ID %d atualizado para %s às %s.".formatted(id, dto.getStatus(), DateUtil.format(LocalDateTime.now())));
            return Response.ok(ConsultaMapper.toDTO(consultaAtualizada)).build();
        } catch (IllegalArgumentException e) {
            Log.error("Erro ao atualizar o status da Consulta: ".concat(e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST).entity("Status inválido: " + dto.getStatus()).build();
        }
    }

    @PUT
    @Path("/{id}/data")
    @Transactional
    public Response atualizarData(@PathParam("id") Long id, @Valid ConsutaUpdateDTO dto) {
        try {
            Consulta consultaAtualizada = consultaService.atualizarDataHora(id, dto);
            Log.info("Horário da consulta ID %d atualizado para %s às %s.".formatted(id, DateUtil.format(dto.getDataHora()), DateUtil.format(LocalDateTime.now())));
            return Response.ok(ConsultaMapper.toDTO(consultaAtualizada)).build();
        } catch (Exception e) {
            Log.error("Erro ao atualizar o horário da Consulta: ".concat(e.getMessage()));
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Data e hora inválida: " + dto.getDataHora())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        consultaService.deletar(id);
        Log.warn("Consulta ID %d deletada às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
