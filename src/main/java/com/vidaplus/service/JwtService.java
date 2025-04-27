package com.vidaplus.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Duration;
import java.util.Date;

@ApplicationScoped
public class JwtService {

    public String gerarToken(String userEmail, String role) {
        // Criação do token JWT usando a chave privada
        return Jwt.issuer("vidaplus-auth") // Defina o emissor
                .upn("user-" + userEmail) // Identificador do usuário
                .groups(role) // Função do usuário
                .expiresIn(Duration.ofMinutes(30)) // Tempo de expiração
                .sign();
    }
}
