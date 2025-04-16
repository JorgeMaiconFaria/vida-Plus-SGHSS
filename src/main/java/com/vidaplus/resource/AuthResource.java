package com.vidaplus.resource;

import com.vidaplus.auth.Usuario;
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

    @POST
    @Path("/login")
    public Response login(Usuario usuario) {
        // Aqui faremos uma validação simples (você pode depois conectar com o banco)
        if ("admin@vidaplus.com".equals(usuario.email) && "admin123".equals(usuario.senha)) {
            // Em vez de criar o token manualmente, utilizamos o JwtService
            String token = jwtService.gerarToken(usuario.email, "ADMIN");

            return Response.ok().entity("{\"token\":\"" + token + "\"}").build();
        }

        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
