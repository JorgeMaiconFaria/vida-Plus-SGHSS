package com.vidaplus.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;

@ApplicationScoped
public class JwtService {

    public String gerarToken(String userEmail, String role) {
        return Jwt.issuer("vidaplus-auth") // Defina o emissor
                .upn("user-" + userEmail) // Identificador do usuário
                .groups(role) // Função do usuário
                .expiresIn(Duration.ofMinutes(30)) // Tempo de expiração
                .sign();
    }
}
