package com.vidaplus.resource;

import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.dto.UsuarioUpdateDTO;
import com.vidaplus.mapper.UsuarioMapper;
import com.vidaplus.model.Usuario;
import com.vidaplus.service.UsuarioService;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

    @GET
    @RolesAllowed({"ADMIN"})
    public Response listar(@PathParam("id") Long id) {
        List<Usuario> usuarios = usuarioService.listar();
        Log.info("Listagem de usuários realizada às %s.".formatted(DateUtil.format(LocalDateTime.now())));
        return Response.ok(UsuarioMapper.toDTOList(usuarios)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN"})
    public Response buscar(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            Log.info("Usuário ID %d buscado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.ok(UsuarioMapper.toDTO(usuario)).build();
        }

        Log.error("Usuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response salvar(@Valid UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
            usuarioService.salvar(usuario);
            Log.info("Usuário ID %d criado com sucesso às %s.".formatted(usuario.id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            Log.error("Erro ao criar usuário: %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response atualizar(@PathParam("id") Long id, UsuarioUpdateDTO dto) {
        try {
            Usuario usuarioAtualizado = usuarioService.atualizar(id, dto);
            if (usuarioAtualizado != null) {
                Log.info("Usuário ID %d atualizado com sucesso às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
                return Response.ok(UsuarioMapper.toDTO(usuarioAtualizado)).build();
            }

            Log.error("Usuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        } catch (Exception e) {
            Log.error("Erro ao criar usuário: %s às %s.".formatted(e.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"ADMIN"})
    public Response deletar(@PathParam("id") Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            Log.error("Usuário ID %d não encontrado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário ID %d não encontrado.".formatted(id)).build();
        }

        usuarioService.deletar(id);
        Log.warn("Usuário ID %d deletado às %s.".formatted(id, DateUtil.format(LocalDateTime.now())));
        return Response.noContent().build();
    }
}
