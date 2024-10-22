package com.codigo.Examen.service.impl;

import com.codigo.Examen.entity.PedidoEntity;
import com.codigo.Examen.entity.PersonaEntity;
import com.codigo.Examen.repository.PedidoRepository;
import com.codigo.Examen.repository.PersonaRepository;
import com.codigo.Examen.service.PedidoService;
import com.codigo.Examen.service.PersonaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PedidoRepository pedidoRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository, PedidoRepository pedidoRepository) {
        this.personaRepository = personaRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public PersonaEntity guardarPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override
    public PersonaEntity obtenerPersonaPorId(Long id) {
        return personaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Persona no Encontrada"));
    }

    @Override
    public PersonaEntity obtenerPersonaPorDocum(String numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento).orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
    }

    @Override
    public List<PersonaEntity> obtenerTodasLosPersona() {
        return personaRepository.findAll();
    }

    public PersonaEntity actualizarPersona(Long id, PersonaEntity persona) {
        PersonaEntity personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

        // Actualiza todos los campos, no solo el nombre
        personaExistente.setNombres(persona.getNombres());
        personaExistente.setApellidos(persona.getApellidos());
        personaExistente.setNumeroDocumento(persona.getNumeroDocumento());
        personaExistente.setEstado(persona.getEstado());
        // También puedes actualizar la dirección y pedidos si es necesario

        return personaRepository.save(personaExistente); // Persistir cambios
    }



    private void gestionarPedidos(PersonaEntity personaExistente, List<PedidoEntity> pedidosActualizados) {
        List<PedidoEntity> pedidosExistentes = personaExistente.getPedidos();
        pedidosExistentes.clear();
        for (PedidoEntity pedido : pedidosActualizados) {
            if (pedido.getId() != null) {
                PedidoEntity pedidoEncontrado = pedidoRepository.findById(pedido.getId()).orElseThrow(() -> new NoSuchElementException("Error pedido no ubicado"));
                pedidoEncontrado.setDescripcion(pedido.getDescripcion());
                pedidosExistentes.add(pedidoEncontrado);
            } else {
                pedido.setPersona(personaExistente);
                pedidosExistentes.add(pedido);
            }
        }
    }

    @Override
    public void eliminarPersona(Long id) {
        PersonaEntity personaExistente = obtenerPersonaPorId(id);
        personaRepository.delete(personaExistente);
    }
}
