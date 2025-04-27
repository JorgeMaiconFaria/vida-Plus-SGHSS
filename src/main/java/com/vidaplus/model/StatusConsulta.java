package com.vidaplus.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusConsulta {
    AGENDADA,
    CANCELADA,
    REALIZADA;

    @JsonCreator
    public static StatusConsulta fromString(String status) {
        for (StatusConsulta statusConsulta : StatusConsulta.values()) {
            if (statusConsulta.name().equalsIgnoreCase(status)) {
                return statusConsulta;
            }
        }
        throw new IllegalArgumentException("Valor inesperado: " + status);
    }
}