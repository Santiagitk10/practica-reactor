package com.santi.practicareactor.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Persona {

    private Integer idPersona;
    private String nombres;
    private Integer edad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return idPersona.equals(persona.idPersona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPersona);
    }
}
