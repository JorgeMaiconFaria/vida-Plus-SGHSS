package com.vidaplus.resource;

import com.vidaplus.model.Usuario;
import com.vidaplus.service.JwtService;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    JwtService jwtService;

    public static class LoginRequest {
        public String email;
        public String senha;
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest login) {
        Usuario usuario = Usuario.find("email", login.email).firstResult();

        if (usuario == null || !usuario.getSenha().equals(login.senha)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Set<String> roles = new HashSet<>(usuario.getRoles());

        String token = Jwt.issuer("vidaplus-auth")
                .upn(usuario.getEmail())
                .groups(roles)
                .expiresIn(Duration.ofHours(2))
                .sign();

        return Response.ok().entity("{\"token\":\"" + token + "\"}").build();
    }
}
