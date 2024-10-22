package com.codigo.Examen.service;

import com.codigo.Examen.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity guardarPersona(PersonaEntity cliente);
    PersonaEntity obtenerPersonaPorDocum(String numeroDocumento);
    PersonaEntity obtenerPersonaPorId(Long id);
    List<PersonaEntity> obtenerTodasLosPersona();
    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);
    void eliminarPersona(Long id);
}
