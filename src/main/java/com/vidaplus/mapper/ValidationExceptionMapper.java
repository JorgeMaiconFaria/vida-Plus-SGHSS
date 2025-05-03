package com.vidaplus.mapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<Map<String, String>> erros = exception.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String campo = violation.getPropertyPath().toString();
                    campo = campo.substring(campo.lastIndexOf('.') + 1); // extrai só o campo
                    Map<String, String> erro = new HashMap<>();
                    erro.put("campo", campo);
                    erro.put("mensagem", violation.getMessage());
                    return erro;
                })
                .collect(Collectors.toList());

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("erros", erros);

        return Response.status(Response.Status.BAD_REQUEST).entity(resposta).build();
    }
}
