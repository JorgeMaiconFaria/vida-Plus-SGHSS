package com.vidaplus.resource;

import com.vidaplus.dto.ProntuarioDTO;
import com.vidaplus.dto.UsuarioDTO;
import com.vidaplus.mapper.ProntuarioMapper;
import com.vidaplus.mapper.UsuarioMapper;
import com.vidaplus.model.Prontuario;
import com.vidaplus.model.Usuario;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(UsuarioMapper.toDTO(usuario)).build();
    }

    @POST
    @Transactional
    public Response cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.toEntity(usuarioDTO);
        usuario.persist();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, UsuarioDTO dto) {
        Usuario usuario = Usuario.findById(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Prontuário não encontrado").build();
        }

        usuario.email = dto.email;
        usuario.senha = dto.senha;
        usuario.roles = dto.roles;

        return Response.ok(UsuarioMapper.toDTO(usuario)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletar(@PathParam("id") Long id) {
        boolean deleted = Prontuario.deleteById(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
