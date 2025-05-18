package com.vidaplus.mapper;

import com.vidaplus.util.DateUtil;
import io.quarkus.logging.Log;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Throwable causaRaiz = encontrarCausaRaiz(exception);

        if (causaRaiz instanceof SQLIntegrityConstraintViolationException
                || causaRaiz instanceof ConstraintViolationException) {
            String mensagemAmigavel = "Valor duplicado. Verifique se campos únicos já foram cadastrados.";
            Log.error("Erro ao criar entidade: %s às %s".formatted(causaRaiz.getMessage(), DateUtil.format(LocalDateTime.now())));
            return Response.status(Response.Status.CONFLICT)
                    .entity(mensagemAmigavel)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        Log.error("Erro ao criar entidade: %s às %s.".formatted(causaRaiz.getMessage(), DateUtil.format(LocalDateTime.now())));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro interno no servidor.")
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private Throwable encontrarCausaRaiz(Throwable e) {
        Throwable causa = e;
        while (causa.getCause() != null && causa != causa.getCause()) {
            causa = causa.getCause();
        }

        return causa;
    }
}