package com.codigo.Examen.controller;

import com.codigo.Examen.entity.PersonaEntity;
import com.codigo.Examen.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/personas/v1")
public class PersonaController {

    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        PersonaEntity nuevaPersona = personaService.guardarPersona(persona);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PersonaEntity> obtenerTodosLasPersonas() {
        return personaService.obtenerTodasLosPersona();
    }

    @GetMapping("/{numeroDocumento}")
    public ResponseEntity<PersonaEntity> obtenerPersona(@PathVariable String numeroDocumento) {
        PersonaEntity persona = personaService.obtenerPersonaPorDocum(numeroDocumento);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaEntity> actualizarPersona(@PathVariable Long id, @RequestBody PersonaEntity persona) {
        PersonaEntity personaActualizada = personaService.actualizarPersona(id, persona);
        return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}