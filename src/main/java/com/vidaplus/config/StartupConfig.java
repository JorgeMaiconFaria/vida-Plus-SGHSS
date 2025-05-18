package com.vidaplus.config;

import com.vidaplus.model.Usuario;
import com.vidaplus.repository.UsuarioRepository;
import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class StartupConfig {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    void init(@Observes StartupEvent event) {
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha("admin123");
            admin.setRoles(List.of("ADMIN"));

            Log.warn("Usuário ADMIN criado para testes: email: %s, senha: %s.".formatted(admin.getEmail(), admin.getSenha()));
            usuarioRepository.persist(admin);
        }
    }
}
