package com.codigo.Examen.repository;

import com.codigo.Examen.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByNumeroDocumento(String numeroDocumento);
}
